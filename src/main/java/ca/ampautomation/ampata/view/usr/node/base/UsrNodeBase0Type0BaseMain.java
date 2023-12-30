package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseTypeGrpg;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public abstract class UsrNodeBase0Type0BaseMain<NodeTypeT extends UsrNodeBaseType, NodeTypeServiceT extends UsrNodeBase0Type0Service, NodeTypeRepoT extends UsrNodeBase0Type0Repo, TableT extends Table> extends MasterDetailScreen<NodeTypeT> {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<NodeTypeT> typeOfNodeTypeT;

    @SuppressWarnings("unchecked")
    public UsrNodeBase0Type0BaseMain() {
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<NodeTypeT> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    //Service
    protected NodeTypeServiceT service;

    protected NodeTypeServiceT getService(){
        return service;
    }

    public void setService(NodeTypeServiceT service) {
        this.service = service;
    }

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


    //Repository
    protected NodeTypeRepoT repo;

    protected NodeTypeRepoT getRepo(){
        return repo;
    }

    public void setRepo(NodeTypeRepoT repo) {
        this.repo = repo;
    }


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected  PropertyFilter<NodeTypeT> filterConfig1A_Parent1_Id;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Template
    @Autowired
    protected TextField<Integer> tmplt_SortIdxField;
    @Autowired
    protected RadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<NodeTypeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<NodeTypeT> colCntnrMain;
    @Autowired
    protected InstanceContainer<NodeTypeT> instCntnrMain;
    @Autowired
    protected TableT tableMain;


    //Other data loaders, containers and tables



    //Field
    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected TagField<UsrItemGenTag> genTags1_IdField;

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_SortIdxFieldRdo.setOptionsMap(map1);

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put(UpdateOption.SKIP.toString(), UpdateOption.SKIP.toInt());
        map2.put(UpdateOption.LOCAL.toString(), UpdateOption.LOCAL.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST.toString(), UpdateOption.LOCAL__REF_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_TO_EXIST_NEW.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toInt());
        updateColItemCalcValsOption.setOptionsMap(map2);
        updateColItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());
        updateInstItemCalcValsOption.setOptionsMap(map2);
        updateInstItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());


        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        //tableMain.sort("sortKey", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<NodeTypeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisNodeType is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeTypeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = event.getItem();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null");
            //todo I observed thisNodeType is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (thisNodeType.getClassName() == null || thisNodeType.getClassName().isBlank()){
            thisNodeType.setClassName(typeOfNodeTypeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfNodeTypeT.getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPr_Upd_AllCalcVals_ForAllRows");
        repo.execPr_Upd_AllCalcVals_ForAllRows();
        logger.debug(logPrfx + " --- finished repo.execPr_Upd_AllCalcVals_ForAllRows");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeTypes.forEach(orig -> {
            NodeTypeT copy = onDuplicateBtnClickHelper(orig);

            NodeTypeT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }


    public NodeTypeT onDuplicateBtnClickHelper(NodeTypeT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        NodeTypeT copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (orig.getId2().equals(copy.getId2())){
            copy.setId2(copy.getId2() + " Copy");
            copy.setId2Calc(copy.getId2());
        }
        logger.trace(logPrfx + " <-- ");
        return copy;
    }

    
    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<NodeTypeT> chngNodes = new ArrayList<>();
        List<NodeTypeT> finalChngNodes = chngNodes;

        thisNodeTypes.forEach(thisNodeType -> {
            thisNodeType = dataContext.merge(thisNodeType);
            if (thisNodeType != null) {

                Boolean thisNodeTypeIsChanged = false;

                thisNodeTypeIsChanged = onSetBtnClickHelper(thisNodeType);

                if (thisNodeTypeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisNodeType).");
                    //dataManager.save(thisNodeType);
                }
            }
        });
        chngNodes = finalChngNodes.stream().distinct().collect(Collectors.toList());
        updateHelper(chngNodes);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(NodeTypeT thisNodeType){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisNodeTypeIsChanged = false;

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodeTypeIsChanged = service.updateId2Calc(this, thisNodeType, updOption) || thisNodeTypeIsChanged;
        thisNodeTypeIsChanged = service.updateId2(this, thisNodeType, updOption) || thisNodeTypeIsChanged;
        thisNodeTypeIsChanged = service.updateId2Cmp(this, thisNodeType, updOption) || thisNodeTypeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisNodeTypeIsChanged;
    }



    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<NodeTypeT> chngNodeTypes = new ArrayList<>();

        Map<?, List<NodeTypeT>> grpgMap = getGrpgMap(thisNodeTypes);

        grpgMap.forEach((key, nodeTypes) -> {
            onRebuildSortIdxBtnHelper(key, nodeTypes, chngNodeTypes);

        });

        updateHelper(chngNodeTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper(Object grpgKey, List<NodeTypeT> grpgNodeTypes, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onRebuildSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        // get all adj Nodes that could be modified
        LogicalCondition logcCond = LogicalCondition.and();

        //Add condition for this group
        Condition grpgCond = getConditionGrpg(grpgKey);
        if (grpgCond != null) {
            logcCond.add(grpgCond);
        }

        List<NodeTypeT> adjNodeTypes = dataManager.load(typeOfNodeTypeT).condition(logcCond).list();

        AtomicInteger sortIdx = new AtomicInteger(0);
        // sort the nodes
        adjNodeTypes.sort(getComparator());

        //Update the adjacent nodes
        adjNodeTypes.forEach(adjNodeType -> {
            if (adjNodeType != null) {
                adjNodeType = dataContext.merge(adjNodeType);

                Boolean adjNodeTypeIsChanged = false;

                Integer sortIdx_ = adjNodeType.getSortIdx();
                logger.debug(logPrfx + " --- sortIdx:" + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    adjNodeType.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- called adjNodeType.setSortIdx(id2)");
                    adjNodeTypeIsChanged = true;
                    chngNodeTypes.add(adjNodeType);
                }
                sortIdx.incrementAndGet() ;
            }
        });


        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper2(Object grpgKey, NodeTypeT grpNodeType, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onRebuildSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = new ArrayList<NodeTypeT>(tableMain.getSelected().stream().toList());
        if (thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodeTypes.sort(getComparatorThisNode());

        List<NodeTypeT> chngNodeTypes = new ArrayList<>();

        Map<?, List<NodeTypeT>> grpgMap = getGrpgMap(thisNodeTypes);

        grpgMap.forEach((grpgKey, grpgNodeTypes) -> {
            onMoveFrstSortIdxBtnHelper2(grpgKey, grpgNodeTypes, chngNodeTypes);
        });

        updateHelper(chngNodeTypes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveFrstSortIdxBtnHelper2(Object grpgKey, List<NodeTypeT> grpgNodeTypes, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onMoveFrstSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        grpgNodeTypes.forEach(thisNode -> {
            onMoveFrstSortIdxBtnHelper(grpgKey, thisNode, sortIdxMin, chngNodeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveFrstSortIdxBtnHelper(Object grpgKey, NodeTypeT thisNodeType, AtomicInteger sortIdxMin, List<NodeTypeT> chngNodeTypes){
        String logPrfx = "onMoveFrstSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodeType = dataContext.merge(thisNodeType);
        Boolean thisNodeTypeIsChanged = false;

        Integer sortIdx_ = thisNodeType.getSortIdx();
        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin.intValue()){

            // for this node, set sortIdx to minSortIdx
            Integer sortIdx = sortIdxMin.intValue();
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            if (Objects.equals(sortIdx_, sortIdx)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisNodeType.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisNodeType.setSortIdx(sortIdx)");
                thisNodeTypeIsChanged = true;
                chngNodeTypes.add(thisNodeType);
            }

            // for all prev nodes, inc sortIdx

            // build condition
            LogicalCondition logcCond = LogicalCondition.and();
            logcCond.add(PropertyCondition.greaterOrEqual("sortIdx",sortIdxMin.intValue()));
            logcCond.add(PropertyCondition.less("sortIdx",sortIdx_));

            //Add condition for this group
            Condition grpgCond = getConditionGrpg(grpgKey);
            if (grpgCond != null) {
                logcCond.add(grpgCond);
            }

            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            List<NodeTypeT> prevNodeTypes = dataManager.load(typeOfNodeTypeT).condition(logcCond).list();

            prevNodeTypes.forEach(prevNodeType -> {
                if (prevNodeType != null) {
                    prevNodeType = dataContext.merge(prevNodeType);

                    Boolean prevNodeTypeIsChanged = false;

                    Integer prevNodeTypeSortIdx_ = prevNodeType.getSortIdx();
                    if (prevNodeTypeSortIdx_ != null
                            && prevNodeTypeSortIdx_ >= sortIdxMin.intValue()) {

                        // for prevNodeType, inc sortIdx
                        Integer prevNodeTypeSortIdx = prevNodeTypeSortIdx_ + 1;
                        logger.debug(logPrfx + "prevNodeTypeSortIdx: " + prevNodeTypeSortIdx);

                        prevNodeType.setSortIdx(prevNodeTypeSortIdx);
                        logger.debug(logPrfx + " --- prevNodeType.setSortIdx(prevNodeTypeSortIdx)");
                        prevNodeTypeIsChanged = true;
                        chngNodeTypes.add(prevNodeType);
                    }
                }
            });

            sortIdxMin.incrementAndGet();

            if (dataContext.hasChanges()) {
                // database write
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevSortIdxBtn")
    public void onMovePrevSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMovePrevSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = new ArrayList<NodeTypeT>(tableMain.getSelected().stream().toList());
        if (thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodeTypes.sort(getComparatorThisNode());
        List<NodeTypeT> chngNodeTypes = new ArrayList<>();

        Map<?, List<NodeTypeT>> grpgMap = getGrpgMap(thisNodeTypes);

        grpgMap.forEach((grpgKey, grpgNodeTypes) -> {
            onMovePrevSortIdxBtnHelper2(grpgKey, grpgNodeTypes, chngNodeTypes);
        });

        updateHelper(chngNodeTypes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper2(Object grpgKey, List<NodeTypeT> grpgNodeTypes, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onMovePrevSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create().withCaption("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        grpgNodeTypes.forEach(thisNodeType -> {
            onMovePrevSortIdxBtnHelper(grpgKey, thisNodeType, sortIdxMin, sortIdxMax, chngNodeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper(Object grpgKey, NodeTypeT thisNodeType, Integer sortIdxMin, Integer sortIdxMax, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onMovePrevSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType = dataContext.merge(thisNodeType);
        Boolean thisNodeTypeIsChanged = false;

        Integer sortIdx_ = thisNodeType.getSortIdx();

        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin){

            // for this node, dec sortIdx
            Integer sortIdx = sortIdx_ - 1;
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            thisNodeType.setSortIdx(sortIdx);
            logger.debug(logPrfx + " --- called thisNodeType.setSortIdx(sortIdx)");
            thisNodeTypeIsChanged = true;
            chngNodeTypes.add(thisNodeType);

            // for prev node, inc sortIdx

            // build condition
            LogicalCondition logcCond =  LogicalCondition.and();
            logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ - 1));

            //Add condition for this group
            Condition grpgCond = getConditionGrpg(grpgKey);
            if (grpgCond != null) {
                logcCond.add(grpgCond);
            }

            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            NodeTypeT prevNodeType = dataManager.load(typeOfNodeTypeT).condition(logcCond).one();

            if(prevNodeType != null){
                prevNodeType = dataContext.merge(prevNodeType);
                Boolean prevNodeTypeIsChanged = false;

                Integer prevNodeTypeSortIdx_ = prevNodeType.getSortIdx();
                if (prevNodeTypeSortIdx_ != null
                        && prevNodeTypeSortIdx_ < sortIdxMax){

                    Integer prevNodeSortIdx = prevNodeTypeSortIdx_ + 1;
                    logger.debug(logPrfx + "prevNodeSortIdx: " + prevNodeSortIdx);

                    prevNodeType.setSortIdx(prevNodeSortIdx);
                    logger.debug(logPrfx + " --- prevNodeType.setSortIdx(prevNodeSortIdx)");
                    prevNodeTypeIsChanged = true;
                    chngNodeTypes.add(prevNodeType);
                }
            }

            // database write
            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();
            }

        }

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("moveNextSortIdxBtn")
    public void onMoveNextSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveNextSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = new ArrayList<NodeTypeT>(tableMain.getSelected().stream().toList());
        if (thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodeTypes.sort(getComparatorThisNode());

        List<NodeTypeT> chngNodeTypes = new ArrayList<>();

        Map<?, List<NodeTypeT>> grpgMap = getGrpgMap(thisNodeTypes);

        grpgMap.forEach((grpgKey, grpgNodeTypes) -> {
            onMoveNextSortIdxBtnHelper2(grpgKey, grpgNodeTypes, chngNodeTypes);
        });

        updateHelper(chngNodeTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveNextSortIdxBtnHelper2(Object grpgKey, List<NodeTypeT> grpgNodeTypes, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);

        grpgNodeTypes.forEach(thisNodeType -> {
            onMoveNextSortIdxBtnHelper(grpgKey, thisNodeType, sortIdxMin, sortIdxMax, chngNodeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveNextSortIdxBtnHelper(Object grpgKey, NodeTypeT thisNodeType, Integer sortIdxMin, Integer sortIdxMax, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNodeType != null) {
            thisNodeType = dataContext.merge(thisNodeType);
            Boolean thisNodeTypeIsChanged = false;

            Integer sortIdx_ = thisNodeType.getSortIdx();

            if (sortIdxMax != null
                    && sortIdx_ < sortIdxMax){

                // for this node, inc sortIdx
                Integer sortIdx = sortIdx_ + 1;
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                thisNodeType.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisNodeType.setSortIdx(sortIdx)");
                thisNodeTypeIsChanged = true;
                chngNodeTypes.add(thisNodeType);


                // for next node, dec sortIdx

                // build condition
                LogicalCondition logcCond =  LogicalCondition.and();
                logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ + 1));

                //Add condition for this group
                Condition grpgCond = getConditionGrpg(grpgKey);
                if (grpgCond != null) {
                    logcCond.add(grpgCond);
                }

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                NodeTypeT nextNodeType = dataManager.load(typeOfNodeTypeT).condition(logcCond).one();

                if(nextNodeType != null){
                    nextNodeType = dataContext.merge(nextNodeType);
                    Boolean nextNodeTypeIsChanged = false;

                    Integer nextNodeTypeSortIdx_ = nextNodeType.getSortIdx();
                    if (nextNodeTypeSortIdx_ != null
                            && nextNodeTypeSortIdx_ > sortIdxMin){

                        Integer nextNodeTypeSortIdx = nextNodeTypeSortIdx_ - 1;
                        logger.debug(logPrfx + " --- nextNodeTypeSortIdx: " + sortIdx);

                        nextNodeType.setSortIdx(nextNodeTypeSortIdx);
                        logger.debug(logPrfx + " --- called nextNodeType.setSortIdx(nextNodeTypeSortIdx)");
                        nextNodeTypeIsChanged = true;
                        chngNodeTypes.add(nextNodeType);
                    }

                }

                if (dataContext.hasChanges()) {
                    logger.debug(logPrfx + " --- executing dataContext.commit().");
                    dataContext.commit();
                }

            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveLastSortIdxBtn")
    public void onMoveLastSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveLastSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = new ArrayList<NodeTypeT>(tableMain.getSelected().stream().toList());
        if (thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodeTypes.sort(getComparatorThisNode());

        List<NodeTypeT> chngNodeTypes = new ArrayList<>();

        Map<?, List<NodeTypeT>> grpgMap = getGrpgMap(thisNodeTypes);

        grpgMap.forEach((grpgKey, grpgNodeTypes) -> {
            onMoveLastSortIdxBtnHelper2(grpgKey, grpgNodeTypes, chngNodeTypes);
        });

        updateHelper(chngNodeTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveLastSortIdxBtnHelper2(Object grpgKey, List<NodeTypeT> grpgNodeTypes, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onMoveLastSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create().withCaption("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        AtomicInteger sortIdxMaxAtmc = new AtomicInteger(sortIdxMax);

        grpgNodeTypes.forEach(thisNodeType -> {
            onMoveLastSortIdxBtnHelper(grpgKey, thisNodeType, sortIdxMaxAtmc, chngNodeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveLastSortIdxBtnHelper(Object grpgKey, NodeTypeT thisNodeType, AtomicInteger sortIdxMax, List<NodeTypeT> chngNodeTypes) {
        String logPrfx = "onMoveLastSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNodeType != null) {
            thisNodeType = dataContext.merge(thisNodeType);

            Boolean thisNodeTypeIsChanged = false;

            Integer sortIdx_ = thisNodeType.getSortIdx();
            if (sortIdx_ != null
                    && sortIdx_ < sortIdxMax.intValue()){

                // for this node, max sortIdx
                Integer sortIdx = sortIdxMax.intValue();
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNodeType.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- called thisNodeType.setSortIdx(sortIdx)");
                    thisNodeTypeIsChanged = true;
                    chngNodeTypes.add(thisNodeType);
                }


                // for all next nodes, dec sortIdx

                // build condition
                LogicalCondition logcCond = LogicalCondition.and();
                logcCond.add(PropertyCondition.greater("sortIdx",sortIdx_));
                logcCond.add(PropertyCondition.lessOrEqual("sortIdx", sortIdxMax.intValue()));

                //Add condition for this group
                Condition grpgCond = getConditionGrpg(grpgKey);
                if (grpgCond != null) {
                    logcCond.add(grpgCond);
                }

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                List<NodeTypeT> nextNodeTypes = dataManager.load(typeOfNodeTypeT).condition(logcCond).list();

                nextNodeTypes.forEach(nextNodeType -> {
                    if (nextNodeType != null) {
                        nextNodeType = dataContext.merge(nextNodeType);

                        Boolean nextNodeTypeIsChanged = false;

                        Integer nextSortIdx_ = nextNodeType.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ <= sortIdxMax.intValue()) {

                            // for this item, dec sortIdx
                            Integer nextSortIdx = nextNodeType.getSortIdx() - 1;
                            logger.debug(logPrfx + "nextSortIdx: " + nextSortIdx);

                            nextNodeType.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextNodeType.setSortIdx(nextSortIdx)");
                            nextNodeTypeIsChanged = true;
                            chngNodeTypes.add(nextNodeType);
                        }
                    }
                });

                sortIdxMax.decrementAndGet();

                // database write
                if (dataContext.hasChanges()) {
                    logger.debug(logPrfx + " --- executing dataContext.commit().");
                    dataContext.commit();
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    
    protected Map<?, List<NodeTypeT>> getGrpgMap(List<NodeTypeT> thisNodeTypes){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrNodeBaseTypeGrpg, List<NodeTypeT>> grpMap = thisNodeTypes.stream()
                .collect(groupingBy(nodeType -> new UsrNodeBaseTypeGrpg(nodeType.getParent1_Id())));

        logger.trace(logPrfx + " <-- ");
        return grpMap;
    }

    protected Condition getConditionGrpg(Object grpgKey){
        String logPrfx = "getConditionGrpg";
        logger.trace(logPrfx + " --> ");

        // Ensure grpgKey is instanceof UsrNodeBaseGrpg
        if (!(grpgKey instanceof UsrNodeBaseGrpg l_grpgKey)){
            logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeBaseGrpg");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        PropertyCondition cond;
        //Add condition for this group
        cond = PropertyCondition.equal("parent1_Id",l_grpgKey.parent1_Id());

        logger.trace(logPrfx + " <-- ");
        return cond;
    }

    protected Comparator<NodeTypeT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<NodeTypeT> comparator = Comparator.comparing(
                NodeTypeT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }

    protected void updateHelper(List<NodeTypeT> chngNodes) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");


        if(chngNodes != null && !chngNodes.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngNodes.forEach(thisNodeType -> {
                //NodeTypeT thisNodeType = dataContext.merge(thisNodeType);
                if (thisNodeType != null) {
                    thisNodeType = dataContext.merge(thisNodeType);
                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = service.updateId2Dup(this, thisNodeType, updOption) || thisNodeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisNodeTypes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodeTypes.forEach(thisNodeType -> {
            if (thisNodeType != null) {

                thisNodeType = dataContext.merge(thisNodeType);;

                boolean isChanged = false;

                isChanged = service.updateCalcVals(this, thisNodeType, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisNodeTypes);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodeTypes.forEach(thisNodeType -> {
            if (thisNodeType != null) {

                thisNodeType = dataContext.merge(thisNodeType);;

                boolean isChanged = false;

                isChanged = service.updateId2(this, thisNodeType, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisNodeTypes);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeTypeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = event.getSource().getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            //todo I observed thisNodeType is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (StringUtils.isEmpty(thisNodeType.getClassName())) {
            thisNodeType.setClassName(typeOfNodeTypeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfNodeTypeT.getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNodeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
            if (thisNodeType == null) {
                logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisNodeType, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisNodeType, updOption);
        service.updateId2Deps(this, thisNodeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisNodeType, updOption);
        service.updateId2Deps(this, thisNodeType, updOption);

        logger.debug(logPrfx + " --- id2Calc: " + thisNodeType.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisNodeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisNodeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            NodeTypeT thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSortKeyFieldBtn")
    public void onUpdateSortKeyFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisNodeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisNodeType, updOption);
        service.updateName1Deps(this, thisNodeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        //todo
        //service.updateDesc1(thisNodeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


}