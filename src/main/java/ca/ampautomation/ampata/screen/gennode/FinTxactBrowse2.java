package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.components.textfieldwithbutton.TextFieldWithButton;
import ca.ampautomation.ampata.entity.*;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@UiController("ampata_FinTxact.browse2")
@UiDescriptor("fin-txact-browse2.xml")
@LookupComponent("table")
public class FinTxactBrowse2 extends MasterDetailScreen<GenNode> {

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
    private GroupTable<GenNode> table;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> finTxactsDc;

    @Autowired
    private InstanceContainer<GenNode> finTxactDc;


    private CollectionContainer<GenNodeType> finTxactTypesDc;

    private CollectionLoader<GenNodeType> finTxactTypesDl;


    private CollectionContainer<GenNode> genChansDc;

    private CollectionLoader<GenNode> genChansDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    private CollectionContainer<GenNode> finTxsetsDc;

    private CollectionLoader<GenNode> finTxsetsDl;

    private CollectionContainer<FinHow> finHowsDc;

    private CollectionLoader<FinHow> finHowsDl;


    private CollectionContainer<FinWhat> finWhatsDc;

    private CollectionLoader<FinWhat> finWhatsDl;


    private CollectionContainer<FinWhy> finWhysDc;

    private CollectionLoader<FinWhy> finWhysDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genChan1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTxset1_IdField;

    @Autowired
    private TextField<String> finTxset1_Id2CalcField;

    @Autowired
    private EntityComboBox<FinHow>  finHow1_IdField;

    @Autowired
    private EntityComboBox<FinWhat>  finWhat1_IdField;

    @Autowired
    private EntityComboBox<FinWhat>  finTxset1_Id_FinWhat1_IdField;


    @Autowired
    private EntityComboBox<FinWhy>  finWhy1_IdField;

    @Autowired
    private EntityComboBox<FinWhy>  finTxset1_Id_FinWhy1_IdField;


    Logger logger = LoggerFactory.getLogger(FinTxactBrowse2.class);

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

/*
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
*/

        finTxactTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finTxactTypesDl = dataComponents.createCollectionLoader();
        finTxactTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactTypesDl.setFetchPlan(finTxactTypesFp);
        finTxactTypesDl.setContainer(finTxactTypesDc);
        finTxactTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finTxactTypesDc);


        genChansDc = dataComponents.createCollectionContainer(GenNode.class);
        genChansDl = dataComponents.createCollectionLoader();
        genChansDl.setQuery("select e from ampata_GenNode e where e.className = 'GenChan' order by e.id2");
        FetchPlan genChansFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genChansDl.setFetchPlan(genChansFp);
        genChansDl.setContainer(genChansDc);
        genChansDl.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(genChansDc);


        genDocVersDc = dataComponents.createCollectionContainer(GenNode.class);
        genDocVersDl = dataComponents.createCollectionLoader();
        genDocVersDl.setQuery("select e from ampata_GenNode e where e.className = 'GenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genDocVersDl.setFetchPlan(genDocVersFp);
        genDocVersDl.setContainer(genDocVersDc);
        genDocVersDl.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(genDocVersDc);


        genTagsDc = dataComponents.createCollectionContainer(GenNode.class);
        genTagsDl = dataComponents.createCollectionLoader();
        genTagsDl.setQuery("select e from ampata_GenNode e where e.className = 'GenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genTagsDl.setFetchPlan(genTagsFp);
        genTagsDl.setContainer(genTagsDc);
        genTagsDl.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(genTagsDc);


        finTxsetsDc = dataComponents.createCollectionContainer(GenNode.class);
        finTxsetsDl = dataComponents.createCollectionLoader();
        finTxsetsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetsDl.setFetchPlan(finTxsetsFp);
        finTxsetsDl.setContainer(finTxsetsDc);
        finTxsetsDl.setDataContext(getScreenData().getDataContext());

        finTxset1_IdField.setOptionsContainer(finTxsetsDc);


        finHowsDc = dataComponents.createCollectionContainer(FinHow.class);
        finHowsDl = dataComponents.createCollectionLoader();
        finHowsDl.setQuery("select e from ampata_FinHow e order by e.id2");
        FetchPlan finHowsFp = fetchPlans.builder(FinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finHowsDl.setFetchPlan(finHowsFp);
        finHowsDl.setContainer(finHowsDc);
        finHowsDl.setDataContext(getScreenData().getDataContext());

        finHow1_IdField.setOptionsContainer(finHowsDc);


        finWhatsDc = dataComponents.createCollectionContainer(FinWhat.class);
        finWhatsDl = dataComponents.createCollectionLoader();
        finWhatsDl.setQuery("select e from ampata_FinWhat e order by e.id2");
        FetchPlan finWhatsFp = fetchPlans.builder(FinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finWhatsDl.setFetchPlan(finWhatsFp);
        finWhatsDl.setContainer(finWhatsDc);
        finWhatsDl.setDataContext(getScreenData().getDataContext());

        finWhat1_IdField.setOptionsContainer(finWhatsDc);
        finTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);


        finWhysDc = dataComponents.createCollectionContainer(FinWhy.class);
        finWhysDl = dataComponents.createCollectionLoader();
        finWhysDl.setQuery("select e from ampata_FinWhy e order by e.id2");
        FetchPlan finWhysFp = fetchPlans.builder(FinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finWhysDl.setFetchPlan(finWhysFp);
        finWhysDl.setContainer(finWhysDc);
        finWhysDl.setDataContext(getScreenData().getDataContext());

        finWhy1_IdField.setOptionsContainer(finWhysDc);
        finTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);


        logger.trace(logPrfx + " <--- ");


    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " -- Changed entity: " + event.getEntity());

        logger.trace(logPrfx + " <--- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <--- ");
    }

    @Subscribe(id = "finTxactsDc", target = Target.DATA_CONTAINER)
    public void onFinTxactsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinTxactsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = event.getItem();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //updateAllFrontEndCalc(thisFinTxact);

        thisFinTxact.setClassName("FinTxact");
        logger.debug(logPrfx + " --- className: FinTxact");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateAllRowAllBackEndCalcBtn")
    public void onUpdateAllRowAllBackEndCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAllRowAllBackEndCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAllFrontEndCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        table.getSelected()
//                .map(this::makeCopy)
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode savedCopy = dataManager.save(copy);
                    finTxactsDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinTxact " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );

/*
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode trackedCopy = dataContext.merge(copy);
                    dataContext.commit();
                    finTxactsDc.getMutableItems().add(trackedCopy);
                    logger.debug("Duplicated FinTxact " + orig.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );
*/

                });
    }


    private GenNode makeCopy(GenNode orig) {
        String logPrfx = "makeCopy";
        logger.trace(logPrfx + " --> ");

        GenNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        copy.setIdZ(copy.getIdZ() + 1);
        copy.setId2(copy.getId2CalcFrFields());
        copy.setId2Calc(copy.getId2CalcFrFields());

        logger.trace(logPrfx + " <--- ");
        return copy;
    }


    @Subscribe("updateAllFrontEndCalcBtn")
    public void onUpdateAllFrontEndCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAllFrontEndCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAllFrontEndCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxact);
        updateId2Dup(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setId2(thisFinTxact.getId2Calc());

        logger.debug(logPrfx + " --- id2: " + thisFinTxact.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("id2CalcField")
    public void onId2CalcFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2CalcFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateListGenDocVer1_IdFieldBtn")
    public void onUpdateListGenDocVer1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListGenDocVer1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListGenTag1_IdFieldBtn")
    public void onUpdateListGenTag1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListGenTag1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }




    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idYField")
    public void onIdYFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdYFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("BegDate1Field")
    public void onBegDate1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onBegDate1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("BegTime1Field")
    public void onBegTime1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onBegTime1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("finTxset1_BegDate1Field")
    public void onFinTxset1_BegDate1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onFinTxset1_BegDate1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxset1_BegTime1Field")
    public void onFinTxset1_BegTime1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onFinTxset1_BegTime1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxset1_IdFieldBtn")
    public void onUpdateFinTxset1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxset1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxset1_IdField(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinTxset1_IdFieldBtn")
    public void onUpdateListFinTxset1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTxset1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finTxsetsDl.load();
        logger.debug(logPrfx + " --- called finTxsetsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("createFinTxset1_Id2CalcFieldBtn")
    public void onCreateFinTxset1_Id2CalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onCreateFinTxset1_Id2CalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String finTxset1_Id2Calc = null;
        if (thisFinTxact.getFinTxset1_Id2Calc() == null) {
            logger.debug(logPrfx + " --- thisFinTxact.getFinTxset1_Id2Calc() is null, set FinTxset1_Id2Calc.");
            notifications.create().withCaption("FinTxset1_Id2Calc is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        finTxset1_Id2Calc = thisFinTxact.getFinTxset1_Id2Calc();

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxset' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxset1_Id2Calc);

        GenNode finTxset1_Id = null;
        try{
            finTxset1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2",finTxset1_Id2Calc)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        }catch (IllegalStateException e){
            logger.debug(logPrfx + " --- query qry returned NO results");

            GenNode newFinTxset = dataManager.create(GenNode.class);
            newFinTxset.setId2(thisFinTxact.getFinTxset1_Id2Calc());
            newFinTxset.setClassName("FinTxset");
            GenNode savedFinTxset = dataManager.save(newFinTxset);

            logger.debug(logPrfx + " --- created FinTxact id: " + savedFinTxset.getId());
            notifications.create().withCaption("Created FinTxact id2:" + savedFinTxset.getId2()).show();

            //finTxsetsDl.load();
            //logger.debug(logPrfx + " --- called finTxsetsDl.load() ");
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxset1_Id2CalcBtn")
    public void onUpdateFinTxset1_Id2CalcBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id2CalcBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxset1_Id2CalcField(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateListFinHow1_IdFieldBtn")
    public void onUpdateListFinHow1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinHow1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finHowsDl.load();
        logger.debug(logPrfx + " --- called finHowsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinWhat1_IdFieldBtn")
    public void onUpdateListFinWhat1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinWhat1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateListFinTxset1_Id_What1_IdFieldBtn")
    public void onUpdateListFinTxset1_Id_What1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTxset1_Id_What1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinWhy1_IdFieldBtn")
    public void onUpdateListFinWhy1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinWhy1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateListFinTxset1_Id_Why1_IdFieldBtn")
    public void onUpdateListFinTxset1_Id_Why1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTxset1_Id_Why1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    private void updateAllFrontEndCalc(GenNode thisFinTxact){
        String logPrfx = "updateAllFrontEndCalc";
        logger.trace(logPrfx + " --> ");

        updateId2Calc(thisFinTxact);
        updateId2Cmp(thisFinTxact);
        updateId2Dup(thisFinTxact);
        updateDesc1(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    private void updateId2Calc(GenNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisFinTxact.getId2CalcFrFields();
        thisFinTxact.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
    }

    private void updateId2Cmp(GenNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisFinTxact.setId2Cmp(!thisFinTxact.getId2().equals(thisFinTxact.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisFinTxact.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }
    private void updateId2Dup(GenNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisFinTxact.getId2() != null){
            Integer id2Dup = dataManager.loadValue("select count(e) from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2",Integer.class)
                    .store("main")
                    .parameter("id2",thisFinTxact.getId2())
                    .one();
            thisFinTxact.setId2Dup(id2Dup);

            logger.debug(logPrfx + " --- id2Dup: " + id2Dup);
        }
        logger.trace(logPrfx + " <-- ");
    }

    private void updateDesc1(GenNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        String desc1 ="";
        //thisFinTxact.setDesc1(desc1);

        logger.debug(logPrfx + " --- desc1: " + desc1);
        logger.trace(logPrfx + " <-- ");
    }

    private void updateFinTxset1_IdField(GenNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_IdField";
        logger.trace(logPrfx + " --> ");

        String finTxset1_Id2Calc = finTxset1_Id2CalcField.getValue();
        logger.debug(logPrfx + " --- finTxset1_Id2Calc: " + finTxset1_Id2Calc);
        if (finTxset1_Id2Calc == null
                || finTxset1_Id2Calc == ""){
            logger.debug(logPrfx + " --- finTxset1_Id2Calc: null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxset' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxset1_Id2Calc);

        GenNode finTxset1_Id = null;
        try{
            finTxset1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2",finTxset1_Id2Calc)
                    .one();
        }catch (IllegalStateException e){
            logger.debug(logPrfx + " --- query qry returned no results");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxact.setFinTxset1_Id(finTxset1_Id);

        logger.debug(logPrfx + " --- finTxset1_Id.Id: " + finTxset1_Id.getId());
        logger.trace(logPrfx + " <-- ");
    }

    private void updateFinTxset1_Id2CalcField(GenNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_Id2CalcField";
        logger.trace(logPrfx + " --> ");

        String finTxset1_Id2Calc ="";
        finTxset1_Id2CalcField.setValue(thisFinTxact.getId2Calc().substring(0,24));

        logger.debug(logPrfx + " --- finTxset1_Id2Calc: " + finTxset1_Id2Calc);
        logger.trace(logPrfx + " <-- ");
    }

}