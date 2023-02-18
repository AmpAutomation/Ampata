package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.*;
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

@UiController("enty_UsrItem.main")
@UiDescriptor("usr-item-main.xml")
@LookupComponent("tableMain")
public class UsrItemMain extends MasterDetailScreen<UsrItem> {

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
    @Autowired
    private UsrItemQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<UsrItemType> filterConfig1A_Type1_Id;

    //Toolbar

    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrItemType> tmplt_Type1_IdField;

    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrItem> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrItem> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrItem> instCntnrMain;
    @Autowired
    private Table<UsrItem> tableMain;


    //Type data container and loader
    private CollectionLoader<UsrItemType> colLoadrType;
    private CollectionContainer<UsrItemType> colCntnrType;

    //Other data loaders, containers and tables


    //Field
    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrItemType> type1_IdField;

    protected ListComponent<UsrItem> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colCntnrType = dataComponents.createCollectionContainer(UsrItemType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrItemType e order by e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(UsrItemType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //filter
        EntityComboBox<UsrItemType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrItemType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onUsrItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrItem> event) {
        String logPrfx = "onUsrItemsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisItem is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onUsrItemsDcItemChange(InstanceContainer.ItemChangeEvent<UsrItem> event) {
        String logPrfx = "onUsrItemsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrItem thisItem = event.getItem();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }


        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");


        logger.debug(logPrfx + " --- executing repo.execPrUpdAllCalcValsforAllRowsNative()");
        qryMngr.execPrUpdAllCalcValsforAllRowsNative();
        logger.debug(logPrfx + " --- finished repo.execPrUpdAllCalcValsforAllRowsNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrItem> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItems.forEach(orig -> {
            UsrItem copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrItem savedCopy = dataManager.save(copy);
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

        List<UsrItem> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisItems.forEach(thisItem -> {
            thisItem = dataContext.merge(thisItem);
            if (thisItem != null) {

                Boolean thisItemIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisItemIsChanged = true;
                    thisItem.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisItemIsChanged = thisItem.updateId2Calc() || thisItemIsChanged;
                thisItemIsChanged = thisItem.updateId2() || thisItemIsChanged;
                thisItemIsChanged = thisItem.updateId2Cmp() || thisItemIsChanged;

                if (thisItemIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisItem).");
                    //dataManager.save(thisItem);
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

            List<UsrItem> thisItems = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisItems.forEach(thisItem -> {
                //UsrItem thisTrackedItem = dataContext.merge(thisItem);
                if (thisItem != null) {
                    thisItem = dataContext.merge(thisItem);

                    Boolean thisItemIsChanged = false;

                    thisItemIsChanged = thisItem.updateId2Dup(dataManager) || thisItemIsChanged;

                    if (thisItemIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisItem).");
                        //dataManager.save(thisItem);
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

        List<UsrItem> thisItems = tableMain.getSelected().stream().toList();
        if (thisItems == null || thisItems.isEmpty()) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItems.forEach(thisItem -> {
            if (thisItem != null) {

                thisItem = dataContext.merge(thisItem);;

                boolean isChanged = false;

                isChanged = thisItem.updateCalcVals(dataManager);

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
    public void onItemDcItemChange(InstanceContainer.ItemChangeEvent<UsrItem> event) {
        String logPrfx = "onItemDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrItem thisItem = event.getSource().getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            //todo I observed thisItem is null when selecting a new item
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

        UsrItem thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem.updateDesc1();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrItem thisItem = instCntnrMain.getItemOrNull();
            if (thisItem == null) {
                logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisItem.updateId2Cmp();
            thisItem.updateId2Dup(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItem thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem.updateId2();
        thisItem.updateId2Cmp();
        thisItem.updateId2Dup(dataManager);

        logger.debug(logPrfx + " --- id2: " + thisItem.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItem thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem.updateId2Calc();
        thisItem.updateId2Cmp();

        logger.debug(logPrfx + " --- id2Calc: " + thisItem.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItem thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem.updateId2Cmp();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItem thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem.updateId2Dup(dataManager);

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

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrItem thisItem = instCntnrMain.getItemOrNull();
        if (thisItem == null) {
            logger.debug(logPrfx + " --- thisItem is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisItem.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

}