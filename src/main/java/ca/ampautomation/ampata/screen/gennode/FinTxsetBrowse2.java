package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.components.textfieldwithbutton.TextFieldWithButton;
import ca.ampautomation.ampata.entity.*;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.component.propertyfilter.SingleFilterSupport;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@UiController("ampata_FinTxset.browse2")
@UiDescriptor("fin-txset-browse2.xml")
@LookupComponent("table")
public class FinTxsetBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<GenNodeType> filterConfig1A_type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_idDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_idDtLE;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_genChan1_Id;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_genChan2_Id;

    @Autowired
    protected PropertyFilter<FinHow> filterConfig1A_finHow1_Id;

    @Autowired
    protected PropertyFilter<FinWhat> filterConfig1A_finWhat1_Id;

    @Autowired
    protected PropertyFilter<FinWhy> filterConfig1A_finWhy1_Id;


    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_idDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_idDtLE;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idX;


    //FinTxset

    @Autowired
    protected EntityComboBox<GenNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Beg1Ts1Field;

    @Autowired
    protected CheckBox tmplt_Beg1Ts1FieldChk;

    @Autowired
    protected TextField<Integer> tmplt_IdXField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdXFieldRdo;

    @Autowired
    protected EntityComboBox<GenNode> tmplt_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<GenNode> tmplt_GenChan2_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan2_IdFieldChk;

    @Autowired
    protected EntityComboBox<FinHow> tmplt_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<FinWhat> tmplt_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<FinWhy> tmplt_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhy1_IdFieldChk;



    @Autowired
    protected CheckBox updateFilterConfig1A_IdDtLE_SyncChk;


    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdDtGERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdXRdo;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GenNodeRepository repo;


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
    private Table<GenNode> table;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> finTxsetsDc;

    @Autowired
    private DataLoader finTxsetsDl;

    @Autowired
    private InstanceContainer<GenNode> finTxsetDc;

    
    private CollectionContainer<GenNodeType> finTxsetTypesDc;

    private CollectionLoader<GenNodeType> finTxsetTypesDl;


    private CollectionContainer<GenNode> genChansDc;

    private CollectionLoader<GenNode> genChansDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;




    private CollectionContainer<GenNode> finCurcysDc;

    private CollectionLoader<GenNode> finCurcysDl;


    private CollectionContainer<FinHow> finHowsDc;

    private CollectionLoader<FinHow> finHowsDl;


    private CollectionContainer<FinWhat> finWhatsDc;

    private CollectionLoader<FinWhat> finWhatsDl;


    private CollectionContainer<FinWhy> finWhysDc;

    private CollectionLoader<FinWhy> finWhysDl;


    private CollectionContainer<GenPat> genPatsDc;

    private CollectionLoader<GenPat> genPatsDl;


    private CollectionContainer<GenNode> finTxactItmsDc;

    private CollectionLoader<GenNode> finTxactItmsDl;



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
    private EntityComboBox<GenNode> genChan2_IdField;

    @Autowired
    private EntityComboBox<FinHow>  finHow1_IdField;

    @Autowired
    private ComboBox<String> whatText1Field;

    @Autowired
    private EntityComboBox<FinWhat>  finWhat1_IdField;

    @Autowired
    private ComboBox<String> whyText1Field;

    @Autowired
    private EntityComboBox<FinWhy>  finWhy1_IdField;

    @Autowired
    private EntityComboBox<GenPat> desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<GenNode> desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<GenNode> desc1FinTxactItm2_IdField;

    
    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;


    Logger logger = LoggerFactory.getLogger(FinTxsetBrowse2.class);

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_IdXFieldRdo.setOptionsMap(map1);

        Map<String, Integer> map3 = new LinkedHashMap<>();
        map3.put("Clear", 0);
        map3.put("Match Current Row", 1);
        updateFilterConfig1B_IdDtGERdo.setOptionsMap(map3);
        updateFilterConfig1B_IdXRdo.setOptionsMap(map3);


        finTxsetTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finTxsetTypesDl = dataComponents.createCollectionLoader();
        finTxsetTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetTypesDl.setFetchPlan(finTxsetTypesFp);
        finTxsetTypesDl.setContainer(finTxsetTypesDc);
        finTxsetTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finTxsetTypesDc);

        //template
        tmplt_Type1_IdField.setOptionsContainer(finTxsetTypesDc);
        //filter
        EntityComboBox<GenNodeType> propFilterCmpnt_type1_Id;
        propFilterCmpnt_type1_Id= (EntityComboBox<GenNodeType>) filterConfig1A_type1_Id.getValueComponent();
        propFilterCmpnt_type1_Id.setOptionsContainer(finTxsetTypesDc);


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
        genChan2_IdField.setOptionsContainer(genChansDc);
        EntityComboBox<GenNode> propFilterCmpnt_genChan1_Id = (EntityComboBox<GenNode>) filterConfig1A_genChan1_Id.getValueComponent();
        propFilterCmpnt_genChan1_Id.setOptionsContainer(genChansDc);
        EntityComboBox<GenNode> propFilterCmpnt_genChan2_Id = (EntityComboBox<GenNode>) filterConfig1A_genChan2_Id.getValueComponent();
        propFilterCmpnt_genChan2_Id.setOptionsContainer(genChansDc);

        
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


        genPatsDc = dataComponents.createCollectionContainer(GenPat.class);
        genPatsDl = dataComponents.createCollectionLoader();
        genPatsDl.setQuery("select e from ampata_GenPat e order by e.id2");
        FetchPlan genPatsFp = fetchPlans.builder(GenPat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genPatsDl.setFetchPlan(genPatsFp);
        genPatsDl.setContainer(genPatsDc);
        genPatsDl.setDataContext(getScreenData().getDataContext());

        desc1GenPat1_IdField.setOptionsContainer(genPatsDc);

        finTxactItmsDc = dataComponents.createCollectionContainer(GenNode.class);
        finTxactItmsDl = dataComponents.createCollectionLoader();
        finTxactItmsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinTxactItm' order by e.id2");
        FetchPlan finTxactItmsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactItmsDl.setFetchPlan(finTxactItmsFp);
        finTxactItmsDl.setContainer(finTxactItmsDc);
        finTxactItmsDl.setDataContext(getScreenData().getDataContext());

        desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItmsDc);
        desc1FinTxactItm2_IdField.setOptionsContainer(finTxactItmsDc);


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


        logger.trace(logPrfx + " <--- ");


    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " -- Changed entity: " + event.getEntity());

        logger.trace(logPrfx + " <-- ");
    }
    

    /*
    AfterInitEvent is sent when the screen controller and all its declaratively defined components are created,
    dependency injection is completed, and all components have completed their internal initialization procedures.
    Nested screen fragments (if any) have sent their InitEvent and AfterInitEvent. In this event listener, you can
    create visual and data components and perform additional initialization if it depends on initialized nested
    fragments.
    */
    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        String logPrfx = "onAfterInit";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    /*
    InitEntityEvent is sent in screens inherited from StandardEditor and MasterDetailScreen
    before the new entity instance is set to edited entity container.
    Use this event listener to initialize default values in the new entity instance
    */
    @Subscribe
    public void onInitEntity(InitEntityEvent<GenNode> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = event.getEntity();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxset.setClassName("FinTxset");
        logger.debug(logPrfx + " --- className: FinTxset");

        logger.trace(logPrfx + " <-- ");

    }

    /*
    BeforeShowEvent is sent right before the screen is shown, for example, it is not added to the application UI yet.
    Security restrictions are applied to UI components. In this event listener, you can load data,
    check permissions and modify UI components.
    */
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }

    /*
    AfterShowEvent is sent right after the screen is shown, for example, when it is added to the application UI.
    In this event listener, you can show notifications, dialogs or other screens
    */
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        String logPrfx = "onAfterShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "finTxsetsDc", target = Target.DATA_CONTAINER)
    public void onFinTxactsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinTxsetsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = event.getItem();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxset.setClassName("FinTxset");
        logger.debug(logPrfx + " --- className: FinTxset");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "table.[idDt.date1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDate ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");

        finTxsetTypesDl.load();
        logger.debug(logPrfx + " --- called finTxsetTypesDl.load() ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        finHowsDl.load();
        logger.debug(logPrfx + " --- called finHowsDl.load() ");

        reloadWhatText1List();
        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        reloadWhyText1List();
        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");


        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Gen_Node_Pr_Upd()");
        repo.execGenNodePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Gen_Node_Pr_Upd()");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txset_Pr_Upd()");
        repo.execFinTxsetPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txset_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finTxactItmsDl.load()");
        finTxsetsDl.load();
        logger.debug(logPrfx + " --- finished finTxactItmsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("purgeColBtn")
    public void onPurgeColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onPurgeColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txset_Pr_Purge()");
        repo.execFinTxsetPrPurgeNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txset_Pr_Purge()");

        logger.debug(logPrfx + " --- loading finTxactItmsDl.load()");
        finTxsetsDl.load();
        logger.debug(logPrfx + " --- finished finTxactItmsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deleteColOrphansBtn")
    public void onDeleteColOrphansBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColOrphansBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txset_Pr_Del_Orph()");
        repo.execFinTxsetPrDelOrphNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txset_Pr_Del_Orph()");

        logger.debug(logPrfx + " --- loading finTxactItmsDl.load()");
        finTxsetsDl.load();
        logger.debug(logPrfx + " --- finished finTxactItmsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinTxsets.forEach(orig -> {
            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDate idDt1 = copy.getIdDt() != null ? copy.getIdDt().getDate1() : null;
            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                copy.getBeg1().setTs1(tmplt_Beg1Ts1Field.getValue());
                updateIdDt(copy);
            }

            Integer idX = copy.getIdX();
            if (tmplt_IdXFieldRdo.getValue() != null){
                // Set
                if (tmplt_IdXFieldRdo.getValue() == 1){
                    idX = tmplt_IdXField.getValue();
                    copy.setIdX(idX);
                }
                // Max
                else if (tmplt_IdXFieldRdo.getValue() == 2
                        && idDt1 != null) {

                    idX = getIdXMax(idDt1);
                    if (idX == null) return;
                    copy.setIdX(idX);
                }
            }

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (!Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setIdY(copy.getIdY() == null ? 1 : copy.getIdY() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            updateCalcVals(copy);

            GenNode savedCopy = dataManager.save(copy);
            finTxsetsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinTxset " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxsets.forEach(thisFinTxset -> {
            GenNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
            thisFinTxset = thisTrackedFinTxset;
            if (thisFinTxset != null) {

                Boolean thisFinTxsetIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                LocalDate idDt1 = thisFinTxset.getIdDt() != null ? thisFinTxset.getIdDt().getDate1() : null;
                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.getBeg1().setTs1(tmplt_Beg1Ts1Field.getValue());
                    updateIdDt(thisFinTxset);
                }

                Integer idX = thisFinTxset.getIdX();
                if (tmplt_IdXFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1){
                        thisFinTxsetIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinTxset.setIdX(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxsetIsChanged = true;
                        idX = getIdXMax(idDt1);
                        if (idX == null) return;
                        thisFinTxset.setIdX(idX);
                    }
                }

                if (tmplt_GenChan1_IdFieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setGenChan1_Id(tmplt_GenChan1_IdField.getValue());
                }

                if (tmplt_GenChan2_IdFieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setGenChan2_Id(tmplt_GenChan2_IdField.getValue());
                }

                if (tmplt_FinHow1_IdFieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setFinHow1_Id(tmplt_FinHow1_IdField.getValue());
                }

                if (tmplt_WhatText1FieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setWhatText1(tmplt_WhatText1Field.getValue());
                }

                if (tmplt_FinWhat1_IdFieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setFinWhat1_Id(tmplt_FinWhat1_IdField.getValue());
                }

                if (tmplt_WhyText1FieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setWhyText1(tmplt_WhyText1Field.getValue());
                }

                if (tmplt_FinWhy1_IdFieldChk.isChecked()
                ) {
                    thisFinTxsetIsChanged = true;
                    thisFinTxset.setFinWhy1_Id(tmplt_FinWhy1_IdField.getValue());
                }


                thisFinTxsetIsChanged = updateId2Calc(thisFinTxset) || thisFinTxsetIsChanged;
                thisFinTxsetIsChanged = updateId2(thisFinTxset) || thisFinTxsetIsChanged;
                thisFinTxsetIsChanged = updateId2Cmp(thisFinTxset) || thisFinTxsetIsChanged;

                if (thisFinTxsetIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                    //dataManager.save(thisFinTxset);
                }

            }
        });
        updateFinTxsetHelper();
        logger.trace(logPrfx + " <-- ");
    }


    private void updateFinTxsetHelper() {
        String logPrfx = "updateFinTxsetHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxsetsDl.load().");
            finTxsetsDl.load();

            List<GenNode> thisFinTxsets = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinTxsets.forEach(thisFinTxset -> {
                //GenNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
                if (thisFinTxset != null) {
                    GenNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
                    thisFinTxset = thisTrackedFinTxset;

                    Boolean thisFinTxsetIsChanged = false;

                    thisFinTxsetIsChanged = updateId2Dup(thisFinTxset) || thisFinTxsetIsChanged;

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finTxsetsDl.load().");
                finTxsetsDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinTxsets);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFilterConfig1A_IdDtGE_DecMonBtn")
    public void onUpdateFilterConfig1A_IdDtGE_DecMonBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_DecMonBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> propFilterCmpnt_idDtGE = (DateField<LocalDate>) filterConfig1A_idDtGE.getValueComponent();
        LocalDate idDtGE = propFilterCmpnt_idDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.minusMonths(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_idDtGE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()){
            DateField<LocalDate> propFilterCmpnt_idDtLE = (DateField<LocalDate>) filterConfig1A_idDtLE.getValueComponent();
            LocalDate idDtLE = propFilterCmpnt_idDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.minusMonths(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_idDtLE.setValue(idDtLE);
                filter.apply();
            }
        };
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1A_IdDtGE_IncMonBtn")
    public void onUpdateFilterConfig1A_IdDtGE_IncMonBtnBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_IncMonBtnBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> propFilterCmpnt_idDtGE = (DateField<LocalDate>) filterConfig1A_idDtGE.getValueComponent();
        LocalDate idDtGE = propFilterCmpnt_idDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.plusMonths(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_idDtGE.setValue(idDtGE);
            filter.apply();
        }
        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()) {
            DateField<LocalDate> propFilterCmpnt_idDtLE = (DateField<LocalDate>) filterConfig1A_idDtLE.getValueComponent();
            LocalDate idDtLE = propFilterCmpnt_idDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.plusMonths(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_idDtLE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdDtGE_DecDayBtn")
    public void onUpdateFilterConfig1A_IdDtGE_DecDayBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_DecDayBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> cmpnt_idDtGE = (DateField<LocalDate>) filterConfig1A_idDtGE.getValueComponent();
        LocalDate idDtGE = cmpnt_idDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.minusDays(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_idDtGE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()) {
            DateField<LocalDate> cmpnt_idDtLE = (DateField<LocalDate>) filterConfig1A_idDtLE.getValueComponent();
            LocalDate idDtLE = cmpnt_idDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.minusDays(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_idDtLE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdDtGE_IncDayBtn")
    public void onUpdateFilterConfig1A_IdDtGE_IncDayClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_IncDayClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> cmpnt_idDtGE = (DateField<LocalDate>) filterConfig1A_idDtGE.getValueComponent();
        LocalDate idDtGE = cmpnt_idDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.plusDays(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_idDtGE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()) {
            DateField<LocalDate> cmpnt_idDtLE = (DateField<LocalDate>) filterConfig1A_idDtLE.getValueComponent();
            LocalDate idDtLE = cmpnt_idDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.plusDays(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_idDtLE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1B_IdDtGEBtn")
    public void onUpdateFilterConfig1B_IdDtGEBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_IdDtGEBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer idDtGEOption = updateFilterConfig1B_IdDtGERdo.getValue();
        switch (idDtGEOption){
            case 0: // Clear
                filterConfig1B_idDtGE.clear();
                break;

            case 1:  // Set to match current row
                List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                GenNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getIdX() != null && thisFinTxact.getIdDt().getDate1() != null){
                        filterConfig1B_idDtGE.setValue(thisFinTxact.getIdDt().getDate1());
                        filterConfig1B_idDtGE.apply();
                    }
                }
                break;
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1B_IdXBtn")
    public void onUpdateFilterConfig1B_IdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_IdXBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer idXOption = updateFilterConfig1B_IdXRdo.getValue();
        switch (idXOption){
            case 0: // Clear
                filterConfig1B_idX.clear();
                break;

            case 1:  // Set to match current row
                List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                GenNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getIdX() != null){
                        filterConfig1B_idX.setValue(thisFinTxact.getIdX());
                        filterConfig1B_idX.apply();
                    }
                }
                break;
        }

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("zeroIdXBtn")
    public void onZeroIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxsets.forEach(thisFinTxset -> {
            if (thisFinTxset != null) {
                GenNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
                thisFinTxset = thisTrackedFinTxset;

                Boolean thisFinTxsetIsChanged = false;

                LocalDate idDt1 = thisFinTxset.getIdDt() != null ? thisFinTxset.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxset.getIdX();
                    Integer idX = 0;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxset.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxset.setIdX(" + (idX) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Calc(thisFinTxset) || thisFinTxsetIsChanged;
                    String id2_ = thisFinTxset.getId2();
                    String id2 = thisFinTxset.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxset.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxset.setId2(" + (id2) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Cmp(thisFinTxset) || thisFinTxsetIsChanged;

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }
                }
            }
        });

        updateFinTxsetHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxsets.forEach(thisFinTxset -> {
            if (thisFinTxset != null) {
                GenNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
                thisFinTxset = thisTrackedFinTxset;

                Boolean thisFinTxsetIsChanged = false;

                LocalDate idDt1 = thisFinTxset.getIdDt() != null ? thisFinTxset.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxset.getIdX();
                    Integer idX = thisFinTxset.getIdX() == null || thisFinTxset.getIdX() == 0 || thisFinTxset.getIdX() == 1 ? 0 : thisFinTxset.getIdX() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxset.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxset.setIdX(" + (idX) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Calc(thisFinTxset) || thisFinTxsetIsChanged;
                    String id2_ = thisFinTxset.getId2();
                    String id2 = thisFinTxset.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxset.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxset.setId2(" + (id2) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Cmp(thisFinTxset) || thisFinTxsetIsChanged;

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }
                }
            }
        });

        updateFinTxsetHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdXBtn")
    public void onIncIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxsets.forEach(thisFinTxset -> {
            if (thisFinTxset != null) {
                GenNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
                thisFinTxset = thisTrackedFinTxset;

                Boolean thisFinTxsetIsChanged = false;

                LocalDate idDt1 = thisFinTxset.getIdDt() != null ? thisFinTxset.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxset.getIdX();
                    Integer idX = (thisFinTxset.getIdX() == null ? 0 : thisFinTxset.getIdX()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxset.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxset.setIdX(" + (idX) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Calc(thisFinTxset) || thisFinTxsetIsChanged;
                    String id2_ = thisFinTxset.getId2();
                    String id2 = thisFinTxset.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxset.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxset.setId2(" + (id2) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Cmp(thisFinTxset) || thisFinTxsetIsChanged;

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }
                }
            }
        });

        updateFinTxsetHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("maxIdXBtn")
    public void onMaxIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxsets.forEach(thisFinTxset -> {
            if (thisFinTxset != null) {
                GenNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
                thisFinTxset = thisTrackedFinTxset;

                Boolean thisFinTxsetIsChanged = false;

                LocalDate idDt1 = thisFinTxset.getIdDt() != null ? thisFinTxset.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                            + " where e.className = 'FinTxset'"
                            + " and e.idDt.date1 = :idDt1";
                    try {
                        idXMax = dataManager.loadValue(idXMaxQry, Integer.class)
                                .store("main")
                                .parameter("idDt1", idDt1)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idXMaxQry error: " + e.getMessage());
                        idXMax = null;
                    }
                    logger.debug(logPrfx + " --- idXMaxQry result: " + idXMax + "");

                    Integer idX_ = thisFinTxset.getIdX();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinTxset.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxset.setIdX(" + (idX) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Calc(thisFinTxset) || thisFinTxsetIsChanged;
                    String id2_ = thisFinTxset.getId2();
                    String id2 = thisFinTxset.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxset.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxset.setId2(" + (id2) + ")");
                        thisFinTxsetIsChanged = true;
                    }

                    thisFinTxsetIsChanged = updateId2Cmp(thisFinTxset) || thisFinTxsetIsChanged;

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }
                }

            }
        });

        updateFinTxsetHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxsets.forEach(thisFinTxset -> {
            if (thisFinTxset != null) {
                thisFinTxset = dataContext.merge(thisFinTxset);
                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinTxset);
            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxsetsDl.load().");
            finTxsetsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinTxsets);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxsets = table.getSelected().stream().toList();
        if (thisFinTxsets == null || thisFinTxsets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxsets.forEach(thisFinTxset -> {
            if (thisFinTxset != null) {
                 thisFinTxset = dataContext.merge(thisFinTxset);

                boolean isChanged = false;

                updateId2(thisFinTxset);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxsetsDl.load().");
            finTxsetsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinTxsets);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_WhatText1FieldFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_WhatText1FieldFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_WhyText1FieldFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_WhyText1FieldFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = finTxsetDc.getItemOrNull();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- thisFinTxset is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateCalcVals(thisFinTxset);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = finTxsetDc.getItemOrNull();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- finTxsetDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinTxset);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxset = finTxsetDc.getItemOrNull();
            if (thisFinTxset == null) {
                logger.debug(logPrfx + " --- finTxsetDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Dup(thisFinTxset);
        }
        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = finTxsetDc.getItemOrNull();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- finTxsetDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinTxset);
        updateId2Cmp(thisFinTxset);
        updateId2Dup(thisFinTxset);

        logger.debug(logPrfx + " --- id2: " + thisFinTxset.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = finTxsetDc.getItemOrNull();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxset);
        updateId2Cmp(thisFinTxset);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinTxset.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = finTxsetDc.getItemOrNull();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- finTxsetDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxset);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = finTxsetDc.getItemOrNull();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- finTxsetDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinTxset);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxsetTypesDl.load();
        logger.debug(logPrfx + " --- called finTxsetTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenChan1_IdFieldListBtn")
    public void onUpdateGenChan1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenChan2_IdFieldListBtn")
    public void onUpdateGenChan2_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan2_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinHow1_IdFieldListBtn")
    public void onUpdateFinHow1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinHow1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finHowsDl.load();
        logger.debug(logPrfx + " --- called finHowsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "whatText1Field", subject = "enterPressHandler")
    private void whatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "whatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateWhatText1FieldListBtn")
    public void onUpdateWhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateWhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinWhat1_IdFieldListBtn")
    public void onUpdateFinWhat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "whyText1Field", subject = "enterPressHandler")
    private void whyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "whyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateWhyText1FieldListBtn")
    public void onUpdateWhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateWhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinWhy1_IdFieldListBtn")
    public void onUpdateFinWhy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxset = finTxsetDc.getItemOrNull();
            if (thisFinTxset == null) {
                logger.debug(logPrfx + " --- thisFinTxset is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateIdDt(thisFinTxset);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxset = finTxsetDc.getItemOrNull();
            if (thisFinTxset == null) {
                logger.debug(logPrfx + " --- finTxsetDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxset);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDescPat1_IdFieldListBtn")
    public void onUpdateDescPat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDescPat1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genPatsDl.load();
        logger.debug(logPrfx + " --- called genPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateDesc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItmsDl.load();
        logger.debug(logPrfx + " --- called finTxactItmsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FinTxactItm2_IdFieldListBtn")
    public void onUpdateDesc1FinTxactItm2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FinTxactItm2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItmsDl.load();
        logger.debug(logPrfx + " --- called finTxactItmsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateGenDocVer1_IdFieldListBtn")
    public void onUpdateGenDocVer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenDocVer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    private Boolean updateCalcVals(@NotNull GenNode thisFinTxset) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxsetCalcVals(thisFinTxset) || isChanged;

        
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxsetCalcVals(@NotNull GenNode thisFinTxset) {
        String logPrfx = "updateFinTxsetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxset Object
        isChanged = updateIdDt(thisFinTxset) || isChanged;
        isChanged = updateId2Calc(thisFinTxset) || isChanged;
        isChanged = updateId2Cmp(thisFinTxset) || isChanged;
        isChanged = updateId2Dup(thisFinTxset) || isChanged;

        isChanged = updateDesc1(thisFinTxset) || isChanged;
        isChanged = updateFinTxacts1_IdCntCalc(thisFinTxset)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private Boolean updateId2(@NotNull GenNode thisFinTxset) {
        // Assume thisFinTxset is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinTxset.getId2();
        String id2 = thisFinTxset.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinTxset.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2Calc(GenNode thisFinTxset){
        // Assume thisFinTxset is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinTxset.getId2Calc();
        String id2Calc = thisFinTxset.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinTxset.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2Cmp(GenNode thisFinTxset) {
        // Assume thisFinTxset is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinTxset.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinTxset.getId2(),thisFinTxset.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinTxset.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateId2Dup(GenNode thisFinTxset) {
        // Assume thisFinTxset is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxset.getId2Dup();
        if (thisFinTxset.getId2() != null) {
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinTxset' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinTxset.getId())
                        .parameter("id2", thisFinTxset.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinTxset.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinTxset.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateDesc1(@NotNull GenNode thisFinTxset) {
        // Assume thisFinTxset is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        if (thisFinTxset != null) {
            //finTxset is a 2nd ref (ref of a ref) of finTxactItm and is not automatically loaded into the dataContext
            thisFinTxset = dataContext.merge(thisFinTxset);

            String desc1_ = thisFinTxset.getDesc1();

            if (thisFinTxset.getDesc1GenPat1_Id() == null) {

                //thisType
                String thisType = "";
                if (thisFinTxset.getType1_Id() != null) {
                    thisType = Objects.toString(thisFinTxset.getType1_Id().getId2(), "");
                }
                logger.debug(logPrfx + " --- thisType: " + thisType);

                GenNode desc1FinTxactItm1 = thisFinTxset.getDesc1FinTxactItm1_Id() == null
                        ? findFirstFinTxactItmLikeId2(thisFinTxset.getId2() + "/%")
                        : thisFinTxset.getDesc1FinTxactItm1_Id();


                //thisAmt
                String thisAmt = "";
                if (desc1FinTxactItm1 != null) {
                    if (desc1FinTxactItm1.getAmtDebt() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtDebt();
                    } else if (desc1FinTxactItm1.getAmtCred() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtCred();
                    }
                    if (desc1FinTxactItm1.getFinCurcy1_Id() != null) {
                        thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm1.getFinCurcy1_Id().getId2(), "");
                        thisAmt = thisAmt.trim();
                    }
                    if (thisType.contains("Exch")) {
                        GenNode desc1FinTxactItm2 = thisFinTxset.getDesc1FinTxactItm2_Id() == null
                                ? findFirstFinTxactItmLikeId2(thisFinTxset.getId2() + "/Y01/%")
                                : thisFinTxset.getDesc1FinTxactItm2_Id();

                        if (desc1FinTxactItm2 != null) {
                            thisAmt = thisAmt + " -> ";
                            if (desc1FinTxactItm2.getAmtDebt() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtDebt();
                            } else if (desc1FinTxactItm2.getAmtCred() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtCred();
                            }
                            if (desc1FinTxactItm2.getFinCurcy1_Id() != null) {
                                thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm2.getFinCurcy1_Id().getId2(), "");
                                thisAmt = thisAmt.trim();
                            }
                        }
                    }

                    if (!thisAmt.equals("")) {
                        thisAmt = thisAmt.trim();
                    }
                }
                logger.debug(logPrfx + " --- thisAmt: " + thisAmt);


                String thisChan = "";
                if (thisFinTxset.getGenChan1_Id() != null) {
                    thisChan = Objects.toString(thisFinTxset.getGenChan1_Id().getId2(), "");
                }
                if (!thisChan.equals("")) {
                    thisChan = "in chan [" + thisChan + "]";
                }
                logger.debug(logPrfx + " --- thisChan: " + thisChan);


                String thisHow = "";
                if (thisFinTxset.getFinHow1_Id() != null) {
                    thisHow = Objects.toString(thisFinTxset.getFinHow1_Id().getId2(), "");
                }
                if (!thisHow.equals("")) {
                    thisHow = "via [" + thisHow + "]";
                }
                logger.debug(logPrfx + " --- thisHow: " + thisHow);

                String thisWhat = Objects.toString(thisFinTxset.getWhatText1(), "");
                if (thisFinTxset.getFinWhat1_Id() != null) {
                    thisWhat = thisWhat + " " + Objects.toString(thisFinTxset.getFinWhat1_Id().getId2());
                }
                if (!thisWhat.equals("")) {
                    thisWhat = "for " + thisWhat.trim();
                }
                logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

                String thisWhy = Objects.toString(thisFinTxset.getWhyText1(), "");
                if (thisFinTxset.getFinWhy1_Id() != null) {
                    thisWhy = thisWhy + " " + Objects.toString(thisFinTxset.getFinWhy1_Id().getId2());
                }
                if (!thisWhy.equals("")) {
                    thisWhy = "for " + thisWhy.trim();
                }
                logger.debug(logPrfx + " --- thisWhy: " + thisWhy);

                String thisDocVer = "";
                if (thisFinTxset.getGenDocVer1_Id() != null) {
                    thisDocVer = Objects.toString(thisFinTxset.getGenDocVer1_Id().getId2());
                }
                if (!thisDocVer.equals("")) {
                    thisDocVer = "doc ver " + thisDocVer;
                }
                logger.debug(logPrfx + " --- thisDocVer: " + thisDocVer);

                String thisTag = "";
                String thisTag1 = "";
                if (thisFinTxset.getGenTag1_Id() != null) {
                    thisTag1 = Objects.toString(thisFinTxset.getGenTag1_Id().getId2());
                }
                String thisTag2 = "";
                if (thisFinTxset.getGenTag1_Id() != null) {
                    thisTag2 = Objects.toString(thisFinTxset.getGenTag2_Id().getId2());
                }
                String thisTag3 = "";
                if (thisFinTxset.getGenTag1_Id() != null) {
                    thisTag3 = Objects.toString(thisFinTxset.getGenTag3_Id().getId2());
                }
                String thisTag4 = "";
                if (thisFinTxset.getGenTag1_Id() != null) {
                    thisTag4 = Objects.toString(thisFinTxset.getGenTag4_Id().getId2());
                }
                if (!(thisTag1 + thisTag2 + thisTag3 + thisTag4).equals("")) {
                    thisTag = "tag [" + String.join(",", thisTag1, thisTag2, thisTag3, thisTag4) + "]";
                }
                logger.debug(logPrfx + " --- thisTag: " + thisTag);

                List<String> list = Arrays.asList(
                        thisType
                        , thisAmt
                        , thisChan
                        , thisWhat
                        , thisWhy
                        , thisHow
                        , thisDocVer
                        , thisTag);

                String desc1 = list.stream().filter(StringUtils::isNotBlank)
                        .collect(Collectors.joining(" "));

                if (!Objects.equals(desc1_, desc1)) {
                    thisFinTxset.setDesc1(desc1);
                    logger.debug(logPrfx + " --- desc1: " + desc1);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull GenNode thisFinTxset) {
        // Assume thisFinTxset is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxset.updateIdDt();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private void reloadWhatText1List(){
        String logPrfx = "reloadWhatText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whatText1"
                + " from ampata_GenNode e"
                + " where e.className = 'FinTxset'"
                + " and e.whatText1 IS NOT NULL"
                + " order by e.whatText1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> texts = null;
        try {
            texts = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + texts.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_WhatText1Field.setOptionsList()");

        whatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whatText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadWhyText1List(){
        String logPrfx = "reloadWhyText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whyText1"
                + " from ampata_GenNode e"
                + " where e.className = 'FinTxset'"
                + " and e.whyText1 IS NOT NULL"
                + " order by e.whyText1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> texts = null;
        try {
            texts = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + texts.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_WhyText1Field.setOptionsList()");

        whyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }



    private void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "addEnteredTextToComboBoxOptionsList";
        logger.trace(logPrfx + " --> ");

        String text = enterPressEvent.getText();
        if (!Objects.equals(text, "<null>")){
            @SuppressWarnings("unchecked")
            // enterPressEvent.getSource is directly connected to a ComboBox
            ComboBox<String> cb = (ComboBox<String>) enterPressEvent.getSource();

            List<String> list;
            // this comboBox options list is created with a call to setOptionsList(List)
            // see onUpdateFinStmtItm1_Desc1Field
            // therefore cb.getOptions is type ListOptions
            ListOptions<String> listOptions = (ListOptions<String>) cb.getOptions();
            if (listOptions != null && !listOptions.getItemsCollection().isEmpty()) {
                list = (List<String>) listOptions.getItemsCollection();
            } else {
                list = new ArrayList<String>();
            }

            list.add(text);
            logger.trace(logPrfx + " --- called list.add( " + text + ")");

            cb.setOptionsList(list);

            notifications.create()
                    .withCaption("Added " + text + " to list.")
                    .show();
        }

    }


    private Integer getIdXMax(LocalDate idDt1){
        String logPrfx = "getIdXMax";
        logger.trace(logPrfx + " --> ");

        Integer idX, idXMax;
        String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                + " where e.className = 'FinTxset'"
                + " and e.idDt.date1 = :idDt1";
        try {
            idXMax = dataManager.loadValue(idXMaxQry, Integer.class)
                    .store("main")
                    .parameter("idDt1", idDt1)
                    .one();
            // max returns null if no rows or if all rows have a null value
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- idXMaxQry error: " + e.getMessage());
            notifications.create().withCaption("idXMaxQry error: " + e.getMessage()).show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }
        logger.debug(logPrfx + " --- idXMaxQry result: " + idXMax + "");

        Integer idXCntIsNull = null;
        String idXCntIsNullQry = "select count(e) from ampata_GenNode e"
                + " where e.className = 'FinTxset'"
                + " and e.idDt.date1 = :idDt1"
                + " and e.idX is null";
        try {
            idXCntIsNull = dataManager.loadValue(idXCntIsNullQry, Integer.class)
                    .store("main")
                    .parameter("idDt1", idDt1)
                    .one();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- idXCntIsNullQry error: " + e.getMessage());
            notifications.create().withCaption("idXCntIsNullQry error: " + e.getMessage()).show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }
        logger.debug(logPrfx + " --- idXCntIsNullQry result: " + idXCntIsNull + "");

        if (idXMax == null && idXCntIsNull != 0){
            idX = 1;
        }else{
            idX = idXMax == null ? 0 : idXMax + 1;
        }

        logger.trace(logPrfx + " <-- ");
        return idX;

    }


    @Subscribe("updateFinTxacts1_IdCntCalcFieldBtn")
    public void onUpdateFinTxacts1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxacts1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxset = finTxsetDc.getItemOrNull();
        if (thisFinTxset == null) {
            logger.debug(logPrfx + " --- finTxsetDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxacts1_IdCntCalc(thisFinTxset);

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateFinTxacts1_IdCntCalc(@NotNull GenNode thisFinTxset) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxacts1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer idCntCalc_ = thisFinTxset.getFinTxacts1_IdCntCalc();
        Integer idCntCalc = null ;
        String qry1 = "select count(e.id2) from ampata_GenNode e where e.className = 'FinTxact' and e.finTxset1_Id = :finTxset1_Id";
        try{
            idCntCalc = dataManager.loadValue(qry1,Integer.class)
                    .store("main")
                    .parameter("finTxset1_Id",thisFinTxset)
                    .one();
            if (idCntCalc == null) {
                idCntCalc = 0;}
            logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- idCntCalc: null");
        }

        if(!Objects.equals(idCntCalc_, idCntCalc)){
            isChanged = true;
            thisFinTxset.setFinTxacts1_IdCntCalc(idCntCalc);
            logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private GenNode findFirstFinTxactItmLikeId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFirstFinTxactItmLikeId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxactItm' and e.id2 like :id2 order by e.id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        GenNode finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finTxactItm_Id2)
                    .firstResult(0).one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finTxactItm1_Id;
    }

}