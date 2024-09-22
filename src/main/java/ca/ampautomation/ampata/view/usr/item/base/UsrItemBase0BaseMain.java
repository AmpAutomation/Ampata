package ca.ampautomation.ampata.view.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Repo;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Service;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.*;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.genericfilter.Configuration;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.genericfilter.configuration.DesignTimeConfiguration;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.logicalfilter.LogicalFilterComponent;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.filter.FilterComponent;
import io.jmix.flowui.entity.filter.FilterCondition;
import io.jmix.flowui.kit.action.*;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.grid.JmixTreeGrid;
import io.jmix.flowui.model.*;
import io.jmix.flowui.util.OperationResult;
import io.jmix.flowui.view.*;
import io.jmix.flowuidata.entity.FilterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public abstract class UsrItemBase0BaseMain<ItemT extends UsrItemBase, ItemTypeT extends UsrItemBaseType, ItemServiceT extends UsrItemBase0Service, ItemRepoT extends UsrItemBase0Repo, DataGridT extends Grid<ItemT>> extends StandardListView<ItemT> implements UsrItemBase0BaseComn {

    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<ItemT> typeOfItemT;
    protected Class<ItemTypeT> typeOfItemTypeT;

    @SuppressWarnings("unchecked")
    public UsrItemBase0BaseMain() {
        this.typeOfItemT = (Class<ItemT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfItemTypeT = (Class<ItemTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[1];
    }

    protected DataGrid<ItemT> getDataGrid() {return (DataGrid<ItemT>) getContent().getComponent("dataGridMain"); }

    //Service
    protected ItemServiceT service;

    protected ItemServiceT getService(){ return service; }

    public void setService(ItemServiceT service) { this.service = service; }

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

    @Autowired
    private DialogWindows dialogWindows;

    @ViewComponent
    private VerticalLayout listLayout;

    @ViewComponent
    private VerticalLayout detailsLayout;

    @ViewComponent
    private HorizontalLayout detailActions;


    //Repository
    protected ItemRepoT repo;

    protected ItemRepoT getRepo(){ return repo; }

    public void setRepo(ItemRepoT repo) { this.repo = repo; }


    //Toolbar
    @ViewComponent
    protected JmixComboBox<Integer> updateColItemCalcValsOption;

    @ViewComponent
    protected JmixComboBox<Integer> updateInstItemCalcValsOption;


    //Filter
    @ViewComponent
    protected GenericFilter filter;

    @ViewComponent
    protected PropertyFilter<ItemTypeT> filterConfig1A_Type1_Id;
    
    
    //Template
    @ViewComponent
    protected JmixCheckbox tmplt_Type1_IdFieldChk;
    @ViewComponent
    protected EntityComboBox<ItemTypeT> tmplt_Type1_IdField;

    @ViewComponent
    protected TypedTextField<Integer> tmplt_SortIdxField;
    @ViewComponent
    protected JmixRadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    //Main data containers, loaders and table
    @ViewComponent
    protected CollectionContainer<ItemT> colCntnrMain;
    @ViewComponent
    protected CollectionLoader<ItemT> colLoadrMain;
    @ViewComponent
    private InstanceLoader<ItemT> instLoadrMain;
    @ViewComponent
    protected InstanceContainer<ItemT> instCntnrMain;
    @ViewComponent
    protected DataGridT dataGridMain;


    //Type data container and loader
    protected CollectionContainer<ItemTypeT> colCntnrType;
    protected CollectionLoader<ItemTypeT> colLoadrType;


    //Field
    @ViewComponent
    protected TypedTextField<String> id2Field;

    @ViewComponent
    protected TypedTextField<String> id2CalcField;

    @ViewComponent
    protected EntityComboBox<ItemTypeT> type1_IdField;


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

        updateControls(false);

        Map<Integer, String> map1 = new LinkedHashMap<>();
        map1.put(0, "Skip");
        map1.put(2,"Max+1");
        map1.put(1, "");
        ComponentUtils.setItemsMap(tmplt_SortIdxFieldRdo, map1);

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

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfItemTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfItemTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfItemTypeT)
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
        //todo filterConfig1A_Type1_Id.getValueComponent()

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
/*
    @Subscribe
    public void onInitEntity(final StandardDetailView.InitEntityEvent<ItemT> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = event.getEntity();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");

    }
*/
    private void updateControls(boolean editing) {
        String logPrfx = "updateControls";
        logger.trace(logPrfx + " --> ");

//        form.getChildren().forEach(component -> {
//            if (component instanceof HasValueAndElement<?, ?> field) {
//                field.setReadOnly(!editing);
//            }
//        });

        detailActions.setVisible(editing);
        listLayout.setEnabled(!editing);

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
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<ItemT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisItem is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<ItemT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = event.getItem();
        dataContext.clear();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null");

            instLoadrMain.setEntityId(null);
            instCntnrMain.setItem(null);

            logger.trace(logPrfx + " <-- ");
            return;

        }else{
            instLoadrMain.setEntityId(thisItem.getId());
            instLoadrMain.load();
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("filter")
    public void onFilterConfigurationChange(final GenericFilter.ConfigurationChangeEvent event) {
        String logPrfx = "onFilterConfigurationChange";
        logger.trace(logPrfx + " --> ");

        Configuration currentConfiguration = event.getNewConfiguration();

        if (currentConfiguration.getId().equals("filterConfig1")) {
            Optional<FilterComponent> o_filterConfig1A_Type1_Id = currentConfiguration.getRootLogicalFilterComponent().getFilterComponents()
                    .stream().filter(c -> c instanceof PropertyFilter)
                    .filter(c -> ((PropertyFilter<?>) c).getId().orElse("").equals("filterConfig1A_Type1_Id"))
                    .findFirst();

            PropertyFilter<ItemTypeT> filterConfig1A_Type1_Id = (PropertyFilter) o_filterConfig1A_Type1_Id.orElse(null);
            EntityComboBox<ItemTypeT> propFilterCmpnt_Type1_Id;
            propFilterCmpnt_Type1_Id = (EntityComboBox<ItemTypeT>) filterConfig1A_Type1_Id.getValueComponent();
            propFilterCmpnt_Type1_Id.setItems(colCntnrType);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("dataGridMain.create")
    public void onDataGridMainCreate(final ActionPerformedEvent event) {
        String logPrfx = "onDataGridMainCreate";
        logger.trace(logPrfx + " --> ");

        dataContext.clear();
        ItemT thisItem = dataContext.create(typeOfItemT);
        instCntnrMain.setItem(thisItem);
        updateControls(true);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("dataGridMain.edit")
    public void ononDataGridMainEdit(final ActionPerformedEvent event) {
        String logPrfx = "ononDataGridMainEdit";
        logger.trace(logPrfx + " --> ");

        updateControls(true);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("saveBtn")
    public void onSaveButtonClick(final ClickEvent<JmixButton> event) {
        String logPrfx = "onSaveButtonClick";
        logger.trace(logPrfx + " --> ");

        dataContext.save();
        colCntnrMain.replaceItem(instCntnrMain.getItem());
        updateControls(false);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("cancelBtn")
    public void onCancelButtonClick(final ClickEvent<JmixButton> event) {
        String logPrfx = "onCancelButtonClick";
        logger.trace(logPrfx + " --> ");

        dataContext.clear();
        instLoadrMain.load();
        updateControls(false);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- called colLoadrType.load() ");
        colLoadrType.load();
        logger.debug(logPrfx + " --- finished colLoadrType.load() ");

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

        List<ItemT> thisItems = dataGridMain.getSelectedItems().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItems.forEach(orig -> {
            ItemT copy = onDuplicateBtnClickHelper(orig);

            ItemT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }

    public ItemT onDuplicateBtnClickHelper(ItemT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        ItemT copy = metadataTools.copy(orig);
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

        List<ItemT> thisItems = dataGridMain.getSelectedItems().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<ItemT> chngItems = new ArrayList<>();

        thisItems.forEach(thisItem -> {
            thisItem = dataContext.merge(thisItem);
            if (thisItem != null) {

                Boolean thisItemIsChanged = false;

                thisItemIsChanged = onSetBtnClickHelper(thisItem);
                if(thisItemIsChanged){
                    chngItems.add(thisItem);
                }

                if (thisItemIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisItem).");
                    //dataManager.save(thisItem);
                }
            }
        });
        updateHelper(chngItems);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(ItemT thisItem){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisItemIsChanged = false;

        if (tmplt_Type1_IdFieldChk.getValue()
        ) {
            thisItemIsChanged = true;
            thisItem.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItemIsChanged = service.updateId2Calc(this, thisItem, updOption) || thisItemIsChanged;
        thisItemIsChanged = service.updateId2(this, thisItem, updOption) || thisItemIsChanged;
        thisItemIsChanged = service.updateId2Cmp(this, thisItem, updOption) || thisItemIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisItemIsChanged;
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = dataGridMain.getSelectedItems().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<ItemT> chngItems = new ArrayList<>();

        onRebuildSortIdxBtnHelper(thisItems, chngItems);

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper(List<ItemT> thisItems, List<ItemT> chngItems) {
        String logPrfx = "onRebuildSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        // get all adj Items that could be modified
        LogicalCondition logcCond = LogicalCondition.and();

        List<ItemT> adjItems = dataManager.load(typeOfItemT).condition(logcCond).list();

        AtomicInteger sortIdx = new AtomicInteger(0);
        // sort the nodes
        adjItems.sort(getComparator());

        //Update the adjacent nodes
        adjItems.forEach(adjItem -> {
            if (adjItem != null) {
                adjItem = dataContext.merge(adjItem);

                Boolean adjItemIsChanged = false;

                Integer sortIdx_ = adjItem.getSortIdx();
                logger.debug(logPrfx + " --- sortIdx:" + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    adjItem.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- called adjItem.setSortIdx(l_id2)");
                    adjItemIsChanged = true;
                    chngItems.add(adjItem);
                }
                sortIdx.incrementAndGet() ;
            }
        });


        logger.trace(logPrfx + " <-- ");
    }

    void onRebuildSortIdxBtnHelper2(Object grpgKey, ItemT grpItem, List<ItemT> chngItems) {
        String logPrfx = "onRebuildSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = new ArrayList<ItemT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());

        List<ItemT> chngItems = new ArrayList<>();
        AtomicInteger sortIdxMin = new AtomicInteger(0);

        thisItems.forEach(thisItem -> {
            onMoveFrstSortIdxBtnHelper(thisItem, sortIdxMin, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveFrstSortIdxBtnHelper(ItemT thisItem, AtomicInteger sortIdxMin, List<ItemT> chngItems){
        String logPrfx = "onMoveFrstSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisItem = dataContext.merge(thisItem);
        Boolean thisItemIsChanged = false;

        Integer sortIdx_ = thisItem.getSortIdx();
        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin.intValue()){

            // for this node, set sortIdx to minSortIdx
            Integer sortIdx = sortIdxMin.intValue();
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            if (Objects.equals(sortIdx_, sortIdx)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisItem.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
                thisItemIsChanged = true;
                chngItems.add(thisItem);
            }

            // for all prev nodes, inc sortIdx

            // build condition
            LogicalCondition logcCond = LogicalCondition.and();
            logcCond.add(PropertyCondition.greaterOrEqual("sortIdx",sortIdxMin.intValue()));
            logcCond.add(PropertyCondition.less("sortIdx",sortIdx_));


            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            List<ItemT> prevItems = dataManager.load(typeOfItemT).condition(logcCond).list();

            prevItems.forEach(prevItem -> {
                if (prevItem != null) {
                    prevItem = dataContext.merge(prevItem);

                    Boolean prevItemIsChanged = false;

                    Integer prevItemSortIdx_ = prevItem.getSortIdx();
                    if (prevItemSortIdx_ != null
                            && prevItemSortIdx_ >= sortIdxMin.intValue()) {

                        // for prevItem, inc sortIdx
                        Integer prevItemSortIdx = prevItemSortIdx_ + 1;
                        logger.debug(logPrfx + "prevItemSortIdx: " + prevItemSortIdx);

                        prevItem.setSortIdx(prevItemSortIdx);
                        logger.debug(logPrfx + " --- prevItem.setSortIdx(prevItemSortIdx)");
                        prevItemIsChanged = true;
                        chngItems.add(prevItem);
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

        List<ItemT> thisItems = new ArrayList<ItemT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());
        List<ItemT> chngItems = new ArrayList<>();

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisItems.forEach(thisItem -> {
            onMovePrevSortIdxBtnHelper(thisItem, sortIdxMin, sortIdxMax, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper(ItemT thisItem, Integer sortIdxMin, Integer sortIdxMax, List<ItemT> chngItems) {
        String logPrfx = "onMovePrevSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem = dataContext.merge(thisItem);
        Boolean thisItemIsChanged = false;

        Integer sortIdx_ = thisItem.getSortIdx();

        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin){

            // for this node, dec sortIdx
            Integer sortIdx = sortIdx_ - 1;
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            thisItem.setSortIdx(sortIdx);
            logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
            thisItemIsChanged = true;
            chngItems.add(thisItem);

            // for prev node, inc sortIdx

            // build condition
            LogicalCondition logcCond =  LogicalCondition.and();
            logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ - 1));

            // database read
            logger.debug(logPrfx + " --- executing dataManager.load");
            ItemT prevItem = dataManager.load(typeOfItemT).condition(logcCond).one();

            if(prevItem != null){
                prevItem = dataContext.merge(prevItem);
                Boolean prevItemIsChanged = false;

                Integer prevItemSortIdx_ = prevItem.getSortIdx();
                if (prevItemSortIdx_ != null
                        && prevItemSortIdx_ < sortIdxMax){

                    Integer prevItemSortIdx = prevItemSortIdx_ + 1;
                    logger.debug(logPrfx + "prevItemSortIdx: " + prevItemSortIdx);

                    prevItem.setSortIdx(prevItemSortIdx);
                    logger.debug(logPrfx + " --- prevItem.setSortIdx(prevItemSortIdx)");
                    prevItemIsChanged = true;
                    chngItems.add(prevItem);
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

        List<ItemT> thisItems = new ArrayList<ItemT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());

        List<ItemT> chngItems = new ArrayList<>();

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this);

        thisItems.forEach(thisItem -> {
            onMoveNextSortIdxBtnHelper(thisItem, sortIdxMin, sortIdxMax, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveNextSortIdxBtnHelper(ItemT thisItem, Integer sortIdxMin, Integer sortIdxMax, List<ItemT> chngItems) {
        String logPrfx = "onMoveNextSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem != null) {
            thisItem = dataContext.merge(thisItem);
            Boolean thisItemIsChanged = false;

            Integer sortIdx_ = thisItem.getSortIdx();

            if (sortIdxMax != null
                    && sortIdx_ < sortIdxMax){

                // for this node, inc sortIdx
                Integer sortIdx = sortIdx_ + 1;
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                thisItem.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
                thisItemIsChanged = true;
                chngItems.add(thisItem);


                // for next node, dec sortIdx

                // build condition
                LogicalCondition logcCond =  LogicalCondition.and();
                logcCond.add(PropertyCondition.equal("sortIdx",sortIdx_ + 1));

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                ItemT nextItem = dataManager.load(typeOfItemT).condition(logcCond).one();

                if(nextItem != null){
                    nextItem = dataContext.merge(nextItem);
                    Boolean nextItemIsChanged = false;

                    Integer nextItemSortIdx_ = nextItem.getSortIdx();
                    if (nextItemSortIdx_ != null
                            && nextItemSortIdx_ > sortIdxMin){

                        Integer nextItemSortIdx = nextItemSortIdx_ - 1;
                        logger.debug(logPrfx + " --- nextItemSortIdx: " + sortIdx);

                        nextItem.setSortIdx(nextItemSortIdx);
                        logger.debug(logPrfx + " --- called nextItem.setSortIdx(nextItemSortIdx)");
                        nextItemIsChanged = true;
                        chngItems.add(nextItem);
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

        List<ItemT> thisItems = new ArrayList<ItemT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItems is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisItems.sort(getComparatorThisItem());

        List<ItemT> chngItems = new ArrayList<>();

        Integer sortIdxMax = service.getSortIdxMax(this);
        if(sortIdxMax == null){
            logger.debug(logPrfx + " --- thisWindow.getSortIdxMax(grpgKey) returned null");
            notifications.create("thisWindow.getSortIdxMax(grpgKey) returned null. Rebuild the sortIdx").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        AtomicInteger sortIdxMaxAtmc = new AtomicInteger(sortIdxMax);

        thisItems.forEach(thisItem -> {
            onMoveLastSortIdxBtnHelper(thisItem, sortIdxMaxAtmc, chngItems);
        });

        updateHelper(chngItems);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveLastSortIdxBtnHelper(ItemT thisItem, AtomicInteger sortIdxMax, List<ItemT> chngItems) {
        String logPrfx = "onMoveLastSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisItem != null) {
            thisItem = dataContext.merge(thisItem);

            Boolean thisItemIsChanged = false;

            Integer sortIdx_ = thisItem.getSortIdx();
            if (sortIdx_ != null
                    && sortIdx_ < sortIdxMax.intValue()){

                // for this node, max sortIdx
                Integer sortIdx = sortIdxMax.intValue();
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisItem.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- called thisItem.setSortIdx(sortIdx)");
                    thisItemIsChanged = true;
                    chngItems.add(thisItem);
                }


                // for all next nodes, dec sortIdx

                // build condition
                LogicalCondition logcCond = LogicalCondition.and();
                logcCond.add(PropertyCondition.greater("sortIdx",sortIdx_));
                logcCond.add(PropertyCondition.lessOrEqual("sortIdx", sortIdxMax.intValue()));

                // database read
                logger.debug(logPrfx + " --- executing dataManager.load");
                List<ItemT> nextItems = dataManager.load(typeOfItemT).condition(logcCond).list();

                nextItems.forEach(nextItem -> {
                    if (nextItem != null) {
                        nextItem = dataContext.merge(nextItem);

                        Boolean nextItemIsChanged = false;

                        Integer nextSortIdx_ = nextItem.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ <= sortIdxMax.intValue()) {

                            // for this item, dec sortIdx
                            Integer nextSortIdx = nextItem.getSortIdx() - 1;
                            logger.debug(logPrfx + "nextSortIdx: " + nextSortIdx);

                            nextItem.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextItem.setSortIdx(nextSortIdx)");
                            nextItemIsChanged = true;
                            chngItems.add(nextItem);
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


    protected Comparator<ItemT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<ItemT> comparator = Comparator.comparing(
                ItemT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }


    protected void updateHelper(List<ItemT> chngItems) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(chngItems != null && !chngItems.isEmpty()) {

            List<ItemT> chngUniqItems = chngItems
                    .stream().distinct().collect(Collectors.toList());

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<ItemT> thisItems = dataGridMain.getSelectedItems().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngUniqItems.forEach(thisItem -> {
                //ItemT thisItem = dataContext.merge(thisItem);
                if (thisItem != null) {
                    thisItem = dataContext.merge(thisItem);
                    Boolean thisItemIsChanged = false;

                    thisItemIsChanged = service.updateId2Dup(this, thisItem, updOption) || thisItemIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.save().");
                dataContext.save();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                //todo check how to dataGridMain.setSelected
                //dataGridMain.setSelected(thisItems);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ItemT> thisItems = dataGridMain.getSelectedItems().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItems.forEach(thisItem -> {
            if (thisItem != null) {

                thisItem = dataContext.merge(thisItem);;

                boolean isChanged = false;

                isChanged = service.updateCalcVals(this, thisItem, updOption);

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
            try{dataGridMain.setSelected(thisItems);
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

        List<ItemT> thisItems = dataGridMain.getSelectedItems().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisItems.forEach(thisItem -> {
            if (thisItem != null) {

                thisItem = dataContext.merge(thisItem);;

                boolean isChanged = false;

                isChanged = service.updateId2(this, thisItem, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.save().");
            dataContext.save();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //todo check how to dataGridMain.setSelected
            //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
            //dataGridMain.setSelected(thisItems);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<ItemT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = event.getSource().getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            //todo I observed thisItem is null when selecting a new item
            //notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            ItemT thisItem = instCntnrMain.getItemOrNull();
            if (thisItem == null) {
                logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisItem, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisItem, updOption);
        service.updateId2Deps(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisItem, updOption);
        service.updateId2Deps(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrItemBase thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisFinStmtItm, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSortKeyFieldBtn")
    public void onUpdateSortKeyFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ItemT thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateName1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemBase thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisItem, updOption);
        service.updateName1Deps(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemBase thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateDesc1(this, thisItem, updOption);

        logger.trace(logPrfx + " <-- ");
    }

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