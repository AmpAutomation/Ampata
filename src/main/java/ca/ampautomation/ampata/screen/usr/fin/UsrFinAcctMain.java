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
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import ca.ampautomation.ampata.screen.usr.UsrNodeBaseMain;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@UiController("enty_UsrFinAcct.main")
@UiDescriptor("usr-fin-acct-main.xml")
@LookupComponent("tableMain")
public class UsrFinAcctMain extends UsrNodeBaseMain<UsrFinAcct, UsrFinAcctType, UsrFinAcctQryMngr> {


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


    private CollectionContainer<UsrGenTag> colCntnrGenTag;
    private CollectionLoader<UsrGenTag> colLoadrGenTag;


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
    private EntityComboBox<UsrGenTag> genTag1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag2_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag3_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag4_IdField;

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


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrGenTag e where order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(UsrGenTag.class)
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
            copy.updateCalcVals(option);

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

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

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
        updateFinAcctHelper(chngFinAccts);

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

        List<UsrNode> l = getNodeListByParent1(firstFinAcct.getParent1_Id());
        List<UsrFinAcct> b = (List<UsrFinAcct>) l;
        thisFinAccts = new ArrayList<>((List<UsrFinAcct>) l);
        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrFinAcct> chngFinAccts = new ArrayList<>();
        List<UsrFinAcct> finalChngFinAccts = chngFinAccts;

        AtomicInteger sortIdx = new AtomicInteger(0);
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                if (!Objects.equals(sortIdx_, sortIdx)){
                    thisFinAcct.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + sortIdx.get() + ")");
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }
            }
            sortIdx.incrementAndGet() ;
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateFinAcctHelper(chngFinAccts);

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
                    List<UsrNode> nextFinAccts =  getFinAcctsBtwnSortIdx(-1
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
        updateFinAcctHelper(chngFinAccts);

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
                    UsrNode prevFinAcct = getFinAcctBySortIdx(sortIdx_-1,thisFinAcct.getParent1_Id());
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
        updateFinAcctHelper(chngFinAccts);

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
                    UsrNode nextFinAcct = getFinAcctBySortIdx(sortIdx_+1,thisFinAcct.getParent1_Id());
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
        updateFinAcctHelper(chngFinAccts);

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
                    List<UsrNode> nextFinAccts =  getFinAcctsBtwnSortIdx(sortIdx_
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
        updateFinAcctHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }


    private void updateFinAcctHelper(List<UsrNode> chngFinAccts) {
        String logPrfx = "updateFinAcctHelper";
        logger.trace(logPrfx + " --> ");

        if(chngFinAccts != null && !chngFinAccts.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngFinAccts.forEach(thisFinAcct -> {
                //UsrNode thisTrackedFinAcct = dataContext.merge(thisFinAcct);
                if (thisFinAcct != null) {
                    thisFinAcct = dataContext.merge(thisFinAcct);
                    Boolean thisFinAcctIsChanged = false;

                    thisFinAcctIsChanged = updateId2Dup(thisFinAcct) || thisFinAcctIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisFinAccts);
            }
        }
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


    @Subscribe("updateColItemIdPartsBtn")
    public void onUpdateColItemIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {

                UsrNode thisTrackedFinAcct = dataContext.merge(thisFinAcct);
                thisFinAcct = thisTrackedFinAcct;

                boolean isChanged = false;

                isChanged = updateIdParts(thisFinAcct);

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

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {

                UsrNode thisTrackedFinAcct = dataContext.merge(thisFinAcct);
                thisFinAcct = thisTrackedFinAcct;

                boolean isChanged = false;

                Integer option = Optional.ofNullable(updateColItemCalcValsOption.getValue()).orElse(0);

                isChanged = updateCalcVals(thisFinAcct, option);

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

    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateIdParts(thisFinAcct);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer option = Optional.ofNullable(updateInstItemCalcValsOption.getValue()).orElse(0);

        updateCalcVals(thisFinAcct, option);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
            if (thisFinAcct == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinAcct);
            updateId2Dup(thisFinAcct);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinAcct);
        updateId2Cmp(thisFinAcct);
        updateId2Dup(thisFinAcct);

        logger.debug(logPrfx + " --- id2: " + thisFinAcct.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinAcct);
        updateId2Cmp(thisFinAcct);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinAcct.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinAcct);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinAcct);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("name1Field")
    public void onName1FieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onName1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
            if (thisFinAcct == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinAcct)");
            updateId2Calc(thisFinAcct);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDescPat1_IdFieldListBtn")
    public void onUpdateDescPat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDescPat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

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

        reloadStatusList();

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


    private UsrNode getFinAcctBySortIdx(@NotNull Integer sortIdx, UsrNode parent1_Id) {
        String logPrfx = "getFinAcctBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent1_Id"
                + " and e.sortIdx = :sortIdx"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdx: " + sortIdx);

        UsrNode finAcct = null;
        try {
            finAcct = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdx", sortIdx)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finAcct;
    }


    private List<UsrNode> getFinAcctsBtwnSortIdx(@NotNull Integer sortIdxA , @NotNull Integer sortIdxB, UsrNode parent1_Id) {
        String logPrfx = "getFinAcctsBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent1_Id"
                + " and e.sortIdx > :sortIdxA"
                + " and e.sortIdx < :sortIdxB"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdxA: " + sortIdxA);
        logger.debug(logPrfx + " --- qry:sortIdxB: " + sortIdxB);

        List<UsrNode> finAccts = null;
        try {
            finAccts = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdxA", sortIdxA)
                    .parameter("sortIdxB", sortIdxB)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ finAccts.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finAccts;
    }

    private List<UsrNode> getNodeListByParent1(UsrNode parent1_Id) {
        String logPrfx = "getNodeListByParent1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent1_Id"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());

        List<UsrNode> l_childs = null;
        try {
            l_childs = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ l_childs.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return l_childs;
    }

    private void reloadStatusList(){
        String logPrfx = "reloadStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.status IS NOT NULL"
                + " order by e.status"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> statuss = null;
        try {
            statuss = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + statuss.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        statusField.setOptionsList(statuss);
        logger.debug(logPrfx + " --- called statusField.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "addEnteredTextToComboBoxOptionsList";
        logger.trace(logPrfx + " --> ");

        String text = enterPressEvent.getText();
        if (!Objects.equals(text, "<null>")){
            @SuppressWarnings("unchecked")
            // enterPressEvent.getSource is directly connected to a ComboBox
            ComboBox<String> cb = (ComboBox<String>) enterPressEvent.getSource();

            List<String> list;
            // this comboBox options list is created with a call to setOptionsList(List)
            // see onUpdateFinStmtItm1_Desc1Field
            // therefore cb.getOptions is type ListOptions
            ListOptions<String> listOptions = (ListOptions<String>) cb.getOptions();
            if (listOptions != null && !listOptions.getItemsCollection().isEmpty()) {
                list = (List<String>) listOptions.getItemsCollection();
            } else {
                list = new ArrayList<String>();
            }

            list.add(text);
            logger.trace(logPrfx + " --- called list.add( " + text + ")");

            cb.setOptionsList(list);

            notifications.create()
                    .withCaption("Added " + text + " to list.")
                    .show();
        }

    }

}