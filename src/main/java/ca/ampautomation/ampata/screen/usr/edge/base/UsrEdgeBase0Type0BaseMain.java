package ca.ampautomation.ampata.screen.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.comn.UsrComnBaseQryMngr;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import io.jmix.core.*;
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
import java.util.List;


public abstract class UsrEdgeBase0Type0BaseMain<EdgeTypeT extends UsrEdgeBaseType, EdgeTypeQryMngrT extends UsrComnBaseQryMngr, TableT extends Table> extends MasterDetailScreen<EdgeTypeT> {


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

    protected ListComponent<EdgeTypeT> getTable() {
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
    protected EdgeTypeQryMngrT qryMngr;


    //Toolbar


    //Template


    //Filter
    @Autowired
    protected Filter filter;


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<UsrEdgeBaseType> colLoadrMain;
    @Autowired
    protected CollectionContainer<UsrEdgeBaseType> colCntnrMain;
    @Autowired
    protected InstanceContainer<UsrEdgeBaseType> instCntnrMain;
    @Autowired
    protected TableT tableMain;


    //GenFmla data container and loader
    protected CollectionLoader<UsrItemGenFmla> colLoadrGenFmla;
    protected CollectionContainer<UsrItemGenFmla> colCntnrGenFmla;


    //Other data loaders, containers and tables


    //Field
    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected EntityComboBox<UsrItemGenFmla> name1GenFmla1_IdField;

    @Autowired
    protected EntityComboBox<UsrItemGenFmla> desc1GenFmla1_IdField;

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrItemGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrItemGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla = fetchPlans.builder(UsrItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());
        //Field
        name1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

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

        logger.debug(logPrfx + " --- executing qryMngr.execPrUpdAllCalcValsforAllRowsNative()");
        qryMngr.execPrUpdAllCalcValsforAllRowsNative();
        logger.debug(logPrfx + " --- finished qryMngr.execPrUpdAllCalcValsforAllRowsNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrEdgeBaseType> thisEdgeTypes = tableMain.getSelected().stream().toList();
        if (thisEdgeTypes == null || thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeTypes.forEach(orig -> {
            UsrEdgeBaseType copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrEdgeBaseType savedCopy = dataManager.save(copy);
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

        List<UsrEdgeBaseType> thisEdgeTypes = tableMain.getSelected().stream().toList();
        if (thisEdgeTypes == null || thisEdgeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisEdgeTypes.forEach(thisEdgeType -> {
            thisEdgeType = dataContext.merge(thisEdgeType);
            if (thisEdgeType != null) {

                Boolean thisEdgeTypeIsChanged = false;

                thisEdgeTypeIsChanged = thisEdgeType.updateId2Calc(dataManager) || thisEdgeTypeIsChanged;
                thisEdgeTypeIsChanged = thisEdgeType.updateId2(dataManager) || thisEdgeTypeIsChanged;
                thisEdgeTypeIsChanged = thisEdgeType.updateId2Cmp(dataManager) || thisEdgeTypeIsChanged;

                if (thisEdgeTypeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisEdgeType).");
                    //dataManager.save(thisEdgeType);
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
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrEdgeBaseType> thisEdgeTypes = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisEdgeTypes.forEach(thisEdgeType -> {
                //UsrEdgeItemType thisTrackedEdge = dataContext.merge(thisEdgeType);
                if (thisEdgeType != null) {
                    thisEdgeType = dataContext.merge(thisEdgeType);

                    Boolean thisEdgeTypeIsChanged = false;

                    thisEdgeTypeIsChanged = thisEdgeType.updateId2Dup(dataManager) || thisEdgeTypeIsChanged;

                    if (thisEdgeTypeIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisEdgeType).");
                        //dataManager.save(thisEdgeType);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisEdgeTypes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrEdgeBaseType> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdges.forEach(thisEdgeType -> {
            if (thisEdgeType != null) {

                thisEdgeType = dataContext.merge(thisEdgeType);;

                boolean isChanged = false;

                isChanged = thisEdgeType.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisEdges);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<UsrEdgeBaseType> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = event.getSource().getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            //todo I observed thisEdgeType is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
            if (thisEdgeType == null) {
                logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisEdgeType.updateId2Deps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType.updateId2(dataManager);
        thisEdgeType.updateId2Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType.updateId2Calc(dataManager);
        thisEdgeType.updateId2CalDeps(dataManager);

        logger.debug(logPrfx + " --- id2Calc: " + thisEdgeType.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType.updateId2Cmp(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType.updateName1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1GenFmla1_IdFieldListBtn")
    public void onUpdateName1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrEdgeBaseType thisEdgeType = instCntnrMain.getItemOrNull();
        if (thisEdgeType == null) {
            logger.debug(logPrfx + " --- thisEdgeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdgeType.updateDesc1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1GenFmla1_IdFieldListBtn")
    public void onUpdateDesc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }


}