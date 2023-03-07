package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
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
@UiController("enty_UsrNodeFinTxactItm.main")
@UiDescriptor("usr-node-fin-txact-itm-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinTxactItm0Main extends MasterDetailScreen<UsrNodeBase> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ListComponent<UsrNodeBase> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

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

    @Autowired
    private MultitenancyUiSupport multitenancyUiSupport;

    @Autowired
    private TenantProvider tenantProvider;

    @Autowired
    private ComboBox<String> tenantField;



    //Query Manager
    private UsrNodeFinTxactItmQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    //Filter (filterConfig1A)
    @Autowired
    protected PropertyFilter<UsrNodeBaseType> filterConfig1A_Type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_IdDtLE;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_FinTxact1_EI1_Role;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_IdX;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_IdY;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_IdZ;

    @Autowired
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1A_FinAcct1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinDept> filterConfig1A_FinDept1_Id;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_SysNodeFinCurcy1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenDocVer> filterConfig1A_GenDocVer1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1A_GenTag1_Id;
    
    //Filter (Config1A.FinTxact)
    @Autowired
    protected PropertyFilter<String> filterConfig1A_FinTxact1_Id_FinTxactSet1_EI1_Role;
    

    //Filter (Config1A.FinTxact.FinTxactSet)
    @Autowired
    protected PropertyFilter<UsrNodeFinTxactSetType> filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_GenTag1_Id;

    
    //Filter (filterConfig1A.FinStmtItm)
    @Autowired
    protected PropertyFilter<UsrNodeFinStmt> filterConfig1A_FinStmtItm1_Id_FinStmt1_Id;


    //Filter (filterConfig1B)
    @Autowired
    protected PropertyFilter<UsrNodeBaseType> filterConfig1B_Type1_Id;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_IdDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1B_IdDtLE;

    @Autowired
    protected PropertyFilter<String> filterConfig1B_FinTxact1_EI1_Role;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_IdX;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_IdY;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_IdZ;

    @Autowired
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1B_FinAcct1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinDept> filterConfig1B_FinDept1_Id;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1B_SysNodeFinCurcy1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenDocVer> filterConfig1B_GenDocVer1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1B_GenTag1_Id;

    //Filter (filterConfig1B.FinTxact)
    @Autowired
    protected PropertyFilter<String> filterConfig1B_FinTxact1_Id_FinTxactSet1_EI1_Role;


    //Filter (filterConfig1B.FinTxact.FinTxactSet)
    @Autowired
    protected PropertyFilter<UsrNodeFinTxactSetType> filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_GenTag1_Id;


    //Filter (filterConfig1B.FinStmtItm)
    @Autowired
    protected PropertyFilter<UsrNodeFinStmt> filterConfig1B_FinStmtItm1_Id_FinStmt1_Id;


    //Toolbar
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

    @Autowired
    protected CheckBox updateFilterConfig1A_IdDtLE_SyncChk;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdDtGERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdXRdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdYRdo;


    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrNodeBaseType> tmplt_Type1_IdField;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_EI1_RoleFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1ElTsField;

    @Autowired
    protected CheckBox tmplt_Ts1ElTsFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts2ElTsField;

    @Autowired
    protected CheckBox tmplt_Ts2ElTsFieldChk;

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
    protected EntityComboBox<UsrNodeGenChan> tmplt_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinHow> tmplt_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhat> tmplt_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhy> tmplt_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhy1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinStmtItm> tmplt_FinStmtItm1_IdField;

    @Autowired
    protected CheckBox tmplt_FinStmtItm1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinTaxLne> tmplt_FinTaxLne1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTaxLne1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTaxLne1_CodeField;

    @Autowired
    protected CheckBox tmplt_FinTaxLne1_CodeFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinAcct> tmplt_FinAcct1_IdField;

    @Autowired
    protected CheckBox tmplt_FinAcct1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinDept> tmplt_FinDept1_IdField;

    @Autowired
    protected CheckBox tmplt_FinDept1_IdFieldChk;

    @Autowired
    protected TextField<BigDecimal> tmplt_AmtDebtField;

    @Autowired
    protected CheckBox tmplt_AmtDebtFieldChk;

    @Autowired
    protected TextField<BigDecimal> tmplt_AmtCredField;

    @Autowired
    protected CheckBox tmplt_AmtCredFieldChk;

    @Autowired
    protected EntityComboBox<SysNodeFinCurcy> tmplt_SysNodeFinCurcy1_IdField;

    @Autowired
    protected CheckBox tmplt_SysNodeFinCurcy1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemGenFmla> tmplt_AmtCalcGenFmla1_IdField;

    @Autowired
    protected CheckBox tmplt_AmtCalcGenFmla1_IdFieldChk;


    //Template (FinTxact)
    @Autowired
    protected EntityComboBox<UsrNodeFinTxactType> tmplt_FinTxact1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_Type1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleFieldChk;


    //Template (FinTxactSet)
    @Autowired
    protected EntityComboBox<UsrNodeFinTxactSetType> tmplt_FinTxact1_Id_FinTxactSet1_Id_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_Id_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeGenChan> tmplt_FinTxact1_Id_FinTxactSet1_Id_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_Id_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinHow> tmplt_FinTxact1_Id_FinTxactSet1_Id_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_Id_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhat> tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhy> tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_IdFieldChk;



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
    private CollectionLoader<UsrNodeBaseType> colLoadrType;


    //Other data containers, loaders and table
    private CollectionContainer<UsrNodeGenChan> colCntnrGenChan;
    private CollectionLoader<UsrNodeGenChan> colLoadrGenChan;

    private CollectionContainer<UsrNodeGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrNodeGenDocVer> colLoadrGenDocVer;

    private CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    private CollectionLoader<UsrItemGenTag> colLoadrGenTag;

    private CollectionContainer<UsrNodeFinTxact> colCntnrFinTxact;
    private CollectionLoader<UsrNodeFinTxact> colLoadrFinTxact;

    private CollectionContainer<UsrNodeFinTxactType> colCntnrFinTxactType;
    private CollectionLoader<UsrNodeFinTxactType> colLoadrFinTxactType;

    private CollectionContainer<UsrNodeFinTxact> colContnrFinTxact1_IdCandids;
    private CollectionLoader<UsrNodeFinTxact> colLoadrFinTxact1_IdCandids;

    private CollectionContainer<UsrNodeFinTxactSet> colCntnrFinTxactSet;
    private CollectionLoader<UsrNodeFinTxactSet> colLoadrFinTxactSet;

    private CollectionContainer<UsrNodeFinTxactSetType> colCntnrFinTxactSetType;
    private CollectionLoader<UsrNodeFinTxactSetType> colLoadrFinTxactSetType;

    private CollectionContainer<UsrNodeFinTxactSet> colCntnrFinTxactSet1_IdCandids;
    private CollectionLoader<UsrNodeFinTxactSet> colLoadrFinTxactSet1_IdCandids;

    private CollectionContainer<UsrNodeFinStmt> colCntnrFinStmt;
    private CollectionLoader<UsrNodeFinStmt> colLoadrFinStmt;

    private CollectionContainer<UsrNodeFinStmtItm> colCntnrFinStmtItm;
    private CollectionLoader<UsrNodeFinStmtItm> colLoadrFinStmtItm;

    private CollectionContainer<UsrNodeFinStmtItm> colCntnrFinStmtItm1_IdCandids;
    private CollectionLoader<UsrNodeFinStmtItm> colLoadrFinStmtItm1_IdCandids;

    private CollectionContainer<UsrNodeFinDept> colCntnrFinDept;
    private CollectionLoader<UsrNodeFinDept> colLoadrFinDept;

    private CollectionContainer<UsrNodeFinTaxLne> colCntnrFinTaxLne;
    private CollectionLoader<UsrNodeFinTaxLne> colLoadrFinTaxLne;

    private CollectionContainer<UsrNodeFinAcct> colCntnrFinAcct;
    private CollectionLoader<UsrNodeFinAcct> colLoadrFinAcct;

    private CollectionContainer<SysNodeFinCurcy> colCntnrSysNodeFinCurcy;
    private CollectionLoader<SysNodeFinCurcy> colLoadrSysNodeFinCurcy;

    private CollectionContainer<UsrNodeFinTxactItm> colCntnrFinTxactItm1;
    private CollectionLoader<UsrNodeFinTxactItm> colLoadrFinTxactItm1;

    private CollectionContainer<UsrItemGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrItemGenFmla> colLoadrGenFmla;

    private CollectionContainer<UsrItemFinHow> colCntnrFinHow;
    private CollectionLoader<UsrItemFinHow> colLoadrFinHow;

    private CollectionContainer<UsrItemFinWhat> colCntnrFinWhat;
    private CollectionLoader<UsrItemFinWhat> colLoadrFinWhat;

    private CollectionContainer<UsrItemFinWhy> colCntnrFinWhy;
    private CollectionLoader<UsrItemFinWhy> colLoadrFinWhy;

    private CollectionContainer<UsrItemGenFmla> colCntnrAmtCalcGenFmla;
    private CollectionLoader<UsrItemGenFmla> colLoadrAmtCalcGenFmla;

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
    private ComboBox<String> finTxact1_EI1_RoleField;

    @Autowired
    private EntityComboBox<UsrNodeGenChan> genChan1_IdField;

    @Autowired
    private EntityComboBox<UsrItemFinHow> finHow1_IdField;

    @Autowired
    private ComboBox<String> whatText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhat> finWhat1_IdField;

    @Autowired
    private ComboBox<String> whyText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhy> finWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> desc1Node1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> genTag1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> genTag2_IdField;


    @Autowired
    private EntityComboBox<UsrNodeFinStmtItm> finStmtItm1_IdField;

    @Autowired
    private Table<UsrNodeFinStmtItm> tableFinStmtItm1_IdCandids;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc1Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc2Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Id_Desc3Field;


    @Autowired
    private EntityComboBox<UsrNodeFinTaxLne> finTaxLne1_IdField;

    @Autowired
    private ComboBox<String> finTaxLne1_CodeField;

    @Autowired
    private EntityComboBox<UsrNodeFinAcct> finAcct1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinDept> finDept1_IdField;

    @Autowired
    private EntityComboBox<SysNodeFinCurcy> sysNodeFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> amtFinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> amtCalcGenFmla1_IdField;


    //Field (FinTxact)
    @Autowired
    private EntityComboBox<UsrNodeFinTxact> finTxact1_IdField;

    @Autowired
    private Table<UsrNodeFinTxact> tableFinTxact1_IdCandids;

    @Autowired
    private EntityComboBox<UsrNodeFinTxactType> finTxact1_Id_Type1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxactSet1_EI1_RoleField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> finTxact1_Id_Desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finTxact1_Id_Desc1Node1_IdField;


    //Field (FinTxact.FinTxactSet)
    @Autowired
    private EntityComboBox<UsrNodeFinTxactSet> finTxact1_Id_FinTxactSet1_IdField;

    @Autowired
    private Table<UsrNodeFinTxactSet> tableFinTxact1_FinTxactSet1_IdCandids;

    @Autowired
    private EntityComboBox<UsrNodeFinTxactSetType> finTxact1_Id_FinTxactSet1_Id_Type1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenChan> finTxact1_Id_FinTxactSet1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenChan> finTxact1_Id_FinTxactSet1_Id_GenChan2_IdField;

    @Autowired
    private EntityComboBox<UsrItemFinHow> finTxact1_Id_FinTxactSet1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxactSet1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhat> finTxact1_Id_FinTxactSet1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxactSet1_Id_WhyText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhy> finTxact1_Id_FinTxactSet1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> finTxact1_Id_FinTxactSet1_Id_Desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finTxact1_Id_FinTxactSet1_Id_Desc1Node1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finTxact1_Id_FinTxactSet1_Id_Desc1Node2_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> finTxact1_Id_FinTxactSet1_Id_GenTag1_IdField;



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
        ComboBox<String> propFilterCmpnt_FinTxact1_EI1_Role;
        propFilterCmpnt_FinTxact1_EI1_Role = (ComboBox<String>) filterConfig1A_FinTxact1_EI1_Role.getValueComponent();
        propFilterCmpnt_FinTxact1_EI1_Role.setNullOptionVisible(true);
        propFilterCmpnt_FinTxact1_EI1_Role.setNullSelectionCaption("<null>");
        propFilterCmpnt_FinTxact1_EI1_Role = (ComboBox<String>) filterConfig1B_FinTxact1_EI1_Role.getValueComponent();
        propFilterCmpnt_FinTxact1_EI1_Role.setNullOptionVisible(true);
        propFilterCmpnt_FinTxact1_EI1_Role.setNullSelectionCaption("<null>");

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

        tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleField.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleField.setNullSelectionCaption("<null>");
        tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1Field.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1Field.setNullSelectionCaption("<null>");
        tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1Field.setNullOptionVisible(true);
        tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1Field.setNullSelectionCaption("<null>");

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


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrNodeFinTxactItmType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactItm = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnFinTxactItm);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //filter
        EntityComboBox<UsrNodeBaseType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id= (EntityComboBox<UsrNodeBaseType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeBaseType>) filterConfig1B_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrNodeGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrNodeGenChan e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenChan = fetchPlans.builder(UsrNodeGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(fchPlnGenChan);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(colCntnrGenChan);
        finTxact1_Id_FinTxactSet1_Id_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        finTxact1_Id_FinTxactSet1_Id_GenChan2_IdField.setOptionsContainer(colCntnrGenChan);
        //template
        tmplt_FinTxact1_Id_FinTxactSet1_Id_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        tmplt_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        //filter
        EntityComboBox<UsrNodeGenChan> propFilterCmpnt_GenChan1_Id;
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);


        colCntnrFinHow = dataComponents.createCollectionContainer(UsrItemFinHow.class);
        colLoadrFinHow = dataComponents.createCollectionLoader();
        colLoadrFinHow.setQuery("select e from enty_UsrItemFinHow e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinHow = fetchPlans.builder(UsrItemFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinHow.setFetchPlan(fchPlnFinHow);
        colLoadrFinHow.setContainer(colCntnrFinHow);
        colLoadrFinHow.setDataContext(getScreenData().getDataContext());

        finHow1_IdField.setOptionsContainer(colCntnrFinHow);
        finTxact1_Id_FinTxactSet1_Id_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        //template
        tmplt_FinTxact1_Id_FinTxactSet1_Id_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        tmplt_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        //filter
        EntityComboBox<UsrItemFinHow> propFilterCmpnt_FinHow1_Id;
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(colCntnrFinHow);
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(colCntnrFinHow);


        colCntnrFinWhat = dataComponents.createCollectionContainer(UsrItemFinWhat.class);
        colLoadrFinWhat = dataComponents.createCollectionLoader();
        colLoadrFinWhat.setQuery("select e from enty_UsrItemFinWhat e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhat = fetchPlans.builder(UsrItemFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhat.setFetchPlan(fchPlnFinWhat);
        colLoadrFinWhat.setContainer(colCntnrFinWhat);
        colLoadrFinWhat.setDataContext(getScreenData().getDataContext());

        finWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        finTxact1_Id_FinTxactSet1_Id_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        //template
        tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        tmplt_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        //filter
        EntityComboBox<UsrItemFinWhat> propFilterCmpnt_FinWhat1_Id;
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(colCntnrFinWhat);
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(colCntnrFinWhat);


        colCntnrFinWhy = dataComponents.createCollectionContainer(UsrItemFinWhy.class);
        colLoadrFinWhy = dataComponents.createCollectionLoader();
        colLoadrFinWhy.setQuery("select e from enty_UsrItemFinWhy e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhy = fetchPlans.builder(UsrItemFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhy.setFetchPlan(fchPlnFinWhy);
        colLoadrFinWhy.setContainer(colCntnrFinWhy);
        colLoadrFinWhy.setDataContext(getScreenData().getDataContext());

        finWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        finTxact1_Id_FinTxactSet1_Id_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        //template
        tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        tmplt_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        //filter
        EntityComboBox<UsrItemFinWhy> propFilterCmpnt_FinWhy1_Id;
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(colCntnrFinWhy);
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(colCntnrFinWhy);


        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrItemGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrItemGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla = fetchPlans.builder(UsrItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());

        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        finTxact1_Id_Desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        finTxact1_Id_FinTxactSet1_Id_Desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);

        
        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrNodeGenDocVer.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrNodeGenDocVer e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenDocVer = fetchPlans.builder(UsrNodeGenDocVer.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(fchPlnGenDocVer);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);
        
        //filter
        EntityComboBox<UsrNodeGenDocVer> propFilterCmpnt_GenDocVer1_Id;
        propFilterCmpnt_GenDocVer1_Id = (EntityComboBox<UsrNodeGenDocVer>) filterConfig1A_GenDocVer1_Id.getValueComponent();
        propFilterCmpnt_GenDocVer1_Id.setOptionsContainer(colCntnrGenDocVer);
        propFilterCmpnt_GenDocVer1_Id = (EntityComboBox<UsrNodeGenDocVer>) filterConfig1B_GenDocVer1_Id.getValueComponent();
        propFilterCmpnt_GenDocVer1_Id.setOptionsContainer(colCntnrGenDocVer);

                
        colCntnrGenTag = dataComponents.createCollectionContainer(UsrItemGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrItemGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag = fetchPlans.builder(UsrItemGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);
        genTag2_IdField.setOptionsContainer(colCntnrGenTag);
        finTxact1_Id_FinTxactSet1_Id_GenTag1_IdField.setOptionsContainer(colCntnrGenTag);

        //filter
        EntityComboBox<UsrItemGenTag> propFilterCmpnt_GenTag1_Id;
        propFilterCmpnt_GenTag1_Id = (EntityComboBox<UsrItemGenTag>) filterConfig1A_GenTag1_Id.getValueComponent();
        propFilterCmpnt_GenTag1_Id.setOptionsContainer(colCntnrGenTag);
        propFilterCmpnt_GenTag1_Id = (EntityComboBox<UsrItemGenTag>) filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_GenTag1_Id.getValueComponent();
        propFilterCmpnt_GenTag1_Id.setOptionsContainer(colCntnrGenTag);
        propFilterCmpnt_GenTag1_Id = (EntityComboBox<UsrItemGenTag>) filterConfig1B_GenTag1_Id.getValueComponent();
        propFilterCmpnt_GenTag1_Id.setOptionsContainer(colCntnrGenTag);
        propFilterCmpnt_GenTag1_Id = (EntityComboBox<UsrItemGenTag>) filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_GenTag1_Id.getValueComponent();
        propFilterCmpnt_GenTag1_Id.setOptionsContainer(colCntnrGenTag);


        colCntnrFinTxact = dataComponents.createCollectionContainer(UsrNodeFinTxact.class);
        colLoadrFinTxact = dataComponents.createCollectionLoader();
        colLoadrFinTxact.setQuery("select e from enty_UsrNodeFinTxact e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxact = fetchPlans.builder(UsrNodeFinTxact.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxact.setFetchPlan(fchPlnFinTxact);
        colLoadrFinTxact.setContainer(colCntnrFinTxact);
        colLoadrFinTxact.setDataContext(getScreenData().getDataContext());

        finTxact1_IdField.setOptionsContainer(colCntnrFinTxact);


        colCntnrFinTxactType = dataComponents.createCollectionContainer(UsrNodeFinTxactType.class);
        colLoadrFinTxactType = dataComponents.createCollectionLoader();
        colLoadrFinTxactType.setQuery("select e from enty_UsrNodeFinTxactType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactType = fetchPlans.builder(UsrNodeFinTxactType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactType.setFetchPlan(fchPlnFinTxactType);
        colLoadrFinTxactType.setContainer(colCntnrFinTxactType);
        colLoadrFinTxactType.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_Type1_IdField.setOptionsContainer(colCntnrFinTxactType);
        //template
        tmplt_FinTxact1_Id_Type1_IdField.setOptionsContainer(colCntnrFinTxactType);


        colContnrFinTxact1_IdCandids = dataComponents.createCollectionContainer(UsrNodeFinTxact.class);
        colLoadrFinTxact1_IdCandids = dataComponents.createCollectionLoader();
        colLoadrFinTxact1_IdCandids.setQuery("select e from enty_UsrNodeFinTxact e where e.id is null order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactCandid = fetchPlans.builder(UsrNodeFinTxact.class)
                .addFetchPlan("fetchPlan_UsrNodeFinTxact_Base")
                .build();
        colLoadrFinTxact1_IdCandids.setFetchPlan(fchPlnFinTxactCandid);
        colLoadrFinTxact1_IdCandids.setContainer(colContnrFinTxact1_IdCandids);
        colLoadrFinTxact1_IdCandids.setDataContext(getScreenData().getDataContext());

        tableFinTxact1_IdCandids.setItems(new ContainerTableItems<>(colContnrFinTxact1_IdCandids));

        
        colCntnrFinTxactSet = dataComponents.createCollectionContainer(UsrNodeFinTxactSet.class);
        colLoadrFinTxactSet = dataComponents.createCollectionLoader();
        colLoadrFinTxactSet.setQuery("select e from enty_UsrNodeFinTxactSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSet = fetchPlans.builder(UsrNodeFinTxactSet.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactSet.setFetchPlan(fchPlnFinTxactSet);
        colLoadrFinTxactSet.setContainer(colCntnrFinTxactSet);
        colLoadrFinTxactSet.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxactSet1_IdField.setOptionsContainer(colCntnrFinTxactSet);


        colCntnrFinTxactSetType = dataComponents.createCollectionContainer(UsrNodeFinTxactSetType.class);
        colLoadrFinTxactSetType = dataComponents.createCollectionLoader();
        colLoadrFinTxactSetType.setQuery("select e from enty_UsrNodeFinTxactSetType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSetType = fetchPlans.builder(UsrNodeFinTxactSetType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactSetType.setFetchPlan(fchPlnFinTxactSetType);
        colLoadrFinTxactSetType.setContainer(colCntnrFinTxactSetType);
        colLoadrFinTxactSetType.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxactSet1_Id_Type1_IdField.setOptionsContainer(colCntnrFinTxactSetType);
        //template
        tmplt_FinTxact1_Id_FinTxactSet1_Id_Type1_IdField.setOptionsContainer(colCntnrFinTxactSetType);
        //filter
        EntityComboBox<UsrNodeFinTxactSetType> propFilterCmpnt_FinTxact1_Id_FinTxactSet1_Id_Type1_Id;
        propFilterCmpnt_FinTxact1_Id_FinTxactSet1_Id_Type1_Id = (EntityComboBox<UsrNodeFinTxactSetType>) filterConfig1A_FinTxact1_Id_FinTxactSet1_Id_Type1_Id.getValueComponent();
        propFilterCmpnt_FinTxact1_Id_FinTxactSet1_Id_Type1_Id.setOptionsContainer(colCntnrFinTxactSetType);
        propFilterCmpnt_FinTxact1_Id_FinTxactSet1_Id_Type1_Id = (EntityComboBox<UsrNodeFinTxactSetType>) filterConfig1B_FinTxact1_Id_FinTxactSet1_Id_type1_Id.getValueComponent();
        propFilterCmpnt_FinTxact1_Id_FinTxactSet1_Id_Type1_Id.setOptionsContainer(colCntnrFinTxactSetType);


        colCntnrFinTxactSet1_IdCandids = dataComponents.createCollectionContainer(UsrNodeFinTxactSet.class);
        colLoadrFinTxactSet1_IdCandids = dataComponents.createCollectionLoader();
        colLoadrFinTxactSet1_IdCandids.setQuery("select e from enty_UsrNodeFinTxactSet e where e.id is null order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSetCandid = fetchPlans.builder(UsrNodeFinTxactSet.class)
                .addFetchPlan("fetchPlan_UsrNodeFinTxactSet_Base")
                .build();
        colLoadrFinTxactSet1_IdCandids.setFetchPlan(fchPlnFinTxactSetCandid);
        colLoadrFinTxactSet1_IdCandids.setContainer(colCntnrFinTxactSet1_IdCandids);
        colLoadrFinTxactSet1_IdCandids.setDataContext(getScreenData().getDataContext());

        tableFinTxact1_FinTxactSet1_IdCandids.setItems(new ContainerTableItems<>(colCntnrFinTxactSet1_IdCandids));

        
        colCntnrFinStmt = dataComponents.createCollectionContainer(UsrNodeFinStmt.class);
        colLoadrFinStmt = dataComponents.createCollectionLoader();
        colLoadrFinStmt.setQuery("select e from enty_UsrNodeFinStmt e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinStmt = fetchPlans.builder(UsrNodeFinStmt.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinStmt.setFetchPlan(fchPlnFinStmt);
        colLoadrFinStmt.setContainer(colCntnrFinStmt);
        colLoadrFinStmt.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<UsrNodeFinStmt> propFilterCmpnt_FinStmt1_Id;
        propFilterCmpnt_FinStmt1_Id = (EntityComboBox<UsrNodeFinStmt>) filterConfig1A_FinStmtItm1_Id_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_FinStmt1_Id.setOptionsContainer(colCntnrFinStmt);
        propFilterCmpnt_FinStmt1_Id = (EntityComboBox<UsrNodeFinStmt>) filterConfig1B_FinStmtItm1_Id_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_FinStmt1_Id.setOptionsContainer(colCntnrFinStmt);


        colCntnrFinStmtItm = dataComponents.createCollectionContainer(UsrNodeFinStmtItm.class);
        colLoadrFinStmtItm = dataComponents.createCollectionLoader();
        colLoadrFinStmtItm.setQuery("select e from enty_UsrNodeFinStmtItm e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinStmtItm = fetchPlans.builder(UsrNodeFinStmtItm.class)
                .addFetchPlan("fetchPlan_UsrNodeFinStmtItm_Base")
                .build();
        colLoadrFinStmtItm.setFetchPlan(fchPlnFinStmtItm);
        colLoadrFinStmtItm.setContainer(colCntnrFinStmtItm);
        colLoadrFinStmtItm.setDataContext(getScreenData().getDataContext());

        finStmtItm1_IdField.setOptionsContainer(colCntnrFinStmtItm);
        //template
        tmplt_FinStmtItm1_IdField.setOptionsContainer(colCntnrFinStmtItm);


        colCntnrFinStmtItm1_IdCandids = dataComponents.createCollectionContainer(UsrNodeFinStmtItm.class);
        colLoadrFinStmtItm1_IdCandids = dataComponents.createCollectionLoader();
        colLoadrFinStmtItm1_IdCandids.setQuery("select e from enty_UsrNodeFinStmtItm e where e.id is null order by e.sortKey, e.id2");
        FetchPlan fchPlnFinStmtItmCandid = fetchPlans.builder(UsrNodeFinStmtItm.class)
                .addFetchPlan("fetchPlan_UsrNodeFinStmtItm_Base")
                .build();
        colLoadrFinStmtItm1_IdCandids.setFetchPlan(fchPlnFinStmtItmCandid);
        colLoadrFinStmtItm1_IdCandids.setContainer(colCntnrFinStmtItm1_IdCandids);
        colLoadrFinStmtItm1_IdCandids.setDataContext(getScreenData().getDataContext());

        tableFinStmtItm1_IdCandids.setItems(new ContainerTableItems<>(colCntnrFinStmtItm1_IdCandids));


        colCntnrFinTaxLne = dataComponents.createCollectionContainer(UsrNodeFinTaxLne.class);
        colLoadrFinTaxLne = dataComponents.createCollectionLoader();
        colLoadrFinTaxLne.setQuery("select e from enty_UsrNodeFinTaxLne e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTaxLne = fetchPlans.builder(UsrNodeFinTaxLne.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTaxLne.setFetchPlan(fchPlnFinTaxLne);
        colLoadrFinTaxLne.setContainer(colCntnrFinTaxLne);
        colLoadrFinTaxLne.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(colCntnrFinTaxLne);
        //template
        tmplt_FinTaxLne1_IdField.setOptionsContainer(colCntnrFinTaxLne);


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNodeFinAcct.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrNodeFinAcct e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcct = fetchPlans.builder(UsrNodeFinAcct.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(fchPlnFinAcct);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(colCntnrFinAcct);
        //template
        tmplt_FinAcct1_IdField.setOptionsContainer(colCntnrFinAcct);
        //filter
        EntityComboBox<UsrNodeFinAcct> propFilterCmpnt_FinAcct1_Id;
        propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNodeFinAcct>) filterConfig1A_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setOptionsContainer(colCntnrFinAcct);
        propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNodeFinAcct>) filterConfig1B_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setOptionsContainer(colCntnrFinAcct);


        colCntnrFinDept = dataComponents.createCollectionContainer(UsrNodeFinDept.class);
        colLoadrFinDept = dataComponents.createCollectionLoader();
        colLoadrFinDept.setQuery("select e from enty_UsrNodeFinDept e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinDept = fetchPlans.builder(UsrNodeFinDept.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinDept.setFetchPlan(fchPlnFinDept);
        colLoadrFinDept.setContainer(colCntnrFinDept);
        colLoadrFinDept.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(colCntnrFinDept);
        //template
        tmplt_FinDept1_IdField.setOptionsContainer(colCntnrFinDept);
        //filter
        EntityComboBox<UsrNodeFinDept> propFilterCmpnt_FinDept1_Id;
        propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNodeFinDept>) filterConfig1A_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setOptionsContainer(colCntnrFinDept);
        propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNodeFinDept>) filterConfig1B_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setOptionsContainer(colCntnrFinDept);


        colCntnrSysNodeFinCurcy = dataComponents.createCollectionContainer(SysNodeFinCurcy.class);
        colLoadrSysNodeFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysNodeFinCurcy.setQuery("select e from enty_SysNodeFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnSysNodeFinCurcy = fetchPlans.builder(SysNodeFinCurcy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysNodeFinCurcy.setFetchPlan(fchPlnSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setContainer(colCntnrSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setDataContext(getScreenData().getDataContext());

        sysNodeFinCurcy1_IdField.setOptionsContainer(colCntnrSysNodeFinCurcy);
        //template
        tmplt_SysNodeFinCurcy1_IdField.setOptionsContainer(colCntnrSysNodeFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_SysNodeFinCurcy1_Id;
        propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setOptionsContainer(colCntnrSysNodeFinCurcy);
        propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1B_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setOptionsContainer(colCntnrSysNodeFinCurcy);


        colCntnrFinTxactItm1 = dataComponents.createCollectionContainer(UsrNodeFinTxactItm.class);
        colLoadrFinTxactItm1 = dataComponents.createCollectionLoader();
        colLoadrFinTxactItm1.setQuery("select e from enty_UsrNodeFinTxactItm e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactItm1 = fetchPlans.builder(UsrNodeFinTxactItm.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactItm1.setFetchPlan(fchPlnFinTxactItm1);
        colLoadrFinTxactItm1.setContainer(colCntnrFinTxactItm1);
        colLoadrFinTxactItm1.setDataContext(getScreenData().getDataContext());

        desc1Node1_IdField.setOptionsContainer((CollectionContainer<UsrNodeBase>)(Object)colCntnrFinTxactItm1);
        finTxact1_Id_Desc1Node1_IdField.setOptionsContainer((CollectionContainer<UsrNodeBase>)(Object)colCntnrFinTxactItm1);
        finTxact1_Id_FinTxactSet1_Id_Desc1Node1_IdField.setOptionsContainer((CollectionContainer<UsrNodeBase>)(Object)colCntnrFinTxactItm1);
        finTxact1_Id_FinTxactSet1_Id_Desc1Node2_IdField.setOptionsContainer((CollectionContainer<UsrNodeBase>)(Object)colCntnrFinTxactItm1);
        amtFinTxactItm1_IdField.setOptionsContainer((CollectionContainer<UsrNodeBase>)(Object)colCntnrFinTxactItm1);


        colCntnrAmtCalcGenFmla = dataComponents.createCollectionContainer(UsrItemGenFmla.class);
        colLoadrAmtCalcGenFmla = dataComponents.createCollectionLoader();
        colLoadrAmtCalcGenFmla.setQuery("select e from enty_UsrItemGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnAmtCalcGenFmla = fetchPlans.builder(UsrItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrAmtCalcGenFmla.setFetchPlan(fchPlnAmtCalcGenFmla);
        colLoadrAmtCalcGenFmla.setContainer(colCntnrAmtCalcGenFmla);
        colLoadrAmtCalcGenFmla.setDataContext(getScreenData().getDataContext());

        amtCalcGenFmla1_IdField.setOptionsContainer(colCntnrAmtCalcGenFmla);
        //template
        tmplt_AmtCalcGenFmla1_IdField.setOptionsContainer(colCntnrAmtCalcGenFmla);


        logger.trace(logPrfx + " <-- ");


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
    public void onInitEntity(InitEntityEvent<UsrNodeBase> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = event.getEntity();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItm.setClassName("UsrNodeFinTxactItm");
        logger.debug(logPrfx + " --- className: UsrNodeFinTxactItm");

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
    public void onFinTxactItmsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinTxactItmsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = event.getItem();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItm.setClassName("UsrNodeFinTxactItm");
        logger.debug(logPrfx + " --- className: UsrNodeFinTxactItm");

/*
        updateFinTxact1_IdCandidsTable(thisFinTxactItm);
        updateFinTxact1_Id_FinTxactSet1_IdCandidsTable(thisFinTxactItm);
        updateFinStmtItm1_IdCandidsTable(thisFinTxactItm);
*/

        logger.trace(logPrfx + " <-- ");

    }


    @Install(to = "tableMain.[instDt1.elDt]", subject = "formatter")
    private String tableMainInstDt1ElDtFormatter(LocalDate dt) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return dt == null ? null : dt.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        reloadFinTxact1_Id_FinTxactSet1_EI1_RoleList();

        colLoadrFinTxactType.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactType.load() ");

        reloadFinTxact1_EI1_RoleList();

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


        colLoadrGenDocVer.load();
        logger.debug(logPrfx + " --- called colLoadrGenDocVer.load() ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        colLoadrFinStmtItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmtItm.load() ");

        colLoadrFinStmt.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmt.load() ");
        reloadFinStmtItm1_Id_Desc1List();
        reloadFinStmtItm1_Id_Desc2List();
        reloadFinStmtItm1_Id_Desc3List();

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");
        reloadFinTaxLne1_CodeList();

        colLoadrAmtCalcGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrAmtCalcGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing qryMngr.execPrUpdAllCalcValsforAllRowsNative()");
        qryMngr.execPrUpdAllCalcValsforAllRowsNative();
        logger.debug(logPrfx + " --- finished qryMngr.execPrUpdAllCalcValsforAllRowsNative()");

        logger.debug(logPrfx + " --- calling colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deleteColDeletedColBtn")
    public void onDeleteColDeletedColBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColDeletedColBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing qryMngr.execPrDelDeletedNative()");
        qryMngr.execPrDelDeletedNative();
        logger.debug(logPrfx + " --- finished qryMngr.execPrDelDeletedNative()");

        logger.debug(logPrfx + " --- executing colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("splitBtn")
    public void onSplitBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSplitBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeBase> sels = new ArrayList<>();

        thisFinTxactItms.forEach(orig -> {

            UsrNodeBase copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (Objects.equals(orig.getId2(), copy.getId2())) {
                copy.setNm1s1Inst1Int3(copy.getNm1s1Inst1Int3() == null ? 1 : copy.getNm1s1Inst1Int3() + 1);
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

            if (tmplt_FinAcct1_IdFieldChk.isChecked()) {
                copy.setFinAcct1_Id(tmplt_FinAcct1_IdField.getValue());
            }

            if (tmplt_FinDept1_IdFieldChk.isChecked()) {
                copy.setFinDept1_Id(tmplt_FinDept1_IdField.getValue());
            }

            if (tmplt_SysNodeFinCurcy1_IdFieldChk.isChecked()) {
                copy.setSysNodeFinCurcy1_Id(tmplt_SysNodeFinCurcy1_IdField.getValue());
            }

            Integer finTxactOption = Optional.ofNullable(updateColItemCalcValsTxactOption.getValue()).orElse(0);
            Integer finTxactSetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
            Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);

            updateCalcVals(copy, finTxactOption, finTxactSetOption, finStmtItmOption);

            UsrNodeBase savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Split FinTxactItm " + copy.getId2() + " "
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

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeBase> sels = new ArrayList<>();

        thisFinTxactItms.forEach(orig -> {
            UsrNodeBase copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_FinTxact1_EI1_RoleFieldChk.isChecked()) {
                copy.setFinTxact1_EI1_Role(tmplt_FinTxact1_EI1_RoleField.getValue());
            }

            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                copy.getTs1().setElTs(tmplt_Ts1ElTsField.getValue());
                updateIdDt(copy);
            }
            if (tmplt_Ts2ElTsFieldChk.isChecked()) {
                copy.getTs2().setElTs(tmplt_Ts2ElTsField.getValue());
                updateIdDt(copy);
            }

            LocalDate idDt1 = copy.getNm1s1Inst1Dt1() != null ? copy.getNm1s1Inst1Dt1().getElDt() : null;

            Integer idX = copy.getNm1s1Inst1Int1();
            if (tmplt_IdXFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdXFieldRdo.getValue() == 1) {
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

            Integer idZ = copy.getNm1s1Inst1Int3();
            if (tmplt_IdZFieldRdo.getValue() != null) {
                // Set
                if (tmplt_IdZFieldRdo.getValue() == 1) {
                    idZ = tmplt_IdZField.getValue();
                    copy.setNm1s1Inst1Int3(idZ);
                }
                // Max
                else if (tmplt_IdZFieldRdo.getValue() == 2
                        && idDt1 != null) {

                    idZ = getIdZMax(idDt1, idX, idY);
                    if (idZ == null) return;
                    copy.setNm1s1Inst1Int3(idZ);
                }
            }

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            if (tmplt_FinAcct1_IdFieldChk.isChecked()) {
                copy.setFinAcct1_Id(tmplt_FinAcct1_IdField.getValue());
            }

            if (tmplt_FinDept1_IdFieldChk.isChecked()) {
                copy.setFinDept1_Id(tmplt_FinDept1_IdField.getValue());
            }

            if (tmplt_AmtDebtFieldChk.isChecked()) {
                copy.setAmtDebt(tmplt_AmtDebtField.getValue());
            }

            if (tmplt_AmtCredFieldChk.isChecked()) {
                copy.setAmtCred(tmplt_AmtCredField.getValue());
            }

            if (tmplt_SysNodeFinCurcy1_IdFieldChk.isChecked()) {
                copy.setSysNodeFinCurcy1_Id(tmplt_SysNodeFinCurcy1_IdField.getValue());
            }

            if (tmplt_AmtCalcGenFmla1_IdFieldChk.isChecked()) {
                copy.setAmtCalcGenFmla1_Id(tmplt_AmtCalcGenFmla1_IdField.getValue());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (!Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setNm1s1Inst1Int3(copy.getNm1s1Inst1Int3() == null ? 1 : copy.getNm1s1Inst1Int3() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }
            Integer finTxactOption = Optional.ofNullable(updateColItemCalcValsTxactOption.getValue()).orElse(0);
            Integer finTxactSetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
            Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);

            updateCalcVals(copy, finTxactOption, finTxactSetOption, finStmtItmOption);

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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxactItms.forEach(thisFinTxactItm -> {
            UsrNodeBase thisTrackedFinTxactItm = dataContext.merge(thisFinTxactItm);
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

                if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.getTs1().setElTs(tmplt_Ts1ElTsField.getValue());
                    updateIdDt(thisFinTxactItm);
                }
                if (tmplt_Ts2ElTsFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.getTs2().setElTs(tmplt_Ts2ElTsField.getValue());
                    updateIdDt(thisFinTxactItm);
                }
                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;

                Integer idX = thisFinTxactItm.getNm1s1Inst1Int1();
                if (tmplt_IdXFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1) {
                        thisFinTxactItmIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinTxactItm.setNm1s1Inst1Int1(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactItmIsChanged = true;
                        idX = getIdXMax(idDt1);
                        if (idX == null) return;
                        thisFinTxactItm.setNm1s1Inst1Int1(idX);
                    }
                }

                Integer idY = thisFinTxactItm.getNm1s1Inst1Int2();
                if (tmplt_IdYFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdYFieldRdo.getValue() == 1) {
                        thisFinTxactItmIsChanged = true;
                        idY = tmplt_IdYField.getValue();
                        thisFinTxactItm.setNm1s1Inst1Int2(idY);
                    }
                    // Max
                    else if (tmplt_IdYFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactItmIsChanged = true;
                        idY = getIdYMax(idDt1, idX);
                        if (idY == null) return;
                        thisFinTxactItm.setNm1s1Inst1Int2(idY);
                    }
                }

                Integer idZ = thisFinTxactItm.getNm1s1Inst1Int3();
                if (tmplt_IdZFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdZFieldRdo.getValue() == 1) {
                        thisFinTxactItmIsChanged = true;
                        idZ = tmplt_IdZField.getValue();
                        thisFinTxactItm.setNm1s1Inst1Int3(idZ);
                    }
                    // Max
                    else if (tmplt_IdZFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinTxactItmIsChanged = true;
                        idZ = getIdZMax(idDt1, idX, idY);
                        if (idZ == null) return;
                        thisFinTxactItm.setNm1s1Inst1Int3(idZ);
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


                if (tmplt_FinAcct1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinAcct1_Id(tmplt_FinAcct1_IdField.getValue());
                }

                if (tmplt_FinDept1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setFinDept1_Id(tmplt_FinDept1_IdField.getValue());
                }

                if (tmplt_AmtDebtFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setAmtDebt(tmplt_AmtDebtField.getValue());
                }

                if (tmplt_AmtCredFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setAmtCred(tmplt_AmtCredField.getValue());
                }
                
                if (tmplt_SysNodeFinCurcy1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setSysNodeFinCurcy1_Id(tmplt_SysNodeFinCurcy1_IdField.getValue());
                }

                if (tmplt_AmtCalcGenFmla1_IdFieldChk.isChecked()) {
                    thisFinTxactItmIsChanged = true;
                    thisFinTxactItm.setAmtCalcGenFmla1_Id(tmplt_AmtCalcGenFmla1_IdField.getValue());
                }

                thisFinTxactItmIsChanged = updateId2Calc(thisFinTxactItm) || thisFinTxactItmIsChanged;
                thisFinTxactItmIsChanged = updateId2(thisFinTxactItm) || thisFinTxactItmIsChanged;
                thisFinTxactItmIsChanged = updateId2Cmp(thisFinTxactItm) || thisFinTxactItmIsChanged;

                if (thisFinTxactItmIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactItm).");
                    //dataManager.save(thisFinTxactItm);
                }

                UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
                if (thisFinTxact != null) {
                    thisFinTxact = dataContext.merge(thisFinTxact);

                    boolean thisFinTxactIsChanged = false;

                    if (tmplt_FinTxact1_Id_Type1_IdFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setType1_Id(tmplt_FinTxact1_Id_Type1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleFieldChk.isChecked()) {
                        thisFinTxactIsChanged = true;
                        thisFinTxact.setFinTxactSet1_EI1_Role(tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleField.getValue());
                    }


                    if (thisFinTxactIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                        //dataManager.save(thisFinTxact);
                    }
                }

                UsrNodeBase thisFinTxactSet = thisFinTxactItm.getFinTxact1_Id() == null ? null : thisFinTxactItm.getFinTxact1_Id().getFinTxactSet1_Id();
                if (thisFinTxactSet != null) {
                    thisFinTxactSet = dataContext.merge(thisFinTxactSet);

                    boolean thisFinTxactSetIsChanged = false;

                    if (tmplt_FinTxact1_Id_FinTxactSet1_Id_Type1_IdFieldChk.isChecked()) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setType1_Id(tmplt_FinTxact1_Id_FinTxactSet1_Id_Type1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxactSet1_Id_GenChan1_IdFieldChk.isChecked()) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setGenChan1_Id(tmplt_FinTxact1_Id_FinTxactSet1_Id_GenChan1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxactSet1_Id_FinHow1_IdFieldChk.isChecked()) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setFinHow1_Id(tmplt_FinTxact1_Id_FinTxactSet1_Id_FinHow1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1FieldChk.isChecked()) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setWhatText1(tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1Field.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_IdFieldChk.isChecked()) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setFinWhat1_Id(tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhat1_IdField.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1FieldChk.isChecked()) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setWhyText1(tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1Field.getValue());
                    }

                    if (tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_IdFieldChk.isChecked()) {
                        thisFinTxactSetIsChanged = true;
                        thisFinTxactSet.setFinWhy1_Id(tmplt_FinTxact1_Id_FinTxactSet1_Id_FinWhy1_IdField.getValue());
                    }

                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinTxactItm,thisFinTxactItm).");
                    //metadataTools.copy(thisFinTxactItm, thisFinTxactItm);

                    //logger.debug(logPrfx + " --- executing dataContext.commit().");
                    //dataContext.commit();

                    if (thisFinTxactSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                        //dataManager.save(thisFinTxactSet);
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
    
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
    
            //Loop throught the items again to update the id2Dup attribute
            thisFinTxactItms.forEach(thisFinTxactItm -> {
                //UsrNodeFinTxactItm thisTrackedFinTxactItm = dataContext.merge(thisFinTxactItm);
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
    
                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();
    
                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisFinTxactItms);
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
        }
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
        switch (idDtGEOption) {
            case 0 -> // Clear
                    filterConfig1B_IdDtGE.clear();
            case 1 -> {  // Set to match current row
                List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
                if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeBase thisFinTxactItm = thisFinTxactItms.get(0);
                if (thisFinTxactItm != null) {
                    if (thisFinTxactItm.getNm1s1Inst1Dt1() != null && thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() != null) {
                        filterConfig1B_IdDtGE.setValue(thisFinTxactItm.getNm1s1Inst1Dt1().getElDt());
                        filterConfig1B_IdDtGE.apply();
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
                    filterConfig1B_IdX.clear();
            case 1 -> {  // Set to match current row
                List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
                if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeBase thisFinTxactItm = thisFinTxactItms.get(0);
                if (thisFinTxactItm != null) {
                    if (thisFinTxactItm.getNm1s1Inst1Int1() != null) {
                        filterConfig1B_IdX.setValue(thisFinTxactItm.getNm1s1Inst1Int1());
                        filterConfig1B_IdX.apply();
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
                    filterConfig1B_IdY.clear();
            case 1 -> {  // Set to match current row
                List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
                if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeBase thisFinTxactItm = thisFinTxactItms.get(0);
                if (thisFinTxactItm != null) {
                    if (thisFinTxactItm.getNm1s1Inst1Int2() != null) {
                        filterConfig1B_IdY.setValue(thisFinTxactItm.getNm1s1Inst1Int2());
                        filterConfig1B_IdY.apply();
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactItm.getNm1s1Inst1Int1();
                    Integer idX = 0;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactItm.getNm1s1Inst1Int1();
                    Integer idX = thisFinTxactItm.getNm1s1Inst1Int1() == null || thisFinTxactItm.getNm1s1Inst1Int1() == 0 || thisFinTxactItm.getNm1s1Inst1Int1() == 1 ? 0 : thisFinTxactItm.getNm1s1Inst1Int1() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinTxactItm.getNm1s1Inst1Int1();
                    Integer idX = (thisFinTxactItm.getNm1s1Inst1Int1() == null ? 0 : thisFinTxactItm.getNm1s1Inst1Int1()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX)"
                            + " from enty_UsrNodeFinTxactItm e"
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

                    Integer idX_ = thisFinTxactItm.getNm1s1Inst1Int1();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinTxactItm.setNm1s1Inst1Int1(idX);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxactItm.getNm1s1Inst1Int2();
                    Integer idY = 0;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxactItm.getNm1s1Inst1Int2();
                    Integer idY = thisFinTxactItm.getNm1s1Inst1Int2() == null || thisFinTxactItm.getNm1s1Inst1Int2() == 0 || thisFinTxactItm.getNm1s1Inst1Int2() == 1 ? 0 : thisFinTxactItm.getNm1s1Inst1Int2() - 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idY_ = thisFinTxactItm.getNm1s1Inst1Int2();
                    Integer idY = (thisFinTxactItm.getNm1s1Inst1Int2() == null ? 0 : thisFinTxactItm.getNm1s1Inst1Int2()) + 1;
                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY)"
                            + " from enty_UsrNodeFinTxactItm e"
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

                    Integer idY_ = thisFinTxactItm.getNm1s1Inst1Int2();
                    Integer idY = idYMax == null ? 0 : idYMax;

                    if (!Objects.equals(idY_, idY)){
                        thisFinTxactItm.setNm1s1Inst1Int2(idY);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idZ_ = thisFinTxactItm.getNm1s1Inst1Int3();
                    Integer idZ = 0;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setNm1s1Inst1Int3(idZ);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idZ_ = thisFinTxactItm.getNm1s1Inst1Int3();
                    Integer idZ = thisFinTxactItm.getNm1s1Inst1Int3() == null || thisFinTxactItm.getNm1s1Inst1Int3() == 0 || thisFinTxactItm.getNm1s1Inst1Int3() == 1 ? 0 : thisFinTxactItm.getNm1s1Inst1Int3() - 1;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setNm1s1Inst1Int3(idZ);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idZ_ = thisFinTxactItm.getNm1s1Inst1Int3();
                    Integer idZ = (thisFinTxactItm.getNm1s1Inst1Int3() == null ? 0 : thisFinTxactItm.getNm1s1Inst1Int3()) + 1;
                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setNm1s1Inst1Int3(idZ);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

                LocalDate idDt1 = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
                if (idDt1 != null){

                    Integer idZMax = 0;
                    String idZMaxQry = "select max(e.idZ)"
                            + " from enty_UsrNodeFinTxactItm e"
                            + " where e.n1s1Inst1Dt1.elDt = :idDt1";
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

                    Integer idZ_ = thisFinTxactItm.getNm1s1Inst1Int3();
                    Integer idZ = idZMax == null ? 0 : idZMax;

                    if (!Objects.equals(idZ_, idZ)){
                        thisFinTxactItm.setNm1s1Inst1Int3(idZ);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinTxactItms);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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
                Integer finTxactSetOption = Optional.ofNullable(updateColItemCalcValsTxsetOption.getValue()).orElse(0);
                Integer finStmtItmOption = Optional.ofNullable(updateColItemCalcValsStmtItmOption.getValue()).orElse(0);
                isChanged = updateCalcVals(thisFinTxactItm, finTxactOption, finTxactSetOption, finStmtItmOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisFinTxactItms);
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisFinTxactItms);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleField", subject = "enterPressHandler")
    private void tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleFieldEnterPressHandler";
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
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

        List<UsrNodeBase> thisFinTxactItms = tableMain.getSelected().stream().toList();
        if (thisFinTxactItms.size() < 2) {
            logger.debug(logPrfx + " --- thisFinTxactItms contains less that 2 items.");
            notifications.create().withCaption("Less than 2 records selected. Please select 2 records.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UsrNodeBase selFinTxactItm0Tracked = dataContext.merge(thisFinTxactItms.get(0));
        UsrNodeBase selFinTxactItm1Tracked = dataContext.merge(thisFinTxactItms.get(1));

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

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinTxactItms);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxactSetOption = Optional.ofNullable(updateInstItemCalcValsTxsetOption.getValue()).orElse(0);
        Integer finTxactOption = Optional.ofNullable(updateInstItemCalcValsTxactOption.getValue()).orElse(0);
        Integer finStmtItmOption = Optional.ofNullable(updateInstItemCalcValsStmtItmOption.getValue()).orElse(0);

        updateCalcVals(thisFinTxactItm, finTxactOption, finTxactSetOption, finStmtItmOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        ((UsrNodeFinTxactItm) thisFinTxactItm).updateDesc1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

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

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinHow1_IdFieldListBtn")
    public void onUpdateFinHow1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinHow1_IdFieldListBtnClick";
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
    public void onUpdateFinWhat1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhat1_IdFieldListBtnClick";
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
    public void onUpdateFinWhy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

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

    @Subscribe("updateDesc1Node1_IdFieldListBtn")
    public void onUpdateDesc1Node1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1Node1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm1.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("ts1ElTsField")
    public void onTs1ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
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

    @Subscribe("updateTs1ElTsFieldBtn")
    public void onUpdateTs1ElTsFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTs1ElTsFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateTs1(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("Ts2ElTsField")
    public void onTs2ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs2ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
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


    @Subscribe("updateTs2ElTsFieldBtn")
    public void onUpdateTs2ElTsFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTs2ElTsFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateTs2(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinTxactItm);
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
            updateFinTxact1_FinTxactSet1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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



    @Subscribe("updateFinTxact1_Id_Desc1FieldBtn")
    public void onUpdateFinTxact1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_Desc1(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxact1_IdField")
    public void onFinTxact1_IdFieldValueChange(HasValue.ValueChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinTxact1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            if (thisFinTxactItm.getFinTxact1_Id() != null) {
                updateFinTxact1_FinTxactSet1_Id2Trgt(thisFinTxactItm);
            }
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateFinTxact1_IdFieldBtn")
    public void onUpdateFinTxact1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
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

        colLoadrFinTxact.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxact.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxact1_IdCandidsTableBtn")
    public void onUpdateFinTxact1_IdCandidsTableBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_IdCandidsTableBtnClick";
        logger.trace(logPrfx + " --> ");


        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        colLoadrFinTxactType.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxact1_Id_FinTxactSet1_EI1_RoleField", subject = "enterPressHandler")
    private void finTxact1_Id_FinTxactSet1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_FinTxactSet1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_EI1_RoleFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_EI1_RoleFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinTxact1_Id_FinTxactSet1_EI1_RoleList();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id_Desc1GenFmla1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Desc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm1.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_AmtEqCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateFintxact1_Id_FinTxactItms1_SysNodeFinCurcyEqCalcBoxBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_Desc1FieldBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_FinTxactSet1_Id_Desc1(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_IdFieldBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxactSetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxactSetOption == null){
            finTxactSetOption = 0;}
        updateFinTxact1_FinTxactSet1_Id(thisFinTxactItm, finTxactSetOption);
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactSet.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactSet.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id_FinTxactSet1_IdCandidsTableBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_IdCandidsTableBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_IdCandidsTableBtnClick";
        logger.trace(logPrfx + " --> ");


        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_Id_FinTxactSet1_IdCandidsTable(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    
    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id2TrgtBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxact1_FinTxactSet1_Id2Trgt(thisFinTxactItm);
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_Type1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Type1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactSetType.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactSetType.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_GenChan1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_GenChan2_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_GenChan2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_GenChan2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_How1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_How1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_WhatText1FieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_WhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_WhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxact1_Id_FinTxactSet1_Id_WhatText1Field", subject = "enterPressHandler")
    private void finTxact1_Id_FinTxactSet1_Id_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_FinTxactSet1_Id_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_What1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_What1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_What1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_WhyText1FieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_WhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_WhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "finTxact1_Id_FinTxactSet1_Id_WhyText1Field", subject = "enterPressHandler")
    private void finTxact1_Id_FinTxactSet1_Id_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finTxact1_Id_FinTxactSet1_Id_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_Why1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Why1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_Why1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_Desc1GenFmla1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_Desc1Node1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1Node1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1Node1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm1.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_Desc1Node2_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1Node2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1Node2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm1.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_GenTag1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_GenTag1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_GenTag1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinStmtItm1_IdFieldBtn")
    public void onUpdateFinStmtItm1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");


        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        // todo
        //updateFinStmtItm1_Id(thisFinTxactItm);
        List<UsrNodeFinStmtItm> finStmtItms = tableFinStmtItm1_IdCandids.getSelected().stream().toList();
        if (finStmtItms.size() > 0){
            UsrNodeFinStmtItm finStmtItm = finStmtItms.get(0);
            finStmtItm = dataContext.merge(finStmtItm);
            thisFinTxactItm.setFinStmtItm1_Id(finStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_IdFieldListBtn")
    public void onUpdateFinStmtItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinStmtItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmtItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinStmtItm1_IdCandidsTableBtn")
    public void onUpdateFinStmtItm1_IdCandidsTableBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItm1_IdCandidsTableBtnClick";
        logger.trace(logPrfx + " --> ");


        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
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

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");

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

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSysNodeFinCurcy1_IdFieldListBtn")
    public void onUpdateStsFinCurcy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateStsFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtFinTxactItm1_IdFieldListBtn")
    public void onUpdateAmtFinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxactItm1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm1.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtFinTxactItm1_EI1_RateBtn")
    public void onUpdateAmtFinTxactItm1_EI1_RateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxactItm1_EI1_RateBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

        UsrNodeBase thisFinTxactItm = instCntnrMain.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtNet(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtCalcGenFmla1_IdFieldListBtn")
    public void onUpdateAmtCalcGenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtCalcGenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrAmtCalcGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrAmtCalcGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull UsrNodeBase thisFinTxactItm, Integer finTxactOption, Integer finTxactSetOption, Integer finStmtItmOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItmCalcVals(thisFinTxactItm, finTxactOption) || isChanged;
        isChanged = updateFinTxactCalcVals(thisFinTxactItm, finTxactSetOption) || isChanged;
        isChanged = updateFinStmtItmCalcVals(thisFinTxactItm, finStmtItmOption) || isChanged;
        isChanged = updateFinTxactSetCalcVals(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactItmCalcVals(@NotNull UsrNodeBase thisFinTxactItm, Integer finTxactOption) {
        String logPrfx = "updateFinTxactItmCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxactItm Object
        isChanged = updateIdDt(thisFinTxactItm) || isChanged;
        isChanged = updateId2Calc(thisFinTxactItm) || isChanged;
        isChanged = updateId2Cmp(thisFinTxactItm) || isChanged;
        isChanged = updateId2Dup(thisFinTxactItm) || isChanged;
        isChanged = ((UsrNodeFinTxactItm) thisFinTxactItm).updateDesc1(dataManager) || isChanged;

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

    private Boolean updateFinTxactCalcVals(@NotNull UsrNodeBase thisFinTxactItm, Integer finTxactSetOption) {
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
        

        isChanged = updateFinTxact1_FinTxactSet1_Id2Trgt(thisFinTxactItm) || isChanged;
        isChanged = updateFinTxact1_FinTxactSet1_Id(thisFinTxactItm, finTxactSetOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactSetCalcVals(@NotNull UsrNodeBase thisFinTxactItm) {
        String logPrfx = "updateFinTxactSetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxactSet Object
        isChanged = updateFinTxact1_Id_FinTxactSet1_Id_Desc1(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinStmtItmCalcVals(@NotNull UsrNodeBase thisFinTxactItm, Integer finStmtItmOption) {
        String logPrfx = "updateFinStmtItmCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        
        // Stored in FinStmtItm Object
        // todo
//        isChanged = updateFinStmtItm1_Id_Desc1(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdParts(@NotNull UsrNodeBase thisFinTxactItm) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateTs1(thisFinTxactItm) || isChanged;
        isChanged = updateTs2(thisFinTxactItm) || isChanged;
        isChanged = updateIdDt(thisFinTxactItm)  || isChanged;
        isChanged = updateIdX(thisFinTxactItm)  || isChanged;
        isChanged = updateIdY(thisFinTxactItm)  || isChanged;
        isChanged = updateIdZ(thisFinTxactItm)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull UsrNodeBase thisFinTxactItm) {
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

    private Boolean updateId2Calc(@NotNull UsrNodeBase thisFinTxactItm) {
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

    private Boolean updateId2Cmp(@NotNull UsrNodeBase thisFinTxactItm) {
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

    private Boolean updateId2Dup(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxactItm.getId2Dup();
        if (thisFinTxactItm.getId2() != null) {
            String id2Qry = "select count(e)"
                    + " from enty_UsrFinTxactItm e"
                    + " where e.id2 = :id2"
                    + " and e.id <> :id";
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

    private Boolean updateFinTxact1_Id_Desc1(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();

        if (thisFinTxact != null) {
            //finTxactSet is a 2nd ref (ref of a ref) of finTxactItm and is not automatically loaded into the dataContext
            thisFinTxact = dataContext.merge(thisFinTxact);

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

    private Boolean updateFinTxact1_Id_FinTxactSet1_Id_Desc1(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id_FinTxactSet1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxactSet = thisFinTxactItm.getFinTxact1_Id() == null ? null : thisFinTxactItm.getFinTxact1_Id().getFinTxactSet1_Id();

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
                        thisAmt = thisAmt.trim();
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


    private Boolean updateIdDt(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateDt1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateTs1(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateTs1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateTs1FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateTs2(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateTs2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateTs2FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateInt1FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdY(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdY";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateInt2FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdZ(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdZ";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateInt3FrId2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateFinStmtItm1_Id(@NotNull UsrNodeBase thisFinTxactItm, Integer finStmtItmOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinStmtItm1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase finStmtItm1_Id_ = thisFinTxactItm.getFinTxact1_Id();
        UsrNodeFinStmtItm finStmtItm1_Id;
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


    private Boolean updateFinTxact1_Id(@NotNull UsrNodeBase thisFinTxactItm, Integer finTxactOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase finTxact1_Id_ = thisFinTxactItm.getFinTxact1_Id();
        UsrNodeBase finTxact1_Id;
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
                UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
                if (thisFinTxact != null) {
                    if (!Objects.equals(thisFinTxact.getNm1s1Inst1Dt1().getElDt(), thisFinTxactItm.getNm1s1Inst1Dt1().getElDt())) {
                        thisFinTxact.getTs1().setElTs(thisFinTxactItm.getNm1s1Inst1Dt1().getElDt().atStartOfDay());
                        thisFinTxact.updateDt1();
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getNm1s1Inst1Int1(), thisFinTxactItm.getNm1s1Inst1Int1())) {
                        thisFinTxact.setNm1s1Inst1Int1(thisFinTxactItm.getNm1s1Inst1Int1());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getNm1s1Inst1Int2(), thisFinTxactItm.getNm1s1Inst1Int2())) {
                        thisFinTxact.setNm1s1Inst1Int2(thisFinTxactItm.getNm1s1Inst1Int2());
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


    private void updateFinTxact1_IdCandidsTable(@NotNull UsrNodeBase thisFinTxactItm){
        String logPrfx = "updateFinTxact1_IdCandidsTable";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxact1_IdCandids.setQuery("select e from enty_UsrFinTxact e"
                + " where ( "
                + "        (    e.id2 = :id2 "
                + "        ) "
                + "     or (    e.n1s1Inst1Dt1.elDt = :idDt "
                + "         and e.idX = :idX "
                + "         and e.idY = :idY "
                + "        ) "
                + "    ) "
                + " order by e.sortKey, e.id2");

        String id2Trgt = thisFinTxactItm.getFinTxact1_Id2Trgt();
        if (id2Trgt == null) {
            logger.debug(logPrfx + " --- id2Trgt is null.");
            notifications.create().withCaption("id2Trgt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            colLoadrFinTxact1_IdCandids.setParameter("id2",id2Trgt);
        }

        LocalDate idDt = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
        if (idDt == null) {
            logger.debug(logPrfx + " --- idDt is null.");
            notifications.create().withCaption("idDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            idDt = thisFinTxactItm.getNm1s1Inst1Dt1().getElDt();
            colLoadrFinTxact1_IdCandids.setParameter("idDt",idDt);
        }

        Integer idX = thisFinTxactItm.getNm1s1Inst1Int1() != null ? thisFinTxactItm.getNm1s1Inst1Int1() : 0;
        colLoadrFinTxact1_IdCandids.setParameter("idX",idX);

        Integer idY = thisFinTxactItm.getNm1s1Inst1Int2() != null ? thisFinTxactItm.getNm1s1Inst1Int2() : 0;
        colLoadrFinTxact1_IdCandids.setParameter("idY",idY);

        colLoadrFinTxact1_IdCandids.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxact1_IdCandids.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateFinTxact1_Id2Trgt(@NotNull UsrNodeBase thisFinTxactItm) {
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

    private Boolean updateFintxact1_Id_FinTxactItms1_IdCntCalc(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            Integer idCntCalc_ = thisFinTxact.getFinTxactItms1_IdCntCalc();
            Integer idCntCalc = null ;
            String qry1 = "select count(e.amtNet)"
                    + " from enty_UsrFinTxactItm e"
                    + " where e.finTxact1_Id = :finTxact1_Id"
                    + " group by e.finTxact1_Id";
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

    private Boolean updateFintxact1_Id_FinTxactItms1_AmtDebtSumCalc(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_DebtSum";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            BigDecimal amtDebtSumCalc_ = thisFinTxact.getFinTxactItms1_AmtDebtSumCalc();
            BigDecimal amtDebtSumCalc = null ;
            String qry1 = "select sum(e.amtDebt)"
                    + " from enty_UsrFinTxactItm e"
                    + " where e.finTxact1_Id = :finTxact1_Id"
                    + " group by e.finTxact1_Id";
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

    private Boolean updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            BigDecimal amtCredSumCalc_ = thisFinTxact.getFinTxactItms1_AmtCredSumCalc();
            BigDecimal amtCredSumCalc = null ;
            String qry1 = "select sum(e.amtCred)"
                    + " from enty_UsrFinTxactItm e"
                    + " where e.finTxact1_Id = :finTxact1_Id"
                    + " group by e.finTxact1_Id";
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
    
    
    private Boolean updateFintxact1_Id_FinTxactItms1_AmtEqCalc(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_AmtEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
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

    private Boolean updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalc(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_FinCurcyEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();

        if(thisFinTxact == null){
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        Boolean sysNodeFinCurcyEqCalc_ = thisFinTxact.getFinTxactItms1_SysNodeFinCurcyEqCalc();
        Boolean sysNodeFinCurcyEqCalc = false;

        List<SysNodeBase> sysNodeFinCurcyList = null;
        String qry1 = "select e.sysNodeFinCurcy1_Id"
                + " from enty_UsrFinTxactItm e"
                + " where e.finTxact1_Id = :finTxact1_Id";
        try{
            sysNodeFinCurcyList = dataManager.loadValue(qry1, SysNodeBase.class)
                    .store("main")
                    .parameter("finTxact1_Id",thisFinTxact)
                    .list();
            if (sysNodeFinCurcyList == null || sysNodeFinCurcyList.isEmpty()) {
                logger.debug(logPrfx + " --- sysNodeFinCurcyList.size(): 0");
            }else{
                logger.debug(logPrfx + " --- sysNodeFinCurcyList.size(): " + sysNodeFinCurcyList.size());

                String sysNodeFinCurcyFirst_Id2 = sysNodeFinCurcyList.get(0).getId2();
                sysNodeFinCurcyEqCalc = true;
                for (SysNodeBase sysNodeFinCurcy: sysNodeFinCurcyList){
                    if (Objects.equals(sysNodeFinCurcyFirst_Id2,sysNodeFinCurcy.getId2())){
                        sysNodeFinCurcyEqCalc = false;
                    }
                }
            }
        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- sysNodeFinCurcyEqCalc: null");
        }

        if(!Objects.equals(sysNodeFinCurcyEqCalc_, sysNodeFinCurcyEqCalc)){
            isChanged = true;
            thisFinTxact.setFinTxactItms1_SysNodeFinCurcyEqCalc(sysNodeFinCurcyEqCalc);
            logger.debug(logPrfx + " --- sysNodeFinCurcyEqCalc: " + sysNodeFinCurcyEqCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    

    private void updateFinStmtItm1_IdCandidsTable(@NotNull UsrNodeBase thisFinTxactItm){
        String logPrfx = "updateFinStmtItm1_IdCandidsTable";
        logger.trace(logPrfx + " --> ");

        colLoadrFinStmtItm1_IdCandids.setQuery("select e from enty_UsrFinStmtItm e"
                + " where e.finStmt1_Id.finAcct1_Id = :finAcct1_Id"
                + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
                + " and (e.amtDebt = :amt or e.amtCred = :amt)"
                + " order by e.sortKey, e.id2");
        UsrNodeBase finAcct1 = thisFinTxactItm.getFinAcct1_Id();
        if (finAcct1 == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null.");
            notifications.create().withCaption("finAcct1_Id is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            colLoadrFinStmtItm1_IdCandids.setParameter("finAcct1_Id",finAcct1);
        }

        HasDate thisDt = thisFinTxactItm.getNm1s1Inst1Dt1();
        LocalDate begDt;
        LocalDate endDt;
        if (thisDt == null) {
            logger.debug(logPrfx + " --- idDt is null.");
            notifications.create().withCaption("idDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        begDt = thisFinTxactItm.getNm1s1Inst1Dt1().getElDt().minusDays(3);
        colLoadrFinStmtItm1_IdCandids.setParameter("begDt",begDt);
        endDt = thisFinTxactItm.getNm1s1Inst1Dt1().getElDt().plusDays(3);
        colLoadrFinStmtItm1_IdCandids.setParameter("endDt",endDt);

        BigDecimal amtDebt = thisFinTxactItm.getAmtDebt();
        BigDecimal amtCred = thisFinTxactItm.getAmtCred();
        if (amtDebt != null) {
            colLoadrFinStmtItm1_IdCandids.setParameter("amt",amtDebt);
        }else if (amtCred != null) {
            colLoadrFinStmtItm1_IdCandids.setParameter("amt",amtCred);
        }else{
            logger.debug(logPrfx + " --- amtDebt and amtCred are null.");
            notifications.create().withCaption("amtDebt and amtCred are null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        colLoadrFinStmtItm1_IdCandids.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmtItm1_IdCandids.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateFinStmtItm1_Id2Trgt(@NotNull UsrNodeBase thisFinTxactItm) {
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

    private UsrNodeBase findFinTxactItmEqId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFinTxactItmEqId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e "
                + " from enty_UsrFinTxactItm e"
                + " where e.id2 = :id2";
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

    private UsrNodeBase findFinTxactEqId2(@NotNull String finTxact_Id2) {
        String logPrfx = "findFinTxactEqId2";
        logger.trace(logPrfx + " --> ");

        if (finTxact_Id2 == null) {
            logger.debug(logPrfx + " --- finTxact_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxact_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e"
                + " from enty_UsrFinTxact e"
                + " where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        UsrNodeBase finTxact1_Id = null;
        try {
            finTxact1_Id = dataManager.load(UsrNodeBase.class)
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


    private UsrNodeBase findFirstFinTxactItmLikeId2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFirstFinTxactItmLikeId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e"
                + " from enty_UsrFinTxactItm e"
                + " where e.id2 like :id2"
                + " order by e.sortKey, e.id2";
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

    private UsrNodeBase createFinTxactFrFinTxactItm(@NotNull UsrNodeBase thisFinTxactItm) {
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

        String qry = "select e"
                + " from enty_UsrFinTxact e"
                + " where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        try {
            UsrNodeBase finTxact = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("id2", finTxact_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            logger.trace(logPrfx + " <-- ");
            return finTxact;

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

            UsrNodeBase newFinTxact = dataManager.create(UsrNodeBase.class);
            newFinTxact.setClassName("UsrFinTxact");

            HasTmst ts1 = dataManager.create(HasTmst.class);
            ts1.setElTs(thisFinTxactItm.getNm1s1Inst1Dt1().getElDt().atStartOfDay());
            newFinTxact.setTs1(ts1);
            newFinTxact.updateDt1();

            newFinTxact.setNm1s1Inst1Int1(thisFinTxactItm.getNm1s1Inst1Int1());
            newFinTxact.setNm1s1Inst1Int2(thisFinTxactItm.getNm1s1Inst1Int2());

            newFinTxact.setId2Calc(newFinTxact.getId2CalcFrFields());
            newFinTxact.setId2(newFinTxact.getId2Calc());


            UsrNodeBase savedFinTxact = dataManager.save(newFinTxact);

            UsrNodeBase mergedFinTxact = dataContext.merge(savedFinTxact);
            logger.debug(logPrfx + " --- created FinTxact id: " + mergedFinTxact.getId());
            notifications.create().withCaption("Created FinTxact with id2:" + mergedFinTxact.getId2()).show();

            logger.trace(logPrfx + " <-- ");
            return mergedFinTxact;
        }
    }


    private Boolean updateFinTxact1_FinTxactSet1_Id(@NotNull UsrNodeBase thisFinTxactItm, Integer finTxactSetOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_FinTxactSet1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null){
            UsrNodeBase finTxactSet1_Id_ = thisFinTxact.getFinTxactSet1_Id();
            UsrNodeBase finTxactSet1_Id;
            // Update finTxact
            switch (finTxactSetOption) {
                case 1 -> { // Link to Exist Txset
                    finTxactSet1_Id = findFinTxactSetEqId2(thisFinTxact.getFinTxactSet1_Id2Trgt());
                    if (!Objects.equals(finTxactSet1_Id_, finTxactSet1_Id)) {
                        isChanged = true;
                        thisFinTxact.setFinTxactSet1_Id(finTxactSet1_Id);
                    }
                }
                case 2 -> { // Link to Exist/New Txset
                    finTxactSet1_Id = findFinTxactSetEqId2(thisFinTxact.getFinTxactSet1_Id2Trgt());
                    if (finTxactSet1_Id == null) {
                        finTxactSet1_Id = createFinTxactSetFrFinTxactItm(thisFinTxactItm);
                    }
                    if (!Objects.equals(finTxactSet1_Id_, finTxactSet1_Id)) {
                        thisFinTxact.setFinTxactSet1_Id(finTxactSet1_Id);
                        isChanged = true;
                    }
                }
                case 3 -> { // Update Exist Txact
                    boolean thisFinTxactSetIsChanged = false;
                    UsrNodeBase thisFinTxactSet = thisFinTxact.getFinTxactSet1_Id();
                    if (thisFinTxactSet != null) {
                        if (!Objects.equals(thisFinTxactSet.getNm1s1Inst1Dt1().getElDt(), thisFinTxactItm.getNm1s1Inst1Dt1().getElDt())) {
                            thisFinTxactSet.getTs1().setElTs(thisFinTxactItm.getNm1s1Inst1Dt1().getElDt().atStartOfDay());
                            thisFinTxactSetIsChanged = true;
                        }
                        if (!Objects.equals(thisFinTxactSet.getNm1s1Inst1Int1(), thisFinTxactItm.getNm1s1Inst1Int1())) {
                            thisFinTxactSet.setNm1s1Inst1Int1(thisFinTxactItm.getNm1s1Inst1Int1());
                            thisFinTxactSetIsChanged = true;
                        }
                        if (thisFinTxactSetIsChanged) {
                            thisFinTxactSet.updateDt1();
                            thisFinTxactSet.setId2Calc(thisFinTxactSet.getId2CalcFrFields());
                            thisFinTxactSet.setId2(thisFinTxactSet.getId2Calc());
                            dataManager.save(thisFinTxactSet);
                        }
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private void updateFinTxact1_Id_FinTxactSet1_IdCandidsTable(@NotNull UsrNodeBase thisFinTxactItm){
        String logPrfx = "updateFinTxact1_Id_FinTxactSet1_IdCandidsTable";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactSet1_IdCandids.setQuery("select e"
                + " from enty_UsrFinTxactSet e"
                + " where ( "
                + "        (    e.id2 = :id2 "
                + "        ) "
                + "     or (    e.n1s1Inst1Dt1.elDt = :idDt "
                + "         and e.idX = :idX "
                + "        ) "
                + "    ) "
                + "order by e.sortKey, e.id2");

        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact == null){
            logger.trace(logPrfx + " <-- ");
            return;
        }

        String id2Trgt = thisFinTxact.getFinTxactSet1_Id2Trgt();
        if (id2Trgt == null) {
            logger.debug(logPrfx + " --- id2Trgt is null.");
            notifications.create().withCaption("id2Trgt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            colLoadrFinTxactSet1_IdCandids.setParameter("id2",id2Trgt);
        }

        LocalDate idDt = thisFinTxactItm.getNm1s1Inst1Dt1() != null ? thisFinTxactItm.getNm1s1Inst1Dt1().getElDt() : null;
        if (idDt == null) {
            logger.debug(logPrfx + " --- idDt is null.");
            notifications.create().withCaption("idDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }else{
            idDt = thisFinTxactItm.getNm1s1Inst1Dt1().getElDt();
            colLoadrFinTxactSet1_IdCandids.setParameter("idDt",idDt);
        }

        Integer idX = thisFinTxactItm.getNm1s1Inst1Int1() != null ? thisFinTxactItm.getNm1s1Inst1Int1() : 0;
        colLoadrFinTxactSet1_IdCandids.setParameter("idX",idX);

        colLoadrFinTxactSet1_IdCandids.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactSet1_IdCandids.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateFinTxact1_FinTxactSet1_Id2Trgt(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_FinTxactSet1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null){
            String id2Calc_ = thisFinTxactItm.getId2Calc();
            String id2Calc = thisFinTxactItm.getId2CalcFrFields().substring(0, 16);
            if (!Objects.equals(id2Calc_, id2Calc)){
                isChanged = true;
                thisFinTxact.setFinTxactSet1_Id2Trgt(id2Calc);
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private UsrNodeBase findFinTxactSetEqId2(@NotNull String finTxactSet_Id2) {
        String logPrfx = "findFinTxactSetEqId2";
        logger.trace(logPrfx + " --> ");

        if (finTxactSet_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactSet_Id2 is null.");
            notifications.create().withCaption("finTxactSet_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e"
                + " from enty_UsrFinTxactSet e"
                + " where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxactSet_Id2);

        UsrNodeBase finTxactSet1_Id = null;
        try {
            finTxactSet1_Id = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("id2", finTxactSet_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            // notifications.create().withCaption("finTxactSet with id2: " + finTxactSet1_Id2Trgt + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finTxactSet1_Id;

    }

    private UsrNodeBase createFinTxactSetFrFinTxactItm(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "createFinTxactSetFrFinTxactItm";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase mergedFinTxactSet = null;
        if (thisFinTxactItm.getFinTxact1_Id() != null) {
            String finTxactSet_Id2 = thisFinTxactItm.getFinTxact1_Id().getFinTxactSet1_Id2Trgt();
            if (finTxactSet_Id2 == null) {
                logger.debug(logPrfx + " --- finTxactSet_Id2 is null.");
                notifications.create().withCaption("finTxactSet_Id2 is empty. Please set it to correct value.").show();
                logger.trace(logPrfx + " <-- ");
                return null;
            }

            String qry = "select e"
                    + " from enty_UsrFinTxactSet e"
                    +" where e.id2 = :id2";
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
                newFinTxactSet.setClassName("UsrFinTxactSet");

                HasTmst ts1 = dataManager.create(HasTmst.class);
                ts1.setElTs(thisFinTxactItm.getNm1s1Inst1Dt1().getElDt().atStartOfDay());
                newFinTxactSet.setTs1(ts1);
                newFinTxactSet.updateDt1();

                newFinTxactSet.setNm1s1Inst1Int1(thisFinTxactItm.getNm1s1Inst1Int1());

                newFinTxactSet.setId2Calc(newFinTxactSet.getId2CalcFrFields());
                newFinTxactSet.setId2(newFinTxactSet.getId2Calc());


                UsrNodeBase savedFinTxactSet = dataManager.save(newFinTxactSet);

                mergedFinTxactSet = dataContext.merge(savedFinTxactSet);
                logger.debug(logPrfx + " --- created FinTxactSet id: " + mergedFinTxactSet.getId());
                notifications.create().withCaption("Created FinTxactSet with id2:" + mergedFinTxactSet.getId2()).show();
            }

        }
        logger.trace(logPrfx + " <-- ");
        return mergedFinTxactSet;
    }


    private UsrNodeFinStmtItm findFinStmtItmEqId2(@NotNull String finStmtItm_Id2) {
        String logPrfx = "findFinStmtItmEqId2";
        logger.trace(logPrfx + " --> ");

        String qry = "select e"
                + " from enty_UsrFinStmtItm e where"
                + " e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finStmtItm_Id2);

        UsrNodeFinStmtItm finStmtItm_Id = null;
        try {
            finStmtItm_Id = dataManager.load(UsrNodeFinStmtItm.class)
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


    private Boolean updateAmtFinTxactItm1_EI1_Rate(@NotNull UsrNodeBase thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtFinTxactItm1_EI1_Rate";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal rate_ = thisFinTxactItm.getAmtFinTxactItm1_EI1_Rate();

        UsrItemGenFmla amtCalcGenFmla1 = thisFinTxactItm.getAmtCalcGenFmla1_Id();
        if (amtCalcGenFmla1 == null) {
            logger.debug(logPrfx + " --- amtCalcGenFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else if (!Objects.equals(amtCalcGenFmla1.getId2(), "Ref1 Mult Exch_Rate")) {
            logger.debug(logPrfx + " --- amtCalcGenFmla1: " + amtCalcGenFmla1.getId2());
            notifications.create().withCaption("amtGenFmla1_Id must be 'Ref1 Mult Exch_Rate' to get an exchange rate.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- amtCalcGenFmla1: " + amtCalcGenFmla1.getId());
        }

        UsrNodeBase amtFinTxactItm1 = thisFinTxactItm.getAmtFinTxactItm1_Id();
        if (amtFinTxactItm1 == null) {
            logger.debug(logPrfx + " --- amtFinTxactItm1: null");
            notifications.create().withCaption("Unable to get reference to the other FinTxactItm. Please ensure amtFinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        SysNodeBase curcyFr = thisFinTxactItm.getAmtFinTxactItm1_Id().getSysNodeFinCurcy1_Id();
        if (curcyFr == null) {
            logger.debug(logPrfx + " --- curcyFr: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxactItm. Please ensure a FinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- curcyFr.Id: " + curcyFr.getId());
        }

        SysNodeBase curcyTo = thisFinTxactItm.getSysNodeFinCurcy1_Id();
        if (curcyTo == null) {
            logger.debug(logPrfx + " --- curcyTo: null");
            notifications.create().withCaption("Unable to get the currency from this FinTxactItm. Please ensure a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- curcyTo.Id: " + curcyTo.getId());

        }

        LocalDate date1 = thisFinTxactItm.getTs1().getElDt();

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

        SysNodeBase curcyExchRate;
        try {
            curcyExchRate = dataManager.load(SysNodeBase.class)
                    .query("select e from enty_SysNodeBase e"
                            + " where e.finCurcy1_Id.id = :curcyFr"
                            + " and   e.finCurcy2_Id.id = :curcyTo"
                            + " and   e.ts1.elDt = :date1"
                    )
                    .parameter("curcyFr", curcyFr.getId())
                    .parameter("curcyTo", curcyTo.getId())
                    .parameter("date1", date1)
                    .one()
            ;

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- qry1 java.lang.IllegalStateException: " + e.getMessage());
            curcyExchRate = null;
        }


        if (curcyExchRate != null) {
            rate = curcyExchRate.getAmt1();
            qryRsltGood = true;
            logger.debug(logPrfx + " --- qry1 result is Id: " + curcyExchRate.getId());

        } else {
            logger.debug(logPrfx + " --- qry1 result is empty");
            try {

                curcyExchRate = dataManager.load(SysNodeBase.class)
                        .query("select e from enty_SysNodeBase e"
                                + " where e.finCurcy1_Id.id = :curcyFr"
                                + " and   e.finCurcy2_Id.id = :curcyTo"
                                + " and   e.ts1.elDt = :date1"
                        )
                        .parameter("curcyFr", curcyTo.getId())
                        .parameter("curcyTo", curcyFr.getId())
                        .parameter("date1", date1)
                        .one()
                ;
            } catch (IllegalStateException e) {
                logger.debug(logPrfx + " --- qry2 java.lang.IllegalStateException: " + e.getMessage());
                curcyExchRate = null;
            }

            if (curcyExchRate != null) {
                rate = curcyExchRate.getAmt2();
                qryRsltGood = true;
                logger.debug(logPrfx + " --- qry2 result is Id: " + curcyExchRate.getId());
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


    private Boolean updateAmtCalc(@NotNull UsrNodeBase thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtCalc_ = thisFinTxactItm.getAmtCalc();
        BigDecimal amtCalc = BigDecimal.valueOf(0);
        boolean amtCalcGood = false;

        UsrItemGenFmla amtCalcGenFmla1 = thisFinTxactItm.getAmtCalcGenFmla1_Id();
        if (amtCalcGenFmla1 == null) {
            logger.debug(logPrfx + " --- amtCalcGenFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- amtCalcGenFmla1: " + amtCalcGenFmla1.getId());
        }

        UsrNodeBase prev1FinTxactItm;
        UsrNodeBase prev2FinTxactItm;

        // Switch statement over above string
        switch (amtCalcGenFmla1.getId2()) {

            // Ref1 Mult Exch_Rate
            case "Ref1 Mult Exch_Rate":

                UsrNodeBase amtFinTxactItm1 = thisFinTxactItm.getAmtFinTxactItm1_Id();
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
                logger.debug(logPrfx + " --- formula not implemented: " + amtCalcGenFmla1.getId2());
                notifications.create().withCaption("Formula not implemented: " + amtCalcGenFmla1.getId2()).show();
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

    public Boolean updateAmtNet(@NotNull UsrNodeBase thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtNet_ = thisFinTxactItm.getAmtNet();

        UsrNodeBase thisFinAcct = thisFinTxactItm.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        UsrNodeBaseType thisFinAcctType = thisFinAcct.getType1_Id();
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

    private Boolean updateFinTaxLne1_Id(@NotNull UsrNodeBase thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTaxLne1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase finTaxLne_ = thisFinTxactItm.getFinTaxLne1_Id();
        UsrNodeBase finTaxLne = thisFinTxactItm.getFinAcct1_Id() == null ? null :
                thisFinTxactItm.getFinAcct1_Id().getFinTaxLne1_Id();

        if (!Objects.equals(finTaxLne_, finTaxLne)){
            thisFinTxactItm.setFinTaxLne1_Id(finTaxLne);
            logger.debug(logPrfx + " --- finTaxLne.id2: " + finTaxLne == null ? "null" : finTaxLne.getId2());
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public UsrNodeBase getPrevFinTxactItm(@NotNull UsrNodeBase thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "getPrevFinTxactItm";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase prevFinTxactItm = null;
        boolean qryRsltGood = false;

        if (thisFinTxactItm.getNm1s1Inst1Int3() != null || thisFinTxactItm.getNm1s1Inst1Int3() >= 1) {
            prevFinTxactItm = metadataTools.copy(thisFinTxactItm);
            prevFinTxactItm.setNm1s1Inst1Int3(prevFinTxactItm.getNm1s1Inst1Int3() - 1);
            String prev1FinTxactItm_Id2 = prevFinTxactItm.getId2CalcFrFields();

            String qry = "select e"
                    + " from enty_UsrFinTxactItm e"
                    + " where e.id2 = :id2";

            logger.debug(logPrfx + " --- qry: " + qry);

            try {
                prevFinTxactItm = dataManager.load(UsrNodeBase.class)
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


        tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_FinTxactSet1_Id_WhatText1Field.setOptionsList()");

        finTxact1_Id_FinTxactSet1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_FinTxactSet1_Id_WhatText1Field.setOptionsList()");

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


        tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_FinTxactSet1_Id_WhyText1Field.setOptionsList()");

        finTxact1_Id_FinTxactSet1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_FinTxactSet1_Id_WhyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxact1_Id_FinTxactSet1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_Id_FinTxactSet1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTxactSet1_EI1_Role"
                + " from enty_UsrFinTxact e"
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

        tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called tmplt_FinTxact1_Id_FinTxactSet1_EI1_RoleField.setOptionsList()");

        finTxact1_Id_FinTxactSet1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxact1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTxact1_EI1_Role"
                + " from enty_UsrFinTxactItm e"
                + " where e.finTxact1_EI1_Role is not null"
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

        ComboBox<String> propFilterCmpnt_FinTxact1_EI1_Role = (ComboBox<String>) filterConfig1A_FinTxact1_EI1_Role.getValueComponent();
        propFilterCmpnt_FinTxact1_EI1_Role.setOptionsList(roles);
        logger.debug(logPrfx + " --- called propFilterCmpnt_FinTxact1_EI1_Role.setOptionsList()");

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
                + " from enty_UsrFinStmtItm e"
                + " where e.desc1 is not null"
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
                + " from enty_UsrFinStmtItm e"
                + " where e.desc2 is not null"
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
                + " from enty_UsrFinStmtItm e"
                + " where e.desc3 is not null"
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
                + " from enty_UsrFinTxactItm e"
                + " where e.finTaxLne1_Code is not null"
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
        String idXMaxQry = "select max(e.idX)"
                + " from enty_UsrFinTxactItm e"
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
        String idXCntIsNullQry = "select count(e) from enty_UsrFinTxactItm e"
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
        String idYMaxQry = "select max(e.idY)"
                + " from enty_UsrFinTxactItm e"
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
        String idYCntIsNullQry = "select count(e)"
                + " from enty_UsrFinTxactItm e"
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

    private Integer getIdZMax(LocalDate idDt1, Integer idX, Integer idY) {
        String logPrfx = "getIdZMax";
        logger.trace(logPrfx + " --> ");

        Integer idZ, idZMax = 0;
        String idZMaxQry = "select max(e.idZ)"
                + " from enty_UsrFinTxactItm e"
                + " where e.n1s1Inst1Dt1.elDt = :idDt1"
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
        String idZCntIsNullQry = "select count(e)"
                + " from enty_UsrFinTxactItm e"
                + " where e.n1s1Inst1Dt1.elDt = :idDt1"
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
