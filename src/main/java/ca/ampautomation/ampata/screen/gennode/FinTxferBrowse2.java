package ca.ampautomation.ampata.screen.gennode;

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
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
// import javax.transaction.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@UiController("ampata_FinTxfer.browse2")
@UiDescriptor("fin-txfer-browse2.xml")
@LookupComponent("table")
public class FinTxferBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<LocalDateTime> propFilter_idTsGE;

    @Autowired
    protected PropertyFilter<LocalDateTime> propFilter_idTsLE;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_genChan1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finAcct1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finDept1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finStmt1_Id;

    @Autowired
    protected DateField<LocalDateTime> tmplt_beg1Ts1Field;

    @Autowired
    protected CheckBox tmplt_beg1Ts1FieldChk;

    @Autowired
    protected TextField<Integer> tmplt_idXField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_idXFieldRdo;

    @Autowired
    protected TextField<Integer> tmplt_idYField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_idYFieldRdo;

    @Autowired
    protected TextField<Integer> tmplt_idZField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_idZFieldRdo;

    @Autowired
    protected EntityComboBox<GenNode> tmplt_finStmt1_IdField;

    @Autowired
    protected CheckBox tmplt_finStmt1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_finStmtItm1_Desc1Field;

    @Autowired
    protected CheckBox tmplt_finStmtItm1_Desc1FieldChk;

    @Autowired
    protected ComboBox<String> tmplt_finStmtItm1_Desc2Field;

    @Autowired
    protected CheckBox tmplt_finStmtItm1_Desc2FieldChk;

    @Autowired
    protected ComboBox<String> tmplt_finStmtItm1_Desc3Field;

    @Autowired
    protected CheckBox tmplt_finStmtItm1_Desc3FieldChk;

    @Autowired
    protected TextField<String> tmplt_finStmtItm1_DescAmtField;

    @Autowired
    protected CheckBox tmplt_finStmtItm1_DescAmtFieldChk;

    @Autowired
    protected TextField<String> tmplt_finStmtItm1_RefIdField;

    @Autowired
    protected CheckBox tmplt_finStmtItm1_RefIdFieldChk;

    @Autowired
    protected EntityComboBox<GenNode> tmplt_finAcct1_IdField;

    @Autowired
    protected CheckBox tmplt_finAcct1_IdFieldChk;

    @Autowired
    protected EntityComboBox<GenNode> tmplt_finDept1_IdField;

    @Autowired
    protected CheckBox tmplt_finDept1_IdFieldChk;

    @Autowired
    protected TextField<BigDecimal> tmplt_amtDebtField;

    @Autowired
    protected CheckBox tmplt_amtDebtFieldChk;

    @Autowired
    protected TextField<BigDecimal> tmplt_amtCredField;

    @Autowired
    protected CheckBox tmplt_amtCredFieldChk;

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
    private GroupTable<GenNode> table;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> finTxfersDc;

    @Autowired
    private DataLoader finTxfersDl;

    @Autowired
    private InstanceContainer<GenNode> finTxferDc;


    private CollectionContainer<GenNodeType> finTxferTypesDc;

    private CollectionLoader<GenNodeType> finTxferTypesDl;


    private CollectionContainer<GenNode> genChansDc;

    private CollectionLoader<GenNode> genChansDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    private CollectionContainer<GenNode> finTxactsDc;

    private CollectionLoader<GenNode> finTxactsDl;


    private CollectionContainer<GenNodeType> finTxactTypesDc;

    private CollectionLoader<GenNodeType> finTxactTypesDl;


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
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag2_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag3_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag4_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTxact1_IdField;

    @Autowired
    private EntityComboBox<GenNodeType> finTxact1_Id_Type1_IdField;

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
    private EntityComboBox<FinFmla> finFmla1_IdField;

    @Autowired
    private EntityComboBox<FinHow> finHow1_IdField;

    @Autowired
    private EntityComboBox<FinHow> finTxact1_Id_FinHow1_IdField;


    @Autowired
    private EntityComboBox<FinWhat> finWhat1_IdField;

    @Autowired
    private EntityComboBox<FinWhat> finTxact1_Id_FinWhat1_IdField;


    @Autowired
    private EntityComboBox<FinWhy> finWhy1_IdField;

    @Autowired
    private EntityComboBox<FinWhy> finTxact1_Id_FinWhy1_IdField;


    @Autowired
    private ComboBox<String> finStmtItm1_Desc1Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc2Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc3Field;


    Logger logger = LoggerFactory.getLogger(FinTxferBrowse2.class);

    Boolean showNotifications = true;

    /*
    InitEvent is sent when the screen controller and all its declaratively defined components are created,
    and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
    are not fully initialized, for example, buttons are not linked with actions.
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        showNotifications = true;

        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Skip", 0);
        map.put("Max+1", 2);
        map.put("", 1);
        tmplt_idXFieldRdo.setOptionsMap(map);
        tmplt_idYFieldRdo.setOptionsMap(map);
        tmplt_idZFieldRdo.setOptionsMap(map);

        tmplt_finStmtItm1_Desc1Field.setNullOptionVisible(true);
        tmplt_finStmtItm1_Desc1Field.setNullSelectionCaption("<null>");
        tmplt_finStmtItm1_Desc2Field.setNullOptionVisible(true);
        tmplt_finStmtItm1_Desc2Field.setNullSelectionCaption("<null>");
        tmplt_finStmtItm1_Desc3Field.setNullOptionVisible(true);
        tmplt_finStmtItm1_Desc3Field.setNullSelectionCaption("<null>");

        finStmtItm1_Desc1Field.setNullOptionVisible(true);
        finStmtItm1_Desc1Field.setNullSelectionCaption("<null>");
        finStmtItm1_Desc2Field.setNullOptionVisible(true);
        finStmtItm1_Desc2Field.setNullSelectionCaption("<null>");
        finStmtItm1_Desc3Field.setNullOptionVisible(true);
        finStmtItm1_Desc3Field.setNullSelectionCaption("<null>");

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
        EntityComboBox<GenNode> propFilterCmpnt_genChan1_Id = (EntityComboBox<GenNode>) propFilter_genChan1_Id.getValueComponent();
        propFilterCmpnt_genChan1_Id.setOptionsContainer(genChansDc);


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
        genTag2_IdField.setOptionsContainer(genTagsDc);
        genTag3_IdField.setOptionsContainer(genTagsDc);
        genTag4_IdField.setOptionsContainer(genTagsDc);


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

        finTxactTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finTxactTypesDl = dataComponents.createCollectionLoader();
        finTxactTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactTypesDl.setFetchPlan(finTxactTypesFp);
        finTxactTypesDl.setContainer(finTxactTypesDc);
        finTxactTypesDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_Type1_IdField.setOptionsContainer(finTxactTypesDc);


        finStmtsDc = dataComponents.createCollectionContainer(GenNode.class);
        finStmtsDl = dataComponents.createCollectionLoader();
        finStmtsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtsDl.setFetchPlan(finStmtsFp);
        finStmtsDl.setContainer(finStmtsDc);
        finStmtsDl.setDataContext(getScreenData().getDataContext());

        tmplt_finStmt1_IdField.setOptionsContainer(finStmtsDc);
        finStmt1_IdField.setOptionsContainer(finStmtsDc);
        EntityComboBox<GenNode> propFilterCmpnt_finStmt1_Id = (EntityComboBox<GenNode>) propFilter_finStmt1_Id.getValueComponent();
        propFilterCmpnt_finStmt1_Id.setOptionsContainer(finStmtsDc);


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

        tmplt_finAcct1_IdField.setOptionsContainer(finAcctsDc);
        finAcct1_IdField.setOptionsContainer(finAcctsDc);
        EntityComboBox<GenNode> propFilterCmpnt_finAcct1_Id = (EntityComboBox<GenNode>) propFilter_finAcct1_Id.getValueComponent();
        propFilterCmpnt_finAcct1_Id.setOptionsContainer(finAcctsDc);


        finDeptsDc = dataComponents.createCollectionContainer(GenNode.class);
        finDeptsDl = dataComponents.createCollectionLoader();
        finDeptsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinDept' order by e.id2");
        FetchPlan finDeptsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finDeptsDl.setFetchPlan(finDeptsFp);
        finDeptsDl.setContainer(finDeptsDc);
        finDeptsDl.setDataContext(getScreenData().getDataContext());

        tmplt_finDept1_IdField.setOptionsContainer(finDeptsDc);
        finDept1_IdField.setOptionsContainer(finDeptsDc);
        EntityComboBox<GenNode> propFilterCmpnt_finDept1_Id = (EntityComboBox<GenNode>) propFilter_finDept1_Id.getValueComponent();
        propFilterCmpnt_finDept1_Id.setOptionsContainer(finDeptsDc);


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
        finTxact1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);


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


        logger.trace(logPrfx + " <-- ");


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

        GenNode thisFinTxfer = event.getEntity();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfer.setClassName("FinTxfer");
        logger.debug(logPrfx + " --- className: FinTxfer");

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

    @Subscribe(id = "finTxfersDc", target = Target.DATA_CONTAINER)
    public void onFinTxfersDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinTxfersDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = event.getItem();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
//            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxfer.setClassName("FinTxfer");
        logger.debug(logPrfx + " --- className: FinTxfer");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "table.[idTs.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }



    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");

        finDeptsDl.load();
        logger.debug(logPrfx + " --- called finDeptsDl.load() ");

        reloadFinStmtItm1_Desc1List();
        reloadFinStmtItm1_Desc2List();
        reloadFinStmtItm1_Desc3List();

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Gen_Node_Pr_Upd()");
        repo.execGenNodePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Gen_Node_Pr_Upd()");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txfer_Pr_Upd()");
        repo.execFinTxferPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txfer_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finTxfersDl.load()");
        finTxfersDl.load();
        logger.debug(logPrfx + " --- finished finTxfersDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("splitBtn")
    public void onSplitBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSplitBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinTxfers.forEach(orig -> {

            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setIdZ(copy.getIdZ() == null ? 1 : copy.getIdZ() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            copy.setFinAcct1_Id(null);

            copy.setFinStmt1_Id(null);
            copy.setFinStmtItm1_Desc1(null);
            copy.setFinStmtItm1_Desc2(null);
            copy.setFinStmtItm1_Desc3(null);
            copy.setFinStmtItm1_DescAmt(null);
            copy.setFinStmtItm1_RefId(null);


            if (tmplt_finAcct1_IdFieldChk.isChecked()){
                copy.setFinAcct1_Id(tmplt_finAcct1_IdField.getValue());}

            if (tmplt_finDept1_IdFieldChk.isChecked()){
                copy.setFinDept1_Id(tmplt_finDept1_IdField.getValue());}

            if (copy.getAmtDebt() != null) {
                copy.setAmtCred(copy.getAmtDebt());
                copy.setAmtDebt(null);
            }else if (copy.getAmtCred() != null){
                copy.setAmtDebt(copy.getAmtCred());
                copy.setAmtCred(null);
            }

            updateCalcVals(copy);

            GenNode savedCopy = dataManager.save(copy);
            finTxfersDc.getMutableItems().add(savedCopy);
            logger.debug("Split FinTxfer " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        table.setSortable(false);
        table.setSortable(true);
        table.setSelected(sels);
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinTxfers.forEach(orig -> {
            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDateTime idTs1 = copy.getIdTs() != null ? copy.getIdTs().getTs1() : null;
            if (tmplt_beg1Ts1FieldChk.isChecked()) {
                idTs1 = tmplt_beg1Ts1Field.getValue();
                copy.getBeg1().setTs1(idTs1);
                updateIdTs(copy);
            }

            Integer idX = copy.getIdX();
            if (tmplt_idXFieldRdo.getValue() != null){
                // Set
                if (tmplt_idXFieldRdo.getValue() == 1){
                    idX = tmplt_idXField.getValue();
                    copy.setIdX(idX);
                }
                // Max
                else if (tmplt_idXFieldRdo.getValue() == 2
                    && idTs1 != null) {

                    Integer idXMax;
                    String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                    + " where e.className = 'FinTxfer'"
                    + " and e.idTs.ts1 = :idTs1";
                    try {
                        idXMax = dataManager.loadValue(idXMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idXMaxQry error: " + e.getMessage());
                        notifications.create().withCaption("idXMaxQry error: " + e.getMessage()).show();
                        logger.trace(logPrfx + " <-- ");
                        return;
                    }
                    logger.debug(logPrfx + " --- idXMaxQry result: " + idXMax + "");

                    Integer idXCntIsNull = null;
                    String idXCntIsNullQry = "select count(e) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1"
                            + " and e.idX is null";
                    try {
                        idXCntIsNull = dataManager.loadValue(idXCntIsNullQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .one();
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idXCntIsNullQry error: " + e.getMessage());
                        notifications.create().withCaption("idXCntIsNullQry error: " + e.getMessage()).show();
                        logger.trace(logPrfx + " <-- ");
                        return;
                    }
                    logger.debug(logPrfx + " --- idXCntIsNullQry result: " + idXCntIsNull + "");

                    if (idXMax == null && idXCntIsNull != 0){
                        idX = 1;
                    }else{
                        idX = idXMax == null ? 0 : idXMax + 1;
                    }
                    copy.setIdX(idX);
                }
            }

            Integer idY = copy.getIdY();
            if (tmplt_idYFieldRdo.getValue() != null) {
                // Set
                if (tmplt_idYFieldRdo.getValue() == 1) {
                    idY = tmplt_idYField.getValue();
                    copy.setIdY(idY);
                }
                // Max
                else if (tmplt_idYFieldRdo.getValue() == 2
                    && idTs1 != null) {

                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1"
                            + " and e.idX = :idX";
                    try {
                        idYMax = dataManager.loadValue(idYMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .parameter("idX", idX)
                                .one();
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idYMaxQry error: " + e.getMessage());
                        notifications.create().withCaption("idYMaxQry error: " + e.getMessage()).show();
                        logger.trace(logPrfx + " <-- ");
                        return;
                    }
                    logger.debug(logPrfx + " --- idYMaxQry result: " + idYMax + "");

                    Integer idYCntIsNull = null;
                    String idYCntIsNullQry = "select count(e) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1"
                            + " and e.idX = :idX"
                            + " and e.idY is null";
                    try {
                        idYCntIsNull = dataManager.loadValue(idYCntIsNullQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .parameter("idX", idX)
                                .one();
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idYCntIsNullQry error: " + e.getMessage());
                        notifications.create().withCaption("idYCntIsNullQry error: " + e.getMessage()).show();
                        logger.trace(logPrfx + " <-- ");
                        return;
                    }
                    logger.debug(logPrfx + " --- idYCntIsNullQry result: " + idYCntIsNull + "");

                    if (idYMax == null && idYCntIsNull != 0){
                        idY = 1;
                    }else{
                        idY = idYMax == null ? 0 : idYMax + 1;
                    }
                    copy.setIdY(idY);
                }

            }

            Integer idZ = copy.getIdZ();
            if (tmplt_idZFieldRdo.getValue() != null) {
                // Set
                if (tmplt_idZFieldRdo.getValue() == 1) {
                    idZ = tmplt_idZField.getValue();
                    copy.setIdZ(idZ);
                }
                // Max
                else if (tmplt_idZFieldRdo.getValue() == 2
                        && idTs1 != null) {

                    Integer idZMax = 0;
                    String idZMaxQry = "select max(e.idZ) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1"
                            + " and e.idX = :idX"
                            + " and e.idY = :idY";
                    try {
                        idZMax = dataManager.loadValue(idZMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .parameter("idX", idX)
                                .parameter("idY", idY)
                                .one();
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idZMaxQry error: " + e.getMessage());
                        notifications.create().withCaption("idZMaxQry error: " + e.getMessage()).show();
                        logger.trace(logPrfx + " <-- ");
                        return;
                    }
                    logger.debug(logPrfx + " --- idZMaxQry result: " + idZMax + "");

                    Integer idZCntIsNull = null;
                    String idZCntIsNullQry = "select count(e) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1"
                            + " and e.idX = :idX"
                            + " and e.idX = :idY"
                            + " and e.idY is null";
                    try {
                        idZCntIsNull = dataManager.loadValue(idZCntIsNullQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .parameter("idX", idX)
                                .parameter("idY", idY)
                                .one();
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idYCntIsNullQry error: " + e.getMessage());
                        notifications.create().withCaption("idYCntIsNullQry error: " + e.getMessage()).show();
                        logger.trace(logPrfx + " <-- ");
                        return;
                    }
                    logger.debug(logPrfx + " --- idYCntIsNullQry result: " + idZCntIsNull + "");

                    if (idZMax == null && idZCntIsNull != 0){
                        idZ = 1;
                    }else{
                        idZ = idZMax == null ? 0 : idZMax + 1;
                    }
                    copy.setIdZ(idZ);
                }
            }

            if (tmplt_finStmt1_IdFieldChk.isChecked()){
                copy.setFinStmt1_Id(tmplt_finStmt1_IdField.getValue());}

            if (tmplt_finStmtItm1_Desc1FieldChk.isChecked()){
                copy.setFinStmtItm1_Desc1(tmplt_finStmtItm1_Desc1Field.getValue());}

            if (tmplt_finStmtItm1_Desc2FieldChk.isChecked()){
                copy.setFinStmtItm1_Desc2(tmplt_finStmtItm1_Desc2Field.getValue());}

            if (tmplt_finStmtItm1_Desc3FieldChk.isChecked()){
                copy.setFinStmtItm1_Desc3(tmplt_finStmtItm1_Desc3Field.getValue());}

            if (tmplt_finStmtItm1_DescAmtFieldChk.isChecked()){
                copy.setFinStmtItm1_DescAmt(tmplt_finStmtItm1_DescAmtField.getValue());}

            if (tmplt_finStmtItm1_RefIdFieldChk.isChecked()){
                copy.setFinStmtItm1_RefId(tmplt_finStmtItm1_RefIdField.getValue());}

            if (tmplt_finAcct1_IdFieldChk.isChecked()){
                copy.setFinAcct1_Id(tmplt_finAcct1_IdField.getValue());}

            if (tmplt_finDept1_IdFieldChk.isChecked()){
                copy.setFinDept1_Id(tmplt_finDept1_IdField.getValue());}

            if (tmplt_amtDebtFieldChk.isChecked()){
                copy.setAmtDebt(tmplt_amtDebtField.getValue());}

            if (tmplt_amtCredFieldChk.isChecked()){
                copy.setAmtCred(tmplt_amtCredField.getValue());}

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setIdZ(copy.getIdZ() == null ? 1 : copy.getIdZ() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            updateCalcVals(copy);

            GenNode savedCopy = dataManager.save(copy);
            finTxfersDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinTxfer " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        table.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

/*
                updateId2Calc(thisFinTxferTracked);

                thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                updateId2Cmp(thisFinTxferTracked);
*/

                if (tmplt_finStmt1_IdFieldChk.isChecked()){
                    thisFinTxferTracked.setFinStmt1_Id(tmplt_finStmt1_IdField.getValue());}

                if (tmplt_finStmtItm1_Desc1FieldChk.isChecked()){
                    thisFinTxferTracked.setFinStmtItm1_Desc1(tmplt_finStmtItm1_Desc1Field.getValue());}

                if (tmplt_finStmtItm1_Desc2FieldChk.isChecked()){
                    thisFinTxferTracked.setFinStmtItm1_Desc2(tmplt_finStmtItm1_Desc2Field.getValue());}

                if (tmplt_finStmtItm1_Desc3FieldChk.isChecked()){
                    thisFinTxferTracked.setFinStmtItm1_Desc3(tmplt_finStmtItm1_Desc3Field.getValue());}

                if (tmplt_finStmtItm1_DescAmtFieldChk.isChecked()){
                    thisFinTxferTracked.setFinStmtItm1_DescAmt(tmplt_finStmtItm1_DescAmtField.getValue());}

                if (tmplt_finStmtItm1_RefIdFieldChk.isChecked()){
                    thisFinTxferTracked.setFinStmtItm1_RefId(tmplt_finStmtItm1_RefIdField.getValue());}


                if (tmplt_finAcct1_IdFieldChk.isChecked()){
                    thisFinTxferTracked.setFinAcct1_Id(tmplt_finAcct1_IdField.getValue());}

                if (tmplt_finDept1_IdFieldChk.isChecked()){
                    thisFinTxferTracked.setFinDept1_Id(tmplt_finDept1_IdField.getValue());}


                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });

        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                updateId2Dup(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();
        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_finStmtItm1_Desc1Field", subject = "enterPressHandler")
    private void tmplt_finStmtItm1_Desc1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_finStmtItm1_Desc1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_finStmtItm1_Desc2Field", subject = "enterPressHandler")
    private void tmplt_finStmtItm1_Desc2FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_finStmtItm1_Desc2FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_finStmtItm1_Desc3Field", subject = "enterPressHandler")
    private void tmplt_finStmtItm1_Desc3FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_finStmtItm1_Desc3FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("filterPrevIdTsTs1GEBtn")
    public void onFilterPrevIdTsTs1GEBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFilterPrevIdTsTs1GEBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> propFilterCmpnt_idTsGE = (DateField<LocalDateTime>) propFilter_idTsGE.getValueComponent();
        LocalDateTime idTsGE = propFilterCmpnt_idTsGE.getValue();
        if (idTsGE == null) {
            logger.debug(logPrfx + " --- idTsGE: null");
        } else {
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            idTsGE = idTsGE.minusDays(1);
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            propFilterCmpnt_idTsGE.setValue(idTsGE);
            filter.apply();
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("filterNextIdTsTs1GEBtn")
    public void onFilterNextIdTsTs1GEBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFilterNextIdTsTs1GEBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> propFilterCmpnt_idTsGE = (DateField<LocalDateTime>) propFilter_idTsGE.getValueComponent();
        LocalDateTime idTsGE = propFilterCmpnt_idTsGE.getValue();
        if (idTsGE == null) {
            logger.debug(logPrfx + " --- idTsGE: null");
        } else {
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            idTsGE = idTsGE.plusDays(1);
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            propFilterCmpnt_idTsGE.setValue(idTsGE);
            filter.apply();
        }

        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("zeroIdXBtn")
    public void onZeroIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                LocalDateTime idTs1 = thisFinTxferTracked.getIdTs() != null ? thisFinTxferTracked.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX = thisFinTxferTracked.getIdX();
                    if (!(Objects.equals(idX == null ? 0 : idX, 0))){
                        idX = 0;
                        thisFinTxferTracked.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setIdX(" + (idX) + ")");

                        updateId2Calc(thisFinTxferTracked);

                        thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                        updateId2Cmp(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        updateId2Dup(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        table.sort("id2", Table.SortDirection.ASCENDING);
                    }
                }

            }
        });

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                Integer idX = thisFinTxferTracked.getIdX();
                logger.debug(logPrfx + " --- idX: " + idX);
                if (idX == null || idX == 0) {
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                thisFinTxferTracked.setIdX(idX - 1);
                logger.debug(logPrfx + " --- thisFinTxferTracked.setIdX(" + (idX - 1) + ")");

                updateId2Calc(thisFinTxferTracked);

                thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                updateId2Cmp(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                updateId2Dup(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();
        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdXBtn")
    public void onIncIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                Integer idX = thisFinTxferTracked.getIdX();
                logger.debug(logPrfx + " --- idX: " + idX);
                if (idX == null) {
                    thisFinTxferTracked.setIdX(1);
                    logger.debug(logPrfx + " --- thisFinTxferTracked.setIdX(" + 1 + ")");
                } else {
                    thisFinTxferTracked.setIdX(idX + 1);
                    logger.debug(logPrfx + " --- thisFinTxferTracked.setIdX(" + (idX + 1) + ")");
                }

                updateId2Calc(thisFinTxferTracked);

                thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                updateId2Cmp(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                updateId2Dup(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();
        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("maxIdXBtn")
    public void onMaxIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                LocalDateTime idTs1 = thisFinTxferTracked.getIdTs() != null ? thisFinTxferTracked.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX = thisFinTxferTracked.getIdX();
                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1";
                    try {
                        idXMax = dataManager.loadValue(idXMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idXMaxQry error: " + e.getMessage());
                        idXMax = null;
                    }
                    logger.debug(logPrfx + " --- idXMaxQry result: " + idXMax + "");
                    if (!(Objects.equals(idX == null ? 0 : idX, idXMax == null ? 0 : idXMax))){
                        idX = (idXMax == null ? 0 : idXMax);
                        thisFinTxferTracked.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setIdX(" + (idX) + ")");

                        updateId2Calc(thisFinTxferTracked);

                        thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                        updateId2Cmp(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        updateId2Dup(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        table.sort("id2", Table.SortDirection.ASCENDING);
                    }
                }

            }
        });

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("zeroIdYBtn")
    public void onZeroIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                LocalDateTime idTs1 = thisFinTxferTracked.getIdTs() != null ? thisFinTxferTracked.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idY = thisFinTxferTracked.getIdY();
                    if (!(Objects.equals(idY == null ? 0 : idY, 0))){
                        idY = 0;
                        thisFinTxferTracked.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setIdY(" + (idY) + ")");

                        updateId2Calc(thisFinTxferTracked);

                        thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                        updateId2Cmp(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        updateId2Dup(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        table.sort("id2", Table.SortDirection.ASCENDING);
                    }
                }

            }
        });

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdYBtn")
    public void onDecIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                Integer idY = thisFinTxferTracked.getIdY();
                logger.debug(logPrfx + " --- idY: " + idY);
                if (idY == null || idY == 0) {
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                thisFinTxferTracked.setIdY(idY - 1);
                logger.debug(logPrfx + " --- thisFinTxferTracked.setIdY(" + (idY - 1) + ")");

                updateId2Calc(thisFinTxferTracked);

                thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                updateId2Cmp(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                updateId2Dup(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();
        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdYBtn")
    public void onIncIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                Integer idY = thisFinTxferTracked.getIdY();
                logger.debug(logPrfx + " --- idY: " + idY);
                if (idY == null) {
                    thisFinTxferTracked.setIdY(1);
                    logger.debug(logPrfx + " --- thisFinTxferTracked.setIdY(" + 1 + ")");
                } else {
                    thisFinTxferTracked.setIdY(idY + 1);
                    logger.debug(logPrfx + " --- thisFinTxferTracked.setIdY(" + (idY + 1) + ")");
                }

                updateId2Calc(thisFinTxferTracked);

                thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                updateId2Cmp(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                updateId2Dup(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();
        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("maxIdYBtn")
    public void onMaxIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                LocalDateTime idTs1 = thisFinTxferTracked.getIdTs() != null ? thisFinTxferTracked.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX = thisFinTxferTracked.getIdX();
                    Integer idY = thisFinTxferTracked.getIdY();
                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1"
                            + " and e.idY = :idX";
                    try {
                        idYMax = dataManager.loadValue(idYMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .parameter("idX", idX)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idYMaxQry error: " + e.getMessage());
                        idYMax = null;
                    }
                    logger.debug(logPrfx + " --- idYMaxQry result: " + idYMax + "");
                    if (!(Objects.equals(idY == null ? 0 : idY, idYMax == null ? 0 : idYMax))){
                        idY = (idYMax == null ? 0 : idYMax);
                        thisFinTxferTracked.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setIdY(" + (idY) + ")");

                        updateId2Calc(thisFinTxferTracked);

                        thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                        updateId2Cmp(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        updateId2Dup(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        table.sort("id2", Table.SortDirection.ASCENDING);
                    }
                }

            }
        });

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("zeroIdZBtn")
    public void onZeroIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                LocalDateTime idTs1 = thisFinTxferTracked.getIdTs() != null ? thisFinTxferTracked.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idZ = thisFinTxferTracked.getIdZ();
                    if (!(Objects.equals(idZ == null ? 0 : idZ, 0))){
                        idZ = 0;
                        thisFinTxferTracked.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setIdZ(" + (idZ) + ")");

                        updateId2Calc(thisFinTxferTracked);

                        thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                        updateId2Cmp(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        updateId2Dup(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        table.sort("id2", Table.SortDirection.ASCENDING);
                    }
                }

            }
        });

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdZBtn")
    public void onDecIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                Integer idZ = thisFinTxferTracked.getIdZ();
                logger.debug(logPrfx + " --- idZ: " + idZ);
                if (idZ == null || idZ == 0) {
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                thisFinTxferTracked.setIdZ(idZ - 1);
                logger.debug(logPrfx + " --- thisFinTxferTracked.setIdZ(" + (idZ - 1) + ")");

                updateId2Calc(thisFinTxferTracked);

                thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                updateId2Cmp(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
                }
            });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                updateId2Dup(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();
        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("incIdZBtn")
    public void onIncIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                Integer idZ = thisFinTxferTracked.getIdZ();
                logger.debug(logPrfx + " --- idZ: " + idZ);
                if (idZ == null) {
                    thisFinTxferTracked.setIdZ(1);
                    logger.debug(logPrfx + " --- thisFinTxferTracked.setIdZ(" + 1 + ")");
                } else {
                    thisFinTxferTracked.setIdZ(idZ + 1);
                    logger.debug(logPrfx + " --- thisFinTxferTracked.setIdZ(" + (idZ + 1) + ")");
                }

                updateId2Calc(thisFinTxferTracked);

                thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                updateId2Cmp(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                updateId2Dup(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();
        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("maxIdZBtn")
    public void onMaxIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                LocalDateTime idTs1 = thisFinTxferTracked.getIdTs() != null ? thisFinTxferTracked.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX = thisFinTxferTracked.getIdX();
                    Integer idY = thisFinTxferTracked.getIdY();
                    Integer idZ = thisFinTxferTracked.getIdZ();
                    Integer idZMax = 0;
                    String idZMaxQry = "select max(e.idZ) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1"
                            + " and e.idZ = :idX"
                            + " and e.idZ = :idY";
                    try {
                        idZMax = dataManager.loadValue(idZMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .parameter("idX", idX)
                                .parameter("idY", idY)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idZMaxQry error: " + e.getMessage());
                        idZMax = null;
                    }
                    logger.debug(logPrfx + " --- idZMaxQry result: " + idZMax + "");
                    if (!(Objects.equals(idZ == null ? 0 : idZ, idZMax == null ? 0 : idZMax))){
                        idZ = (idZMax == null ? 0 : idZMax);
                        thisFinTxferTracked.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setIdZ(" + (idZ) + ")");

                        updateId2Calc(thisFinTxferTracked);

                        thisFinTxferTracked.setId2(thisFinTxferTracked.getId2Calc());
                        logger.debug(logPrfx + " --- thisFinTxferTracked.setId2(" + thisFinTxferTracked.getId2Calc() + ")");

                        updateId2Cmp(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        updateId2Dup(thisFinTxferTracked);

                        logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                        metadataTools.copy(thisFinTxferTracked, thisFinTxfer);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        table.sort("id2", Table.SortDirection.ASCENDING);
                    }
                }

            }
        });

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {
                updateCalcVals(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTxactBtn")
    public void onUpdateTxactBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTxactBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisFinTxferTracked = dataContext.find(thisFinTxfer);
            if (thisFinTxferTracked != null) {

                createFinTxact1_Id2Calc(thisFinTxferTracked);
                updateFinTxact1_Id(thisFinTxferTracked);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxferTracked,thisFinTxfer).");
                metadataTools.copy(thisFinTxferTracked, thisFinTxfer);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("linkAsRef1Btn")
    public void onLinkAsRef1BtnClick(Button.ClickEvent event) {
        String logPrfx = "onLinkAsRef1BtnClick";
        logger.trace(logPrfx + " --> ");


        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers.size() < 2) {
            logger.debug(logPrfx + " --- thisFinTxfers contains less that 2 items.");
            notifications.create().withCaption("Less than 2 records selected. Please select 2 records.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        GenNode selFinTxfer0Tracked = dataContext.find(thisFinTxfers.get(0));
        GenNode selFinTxfer1Tracked = dataContext.find(thisFinTxfers.get(1));

        if (selFinTxfer0Tracked != null && selFinTxfer1Tracked != null) {
            selFinTxfer0Tracked.setFinTxfer1_Id(selFinTxfer1Tracked);
            logger.debug("Linked FinTxfer "
                    + selFinTxfer0Tracked.getId2() + " [" + selFinTxfer0Tracked.getId() + "]"
                    + " Ref1 linked to "
                    + selFinTxfer1Tracked.getId2() + " [" + selFinTxfer1Tracked.getId() + "]"
            );

            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinTxfer);

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

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinTxfer);
            updateId2Dup(thisFinTxfer);
        }
        logger.trace(logPrfx + " <-- ");
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
        updateId2Cmp(thisFinTxfer);
        updateId2Dup(thisFinTxfer);

        logger.debug(logPrfx + " --- id2: " + thisFinTxfer.getId2());
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


    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxferTypesDl.load();
        logger.debug(logPrfx + " --- called finTxferTypesDl.load() ");

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

    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdTs(thisFinTxfer)");
            updateIdTs(thisFinTxfer);
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxfer)");
            String id2Calc = updateId2Calc(thisFinTxfer);
            updateFinTxact1_Id2Calc(thisFinTxfer, id2Calc.substring(0, 24));
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("beg2Ts1Field")
    public void onBeg2Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg2Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdTs(thisFinTxfer)");
            updateIdTs(thisFinTxfer);
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxfer)");
            String id2Calc = updateId2Calc(thisFinTxfer);
            updateFinTxact1_Id2Calc(thisFinTxfer, id2Calc.substring(0, 24));
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxfer)");
            String id2Calc = updateId2Calc(thisFinTxfer);
            updateFinTxact1_Id2Calc(thisFinTxfer, id2Calc.substring(0, 24));
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idYField")
    public void onIdYFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdYFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxfer)");
            String id2Calc = updateId2Calc(thisFinTxfer);
            updateFinTxact1_Id2Calc(thisFinTxfer, id2Calc.substring(0, 24));
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idZField")
    public void onIdZFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdZFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxfer)");
            String id2Calc = updateId2Calc(thisFinTxfer);
        }

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinTxact1_IdFieldBtn")
    public void onUpdateFinTxact1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateFinTxact1_Id(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_IdFieldListBtn")
    public void onUpdateFinTxact1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactsDl.load();
        logger.debug(logPrfx + " --- called finTxactsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxact1_Id2CalcField")
    public void onFinTxact1_Id2CalcFieldValueChange(HasValue.ValueChangeEvent event) {
        String logPrfx = "onFinTxact1_Id2CalcFieldValueChange";
        logger.trace(logPrfx + " --> ");

//        logger.debug(logPrfx + " --- event: " + event.getSource());

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("createFinTxact1_Id2CalcFieldBtn")
    public void onCreateFinTxact1_Id2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onCreateFinTxact1_Id2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        createFinTxact1_Id2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id2CalcBtn")
    public void onUpdateFinTxact1_Id2CalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id2CalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id2Calc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_Type1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Type1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactTypesDl.load();
        logger.debug(logPrfx + " --- called finTxactTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finStmtItm1_Desc1Field", subject = "enterPressHandler")
    private void finStmtItm1_Desc1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finStmtItm1_Desc1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinStmt1_IdFieldListBtn")
    public void onUpdateFinStmt1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmt1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_Desc1FieldListBtn")
    public void onUpdateFinStmtItm1_Desc1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_Desc1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Desc1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finStmtItm1_Desc2Field", subject = "enterPressHandler")
    private void finStmtItm1_Desc2FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finStmtItm1_Desc2FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_Desc2FieldListBtn")
    public void onUpdateFinStmtItm1_Desc2FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_Desc2FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Desc2List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finStmtItm1_Desc3Field", subject = "enterPressHandler")
    private void finStmtItm1_Desc3FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finStmtItm1_Desc3FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinStmtItm1_Desc3FieldListBtn")
    public void onUpdateFinStmtItm1_Desc3FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_Desc3FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Desc3List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinDept1_IdFieldListBtn")
    public void onUpdateFinDept1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinDept1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finDeptsDl.load();
        logger.debug(logPrfx + " --- called finDeptsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTaxLne1_IdFieldListBtn")
    public void onUpdateFinTaxLne1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTaxLnesDl.load();
        logger.debug(logPrfx + " --- called finTaxLnesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxfer1_IdFieldListBtn")
    public void onUpdateFinTxfer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfer1_IdFieldListBtn";
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

    @Subscribe("updateFinFmla1_IdFieldListBtn")
    public void onUpdateFinFmla1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinFmla1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finFmlasDl.load();
        logger.debug(logPrfx + " --- called finFmlasDl.load() ");

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

    @Subscribe("updateFinWhat1_IdFieldListBtn")
    public void onUpdateFinWhat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_What1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_What1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_What1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

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

    @Subscribe("updateFinTxact1_Id_Why1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Why1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Why1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private void updateCalcVals(@NotNull GenNode thisFinTxfer) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        updateIdTs(thisFinTxfer);
        String id2Calc = updateId2Calc(thisFinTxfer);
        updateDesc1(thisFinTxfer);
        updateFinTxact1_Id2Calc(thisFinTxfer, id2Calc.substring(0, 24));
        updateCalcAmtNet(thisFinTxfer);
        updateFinTxfer1_EI1_Rate(thisFinTxfer);
        updateCalcRslt(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }


    private String updateId2Calc(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisFinTxfer.getId2CalcFrFields();
        thisFinTxfer.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        updateId2Cmp(thisFinTxfer);
        updateId2Dup(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private String updateId2Calc(@NotNull GenNode thisFinTxfer, String id2Calc) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        thisFinTxfer.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private void updateId2Cmp(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisFinTxfer.setId2Cmp(!thisFinTxfer.getId2().equals(thisFinTxfer.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisFinTxfer.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }

    private void updateId2Dup(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisFinTxfer.getId2() != null) {
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinTxfer' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinTxfer.getId())
                        .parameter("id2", thisFinTxfer.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");

            thisFinTxfer.setId2Dup(id2Dup + 1);
            logger.debug(logPrfx + " --- thisFinTxfer.setId2Dup(" + (id2Dup + 1) + ")");

        }
        logger.trace(logPrfx + " <-- ");
    }

    private void updateDesc1(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        String thisAmt = "";
        if (thisFinTxfer.getAmtDebt() != null) {
            thisAmt = thisAmt + " Debt " + thisFinTxfer.getAmtDebt();
        }
        if (thisFinTxfer.getAmtCred() != null) {
            thisAmt = thisAmt + " Cred " + thisFinTxfer.getAmtCred();
        }
        if (!thisAmt.equals("")) {
            thisAmt = thisAmt.trim();
        }
        logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

        String thisCurcy = "";
        if (thisFinTxfer.getFinCurcy1_Id() != null) {
            thisCurcy = Objects.toString(thisFinTxfer.getFinCurcy1_Id().getId2(), "");
        }
        logger.debug(logPrfx + " --- thisCurcy: " + thisCurcy);

        String thisStmtItm1_Desc1 = Objects.toString(thisFinTxfer.getFinStmtItm1_Desc1(), "");
        String thisStmtItm1_Desc2 = Objects.toString(thisFinTxfer.getFinStmtItm1_Desc2(), "");
        String thisStmtItm1_Desc3 = Objects.toString(thisFinTxfer.getFinStmtItm1_Desc3(), "");
        List<String> list1 = Arrays.asList(
                  thisStmtItm1_Desc1
                , thisStmtItm1_Desc2
        );
        String thisStmtItm1_Desc = list1.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
        if (!thisStmtItm1_Desc.equals("")) {
            thisStmtItm1_Desc = "desc " + thisStmtItm1_Desc;
        }
        logger.debug(logPrfx + " --- thisStmtItm1_Desc: " + thisStmtItm1_Desc);

        String thisAcct = "";
        if (thisFinTxfer.getFinAcct1_Id() != null) {
            thisAcct = Objects.toString(thisFinTxfer.getFinAcct1_Id().getId2(), "");
        }
        if (!thisAcct.equals("")) {
            thisAcct = "on acct [" + thisAcct + "]";
        }
        logger.debug(logPrfx + " --- thisAcct: " + thisAcct);

        String thisDept = "";
        if (thisFinTxfer.getFinDept1_Id() != null) {
            thisDept = Objects.toString(thisFinTxfer.getFinDept1_Id().getId2(), "");
        }
        if (!thisDept.equals("")) {
            thisDept = "on dept [" + thisDept + "]";
        }
        logger.debug(logPrfx + " --- thisDept: " + thisDept);

        String thisTaxLne = "";
        if (thisFinTxfer.getFinTaxLne1_Id() != null) {
            thisTaxLne = "[" + Objects.toString(thisFinTxfer.getFinTaxLne1_Id().getId2(), "") + "]";
        }
        if (!thisTaxLne.equals("")) {
            thisTaxLne = "with tax line " + thisTaxLne;
        }
        logger.debug(logPrfx + " --- thisTaxLne: " + thisTaxLne);

        String thisChan = "";
        if (thisFinTxfer.getGenChan1_Id() != null) {
            thisChan = Objects.toString(thisFinTxfer.getGenChan1_Id().getId2(), "");
        }
        if (!thisChan.equals("")) {
            thisChan = "in chan [" + thisChan + "]";
        }
        logger.debug(logPrfx + " --- thisChan: " + thisChan);


        String thisHow = "";
        if (thisFinTxfer.getFinHow1_Id() != null) {
            thisHow = Objects.toString(thisFinTxfer.getFinHow1_Id().getId2(), "");
        }
        if (!thisHow.equals("")) {
            thisHow = "via " + thisHow;
        }
        logger.debug(logPrfx + " --- thisHow: " + thisHow);

        String thisWhat = Objects.toString(thisFinTxfer.getWhatText1(), "");
        if (thisFinTxfer.getFinWhat1_Id() != null) {
            thisWhat = thisWhat + " " + Objects.toString(thisFinTxfer.getFinWhat1_Id().getId2());
        }
        if (!thisWhat.equals("")) {
            thisWhat = "for " + thisWhat;
        }
        logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

        String thisWhy = Objects.toString(thisFinTxfer.getWhyText1(), "");
        if (thisFinTxfer.getFinWhy1_Id() != null) {
            thisWhy = thisWhy + " " + Objects.toString(thisFinTxfer.getFinWhy1_Id().getId2());
        }
        if (!thisWhy.equals("")) {
            thisWhy = "for " + thisWhy;
        }
        logger.debug(logPrfx + " --- thisWhy: " + thisWhy);

        String thisDocVer = "";
        if (thisFinTxfer.getGenDocVer1_Id() != null) {
            thisDocVer = Objects.toString(thisFinTxfer.getGenDocVer1_Id().getId2());
        }
        if (!thisDocVer.equals("")) {
            thisDocVer = "doc ver " + thisDocVer;
        }
        logger.debug(logPrfx + " --- thisDocVer: " + thisDocVer);

        String thisTag = "";
        String thisTag1 = "";
        if (thisFinTxfer.getGenTag1_Id() != null) {
            thisTag1 = Objects.toString(thisFinTxfer.getGenTag1_Id().getId2());
        }
        String thisTag2 = "";
        if (thisFinTxfer.getGenTag1_Id() != null) {
            thisTag2 = Objects.toString(thisFinTxfer.getGenTag2_Id().getId2());
        }
        String thisTag3 = "";
        if (thisFinTxfer.getGenTag1_Id() != null) {
            thisTag3 = Objects.toString(thisFinTxfer.getGenTag3_Id().getId2());
        }
        String thisTag4 = "";
        if (thisFinTxfer.getGenTag1_Id() != null) {
            thisTag4 = Objects.toString(thisFinTxfer.getGenTag4_Id().getId2());
        }
        if (!(thisTag1 + thisTag2 + thisTag3 + thisTag4).equals("")) {
            thisTag = "tag [" + String.join(",", thisTag1, thisTag2, thisTag3, thisTag4) + "]";
        }
        logger.debug(logPrfx + " --- thisTag: " + thisTag);

        List<String> list = Arrays.asList(
                thisAmt
                , thisCurcy
                , thisStmtItm1_Desc
                , thisAcct
                , thisDept
                , thisChan
                , thisTaxLne
                , thisWhat
                , thisWhy
                , thisHow
                , thisDocVer
                , thisTag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
        thisFinTxfer.setDesc1(desc1);

        logger.debug(logPrfx + " --- desc1: " + desc1);
        logger.trace(logPrfx + " <-- ");
    }

    private void updateIdTs(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateIdTs";
        logger.trace(logPrfx + " --> ");

        thisFinTxfer.updateIdTs();

        logger.trace(logPrfx + " <-- ");
    }

    private void updateFinTxact1_Id(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id";
        logger.trace(logPrfx + " --> ");

        String finTxact1_Id2Calc = thisFinTxfer.getFinTxact1_Id2Calc();
        logger.debug(logPrfx + " --- finTxact1_Id2Calc: " + finTxact1_Id2Calc);
        if (finTxact1_Id2Calc == null
                || finTxact1_Id2Calc == "") {
            logger.debug(logPrfx + " --- finTxact1_Id2Calc: null");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact1_Id2Calc);

        GenNode finTxact1_Id = null;
        try {
            finTxact1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finTxact1_Id2Calc)
                    .one();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no results");
            notifications.create().withCaption("FinTxact with id2:" + finTxact1_Id2Calc + " not found.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxfer.setFinTxact1_Id(finTxact1_Id);

        logger.debug(logPrfx + " --- finTxact1_Id.Id: " + finTxact1_Id.getId());
        logger.trace(logPrfx + " <-- ");
    }

    private String updateFinTxact1_Id2Calc(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id2Calc";
        logger.trace(logPrfx + " --> ");

        String finTxact1_Id2Calc = thisFinTxfer.getId2CalcFrFields().substring(0, 24);
        thisFinTxfer.setFinTxact1_Id2Calc(finTxact1_Id2Calc);
        logger.debug(logPrfx + " --- finTxact1_Id2Calc: " + finTxact1_Id2Calc);

        logger.trace(logPrfx + " <-- ");
        return finTxact1_Id2Calc;
    }

    private String updateFinTxact1_Id2Calc(GenNode thisFinTxfer, String finTxact1_Id2Calc) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id2Calc";
        logger.trace(logPrfx + " --> ");

        thisFinTxfer.setFinTxact1_Id2Calc(finTxact1_Id2Calc);
        logger.debug(logPrfx + " --- finTxact1_Id2Calc: " + finTxact1_Id2Calc);

        logger.trace(logPrfx + " <-- ");
        return finTxact1_Id2Calc;
    }

    private void createFinTxact1_Id2Calc(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "createFinTxact1_Id2Calc";
        logger.trace(logPrfx + " --> ");

        if (thisFinTxfer.getFinTxact1_Id2Calc() == null) {
            logger.debug(logPrfx + " --- thisFinTxfer.getFinTxact1_Id2Calc() is null, set FinTxact1_Id2Calc.");
            notifications.create().withCaption("FinTxact1_Id2Calc is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        String finTxact1_Id2Calc = thisFinTxfer.getFinTxact1_Id2Calc();

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact1_Id2Calc);

        GenNode finTxact1_Id;
        try {
            finTxact1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finTxact1_Id2Calc)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            // notifications.create().withCaption("FinTxact with id2: " + finTxact1_Id2Calc + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

            GenNode newFinTxact = dataManager.create(GenNode.class);
            newFinTxact.setId2(finTxact1_Id2Calc);
            newFinTxact.setClassName("FinTxact");

            HasTmst beg1 = dataManager.create(HasTmst.class);
            beg1.setTs1(thisFinTxfer.getIdTs().getTs1());
            newFinTxact.setBeg1(beg1);
            logger.debug(logPrfx + " --- called newFinTxact.setBeg(): ");

            GenNode savedFinTxact = dataManager.save(newFinTxact);

            GenNode mergedFinTxact = dataContext.merge(savedFinTxact);
            logger.debug(logPrfx + " --- created FinTxfer id: " + mergedFinTxact.getId());
            notifications.create().withCaption("Created FinTxfer with id2:" + mergedFinTxact.getId2()).show();

        }

        logger.trace(logPrfx + " <-- ");

    }

    private void updateFinTxfer1_EI1_Rate(@NotNull GenNode thisFinTxfer) {
        //Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxfer1_EI1_Rate";
        logger.trace(logPrfx + " --> ");

        FinFmla finFmla1 = thisFinTxfer.getFinFmla1_Id();
        if (finFmla1 == null) {
            logger.debug(logPrfx + " --- finFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return;
        } else if (!finFmla1.getId2().equals("Ref1 Mult Exch_Rate")) {
            logger.debug(logPrfx + " --- finfmla: " + finFmla1.getId2());
            notifications.create().withCaption("finFmla1_Id must be 'Ref1 Mult Exch_Rate' to get an exchange rate.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        } else {
            logger.debug(logPrfx + " --- finFmla1_Id: " + finFmla1.getId());
        }

        GenNode finTxfer1 = thisFinTxfer.getFinTxfer1_Id();
        if (finTxfer1 == null) {
            logger.debug(logPrfx + " --- finTxfer1: null");
            notifications.create().withCaption("Unable to get reference to the other FinTxfer. Please ensure finTxfer1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        GenNode curcyFr = thisFinTxfer.getFinTxfer1_Id().getFinCurcy1_Id();
        if (curcyFr == null) {
            logger.debug(logPrfx + " --- curcyFr: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxfer. Please ensure a FinTxfer1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        } else {
            logger.debug(logPrfx + " --- curcyFr.Id: " + curcyFr.getId());
        }

        GenNode curcyTo = thisFinTxfer.getFinCurcy1_Id();
        if (curcyTo == null) {
            logger.debug(logPrfx + " --- curcyTo: null");
            notifications.create().withCaption("Unable to get the currency from this FinTxfer. Please ensure a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        } else {
            logger.debug(logPrfx + " --- curcyTo.Id: " + curcyTo.getId());

        }

        LocalDate date1 = thisFinTxfer.getBeg1().getDate1();

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd")
                .toFormatter();

        if (date1 == null) {
            logger.debug(logPrfx + " --- date1: null");
            notifications.create().withCaption("Unable to get the date from this FinTxfer. Please ensure a date is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        } else {
            logger.debug(logPrfx + " --- date1: " + date1.format(frmtDt));

        }

        FinRate finRate;
        try {
            finRate = dataManager.load(FinRate.class)
                    .query("select fr from ampata_FinRate fr"
                            + " where fr.finCurcy1_Id.id = :curcyFr"
                            + " and   fr.finCurcy2_Id.id = :curcyTo"
                            + " and   fr.beg1.date1 = :date1"
                    )
                    .parameter("curcyFr", curcyFr.getId())
                    .parameter("curcyTo", curcyTo.getId())
                    .parameter("date1", date1)
                    .one()
            ;

        } catch (IllegalStateException e) {
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
                        .query("select fr from ampata_FinRate fr "
                                + " where fr.finCurcy1_Id.id = :curcyFr"
                                + " and   fr.finCurcy2_Id.id = :curcyTo"
                                + " and   fr.beg1.date1 = :date1"
                        )
                        .parameter("curcyFr", curcyTo.getId())
                        .parameter("curcyTo", curcyFr.getId())
                        .parameter("date1", date1)
                        .one()
                ;
            } catch (IllegalStateException e) {
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

    
    private void updateCalcRslt(@NotNull GenNode thisFinTxfer) {
        //Assume thisFinTxfer is not null
        String logPrfx = "updateCalcRslt";
        logger.trace(logPrfx + " --> ");

        BigDecimal rslt = BigDecimal.valueOf(0);
        boolean rsltGood = false;

        FinFmla finFmla1 = thisFinTxfer.getFinFmla1_Id();
        if (finFmla1 == null) {
            logger.debug(logPrfx + " --- finFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return;
        } else {
            logger.debug(logPrfx + " --- finFmla1: " + finFmla1.getId());
        }

        GenNode prev1FinTxfer;
        GenNode prev2FinTxfer;

        // Switch statement over above string
        switch (finFmla1.getId2()) {

            // Ref1 Mult Exch_Rate
            case "Ref1 Mult Exch_Rate":

                GenNode finTxfer1 = thisFinTxfer.getFinTxfer1_Id();
                if (finTxfer1 == null) {
                    logger.debug(logPrfx + " --- finTxfer1_Id: null");
                    notifications.create().withCaption("Unable to get finTxfer1. Please ensure  finTxfer1 has a finTxfer selected.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                } else {
                    logger.debug(logPrfx + " --- finTxfer1_Id: " + finTxfer1.getId());
                }

                BigDecimal val = finTxfer1.getAmtNet();
                if (val == null) {
                    logger.debug(logPrfx + " --- finTxfer1_AmtNet: null");
                    notifications.create().withCaption("Unable to get finTxfer1_AmtNet. Please ensure  finTxfer1_AmtNet has a value.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                } else {
                    logger.debug(logPrfx + " --- finTxfer1_AmtNet: " + val);
                }

                BigDecimal rate = thisFinTxfer.getFinTxfer1_EI1_Rate();
                rslt = val.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP).negate();
                rsltGood = true;
                break;

            case "Prev2.Cr Sub Prev1.Dr":
                prev1FinTxfer = getPrevFinTxfer(thisFinTxfer);
                if (prev1FinTxfer != null) {
                    BigDecimal prev1Dr = prev1FinTxfer.getAmtDebt() != null ? prev1FinTxfer.getAmtDebt() : BigDecimal.valueOf(0);

                    prev2FinTxfer = getPrevFinTxfer(prev1FinTxfer);
                    if (prev2FinTxfer != null) {
                        BigDecimal prev2Cr = prev2FinTxfer.getAmtCred() != null ? prev2FinTxfer.getAmtCred() : BigDecimal.valueOf(0);

                        rslt = prev2Cr.subtract(prev1Dr);
                        rsltGood = true;
                    }
                }
                break;

            case "Prev2.Dr Sub Prev1.Cr":
                prev1FinTxfer = getPrevFinTxfer(thisFinTxfer);
                if (prev1FinTxfer != null) {
                    BigDecimal prev1Cr = prev1FinTxfer.getAmtCred() != null ? prev1FinTxfer.getAmtCred() : BigDecimal.valueOf(0);

                    prev2FinTxfer = getPrevFinTxfer(prev1FinTxfer);
                    if (prev2FinTxfer != null) {
                        BigDecimal prev2Dr = prev2FinTxfer.getAmtDebt() != null ? prev2FinTxfer.getAmtDebt() : BigDecimal.valueOf(0);

                        rslt = prev2Dr.subtract(prev1Cr);
                        rsltGood = true;
                    }
                }
                break;


            default:
                logger.debug(logPrfx + " --- formula not implemented: " + finFmla1.getId2());
                notifications.create().withCaption("Formula not implemented: " + finFmla1.getId2()).show();
                logger.trace(logPrfx + " <-- ");
                return;
        }

        if (rsltGood) {
            thisFinTxfer.setCalcRslt(rslt);
            logger.debug(logPrfx + " --- calcRslt: " + rslt);
        }
        logger.trace(logPrfx + " <-- ");
    }

    public void updateCalcAmtNet(@NotNull GenNode thisFinTxfer) {
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


    public GenNode getPrevFinTxfer(@NotNull GenNode thisFinTxfer) {
        //Assume thisFinTxfer is not null
        String logPrfx = "getPrevFinTxfer";
        logger.trace(logPrfx + " --> ");

        GenNode prevFinTxfer = null;
        boolean qryRsltGood = false;

        if (thisFinTxfer.getIdZ() != null || thisFinTxfer.getIdZ() >= 1) {
            prevFinTxfer = metadataTools.copy(thisFinTxfer);
            prevFinTxfer.setIdZ(prevFinTxfer.getIdZ() - 1);
            String prev1FinTxfer_Id2 = prevFinTxfer.getId2CalcFrFields();

            String qry = "select gn from ampata_GenNode gn"
                    + " where gn.id2 = :id2";

            logger.debug(logPrfx + " --- qry: " + qry);

            try {
                prevFinTxfer = dataManager.load(GenNode.class)
                        .query(qry)
                        .parameter("id2", prev1FinTxfer_Id2)
                        .one()
                ;

            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- qry1 java.lang.IllegalStateException: " + e.getMessage());
                prevFinTxfer = null;
            }

            if (prevFinTxfer != null) {
                logger.debug(logPrfx + " --- qry1 result is Id: " + prevFinTxfer.getId());
            }
        }
        logger.trace(logPrfx + " <-- ");
        return prevFinTxfer;
    }

    private void reloadFinStmtItm1_Desc1List(){
        String logPrfx = "reloadFinStmtItm1_Desc1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc1"
                + " from ampata_GenNode e"
                + " where e.className = 'FinTxfer'"
                + " and e.finStmtItm1_Desc1 IS NOT NULL"
                + " order by e.finStmtItm1_Desc1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_finStmtItm1_Desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called tmplt_finStmtItm1_Desc1Field.setOptionsList()");

        finStmtItm1_Desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Desc2List(){
        String logPrfx = "reloadFinStmtItm1_Desc2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc2"
                + " from ampata_GenNode e"
                + " where e.className = 'FinTxfer'"
                + " and e.finStmtItm1_Desc2 IS NOT NULL"
                + " order by e.finStmtItm1_Desc2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_finStmtItm1_Desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called tmplt_finStmtItm1_Desc2Field.setOptionsList()");

        finStmtItm1_Desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc2Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Desc3List(){
        String logPrfx = "reloadFinStmtItm1_Desc3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc3"
                + " from ampata_GenNode e"
                + " where e.className = 'FinTxfer'"
                + " and e.finStmtItm1_Desc3 IS NOT NULL"
                + " order by e.finStmtItm1_Desc3"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_finStmtItm1_Desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called tmplt_finStmtItm1_Desc3Field.setOptionsList()");

        finStmtItm1_Desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc3Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "addEnteredTextToComboBoxOptionsList";
        logger.trace(logPrfx + " --> ");

        String text = enterPressEvent.getText();
        if (!text.equals("<null>")){
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

        logger.trace(logPrfx + " <-- ");
    }


}
