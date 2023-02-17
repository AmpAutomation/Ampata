package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNodeRepo;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinTxactQryMngr;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFile;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFileQryMngr;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@UiController("enty_UsrGenFile.main")
@UiDescriptor("usr-gen-file-main.xml")
@LookupComponent("tableMain")
public class UsrGenFileMain extends MasterDetailScreen<UsrGenFile> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

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
    private UsrGenFileQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<UsrNodeType> filterConfig1A_Type1_Id;


    //Toolbar


    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrNodeType> tmplt_Type1_IdField;


    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrGenFile> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrGenFile> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrGenFile> instCntnrMain;
    @Autowired
    private Table<UsrGenFile> tableMain;


    //Type data container and loader
    private CollectionContainer<UsrNodeType> colCntnrType;
    private CollectionLoader<UsrNodeType> colLoadrType;


    //Other data loaders, containers and tables
    private CollectionContainer<UsrGenTag> colCntnrGenTag;
    private CollectionLoader<UsrGenTag> colLoadrGenTag;


    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag1_IdField;


    protected ListComponent<UsrGenFile> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrNodeType e where e.className = 'UsrGenFile' order by e.id2");
        FetchPlan genFileTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(genFileTypesFp);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //filter
        EntityComboBox<UsrNodeType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrGenTag e order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(UsrGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(genTagsFp);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenFilesDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrGenFile> event) {
        String logPrfx = "onGenFilesDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenFilesDcItemChange(InstanceContainer.ItemChangeEvent<UsrGenFile> event) {
        String logPrfx = "onGenFilesDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = event.getItem();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenFile.setClassName("UsrGenFile");
        logger.debug(logPrfx + " --- className: UsrGenFile");

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

        List<UsrGenFile> thisGenFiles = tableMain.getSelected().stream().toList();
        if (thisGenFiles == null || thisGenFiles.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFiles.forEach(orig -> {
            UsrGenFile copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());


            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrGenFile savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
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

        List<UsrGenFile> thisGenFiles = tableMain.getSelected().stream().toList();
        if (thisGenFiles == null || thisGenFiles.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenFiles.forEach(thisGenFile -> {
            thisGenFile = dataContext.merge(thisGenFile);
            if (thisGenFile != null) {

                Boolean thisGenFileIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenFileIsChanged = true;
                    thisGenFile.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisGenFileIsChanged = thisGenFile.updateId2Calc() || thisGenFileIsChanged;
                thisGenFileIsChanged = thisGenFile.updateId2() || thisGenFileIsChanged;
                thisGenFileIsChanged = thisGenFile.updateId2Cmp() || thisGenFileIsChanged;

                if (thisGenFileIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisGenFile).");
                    //dataManager.save(thisGenFile);
                }
            }
        });
        updateGenFileHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateGenFileHelper() {
        String logPrfx = "updateGenFileHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrGenFile> thisGenFiles = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisGenFiles.forEach(thisGenFile -> {
                //UsrGenFile thisTrackedGenFile = dataContext.merge(thisGenFile);
                if (thisGenFile != null) {
                    thisGenFile = dataContext.merge(thisGenFile);

                    Boolean thisGenFileIsChanged = false;

                    thisGenFileIsChanged = thisGenFile.updateId2Dup(dataManager) || thisGenFileIsChanged;

                    if (thisGenFileIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisGenFile).");
                        //dataManager.save(thisGenFile);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisGenFiles);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrGenFile> thisGenFiles = tableMain.getSelected().stream().toList();
        if (thisGenFiles == null || thisGenFiles.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFiles.forEach(thisGenFile -> {
            if (thisGenFile != null) {

                thisGenFile = dataContext.merge(thisGenFile);;

                boolean isChanged = false;

                isChanged = thisGenFile.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisGenFiles);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenFileDcItemChange(InstanceContainer.ItemChangeEvent<UsrGenFile> event) {
        String logPrfx = "onGenFileDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = event.getSource().getItemOrNull();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
            //todo I observed thisGenFile is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFile.setClassName("UsrGenFile");
        logger.debug(logPrfx + " --- className: UsrGenFile");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = instCntnrMain.getItemOrNull();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFile.updateDesc1();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrGenFile thisGenFile = instCntnrMain.getItemOrNull();
            if (thisGenFile == null) {
                logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisGenFile.updateId2Cmp();
            thisGenFile.updateId2Dup(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = instCntnrMain.getItemOrNull();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFile.updateId2();
        thisGenFile.updateId2Cmp();
        thisGenFile.updateId2Dup(dataManager);

        logger.debug(logPrfx + " --- id2: " + thisGenFile.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = instCntnrMain.getItemOrNull();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFile.updateId2Calc();
        thisGenFile.updateId2Cmp();

        logger.debug(logPrfx + " --- id2Calc: " + thisGenFile.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = instCntnrMain.getItemOrNull();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFile.updateId2Cmp();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = instCntnrMain.getItemOrNull();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFile.updateId2Dup(dataManager);

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

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenFile thisGenFile = instCntnrMain.getItemOrNull();
        if (thisGenFile == null) {
            logger.debug(logPrfx + " --- thisGenFile is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenFile.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

}