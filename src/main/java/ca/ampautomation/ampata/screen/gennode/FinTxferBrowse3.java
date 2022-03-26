package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.FinRate;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.core.UuidProvider;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("ampata_FinTxfer.browse3")
@UiDescriptor("fin-txfer-browse3.xml")
@LookupComponent("table")
public class FinTxferBrowse3 extends MasterDetailScreen<GenNode> {
    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

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
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private Metadata metadata;

    Logger logger = LoggerFactory.getLogger(FinTxferBrowse2.class);

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinTxfer");
    }

    @Subscribe("id2CalcButton")
    public void onId2CalcButtonClick(Button.ClickEvent event) {

    }

    @Subscribe("finTxfer1_EI1_RateBtn")
    public void onFinTxfer1_EI1_RateBtnClick(Button.ClickEvent event) {
        GenNode thisFinTxfer = genNodeDc.getItem();
        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        FinRate qryRslt = dataManager.load(FinRate.class)
                .query("select ft from GenEntity ft "
                        +" where ft.className = 'FinRate'"
                        + " and ft.curcy1_id = ':curcy1'"
                        + " and ft.curcy2_id = ':curcy2'"
                        + " and ft.beg_date1 = ':date1'"
                )
                .parameter("date1", thisFinTxfer.getBeg().getDate1())
                .parameter("curcy1", thisFinTxfer.getFinCurcy1_Id().getId())
                .parameter("curcy2", thisFinTxfer.getFinTxfer1_Id().getFinCurcy1_Id().getId())
                .one()
                ;
        if (qryRslt != null) {
            logger.debug("onFinTxfer1_EI1_RateBtnClick ---"
                    + " qry1 result good"
            );
            rate = qryRslt.getRate();
            qryRsltGood = true;

        } else {
            logger.debug("onFinTxfer1_EI1_RateBtnClick ---"
                    + " qry1 returned empty"
            );
            qryRslt = dataManager.load(FinRate.class)
                    .query("select ft from GenEntity ft "
                            +" where ft.className = 'FinRate'"
                            + " and ft.curcy1_id = ':curcy1'"
                            + " and ft.curcy2_id = ':curcy2'"
                            + " and ft.beg_date1 = ':date1'"
                    )
                    .parameter("date1", thisFinTxfer.getBeg().getDate1())
                    .parameter("curcy2", thisFinTxfer.getFinCurcy1_Id().getId())
                    .parameter("curcy1", thisFinTxfer.getFinTxfer1_Id().getFinCurcy1_Id().getId())
                    .one()
            ;
            if (qryRslt != null) {
                logger.debug("onFinTxfer1_EI1_RateBtnClick ---"
                        + " qry2 result Id: " + qryRslt.getId()
                );
                rate = qryRslt.getRateInv();
                qryRsltGood = true;
            } else {
                logger.debug("onFinTxfer1_EI1_RateBtnClick ---"
                        + " qry2 returned empty"
                );
            }
        }

        if (qryRsltGood) {
            thisFinTxfer.setFinTxfer1_EI1_Rate(rate);
            logger.debug("onFinTxfer1_EI1_RateBtnClick ---"
                    + " rate: " + rate
            );
        }

    }

/*    @Subscribe("calcRsltBtn")
    public void onCalcRsltBtnClick(Button.ClickEvent event) {

    }*/

    @Subscribe("id2Button")
    public void onId2ButtonClick(Button.ClickEvent event) {
        id2Field.setValue(id2CalcField.getValue());

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