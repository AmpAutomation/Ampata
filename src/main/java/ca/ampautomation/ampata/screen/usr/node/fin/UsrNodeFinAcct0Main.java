package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcct;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctQryMngr;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTaxLne;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@UiController("enty_UsrNodeFinAcct.main")
@UiDescriptor("usr-node-fin-acct-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinAcct0Main extends UsrNodeBase0BaseMain<UsrNodeFinAcct, UsrNodeFinAcctType, UsrNodeFinAcctQryMngr, TreeTable<UsrNodeFinAcct>> {


    //Filter
    @Autowired
    protected PropertyFilter<UsrNodeGenAgent> filterConfig1A_GenAgent1_Id;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_SysNodeFinCurcy1_Id;

    

    //Template
    @Autowired
    protected EntityComboBox<UsrNodeGenAgent> tmplt_GenAgent1_IdField;
    @Autowired
    protected CheckBox tmplt_GenAgent1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysNodeFinCurcy> tmplt_SysNodeFinCurcy1_IdField;
    @Autowired
    protected CheckBox tmplt_SysNodeFinCurcy1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrNodeFinTaxLne> tmplt_FinTaxLne1_IdField;
    @Autowired
    protected CheckBox tmplt_FinTaxLne1_IdFieldChk;



    //Other data loaders and containers
    private CollectionContainer<UsrNodeGenAgent> colCntnrGenAgent;
    private CollectionLoader<UsrNodeGenAgent> colLoadrGenAgent;

    private CollectionContainer<SysNodeFinCurcy> colCntnrSysNodeFinCurcy;
    private CollectionLoader<SysNodeFinCurcy> colLoadrSysNodeFinCurcy;

    private CollectionContainer<UsrItemGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrItemGenFmla> colLoadrGenFmla;

    private CollectionContainer<UsrNodeFinTaxLne> colCntnrFinTaxLne;
    private CollectionLoader<UsrNodeFinTaxLne> colLoadrFinTaxLne;

    private CollectionContainer<UsrNodeGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrNodeGenDocVer> colLoadrGenDocVer;


    private CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    private CollectionLoader<UsrItemGenTag> colLoadrGenTag;


    //Field
    @Autowired
    private ComboBox<String> statusField;

    @Autowired
    private EntityComboBox<UsrNodeGenAgent> genAgent1_IdField;

    @Autowired
    private EntityComboBox<SysNodeFinCurcy> sysNodeFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinTaxLne> fchPlnFinTaxLne_Inst;

    @Autowired
    private EntityComboBox<UsrNodeGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> genTag1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> genTag2_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinTaxLne> finTaxLne1_IdField;


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


        colCntnrGenAgent = dataComponents.createCollectionContainer(UsrNodeGenAgent.class);
        colLoadrGenAgent = dataComponents.createCollectionLoader();
        colLoadrGenAgent.setQuery("select e from enty_UsrNodeGenAgent e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenAgent_Inst = fetchPlans.builder(UsrNodeGenAgent.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenAgent.setFetchPlan(fchPlnGenAgent_Inst);
        colLoadrGenAgent.setContainer(colCntnrGenAgent);
        colLoadrGenAgent.setDataContext(getScreenData().getDataContext());

        genAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        //template
        tmplt_GenAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        //filter
        EntityComboBox<UsrNodeGenAgent> propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrNodeGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(colCntnrGenAgent);


        colCntnrSysNodeFinCurcy = dataComponents.createCollectionContainer(SysNodeFinCurcy.class);
        colLoadrSysNodeFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysNodeFinCurcy.setQuery("select e from enty_SysNodeFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnSysNodeFinCurcy_Inst = fetchPlans.builder(SysNodeFinCurcy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysNodeFinCurcy.setFetchPlan(fchPlnSysNodeFinCurcy_Inst);
        colLoadrSysNodeFinCurcy.setContainer(colCntnrSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setDataContext(getScreenData().getDataContext());

        sysNodeFinCurcy1_IdField.setOptionsContainer(colCntnrSysNodeFinCurcy);
        //template
        tmplt_SysNodeFinCurcy1_IdField.setOptionsContainer(colCntnrSysNodeFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setOptionsContainer(colCntnrSysNodeFinCurcy);



        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrItemGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrItemGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla_Inst = fetchPlans.builder(UsrItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla_Inst);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());

        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);


        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrNodeGenDocVer.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrNodeGenDocVer e order by e.sortKey, e.id2");
        FetchPlan fchPlnUsrNodeGenDocVer_Inst = fetchPlans.builder(UsrNodeGenDocVer.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(fchPlnUsrNodeGenDocVer_Inst);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrItemGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrItemGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag_Inst = fetchPlans.builder(UsrItemGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag_Inst);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);
        genTag2_IdField.setOptionsContainer(colCntnrGenTag);


        colCntnrFinTaxLne = dataComponents.createCollectionContainer(UsrNodeFinTaxLne.class);
        colLoadrFinTaxLne = dataComponents.createCollectionLoader();
        colLoadrFinTaxLne.setQuery("select e from enty_UsrNodeFinTaxLne e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTaxLne_Inst = fetchPlans.builder(UsrNodeBase.class)
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

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");
        
        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Override
    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeFinAcct> sels = new ArrayList<>();

        thisFinAccts.forEach(orig -> {
            UsrNodeFinAcct copy = metadataTools.copy(orig);
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

            UsrNodeFinAcct savedCopy = dataManager.save(copy);
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

        List<UsrNodeFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeFinAcct> chngFinAccts = new ArrayList<>();
        List<UsrNodeFinAcct> finalChngFinAccts = chngFinAccts;

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

        List<UsrNodeFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UsrNodeFinAcct firstFinAcct = thisFinAccts.get(0);
        UsrNodeBase firstNode = thisFinAccts.get(0);

        List<UsrNodeBase> thisNodes = new ArrayList<>(UsrNodeBase.getNodesByParent1(UsrNodeBase.class, dataManager, firstNode.getParent1_Id()));
        thisNodes.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngNodes = new ArrayList<>();
        List<UsrNodeBase> finalChngNodes = chngNodes;

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

        List<UsrNodeBase> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        //order ascending
        thisFinAccts.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngFinAccts = new ArrayList<>();
        List<UsrNodeBase> finalChngFinAccts = chngFinAccts;

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
                    List<UsrNodeBase> nextFinAccts =  UsrNodeBase.getNodesBtwnSortIdx(UsrNodeBase.class,dataManager,-1
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

        List<UsrNodeBase> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinAccts.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngFinAccts = new ArrayList<>();
        List<UsrNodeBase> finalChngFinAccts = chngFinAccts;

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
                    UsrNodeFinAcct prevFinAcct = UsrNodeBase.getNodesBySortIdx(UsrNodeFinAcct.class, dataManager,-1,thisFinAcct.getParent1_Id());
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

        List<UsrNodeBase> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinAccts.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngFinAccts = new ArrayList<>();
        List<UsrNodeBase> finalChngFinAccts = chngFinAccts;

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
                    UsrNodeFinAcct nextFinAcct = UsrNodeBase.getNodesBySortIdx(UsrNodeFinAcct.class,dataManager,+1,thisFinAcct.getParent1_Id());
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

        List<UsrNodeBase> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        //order descending
        thisFinAccts.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        List<UsrNodeBase> chngFinAccts = new ArrayList<>();
        List<UsrNodeBase> finalChngFinAccts = chngFinAccts;

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
                    List<UsrNodeBase> nextFinAccts =  UsrNodeBase.getNodesBtwnSortIdx(UsrNodeBase.class, dataManager,sortIdx_
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



    private Integer getSortIdxMax(UsrNodeBase thisFinAcct) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;
        String sortIdxMaxQry = "select max(e.sortIdx)"
                + " from enty_UsrNodeFinAcct e"
                + " where e.parent1_Id = :parent_Id1";
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

        List<UsrNodeFinAcct> thisFinAccts = tableMain.getSelected().stream().toList();
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

        UsrNodeFinAcct thisFinAcct = instCntnrMain.getItemOrNull();
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

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

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

        List<String> statuss = UsrNodeFinAcct.getStatusList(UsrNodeFinAcct.class, dataManager);
        logger.debug(logPrfx + " --- called UsrNodeFinAcct.getStatusList(UsrNodeFinAcct.class, dataManager)");
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
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");

        logger.trace(logPrfx + " <-- ");
    }




}