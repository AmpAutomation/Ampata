package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseGrpg;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.filter.FilterComponent;
import io.jmix.flowui.component.genericfilter.Configuration;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.JmixMultiValuePicker;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
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

public abstract class UsrNodeBase0BaseMain<NodeT extends UsrNodeBase, NodeTypeT extends UsrNodeBaseType, NodeServiceT extends UsrNodeBase0Service, NodeRepoT extends UsrNodeBase0Repo, DataGridT extends Grid<NodeT>> extends StandardListView<NodeT> implements UsrNodeBase0BaseComn {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<NodeT> typeOfNodeT;
    protected Class<NodeTypeT> typeOfNodeTypeT;
    protected Class<NodeServiceT> typeOfNodeServiceT;
    protected Class<NodeRepoT> typeOfNodeRepoT;

    @SuppressWarnings("unchecked")
    public UsrNodeBase0BaseMain() {
        this.typeOfNodeT = (Class<NodeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[1];
        this.typeOfNodeServiceT = (Class<NodeServiceT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[2];
        this.typeOfNodeRepoT = (Class<NodeRepoT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[3];
    }

    protected DataGrid<NodeT> getDataGrid() {return (DataGrid<NodeT>) getContent().getComponent("dataGridMain"); }

    //Service
    protected NodeServiceT service;

    protected NodeServiceT getService(){ return service; }

    public void setService(NodeServiceT service) { this.service = service; }

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
    protected NodeRepoT repo;

    protected NodeRepoT getRepo(){ return repo; }

    public void setRepo(NodeRepoT repo) { this.repo = repo; }


    //Toolbar
    @ViewComponent
    protected JmixComboBox<Integer> updateColItemCalcValsOption;

    @ViewComponent
    protected JmixComboBox<Integer> updateInstItemCalcValsOption;


    //Filter
    @ViewComponent
    protected GenericFilter filter;

    @ViewComponent
    protected PropertyFilter<NodeTypeT> filterConfig1A_Type1_Id;


    //Template
    @ViewComponent
    protected JmixCheckbox tmplt_Type1_IdFieldChk;
    @ViewComponent
    protected EntityComboBox<NodeTypeT> tmplt_Type1_IdField;

    @ViewComponent
    protected TypedTextField<Integer> tmplt_SortIdxField;
    @ViewComponent
    protected JmixRadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    //Main data containers, loaders and table
    @ViewComponent
    protected CollectionLoader<NodeT> colLoadrMain;
    @ViewComponent
    protected CollectionContainer<NodeT> colCntnrMain;
    @ViewComponent
    private InstanceLoader<NodeT> instLoadrMain;
    @ViewComponent
    protected InstanceContainer<NodeT> instCntnrMain;
    @ViewComponent
    protected DataGridT dataGridMain;


    //Type data container and loader
    protected CollectionContainer<NodeTypeT> colCntnrType;
    protected CollectionLoader<NodeTypeT> colLoadrType;


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
    protected EntityComboBox<NodeTypeT> type1_IdField;

    @ViewComponent
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

        updateControls(false);

/*
        try{
            repo = typeOfNodeRepoT.getDeclaredConstructor().newInstance();

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
        ComponentUtils.setItemsMap(updateColItemCalcValsOption, map2);
        updateColItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());
        ComponentUtils.setItemsMap(updateInstItemCalcValsOption, map2);
        updateInstItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfNodeTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfNodeTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfNodeTypeT)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getViewData().getDataContext());
        //Field
        type1_IdField.setItems(colCntnrType);
        //Template
        tmplt_Type1_IdField.setItems(colCntnrType);

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
/*
    @Subscribe
    public void onInitEntity(final StandardDetailView.InitEntityEvent<NodeT> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = event.getEntity();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
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
        dataContext.clear();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");

            instLoadrMain.setEntityId(null);
            instCntnrMain.setItem(null);

            logger.trace(logPrfx + " <-- ");
            return;

        }else{
            instLoadrMain.setEntityId(thisNode.getId());
            instLoadrMain.load();
        }
/*
        if (thisNode.getClassName() == null || thisNode.getClassName().isBlank()){
            thisNode.setClassName(this.getClass().getSimpleName());
            logger.debug(logPrfx + " --- className: " + this.getClass().getSimpleName());
        }
*/

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

            PropertyFilter<NodeTypeT> filterConfig1A_Type1_Id = (PropertyFilter) o_filterConfig1A_Type1_Id.orElse(null);
            EntityComboBox<NodeTypeT> propFilterCmpnt_Type1_Id;
            propFilterCmpnt_Type1_Id = (EntityComboBox<NodeTypeT>) filterConfig1A_Type1_Id.getValueComponent();
            propFilterCmpnt_Type1_Id.setItems(colCntnrType);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("dataGridMain.create")
    public void onDataGridMainCreate(final ActionPerformedEvent event) {
        String logPrfx = "onDataGridMainCreate";
        logger.trace(logPrfx + " --> ");

        dataContext.clear();
        NodeT thisNode = dataContext.create(typeOfNodeT);
        instCntnrMain.setItem(thisNode);
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

        List<NodeT> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(orig -> {
            NodeT copy = onDuplicateBtnClickHelper(orig);

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

    public NodeT onDuplicateBtnClickHelper(NodeT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        NodeT copy = metadataTools.copy(orig);
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

        List<NodeT> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<NodeT> chngNodes = new ArrayList<>();

        thisNodes.forEach(thisNode -> {
            thisNode = dataContext.merge(thisNode);
            if (thisNode != null) {

                Boolean thisNodeIsChanged = false;

                thisNodeIsChanged = onSetBtnClickHelper(thisNode);
                if(thisNodeIsChanged){
                    chngNodes.add(thisNode);
                }

                if (thisNodeIsChanged) {
                    //logger.debug(logPrfx + " --- executing dataManager.save(thisNode).");
                    //dataManager.save(thisNode);
                    //logger.debug(logPrfx + " --- finished dataManager.save(thisNode).");
                }
            }
        });
        updateHelper(chngNodes);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(NodeT thisNode){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisNodeIsChanged = false;

        if (tmplt_Type1_IdFieldChk.getValue()
        ) {
            thisNodeIsChanged = true;
            thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodeIsChanged = service.updateId2Calc(this, thisNode, updOption) || thisNodeIsChanged;
        thisNodeIsChanged = service.updateId2(this, thisNode, updOption) || thisNodeIsChanged;
        thisNodeIsChanged = service.updateId2Cmp(this, thisNode, updOption) || thisNodeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<NodeT> chngNodes = new ArrayList<>();

        Map<?, List<NodeT>> grpgMap = getGrpgMap(thisNodes);

        grpgMap.forEach((key, nodes) -> {
            onRebuildSortIdxBtnHelper(key, nodes, chngNodes);

        });

        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }

    void onRebuildSortIdxBtnHelper(Object grpgKey, List<NodeT> grpgNodes, List<NodeT> chngNodes) {
        String logPrfx = "onRebuildSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        // get all adj Nodes that could be modified
        LogicalCondition logcCond = LogicalCondition.and();

        //Add condition for this group
        Condition grpgCond = getConditionGrpg(grpgKey);
        if (grpgCond != null) {
            logcCond.add(grpgCond);
        }

        List<NodeT> adjNodes = dataManager.load(typeOfNodeT).condition(logcCond).list();

        AtomicInteger sortIdx = new AtomicInteger(0);
        // sort the nodes
        adjNodes.sort(getComparator());

        //Update the adjacent nodes
        adjNodes.forEach(adjNode -> {
            if (adjNode != null) {
                adjNode = dataContext.merge(adjNode);

                Boolean adjNodeIsChanged = false;

                Integer sortIdx_ = adjNode.getSortIdx();
                logger.debug(logPrfx + " --- sortIdx:" + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    adjNode.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- called adjNode.setSortIdx(id2)");
                    adjNodeIsChanged = true;
                    chngNodes.add(adjNode);
                }
                sortIdx.incrementAndGet() ;
            }
        });


        logger.trace(logPrfx + " <-- ");
    }


    void onRebuildSortIdxBtnHelper2(Object grpgKey, NodeT grpNode, List<NodeT> chngNodes) {
        String logPrfx = "onRebuildSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = new ArrayList<NodeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodes.sort(getComparatorThisNode());

        List<NodeT> chngNodes = new ArrayList<>();

        Map<?, List<NodeT>> grpgMap = getGrpgMap(thisNodes);

        grpgMap.forEach((grpgKey, grpgNodes) -> {
            onMoveFrstSortIdxBtnHelper2(grpgKey, grpgNodes, chngNodes);
        });

        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveFrstSortIdxBtnHelper2(Object grpgKey, List<NodeT> grpgNodes, List<NodeT> chngNodes) {
        String logPrfx = "onMoveFrstSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        grpgNodes.forEach(thisNode -> {
            onMoveFrstSortIdxBtnHelper(grpgKey, thisNode, sortIdxMin, chngNodes);
        });

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveFrstSortIdxBtnHelper(Object grpgKey, NodeT thisNode, AtomicInteger sortIdxMin, List<NodeT> chngNodes){
        String logPrfx = "onMoveFrstSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNode = dataContext.merge(thisNode);
        Boolean thisNodeIsChanged = false;

        Integer sortIdx_ = thisNode.getSortIdx();
        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin.intValue()){

            // for this node, set sortIdx to minSortIdx
            Integer sortIdx = sortIdxMin.intValue();
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            if (Objects.equals(sortIdx_, sortIdx)) {
                logger.debug(logPrfx + " --- no change detected");
            }else{
                thisNode.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisNode.setSortIdx(sortIdx)");
                thisNodeIsChanged = true;
                chngNodes.add(thisNode);
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
            List<NodeT> prevNodes = dataManager.load(typeOfNodeT).condition(logcCond).list();

            prevNodes.forEach(prevNode -> {
                if (prevNode != null) {
                    prevNode = dataContext.merge(prevNode);

                    Boolean prevNodeIsChanged = false;

                    Integer prevNodeSortIdx_ = prevNode.getSortIdx();
                    if (prevNodeSortIdx_ != null
                            && prevNodeSortIdx_ >= sortIdxMin.intValue()) {

                        // for prevNode, inc sortIdx
                        Integer prevNodeSortIdx = prevNodeSortIdx_ + 1;
                        logger.debug(logPrfx + "prevNodeSortIdx: " + prevNodeSortIdx);

                        prevNode.setSortIdx(prevNodeSortIdx);
                        logger.debug(logPrfx + " --- prevNode.setSortIdx(prevNodeSortIdx)");
                        prevNodeIsChanged = true;
                        chngNodes.add(prevNode);
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

        List<NodeT> thisNodes = new ArrayList<NodeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodes.sort(getComparatorThisNode());
        List<NodeT> chngNodes = new ArrayList<>();

        Map<?, List<NodeT>> grpgMap = getGrpgMap(thisNodes);

        grpgMap.forEach((grpgKey, grpgNodes) -> {
            onMovePrevSortIdxBtnHelper2(grpgKey, grpgNodes, chngNodes);
        });

        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper2(Object grpgKey, List<NodeT> grpgNodes, List<NodeT> chngNodes) {
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

        grpgNodes.forEach(thisNode -> {
            onMovePrevSortIdxBtnHelper(grpgKey, thisNode, sortIdxMin, sortIdxMax, chngNodes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMovePrevSortIdxBtnHelper(Object grpgKey, NodeT thisNode, Integer sortIdxMin, Integer sortIdxMax, List<NodeT> chngNodes) {
        String logPrfx = "onMovePrevSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode = dataContext.merge(thisNode);
        Boolean thisNodeIsChanged = false;

        Integer sortIdx_ = thisNode.getSortIdx();

        if (sortIdx_ != null
                && sortIdx_ > sortIdxMin){

            // for this node, dec sortIdx
            Integer sortIdx = sortIdx_ - 1;
            logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

            thisNode.setSortIdx(sortIdx);
            logger.debug(logPrfx + " --- called thisNode.setSortIdx(sortIdx)");
            thisNodeIsChanged = true;
            chngNodes.add(thisNode);

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
            NodeT prevNode = dataManager.load(typeOfNodeT).condition(logcCond).one();

            if(prevNode != null){
                prevNode = dataContext.merge(prevNode);
                Boolean prevNodeIsChanged = false;

                Integer prevNodeSortIdx_ = prevNode.getSortIdx();
                if (prevNodeSortIdx_ != null
                        && prevNodeSortIdx_ < sortIdxMax){

                    Integer prevNodeSortIdx = prevNodeSortIdx_ + 1;
                    logger.debug(logPrfx + "prevNodeSortIdx: " + prevNodeSortIdx);

                    prevNode.setSortIdx(prevNodeSortIdx);
                    logger.debug(logPrfx + " --- prevNode.setSortIdx(prevNodeSortIdx)");
                    prevNodeIsChanged = true;
                    chngNodes.add(prevNode);
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

        List<NodeT> thisNodes = new ArrayList<NodeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodes.sort(getComparatorThisNode());

        List<NodeT> chngNodes = new ArrayList<>();

        Map<?, List<NodeT>> grpgMap = getGrpgMap(thisNodes);

        grpgMap.forEach((grpgKey, grpgNodes) -> {
            onMoveNextSortIdxBtnHelper2(grpgKey, grpgNodes, chngNodes);
        });

        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveNextSortIdxBtnHelper2(Object grpgKey, List<NodeT> grpgNodes, List<NodeT> chngNodes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper2";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMin = 0;
        Integer sortIdxMax = service.getSortIdxMax(this, grpgKey);

        grpgNodes.forEach(thisNode -> {
            onMoveNextSortIdxBtnHelper(grpgKey, thisNode, sortIdxMin, sortIdxMax, chngNodes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveNextSortIdxBtnHelper(Object grpgKey, NodeT thisNode, Integer sortIdxMin, Integer sortIdxMax, List<NodeT> chngNodes) {
        String logPrfx = "onMoveNextSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNode != null) {
            thisNode = dataContext.merge(thisNode);
            Boolean thisNodeIsChanged = false;

            Integer sortIdx_ = thisNode.getSortIdx();

            if (sortIdxMax != null
                    && sortIdx_ < sortIdxMax){

                // for this node, inc sortIdx
                Integer sortIdx = sortIdx_ + 1;
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                thisNode.setSortIdx(sortIdx);
                logger.debug(logPrfx + " --- called thisNode.setSortIdx(sortIdx)");
                thisNodeIsChanged = true;
                chngNodes.add(thisNode);


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
                NodeT nextNode = dataManager.load(typeOfNodeT).condition(logcCond).one();

                if(nextNode != null){
                    nextNode = dataContext.merge(nextNode);
                    Boolean nextNodeIsChanged = false;

                    Integer nextNodeSortIdx_ = nextNode.getSortIdx();
                    if (nextNodeSortIdx_ != null
                            && nextNodeSortIdx_ > sortIdxMin){

                        Integer nextNodeSortIdx = nextNodeSortIdx_ - 1;
                        logger.debug(logPrfx + " --- nextNodeSortIdx: " + sortIdx);

                        nextNode.setSortIdx(nextNodeSortIdx);
                        logger.debug(logPrfx + " --- called nextNode.setSortIdx(nextNodeSortIdx)");
                        nextNodeIsChanged = true;
                        chngNodes.add(nextNode);
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

        List<NodeT> thisNodes = new ArrayList<NodeT>(dataGridMain.getSelectedItems().stream().toList());
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //thisNodes.sort(getComparatorThisNode());

        List<NodeT> chngNodes = new ArrayList<>();

        Map<?, List<NodeT>> grpgMap = getGrpgMap(thisNodes);

        grpgMap.forEach((grpgKey, grpgNodes) -> {
            onMoveLastSortIdxBtnHelper2(grpgKey, grpgNodes, chngNodes);
        });

        updateHelper(chngNodes);

        logger.trace(logPrfx + " <-- ");
    }


    void onMoveLastSortIdxBtnHelper2(Object grpgKey, List<NodeT> grpgNodes, List<NodeT> chngNodes) {
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

        grpgNodes.forEach(thisNode -> {
            onMoveLastSortIdxBtnHelper(grpgKey, thisNode, sortIdxMaxAtmc, chngNodes);
        });

        logger.trace(logPrfx + " <-- ");
    }

    void onMoveLastSortIdxBtnHelper(Object grpgKey, NodeT thisNode, AtomicInteger sortIdxMax, List<NodeT> chngNodes) {
        String logPrfx = "onMoveLastSortIdxBtnHelper";
        logger.trace(logPrfx + " --> ");

        if (thisNode != null) {
            thisNode = dataContext.merge(thisNode);

            Boolean thisNodeIsChanged = false;

            Integer sortIdx_ = thisNode.getSortIdx();
            if (sortIdx_ != null
                    && sortIdx_ < sortIdxMax.intValue()){

                // for this node, max sortIdx
                Integer sortIdx = sortIdxMax.intValue();
                logger.debug(logPrfx + " --- sortIdx: " + sortIdx);

                if (Objects.equals(sortIdx_, sortIdx)) {
                    logger.debug(logPrfx + " --- no change detected");
                }else{
                    thisNode.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- called thisNode.setSortIdx(sortIdx)");
                    thisNodeIsChanged = true;
                    chngNodes.add(thisNode);
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
                List<NodeT> nextNodes = dataManager.load(typeOfNodeT).condition(logcCond).list();

                nextNodes.forEach(nextNode -> {
                    if (nextNode != null) {
                        nextNode = dataContext.merge(nextNode);

                        Boolean nextNodeIsChanged = false;

                        Integer nextSortIdx_ = nextNode.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ <= sortIdxMax.intValue()) {

                            // for this item, dec sortIdx
                            Integer nextSortIdx = nextNode.getSortIdx() - 1;
                            logger.debug(logPrfx + "nextSortIdx: " + nextSortIdx);

                            nextNode.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextNode.setSortIdx(nextSortIdx)");
                            nextNodeIsChanged = true;
                            chngNodes.add(nextNode);
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

    protected Map<?, List<NodeT>> getGrpgMap(List<NodeT> thisNodes){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrNodeBaseGrpg, List<NodeT>> grpMap = thisNodes.stream()
                .collect(groupingBy(node -> new UsrNodeBaseGrpg(node.getParent1_Id())));

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

    protected Comparator<NodeT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<NodeT> comparator = Comparator.comparing(
                NodeT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }


    protected void updateHelper(List<NodeT> chngNodes) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(chngNodes != null && !chngNodes.isEmpty()) {

            List<NodeT> chngUniqNodes = chngNodes
                    .stream().distinct().collect(Collectors.toList());

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<NodeT> thisNodes = dataGridMain.getSelectedItems().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngUniqNodes.forEach(thisNode -> {
                //NodeT thisNode = dataContext.merge(thisNode);
                if (thisNode != null) {
                    thisNode = dataContext.merge(thisNode);
                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = service.updateId2Dup(this, thisNode, updOption) || thisNodeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.save().");
                dataContext.save();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                //todo check how to dataGridMain.setSelected
                //dataGridMain.setSelected(thisNodes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeT> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {

                thisNode = dataContext.merge(thisNode);;

                boolean isChanged = false;

                isChanged = service.updateCalcVals(this, thisNode, updOption);

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
            try{dataGridMain.setSelected(thisNodes);
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

        List<NodeT> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {

                thisNode = dataContext.merge(thisNode);;

                boolean isChanged = false;

                isChanged = service.updateId2(this, thisNode, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.save().");
            dataContext.save();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //todo check how to dataGridMain.setSelected
            //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
            //dataGridMain.setSelected(thisNodes);
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
            //notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
/*
        if (StringUtils.isEmpty(thisNode.getClassName())) {
            thisNode.setClassName(typeOfNodeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfNodeT.getSimpleName());
        }
*/

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            NodeT thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisNode, updOption);
        service.updateId2Deps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisNode, updOption);
        service.updateId2Deps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            NodeT thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateSortKeyFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisNode, updOption);
        service.updateName1Deps(this, thisNode, updOption);

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

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateInst1(this, thisNode, updOption);
        service.updateInst1Deps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateDesc1(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

/*

    @Install(to = "genTags1_IdField", subject = "searchExecutor")
    protected List<UsrNodeGenTag> genTags1_IdFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        return dataManager.load(UsrNodeGenTag.class)
                .query("e.id2 like ?1 order by e.sortKey, e.id2"
                        , "(?i)%" + searchString + "%")
                .list();
    }
*/

/*
    @Subscribe("genTags1_IdField.genTags1_IdField_Select")
    public void onGenTags1_IdFieldSelect(ActionPerformedEvent event) {
        dialogWindows.lookup(this, UsrItemGenTag.class)
                .withMultiValueField(genTags1_IdField)
                .withViewId("enty_UsrItemGenTag.main")
                .open();
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