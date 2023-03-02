package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNodeType;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinHow;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinTxactSetQryMngr;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinWhat;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinWhy;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenChan;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenDocVer;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
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

@UiController("enty_UsrFinTxactSet.main")
@UiDescriptor("usr-fin-txact-set-0-main.xml")
@LookupComponent("tableMain")
public class UsrFinTxactSet0Main extends MasterDetailScreen<UsrBaseNode> {

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
    private UsrFinTxactSetQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<UsrBaseNodeType> filterConfig1A_Type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtLE;

    @Autowired
    protected PropertyFilter<UsrGenChan> filterConfig1A_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrGenChan> filterConfig1A_GenChan2_Id;

    @Autowired
    protected PropertyFilter<UsrFinHow> filterConfig1A_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrFinWhat> filterConfig1A_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrFinWhy> filterConfig1A_FinWhy1_Id;


    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_IdDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_IdDtLE;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_IdX;


    //Template
    @Autowired
    protected EntityComboBox<UsrBaseNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1ElTsField;

    @Autowired
    protected CheckBox tmplt_Ts1ElTsFieldChk;

    @Autowired
    protected TextField<Integer> tmplt_IdXField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdXFieldRdo;

    @Autowired
    protected EntityComboBox<UsrGenChan> tmplt_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrGenChan> tmplt_GenChan2_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan2_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrFinHow> tmplt_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrFinWhat> tmplt_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrFinWhy> tmplt_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhy1_IdFieldChk;


    //Toolbar
    @Autowired
    protected CheckBox updateFilterConfig1A_IdDtLE_SyncChk;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdDtGERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdXRdo;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrBaseNode> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrBaseNode> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrBaseNode> instCntnrMain;
    @Autowired
    private Table<UsrBaseNode> tableMain;

    //Type data container and loader
    private CollectionContainer<UsrBaseNodeType> colCntnrType;
    private CollectionLoader<UsrBaseNodeType> colLoadrType;

    //Other data containers, loaders and table
    private CollectionContainer<UsrGenChan> colCntnrGenChan;
    private CollectionLoader<UsrGenChan> colLoadrGenChan;

    private CollectionContainer<UsrGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrGenDocVer> colLoadrGenDocVer;

    private CollectionContainer<UsrGenTag> colCntnrGenTag;
    private CollectionLoader<UsrGenTag> colLoadrGenTag;

    private CollectionContainer<UsrBaseNode> finCurcysDc;
    private CollectionLoader<UsrBaseNode> finCurcysDl;

    private CollectionContainer<UsrFinHow> colCntnrFinHow;
    private CollectionLoader<UsrFinHow> colLoadrFinHow;

    private CollectionContainer<UsrFinWhat> colCntnrFinWhat;
    private CollectionLoader<UsrFinWhat> colLoadrFinWhat;

    private CollectionContainer<UsrFinWhy> colCntnrFinWhy;
    private CollectionLoader<UsrFinWhy> colLoadrFinWhy;

    private CollectionContainer<UsrGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrGenFmla> colLoadrGenFmla;

    private CollectionContainer<UsrBaseNode> colCntnrFinTxactItm;
    private CollectionLoader<UsrBaseNode> colLoadrFinTxactItm;


    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrBaseNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<UsrGenChan> genChan1_IdField;

    @Autowired
    private EntityComboBox<UsrGenChan> genChan2_IdField;

    @Autowired
    private EntityComboBox<UsrFinHow>  finHow1_IdField;

    @Autowired
    private ComboBox<String> whatText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhat>  finWhat1_IdField;

    @Autowired
    private ComboBox<String> whyText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhy>  finWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenFmla> desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrBaseNode> desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrBaseNode> desc1FinTxactItm2_IdField;

    
    @Autowired
    private EntityComboBox<UsrGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag1_IdField;



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


        colCntnrType = dataComponents.createCollectionContainer(UsrBaseNodeType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrFinTxactSetType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSetType_Inst = fetchPlans.builder(UsrBaseNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnFinTxactSetType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);

        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //filter
        EntityComboBox<UsrBaseNodeType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id= (EntityComboBox<UsrBaseNodeType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrGenChan e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenChan_Inst = fetchPlans.builder(UsrGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(fchPlnGenChan_Inst);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(colCntnrGenChan);
        genChan2_IdField.setOptionsContainer(colCntnrGenChan);
        EntityComboBox<UsrGenChan> propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrGenChan>) filterConfig1A_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);
        EntityComboBox<UsrGenChan> propFilterCmpnt_GenChan2_Id = (EntityComboBox<UsrGenChan>) filterConfig1A_GenChan2_Id.getValueComponent();
        propFilterCmpnt_GenChan2_Id.setOptionsContainer(colCntnrGenChan);

        
        colCntnrFinHow = dataComponents.createCollectionContainer(UsrFinHow.class);
        colLoadrFinHow = dataComponents.createCollectionLoader();
        colLoadrFinHow.setQuery("select e from enty_UsrFinHow e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinHow_Inst = fetchPlans.builder(UsrFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinHow.setFetchPlan(fchPlnFinHow_Inst);
        colLoadrFinHow.setContainer(colCntnrFinHow);
        colLoadrFinHow.setDataContext(getScreenData().getDataContext());

        finHow1_IdField.setOptionsContainer(colCntnrFinHow);


        colCntnrFinWhat = dataComponents.createCollectionContainer(UsrFinWhat.class);
        colLoadrFinWhat = dataComponents.createCollectionLoader();
        colLoadrFinWhat.setQuery("select e from enty_UsrFinWhat e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhat_Inst = fetchPlans.builder(UsrFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhat.setFetchPlan(fchPlnFinWhat_Inst);
        colLoadrFinWhat.setContainer(colCntnrFinWhat);
        colLoadrFinWhat.setDataContext(getScreenData().getDataContext());

        finWhat1_IdField.setOptionsContainer(colCntnrFinWhat);


        colCntnrFinWhy = dataComponents.createCollectionContainer(UsrFinWhy.class);
        colLoadrFinWhy = dataComponents.createCollectionLoader();
        colLoadrFinWhy.setQuery("select e from enty_UsrFinWhy e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhy_Inst = fetchPlans.builder(UsrFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhy.setFetchPlan(fchPlnFinWhy_Inst);
        colLoadrFinWhy.setContainer(colCntnrFinWhy);
        colLoadrFinWhy.setDataContext(getScreenData().getDataContext());

        finWhy1_IdField.setOptionsContainer(colCntnrFinWhy);


        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla_Inst = fetchPlans.builder(UsrGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla_Inst);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());

        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);

        colCntnrFinTxactItm = dataComponents.createCollectionContainer(UsrBaseNode.class);
        colLoadrFinTxactItm = dataComponents.createCollectionLoader();
        colLoadrFinTxactItm.setQuery("select e from enty_UsrFinTxactItm e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactItm_Inst = fetchPlans.builder(UsrBaseNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactItm.setFetchPlan(fchPlnFinTxactItm_Inst);
        colLoadrFinTxactItm.setContainer(colCntnrFinTxactItm);
        colLoadrFinTxactItm.setDataContext(getScreenData().getDataContext());

        desc1FinTxactItm1_IdField.setOptionsContainer(colCntnrFinTxactItm);
        desc1FinTxactItm2_IdField.setOptionsContainer(colCntnrFinTxactItm);


        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrGenDocVer.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrGenDocVer e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenDocVer_Inst = fetchPlans.builder(UsrGenDocVer.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(fchPlnGenDocVer_Inst);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag_Inst = fetchPlans.builder(UsrGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag_Inst);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);


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
    public void onInitEntity(InitEntityEvent<UsrBaseNode> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        UsrBaseNode thisFinTxactSet = event.getEntity();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSet.setClassName("UsrFinTxactSet");
        logger.debug(logPrfx + " --- className: UsrFinTxactSet");

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

        tableMain.sort("id2", Table.SortDirection.ASCENDING);

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


    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinTxactsDcItemChange(InstanceContainer.ItemChangeEvent<UsrBaseNode> event) {
        String logPrfx = "onFinTxactSetsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrBaseNode thisFinTxactSet = event.getItem();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSet.setClassName("UsrFinTxactSet");
        logger.debug(logPrfx + " --- className: UsrFinTxactSet");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "tableMain.[n1s1Inst1Dt1.elDt]", subject = "formatter")
    private String tableMainIdDtDate1Formatter(LocalDate ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

        reloadWhatText1List();
        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

        reloadWhyText1List();
        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");


        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing qryMngr.execPrUpdAllCalcValsforAllRowsNative()");
        qryMngr.execPrUpdAllCalcValsforAllRowsNative();
        logger.debug(logPrfx + " --- finished qryMngr.execPrUpdAllCalcValsforAllRowsNative()");

        logger.debug(logPrfx + " --- loading colLoadrFinTxactItm.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrFinTxactItm.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deleteColDeletedColBtn")
    public void onDeleteColDeletedColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColDeletedColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing qryMngr.execPrDelDeletedNative()");
        qryMngr.execPrDelDeletedNative();
        logger.debug(logPrfx + " --- finished qryMngr.execPrDelDeletedNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deleteColOrphansBtn")
    public void onDeleteColOrphansBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColOrphansBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing qryMngr.execPrDelOrphanNative()");
        qryMngr.execPrDelOrphanNative();
        logger.debug(logPrfx + " --- finished qryMngr.execPrDelOrphanNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrBaseNode> sels = new ArrayList<>();

        thisFinTxactSets.forEach(orig -> {
            UsrBaseNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDate idDt1 = copy.getNm1s1Inst1Dt1() != null ? copy.getNm1s1Inst1Dt1().getElDt() : null;
            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                copy.getTs1().setElTs(tmplt_Ts1ElTsField.getValue());
                updateIdDt(copy);
            }

            Integer idX = copy.getNm1s1Inst1Int1();
            if (tmplt_IdXFieldRdo.getValue() != null){
                // Set
                if (tmplt_IdXFieldRdo.getValue() == 1){
                    idX = tmplt_IdXField.getValue();
                    copy.setNm1s1Inst1Int1(idX);
                }
                // Max
                else if (tmplt_IdXFieldRdo.getValue() == 2
                        && idDt1 != null) {

                    idX = getIdXMax(idDt1);
                    if (idX == null) return;
                    copy.setNm1s1Inst1Int1(idX);
                }
            }

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (!Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setNm1s1Inst1Int2(copy.getNm1s1Inst1Int2() == null ? 1 : copy.getNm1s1Inst1Int2() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            updateCalcVals(copy);

            UsrBaseNode savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        tableMain.sort("id2", Table.SortDirection.ASCENDING);
        tableMain.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxactSets.forEach(thisFinTxactSet -> {
            UsrBaseNode thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
            thisFinTxactSet = thisTrackedFinTxactSet;
            if (thisFinTxactSet != null) {

                Boolean thisFinTxactSetIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                LocalDate idDt1 = thisFinTxactSet.getNm1s1Inst1Dt1() != null ? thisFinTxactSet.getNm1s1Inst1Dt1().getElDt() : null;
                if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.getTs1().setElTs(tmplt_Ts1ElTsField.getValue());
                    updateIdDt(thisFinTxactSet);
                }

                Integer idX = thisFinTxactSet.getNm1s1Inst1Int1();
                if (tmplt_IdXFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1){
                        thisFinTxactSetIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinTxactSet.setNm1s1Inst1Int1(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactSetIsChanged = true;
                        idX = getIdXMax(idDt1);
                        if (idX == null) return;
                        thisFinTxactSet.setNm1s1Inst1Int1(idX);
                    }
                }

                if (tmplt_GenChan1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setGenChan1_Id(tmplt_GenChan1_IdField.getValue());
                }

                if (tmplt_GenChan2_IdFieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setGenChan2_Id(tmplt_GenChan2_IdField.getValue());
                }

                if (tmplt_FinHow1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setFinHow1_Id(tmplt_FinHow1_IdField.getValue());
                }

                if (tmplt_WhatText1FieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setWhatText1(tmplt_WhatText1Field.getValue());
                }

                if (tmplt_FinWhat1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setFinWhat1_Id(tmplt_FinWhat1_IdField.getValue());
                }

                if (tmplt_WhyText1FieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setWhyText1(tmplt_WhyText1Field.getValue());
                }

                if (tmplt_FinWhy1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactSetIsChanged = true;
                    thisFinTxactSet.setFinWhy1_Id(tmplt_FinWhy1_IdField.getValue());
                }


                thisFinTxactSetIsChanged = updateId2Calc(thisFinTxactSet) || thisFinTxactSetIsChanged;
                thisFinTxactSetIsChanged = updateId2(thisFinTxactSet) || thisFinTxactSetIsChanged;
                thisFinTxactSetIsChanged = updateId2Cmp(thisFinTxactSet) || thisFinTxactSetIsChanged;

                if (thisFinTxactSetIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                    //dataManager.save(thisFinTxactSet);
                }

            }
        });
        updateFinTxactSetHelper();
        logger.trace(logPrfx + " <-- ");
    }


    private void updateFinTxactSetHelper() {
        String logPrfx = "updateFinTxactSetHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinTxactSets.forEach(thisFinTxactSet -> {
                //UsrFinTxactSet thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
                if (thisFinTxactSet != null) {
                    UsrBaseNode thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
                    thisFinTxactSet = thisTrackedFinTxactSet;

                    Boolean thisFinTxactSetIsChanged = false;

                    thisFinTxactSetIsChanged = updateId2Dup(thisFinTxactSet) || thisFinTxactSetIsChanged;

                    if (thisFinTxactSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                        //dataManager.save(thisFinTxactSet);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisFinTxactSets);
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
                List<UsrBaseNode> thisFinTxacts = tableMain.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrBaseNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getNm1s1Inst1Int1() != null && thisFinTxact.getNm1s1Inst1Dt1().getElDt() != null){
                        filterConfig1B_IdDtGE.setValue(thisFinTxact.getNm1s1Inst1Dt1().getElDt());
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
                List<UsrBaseNode> thisFinTxacts = tableMain.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrBaseNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getNm1s1Inst1Int1() != null){
                        filterConfig1B_IdX.setValue(thisFinTxact.getNm1s1Inst1Int1());
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

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSets.forEach(thisFinTxactSet -> {
            if (thisFinTxactSet != null) {
                UsrBaseNode thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
                thisFinTxactSet = thisTrackedFinTxactSet;

                Boolean thisFinTxactSetIsChanged = false;

                LocalDate idDt1 = thisFinTxactSet.getNm1s1Inst1Dt1() != null ? thisFinTxactSet.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactSet.getNm1s1Inst1Int1();
                    Integer idX = 0;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactSet.setNm1s1Inst1Int1(idX);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setIdX(" + (idX) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Calc(thisFinTxactSet) || thisFinTxactSetIsChanged;
                    String id2_ = thisFinTxactSet.getId2();
                    String id2 = thisFinTxactSet.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactSet.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setId2(" + (id2) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Cmp(thisFinTxactSet) || thisFinTxactSetIsChanged;

                    if (thisFinTxactSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                        //dataManager.save(thisFinTxactSet);
                    }
                }
            }
        });

        updateFinTxactSetHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSets.forEach(thisFinTxactSet -> {
            if (thisFinTxactSet != null) {
                UsrBaseNode thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
                thisFinTxactSet = thisTrackedFinTxactSet;

                Boolean thisFinTxactSetIsChanged = false;

                LocalDate idDt1 = thisFinTxactSet.getNm1s1Inst1Dt1() != null ? thisFinTxactSet.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactSet.getNm1s1Inst1Int1();
                    Integer idX = thisFinTxactSet.getNm1s1Inst1Int1() == null || thisFinTxactSet.getNm1s1Inst1Int1() == 0 || thisFinTxactSet.getNm1s1Inst1Int1() == 1 ? 0 : thisFinTxactSet.getNm1s1Inst1Int1() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactSet.setNm1s1Inst1Int1(idX);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setIdX(" + (idX) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Calc(thisFinTxactSet) || thisFinTxactSetIsChanged;
                    String id2_ = thisFinTxactSet.getId2();
                    String id2 = thisFinTxactSet.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactSet.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setId2(" + (id2) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Cmp(thisFinTxactSet) || thisFinTxactSetIsChanged;

                    if (thisFinTxactSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                        //dataManager.save(thisFinTxactSet);
                    }
                }
            }
        });

        updateFinTxactSetHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdXBtn")
    public void onIncIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSets.forEach(thisFinTxactSet -> {
            if (thisFinTxactSet != null) {
                UsrBaseNode thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
                thisFinTxactSet = thisTrackedFinTxactSet;

                Boolean thisFinTxactSetIsChanged = false;

                LocalDate idDt1 = thisFinTxactSet.getNm1s1Inst1Dt1() != null ? thisFinTxactSet.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactSet.getNm1s1Inst1Int1();
                    Integer idX = (thisFinTxactSet.getNm1s1Inst1Int1() == null ? 0 : thisFinTxactSet.getNm1s1Inst1Int1()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactSet.setNm1s1Inst1Int1(idX);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setIdX(" + (idX) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Calc(thisFinTxactSet) || thisFinTxactSetIsChanged;
                    String id2_ = thisFinTxactSet.getId2();
                    String id2 = thisFinTxactSet.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactSet.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setId2(" + (id2) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Cmp(thisFinTxactSet) || thisFinTxactSetIsChanged;

                    if (thisFinTxactSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                        //dataManager.save(thisFinTxactSet);
                    }
                }
            }
        });

        updateFinTxactSetHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("maxIdXBtn")
    public void onMaxIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSets.forEach(thisFinTxactSet -> {
            if (thisFinTxactSet != null) {
                UsrBaseNode thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
                thisFinTxactSet = thisTrackedFinTxactSet;

                Boolean thisFinTxactSetIsChanged = false;

                LocalDate idDt1 = thisFinTxactSet.getNm1s1Inst1Dt1() != null ? thisFinTxactSet.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from enty_UsrFinTxactSet e"
                            + " where e.n1s1Inst1Dt1.elDt = :idDt1";
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

                    Integer idX_ = thisFinTxactSet.getNm1s1Inst1Int1();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactSet.setNm1s1Inst1Int1(idX);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setIdX(" + (idX) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Calc(thisFinTxactSet) || thisFinTxactSetIsChanged;
                    String id2_ = thisFinTxactSet.getId2();
                    String id2 = thisFinTxactSet.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactSet.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactSet.setId2(" + (id2) + ")");
                        thisFinTxactSetIsChanged = true;
                    }

                    thisFinTxactSetIsChanged = updateId2Cmp(thisFinTxactSet) || thisFinTxactSetIsChanged;

                    if (thisFinTxactSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                        //dataManager.save(thisFinTxactSet);
                    }
                }

            }
        });

        updateFinTxactSetHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSets.forEach(thisFinTxactSet -> {
            if (thisFinTxactSet != null) {
                thisFinTxactSet = dataContext.merge(thisFinTxactSet);
                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinTxactSet);
            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisFinTxactSets);
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

        List<UsrBaseNode> thisFinTxactSets = tableMain.getSelected().stream().toList();
        if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactSets.forEach(thisFinTxactSet -> {
            if (thisFinTxactSet != null) {
                 thisFinTxactSet = dataContext.merge(thisFinTxactSet);

                boolean isChanged = false;

                updateId2(thisFinTxactSet);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisFinTxactSets);
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

        UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateCalcVals(thisFinTxactSet);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinTxactSet);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
            if (thisFinTxactSet == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Dup(thisFinTxactSet);
        }
        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinTxactSet);
        updateId2Cmp(thisFinTxactSet);
        updateId2Dup(thisFinTxactSet);

        logger.debug(logPrfx + " --- id2: " + thisFinTxactSet.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxactSet);
        updateId2Cmp(thisFinTxactSet);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinTxactSet.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxactSet);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinTxactSet);

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


    @Subscribe("updateGenChan1_IdFieldListBtn")
    public void onUpdateGenChan1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenChan2_IdFieldListBtn")
    public void onUpdateGenChan2_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan2_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinHow1_IdFieldListBtn")
    public void onUpdateFinHow1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinHow1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

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

        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

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

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("ts1ElTsField")
    public void onTs1ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
            if (thisFinTxactSet == null) {
                logger.debug(logPrfx + " --- thisFinTxactSet is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateIdDt(thisFinTxactSet);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
            if (thisFinTxactSet == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxactSet);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDescPat1_IdFieldListBtn")
    public void onUpdateDescPat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDescPat1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateDesc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FinTxactItm2_IdFieldListBtn")
    public void onUpdateDesc1FinTxactItm2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FinTxactItm2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

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


    private Boolean updateCalcVals(@NotNull UsrBaseNode thisFinTxactSet) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactSetCalcVals(thisFinTxactSet) || isChanged;

        
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxactSetCalcVals(@NotNull UsrBaseNode thisFinTxactSet) {
        String logPrfx = "updateFinTxactSetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxactSet Object
        isChanged = updateIdDt(thisFinTxactSet) || isChanged;
        isChanged = updateId2Calc(thisFinTxactSet) || isChanged;
        isChanged = updateId2Cmp(thisFinTxactSet) || isChanged;
        isChanged = updateId2Dup(thisFinTxactSet) || isChanged;

        isChanged = updateDesc1(thisFinTxactSet) || isChanged;
        isChanged = updateFinTxacts1_IdCntCalc(thisFinTxactSet)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private Boolean updateId2(@NotNull UsrBaseNode thisFinTxactSet) {
        // Assume thisFinTxactSet is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinTxactSet.getId2();
        String id2 = thisFinTxactSet.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinTxactSet.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2Calc(UsrBaseNode thisFinTxactSet){
        // Assume thisFinTxactSet is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinTxactSet.getId2Calc();
        String id2Calc = thisFinTxactSet.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinTxactSet.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2Cmp(UsrBaseNode thisFinTxactSet) {
        // Assume thisFinTxactSet is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinTxactSet.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinTxactSet.getId2(),thisFinTxactSet.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinTxactSet.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateId2Dup(UsrBaseNode thisFinTxactSet) {
        // Assume thisFinTxactSet is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxactSet.getId2Dup();
        if (thisFinTxactSet.getId2() != null) {
            String id2Qry = "select count(e) from enty_UsrFinTxactSet e where e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinTxactSet.getId())
                        .parameter("id2", thisFinTxactSet.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinTxactSet.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinTxactSet.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateDesc1(@NotNull UsrBaseNode thisFinTxactSet) {
        // Assume thisFinTxactSet is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        if (thisFinTxactSet != null) {
            //finTxactSet is a 2nd ref (ref of a ref) of finTxactItm and is not automatically loaded into the dataContext
            thisFinTxactSet = dataContext.merge(thisFinTxactSet);

            String desc1_ = thisFinTxactSet.getDesc1();

            if (thisFinTxactSet.getDesc1GenFmla1_Id() == null) {

                //thisType
                String thisType = "";
                if (thisFinTxactSet.getType1_Id() != null) {
                    thisType = Objects.toString(thisFinTxactSet.getType1_Id().getId2(), "");
                }
                logger.debug(logPrfx + " --- thisType: " + thisType);

                UsrBaseNode desc1FinTxactItm1 = thisFinTxactSet.getDesc1Node1_Id() == null
                        ? findFirstFinTxactItmLikeId2(thisFinTxactSet.getId2() + "/%")
                        : thisFinTxactSet.getDesc1Node1_Id();


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
                    if (thisType.contains("Exch")) {
                        UsrBaseNode desc1FinTxactItm2 = thisFinTxactSet.getDesc1Node2_Id() == null
                                ? findFirstFinTxactItmLikeId2(thisFinTxactSet.getId2() + "/Y01/%")
                                : thisFinTxactSet.getDesc1Node2_Id();

                        if (desc1FinTxactItm2 != null) {
                            thisAmt = thisAmt + " -> ";
                            if (desc1FinTxactItm2.getAmtDebt() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtDebt();
                            } else if (desc1FinTxactItm2.getAmtCred() != null) {
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
                if (thisFinTxactSet.getGenChan1_Id() != null) {
                    thisChan = Objects.toString(thisFinTxactSet.getGenChan1_Id().getId2(), "");
                }
                if (!thisChan.equals("")) {
                    thisChan = "in chan [" + thisChan + "]";
                }
                logger.debug(logPrfx + " --- thisChan: " + thisChan);


                String thisHow = "";
                if (thisFinTxactSet.getFinHow1_Id() != null) {
                    thisHow = Objects.toString(thisFinTxactSet.getFinHow1_Id().getId2(), "");
                }
                if (!thisHow.equals("")) {
                    thisHow = "via [" + thisHow + "]";
                }
                logger.debug(logPrfx + " --- thisHow: " + thisHow);

                String thisWhat = Objects.toString(thisFinTxactSet.getWhatText1(), "");
                if (thisFinTxactSet.getFinWhat1_Id() != null) {
                    thisWhat = thisWhat + " " + Objects.toString(thisFinTxactSet.getFinWhat1_Id().getId2());
                }
                if (!thisWhat.equals("")) {
                    thisWhat = "for " + thisWhat.trim();
                }
                logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

                String thisWhy = Objects.toString(thisFinTxactSet.getWhyText1(), "");
                if (thisFinTxactSet.getFinWhy1_Id() != null) {
                    thisWhy = thisWhy + " " + Objects.toString(thisFinTxactSet.getFinWhy1_Id().getId2());
                }
                if (!thisWhy.equals("")) {
                    thisWhy = "for " + thisWhy.trim();
                }
                logger.debug(logPrfx + " --- thisWhy: " + thisWhy);

                String thisDocVer = "";
                if (thisFinTxactSet.getGenDocVer1_Id() != null) {
                    thisDocVer = Objects.toString(thisFinTxactSet.getGenDocVer1_Id().getId2());
                }
                if (!thisDocVer.equals("")) {
                    thisDocVer = "doc ver " + thisDocVer;
                }
                logger.debug(logPrfx + " --- thisDocVer: " + thisDocVer);

                String thisTag = "";
                String thisTag1 = "";
                if (thisFinTxactSet.getGenTag1_Id() != null) {
                    thisTag1 = Objects.toString(thisFinTxactSet.getGenTag1_Id().getId2());
                }
                String thisTag2 = "";
                if (thisFinTxactSet.getGenTag1_Id() != null) {
                    thisTag2 = Objects.toString(thisFinTxactSet.getGenTag2_Id().getId2());
                }
                String thisTag3 = "";
                if (thisFinTxactSet.getGenTag1_Id() != null) {
                    thisTag3 = Objects.toString(thisFinTxactSet.getGenTag3_Id().getId2());
                }
                String thisTag4 = "";
                if (thisFinTxactSet.getGenTag1_Id() != null) {
                    thisTag4 = Objects.toString(thisFinTxactSet.getGenTag4_Id().getId2());
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
                    thisFinTxactSet.setDesc1(desc1);
                    logger.debug(logPrfx + " --- desc1: " + desc1);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull UsrBaseNode thisFinTxactSet) {
        // Assume thisFinTxactSet is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactSet.updateNm1s1Inst1Dt1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private void reloadWhatText1List(){
        String logPrfx = "reloadWhatText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whatText1"
                + " from enty_UsrFinTxactSet e"
                + " where e.whatText1 is not null"
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
                + " from enty_UsrFinTxactSet e"
                + " where e.whyText1 is not null"
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
        String idXMaxQry = "select max(e.idX)"
                + " from enty_UsrFinTxactSet e"
                + " where e.n1s1Inst1Dt1.elDt = :idDt1";
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
        String idXCntIsNullQry = "select count(e)"
                + " from enty_UsrFinTxactSet e"
                + " where e.n1s1Inst1Dt1.elDt = :idDt1"
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

        UsrBaseNode thisFinTxactSet = instCntnrMain.getItemOrNull();
        if (thisFinTxactSet == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxacts1_IdCntCalc(thisFinTxactSet);

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateFinTxacts1_IdCntCalc(@NotNull UsrBaseNode thisFinTxactSet) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxacts1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer idCntCalc_ = thisFinTxactSet.getFinTxacts1_IdCntCalc();
        Integer idCntCalc = null ;
        String qry1 = "select count(e.id2) from enty_UsrFinTxact e where e.finTxactSet1_Id = :finTxactSet1_Id";
        try{
            idCntCalc = dataManager.loadValue(qry1,Integer.class)
                    .store("main")
                    .parameter("finTxactSet1_Id",thisFinTxactSet)
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
            thisFinTxactSet.setFinTxacts1_IdCntCalc(idCntCalc);
            logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private UsrBaseNode findFirstFinTxactItmLikeId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFirstFinTxactItmLikeId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrFinTxactItm e where e.id2 like :id2 order by e.sortKey, e.id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        UsrBaseNode finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(UsrBaseNode.class)
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