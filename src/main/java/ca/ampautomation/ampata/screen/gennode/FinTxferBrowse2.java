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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@UiController("ampata_FinTxfer.browse2")
@UiDescriptor("fin-txfer-browse2.xml")
@LookupComponent("table")
public class FinTxferBrowse2 extends MasterDetailScreen<GenNode> {


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
    private CollectionContainer<GenNode> finTxfersDc;

    @Autowired
    private InstanceContainer<GenNode> finTxferDc;

    
    private CollectionContainer<GenNodeType> finTxferTypesDc;

    private CollectionLoader<GenNodeType> finTxferTypesDl;


    private CollectionContainer<GenNode> genChansDc;

    private CollectionLoader<GenNode> genChansDl;


    private CollectionContainer<GenNode> finTxactsDc;

    private CollectionLoader<GenNode> finTxactsDl;


    private CollectionContainer<GenNode> finStmtsDc;

    private CollectionLoader<GenNode> finStmtsDl;

    
    private CollectionContainer<GenNode> finDeptsDc;

    private CollectionLoader<GenNode> finDeptsDl;

    
    private CollectionContainer<GenNode> finTaxLnesDc;

    private CollectionLoader<GenNode> finTaxLnesDl;


    private CollectionContainer<GenNode> finAcctsDc;

    private CollectionLoader<GenNode> finAcctsDl;


    private CollectionContainer<GenNode> finCurcysDc;

    private CollectionLoader<GenNode> finCurcysDl;


    private CollectionContainer<GenNode> finTxfer1sDc;

    private CollectionLoader<GenNode> finTxfer1sDl;


    private CollectionContainer<FinFmla> finFmlasDc;

    private CollectionLoader<FinFmla> finFmlasDl;


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
    private EntityComboBox<GenNode> finTxact1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finStmt1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finDept1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTaxLne1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finAcct1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTxfer1_IdField;
    
    @Autowired
    private TextField<String> finTxact1_Id2CalcField;

    @Autowired
    private EntityComboBox<FinFmla>  finFmla1_IdField;

    @Autowired
    private EntityComboBox<FinHow>  finHow1_IdField;

    @Autowired
    private EntityComboBox<FinWhat>  finWhat1_IdField;

    @Autowired
    private EntityComboBox<FinWhat>  finTxact1_Id_FinWhat1_IdField;

    @Autowired
    private EntityComboBox<FinWhat>  finTxset1_Id_FinWhat1_IdField;


    @Autowired
    private EntityComboBox<FinWhy>  finWhy1_IdField;

    @Autowired
    private EntityComboBox<FinWhy>  finTxact1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<FinWhy>  finTxset1_Id_FinWhy1_IdField;



    Logger logger = LoggerFactory.getLogger(FinTxferBrowse2.class);

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

        finTxferTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finTxferTypesDl = dataComponents.createCollectionLoader();
        finTxferTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinTxfer' order by e.id2");
        FetchPlan finTxferTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxferTypesDl.setFetchPlan(finTxferTypesFp);
        finTxferTypesDl.setContainer(finTxferTypesDc);
        finTxferTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finTxferTypesDc);


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


        finTxactsDc = dataComponents.createCollectionContainer(GenNode.class);
        finTxactsDl = dataComponents.createCollectionLoader();
        finTxactsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactsDl.setFetchPlan(finTxactsFp);
        finTxactsDl.setContainer(finTxactsDc);
        finTxactsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_IdField.setOptionsContainer(finTxactsDc);
        

        finStmtsDc = dataComponents.createCollectionContainer(GenNode.class);
        finStmtsDl = dataComponents.createCollectionLoader();
        finStmtsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtsDl.setFetchPlan(finStmtsFp);
        finStmtsDl.setContainer(finStmtsDc);
        finStmtsDl.setDataContext(getScreenData().getDataContext());

        finStmt1_IdField.setOptionsContainer(finStmtsDc);

        
        finDeptsDc = dataComponents.createCollectionContainer(GenNode.class);
        finDeptsDl = dataComponents.createCollectionLoader();
        finDeptsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinDept' order by e.id2");
        FetchPlan finDeptsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finDeptsDl.setFetchPlan(finDeptsFp);
        finDeptsDl.setContainer(finDeptsDc);
        finDeptsDl.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(finDeptsDc);


        finTaxLnesDc = dataComponents.createCollectionContainer(GenNode.class);
        finTaxLnesDl = dataComponents.createCollectionLoader();
        finTaxLnesDl.setQuery("select e from ampata_GenNode e where e.className = 'GenDocFrg' order by e.id2");
        FetchPlan finTaxLnesFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTaxLnesDl.setFetchPlan(finTaxLnesFp);
        finTaxLnesDl.setContainer(finTaxLnesDc);
        finTaxLnesDl.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(finTaxLnesDc);


        finAcctsDc = dataComponents.createCollectionContainer(GenNode.class);
        finAcctsDl = dataComponents.createCollectionLoader();
        finAcctsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctsDl.setFetchPlan(finAcctsFp);
        finAcctsDl.setContainer(finAcctsDc);
        finAcctsDl.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(finAcctsDc);


        finCurcysDc = dataComponents.createCollectionContainer(GenNode.class);
        finCurcysDl = dataComponents.createCollectionLoader();
        finCurcysDl.setQuery("select e from ampata_GenNode e where e.className = 'FinCurcy' order by e.id2");
        FetchPlan finCurcysFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finCurcysDl.setFetchPlan(finCurcysFp);
        finCurcysDl.setContainer(finCurcysDc);
        finCurcysDl.setDataContext(getScreenData().getDataContext());

        finCurcy1_IdField.setOptionsContainer(finCurcysDc);


        finTxfer1sDc = dataComponents.createCollectionContainer(GenNode.class);
        finTxfer1sDl = dataComponents.createCollectionLoader();
        finTxfer1sDl.setQuery("select e from ampata_GenNode e where e.className = 'FinTxfer' order by e.id2");
        FetchPlan finTxfer1sFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxfer1sDl.setFetchPlan(finTxfer1sFp);
        finTxfer1sDl.setContainer(finTxfer1sDc);
        finTxfer1sDl.setDataContext(getScreenData().getDataContext());

        finTxfer1_IdField.setOptionsContainer(finTxfer1sDc);


        finFmlasDc = dataComponents.createCollectionContainer(FinFmla.class);
        finFmlasDl = dataComponents.createCollectionLoader();
        finFmlasDl.setQuery("select e from ampata_FinFmla e order by e.id2");
        FetchPlan finFmlasFp = fetchPlans.builder(FinFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finFmlasDl.setFetchPlan(finFmlasFp);
        finFmlasDl.setContainer(finFmlasDc);
        finFmlasDl.setDataContext(getScreenData().getDataContext());

        finFmla1_IdField.setOptionsContainer(finFmlasDc);


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
        finTxact1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
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
        finTxact1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
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

    @Subscribe(id = "finTxfersDc", target = Target.DATA_CONTAINER)
    public void onFinTxfersDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinTxfersDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = event.getItem();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        //updateAllCalc(thisFinTxfer);

        thisFinTxfer.setClassName("FinTxfer");
        logger.debug(logPrfx + " --- className: FinTxfer");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAllCalcBtn")
    public void onUpdateAllCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAllCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAllCalc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        table.getSelected()
//                .map(this::makeCopy)
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode savedCopy = dataManager.save(copy);
                    finTxfersDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinTxfer " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );

/*
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode trackedCopy = dataContext.merge(copy);
                    dataContext.commit();
                    finTxfersDc.getMutableItems().add(trackedCopy);
                    logger.debug("Duplicated FinTxfer " + orig.getId2() + " "
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

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfer.setId2(thisFinTxfer.getId2Calc());

        logger.debug(logPrfx + " --- id2: " + thisFinTxfer.getId2());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxfer);
        updateId2Dup(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2CalcField")
    public void onId2CalcFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2CalcFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateListType1_IdFieldBtn")
    public void onUpdateListType1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListType1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finTxferTypesDl.load();
        logger.debug(logPrfx + " --- called finTxferTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListGenChan1_IdFieldBtn")
    public void onUpdateListGenChan1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListGenChan1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    
    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }




    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idYField")
    public void onIdYFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdYFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idZField")
    public void onIdZFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdZFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("BegDate1Field")
    public void onBegDate1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onBegDate1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("BegTime1Field")
    public void onBegTime1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onBegTime1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxact1_BegDate1Field")
    public void onFinTxact1_BegDate1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onFinTxact1_BegDate1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxact1_BegTime1Field")
    public void onFinTxact1_BegTime1FieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        String logPrfx = "onFinTxact1_BegTime1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_IdFieldBtn")
    public void onUpdateFinTxact1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxact1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_IdField(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinTxact1_IdFieldBtn")
    public void onUpdateListFinTxact1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTxact1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finTxactsDl.load();
        logger.debug(logPrfx + " --- called finTxactsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("createFinTxact1_Id2CalcFieldBtn")
    public void onCreateFinTxact1_Id2CalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onCreateFinTxact1_Id2CalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String finTxact1_Id2Calc = null;
        if (thisFinTxfer.getFinTxact1_Id2Calc() == null) {
            logger.debug(logPrfx + " --- thisFinTxfer.getFinTxact1_Id2Calc() is null, set FinTxact1_Id2Calc.");
            notifications.create().withCaption("FinTxact1_Id2Calc is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        finTxact1_Id2Calc = thisFinTxfer.getFinTxact1_Id2Calc();

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact1_Id2Calc);

        GenNode finTxact1_Id = null;
        try{
            finTxact1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2",finTxact1_Id2Calc)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        }catch (IllegalStateException e){
            logger.debug(logPrfx + " --- query qry returned NO results");

            GenNode newFinTxact = dataManager.create(GenNode.class);
            newFinTxact.setId2(thisFinTxfer.getFinTxact1_Id2Calc());
            newFinTxact.setClassName("FinTxact");
            GenNode savedFinTxact = dataManager.save(newFinTxact);

            logger.debug(logPrfx + " --- created FinTxfer id: " + savedFinTxact.getId());
            notifications.create().withCaption("Created FinTxfer id2:" + savedFinTxact.getId2()).show();

            //finTxactsDl.load();
            //logger.debug(logPrfx + " --- called finTxactsDl.load() ");
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id2CalcBtn")
    public void onUpdateFinTxact1_Id2CalcBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id2CalcBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id2CalcField(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinStmt1_IdFieldBtn")
    public void onUpdateListFinStmt1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinStmt1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinDept1_IdFieldBtn")
    public void onUpdateListFinDept1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinDept1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finDeptsDl.load();
        logger.debug(logPrfx + " --- called finDeptsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinTaxLne1_IdFieldBtn")
    public void onUpdateListFinTaxLne1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTaxLne1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finTaxLnesDl.load();
        logger.debug(logPrfx + " --- called finTaxLnesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinAcct1_IdFieldBtn")
    public void onUpdateListFinAcct1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinAcct1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinCurcy1_IdFieldBtn")
    public void onUpdateListFinCurcy1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinCurcy1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinTxfer1_IdFieldBtn")
    public void onUpdateListFinTxfer1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTxfer1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finTxfer1sDl.load();
        logger.debug(logPrfx + " --- called finTxfer1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxfer1_EI1_RateBtn")
    public void onUpdateFinTxfer1_EI1_RateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfer1_EI1_RateBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfer1_EI1_Rate(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateCalcRsltBtn")
    public void onUpdateCalcRsltBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateCalcRsltBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcRslt(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateCalcAmtNetBtn")
    public void onUpdateCalcAmtNetBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateCalcAmtNetBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcAmtNet(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListFinFmla1_IdFieldBtn")
    public void onUpdateListFinFmla1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinFmla1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finFmlasDl.load();
        logger.debug(logPrfx + " --- called finFmlasDl.load() ");

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

    @Subscribe("updateListFinTxact1_Id_What1_IdFieldBtn")
    public void onUpdateListFinTxact1_Id_What1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTxact1_Id_What1_IdFieldBtn";
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

    @Subscribe("updateListFinTxact1_Id_Why1_IdFieldBtn")
    public void onUpdateListFinTxact1_Id_Why1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListFinTxact1_Id_Why1_IdFieldBtn";
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


    public void updateAllCalc(GenNode thisFinTxfer){
        String logPrfx = "updateAllCalc";
        logger.trace(logPrfx + " --> ");

        updateId2Calc(thisFinTxfer);
        updateId2Cmp(thisFinTxfer);
        updateId2Dup(thisFinTxfer);
        updateDesc1(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }


    public void updateId2Calc(GenNode thisFinTxfer){
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisFinTxfer.getId2CalcFrFields();
        thisFinTxfer.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
    }

    public void updateId2Cmp(GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisFinTxfer.setId2Cmp(!thisFinTxfer.getId2().equals(thisFinTxfer.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisFinTxfer.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }
    public void updateId2Dup(GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisFinTxfer.getId2() != null){
            Integer id2Dup = dataManager.loadValue("select count(e) from ampata_GenNode e where e.className = 'FinTxfer' and e.id2 = :id2",Integer.class)
                    .store("main")
                    .parameter("id2",thisFinTxfer.getId2())
                    .one();
            thisFinTxfer.setId2Dup(id2Dup);

            logger.debug(logPrfx + " --- id2Dup: " + id2Dup);
        }
        logger.trace(logPrfx + " <-- ");
    }

    public void updateDesc1(GenNode thisFinTxfer){
        // Assume thisFinTxfer is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        String desc1 ="";
        //thisFinTxfer.setDesc1(desc1);

        logger.debug(logPrfx + " --- desc1: " + desc1);
        logger.trace(logPrfx + " <-- ");
    }

    public void updateFinTxact1_IdField(GenNode thisFinTxfer){
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_IdField";
        logger.trace(logPrfx + " --> ");

        String finTxact1_Id2Calc = finTxact1_Id2CalcField.getValue();
        logger.debug(logPrfx + " --- finTxact1_Id2Calc: " + finTxact1_Id2Calc);
        if (finTxact1_Id2Calc == null
            || finTxact1_Id2Calc == ""){
            logger.debug(logPrfx + " --- finTxact1_Id2Calc: null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact1_Id2Calc);

        GenNode finTxact1_Id = null;
        try{
            finTxact1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2",finTxact1_Id2Calc)
                    .one();
        }catch (IllegalStateException e){
            logger.debug(logPrfx + " --- query qry returned no results");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxfer.setFinTxact1_Id(finTxact1_Id);

        logger.debug(logPrfx + " --- finTxact1_Id.Id: " + finTxact1_Id.getId());
        logger.trace(logPrfx + " <-- ");
    }

    public void updateFinTxact1_Id2CalcField(GenNode thisFinTxfer){
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id2CalcField";
        logger.trace(logPrfx + " --> ");

        String finTxact1_Id2Calc ="";
        finTxact1_Id2CalcField.setValue(thisFinTxfer.getId2Calc().substring(0,24));

        logger.debug(logPrfx + " --- finTxact1_Id2Calc: " + finTxact1_Id2Calc);
        logger.trace(logPrfx + " <-- ");
    }

    public void updateFinTxfer1_EI1_Rate(GenNode thisFinTxfer){
        //Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxfer1_EI1_Rate";
        logger.trace(logPrfx + " --> ");

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        String qry = "select ft from ampata_FinRate ft"
                + " where ft.finCurcy1_Id.id = :curcyFr1"
                + " and   ft.finCurcy2_Id.id = :curcyTo2"
                + " and   ft.beg.date1 = :date1";

        logger.debug(logPrfx + " --- qry: " +  qry);

        UUID curcyFrId = thisFinTxfer.getFinTxfer1_Id().getFinCurcy1_Id().getId();
        if (curcyFrId == null) {
            logger.debug(logPrfx + " --- curcyFrId: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxfer. Please ensure a Fin/Txfer.Id is selected it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- curcyFrId: " + curcyFrId);

        }

        UUID curcyToId = thisFinTxfer.getFinCurcy1_Id().getId();
        if (curcyToId == null) {
            logger.debug(logPrfx + " --- curcyToId: null");
            notifications.create().withCaption("Unable to get the currency from this FinTxfer. Please ensure a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- curcyToId: " + curcyToId);

        }


        Date date1 = thisFinTxfer.getBeg().getDate1();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (date1 == null) {
            logger.debug(logPrfx + " --- date1: null");
            notifications.create().withCaption("Unable to get the date from this FinTxfer. Please ensure a date is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- date1: " + formatter.format(date1));

        }

        FinRate finRate;
        try {
            finRate = dataManager.load(FinRate.class)
                    .query("select ft from ampata_FinRate ft"
                            + " where ft.finCurcy1_Id.id = :curcyFr"
                            + " and   ft.finCurcy2_Id.id = :curcyTo"
                            + " and   ft.beg.date1 = :date1"
                    )
                    .parameter("curcyFr", curcyFrId)
                    .parameter("curcyTo", curcyToId)
                    .parameter("date1", date1)
                    .one()
            ;

        }catch(IllegalStateException e){
            logger.debug(logPrfx + " --- qry1 java.lang.IllegalStateException: " + e.getMessage());
            finRate = null;
        }


        if (finRate != null) {
            rate = finRate.getRate();
            qryRsltGood = true;
            logger.debug(logPrfx + " --- qry1 result is Id: " + finRate.getId());

        } else {
            logger.debug(logPrfx + " --- qry1 result is empty");
            try {

                finRate = dataManager.load(FinRate.class)
                        .query("select ft from ampata_FinRate ft "
                                + " where ft.finCurcy1_Id.id = :curcyFr"
                                + " and   ft.finCurcy2_Id.id = :curcyTo"
                                + " and   ft.beg.date1 = :date1"
                        )
                        .parameter("curcyFr", curcyToId)
                        .parameter("curcyTo", curcyFrId)
                        .parameter("date1", date1)
                        .one()
                ;
            }catch(IllegalStateException e){
                logger.debug(logPrfx + " --- qry2 java.lang.IllegalStateException: " + e.getMessage());
                finRate = null;
            }

            if (finRate != null) {
                rate = finRate.getRate();
                qryRsltGood = true;
                logger.debug(logPrfx + " --- qry2 result is Id: " + finRate.getId());
            } else {
                logger.debug(logPrfx + " --- qry2 result is empty");
            }
        }

        if (qryRsltGood) {
            thisFinTxfer.setFinTxfer1_EI1_Rate(rate);
            logger.debug(logPrfx + " --- rate: " + rate);
        }
        logger.trace(logPrfx + " <-- ");
    }

    public void updateCalcRslt(GenNode thisFinTxfer){
        //Assume thisFinTxfer is not null
        String logPrfx = "updateCalcRslt";
        logger.trace(logPrfx + " --> ");

        BigDecimal rslt = BigDecimal.valueOf(0);
        boolean rsltGood = false;

        FinFmla fmla1 = thisFinTxfer.getFinFmla1_Id();
        if (fmla1 == null) {
            logger.debug(logPrfx + " --- fmla1_Id: null");
            notifications.create().withCaption("Unable to get the finFmla1. Please finFmla1 has a formula selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            logger.debug(logPrfx + " --- fmla1_Id: " + fmla1.getId());

        }


        // Switch statement over above string
        switch (fmla1.getId2()) {

            // Ref1 Mult Exch_Rate
            case "Ref1 Mult Exch_Rate":

                GenNode finTxfer1 = thisFinTxfer.getFinTxfer1_Id();
                if (finTxfer1 == null) {
                    logger.debug(logPrfx + " --- finTxfer1_Id: null");
                    notifications.create().withCaption("Unable to get finTxfer1. Please ensure  finTxfer1 has a finTxfer selected.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }else{
                    logger.debug(logPrfx + " --- finTxfer1_Id: " + finTxfer1.getId());
                }

                BigDecimal val = finTxfer1.getAmtNet();
                if (val == null) {
                    logger.debug(logPrfx + " --- finTxfer1_AmtNet: null");
                    notifications.create().withCaption("Unable to get finTxfer1_AmtNet. Please ensure  finTxfer1_AmtNet has a value.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }else{
                    logger.debug(logPrfx + " --- finTxfer1_AmtNet: " + val);
                }

                BigDecimal rate = thisFinTxfer.getFinTxfer1_EI1_Rate();
                rslt = val.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP).negate();
                rsltGood = true;
                break;

            default:
                logger.debug(logPrfx + " --- formula not implemented: " + fmla1.getId2());
                notifications.create().withCaption("Formula not implemented: " + fmla1.getId2()).show();
                logger.trace(logPrfx + " <-- ");
                return;
        }

        if (rsltGood) {
            thisFinTxfer.setCalcRslt(rslt);
            logger.debug(logPrfx + " --- calcRslt: " + rslt);
        }
        logger.trace(logPrfx + " <-- ");
    }

    public void updateCalcAmtNet(GenNode thisFinTxfer){
        //Assume thisFinTxfer is not null
        String logPrfx = "updateCalcAmtNet";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinAcct = thisFinTxfer.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        GenNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();
        BigDecimal netAmt = BigDecimal.valueOf(0);

        if (thisFinTxfer.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                netAmt = netAmt.subtract(thisFinTxfer.getAmtDebt());
            } else {
                netAmt = netAmt.add(thisFinTxfer.getAmtDebt());
            }
        }

        if (thisFinTxfer.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                netAmt = netAmt.subtract(thisFinTxfer.getAmtCred());
            } else {
                netAmt = netAmt.add(thisFinTxfer.getAmtCred());
            }
        }
        thisFinTxfer.setAmtNet(netAmt);
        logger.debug(logPrfx + " --- amtNet: " + netAmt);

        logger.trace(logPrfx + " <-- ");
    }





}