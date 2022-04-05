package ca.ampautomation.ampata.screen.gennode;

import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.core.UuidProvider;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@UiController("ampata_FinStmt.browse2")
@UiDescriptor("fin-stmt-browse2.xml")
@LookupComponent("table")
public class FinStmtBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private Notifications notifications;

    @Autowired
    private GroupTable<GenNode> table;

    @Autowired
    private GroupTable<GenNode> table2;

    @Autowired
    private Form form;

    @Autowired
    private CollectionContainer<GenNode> finStmtsDc;

    @Autowired
    private InstanceContainer<GenNode> finStmtDc;

    @Autowired
    private CollectionLoader<GenNode> finStmtsDl;

    @Autowired
    private CollectionLoader<GenNode> finTxfersDl;

    Logger logger = LoggerFactory.getLogger(FinStmtBrowse2.class);

    @Autowired
    private TextField<String> debtSumCalcField;

    @Autowired
    private TextField<String> debtSumDiffField;

    @Autowired
    private TextField<String> credSumCalcField;

    @Autowired
    private TextField<String> credSumDiffField;

    @Autowired
    private TextField<String> netSumCalcField;

    @Autowired
    private TextField<String> netSumDiffField;

    @Autowired
    private Button finStmtHideBtn;

    @Autowired
    private Button finTxferHideBtn;

    @Autowired
    private ComboBox<String> statusField;

    @Subscribe
    public void onInit(InitEvent event) {
        List<String> list = new ArrayList<>();
        list.add("Reconciled");
        list.add("Non-Reconciled");
        statusField.setOptionsList(list);
    }

    @Subscribe("finStmtHideBtn")
    public void onFinStmtHideBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFinStmtHideBtnClick";
        logger.trace(logPrfx + " --> ");
        if (form.isVisible()){
            form.setVisible(false);
            finStmtHideBtn.setCaption("Show Stmt Details");
        }else{
            form.setVisible(true);
            finStmtHideBtn.setCaption("Hide Stmt Details");
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finStmtUpdateAllCalcBtn")
    public void onFinStmtUpdateAllCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFinStmtUpdateAllCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAllCalc(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxferHideBtn")
    public void onFinTxferHideBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFinTxferHideBtnClick";
        logger.trace(logPrfx + " --> ");

        if (table2.isVisible()){
            table2.setVisible(false);
            finTxferHideBtn.setCaption("Show Txfers");
        }else{
            table2.setVisible(true);
            finTxferHideBtn.setCaption("Hide Txfers");
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        finStmtsDl.load();
    }

    @Subscribe(id = "finStmtsDc", target = Target.DATA_CONTAINER)
    public void onFinStmtsDcCollectionChange(CollectionContainer.CollectionChangeEvent<GenNode> event) {
        String logPrfx = "onFinStmtsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "finStmtsDc", target = Target.DATA_CONTAINER)
    public void onFinStmtsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinStmtsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = event.getItem();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinStmt.setClassName("FinStmt");
        logger.debug(logPrfx + " --- className: FinStmt");

        finTxfersDl.setParameter("finStmt1_Id", event.getItem());
        finTxfersDl.load();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");
        table.getSelected()
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode savedCopy = dataManager.save(copy);
                    finStmtsDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinStmt " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );
                });
        logger.trace(logPrfx + " <-- ");
    }

    private GenNode makeCopy(GenNode orig) {
        String logPrfx = "makeCopy";
        logger.trace(logPrfx + " --> ");

        GenNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        copy.setId2(copy.getId2() + " Copy");
        copy.setId2Calc(copy.getId2() + " Copy");

        logger.trace(logPrfx + " <--- ");
        return copy;
    }

    @Subscribe("begBalField")
    public void onBegBalFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "begBalField";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateEndBal(thisFinStmt);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "finStmtDc", target = Target.DATA_CONTAINER)
    public void onFinStmtDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinStmtDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = event.getSource().getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmt.setClassName("FinStmt");
        updateAllCalc(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("credSumField")
    public void onCredSumFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onCredSumFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateSumNet(thisFinStmt);
        updateEndBal(thisFinStmt);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("debtSumField")
    public void onDebtSumFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onDebtSumFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finStmtDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateSumNet(thisFinStmt);
        updateEndBal(thisFinStmt);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("finAcct1_IdField")
    public void onFinAcct1_IdFieldValueChange(HasValue.ValueChangeEvent<GenNode> event) {
        String logPrfx = "onFinAcct1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finStmtDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinAcct1_Type1_Id2(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    public void updateAllCalc(GenNode thisFinStmt){
        String logPrfx = "updateAllCalc";
        logger.trace(logPrfx + " --> ");

        updateFinAcct1_Type1_Id2(thisFinStmt);
        updateSumNet(thisFinStmt);
        updateEndBal(thisFinStmt);
        updateFieldSet1(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    public void updateEndBal(GenNode thisFinStmt){
        String logPrfx = "updateEndBal";
        logger.trace(logPrfx + " --> ");

        BigDecimal endBal = BigDecimal.valueOf(0);
        if (thisFinStmt.getBegBal() == null || thisFinStmt.getNetSum() == null){
            thisFinStmt.setEndBal(null);
            logger.debug(logPrfx + " --- endBal: null");
            return;
        }
        endBal = thisFinStmt.getBegBal().add(thisFinStmt.getNetSum());
        thisFinStmt.setEndBal(endBal);
        logger.debug(logPrfx + " --- endBal: " + endBal);

        logger.trace(logPrfx + " <-- ");
    }

    public void updateSumNet(GenNode thisFinStmt){
        String logPrfx = "updateSumNet";
        logger.trace(logPrfx + " --> ");

        BigDecimal netSum = BigDecimal.valueOf(0);
        if (thisFinStmt.getDebtSum() == null || thisFinStmt.getCredSum() == null){
            thisFinStmt.setNetSum(null);
            logger.debug(logPrfx + " --- netSum: null");
            return;
        }

        if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
            netSum = thisFinStmt.getDebtSum().subtract(thisFinStmt.getCredSum());

        }else{
            netSum = thisFinStmt.getCredSum().subtract(thisFinStmt.getDebtSum());
        }
        thisFinStmt.setNetSum(netSum);
        logger.debug(logPrfx + " --- netSum: " + netSum);
        logger.trace(logPrfx + " <-- ");
    }

    public void updateFinAcct1_Type1_Id2(GenNode thisFinStmt){
        String logPrfx = "updateFinAcct1_Type1_Id2";
        logger.trace(logPrfx + " --> ");

        String finAcct1_Type1_Id2;
        if (thisFinStmt.getFinAcct1_Id() == null
                || thisFinStmt.getFinAcct1_Id().getType1_Id() == null){
            thisFinStmt.setFinAcct1_Type1_Id2(null);
            logger.debug(logPrfx + " --- finAcct1_Type1_Id2: null");

        }else{
            finAcct1_Type1_Id2 = thisFinStmt.getFinAcct1_Id().getType1_Id().getId2();
            thisFinStmt.setFinAcct1_Type1_Id2(finAcct1_Type1_Id2);
            logger.debug(logPrfx + " --- finAcct1_Type1_Id2: " + finAcct1_Type1_Id2);
        }

        logger.trace(logPrfx + " <-- ");
    }

    public void updateFieldSet1(GenNode thisFinStmt) {
        String logPrfx = "updateFieldSet1";
        logger.trace(logPrfx + " --> ");

        BigDecimal debtSumCalc = null;
        BigDecimal debtSumDiff = null;
        try{
            debtSumCalc = dataManager.loadValue("select sum(e.amtDebt) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmt1_Id = :finStmt1_Id group by e.finStmt1_Id",BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id",thisFinStmt)
                    .one();
            debtSumCalcField.setValue(debtSumCalc.toString());
            logger.debug(logPrfx + " --- debtSumCalc: " + debtSumCalc);

            if (thisFinStmt.getDebtSum() == null){
                debtSumDiffField.setValue(null);
                logger.debug(logPrfx + " --- debtSumDiff: null");
            }else{
                debtSumDiff = thisFinStmt.getDebtSum().subtract(debtSumCalc);
                debtSumDiffField.setValue(debtSumDiff.toString());
                logger.debug(logPrfx + " --- debtSumDiff: " + debtSumDiff);
            }
        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            debtSumCalcField.setValue(null);
            logger.debug(logPrfx + " --- debtSumCalc: null");
            debtSumDiffField.setValue(null);
            logger.debug(logPrfx + " --- debtSumDiff: null");
        }

        BigDecimal credSumCalc = null;
        BigDecimal credSumDiff;
        try {
            credSumCalc = dataManager.loadValue("select sum(e.amtCred) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmt1_Id = :finStmt1_Id group by e.finStmt1_Id", BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id", thisFinStmt)
                    .one();
            credSumCalcField.setValue(credSumCalc.toString());
            logger.debug(logPrfx + " --- credSumCalc: " + credSumCalc);

            if (thisFinStmt.getDebtSum() == null){
                credSumDiffField.setValue(null);
                logger.debug(logPrfx + " --- credSumDiff: null");
            }else{
                credSumDiff = thisFinStmt.getCredSum().subtract(credSumCalc);
                credSumDiffField.setValue(credSumDiff.toString());
                logger.debug(logPrfx + " --- credSumDiff: " + credSumDiff);
            }
        } catch (IllegalStateException e ){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            credSumCalcField.setValue(null);
            logger.debug(logPrfx + " --- credSumCalc: null");
            credSumDiffField.setValue(null);
            logger.debug(logPrfx + " --- credSumDiff: null");
        }

        BigDecimal netSumCalc = null;
        BigDecimal netSumDiff = null;
        if (debtSumCalc == null || credSumCalc == null){
            netSumCalcField.setValue(null);
            logger.debug(logPrfx + " --- netSumCalc: null");
            netSumDiffField.setValue(null);
            logger.debug(logPrfx + " --- netSumDiff: null");
        }else{

            if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                    && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                netSumCalc = debtSumCalc.subtract(credSumCalc);
            }else{
                netSumCalc = credSumCalc.subtract(debtSumCalc);
            }
            netSumCalcField.setValue(netSumCalc.toString());
            logger.debug(logPrfx + " --- netSumCalc: " + netSumCalc);

            netSumDiff = thisFinStmt.getNetSum().subtract(netSumCalc);
            netSumDiffField.setValue(netSumDiff.toString());
            logger.debug(logPrfx + " --- netSumDiff: " + netSumDiff);

        }

        logger.trace(logPrfx + " <-- ");
    }


}