package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.components.textfieldwithbutton.TextFieldWithButton;
import ca.ampautomation.ampata.entity.FinFmla;
import ca.ampautomation.ampata.entity.FinRate;
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
import java.math.MathContext;
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
    private TextFieldWithButton id2CalcFieldWithButton;

    Logger logger = LoggerFactory.getLogger(FinTxferBrowse2.class);

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinTxfer");
    }

    @Subscribe("id2FieldWithButton")
    public void onId2FieldWithButtonClick(Button.ClickEvent event) {
        logger.trace("onId2FieldWithButtonClick --> ");
        logger.debug("onId2FieldWithButtonClick --- ");
        logger.trace("onId2FieldWithButtonClick <-- ");
    }

    @Subscribe("id2CalcFieldWithButton")
    public void onId2CalcFieldWithButtonClick(Button.ClickEvent event) {
        logger.trace("onId2CalcFieldWithButtonClick --> ");
        logger.debug("onId2CalcFieldWithButtonClick --- ");
        logger.trace("onId2CalcFieldWithButtonClick <-- ");
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

    @Autowired
    private MetadataTools metadataTools;


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        table.getSelected().stream()
/*
                .map(this::makeCopy)
                .forEach(copy -> {
                    GenNode savedCopy = dataManager.save(copy);
                    genNodesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinTxfer " + copy.getId2() + " "
//                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );
*/

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

                });
    }


/*
    private GenNode makeCopy(GenNode orig) {
        GenNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
*/



    private GenNode makeCopy(GenNode orig) {

            GenNode copy = metadata.create(GenNode.class);
            // Can't use metadataTools because when the copy method tries to access attributes
            // not included in the data container it raises an exception
            //GenNode finTxfer = metadataTools.copy(orig);

            copy.setId(UuidProvider.createUuid());
            // fetch plan genNode-fetch-plan-base
            copy.setId2(orig.getId2());
            copy.setId2Calc(orig.getId2Calc());
            copy.setId2Cmp(orig.getId2Cmp());
            copy.setId2Dup(orig.getId2Dup());
            copy.setClassName(orig.getClassName());
            copy.setSortOrder(orig.getSortOrder());
//            copy.setType1_Id(orig.getType1_Id());
            copy.setType1_Id2(orig.getType1_Id2());
            copy.setInst(orig.getInst());
            copy.setName1(orig.getName1());
            copy.setName1Pat1_Id(orig.getName1Pat1_Id());
            copy.setName1Pat1_Id2(orig.getName1Pat1_Id2());
            copy.setName2(orig.getName2());
            copy.setAbrv(orig.getAbrv());
            copy.setDesc1(orig.getDesc1());

            // fetch plan genNode-fetch-plan-base extends genNode-fetch-plan-lean
            copy.setParent1_Id(orig.getParent1_Id());
            copy.setParent1_Id2(orig.getParent1_Id2());
            copy.setAncestors1_Id2(orig.getAncestors1_Id2());
            copy.setGenDocVers1_Id2(orig.getGenDocVers1_Id2());
            copy.setGenDocVer1_Id(orig.getGenDocVer1_Id());
            copy.setGenDocVer1_Id2(orig.getGenDocVer1_Id2());
            copy.setGenFile1_Id(orig.getGenFile1_Id());
            copy.setGenFile1_Id2(orig.getGenFile1_Id2());
            copy.setGenFile1_URI(orig.getGenFile1_URI());
            copy.setGenTags1_Id2(orig.getGenTags1_Id2());
            copy.setGenTag1_Id(orig.getGenTag1_Id());
            copy.setGenTag1_Id2(orig.getGenTag1_Id2());

            // fetch plan finTxfer-fetch-plan-base extends genNode-fetch-plan-base
            copy.setGenTag2_Id(orig.getGenTag2_Id());
            copy.setGenTag2_Id2(orig.getGenTag2_Id2());
            copy.setGenTag3_Id(orig.getGenTag3_Id());
            copy.setGenTag3_Id2(orig.getGenTag3_Id2());
            copy.setGenTag4_Id(orig.getGenTag4_Id());
            copy.setGenTag4_Id2(orig.getGenTag4_Id2());
            copy.setGenChan1_Id(orig.getGenChan1_Id());
            copy.setGenChan1_Id2(orig.getGenChan1_Id2());
            copy.setFinTxset1_FinTxacts1_Id2(orig.getFinTxset1_FinTxacts1_Id2());
            copy.setFinTxset1_Id(orig.getFinTxset1_Id());
            copy.setFinTxset1_Id2(orig.getFinTxset1_Id2());
            copy.setFinTxset1_Id2Calc(orig.getFinTxset1_Id2Calc());
            copy.setFinTxset1_EI1_Role(orig.getFinTxset1_EI1_Role());
            copy.setFinTxset1_Type1_Id(orig.getFinTxset1_Type1_Id());
            copy.setFinTxset1_Type1_Id2(orig.getFinTxset1_Type1_Id2());
//            copy.setFinTxact1_FinTxfers1_Id2(orig.getFinTxact1_FinTxfers1_Id2());
            copy.setFinTxact1_Id(orig.getFinTxact1_Id());
            copy.setFinTxact1_Id2(orig.getFinTxact1_Id2());
//            copy.setFinTxact1_Id2Calc(orig.getFinTxact1_Id2Calc());
            copy.setFinTxact1_EI1_Role(orig.getFinTxact1_EI1_Role());
            copy.setFinTxact1_Type1_Id(orig.getFinTxact1_Type1_Id());
            copy.setFinTxact1_Type1_Id2(orig.getFinTxact1_Type1_Id2());
            copy.setIdX(orig.getIdX());
            copy.setIdY(orig.getIdY());
            copy.setIdZ(orig.getIdZ());
            copy.setFinStmt1_Id(orig.getFinStmt1_Id());
            copy.setFinStmt1_Id2(orig.getFinStmt1_Id2());
            copy.setStmtDescL1(orig.getStmtDescL1());
            copy.setStmtDescL2(orig.getStmtDescL2());
            copy.setStmtDescL3(orig.getStmtDescL3());
            copy.setStmtDescAmt(orig.getStmtDescAmt());
            copy.setStmtRefId(orig.getStmtRefId());
//            copy.setFinTxset1_FinAccts1_Id2(orig.getFinTxset1_FinAccts1_Id2());
//            copy.setFinTxact1_FinAccts1_Id2(orig.getFinTxact1_FinAccts1_Id2());
            copy.setFinAcct1_Id(orig.getFinAcct1_Id());
            copy.setFinAcct1_Id2(orig.getFinAcct1_Id2());
            copy.setFinAcct1_Type1_Id(orig.getFinAcct1_Type1_Id());
//            copy.setFinAcct1_Type1_Id2(orig.getFinAcct1_Type1_Id2());
            copy.setFinDept1_Id(orig.getFinDept1_Id());
            copy.setFinDept1_Id2(orig.getFinDept1_Id2());
            copy.setFinTaxLne1_Id(orig.getFinTaxLne1_Id());
            copy.setFinTaxLne1_Id2(orig.getFinTaxLne1_Id2());
            copy.setFinTaxLne1_Code(orig.getFinTaxLne1_Code());
            copy.setFinTaxLne1_Type1_Id(orig.getFinTaxLne1_Type1_Id());
            copy.setFinTaxLne1_Type1_Id2(orig.getFinTaxLne1_Type1_Id2());
            copy.setFinHow1_Id(orig.getFinHow1_Id());
            copy.setFinHow1_Id2(orig.getFinHow1_Id2());
            copy.setWhatText1(orig.getWhatText1());
            copy.setFinWhat1_Id(orig.getFinWhat1_Id());
            copy.setFinWhat1_Id2(orig.getFinWhat1_Id2());
            copy.setFinTxact1_WhatText1(orig.getFinTxact1_WhatText1());
            copy.setFinTxact1_What1_Id(orig.getFinTxact1_What1_Id());
            copy.setFinTxact1_What1_Id2(orig.getFinTxact1_What1_Id2());
            copy.setFinTxset1_WhatText1(orig.getFinTxset1_WhatText1());
            copy.setFinTxset1_What1_Id(orig.getFinTxset1_What1_Id());
            copy.setFinTxset1_What1_Id2(orig.getFinTxset1_What1_Id2());
            copy.setWhyText1(orig.getWhyText1());
            copy.setFinWhy1_Id(orig.getFinWhy1_Id());
            copy.setFinWhy1_Id2(orig.getFinWhy1_Id2());
            copy.setFinTxact1_WhyText1(orig.getFinTxact1_WhyText1());
            copy.setFinTxact1_Why1_Id(orig.getFinTxact1_Why1_Id());
            copy.setFinTxact1_Why1_Id2(orig.getFinTxact1_Why1_Id2());
            copy.setFinTxset1_WhyText1(orig.getFinTxset1_WhyText1());
            copy.setFinTxset1_Why1_Id(orig.getFinTxset1_Why1_Id());
            copy.setFinTxset1_Why1_Id2(orig.getFinTxset1_Why1_Id2());
            copy.setAmtDebt(orig.getAmtDebt());
            copy.setAmtCred(orig.getAmtCred());
            copy.setAmtNet(orig.getAmtNet());
            copy.setFinCurcy1_Id(orig.getFinCurcy1_Id());
            copy.setFinCurcy1_Id2(orig.getFinCurcy1_Id2());
            copy.setFinFmla1_Id(orig.getFinFmla1_Id());
            copy.setFinFmla1_Id2(orig.getFinFmla1_Id2());
            copy.setFinTxfer1_Id(orig.getFinTxfer1_Id());
            copy.setFinTxfer1_Id2(orig.getFinTxfer1_Id2());
            copy.setFinTxfer1_EI1_Rate(orig.getFinTxfer1_EI1_Rate());
            copy.setCalcRslt(orig.getCalcRslt());
            copy.setFinTxset1_GenDocVers1_Id2(orig.getFinTxset1_GenDocVers1_Id2());
            copy.setFinTxact1_GenDocVers1_Id2(orig.getFinTxact1_GenDocVers1_Id2());
            copy.setFinTxset1_GenTags1_Id2(orig.getFinTxset1_GenTags1_Id2());
            copy.setFinTxact1_GenTags1_Id2(orig.getFinTxact1_GenTags1_Id2());
            copy.setBeg(orig.getBeg());
            copy.setFinTxact1_BegDate1(orig.getFinTxact1_BegDate1());
            copy.setFinTxact1_BegTime1(orig.getFinTxact1_BegTime1());
            copy.setFinTxset1_BegDate1(orig.getFinTxset1_BegDate1());
            copy.setFinTxset1_BegTime1(orig.getFinTxset1_BegTime1());

            logger.debug("Duplicated FinTxfer " + copy.getId2() + " "
                + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
                    );
    /*
            notifications.create()
                    .withCaption("Duplicated Id2: " + e.getId2().toString())
                    .show();
*/

        return copy;
    }




    @Subscribe
    public void onInit(InitEvent event) {

/*
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
*/

    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        logger.debug("Changed entity: " + event.getEntity());

    }

}