package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrItemType;
import ca.ampautomation.ampata.entity.usr.UsrItemType;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@UiController("enty_UsrItemType.main")
@UiDescriptor("usr-item-type-main.xml")
@LookupComponent("tableMain")
public class UsrItemTypeMain extends MasterDetailScreen<UsrItemType> {

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
    //private UsrItemTypeRepo repo;


    //Filter
    @Autowired
    protected Filter filter;


    //Toolbar


    //Template


    //Main data containers, loaders and table
    @Autowired
    private CollectionLoader<UsrItemType> colLoadrMain;
    @Autowired
    private CollectionContainer<UsrItemType> colCntnrMain;
    @Autowired
    private InstanceContainer<UsrItemType> instCntnrMain;
    @Autowired
    private Table<UsrItemType> tableMain;


    //Other data loaders, containers and tables


    //Field
    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;



    protected ListComponent<UsrItemType> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onUsrItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrItemType> event) {
        String logPrfx = "onUsrItemsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisItemType is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onUsrItemsDcItemChange(InstanceContainer.ItemChangeEvent<UsrItemType> event) {
        String logPrfx = "onUsrItemsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = event.getItem();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null");
            //todo I observed thisFinBal is null when selecting a new item
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

        notifications.create().withCaption("Functionality not implemented.").show();
/*
        logger.debug(logPrfx + " --- executing repo.execUsrGenItemTypePrUpdNative()");
        repo.execUsrGenItemTypePrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execUsrGenItemTypePrUpdNative()");

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

        List<UsrItemType> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItems.forEach(orig -> {
            UsrItemType copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrItemType savedCopy = dataManager.save(copy);
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

        List<UsrItemType> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisItems.forEach(thisItemType -> {
            thisItemType = dataContext.merge(thisItemType);
            if (thisItemType != null) {

                Boolean thisItemIsChanged = false;

                thisItemIsChanged = thisItemType.updateId2Calc(dataManager) || thisItemIsChanged;
                thisItemIsChanged = thisItemType.updateId2(dataManager) || thisItemIsChanged;
                thisItemIsChanged = thisItemType.updateId2Cmp(dataManager) || thisItemIsChanged;

                if (thisItemIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisItemType).");
                    //dataManager.save(thisItemType);
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

            List<UsrItemType> thisItems = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisItems.forEach(thisItemType -> {
                //UsrItemType thisTrackedItem = dataContext.merge(thisItemType);
                if (thisItemType != null) {
                    thisItemType = dataContext.merge(thisItemType);

                    Boolean thisItemIsChanged = false;

                    thisItemIsChanged = thisItemType.updateId2Dup(dataManager) || thisItemIsChanged;

                    if (thisItemIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisItemType).");
                        //dataManager.save(thisItemType);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisItems);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrItemType> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItems.forEach(thisItemType -> {
            if (thisItemType != null) {

                thisItemType = dataContext.merge(thisItemType);;

                boolean isChanged = false;

                isChanged = thisItemType.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisItems);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onItemDcItemChange(InstanceContainer.ItemChangeEvent<UsrItemType> event) {
        String logPrfx = "onItemDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = event.getSource().getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            //todo I observed thisItemType is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemType.updateDesc1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrItemType thisItemType = instCntnrMain.getItemOrNull();
            if (thisItemType == null) {
                logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisItemType.updateId2Cmp(dataManager);
            thisItemType.updateId2Dup(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemType.updateId2(dataManager);
        thisItemType.updateId2Cmp(dataManager);
        thisItemType.updateId2Dup(dataManager);

        logger.debug(logPrfx + " --- id2: " + thisItemType.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemType.updateId2Calc(dataManager);
        thisItemType.updateId2Cmp(dataManager);

        logger.debug(logPrfx + " --- id2Calc: " + thisItemType.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemType.updateId2Cmp(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemType.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItemType thisItemType = instCntnrMain.getItemOrNull();
        if (thisItemType == null) {
            logger.debug(logPrfx + " --- thisItemType is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItemType.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

}