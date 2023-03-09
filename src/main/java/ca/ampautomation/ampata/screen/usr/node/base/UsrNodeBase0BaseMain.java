package ca.ampautomation.ampata.screen.usr.node.base;

import ca.ampautomation.ampata.entity.usr.comn.UsrComnBaseQryMngr;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import io.jmix.core.*;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class UsrNodeBase0BaseMain<NodeT extends UsrNodeBase, NodeTypeT extends UsrNodeBaseType, NodeQryMngrT extends UsrComnBaseQryMngr, TableT extends Table>  extends MasterDetailScreen<NodeT> {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<NodeT> typeOfNodeT;
    protected Class<NodeTypeT> typeOfNodeTypeT;

    @SuppressWarnings("unchecked")
    public UsrNodeBase0BaseMain() {
        this.typeOfNodeT = (Class<NodeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<NodeT> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    @Autowired
    protected DataComponents dataComponents;

    @Autowired
    protected FetchPlans fetchPlans;

    @Autowired
    protected DataContext dataContext;

    @Autowired
    protected DataManager dataManager;

    @Autowired
    protected Metadata metadata;

    @Autowired
    protected MetadataTools metadataTools;

    @Autowired
    protected Notifications notifications;


    //Query Manager
    protected NodeQryMngrT qryMngr;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<NodeTypeT> filterConfig1A_Type1_Id;


    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<NodeTypeT> tmplt_Type1_IdField;


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<NodeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<NodeT> colCntnrMain;
    @Autowired
    protected InstanceContainer<NodeT> instCntnrMain;
    @Autowired
    protected TableT tableMain;


    //Type data container and loader
    protected CollectionContainer<NodeTypeT> colCntnrType;
    protected CollectionLoader<NodeTypeT> colLoadrType;

    //GenTag data container loader and data property container
    protected CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    protected CollectionLoader<UsrItemGenTag> colLoadrGenTag;
    @Autowired
    protected CollectionPropertyContainer<UsrItemGenTag> colPropCntnrGenTag;


    //GenFmla data container and loader
    protected CollectionLoader<UsrItemGenFmla> colLoadrGenFmla;
    protected CollectionContainer<UsrItemGenFmla> colCntnrGenFmla;


    //Field
    @Autowired
    protected TextField<String> classNameField;

    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected EntityPicker<NodeT> parent1_IdField;

    @Autowired
    protected EntityComboBox<NodeTypeT> type1_IdField;

    @Autowired
    protected EntityComboBox<UsrItemGenFmla> name1GenFmla1_IdField;

    @Autowired
    protected EntityComboBox<UsrItemGenFmla> desc1GenFmla1_IdField;

    @Autowired
    protected TagField<UsrItemGenTag> genTags1_IdField;

    @Autowired
    protected EntityComboBox<UsrItemGenTag> genTag1_IdField;

    @Autowired
    protected EntityComboBox<UsrItemGenTag> genTag2_IdField;


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfNodeTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfNodeTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfNodeTypeT)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());
        //Field
        type1_IdField.setOptionsContainer(colCntnrType);
        //Template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //Filter
        EntityComboBox<NodeTypeT> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<NodeTypeT>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);

        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrItemGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrItemGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla_Inst = fetchPlans.builder(UsrItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla_Inst);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());
        //Field
        name1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrItemGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrItemGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag = fetchPlans.builder(UsrItemGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);
        genTag2_IdField.setOptionsContainer(colCntnrGenTag);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }


    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<NodeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisNode is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = event.getItem();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");
            //todo I observed thisNode is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if (thisNode.getClassName() == null || thisNode.getClassName().isBlank()){
            thisNode.setClassName(this.getClass().getSimpleName());
            logger.debug(logPrfx + " --- className: " + this.getClass().getSimpleName());
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPrUpdAllCalcValsforAllNative()");
        qryMngr.execPrUpdAllCalcValsforAllRowsNative();
        logger.debug(logPrfx + " --- finished repo.execPrUpdAllCalcValsforAllNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(orig -> {
            NodeT copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            NodeT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodes.forEach(thisNode -> {
            thisNode = dataContext.merge(thisNode);
            if (thisNode != null) {

                Boolean thisNodeIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisNodeIsChanged = true;
                    thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisNodeIsChanged = thisNode.updateId2Calc(dataManager) || thisNodeIsChanged;
                thisNodeIsChanged = thisNode.updateId2(dataManager) || thisNodeIsChanged;
                thisNodeIsChanged = thisNode.updateId2Cmp(dataManager) || thisNodeIsChanged;

                if (thisNodeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisNode).");
                    //dataManager.save(thisNode);
                }
            }
        });
        updateHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        NodeT firstNode = thisNodes.get(0);
        List<UsrNodeBase> thisNodes2 = null;
        List<NodeT> thisNodes3 = null;
        if (firstNode.getParent1_Id() == null){
            thisNodes3 = dataManager.load(typeOfNodeT).all().list();
        }else{
            thisNodes3 = dataManager.load(typeOfNodeT)
                    .condition(PropertyCondition.contains("parent1_Id",firstNode.getParent1_Id()))
                    .list();
            //thisNodes2 = new ArrayList<>(UsrNodeBase.getNodesByParent1(UsrNodeBase.class, dataManager, firstNode.getParent1_Id()));
            //thisNodes3 = (List<NodeT>) thisNodes2.stream().filter(n -> n.getDtype().equals("enty_"+typeOfNodeT.getSimpleName()));
        }
        thisNodes3.sort(Comparator.comparing(n -> n.getSortIdx(),Comparator.nullsFirst(Comparator.naturalOrder())));

        List<NodeT> chngNodes = new ArrayList<>();
        List<NodeT> finalChngNodes = chngNodes;

        AtomicInteger sortIdx = new AtomicInteger(0);
        thisNodes3.forEach(thisNode -> {
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

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        //order ascending
        thisNodes.sort(Comparator.comparing(n -> n.getSortIdx(),Comparator.nullsFirst(Comparator.naturalOrder())));

        List<NodeT> chngNodes = new ArrayList<>();
        List<NodeT> finalChngNodes = chngNodes;

        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {
                thisNode = dataContext.merge(thisNode);
                Boolean thisNodeIsChanged = false;

                Integer sortIdx_ = thisNode.getSortIdx();
                if (sortIdx_ != null
                        && sortIdx_ > sortIdxMin.intValue()){

                    // for this item, zero idx
                    Integer sortIdx = sortIdxMin.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisNode.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisNode.setSortIdx(" + (sortIdx) + ")");
                        thisNodeIsChanged = true;
                        finalChngNodes.add(thisNode);
                    }

                    // for all next items, inc idx
                    LogicalCondition logcCond = LogicalCondition.and();
                    logcCond.add(PropertyCondition.greater("sortIdx",-1));
                    logcCond.add(PropertyCondition.less("sortIdx",sortIdx_));
                    if(thisNode.getParent1_Id() != null){
                        logcCond.add(PropertyCondition.greater("parent1_Id",thisNode.getParent1_Id()));
                    }
                    List<NodeT> nextFinAccts = dataManager.load(typeOfNodeT).condition(logcCond).list();
                    // List<NodeT> nextFinAccts =  UsrNodeBase.getNodesBtwnSortIdx(typeOfNodeT,dataManager,-1
                    //        , sortIdx_, thisNode.getParent1_Id());
                    
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
                                    logger.debug(logPrfx + " --- thisNode.setSortIdx(" + (nextSortIdx) + ")");
                                    nextFinAcctIsChanged = true;
                                    finalChngNodes.add(nextFinAcct);
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

        chngNodes = finalChngNodes.stream().distinct().collect(Collectors.toList());
        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevSortIdxBtn")
    public void onMovePrevSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMovePrevSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodes.sort(Comparator.comparing(n -> n.getSortIdx(),Comparator.nullsFirst(Comparator.naturalOrder())));

        List<NodeT> chngNodes = new ArrayList<>();
        List<NodeT> finalChngNodes = chngNodes;

        thisNodes.forEach(thisNode -> {

            if (thisNode != null) {
                thisNode = dataContext.merge(thisNode);
                Boolean thisNodeIsChanged = false;

                Integer sortIdx_ = thisNode.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisNode);
                if (sortIdx_ != null
                        && sortIdx_ > sortIdxMin){

                    // for this item, dec idx
                    Integer sortIdx = sortIdx_ - 1;

                    thisNode.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisNode.setSortIdx(" + (sortIdx) + ")");
                    thisNodeIsChanged = true;
                    finalChngNodes.add(thisNode);

                    // for prev item, inc idx
                    LogicalCondition logcCond = LogicalCondition.and();
                    logcCond.add(PropertyCondition.equal("sortIdx",-1));
                    if(thisNode.getParent1_Id() != null){
                        logcCond.add(PropertyCondition.greater("parent1_Id",thisNode.getParent1_Id()));
                    }
                    NodeT prevNode = dataManager.load(typeOfNodeT).condition(logcCond).one();
                    //UsrNodeBase prevNode = UsrNodeBase.getNodesBySortIdx(UsrNodeBase.class, dataManager,-1,thisNode.getParent1_Id());
                    if(prevNode != null){
                        prevNode = dataContext.merge(prevNode);
                        Boolean prevNodeIsChanged = false;

                        Integer prevSortIdx_ = prevNode.getSortIdx();
                        if (prevSortIdx_ != null
                                && prevSortIdx_ < sortIdxMax){

                            Integer prevSortIdx = prevSortIdx_ + 1;

                            prevNode.setSortIdx(prevSortIdx);
                            logger.debug(logPrfx + " --- prevNode.setSortIdx(" + (prevSortIdx) + ")");
                            prevNodeIsChanged = true;
                            finalChngNodes.add(prevNode);
                        }
                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngNodes = finalChngNodes.stream().distinct().collect(Collectors.toList());
        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveNextSortIdxBtn")
    public void onMoveNextSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveNextSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodes.sort(Comparator.comparing(n -> n.getSortIdx(),Comparator.nullsFirst(Comparator.naturalOrder())));

        List<NodeT> chngNodes = new ArrayList<>();
        List<NodeT> finalChngNodes = chngNodes;

        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {
                thisNode = dataContext.merge(thisNode);
                Boolean thisNodeIsChanged = false;

                Integer sortIdx_ = thisNode.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisNode);
                if (sortIdxMax != null
                        && sortIdx_ < sortIdxMax){

                    // for this item, inc idx
                    Integer sortIdx = sortIdx_ + 1;

                    thisNode.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisNode.setSortIdx(" + (sortIdx) + ")");
                    thisNodeIsChanged = true;
                    finalChngNodes.add(thisNode);


                    // for next item, dec idx
                    LogicalCondition logcCond = LogicalCondition.and();
                    logcCond.add(PropertyCondition.equal("sortIdx",+1));
                    if(thisNode.getParent1_Id() != null){
                        logcCond.add(PropertyCondition.greater("parent1_Id",thisNode.getParent1_Id()));
                    }
                    NodeT nextNode = dataManager.load(typeOfNodeT).condition(logcCond).one();
                    //UsrNodeBase nextNode = UsrNodeBase.getNodesBySortIdx(UsrNodeBase.class,dataManager,+1,thisNode.getParent1_Id());
                    if(nextNode != null){
                        nextNode = dataContext.merge(nextNode);
                        Boolean nextNodeIsChanged = false;

                        Integer nextSortIdx_ = nextNode.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ > sortIdxMin){

                            Integer nextSortIdx = nextSortIdx_ - 1;

                            nextNode.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextNode.setSortIdx(" + (nextSortIdx) + ")");
                            nextNodeIsChanged = true;
                            finalChngNodes.add(nextNode);
                        }

                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngNodes = finalChngNodes.stream().distinct().collect(Collectors.toList());
        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveLastSortIdxBtn")
    public void onMoveLastSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveLastSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        //order descending
        thisNodes.sort(Comparator.comparing(n -> n.getSortIdx(),Comparator.nullsFirst(Comparator.naturalOrder())));

        List<NodeT> chngNodes = new ArrayList<>();
        List<NodeT> finalChngNodes = chngNodes;

        AtomicInteger sortIdxMax = new AtomicInteger(getSortIdxMax(thisNodes.get(0)));
        if (sortIdxMax == null) {
            logger.debug(logPrfx + " --- sortIdxMax is null.");
            notifications.create().withCaption("No records selected. Please rebuild sortIdx.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {
                thisNode = dataContext.merge(thisNode);

                Boolean thisNodeIsChanged = false;

                Integer sortIdx_ = thisNode.getSortIdx();
                if (sortIdx_ != null
                        && sortIdx_ < sortIdxMax.intValue()){

                    // for this item, max idx
                    Integer sortIdx = sortIdxMax.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisNode.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisNode.setSortIdx(" + (sortIdx) + ")");
                        thisNodeIsChanged = true;
                        finalChngNodes.add(thisNode);
                    }

                    // for all next items, dec idx
                    LogicalCondition logcCond = LogicalCondition.and();
                    logcCond.add(PropertyCondition.greater("sortIdx",sortIdx_));
                    logcCond.add(PropertyCondition.less("sortIdx", sortIdxMax.intValue()+1))
                    if(thisNode.getParent1_Id() != null){
                        logcCond.add(PropertyCondition.greater("parent1_Id",thisNode.getParent1_Id()));
                    }
                    List<NodeT> nextNodes = dataManager.load(typeOfNodeT).condition(logcCond).list();
                    //List<UsrNodeBase> nextNodes =  UsrNodeBase.getNodesBtwnSortIdx(UsrNodeBase.class, dataManager,sortIdx_
                            , sortIdxMax.intValue()+1, thisNode.getParent1_Id());

                    nextNodes.forEach(nextNode -> {
                        if (nextNode != null) {
                            nextNode = dataContext.merge(nextNode);

                            Boolean nextNodeIsChanged = false;

                            Integer nextSortIdx_ = nextNode.getSortIdx();
                            if (nextSortIdx_ != null
                                    && nextSortIdx_ <= sortIdxMax.intValue()) {

                                // for this item, dec idx
                                Integer nextSortIdx = nextNode.getSortIdx() - 1;

                                if (!Objects.equals(nextSortIdx_, nextSortIdx)) {
                                    nextNode.setSortIdx(nextSortIdx);
                                    logger.debug(logPrfx + " --- thisNode.setSortIdx(" + (nextSortIdx) + ")");
                                    nextNodeIsChanged = true;
                                    finalChngNodes.add(nextNode);
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


        chngNodes = finalChngNodes.stream().distinct().collect(Collectors.toList());
        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }

    private Integer getSortIdxMax(NodeT thisNode) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;
        if (thisNode.getParent1_Id() == null){
            String sortIdxMaxQry = "select max(e.sortIdx)"
                    + " from enty_"+ typeOfNodeT.getSimpleName() +" e"
                    ;

            try {
                sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                        .store("main")
                        .one();
                // max returns null if no rows or if all rows have a null value
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- sortIdxMaxQry error: " + e.getMessage());
            }
        }else{
            String sortIdxMaxQry = "select max(e.sortIdx)"
                    + " from enty_"+ typeOfNodeT.getSimpleName() +" e"
                    + " where e.parent1_Id = :parent_Id1"
                    ;
            try {
                sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                        .store("main")
                        .parameter("parent_Id1", thisNode.getParent1_Id())
                        .one();
                // max returns null if no rows or if all rows have a null value
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- sortIdxMaxQry error: " + e.getMessage());
            }
        }
        logger.debug(logPrfx + " --- sortIdxMaxQry result: " + sortIdxMax + "");

        logger.trace(logPrfx + " <-- ");
        return sortIdxMax;
    }

    protected void updateHelper() {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<NodeT> thisNodes = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisNodes.forEach(thisNode -> {
                //T thisTrackedNode = dataContext.merge(thisNode);
                if (thisNode != null) {
                    thisNode = dataContext.merge(thisNode);

                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = thisNode.updateId2Dup(dataManager) || thisNodeIsChanged;

                    if (thisNodeIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisNode).");
                        //dataManager.save(thisNode);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisNodes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    protected void updateHelper2(List<NodeT> chngNodes) {
        String logPrfx = "updateHelper2";
        logger.trace(logPrfx + " --> ");

        if(chngNodes != null && !chngNodes.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<NodeT> thisNodes = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngNodes.forEach(thisNode -> {
                //NodeT thisNode = dataContext.merge(thisNode);
                if (thisNode != null) {
                    thisNode = dataContext.merge(thisNode);
                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = thisNode.updateId2Dup(dataManager) || thisNodeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisNodes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    protected void updateHelper(List<NodeT> chngNodes) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(chngNodes != null && !chngNodes.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<NodeT> thisNodes = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngNodes.forEach(thisNode -> {
                //NodeT thisNode = dataContext.merge(thisNode);
                if (thisNode != null) {
                    thisNode = dataContext.merge(thisNode);
                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = thisNode.updateId2Dup(dataManager) || thisNodeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisNodes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {

                thisNode = dataContext.merge(thisNode);;

                boolean isChanged = false;

                isChanged = thisNode.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisNodes);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {

                thisNode = dataContext.merge(thisNode);;

                boolean isChanged = false;

                isChanged = thisNode.updateId2(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisNodes);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = event.getSource().getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            //todo I observed thisNode is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if (StringUtils.isEmpty(thisNode.getClassName())) {
            thisNode.setClassName(typeOfNodeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfNodeT.getSimpleName());
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            NodeT thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisNode.updateId2Deps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2(dataManager);
        thisNode.updateId2Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2Calc(dataManager);
        thisNode.updateId2Deps(dataManager);

        logger.debug(logPrfx + " --- id2Calc: " + thisNode.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2Cmp(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateName1(dataManager);
        thisNode.updateName1Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1GenFmla1_IdFieldListBtn")
    public void onUpdateName1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

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


    @Subscribe("updateInst1FieldBtn")
    public void onUpdateInst1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInst1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateInst1(dataManager);
        thisNode.updateInst1Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateDesc1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1GenFmla1_IdFieldListBtn")
    public void onUpdateDesc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

/*

    @Install(to = "genTags1_IdField", subject = "searchExecutor")
    protected List<UsrItemGenTag> genTags1_IdFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        return dataManager.load(UsrItemGenTag.class)
                .query("e.id2 like ?1 order by e.sortKey, e.id2"
                        , "(?i)%" + searchString + "%")
                .list();
    }
*/

    protected void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
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