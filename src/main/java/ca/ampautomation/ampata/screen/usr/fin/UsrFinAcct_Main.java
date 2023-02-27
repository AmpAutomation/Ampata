package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcy;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinAcct;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinAcctQryMngr;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinAcctType;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinTaxLne;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenAgent;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenDocVer;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.gen.UsrGen;
import ca.ampautomation.ampata.screen.usr.UsrNode0BaseMain;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@UiController("enty_UsrFinAcct.main")
@UiDescriptor("usr-fin-acct-main.xml")
@LookupComponent("tableMain")
public class UsrFinAcct_Main extends UsrNode0BaseMain<UsrFinAcct, UsrFinAcctType, UsrFinAcctQryMngr, TreeTable<UsrFinAcct>> {


    //Filter
    @Autowired
    protected PropertyFilter<UsrGenAgent> filterConfig1A_GenAgent1_Id;

    @Autowired
    protected PropertyFilter<SysFinCurcy> filterConfig1A_SysFinCurcy1_Id;

    

    //Template
    @Autowired
    protected EntityComboBox<UsrGenAgent> tmplt_GenAgent1_IdField;
    @Autowired
    protected CheckBox tmplt_GenAgent1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysFinCurcy> tmplt_SysFinCurcy1_IdField;
    @Autowired
    protected CheckBox tmplt_SysFinCurcy1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrFinTaxLne> tmplt_FinTaxLne1_IdField;
    @Autowired
    protected CheckBox tmplt_FinTaxLne1_IdFieldChk;



    //Other data loaders and containers
    private CollectionContainer<UsrGenAgent> colCntnrGenAgent;
    private CollectionLoader<UsrGenAgent> colLoadrGenAgent;

    private CollectionContainer<SysFinCurcy> colCntnrSysFinCurcy;
    private CollectionLoader<SysFinCurcy> colLoadrSysFinCurcy;

    private CollectionContainer<UsrGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrGenFmla> colLoadrGenFmla;

    private CollectionContainer<UsrFinTaxLne> colCntnrFinTaxLne;
    private CollectionLoader<UsrFinTaxLne> colLoadrFinTaxLne;

    private CollectionContainer<UsrGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrGenDocVer> colLoadrGenDocVer;


    private CollectionContainer<UsrGen> colCntnrGenTag;
    private CollectionLoader<UsrGen> colLoadrGenTag;


    //Field
    @Autowired
    private ComboBox<String> statusField;

    @Autowired
    private EntityComboBox<UsrGenAgent> genAgent1_IdField;

    @Autowired
    private EntityComboBox<SysFinCurcy> sysFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenFmla> desc1UsrNode1_IdField;

    @Autowired
    private EntityComboBox<UsrFinTaxLne> fchPlnFinTaxLne_Inst;

    @Autowired
    private EntityComboBox<UsrGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrGen> genTag1_IdField;

    @Autowired
    private EntityComboBox<UsrGen> genTag2_IdField;

    @Autowired
    private EntityComboBox<UsrGen> genTag3_IdField;

    @Autowired
    private EntityComboBox<UsrGen> genTag4_IdField;

    @Autowired
    private EntityComboBox<UsrFinTaxLne> finTaxLne1_IdField;


    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        statusField.setNullOptionVisible(true);
        statusField.setNullSelectionCaption("<null>");


        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("None", 0);
        map1.put("Include Id2", 1);
        updateColItemCalcValsOption.setOptionsMap(map1);
        updateInstItemCalcValsOption.setOptionsMap(map1);


        colCntnrGenAgent = dataComponents.createCollectionContainer(UsrGenAgent.class);
        colLoadrGenAgent = dataComponents.createCollectionLoader();
        colLoadrGenAgent.setQuery("select e from enty_UsrGenAgent e order by e.id2");
        FetchPlan ftchPlnGenAgent_Inst = fetchPlans.builder(UsrGenAgent.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenAgent.setFetchPlan(ftchPlnGenAgent_Inst);
        colLoadrGenAgent.setContainer(colCntnrGenAgent);
        colLoadrGenAgent.setDataContext(getScreenData().getDataContext());

        genAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        //template
        tmplt_GenAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        //filter
        EntityComboBox<UsrGenAgent> propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(colCntnrGenAgent);


        colCntnrSysFinCurcy = dataComponents.createCollectionContainer(SysFinCurcy.class);
        colLoadrSysFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysFinCurcy.setQuery("select e from enty_SysFinCurcy e where order by e.id2");
        FetchPlan fchPlnSysFinCurcy_Inst = fetchPlans.builder(SysFinCurcy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysFinCurcy.setFetchPlan(fchPlnSysFinCurcy_Inst);
        colLoadrSysFinCurcy.setContainer(colCntnrSysFinCurcy);
        colLoadrSysFinCurcy.setDataContext(getScreenData().getDataContext());

        sysFinCurcy1_IdField.setOptionsContainer(colCntnrSysFinCurcy);
        //template
        tmplt_SysFinCurcy1_IdField.setOptionsContainer(colCntnrSysFinCurcy);
        //filter
        EntityComboBox<SysFinCurcy> propFilterCmpnt_SysFinCurcy1_Id = (EntityComboBox<SysFinCurcy>) filterConfig1A_SysFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysFinCurcy1_Id.setOptionsContainer(colCntnrSysFinCurcy);



        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrGenFmla e where order by e.id2");
        FetchPlan fchPlnGenFmla_Inst = fetchPlans.builder(UsrGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla_Inst);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());

        desc1UsrNode1_IdField.setOptionsContainer(colCntnrGenFmla);


        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrGenDocVer.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrGenDocVer e order by e.id2");
        FetchPlan fchPlnUsrGenDocVer_Inst = fetchPlans.builder(UsrGenDocVer.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(fchPlnUsrGenDocVer_Inst);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGen.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrGenTag e where order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(UsrGen.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(genTagsFp);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);
        genTag2_IdField.setOptionsContainer(colCntnrGenTag);
        genTag3_IdField.setOptionsContainer(colCntnrGenTag);
        genTag4_IdField.setOptionsContainer(colCntnrGenTag);


        colCntnrFinTaxLne = dataComponents.createCollectionContainer(UsrFinTaxLne.class);
        colLoadrFinTaxLne = dataComponents.createCollectionLoader();
        colLoadrFinTaxLne.setQuery("select e from enty_UsrFinTaxLne e order by e.id2");
        FetchPlan fchPlnFinTaxLne_Inst = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTaxLne.setFetchPlan(fchPlnFinTaxLne_Inst);
        colLoadrFinTaxLne.setContainer(colCntnrFinTaxLne);
        colLoadrFinTaxLne.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(colCntnrFinTaxLne);
        //template
        tmplt_FinTaxLne1_IdField.setOptionsContainer(colCntnrFinTaxLne);
        //filter


        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

        colLoadrSysFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysFinCurcy.load() ");
        
        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called usrFinTaxLnesDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Override
    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrFinAcct> sels = new ArrayList<>();

        thisFinAccts.forEach(orig -> {
            UsrFinAcct copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            if (tmplt_GenAgent1_IdFieldChk.isChecked()) {
                copy.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
            }

            copy.updateInst1(dataManager);
            copy.updateName1(dataManager);
            copy.updateId2Calc(dataManager);
            copy.updateId2Deps(dataManager);

            if (!Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setNm1s1Inst1Int3(copy.getNm1s1Inst1Int3() == null ? 1 : copy.getNm1s1Inst1Int3() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            Integer option = Optional.ofNullable(updateColItemCalcValsOption.getValue()).orElse(0);
            copy.updateCalcVals(dataManager,option);

            UsrFinAcct savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        tableMain.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrFinAcct> chngFinAccts = new ArrayList<>();
        List<UsrFinAcct> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {
            thisFinAcct = dataContext.merge(thisFinAcct);
            if (thisFinAcct != null) {

                Boolean thisFinAcctIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinAcct.setType1_Id(tmplt_Type1_IdField.getValue());
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }

                if (tmplt_GenAgent1_IdFieldChk.isChecked()
                ) {
                    thisFinAcct.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }

                if (tmplt_FinTaxLne1_IdFieldChk.isChecked()
                ) {
                    thisFinAcct.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }
            }
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateHelper2(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UsrFinAcct firstFinAcct = thisFinAccts.get(0);
        UsrNode firstNode = thisFinAccts.get(0);

        List<UsrNode> thisNodes = new ArrayList<>(UsrNode.getNodesByParent1(UsrNode.class, dataManager, firstNode.getParent1_Id()));
        thisNodes.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngNodes = new ArrayList<>();
        List<UsrNode> finalChngNodes = chngNodes;

        AtomicInteger sortIdx = new AtomicInteger(0);
        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {
                thisNode = dataContext.merge(thisNode);
                Boolean thisNodeIsChanged = false;

                Integer sortIdx_ = thisNode.getSortIdx();
                if (!Objects.equals(sortIdx_, sortIdx)){
                    thisNode.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- thisNode.setSortIdx(" + sortIdx.get() + ")");
                    thisNodeIsChanged = true;
                    finalChngNodes.add(thisNode);
                }
            }
            sortIdx.incrementAndGet() ;
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngNodes = finalChngNodes.stream().distinct().collect(Collectors.toList());
        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        //order ascending
        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                if (sortIdx_ != null
                        && sortIdx_ > sortIdxMin.intValue()){

                    // for this item, zero idx
                    Integer sortIdx = sortIdxMin.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisFinAcct.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                        thisFinAcctIsChanged = true;
                        finalChngFinAccts.add(thisFinAcct);
                    }

                    // for all next items, inc idx
                    List<UsrNode> nextFinAccts =  UsrNode.getNodesBtwnSortIdx(UsrNode.class,dataManager,-1
                            , sortIdx_, thisFinAcct.getParent1_Id());

                    nextFinAccts.forEach(nextFinAcct -> {
                        if (nextFinAcct != null) {
                            nextFinAcct = dataContext.merge(nextFinAcct);

                            Boolean nextFinAcctIsChanged = false;

                            Integer nextSortIdx_ = nextFinAcct.getSortIdx();
                            if (nextSortIdx_ != null
                                    && nextSortIdx_ >= sortIdxMin.intValue()) {

                                // for this item, dec idx
                                Integer nextSortIdx = nextFinAcct.getSortIdx() + 1;

                                if (!Objects.equals(nextSortIdx_, nextSortIdx)) {
                                    nextFinAcct.setSortIdx(nextSortIdx);
                                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (nextSortIdx) + ")");
                                    nextFinAcctIsChanged = true;
                                    finalChngFinAccts.add(nextFinAcct);
                                }
                            }
                        }
                    });

                    sortIdxMin.incrementAndGet();

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevSortIdxBtn")
    public void onMovePrevSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMovePrevSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {

            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisFinAcct);
                if (sortIdx_ != null
                    && sortIdx_ > sortIdxMin){

                    // for this item, dec idx
                    Integer sortIdx = sortIdx_ - 1;

                    thisFinAcct.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);

                    // for prev item, inc idx
                    UsrFinAcct prevFinAcct = UsrNode.getNodesBySortIdx(UsrFinAcct.class, dataManager,-1,thisFinAcct.getParent1_Id());
                    if(prevFinAcct != null){
                        prevFinAcct = dataContext.merge(prevFinAcct);
                        Boolean prevFinAcctIsChanged = false;

                        Integer prevSortIdx_ = prevFinAcct.getSortIdx();
                        if (prevSortIdx_ != null
                                && prevSortIdx_ < sortIdxMax){

                            Integer prevSortIdx = prevSortIdx_ + 1;

                            prevFinAcct.setSortIdx(prevSortIdx);
                            logger.debug(logPrfx + " --- prevFinAcct.setSortIdx(" + (prevSortIdx) + ")");
                            prevFinAcctIsChanged = true;
                            finalChngFinAccts.add(prevFinAcct);
                        }
                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveNextSortIdxBtn")
    public void onMoveNextSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveNextSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisFinAcct);
                if (sortIdxMax != null
                        && sortIdx_ < sortIdxMax){

                    // for this item, inc idx
                    Integer sortIdx = sortIdx_ + 1;

                    thisFinAcct.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);


                    // for next item, dec idx
                    UsrFinAcct nextFinAcct = UsrNode.getNodesBySortIdx(UsrFinAcct.class,dataManager,+1,thisFinAcct.getParent1_Id());
                    if(nextFinAcct != null){
                        nextFinAcct = dataContext.merge(nextFinAcct);
                        Boolean nextFinAcctIsChanged = false;

                        Integer nextSortIdx_ = nextFinAcct.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ > sortIdxMin){

                            Integer nextSortIdx = nextSortIdx_ - 1;

                            nextFinAcct.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextFinAcct.setSortIdx(" + (nextSortIdx) + ")");
                            nextFinAcctIsChanged = true;
                            finalChngFinAccts.add(nextFinAcct);
                        }

                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveLastSortIdxBtn")
    public void onMoveLastSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveLastSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        //order descending
        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        AtomicInteger sortIdxMax = new AtomicInteger(getSortIdxMax(thisFinAccts.get(0)));
        if (sortIdxMax == null) {
            logger.debug(logPrfx + " --- sortIdxMax is null.");
            notifications.create().withCaption("No records selected. Please rebuild sortIdx.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);

                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                if (sortIdx_ != null 
                        && sortIdx_ < sortIdxMax.intValue()){

                    // for this item, max idx
                    Integer sortIdx = sortIdxMax.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisFinAcct.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                        thisFinAcctIsChanged = true;
                        finalChngFinAccts.add(thisFinAcct);
                    }

                    // for all next items, dec idx
                    List<UsrNode> nextFinAccts =  UsrNode.getNodesBtwnSortIdx(UsrNode.class, dataManager,sortIdx_
                            , sortIdxMax.intValue()+1, thisFinAcct.getParent1_Id());

                    nextFinAccts.forEach(nextFinAcct -> {
                                if (nextFinAcct != null) {
                                    nextFinAcct = dataContext.merge(nextFinAcct);

                                    Boolean nextFinAcctIsChanged = false;

                                    Integer nextSortIdx_ = nextFinAcct.getSortIdx();
                                    if (nextSortIdx_ != null 
                                            && nextSortIdx_ <= sortIdxMax.intValue()) {

                                        // for this item, dec idx
                                        Integer nextSortIdx = nextFinAcct.getSortIdx() - 1;

                                        if (!Objects.equals(nextSortIdx_, nextSortIdx)) {
                                            nextFinAcct.setSortIdx(nextSortIdx);
                                            logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (nextSortIdx) + ")");
                                            nextFinAcctIsChanged = true;
                                            finalChngFinAccts.add(nextFinAcct);
                                        }
                                    }
                                }
                            });

                    sortIdxMax.decrementAndGet();

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }

            }
        });


        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }



    private Integer getSortIdxMax(UsrNode thisFinAcct) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;
        String sortIdxMaxQry = "select max(e.sortIdx) from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent_Id1";
        try {
            sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                    .store("main")
                    .parameter("parent_Id1", thisFinAcct.getParent1_Id())
                    .one();
            // max returns null if no rows or if all rows have a null value
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- sortIdxMaxQry error: " + e.getMessage());
        }
        logger.debug(logPrfx + " --- sortIdxMaxQry result: " + sortIdxMax + "");

        logger.trace(logPrfx + " <-- ");
        return sortIdxMax;
    }

    @Override
    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {

                thisFinAcct = dataContext.merge(thisFinAcct);

                boolean isChanged = false;

                Integer option = Optional.ofNullable(updateColItemCalcValsOption.getValue()).orElse(0);

                isChanged = thisFinAcct.updateCalcVals(dataManager, option);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.setSelected(thisFinAccts);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrFinAcct thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer option = Optional.ofNullable(updateInstItemCalcValsOption.getValue()).orElse(0);

        thisFinAcct.updateCalcVals(dataManager, option);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenAgent1_IdFieldListBtn")
    public void onUpdateGenAgent1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenAgent1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrSysFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "statusField", subject = "enterPressHandler")
    private void statusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "statusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("updateStatusFieldListBtn")
    public void onUpdateStatusFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateStatusFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        List<String> statuss = UsrNode.getStatusList(UsrFinAcct.class, dataManager);
        logger.debug(logPrfx + " --- called UsrNode.getStatusList(UsrFinAcct.class, dataManager)");
        statusField.setOptionsList(statuss);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenDocVer1_IdFieldListBtn")
    public void onUpdateGenDocVer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenDocVer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenDocVer.load();
        logger.debug(logPrfx + " --- called colLoadrGenDocVer.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTaxLne1_IdFieldListBtn")
    public void onUpdateFinTaxLne1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called usrFinTaxLnesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }




}