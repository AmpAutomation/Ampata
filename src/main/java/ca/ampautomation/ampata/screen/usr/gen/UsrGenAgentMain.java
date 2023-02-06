package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNodeRepo;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenAgent;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenAgentType;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@UiController("enty_UsrGenAgent.main")
@UiDescriptor("usr-gen-agent-main.xml")
@LookupComponent("tableMain")
public class UsrGenAgentMain extends MasterDetailScreen<UsrGenAgent> {

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
    protected PropertyFilter<UsrGenAgentType> filterConfig1A_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrGenAgent> filterConfig1A_GenAgent1_Id;

    @Autowired
    protected PropertyFilter<UsrGenAgent> filterConfig1A_GenAgent2_Id;

    //Toolbar

    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrGenAgentType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_GenAgent1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrGenAgent> tmplt_GenAgent1_IdField;

    @Autowired
    protected CheckBox tmplt_GenAgent2_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrGenAgent> tmplt_GenAgent2_IdField;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrGenAgent> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrGenAgent> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrGenAgent> instCntnrMain;
    @Autowired
    private Table<UsrGenAgent> tableMain;


    //Type data container and loader
    private CollectionContainer<UsrGenAgentType> genAgentTypesDc;
    private CollectionLoader<UsrGenAgentType> genAgentTypesDl;


    //Other data loaders, containers and tables
    private CollectionLoader<UsrGenAgent> genAgent1sDl;
    private CollectionContainer<UsrGenAgent> genAgent1sDc;

    private CollectionLoader<UsrNode> colLoadrGenDocVer;
    private CollectionContainer<UsrNode> colCntnrGenDocVer;

    private CollectionLoader<UsrGenTag> colLoadrGenTag;
    private CollectionContainer<UsrGenTag> colCntnrGenTag;



    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrGenAgentType> type1_IdField;

    @Autowired
    private EntityComboBox<UsrGenAgent> genAgent1_IdField;

    @Autowired
    private EntityComboBox<UsrGenAgent> genAgent2_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag1_IdField;


    protected ListComponent<UsrGenAgent> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        genAgentTypesDc = dataComponents.createCollectionContainer(UsrGenAgentType.class);
        genAgentTypesDl = dataComponents.createCollectionLoader();
        genAgentTypesDl.setQuery("select e from enty_UsrGenAgentType e where e.className = 'UsrGenAgent' order by e.id2");
        FetchPlan genAgentTypesFp = fetchPlans.builder(UsrGenAgentType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genAgentTypesDl.setFetchPlan(genAgentTypesFp);
        genAgentTypesDl.setContainer(genAgentTypesDc);
        genAgentTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(genAgentTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(genAgentTypesDc);
        //filter
        EntityComboBox<UsrGenAgentType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrGenAgentType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(genAgentTypesDc);


        genAgent1sDc = dataComponents.createCollectionContainer(UsrGenAgent.class);
        genAgent1sDl = dataComponents.createCollectionLoader();
        genAgent1sDl.setQuery("select e from enty_UsrGenAgent e where order by e.id2");
        FetchPlan genAgent1sFp = fetchPlans.builder(UsrGenAgent.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genAgent1sDl.setFetchPlan(genAgent1sFp);
        genAgent1sDl.setContainer(genAgent1sDc);
        genAgent1sDl.setDataContext(getScreenData().getDataContext());

        genAgent1_IdField.setOptionsContainer(genAgent1sDc);
        genAgent2_IdField.setOptionsContainer(genAgent1sDc);
        //template
        tmplt_GenAgent1_IdField.setOptionsContainer(genAgent1sDc);
        tmplt_GenAgent2_IdField.setOptionsContainer(genAgent1sDc);
        //filter
        EntityComboBox<UsrGenAgent> propFilterCmpnt_GenAgent1_Id;
        propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(genAgent1sDc);
        EntityComboBox<UsrGenAgent> propFilterCmpnt_GenAgent2_Id;
        propFilterCmpnt_GenAgent2_Id = (EntityComboBox<UsrGenAgent>) filterConfig1A_GenAgent2_Id.getValueComponent();
        propFilterCmpnt_GenAgent2_Id.setOptionsContainer(genAgent1sDc);



        
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
    public void onGenAgentsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrGenAgent> event) {
        String logPrfx = "onGenAgentsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = event.getSource().getItemOrNull();
        if (thisGenAgent == null){
            logger.debug(logPrfx + " --- thisGenAgent is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }else {
            logger.debug(logPrfx + " --- thisGenAgent is is not null.");
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenAgentsDcItemChange(InstanceContainer.ItemChangeEvent<UsrGenAgent> event) {
        String logPrfx = "onGenAgentsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = event.getItem();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- thisGenAgent is null.");
            //todo I observed thisGenAgent is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- thisGenAgent is is not null.");
        }

        thisGenAgent.setClassName("UsrGenAgent");
        logger.debug(logPrfx + " --- className: UsrGenAgent");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        genAgentTypesDl.load();
        logger.debug(logPrfx + " --- called genAgentTypesDl.load() ");

        genAgent1sDl.load();
        logger.debug(logPrfx + " --- called genAgent1sDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execGenAgentPrUpdNative()");
        repo.execUsrGenAgentPrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execGenAgentPrUpdNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrGenAgent> thisGenAgents = tableMain.getSelected().stream().toList();
        if (thisGenAgents == null || thisGenAgents.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgents.forEach(orig -> {
            UsrGenAgent copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());


            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrGenAgent savedCopy = dataManager.save(copy);
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

        List<UsrGenAgent> thisGenAgents = tableMain.getSelected().stream().toList();
        if (thisGenAgents == null || thisGenAgents.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenAgents.forEach(thisGenAgent -> {
            thisGenAgent = dataContext.merge(thisGenAgent);
            if (thisGenAgent != null) {

                Boolean thisGenAgentIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenAgentIsChanged = true;
                    thisGenAgent.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                if (tmplt_GenAgent1_IdFieldChk.isChecked()
                ) {
                    thisGenAgentIsChanged = true;
                    thisGenAgent.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
                }

                if (tmplt_GenAgent2_IdFieldChk.isChecked()
                ) {
                    thisGenAgentIsChanged = true;
                    thisGenAgent.setGenAgent2_Id(tmplt_GenAgent2_IdField.getValue());
                }

                thisGenAgentIsChanged = thisGenAgent.updateId2Calc() || thisGenAgentIsChanged;
                thisGenAgentIsChanged = thisGenAgent.updateId2() || thisGenAgentIsChanged;
                thisGenAgentIsChanged = thisGenAgent.updateId2Cmp() || thisGenAgentIsChanged;

                if (thisGenAgentIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisGenAgent).");
                    //dataManager.save(thisGenAgent);
                }
            }
        });
        updateGenAgentHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateGenAgentHelper() {
        String logPrfx = "updateGenAgentHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrGenAgent> thisGenAgents = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisGenAgents.forEach(thisGenAgent -> {
                //UsrGenAgent thisTrackedGenAgent = dataContext.merge(thisGenAgent);
                if (thisGenAgent != null) {
                    thisGenAgent = dataContext.merge(thisGenAgent);

                    Boolean thisGenAgentIsChanged = false;

                    thisGenAgentIsChanged = thisGenAgent.updateId2Dup(dataManager) || thisGenAgentIsChanged;

                    if (thisGenAgentIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisGenAgent).");
                        //dataManager.save(thisGenAgent);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisGenAgents);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrGenAgent> thisGenAgents = tableMain.getSelected().stream().toList();
        if (thisGenAgents == null || thisGenAgents.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgents.forEach(thisGenAgent -> {
            if (thisGenAgent != null) {

                thisGenAgent = dataContext.merge(thisGenAgent);;

                boolean isChanged = false;

                isChanged = thisGenAgent.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisGenAgents);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenAgentDcItemChange(InstanceContainer.ItemChangeEvent<UsrGenAgent> event) {
        String logPrfx = "onGenAgentDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = event.getSource().getItemOrNull();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- thisGenAgent is null.");
            //todo I observed thisGenAgent is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- thisGenAgent is not null.");
        }
        thisGenAgent.setClassName("UsrGenAgent");
        logger.debug(logPrfx + " --- className: UsrGenAgent");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = instCntnrMain.getItemOrNull();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgent.updateDesc1();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            logger.debug(logPrfx + " --- event is user originated.");
            UsrGenAgent thisGenAgent = instCntnrMain.getItemOrNull();
            if (thisGenAgent == null) {
                logger.debug(logPrfx + " --- thisGenAgent is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisGenAgent.updateId2Cmp();
            thisGenAgent.updateId2Dup(dataManager);
        }else {
            logger.debug(logPrfx + " --- event is not user originated.");
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = instCntnrMain.getItemOrNull();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgent.updateId2();
        thisGenAgent.updateId2Cmp();
        thisGenAgent.updateId2Dup(dataManager);

        logger.debug(logPrfx + " --- id2: " + thisGenAgent.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = instCntnrMain.getItemOrNull();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgent.updateId2Calc();
        thisGenAgent.updateId2Cmp();

        logger.debug(logPrfx + " --- id2Calc: " + thisGenAgent.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = instCntnrMain.getItemOrNull();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgent.updateId2Cmp();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrGenAgent thisGenAgent = instCntnrMain.getItemOrNull();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgent.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genAgentTypesDl.load();
        logger.debug(logPrfx + " --- called genAgentTypesDl.load() ");

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

        UsrGenAgent thisGenAgent = instCntnrMain.getItemOrNull();
        if (thisGenAgent == null) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenAgent.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }







}