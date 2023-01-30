package ca.ampautomation.ampata.screen.sysnode.fin;

import ca.ampautomation.ampata.entity.*;
import com.google.common.base.Strings;
import io.jmix.core.*;
import io.jmix.multitenancy.core.TenantProvider;
import io.jmix.multitenancyui.MultitenancyUiSupport;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.component.data.table.ContainerTableItems;
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
// import javax.transaction.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

//@PessimisticLock
@UiController("ampata_FinTxactItm.browse2")
@UiDescriptor("fin-txact-itm-browse2.xml")
@LookupComponent("table")
public class FinTxactItmBrowse2 extends MasterDetailScreen<SysNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SysNodeRepository repo;

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
    private MultitenancyUiSupport multitenancyUiSupport;

    @Autowired
    private TenantProvider tenantProvider;

    @Autowired
    private ComboBox<String> tenantField;


    @Autowired
    protected Filter filter;

    //FinTxactItm filterConfig1A
    @Autowired
    protected PropertyFilter<SysNodeType> filterConfig1A_type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_idDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_idDtLE;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_finTxact1_EI1_Role;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_idX;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_idY;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_idZ;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_finAcct1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_finDept1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_finCurcy1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_genDocVer1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_genTag1_Id;
    
    //FinTxact filterConfig1A
    @Autowired
    protected PropertyFilter<String> filterConfig1A_finTxact1_Id_finTxset1_EI1_Role;
    

    //FinTxact.FinTxset Config1A
    @Autowired
    protected PropertyFilter<SysNodeType> filterConfig1A_finTxact1_Id_finTxset1_Id_type1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_finTxact1_Id_finTxset1_Id_genChan1_Id;

    @Autowired
    protected PropertyFilter<FinHow> filterConfig1A_finTxact1_Id_finTxset1_Id_finHow1_Id;

    @Autowired
    protected PropertyFilter<FinWhat> filterConfig1A_finTxact1_Id_finTxset1_Id_finWhat1_Id;

    @Autowired
    protected PropertyFilter<FinWhy> filterConfig1A_finTxact1_Id_finTxset1_Id_finWhy1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_finTxact1_Id_finTxset1_Id_genTag1_Id;

    
    //FinStmtItm filterConfig1A
    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_finStmtItm1_Id_FinStmt1_Id;


    //FinTxactItm filterConfig1B
    @Autowired
    protected PropertyFilter<SysNodeType> filterConfig1B_type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_idDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_idDtLE;

    @Autowired
    protected PropertyFilter<String> filterConfig1B_finTxact1_EI1_Role;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idX;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idY;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idZ;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_finAcct1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_finDept1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_finCurcy1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_genDocVer1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_genTag1_Id;

    //FinTxact filterConfig1B
    @Autowired
    protected PropertyFilter<String> filterConfig1B_finTxact1_Id_finTxset1_EI1_Role;


    //FinTxact.FinTxset filterConfig1B
    @Autowired
    protected PropertyFilter<SysNodeType> filterConfig1B_finTxact1_Id_finTxset1_Id_type1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_finTxact1_Id_finTxset1_Id_genChan1_Id;

    @Autowired
    protected PropertyFilter<FinHow> filterConfig1B_finTxact1_Id_finTxset1_Id_finHow1_Id;

    @Autowired
    protected PropertyFilter<FinWhat> filterConfig1B_finTxact1_Id_finTxset1_Id_finWhat1_Id;

    @Autowired
    protected PropertyFilter<FinWhy> filterConfig1B_finTxact1_Id_finTxset1_Id_finWhy1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_finTxact1_Id_finTxset1_Id_genTag1_Id;


    //FinStmtItm
    @Autowired
    protected PropertyFilter<SysNode> filterConfig1B_finStmtItm1_Id_FinStmt1_Id;


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

    //FinTxset tmplt
    @Autowired
    protected EntityComboBox<SysNodeType> tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysNode> tmplt_FinTxact1_Id_FinTxset1_Id_GenChan1_IdField;

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


    //FinTxact tmplt
    @Autowired
    protected EntityComboBox<SysNodeType> tmplt_FinTxact1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_Type1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_FinTxset1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxset1_EI1_RoleFieldChk;

    //FinTxactItm tmplt
    @Autowired
    protected EntityComboBox<SysNodeType> tmplt_Type1_IdField;

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
    protected DateField<LocalDateTime> tmplt_Beg2Ts1Field;

    @Autowired
    protected CheckBox tmplt_Beg2Ts1FieldChk;

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
    protected EntityComboBox<SysNode> tmplt_GenChan1_IdField;

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
    protected EntityComboBox<SysNode> tmplt_FinStmtItm1_IdField;

    @Autowired
    protected CheckBox tmplt_FinStmtItm1_IdFieldChk;


    @Autowired
    protected EntityComboBox<SysNode> tmplt_FinTaxLne1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTaxLne1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTaxLne1_CodeField;

    @Autowired
    protected CheckBox tmplt_FinTaxLne1_CodeFieldChk;


    @Autowired
    protected EntityComboBox<SysNode> tmplt_finAcct1_IdField;

    @Autowired
    protected CheckBox tmplt_finAcct1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysNode> tmplt_finDept1_IdField;

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
    protected EntityComboBox<SysNode> tmplt_finCurcy1_IdField;

    @Autowired
    protected CheckBox tmplt_finCurcy1_IdFieldChk;


    @Autowired
    protected EntityComboBox<FinFmla> tmplt_amtFinFmla1_IdField;

    @Autowired
    protected CheckBox tmplt_amtFinFmla1_IdFieldChk;


    @Autowired
    protected CheckBox updateFilterConfig1A_IdDtLE_SyncChk;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdDtGERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdXRdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdYRdo;



    @Autowired
    private Table<SysNode> table;

    @Autowired
    private CollectionContainer<SysNode> finTxactItmsDc;

    @Autowired
    private DataLoader finTxactItmsDl;

    @Autowired
    private InstanceContainer<SysNode> finTxactItmDc;

    private CollectionContainer<SysNodeType> finTxactItmTypesDc;

    private CollectionLoader<SysNodeType> finTxactItmTypesDl;


    private CollectionContainer<SysNode> genChansDc;

    private CollectionLoader<SysNode> genChansDl;


    private CollectionContainer<SysNode> genDocVersDc;

    private CollectionLoader<SysNode> genDocVersDl;


    private CollectionContainer<SysNode> genTagsDc;

    private CollectionLoader<SysNode> genTagsDl;


    private CollectionContainer<SysNode> finTxactsDc;

    private CollectionLoader<SysNode> finTxactsDl;


    private CollectionContainer<SysNodeType> finTxactTypesDc;

    private CollectionLoader<SysNodeType> finTxactTypesDl;

    private CollectionContainer<SysNode> finTxact1_IdCandidsDc;

    private CollectionLoader<SysNode> finTxact1_IdCandidsDl;


    private CollectionContainer<SysNode> finTxsetsDc;

    private CollectionLoader<SysNode> finTxsetsDl;


    private CollectionContainer<SysNodeType> finTxsetTypesDc;

    private CollectionLoader<SysNodeType> finTxsetTypesDl;

    private CollectionContainer<SysNode> finTxset1_IdCandidsDc;

    private CollectionLoader<SysNode> finTxset1_IdCandidsDl;


    private CollectionContainer<SysNode> finStmtsDc;

    private CollectionLoader<SysNode> finStmtsDl;

    private CollectionContainer<SysNode> finStmtItmsDc;

    private CollectionLoader<SysNode> finStmtItmsDl;

    private CollectionContainer<SysNode> finStmtItm1_IdCandidsDc;

    private CollectionLoader<SysNode> finStmtItm1_IdCandidsDl;


    private CollectionContainer<SysNode> finDeptsDc;

    private CollectionLoader<SysNode> finDeptsDl;


    private CollectionContainer<SysNode> finTaxLnesDc;

    private CollectionLoader<SysNode> finTaxLnesDl;


    private CollectionContainer<SysNode> finAcctsDc;

    private CollectionLoader<SysNode> finAcctsDl;


    private CollectionContainer<SysNode> finCurcysDc;

    private CollectionLoader<SysNode> finCurcysDl;


    private CollectionContainer<SysNode> finTxactItm1sDc;

    private CollectionLoader<SysNode> finTxactItm1sDl;


    private CollectionContainer<FinFmla> finFmlasDc;

    private CollectionLoader<FinFmla> finFmlasDl;


    private CollectionContainer<FinHow> finHowsDc;

    private CollectionLoader<FinHow> finHowsDl;


    private CollectionContainer<FinWhat> finWhatsDc;

    private CollectionLoader<FinWhat> finWhatsDl;


    private CollectionContainer<FinWhy> finWhysDc;

    private CollectionLoader<FinWhy> finWhysDl;


    private CollectionContainer<SysPat> sysPatsDc;

    private CollectionLoader<SysPat> sysPatsDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<SysNodeType> type1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_EI1_RoleField;

    @Autowired
    private EntityComboBox<SysNode> genChan1_IdField;

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
    private EntityComboBox<SysPat> desc1SysNode1_IdField;

    @Autowired
    private EntityComboBox<SysNode> desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<SysNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<SysNode> genTag1_IdField;

    @Autowired
    private EntityComboBox<SysNode> genTag2_IdField;

    @Autowired
    private EntityComboBox<SysNode> genTag3_IdField;

    @Autowired
    private EntityComboBox<SysNode> genTag4_IdField;

    
    
    @Autowired
    private EntityComboBox<SysNode> finStmtItm1_IdField;

    @Autowired
    private Table<SysNode> finStmtItm1_IdCandidsTable;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc1Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc2Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc3Field;


    @Autowired
    private EntityComboBox<SysNode> finTaxLne1_IdField;

    @Autowired
    private ComboBox<String> finTaxLne1_CodeField;

    @Autowired
    private EntityComboBox<SysNode> finAcct1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finDept1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<SysNode> amtFinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<FinFmla> amtFinFmla1_IdField;


    // FinTxact
    @Autowired
    private EntityComboBox<SysNode> finTxact1_IdField;

    @Autowired
    private Table<SysNode> finTxact1_IdCandidsTable;

    @Autowired
    private TextField<String> finTxact1_Id2TrgtField;

    @Autowired
    private EntityComboBox<SysNodeType> finTxact1_Id_Type1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxset1_EI1_RoleField;

    @Autowired
    private EntityComboBox<SysPat> finTxact1_Id_Desc1SysNode1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finTxact1_Id_Desc1FinTxactItm1_IdField;

    // FinTxset
    @Autowired
    private EntityComboBox<SysNode> finTxact1_Id_FinTxset1_IdField;

    @Autowired
    private Table<SysNode> finTxact1_Id_FinTxset1_IdCandidsTable;

    @Autowired
    private TextField<String> finTxact1_Id_FinTxset1_Id2TrgtField;

    @Autowired
    private EntityComboBox<SysNodeType> finTxact1_Id_FinTxset1_Id_Type1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finTxact1_Id_FinTxset1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finTxact1_Id_FinTxset1_Id_GenChan2_IdField;

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
    private EntityComboBox<SysPat> finTxact1_Id_FinTxset1_Id_Desc1SysNode1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finTxact1_Id_FinTxset1_Id_Desc1FinTxactItm2_IdField;

    @Autowired
    private EntityComboBox<SysNode> finTxact1_Id_FinTxset1_Id_GenTag1_IdField;



    Logger logger = LoggerFactory.getLogger(FinTxactItmBrowse2.class);

    /*
    InitEvent is sent when the screen controller and all its declaratively defined components are created,
    and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
    are not fully initialized, for example, buttons are not linked with actions.
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tenantField.setOptionsList(multitenancyUiSupport.getTenantOptions());

        //filter
        ComboBox<String> propFilterCmpnt_finTxact1_EI1_Role;
        propFilterCmpnt_finTxact1_EI1_Role = (ComboBox<String>) filterConfig1A_finTxact1_EI1_Role.getValueComponent();
        propFilterCmpnt_finTxact1_EI1_Role.setNullOptionVisible(true);
        propFilterCmpnt_finTxact1_EI1_Role.setNullSelectionCaption("<null>");
        propFilterCmpnt_finTxact1_EI1_Role = (ComboBox<String>) filterConfig1B_finTxact1_EI1_Role.getValueComponent();
        propFilterCmpnt_finTxact1_EI1_Role.setNullOptionVisible(true);
        propFilterCmpnt_finTxact1_EI1_Role.setNullSelectionCaption("<null>");

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
        updateFilterConfig1B_IdDtGERdo.setOptionsMap(map5);
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


        finTxactItmTypesDc = dataComponents.createCollectionContainer(SysNodeType.class);
        finTxactItmTypesDl = dataComponents.createCollectionLoader();
        finTxactItmTypesDl.setQuery("select e from ampata_SysNodeType e where e.className = 'FinTxactItm' order by e.id2");
        FetchPlan finTxactItmTypesFp = fetchPlans.builder(SysNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactItmTypesDl.setFetchPlan(finTxactItmTypesFp);
        finTxactItmTypesDl.setContainer(finTxactItmTypesDc);
        finTxactItmTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(finTxactItmTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(finTxactItmTypesDc);
        //filter
        EntityComboBox<SysNodeType> propFilterCmpnt_type1_Id;
        propFilterCmpnt_type1_Id= (EntityComboBox<SysNodeType>) filterConfig1A_type1_Id.getValueComponent();
        propFilterCmpnt_type1_Id.setOptionsContainer(finTxactItmTypesDc);
        propFilterCmpnt_type1_Id = (EntityComboBox<SysNodeType>) filterConfig1B_type1_Id.getValueComponent();
        propFilterCmpnt_type1_Id.setOptionsContainer(finTxactItmTypesDc);


        genChansDc = dataComponents.createCollectionContainer(SysNode.class);
        genChansDl = dataComponents.createCollectionLoader();
        genChansDl.setQuery("select e from ampata_SysNode e where e.className = 'GenChan' order by e.id2");
        FetchPlan genChansFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genChansDl.setFetchPlan(genChansFp);
        genChansDl.setContainer(genChansDc);
        genChansDl.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(genChansDc);
        finTxact1_Id_FinTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        finTxact1_Id_FinTxset1_Id_GenChan2_IdField.setOptionsContainer(genChansDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        tmplt_GenChan1_IdField.setOptionsContainer(genChansDc);
        //filter
        EntityComboBox<SysNode> propFilterCmpnt_genChan1_Id;
        propFilterCmpnt_genChan1_Id = (EntityComboBox<SysNode>) filterConfig1A_finTxact1_Id_finTxset1_Id_genChan1_Id.getValueComponent();
        propFilterCmpnt_genChan1_Id.setOptionsContainer(genChansDc);
        propFilterCmpnt_genChan1_Id = (EntityComboBox<SysNode>) filterConfig1B_finTxact1_Id_finTxset1_Id_genChan1_Id.getValueComponent();
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
        finTxact1_Id_FinTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        tmplt_FinHow1_IdField.setOptionsContainer(finHowsDc);
        //filter
        EntityComboBox<FinHow> propFilterCmpnt_finHow1_Id;
        propFilterCmpnt_finHow1_Id = (EntityComboBox<FinHow>) filterConfig1A_finTxact1_Id_finTxset1_Id_finHow1_Id.getValueComponent();
        propFilterCmpnt_finHow1_Id.setOptionsContainer(finHowsDc);
        propFilterCmpnt_finHow1_Id = (EntityComboBox<FinHow>) filterConfig1B_finTxact1_Id_finTxset1_Id_finHow1_Id.getValueComponent();
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
        finTxact1_Id_FinTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        tmplt_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        //filter
        EntityComboBox<FinWhat> propFilterCmpnt_finWhat1_Id;
        propFilterCmpnt_finWhat1_Id = (EntityComboBox<FinWhat>) filterConfig1A_finTxact1_Id_finTxset1_Id_finWhat1_Id.getValueComponent();
        propFilterCmpnt_finWhat1_Id.setOptionsContainer(finWhatsDc);
        propFilterCmpnt_finWhat1_Id = (EntityComboBox<FinWhat>) filterConfig1B_finTxact1_Id_finTxset1_Id_finWhat1_Id.getValueComponent();
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
        finTxact1_Id_FinTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        tmplt_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        //filter
        EntityComboBox<FinWhy> propFilterCmpnt_finWhy1_Id;
        propFilterCmpnt_finWhy1_Id = (EntityComboBox<FinWhy>) filterConfig1A_finTxact1_Id_finTxset1_Id_finWhy1_Id.getValueComponent();
        propFilterCmpnt_finWhy1_Id.setOptionsContainer(finWhysDc);
        propFilterCmpnt_finWhy1_Id = (EntityComboBox<FinWhy>) filterConfig1B_finTxact1_Id_finTxset1_Id_finWhy1_Id.getValueComponent();
        propFilterCmpnt_finWhy1_Id.setOptionsContainer(finWhysDc);


        sysPatsDc = dataComponents.createCollectionContainer(SysPat.class);
        sysPatsDl = dataComponents.createCollectionLoader();
        sysPatsDl.setQuery("select e from ampata_SysPat e order by e.id2");
        FetchPlan sysPatsFp = fetchPlans.builder(SysPat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        sysPatsDl.setFetchPlan(sysPatsFp);
        sysPatsDl.setContainer(sysPatsDc);
        sysPatsDl.setDataContext(getScreenData().getDataContext());

        desc1SysNode1_IdField.setOptionsContainer(sysPatsDc);
        finTxact1_Id_Desc1SysNode1_IdField.setOptionsContainer(sysPatsDc);
        finTxact1_Id_FinTxset1_Id_Desc1SysNode1_IdField.setOptionsContainer(sysPatsDc);

        
        genDocVersDc = dataComponents.createCollectionContainer(SysNode.class);
        genDocVersDl = dataComponents.createCollectionLoader();
        genDocVersDl.setQuery("select e from ampata_SysNode e where e.className = 'GenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genDocVersDl.setFetchPlan(genDocVersFp);
        genDocVersDl.setContainer(genDocVersDc);
        genDocVersDl.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(genDocVersDc);
        
        //filter
        EntityComboBox<SysNode> propFilterCmpnt_genDocVer1_Id;
        propFilterCmpnt_genDocVer1_Id = (EntityComboBox<SysNode>) filterConfig1A_genDocVer1_Id.getValueComponent();
        propFilterCmpnt_genDocVer1_Id.setOptionsContainer(genDocVersDc);
        propFilterCmpnt_genDocVer1_Id = (EntityComboBox<SysNode>) filterConfig1B_genDocVer1_Id.getValueComponent();
        propFilterCmpnt_genDocVer1_Id.setOptionsContainer(genDocVersDc);

                
        genTagsDc = dataComponents.createCollectionContainer(SysNode.class);
        genTagsDl = dataComponents.createCollectionLoader();
        genTagsDl.setQuery("select e from ampata_SysNode e where e.className = 'GenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genTagsDl.setFetchPlan(genTagsFp);
        genTagsDl.setContainer(genTagsDc);
        genTagsDl.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(genTagsDc);
        genTag2_IdField.setOptionsContainer(genTagsDc);
        genTag3_IdField.setOptionsContainer(genTagsDc);
        genTag4_IdField.setOptionsContainer(genTagsDc);
        finTxact1_Id_FinTxset1_Id_GenTag1_IdField.setOptionsContainer(genTagsDc);

        //filter
        EntityComboBox<SysNode> propFilterCmpnt_genTag1_Id;
        propFilterCmpnt_genTag1_Id = (EntityComboBox<SysNode>) filterConfig1A_genTag1_Id.getValueComponent();
        propFilterCmpnt_genTag1_Id.setOptionsContainer(genTagsDc);
        propFilterCmpnt_genTag1_Id = (EntityComboBox<SysNode>) filterConfig1A_finTxact1_Id_finTxset1_Id_genTag1_Id.getValueComponent();
        propFilterCmpnt_genTag1_Id.setOptionsContainer(genTagsDc);
        propFilterCmpnt_genTag1_Id = (EntityComboBox<SysNode>) filterConfig1B_genTag1_Id.getValueComponent();
        propFilterCmpnt_genTag1_Id.setOptionsContainer(genTagsDc);
        propFilterCmpnt_genTag1_Id = (EntityComboBox<SysNode>) filterConfig1B_finTxact1_Id_finTxset1_Id_genTag1_Id.getValueComponent();
        propFilterCmpnt_genTag1_Id.setOptionsContainer(genTagsDc);


        finTxactsDc = dataComponents.createCollectionContainer(SysNode.class);
        finTxactsDl = dataComponents.createCollectionLoader();
        finTxactsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactsDl.setFetchPlan(finTxactsFp);
        finTxactsDl.setContainer(finTxactsDc);
        finTxactsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_IdField.setOptionsContainer(finTxactsDc);


        finTxactTypesDc = dataComponents.createCollectionContainer(SysNodeType.class);
        finTxactTypesDl = dataComponents.createCollectionLoader();
        finTxactTypesDl.setQuery("select e from ampata_SysNodeType e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactTypesFp = fetchPlans.builder(SysNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactTypesDl.setFetchPlan(finTxactTypesFp);
        finTxactTypesDl.setContainer(finTxactTypesDc);
        finTxactTypesDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_Type1_IdField.setOptionsContainer(finTxactTypesDc);
        //template
        tmplt_FinTxact1_Id_Type1_IdField.setOptionsContainer(finTxactTypesDc);


        finTxact1_IdCandidsDc = dataComponents.createCollectionContainer(SysNode.class);
        finTxact1_IdCandidsDl = dataComponents.createCollectionLoader();
        finTxact1_IdCandidsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinTxact' and e.id is null order by e.id2");
        FetchPlan finTxactCandidsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan("finTxact-fetch-plan-base")
                .build();
        finTxact1_IdCandidsDl.setFetchPlan(finTxactCandidsFp);
        finTxact1_IdCandidsDl.setContainer(finTxact1_IdCandidsDc);
        finTxact1_IdCandidsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_IdCandidsTable.setItems(new ContainerTableItems<>(finTxact1_IdCandidsDc));

        
        finTxsetsDc = dataComponents.createCollectionContainer(SysNode.class);
        finTxsetsDl = dataComponents.createCollectionLoader();
        finTxsetsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetsDl.setFetchPlan(finTxsetsFp);
        finTxsetsDl.setContainer(finTxsetsDc);
        finTxsetsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxset1_IdField.setOptionsContainer(finTxsetsDc);


        finTxsetTypesDc = dataComponents.createCollectionContainer(SysNodeType.class);
        finTxsetTypesDl = dataComponents.createCollectionLoader();
        finTxsetTypesDl.setQuery("select e from ampata_SysNodeType e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetTypesFp = fetchPlans.builder(SysNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetTypesDl.setFetchPlan(finTxsetTypesFp);
        finTxsetTypesDl.setContainer(finTxsetTypesDc);
        finTxsetTypesDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxset1_Id_Type1_IdField.setOptionsContainer(finTxsetTypesDc);
        //template
        tmplt_FinTxact1_Id_FinTxset1_Id_Type1_IdField.setOptionsContainer(finTxsetTypesDc);
        //filter
        EntityComboBox<SysNodeType> propFilterCmpnt_finTxact1_Id_finTxset1_Id_ype1_Id;
        propFilterCmpnt_finTxact1_Id_finTxset1_Id_ype1_Id = (EntityComboBox<SysNodeType>) filterConfig1A_finTxact1_Id_finTxset1_Id_type1_Id.getValueComponent();
        propFilterCmpnt_finTxact1_Id_finTxset1_Id_ype1_Id.setOptionsContainer(finTxsetTypesDc);
        propFilterCmpnt_finTxact1_Id_finTxset1_Id_ype1_Id = (EntityComboBox<SysNodeType>) filterConfig1B_finTxact1_Id_finTxset1_Id_type1_Id.getValueComponent();
        propFilterCmpnt_finTxact1_Id_finTxset1_Id_ype1_Id.setOptionsContainer(finTxsetTypesDc);


        finTxset1_IdCandidsDc = dataComponents.createCollectionContainer(SysNode.class);
        finTxset1_IdCandidsDl = dataComponents.createCollectionLoader();
        finTxset1_IdCandidsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinTxset' and e.id is null order by e.id2");
        FetchPlan finTxsetCandidsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan("finTxset-fetch-plan-base")
                .build();
        finTxset1_IdCandidsDl.setFetchPlan(finTxsetCandidsFp);
        finTxset1_IdCandidsDl.setContainer(finTxset1_IdCandidsDc);
        finTxset1_IdCandidsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxset1_IdCandidsTable.setItems(new ContainerTableItems<>(finTxset1_IdCandidsDc));

        
        finStmtsDc = dataComponents.createCollectionContainer(SysNode.class);
        finStmtsDl = dataComponents.createCollectionLoader();
        finStmtsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtsDl.setFetchPlan(finStmtsFp);
        finStmtsDl.setContainer(finStmtsDc);
        finStmtsDl.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<SysNode> propFilterCmpnt_finStmt1_Id;
        propFilterCmpnt_finStmt1_Id = (EntityComboBox<SysNode>) filterConfig1A_finStmtItm1_Id_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_finStmt1_Id.setOptionsContainer(finStmtsDc);
        propFilterCmpnt_finStmt1_Id = (EntityComboBox<SysNode>) filterConfig1B_finStmtItm1_Id_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_finStmt1_Id.setOptionsContainer(finStmtsDc);


        finStmtItmsDc = dataComponents.createCollectionContainer(SysNode.class);
        finStmtItmsDl = dataComponents.createCollectionLoader();
        finStmtItmsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinStmtItm' order by e.id2");
        FetchPlan finStmtItmsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan("finStmtItm-fetch-plan-base")
                .build();
        finStmtItmsDl.setFetchPlan(finStmtItmsFp);
        finStmtItmsDl.setContainer(finStmtItmsDc);
        finStmtItmsDl.setDataContext(getScreenData().getDataContext());

        finStmtItm1_IdField.setOptionsContainer(finStmtItmsDc);
        //template
        tmplt_FinStmtItm1_IdField.setOptionsContainer(finStmtItmsDc);


        finStmtItm1_IdCandidsDc = dataComponents.createCollectionContainer(SysNode.class);
        finStmtItm1_IdCandidsDl = dataComponents.createCollectionLoader();
        finStmtItm1_IdCandidsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinStmtItm' and e.id is null order by e.id2");
        FetchPlan finStmtItmCandidsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan("finStmtItm-fetch-plan-base")
                .build();
        finStmtItm1_IdCandidsDl.setFetchPlan(finStmtItmCandidsFp);
        finStmtItm1_IdCandidsDl.setContainer(finStmtItm1_IdCandidsDc);
        finStmtItm1_IdCandidsDl.setDataContext(getScreenData().getDataContext());

        finStmtItm1_IdCandidsTable.setItems(new ContainerTableItems<>(finStmtItm1_IdCandidsDc));


        finTaxLnesDc = dataComponents.createCollectionContainer(SysNode.class);
        finTaxLnesDl = dataComponents.createCollectionLoader();
        finTaxLnesDl.setQuery("select e from ampata_SysNode e where e.className = 'GenDocFrg' order by e.id2");
        FetchPlan finTaxLnesFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTaxLnesDl.setFetchPlan(finTaxLnesFp);
        finTaxLnesDl.setContainer(finTaxLnesDc);
        finTaxLnesDl.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(finTaxLnesDc);
        //template
        tmplt_FinTaxLne1_IdField.setOptionsContainer(finTaxLnesDc);


        finAcctsDc = dataComponents.createCollectionContainer(SysNode.class);
        finAcctsDl = dataComponents.createCollectionLoader();
        finAcctsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctsDl.setFetchPlan(finAcctsFp);
        finAcctsDl.setContainer(finAcctsDc);
        finAcctsDl.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(finAcctsDc);
        //template
        tmplt_finAcct1_IdField.setOptionsContainer(finAcctsDc);
        //filter
        EntityComboBox<SysNode> propFilterCmpnt_finAcct1_Id;
        propFilterCmpnt_finAcct1_Id = (EntityComboBox<SysNode>) filterConfig1A_finAcct1_Id.getValueComponent();
        propFilterCmpnt_finAcct1_Id.setOptionsContainer(finAcctsDc);
        propFilterCmpnt_finAcct1_Id = (EntityComboBox<SysNode>) filterConfig1B_finAcct1_Id.getValueComponent();
        propFilterCmpnt_finAcct1_Id.setOptionsContainer(finAcctsDc);


        finDeptsDc = dataComponents.createCollectionContainer(SysNode.class);
        finDeptsDl = dataComponents.createCollectionLoader();
        finDeptsDl.setQuery("select e from ampata_SysNode e where e.className = 'FinDept' order by e.id2");
        FetchPlan finDeptsFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finDeptsDl.setFetchPlan(finDeptsFp);
        finDeptsDl.setContainer(finDeptsDc);
        finDeptsDl.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(finDeptsDc);
        //template
        tmplt_finDept1_IdField.setOptionsContainer(finDeptsDc);
        //filter
        EntityComboBox<SysNode> propFilterCmpnt_finDept1_Id;
        propFilterCmpnt_finDept1_Id = (EntityComboBox<SysNode>) filterConfig1A_finDept1_Id.getValueComponent();
        propFilterCmpnt_finDept1_Id.setOptionsContainer(finDeptsDc);
        propFilterCmpnt_finDept1_Id = (EntityComboBox<SysNode>) filterConfig1B_finDept1_Id.getValueComponent();
        propFilterCmpnt_finDept1_Id.setOptionsContainer(finDeptsDc);


        finCurcysDc = dataComponents.createCollectionContainer(SysNode.class);
        finCurcysDl = dataComponents.createCollectionLoader();
        finCurcysDl.setQuery("select e from ampata_SysNode e where e.className = 'FinCurcy' order by e.id2");
        FetchPlan finCurcysFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finCurcysDl.setFetchPlan(finCurcysFp);
        finCurcysDl.setContainer(finCurcysDc);
        finCurcysDl.setDataContext(getScreenData().getDataContext());

        finCurcy1_IdField.setOptionsContainer(finCurcysDc);
        //template
        tmplt_finCurcy1_IdField.setOptionsContainer(finCurcysDc);
        //filter
        EntityComboBox<SysNode> propFilterCmpnt_finCurcy1_Id;
        propFilterCmpnt_finCurcy1_Id = (EntityComboBox<SysNode>) filterConfig1A_finCurcy1_Id.getValueComponent();
        propFilterCmpnt_finCurcy1_Id.setOptionsContainer(finCurcysDc);
        propFilterCmpnt_finCurcy1_Id = (EntityComboBox<SysNode>) filterConfig1B_finCurcy1_Id.getValueComponent();
        propFilterCmpnt_finCurcy1_Id.setOptionsContainer(finCurcysDc);


        finTxactItm1sDc = dataComponents.createCollectionContainer(SysNode.class);
        finTxactItm1sDl = dataComponents.createCollectionLoader();
        finTxactItm1sDl.setQuery("select e from ampata_SysNode e where e.className = 'FinTxactItm' order by e.id2");
        FetchPlan finTxactItm1sFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactItm1sDl.setFetchPlan(finTxactItm1sFp);
        finTxactItm1sDl.setContainer(finTxactItm1sDc);
        finTxactItm1sDl.setDataContext(getScreenData().getDataContext());

        desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);
        finTxact1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);
        finTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);
        finTxact1_Id_FinTxset1_Id_Desc1FinTxactItm2_IdField.setOptionsContainer(finTxactItm1sDc);
        amtFinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);


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
    public void onInitEntity(InitEntityEvent<SysNode> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = event.getEntity();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItm.setClassName("FinTxactItm");
        logger.debug(logPrfx + " --- className: FinTxactItm");

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

        String currentTenantId = tenantProvider.getCurrentUserTenantId();
        if (!currentTenantId.equals(TenantProvider.NO_TENANT)
                && Strings.isNullOrEmpty(tenantField.getValue())) {
            //tenantField.setEditable(false);
            tenantField.setValue(currentTenantId);
        }

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

    @Subscribe(id = "finTxactItmsDc", target = Target.DATA_CONTAINER)
    public void onFinTxactItmsDcItemChange(InstanceContainer.ItemChangeEvent<SysNode> event) {
        String logPrfx = "onFinTxactItmsDcItemChange";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = event.getItem();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItm.setClassName("FinTxactItm");
        logger.debug(logPrfx + " --- className: FinTxactItm");

/*
        updateFinTxact1_IdCandidsTable(thisFinTxactItm);
        updateFinTxact1_Id_FinTxset1_IdCandidsTable(thisFinTxactItm);
        updateFinStmtItm1_IdCandidsTable(thisFinTxactItm);
*/

        logger.trace(logPrfx + " <-- ");

    }


    @Install(to = "table.[idDt.date1]", subject = "formatter")
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

        finTxactItmTypesDl.load();
        logger.debug(logPrfx + " --- called finTxactItmTypesDl.load() ");

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

        sysPatsDl.load();
        logger.debug(logPrfx + " --- called sysPat.load() ");


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

        logger.debug(logPrfx + " --- executing Db-Proc.Sys_Node_Pr_Upd()");
        repo.execSysNodePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Sys_Node_Pr_Upd()");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txact_Itm_Pr_Upd()");
        repo.execFinTxactItmPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txact_Itm_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finTxactItmsDl.load()");
        finTxactItmsDl.load();
        logger.debug(logPrfx + " --- finished finTxactItmsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("purgeColBtn")
    public void onPurgeColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onPurgeColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txact_Itm_Pr_Purge()");
        repo.execFinTxactItmPrPurgeNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txact_Itm_Pr_Purge()");

        logger.debug(logPrfx + " --- loading finTxactItmsDl.load()");
        finTxactItmsDl.load();
        logger.debug(logPrfx + " --- finished finTxactItmsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deleteColOrphansBtn")
    public void onDeleteColOrphansBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColOrphansBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txact_Itm_Pr_Del_Orph()");
        repo.execFinTxactItmPrDelOrphNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txact_Itm_Pr_Del_Orph()");

        logger.debug(logPrfx + " --- loading finTxactItmsDl.load()");
        finTxactItmsDl.load();
        logger.debug(logPrfx + " --- finished finTxactItmsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("splitBtn")
    public void onSplitBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSplitBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNode> sels = new ArrayList<>();

        thisFinTxactItms.forEach(orig -> {

            SysNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (Objects.equals(orig.getId2(), copy.getId2())) {
                copy.setIdZ(copy.getIdZ() == null ? 1 : copy.getIdZ() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            // on split clear the account
            copy.setFinAcct1_Id(null);
            // on split clear the statement item
            copy.setFinStmtItm1_Id(null);


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

            if (tmplt_finCurcy1_IdFieldChk.isChecked()) {
                copy.setFinCurcy1_Id(tmplt_finCurcy1_IdField.getValue());
            }

            Integer finTxactOption = Optional.ofNullable(updateColItemCalcValsTxactOption.getValue()).orElse(0);
            Integer finTxsetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
            Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);

            updateCalcVals(copy, finTxactOption, finTxsetOption, finStmtItmOption);

            SysNode savedCopy = dataManager.save(copy);
            finTxactItmsDc.getMutableItems().add(savedCopy);
            logger.debug("Split FinTxactItm " + copy.getId2() + " "
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

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNode> sels = new ArrayList<>();

        thisFinTxactItms.forEach(orig -> {
            SysNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_FinTxact1_EI1_RoleFieldChk.isChecked()) {
                copy.setFinTxact1_EI1_Role(tmplt_FinTxact1_EI1_RoleField.getValue());
            }

            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                copy.getBeg1().setTs1(tmplt_Beg1Ts1Field.getValue());
                updateIdDt(copy);
            }
            if (tmplt_Beg2Ts1FieldChk.isChecked()) {
                copy.getBeg2().setTs1(tmplt_Beg2Ts1Field.getValue());
                updateIdDt(copy);
            }

            LocalDate idDt1 = copy.getIdDt() != null ? copy.getIdDt().getDate1() : null;

            Integer idX = copy.getIdX();
            if (tmplt_IdXFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdXFieldRdo.getValue() == 1) {
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

            Integer idZ = copy.getIdZ();
            if (tmplt_IdZFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdZFieldRdo.getValue() == 1) {
                    idZ = tmplt_IdZField.getValue();
                    copy.setIdZ(idZ);
                }
                // Max
                else if (tmplt_IdZFieldRdo.getValue() == 2
                        && idDt1 != null) {

                    idZ = getIdZMax(idDt1, idX, idY);
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

            if (tmplt_finCurcy1_IdFieldChk.isChecked()) {
                copy.setFinCurcy1_Id(tmplt_finCurcy1_IdField.getValue());
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

            SysNode savedCopy = dataManager.save(copy);
            finTxactItmsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinTxactItm " + copy.getId2() + " "
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

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxactItms.forEach(thisFinTxactItm -> {
            SysNode thisTrackedFinTxactItm = dataContext.merge(thisFinTxactItm);
            thisFinTxactItm = thisTrackedFinTxactItm;
            if (thisFinTxactItm != null) {

                Boolean thisFinTxactItmIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                if (tmplt_FinTxact1_EI1_RoleFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinTxact1_EI1_Role(tmplt_FinTxact1_EI1_RoleField.getValue());
                }

                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.getBeg1().setTs1(tmplt_Beg1Ts1Field.getValue());
                    updateIdDt(thisFinTxactItm);
                }
                if (tmplt_Beg2Ts1FieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.getBeg2().setTs1(tmplt_Beg2Ts1Field.getValue());
                    updateIdDt(thisFinTxactItm);
                }
                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;

                Integer idX = thisFinTxactItm.getIdX();
                if (tmplt_IdXFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1) {
                        thisFinTxactItmIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinTxactItm.setIdX(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactItmIsChanged = true;
                        idX = getIdXMax(idDt1);
                        if (idX == null) return;
                        thisFinTxactItm.setIdX(idX);
                    }
                }

                Integer idY = thisFinTxactItm.getIdY();
                if (tmplt_IdYFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdYFieldRdo.getValue() == 1) {
                        thisFinTxactItmIsChanged = true;
                        idY = tmplt_IdYField.getValue();
                        thisFinTxactItm.setIdY(idY);
                    }
                    // Max
                    else if (tmplt_IdYFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactItmIsChanged = true;
                        idY = getIdYMax(idDt1, idX);
                        if (idY == null) return;
                        thisFinTxactItm.setIdY(idY);
                    }
                }

                Integer idZ = thisFinTxactItm.getIdZ();
                if (tmplt_IdZFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdZFieldRdo.getValue() == 1) {
                        thisFinTxactItmIsChanged = true;
                        idZ = tmplt_IdZField.getValue();
                        thisFinTxactItm.setIdZ(idZ);
                    }
                    // Max
                    else if (tmplt_IdZFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactItmIsChanged = true;
                        idZ = getIdZMax(idDt1, idX, idY);
                        if (idZ == null) return;
                        thisFinTxactItm.setIdZ(idZ);
                    }
                }

                if (tmplt_GenChan1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setGenChan1_Id(tmplt_GenChan1_IdField.getValue());
                }

                if (tmplt_FinHow1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinHow1_Id(tmplt_FinHow1_IdField.getValue());
                }

                if (tmplt_WhatText1FieldChk.isChecked()
                ) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setWhatText1(tmplt_WhatText1Field.getValue());
                }

                if (tmplt_FinWhat1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinWhat1_Id(tmplt_FinWhat1_IdField.getValue());
                }

                if (tmplt_WhyText1FieldChk.isChecked()
                ) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setWhyText1(tmplt_WhyText1Field.getValue());
                }

                if (tmplt_FinWhy1_IdFieldChk.isChecked()
                ) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinWhy1_Id(tmplt_FinWhy1_IdField.getValue());
                }


                if (tmplt_FinTaxLne1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
                }

                if (tmplt_FinTaxLne1_CodeFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinTaxLne1_Code(tmplt_FinTaxLne1_CodeField.getValue());
                }


                if (tmplt_finAcct1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinAcct1_Id(tmplt_finAcct1_IdField.getValue());
                }

                if (tmplt_finDept1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinDept1_Id(tmplt_finDept1_IdField.getValue());
                }

                if (tmplt_amtDebtFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setAmtDebt(tmplt_amtDebtField.getValue());
                }

                if (tmplt_amtCredFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setAmtCred(tmplt_amtCredField.getValue());
                }
                
                if (tmplt_finCurcy1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinCurcy1_Id(tmplt_finCurcy1_IdField.getValue());
                }

                if (tmplt_amtFinFmla1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setAmtFinFmla1_Id(tmplt_amtFinFmla1_IdField.getValue());
                }

                thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                thisFinTxactItmIsChanged = updateId2(thisFinTxactItm) || thisFinTxactItmIsChanged;
                thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                if (thisFinTxactItmIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                    //dataManager.save(thisFinTxactItm);
                }

                SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
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


                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }

                SysNode thisFinTxset = thisFinTxactItm.getFinTxact1_Id() == null ? null : thisFinTxactItm.getFinTxact1_Id().getFinTxset1_Id();
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

                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxactItm,thisFinTxactItm).");
                    //metadataTools.copy(thisFinTxactItm, thisFinTxactItm);

                    //logger.debug(logPrfx + " --- executing dataContext.commit().");
                    //dataContext.commit();

                    if (thisFinTxsetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxset).");
                        //dataManager.save(thisFinTxset);
                    }

                }

            }
        });
        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }
    private void updateFinTxactItmHelper() {
        String logPrfx = "updateFinTxactItmHelper";
        logger.trace(logPrfx + " --> ");
    
        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
    
            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactItmsDl.load();

            List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
    
            //Loop throught the items again to update the id2Dup attribute
            thisFinTxactItms.forEach(thisFinTxactItm -> {
                //SysNode thisTrackedFinTxactItm = dataContext.merge(thisFinTxactItm);
                if (thisFinTxactItm != null) {
                    thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                    Boolean thisFinTxactItmIsChanged = false;
    
                    thisFinTxactItmIsChanged = updateId2Dup(thisFinTxactItm) || thisFinTxactItmIsChanged;
    
                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            });
    
            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();
    
                logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
                finTxactItmsDl.load();
    
                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinTxactItms);
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
        }
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
        switch (idDtGEOption) {
            case 0 -> // Clear
                    filterConfig1B_idDtGE.clear();
            case 1 -> {  // Set to match current row
                List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
                if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                SysNode thisFinTxactItm = thisFinTxactItms.get(0);
                if (thisFinTxactItm != null) {
                    if (thisFinTxactItm.getIdDt() != null && thisFinTxactItm.getIdDt().getDate1() != null) {
                        filterConfig1B_idDtGE.setValue(thisFinTxactItm.getIdDt().getDate1());
                        filterConfig1B_idDtGE.apply();
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
                List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
                if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                SysNode thisFinTxactItm = thisFinTxactItms.get(0);
                if (thisFinTxactItm != null) {
                    if (thisFinTxactItm.getIdX() != null) {
                        filterConfig1B_idX.setValue(thisFinTxactItm.getIdX());
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
                List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
                if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                SysNode thisFinTxactItm = thisFinTxactItms.get(0);
                if (thisFinTxactItm != null) {
                    if (thisFinTxactItm.getIdY() != null) {
                        filterConfig1B_idY.setValue(thisFinTxactItm.getIdY());
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

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactItm.getIdX();
                    Integer idX = 0;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdX(" + (idX) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactItm.getIdX();
                    Integer idX = thisFinTxactItm.getIdX() == null || thisFinTxactItm.getIdX() == 0 || thisFinTxactItm.getIdX() == 1 ? 0 : thisFinTxactItm.getIdX() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdX(" + (idX) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdXBtn")
    public void onIncIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactItm.getIdX();
                    Integer idX = (thisFinTxactItm.getIdX() == null ? 0 : thisFinTxactItm.getIdX()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdX(" + (idX) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("maxIdXBtn")
    public void onMaxIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from ampata_SysNode e"
                            + " where e.className = 'FinTxactItm'"
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

                    Integer idX_ = thisFinTxactItm.getIdX();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdX(" + (idX) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("zeroIdYBtn")
    public void onZeroIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxactItm.getIdY();
                    Integer idY = 0;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdY(" + (idY) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdYBtn")
    public void onDecIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxactItm.getIdY();
                    Integer idY = thisFinTxactItm.getIdY() == null || thisFinTxactItm.getIdY() == 0 || thisFinTxactItm.getIdY() == 1 ? 0 : thisFinTxactItm.getIdY() - 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdY(" + (idY) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdYBtn")
    public void onIncIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxactItm.getIdY();
                    Integer idY = (thisFinTxactItm.getIdY() == null ? 0 : thisFinTxactItm.getIdY()) + 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdY(" + (idY) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("maxIdYBtn")
    public void onMaxIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY) from ampata_SysNode e"
                            + " where e.className = 'FinTxactItm'"
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

                    Integer idY_ = thisFinTxactItm.getIdY();
                    Integer idY = idYMax == null ? 0 : idYMax;

                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setIdY(idY);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdY(" + (idY) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("zeroIdZBtn")
    public void onZeroIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idZ_ = thisFinTxactItm.getIdZ();
                    Integer idZ = 0;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdY(" + (idZ) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdZBtn")
    public void onDecIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idZ_ = thisFinTxactItm.getIdZ();
                    Integer idZ = thisFinTxactItm.getIdZ() == null || thisFinTxactItm.getIdZ() == 0 || thisFinTxactItm.getIdZ() == 1 ? 0 : thisFinTxactItm.getIdZ() - 1;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdZ(" + (idZ) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("incIdZBtn")
    public void onIncIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idZ_ = thisFinTxactItm.getIdZ();
                    Integer idZ = (thisFinTxactItm.getIdZ() == null ? 0 : thisFinTxactItm.getIdZ()) + 1;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdZ(" + (idZ) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("maxIdZBtn")
    public void onMaxIdZBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdZBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                LocalDate idDt1 = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idZMax = 0;
                    String idZMaxQry = "select max(e.idZ) from ampata_SysNode e"
                            + " where e.className = 'FinTxactItm'"
                            + " and e.idDt.date1 = :idDt1";
                    try {
                        idZMax = dataManager.loadValue(idZMaxQry, Integer.class)
                                .store("main")
                                .parameter("idDt1", idDt1)
                                .one();
                        // max returns null if no rows or if all rows have a null value
                    } catch (IllegalStateException e) {
                        logger.debug(logPrfx + " --- idZMaxQry error: " + e.getMessage());
                        idZMax = null;
                    }
                    logger.debug(logPrfx + " --- idZMaxQry result: " + idZMax + "");

                    Integer idZ_ = thisFinTxactItm.getIdZ();
                    Integer idZ = idZMax == null ? 0 : idZMax;

                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setIdZ(idZ);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setIdZ(" + (idZ) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                    String id2_ = thisFinTxactItm.getId2();
                    String id2 = thisFinTxactItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinTxactItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                        thisFinTxactItmIsChanged = true;
                    }

                    thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                    if (thisFinTxactItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                        //dataManager.save(thisFinTxactItm);
                    }
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("updateColItemIdPartsBtn")
    public void onUpdateColItemIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                boolean isChanged = false;

                isChanged = updateIdParts(thisFinTxactItm);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactItmsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxactItms);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                boolean isChanged = false;

                Integer finTxactOption = Optional.ofNullable(updateColItemCalcValsTxactOption.getValue()).orElse(0);
                Integer finTxsetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
                Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);
                isChanged = updateCalcVals(thisFinTxactItm, finTxactOption, finTxsetOption, finStmtItmOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactItmsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinTxactItms);
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

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                boolean isChanged = false;

                updateId2(thisFinTxactItm);
            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactItmsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinTxactItms);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
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

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer finTxactOption = 3; // update finTxact to match this finTxactItm

        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                thisFinTxactItmIsChanged = updateFinTxact1_Id(thisFinTxactItm, finTxactOption) || thisFinTxactItmIsChanged;
                String id2_ = thisFinTxactItm.getId2();
                String id2 = thisFinTxactItm.getId2Calc();
                if (!Objects.equals(id2_, id2)){
                    thisFinTxactItm.setId2(id2);
                    logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                    thisFinTxactItmIsChanged = true;
                }

                thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                if (thisFinTxactItmIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                    //dataManager.save(thisFinTxactItm);
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("linkTxactBtn")
    public void onLinkTxactBtnClick(Button.ClickEvent event) {
        String logPrfx = "onLinkTxactBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
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

        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                thisFinTxactItm = dataContext.merge(thisFinTxactItm);

                Boolean thisFinTxactItmIsChanged = false;

                thisFinTxactItmIsChanged = updateFinTxact1_Id(thisFinTxactItm, finTxactOption) || thisFinTxactItmIsChanged;
                String id2_ = thisFinTxactItm.getId2();
                String id2 = thisFinTxactItm.getId2Calc();
                if (!Objects.equals(id2_, id2)){
                    thisFinTxactItm.setId2(id2);
                    logger.debug(logPrfx + " --- thisFinTxactItm.setId2(" + (id2) + ")");
                    thisFinTxactItmIsChanged = true;
                }

                thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                if (thisFinTxactItmIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                    //dataManager.save(thisFinTxactItm);
                }
            }
        });

        updateFinTxactItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("linkAsRef1Btn")
    public void onLinkAsRef1BtnClick(Button.ClickEvent event) {
        String logPrfx = "onLinkAsRef1BtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinTxactItms = table.getSelected().stream().toList();
        if (thisFinTxactItms.size() < 2) {
            logger.debug(logPrfx + " --- thisFinTxactItms contains less that 2 items.");
            notifications.create().withCaption("Less than 2 records selected. Please select 2 records.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        SysNode selFinTxactItm0Tracked = dataContext.merge(thisFinTxactItms.get(0));
        SysNode selFinTxactItm1Tracked = dataContext.merge(thisFinTxactItms.get(1));

        if (selFinTxactItm0Tracked != null && selFinTxactItm1Tracked != null) {
            selFinTxactItm0Tracked.setAmtFinTxactItm1_Id(selFinTxactItm1Tracked);
            logger.debug("Linked FinTxactItm "
                    + selFinTxactItm0Tracked.getId2() + " [" + selFinTxactItm0Tracked.getId() + "]"
                    + " Ref1 linked to "
                    + selFinTxactItm1Tracked.getId2() + " [" + selFinTxactItm1Tracked.getId() + "]"
            );
        }
        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactItmsDl.load().");
            finTxactItmsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxactItms);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateIdParts(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxsetOption = Optional.ofNullable(updateInstItemCalcValsTxsetOption.getValue()).orElse(0);
        Integer finTxactOption = Optional.ofNullable(updateInstItemCalcValsTxactOption.getValue()).orElse(0);
        Integer finStmtItmOption = Optional.ofNullable(updateInstItemCalcValsStmtItmOption.getValue()).orElse(0);

        updateCalcVals(thisFinTxactItm, finTxactOption, finTxsetOption, finStmtItmOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinTxactItm);
            updateId2Dup(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinTxactItm);
        updateId2Cmp(thisFinTxactItm);
        updateId2Dup(thisFinTxactItm);

        logger.debug(logPrfx + " --- id2: " + thisFinTxactItm.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinTxactItm);
        updateId2Cmp(thisFinTxactItm);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinTxactItm.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItmTypesDl.load();
        logger.debug(logPrfx + " --- called finTxactItmTypesDl.load() ");

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
    public void onUpdateGenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinHow1_IdFieldListBtn")
    public void onUpdateFinHow1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinHow1_IdFieldListBtnClick";
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
    public void onUpdateFinWhat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhat1_IdFieldListBtnClick";
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
    public void onUpdateFinWhy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDescPat1_IdFieldListBtn")
    public void onUpdateDescPat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDescPat1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        sysPatsDl.load();
        logger.debug(logPrfx + " --- called sysPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateDesc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateIdDt(thisFinTxactItm);
            updateId2Calc(thisFinTxactItm);
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateBeg1Ts1FieldBtn")
    public void onUpdateBeg1Ts1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg1Ts1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg1(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("beg2Ts1Field")
    public void onBeg2Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg2Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateIdDt(thisFinTxactItm);
            updateId2Calc(thisFinTxactItm);
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateBeg2Ts1FieldBtn")
    public void onUpdateBeg2Ts1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg2Ts1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg2(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxactItm);
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
            updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdX(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idYField")
    public void onIdYFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdYFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxactItm);
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateIdYFieldBtn")
    public void onUpdateIdYFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdYFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdY(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idZField")
    public void onIdZFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdZFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateIdZFieldBtn")
    public void onUpdateIdZFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdZFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdZ(thisFinTxactItm);

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



    @Subscribe("updateFinTxact1_Id_Desc1FieldBtn")
    public void onUpdateFinTxact1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_Desc1(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxact1_IdField")
    public void onFinTxact1_IdFieldValueChange(HasValue.ValueChangeEvent<SysNode> event) {
        String logPrfx = "onFinTxact1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            if (thisFinTxactItm.getFinTxact1_Id() != null) {
                updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxactItm);
            }
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateFinTxact1_IdFieldBtn")
    public void onUpdateFinTxact1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxactOption = updateInstItemCalcValsTxactOption.getValue();
        if (finTxactOption == null){
            finTxactOption = 0;}
        updateFinTxact1_Id(thisFinTxactItm, finTxactOption);

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
    
    @Subscribe("updateFinTxact1_IdCandidsTableBtn")
    public void onUpdateFinTxact1_IdCandidsTableBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_IdCandidsTableBtnClick";
        logger.trace(logPrfx + " --> ");


        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_IdCandidsTable(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id2TrgtBtn")
    public void onUpdateFinTxact1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id2Trgt(thisFinTxactItm);

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


    @Subscribe("updateFinTxact1_Id_DescPat1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_DescPat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_DescPat1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        sysPatsDl.load();
        logger.debug(logPrfx + " --- called sysPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_IdCntCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_AmtDebtSumCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_AmtDebtSumCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_AmtDebtSumCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxactItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_AmtCredSumCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_AmtCredSumCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }
    
    
    @Subscribe("updateFintxact1_Id_FinTxactItms1_AmtEqCalcBoxBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_AmtEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_AmtEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_AmtEqCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalcBoxBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_FinCurcyEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_FinCurcyEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Desc1FieldBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_FinTxset1_Id_Desc1(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_IdFieldBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxsetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxsetOption == null){
            finTxsetOption = 0;}
        updateFinTxact1_FinTxset1_Id(thisFinTxactItm, finTxsetOption);
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


    @Subscribe("updateFinTxact1_Id_FinTxset1_IdCandidsTableBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_IdCandidsTableBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_IdCandidsTableBtnClick";
        logger.trace(logPrfx + " --> ");


        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_FinTxset1_IdCandidsTable(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    
    @Subscribe("updateFinTxact1_Id_FinTxset1_Id2TrgtBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxactItm);
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

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_GenChan2_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_GenChan2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_GenChan2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_How1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_How1_IdFieldListBtnClick";
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
    public void onUpdateFinTxact1_Id_FinTxset1_Id_What1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_What1_IdFieldListBtnClick";
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
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Why1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_Why1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_DescPat1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_DescPat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_DescPat1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        sysPatsDl.load();
        logger.debug(logPrfx + " --- called sysPatDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm2_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_GenTag1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_GenTag1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_GenTag1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinStmtItm1_IdFieldBtn")
    public void onUpdateFinStmtItm1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");


        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        // todo
        //updateFinStmtItm1_Id(thisFinTxactItm);
        List<SysNode> finStmtItms = finStmtItm1_IdCandidsTable.getSelected().stream().toList();
        if (finStmtItms.size() > 0){
            SysNode finStmtItm = finStmtItms.get(0);
            finStmtItm = dataContext.merge(finStmtItm);
            thisFinTxactItm.setFinStmtItm1_Id(finStmtItm);
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


    @Subscribe("updateFinStmtItm1_IdCandidsTableBtn")
    public void onUpdateFinStmtItm1_IdCandidsTableBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_IdCandidsTableBtnClick";
        logger.trace(logPrfx + " --> ");


        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinStmtItm1_IdCandidsTable(thisFinTxactItm);

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
    public void onUpdateFinTaxLne1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTaxLne1_Id(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTaxLne1_IdFieldListBtn")
    public void onUpdateFinTaxLne1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtnClick";
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
    public void onUpdateFinDept1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinDept1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finDeptsDl.load();
        logger.debug(logPrfx + " --- called finDeptsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtFinTxactItm1_IdFieldListBtn")
    public void onUpdateAmtFinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtFinTxactItm1_EI1_RateBtn")
    public void onUpdateAmtFinTxactItm1_EI1_RateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxactItm1_EI1_RateBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtFinTxactItm1_EI1_Rate(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtCalcBtn")
    public void onUpdateAmtCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtNet(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("amtCredField")
    public void onAmtCredFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtCredFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtNet(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtNetBtn")
    public void onUpdateAmtNetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtNetBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtNet(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtFinFmla1_IdFieldListBtn")
    public void onUpdateAmtFinFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finFmlasDl.load();
        logger.debug(logPrfx + " --- called finFmlasDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull SysNode thisFinTxactItm, Integer finTxactOption, Integer finTxsetOption, Integer finStmtItmOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItmCalcVals(thisFinTxactItm, finTxactOption) || isChanged;
        isChanged = updateFinTxactCalcVals(thisFinTxactItm, finTxsetOption) || isChanged;
        isChanged = updateFinStmtItmCalcVals(thisFinTxactItm, finStmtItmOption) || isChanged;
        isChanged = updateFinTxsetCalcVals(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactItmCalcVals(@NotNull SysNode thisFinTxactItm, Integer finTxactOption) {
        String logPrfx = "updateFinTxactItmCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxactItm Object
        isChanged = updateIdDt(thisFinTxactItm) || isChanged;
        isChanged = updateId2Calc(thisFinTxactItm) || isChanged;
        isChanged = updateId2Cmp(thisFinTxactItm) || isChanged;
        isChanged = updateId2Dup(thisFinTxactItm) || isChanged;
        isChanged = updateDesc1(thisFinTxactItm) || isChanged;
//        isChanged = updateFinTaxLne1_Id(thisFinTxactItm) || isChanged;
        isChanged = updateAmtNet(thisFinTxactItm) || isChanged;
        isChanged = updateAmtFinTxactItm1_EI1_Rate(thisFinTxactItm) || isChanged;
        isChanged = updateAmtCalc(thisFinTxactItm) || isChanged;

        isChanged = updateFinTxact1_Id2Trgt(thisFinTxactItm) || isChanged;
        isChanged = updateFinTxact1_Id(thisFinTxactItm, finTxactOption) || isChanged;

        isChanged = updateFinStmtItm1_Id2Trgt(thisFinTxactItm) || isChanged;
        isChanged = updateFinStmtItm1_Id(thisFinTxactItm, finTxactOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactCalcVals(@NotNull SysNode thisFinTxactItm, Integer finTxsetOption) {
        String logPrfx = "updateFinTxactCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxact Object
        isChanged = updateFinTxact1_Id_Desc1(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_IdCntCalc(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_AmtDebtSumCalc(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_AmtEqCalc(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalc(thisFinTxactItm)  || isChanged;
        

        isChanged = updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxactItm) || isChanged;
        isChanged = updateFinTxact1_FinTxset1_Id(thisFinTxactItm, finTxsetOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxsetCalcVals(@NotNull SysNode thisFinTxactItm) {
        String logPrfx = "updateFinTxsetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxset Object
        isChanged = updateFinTxact1_Id_FinTxset1_Id_Desc1(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinStmtItmCalcVals(@NotNull SysNode thisFinTxactItm, Integer finStmtItmOption) {
        String logPrfx = "updateFinStmtItmCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        
        // Stored in FinStmtItm Object
        // todo
//        isChanged = updateFinStmtItm1_Id_Desc1(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdParts(@NotNull SysNode thisFinTxactItm) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateBeg1(thisFinTxactItm) || isChanged;
        isChanged = updateBeg2(thisFinTxactItm) || isChanged;
        isChanged = updateIdDt(thisFinTxactItm)  || isChanged;
        isChanged = updateIdX(thisFinTxactItm)  || isChanged;
        isChanged = updateIdY(thisFinTxactItm)  || isChanged;
        isChanged = updateIdZ(thisFinTxactItm)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinTxactItm.getId2();
        String id2 = thisFinTxactItm.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinTxactItm.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinTxactItm.getId2Calc();
        String id2Calc = thisFinTxactItm.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinTxactItm.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinTxactItm.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinTxactItm.getId2(),thisFinTxactItm.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinTxactItm.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxactItm.getId2Dup();
        if (thisFinTxactItm.getId2() != null) {
            String id2Qry = "select count(e) from ampata_SysNode e where e.className = 'FinTxactItm' and e.id2 = :id2 and e.id <> :id";
            int id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinTxactItm.getId())
                        .parameter("id2", thisFinTxactItm.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinTxactItm.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinTxactItm.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String desc1_ = thisFinTxactItm.getDesc1();
        String thisAmt = "";
        if (thisFinTxactItm.getAmtDebt() != null) {
            thisAmt = thisAmt + " Debt " + thisFinTxactItm.getAmtDebt();
        }
        if (thisFinTxactItm.getAmtCred() != null) {
            thisAmt = thisAmt + " Cred " + thisFinTxactItm.getAmtCred();
        }
        if (thisFinTxactItm.getFinCurcy1_Id() != null) {
            thisAmt = thisAmt + " " + Objects.toString(thisFinTxactItm.getFinCurcy1_Id().getId2(), "");
        }
        if (!thisAmt.equals("")) {
            thisAmt = thisAmt.trim();
        }
        logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

        String thisCurcy = "";
        logger.debug(logPrfx + " --- thisCurcy: " + thisCurcy);

        String thisStmtItm1_Desc = "";
        SysNode thisFinStmtItm = thisFinTxactItm.getFinStmtItm1_Id();
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
        if (thisFinTxactItm.getFinAcct1_Id() != null) {
            thisAcct = Objects.toString(thisFinTxactItm.getFinAcct1_Id().getId2(), "");
        }
        if (!thisAcct.equals("")) {
            thisAcct = "to acct [" + thisAcct + "]";
        }
        logger.debug(logPrfx + " --- thisAcct: " + thisAcct);

        String thisDept = "";
        if (thisFinTxactItm.getFinDept1_Id() != null) {
            thisDept = Objects.toString(thisFinTxactItm.getFinDept1_Id().getId2(), "");
        }
        if (!thisDept.equals("")) {
            thisDept = "to dept [" + thisDept + "]";
        }
        logger.debug(logPrfx + " --- thisDept: " + thisDept);

        String thisTaxLne = "";
        if (thisFinTxactItm.getFinTaxLne1_Id() != null) {
            thisTaxLne = "[" + Objects.toString(thisFinTxactItm.getFinTaxLne1_Id().getId2(), "") + "]";
        }
        if (!thisTaxLne.equals("")) {
            thisTaxLne = "with tax line " + thisTaxLne;
        }
        logger.debug(logPrfx + " --- thisTaxLne: " + thisTaxLne);

        String thisChan = "";
        if (thisFinTxactItm.getGenChan1_Id() != null) {
            thisChan = Objects.toString(thisFinTxactItm.getGenChan1_Id().getId2(), "");
        }
        if (!thisChan.equals("")) {
            thisChan = "in chan [" + thisChan + "]";
        }
        logger.debug(logPrfx + " --- thisChan: " + thisChan);


        String thisHow = "";
        if (thisFinTxactItm.getFinHow1_Id() != null) {
            thisHow = Objects.toString(thisFinTxactItm.getFinHow1_Id().getId2(), "");
        }
        if (!thisHow.equals("")) {
            thisHow = "via " + thisHow;
        }
        logger.debug(logPrfx + " --- thisHow: " + thisHow);

        String thisWhat = Objects.toString(thisFinTxactItm.getWhatText1(), "");
        if (thisFinTxactItm.getFinWhat1_Id() != null) {
            thisWhat = thisWhat + " " + Objects.toString(thisFinTxactItm.getFinWhat1_Id().getId2());
            thisWhat = thisWhat.trim();
        }
        if (!thisWhat.equals("")) {
            thisWhat = "for " + thisWhat;
        }
        logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

        String thisWhy = Objects.toString(thisFinTxactItm.getWhyText1(), "");
        if (thisFinTxactItm.getFinWhy1_Id() != null) {
            thisWhy = thisWhy + " " + Objects.toString(thisFinTxactItm.getFinWhy1_Id().getId2());
            thisWhy = thisWhy.trim();
        }
        if (!thisWhy.equals("")) {
            thisWhy = "for " + thisWhy;
        }
        logger.debug(logPrfx + " --- thisWhy: " + thisWhy);

        String thisDocVer = "";
        if (thisFinTxactItm.getGenDocVer1_Id() != null) {
            thisDocVer = Objects.toString(thisFinTxactItm.getGenDocVer1_Id().getId2());
        }
        if (!thisDocVer.equals("")) {
            thisDocVer = "doc ver " + thisDocVer;
        }
        logger.debug(logPrfx + " --- thisDocVer: " + thisDocVer);

        String thisTag = "";
        String thisTag1 = "";
        if (thisFinTxactItm.getGenTag1_Id() != null) {
            thisTag1 = Objects.toString(thisFinTxactItm.getGenTag1_Id().getId2());
        }
        String thisTag2 = "";
        if (thisFinTxactItm.getGenTag1_Id() != null) {
            thisTag2 = Objects.toString(thisFinTxactItm.getGenTag2_Id().getId2());
        }
        String thisTag3 = "";
        if (thisFinTxactItm.getGenTag1_Id() != null) {
            thisTag3 = Objects.toString(thisFinTxactItm.getGenTag3_Id().getId2());
        }
        String thisTag4 = "";
        if (thisFinTxactItm.getGenTag1_Id() != null) {
            thisTag4 = Objects.toString(thisFinTxactItm.getGenTag4_Id().getId2());
        }
        if (!(thisTag1 + thisTag2 + thisTag3 + thisTag4).equals("")) {
            thisTag = "tag [" + String.join(",", thisTag1, thisTag2, thisTag3, thisTag4) + "]";
        }
        logger.debug(logPrfx + " --- thisTag: " + thisTag);

        List<String> list = Arrays.asList(
                thisAmt
                , thisAcct
                , thisDept
                , thisStmtItm1_Desc
                , thisChan
                , thisWhat
                , thisWhy
                , thisHow
                , thisDocVer
                , thisTag);

        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            thisFinTxactItm.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxact1_Id_Desc1(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();

        if (thisFinTxact != null) {
            //finTxset is a 2nd ref (ref of a ref) of finTxactItm and is not automatically loaded into the dataContext
            thisFinTxact = dataContext.merge(thisFinTxact);

            if (thisFinTxact != null) {
                String desc1_ = thisFinTxact.getDesc1();

                //thisType
                String thisType = "";
                if (thisFinTxact.getType1_Id() != null) {
                    thisType = Objects.toString(thisFinTxact.getType1_Id().getId2(), "");
                }
                logger.debug(logPrfx + " --- thisType: " + thisType);

                SysNode desc1FinTxactItm1 = thisFinTxact.getDesc1FinTxactItm1_Id() == null
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
                    if (desc1FinTxactItm1.getFinCurcy1_Id() != null) {
                        thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm1.getFinCurcy1_Id().getId2(), "");
                        thisAmt = thisAmt.trim();
                    }
                    if (!thisAmt.equals("")) {
                        thisAmt = thisAmt.trim();
                    }

                }
                logger.debug(logPrfx + " --- thisAmt: " + thisAmt);


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
                    thisHow = "via [" + thisHow + "]";
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


        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxact1_Id_FinTxset1_Id_Desc1(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id_FinTxset1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxset = thisFinTxactItm.getFinTxact1_Id() == null ? null : thisFinTxactItm.getFinTxact1_Id().getFinTxset1_Id();

        if (thisFinTxset != null) {
            //finTxset is a 2nd ref (ref of a ref) of finTxactItm and is not automatically loaded into the dataContext
            thisFinTxset = dataContext.merge(thisFinTxset);

            String desc1_ = thisFinTxset.getDesc1();

            if (thisFinTxset.getDesc1SysPat1_Id() == null) {

                //thisType
                String thisType = "";
                if (thisFinTxset.getType1_Id() != null) {
                    thisType = Objects.toString(thisFinTxset.getType1_Id().getId2(), "");
                }
                logger.debug(logPrfx + " --- thisType: " + thisType);

                SysNode desc1FinTxactItm1 = thisFinTxset.getDesc1FinTxactItm1_Id() == null
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
                    if (desc1FinTxactItm1.getFinCurcy1_Id() != null) {
                        thisAmt = thisAmt + " " + Objects.toString(desc1FinTxactItm1.getFinCurcy1_Id().getId2(), "");
                        thisAmt = thisAmt.trim();
                    }
                    if (thisType.contains("Exch")) {
                        SysNode desc1FinTxactItm2 = thisFinTxset.getDesc1FinTxactItm2_Id() == null
                                ? findFirstFinTxactItmLikeId2(thisFinTxset.getId2() + "/Y01/%")
                                : thisFinTxset.getDesc1FinTxactItm2_Id();
                        if (desc1FinTxactItm2 != null) {
                            thisAmt = thisAmt + " -> ";
                            if (desc1FinTxactItm2.getAmtDebt() != null) {
                                thisAmt = thisAmt + "" + desc1FinTxactItm2.getAmtDebt();
                            }else if (desc1FinTxactItm2.getAmtCred() != null) {
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


    private Boolean updateIdDt(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdDt();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg1(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateBeg1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateBeg1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg2(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateBeg2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateBeg2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdX();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdY(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdY";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdY();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdZ(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdZ";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdZ();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateFinStmtItm1_Id(@NotNull SysNode thisFinTxactItm, Integer finStmtItmOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinStmtItm1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode finStmtItm1_Id_ = thisFinTxactItm.getFinTxact1_Id();
        SysNode finStmtItm1_Id;
        // Update finStmtItm
        switch (finStmtItmOption) {
            case 1 -> { // Link to Exist Txact
                finStmtItm1_Id = findFinStmtItmEqId2(thisFinTxactItm.getFinStmtItm1_Id2Trgt());
                if (!Objects.equals(finStmtItm1_Id_, finStmtItm1_Id)) {
                    thisFinTxactItm.setFinStmtItm1_Id(finStmtItm1_Id);
                    isChanged = true;
                }
            }
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxact1_Id(@NotNull SysNode thisFinTxactItm, Integer finTxactOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode finTxact1_Id_ = thisFinTxactItm.getFinTxact1_Id();
        SysNode finTxact1_Id;
        // Update finTxact
        switch (finTxactOption) {
            case 1 -> { // Link to Exist Txact
                finTxact1_Id = findFinTxactEqId2(thisFinTxactItm.getFinTxact1_Id2Trgt());
                if (!Objects.equals(finTxact1_Id_, finTxact1_Id)) {
                    thisFinTxactItm.setFinTxact1_Id(finTxact1_Id);
                    isChanged = true;
                }
            }
            case 2 -> { // Link to Exist/New Txact
                finTxact1_Id = findFinTxactEqId2(thisFinTxactItm.getFinTxact1_Id2Trgt());
                if (finTxact1_Id == null) {
                    finTxact1_Id = createFinTxactFrFinTxactItm(thisFinTxactItm);
                }
                if (!Objects.equals(finTxact1_Id_, finTxact1_Id)) {
                    thisFinTxactItm.setFinTxact1_Id(finTxact1_Id);
                    isChanged = true;
                }
            }
            case 3 -> { // Update Exist Txact
                boolean thisFinTxactIsChanged = false;
                SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
                if (thisFinTxact != null) {
                    if (!Objects.equals(thisFinTxact.getIdDt().getDate1(), thisFinTxactItm.getIdDt().getDate1())) {
                        thisFinTxact.getBeg1().setTs1(thisFinTxactItm.getIdDt().getDate1().atStartOfDay());
                        thisFinTxact.updateIdDt();
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getIdX(), thisFinTxactItm.getIdX())) {
                        thisFinTxact.setIdX(thisFinTxactItm.getIdX());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getIdY(), thisFinTxactItm.getIdY())) {
                        thisFinTxact.setIdY(thisFinTxactItm.getIdY());
                        thisFinTxactIsChanged = true;
                    }
                    if (thisFinTxactIsChanged) {
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


    private void updateFinTxact1_IdCandidsTable(@NotNull SysNode thisFinTxactItm){
        String logPrfx = "updateFinTxact1_IdCandidsTable";
        logger.trace(logPrfx + " --> ");

        finTxact1_IdCandidsDl.setQuery("select e from ampata_SysNode e "
                + "where e.className = 'FinTxact' "
                + "and ( "
                + "       (    e.id2 = :id2 "
                + "       ) "
                + "    or (    e.idDt.date1 = :idDt "
                + "        and e.idX = :idX "
                + "        and e.idY = :idY "
                + "       ) "
                + "   ) "
                + "order by e.id2");

        String id2Trgt = thisFinTxactItm.getFinTxact1_Id2Trgt();
        if (id2Trgt == null) {
            logger.debug(logPrfx + " --- id2Trgt is null.");
            notifications.create().withCaption("id2Trgt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            finTxact1_IdCandidsDl.setParameter("id2",id2Trgt);
        }

        LocalDate idDt = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
        if (idDt == null) {
            logger.debug(logPrfx + " --- idDt is null.");
            notifications.create().withCaption("idDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            idDt = thisFinTxactItm.getIdDt().getDate1();
            finTxact1_IdCandidsDl.setParameter("idDt",idDt);
        }

        Integer idX = thisFinTxactItm.getIdX() != null ? thisFinTxactItm.getIdX() : 0;
        finTxact1_IdCandidsDl.setParameter("idX",idX);

        Integer idY = thisFinTxactItm.getIdY() != null ? thisFinTxactItm.getIdY() : 0;
        finTxact1_IdCandidsDl.setParameter("idY",idY);

        finTxact1_IdCandidsDl.load();
        logger.debug(logPrfx + " --- called finTxact1_IdCandidsDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateFinTxact1_Id2Trgt(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String finTxact1_Id2Trgt_ = thisFinTxactItm.getFinTxact1_Id2Trgt();
        String finTxact1_Id2Trgt = thisFinTxactItm.getId2CalcFrFields().substring(0, 20);
        if(!Objects.equals(finTxact1_Id2Trgt_, finTxact1_Id2Trgt)){
            isChanged = true;
            thisFinTxactItm.setFinTxact1_Id2Trgt(finTxact1_Id2Trgt);
            logger.debug(logPrfx + " --- finTxact1_Id2Trgt: " + finTxact1_Id2Trgt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFintxact1_Id_FinTxactItms1_IdCntCalc(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            Integer idCntCalc_ = thisFinTxact.getFinTxactItms1_IdCntCalc();
            Integer idCntCalc = null ;
            String qry1 = "select count(e.amtNet) from ampata_SysNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFintxact1_Id_FinTxactItms1_AmtDebtSumCalc(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_DebtSum";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            BigDecimal amtDebtSumCalc_ = thisFinTxact.getFinTxactItms1_AmtDebtSumCalc();
            BigDecimal amtDebtSumCalc = null ;
            String qry1 = "select sum(e.amtDebt) from ampata_SysNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            BigDecimal amtCredSumCalc_ = thisFinTxact.getFinTxactItms1_AmtCredSumCalc();
            BigDecimal amtCredSumCalc = null ;
            String qry1 = "select sum(e.amtCred) from ampata_SysNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    
    private Boolean updateFintxact1_Id_FinTxactItms1_AmtEqCalc(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_AmtEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            Boolean amtEqCalc_ = thisFinTxact.getFinTxactItms1_AmtEqCalc();
            Boolean amtEqCalc = Objects.equals(thisFinTxact.getFinTxactItms1_AmtDebtSumCalc()
                                                        , thisFinTxact.getFinTxactItms1_AmtCredSumCalc());

            if(!Objects.equals(amtEqCalc_, amtEqCalc)){
                isChanged = true;
                thisFinTxact.setFinTxactItms1_AmtEqCalc(amtEqCalc);
                logger.debug(logPrfx + " --- amtEqCalc: " + amtEqCalc);
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalc(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();

        if(thisFinTxact == null){
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        Boolean finCurcyEqCalc_ = thisFinTxact.getFinTxactItms1_FinCurcyEqCalc();
        Boolean finCurcyEqCalc = false;

        List<SysNode> finCurcyList = null;
        String qry1 = "select e.finCurcy1_Id from ampata_SysNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id";
        try{
            finCurcyList = dataManager.loadValue(qry1, SysNode.class)
                    .store("main")
                    .parameter("finTxact1_Id",thisFinTxact)
                    .list();
            if (finCurcyList == null || finCurcyList.isEmpty()) {
                logger.debug(logPrfx + " --- finCurcyList.size(): 0");
            }else{
                logger.debug(logPrfx + " --- finCurcyList.size(): " + finCurcyList.size());

                String finCurcyFirst_Id2 = finCurcyList.get(0).getId2();
                finCurcyEqCalc = true;
                for (SysNode finCurcy: finCurcyList){
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
            thisFinTxact.setFinTxactItms1_FinCurcyEqCalc(finCurcyEqCalc);
            logger.debug(logPrfx + " --- finCurcyEqCalc: " + finCurcyEqCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    

    private void updateFinStmtItm1_IdCandidsTable(@NotNull SysNode thisFinTxactItm){
        String logPrfx = "updateFinStmtItm1_IdCandidsTable";
        logger.trace(logPrfx + " --> ");

        finStmtItm1_IdCandidsDl.setQuery("select e from ampata_SysNode e "
                + "where e.className = 'FinStmtItm' "
                + "and e.finStmt1_Id.finAcct1_Id = :finAcct1_Id "
                + "and e.idDt.date1 between :begDt and :endDt "
                + "and (e.amtDebt = :amt or e.amtCred = :amt) "
                + "order by e.id2");
        SysNode finAcct1 = thisFinTxactItm.getFinAcct1_Id();
        if (finAcct1 == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null.");
            notifications.create().withCaption("finAcct1_Id is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            finStmtItm1_IdCandidsDl.setParameter("finAcct1_Id",finAcct1);
        }

        HasDate thisDt = thisFinTxactItm.getIdDt();
        LocalDate begDt;
        LocalDate endDt;
        if (thisDt == null) {
            logger.debug(logPrfx + " --- idDt is null.");
            notifications.create().withCaption("idDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        begDt = thisFinTxactItm.getIdDt().getDate1().minusDays(3);
        finStmtItm1_IdCandidsDl.setParameter("begDt",begDt);
        endDt = thisFinTxactItm.getIdDt().getDate1().plusDays(3);
        finStmtItm1_IdCandidsDl.setParameter("endDt",endDt);

        BigDecimal amtDebt = thisFinTxactItm.getAmtDebt();
        BigDecimal amtCred = thisFinTxactItm.getAmtCred();
        if (amtDebt != null) {
            finStmtItm1_IdCandidsDl.setParameter("amt",amtDebt);
        }else if (amtCred != null) {
            finStmtItm1_IdCandidsDl.setParameter("amt",amtCred);
        }else{
            logger.debug(logPrfx + " --- amtDebt and amtCred are null.");
            notifications.create().withCaption("amtDebt and amtCred are null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        finStmtItm1_IdCandidsDl.load();
        logger.debug(logPrfx + " --- called finStmtItm1_IdCandidsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateFinStmtItm1_Id2Trgt(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinStmtItm1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String finStmtItm1_Id2Trgt_ = thisFinTxactItm.getFinStmtItm1_Id2Trgt();
        // TODO
       
        String finStmtItm1_Id2Trgt = null;
        if(!Objects.equals(finStmtItm1_Id2Trgt_, finStmtItm1_Id2Trgt)){
            isChanged = true;
            thisFinTxactItm.setFinStmtItm1_Id2Trgt(finStmtItm1_Id2Trgt);
            logger.debug(logPrfx + " --- finStmtItm1_Id2Trgt: " + finStmtItm1_Id2Trgt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private SysNode findFinTxactItmEqId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFinTxactItmEqId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_SysNode e where e.className = 'FinTxactItm' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        SysNode finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(SysNode.class)
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

    private SysNode findFinTxactEqId2(@NotNull String finTxact_Id2) {
        String logPrfx = "findFinTxactEqId2";
        logger.trace(logPrfx + " --> ");

        if (finTxact_Id2 == null) {
            logger.debug(logPrfx + " --- finTxact_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxact_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_SysNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        SysNode finTxact1_Id = null;
        try {
            finTxact1_Id = dataManager.load(SysNode.class)
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


    private SysNode findFirstFinTxactItmLikeId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFirstFinTxactItmLikeId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_SysNode e where e.className = 'FinTxactItm' and e.id2 like :id2 order by e.id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactItm_Id2);

        SysNode finTxactItm1_Id = null;
        try {
            finTxactItm1_Id = dataManager.load(SysNode.class)
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

    private SysNode createFinTxactFrFinTxactItm(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "createFinTxactFrFinTxactItm";
        logger.trace(logPrfx + " --> ");

        String finTxact_Id2 = thisFinTxactItm.getFinTxact1_Id2Trgt();
        if (finTxact_Id2 == null) {
            logger.debug(logPrfx + " --- finTxact_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxact_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_SysNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        try {
            SysNode finTxact = dataManager.load(SysNode.class)
                    .query(qry)
                    .parameter("id2", finTxact_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            logger.trace(logPrfx + " <-- ");
            return finTxact;

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

            SysNode newFinTxact = dataManager.create(SysNode.class);
            newFinTxact.setClassName("FinTxact");

            HasTmst beg1 = dataManager.create(HasTmst.class);
            beg1.setTs1(thisFinTxactItm.getIdDt().getDate1().atStartOfDay());
            newFinTxact.setBeg1(beg1);
            newFinTxact.updateIdDt();

            newFinTxact.setIdX(thisFinTxactItm.getIdX());
            newFinTxact.setIdY(thisFinTxactItm.getIdY());

            newFinTxact.setId2Calc(newFinTxact.getId2CalcFrFields());
            newFinTxact.setId2(newFinTxact.getId2Calc());


            SysNode savedFinTxact = dataManager.save(newFinTxact);

            SysNode mergedFinTxact = dataContext.merge(savedFinTxact);
            logger.debug(logPrfx + " --- created FinTxact id: " + mergedFinTxact.getId());
            notifications.create().withCaption("Created FinTxact with id2:" + mergedFinTxact.getId2()).show();

            logger.trace(logPrfx + " <-- ");
            return mergedFinTxact;
        }
    }


    private Boolean updateFinTxact1_FinTxset1_Id(@NotNull SysNode thisFinTxactItm, Integer finTxsetOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_FinTxset1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null){
            SysNode finTxset1_Id_ = thisFinTxact.getFinTxset1_Id();
            SysNode finTxset1_Id;
            // Update finTxact
            switch (finTxsetOption) {
                case 1 -> { // Link to Exist Txset
                    finTxset1_Id = findFinTxsetEqId2(thisFinTxact.getFinTxset1_Id2Trgt());
                    if (!Objects.equals(finTxset1_Id_, finTxset1_Id)) {
                        isChanged = true;
                        thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                    }
                }
                case 2 -> { // Link to Exist/New Txset
                    finTxset1_Id = findFinTxsetEqId2(thisFinTxact.getFinTxset1_Id2Trgt());
                    if (finTxset1_Id == null) {
                        finTxset1_Id = createFinTxsetFrFinTxactItm(thisFinTxactItm);
                    }
                    if (!Objects.equals(finTxset1_Id_, finTxset1_Id)) {
                        thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                        isChanged = true;
                    }
                }
                case 3 -> { // Update Exist Txact
                    boolean thisFinTxsetIsChanged = false;
                    SysNode thisFinTxset = thisFinTxact.getFinTxset1_Id();
                    if (thisFinTxset != null) {
                        if (!Objects.equals(thisFinTxset.getIdDt().getDate1(), thisFinTxactItm.getIdDt().getDate1())) {
                            thisFinTxset.getBeg1().setTs1(thisFinTxactItm.getIdDt().getDate1().atStartOfDay());
                            thisFinTxsetIsChanged = true;
                        }
                        if (!Objects.equals(thisFinTxset.getIdX(), thisFinTxactItm.getIdX())) {
                            thisFinTxset.setIdX(thisFinTxactItm.getIdX());
                            thisFinTxsetIsChanged = true;
                        }
                        if (thisFinTxsetIsChanged) {
                            thisFinTxset.updateIdDt();
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



    private void updateFinTxact1_Id_FinTxset1_IdCandidsTable(@NotNull SysNode thisFinTxactItm){
        String logPrfx = "updateFinTxact1_Id_FinTxset1_IdCandidsTable";
        logger.trace(logPrfx + " --> ");

        finTxset1_IdCandidsDl.setQuery("select e from ampata_SysNode e "
                + "where e.className = 'FinTxset' "
                + "and ( "
                + "       (    e.id2 = :id2 "
                + "       ) "
                + "    or (    e.idDt.date1 = :idDt "
                + "        and e.idX = :idX "
                + "       ) "
                + "   ) "
                + "order by e.id2");

        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact == null){
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String id2Trgt = thisFinTxact.getFinTxset1_Id2Trgt();
        if (id2Trgt == null) {
            logger.debug(logPrfx + " --- id2Trgt is null.");
            notifications.create().withCaption("id2Trgt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            finTxset1_IdCandidsDl.setParameter("id2",id2Trgt);
        }

        LocalDate idDt = thisFinTxactItm.getIdDt() != null ? thisFinTxactItm.getIdDt().getDate1() : null;
        if (idDt == null) {
            logger.debug(logPrfx + " --- idDt is null.");
            notifications.create().withCaption("idDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            idDt = thisFinTxactItm.getIdDt().getDate1();
            finTxset1_IdCandidsDl.setParameter("idDt",idDt);
        }

        Integer idX = thisFinTxactItm.getIdX() != null ? thisFinTxactItm.getIdX() : 0;
        finTxset1_IdCandidsDl.setParameter("idX",idX);

        finTxset1_IdCandidsDl.load();
        logger.debug(logPrfx + " --- called finTxset1_IdCandidsDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateFinTxact1_FinTxset1_Id2Trgt(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_FinTxset1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null){
            String id2Calc_ = thisFinTxactItm.getId2Calc();
            String id2Calc = thisFinTxactItm.getId2CalcFrFields().substring(0, 16);
            if (!Objects.equals(id2Calc_, id2Calc)){
                isChanged = true;
                thisFinTxact.setFinTxset1_Id2Trgt(id2Calc);
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private SysNode findFinTxsetEqId2(@NotNull String finTxset_Id2) {
        String logPrfx = "findFinTxsetEqId2";
        logger.trace(logPrfx + " --> ");

        if (finTxset_Id2 == null) {
            logger.debug(logPrfx + " --- finTxset_Id2 is null.");
            notifications.create().withCaption("finTxset_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_SysNode e where e.className = 'FinTxset' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxset_Id2);

        SysNode finTxset1_Id = null;
        try {
            finTxset1_Id = dataManager.load(SysNode.class)
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

    private SysNode createFinTxsetFrFinTxactItm(@NotNull SysNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "createFinTxsetFrFinTxactItm";
        logger.trace(logPrfx + " --> ");

        SysNode mergedFinTxset = null;
        if (thisFinTxactItm.getFinTxact1_Id() != null) {
            String finTxset_Id2 = thisFinTxactItm.getFinTxact1_Id().getFinTxset1_Id2Trgt();
            if (finTxset_Id2 == null) {
                logger.debug(logPrfx + " --- finTxset_Id2 is null.");
                notifications.create().withCaption("finTxset_Id2 is empty. Please set it to correct value.").show();
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            String qry = "select e from ampata_SysNode e where e.className = 'FinTxset' and e.id2 = :id2";
            logger.debug(logPrfx + " --- qry: " + qry);
            logger.debug(logPrfx + " --- qry:id2: " + finTxset_Id2);

            try {
                SysNode finTxset = dataManager.load(SysNode.class)
                        .query(qry)
                        .parameter("id2", finTxset_Id2)
                        .one();
                logger.debug(logPrfx + " --- query qry returned ONE result");
                logger.trace(logPrfx + " <-- ");
                return finTxset;

            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- query qry returned NO results");

                SysNode newFinTxset = dataManager.create(SysNode.class);
                newFinTxset.setClassName("FinTxset");

                HasTmst beg1 = dataManager.create(HasTmst.class);
                beg1.setTs1(thisFinTxactItm.getIdDt().getDate1().atStartOfDay());
                newFinTxset.setBeg1(beg1);
                newFinTxset.updateIdDt();

                newFinTxset.setIdX(thisFinTxactItm.getIdX());

                newFinTxset.setId2Calc(newFinTxset.getId2CalcFrFields());
                newFinTxset.setId2(newFinTxset.getId2Calc());


                SysNode savedFinTxset = dataManager.save(newFinTxset);

                mergedFinTxset = dataContext.merge(savedFinTxset);
                logger.debug(logPrfx + " --- created FinTxset id: " + mergedFinTxset.getId());
                notifications.create().withCaption("Created FinTxset with id2:" + mergedFinTxset.getId2()).show();
            }

        }
        logger.trace(logPrfx + " <-- ");
        return mergedFinTxset;
    }


    private SysNode findFinStmtItmEqId2(@NotNull String finStmtItm_Id2) {
        String logPrfx = "findFinStmtItmEqId2";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from ampata_SysNode e where e.className = 'FinStmtItm' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finStmtItm_Id2);

        SysNode finStmtItm_Id = null;
        try {
            finStmtItm_Id = dataManager.load(SysNode.class)
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


    private Boolean updateAmtFinTxactItm1_EI1_Rate(@NotNull SysNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtFinTxactItm1_EI1_Rate";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal rate_ = thisFinTxactItm.getAmtFinTxactItm1_EI1_Rate();

        FinFmla amtFinFmla1 = thisFinTxactItm.getAmtFinFmla1_Id();
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

        SysNode amtFinTxactItm1 = thisFinTxactItm.getAmtFinTxactItm1_Id();
        if (amtFinTxactItm1 == null) {
            logger.debug(logPrfx + " --- amtFinTxactItm1: null");
            notifications.create().withCaption("Unable to get reference to the other FinTxactItm. Please ensure amtFinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        SysNode curcyFr = thisFinTxactItm.getAmtFinTxactItm1_Id().getFinCurcy1_Id();
        if (curcyFr == null) {
            logger.debug(logPrfx + " --- curcyFr: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxactItm. Please ensure a FinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- curcyFr.Id: " + curcyFr.getId());
        }

        SysNode curcyTo = thisFinTxactItm.getFinCurcy1_Id();
        if (curcyTo == null) {
            logger.debug(logPrfx + " --- curcyTo: null");
            notifications.create().withCaption("Unable to get the currency from this FinTxactItm. Please ensure a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- curcyTo.Id: " + curcyTo.getId());

        }

        LocalDate date1 = thisFinTxactItm.getBeg1().getDate1();

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        if (date1 == null) {
            logger.debug(logPrfx + " --- date1: null");
            notifications.create().withCaption("Unable to get the date from this FinTxactItm. Please ensure a date is selected.").show();
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
            rate = finRate.getAmt1();
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
                rate = finRate.getAmt2();
                qryRsltGood = true;
                logger.debug(logPrfx + " --- qry2 result is Id: " + finRate.getId());
            } else {
                logger.debug(logPrfx + " --- qry2 result is empty");
            }
        }

        if (!Objects.equals(rate_, rate) && qryRsltGood) {
            thisFinTxactItm.setAmtFinTxactItm1_EI1_Rate(rate);
            logger.debug(logPrfx + " --- rate: " + rate);
            isChanged = true;
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;

    }


    private Boolean updateAmtCalc(@NotNull SysNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtCalc_ = thisFinTxactItm.getAmtCalc();
        BigDecimal amtCalc = BigDecimal.valueOf(0);
        boolean amtCalcGood = false;

        FinFmla amtFinFmla1 = thisFinTxactItm.getAmtFinFmla1_Id();
        if (amtFinFmla1 == null) {
            logger.debug(logPrfx + " --- amtFinFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- amtFinFmla1: " + amtFinFmla1.getId());
        }

        SysNode prev1FinTxactItm;
        SysNode prev2FinTxactItm;

        // Switch statement over above string
        switch (amtFinFmla1.getId2()) {

            // Ref1 Mult Exch_Rate
            case "Ref1 Mult Exch_Rate":

                SysNode amtFinTxactItm1 = thisFinTxactItm.getAmtFinTxactItm1_Id();
                if (amtFinTxactItm1 == null) {
                    logger.debug(logPrfx + " --- amtFinTxactItm1: null");
                    notifications.create().withCaption("Unable to get amtFinTxactItm1. Please ensure amtFinTxactItm1 has a finTxactItm selected.").show();
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- amtFinTxactItm1: " + amtFinTxactItm1.getId());
                }

                BigDecimal val = amtFinTxactItm1.getAmtNet();
                if (val == null) {
                    logger.debug(logPrfx + " --- amtFinTxactItm1.getAmtNet(): null");
                    notifications.create().withCaption("Unable to get amtFinTxactItm1.amtNet(). Please ensure  amtFinTxactItm1.amtNet() has a value.").show();
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                } else {
                    logger.debug(logPrfx + " --- amtFinTxactItm1.getAmtNet(): " + val);
                }

                BigDecimal rate = thisFinTxactItm.getAmtFinTxactItm1_EI1_Rate();
                if (rate == null){
                    logger.debug(logPrfx + " --- thisFinTxactItm.getAmtFinTxactItm1_EI1_Rate(): null");
                    notifications.create().withCaption("Unable to get thisFinTxactItm.getAmtFinTxactItm1_EI1_Rate(). Please ensure thisFinTxactItm.getFinTxactItm1_EI1_Rate() has a value.").show();
                    logger.trace(logPrfx + " <-- ");
                    return isChanged;
                }
                amtCalc = val.multiply(rate).setScale(2, RoundingMode.HALF_UP).negate();
                amtCalcGood = true;
                break;

            case "Prev2.Cr Sub Prev1.Dr":
                prev1FinTxactItm = getPrevFinTxactItm(thisFinTxactItm);
                if (prev1FinTxactItm != null) {
                    BigDecimal prev1Dr = prev1FinTxactItm.getAmtDebt() != null ? prev1FinTxactItm.getAmtDebt() : BigDecimal.valueOf(0);

                    prev2FinTxactItm = getPrevFinTxactItm(prev1FinTxactItm);
                    if (prev2FinTxactItm != null) {
                        BigDecimal prev2Cr = prev2FinTxactItm.getAmtCred() != null ? prev2FinTxactItm.getAmtCred() : BigDecimal.valueOf(0);

                        amtCalc = prev2Cr.subtract(prev1Dr);
                        amtCalcGood = true;
                    }
                }
                break;

            case "Prev2.Dr Sub Prev1.Cr":
                prev1FinTxactItm = getPrevFinTxactItm(thisFinTxactItm);
                if (prev1FinTxactItm != null) {
                    BigDecimal prev1Cr = prev1FinTxactItm.getAmtCred() != null ? prev1FinTxactItm.getAmtCred() : BigDecimal.valueOf(0);

                    prev2FinTxactItm = getPrevFinTxactItm(prev1FinTxactItm);
                    if (prev2FinTxactItm != null) {
                        BigDecimal prev2Dr = prev2FinTxactItm.getAmtDebt() != null ? prev2FinTxactItm.getAmtDebt() : BigDecimal.valueOf(0);

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
            thisFinTxactItm.setAmtCalc(amtCalc);
            logger.debug(logPrfx + " --- amtCalc: " + amtCalc);
            isChanged = true;
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNet(@NotNull SysNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtNet_ = thisFinTxactItm.getAmtNet();

        SysNode thisFinAcct = thisFinTxactItm.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        SysNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal amtNet = BigDecimal.valueOf(0);
        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();

        if (thisFinTxactItm.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                amtNet = amtNet.subtract(thisFinTxactItm.getAmtDebt());
            } else {
                amtNet = amtNet.add(thisFinTxactItm.getAmtDebt());
            }
        }

        if (thisFinTxactItm.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                amtNet = amtNet.subtract(thisFinTxactItm.getAmtCred());
            } else {
                amtNet = amtNet.add(thisFinTxactItm.getAmtCred());
            }
        }
        if(!Objects.equals(amtNet_, amtNet)){
            thisFinTxactItm.setAmtNet(amtNet);
            logger.debug(logPrfx + " --- amtNet: " + amtNet);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTaxLne1_Id(@NotNull SysNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTaxLne1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        SysNode finTaxLne_ = thisFinTxactItm.getFinTaxLne1_Id();
        SysNode finTaxLne = thisFinTxactItm.getFinAcct1_Id() == null ? null :
                thisFinTxactItm.getFinAcct1_Id().getFinTaxLne1_Id();

        if (!Objects.equals(finTaxLne_, finTaxLne)){
            thisFinTxactItm.setFinTaxLne1_Id(finTaxLne);
            logger.debug(logPrfx + " --- finTaxLne.id2: " + finTaxLne == null ? "null" : finTaxLne.getId2());
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public SysNode getPrevFinTxactItm(@NotNull SysNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "getPrevFinTxactItm";
        logger.trace(logPrfx + " --> ");

        SysNode prevFinTxactItm = null;
        boolean qryRsltGood = false;

        if (thisFinTxactItm.getIdZ() != null || thisFinTxactItm.getIdZ() >= 1) {
            prevFinTxactItm = metadataTools.copy(thisFinTxactItm);
            prevFinTxactItm.setIdZ(prevFinTxactItm.getIdZ() - 1);
            String prev1FinTxactItm_Id2 = prevFinTxactItm.getId2CalcFrFields();

            String qry = "select gn from ampata_SysNode gn"
                    + " where gn.id2 = :id2";

            logger.debug(logPrfx + " --- qry: " + qry);

            try {
                prevFinTxactItm = dataManager.load(SysNode.class)
                        .query(qry)
                        .parameter("id2", prev1FinTxactItm_Id2)
                        .one()
                ;

            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- qry1 java.lang.IllegalStateException: " + e.getMessage());
                prevFinTxactItm = null;
            }

            if (prevFinTxactItm != null) {
                logger.debug(logPrfx + " --- qry1 result is Id: " + prevFinTxactItm.getId());
            }
        }
        logger.trace(logPrfx + " <-- ");
        return prevFinTxactItm;
    }


    private void reloadWhatText1List(){
        String logPrfx = "reloadWhatText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whatText1"
                + " from ampata_SysNode e"
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
                + " from ampata_SysNode e"
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
                + " from ampata_SysNode e"
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
                + " from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
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

        ComboBox<String> propFilterCmpnt_finTxact1_EI1_Role = (ComboBox<String>) filterConfig1A_finTxact1_EI1_Role.getValueComponent();
        propFilterCmpnt_finTxact1_EI1_Role.setOptionsList(roles);
        logger.debug(logPrfx + " --- called propFilterCmpnt_finTxact1_EI1_Role.setOptionsList()");

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
                + " from ampata_SysNode e"
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
                + " from ampata_SysNode e"
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
                + " from ampata_SysNode e"
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
                + " from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
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

    private Integer getIdXMax(LocalDate idDt1){
        String logPrfx = "getIdXMax";
        logger.trace(logPrfx + " --> ");

        Integer idX, idXMax;
        String idXMaxQry = "select max(e.idX) from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
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
        String idXCntIsNullQry = "select count(e) from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
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
        String idYMaxQry = "select max(e.idY) from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
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
        String idYCntIsNullQry = "select count(e) from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
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

    private Integer getIdZMax(LocalDate idDt1, Integer idX, Integer idY) {
        String logPrfx = "getIdZMax";
        logger.trace(logPrfx + " --> ");

        Integer idZ, idZMax = 0;
        String idZMaxQry = "select max(e.idZ) from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
                + " and e.idDt.date1 = :idDt1"
                + " and e.idX = :idX"
                + " and e.idY = :idY";
        try {
            idZMax = dataManager.loadValue(idZMaxQry, Integer.class)
                    .store("main")
                    .parameter("idDt1", idDt1)
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
        String idZCntIsNullQry = "select count(e) from ampata_SysNode e"
                + " where e.className = 'FinTxactItm'"
                + " and e.idDt.date1 = :idDt1"
                + " and e.idX = :idX"
                + " and e.idX = :idY"
                + " and e.idY is null";
        try {
            idZCntIsNull = dataManager.loadValue(idZCntIsNullQry, Integer.class)
                    .store("main")
                    .parameter("idDt1", idDt1)
                    .parameter("idX", idX)
                    .parameter("idY", idY)
                    .one();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- idZCntIsNullQry error: " + e.getMessage());
            notifications.create().withCaption("idZCntIsNullQry error: " + e.getMessage()).show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }
        logger.debug(logPrfx + " --- idZCntIsNullQry result: " + idZCntIsNull + "");

        if (idZMax == null && idZCntIsNull != 0){
            idZ = 1;
        }else{
            idZ = idZMax == null ? 0 : idZMax + 1;
        }

        return idZ;
    }



}
