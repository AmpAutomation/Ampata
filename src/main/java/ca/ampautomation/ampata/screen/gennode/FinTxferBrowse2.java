package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.components.textfieldwithbutton.TextFieldWithButton;
import ca.ampautomation.ampata.entity.FinFmla;
import ca.ampautomation.ampata.entity.FinRate;
import ca.ampautomation.ampata.entity.GenNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@UiController("ampata_FinTxfer.browse2")
@UiDescriptor("fin-txfer-browse2.xml")
@LookupComponent("table")
public class FinTxferBrowse2 extends MasterDetailScreen<GenNode> {


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
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> genNodesDc;

    @Autowired
    private InstanceContainer<GenNode> genNodeDc;

    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextFieldWithButton id2FieldWithButton;

    @Autowired
    private TextField id2CalcField;

    Logger logger = LoggerFactory.getLogger(FinTxferBrowse2.class);

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinTxfer");
    }

    @Subscribe("updateDesc1Btn")
    public void onupdateDesc1Btn(Button.ClickEvent event) {
        updateDesc1();
    }

    @Subscribe("id2FieldWithButton")
    public void onId2FieldWithButtonClick(Button.ClickEvent event) {
        String logPrfx = "onId2FieldWithButtonClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfer.setId2(thisFinTxfer.getId2Calc());

        logger.debug(logPrfx + " --- id2: " + thisFinTxfer.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("finTxfer1_EI1_RateBtn")
    public void onFinTxfer1_EI1_RateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFinTxfer1_EI1_RateBtnClick";
        logger.trace(logPrfx + " --> ");

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        String qry = "select ft from ampata_FinRate ft"
                + " where ft.finCurcy1_Id.id = :curcyFr1"
                + " and   ft.finCurcy2_Id.id = :curcyTo2"
                + " and   ft.beg.date1 = :date1";

        logger.debug(logPrfx + " --- qry: " +  qry);

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UUID curcyFrId = thisFinTxfer.getFinTxfer1_Id().getFinCurcy1_Id().getId();
        if (curcyFrId == null) {
            logger.debug(logPrfx + " --- curcyFrId: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxfer. Please ensure a Fin/Txfer.Id is selected it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- curcyFrId: " + curcyFrId);

        }

        UUID curcyToId = thisFinTxfer.getFinCurcy1_Id().getId();
        if (curcyToId == null) {
            logger.debug(logPrfx + " --- curcyToId: null");
            notifications.create().withCaption("Unable to get the currency from this FinTxfer. Please ensure a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- curcyToId: " + curcyToId);

        }


        Date date1 = thisFinTxfer.getBeg().getDate1();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (date1 == null) {
            logger.debug(logPrfx + " --- date1: null");
            notifications.create().withCaption("Unable to get the date from this FinTxfer. Please ensure a date is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- date1: " + formatter.format(date1));

        }

        FinRate finRate;
        try {
            finRate = dataManager.load(FinRate.class)
                    .query("select ft from ampata_FinRate ft"
                            + " where ft.finCurcy1_Id.id = :curcyFr"
                            + " and   ft.finCurcy2_Id.id = :curcyTo"
                            + " and   ft.beg.date1 = :date1"
                    )
                    .parameter("curcyFr", curcyFrId)
                    .parameter("curcyTo", curcyToId)
                    .parameter("date1", date1)
                     .one()
                    ;

        }catch(java.lang.IllegalStateException e){
            logger.debug(logPrfx + " --- qry1 java.lang.IllegalStateException: " + e.getMessage());
            finRate = null;
        }


        if (finRate != null) {
            rate = finRate.getRate();
            qryRsltGood = true;
            logger.debug(logPrfx + " --- qry1 result is Id: " + finRate.getId());

        } else {
            logger.debug(logPrfx + " --- qry1 result is empty");
            try {

                finRate = dataManager.load(FinRate.class)
                        .query("select ft from ampata_FinRate ft "
                                + " where ft.finCurcy1_Id.id = :curcyFr"
                                + " and   ft.finCurcy2_Id.id = :curcyTo"
                                + " and   ft.beg.date1 = :date1"
                        )
                        .parameter("curcyFr", curcyToId)
                        .parameter("curcyTo", curcyFrId)
                        .parameter("date1", date1)
                        .one()
                ;
            }catch(java.lang.IllegalStateException e){
                logger.debug(logPrfx + " --- qry2 java.lang.IllegalStateException: " + e.getMessage());
                finRate = null;
            }

            if (finRate != null) {
                rate = finRate.getRate();
                qryRsltGood = true;
                logger.debug(logPrfx + " --- qry2 result is Id: " + finRate.getId());
            } else {
                logger.debug(logPrfx + " --- qry2 result is empty");
            }
        }

        if (qryRsltGood) {
            thisFinTxfer.setFinTxfer1_EI1_Rate(rate);
            logger.debug(logPrfx + " --- rate: " + rate);
        }
        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("calcRsltBtn")
    public void onCalcRsltBtnClick(Button.ClickEvent event) {
        String logPrfx = "onCalcRsltBtnClick";
        logger.trace(logPrfx + " --> ");

        BigDecimal rslt = BigDecimal.valueOf(0);
        boolean rsltGood = false;

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        FinFmla fmla1 = thisFinTxfer.getFinFmla1_Id();
        if (fmla1 == null) {
            logger.debug(logPrfx + " --- fmla1_Id: null");
            notifications.create().withCaption("Unable to get the finFmla1. Please finFmla1 has a formula selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- fmla1_Id: " + fmla1.getId());

        }


        // Switch statement over above string
        switch (fmla1.getId2()) {

            // Ref1 Mult Exch_Rate
            case "Ref1 Mult Exch_Rate":

                GenNode finTxfer1 = thisFinTxfer.getFinTxfer1_Id();
                if (finTxfer1 == null) {
                    logger.debug(logPrfx + " --- finTxfer1_Id: null");
                    notifications.create().withCaption("Unable to get finTxfer1. Please ensure  finTxfer1 has a finTxfer selected.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }else{
                    logger.debug(logPrfx + " --- finTxfer1_Id: " + finTxfer1.getId());
                }

                BigDecimal val = finTxfer1.getAmtNet();
                if (val == null) {
                    logger.debug(logPrfx + " --- finTxfer1_AmtNet: null");
                    notifications.create().withCaption("Unable to get finTxfer1_AmtNet. Please ensure  finTxfer1_AmtNet has a value.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }else{
                    logger.debug(logPrfx + " --- finTxfer1_AmtNet: " + val);
                }

                BigDecimal rate = thisFinTxfer.getFinTxfer1_EI1_Rate();
                rslt = val.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP).negate();
                rsltGood = true;
                break;

            default:
                logger.debug(logPrfx + " --- formula not implemented: " + fmla1.getId2());
                notifications.create().withCaption("Formula not implemented: " + fmla1.getId2()).show();
                logger.trace(logPrfx + " <-- ");
                return;
        }

        if (rsltGood) {
            thisFinTxfer.setCalcRslt(rslt);
            logger.debug(logPrfx + " --- calcRslt: " + rslt);
        }
        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("calcAmtNetBtn")
    public void onCalcAmtNetBtn(Button.ClickEvent event) {
        String logPrfx = "onCalcAmtNetBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        GenNode thisFinAcct = thisFinTxfer.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        GenNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();
        BigDecimal netAmt = BigDecimal.valueOf(0);

        if (thisFinTxfer.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                netAmt = netAmt.subtract(thisFinTxfer.getAmtDebt());
            } else {
                netAmt = netAmt.add(thisFinTxfer.getAmtDebt());
            }
        }

        if (thisFinTxfer.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                netAmt = netAmt.subtract(thisFinTxfer.getAmtCred());
            } else {
                netAmt = netAmt.add(thisFinTxfer.getAmtCred());
            }
        }
        thisFinTxfer.setAmtNet(netAmt);
        logger.debug(logPrfx + " --- amtNet: " + netAmt);
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        table.getSelected().stream()
//                .map(this::makeCopy)
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode savedCopy = dataManager.save(copy);
                    genNodesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinTxfer " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );

/*
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode trackedCopy = dataContext.merge(copy);
                    dataContext.commit();
                    genNodesDc.getMutableItems().add(trackedCopy);
                    logger.debug("Duplicated FinTxfer " + orig.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );
*/

                });
    }


    private GenNode makeCopy(GenNode orig) {
        String logPrfx = "makeCopy";
        logger.trace(logPrfx + " --> ");
        GenNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        copy.setIdZ(copy.getIdZ() + 1);
        copy.setId2(copy.getId2CalcFrFields());
        copy.setId2Calc(copy.getId2CalcFrFields());
        logger.trace(logPrfx + " <--- ");
        return copy;
    }





    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

/*
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
*/
        logger.trace(logPrfx + " <--- ");

    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " -- Changed entity: " + event.getEntity());
        logger.trace(logPrfx + " <--- ");

    }

    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {

        updateId2Calc();
    }

    @Subscribe("idYField")
    public void onIdYFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {

        updateId2Calc();
    }

    @Subscribe("idZField")
    public void onIdZFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {

        updateId2Calc();

    }

    @Subscribe("BegDate1Field")
    public void onBegDate1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {

        updateId2Calc();

    }


    @Subscribe("BegTime1Field")
    public void onBegTime1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {

        updateId2Calc();
    }

    @Subscribe("finTxact1_BegDate1Field")
    public void onFinTxact1_BegDate1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {

        updateId2Calc();
    }

    @Subscribe("finTxact1_BegTime1Field")
    public void onFinTxact1_BegTime1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {

        updateId2Calc();
    }


    @Subscribe("id2FieldWithButton")
    public void onId2FieldWithBUttonValueChange(HasValue.ValueChangeEvent<String> event) {
        updateId2Cmp();
        updateId2Dup();
    }
    @Subscribe("id2CalcField")
    public void onId2CalcFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        updateId2Cmp();
    }

    public void updateId2Calc(){
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfer.setId2Calc(thisFinTxfer.getId2CalcFrFields());

        logger.debug(logPrfx + " --- id2Calc: " + thisFinTxfer.getId2Calc());
        logger.trace(logPrfx + " <-- ");

    }

    public void updateId2Cmp() {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfer.setId2Cmp(!thisFinTxfer.getId2().equals(thisFinTxfer.getId2Calc()));

        logger.debug(logPrfx + " --- id2Cmp: " + thisFinTxfer.getId2Cmp());
        logger.trace(logPrfx + " <-- ");

    }
    public void updateId2Dup() {
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        if (thisFinTxfer.getId2() != null){
            Integer id2Dup = dataManager.loadValue("select count(e) from ampata_GenNode e where e.className = 'FinTxfer' and e.id2 = :id2",Integer.class)
                    .store("main")
                    .parameter("id2",thisFinTxfer.getId2())
                    .one();
            thisFinTxfer.setId2Dup(id2Dup);

            logger.debug(logPrfx + " --- id2Dup: " + id2Dup);

        }
        logger.trace(logPrfx + " <-- ");
    }

    public void updateDesc1(){

        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = genNodeDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String desc1 ="";
        //thisFinTxfer.setDesc1(desc1);

        logger.debug(logPrfx + " --- desc1: " + desc1);
        logger.trace(logPrfx + " <-- ");
    }
}