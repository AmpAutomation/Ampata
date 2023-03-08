package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactQryMngr;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@UiController("enty_UsrNodeFinTxact.main")
@UiDescriptor("usr-node-fin-txact-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinTxact0Main extends MasterDetailScreen<UsrNodeBase> {


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
    private UsrNodeFinTxactQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    // filterConfig1A
    @Autowired
    protected PropertyFilter<UsrNodeBaseType> filterConfig1A_Type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtLE;


    // filterConfig1A
    @Autowired
    protected PropertyFilter<UsrNodeBaseType> filterConfig1B_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeBaseType> filterConfig1A_FinTxactSet1_Id_type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1A_FinTxactSet1_Id_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1A_FinTxactSet1_Id_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1A_FinTxactSet1_Id_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1A_FinTxactSet1_Id_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1A_FinTxactSet1_Id_GenTag1_Id;


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
    protected PropertyFilter<UsrNodeBaseType> filterConfig1B_FinTxactSet1_Id_type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1B_FinTxactSet1_Id_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1B_FinTxactSet1_Id_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1B_FinTxactSet1_Id_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1B_FinTxactSet1_Id_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1B_FinTxactSet1_Id_GenTag1_Id;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsTxsetOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxsetOption;

    @Autowired
    private CheckBox linkTxsetAutoCreateChk;

    @Autowired
    protected CheckBox updateFilterConfig1A_IdDtLE_SyncChk;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdDtGERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdXRdo;


    //Template
    @Autowired
    protected EntityComboBox<UsrNodeBaseType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxactSet1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_EI1_RoleFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1ElTsField;

    @Autowired
    protected CheckBox tmplt_Ts1ElTsFieldChk;

    @Autowired
    protected TextField<Integer> tmplt_IdXField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdXFieldRdo;

    @Autowired
    protected TextField<Integer> tmplt_IdYField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdYFieldRdo;


    //Template (FinTxactSet)
    @Autowired
    protected EntityComboBox<UsrNodeBaseType> tmplt_FinTxactSet1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_Id_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeGenChan> tmplt_FinTxactSet1_Id_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_Id_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinHow> tmplt_FinTxactSet1_Id_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_Id_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxactSet1_Id_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_Id_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhat> tmplt_FinTxactSet1_Id_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_Id_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxactSet1_Id_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_Id_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhy> tmplt_FinTxactSet1_Id_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxactSet1_Id_FinWhy1_IdFieldChk;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNodeBase> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNodeBase> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrNodeBase> instCntnrMain;
    @Autowired
    private Table<UsrNodeBase> tableMain;


    //Type data container and loader
    private CollectionContainer<UsrNodeBaseType> colCntnrType;
    private CollectionLoader<UsrNodeBaseType> colLoadrFinTxactType;


    //Other data loaders, containers and tables
    private CollectionContainer<UsrNodeGenChan> colCntnrGenChan;
    private CollectionLoader<UsrNodeGenChan> colLoadrGenChan;

    private CollectionContainer<UsrNodeGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrNodeGenDocVer> colLoadrGenDocVer;

    private CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    private CollectionLoader<UsrItemGenTag> colLoadrGenTag;

    private CollectionContainer<UsrNodeBase> colCntnrFinTxactSet;
    private CollectionLoader<UsrNodeBase> colLoadrFinTxactSet;

    private CollectionContainer<UsrNodeBaseType> colCntnrFinTxactSetType;
    private CollectionLoader<UsrNodeBaseType> colLoadrFinTxactSetType;

    private CollectionContainer<UsrNodeBase> colCntnrFinTxactItm;
    private CollectionLoader<UsrNodeBase> colLoadrFinTxactItm;

    private CollectionContainer<UsrItemFinHow> colCntnrFinHow;
    private CollectionLoader<UsrItemFinHow> colLoadrFinHow;

    private CollectionContainer<UsrItemFinWhat> colCntnrFinWhat;
    private CollectionLoader<UsrItemFinWhat> colLoadrFinWhat;

    private CollectionContainer<UsrItemFinWhy> colCntnrFinWhy;
    private CollectionLoader<UsrItemFinWhy> colLoadrFinWhy;

    private CollectionContainer<UsrItemGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrItemGenFmla> colLoadrGenFmla;


    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrNodeBaseType> type1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> genTag1_IdField;


    // Field (FinTxactSet)
    @Autowired
    private EntityComboBox<UsrNodeBase> finTxactSet1_IdField;

    @Autowired
    private TextField<String> finTxactSet1_Id2TrgtField;

    @Autowired
    private EntityComboBox<UsrNodeBaseType> finTxactSet1_Id_Type1_IdField;

    @Autowired
    private ComboBox<String> finTxactSet1_EI1_RoleField;

    @Autowired
    private EntityComboBox<UsrNodeGenChan> finTxactSet1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrItemFinHow> finTxactSet1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxactSet1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhat>  finTxactSet1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxactSet1_Id_WhyText1Field;
    @Autowired
    private EntityComboBox<UsrItemFinWhy>  finTxactSet1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> finTxactSet1_Id_Desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finTxactSet1_Id_Desc1FinTxactItm1_IdField;



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


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrFinTxactType = dataComponents.createCollectionLoader();
        colLoadrFinTxactType.setQuery("select e from enty_UsrNodeFinTxactType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactType.setFetchPlan(fchPlnFinTxactType_Inst);
        colLoadrFinTxactType.setContainer(colCntnrType);
        colLoadrFinTxactType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //filter
        EntityComboBox<UsrNodeBaseType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeBaseType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeBaseType>) filterConfig1B_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrNodeGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrNodeGenChan e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenChan_Inst = fetchPlans.builder(UsrNodeGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(fchPlnGenChan_Inst);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());
        finTxactSet1_Id_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        //template
        tmplt_FinTxactSet1_Id_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        //filter
        EntityComboBox<UsrNodeGenChan> propFilterCmpnt_GenChan1_Id;
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1A_FinTxactSet1_Id_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1B_FinTxactSet1_Id_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);


        colCntnrFinHow = dataComponents.createCollectionContainer(UsrItemFinHow.class);
        colLoadrFinHow = dataComponents.createCollectionLoader();
        colLoadrFinHow.setQuery("select e from enty_UsrItemFinHow e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinHow_Inst = fetchPlans.builder(UsrItemFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinHow.setFetchPlan(fchPlnFinHow_Inst);
        colLoadrFinHow.setContainer(colCntnrFinHow);
        colLoadrFinHow.setDataContext(getScreenData().getDataContext());

        finTxactSet1_Id_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        //template
        tmplt_FinTxactSet1_Id_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        //filter
        EntityComboBox<UsrItemFinHow> propFilterCmpnt_FinHow1_Id;
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1A_FinTxactSet1_Id_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(colCntnrFinHow);
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1B_FinTxactSet1_Id_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(colCntnrFinHow);


        colCntnrFinWhat = dataComponents.createCollectionContainer(UsrItemFinWhat.class);
        colLoadrFinWhat = dataComponents.createCollectionLoader();
        colLoadrFinWhat.setQuery("select e from enty_UsrItemFinWhat e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhat_Inst = fetchPlans.builder(UsrItemFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhat.setFetchPlan(fchPlnFinWhat_Inst);
        colLoadrFinWhat.setContainer(colCntnrFinWhat);
        colLoadrFinWhat.setDataContext(getScreenData().getDataContext());

        finTxactSet1_Id_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        //template
        tmplt_FinTxactSet1_Id_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        //filter
        EntityComboBox<UsrItemFinWhat> propFilterCmpnt_FinWhat1_Id;
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1A_FinTxactSet1_Id_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(colCntnrFinWhat);
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1B_FinTxactSet1_Id_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(colCntnrFinWhat);


        colCntnrFinWhy = dataComponents.createCollectionContainer(UsrItemFinWhy.class);
        colLoadrFinWhy = dataComponents.createCollectionLoader();
        colLoadrFinWhy.setQuery("select e from enty_UsrItemFinWhy e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhy_Inst = fetchPlans.builder(UsrItemFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhy.setFetchPlan(fchPlnFinWhy_Inst);
        colLoadrFinWhy.setContainer(colCntnrFinWhy);
        colLoadrFinWhy.setDataContext(getScreenData().getDataContext());

        finTxactSet1_Id_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        //template
        tmplt_FinTxactSet1_Id_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        //filter
        EntityComboBox<UsrItemFinWhy> propFilterCmpnt_FinWhy1_Id;
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1A_FinTxactSet1_Id_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(colCntnrFinWhy);
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1B_FinTxactSet1_Id_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(colCntnrFinWhy);

        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrItemGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrItemGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla_Inst = fetchPlans.builder(UsrItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla_Inst);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());

        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        finTxactSet1_Id_Desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);


        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrNodeGenDocVer.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrNodeGenDocVer e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenDocVer_Inst = fetchPlans.builder(UsrNodeGenDocVer.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(fchPlnGenDocVer_Inst);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrItemGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrItemGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag_Inst = fetchPlans.builder(UsrItemGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag_Inst);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);


        colCntnrFinTxactSet = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinTxactSet = dataComponents.createCollectionLoader();
        colLoadrFinTxactSet.setQuery("select e from enty_UsrNodeFinTxactSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSet_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactSet.setFetchPlan(fchPlnFinTxactSet_Inst);
        colLoadrFinTxactSet.setContainer(colCntnrFinTxactSet);
        colLoadrFinTxactSet.setDataContext(getScreenData().getDataContext());

        finTxactSet1_IdField.setOptionsContainer(colCntnrFinTxactSet);


        colCntnrFinTxactSetType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrFinTxactSetType = dataComponents.createCollectionLoader();
        colLoadrFinTxactSetType.setQuery("select e from enty_UsrNodeFinTxactSetType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSetType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactSetType.setFetchPlan(fchPlnFinTxactSetType_Inst);
        colLoadrFinTxactSetType.setContainer(colCntnrFinTxactSetType);
        colLoadrFinTxactSetType.setDataContext(getScreenData().getDataContext());

        finTxactSet1_Id_Type1_IdField.setOptionsContainer(colCntnrFinTxactSetType);
        //filter
        EntityComboBox<UsrNodeBaseType> propFilterCmpnt_FinTxactSetType1_Id;
        propFilterCmpnt_FinTxactSetType1_Id = (EntityComboBox<UsrNodeBaseType>) filterConfig1A_FinTxactSet1_Id_type1_Id.getValueComponent();
        propFilterCmpnt_FinTxactSetType1_Id.setOptionsContainer(colCntnrFinTxactSetType);
        propFilterCmpnt_FinTxactSetType1_Id = (EntityComboBox<UsrNodeBaseType>) filterConfig1B_FinTxactSet1_Id_type1_Id.getValueComponent();
        propFilterCmpnt_FinTxactSetType1_Id.setOptionsContainer(colCntnrFinTxactSetType);


        colCntnrFinTxactItm = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinTxactItm = dataComponents.createCollectionLoader();
        colLoadrFinTxactItm.setQuery("select e from enty_UsrNodeFinTxactItm e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactItm_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactItm.setFetchPlan(fchPlnFinTxactItm_Inst);
        colLoadrFinTxactItm.setContainer(colCntnrFinTxactItm);
        colLoadrFinTxactItm.setDataContext(getScreenData().getDataContext());

        desc1FinTxactItm1_IdField.setOptionsContainer(colCntnrFinTxactItm);
        finTxactSet1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(colCntnrFinTxactItm);


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
    public void onInitEntity(InitEntityEvent<UsrNodeBase> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = event.getEntity();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setClassName("UsrNodeFinTxact");
        logger.debug(logPrfx + " --- className: UsrNodeFinTxact");

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
    public void onFinTxactsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinTxactsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = event.getItem();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setClassName("UsrNodeFinTxact");
        logger.debug(logPrfx + " --- className: UsrNodeFinTxact");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "tableMain.[n1s1Inst1Dt1.elDt]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDate dt) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return dt == null ? null : dt.format(formatter);
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactType.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactType.load() ");

        reloadFinTxactSet1_EI1_RoleList();

        colLoadrFinTxactSetType.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactSetType.load() ");

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

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing qryMngr.execPrUpdAllCalcValsforAllRowsNative()");
        qryMngr.execPrUpdAllCalcValsforAllRowsNative();
        logger.debug(logPrfx + " --- finished qryMngr.execPrUpdAllCalcValsforAllRowsNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deleteColDeletedColBtn")
    public void onDeleteColDeletedColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColDeletedColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing qryMngr.execPrDelDeletedNative()");
        qryMngr.execPrDelOrphanNative();
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeBase> sels = new ArrayList<>();

        thisFinTxacts.forEach(orig -> {
            UsrNodeBase copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_FinTxactSet1_EI1_RoleFieldChk.isChecked()) {
                copy.setFinTxactSet1_EI1_Role(tmplt_FinTxactSet1_EI1_RoleField.getValue());
            }

            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                copy.getTs1().setElTs(tmplt_Ts1ElTsField.getValue());
                updateIdDt(copy);
            }
            LocalDate idDt1 = copy.getNm1s1Inst1Dt1() != null ? copy.getNm1s1Inst1Dt1().getElDt() : null;

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

            Integer idY = copy.getNm1s1Inst1Int2();
            if (tmplt_IdYFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdYFieldRdo.getValue() == 1) {
                    idY = tmplt_IdYField.getValue();
                    copy.setNm1s1Inst1Int2(idY);
                }
                // Max
                else if (tmplt_IdYFieldRdo.getValue() == 2
                        && idDt1 != null) {

                    idY = getIdYMax(idDt1, idX);
                    if (idY == null) return;
                    copy.setNm1s1Inst1Int2(idY);
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
            Integer finTxactSetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);

            updateCalcVals(copy, finTxactSetOption);

            UsrNodeBase savedCopy = dataManager.save(copy);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxacts.forEach(thisFinTxact -> {
            UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            thisFinTxact = thisTrackedFinTxact;
            if (thisFinTxact != null) {

                Boolean thisFinTxactIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactIsChanged = true;
                    thisFinTxact.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                if (tmplt_FinTxactSet1_EI1_RoleFieldChk.isChecked()) {
                    thisFinTxactIsChanged = true;
                    thisFinTxact.setFinTxactSet1_EI1_Role(tmplt_FinTxactSet1_EI1_RoleField.getValue());
                }

                if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                    thisFinTxactIsChanged = true;
                    thisFinTxact.getTs1().setElTs(tmplt_Ts1ElTsField.getValue());
                    updateIdDt(thisFinTxact);
                }
                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;

                Integer idX = thisFinTxact.getNm1s1Inst1Int1();
                if (tmplt_IdXFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1){
                        thisFinTxactIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinTxact.setNm1s1Inst1Int1(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactIsChanged = true;
                        idX = getIdXMax(idDt1);
                        if (idX == null) return;
                        thisFinTxact.setNm1s1Inst1Int1(idX);
                    }
                }

                Integer idY = thisFinTxact.getNm1s1Inst1Int2();
                if (tmplt_IdYFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_IdYFieldRdo.getValue() == 1){
                        thisFinTxactIsChanged = true;
                        idY = tmplt_IdYField.getValue();
                        thisFinTxact.setNm1s1Inst1Int2(idY);
                    }
                    // Max
                    else if (tmplt_IdYFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactIsChanged = true;
                        idY = getIdYMax(idDt1, idX);
                        if (idY == null) return;
                        thisFinTxact.setNm1s1Inst1Int2(idY);
                    }
                }


                thisFinTxactIsChanged = updateId2Calc(thisFinTxact) || thisFinTxactIsChanged;
                thisFinTxactIsChanged = updateId2(thisFinTxact) || thisFinTxactIsChanged;
                thisFinTxactIsChanged = updateId2Cmp(thisFinTxact) || thisFinTxactIsChanged;

                if (thisFinTxactIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                    //dataManager.save(thisFinTxact);
                }


                UsrNodeBase thisFinTxactSet = thisFinTxact.getFinTxactSet1_Id();
                if (thisFinTxactSet != null) {

                    UsrNodeBase thisTrackedFinTxactSet = dataContext.merge(thisFinTxactSet);
                    thisFinTxactSet = thisTrackedFinTxactSet;

                    Boolean thisFinTxactSetIsChanged = false;

                    if (tmplt_FinTxactSet1_Id_Type1_IdFieldChk.isChecked()
                            && thisFinTxactSet != null
                    ) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setType1_Id(tmplt_FinTxactSet1_Id_Type1_IdField.getValue());
                    }

                    if (tmplt_FinTxactSet1_Id_GenChan1_IdFieldChk.isChecked()
                            && thisFinTxactSet != null
                    ) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setGenChan1_Id(tmplt_FinTxactSet1_Id_GenChan1_IdField.getValue());
                    }

                    if (tmplt_FinTxactSet1_Id_FinHow1_IdFieldChk.isChecked()
                            && thisFinTxactSet != null
                    ) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setFinHow1_Id(tmplt_FinTxactSet1_Id_FinHow1_IdField.getValue());
                    }

                    if (tmplt_FinTxactSet1_Id_WhatText1FieldChk.isChecked()
                            && thisFinTxactSet != null
                    ) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setWhatText1(tmplt_FinTxactSet1_Id_WhatText1Field.getValue());
                    }

                    if (tmplt_FinTxactSet1_Id_FinWhat1_IdFieldChk.isChecked()
                            && thisFinTxactSet != null
                    ) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setFinWhat1_Id(tmplt_FinTxactSet1_Id_FinWhat1_IdField.getValue());
                    }

                    if (tmplt_FinTxactSet1_Id_WhyText1FieldChk.isChecked()
                            && thisFinTxactSet != null
                    ) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setWhyText1(tmplt_FinTxactSet1_Id_WhyText1Field.getValue());
                    }

                    if (tmplt_FinTxactSet1_Id_FinWhy1_IdFieldChk.isChecked()
                            && thisFinTxactSet != null
                    ) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setFinWhy1_Id(tmplt_FinTxactSet1_Id_FinWhy1_IdField.getValue());
                    }

                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxact,thisFinTxact).");
                    //metadataTools.copy(thisFinTxact, thisFinTxact);

                    //logger.debug(logPrfx + " --- executing dataContext.commit().");
                    //dataContext.commit();

                    if (thisFinTxactSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                        //dataManager.save(thisFinTxactSet);
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

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinTxacts.forEach(thisFinTxact -> {
                //UsrNodeFinTxactItm thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                if (thisFinTxact != null) {
                    UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
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

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisFinTxacts);
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
                List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeBase thisFinTxact = thisFinTxacts.get(0);
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
                List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeBase thisFinTxact = thisFinTxacts.get(0);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxact.getNm1s1Inst1Int1();
                    Integer idX = 0;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxact.getNm1s1Inst1Int1();
                    Integer idX = thisFinTxact.getNm1s1Inst1Int1() == null || thisFinTxact.getNm1s1Inst1Int1() == 0 || thisFinTxact.getNm1s1Inst1Int1() == 1 ? 0 : thisFinTxact.getNm1s1Inst1Int1() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxact.getNm1s1Inst1Int1();
                    Integer idX = (thisFinTxact.getNm1s1Inst1Int1() == null ? 0 : thisFinTxact.getNm1s1Inst1Int1()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from enty_UsrNodeFinTxact e"
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

                    Integer idX_ = thisFinTxact.getNm1s1Inst1Int1();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinTxact.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxact.getNm1s1Inst1Int2();
                    Integer idY = 0;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxact.getNm1s1Inst1Int2();
                    Integer idY = thisFinTxact.getNm1s1Inst1Int2() == null || thisFinTxact.getNm1s1Inst1Int2() == 0 || thisFinTxact.getNm1s1Inst1Int2() == 1 ? 0 : thisFinTxact.getNm1s1Inst1Int2() - 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxact.getNm1s1Inst1Int2();
                    Integer idY = (thisFinTxact.getNm1s1Inst1Int2() == null ? 0 : thisFinTxact.getNm1s1Inst1Int2()) + 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                LocalDate idDt1 = thisFinTxact.getNm1s1Inst1Dt1() != null ? thisFinTxact.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY) from enty_UsrNodeFinTxact e"
                            + " where e.n1s1Inst1Dt1.elDt = :idDt1";
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

                    Integer idY_ = thisFinTxact.getNm1s1Inst1Int2();
                    Integer idY = idYMax == null ? 0 : idYMax;

                    if (!Objects.equals(idY_, idY)){
                        thisFinTxact.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
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

                Integer finTxactSetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
                isChanged = updateCalcVals(thisFinTxact, finTxactSetOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrFinTxactItm.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisFinTxacts);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
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

            logger.debug(logPrfx + " --- executing colLoadrFinTxactItm.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisFinTxacts);
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
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

            logger.debug(logPrfx + " --- executing colLoadrFinTxactItm.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinTxacts);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tmplt_FinTxactSet1_Id_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxactSet1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxactSet1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxactSet1_Id_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxactSet1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxactSet1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }



    @Install(to = "tmplt_FinTxactSet1_EI1_RoleField", subject = "enterPressHandler")
    private void tmplt_FinTxactSet1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxactSet1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateTxsetBtn")
    public void onUpdateTxsetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTxsetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer finTxactSetOption = 3; // update finTxactSet to match this finTxact

        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                thisFinTxactIsChanged = updateFinTxactSet1_Id(thisFinTxact, finTxactSetOption) || thisFinTxactIsChanged;
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

        List<UsrNodeBase> thisFinTxacts = tableMain.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer finTxactSetOption;
        if (!linkTxsetAutoCreateChk.isChecked()){
            finTxactSetOption = 1;
        }else{
            finTxactSetOption = 2;
        }

        thisFinTxacts.forEach(thisFinTxact -> {
            if (thisFinTxact != null) {
                UsrNodeBase thisTrackedFinTxact = dataContext.merge(thisFinTxact);
                thisFinTxact = thisTrackedFinTxact;

                Boolean thisFinTxactIsChanged = false;

                thisFinTxactIsChanged = updateFinTxactSet1_Id(thisFinTxact, finTxactSetOption) || thisFinTxactIsChanged;
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxactSetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxactSetOption == null){
            finTxactSetOption = 0;}

        updateCalcVals(thisFinTxact, finTxactSetOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        colLoadrFinTxactType.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxactSet1_EI1_RoleField", subject = "enterPressHandler")
    private void finTxactSet1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxactSet1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_EI1_RoleFieldListBtn")
    public void onUpdateFinTxactSet1_EI1_RoleFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_EI1_RoleFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinTxactSet1_EI1_RoleList();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1GenFmla1_IdFieldListBtn")
    public void onUpdateDesc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1GenFmla1_IdFieldListBtnClick";
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

    @Subscribe("ts1ElTsField")
    public void onTs1ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateIdDt(thisFinTxact);
            updateId2Calc(thisFinTxact);
            updateFinTxactSet1_Id2Trgt(thisFinTxact);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTs1ElTsFieldBtn")
    public void onUpdateTs1ElTsFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTs1ElTsFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateTs1(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxact);
            updateFinTxactSet1_Id2Trgt(thisFinTxact);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        colLoadrGenDocVer.load();
        logger.debug(logPrfx + " --- called colLoadrGenDocVer.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinTxactSet1_Id_Desc1FieldBtn")
    public void onUpdateFinTxactSet1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactSet1_Id_Desc1(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactSet1_IdFieldBtn")
    public void onUpdateFinTxactSet1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxactSetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxactSetOption == null){
            finTxactSetOption = 0;}
        updateFinTxactSet1_Id(thisFinTxact, finTxactSetOption);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxactSet1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactSet.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactSet.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id2TrgtBtn")
    public void onUpdateFinTxactSet1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactSet1_Id2Trgt(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactSet1_Id_Type1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_Type1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactSetType.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactSetType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_GenChan1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_How1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_How1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxactSet1_Id_WhatText1Field", subject = "enterPressHandler")
    private void finTxactSet1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxactSet1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_WhatText1FieldListBtn")
    public void onUpdateFinTxactSet1_Id_WhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_WhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_What1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_What1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_What1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxactSet1_Id_WhyText1Field", subject = "enterPressHandler")
    private void finTxactSet1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxactSet1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_WhyText1FieldListBtn")
    public void onUpdateFinTxactSet1_Id_WhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_WhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_Why1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_Why1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_Why1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactSet1_Id_Desc1GenFmla1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_Desc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_Desc1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_Desc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_Desc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactSet1_Id_Desc1FinTxactItm2_IdFieldListBtn")
    public void onUpdateFinTxactSet1_Id_Desc1FinTxactItm2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactSet1_Id_Desc1FinTxactItm2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull UsrNodeBase thisFinTxact, Integer finTxactSetOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactCalcVals(thisFinTxact, finTxactSetOption) || isChanged;
        isChanged = updateFinTxactSetCalcVals(thisFinTxact) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactCalcVals(@NotNull UsrNodeBase thisFinTxact, Integer finTxactSetOption) {
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

        isChanged = updateFinTxactSet1_Id2Trgt(thisFinTxact) || isChanged;
        isChanged = updateFinTxactSet1_Id(thisFinTxact, finTxactSetOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactSetCalcVals(@NotNull UsrNodeBase thisFinTxact) {
        String logPrfx = "updateFinTxactSetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxactSet Object
        isChanged = updateFinTxactSet1_Id_Desc1(thisFinTxact) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNodeBase thisFinTxact) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateTs1(thisFinTxact) || isChanged;
        isChanged = updateTs2(thisFinTxact) || isChanged;
        isChanged = updateIdX(thisFinTxact)  || isChanged;
        isChanged = updateIdY(thisFinTxact)  || isChanged;
        isChanged = updateIdDt(thisFinTxact) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull UsrNodeBase thisFinTxact) {
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

    private Boolean updateId2Calc(UsrNodeBase thisFinTxact){
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

    private Boolean updateId2Cmp(UsrNodeBase thisFinTxact) {
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

    private Boolean updateId2Dup(UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxact.getId2Dup();
        if (thisFinTxact.getId2() != null) {
            String id2Qry = "select count(e) from enty_UsrNodeFinTxact e where e.id2 = :id2 and e.id <> :id";
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

    private Boolean updateDesc1(UsrNodeBase thisFinTxact){
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

            UsrNodeBase desc1FinTxactItm1 = thisFinTxact.getDesc1Node1_Id() == null
                    ? findFirstFinTxactItmLikeId2(thisFinTxact.getId2() + "/%")
                    : thisFinTxact.getDesc1Node1_Id();

            //thisAmt
            String thisAmt = "";
            if (desc1FinTxactItm1 != null) {
                if (desc1FinTxactItm1.getAmtDebt() != null) {
                    thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtDebt();
                } else if (desc1FinTxactItm1.getAmtCred() != null) {
                    thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtCred();
                }
                if (desc1FinTxactItm1.getSysNodeFinCurcy1_Id() != null) {
                    thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm1.getSysNodeFinCurcy1_Id().getId2(), "");
                    thisAmt = thisAmt.strip();
                }
                if (!thisAmt.equals("")) {
                    thisAmt = thisAmt.strip();
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


    private Boolean updateFinTxactSet1_Id_Desc1(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactSet1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxactSet = thisFinTxact.getFinTxactSet1_Id();

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

                UsrNodeBase desc1FinTxactItm1 = thisFinTxactSet.getDesc1Node1_Id() == null
                        ? findFirstFinTxactItmLikeId2(thisFinTxactSet.getId2() + "/%")
                        : thisFinTxactSet.getDesc1Node1_Id();


                //thisAmt
                String thisAmt = "";
                if (desc1FinTxactItm1 != null){
                    if (desc1FinTxactItm1.getAmtDebt() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtDebt();
                    }else if (desc1FinTxactItm1.getAmtCred() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtCred();
                    }
                    if (desc1FinTxactItm1.getSysNodeFinCurcy1_Id() != null) {
                        thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm1.getSysNodeFinCurcy1_Id().getId2(), "");
                        thisAmt = thisAmt.strip();
                    }
                    if (thisType.contains("Exch")) {
                        UsrNodeBase desc1FinTxactItm2 = thisFinTxactSet.getDesc1Node2_Id() == null
                                ? findFirstFinTxactItmLikeId2(thisFinTxactSet.getId2() + "/Y01/%")
                                : thisFinTxactSet.getDesc1Node2_Id();

                        if (desc1FinTxactItm2 != null) {
                            thisAmt = thisAmt + " -> ";
                            if (desc1FinTxactItm2.getAmtDebt() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtDebt();
                            }else if (desc1FinTxactItm2.getAmtCred() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtCred();
                            }
                            if (desc1FinTxactItm2.getSysNodeFinCurcy1_Id() != null) {
                                thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm2.getSysNodeFinCurcy1_Id().getId2(), "");
                                thisAmt = thisAmt.strip();
                            }
                        }
                    }

                    if (!thisAmt.equals("")) {
                        thisAmt = thisAmt.strip();
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
                    thisWhat = "for " + thisWhat.strip();
                }
                logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

                String thisWhy = Objects.toString(thisFinTxactSet.getWhyText1(), "");
                if (thisFinTxactSet.getFinWhy1_Id() != null) {
                    thisWhy = thisWhy + " " + Objects.toString(thisFinTxactSet.getFinWhy1_Id().getId2());
                }
                if (!thisWhy.equals("")) {
                    thisWhy = "for " + thisWhy.strip();
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

                if (!Objects.equals(desc1_, desc1)){
                    thisFinTxactSet.setDesc1(desc1);
                    logger.debug(logPrfx + " --- desc1: " + desc1);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdDt(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateDt1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    private Boolean updateTs1(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateTs1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateTs1FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateTs2(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateTs2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateTs2FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdX(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateInt1FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdY(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateIdY";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxact.updateInt2FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactSet1_Id(@NotNull UsrNodeBase thisFinTxact, Integer finTxactSetOption) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactSet1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        if (thisFinTxact != null){
            UsrNodeBase finTxactSet1_Id_ = thisFinTxact.getFinTxactSet1_Id();
            UsrNodeBase finTxactSet1_Id;
            // Update finTxact
            switch (finTxactSetOption) {
                case 1: // Link to Exist Txset
                    finTxactSet1_Id = findFinTxactSetById2(thisFinTxact.getFinTxactSet1_Id2Trgt());
                    if (!Objects.equals(finTxactSet1_Id_, finTxactSet1_Id)){
                        thisFinTxact.setFinTxactSet1_Id(finTxactSet1_Id);
                        isChanged = true;
                    }
                    break;
                case 2: // Link to Exist/New Txset
                    finTxactSet1_Id = findFinTxactSetById2(thisFinTxact.getFinTxactSet1_Id2Trgt());
                    if (finTxactSet1_Id == null) {
                        finTxactSet1_Id = createFinTxactSetFrFinTxact(thisFinTxact);
                    }
                    if (!Objects.equals(finTxactSet1_Id_, finTxactSet1_Id)){
                        thisFinTxact.setFinTxactSet1_Id(finTxactSet1_Id);
                        isChanged = true;
                    }
                    break;
                case 3: // Update Exist Txact
                    Boolean thisFinTxactSetIsChanged = false;
                    UsrNodeBase thisFinTxactSet = thisFinTxact.getFinTxactSet1_Id();
                    if (thisFinTxactSet != null) {
                        if (!Objects.equals(thisFinTxactSet.getNm1s1Inst1Dt1().getElDt(), thisFinTxact.getNm1s1Inst1Dt1().getElDt())) {
                            thisFinTxactSet.getTs1().setElTs(thisFinTxact.getNm1s1Inst1Dt1().getElDt().atStartOfDay());
                            thisFinTxactSetIsChanged = true;
                        }
                        if (!Objects.equals(thisFinTxactSet.getNm1s1Inst1Int1(), thisFinTxact.getNm1s1Inst1Int1())) {
                            thisFinTxactSet.setNm1s1Inst1Int1(thisFinTxact.getNm1s1Inst1Int1());
                            thisFinTxactSetIsChanged = true;
                        }
                        if (thisFinTxactSetIsChanged) {
                            thisFinTxactSet.updateDt1();
                            thisFinTxactSet.setId2Calc(thisFinTxactSet.getId2CalcFrFields());
                            thisFinTxactSet.setId2(thisFinTxactSet.getId2Calc());
                            dataManager.save(thisFinTxactSet);
                        }
                    }
                    break;
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactSet1_Id2Trgt(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactSet1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String finTxactSet1_Id2Trgt_ = thisFinTxact.getFinTxactSet1_Id2Trgt();
        String finTxactSet1_Id2Trgt = thisFinTxact.getId2CalcFrFields().substring(0, 16);
        if(!Objects.equals(finTxactSet1_Id2Trgt_, finTxactSet1_Id2Trgt)){
            isChanged = true;
            thisFinTxact.setFinTxactSet1_Id2Trgt(finTxactSet1_Id2Trgt);
            logger.debug(logPrfx + " --- finTxactSet1_Id2Trgt: " + finTxactSet1_Id2Trgt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private UsrNodeBase findFinTxactSetById2(@NotNull String finTxactSet_Id2) {
        String logPrfx = "findFinTxactSetById2";
        logger.trace(logPrfx + " --> ");

        if (finTxactSet_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactSet_Id2 is null, finTxactSet_Id2.");
            notifications.create().withCaption("finTxactSet_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNodeFinTxactSet e where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactSet_Id2);

        UsrNodeBase finTxactSet1_Id = null;
        try {
            finTxactSet1_Id = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("id2", finTxactSet_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            // notifications.create().withCaption("FinTxactSet with id2: " + finTxactSet1_Id2Trgt + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finTxactSet1_Id;

    }

    private UsrNodeBase findFirstFinTxactItmLikeId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFirstFinTxactItmLikeId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNodeFinTxactItm e where e.id2 like :id2 order by e.sortKey, e.id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        UsrNodeBase finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(UsrNodeBase.class)
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

    private UsrNodeBase createFinTxactSetFrFinTxact(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "createFinTxactSetFrFinTxact";
        logger.trace(logPrfx + " --> ");

        String finTxactSet_Id2 = thisFinTxact.getFinTxactSet1_Id2Trgt();
        if (finTxactSet_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactSet_Id2 is null, finTxactSet_Id2.");
            notifications.create().withCaption("finTxactSet_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNodeFinTxactSet e where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactSet_Id2);

        try {
            UsrNodeBase finTxactSet = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("id2", finTxactSet_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            logger.trace(logPrfx + " <-- ");
            return finTxactSet;

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

            UsrNodeBase newFinTxactSet = dataManager.create(UsrNodeBase.class);
            newFinTxactSet.setClassName("UsrNodeFinTxactSet");

            HasTmst ts1 = dataManager.create(HasTmst.class);
            ts1.setElTs(thisFinTxact.getNm1s1Inst1Dt1().getElDt().atStartOfDay());
            newFinTxactSet.setTs1(ts1);
            newFinTxactSet.updateDt1();

            newFinTxactSet.setNm1s1Inst1Int1(thisFinTxact.getNm1s1Inst1Int1());
            newFinTxactSet.setNm1s1Inst1Int2(thisFinTxact.getNm1s1Inst1Int2());

            newFinTxactSet.setId2Calc(newFinTxactSet.getId2CalcFrFields());
            newFinTxactSet.setId2(newFinTxactSet.getId2Calc());


            UsrNodeBase savedFinTxactSet = dataManager.save(newFinTxactSet);

            UsrNodeBase mergedFinTxactSet = dataContext.merge(savedFinTxactSet);
            logger.debug(logPrfx + " --- created FinTxact id: " + mergedFinTxactSet.getId());
            notifications.create().withCaption("Created FinTxact with id2:" + mergedFinTxactSet.getId2()).show();

            logger.trace(logPrfx + " <-- ");
            return mergedFinTxactSet;
        }
    }


    private void reloadWhatText1List(){
        String logPrfx = "reloadWhatText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whatText1"
                + " from enty_UsrNodeFinTxactSet e"
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


        tmplt_FinTxactSet1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxactSet1_Id_WhatText1Field.setOptionsList()");

        finTxactSet1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxactSet1_Id_WhatText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadWhyText1List(){
        String logPrfx = "reloadWhyText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whyText1"
                + " from enty_UsrNodeFinTxactSet e"
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

        tmplt_FinTxactSet1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxactSet1_Id_WhyText1Field.setOptionsList()");

        finTxactSet1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finFinTxactSet1_Id_WhyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxactSet1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTxactSet1_EI1_Role"
                + " from enty_UsrNodeFinTxact e"
                + " where e.finTxactSet1_EI1_Role is not null"
                + " order by e.finTxactSet1_EI1_Role"
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

        tmplt_FinTxactSet1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called tmplt_FinTxactSet1_EI1_RoleField.setOptionsList()");

        finTxactSet1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finTxactSet1_EI1_RoleField.setOptionsList()");

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
        String idXMaxQry = "select max(e.idX) from enty_UsrNodeFinTxact e"
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
        String idXCntIsNullQry = "select count(e) from enty_UsrNodeFinTxact e"
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

    private Integer getIdYMax(LocalDate idDt1, Integer idX) {
        String logPrfx = "getIdYMax";
        logger.trace(logPrfx + " --> ");

        Integer idY, idYMax = 0;
        String idYMaxQry = "select max(e.idY) from enty_UsrNodeFinTxact e"
                + " where e.n1s1Inst1Dt1.elDt = :idDt1"
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
        String idYCntIsNullQry = "select count(e) from enty_UsrNodeFinTxact e"
                + " where e.n1s1Inst1Dt1.elDt = :idDt1"
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtEqCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateFinTxactItms1_SysNodeFinCurcyEqCalcBoxBtn")
    public void onUpdateFinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxact = instCntnrMain.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_SysNodeFinCurcyEqCalc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateFinTxactItms1_IdCntCalc(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer idCntCalc_ = thisFinTxact.getFinTxactItms1_IdCntCalc();
        Integer idCntCalc = null ;
        String qry1 = "select count(e.amtNet) from enty_UsrNodeFinTxactItm e where e.finTxact1_Id = :finTxact1_Id";
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

    
    private Boolean updateFinTxactItms1_AmtDebtSumCalc(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtDebtSumCalc_ = thisFinTxact.getFinTxactItms1_AmtDebtSumCalc();
        BigDecimal amtDebtSumCalc = null ;
        String qry1 = "select sum(e.amtDebt) from enty_UsrNodeFinTxactItm e where e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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

    private Boolean updateFinTxactItms1_AmtCredSumCalc(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtCredSumCalc_ = thisFinTxact.getFinTxactItms1_AmtCredSumCalc();
        BigDecimal amtCredSumCalc = null ;
        String qry1 = "select sum(e.amtCred) from enty_UsrNodeFinTxactItm e where e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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


    private Boolean updateFinTxactItms1_AmtEqCalc(@NotNull UsrNodeBase thisFinTxact) {
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


    private Boolean updateFinTxactItms1_SysNodeFinCurcyEqCalc(@NotNull UsrNodeBase thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxactItms1_SysNodeFinCurcyEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean finCurcyEqCalc_ = thisFinTxact.getFinTxactItms1_SysNodeFinCurcyEqCalc();
        Boolean finCurcyEqCalc = false;

        List<UsrNodeBase> finCurcyList = null;
        String qry1 = "select e.finCurcy1_Id from enty_UsrNodeFinTxactItm e"
                + " where e.finTxact1_Id = :finTxact1_Id";
        try{
            finCurcyList = dataManager.loadValue(qry1, UsrNodeBase.class)
                    .store("main")
                    .parameter("finTxact1_Id",thisFinTxact)
                    .list();
            if (finCurcyList == null || finCurcyList.isEmpty()) {
                logger.debug(logPrfx + " --- finCurcyList.size(): 0");
            }else{
                logger.debug(logPrfx + " --- finCurcyList.size(): " + finCurcyList.size());

                String finCurcyFirst_Id2 = finCurcyList.get(0).getId2();
                finCurcyEqCalc = true;
                for (UsrNodeBase finCurcy: finCurcyList){
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
            thisFinTxact.setFinTxactItms1_SysNodeFinCurcyEqCalc(finCurcyEqCalc);
            logger.debug(logPrfx + " --- finCurcyEqCalc: " + finCurcyEqCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private UsrNodeBase findFinTxactItmById2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFinTxactItmById2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNodeFinTxactItm e where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        UsrNodeBase finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(UsrNodeBase.class)
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