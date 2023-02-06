package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNodeRepo;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.LookupComponent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Objects;

@UiController("enty_UsrGenDocVer.main")
@UiDescriptor("usr-gen-doc-ver-main.xml")
@LookupComponent("tableMain")
public class UsrGenDocVerMain extends MasterDetailScreen<UsrNode> {

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


    //CRUD Repo
    @Autowired
    private UsrNodeRepo repo;



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
    private CollectionContainer<UsrNode> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNode> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrNode> instCntnrMain;
    @Autowired
    private Table<UsrNode> tableMain;


    //Type data container and loader
    private CollectionLoader<UsrNodeType> colCntnrType;
    private CollectionContainer<UsrNodeType> colLoadrType;


    //Other data loaders, containers and tables
    private CollectionContainer<UsrNode> colCntnrGenChan;
    private CollectionLoader<UsrNode> colLoadrGenChan;

    private CollectionContainer<UsrNode> genFilesDc;
    private CollectionLoader<UsrNode> genFilesDl;

    private CollectionContainer<UsrNode> colCntnrGenTag;
    private CollectionLoader<UsrNode> colLoadrGenTag;


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
    private EntityComboBox<UsrNode> genChan1_IdField;
    @Autowired
    private EntityComboBox<UsrNode> genFile1_IdField;
    @Autowired
    private EntityComboBox<UsrNode> genTag1_IdField;



    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colLoadrType = dataComponents.createCollectionContainer(UsrNodeType.class);
        colCntnrType = dataComponents.createCollectionLoader();
        colCntnrType.setQuery("select e from enty_UsrNodeType e where e.className = 'UsrGenDocVer' order by e.id2");
        FetchPlan genDocVerTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colCntnrType.setFetchPlan(genDocVerTypesFp);
        colCntnrType.setContainer(colLoadrType);
        colCntnrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colLoadrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colLoadrType);
        //filter
        EntityComboBox<UsrNodeType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colLoadrType);


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenDocVer' order by e.id2");
        FetchPlan genChansFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(genChansFp);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(colCntnrGenChan);


        genFilesDc = dataComponents.createCollectionContainer(UsrNode.class);
        genFilesDl = dataComponents.createCollectionLoader();
        genFilesDl.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenFile' order by e.id2");
        FetchPlan genFilesFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genFilesDl.setFetchPlan(genFilesFp);
        genFilesDl.setContainer(genFilesDc);
        genFilesDl.setDataContext(getScreenData().getDataContext());

        genFile1_IdField.setOptionsContainer(genFilesDc);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(UsrNode.class)
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
    public void onGenDocVersDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNode> event) {
        String logPrfx = "onGenDocVersDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenDocVersDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onGenDocVersDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenDocVer = event.getItem();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenDocVer.setClassName("UsrGenDocVer");
        logger.debug(logPrfx + " --- className: UsrGenDocVer");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colCntnrType.load();
        logger.debug(logPrfx + " --- called colCntnrType.load() ");

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execGenDocVerPrUpdNative()");
        repo.execUsrGenDocVerPrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execGenDocVerPrUpdNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisGenDocVers = tableMain.getSelected().stream().toList();
        if (thisGenDocVers == null || thisGenDocVers.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenDocVers.forEach(orig -> {
            UsrNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());


            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNode savedCopy = dataManager.save(copy);
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

        List<UsrNode> thisGenDocVers = tableMain.getSelected().stream().toList();
        if (thisGenDocVers == null || thisGenDocVers.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenDocVers.forEach(thisGenDocVer -> {
            UsrNode thisTrackedGenDocVer = dataContext.merge(thisGenDocVer);
            thisGenDocVer = thisTrackedGenDocVer;
            if (thisGenDocVer != null) {

                Boolean thisGenDocVerIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenDocVerIsChanged = true;
                    thisGenDocVer.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisGenDocVerIsChanged = updateId2Calc(thisGenDocVer) || thisGenDocVerIsChanged;
                thisGenDocVerIsChanged = updateId2(thisGenDocVer) || thisGenDocVerIsChanged;
                thisGenDocVerIsChanged = updateId2Cmp(thisGenDocVer) || thisGenDocVerIsChanged;

                if (thisGenDocVerIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisGenDocVer).");
                    //dataManager.save(thisGenDocVer);
                }
            }
        });
        updateGenDocVerHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateGenDocVerHelper() {
        String logPrfx = "updateGenDocVerHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNode> thisGenDocVers = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisGenDocVers.forEach(thisGenDocVer -> {
                //UsrNode thisTrackedGenDocVer = dataContext.merge(thisGenDocVer);
                if (thisGenDocVer != null) {
                    thisGenDocVer = dataContext.merge(thisGenDocVer);

                    Boolean thisGenDocVerIsChanged = false;

                    thisGenDocVerIsChanged = updateId2Dup(thisGenDocVer) || thisGenDocVerIsChanged;

                    if (thisGenDocVerIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisGenDocVer).");
                        //dataManager.save(thisGenDocVer);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisGenDocVers);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisGenDocVers = tableMain.getSelected().stream().toList();
        if (thisGenDocVers == null || thisGenDocVers.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenDocVers.forEach(thisGenDocVer -> {
            if (thisGenDocVer != null) {

                thisGenDocVer = dataContext.merge(thisGenDocVer);;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisGenDocVer);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisGenDocVers);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenDocVerDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onGenDocVerDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenDocVer = event.getSource().getItemOrNull();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no record is selected.");
            //todo I observed thisGenDocVer is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenDocVer.setClassName("UsrGenDocVer");
        logger.debug(logPrfx + " --- className: UsrGenDocVer");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenDocVer = instCntnrMain.getItemOrNull();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisGenDocVer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisGenDocVer = instCntnrMain.getItemOrNull();
            if (thisGenDocVer == null) {
                logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisGenDocVer);
            updateId2Dup(thisGenDocVer);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenDocVer = instCntnrMain.getItemOrNull();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisGenDocVer);
        updateId2Cmp(thisGenDocVer);
        updateId2Dup(thisGenDocVer);

        logger.debug(logPrfx + " --- id2: " + thisGenDocVer.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenDocVer = instCntnrMain.getItemOrNull();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisGenDocVer);
        updateId2Cmp(thisGenDocVer);

        logger.debug(logPrfx + " --- id2Calc: " + thisGenDocVer.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenDocVer = instCntnrMain.getItemOrNull();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisGenDocVer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenDocVer = instCntnrMain.getItemOrNull();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisGenDocVer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colCntnrType.load();
        logger.debug(logPrfx + " --- called colCntnrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenChan1_IdFieldListBtn")
    public void onUpdateGenChan1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenFile1_IdFieldListBtn")
    public void onUpdateGenFile1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenFile1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genFilesDl.load();
        logger.debug(logPrfx + " --- called genFilesDl.load() ");

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

        UsrNode thisGenDocVer = instCntnrMain.getItemOrNull();
        if (thisGenDocVer == null) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisGenDocVer);

        logger.trace(logPrfx + " <-- ");
    }

    public Boolean updateCalcVals(UsrNode thisGenDocVer){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateGenDocVerCalcVals(thisGenDocVer) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateGenDocVerCalcVals(@NotNull UsrNode thisGenDocVer) {
        String logPrfx = "updateGenDocVerCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in GenDocVer Object
        isChanged = updateId2Calc(thisGenDocVer) || isChanged;
        isChanged = updateId2Cmp(thisGenDocVer) || isChanged;
        isChanged = updateId2Dup(thisGenDocVer) || isChanged;
        isChanged = updateDesc1(thisGenDocVer) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNode thisGenDocVer) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull UsrNode thisGenDocVer) {
        // Assume thisGenDocVer is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisGenDocVer.getId2();
        String id2 = thisGenDocVer.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisGenDocVer.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(UsrNode thisGenDocVer){
        // Assume thisGenDocVer is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisGenDocVer.getId2Calc();
        String id2Calc = thisGenDocVer.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisGenDocVer.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull UsrNode thisGenDocVer) {
        // Assume thisGenDocVer is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisGenDocVer.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisGenDocVer.getId2(),thisGenDocVer.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisGenDocVer.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull UsrNode thisGenDocVer) {
        // Assume thisGenDocVer is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisGenDocVer.getId2Dup();
        if (thisGenDocVer.getId2() != null){
            String id2Qry = "select count(e) from enty_UsrNode e where e.className = 'UsrGenDocVer' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try{
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",thisGenDocVer.getId())
                        .parameter("id2",thisGenDocVer.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisGenDocVer.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisGenDocVer.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull UsrNode thisGenDocVer){
        // Assume thisGenDocVer is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        final String SEP = "/";
        final String SEP2 = ";";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();
        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .toFormatter();
        DecimalFormat frmtDec = new DecimalFormat("+0.00;-0.00");

        boolean isChanged = false;
        String desc1_ = thisGenDocVer.getDesc1();

        String nm = thisGenDocVer.getName1() != null ? thisGenDocVer.getName1()
                : "";

        switch (thisGenDocVer.getType1_Id2()){
            default -> {
                sb.append(nm);
            }
        }

        String tag = "";
        String tag1 = "";
        if (thisGenDocVer.getGenTag1_Id() != null) {
            tag1 = Objects.toString(thisGenDocVer.getGenTag1_Id().getId2());}
        String tag2 = "";
        if (thisGenDocVer.getGenTag1_Id() != null) {
            tag2 = Objects.toString(thisGenDocVer.getGenTag2_Id().getId2());}
        String tag3 = "";
        if (thisGenDocVer.getGenTag1_Id() != null) {
            tag3 = Objects.toString(thisGenDocVer.getGenTag3_Id().getId2());}
        String tag4 = "";
        if (thisGenDocVer.getGenTag1_Id() != null) {
            tag4 = Objects.toString(thisGenDocVer.getGenTag4_Id().getId2());}
        if (!(tag1 + tag2 + tag3 + tag4).equals("")) {
            tag = "tag [" + String.join(",",tag1, tag2, tag3, tag4) + "]";}
        logger.debug(logPrfx + " --- tag: " + tag);


        sb.append(tag);
        String desc1 = sb.toString();
/*
        List<String> list = Arrays.asList(
                name1
                ,tag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
*/

        if (!Objects.equals(desc1_, desc1)){
            thisGenDocVer.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private UsrNode findGenDocVerById2(@NotNull String GenDocVer_Id2) {
        String logPrfx = "findGenDocVerById2";
        logger.trace(logPrfx + " --> ");

        if (GenDocVer_Id2 == null) {
            logger.debug(logPrfx + " --- GenDocVer_Id2 is null.");
            notifications.create().withCaption("GenDocVer_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNode e where e.className = 'UsrGenDocVer' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + GenDocVer_Id2);

        UsrNode GenDocVer1_Id = null;
        try {
            GenDocVer1_Id = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", GenDocVer_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return GenDocVer1_Id;

    }




}