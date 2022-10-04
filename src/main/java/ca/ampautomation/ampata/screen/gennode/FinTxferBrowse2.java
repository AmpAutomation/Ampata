package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.*;
import io.jmix.core.*;
import io.jmix.core.pessimisticlocking.PessimisticLock;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.component.data.table.ContainerTableItems;
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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

//@PessimisticLock
@UiController("ampata_FinTxfer.browse2")
@UiDescriptor("fin-txfer-browse2.xml")
@LookupComponent("table")
public class FinTxferBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

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
    private Notifications notifications;


    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_idTsGE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_idTsLE;

//    @Autowired
//    protected PropertyFilter<GenNode> filterConfig1A_genChan1_Id;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_finTxact1_Id_finTxset1_Id_genChan1_Id;

    @Autowired
    protected PropertyFilter<FinHow> filterConfig1A_finTxact1_Id_finTxset1_Id_finHow1_Id;

    @Autowired
    protected PropertyFilter<FinWhat> filterConfig1A_finTxact1_Id_finTxset1_Id_finWhat1_Id;

    @Autowired
    protected PropertyFilter<FinWhy> filterConfig1A_finTxact1_Id_finTxset1_Id_finWhy1_Id;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_finAcct1_Id;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_finDept1_Id;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_finStmt1_Id;


    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_idTsGE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_idTsLE;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idX;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idY;
    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idZ;


    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsTxactOption;

    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsTxsetOption;

    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsStmtItmOption;


    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxactOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxsetOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsStmtItmOption;

    @Autowired
    private CheckBox linkTxactAutoCreateChk;

    //FinTxset

    @Autowired
    protected EntityComboBox<GenNodeType> tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<GenNode> tmplt_FinTxact1_Id_FinTxset1_Id_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<FinHow> tmplt_FinTxact1_Id_FinTxset1_Id_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<FinWhat> tmplt_FinTxact1_Id_FinTxset1_Id_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<FinWhy> tmplt_FinTxact1_Id_FinTxset1_Id_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_FinWhy1_IdFieldChk;

    //FinTxact

    @Autowired
    protected EntityComboBox<GenNodeType> tmplt_FinTxact1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_Type1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_EI1_RoleFieldChk;

    @Autowired
    protected EntityComboBox<GenNode> tmplt_FinTxact1_Id_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<FinHow> tmplt_FinTxact1_Id_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<FinWhat> tmplt_FinTxact1_Id_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<FinWhy> tmplt_FinTxact1_Id_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinWhy1_IdFieldChk;

    //FinTxfer

    @Autowired
    protected EntityComboBox<GenNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_EI1_RoleFieldChk;

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
    protected TextField<Integer> tmplt_IdZField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdZFieldRdo;


    @Autowired
    protected EntityComboBox<GenNode> tmplt_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan1_IdFieldChk;

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
    protected EntityComboBox<GenNode> tmplt_FinStmtItm1_IdField;

    @Autowired
    protected CheckBox tmplt_FinStmtItm1_IdFieldChk;


    @Autowired
    protected EntityComboBox<GenNode> tmplt_FinTaxLne1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTaxLne1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTaxLne1_CodeField;

    @Autowired
    protected CheckBox tmplt_FinTaxLne1_CodeFieldChk;


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
    protected EntityComboBox<FinFmla> tmplt_amtFinFmla1_IdField;

    @Autowired
    protected CheckBox tmplt_amtFinFmla1_IdFieldChk;


    @Autowired
    protected CheckBox updateFilterConfig1A_IdTsLE_SyncChk;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdTsGERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdXRdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdYRdo;



    @Autowired
    private Table<GenNode> table;

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


    private CollectionContainer<GenNode> finTxsetsDc;

    private CollectionLoader<GenNode> finTxsetsDl;


    private CollectionContainer<GenNodeType> finTxsetTypesDc;

    private CollectionLoader<GenNodeType> finTxsetTypesDl;


    private CollectionContainer<GenNode> finStmtsDc;

    private CollectionLoader<GenNode> finStmtsDl;

    private CollectionContainer<GenNode> finStmtItmsDc;

    private CollectionLoader<GenNode> finStmtItmsDl;

    private CollectionContainer<GenNode> finStmtItmCandidsDc;

    private CollectionLoader<GenNode> finStmtItmCandidsDl;


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


    private CollectionContainer<GenPat> genPatsDc;

    private CollectionLoader<GenPat> genPatsDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_EI1_RoleField;

    @Autowired
    private EntityComboBox<GenNode> genChan1_IdField;

    @Autowired
    private EntityComboBox<FinHow> finHow1_IdField;

    @Autowired
    private ComboBox<String> whatText1Field;

    @Autowired
    private EntityComboBox<FinWhat> finWhat1_IdField;

    @Autowired
    private ComboBox<String> whyText1Field;

    @Autowired
    private EntityComboBox<FinWhy> finWhy1_IdField;

    @Autowired
    private EntityComboBox<GenPat> desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<GenNode> desc1FinTxfer1_IdField;

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
    private EntityComboBox<GenNode> finStmtItm1_IdField;

    @Autowired
    private Table<GenNode> finStmtItmCandidsTable;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc1Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc2Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc3Field;


    @Autowired
    private EntityComboBox<GenNode> finTaxLne1_IdField;

    @Autowired
    private ComboBox<String> finTaxLne1_CodeField;

    @Autowired
    private EntityComboBox<GenNode> finAcct1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finDept1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<GenNode> amtFinTxfer1_IdField;

    @Autowired
    private EntityComboBox<FinFmla> amtFinFmla1_IdField;


    // FinTxact
    @Autowired
    private EntityComboBox<GenNode> finTxact1_IdField;

    @Autowired
    private TextField<String> finTxact1_Id2TrgtField;

    @Autowired
    private EntityComboBox<GenNodeType> finTxact1_Id_Type1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxset1_EI1_RoleField;

    @Autowired
    private EntityComboBox<GenNode> finTxact1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<FinHow> finTxact1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<FinWhat> finTxact1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_WhyText1Field;

    @Autowired
    private EntityComboBox<FinWhy> finTxact1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<GenPat> finTxact1_Id_Desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTxact1_Id_Desc1FinTxfer1_IdField;

    // FinTxset
    @Autowired
    private EntityComboBox<GenNode> finTxact1_Id_FinTxset1_IdField;

    @Autowired
    private TextField<String> finTxact1_Id_FinTxset1_Id2TrgtField;

    @Autowired
    private EntityComboBox<GenNodeType> finTxact1_Id_FinTxset1_Id_Type1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTxact1_Id_FinTxset1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<FinHow> finTxact1_Id_FinTxset1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxset1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<FinWhat> finTxact1_Id_FinTxset1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxset1_Id_WhyText1Field;

    @Autowired
    private EntityComboBox<FinWhy> finTxact1_Id_FinTxset1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<GenPat> finTxact1_Id_FinTxset1_Id_Desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTxact1_Id_FinTxset1_Id_Desc1FinTxfer1_IdField;

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

        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_IdXFieldRdo.setOptionsMap(map1);
        tmplt_IdYFieldRdo.setOptionsMap(map1);
        tmplt_IdZFieldRdo.setOptionsMap(map1);

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put("None", 0);
        map2.put("Link to Exist Txact", 1);
        map2.put("Link to Exist/New Txact", 2);
        map2.put("Update Exist Txact", 3);
        updateColItemCalcValsTxactOption.setOptionsMap(map2);
        updateInstItemCalcValsTxactOption.setOptionsMap(map2);

        Map<String, Integer> map3 = new LinkedHashMap<>();
        map3.put("None", 0);
        map3.put("Link to Exist Txset", 1);
        map3.put("Link to Exist/New Txset", 2);
        map3.put("Update Exist Txset", 3);
        updateColItemCalcValsTxsetOption.setOptionsMap(map3);
        updateInstItemCalcValsTxsetOption.setOptionsMap(map3);

        Map<String, Integer> map4 = new LinkedHashMap<>();
        map4.put("None", 0);
        map4.put("Link to Exist StmtItm", 1);
        updateColItemCalcValsStmtItmOption.setOptionsMap(map4);
        updateInstItemCalcValsStmtItmOption.setOptionsMap(map4);

        Map<String, Integer> map5 = new LinkedHashMap<>();
        map5.put("Clear", 0);
        map5.put("Match Current Row", 1);
        updateFilterConfig1B_IdTsGERdo.setOptionsMap(map5);
        updateFilterConfig1B_IdXRdo.setOptionsMap(map5);
        updateFilterConfig1B_IdYRdo.setOptionsMap(map5);

        tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField.setNullSelectionCaption("<null>");
        tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1Field.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1Field.setNullSelectionCaption("<null>");
        tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1Field.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1Field.setNullSelectionCaption("<null>");

        tmplt_FinTxact1_EI1_RoleField.setNullOptionVisible(true);
        tmplt_FinTxact1_EI1_RoleField.setNullSelectionCaption("<null>");
        tmplt_FinTxact1_Id_WhatText1Field.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_WhatText1Field.setNullSelectionCaption("<null>");
        tmplt_FinTxact1_Id_WhyText1Field.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_WhyText1Field.setNullSelectionCaption("<null>");

        tmplt_WhatText1Field.setNullOptionVisible(true);
        tmplt_WhatText1Field.setNullSelectionCaption("<null>");
        tmplt_WhyText1Field.setNullOptionVisible(true);
        tmplt_WhyText1Field.setNullSelectionCaption("<null>");

        tmplt_FinTaxLne1_CodeField.setNullOptionVisible(true);
        tmplt_FinTaxLne1_CodeField.setNullSelectionCaption("<null>");


        finStmtItm1_Id_Desc1Field.setNullOptionVisible(true);
        finStmtItm1_Id_Desc1Field.setNullSelectionCaption("<null>");
        finStmtItm1_Id_Desc2Field.setNullOptionVisible(true);
        finStmtItm1_Id_Desc2Field.setNullSelectionCaption("<null>");
        finStmtItm1_Id_Desc3Field.setNullOptionVisible(true);
        finStmtItm1_Id_Desc3Field.setNullSelectionCaption("<null>");

        finTaxLne1_CodeField.setNullOptionVisible(true);
        finTaxLne1_CodeField.setNullSelectionCaption("<null>");


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
        //template
        tmplt_Type1_IdField.setOptionsContainer(finTxferTypesDc);


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
        finTxact1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        finTxact1_Id_FinTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        tmplt_FinTxact1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        tmplt_GenChan1_IdField.setOptionsContainer(genChansDc);
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_genChan1_Id = (EntityComboBox<GenNode>) filterConfig1A_finTxact1_Id_finTxset1_Id_genChan1_Id.getValueComponent();
        propFilterCmpnt_genChan1_Id.setOptionsContainer(genChansDc);


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
        finTxact1_Id_FinTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        tmplt_FinTxact1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        tmplt_FinHow1_IdField.setOptionsContainer(finHowsDc);
        //filter
        EntityComboBox<FinHow> propFilterCmpnt_finHow1_Id = (EntityComboBox<FinHow>) filterConfig1A_finTxact1_Id_finTxset1_Id_finHow1_Id.getValueComponent();
        propFilterCmpnt_finHow1_Id.setOptionsContainer(finHowsDc);


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
        finTxact1_Id_FinTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        tmplt_FinTxact1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        tmplt_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        //filter
        EntityComboBox<FinWhat> propFilterCmpnt_finWhat1_Id = (EntityComboBox<FinWhat>) filterConfig1A_finTxact1_Id_finTxset1_Id_finWhat1_Id.getValueComponent();
        propFilterCmpnt_finWhat1_Id.setOptionsContainer(finWhatsDc);


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
        finTxact1_Id_FinTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        tmplt_FinTxact1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        tmplt_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        //filter
        EntityComboBox<FinWhy> propFilterCmpnt_finWhy1_Id = (EntityComboBox<FinWhy>) filterConfig1A_finTxact1_Id_finTxset1_Id_finWhy1_Id.getValueComponent();
        propFilterCmpnt_finWhy1_Id.setOptionsContainer(finWhysDc);


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
        finTxact1_Id_Desc1GenPat1_IdField.setOptionsContainer(genPatsDc);
        finTxact1_Id_FinTxset1_Id_Desc1GenPat1_IdField.setOptionsContainer(genPatsDc);

        
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

        tmplt_FinTxact1_Id_Type1_IdField.setOptionsContainer(finTxactTypesDc);


        finTxsetsDc = dataComponents.createCollectionContainer(GenNode.class);
        finTxsetsDl = dataComponents.createCollectionLoader();
        finTxsetsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetsDl.setFetchPlan(finTxsetsFp);
        finTxsetsDl.setContainer(finTxsetsDc);
        finTxsetsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxset1_IdField.setOptionsContainer(finTxsetsDc);


        finTxsetTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finTxsetTypesDl = dataComponents.createCollectionLoader();
        finTxsetTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetTypesDl.setFetchPlan(finTxsetTypesFp);
        finTxsetTypesDl.setContainer(finTxsetTypesDc);
        finTxsetTypesDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxset1_Id_Type1_IdField.setOptionsContainer(finTxsetTypesDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdField.setOptionsContainer(finTxsetTypesDc);

        
        finStmtsDc = dataComponents.createCollectionContainer(GenNode.class);
        finStmtsDl = dataComponents.createCollectionLoader();
        finStmtsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtsDl.setFetchPlan(finStmtsFp);
        finStmtsDl.setContainer(finStmtsDc);
        finStmtsDl.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finStmt1_Id = (EntityComboBox<GenNode>) filterConfig1A_finStmt1_Id.getValueComponent();
        propFilterCmpnt_finStmt1_Id.setOptionsContainer(finStmtsDc);


        finStmtItmsDc = dataComponents.createCollectionContainer(GenNode.class);
        finStmtItmsDl = dataComponents.createCollectionLoader();
        finStmtItmsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinStmtItm' order by e.id2");
        FetchPlan finStmtItmsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtItmsDl.setFetchPlan(finStmtItmsFp);
        finStmtItmsDl.setContainer(finStmtItmsDc);
        finStmtItmsDl.setDataContext(getScreenData().getDataContext());

        finStmtItm1_IdField.setOptionsContainer(finStmtItmsDc);
        //template
        tmplt_FinStmtItm1_IdField.setOptionsContainer(finStmtItmsDc);


        finStmtItmCandidsDc = dataComponents.createCollectionContainer(GenNode.class);
        finStmtItmCandidsDl = dataComponents.createCollectionLoader();
        finStmtItmCandidsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinStmtItm' order by e.id2");
        FetchPlan finStmtItmCandidsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtItmCandidsDl.setFetchPlan(finStmtItmCandidsFp);
        finStmtItmCandidsDl.setContainer(finStmtItmCandidsDc);
        finStmtItmCandidsDl.setDataContext(getScreenData().getDataContext());

        finStmtItmCandidsTable.setItems(new ContainerTableItems<>(finStmtItmCandidsDc));


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
        //Template
        tmplt_FinTaxLne1_IdField.setOptionsContainer(finTaxLnesDc);


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
        //template
        tmplt_finAcct1_IdField.setOptionsContainer(finAcctsDc);
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finAcct1_Id = (EntityComboBox<GenNode>) filterConfig1A_finAcct1_Id.getValueComponent();
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

        finDept1_IdField.setOptionsContainer(finDeptsDc);
        //template
        tmplt_finDept1_IdField.setOptionsContainer(finDeptsDc);
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finDept1_Id = (EntityComboBox<GenNode>) filterConfig1A_finDept1_Id.getValueComponent();
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

        desc1FinTxfer1_IdField.setOptionsContainer(finTxfer1sDc);
        finTxact1_Id_Desc1FinTxfer1_IdField.setOptionsContainer(finTxfer1sDc);
        finTxact1_Id_FinTxset1_Id_Desc1FinTxfer1_IdField.setOptionsContainer(finTxfer1sDc);
        amtFinTxfer1_IdField.setOptionsContainer(finTxfer1sDc);


        finFmlasDc = dataComponents.createCollectionContainer(FinFmla.class);
        finFmlasDl = dataComponents.createCollectionLoader();
        finFmlasDl.setQuery("select e from ampata_FinFmla e order by e.id2");
        FetchPlan finFmlasFp = fetchPlans.builder(FinFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finFmlasDl.setFetchPlan(finFmlasFp);
        finFmlasDl.setContainer(finFmlasDc);
        finFmlasDl.setDataContext(getScreenData().getDataContext());

        amtFinFmla1_IdField.setOptionsContainer(finFmlasDc);
        //template
        tmplt_amtFinFmla1_IdField.setOptionsContainer(finFmlasDc);


        logger.trace(logPrfx + " <--- ");


    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- Changed entity: " + event.getEntity());

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
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfer.setClassName("FinTxfer");
        logger.debug(logPrfx + " --- className: FinTxfer");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "table.[idTs.ts1]", subject = "formatter")
    private String tableIdTsTs1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxferTypesDl.load();
        logger.debug(logPrfx + " --- called finTxferTypesDl.load() ");

        reloadFinTxact1_Id_FinTxset1_EI1_RoleList();

        finTxactTypesDl.load();
        logger.debug(logPrfx + " --- called finTxactTypesDl.load() ");

        reloadFinTxact1_EI1_RoleList();

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
        logger.debug(logPrfx + " --- called getPat.load() ");


        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        finStmtItmsDl.load();
        logger.debug(logPrfx + " --- called finStmtItmsDl.load() ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");
        reloadFinStmtItm1_Id_Desc1List();
        reloadFinStmtItm1_Id_Desc2List();
        reloadFinStmtItm1_Id_Desc3List();

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        finDeptsDl.load();
        logger.debug(logPrfx + " --- called finDeptsDl.load() ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        finTaxLnesDl.load();
        logger.debug(logPrfx + " --- called finTaxLnesDl.load() ");
        reloadFinTaxLne1_CodeList();

        finFmlasDl.load();
        logger.debug(logPrfx + " --- called finFmlasDl.load() ");

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
            if (Objects.equals(orig.getId2(), copy.getId2())) {
                copy.setIdZ(copy.getIdZ() == null ? 1 : copy.getIdZ() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            copy.setFinAcct1_Id(null);

            copy.setFinStmt1_Id(null);
            copy.setFinStmtItm1_Desc1(null);
            copy.setFinStmtItm1_Desc2(null);
            copy.setFinStmtItm1_Desc3(null);
            copy.setFinStmtItm1_ExchDesc(null);
            copy.setFinStmtItm1_RefId(null);


            if (copy.getAmtDebt() != null) {
                copy.setAmtCred(copy.getAmtDebt());
                copy.setAmtDebt(null);
                copy.setFinTxact1_EI1_Role("fr");
            } else if (copy.getAmtCred() != null) {
                copy.setAmtDebt(copy.getAmtCred());
                copy.setAmtCred(null);
                copy.setFinTxact1_EI1_Role("to");
            }

            if (tmplt_FinTaxLne1_IdFieldChk.isChecked()) {
                copy.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
            }

            if (tmplt_FinTaxLne1_CodeFieldChk.isChecked()) {
                copy.setFinTaxLne1_Code(tmplt_FinTaxLne1_CodeField.getValue());
            }


            if (tmplt_FinTxact1_EI1_RoleFieldChk.isChecked()) {
                copy.setFinTxact1_EI1_Role(tmplt_FinTxact1_EI1_RoleField.getValue());
            }

            if (tmplt_finAcct1_IdFieldChk.isChecked()) {
                copy.setFinAcct1_Id(tmplt_finAcct1_IdField.getValue());
            }

            if (tmplt_finDept1_IdFieldChk.isChecked()) {
                copy.setFinDept1_Id(tmplt_finDept1_IdField.getValue());
            }

            Integer finTxactOption = Optional.ofNullable(updateColItemCalcValsTxactOption.getValue()).orElse(0);
            Integer finTxsetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
            Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);

            updateCalcVals(copy, finTxactOption, finTxsetOption, finStmtItmOption);

            GenNode savedCopy = dataManager.save(copy);
            finTxfersDc.getMutableItems().add(savedCopy);
            logger.debug("Split FinTxfer " + copy.getId2() + " "
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

            if (tmplt_FinTxact1_EI1_RoleFieldChk.isChecked()) {
                copy.setFinTxact1_EI1_Role(tmplt_FinTxact1_EI1_RoleField.getValue());
            }

            LocalDateTime idTs1 = copy.getIdTs() != null ? copy.getIdTs().getTs1() : null;
            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                idTs1 = tmplt_Beg1Ts1Field.getValue();
                copy.getBeg1().setTs1(idTs1);
                updateIdTs(copy);
            }

            Integer idX = copy.getIdX();
            if (tmplt_IdXFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdXFieldRdo.getValue() == 1) {
                    idX = tmplt_IdXField.getValue();
                    copy.setIdX(idX);
                }
                // Max
                else if (tmplt_IdXFieldRdo.getValue() == 2
                        && idTs1 != null) {

                    idX = getIdXMax(idTs1);
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
                        && idTs1 != null) {

                    idY = getIdYMax(idTs1, idX);
                    if (idY == null) return;
                    copy.setIdY(idY);
                }

            }

            Integer idZ = copy.getIdZ();
            if (tmplt_IdZFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdZFieldRdo.getValue() == 1) {
                    idZ = tmplt_IdZField.getValue();
                    copy.setIdZ(idZ);
                }
                // Max
                else if (tmplt_IdZFieldRdo.getValue() == 2
                        && idTs1 != null) {

                    idZ = getIdZMax(idTs1, idX, idY);
                    if (idZ == null) return;
                    copy.setIdZ(idZ);
                }
            }

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            if (tmplt_finAcct1_IdFieldChk.isChecked()) {
                copy.setFinAcct1_Id(tmplt_finAcct1_IdField.getValue());
            }

            if (tmplt_finDept1_IdFieldChk.isChecked()) {
                copy.setFinDept1_Id(tmplt_finDept1_IdField.getValue());
            }

            if (tmplt_amtDebtFieldChk.isChecked()) {
                copy.setAmtDebt(tmplt_amtDebtField.getValue());
            }

            if (tmplt_amtCredFieldChk.isChecked()) {
                copy.setAmtCred(tmplt_amtCredField.getValue());
            }

            if (tmplt_amtFinFmla1_IdFieldChk.isChecked()) {
                copy.setAmtFinFmla1_Id(tmplt_amtFinFmla1_IdField.getValue());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (!Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setIdZ(copy.getIdZ() == null ? 1 : copy.getIdZ() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }
            Integer finTxactOption = Optional.ofNullable(updateColItemCalcValsTxactOption.getValue()).orElse(0);
            Integer finTxsetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
            Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);

            updateCalcVals(copy, finTxactOption, finTxsetOption, finStmtItmOption);

            GenNode savedCopy = dataManager.save(copy);
            finTxfersDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinTxfer " + copy.getId2() + " "
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

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxfers.forEach(thisFinTxfer -> {
            GenNode thisTrackedFinTxfer = dataContext.merge(thisFinTxfer);
            thisFinTxfer = thisTrackedFinTxfer;
            if (thisFinTxfer != null) {

                Boolean thisFinTxferIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                if (tmplt_FinTxact1_EI1_RoleFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinTxact1_EI1_Role(tmplt_FinTxact1_EI1_RoleField.getValue());
                }

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    idTs1 = tmplt_Beg1Ts1Field.getValue();
                    thisFinTxfer.getBeg1().setTs1(idTs1);
                    updateIdTs(thisFinTxfer);
                }

                Integer idX = thisFinTxfer.getIdX();
                if (tmplt_IdXFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1) {
                        thisFinTxferIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinTxfer.setIdX(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idTs1 != null) {
                        thisFinTxferIsChanged = true;
                        idX = getIdXMax(idTs1);
                        if (idX == null) return;
                        thisFinTxfer.setIdX(idX);
                    }
                }

                Integer idY = thisFinTxfer.getIdY();
                if (tmplt_IdYFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdYFieldRdo.getValue() == 1) {
                        thisFinTxferIsChanged = true;
                        idY = tmplt_IdYField.getValue();
                        thisFinTxfer.setIdY(idY);
                    }
                    // Max
                    else if (tmplt_IdYFieldRdo.getValue() == 2
                            && idTs1 != null) {
                        thisFinTxferIsChanged = true;
                        idY = getIdYMax(idTs1, idX);
                        if (idY == null) return;
                        thisFinTxfer.setIdY(idY);
                    }
                }

                Integer idZ = thisFinTxfer.getIdZ();
                if (tmplt_IdZFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdZFieldRdo.getValue() == 1) {
                        thisFinTxferIsChanged = true;
                        idZ = tmplt_IdZField.getValue();
                        thisFinTxfer.setIdZ(idZ);
                    }
                    // Max
                    else if (tmplt_IdZFieldRdo.getValue() == 2
                            && idTs1 != null) {
                        thisFinTxferIsChanged = true;
                        idZ = getIdZMax(idTs1, idX, idY);
                        if (idZ == null) return;
                        thisFinTxfer.setIdZ(idZ);
                    }
                }

                if (tmplt_GenChan1_IdFieldChk.isChecked()
                ) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setGenChan1_Id(tmplt_GenChan1_IdField.getValue());
                }

                if (tmplt_FinHow1_IdFieldChk.isChecked()
                ) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinHow1_Id(tmplt_FinHow1_IdField.getValue());
                }

                if (tmplt_WhatText1FieldChk.isChecked()
                ) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setWhatText1(tmplt_WhatText1Field.getValue());
                }

                if (tmplt_FinWhat1_IdFieldChk.isChecked()
                ) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinWhat1_Id(tmplt_FinWhat1_IdField.getValue());
                }

                if (tmplt_WhyText1FieldChk.isChecked()
                ) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setWhyText1(tmplt_WhyText1Field.getValue());
                }

                if (tmplt_FinWhy1_IdFieldChk.isChecked()
                ) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinWhy1_Id(tmplt_FinWhy1_IdField.getValue());
                }


                if (tmplt_FinTaxLne1_IdFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
                }

                if (tmplt_FinTaxLne1_CodeFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinTaxLne1_Code(tmplt_FinTaxLne1_CodeField.getValue());
                }


                if (tmplt_finAcct1_IdFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinAcct1_Id(tmplt_finAcct1_IdField.getValue());
                }

                if (tmplt_finDept1_IdFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setFinDept1_Id(tmplt_finDept1_IdField.getValue());
                }

                if (tmplt_amtDebtFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setAmtDebt(tmplt_amtDebtField.getValue());
                }

                if (tmplt_amtCredFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setAmtCred(tmplt_amtCredField.getValue());
                }

                if (tmplt_amtFinFmla1_IdFieldChk.isChecked()) {
                    thisFinTxferIsChanged = true;
                    thisFinTxfer.setAmtFinFmla1_Id(tmplt_amtFinFmla1_IdField.getValue());
                }

                thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                thisFinTxferIsChanged = updateId2(thisFinTxfer) || thisFinTxferIsChanged;
                thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                if (thisFinTxferIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                    //dataManager.save(thisFinTxfer);
                }

                GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
                if (thisFinTxact != null) {
                    thisFinTxact = dataContext.merge(thisFinTxact);

                    boolean thisFinTxactIsChanged = false;

                    if (tmplt_FinTxact1_Id_Type1_IdFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setType1_Id(tmplt_FinTxact1_Id_Type1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxset1_EI1_RoleFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setFinTxset1_EI1_Role(tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_GenChan1_IdFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setGenChan1_Id(tmplt_FinTxact1_Id_GenChan1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinHow1_IdFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setFinHow1_Id(tmplt_FinTxact1_Id_FinHow1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_WhatText1FieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setWhatText1(tmplt_FinTxact1_Id_WhatText1Field.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinWhat1_IdFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setFinWhat1_Id(tmplt_FinTxact1_Id_FinWhat1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_WhyText1FieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setWhyText1(tmplt_FinTxact1_Id_WhyText1Field.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinWhy1_IdFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setFinWhy1_Id(tmplt_FinTxact1_Id_FinWhy1_IdField.getValue());
                    }

                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }

                GenNode thisFinTxset = thisFinTxfer.getFinTxact1_Id() == null ? null : thisFinTxfer.getFinTxact1_Id().getFinTxset1_Id();
                if (thisFinTxset != null) {
                    thisFinTxset = dataContext.merge(thisFinTxset);

                    boolean thisFinTxsetIsChanged = false;

                    if (tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdFieldChk.isChecked()) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setType1_Id(tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxset1_Id_GenChan1_IdFieldChk.isChecked()) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setGenChan1_Id(tmplt_FinTxact1_Id_FinTxset1_Id_GenChan1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxset1_Id_FinHow1_IdFieldChk.isChecked()) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setFinHow1_Id(tmplt_FinTxact1_Id_FinTxset1_Id_FinHow1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1FieldChk.isChecked()) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setWhatText1(tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1Field.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxset1_Id_FinWhat1_IdFieldChk.isChecked()) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setFinWhat1_Id(tmplt_FinTxact1_Id_FinTxset1_Id_FinWhat1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1FieldChk.isChecked()) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setWhyText1(tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1Field.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxset1_Id_FinWhy1_IdFieldChk.isChecked()) {
                        thisFinTxsetIsChanged = true;
                        thisFinTxset.setFinWhy1_Id(tmplt_FinTxact1_Id_FinTxset1_Id_FinWhy1_IdField.getValue());
                    }

                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxfer,thisFinTxfer).");
                    //metadataTools.copy(thisFinTxfer, thisFinTxfer);

                    //logger.debug(logPrfx + " --- executing dataContext.commit().");
                    //dataContext.commit();

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }

                }

            }
        });
        updateFinTxferHelper();
        logger.trace(logPrfx + " <-- ");
    }
    private void updateFinTxferHelper() {
        String logPrfx = "updateFinTxferHelper";
        logger.trace(logPrfx + " --> ");
    
        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
    
            logger.debug(logPrfx + " --- executing finTxfersDl.load().");
            finTxfersDl.load();

            List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
    
            //Loop throught the items again to update the id2Dup attribute
            thisFinTxfers.forEach(thisFinTxfer -> {
                //GenNode thisTrackedFinTxfer = dataContext.merge(thisFinTxfer);
                if (thisFinTxfer != null) {
                    thisFinTxfer = dataContext.merge(thisFinTxfer);

                    Boolean thisFinTxferIsChanged = false;
    
                    thisFinTxferIsChanged = updateId2Dup(thisFinTxfer) || thisFinTxferIsChanged;
    
                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            });
    
            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();
    
                logger.debug(logPrfx + " --- executing finTxfersDl.load().");
                finTxfersDl.load();
    
                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinTxfers);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdTsGE_DecMonBtn")
    public void onUpdateFilterConfig1A_IdTsGE_DecMonBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdTsGE_DecMonBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> propFilterCmpnt_idTsGE = (DateField<LocalDateTime>) filterConfig1A_idTsGE.getValueComponent();
        LocalDateTime idTsGE = propFilterCmpnt_idTsGE.getValue();
        if (idTsGE == null) {
            logger.debug(logPrfx + " --- idTsGE: null");
        } else {
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            idTsGE = idTsGE.minusMonths(1);
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            propFilterCmpnt_idTsGE.setValue(idTsGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdTsLE_SyncChk.isChecked()){
            DateField<LocalDateTime> propFilterCmpnt_idTsLE = (DateField<LocalDateTime>) filterConfig1A_idTsLE.getValueComponent();
            LocalDateTime idTsLE = propFilterCmpnt_idTsLE.getValue();
            if (idTsLE == null) {
                logger.debug(logPrfx + " --- idTsLE: null");
            } else {
                logger.debug(logPrfx + " --- idTsLE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                idTsLE = idTsLE.minusMonths(1);
                logger.debug(logPrfx + " --- idTsGE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                propFilterCmpnt_idTsLE.setValue(idTsLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdTsGE_IncMonBtn")
    public void onUpdateFilterConfig1A_IdTsGE_IncMonBtnBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdTsGE_IncMonBtnBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> propFilterCmpnt_idTsGE = (DateField<LocalDateTime>) filterConfig1A_idTsGE.getValueComponent();
        LocalDateTime idTsGE = propFilterCmpnt_idTsGE.getValue();
        if (idTsGE == null) {
            logger.debug(logPrfx + " --- idTsGE: null");
        } else {
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            idTsGE = idTsGE.plusMonths(1);
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            propFilterCmpnt_idTsGE.setValue(idTsGE);
            filter.apply();
        }
        if (updateFilterConfig1A_IdTsLE_SyncChk.isChecked()) {
            DateField<LocalDateTime> propFilterCmpnt_idTsLE = (DateField<LocalDateTime>) filterConfig1A_idTsLE.getValueComponent();
            LocalDateTime idTsLE = propFilterCmpnt_idTsLE.getValue();
            if (idTsLE == null) {
                logger.debug(logPrfx + " --- idTsLE: null");
            } else {
                logger.debug(logPrfx + " --- idTsLE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                idTsLE = idTsLE.plusMonths(1);
                logger.debug(logPrfx + " --- idTsGE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                propFilterCmpnt_idTsLE.setValue(idTsLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdTsGE_DecDayBtn")
    public void onUpdateFilterConfig1A_IdTsGE_DecDayBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdTsGE_DecDayBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> cmpnt_idTsGE = (DateField<LocalDateTime>) filterConfig1A_idTsGE.getValueComponent();
        LocalDateTime idTsGE = cmpnt_idTsGE.getValue();
        if (idTsGE == null) {
            logger.debug(logPrfx + " --- idTsGE: null");
        } else {
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            idTsGE = idTsGE.minusDays(1);
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            cmpnt_idTsGE.setValue(idTsGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdTsLE_SyncChk.isChecked()) {
            DateField<LocalDateTime> cmpnt_idTsLE = (DateField<LocalDateTime>) filterConfig1A_idTsLE.getValueComponent();
            LocalDateTime idTsLE = cmpnt_idTsLE.getValue();
            if (idTsLE == null) {
                logger.debug(logPrfx + " --- idTsLE: null");
            } else {
                logger.debug(logPrfx + " --- idTsLE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                idTsLE = idTsLE.minusDays(1);
                logger.debug(logPrfx + " --- idTsGE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                cmpnt_idTsLE.setValue(idTsLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_IdTsGE_IncDayBtn")
    public void onUpdateFilterConfig1A_IdTsGE_IncDayClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_IdTsGE_IncDayClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> cmpnt_idTsGE = (DateField<LocalDateTime>) filterConfig1A_idTsGE.getValueComponent();
        LocalDateTime idTsGE = cmpnt_idTsGE.getValue();
        if (idTsGE == null) {
            logger.debug(logPrfx + " --- idTsGE: null");
        } else {
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            idTsGE = idTsGE.plusDays(1);
            logger.debug(logPrfx + " --- idTsGE: " + idTsGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
            cmpnt_idTsGE.setValue(idTsGE);
            filter.apply();
        }

        if (updateFilterConfig1A_IdTsLE_SyncChk.isChecked()) {
            DateField<LocalDateTime> cmpnt_idTsLE = (DateField<LocalDateTime>) filterConfig1A_idTsLE.getValueComponent();
            LocalDateTime idTsLE = cmpnt_idTsLE.getValue();
            if (idTsLE == null) {
                logger.debug(logPrfx + " --- idTsLE: null");
            } else {
                logger.debug(logPrfx + " --- idTsLE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                idTsLE = idTsLE.plusDays(1);
                logger.debug(logPrfx + " --- idTsGE: " + idTsLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
                cmpnt_idTsLE.setValue(idTsLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1B_IdTsGEBtn")
    public void onUpdateFilterConfig1B_IdTsGEBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_IdTsGEBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer idTsGEOption = updateFilterConfig1B_IdTsGERdo.getValue();
        switch (idTsGEOption) {
            case 0 -> // Clear
                    filterConfig1B_idTsGE.clear();
            case 1 -> {  // Set to match current row
                List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
                if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                GenNode thisFinTxfer = thisFinTxfers.get(0);
                if (thisFinTxfer != null) {
                    if (thisFinTxfer.getIdTs() != null && thisFinTxfer.getIdTs().getTs1() != null) {
                        filterConfig1B_idTsGE.setValue(thisFinTxfer.getIdTs().getTs1());
                        filterConfig1B_idTsGE.apply();
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1B_IdXBtn")
    public void onUpdateFilterConfig1B_IdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_IdXBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer idXOption = updateFilterConfig1B_IdXRdo.getValue();
        switch (idXOption) {
            case 0 -> // Clear
                    filterConfig1B_idX.clear();
            case 1 -> {  // Set to match current row
                List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
                if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                GenNode thisFinTxfer = thisFinTxfers.get(0);
                if (thisFinTxfer != null) {
                    if (thisFinTxfer.getIdX() != null) {
                        filterConfig1B_idX.setValue(thisFinTxfer.getIdX());
                        filterConfig1B_idX.apply();
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1B_IdYBtn")
    public void onUpdateFilterConfig1B_IdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_IdYBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer idYOption = updateFilterConfig1B_IdYRdo.getValue();
        switch (idYOption) {
            case 0 -> // Clear
                    filterConfig1B_idY.clear();
            case 1 -> {  // Set to match current row
                List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
                if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                GenNode thisFinTxfer = thisFinTxfers.get(0);
                if (thisFinTxfer != null) {
                    if (thisFinTxfer.getIdY() != null) {
                        filterConfig1B_idY.setValue(thisFinTxfer.getIdY());
                        filterConfig1B_idY.apply();
                    }
                }
            }
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX_ = thisFinTxfer.getIdX();
                    Integer idX = 0;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxfer.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdX(" + (idX) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX_ = thisFinTxfer.getIdX();
                    Integer idX = thisFinTxfer.getIdX() == null || thisFinTxfer.getIdX() == 0 || thisFinTxfer.getIdX() == 1 ? 0 : thisFinTxfer.getIdX() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxfer.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdX(" + (idX) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX_ = thisFinTxfer.getIdX();
                    Integer idX = (thisFinTxfer.getIdX() == null ? 0 : thisFinTxfer.getIdX()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxfer.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdX(" + (idX) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

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

                    Integer idX_ = thisFinTxfer.getIdX();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinTxfer.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdX(" + (idX) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idY_ = thisFinTxfer.getIdY();
                    Integer idY = 0;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxfer.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdY(" + (idY) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idY_ = thisFinTxfer.getIdY();
                    Integer idY = thisFinTxfer.getIdY() == null || thisFinTxfer.getIdY() == 0 || thisFinTxfer.getIdY() == 1 ? 0 : thisFinTxfer.getIdY() - 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxfer.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdY(" + (idY) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idY_ = thisFinTxfer.getIdY();
                    Integer idY = (thisFinTxfer.getIdY() == null ? 0 : thisFinTxfer.getIdY()) + 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxfer.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdY(" + (idY) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1";
                    try {
                        idYMax = dataManager.loadValue(idYMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idYMaxQry error: " + e.getMessage());
                        idYMax = null;
                    }
                    logger.debug(logPrfx + " --- idYMaxQry result: " + idYMax + "");

                    Integer idY_ = thisFinTxfer.getIdY();
                    Integer idY = idYMax == null ? 0 : idYMax;

                    if (!Objects.equals(idY_, idY)){
                        thisFinTxfer.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdY(" + (idY) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idZ_ = thisFinTxfer.getIdZ();
                    Integer idZ = 0;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxfer.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdY(" + (idZ) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idZ_ = thisFinTxfer.getIdZ();
                    Integer idZ = thisFinTxfer.getIdZ() == null || thisFinTxfer.getIdZ() == 0 || thisFinTxfer.getIdZ() == 1 ? 0 : thisFinTxfer.getIdZ() - 1;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxfer.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdZ(" + (idZ) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idZ_ = thisFinTxfer.getIdZ();
                    Integer idZ = (thisFinTxfer.getIdZ() == null ? 0 : thisFinTxfer.getIdZ()) + 1;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxfer.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdZ(" + (idZ) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                LocalDateTime idTs1 = thisFinTxfer.getIdTs() != null ? thisFinTxfer.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idZMax = 0;
                    String idZMaxQry = "select max(e.idZ) from ampata_GenNode e"
                            + " where e.className = 'FinTxfer'"
                            + " and e.idTs.ts1 = :idTs1";
                    try {
                        idZMax = dataManager.loadValue(idZMaxQry, Integer.class)
                                .store("main")
                                .parameter("idTs1", idTs1)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idZMaxQry error: " + e.getMessage());
                        idZMax = null;
                    }
                    logger.debug(logPrfx + " --- idZMaxQry result: " + idZMax + "");

                    Integer idZ_ = thisFinTxfer.getIdZ();
                    Integer idZ = idZMax == null ? 0 : idZMax;

                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxfer.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxfer.setIdZ(" + (idZ) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Calc(thisFinTxfer) || thisFinTxferIsChanged;
                    String id2_ = thisFinTxfer.getId2();
                    String id2 = thisFinTxfer.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxfer.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                        thisFinTxferIsChanged = true;
                    }

                    thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                    if (thisFinTxferIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                        //dataManager.save(thisFinTxfer);
                    }
                }
            }
        });

        updateFinTxferHelper();
        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("updateColItemIdPartsBtn")
    public void onUpdateColItemIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                boolean isChanged = false;

                isChanged = updateIdParts(thisFinTxfer);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxfersDl.load().");
            finTxfersDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxfers);
        }

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
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                boolean isChanged = false;

                Integer finTxactOption = Optional.ofNullable(updateColItemCalcValsTxactOption.getValue()).orElse(0);
                Integer finTxsetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
                Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);
                isChanged = updateCalcVals(thisFinTxfer, finTxactOption, finTxsetOption, finStmtItmOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxfersDl.load().");
            finTxfersDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxfers);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_FinTxset1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_FinTxset1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_EI1_RoleField", subject = "enterPressHandler")
    private void tmplt_FinTxact1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

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

        Integer finTxactOption = 3; // update finTxact to match this finTxfer

        thisFinTxfers.forEach(thisFinTxfer -> {
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                thisFinTxferIsChanged = updateFinTxact1_Id(thisFinTxfer, finTxactOption) || thisFinTxferIsChanged;
                String id2_ = thisFinTxfer.getId2();
                String id2 = thisFinTxfer.getId2Calc();
                if (!Objects.equals(id2_, id2)){
                    thisFinTxfer.setId2(id2);
                    logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                    thisFinTxferIsChanged = true;
                }

                thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                if (thisFinTxferIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                    //dataManager.save(thisFinTxfer);
                }
            }
        });

        updateFinTxferHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("linkTxactBtn")
    public void onLinkTxactBtnClick(Button.ClickEvent event) {
        String logPrfx = "onLinkTxactBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        int finTxactOption;
        if (!linkTxactAutoCreateChk.isChecked()){
            finTxactOption = 1;
        }else{
            finTxactOption = 2;
        }

        thisFinTxfers.forEach(thisFinTxfer -> {
            if (thisFinTxfer != null) {
                thisFinTxfer = dataContext.merge(thisFinTxfer);

                Boolean thisFinTxferIsChanged = false;

                thisFinTxferIsChanged = updateFinTxact1_Id(thisFinTxfer, finTxactOption) || thisFinTxferIsChanged;
                String id2_ = thisFinTxfer.getId2();
                String id2 = thisFinTxfer.getId2Calc();
                if (!Objects.equals(id2_, id2)){
                    thisFinTxfer.setId2(id2);
                    logger.debug(logPrfx + " --- thisFinTxfer.setId2(" + (id2) + ")");
                    thisFinTxferIsChanged = true;
                }

                thisFinTxferIsChanged = updateId2Cmp(thisFinTxfer) || thisFinTxferIsChanged;

                if (thisFinTxferIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxfer).");
                    //dataManager.save(thisFinTxfer);
                }
            }
        });

        updateFinTxferHelper();
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

        GenNode selFinTxfer0Tracked = dataContext.merge(thisFinTxfers.get(0));
        GenNode selFinTxfer1Tracked = dataContext.merge(thisFinTxfers.get(1));

        if (selFinTxfer0Tracked != null && selFinTxfer1Tracked != null) {
            selFinTxfer0Tracked.setAmtFinTxfer1_Id(selFinTxfer1Tracked);
            logger.debug("Linked FinTxfer "
                    + selFinTxfer0Tracked.getId2() + " [" + selFinTxfer0Tracked.getId() + "]"
                    + " Ref1 linked to "
                    + selFinTxfer1Tracked.getId2() + " [" + selFinTxfer1Tracked.getId() + "]"
            );
        }
        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxfersDl.load().");
            finTxfersDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxfers);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateIdParts(thisFinTxfer);

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
        Integer finTxsetOption = Optional.ofNullable(updateInstItemCalcValsTxsetOption.getValue()).orElse(0);
        Integer finTxactOption = Optional.ofNullable(updateInstItemCalcValsTxactOption.getValue()).orElse(0);
        Integer finStmtItmOption = Optional.ofNullable(updateInstItemCalcValsStmtItmOption.getValue()).orElse(0);

        updateCalcVals(thisFinTxfer, finTxactOption, finTxsetOption, finStmtItmOption);

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
        updateId2(thisFinTxfer);
        updateId2Cmp(thisFinTxfer);
        updateId2Dup(thisFinTxfer);

        logger.debug(logPrfx + " --- id2: " + thisFinTxfer.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxfer);
        updateId2Cmp(thisFinTxfer);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinTxfer.getId2Calc());
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


    @Install(to = "finTxact1_EI1_RoleField", subject = "enterPressHandler")
    private void finTxact1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_EI1_RoleFieldListBtn")
    public void onUpdateFinTxact1_EI1_RoleFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_EI1_RoleFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinTxact1_EI1_RoleList();

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

    @Subscribe("updateDescPat1_IdFieldListBtn")
    public void onUpdateDescPat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDescPat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genPatsDl.load();
        logger.debug(logPrfx + " --- called genPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FinTxfer1_IdFieldListBtn")
    public void onUpdateDesc1FinTxfer1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FinTxfer1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxfer1sDl.load();
        logger.debug(logPrfx + " --- called finTxfer1sDl.load() ");

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
            updateId2Calc(thisFinTxfer);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxfer)");
            updateFinTxact1_Id2Trgt(thisFinTxfer);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateBeg1Ts1FieldBtn")
    public void onUpdateBeg1Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg1Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg1(thisFinTxfer);

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
            updateId2Calc(thisFinTxfer);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxfer)");
            updateFinTxact1_Id2Trgt(thisFinTxfer);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateBeg2Ts1FieldBtn")
    public void onUpdateBeg2Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg2Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg2(thisFinTxfer);

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
            updateId2Calc(thisFinTxfer);

            logger.debug(logPrfx + " --- calling updateFinTxact1_Id2Trgt(thisFinTxfer)");
            updateFinTxact1_Id2Trgt(thisFinTxfer);

            logger.debug(logPrfx + " --- calling updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxfer)");
            updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxfer);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdX(thisFinTxfer);

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
            updateId2Calc(thisFinTxfer);

            logger.debug(logPrfx + " --- calling updateFinTxact1_Id2Trgt(thisFinTxfer)");
            updateFinTxact1_Id2Trgt(thisFinTxfer);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateIdYFieldBtn")
    public void onUpdateIdYFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdYFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdY(thisFinTxfer);

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
            updateId2Calc(thisFinTxfer);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateIdZFieldBtn")
    public void onUpdateIdZFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdZFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdZ(thisFinTxfer);

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



    @Subscribe("updateFinTxact1_Id_Desc1FieldBtn")
    public void onUpdateFinTxact1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_Desc1(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxact1_IdField")
    public void onFinTxact1_IdFieldValueChange(HasValue.ValueChangeEvent<GenNode> event) {
        String logPrfx = "onFinTxact1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            if (thisFinTxfer.getFinTxact1_Id() != null) {
                updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxfer);
            }
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
        Integer finTxactOption = updateInstItemCalcValsTxactOption.getValue();
        if (finTxactOption == null){
            finTxactOption = 0;}
        updateFinTxact1_Id(thisFinTxfer, finTxactOption);

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

    @Subscribe("updateFinTxact1_Id2TrgtBtn")
    public void onUpdateFinTxact1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id2Trgt(thisFinTxfer);

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

    @Install(to = "finTxact1_Id_FinTxset1_EI1_RoleField", subject = "enterPressHandler")
    private void finTxact1_Id_FinTxset1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_FinTxset1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_EI1_RoleFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_EI1_RoleFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinTxact1_Id_FinTxset1_EI1_RoleList();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_GenChan1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_How1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_How1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finHowsDl.load();
        logger.debug(logPrfx + " --- called finHowsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxact1_Id_WhatText1Field", subject = "enterPressHandler")
    private void finTxact1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_WhatText1FieldListBtn")
    public void onUpdateFinTxact1_Id_WhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_WhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

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

    @Install(to = "finTxact1_Id_WhyText1Field", subject = "enterPressHandler")
    private void finTxact1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_WhyText1FieldListBtn")
    public void onUpdateFinTxact1_Id_WhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_WhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

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


    @Subscribe("updateFinTxact1_Id_DescPat1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_DescPat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_DescPat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genPatsDl.load();
        logger.debug(logPrfx + " --- called genPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_Desc1FinTxfer1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Desc1FinTxfer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FinTxfer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxfer1sDl.load();
        logger.debug(logPrfx + " --- called finTxfer1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxfers1_DebtSumFieldBtn")
    public void onUpdateFintxact1_Id_FinTxfers1_DebtSumFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxfers1_DebtSumFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxfers1_DebtSum(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxfers1_CredSumFieldBtn")
    public void onUpdateFintxact1_Id_FinTxfers1_CredSumFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxfers1_CredSumFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxfers1_CredSum(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxfers1_IdCntFieldBtn")
    public void onUpdateFintxact1_Id_FinTxfers1_IdCntFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxfers1_IdCntFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxfers1_IdCnt(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxfers1_DebtEqCredBoxBtn")
    public void onUpdateFintxact1_Id_FinTxfers1_DebtEqCredBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxfers1_DebtEqCredBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxfers1_DebtEqCred(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");

    }
    

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Desc1FieldBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_FinTxset1_Id_Desc1(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_IdFieldBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxsetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxsetOption == null){
            finTxsetOption = 0;}
        updateFinTxact1_FinTxset1_Id(thisFinTxfer, finTxsetOption);
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxsetsDl.load();
        logger.debug(logPrfx + " --- called finTxsetsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id_FinTxset1_Id2TrgtBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxfer);
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Type1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Type1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxsetTypesDl.load();
        logger.debug(logPrfx + " --- called finTxsetTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_GenChan1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_How1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_How1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finHowsDl.load();
        logger.debug(logPrfx + " --- called finHowsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_WhatText1FieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_WhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_WhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxact1_Id_FinTxset1_Id_WhatText1Field", subject = "enterPressHandler")
    private void finTxact1_Id_FinTxset1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_FinTxset1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_What1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_What1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_What1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_WhyText1FieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_WhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_WhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxact1_Id_FinTxset1_Id_WhyText1Field", subject = "enterPressHandler")
    private void finTxact1_Id_FinTxset1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_FinTxset1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Why1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Why1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_Why1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_DescPat1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_DescPat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_DescPat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genPatsDl.load();
        logger.debug(logPrfx + " --- called genPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Desc1FinTxfer1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxfer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxfer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxfer1sDl.load();
        logger.debug(logPrfx + " --- called finTxfer1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_IdFieldBtn")
    public void onUpdateFinStmtItm1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");


        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        // todo
        //updateFinStmtItm1_Id(thisFinTxfer);
        List<GenNode> finStmtItms = finStmtItmCandidsTable.getSelected().stream().toList();
        if (finStmtItms.size() > 0){
            GenNode finStmtItm = finStmtItms.get(0);
            finStmtItm = dataContext.merge(finStmtItm);
            thisFinTxfer.setFinStmtItm1_Id(finStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_IdFieldListBtn")
    public void onUpdateFinStmtItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finStmtItmsDl.load();
        logger.debug(logPrfx + " --- called finStmtItmsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updatefinStmtItmCandidsTableBtn")
    public void onUpdatefinStmtItmCandidsTableBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdatefinStmtItmCandidsTableBtnClick";
        logger.trace(logPrfx + " --> ");


        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        finStmtItmCandidsDl.setQuery("select e from ampata_GenNode e "
                + "where e.className = 'FinStmtItm' "
                + "and e.finStmt1_Id.finAcct1_Id = :finAcct1_Id "
                + "and e.idTs.ts1 between :begTs and :endTs "
                + "and (e.amtDebt = :amt or e.amtCred = :amt) "
                + "order by e.id2");
        GenNode finAcct1 = thisFinTxfer.getFinAcct1_Id();
        if (finAcct1 == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null.");
            notifications.create().withCaption("finAcct1_Id is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            finStmtItmCandidsDl.setParameter("finAcct1_Id",finAcct1);
        }

        HasTmst thisTs = thisFinTxfer.getIdTs();
        LocalDateTime begTs;
        LocalDateTime endTs;
        if (thisTs == null) {
            logger.debug(logPrfx + " --- idTs is null.");
            notifications.create().withCaption("idTs is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            begTs = thisFinTxfer.getIdTs().getTs1().minusDays(3);
            endTs = thisFinTxfer.getIdTs().getTs1().plusDays(3);
            finStmtItmCandidsDl.setParameter("begTs",begTs);
            finStmtItmCandidsDl.setParameter("endTs",endTs);
        }

        BigDecimal amtDebt = thisFinTxfer.getAmtDebt();
        BigDecimal amtCred = thisFinTxfer.getAmtCred();
        if (amtDebt != null) {
            finStmtItmCandidsDl.setParameter("amt",amtDebt);
        }else if (amtCred != null) {
            finStmtItmCandidsDl.setParameter("amt",amtCred);
        }else{
            logger.debug(logPrfx + " --- amtDebt and amtCred are null.");
            notifications.create().withCaption("amtDebt and amtCred are null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }


        finStmtItmCandidsDl.load();
        logger.debug(logPrfx + " --- called finStmtItmCandidsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finStmtItm1_Id_Desc1Field", subject = "enterPressHandler")
    private void finStmtItm1_Id_Desc1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finStmtItm1_Id_Desc1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinStmtItm1_Id_Desc1FieldListBtn")
    public void onUpdateFinStmtItm1_Id_Desc1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_Id_Desc1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Id_Desc1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finStmtItm1_Id_Desc2Field", subject = "enterPressHandler")
    private void finStmtItm1_Id_Desc2FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finStmtItm1_Desc2FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_Id_Desc2FieldListBtn")
    public void onUpdateFinStmtItm1_Id_Desc2FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_Id_Desc2FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Id_Desc2List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finStmtItm1_Id_Desc3Field", subject = "enterPressHandler")
    private void finStmtItm1_Id_Desc3FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finStmtItm1_Id_Desc3FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_Id_Desc3FieldListBtn")
    public void onUpdateFinStmtItm1_Id_Desc3FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_Id_Desc3FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Id_Desc3List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTaxLne1_IdFieldBtn")
    public void onUpdateFinTaxLne1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTaxLne1_Id(thisFinTxfer);

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

    @Install(to = "finTaxLne1_CodeField", subject = "enterPressHandler")
    private void finTaxLne1_CodeFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTaxLne1_CodeFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTaxLne1_CodeFieldListBtn")
    public void onUpdateFinTaxLne1_CodeFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_CodeFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinTaxLne1_CodeList();

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

    @Subscribe("updateAmtFinTxfer1_IdFieldListBtn")
    public void onUpdateAmtFinTxfer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxfer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxfer1sDl.load();
        logger.debug(logPrfx + " --- called finTxfer1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtFinTxfer1_EI1_RateBtn")
    public void onUpdateAmtFinTxfer1_EI1_RateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxfer1_EI1_RateBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtFinTxfer1_EI1_Rate(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtCalcBtn")
    public void onUpdateAmtCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtCalc(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateAmtNet(thisFinTxfer)");
            updateAmtNet(thisFinTxfer);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("amtCredField")
    public void onAmtCredFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtCredFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxfer = finTxferDc.getItemOrNull();
            if (thisFinTxfer == null) {
                logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateAmtNet(thisFinTxfer)");
            updateAmtNet(thisFinTxfer);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtNetBtn")
    public void onUpdateAmtNetBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtNetBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxfer = finTxferDc.getItemOrNull();
        if (thisFinTxfer == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtNet(thisFinTxfer);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtFinFmla1_IdFieldListBtn")
    public void onUpdateAmtFinFmla1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinFmla1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finFmlasDl.load();
        logger.debug(logPrfx + " --- called finFmlasDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull GenNode thisFinTxfer, Integer finTxactOption, Integer finTxsetOption, Integer finStmtItmOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxferCalcVals(thisFinTxfer, finTxactOption) || isChanged;
        isChanged = updateFinTxactCalcVals(thisFinTxfer, finTxsetOption) || isChanged;
        isChanged = updateFinStmtItmCalcVals(thisFinTxfer, finStmtItmOption) || isChanged;
        isChanged = updateFinTxsetCalcVals(thisFinTxfer) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxferCalcVals(@NotNull GenNode thisFinTxfer, Integer finTxactOption) {
        String logPrfx = "updateFinTxferCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxfer Object
        isChanged = updateIdTs(thisFinTxfer) || isChanged;
        isChanged = updateId2Calc(thisFinTxfer) || isChanged;
        isChanged = updateId2Cmp(thisFinTxfer) || isChanged;
        isChanged = updateId2Dup(thisFinTxfer) || isChanged;
        isChanged = updateDesc1(thisFinTxfer) || isChanged;
//        isChanged = updateFinTaxLne1_Id(thisFinTxfer) || isChanged;
        isChanged = updateAmtNet(thisFinTxfer) || isChanged;
        isChanged = updateAmtFinTxfer1_EI1_Rate(thisFinTxfer) || isChanged;
        isChanged = updateAmtCalc(thisFinTxfer) || isChanged;

        isChanged = updateFinTxact1_Id2Trgt(thisFinTxfer) || isChanged;
        isChanged = updateFinTxact1_Id(thisFinTxfer, finTxactOption) || isChanged;

        isChanged = updateFinStmtItm1_Id2Trgt(thisFinTxfer) || isChanged;
        isChanged = updateFinStmtItm1_Id(thisFinTxfer, finTxactOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactCalcVals(@NotNull GenNode thisFinTxfer, Integer finTxsetOption) {
        String logPrfx = "updateFinTxactCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxact Object
        isChanged = updateFinTxact1_Id_Desc1(thisFinTxfer)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxfers1_DebtSum(thisFinTxfer)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxfers1_CredSum(thisFinTxfer)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxfers1_IdCnt(thisFinTxfer)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxfers1_DebtEqCred(thisFinTxfer)  || isChanged;

        isChanged = updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxfer) || isChanged;
        isChanged = updateFinTxact1_FinTxset1_Id(thisFinTxfer, finTxsetOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxsetCalcVals(@NotNull GenNode thisFinTxfer) {
        String logPrfx = "updateFinTxsetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxset Object
        isChanged = updateFinTxact1_Id_FinTxset1_Id_Desc1(thisFinTxfer) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinStmtItmCalcVals(@NotNull GenNode thisFinTxfer, Integer finStmtItmOption) {
        String logPrfx = "updateFinStmtItmCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        
        // Stored in FinStmtItm Object
        // todo
//        isChanged = updateFinStmtItm1_Id_Desc1(thisFinTxfer) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdParts(@NotNull GenNode thisFinTxfer) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateBeg1(thisFinTxfer) || isChanged;
        isChanged = updateBeg2(thisFinTxfer) || isChanged;
        isChanged = updateIdX(thisFinTxfer)  || isChanged;
        isChanged = updateIdY(thisFinTxfer)  || isChanged;
        isChanged = updateIdZ(thisFinTxfer)  || isChanged;
        isChanged = updateIdTs(thisFinTxfer)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinTxfer.getId2();
        String id2 = thisFinTxfer.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinTxfer.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinTxfer.getId2Calc();
        String id2Calc = thisFinTxfer.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinTxfer.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinTxfer.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinTxfer.getId2(),thisFinTxfer.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinTxfer.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxfer.getId2Dup();
        if (thisFinTxfer.getId2() != null) {
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinTxfer' and e.id2 = :id2 and e.id <> :id";
            int id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinTxfer.getId())
                        .parameter("id2", thisFinTxfer.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinTxfer.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinTxfer.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String desc1_ = thisFinTxfer.getDesc1();
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

        String thisStmtItm1_Desc = "";
        GenNode thisFinStmtItm = thisFinTxfer.getFinStmtItm1_Id();
        //thisFinStmtItm = dataContext.merge(thisFinStmtItm);
        if (thisFinStmtItm != null) {
            String thisStmtItm1_Desc1 = Objects.toString(thisFinStmtItm.getDesc1(), "");
            String thisStmtItm1_Desc2 = Objects.toString(thisFinStmtItm.getDesc2(), "");
            List<String> list1 = Arrays.asList(
                    thisStmtItm1_Desc1
                    , thisStmtItm1_Desc2
            );
            thisStmtItm1_Desc = list1.stream().filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining(" "));

        }
        if (!thisStmtItm1_Desc.equals("")) {
            thisStmtItm1_Desc = "for " + thisStmtItm1_Desc;
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
            thisWhat = thisWhat.trim();
        }
        if (!thisWhat.equals("")) {
            thisWhat = "for " + thisWhat;
        }
        logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

        String thisWhy = Objects.toString(thisFinTxfer.getWhyText1(), "");
        if (thisFinTxfer.getFinWhy1_Id() != null) {
            thisWhy = thisWhy + " " + Objects.toString(thisFinTxfer.getFinWhy1_Id().getId2());
            thisWhy = thisWhy.trim();
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
                , thisWhat
                , thisWhy
                , thisHow
                , thisDocVer
                , thisTag);

        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            thisFinTxfer.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxact1_Id_Desc1(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();

        if (thisFinTxact != null) {
            String desc1_ = thisFinTxact.getDesc1();
            String thisType = "";
            if (thisFinTxact.getType1_Id() != null) {
                thisType = Objects.toString(thisFinTxact.getType1_Id().getId2(), "");
            }
            logger.debug(logPrfx + " --- thisType: " + thisType);
            
            String thisAmt = "";
            logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

            String thisCurcy = "";
            logger.debug(logPrfx + " --- thisCurcy: " + thisCurcy);


            String thisChan = "";
            if (thisFinTxact.getGenChan1_Id() != null) {
                thisChan = Objects.toString(thisFinTxact.getGenChan1_Id().getId2(), "");
            }
            if (!thisChan.equals("")) {
                thisChan = "in chan [" + thisChan + "]";
            }
            logger.debug(logPrfx + " --- thisChan: " + thisChan);


            String thisHow = "";
            if (thisFinTxact.getFinHow1_Id() != null) {
                thisHow = Objects.toString(thisFinTxact.getFinHow1_Id().getId2(), "");
            }
            if (!thisHow.equals("")) {
                thisHow = "via " + thisHow;
            }
            logger.debug(logPrfx + " --- thisHow: " + thisHow);

            String thisWhat = Objects.toString(thisFinTxact.getWhatText1(), "");
            if (thisFinTxact.getFinWhat1_Id() != null) {
                thisWhat = thisWhat + " " + Objects.toString(thisFinTxact.getFinWhat1_Id().getId2());
            }
            if (!thisWhat.equals("")) {
                thisWhat = "for " + thisWhat;
            }
            logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

            String thisWhy = Objects.toString(thisFinTxact.getWhyText1(), "");
            if (thisFinTxact.getFinWhy1_Id() != null) {
                thisWhy = thisWhy + " " + Objects.toString(thisFinTxact.getFinWhy1_Id().getId2());
            }
            if (!thisWhy.equals("")) {
                thisWhy = "for " + thisWhy;
            }
            logger.debug(logPrfx + " --- thisWhy: " + thisWhy);

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
                    , thisChan
                    , thisWhat
                    , thisWhy
                    , thisHow
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

    private Boolean updateFinTxact1_Id_FinTxset1_Id_Desc1(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id_FinTxset1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxset = thisFinTxfer.getFinTxact1_Id() == null ? null : thisFinTxfer.getFinTxact1_Id().getFinTxset1_Id();

        if (thisFinTxset != null) {
            //finTxset is a 2nd ref (ref of a ref) of finTxfer and is not automatically loaded into the dataContext
            GenNode thisTrackedTxset = dataContext.merge(thisFinTxset);
            thisFinTxset = thisTrackedTxset;

            String desc1_ = thisFinTxset.getDesc1();

            if (thisFinTxset.getDesc1GenPat1_Id() == null) {

                //thisType
                String thisType = "";
                if (thisFinTxset.getType1_Id() != null) {
                    thisType = Objects.toString(thisFinTxset.getType1_Id().getId2(), "");
                }
                logger.debug(logPrfx + " --- thisType: " + thisType);

                String desc1FinTxfer1_Id2 = null;
                switch (thisType) {
                    case "/Txfer-Exch" ->
                        desc1FinTxfer1_Id2 = thisFinTxset.getId2()+ "/Y01/Z00";
                    default ->
                        desc1FinTxfer1_Id2 = thisFinTxset.getId2()+ "/Y00/Z00";
                }
                GenNode desc1FinTxfer1 = thisFinTxset.getDesc1FinTxfer1_Id() == null
                        ? findFinTxferById2(desc1FinTxfer1_Id2)
                        : thisFinTxset.getDesc1FinTxfer1_Id();

                //thisAmt
                String thisAmt = "";
                if (desc1FinTxfer1 != null){
                    if (desc1FinTxfer1.getAmtDebt() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxfer1.getAmtDebt();
                    }else if (desc1FinTxfer1.getAmtCred() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxfer1.getAmtCred();
                    }
                    if (!thisAmt.equals("")) {
                        thisAmt = thisAmt.trim();
                    }
                }
                logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

                //thisCurcy
                String thisCurcy = "";
                if (desc1FinTxfer1 != null) {
                    if (desc1FinTxfer1.getFinCurcy1_Id() != null) {
                        thisCurcy = Objects.toString(desc1FinTxfer1.getFinCurcy1_Id().getId2(), "");
                    }
                }
                logger.debug(logPrfx + " --- thisCurcy: " + thisCurcy);


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
                    thisHow = "via " + thisHow;
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
                        , thisCurcy
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


    private Boolean updateIdTs(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateIdTs";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxfer.updateIdTs();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg1(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateBeg1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxfer.updateBeg1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg2(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateBeg2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxfer.updateBeg2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxfer.updateIdX();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdY(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateIdY";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxfer.updateIdY();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdZ(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateIdZ";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxfer.updateIdZ();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateFinStmtItm1_Id(@NotNull GenNode thisFinTxfer, Integer finStmtItmOption) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinStmtItm1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode finStmtItm1_Id_ = thisFinTxfer.getFinTxact1_Id();
        GenNode finStmtItm1_Id;
        // Update finStmtItm
        switch (finStmtItmOption) {
            case 1 -> { // Link to Exist Txact
                finStmtItm1_Id = findFinStmtItmById2(thisFinTxfer.getFinStmtItm1_Id2Trgt());
                if (!Objects.equals(finStmtItm1_Id_, finStmtItm1_Id)) {
                    thisFinTxfer.setFinTxact1_Id(finStmtItm1_Id);
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxact1_Id(@NotNull GenNode thisFinTxfer, Integer finTxactOption) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode finTxact1_Id_ = thisFinTxfer.getFinTxact1_Id();
        GenNode finTxact1_Id;
        // Update finTxact
        switch (finTxactOption) {
            case 1 -> { // Link to Exist Txact
                finTxact1_Id = findFinTxactById2(thisFinTxfer.getFinTxact1_Id2Trgt());
                if (!Objects.equals(finTxact1_Id_, finTxact1_Id)) {
                    thisFinTxfer.setFinTxact1_Id(finTxact1_Id);
                    isChanged = true;
                }
            }
            case 2 -> { // Link to Exist/New Txact
                finTxact1_Id = findFinTxactById2(thisFinTxfer.getFinTxact1_Id2Trgt());
                if (finTxact1_Id == null) {
                    finTxact1_Id = createFinTxactFrFinTxfer(thisFinTxfer);
                }
                if (!Objects.equals(finTxact1_Id_, finTxact1_Id)) {
                    thisFinTxfer.setFinTxact1_Id(finTxact1_Id);
                    isChanged = true;
                }
            }
            case 3 -> { // Update Exist Txact
                boolean thisFinTxactIsChanged = false;
                GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
                if (thisFinTxact != null) {
                    if (!Objects.equals(thisFinTxact.getIdTs().getTs1(), thisFinTxfer.getIdTs().getTs1())) {
                        thisFinTxact.getBeg1().setTs1(thisFinTxfer.getIdTs().getTs1());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getIdX(), thisFinTxfer.getIdX())) {
                        thisFinTxact.setIdX(thisFinTxfer.getIdX());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getIdY(), thisFinTxfer.getIdY())) {
                        thisFinTxact.setIdY(thisFinTxfer.getIdY());
                        thisFinTxactIsChanged = true;
                    }
                    if (thisFinTxactIsChanged) {
                        thisFinTxact.updateIdTs();
                        thisFinTxact.setId2Calc(thisFinTxact.getId2CalcFrFields());
                        thisFinTxact.setId2(thisFinTxact.getId2Calc());
                        dataManager.save(thisFinTxact);
                    }
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateFinTxact1_Id2Trgt(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String finTxact1_Id2Trgt_ = thisFinTxfer.getFinTxact1_Id2Trgt();
        String finTxact1_Id2Trgt = thisFinTxfer.getId2CalcFrFields().substring(0, 24);
        if(!Objects.equals(finTxact1_Id2Trgt_, finTxact1_Id2Trgt)){
            isChanged = true;
            thisFinTxfer.setFinTxact1_Id2Trgt(finTxact1_Id2Trgt);
            logger.debug(logPrfx + " --- finTxact1_Id2Trgt: " + finTxact1_Id2Trgt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFintxact1_Id_FinTxfers1_DebtSum(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFintxact1_Id_FinTxfers1_DebtSum";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
        if (thisFinTxact != null) {
            BigDecimal finTxact1_DebtSum_ = thisFinTxact.getFinTxfers1_DebtSum();
            BigDecimal finTxact1_DebtSum = null ;
            String qry1 = "select sum(e.amtDebt) from ampata_GenNode e where e.className = 'FinTxfer' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
            try{
                finTxact1_DebtSum = dataManager.loadValue(qry1,BigDecimal.class)
                        .store("main")
                        .parameter("finTxact1_Id",thisFinTxact)
                        .one();
                if (finTxact1_DebtSum == null) {
                    finTxact1_DebtSum = BigDecimal.valueOf(0);}
                logger.debug(logPrfx + " --- finTxact1_DebtSum: " + finTxact1_DebtSum);

            } catch (IllegalStateException e){
                logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                logger.debug(logPrfx + " --- debtSum: null");
            }

            if(!Objects.equals(finTxact1_DebtSum_, finTxact1_DebtSum)){
                isChanged = true;
                thisFinTxact.setFinTxfers1_DebtSum(finTxact1_DebtSum);
                logger.debug(logPrfx + " --- finTxact1_DebtSum: " + finTxact1_DebtSum);
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFintxact1_Id_FinTxfers1_CredSum(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFintxact1_Id_FinTxfers1_CredSum";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
        if (thisFinTxact != null) {
            BigDecimal finTxact1_CredSum_ = thisFinTxact.getFinTxfers1_CredSum();
            BigDecimal finTxact1_CredSum = null ;
            String qry1 = "select sum(e.amtCred) from ampata_GenNode e where e.className = 'FinTxfer' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
            try{
                finTxact1_CredSum = dataManager.loadValue(qry1,BigDecimal.class)
                        .store("main")
                        .parameter("finTxact1_Id",thisFinTxact)
                        .one();
                if (finTxact1_CredSum == null) {
                    finTxact1_CredSum = BigDecimal.valueOf(0);}
                logger.debug(logPrfx + " --- finTxact1_CredSum: " + finTxact1_CredSum);

            } catch (IllegalStateException e){
                logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                logger.debug(logPrfx + " --- debtSum: null");
            }

            if(!Objects.equals(finTxact1_CredSum_, finTxact1_CredSum)){
                isChanged = true;
                thisFinTxact.setFinTxfers1_CredSum(finTxact1_CredSum);
                logger.debug(logPrfx + " --- finTxact1_CredSum: " + finTxact1_CredSum);
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFintxact1_Id_FinTxfers1_IdCnt(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFintxact1_Id_FinTxfers1_IdCnt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
        if (thisFinTxact != null) {
            Integer finTxact1_IdCnt_ = thisFinTxact.getFinTxfers1_IdCnt();
            Integer finTxact1_IdCnt = null ;
            String qry1 = "select count(e.amtNet) from ampata_GenNode e where e.className = 'FinTxfer' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
            try{
                finTxact1_IdCnt = dataManager.loadValue(qry1,Integer.class)
                        .store("main")
                        .parameter("finTxact1_Id",thisFinTxact)
                        .one();
                if (finTxact1_IdCnt == null) {
                    finTxact1_IdCnt = 0;}
                logger.debug(logPrfx + " --- finTxact1_IdCnt: " + finTxact1_IdCnt);

            } catch (IllegalStateException e){
                logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                logger.debug(logPrfx + " --- debtSum: null");
            }

            if(!Objects.equals(finTxact1_IdCnt_, finTxact1_IdCnt)){
                isChanged = true;
                thisFinTxact.setFinTxfers1_IdCnt(finTxact1_IdCnt);
                logger.debug(logPrfx + " --- finTxact1_IdCnt: " + finTxact1_IdCnt);
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFintxact1_Id_FinTxfers1_DebtEqCred(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFintxact1_Id_FinTxfers1_DebtEqCred";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
        if (thisFinTxact != null) {
            Boolean finTxact1_DebtEqCred_ = thisFinTxact.getFinTxfers1_DebtEqCred();
            Boolean finTxact1_DebtEqCred = Objects.equals(thisFinTxact.getFinTxfers1_DebtSum()
                                                        , thisFinTxact.getFinTxfers1_CredSum());

            if(!Objects.equals(finTxact1_DebtEqCred_, finTxact1_DebtEqCred)){
                isChanged = true;
                thisFinTxact.setFinTxfers1_DebtEqCred(finTxact1_DebtEqCred);
                logger.debug(logPrfx + " --- finTxact1_DebtEqCred: " + finTxact1_DebtEqCred);
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinStmtItm1_Id2Trgt(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinStmtItm1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String finStmtItm1_Id2Trgt_ = thisFinTxfer.getFinStmtItm1_Id2Trgt();
        // TODO
       
        String finStmtItm1_Id2Trgt = "";
        if(!Objects.equals(finStmtItm1_Id2Trgt_, finStmtItm1_Id2Trgt)){
            isChanged = true;
            thisFinTxfer.setFinTxact1_Id2Trgt(finStmtItm1_Id2Trgt);
            logger.debug(logPrfx + " --- finStmtItm1_Id2Trgt: " + finStmtItm1_Id2Trgt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private GenNode findFinTxferById2(@NotNull String finTxfer_Id2) {
        String logPrfx = "findFinTxferById2";
        logger.trace(logPrfx + " --> ");

        if (finTxfer_Id2 == null) {
            logger.debug(logPrfx + " --- finTxfer_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxfer_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxfer' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxfer_Id2);

        GenNode finTxfer1_Id = null;
        try {
            finTxfer1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finTxfer_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finTxfer1_Id;
    }
    private GenNode findFinTxactById2(@NotNull String finTxact_Id2) {
        String logPrfx = "findFinTxactById2";
        logger.trace(logPrfx + " --> ");

        if (finTxact_Id2 == null) {
            logger.debug(logPrfx + " --- finTxact_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxact_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        GenNode finTxact1_Id = null;
        try {
            finTxact1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finTxact_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            // notifications.create().withCaption("FinTxact with id2: " + finTxact1_Id2Trgt + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finTxact1_Id;

    }

    private GenNode createFinTxactFrFinTxfer(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "createFinTxactFrFinTxfer";
        logger.trace(logPrfx + " --> ");

        String finTxact_Id2 = thisFinTxfer.getFinTxact1_Id2Trgt();
        if (finTxact_Id2 == null) {
            logger.debug(logPrfx + " --- finTxact_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxact_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        try {
            GenNode finTxact = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finTxact_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            logger.trace(logPrfx + " <-- ");
            return finTxact;

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

            GenNode newFinTxact = dataManager.create(GenNode.class);
            newFinTxact.setClassName("FinTxact");

            HasTmst beg1 = dataManager.create(HasTmst.class);
            beg1.setTs1(thisFinTxfer.getIdTs().getTs1());
            newFinTxact.setBeg1(beg1);
            newFinTxact.updateIdTs();

            newFinTxact.setIdX(thisFinTxfer.getIdX());
            newFinTxact.setIdY(thisFinTxfer.getIdY());

            newFinTxact.setId2Calc(newFinTxact.getId2CalcFrFields());
            newFinTxact.setId2(newFinTxact.getId2Calc());


            GenNode savedFinTxact = dataManager.save(newFinTxact);

            GenNode mergedFinTxact = dataContext.merge(savedFinTxact);
            logger.debug(logPrfx + " --- created FinTxfer id: " + mergedFinTxact.getId());
            notifications.create().withCaption("Created FinTxfer with id2:" + mergedFinTxact.getId2()).show();

            logger.trace(logPrfx + " <-- ");
            return mergedFinTxact;
        }
    }


    private Boolean updateFinTxact1_FinTxset1_Id(@NotNull GenNode thisFinTxfer, Integer finTxsetOption) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_FinTxset1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
        if (thisFinTxact != null){
            GenNode finTxset1_Id_ = thisFinTxact.getFinTxset1_Id();
            GenNode finTxset1_Id;
            // Update finTxact
            switch (finTxsetOption) {
                case 1 -> { // Link to Exist Txset
                    finTxset1_Id = findFinTxsetById2(thisFinTxact.getFinTxset1_Id2Trgt());
                    if (!Objects.equals(finTxset1_Id_, finTxset1_Id)) {
                        isChanged = true;
                        thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                    }
                }
                case 2 -> { // Link to Exist/New Txset
                    finTxset1_Id = findFinTxsetById2(thisFinTxact.getFinTxset1_Id2Trgt());
                    if (finTxset1_Id == null) {
                        finTxset1_Id = createFinTxsetFrFinTxfer(thisFinTxfer);
                    }
                    if (!Objects.equals(finTxset1_Id_, finTxset1_Id)) {
                        thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                        isChanged = true;
                    }
                }
                case 3 -> { // Update Exist Txact
                    boolean thisFinTxsetIsChanged = false;
                    GenNode thisFinTxset = thisFinTxact.getFinTxset1_Id();
                    if (thisFinTxset != null) {
                        if (!Objects.equals(thisFinTxset.getIdTs().getTs1(), thisFinTxfer.getIdTs().getTs1())) {
                            thisFinTxset.getBeg1().setTs1(thisFinTxfer.getIdTs().getTs1());
                            thisFinTxsetIsChanged = true;
                        }
                        if (!Objects.equals(thisFinTxset.getIdX(), thisFinTxfer.getIdX())) {
                            thisFinTxset.setIdX(thisFinTxfer.getIdX());
                            thisFinTxsetIsChanged = true;
                        }
                        if (thisFinTxsetIsChanged) {
                            thisFinTxset.updateIdTs();
                            thisFinTxset.setId2Calc(thisFinTxset.getId2CalcFrFields());
                            thisFinTxset.setId2(thisFinTxset.getId2Calc());
                            dataManager.save(thisFinTxset);
                        }
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxact1_FinTxset1_Id2Trgt(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateFinTxact1_FinTxset1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode thisFinTxact = thisFinTxfer.getFinTxact1_Id();
        if (thisFinTxact != null){
            String id2Calc_ = thisFinTxfer.getId2Calc();
            String id2Calc = thisFinTxfer.getId2CalcFrFields().substring(0, 20);
            if (!Objects.equals(id2Calc_, id2Calc)){
                isChanged = true;
                thisFinTxact.setFinTxset1_Id2Trgt(id2Calc);
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private GenNode findFinTxsetById2(@NotNull String finTxset_Id2) {
        String logPrfx = "findFinTxsetById2";
        logger.trace(logPrfx + " --> ");

        if (finTxset_Id2 == null) {
            logger.debug(logPrfx + " --- finTxset_Id2 is null.");
            notifications.create().withCaption("finTxset_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinTxset' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxset_Id2);

        GenNode finTxset1_Id = null;
        try {
            finTxset1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finTxset_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            // notifications.create().withCaption("finTxset with id2: " + finTxset1_Id2Trgt + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finTxset1_Id;

    }

    private GenNode createFinTxsetFrFinTxfer(@NotNull GenNode thisFinTxfer) {
        // Assume thisFinTxfer is not null
        String logPrfx = "createFinTxsetFrFinTxfer";
        logger.trace(logPrfx + " --> ");

        GenNode mergedFinTxset = null;
        if (thisFinTxfer.getFinTxact1_Id() != null) {
            String finTxset_Id2 = thisFinTxfer.getFinTxact1_Id().getFinTxset1_Id2Trgt();
            if (finTxset_Id2 == null) {
                logger.debug(logPrfx + " --- finTxset_Id2 is null.");
                notifications.create().withCaption("finTxset_Id2 is empty. Please set it to correct value.").show();
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            String qry = "select e from ampata_GenNode e where e.className = 'FinTxset' and e.id2 = :id2";
            logger.debug(logPrfx + " --- qry: " + qry);
            logger.debug(logPrfx + " --- qry:id2: " + finTxset_Id2);

            try {
                GenNode finTxset = dataManager.load(GenNode.class)
                        .query(qry)
                        .parameter("id2", finTxset_Id2)
                        .one();
                logger.debug(logPrfx + " --- query qry returned ONE result");
                logger.trace(logPrfx + " <-- ");
                return finTxset;

            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- query qry returned NO results");

                GenNode newFinTxset = dataManager.create(GenNode.class);
                newFinTxset.setClassName("FinTxset");

                HasTmst beg1 = dataManager.create(HasTmst.class);
                beg1.setTs1(thisFinTxfer.getIdTs().getTs1());
                newFinTxset.setBeg1(beg1);
                newFinTxset.updateIdTs();

                newFinTxset.setIdX(thisFinTxfer.getIdX());

                newFinTxset.setId2Calc(newFinTxset.getId2CalcFrFields());
                newFinTxset.setId2(newFinTxset.getId2Calc());


                GenNode savedFinTxset = dataManager.save(newFinTxset);

                mergedFinTxset = dataContext.merge(savedFinTxset);
                logger.debug(logPrfx + " --- created FinTxset id: " + mergedFinTxset.getId());
                notifications.create().withCaption("Created FinTxset with id2:" + mergedFinTxset.getId2()).show();
            }

        }
        logger.trace(logPrfx + " <-- ");
        return mergedFinTxset;
    }


    private GenNode findFinStmtItmById2(@NotNull String finStmtItm_Id2) {
        String logPrfx = "findFinStmtItmById2";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from ampata_GenNode e where e.className = 'FinStmtItm' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finStmtItm_Id2);

        GenNode finStmtItm_Id = null;
        try {
            finStmtItm_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finStmtItm_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finStmtItm_Id;

    }


    private Boolean updateAmtFinTxfer1_EI1_Rate(@NotNull GenNode thisFinTxfer) {
        //Assume thisFinTxfer is not null
        String logPrfx = "updateAmtFinTxfer1_EI1_Rate";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal rate_ = thisFinTxfer.getAmtFinTxfer1_EI1_Rate();

        FinFmla amtFinFmla1 = thisFinTxfer.getAmtFinFmla1_Id();
        if (amtFinFmla1 == null) {
            logger.debug(logPrfx + " --- amtFinFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else if (!Objects.equals(amtFinFmla1.getId2(), "Ref1 Mult Exch_Rate")) {
            logger.debug(logPrfx + " --- amtFinFmla1: " + amtFinFmla1.getId2());
            notifications.create().withCaption("amtFinFmla1_Id must be 'Ref1 Mult Exch_Rate' to get an exchange rate.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- amtFinFmla1: " + amtFinFmla1.getId());
        }

        GenNode amtFinTxfer1 = thisFinTxfer.getAmtFinTxfer1_Id();
        if (amtFinTxfer1 == null) {
            logger.debug(logPrfx + " --- amtFinTxfer1: null");
            notifications.create().withCaption("Unable to get reference to the other FinTxfer. Please ensure amtFinTxfer1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        GenNode curcyFr = thisFinTxfer.getAmtFinTxfer1_Id().getFinCurcy1_Id();
        if (curcyFr == null) {
            logger.debug(logPrfx + " --- curcyFr: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxfer. Please ensure a FinTxfer1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- curcyFr.Id: " + curcyFr.getId());
        }

        GenNode curcyTo = thisFinTxfer.getFinCurcy1_Id();
        if (curcyTo == null) {
            logger.debug(logPrfx + " --- curcyTo: null");
            notifications.create().withCaption("Unable to get the currency from this FinTxfer. Please ensure a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
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
            return isChanged;
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

        if (!Objects.equals(rate_, rate) && qryRsltGood) {
            thisFinTxfer.setAmtFinTxfer1_EI1_Rate(rate);
            logger.debug(logPrfx + " --- rate: " + rate);
            isChanged = true;
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    private Boolean updateAmtCalc(@NotNull GenNode thisFinTxfer) {
        //Assume thisFinTxfer is not null
        String logPrfx = "updateAmtCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtCalc_ = thisFinTxfer.getAmtCalc();
        BigDecimal amtCalc = BigDecimal.valueOf(0);
        boolean amtCalcGood = false;

        FinFmla amtFinFmla1 = thisFinTxfer.getAmtFinFmla1_Id();
        if (amtFinFmla1 == null) {
            logger.debug(logPrfx + " --- amtFinFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- amtFinFmla1: " + amtFinFmla1.getId());
        }

        GenNode prev1FinTxfer;
        GenNode prev2FinTxfer;

        // Switch statement over above string
        switch (amtFinFmla1.getId2()) {

            // Ref1 Mult Exch_Rate
            case "Ref1 Mult Exch_Rate":

                GenNode amtFinTxfer1 = thisFinTxfer.getAmtFinTxfer1_Id();
                if (amtFinTxfer1 == null) {
                    logger.debug(logPrfx + " --- amtFinTxfer1: null");
                    notifications.create().withCaption("Unable to get amtFinTxfer1. Please ensure amtFinTxfer1 has a finTxfer selected.").show();
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- amtFinTxfer1: " + amtFinTxfer1.getId());
                }

                BigDecimal val = amtFinTxfer1.getAmtNet();
                if (val == null) {
                    logger.debug(logPrfx + " --- amtFinTxfer1.getAmtNet(): null");
                    notifications.create().withCaption("Unable to get amtFinTxfer1.amtNet(). Please ensure  amtFinTxfer1.amtNet() has a value.").show();
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- amtFinTxfer1.getAmtNet(): " + val);
                }

                BigDecimal rate = thisFinTxfer.getAmtFinTxfer1_EI1_Rate();
                if (rate == null){
                    logger.debug(logPrfx + " --- thisFinTxfer.getAmtFinTxfer1_EI1_Rate(): null");
                    notifications.create().withCaption("Unable to get thisFinTxfer.getAmtFinTxfer1_EI1_Rate(). Please ensure thisFinTxfer.getFinTxfer1_EI1_Rate() has a value.").show();
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
                amtCalc = val.multiply(rate).setScale(2, RoundingMode.HALF_UP).negate();
                amtCalcGood = true;
                break;

            case "Prev2.Cr Sub Prev1.Dr":
                prev1FinTxfer = getPrevFinTxfer(thisFinTxfer);
                if (prev1FinTxfer != null) {
                    BigDecimal prev1Dr = prev1FinTxfer.getAmtDebt() != null ? prev1FinTxfer.getAmtDebt() : BigDecimal.valueOf(0);

                    prev2FinTxfer = getPrevFinTxfer(prev1FinTxfer);
                    if (prev2FinTxfer != null) {
                        BigDecimal prev2Cr = prev2FinTxfer.getAmtCred() != null ? prev2FinTxfer.getAmtCred() : BigDecimal.valueOf(0);

                        amtCalc = prev2Cr.subtract(prev1Dr);
                        amtCalcGood = true;
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

                        amtCalc = prev2Dr.subtract(prev1Cr);
                        amtCalcGood = true;
                    }
                }
                break;


            default:
                logger.debug(logPrfx + " --- formula not implemented: " + amtFinFmla1.getId2());
                notifications.create().withCaption("Formula not implemented: " + amtFinFmla1.getId2()).show();
                logger.trace(logPrfx + " <-- ");
                return isChanged;
        }

        if (!Objects.equals(amtCalc_, amtCalc) && amtCalcGood) {
            thisFinTxfer.setAmtCalc(amtCalc);
            logger.debug(logPrfx + " --- amtCalc: " + amtCalc);
            isChanged = true;
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNet(@NotNull GenNode thisFinTxfer) {
        //Assume thisFinTxfer is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtNet_ = thisFinTxfer.getAmtNet();

        GenNode thisFinAcct = thisFinTxfer.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        GenNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal amtNet = BigDecimal.valueOf(0);
        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();

        if (thisFinTxfer.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                amtNet = amtNet.subtract(thisFinTxfer.getAmtDebt());
            } else {
                amtNet = amtNet.add(thisFinTxfer.getAmtDebt());
            }
        }

        if (thisFinTxfer.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                amtNet = amtNet.subtract(thisFinTxfer.getAmtCred());
            } else {
                amtNet = amtNet.add(thisFinTxfer.getAmtCred());
            }
        }
        if(!Objects.equals(amtNet_, amtNet)){
            thisFinTxfer.setAmtNet(amtNet);
            logger.debug(logPrfx + " --- amtNet: " + amtNet);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTaxLne1_Id(@NotNull GenNode thisFinTxfer) {
        //Assume thisFinTxfer is not null
        String logPrfx = "updateFinTaxLne1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        GenNode finTaxLne_ = thisFinTxfer.getFinTaxLne1_Id();
        GenNode finTaxLne = thisFinTxfer.getFinAcct1_Id() == null ? null :
                thisFinTxfer.getFinAcct1_Id().getFinTaxLne1_Id();

        if (!Objects.equals(finTaxLne_, finTaxLne)){
            thisFinTxfer.setFinTaxLne1_Id(finTaxLne);
            logger.debug(logPrfx + " --- finTaxLne.id2: " + finTaxLne == null ? "null" : finTaxLne.getId2());
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
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


        tmplt_FinTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_WhatText1Field.setOptionsList()");

        finTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_WhatText1Field.setOptionsList()");


        tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_FinTxset1_Id_WhatText1Field.setOptionsList()");

        finTxact1_Id_FinTxset1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_FinTxset1_Id_WhatText1Field.setOptionsList()");

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


        tmplt_FinTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_WhatText1Field.setOptionsList()");

        finTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_WhatText1Field.setOptionsList()");


        tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_FinTxset1_Id_WhyText1Field.setOptionsList()");

        finTxact1_Id_FinTxset1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_FinTxset1_Id_WhyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxact1_Id_FinTxset1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_Id_FinTxset1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTxset1_EI1_Role"
                + " from ampata_GenNode e"
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

        tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField.setOptionsList()");

        finTxact1_Id_FinTxset1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxact1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTxact1_EI1_Role"
                + " from ampata_GenNode e"
                + " where e.className = 'FinTxfer'"
                + " and e.finTxact1_EI1_Role IS NOT NULL"
                + " order by e.finTxact1_EI1_Role"
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

        tmplt_FinTxact1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_EI1_RoleField.setOptionsList()");

        finTxact1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finTxact1_EI1_RoleField.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinStmtItm1_Id_Desc1List(){
        String logPrfx = "reloadFinStmtItm1_Id_Desc1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc1"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.desc1 IS NOT NULL"
                + " order by e.desc1"
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

        finStmtItm1_Id_Desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Id_Desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Id_Desc2List(){
        String logPrfx = "reloadFinStmtItm1_Id_Desc2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc2"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.desc2 IS NOT NULL"
                + " order by e.desc2"
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

        finStmtItm1_Id_Desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Id_Desc2Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Id_Desc3List(){
        String logPrfx = "reloadFinStmtItm1_Id_Desc3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc3"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.desc3 IS NOT NULL"
                + " order by e.desc3"
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

        finStmtItm1_Id_Desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Id_Desc3Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinTaxLne1_CodeList(){
        String logPrfx = "reloadFinTaxLne1_CodeList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTaxLne1_Code"
                + " from ampata_GenNode e"
                + " where e.className = 'FinTxfer'"
                + " and e.finTaxLne1_Code IS NOT NULL"
                + " order by e.finTaxLne1_Code"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> codes = null;
        try {
            codes = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + codes.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_FinTaxLne1_CodeField.setOptionsList(codes);
        logger.debug(logPrfx + " --- called tmplt_FinTaxLne1_CodeField.setOptionsList()");

        finTaxLne1_CodeField.setOptionsList(codes);
        logger.debug(logPrfx + " --- called finTaxLne1_CodeField.setOptionsList()");

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

    private Integer getIdXMax(LocalDateTime idTs1){
        String logPrfx = "getIdXMax";
        logger.trace(logPrfx + " --> ");

        Integer idX, idXMax;
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
            return null;
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

    private Integer getIdYMax(LocalDateTime idTs1, Integer idX) {
        String logPrfx = "getIdYMax";
        logger.trace(logPrfx + " --> ");

        Integer idY, idYMax = 0;
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
            return null;
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

    private Integer getIdZMax(LocalDateTime idTs1, Integer idX, Integer idY) {
        String logPrfx = "getIdZMax";
        logger.trace(logPrfx + " --> ");

        Integer idZ, idZMax = 0;
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
            return null;
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
            return null;
        }
        logger.debug(logPrfx + " --- idYCntIsNullQry result: " + idZCntIsNull + "");

        if (idZMax == null && idZCntIsNull != 0){
            idZ = 1;
        }else{
            idZ = idZMax == null ? 0 : idZMax + 1;
        }

        return idZ;
    }



}
