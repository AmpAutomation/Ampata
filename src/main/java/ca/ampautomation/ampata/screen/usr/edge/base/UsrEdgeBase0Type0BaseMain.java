package ca.ampautomation.ampata.screen.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseTypeGrpg;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.edge.base.UsrEdgeBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.edge.base.UsrEdgeBase0Type0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public abstract class UsrEdgeBase0Type0BaseMain<EdgeTypeT extends UsrEdgeBaseType, EdgeTypeServiceT extends UsrEdgeBase0Type0Service, EdgeTypeRepoT extends UsrEdgeBase0Type0Repo, TableT extends Table> extends MasterDetailScreen<EdgeTypeT> {



    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<EdgeTypeT> typeOfEdgeTypeT;

    @SuppressWarnings("unchecked")
    public UsrEdgeBase0Type0BaseMain() {
        this.typeOfEdgeTypeT = (Class<EdgeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<EdgeTypeT> getTable() { return (ListComponent) getWindow().getComponentNN("tableMain"); }

    //Service
    protected EdgeTypeServiceT service;

    protected EdgeTypeServiceT getService(){ return service; }

    public void setService(EdgeTypeServiceT service) { this.service = service; }

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
    protected EdgeTypeRepoT repo;

    protected EdgeTypeRepoT getRepo(){
        return repo;
    }

    public void setRepo(EdgeTypeRepoT repo) {
        this.repo = repo;
    }


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected  PropertyFilter<EdgeTypeT> filterConfig1A_Parent1_Id;


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
    protected CollectionLoader<EdgeTypeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<EdgeTypeT> colCntnrMain;
    @Autowired
    protected InstanceContainer<EdgeTypeT> instCntnrMain;
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
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<EdgeTypeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisEdgeType is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<EdgeTypeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = event.getItem();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null");
            //todo I observed thisEdgeType is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (thisEdgeType.getClassName() == null || thisEdgeType.getClassName().isBlank()){
            thisEdgeType.setClassName(typeOfEdgeTypeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfEdgeTypeT.getSimpleName());
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

        List<EdgeTypeT> thisEdgeTypes = tableMain.getSelected().stream().toList();
        if (thisEdgeTypes == null || thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeTypes.forEach(orig -> {
            EdgeTypeT copy = onDuplicateBtnClickHelper(orig);

            EdgeTypeT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }


    public EdgeTypeT onDuplicateBtnClickHelper(EdgeTypeT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT copy = metadataTools.copy(orig);
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

        List<EdgeTypeT> thisEdgeTypes = tableMain.getSelected().stream().toList();
        if (thisEdgeTypes == null || thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<EdgeTypeT> chngEdges = new ArrayList<>();
        List<EdgeTypeT> finalChngEdges = chngEdges;

        thisEdgeTypes.forEach(thisEdgeType -> {
            thisEdgeType = dataContext.merge(thisEdgeType);
            if (thisEdgeType != null) {

                Boolean thisEdgeTypeIsChanged = false;

                thisEdgeTypeIsChanged = onSetBtnClickHelper(thisEdgeType);

                if (thisEdgeTypeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisEdgeType).");
                    //dataManager.save(thisEdgeType);
                }
            }
        });
        chngEdges = finalChngEdges.stream().distinct().collect(Collectors.toList());
        updateHelper(chngEdges);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(EdgeTypeT thisEdgeType){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisEdgeTypeIsChanged = false;

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisEdgeTypeIsChanged = service.updateId2Calc(this, thisEdgeType, updOption) || thisEdgeTypeIsChanged;
        thisEdgeTypeIsChanged = service.updateId2(this, thisEdgeType, updOption) || thisEdgeTypeIsChanged;
        thisEdgeTypeIsChanged = service.updateId2Cmp(this, thisEdgeType, updOption) || thisEdgeTypeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisEdgeTypeIsChanged;
    }



    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeTypeT> thisEdgeTypes = tableMain.getSelected().stream().toList();
        if (thisEdgeTypes == null || thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<EdgeTypeT> chngEdgeTypes = new ArrayList<>();

        Map<?, List<EdgeTypeT>> grpgMap = getGrpgMap(thisEdgeTypes);

        grpgMap.forEach((key, nodeTypes) -> {
            onRebuildSortIdxBtnHelper(key, nodeTypes, chngEdgeTypes);

        });

        updateHelper(chngEdgeTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper(Object grpgKey, List<EdgeTypeT> grpgEdgeTypes, List<EdgeTypeT> chngEdgeTypes) {
        String logPrfx = "onRebuildSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        // get all adj Edges that could be modified
        LogicalCondition logcCond = LogicalCondition.and();

        //Add condition for this group
        Condition grpgCond = getConditionGrpg(grpgKey);
        if (grpgCond != null) {
            logcCond.add(grpgCond);
        }

        List<EdgeTypeT> adjEdgeTypes = dataManager.load(typeOfEdgeTypeT).condition(logcCond).list();

        AtomicInteger sortIdx = new AtomicInteger(0);
        // sort the nodes
        adjEdgeTypes.sort(getComparator());

        //Update the adjacent nodes
        adjEdgeTypes.forEach(adjEdgeType -> {
            if (adjEdgeType != null) {
                adjEdgeType = dataContext.merge(adjEdgeType);

                Boolean adjEdgeTypeIsChanged = false;

                Integer sortIdx_ = adjEdgeType.getSortIdx();
                logger.debug(logPrfx + " --- sortIdx:" + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    adjEdgeType.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- called adjEdgeType.setSortIdx(id2)");
                    adjEdgeTypeIsChanged = true;
                    chngEdgeTypes.add(adjEdgeType);
                }
                sortIdx.incrementAndGet() ;
            }
        });


        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper2(Object grpgKey, EdgeTypeT grpEdgeType, List<EdgeTypeT> chngEdgeTypes) {
        String logPrfx = "onRebuildSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeTypeT> thisEdgeTypes = new ArrayList<EdgeTypeT>(tableMain.getSelected().stream().toList());
        if (thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdgeTypes.sort(getComparatorThisEdge());

        List<EdgeTypeT> chngEdgeTypes = new ArrayList<>();

        Map<?, List<EdgeTypeT>> grpgMap = getGrpgMap(thisEdgeTypes);

        grpgMap.forEach((grpgKey, grpgEdgeTypes) -> {
            onMoveFrstSortIdxBtnHelper2(grpgKey, grpgEdgeTypes, chngEdgeTypes);
        });

        updateHelper(chngEdgeTypes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveFrstSortIdxBtnHelper2(Object grpgKey, List<EdgeTypeT> grpgEdgeTypes, List<EdgeTypeT> chngEdgeTypes) {
        String logPrfx = "onMoveFrstSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        grpgEdgeTypes.forEach(thisEdge -> {
            onMoveFrstSortIdxBtnHelper(grpgKey, thisEdge, sortIdxMin, chngEdgeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveFrstSortIdxBtnHelper(Object grpgKey, EdgeTypeT thisEdgeType, AtomicInteger sortIdxMin, List<EdgeTypeT> chngEdgeTypes){
        String logPrfx = "onMoveFrstSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisEdgeType = dataContext.merge(thisEdgeType);
        Boolean thisEdgeTypeIsChanged = false;

        Integer sortIdx_ = thisEdgeType.getSortIdx();
        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin.intValue()){

            // for this node, set sortIdx to minSortIdx
            Integer sortIdx = sortIdxMin.intValue();
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            if (Objects.equals(sortIdx_, sortIdx)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisEdgeType.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisEdgeType.setSortIdx(sortIdx)");
                thisEdgeTypeIsChanged = true;
                chngEdgeTypes.add(thisEdgeType);
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
            List<EdgeTypeT> prevEdgeTypes = dataManager.load(typeOfEdgeTypeT).condition(logcCond).list();

            prevEdgeTypes.forEach(prevEdgeType -> {
                if (prevEdgeType != null) {
                    prevEdgeType = dataContext.merge(prevEdgeType);

                    Boolean prevEdgeTypeIsChanged = false;

                    Integer prevEdgeTypeSortIdx_ = prevEdgeType.getSortIdx();
                    if (prevEdgeTypeSortIdx_ != null
                            && prevEdgeTypeSortIdx_ >= sortIdxMin.intValue()) {

                        // for prevEdgeType, inc sortIdx
                        Integer prevEdgeTypeSortIdx = prevEdgeTypeSortIdx_ + 1;
                        logger.debug(logPrfx + "prevEdgeTypeSortIdx: " + prevEdgeTypeSortIdx);

                        prevEdgeType.setSortIdx(prevEdgeTypeSortIdx);
                        logger.debug(logPrfx + " --- prevEdgeType.setSortIdx(prevEdgeTypeSortIdx)");
                        prevEdgeTypeIsChanged = true;
                        chngEdgeTypes.add(prevEdgeType);
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

        List<EdgeTypeT> thisEdgeTypes = new ArrayList<EdgeTypeT>(tableMain.getSelected().stream().toList());
        if (thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdgeTypes.sort(getComparatorThisEdge());
        List<EdgeTypeT> chngEdgeTypes = new ArrayList<>();

        Map<?, List<EdgeTypeT>> grpgMap = getGrpgMap(thisEdgeTypes);

        grpgMap.forEach((grpgKey, grpgEdgeTypes) -> {
            onMovePrevSortIdxBtnHelper2(grpgKey, grpgEdgeTypes, chngEdgeTypes);
        });

        updateHelper(chngEdgeTypes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper2(Object grpgKey, List<EdgeTypeT> grpgEdgeTypes, List<EdgeTypeT> chngEdgeTypes) {
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

        grpgEdgeTypes.forEach(thisEdgeType -> {
            onMovePrevSortIdxBtnHelper(grpgKey, thisEdgeType, sortIdxMin, sortIdxMax, chngEdgeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper(Object grpgKey, EdgeTypeT thisEdgeType, Integer sortIdxMin, Integer sortIdxMax, List<EdgeTypeT> chngEdgeTypes) {
        String logPrfx = "onMovePrevSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType = dataContext.merge(thisEdgeType);
        Boolean thisEdgeTypeIsChanged = false;

        Integer sortIdx_ = thisEdgeType.getSortIdx();

        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin){

            // for this node, dec sortIdx
            Integer sortIdx = sortIdx_ - 1;
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            thisEdgeType.setSortIdx(sortIdx);
            logger.debug(logPrfx + " --- called thisEdgeType.setSortIdx(sortIdx)");
            thisEdgeTypeIsChanged = true;
            chngEdgeTypes.add(thisEdgeType);

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
            EdgeTypeT prevEdgeType = dataManager.load(typeOfEdgeTypeT).condition(logcCond).one();

            if(prevEdgeType != null){
                prevEdgeType = dataContext.merge(prevEdgeType);
                Boolean prevEdgeTypeIsChanged = false;

                Integer prevEdgeTypeSortIdx_ = prevEdgeType.getSortIdx();
                if (prevEdgeTypeSortIdx_ != null
                        && prevEdgeTypeSortIdx_ < sortIdxMax){

                    Integer prevEdgeSortIdx = prevEdgeTypeSortIdx_ + 1;
                    logger.debug(logPrfx + "prevEdgeSortIdx: " + prevEdgeSortIdx);

                    prevEdgeType.setSortIdx(prevEdgeSortIdx);
                    logger.debug(logPrfx + " --- prevEdgeType.setSortIdx(prevEdgeSortIdx)");
                    prevEdgeTypeIsChanged = true;
                    chngEdgeTypes.add(prevEdgeType);
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

        List<EdgeTypeT> thisEdgeTypes = new ArrayList<EdgeTypeT>(tableMain.getSelected().stream().toList());
        if (thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdgeTypes.sort(getComparatorThisEdge());

        List<EdgeTypeT> chngEdgeTypes = new ArrayList<>();

        Map<?, List<EdgeTypeT>> grpgMap = getGrpgMap(thisEdgeTypes);

        grpgMap.forEach((grpgKey, grpgEdgeTypes) -> {
            onMoveNextSortIdxBtnHelper2(grpgKey, grpgEdgeTypes, chngEdgeTypes);
        });

        updateHelper(chngEdgeTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveNextSortIdxBtnHelper2(Object grpgKey, List<EdgeTypeT> grpgEdgeTypes, List<EdgeTypeT> chngEdgeTypes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);

        grpgEdgeTypes.forEach(thisEdgeType -> {
            onMoveNextSortIdxBtnHelper(grpgKey, thisEdgeType, sortIdxMin, sortIdxMax, chngEdgeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveNextSortIdxBtnHelper(Object grpgKey, EdgeTypeT thisEdgeType, Integer sortIdxMin, Integer sortIdxMax, List<EdgeTypeT> chngEdgeTypes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdgeType != null) {
            thisEdgeType = dataContext.merge(thisEdgeType);
            Boolean thisEdgeTypeIsChanged = false;

            Integer sortIdx_ = thisEdgeType.getSortIdx();

            if (sortIdxMax != null
                    && sortIdx_ < sortIdxMax){

                // for this node, inc sortIdx
                Integer sortIdx = sortIdx_ + 1;
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                thisEdgeType.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisEdgeType.setSortIdx(sortIdx)");
                thisEdgeTypeIsChanged = true;
                chngEdgeTypes.add(thisEdgeType);


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
                EdgeTypeT nextEdgeType = dataManager.load(typeOfEdgeTypeT).condition(logcCond).one();

                if(nextEdgeType != null){
                    nextEdgeType = dataContext.merge(nextEdgeType);
                    Boolean nextEdgeTypeIsChanged = false;

                    Integer nextEdgeTypeSortIdx_ = nextEdgeType.getSortIdx();
                    if (nextEdgeTypeSortIdx_ != null
                            && nextEdgeTypeSortIdx_ > sortIdxMin){

                        Integer nextEdgeTypeSortIdx = nextEdgeTypeSortIdx_ - 1;
                        logger.debug(logPrfx + " --- nextEdgeTypeSortIdx: " + sortIdx);

                        nextEdgeType.setSortIdx(nextEdgeTypeSortIdx);
                        logger.debug(logPrfx + " --- called nextEdgeType.setSortIdx(nextEdgeTypeSortIdx)");
                        nextEdgeTypeIsChanged = true;
                        chngEdgeTypes.add(nextEdgeType);
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

        List<EdgeTypeT> thisEdgeTypes = new ArrayList<EdgeTypeT>(tableMain.getSelected().stream().toList());
        if (thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeTypes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdgeTypes.sort(getComparatorThisEdge());

        List<EdgeTypeT> chngEdgeTypes = new ArrayList<>();

        Map<?, List<EdgeTypeT>> grpgMap = getGrpgMap(thisEdgeTypes);

        grpgMap.forEach((grpgKey, grpgEdgeTypes) -> {
            onMoveLastSortIdxBtnHelper2(grpgKey, grpgEdgeTypes, chngEdgeTypes);
        });

        updateHelper(chngEdgeTypes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveLastSortIdxBtnHelper2(Object grpgKey, List<EdgeTypeT> grpgEdgeTypes, List<EdgeTypeT> chngEdgeTypes) {
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

        grpgEdgeTypes.forEach(thisEdgeType -> {
            onMoveLastSortIdxBtnHelper(grpgKey, thisEdgeType, sortIdxMaxAtmc, chngEdgeTypes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveLastSortIdxBtnHelper(Object grpgKey, EdgeTypeT thisEdgeType, AtomicInteger sortIdxMax, List<EdgeTypeT> chngEdgeTypes) {
        String logPrfx = "onMoveLastSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdgeType != null) {
            thisEdgeType = dataContext.merge(thisEdgeType);

            Boolean thisEdgeTypeIsChanged = false;

            Integer sortIdx_ = thisEdgeType.getSortIdx();
            if (sortIdx_ != null
                    && sortIdx_ < sortIdxMax.intValue()){

                // for this node, max sortIdx
                Integer sortIdx = sortIdxMax.intValue();
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisEdgeType.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- called thisEdgeType.setSortIdx(sortIdx)");
                    thisEdgeTypeIsChanged = true;
                    chngEdgeTypes.add(thisEdgeType);
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
                List<EdgeTypeT> nextEdgeTypes = dataManager.load(typeOfEdgeTypeT).condition(logcCond).list();

                nextEdgeTypes.forEach(nextEdgeType -> {
                    if (nextEdgeType != null) {
                        nextEdgeType = dataContext.merge(nextEdgeType);

                        Boolean nextEdgeTypeIsChanged = false;

                        Integer nextSortIdx_ = nextEdgeType.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ <= sortIdxMax.intValue()) {

                            // for this item, dec sortIdx
                            Integer nextSortIdx = nextEdgeType.getSortIdx() - 1;
                            logger.debug(logPrfx + "nextSortIdx: " + nextSortIdx);

                            nextEdgeType.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextEdgeType.setSortIdx(nextSortIdx)");
                            nextEdgeTypeIsChanged = true;
                            chngEdgeTypes.add(nextEdgeType);
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


    protected Map<?, List<EdgeTypeT>> getGrpgMap(List<EdgeTypeT> thisEdgeTypes){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrEdgeBaseTypeGrpg, List<EdgeTypeT>> grpMap = thisEdgeTypes.stream()
                .collect(groupingBy(nodeType -> new UsrEdgeBaseTypeGrpg()));

        logger.trace(logPrfx + " <-- ");
        return grpMap;
    }

    protected Condition getConditionGrpg(Object grpgKey){
        String logPrfx = "getConditionGrpg";
        logger.trace(logPrfx + " --> ");

        // Ensure grpgKey is instanceof UsrEdgeBaseGrpg
        if (!(grpgKey instanceof UsrEdgeBaseGrpg l_grpgKey)){
            logger.trace(logPrfx + " --- grpgKey is not instanceof UsrEdgeBaseGrpg");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        //todo
        PropertyCondition cond = null;
        //Add condition for this group
        //cond = PropertyCondition.equal("parent1_Id",l_grpgKey.parent1_Id());

        logger.trace(logPrfx + " <-- ");
        return cond;
    }

    protected Comparator<EdgeTypeT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<EdgeTypeT> comparator = Comparator.comparing(
                EdgeTypeT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }

    protected void updateHelper(List<EdgeTypeT> chngEdges) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");


        if(chngEdges != null && !chngEdges.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<EdgeTypeT> thisEdgeTypes = tableMain.getSelected().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngEdges.forEach(thisEdgeType -> {
                //EdgeTypeT thisEdgeType = dataContext.merge(thisEdgeType);
                if (thisEdgeType != null) {
                    thisEdgeType = dataContext.merge(thisEdgeType);
                    Boolean thisEdgeIsChanged = false;

                    thisEdgeIsChanged = service.updateId2Dup(this, thisEdgeType, updOption) || thisEdgeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisEdgeTypes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeTypeT> thisEdgeTypes = tableMain.getSelected().stream().toList();
        if (thisEdgeTypes == null || thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisEdgeTypes.forEach(thisEdgeType -> {
            if (thisEdgeType != null) {

                thisEdgeType = dataContext.merge(thisEdgeType);;

                boolean isChanged = false;

                isChanged = service.updateCalcVals(this, thisEdgeType, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisEdgeTypes);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeTypeT> thisEdgeTypes = tableMain.getSelected().stream().toList();
        if (thisEdgeTypes == null || thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisEdgeTypes.forEach(thisEdgeType -> {
            if (thisEdgeType != null) {

                thisEdgeType = dataContext.merge(thisEdgeType);;

                boolean isChanged = false;

                isChanged = service.updateId2(this, thisEdgeType, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisEdgeTypes);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<EdgeTypeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = event.getSource().getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            //todo I observed thisEdgeType is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (StringUtils.isEmpty(thisEdgeType.getClassName())) {
            thisEdgeType.setClassName(typeOfEdgeTypeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfEdgeTypeT.getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisEdgeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
            if (thisEdgeType == null) {
                logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisEdgeType, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisEdgeType, updOption);
        service.updateId2Deps(this, thisEdgeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisEdgeType, updOption);
        service.updateId2Deps(this, thisEdgeType, updOption);

        logger.debug(logPrfx + " --- id2Calc: " + thisEdgeType.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisEdgeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisEdgeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            EdgeTypeT thisEdge = instCntnrMain.getItemOrNull();
            if (thisEdge == null) {
                logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisEdge, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSortKeyFieldBtn")
    public void onUpdateSortKeyFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisEdgeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisEdgeType, updOption);
        service.updateName1Deps(this, thisEdgeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeTypeT thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        //todo
        //service.updateDesc1(thisEdgeType, updOption);

        logger.trace(logPrfx + " <-- ");
    }



}