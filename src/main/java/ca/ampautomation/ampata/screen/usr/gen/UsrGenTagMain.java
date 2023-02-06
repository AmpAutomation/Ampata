package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import ca.ampautomation.ampata.entity.usr.UsrNodeRepo;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
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

@UiController("enty_UsrGenTag.main")
@UiDescriptor("usr-gen-tag-main.xml")
@LookupComponent("tableMain")
public class UsrGenTagMain extends MasterDetailScreen<UsrGenTag> {

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
    private CollectionContainer<UsrGenTag> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrGenTag> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrGenTag> instCntnrMain;
    @Autowired
    private Table<UsrGenTag> tableMain;


    //Other data loaders, containers and tables
    private CollectionContainer<UsrNodeType> genTagTypesDc;

    private CollectionLoader<UsrNodeType> genTagTypesDl;


    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrNodeType> type1_IdField;


    protected ListComponent<UsrGenTag> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        genTagTypesDc = dataComponents.createCollectionContainer(UsrNodeType.class);
        genTagTypesDl = dataComponents.createCollectionLoader();
        genTagTypesDl.setQuery("select e from enty_UsrNodeType e where e.className = 'UsrGenTag' order by e.id2");
        FetchPlan genTagTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genTagTypesDl.setFetchPlan(genTagTypesFp);
        genTagTypesDl.setContainer(genTagTypesDc);
        genTagTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(genTagTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(genTagTypesDc);
        //filter
        EntityComboBox<UsrNodeType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(genTagTypesDc);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenTagsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrGenTag> event) {
        String logPrfx = "onGenTagsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenTagsDcItemChange(InstanceContainer.ItemChangeEvent<UsrGenTag> event) {
        String logPrfx = "onGenTagsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = event.getItem();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenTag.setClassName("UsrGenTag");
        logger.debug(logPrfx + " --- className: UsrGenTag");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        genTagTypesDl.load();
        logger.debug(logPrfx + " --- called genTagTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execGenTagPrUpdNative()");
        repo.execUsrGenTagPrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execGenTagPrUpdNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrGenTag> thisGenTags = tableMain.getSelected().stream().toList();
        if (thisGenTags == null || thisGenTags.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTags.forEach(orig -> {
            UsrGenTag copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());


            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrGenTag savedCopy = dataManager.save(copy);
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

        List<UsrGenTag> thisGenTags = tableMain.getSelected().stream().toList();
        if (thisGenTags == null || thisGenTags.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenTags.forEach(thisGenTag -> {
            UsrGenTag thisTrackedGenTag = dataContext.merge(thisGenTag);
            thisGenTag = thisTrackedGenTag;
            if (thisGenTag != null) {

                Boolean thisGenTagIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenTagIsChanged = true;
                    thisGenTag.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisGenTagIsChanged = thisGenTag.updateId2Calc() || thisGenTagIsChanged;
                thisGenTagIsChanged = thisGenTag.updateId2() || thisGenTagIsChanged;
                thisGenTagIsChanged = thisGenTag.updateId2Cmp() || thisGenTagIsChanged;

                if (thisGenTagIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisGenTag).");
                    //dataManager.save(thisGenTag);
                }
            }
        });
        updateGenTagHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateGenTagHelper() {
        String logPrfx = "updateGenTagHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrGenTag> thisGenTags = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisGenTags.forEach(thisGenTag -> {
                //UsrGenTag thisTrackedGenTag = dataContext.merge(thisGenTag);
                if (thisGenTag != null) {
                    thisGenTag = dataContext.merge(thisGenTag);

                    Boolean thisGenTagIsChanged = false;

                    thisGenTagIsChanged = thisGenTag.updateId2Dup(dataManager) || thisGenTagIsChanged;

                    if (thisGenTagIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisGenTag).");
                        //dataManager.save(thisGenTag);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisGenTags);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrGenTag> thisGenTags = tableMain.getSelected().stream().toList();
        if (thisGenTags == null || thisGenTags.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTags.forEach(thisGenTag -> {
            if (thisGenTag != null) {

                thisGenTag = dataContext.merge(thisGenTag);;

                boolean isChanged = false;

                isChanged = thisGenTag.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisGenTags);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onGenTagDcItemChange(InstanceContainer.ItemChangeEvent<UsrGenTag> event) {
        String logPrfx = "onGenTagDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = event.getSource().getItemOrNull();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no record is selected.");
            //todo I observed thisGenTag is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTag.setClassName("UsrGenTag");
        logger.debug(logPrfx + " --- className: UsrGenTag");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = instCntnrMain.getItemOrNull();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTag.updateDesc1();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrGenTag thisGenTag = instCntnrMain.getItemOrNull();
            if (thisGenTag == null) {
                logger.debug(logPrfx + " --- thisGenTag is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisGenTag.updateId2Cmp();
            thisGenTag.updateId2Dup(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = instCntnrMain.getItemOrNull();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTag.updateId2();
        thisGenTag.updateId2Cmp();
        thisGenTag.updateId2Dup(dataManager);

        logger.debug(logPrfx + " --- id2: " + thisGenTag.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = instCntnrMain.getItemOrNull();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTag.updateId2Calc();
        thisGenTag.updateId2Cmp();

        logger.debug(logPrfx + " --- id2Calc: " + thisGenTag.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = instCntnrMain.getItemOrNull();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTag.updateId2Cmp();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = instCntnrMain.getItemOrNull();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTag.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genTagTypesDl.load();
        logger.debug(logPrfx + " --- called genTagTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrGenTag thisGenTag = instCntnrMain.getItemOrNull();
        if (thisGenTag == null) {
            logger.debug(logPrfx + " --- thisGenTag is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisGenTag.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


}