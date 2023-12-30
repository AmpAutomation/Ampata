package ca.ampautomation.ampata.view.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBase;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.edge.base.UsrEdgeBase0Repo;
import ca.ampautomation.ampata.service.usr.edge.base.UsrEdgeBase0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public abstract class UsrEdgeBase0BaseMain<EdgeT extends UsrEdgeBase, EdgeTypeT extends UsrEdgeBaseType, EdgeServiceT extends UsrEdgeBase0Service, EdgeRepoT extends UsrEdgeBase0Repo, TableT extends Table> extends MasterDetailScreen<EdgeT> implements UsrEdgeBase0BaseComn {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<EdgeT> typeOfEdgeT;
    protected Class<EdgeTypeT> typeOfEdgeTypeT;
    protected Class<EdgeServiceT> typeOfEdgeServiceT;
    protected Class<EdgeRepoT> typeOfEdgeRepoT;

    @SuppressWarnings("unchecked")
    public UsrEdgeBase0BaseMain() {
        this.typeOfEdgeT = (Class<EdgeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfEdgeTypeT = (Class<EdgeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[1];
        this.typeOfEdgeServiceT = (Class<EdgeServiceT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[2];
        this.typeOfEdgeRepoT = (Class<EdgeRepoT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[3];
    }

    protected ListComponent<EdgeT> getTable() { return (ListComponent) getWindow().getComponentNN("tableMain");}

    //Service
    protected EdgeServiceT service;

    protected EdgeServiceT getService() { return service; }

    public void setService(EdgeServiceT service) { this.service = service; }

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
    protected EdgeRepoT repo;

    protected EdgeRepoT getRepo(){ return repo; }

    public void setRepo(EdgeRepoT repo) { this.repo = repo; }


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<EdgeTypeT> filterConfig1A_Type1_Id;


    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<EdgeTypeT> tmplt_Type1_IdField;

    @Autowired
    protected TextField<Integer> tmplt_SortIdxField;
    @Autowired
    protected RadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<EdgeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<EdgeT> colCntnrMain;
    @Autowired
    protected InstanceContainer<EdgeT> instCntnrMain;
    @Autowired
    protected TableT tableMain;


    //Type data container and loader
    protected CollectionContainer<EdgeTypeT> colCntnrType;
    protected CollectionLoader<EdgeTypeT> colLoadrType;


    //GenTag data container loader and data property container
    protected CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    protected CollectionLoader<UsrItemGenTag> colLoadrGenTag;
    @Autowired
    protected CollectionPropertyContainer<UsrItemGenTag> colPropCntnrGenTag;



    //Field
    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected EntityComboBox<EdgeTypeT> type1_IdField;

    @Autowired
    protected TagField<UsrItemGenTag> genTags1_IdField;


    /**
     * InitEvent is sent when the screen controller and all its declaratively defined
     * components are created, and dependency injection is completed. Nested fragments
     * are not initialized yet. Some visual components are not fully initialized,
     * for example, buttons are not linked with actions.
     *
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

/*
        try{
            repo = typeOfEdgeRepoT.getDeclaredConstructor().newInstance();

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
*/

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

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfEdgeTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfEdgeTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfEdgeTypeT)
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
        EntityComboBox<EdgeTypeT> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<EdgeTypeT>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);

        colCntnrGenTag = dataComponents.createCollectionContainer(UsrItemGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrItemGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag = fetchPlans.builder(UsrItemGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        logger.trace(logPrfx + " <-- ");
    }

    /**
     * InitEntityEvent is sent in screens inherited from StandardEditor and MasterDetailScreen
     * before the new entity instance is set to edited entity container.
     * Use this event listener to initialize default values in the new entity instance
     */
    @Subscribe
    public void onInitEntity(InitEntityEvent<EdgeT> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = event.getEntity();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");

    }

    /**
     * AfterInitEvent is sent when the screen controller and all its declaratively defined components are created,
     * dependency injection is completed, and all components have completed their internal initialization procedures.
     * Nested screen fragments (if any) have sent their InitEvent and AfterInitEvent. In this event listener, you can
     * create visual and data components and perform additional initialization if it depends on initialized nested
     * fragments.
     */
    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        String logPrfx = "onAfterInit";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    /**
     * BeforeShowEvent is sent right before the screen is shown, for example, it is
     * not added to the application UI yet.
     * Security restrictions are applied to UI components. In this event listener, you can load data,
     * check permissions and modify UI components.
     */
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        //logger.debug(logPrfx + " --- calling colLoadrMain.load() ");
        //colLoadrMain.load();
        //logger.debug(logPrfx + " --- called colLoadrMain.load() ");
        //tableMain.sort("sortKey", Table.SortDirection.ASCENDING);

/*
        String currentTenantId = tenantProvider.getCurrentUserTenantId();
        if (!currentTenantId.equals(TenantProvider.NO_TENANT)
                && Strings.isNullOrEmpty(tenantField.getValue())) {
            //tenantField.setEditable(false);
            tenantField.setValue(currentTenantId);
        }
*/
        logger.trace(logPrfx + " <-- ");

    }

    /**
     * AfterShowEvent is sent right after the screen is shown, for example, when
     * it is added to the application UI.
     * In this event listener, you can show notifications, dialogs or other screens
     */
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        String logPrfx = "onAfterShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- Changed entity: " + event.getEntity());

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<EdgeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisEdge is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<EdgeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = event.getItem();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null");
            //todo I observed thisEdge is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (thisEdge.getClassName() == null || thisEdge.getClassName().isBlank()){
            thisEdge.setClassName(this.getClass().getSimpleName());
            logger.debug(logPrfx + " --- className: " + this.getClass().getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrType.load() ");
        colLoadrType.load();
        logger.debug(logPrfx + " --- finished colLoadrType.load() ");

        logger.debug(logPrfx + " --- executing colLoadrGenTag.load() ");
        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- finished colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPr_Upd_AllCalcVals_ForAllRows");
        repo.execPr_Upd_AllCalcVals_ForAllRows();
        logger.debug(logPrfx + " --- finished repo.execPr_Upd_AllCalcVals_ForAllRows");

        logger.debug(logPrfx + " --- executing colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("deleteColDeletedColBtn")
    public void onDeleteColDeletedColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColDeletedColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPr_Del_ForDeletedRows");
        repo.execPr_Del_ForDeletedRows();
        logger.debug(logPrfx + " --- finished repo.execPr_Del_ForDeletedRows");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdges.forEach(orig -> {
            EdgeT copy = onDuplicateBtnClickHelper(orig);

            EdgeT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }

    public EdgeT onDuplicateBtnClickHelper(EdgeT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        EdgeT copy = metadataTools.copy(orig);
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

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<EdgeT> chngEdges = new ArrayList<>();

        thisEdges.forEach(thisEdge -> {
            thisEdge = dataContext.merge(thisEdge);
            if (thisEdge != null) {

                Boolean thisEdgeIsChanged = false;

                thisEdgeIsChanged = onSetBtnClickHelper(thisEdge);
                if(thisEdgeIsChanged){
                    chngEdges.add(thisEdge);
                }

                if (thisEdgeIsChanged) {
                    //logger.debug(logPrfx + " --- executing dataManager.save(thisEdge).");
                    //dataManager.save(thisEdge);
                    //logger.debug(logPrfx + " --- finished dataManager.save(thisEdge).");
                }
            }
        });
        updateHelper(chngEdges);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(EdgeT thisEdge){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisEdgeIsChanged = false;

        if (tmplt_Type1_IdFieldChk.isChecked()
        ) {
            thisEdgeIsChanged = true;
            thisEdge.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisEdgeIsChanged = service.updateId2Calc(this, thisEdge, updOption) || thisEdgeIsChanged;
        thisEdgeIsChanged = service.updateId2(this, thisEdge, updOption) || thisEdgeIsChanged;
        thisEdgeIsChanged = service.updateId2Cmp(this, thisEdge, updOption) || thisEdgeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisEdgeIsChanged;
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<EdgeT> chngEdges = new ArrayList<>();

        Map<?, List<EdgeT>> grpgMap = getGrpgMap(thisEdges);

        grpgMap.forEach((key, nodes) -> {
            onRebuildSortIdxBtnHelper(key, nodes, chngEdges);

        });

        updateHelper(chngEdges);

        logger.trace(logPrfx + " <-- ");
    }

    void onRebuildSortIdxBtnHelper(Object grpgKey, List<EdgeT> grpgEdges, List<EdgeT> chngEdges) {
        String logPrfx = "onRebuildSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        // get all adj Edges that could be modified
        LogicalCondition logcCond = LogicalCondition.and();

        //Add condition for this group
        Condition grpgCond = getConditionGrpg(grpgKey);
        if (grpgCond != null) {
            logcCond.add(grpgCond);
        }

        List<EdgeT> adjEdges = dataManager.load(typeOfEdgeT).condition(logcCond).list();

        AtomicInteger sortIdx = new AtomicInteger(0);
        // sort the nodes
        adjEdges.sort(getComparator());

        //Update the adjacent nodes
        adjEdges.forEach(adjEdge -> {
            if (adjEdge != null) {
                adjEdge = dataContext.merge(adjEdge);

                Boolean adjEdgeIsChanged = false;

                Integer sortIdx_ = adjEdge.getSortIdx();
                logger.debug(logPrfx + " --- sortIdx:" + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    adjEdge.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- called adjEdge.setSortIdx(id2)");
                    adjEdgeIsChanged = true;
                    chngEdges.add(adjEdge);
                }
                sortIdx.incrementAndGet() ;
            }
        });


        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper2(Object grpgKey, EdgeT grpEdge, List<EdgeT> chngEdges) {
        String logPrfx = "onRebuildSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(tableMain.getSelected().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdges.sort(getComparatorThisEdge());

        List<EdgeT> chngEdges = new ArrayList<>();

        Map<?, List<EdgeT>> grpgMap = getGrpgMap(thisEdges);

        grpgMap.forEach((grpgKey, grpgEdges) -> {
            onMoveFrstSortIdxBtnHelper2(grpgKey, grpgEdges, chngEdges);
        });

        updateHelper(chngEdges);

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveFrstSortIdxBtnHelper2(Object grpgKey, List<EdgeT> grpgEdges, List<EdgeT> chngEdges) {
        String logPrfx = "onMoveFrstSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        grpgEdges.forEach(thisEdge -> {
            onMoveFrstSortIdxBtnHelper(grpgKey, thisEdge, sortIdxMin, chngEdges);
        });

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveFrstSortIdxBtnHelper(Object grpgKey, EdgeT thisEdge, AtomicInteger sortIdxMin, List<EdgeT> chngEdges){
        String logPrfx = "onMoveFrstSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisEdge = dataContext.merge(thisEdge);
        Boolean thisEdgeIsChanged = false;

        Integer sortIdx_ = thisEdge.getSortIdx();
        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin.intValue()){

            // for this node, set sortIdx to minSortIdx
            Integer sortIdx = sortIdxMin.intValue();
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            if (Objects.equals(sortIdx_, sortIdx)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisEdge.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisEdge.setSortIdx(sortIdx)");
                thisEdgeIsChanged = true;
                chngEdges.add(thisEdge);
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
            List<EdgeT> prevEdges = dataManager.load(typeOfEdgeT).condition(logcCond).list();

            prevEdges.forEach(prevEdge -> {
                if (prevEdge != null) {
                    prevEdge = dataContext.merge(prevEdge);

                    Boolean prevEdgeIsChanged = false;

                    Integer prevEdgeSortIdx_ = prevEdge.getSortIdx();
                    if (prevEdgeSortIdx_ != null
                            && prevEdgeSortIdx_ >= sortIdxMin.intValue()) {

                        // for prevEdge, inc sortIdx
                        Integer prevEdgeSortIdx = prevEdgeSortIdx_ + 1;
                        logger.debug(logPrfx + "prevEdgeSortIdx: " + prevEdgeSortIdx);

                        prevEdge.setSortIdx(prevEdgeSortIdx);
                        logger.debug(logPrfx + " --- prevEdge.setSortIdx(prevEdgeSortIdx)");
                        prevEdgeIsChanged = true;
                        chngEdges.add(prevEdge);
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

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(tableMain.getSelected().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdges.sort(getComparatorThisEdge());
        List<EdgeT> chngEdges = new ArrayList<>();

        Map<?, List<EdgeT>> grpgMap = getGrpgMap(thisEdges);

        grpgMap.forEach((grpgKey, grpgEdges) -> {
            onMovePrevSortIdxBtnHelper2(grpgKey, grpgEdges, chngEdges);
        });

        updateHelper(chngEdges);

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper2(Object grpgKey, List<EdgeT> grpgEdges, List<EdgeT> chngEdges) {
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

        grpgEdges.forEach(thisEdge -> {
            onMovePrevSortIdxBtnHelper(grpgKey, thisEdge, sortIdxMin, sortIdxMax, chngEdges);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper(Object grpgKey, EdgeT thisEdge, Integer sortIdxMin, Integer sortIdxMax, List<EdgeT> chngEdges) {
        String logPrfx = "onMovePrevSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge = dataContext.merge(thisEdge);
        Boolean thisEdgeIsChanged = false;

        Integer sortIdx_ = thisEdge.getSortIdx();

        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin){

            // for this node, dec sortIdx
            Integer sortIdx = sortIdx_ - 1;
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            thisEdge.setSortIdx(sortIdx);
            logger.debug(logPrfx + " --- called thisEdge.setSortIdx(sortIdx)");
            thisEdgeIsChanged = true;
            chngEdges.add(thisEdge);

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
            EdgeT prevEdge = dataManager.load(typeOfEdgeT).condition(logcCond).one();

            if(prevEdge != null){
                prevEdge = dataContext.merge(prevEdge);
                Boolean prevEdgeIsChanged = false;

                Integer prevEdgeSortIdx_ = prevEdge.getSortIdx();
                if (prevEdgeSortIdx_ != null
                        && prevEdgeSortIdx_ < sortIdxMax){

                    Integer prevEdgeSortIdx = prevEdgeSortIdx_ + 1;
                    logger.debug(logPrfx + "prevEdgeSortIdx: " + prevEdgeSortIdx);

                    prevEdge.setSortIdx(prevEdgeSortIdx);
                    logger.debug(logPrfx + " --- prevEdge.setSortIdx(prevEdgeSortIdx)");
                    prevEdgeIsChanged = true;
                    chngEdges.add(prevEdge);
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

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(tableMain.getSelected().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdges.sort(getComparatorThisEdge());

        List<EdgeT> chngEdges = new ArrayList<>();

        Map<?, List<EdgeT>> grpgMap = getGrpgMap(thisEdges);

        grpgMap.forEach((grpgKey, grpgEdges) -> {
            onMoveNextSortIdxBtnHelper2(grpgKey, grpgEdges, chngEdges);
        });

        updateHelper(chngEdges);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveNextSortIdxBtnHelper2(Object grpgKey, List<EdgeT> grpgEdges, List<EdgeT> chngEdges) {
        String logPrfx = "onMoveNextSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);

        grpgEdges.forEach(thisEdge -> {
            onMoveNextSortIdxBtnHelper(grpgKey, thisEdge, sortIdxMin, sortIdxMax, chngEdges);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveNextSortIdxBtnHelper(Object grpgKey, EdgeT thisEdge, Integer sortIdxMin, Integer sortIdxMax, List<EdgeT> chngEdges) {
        String logPrfx = "onMoveNextSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdge != null) {
            thisEdge = dataContext.merge(thisEdge);
            Boolean thisEdgeIsChanged = false;

            Integer sortIdx_ = thisEdge.getSortIdx();

            if (sortIdxMax != null
                    && sortIdx_ < sortIdxMax){

                // for this node, inc sortIdx
                Integer sortIdx = sortIdx_ + 1;
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                thisEdge.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisEdge.setSortIdx(sortIdx)");
                thisEdgeIsChanged = true;
                chngEdges.add(thisEdge);


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
                EdgeT nextEdge = dataManager.load(typeOfEdgeT).condition(logcCond).one();

                if(nextEdge != null){
                    nextEdge = dataContext.merge(nextEdge);
                    Boolean nextEdgeIsChanged = false;

                    Integer nextEdgeSortIdx_ = nextEdge.getSortIdx();
                    if (nextEdgeSortIdx_ != null
                            && nextEdgeSortIdx_ > sortIdxMin){

                        Integer nextEdgeSortIdx = nextEdgeSortIdx_ - 1;
                        logger.debug(logPrfx + " --- nextEdgeSortIdx: " + sortIdx);

                        nextEdge.setSortIdx(nextEdgeSortIdx);
                        logger.debug(logPrfx + " --- called nextEdge.setSortIdx(nextEdgeSortIdx)");
                        nextEdgeIsChanged = true;
                        chngEdges.add(nextEdge);
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

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(tableMain.getSelected().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisEdges.sort(getComparatorThisEdge());

        List<EdgeT> chngEdges = new ArrayList<>();

        Map<?, List<EdgeT>> grpgMap = getGrpgMap(thisEdges);

        grpgMap.forEach((grpgKey, grpgEdges) -> {
            onMoveLastSortIdxBtnHelper2(grpgKey, grpgEdges, chngEdges);
        });

        updateHelper(chngEdges);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveLastSortIdxBtnHelper2(Object grpgKey, List<EdgeT> grpgEdges, List<EdgeT> chngEdges) {
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

        grpgEdges.forEach(thisEdge -> {
            onMoveLastSortIdxBtnHelper(grpgKey, thisEdge, sortIdxMaxAtmc, chngEdges);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveLastSortIdxBtnHelper(Object grpgKey, EdgeT thisEdge, AtomicInteger sortIdxMax, List<EdgeT> chngEdges) {
        String logPrfx = "onMoveLastSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisEdge != null) {
            thisEdge = dataContext.merge(thisEdge);

            Boolean thisEdgeIsChanged = false;

            Integer sortIdx_ = thisEdge.getSortIdx();
            if (sortIdx_ != null
                    && sortIdx_ < sortIdxMax.intValue()){

                // for this node, max sortIdx
                Integer sortIdx = sortIdxMax.intValue();
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisEdge.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- called thisEdge.setSortIdx(sortIdx)");
                    thisEdgeIsChanged = true;
                    chngEdges.add(thisEdge);
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
                List<EdgeT> nextEdges = dataManager.load(typeOfEdgeT).condition(logcCond).list();

                nextEdges.forEach(nextEdge -> {
                    if (nextEdge != null) {
                        nextEdge = dataContext.merge(nextEdge);

                        Boolean nextEdgeIsChanged = false;

                        Integer nextSortIdx_ = nextEdge.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ <= sortIdxMax.intValue()) {

                            // for this item, dec sortIdx
                            Integer nextSortIdx = nextEdge.getSortIdx() - 1;
                            logger.debug(logPrfx + "nextSortIdx: " + nextSortIdx);

                            nextEdge.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextEdge.setSortIdx(nextSortIdx)");
                            nextEdgeIsChanged = true;
                            chngEdges.add(nextEdge);
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

    protected Map<?, List<EdgeT>> getGrpgMap(List<EdgeT> thisEdges){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrEdgeBaseGrpg, List<EdgeT>> grpMap = thisEdges.stream()
                .collect(groupingBy(node -> new UsrEdgeBaseGrpg()));

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

        PropertyCondition cond = null;
        //Add condition for this group
        //cond = PropertyCondition.equal("parent1_Id",l_grpgKey.parent1_Id());

        logger.trace(logPrfx + " <-- ");
        return cond;
    }

    protected Comparator<EdgeT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<EdgeT> comparator = Comparator.comparing(
                EdgeT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }


    protected void updateHelper(List<EdgeT> chngEdges) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(chngEdges != null && !chngEdges.isEmpty()) {

            List<EdgeT> chngUniqEdges = chngEdges
                    .stream().distinct().collect(Collectors.toList());

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngUniqEdges.forEach(thisEdge -> {
                //EdgeT thisEdge = dataContext.merge(thisEdge);
                if (thisEdge != null) {
                    thisEdge = dataContext.merge(thisEdge);
                    Boolean thisEdgeIsChanged = false;

                    thisEdgeIsChanged = service.updateId2Dup(this, thisEdge, updOption) || thisEdgeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisEdges);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisEdges.forEach(thisEdge -> {
            if (thisEdge != null) {

                thisEdge = dataContext.merge(thisEdge);;

                boolean isChanged = false;

                isChanged = service.updateCalcVals(this, thisEdge, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisEdges);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisEdges.forEach(thisEdge -> {
            if (thisEdge != null) {

                thisEdge = dataContext.merge(thisEdge);;

                boolean isChanged = false;

                isChanged = service.updateId2(this, thisEdge, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisEdges);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<EdgeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = event.getSource().getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            //todo I observed thisEdge is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (StringUtils.isEmpty(thisEdge.getClassName())) {
            thisEdge.setClassName(typeOfEdgeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfEdgeT.getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            EdgeT thisEdge = instCntnrMain.getItemOrNull();
            if (thisEdge == null) {
                logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisEdge, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisEdge, updOption);
        service.updateId2Deps(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisEdge, updOption);
        service.updateId2Deps(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            EdgeT thisEdge = instCntnrMain.getItemOrNull();
            if (thisEdge == null) {
                logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
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

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisEdge, updOption);
        service.updateName1Deps(this, thisEdge, updOption);

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

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateInst1(this, thisEdge, updOption);
        service.updateInst1Deps(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateDesc1(this, thisEdge, updOption);

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

    public Notifications getNotifications(){
        return notifications;
    }

}