package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNodeRepo;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
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

@UiController("enty_UsrGenChan.main")
@UiDescriptor("usr-gen-chan-main.xml")
@LookupComponent("tableMain")
public class UsrGenChanMain extends MasterDetailScreen<UsrNode> {

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


    //CRUD repo
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
    private CollectionContainer<UsrNodeType> colCntnrType;
    private CollectionLoader<UsrNodeType> colLoadrType;


    //Other data loaders, containers and tables
    private CollectionContainer<UsrNode> colCntnrGenDocVer;

    private CollectionLoader<UsrNode> colLoadrGenDocVer;


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
    private EntityComboBox<UsrNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genTag1_IdField;



    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrNodeType e where e.className = 'UsrGenChan' order by e.id2");
        FetchPlan genChanTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(genChanTypesFp);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //filter
        EntityComboBox<UsrNodeType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);


        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(genDocVersFp);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


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
    public void onGenChansDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNode> event) {
        String logPrfx = "onGenChansDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenChansDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onGenChansDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenChan = event.getItem();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenChan.setClassName("UsrGenChan");
        logger.debug(logPrfx + " --- className: UsrGenChan");

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

        logger.debug(logPrfx + " --- executing repo.execGenChanPrUpdNative()");
        repo.execUsrGenChanPrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execGenChanPrUpdNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisGenChans = tableMain.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenChans.forEach(orig -> {
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

        List<UsrNode> thisGenChans = tableMain.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenChans.forEach(thisGenChan -> {
            UsrNode thisTrackedGenChan = dataContext.merge(thisGenChan);
            thisGenChan = thisTrackedGenChan;
            if (thisGenChan != null) {

                Boolean thisGenChanIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenChanIsChanged = true;
                    thisGenChan.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisGenChanIsChanged = updateId2Calc(thisGenChan) || thisGenChanIsChanged;
                thisGenChanIsChanged = updateId2(thisGenChan) || thisGenChanIsChanged;
                thisGenChanIsChanged = updateId2Cmp(thisGenChan) || thisGenChanIsChanged;

                if (thisGenChanIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisGenChan).");
                    //dataManager.save(thisGenChan);
                }
            }
        });
        updateGenChanHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateGenChanHelper() {
        String logPrfx = "updateGenChanHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNode> thisGenChans = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisGenChans.forEach(thisGenChan -> {
                //UsrNode thisTrackedGenChan = dataContext.merge(thisGenChan);
                if (thisGenChan != null) {
                    thisGenChan = dataContext.merge(thisGenChan);

                    Boolean thisGenChanIsChanged = false;

                    thisGenChanIsChanged = updateId2Dup(thisGenChan) || thisGenChanIsChanged;

                    if (thisGenChanIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisGenChan).");
                        //dataManager.save(thisGenChan);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisGenChans);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisGenChans = tableMain.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenChans.forEach(thisGenChan -> {
            if (thisGenChan != null) {

                thisGenChan = dataContext.merge(thisGenChan);;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisGenChan);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisGenChans);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenChanDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onGenChanDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenChan = event.getSource().getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            //todo I observed thisGenChan is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenChan.setClassName("UsrGenChan");
        logger.debug(logPrfx + " --- className: UsrGenChan");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenChan = instCntnrMain.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisGenChan = instCntnrMain.getItemOrNull();
            if (thisGenChan == null) {
                logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisGenChan);
            updateId2Dup(thisGenChan);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenChan = instCntnrMain.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisGenChan);
        updateId2Cmp(thisGenChan);
        updateId2Dup(thisGenChan);

        logger.debug(logPrfx + " --- id2: " + thisGenChan.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenChan = instCntnrMain.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisGenChan);
        updateId2Cmp(thisGenChan);

        logger.debug(logPrfx + " --- id2Calc: " + thisGenChan.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenChan = instCntnrMain.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisGenChan = instCntnrMain.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenDocVer1_IdFieldListBtn")
    public void onUpdateGenDocVer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenDocVer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenDocVer.load();
        logger.debug(logPrfx + " --- called colLoadrGenDocVer.load() ");

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

        UsrNode thisGenChan = instCntnrMain.getItemOrNull();
        if (thisGenChan == null) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisGenChan);

        logger.trace(logPrfx + " <-- ");
    }

    public Boolean updateCalcVals(UsrNode thisGenChan){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateGenChanCalcVals(thisGenChan) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateGenChanCalcVals(@NotNull UsrNode thisGenChan) {
        String logPrfx = "updateGenChanCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in GenChan Object
        isChanged = updateId2Calc(thisGenChan) || isChanged;
        isChanged = updateId2Cmp(thisGenChan) || isChanged;
        isChanged = updateId2Dup(thisGenChan) || isChanged;
        isChanged = updateDesc1(thisGenChan) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNode thisGenChan) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull UsrNode thisGenChan) {
        // Assume thisGenChan is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisGenChan.getId2();
        String id2 = thisGenChan.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisGenChan.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(UsrNode thisGenChan){
        // Assume thisGenChan is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisGenChan.getId2Calc();
        String id2Calc = thisGenChan.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisGenChan.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull UsrNode thisGenChan) {
        // Assume thisGenChan is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisGenChan.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisGenChan.getId2(),thisGenChan.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisGenChan.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull UsrNode thisGenChan) {
        // Assume thisGenChan is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisGenChan.getId2Dup();
        if (thisGenChan.getId2() != null){
            String id2Qry = "select count(e) from enty_UsrNode e where e.className = 'UsrGenChan' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try{
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",thisGenChan.getId())
                        .parameter("id2",thisGenChan.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisGenChan.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisGenChan.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull UsrNode thisGenChan){
        // Assume thisGenChan is not null
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
        String desc1_ = thisGenChan.getDesc1();

        String nm = thisGenChan.getName1() != null ? thisGenChan.getName1()
                : "";

        switch (thisGenChan.getType1_Id2()){
            default -> {
                sb.append(nm);
            }
        }

        String tag = "";
        String tag1 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag1 = Objects.toString(thisGenChan.getGenTag1_Id().getId2());}
        String tag2 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag2 = Objects.toString(thisGenChan.getGenTag2_Id().getId2());}
        String tag3 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag3 = Objects.toString(thisGenChan.getGenTag3_Id().getId2());}
        String tag4 = "";
        if (thisGenChan.getGenTag1_Id() != null) {
            tag4 = Objects.toString(thisGenChan.getGenTag4_Id().getId2());}
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
            thisGenChan.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private UsrNode findGenChanById2(@NotNull String GenChan_Id2) {
        String logPrfx = "findGenChanById2";
        logger.trace(logPrfx + " --> ");

        if (GenChan_Id2 == null) {
            logger.debug(logPrfx + " --- GenChan_Id2 is null.");
            notifications.create().withCaption("GenChan_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNode e where e.className = 'UsrGenChan' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + GenChan_Id2);

        UsrNode GenChan1_Id = null;
        try {
            GenChan1_Id = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", GenChan_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return GenChan1_Id;

    }

}