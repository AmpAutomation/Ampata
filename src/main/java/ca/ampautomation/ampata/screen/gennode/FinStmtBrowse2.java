package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.GenNodeType;
import io.jmix.core.*;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@UiController("ampata_FinStmt.browse2")
@UiDescriptor("fin-stmt-browse2.xml")
@LookupComponent("table")
public class FinStmtBrowse2 extends MasterDetailScreen<GenNode> {
    @Autowired
    private DataComponents dataComponents;

    @Autowired
    private FetchPlans fetchPlans;

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<GenNode> table;

    @Autowired
    private GroupTable<GenNode> table2;

    @Autowired
    private Form form;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> finStmtsDc;

    @Autowired
    private CollectionLoader<GenNode> finStmtsDl;

    @Autowired
    private InstanceContainer<GenNode> finStmtDc;

    @Autowired
    private CollectionContainer<GenNode> finTxfersDc;

    @Autowired
    private CollectionLoader<GenNode> finTxfersDl;


    private CollectionContainer<GenNodeType> finStmtTypesDc;

    private CollectionLoader<GenNodeType> finStmtTypesDl;


    private CollectionContainer<GenNode> genChansDc;

    private CollectionLoader<GenNode> genChansDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genChan1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;



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
        String logPrfx = "onFinStmtHideBtnClick";
        logger.trace(logPrfx + " --> ");

        List<String> list = new ArrayList<>();
        list.add("Reconciled");
        list.add("Non-Reconciled");
        statusField.setOptionsList(list);


        finStmtTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finStmtTypesDl = dataComponents.createCollectionLoader();
        finStmtTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtTypesDl.setFetchPlan(finStmtTypesFp);
        finStmtTypesDl.setContainer(finStmtTypesDc);
        finStmtTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finStmtTypesDc);


        genChansDc = dataComponents.createCollectionContainer(GenNode.class);
        genChansDl = dataComponents.createCollectionLoader();
        genChansDl.setQuery("select e from ampata_GenNode e where e.className = 'GenChan' order by e.id2");
        FetchPlan genChansFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genChansDl.setFetchPlan(genChansFp);
        genChansDl.setContainer(genChansDc);
        genChansDl.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(genChansDc);


        genDocVersDc = dataComponents.createCollectionContainer(GenNode.class);
        genDocVersDl = dataComponents.createCollectionLoader();
        genDocVersDl.setQuery("select e from ampata_GenNode e where e.className = 'GenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genDocVersDl.setFetchPlan(genDocVersFp);
        genDocVersDl.setContainer(genDocVersDc);
        genDocVersDl.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(genDocVersDc);


        genTagsDc = dataComponents.createCollectionContainer(GenNode.class);
        genTagsDl = dataComponents.createCollectionLoader();
        genTagsDl.setQuery("select e from ampata_GenNode e where e.className = 'GenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genTagsDl.setFetchPlan(genTagsFp);
        genTagsDl.setContainer(genTagsDc);
        genTagsDl.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(genTagsDc);

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

    @Subscribe("updateAllFrontEndCalcBtn")
    public void onUpdateAllFrontEndCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAllFrontEndCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAllFrontEndCalc(thisFinStmt);

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
        updateAllFrontEndCalc(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "table.[beg.date1]", subject = "formatter")
    private String tableBegDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Install(to = "table.[end.date1]", subject = "formatter")
    private String tableEndDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Install(to = "table2.[beg.date1]", subject = "formatter")
    private String table2BegDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Install(to = "table2.[finTxact1_BegDate1]", subject = "formatter")
    private String table2FinTxact1_BegDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
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

    public void updateAllFrontEndCalc(GenNode thisFinStmt){
        String logPrfx = "updateAllFrontEndCalc";
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