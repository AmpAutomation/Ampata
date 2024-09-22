package ca.ampautomation.ampata.view.sys.node.base;

import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBaseType;
import ca.ampautomation.ampata.entity.sys.item.gen.SysItemGenFmla;
import ca.ampautomation.ampata.repo.sys.node.base.SysNodeBase0Type0Repo;
import ca.ampautomation.ampata.service.sys.node.base.SysNodeBase0Type0Service;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import io.jmix.core.*;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.*;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class SysNodeBase0Type0BaseMain<NodeTypeT extends SysNodeBaseType, NodeTypeServiceT extends SysNodeBase0Type0Service, NodeTypeRepoT extends SysNodeBase0Type0Repo> extends StandardListView<NodeTypeT> {

    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<NodeTypeT> typeOfNodeTypeT;

    @SuppressWarnings("unchecked")
    public SysNodeBase0Type0BaseMain() {
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }


    protected DataGrid<NodeTypeT> getDataGrid() {return (DataGrid<NodeTypeT>) getContent().getComponent("dataGridMain"); }

    //Service
    protected NodeTypeServiceT service;

    protected NodeTypeServiceT getService(){
        return service;
    }

    public void setService(NodeTypeServiceT service) { this.service = service; }

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
    protected NodeTypeRepoT repo;
    protected NodeTypeRepoT getRepo(){
        return repo;
    }

    public void setRepo(NodeTypeRepoT repo) { this.repo = repo; }


    //Filter
    @ViewComponent
    protected GenericFilter filter;


    //Toolbar


    //Template


    //Main data containers, loaders and table
    @ViewComponent
    protected CollectionLoader<NodeTypeT> colLoadrMain;
    @ViewComponent
    protected CollectionContainer<NodeTypeT> colCntnrMain;
    @ViewComponent
    protected InstanceContainer<NodeTypeT> instCntnrMain;
    @ViewComponent
    protected TreeDataGrid<NodeTypeT> dataGridMain;


    //GenFmla data container and loader
    protected CollectionLoader<SysItemGenFmla> colLoadrGenFmla;
    protected CollectionContainer<SysItemGenFmla> colCntnrGenFmla;


    //Other data loaders, containers and tables


    //Field

    @ViewComponent
    protected TypedTextField<String> id2Field;

    @ViewComponent
    protected TypedTextField<String> id2CalcField;

    @ViewComponent
    protected EntityComboBox<SysItemGenFmla> name1GenFmla1_IdField;

    @ViewComponent
    protected EntityComboBox<SysItemGenFmla> desc1GenFmla1_IdField;

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


        colCntnrGenFmla = dataComponents.createCollectionContainer(SysItemGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_SysGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla = fetchPlans.builder(SysItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getViewData().getDataContext());
        //Field
        name1GenFmla1_IdField.setItems(colCntnrGenFmla);
        desc1GenFmla1_IdField.setItems(colCntnrGenFmla);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        //dataGridMain.sort("sortKey", Table.SortDirection.ASCENDING);

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
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(ClickEvent<Button> event) {
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
    public void onDuplicateBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeTypes.forEach(orig -> {
            NodeTypeT copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

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


    @Subscribe("setBtn")
    public void onSetBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodeTypes.forEach(thisNodeType -> {
            thisNodeType = dataContext.merge(thisNodeType);
            if (thisNodeType != null) {

                Boolean thisNodeTypeIsChanged = false;

                thisNodeTypeIsChanged = thisNodeType.updateId2Calc(dataManager) || thisNodeTypeIsChanged;
                thisNodeTypeIsChanged = thisNodeType.updateId2(dataManager) || thisNodeTypeIsChanged;
                thisNodeTypeIsChanged = thisNodeType.updateId2Cmp(dataManager) || thisNodeTypeIsChanged;

                if (thisNodeTypeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisNodeType).");
                    //dataManager.save(thisNodeType);
                }
            }
        });
        updateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    protected void updateHelper() {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.save().");
            dataContext.save();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<NodeTypeT> thisNodeTypes = dataGridMain.getSelectedItems().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisNodeTypes.forEach(thisNodeType -> {
                //SysNodeBaseType thisTrackedNode = dataContext.merge(thisNodeType);
                if (thisNodeType != null) {
                    thisNodeType = dataContext.merge(thisNodeType);

                    Boolean thisNodeTypeIsChanged = false;

                    thisNodeTypeIsChanged = thisNodeType.updateId2Dup(dataManager) || thisNodeTypeIsChanged;

                    if (thisNodeTypeIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisNodeType).");
                        //dataManager.save(thisNodeType);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.save().");
                dataContext.save();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                //todo check how to dataGridMain.setSelected
                //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
                //dataGridMain.setSelected(thisNodeTypes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(thisNodeType -> {
            if (thisNodeType != null) {

                thisNodeType = dataContext.merge(thisNodeType);;

                boolean isChanged = false;

                isChanged = thisNodeType.updateCalcVals(dataManager);

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
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<SysNodeBaseType> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        SysNodeBaseType thisNodeType = event.getSource().getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            //todo I observed thisNodeType is null when selecting a new item
            //notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateInstItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNodeBaseType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            SysNodeBaseType thisNodeType = instCntnrMain.getItemOrNull();
            if (thisNodeType == null) {
                logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisNodeType.updateId2Deps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNodeBaseType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2(dataManager);
        thisNodeType.updateId2Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNodeBaseType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2Calc(dataManager);
        thisNodeType.updateId2CalDeps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNodeBaseType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2Cmp(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNodeBaseType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("name1Field")
    public void onName1FieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onName1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            NodeTypeT thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisNode.updateName1Deps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        SysNodeBaseType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateName1(dataManager);
        thisNodeType.updateName1Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInst1GenFmla1_IdFieldListBtn")
    public void onUpdateInst1GenFmla1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInst1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateDesc1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    
}