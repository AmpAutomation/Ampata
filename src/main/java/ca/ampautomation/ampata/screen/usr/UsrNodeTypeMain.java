package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@UiController("enty_UsrNodeType.main")
@UiDescriptor("usr-node-type-main.xml")
@LookupComponent("tableMain")
public class UsrNodeTypeMain extends MasterDetailScreen<UsrNodeType> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataComponents dataComponents;

    @Autowired
    private FetchPlans fetchPlans;

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private Notifications notifications;


    //Query Manager
    //@Autowired
    //private UsrNodeTypeRepo repo;


    //Filter
    @Autowired
    protected Filter filter;


    //Toolbar


    //Template


    //Main data containers, loaders and table
    @Autowired
    private CollectionLoader<UsrNodeType> colLoadrMain;
    @Autowired
    private CollectionContainer<UsrNodeType> colCntnrMain;
    @Autowired
    private InstanceContainer<UsrNodeType> instCntnrMain;
    @Autowired
    private TreeTable<UsrNodeType> tableMain;


    //GenFmla data container and loader
    private CollectionLoader<UsrGenFmla> colLoadrGenFmla;
    private CollectionContainer<UsrGenFmla> colCntnrGenFmla;


    //Other data loaders, containers and tables


    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrGenFmla> name1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrGenFmla> desc1GenFmla1_IdField;



    protected ListComponent<UsrNodeType> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrGenFmla e order by e.id2");
        FetchPlan fchPlnGenFmla = fetchPlans.builder(UsrGenFmla.class)
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
    public void onUsrNodesDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNodeType> event) {
        String logPrfx = "onUsrNodesDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisNodeType is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onUsrNodesDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeType> event) {
        String logPrfx = "onUsrNodesDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = event.getItem();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null");
            //todo I observed thisFinBal is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if (thisNodeType.getClassName() == null || thisNodeType.getClassName().isBlank()){
            thisNodeType.setClassName("UsrNodeType");
            logger.debug(logPrfx + " --- className: UsrNodeType");
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

        notifications.create().withCaption("Functionality not implemented.").show();
/*
        logger.debug(logPrfx + " --- executing repo.execUsrGenNodeTypePrUpdNative()");
        repo.execUsrGenNodeTypePrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execUsrGenNodeTypePrUpdNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");
*/

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeType> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(orig -> {
            UsrNodeType copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNodeType savedCopy = dataManager.save(copy);
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

        List<UsrNodeType> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodes.forEach(thisNodeType -> {
            thisNodeType = dataContext.merge(thisNodeType);
            if (thisNodeType != null) {

                Boolean thisNodeIsChanged = false;

                thisNodeIsChanged = thisNodeType.updateId2Calc() || thisNodeIsChanged;
                thisNodeIsChanged = thisNodeType.updateId2() || thisNodeIsChanged;
                thisNodeIsChanged = thisNodeType.updateId2Cmp() || thisNodeIsChanged;

                if (thisNodeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisNodeType).");
                    //dataManager.save(thisNodeType);
                }
            }
        });
        updateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateHelper() {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNodeType> thisNodes = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisNodes.forEach(thisNodeType -> {
                //UsrNodeType thisTrackedNode = dataContext.merge(thisNodeType);
                if (thisNodeType != null) {
                    thisNodeType = dataContext.merge(thisNodeType);

                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = thisNodeType.updateId2Dup(dataManager) || thisNodeIsChanged;

                    if (thisNodeIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisNodeType).");
                        //dataManager.save(thisNodeType);
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


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeType> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
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
    public void onNodeDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeType> event) {
        String logPrfx = "onNodeDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = event.getSource().getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            //todo I observed thisNodeType is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.setClassName("UsrNodeType");
        logger.debug(logPrfx + " --- className: UsrNodeType");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateDesc1();

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

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeType thisNodeType = instCntnrMain.getItemOrNull();
            if (thisNodeType == null) {
                logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisNodeType.updateId2Cmp();
            thisNodeType.updateId2Dup(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2();
        thisNodeType.updateId2Cmp();
        thisNodeType.updateId2Dup(dataManager);

        logger.debug(logPrfx + " --- id2: " + thisNodeType.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2Calc();
        thisNodeType.updateId2Cmp();

        logger.debug(logPrfx + " --- id2Calc: " + thisNodeType.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2Cmp();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateId2Dup(dataManager);

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

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeType thisNodeType = instCntnrMain.getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeType.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

}