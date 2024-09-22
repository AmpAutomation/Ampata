package ca.ampautomation.ampata.view.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBase;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.edge.base.UsrEdgeBase0Repo;
import ca.ampautomation.ampata.service.usr.edge.base.UsrEdgeBase0Service;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.JmixMultiValuePicker;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.model.*;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public abstract class UsrEdgeBase0BaseMain<EdgeT extends UsrEdgeBase, EdgeTypeT extends UsrEdgeBaseType, EdgeServiceT extends UsrEdgeBase0Service, EdgeRepoT extends UsrEdgeBase0Repo, DataGridT extends Grid<EdgeT>> extends StandardDetailView<EdgeT> implements UsrEdgeBase0BaseComn {


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

    protected DataGrid<EdgeT> getDataGrid() {return (DataGrid<EdgeT>) getContent().getComponent("dataGridMain"); }

    //Service
    protected EdgeServiceT service;

    protected EdgeServiceT getService() { return service; }

    public void setService(EdgeServiceT service) { this.service = service; }

    @Autowired
    protected DataComponents dataComponents;

    @Autowired
    protected FetchPlans fetchPlans;

    @ViewComponent
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
    @ViewComponent
    protected JmixComboBox<Integer> updateColItemCalcValsOption;

    @ViewComponent
    protected JmixComboBox<Integer> updateInstItemCalcValsOption;


    //Filter
    @ViewComponent
    protected GenericFilter filter;

    @ViewComponent
    protected PropertyFilter<EdgeTypeT> filterConfig1A_Type1_Id;


    //Template
    @ViewComponent
    protected JmixCheckbox tmplt_Type1_IdFieldChk;
    @ViewComponent
    protected EntityComboBox<EdgeTypeT> tmplt_Type1_IdField;

    @ViewComponent
    protected TypedTextField<Integer> tmplt_SortIdxField;
    @ViewComponent
    protected JmixRadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    //Main data containers, loaders and table
    @ViewComponent
    protected CollectionLoader<EdgeT> colLoadrMain;
    @ViewComponent
    protected CollectionContainer<EdgeT> colCntnrMain;
    @ViewComponent
    protected InstanceContainer<EdgeT> instCntnrMain;
    @ViewComponent
    protected DataGridT dataGridMain;


    //Type data container and loader
    protected CollectionContainer<EdgeTypeT> colCntnrType;
    protected CollectionLoader<EdgeTypeT> colLoadrType;


    //GenTag data container loader and data property container
    protected CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    protected CollectionLoader<UsrItemGenTag> colLoadrGenTag;
    @ViewComponent
    protected CollectionPropertyContainer<UsrItemGenTag> colPropCntnrGenTag;



    //Field
    @ViewComponent
    protected TypedTextField<String> id2Field;

    @ViewComponent
    protected TypedTextField<String> id2CalcField;

    @ViewComponent
    protected EntityComboBox<EdgeTypeT> type1_IdField;

    @Autowired
    protected JmixMultiValuePicker<UsrItemGenTag> genTags1_IdField;


    /**
     * The first event in the view opening process.
     * <p>
     * The view and all its declaratively defined components are created, and dependency injection is completed.
     * Some visual components are not fully initialized, for example buttons are not yet linked with actions.
     * <p>
     * In this event listener, you can create visual and data components, for example:
     * <pre>
     *     &#64;Subscribe
     *     protected void onInit(InitEvent event) {
     *         Label label = uiComponents.create(Label.class);
     *         label.setText("Hello World");
     *         getContent().add(label);
     *     }
     * </pre>
     *
     * @see #addInitListener(ComponentEventListener)
     */
    @Subscribe
    public void onInit(final View.InitEvent event) {
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

        Map<Integer, String> map1 = new LinkedHashMap<>();
        map1.put(0, "Skip");
        map1.put(2, "Max+1");
        map1.put(1, "");
        ComponentUtils.setItemsMap(tmplt_SortIdxFieldRdo,map1);

        Map<Integer,String> map2 = new LinkedHashMap<>();
        map2.put(UpdateOption.SKIP.toInt(),UpdateOption.SKIP.toString());
        map2.put(UpdateOption.LOCAL.toInt(),UpdateOption.LOCAL.toString());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST.toInt(), UpdateOption.LOCAL__REF_TO_EXIST.toString());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST_NEW.toInt(), UpdateOption.LOCAL__REF_TO_EXIST_NEW.toString());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toInt(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toString());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toInt(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toString());
        ComponentUtils.setItemsMap(updateColItemCalcValsOption,map2);
        updateColItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());
        ComponentUtils.setItemsMap(updateInstItemCalcValsOption, map2);
        updateInstItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfEdgeTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfEdgeTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfEdgeTypeT)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getViewData().getDataContext());
        //Field
        type1_IdField.setItems(colCntnrType);
        //Template
        tmplt_Type1_IdField.setItems(colCntnrType);
        //Filter
        EntityComboBox<EdgeTypeT> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<EdgeTypeT>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setItems(colCntnrType);

        colCntnrGenTag = dataComponents.createCollectionContainer(UsrItemGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrItemGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag = fetchPlans.builder(UsrItemGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getViewData().getDataContext());

        logger.trace(logPrfx + " <-- ");
    }


    /**
     * The second (after {@link InitEvent}) event in the view opening process.
     * All components have completed their internal initialization procedures.
     * Data loaders have been triggered by the automatically configured {@code DataLoadCoordinator} facet.
     * <p>
     * In this event listener, you can load data, check permissions and modify UI components. For example:
     * <pre>
     *     &#64;Subscribe
     *     protected void onBeforeShow(BeforeShowEvent event) {
     *         customersDl.load();
     *     }
     * </pre>
     * <p>
     * You can abort the process of opening the view by throwing an exception.
     */
    @Subscribe
    public void onBeforeShowEvent(final View.BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        //logger.debug(logPrfx + " --- calling colLoadrMain.load() ");
        //colLoadrMain.load();
        //logger.debug(logPrfx + " --- called colLoadrMain.load() ");
        //dataGridMain.sort("sortKey", Table.SortDirection.ASCENDING);

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
     * The last (after {@link View.BeforeShowEvent}) event in the view opening process.
     * <p>
     * In this event listener, you can make final configuration of the view according to loaded data and
     * show notifications or dialogs:
     * <pre>
     *     &#64;Subscribe
     *     protected void onReady(ReadyEvent event) {
     *         notifications.show("Just opened");
     *     }
     * </pre>
     */
    @Subscribe
    public void onReadyEvent(final View.ReadyEvent event) {
        String logPrfx = "onReadyEvent";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");

    }


    /**
     * The first event in the view closing process.
     * The view is still displayed and fully functional.
     * <p>
     * In this event listener, you can check any conditions and prevent closing using the
     * preventClose() method of the event, for example:
     * <pre>
     *     &#64;Subscribe
     *     protected void onBeforeClose(BeforeCloseEvent event) {
     *         if (Strings.isNullOrEmpty(textField.getTypedValue())) {
     *             notifications.show("Input required");
     *             event.preventClose();
     *         }
     *     }
     * </pre>
     */

    @Subscribe
    public void onBeforeClose(final View.BeforeCloseEvent event) {
        String logPrfx = "onBeforeClose";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");

    }


    /**
     * Event sent before the new entity instance is set to edited entity container.
     * <p>
     * Use this event listener to initialize default values in the new entity instance, for example:
     * <pre>
     *     &#64;Subscribe
     *     protected void onInitEntity(InitEntityEvent&lt;Foo&gt; event) {
     *         event.getEntity().setStatus(Status.ACTIVE);
     *     }
     * </pre>
     *
     * param NodeT type of entity
     */

    @Subscribe
    public void onInitEntity(final StandardDetailView.InitEntityEvent<EdgeT> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = event.getEntity();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

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
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
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
    public void onUpdateColCalcValsBtnClick(ClickEvent<Button> event) {
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
    public void onDeleteColDeletedColBtnClick(ClickEvent<Button> event) {
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
    public void onDuplicateBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = dataGridMain.getSelectedItems().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
    public void onSetBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = dataGridMain.getSelectedItems().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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

        if (tmplt_Type1_IdFieldChk.getValue()
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
    public void onRebuildSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = dataGridMain.getSelectedItems().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
    public void onMoveFrstSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
                logger.debug(logPrfx + " --- executing dataContext.save().");
                dataContext.save();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevSortIdxBtn")
    public void onMovePrevSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onMovePrevSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
            notifications.create("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
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
                logger.debug(logPrfx + " --- executing dataContext.save().");
                dataContext.save();
            }

        }

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("moveNextSortIdxBtn")
    public void onMoveNextSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onMoveNextSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
                    logger.debug(logPrfx + " --- executing dataContext.save().");
                    dataContext.save();
                }

            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveLastSortIdxBtn")
    public void onMoveLastSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onMoveLastSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = new ArrayList<EdgeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdges is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
            notifications.create("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
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
                    logger.debug(logPrfx + " --- executing dataContext.save().");
                    dataContext.save();
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

            List<EdgeT> thisEdges = dataGridMain.getSelectedItems().stream().toList();

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
                logger.debug(logPrfx + " --- executing dataContext.save().");
                dataContext.save();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                //todo check how to dataGridMain.setSelected
                //dataGridMain.setSelected(thisEdges);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = dataGridMain.getSelectedItems().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
            logger.debug(logPrfx + " --- executing dataContext.save().");
            dataContext.save();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //todo check how to dataGridMain.setSelected
            //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
            /*
            try{dataGridMain.setSelected(thisEdges);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create("Unable to keep all previous selections.").show();
            }
            */
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = dataGridMain.getSelectedItems().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
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
            logger.debug(logPrfx + " --- executing dataContext.save().");
            dataContext.save();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //todo check how to dataGridMain.setSelected
            //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
            //dataGridMain.setSelected(thisEdges);
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
            //notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateInstItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
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

        if (event.isFromClient()) {
            EdgeT thisEdge = instCntnrMain.getItemOrNull();
            if (thisEdge == null) {
                logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateId2FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateId2CalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateId2CmpFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
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

        if (event.isFromClient()) {
            EdgeT thisEdge = instCntnrMain.getItemOrNull();
            if (thisEdge == null) {
                logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateSortKeyFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisEdge, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateType1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInst1FieldBtn")
    public void onUpdateInst1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInst1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateDesc1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
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

    protected void addEnteredTextToComboBoxOptionsList(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "addEnteredTextToComboBoxOptionsList";
        logger.trace(logPrfx + " --> ");

        // event.getSource is directly connected to a JmixComboBox
        JmixComboBox<String> cb = (JmixComboBox<String>) event.getSource();

        String text = event.getSource().getValue();

        if (!Objects.equals(text, "<null>")){
            @SuppressWarnings("unchecked")

            /*
            If you use combobox.setItems(items); then ComboBox 
            will automatically create a ListDataProvider out of those items
            Use the ListDataProvider to get the list
            */
            ListDataProvider<String> ldp = (ListDataProvider<String>) cb.getDataProvider();

            List<String> list = new ArrayList<>(ldp.getItems());

            list.add(text);
            logger.trace(logPrfx + " --- called list.add( " + text + ")");

            cb.setItems(list);

            notifications.create("Added " + text + " to list.").show();
        }

    }

    public Notifications getNotifications(){
        return notifications;
    }

}