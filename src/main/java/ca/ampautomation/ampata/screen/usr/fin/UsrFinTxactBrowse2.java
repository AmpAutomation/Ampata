package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.*;
import ca.ampautomation.ampata.entity.usr.*;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@UiController("ampata_UsrFinTxact.browse2")
@UiDescriptor("usr-fin-txact-browse2.xml")
@LookupComponent("table")
public class UsrFinTxactBrowse2 extends MasterDetailScreen<UsrNode> {


    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected Filter filter;

    // filterConfig1A
    @Autowired
    protected PropertyFilter<UsrNodeType> filterConfig1A_Type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtLE;


    // filterConfig1A
    @Autowired
    protected PropertyFilter<UsrNodeType> filterConfig1B_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeType> filterConfig1A_FinTxset1_Id_type1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_FinTxset1_Id_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrFinHow> filterConfig1A_FinTxset1_Id_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrFinWhat> filterConfig1A_FinTxset1_Id_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrFinWhy> filterConfig1A_FinTxset1_Id_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_FinTxset1_Id_GenTag1_Id;

    // filterConfig1B
    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_IdDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_IdDtLE;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_IdX;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_IdY;

    @Autowired
    protected PropertyFilter<UsrNodeType> filterConfig1B_FinTxset1_Id_type1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1B_FinTxset1_Id_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrFinHow> filterConfig1B_FinTxset1_Id_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrFinWhat> filterConfig1B_FinTxset1_Id_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrFinWhy> filterConfig1B_FinTxset1_Id_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1B_FinTxset1_Id_GenTag1_Id;


    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsTxsetOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxsetOption;

    @Autowired
    private CheckBox linkTxsetAutoCreateChk;


    //FinTxset

    @Autowired
    protected EntityComboBox<UsrNodeType> tmplt_FinTxset1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxset1_Id_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNode> tmplt_FinTxset1_Id_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxset1_Id_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrFinHow> tmplt_FinTxset1_Id_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxset1_Id_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxset1_Id_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxset1_Id_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrFinWhat> tmplt_FinTxset1_Id_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxset1_Id_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxset1_Id_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxset1_Id_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrFinWhy> tmplt_FinTxset1_Id_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxset1_Id_FinWhy1_IdFieldChk;

    //FinTxact

    @Autowired
    protected EntityComboBox<UsrNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxset1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_FinTxset1_EI1_RoleFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Beg1Ts1Field;

    @Autowired
    protected CheckBox tmplt_Beg1Ts1FieldChk;

    @Autowired
    protected TextField<Integer> tmplt_IdXField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdXFieldRdo;

    @Autowired
    protected TextField<Integer> tmplt_IdYField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdYFieldRdo;




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
    private UsrNodeRepository repo;

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


    @Autowired
    private Table<UsrNode> table;
    @Autowired
    private CollectionContainer<UsrNode> finTxactsDc;

    @Autowired
    private DataLoader finTxactsDl;

    @Autowired
    private InstanceContainer<UsrNode> finTxactDc;


    private CollectionContainer<UsrNodeType> finTxactTypesDc;

    private CollectionLoader<UsrNodeType> finTxactTypesDl;


    private CollectionContainer<UsrNode> genChansDc;

    private CollectionLoader<UsrNode> genChansDl;


    private CollectionContainer<UsrNode> genDocVersDc;

    private CollectionLoader<UsrNode> genDocVersDl;


    private CollectionContainer<UsrNode> genTagsDc;

    private CollectionLoader<UsrNode> genTagsDl;


    private CollectionContainer<UsrNode> finTxsetsDc;

    private CollectionLoader<UsrNode> finTxsetsDl;

    private CollectionContainer<UsrNodeType> finTxsetTypesDc;

    private CollectionLoader<UsrNodeType> finTxsetTypesDl;



    private CollectionContainer<UsrNode> finTxactItmsDc;

    private CollectionLoader<UsrNode> finTxactItmsDl;


    private CollectionContainer<UsrFinHow> finHowsDc;

    private CollectionLoader<UsrFinHow> finHowsDl;


    private CollectionContainer<UsrFinWhat> finWhatsDc;

    private CollectionLoader<UsrFinWhat> finWhatsDl;


    private CollectionContainer<UsrFinWhy> finWhysDc;

    private CollectionLoader<UsrFinWhy> finWhysDl;


    private CollectionContainer<UsrGenPat> genPatsDc;

    private CollectionLoader<UsrGenPat> genPatsDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<UsrGenPat> desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genTag1_IdField;


    // FinTxset
    @Autowired
    private EntityComboBox<UsrNode> finTxset1_IdField;

    @Autowired
    private TextField<String> finTxset1_Id2TrgtField;

    @Autowired
    private EntityComboBox<UsrNodeType> finTxset1_Id_Type1_IdField;

    @Autowired
    private ComboBox<String> finTxset1_EI1_RoleField;

    @Autowired
    private EntityComboBox<UsrNode> finTxset1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrFinHow> finTxset1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxset1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhat>  finTxset1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxset1_Id_WhyText1Field;
    @Autowired
    private EntityComboBox<UsrFinWhy>  finTxset1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenPat> finTxset1_Id_Desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finTxset1_Id_Desc1FinTxactItm1_IdField;


    Logger logger = LoggerFactory.getLogger(UsrFinTxactBrowse2.class);

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_IdXFieldRdo.setOptionsMap(map1);
        tmplt_IdYFieldRdo.setOptionsMap(map1);

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put("None", 0);
        map2.put("Link to Exist Txset", 1);
        map2.put("Link to Exist/New Txset", 2);
        map2.put("Update Exist Txset", 3);
        updateColItemCalcValsTxsetOption.setOptionsMap(map2);
        updateInstItemCalcValsTxsetOption.setOptionsMap(map2);

        Map<String, Integer> map3 = new LinkedHashMap<>();
        map3.put("Clear", 0);
        map3.put("Match Current Row", 1);
        updateFilterConfig1B_IdDtGERdo.setOptionsMap(map3);
        updateFilterConfig1B_IdXRdo.setOptionsMap(map3);


        finTxactTypesDc = dataComponents.createCollectionContainer(UsrNodeType.class);
        finTxactTypesDl = dataComponents.createCollectionLoader();
        finTxactTypesDl.setQuery("select e from ampata_UsrNodeType e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactTypesDl.setFetchPlan(finTxactTypesFp);
        finTxactTypesDl.setContainer(finTxactTypesDc);
        finTxactTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(finTxactTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(finTxactTypesDc);
        //filter
        EntityComboBox<UsrNodeType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(finTxactTypesDc);
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeType>) filterConfig1B_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(finTxactTypesDc);


        genChansDc = dataComponents.createCollectionContainer(UsrNode.class);
        genChansDl = dataComponents.createCollectionLoader();
        genChansDl.setQuery("select e from ampata_UsrNode e where e.className = 'GenChan' order by e.id2");
        FetchPlan genChansFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genChansDl.setFetchPlan(genChansFp);
        genChansDl.setContainer(genChansDc);
        genChansDl.setDataContext(getScreenData().getDataContext());
        finTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        //template
        tmplt_FinTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        //filter
        EntityComboBox<UsrNode> propFilterCmpnt_GenChan1_Id;
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNode>) filterConfig1A_FinTxset1_Id_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(genChansDc);
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNode>) filterConfig1B_FinTxset1_Id_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(genChansDc);


        finHowsDc = dataComponents.createCollectionContainer(UsrFinHow.class);
        finHowsDl = dataComponents.createCollectionLoader();
        finHowsDl.setQuery("select e from ampata_FinHow e order by e.id2");
        FetchPlan finHowsFp = fetchPlans.builder(UsrFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finHowsDl.setFetchPlan(finHowsFp);
        finHowsDl.setContainer(finHowsDc);
        finHowsDl.setDataContext(getScreenData().getDataContext());

        finTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        //template
        tmplt_FinTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        //filter
        EntityComboBox<UsrFinHow> propFilterCmpnt_FinHow1_Id;
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrFinHow>) filterConfig1A_FinTxset1_Id_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(finHowsDc);
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrFinHow>) filterConfig1B_FinTxset1_Id_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(finHowsDc);


        finWhatsDc = dataComponents.createCollectionContainer(UsrFinWhat.class);
        finWhatsDl = dataComponents.createCollectionLoader();
        finWhatsDl.setQuery("select e from ampata_FinWhat e order by e.id2");
        FetchPlan finWhatsFp = fetchPlans.builder(UsrFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finWhatsDl.setFetchPlan(finWhatsFp);
        finWhatsDl.setContainer(finWhatsDc);
        finWhatsDl.setDataContext(getScreenData().getDataContext());

        finTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        //template
        tmplt_FinTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        //filter
        EntityComboBox<UsrFinWhat> propFilterCmpnt_FinWhat1_Id;
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrFinWhat>) filterConfig1A_FinTxset1_Id_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(finWhatsDc);
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrFinWhat>) filterConfig1B_FinTxset1_Id_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(finWhatsDc);


        finWhysDc = dataComponents.createCollectionContainer(UsrFinWhy.class);
        finWhysDl = dataComponents.createCollectionLoader();
        finWhysDl.setQuery("select e from ampata_FinWhy e order by e.id2");
        FetchPlan finWhysFp = fetchPlans.builder(UsrFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finWhysDl.setFetchPlan(finWhysFp);
        finWhysDl.setContainer(finWhysDc);
        finWhysDl.setDataContext(getScreenData().getDataContext());

        finTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        //template
        tmplt_FinTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        //filter
        EntityComboBox<UsrFinWhy> propFilterCmpnt_FinWhy1_Id;
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrFinWhy>) filterConfig1A_FinTxset1_Id_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(finWhysDc);
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrFinWhy>) filterConfig1B_FinTxset1_Id_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(finWhysDc);

        genPatsDc = dataComponents.createCollectionContainer(UsrGenPat.class);
        genPatsDl = dataComponents.createCollectionLoader();
        genPatsDl.setQuery("select e from ampata_GenPat e order by e.id2");
        FetchPlan genPatsFp = fetchPlans.builder(UsrGenPat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genPatsDl.setFetchPlan(genPatsFp);
        genPatsDl.setContainer(genPatsDc);
        genPatsDl.setDataContext(getScreenData().getDataContext());

        desc1GenPat1_IdField.setOptionsContainer(genPatsDc);
        finTxset1_Id_Desc1GenPat1_IdField.setOptionsContainer(genPatsDc);


        genDocVersDc = dataComponents.createCollectionContainer(UsrNode.class);
        genDocVersDl = dataComponents.createCollectionLoader();
        genDocVersDl.setQuery("select e from ampata_UsrNode e where e.className = 'GenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genDocVersDl.setFetchPlan(genDocVersFp);
        genDocVersDl.setContainer(genDocVersDc);
        genDocVersDl.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(genDocVersDc);


        genTagsDc = dataComponents.createCollectionContainer(UsrNode.class);
        genTagsDl = dataComponents.createCollectionLoader();
        genTagsDl.setQuery("select e from ampata_UsrNode e where e.className = 'GenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genTagsDl.setFetchPlan(genTagsFp);
        genTagsDl.setContainer(genTagsDc);
        genTagsDl.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(genTagsDc);


        finTxsetsDc = dataComponents.createCollectionContainer(UsrNode.class);
        finTxsetsDl = dataComponents.createCollectionLoader();
        finTxsetsDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetsDl.setFetchPlan(finTxsetsFp);
        finTxsetsDl.setContainer(finTxsetsDc);
        finTxsetsDl.setDataContext(getScreenData().getDataContext());

        finTxset1_IdField.setOptionsContainer(finTxsetsDc);


        finTxsetTypesDc = dataComponents.createCollectionContainer(UsrNodeType.class);
        finTxsetTypesDl = dataComponents.createCollectionLoader();
        finTxsetTypesDl.setQuery("select e from ampata_UsrNodeType e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetTypesDl.setFetchPlan(finTxsetTypesFp);
        finTxsetTypesDl.setContainer(finTxsetTypesDc);
        finTxsetTypesDl.setDataContext(getScreenData().getDataContext());

        finTxset1_Id_Type1_IdField.setOptionsContainer(finTxsetTypesDc);
        //filter
        EntityComboBox<UsrNodeType> propFilterCmpnt_FinTxsetType1_Id;
        propFilterCmpnt_FinTxsetType1_Id = (EntityComboBox<UsrNodeType>) filterConfig1A_FinTxset1_Id_type1_Id.getValueComponent();
        propFilterCmpnt_FinTxsetType1_Id.setOptionsContainer(finTxsetTypesDc);
        propFilterCmpnt_FinTxsetType1_Id = (EntityComboBox<UsrNodeType>) filterConfig1B_FinTxset1_Id_type1_Id.getValueComponent();
        propFilterCmpnt_FinTxsetType1_Id.setOptionsContainer(finTxsetTypesDc);


        finTxactItmsDc = dataComponents.createCollectionContainer(UsrNode.class);
        finTxactItmsDl = dataComponents.createCollectionLoader();
        finTxactItmsDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinTxactItm' order by e.id2");
        FetchPlan finTxactItmsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactItmsDl.setFetchPlan(finTxactItmsFp);
        finTxactItmsDl.setContainer(finTxactItmsDc);
        finTxactItmsDl.setDataContext(getScreenData().getDataContext());

        desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItmsDc);
        finTxset1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItmsDc);


        logger.trace(logPrfx + " <--- ");


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
    public void onInitEntity(InitEntityEvent<UsrNode> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = event.getEntity();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setClassName("FinTxact");
        logger.debug(logPrfx + " --- className: FinTxact");

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


    @Subscribe(id = "finTxactsDc", target = Target.DATA_CONTAINER)
    public void onFinTxactsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinTxactsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = event.getItem();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setClassName("FinTxact");
        logger.debug(logPrfx + " --- className: FinTxact");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "table.[idDt.date1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDate dt) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return dt == null ? null : dt.format(formatter);
    }


    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");

        finTxactTypesDl.load();
        logger.debug(logPrfx + " --- called finTxactTypesDl.load() ");

        reloadFinTxset1_EI1_RoleList();

        finTxsetTypesDl.load();
        logger.debug(logPrfx + " --- called finTxsetTypesDl.load() ");

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

        genPatsDl.load();
        logger.debug(logPrfx + " --- called genPat.load() ");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execNodePrUpdNative()");
        repo.execNodePrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execNodePrUpdNative()");

        logger.debug(logPrfx + " --- executing repo.execFinTxactPrUpdNative()");
        repo.execFinTxactPrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execFinTxactPrUpdNative()");

        logger.debug(logPrfx + " --- loading finTxactsDl.load()");
        finTxactsDl.load();
        logger.debug(logPrfx + " --- finished finTxactsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("purgeColBtn")
    public void onPurgeColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onPurgeColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execFinTxactPrPurgeNative()");
        repo.execFinTxactPrPurgeNative();
        logger.debug(logPrfx + " --- finished repo.execFinTxactPrPurgeNative()");

        logger.debug(logPrfx + " --- loading finTxactsDl.load()");
        finTxactsDl.load();
        logger.debug(logPrfx + " --- finished finTxactsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deleteColOrphansBtn")
    public void onDeleteColOrphansBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColOrphansBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execFinTxactPrDelOrphNative()");
        repo.execFinTxactPrDelOrphNative();
        logger.debug(logPrfx + " --- finished repo.execFinTxactPrDelOrphNative()");

        logger.debug(logPrfx + " --- loading finTxactsDl.load()");
        finTxactsDl.load();
        logger.debug(logPrfx + " --- finished finTxactsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNode> sels = new ArrayList<>();

        thisFinTxacts.forEach(orig -> {
            UsrNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_FinTxset1_EI1_RoleFieldChk.isChecked()) {
                copy.setFinTxset1_EI1_Role(tmplt_FinTxset1_EI1_RoleField.getValue());
            }

            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                copy.getBeg1().setTs1(tmplt_Beg1Ts1Field.getValue());
                updateIdDt(copy);
            }
            LocalDate idDt1 = copy.getIdDt() != null ? copy.getIdDt().getDate1() : null;

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

            Integer idY = copy.getIdY();
            if (tmplt_IdYFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdYFieldRdo.getValue() == 1) {
                    idY = tmplt_IdYField.getValue();
                    copy.setIdY(idY);
                }
                // Max
                else if (tmplt_IdYFieldRdo.getValue() == 2
                        && idDt1 != null) {

                    idY = getIdYMax(idDt1, idX);
                    if (idY == null) return;
                    copy.setIdY(idY);
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
            Integer finTxsetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);

            updateCalcVals(copy, finTxsetOption);

            UsrNode savedCopy = dataManager.save(copy);
            finTxactsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
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

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxacts.forEach(thisFinTxact -> {
            UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            thisFinTxact = thisTrackedFinTxact;
            if (thisFinTxact != null) {

                Boolean thisFinTxactIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactIsChanged = true;
                    thisFinTxact.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                if (tmplt_FinTxset1_EI1_RoleFieldChk.isChecked()) {
                    thisFinTxactIsChanged = true;
                    thisFinTxact.setFinTxset1_EI1_Role(tmplt_FinTxset1_EI1_RoleField.getValue());
                }

                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    thisFinTxactIsChanged = true;
                    thisFinTxact.getBeg1().setTs1(tmplt_Beg1Ts1Field.getValue());
                    updateIdDt(thisFinTxact);
                }
                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;

                Integer idX = thisFinTxact.getIdX();
                if (tmplt_IdXFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1){
                        thisFinTxactIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinTxact.setIdX(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactIsChanged = true;
                        idX = getIdXMax(idDt1);
                        if (idX == null) return;
                        thisFinTxact.setIdX(idX);
                    }
                }

                Integer idY = thisFinTxact.getIdY();
                if (tmplt_IdYFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_IdYFieldRdo.getValue() == 1){
                        thisFinTxactIsChanged = true;
                        idY = tmplt_IdYField.getValue();
                        thisFinTxact.setIdY(idY);
                    }
                    // Max
                    else if (tmplt_IdYFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactIsChanged = true;
                        idY = getIdYMax(idDt1, idX);
                        if (idY == null) return;
                        thisFinTxact.setIdY(idY);
                    }
                }


                thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                thisFinTxactIsChanged = updateId2(thisFinTxact) || thisFinTxactIsChanged;
                thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                if (thisFinTxactIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                    //dataManager.save(thisFinTxact);
                }


                UsrNode thisFinTxset = thisFinTxact.getFinTxset1_Id();
                if (thisFinTxset != null) {

                    UsrNode thisTrackedFinTxset = dataContext.merge(thisFinTxset);
                    thisFinTxset = thisTrackedFinTxset;

                    Boolean thisFinTxsetIsChanged = false;

                    if (tmplt_FinTxset1_Id_Type1_IdFieldChk.isChecked()
                            && thisFinTxset != null
                    ) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setType1_Id(tmplt_FinTxset1_Id_Type1_IdField.getValue());
                    }

                    if (tmplt_FinTxset1_Id_GenChan1_IdFieldChk.isChecked()
                            && thisFinTxset != null
                    ) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setGenChan1_Id(tmplt_FinTxset1_Id_GenChan1_IdField.getValue());
                    }

                    if (tmplt_FinTxset1_Id_FinHow1_IdFieldChk.isChecked()
                            && thisFinTxset != null
                    ) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setFinHow1_Id(tmplt_FinTxset1_Id_FinHow1_IdField.getValue());
                    }

                    if (tmplt_FinTxset1_Id_WhatText1FieldChk.isChecked()
                            && thisFinTxset != null
                    ) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setWhatText1(tmplt_FinTxset1_Id_WhatText1Field.getValue());
                    }

                    if (tmplt_FinTxset1_Id_FinWhat1_IdFieldChk.isChecked()
                            && thisFinTxset != null
                    ) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setFinWhat1_Id(tmplt_FinTxset1_Id_FinWhat1_IdField.getValue());
                    }

                    if (tmplt_FinTxset1_Id_WhyText1FieldChk.isChecked()
                            && thisFinTxset != null
                    ) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setWhyText1(tmplt_FinTxset1_Id_WhyText1Field.getValue());
                    }

                    if (tmplt_FinTxset1_Id_FinWhy1_IdFieldChk.isChecked()
                            && thisFinTxset != null
                    ) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setFinWhy1_Id(tmplt_FinTxset1_Id_FinWhy1_IdField.getValue());
                    }

                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxact,thisFinTxact).");
                    //metadataTools.copy(thisFinTxact, thisFinTxact);

                    //logger.debug(logPrfx + " --- executing dataContext.commit().");
                    //dataContext.commit();

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }

                }


            }
        });
        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateFinTxactHelper() {
        String logPrfx = "updateFinTxactHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactsDl.load().");
            finTxactsDl.load();

            List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinTxacts.forEach(thisFinTxact -> {
                //UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                if (thisFinTxact != null) {
                    UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                    thisFinTxact = thisTrackedFinTxact;

                    Boolean thisFinTxactIsChanged = false;

                    thisFinTxactIsChanged = updateId2Dup(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                finTxactsDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinTxacts);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1A_IdDtGE_DecMonBtn")
    public void onUpdateFilterConfig1A_IdDtGE_DecMonBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_DecMonBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> propFilterCmpnt_IdDtGE = (DateField<LocalDate>) filterConfig1A_IdDtGE.getValueComponent();
        LocalDate idDtGE = propFilterCmpnt_IdDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.minusMonths(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_IdDtGE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()){
            DateField<LocalDate> propFilterCmpnt_IdDtLE = (DateField<LocalDate>) filterConfig1A_IdDtLE.getValueComponent();
            LocalDate idDtLE = propFilterCmpnt_IdDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.minusMonths(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_IdDtLE.setValue(idDtLE);
                filter.apply();
            }
        };
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdDtGE_IncMonBtn")
    public void onUpdateFilterConfig1A_IdDtGE_IncMonBtnBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_IncMonBtnBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> propFilterCmpnt_IdDtGE = (DateField<LocalDate>) filterConfig1A_IdDtGE.getValueComponent();
        LocalDate idDtGE = propFilterCmpnt_IdDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.plusMonths(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_IdDtGE.setValue(idDtGE);
            filter.apply();
        }
        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()) {
            DateField<LocalDate> propFilterCmpnt_IdDtLE = (DateField<LocalDate>) filterConfig1A_IdDtLE.getValueComponent();
            LocalDate idDtLE = propFilterCmpnt_IdDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.plusMonths(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_IdDtLE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdDtGE_DecDayBtn")
    public void onUpdateFilterConfig1A_IdDtGE_DecDayBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_DecDayBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> cmpnt_IdDtGE = (DateField<LocalDate>) filterConfig1A_IdDtGE.getValueComponent();
        LocalDate idDtGE = cmpnt_IdDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.minusDays(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_IdDtGE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()) {
            DateField<LocalDate> cmpnt_IdDtLE = (DateField<LocalDate>) filterConfig1A_IdDtLE.getValueComponent();
            LocalDate idDtLE = cmpnt_IdDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.minusDays(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_IdDtLE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdDtGE_IncDayBtn")
    public void onUpdateFilterConfig1A_IdDtGE_IncDayClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdDtGE_IncDayClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDate> cmpnt_IdDtGE = (DateField<LocalDate>) filterConfig1A_IdDtGE.getValueComponent();
        LocalDate idDtGE = cmpnt_IdDtGE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.plusDays(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_IdDtGE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdDtLE_SyncChk.isChecked()) {
            DateField<LocalDate> cmpnt_IdDtLE = (DateField<LocalDate>) filterConfig1A_IdDtLE.getValueComponent();
            LocalDate idDtLE = cmpnt_IdDtLE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.plusDays(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_IdDtLE.setValue(idDtLE);
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
                filterConfig1B_IdDtGE.clear();
                break;

            case 1:  // Set to match current row
                List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getIdX() != null && thisFinTxact.getIdDt().getDate1() != null){
                        filterConfig1B_IdDtGE.setValue(thisFinTxact.getIdDt().getDate1());
                        filterConfig1B_IdDtGE.apply();
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
                filterConfig1B_IdX.clear();
                break;

            case 1:  // Set to match current row
                List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getIdX() != null){
                        filterConfig1B_IdX.setValue(thisFinTxact.getIdX());
                        filterConfig1B_IdX.apply();
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

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxact.getIdX();
                    Integer idX = 0;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdX(" + (idX) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxact.getIdX();
                    Integer idX = thisFinTxact.getIdX() == null || thisFinTxact.getIdX() == 0 || thisFinTxact.getIdX() == 1 ? 0 : thisFinTxact.getIdX() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdX(" + (idX) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdXBtn")
    public void onIncIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxact.getIdX();
                    Integer idX = (thisFinTxact.getIdX() == null ? 0 : thisFinTxact.getIdX()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdX(" + (idX) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("maxIdXBtn")
    public void onMaxIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from ampata_UsrNode e"
                            + " where e.className = 'FinTxact'"
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

                    Integer idX_ = thisFinTxact.getIdX();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdX(" + (idX) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }

            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("zeroIdYBtn")
    public void onZeroIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxact.getIdY();
                    Integer idY = 0;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdY(" + (idY) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");    }

    @Subscribe("decIdYBtn")
    public void onDecIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxact.getIdY();
                    Integer idY = thisFinTxact.getIdY() == null || thisFinTxact.getIdY() == 0 || thisFinTxact.getIdY() == 1 ? 0 : thisFinTxact.getIdY() - 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdY(" + (idY) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdYBtn")
    public void onIncIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxact.getIdY();
                    Integer idY = (thisFinTxact.getIdY() == null ? 0 : thisFinTxact.getIdY()) + 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdY(" + (idY) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("maxIdYBtn")
    public void onMaxIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getIdDt() != null ? thisFinTxact.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY) from ampata_UsrNode e"
                            + " where e.className = 'FinTxact'"
                            + " and e.idDt.date1 = :idDt1";
                    try {
                        idYMax = dataManager.loadValue(idYMaxQry, Integer.class)
                                .store("main")
                                .parameter("idDt1", idDt1)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idYMaxQry error: " + e.getMessage());
                        idYMax = null;
                    }
                    logger.debug(logPrfx + " --- idYMaxQry result: " + idYMax + "");

                    Integer idY_ = thisFinTxact.getIdY();
                    Integer idY = idYMax == null ? 0 : idYMax;

                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxact.setIdY(" + (idY) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                    String id2_ = thisFinTxact.getId2();
                    String id2 = thisFinTxact.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxact.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                        thisFinTxactIsChanged = true;
                    }

                    thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                thisFinTxact = dataContext.merge(thisFinTxact);
                boolean isChanged = false;

                Integer finTxsetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
                isChanged = updateCalcVals(thisFinTxact, finTxsetOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinTxacts);
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

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                thisFinTxact = dataContext.merge(thisFinTxact);
                boolean isChanged = false;

                updateId2(thisFinTxact);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinTxacts);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemIdPartsBtn")
    public void onUpdateColItemIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                thisFinTxact = dataContext.merge(thisFinTxact);
                boolean isChanged = false;

                isChanged = updateIdParts(thisFinTxact);
            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxacts);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tmplt_FinTxset1_Id_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxset1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxset1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxset1_Id_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxset1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxset1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }



    @Install(to = "tmplt_FinTxset1_EI1_RoleField", subject = "enterPressHandler")
    private void tmplt_FinTxset1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxset1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateTxsetBtn")
    public void onUpdateTxsetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTxsetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer finTxsetOption = 3; // update finTxset to match this finTxact

        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                thisFinTxactIsChanged = updateFinTxset1_Id(thisFinTxact, finTxsetOption) || thisFinTxactIsChanged;
                String id2_ = thisFinTxact.getId2();
                String id2 = thisFinTxact.getId2Calc();
                if (!Objects.equals(id2_, id2)){
                    thisFinTxact.setId2(id2);
                    logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                    thisFinTxactIsChanged = true;
                }

                thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                if (thisFinTxactIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                    //dataManager.save(thisFinTxact);
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("linkTxsetBtn")
    public void onLinkTxsetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onLinkTxsetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer finTxsetOption;
        if (!linkTxsetAutoCreateChk.isChecked()){
            finTxsetOption = 1;
        }else{
            finTxsetOption = 2;
        }

        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                thisFinTxactIsChanged = updateFinTxset1_Id(thisFinTxact, finTxsetOption) || thisFinTxactIsChanged;
                String id2_ = thisFinTxact.getId2();
                String id2 = thisFinTxact.getId2Calc();
                if (!Objects.equals(id2_, id2)){
                    thisFinTxact.setId2(id2);
                    logger.debug(logPrfx + " --- thisFinTxact.setId2(" + (id2) + ")");
                    thisFinTxactIsChanged = true;
                }

                thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                if (thisFinTxactIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                    //dataManager.save(thisFinTxact);
                }
            }
        });

        updateFinTxactHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateIdParts(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxsetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxsetOption == null){
            finTxsetOption = 0;}

        updateCalcVals(thisFinTxact, finTxsetOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinTxact);
            updateId2Dup(thisFinTxact);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinTxact);
        updateId2Cmp(thisFinTxact);
        updateId2Dup(thisFinTxact);

        logger.debug(logPrfx + " --- id2: " + thisFinTxact.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxact);
        updateId2Cmp(thisFinTxact);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinTxact.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
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
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactTypesDl.load();
        logger.debug(logPrfx + " --- called finTxactTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxset1_EI1_RoleField", subject = "enterPressHandler")
    private void finTxset1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxset1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_EI1_RoleFieldListBtn")
    public void onUpdateFinTxset1_EI1_RoleFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_EI1_RoleFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinTxset1_EI1_RoleList();

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

    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateIdDt(thisFinTxact);
            updateId2Calc(thisFinTxact);
            updateFinTxset1_Id2Trgt(thisFinTxact);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateBeg1Ts1FieldBtn")
    public void onUpdateBeg1Ts1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg1Ts1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg1(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxact);
            updateFinTxset1_Id2Trgt(thisFinTxact);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdX(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idYField")
    public void onIdYFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdYFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxact);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateIdYFieldBtn")
    public void onUpdateIdYFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdYFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdY(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenDocVer1_IdFieldListBtn")
    public void onUpdateGenDocVer1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenDocVer1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinTxset1_Id_Desc1FieldBtn")
    public void onUpdateFinTxset1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxset1_Id_Desc1(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxset1_IdFieldBtn")
    public void onUpdateFinTxset1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxsetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxsetOption == null){
            finTxsetOption = 0;}
        updateFinTxset1_Id(thisFinTxact, finTxsetOption);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxset1_IdFieldListBtn")
    public void onUpdateFinTxset1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxsetsDl.load();
        logger.debug(logPrfx + " --- called finTxsetsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id2TrgtBtn")
    public void onUpdateFinTxset1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxset1_Id2Trgt(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxset1_Id_Type1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_Type1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxsetTypesDl.load();
        logger.debug(logPrfx + " --- called finTxsetTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_GenChan1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_How1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_How1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finHowsDl.load();
        logger.debug(logPrfx + " --- called finHowsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxset1_Id_WhatText1Field", subject = "enterPressHandler")
    private void finTxset1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxset1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_WhatText1FieldListBtn")
    public void onUpdateFinTxset1_Id_WhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_WhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_What1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_What1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_What1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxset1_Id_WhyText1Field", subject = "enterPressHandler")
    private void finTxset1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxset1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_WhyText1FieldListBtn")
    public void onUpdateFinTxset1_Id_WhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_WhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_Why1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_Why1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_Why1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxset1_Id_DescPat1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_DescPat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_DescPat1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genPatsDl.load();
        logger.debug(logPrfx + " --- called genPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItmsDl.load();
        logger.debug(logPrfx + " --- called finTxactItmsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_Desc1FinTxactItm2_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_Desc1FinTxactItm2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_Desc1FinTxactItm2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItmsDl.load();
        logger.debug(logPrfx + " --- called finTxactItmsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull UsrNode thisFinTxact, Integer finTxsetOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactCalcVals(thisFinTxact, finTxsetOption) || isChanged;
        isChanged = updateFinTxsetCalcVals(thisFinTxact) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactCalcVals(@NotNull UsrNode thisFinTxact, Integer finTxsetOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateIdDt(thisFinTxact) || isChanged;
        isChanged = updateId2Calc(thisFinTxact) || isChanged;
        isChanged = updateId2Cmp(thisFinTxact) || isChanged;
        isChanged = updateId2Dup(thisFinTxact) || isChanged;

        isChanged = updateDesc1(thisFinTxact) || isChanged;
        isChanged = updateFinTxactItms1_IdCntCalc(thisFinTxact)  || isChanged;
        isChanged = updateFinTxactItms1_AmtDebtSumCalc(thisFinTxact)  || isChanged;
        isChanged = updateFinTxactItms1_AmtCredSumCalc(thisFinTxact)  || isChanged;

        isChanged = updateFinTxactItms1_AmtEqCalc(thisFinTxact)  || isChanged;

        isChanged = updateFinTxset1_Id2Trgt(thisFinTxact) || isChanged;
        isChanged = updateFinTxset1_Id(thisFinTxact, finTxsetOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxsetCalcVals(@NotNull UsrNode thisFinTxact) {
        String logPrfx = "updateFinTxsetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxset Object
        isChanged = updateFinTxset1_Id_Desc1(thisFinTxact) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNode thisFinTxact) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateBeg1(thisFinTxact) || isChanged;
        isChanged = updateBeg2(thisFinTxact) || isChanged;
        isChanged = updateIdX(thisFinTxact)  || isChanged;
        isChanged = updateIdY(thisFinTxact)  || isChanged;
        isChanged = updateIdDt(thisFinTxact) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinTxact.getId2();
        String id2 = thisFinTxact.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinTxact.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(UsrNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinTxact.getId2Calc();
        String id2Calc = thisFinTxact.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinTxact.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinTxact.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinTxact.getId2(),thisFinTxact.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinTxact.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxact.getId2Dup();
        if (thisFinTxact.getId2() != null) {
            String id2Qry = "select count(e) from ampata_UsrNode e where e.className = 'FinTxact' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinTxact.getId())
                        .parameter("id2", thisFinTxact.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinTxact.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinTxact.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(UsrNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        if (thisFinTxact != null) {
            String desc1_ = thisFinTxact.getDesc1();

            //thisType
            String thisType = "";
            if (thisFinTxact.getType1_Id() != null) {
                thisType = Objects.toString(thisFinTxact.getType1_Id().getId2(), "");
            }
            logger.debug(logPrfx + " --- thisType: " + thisType);

            UsrNode desc1FinTxactItm1 = thisFinTxact.getDesc1FinTxactItm1_Id() == null
                    ? findFirstFinTxactItmLikeId2(thisFinTxact.getId2() + "/%")
                    : thisFinTxact.getDesc1FinTxactItm1_Id();

            //thisAmt
            String thisAmt = "";
            if (desc1FinTxactItm1 != null) {
                if (desc1FinTxactItm1.getAmtDebt() != null) {
                    thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtDebt();
                } else if (desc1FinTxactItm1.getAmtCred() != null) {
                    thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtCred();
                }
                if (desc1FinTxactItm1.getSysFinCurcy1_Id() != null) {
                    thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm1.getSysFinCurcy1_Id().getId2(), "");
                    thisAmt = thisAmt.trim();
                }
                if (!thisAmt.equals("")) {
                    thisAmt = thisAmt.trim();
                }

            }
            logger.debug(logPrfx + " --- thisAmt: " + thisAmt);


            String thisDocVer = "";
            if (thisFinTxact.getGenDocVer1_Id() != null) {
                thisDocVer = Objects.toString(thisFinTxact.getGenDocVer1_Id().getId2());
            }
            if (!thisDocVer.equals("")) {
                thisDocVer = "doc ver " + thisDocVer;
            }
            logger.debug(logPrfx + " --- thisDocVer: " + thisDocVer);

            String thisTag = "";
            String thisTag1 = "";
            if (thisFinTxact.getGenTag1_Id() != null) {
                thisTag1 = Objects.toString(thisFinTxact.getGenTag1_Id().getId2());
            }
            String thisTag2 = "";
            if (thisFinTxact.getGenTag1_Id() != null) {
                thisTag2 = Objects.toString(thisFinTxact.getGenTag2_Id().getId2());
            }
            String thisTag3 = "";
            if (thisFinTxact.getGenTag1_Id() != null) {
                thisTag3 = Objects.toString(thisFinTxact.getGenTag3_Id().getId2());
            }
            String thisTag4 = "";
            if (thisFinTxact.getGenTag1_Id() != null) {
                thisTag4 = Objects.toString(thisFinTxact.getGenTag4_Id().getId2());
            }
            if (!(thisTag1 + thisTag2 + thisTag3 + thisTag4).equals("")) {
                thisTag = "tag [" + String.join(",", thisTag1, thisTag2, thisTag3, thisTag4) + "]";
            }
            logger.debug(logPrfx + " --- thisTag: " + thisTag);

            List<String> list = Arrays.asList(
                    thisType
                    , thisDocVer
                    , thisTag);

            String desc1 = list.stream().filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining(" "));

            if (!Objects.equals(desc1_, desc1)){
                thisFinTxact.setDesc1(desc1);
                logger.debug(logPrfx + " --- desc1: " + desc1);
                isChanged = true;
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxset1_Id_Desc1(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxset = thisFinTxact.getFinTxset1_Id();

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

                UsrNode desc1FinTxactItm1 = thisFinTxset.getDesc1FinTxactItm1_Id() == null
                        ? findFirstFinTxactItmLikeId2(thisFinTxset.getId2() + "/%")
                        : thisFinTxset.getDesc1FinTxactItm1_Id();


                //thisAmt
                String thisAmt = "";
                if (desc1FinTxactItm1 != null){
                    if (desc1FinTxactItm1.getAmtDebt() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtDebt();
                    }else if (desc1FinTxactItm1.getAmtCred() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtCred();
                    }
                    if (desc1FinTxactItm1.getSysFinCurcy1_Id() != null) {
                        thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm1.getSysFinCurcy1_Id().getId2(), "");
                        thisAmt = thisAmt.trim();
                    }
                    if (thisType.contains("Exch")) {
                        UsrNode desc1FinTxactItm2 = thisFinTxset.getDesc1FinTxactItm2_Id() == null
                                ? findFirstFinTxactItmLikeId2(thisFinTxset.getId2() + "/Y01/%")
                                : thisFinTxset.getDesc1FinTxactItm2_Id();

                        if (desc1FinTxactItm2 != null) {
                            thisAmt = thisAmt + " -> ";
                            if (desc1FinTxactItm2.getAmtDebt() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtDebt();
                            }else if (desc1FinTxactItm2.getAmtCred() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtCred();
                            }
                            if (desc1FinTxactItm2.getSysFinCurcy1_Id() != null) {
                                thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm2.getSysFinCurcy1_Id().getId2(), "");
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

                if (!Objects.equals(desc1_, desc1)){
                    thisFinTxset.setDesc1(desc1);
                    logger.debug(logPrfx + " --- desc1: " + desc1);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdDt(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateIdDt();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    private Boolean updateBeg1(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateBeg1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateBeg1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg2(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateBeg2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateBeg2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdX(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateIdX();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdY(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateIdY";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateIdY();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxset1_Id(@NotNull UsrNode thisFinTxact, Integer finTxsetOption) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        if (thisFinTxact != null){
            UsrNode finTxset1_Id_ = thisFinTxact.getFinTxset1_Id();
            UsrNode finTxset1_Id;
            // Update finTxact
            switch (finTxsetOption) {
                case 1: // Link to Exist Txset
                    finTxset1_Id = findFinTxsetById2(thisFinTxact.getFinTxset1_Id2Trgt());
                    if (!Objects.equals(finTxset1_Id_, finTxset1_Id)){
                        isChanged = true;
                        thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                    }
                    break;
                case 2: // Link to Exist/New Txset
                    finTxset1_Id = findFinTxsetById2(thisFinTxact.getFinTxset1_Id2Trgt());
                    if (finTxset1_Id == null) {
                        finTxset1_Id = createFinTxsetFrFinTxact(thisFinTxact);
                    }
                    if (!Objects.equals(finTxset1_Id_, finTxset1_Id)){
                        thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                        isChanged = true;
                    }
                    break;
                case 3: // Update Exist Txact
                    Boolean thisFinTxsetIsChanged = false;
                    UsrNode thisFinTxset = thisFinTxact.getFinTxset1_Id();
                    if (thisFinTxset != null) {
                        if (!Objects.equals(thisFinTxset.getIdDt().getDate1(), thisFinTxact.getIdDt().getDate1())) {
                            thisFinTxset.getBeg1().setTs1(thisFinTxact.getIdDt().getDate1().atStartOfDay());
                            thisFinTxsetIsChanged = true;
                        }
                        if (!Objects.equals(thisFinTxset.getIdX(), thisFinTxact.getIdX())) {
                            thisFinTxset.setIdX(thisFinTxact.getIdX());
                            thisFinTxsetIsChanged = true;
                        }
                        if (thisFinTxsetIsChanged) {
                            thisFinTxset.updateIdDt();
                            thisFinTxset.setId2Calc(thisFinTxset.getId2CalcFrFields());
                            thisFinTxset.setId2(thisFinTxset.getId2Calc());
                            dataManager.save(thisFinTxset);
                        }
                    }
                    break;
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxset1_Id2Trgt(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String finTxset1_Id2Trgt_ = thisFinTxact.getFinTxset1_Id2Trgt();
        String finTxset1_Id2Trgt = thisFinTxact.getId2CalcFrFields().substring(0, 16);
        if(!Objects.equals(finTxset1_Id2Trgt_, finTxset1_Id2Trgt)){
            isChanged = true;
            thisFinTxact.setFinTxset1_Id2Trgt(finTxset1_Id2Trgt);
            logger.debug(logPrfx + " --- finTxset1_Id2Trgt: " + finTxset1_Id2Trgt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private UsrNode findFinTxsetById2(@NotNull String finTxset_Id2) {
        String logPrfx = "findFinTxsetById2";
        logger.trace(logPrfx + " --> ");

        if (finTxset_Id2 == null) {
            logger.debug(logPrfx + " --- finTxset_Id2 is null, finTxset_Id2.");
            notifications.create().withCaption("finTxset_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_UsrNode e where e.className = 'FinTxset' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxset_Id2);

        UsrNode finTxset1_Id = null;
        try {
            finTxset1_Id = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", finTxset_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            // notifications.create().withCaption("FinTxset with id2: " + finTxset1_Id2Trgt + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finTxset1_Id;

    }

    private UsrNode findFirstFinTxactItmLikeId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFirstFinTxactItmLikeId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_UsrNode e where e.className = 'FinTxactItm' and e.id2 like :id2 order by e.id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        UsrNode finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(UsrNode.class)
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

    private UsrNode createFinTxsetFrFinTxact(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "createFinTxsetFrFinTxact";
        logger.trace(logPrfx + " --> ");

        String finTxset_Id2 = thisFinTxact.getFinTxset1_Id2Trgt();
        if (finTxset_Id2 == null) {
            logger.debug(logPrfx + " --- finTxset_Id2 is null, finTxset_Id2.");
            notifications.create().withCaption("finTxset_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_UsrNode e where e.className = 'FinTxset' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxset_Id2);

        try {
            UsrNode finTxset = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", finTxset_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            logger.trace(logPrfx + " <-- ");
            return finTxset;

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

            UsrNode newFinTxset = dataManager.create(UsrNode.class);
            newFinTxset.setClassName("FinTxset");

            HasTmst beg1 = dataManager.create(HasTmst.class);
            beg1.setTs1(thisFinTxact.getIdDt().getDate1().atStartOfDay());
            newFinTxset.setBeg1(beg1);
            newFinTxset.updateIdDt();

            newFinTxset.setIdX(thisFinTxact.getIdX());
            newFinTxset.setIdY(thisFinTxact.getIdY());

            newFinTxset.setId2Calc(newFinTxset.getId2CalcFrFields());
            newFinTxset.setId2(newFinTxset.getId2Calc());


            UsrNode savedFinTxset = dataManager.save(newFinTxset);

            UsrNode mergedFinTxset = dataContext.merge(savedFinTxset);
            logger.debug(logPrfx + " --- created FinTxact id: " + mergedFinTxset.getId());
            notifications.create().withCaption("Created FinTxact with id2:" + mergedFinTxset.getId2()).show();

            logger.trace(logPrfx + " <-- ");
            return mergedFinTxset;
        }
    }


    private void reloadWhatText1List(){
        String logPrfx = "reloadWhatText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whatText1"
                + " from ampata_UsrNode e"
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


        tmplt_FinTxset1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxset1_Id_WhatText1Field.setOptionsList()");

        finTxset1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxset1_Id_WhatText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadWhyText1List(){
        String logPrfx = "reloadWhyText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whyText1"
                + " from ampata_UsrNode e"
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

        tmplt_FinTxset1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxset1_Id_WhyText1Field.setOptionsList()");

        finTxset1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finFinTxset1_Id_WhyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxset1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTxset1_EI1_Role"
                + " from ampata_UsrNode e"
                + " where e.className = 'FinTxact'"
                + " and e.finTxset1_EI1_Role IS NOT NULL"
                + " order by e.finTxset1_EI1_Role"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);

        List<String> roles = null;
        try {
            roles = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + roles.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_FinTxset1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called tmplt_FinTxset1_EI1_RoleField.setOptionsList()");

        finTxset1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finTxset1_EI1_RoleField.setOptionsList()");

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
        String idXMaxQry = "select max(e.idX) from ampata_UsrNode e"
                + " where e.className = 'FinTxact'"
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
        String idXCntIsNullQry = "select count(e) from ampata_UsrNode e"
                + " where e.className = 'FinTxact'"
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

    private Integer getIdYMax(LocalDate idDt1, Integer idX) {
        String logPrfx = "getIdYMax";
        logger.trace(logPrfx + " --> ");

        Integer idY, idYMax = 0;
        String idYMaxQry = "select max(e.idY) from ampata_UsrNode e"
                + " where e.className = 'FinTxact'"
                + " and e.idDt.date1 = :idDt1"
                + " and e.idX = :idX";
        try {
            idYMax = dataManager.loadValue(idYMaxQry, Integer.class)
                    .store("main")
                    .parameter("idDt1", idDt1)
                    .parameter("idX", idX)
                    .one();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- idYMaxQry error: " + e.getMessage());
            notifications.create().withCaption("idYMaxQry error: " + e.getMessage()).show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }
        logger.debug(logPrfx + " --- idYMaxQry result: " + idYMax + "");

        Integer idYCntIsNull = null;
        String idYCntIsNullQry = "select count(e) from ampata_UsrNode e"
                + " where e.className = 'FinTxact'"
                + " and e.idDt.date1 = :idDt1"
                + " and e.idX = :idX"
                + " and e.idY is null";
        try {
            idYCntIsNull = dataManager.loadValue(idYCntIsNullQry, Integer.class)
                    .store("main")
                    .parameter("idDt1", idDt1)
                    .parameter("idX", idX)
                    .one();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- idYCntIsNullQry error: " + e.getMessage());
            notifications.create().withCaption("idYCntIsNullQry error: " + e.getMessage()).show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }
        logger.debug(logPrfx + " --- idYCntIsNullQry result: " + idYCntIsNull + "");

        if (idYMax == null && idYCntIsNull != 0){
            idY = 1;
        }else{
            idY = idYMax == null ? 0 : idYMax + 1;
        }

        logger.trace(logPrfx + " <-- ");
        return idY;
    }

    @Subscribe("updateFinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateFinTxactItms1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_IdCntCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtDebtSumCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtCredSumCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtCredSumCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtCredSumCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinTxactItms1_AmtEqCalcBoxBtn")
    public void onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtEqCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateFinTxactItms1_FinCurcyEqCalcBoxBtn")
    public void onUpdateFinTxactItms1_FinCurcyEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_FinCurcyEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_FinCurcyEqCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateFinTxactItms1_IdCntCalc(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer idCntCalc_ = thisFinTxact.getFinTxactItms1_IdCntCalc();
        Integer idCntCalc = null ;
        String qry1 = "select count(e.amtNet) from ampata_UsrNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id";
        try{
            idCntCalc = dataManager.loadValue(qry1,Integer.class)
                    .store("main")
                    .parameter("finTxact1_Id",thisFinTxact)
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
            thisFinTxact.setFinTxactItms1_IdCntCalc(idCntCalc);
            logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    
    private Boolean updateFinTxactItms1_AmtDebtSumCalc(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtDebtSumCalc_ = thisFinTxact.getFinTxactItms1_AmtDebtSumCalc();
        BigDecimal amtDebtSumCalc = null ;
        String qry1 = "select sum(e.amtDebt) from ampata_UsrNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
        try{
            amtDebtSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finTxact1_Id",thisFinTxact)
                    .one();
            if (amtDebtSumCalc == null) {
                amtDebtSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- amtDebtSumCalc: " + amtDebtSumCalc);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- amtDebtSumCalc: null");
        }

        if(!Objects.equals(amtDebtSumCalc_, amtDebtSumCalc)){
            isChanged = true;
            thisFinTxact.setFinTxactItms1_AmtDebtSumCalc(amtDebtSumCalc);
            logger.debug(logPrfx + " --- amtDebtSumCalc: " + amtDebtSumCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactItms1_AmtCredSumCalc(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtCredSumCalc_ = thisFinTxact.getFinTxactItms1_AmtCredSumCalc();
        BigDecimal amtCredSumCalc = null ;
        String qry1 = "select sum(e.amtCred) from ampata_UsrNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
        try{
            amtCredSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finTxact1_Id",thisFinTxact)
                    .one();
            if (amtCredSumCalc == null) {
                amtCredSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- amtCredSumCalc: " + amtCredSumCalc);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- amtCredSumCalc: null");
        }

        if(!Objects.equals(amtCredSumCalc_, amtCredSumCalc)){
            isChanged = true;
            thisFinTxact.setFinTxactItms1_AmtCredSumCalc(amtCredSumCalc);
            logger.debug(logPrfx + " --- amtCredSumCalc: " + amtCredSumCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxactItms1_AmtEqCalc(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_AmtEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean amtEqCalc_ = thisFinTxact.getFinTxactItms1_AmtEqCalc();
        Boolean amtEqCalc = Objects.equals(thisFinTxact.getFinTxactItms1_AmtDebtSumCalc()
                , thisFinTxact.getFinTxactItms1_AmtCredSumCalc());

        if(!Objects.equals(amtEqCalc_, amtEqCalc)){
            isChanged = true;
            thisFinTxact.setFinTxactItms1_AmtEqCalc(amtEqCalc);
            logger.debug(logPrfx + " --- amtEqCalc: " + amtEqCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxactItms1_FinCurcyEqCalc(@NotNull UsrNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_FinCurcyEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean finCurcyEqCalc_ = thisFinTxact.getFinTxactItms1_SysFinCurcyEqCalc();
        Boolean finCurcyEqCalc = false;

        List<UsrNode> finCurcyList = null;
        String qry1 = "select e.finCurcy1_Id from ampata_UsrNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id";
        try{
            finCurcyList = dataManager.loadValue(qry1, UsrNode.class)
                    .store("main")
                    .parameter("finTxact1_Id",thisFinTxact)
                    .list();
            if (finCurcyList == null || finCurcyList.isEmpty()) {
                logger.debug(logPrfx + " --- finCurcyList.size(): 0");
            }else{
                logger.debug(logPrfx + " --- finCurcyList.size(): " + finCurcyList.size());

                String finCurcyFirst_Id2 = finCurcyList.get(0).getId2();
                finCurcyEqCalc = true;
                for (UsrNode finCurcy: finCurcyList){
                    if (Objects.equals(finCurcyFirst_Id2,finCurcy.getId2())){
                        finCurcyEqCalc = false;
                    }
                }
            }
        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- amtCredSumCalc: null");
        }

        if(!Objects.equals(finCurcyEqCalc_, finCurcyEqCalc)){
            isChanged = true;
            thisFinTxact.setFinTxactItms1_SysFinCurcyEqCalc(finCurcyEqCalc);
            logger.debug(logPrfx + " --- finCurcyEqCalc: " + finCurcyEqCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private UsrNode findFinTxactItmById2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFinTxactItmById2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_UsrNode e where e.className = 'FinTxactItm' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        UsrNode finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", finTxactItm_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finTxactItm1_Id;
    }

}