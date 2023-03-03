package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.*;
import ca.ampautomation.ampata.entity.usr.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.sys.base.SysNodeBase;
import ca.ampautomation.ampata.entity.usr.fin.*;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrFinFmla;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrFinWhy;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrFinTxactItmQryMngr;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrGenChan;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrGenDocVer;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrGenTag;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@UiController("enty_UsrFinTxactItm.edit")
@UiDescriptor("usr-fin-txact-itm-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrFinTxactItm0Edit extends StandardEditor<UsrNodeBase> {

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
    private UsrFinTxactItmQryMngr qryMngr;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxactOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxsetOption;


    //Main data containers, loaders and table
    @Autowired
    private InstanceContainer<UsrNodeBase> instCntnrMain;


    //Type data container and loader
    private CollectionContainer<UsrNodeBaseType> colCntnrType;
    private CollectionLoader<UsrNodeBaseType> colLoadrType;


    //Other data containers, loaders and table
    private CollectionContainer<UsrGenChan> colCntnrGenChan;
    private CollectionLoader<UsrGenChan> colLoadrGenChan;

    private CollectionContainer<UsrGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrGenDocVer> colLoadrGenDocVer;

    private CollectionContainer<UsrGenTag> colCntnrGenTag;
    private CollectionLoader<UsrGenTag> colLoadrGenTag;

    private CollectionContainer<UsrNodeBase> colCntnrFinTxact;
    private CollectionLoader<UsrNodeBase> colLoadrFinTxact;

    private CollectionContainer<UsrNodeBaseType> colCntnrFinTxactType;
    private CollectionLoader<UsrNodeBaseType> colLoadrFinTxactType;

    private CollectionContainer<UsrNodeBase> colCntnrFinTxactSet;
    private CollectionLoader<UsrNodeBase> colLoadrFinTxactSet;

    private CollectionLoader<UsrNodeBaseType> colLoadrFinTxactSetType;
    private CollectionContainer<UsrNodeBaseType> colCntnrFinTxactSetType;


    private CollectionLoader<UsrNodeBase> colLoadrFinStmt;
    private CollectionContainer<UsrNodeBase> colCntnrFinStmt;

    private CollectionLoader<UsrNodeBase> colLoadrFinDept;
    private CollectionContainer<UsrNodeBase> colCntnrFinDept;

    private CollectionLoader<UsrNodeBase> colLoadrFinTaxLne;
    private CollectionContainer<UsrNodeBase> colCntnrFinTaxLne;

    private CollectionLoader<UsrNodeBase> colLoadrFinAcct;
    private CollectionContainer<UsrNodeBase> colCntnrFinAcct;

    private CollectionContainer<SysNodeBase> colCntnrSysFinCurcy;
    private CollectionLoader<SysNodeBase> colLoadrSysFinCurcy;

    private CollectionContainer<UsrNodeBase> colCntnrFinTxactItm1;
    private CollectionLoader<UsrNodeBase> colLoadrFinTxactItm1;

    private CollectionContainer<UsrFinFmla> colCntnrFinFmla;
    private CollectionLoader<UsrFinFmla> colLoadrFinFmla;

    private CollectionContainer<UsrFinHow> colCntnrFinHow;
    private CollectionLoader<UsrFinHow> colLoadrFinHow;

    private CollectionContainer<UsrFinWhat> colCntnrFinWhat;
    private CollectionLoader<UsrFinWhat> colLoadrFinWhat;

    private CollectionContainer<UsrFinWhy> colCntnrFinWhy;
    private CollectionLoader<UsrFinWhy> colLoadrFinWhy;

    private CollectionContainer<UsrGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrGenFmla> colLoadrGenFmla;


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
    private EntityComboBox<UsrGenChan> genChan1_IdField;

    @Autowired
    private EntityComboBox<UsrFinHow> finHow1_IdField;

    @Autowired
    private ComboBox<String> whatText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhat> finWhat1_IdField;

    @Autowired
    private ComboBox<String> whyText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhy> finWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenFmla> desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag2_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag3_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag4_IdField;



    @Autowired
    private EntityComboBox<UsrNodeBase> finStmt1_IdField;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc1Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc2Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc3Field;


    @Autowired
    private EntityComboBox<UsrNodeBase> finTaxLne1_IdField;

    @Autowired
    private ComboBox<String> finTaxLne1_CodeField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finAcct1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finDept1_IdField;

    @Autowired
    private EntityComboBox<SysNodeBase> sysFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> amtFinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrFinFmla> amtFinFmla1_IdField;


    //Field (FinTxact)
    @Autowired
    private EntityComboBox<UsrNodeBase> finTxact1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBaseType> finTxact1_Id_Type1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxactSet1_EI1_RoleField;

    @Autowired
    private EntityComboBox<UsrGenChan> finTxact1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrFinHow> finTxact1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhat> finTxact1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_WhyText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhy> finTxact1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenFmla> finTxact1_Id_Desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finTxact1_Id_Desc1FinTxactItm1_IdField;


    // Field (FinTxact.FinTxactSet)
    @Autowired
    private EntityComboBox<UsrNodeBase> finTxact1_Id_FinTxactSet1_IdField;

    @Autowired
    private TextField<String> finTxact1_Id_FinTxactSet1_Id2TrgtField;

    @Autowired
    private EntityComboBox<UsrNodeBaseType> finTxact1_Id_FinTxactSet1_Id_Type1_IdField;

    @Autowired
    private EntityComboBox<UsrGenChan> finTxact1_Id_FinTxactSet1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrFinHow> finTxact1_Id_FinTxactSet1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxactSet1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhat> finTxact1_Id_FinTxactSet1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxactSet1_Id_WhyText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhy> finTxact1_Id_FinTxactSet1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenFmla> finTxact1_Id_FinTxactSet1_Id_Desc1GenFmla1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finTxact1_Id_FinTxactSet1_Id_Desc1FinTxactItm1_IdField;


    /*
    InitEvent is sent when the screen controller and all its declaratively defined components are created,
    and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
    are not fully initialized, for example, buttons are not linked with actions.
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put("None", 0);
        map2.put("Link to Exist Txact", 1);
        map2.put("Link to Exist/New Txact", 2);
        map2.put("Update Exist Txact", 3);
        updateInstItemCalcValsTxactOption.setOptionsMap(map2);

        Map<String, Integer> map3 = new LinkedHashMap<>();
        map3.put("None", 0);
        map3.put("Link to Exist Txset", 1);
        map3.put("Link to Exist/New Txset", 2);
        map3.put("Update Exist Txset", 3);
        updateInstItemCalcValsTxsetOption.setOptionsMap(map3);


        finStmtItm1_Desc1Field.setNullOptionVisible(true);
        finStmtItm1_Desc1Field.setNullSelectionCaption("<null>");
        finStmtItm1_Desc2Field.setNullOptionVisible(true);
        finStmtItm1_Desc2Field.setNullSelectionCaption("<null>");
        finStmtItm1_Desc3Field.setNullOptionVisible(true);
        finStmtItm1_Desc3Field.setNullSelectionCaption("<null>");

        finTaxLne1_CodeField.setNullOptionVisible(true);
        finTaxLne1_CodeField.setNullSelectionCaption("<null>");


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrFinTxactItmType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactItmType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnFinTxactItmType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);


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
        finTxact1_Id_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        finTxact1_Id_FinTxactSet1_Id_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);


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
        finTxact1_Id_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        finTxact1_Id_FinTxactSet1_Id_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);


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
        finTxact1_Id_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        finTxact1_Id_FinTxactSet1_Id_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);


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
        finTxact1_Id_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        finTxact1_Id_FinTxactSet1_Id_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);


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
        finTxact1_Id_Desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        finTxact1_Id_FinTxactSet1_Id_Desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);


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
        genTag2_IdField.setOptionsContainer(colCntnrGenTag);
        genTag3_IdField.setOptionsContainer(colCntnrGenTag);
        genTag4_IdField.setOptionsContainer(colCntnrGenTag);


        colCntnrFinTxact = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinTxact = dataComponents.createCollectionLoader();
        colLoadrFinTxact.setQuery("select e from enty_UsrFinTxact e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxact_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxact.setFetchPlan(fchPlnFinTxact_Inst);
        colLoadrFinTxact.setContainer(colCntnrFinTxact);
        colLoadrFinTxact.setDataContext(getScreenData().getDataContext());

        finTxact1_IdField.setOptionsContainer(colCntnrFinTxact);


        colCntnrFinTxactType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrFinTxactType = dataComponents.createCollectionLoader();
        colLoadrFinTxactType.setQuery("select e from enty_UsrFinTxactType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactType.setFetchPlan(fchPlnFinTxactType_Inst);
        colLoadrFinTxactType.setContainer(colCntnrFinTxactType);
        colLoadrFinTxactType.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_Type1_IdField.setOptionsContainer(colCntnrFinTxactType);


        colCntnrFinTxactSet = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinTxactSet = dataComponents.createCollectionLoader();
        colLoadrFinTxactSet.setQuery("select e from enty_UsrFinTxactSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSet_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactSet.setFetchPlan(fchPlnFinTxactSet_Inst);
        colLoadrFinTxactSet.setContainer(colCntnrFinTxactSet);
        colLoadrFinTxactSet.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxactSet1_IdField.setOptionsContainer(colCntnrFinTxactSet);


        colCntnrFinTxactSetType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrFinTxactSetType = dataComponents.createCollectionLoader();
        colLoadrFinTxactSetType.setQuery("select e from enty_UsrFinTxactSetType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSetType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactSetType.setFetchPlan(fchPlnFinTxactSetType_Inst);
        colLoadrFinTxactSetType.setContainer(colCntnrFinTxactSetType);
        colLoadrFinTxactSetType.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxactSet1_Id_Type1_IdField.setOptionsContainer(colCntnrFinTxactSetType);


        colCntnrFinStmt = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinStmt = dataComponents.createCollectionLoader();
        colLoadrFinStmt.setQuery("select e from enty_UsrFinStmt e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinStmt_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinStmt.setFetchPlan(fchPlnFinStmt_Inst);
        colLoadrFinStmt.setContainer(colCntnrFinStmt);
        colLoadrFinStmt.setDataContext(getScreenData().getDataContext());

        finStmt1_IdField.setOptionsContainer(colCntnrFinStmt);


        colCntnrFinTaxLne = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinTaxLne = dataComponents.createCollectionLoader();
        colLoadrFinTaxLne.setQuery("select e from enty_UsrGenDocFrg e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTaxLne_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTaxLne.setFetchPlan(fchPlnFinTaxLne_Inst);
        colLoadrFinTaxLne.setContainer(colCntnrFinTaxLne);
        colLoadrFinTaxLne.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(colCntnrFinTaxLne);


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrFinAcct e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcct_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(fchPlnFinAcct_Inst);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(colCntnrFinAcct);


        colCntnrFinDept = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinDept = dataComponents.createCollectionLoader();
        colLoadrFinDept.setQuery("select e from enty_UsrFinDept e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinDept_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinDept.setFetchPlan(fchPlnFinDept_Inst);
        colLoadrFinDept.setContainer(colCntnrFinDept);
        colLoadrFinDept.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(colCntnrFinDept);


        colCntnrSysFinCurcy = dataComponents.createCollectionContainer(SysNodeBase.class);
        colLoadrSysFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysFinCurcy.setQuery("select e from enty_SysFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnSysFinCurcy_Inst = fetchPlans.builder(SysNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysFinCurcy.setFetchPlan(fchPlnSysFinCurcy_Inst);
        colLoadrSysFinCurcy.setContainer(colCntnrSysFinCurcy);
        colLoadrSysFinCurcy.setDataContext(getScreenData().getDataContext());

        sysFinCurcy1_IdField.setOptionsContainer(colCntnrSysFinCurcy);


        colCntnrFinTxactItm1 = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinTxactItm1 = dataComponents.createCollectionLoader();
        colLoadrFinTxactItm1.setQuery("select e from enty_UsrFinTxactItm e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactItm1_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactItm1.setFetchPlan(fchPlnFinTxactItm1_Inst);
        colLoadrFinTxactItm1.setContainer(colCntnrFinTxactItm1);
        colLoadrFinTxactItm1.setDataContext(getScreenData().getDataContext());

        desc1FinTxactItm1_IdField.setOptionsContainer(colCntnrFinTxactItm1);
        finTxact1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(colCntnrFinTxactItm1);
        finTxact1_Id_FinTxactSet1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(colCntnrFinTxactItm1);
        amtFinTxactItm1_IdField.setOptionsContainer(colCntnrFinTxactItm1);


        colCntnrFinFmla = dataComponents.createCollectionContainer(UsrFinFmla.class);
        colLoadrFinFmla = dataComponents.createCollectionLoader();
        colLoadrFinFmla.setQuery("select e from enty_UsrFinFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinFmla_Inst = fetchPlans.builder(UsrFinFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinFmla.setFetchPlan(fchPlnFinFmla_Inst);
        colLoadrFinFmla.setContainer(colCntnrFinFmla);
        colLoadrFinFmla.setDataContext(getScreenData().getDataContext());

        amtFinFmla1_IdField.setOptionsContainer(colCntnrFinFmla);


        logger.trace(logPrfx + " <-- ");


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
    BeforeShowEvent is sent right before the screen is shown, for example, it is not added to the application UI yet.
    Security restrictions are applied to UI components. In this event listener, you can load data,
    check permissions and modify UI components.
    */
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

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

        colLoadrFinStmt.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmt.load() ");
        reloadFinStmtItm1_Desc1List();
        reloadFinStmtItm1_Desc2List();
        reloadFinStmtItm1_Desc3List();

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        colLoadrSysFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysFinCurcy.load() ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");
        reloadFinTaxLne1_CodeList();

        colLoadrFinFmla.load();
        logger.debug(logPrfx + " --- called colLoadrFinFmla.load() ");

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
        Integer finTxactOption = updateInstItemCalcValsTxactOption.getValue();
        if (finTxactOption == null){
            finTxactOption = 0;}
        Integer finTxactSetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxactSetOption == null){
            finTxactSetOption = 0;}

        updateCalcVals(thisFinTxactItm, finTxactOption, finTxactSetOption);

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
        updateDesc1(thisFinTxactItm);

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
    public void onUpdateGenChan1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan1_IdFieldListBtn";
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

    @Subscribe("updateDescPat1_IdFieldListBtn")
    public void onUpdateDescPat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDescPat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateDesc1FinTxactItm1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FinTxactItm1_IdFieldListBtnClick";
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

            logger.debug(logPrfx + " --- calling updateIdTs(thisFinTxactItm)");
            updateIdTs(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateId2Calc(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTs1ElTsFieldBtn")
    public void onUpdateTs1ElTsFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateTs1ElTsFieldBtn";
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

    @Subscribe("ts2ElTsField")
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

            logger.debug(logPrfx + " --- calling updateIdTs(thisFinTxactItm)");
            updateIdTs(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateId2Calc(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTs2ElTsFieldBtn")
    public void onUpdateTs2ElTsFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateTs2ElTsFieldBtn";
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
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateId2Calc(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateFinTxact1_Id2Trgt(thisFinTxactItm)");
            updateFinTxact1_Id2Trgt(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateFinTxact1_FinTxactSet1_Id2Trgt(thisFinTxactItm)");
            updateFinTxact1_FinTxactSet1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtn";
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
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateId2Calc(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateFinTxact1_Id2Trgt(thisFinTxactItm)");
            updateFinTxact1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateIdYFieldBtn")
    public void onUpdateIdYFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdYFieldBtn";
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
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateId2Calc(thisFinTxactItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdZFieldBtn")
    public void onUpdateIdZFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdZFieldBtn";
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

    @Subscribe("updateFinTxact1_Id_GenChan1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_How1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_How1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

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

        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

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

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxact1_Id_DescPat1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_DescPat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_DescPat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn";
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

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_How1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_How1_IdFieldListBtn";
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
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_What1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_What1_IdFieldListBtn";
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
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Why1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_Why1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_DescPat1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_DescPat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_DescPat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxact1_Id_FinTxactSet1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1FinTxactItm1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxactSet1_Id_Desc1FinTxactItm1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm1.load() ");

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

        colLoadrFinStmt.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmt.load() ");

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

    @Subscribe("updateFinTaxLne1_IdFieldBtn")
    public void onUpdateFinTaxLne1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldBtn";
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
    public void onUpdateFinTaxLne1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtn";
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
    public void onUpdateFinDept1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinDept1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrSysFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtFinTxactItm1_IdFieldListBtn")
    public void onUpdateAmtFinTxactItm1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxactItm1_IdFieldListBtn";
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

    @Subscribe("updateCalcAmtNetBtn")
    public void onUpdateCalcAmtNetBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateCalcAmtNetBtn";
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

    @Subscribe("updateAmtFinFmla1_IdFieldListBtn")
    public void onUpdateAmtFinFmla1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinFmla1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinFmla.load();
        logger.debug(logPrfx + " --- called colLoadrFinFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull UsrNodeBase thisFinTxactItm, Integer finTxactOption, Integer finTxactSetOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItmCalcVals(thisFinTxactItm, finTxactOption) || isChanged;
        isChanged = updateFinTxactCalcVals(thisFinTxactItm, finTxactSetOption) || isChanged;
        isChanged = updateFinTxactSetCalcVals(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactItmCalcVals(@NotNull UsrNodeBase thisFinTxactItm, Integer finTxactOption) {
        String logPrfx = "updateFinTxactItmCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxactItm Object
        isChanged = updateIdTs(thisFinTxactItm) || isChanged;
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

    private Boolean updateIdParts(@NotNull UsrNodeBase thisFinTxactItm) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateTs1(thisFinTxactItm) || isChanged;
        isChanged = updateTs2(thisFinTxactItm) || isChanged;
        isChanged = updateIdX(thisFinTxactItm)  || isChanged;
        isChanged = updateIdY(thisFinTxactItm)  || isChanged;
        isChanged = updateIdZ(thisFinTxactItm)  || isChanged;
        isChanged = updateIdTs(thisFinTxactItm)  || isChanged;

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
            String id2Qry = "select count(e) from enty_UsrFinTxactItm e where e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
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

    private Boolean updateDesc1(@NotNull UsrNodeBase thisFinTxactItm) {
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
        if (!thisAmt.equals("")) {
            thisAmt = thisAmt.trim();
        }
        logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

        String thisCurcy = "";
        if (thisFinTxactItm.getSysFinCurcy1_Id() != null) {
            thisCurcy = Objects.toString(thisFinTxactItm.getSysFinCurcy1_Id().getId2(), "");
        }
        logger.debug(logPrfx + " --- thisCurcy: " + thisCurcy);

        String thisStmtItm1_Desc1 = Objects.toString(thisFinTxactItm.getFinStmtItm1_Desc1(), "");
        String thisStmtItm1_Desc2 = Objects.toString(thisFinTxactItm.getFinStmtItm1_Desc2(), "");
        String thisStmtItm1_Desc3 = Objects.toString(thisFinTxactItm.getFinStmtItm1_Desc3(), "");
        List<String> list1 = Arrays.asList(
                  thisStmtItm1_Desc1
                , thisStmtItm1_Desc2
        );
        String thisStmtItm1_Desc = list1.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
        if (!thisStmtItm1_Desc.equals("")) {
            thisStmtItm1_Desc = "for " + thisStmtItm1_Desc;
        }
        logger.debug(logPrfx + " --- thisStmtItm1_Desc: " + thisStmtItm1_Desc);

        String thisAcct = "";
        if (thisFinTxactItm.getFinAcct1_Id() != null) {
            thisAcct = Objects.toString(thisFinTxactItm.getFinAcct1_Id().getId2(), "");
        }
        if (!thisAcct.equals("")) {
            thisAcct = "on acct [" + thisAcct + "]";
        }
        logger.debug(logPrfx + " --- thisAcct: " + thisAcct);

        String thisDept = "";
        if (thisFinTxactItm.getFinDept1_Id() != null) {
            thisDept = Objects.toString(thisFinTxactItm.getFinDept1_Id().getId2(), "");
        }
        if (!thisDept.equals("")) {
            thisDept = "on dept [" + thisDept + "]";
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
            thisFinTxactItm.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
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

    private Boolean updateFinTxact1_Id_FinTxactSet1_Id_Desc1(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id_FinTxactSet1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxactSet = thisFinTxactItm.getFinTxact1_Id() == null ? null : thisFinTxactItm.getFinTxact1_Id().getFinTxactSet1_Id();

        if (thisFinTxactSet != null) {
            //finTxactSet is a 2nd ref (ref of a ref) of finTxactItm and is not automatically loaded into the dataContext
            UsrNodeBase thisTrackedTxset = dataContext.merge(thisFinTxactSet);
            thisFinTxactSet = thisTrackedTxset;

            String desc1_ = thisFinTxactSet.getDesc1();

            if (thisFinTxactSet.getDesc1GenFmla1_Id() == null) {

                //thisType
                String thisType = "";
                if (thisFinTxactSet.getType1_Id() != null) {
                    thisType = Objects.toString(thisFinTxactSet.getType1_Id().getId2(), "");
                }
                logger.debug(logPrfx + " --- thisType: " + thisType);

                String desc1FinTxactItm1_Id2 = null;
                switch (thisType) {
                    case "/Txfer-Exch":
                        desc1FinTxactItm1_Id2 = thisFinTxactSet.getId2()+ "/Y01/Z00";
                        break;
                    default:
                        desc1FinTxactItm1_Id2 = thisFinTxactSet.getId2()+ "/Y00/Z00";
                }
                UsrNodeBase desc1FinTxactItm1 = thisFinTxactSet.getDesc1Node1_Id() == null
                        ? findFinTxactItmById2(desc1FinTxactItm1_Id2)
                        : thisFinTxactSet.getDesc1Node1_Id();

                //thisAmt
                String thisAmt = "";
                if (desc1FinTxactItm1 != null){
                    if (desc1FinTxactItm1.getAmtDebt() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtDebt();
                    }else if (desc1FinTxactItm1.getAmtCred() != null) {
                        thisAmt = thisAmt + "" + desc1FinTxactItm1.getAmtCred();
                    }
                    if (!thisAmt.equals("")) {
                        thisAmt = thisAmt.trim();
                    }
                }
                logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

                //thisCurcy
                String thisCurcy = "";
                if (desc1FinTxactItm1 != null) {
                    if (desc1FinTxactItm1.getSysFinCurcy1_Id() != null) {
                        thisCurcy = Objects.toString(desc1FinTxactItm1.getSysFinCurcy1_Id().getId2(), "");
                    }
                }
                logger.debug(logPrfx + " --- thisCurcy: " + thisCurcy);


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
                    thisHow = "via " + thisHow;
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
                    thisFinTxactSet.setDesc1(desc1);
                    logger.debug(logPrfx + " --- desc1: " + desc1);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdTs(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdTs";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateNm1s1Inst1Ts1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateTs1(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateTs1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateTs1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateTs2(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateTs2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateTs2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateNm1s1Inst1Int1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdY(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdY";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateNm1s1Inst1Int2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdZ(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdZ";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateNm1s1Inst1Int3();

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
            case 1: // Link to Exist Txact
                finTxact1_Id = findFinTxactById2(thisFinTxactItm.getFinTxact1_Id2Trgt());
                if (!Objects.equals(finTxact1_Id_, finTxact1_Id)){
                    thisFinTxactItm.setFinTxact1_Id(finTxact1_Id);
                    isChanged = true;
                }
                break;
            case 2: // Link to Exist/New Txact
                finTxact1_Id = findFinTxactById2(thisFinTxactItm.getFinTxact1_Id2Trgt());
                if (finTxact1_Id == null) {
                    finTxact1_Id = createFinTxactFrFinTxactItm(thisFinTxactItm);
                }
                if (!Objects.equals(finTxact1_Id_, finTxact1_Id)){
                    thisFinTxactItm.setFinTxact1_Id(finTxact1_Id);
                    isChanged = true;
                }
                break;
            case 3: // Update Exist Txact
                Boolean thisFinTxactIsChanged = false;
                UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
                if (thisFinTxact != null){
                    if (!Objects.equals(thisFinTxact.getNm1s1Inst1Ts1().getElTs(), thisFinTxactItm.getNm1s1Inst1Ts1().getElTs())){
                        thisFinTxact.getTs1().setElTs(thisFinTxactItm.getNm1s1Inst1Ts1().getElTs());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getNm1s1Inst1Int1(), thisFinTxactItm.getNm1s1Inst1Int1())){
                        thisFinTxact.setNm1s1Inst1Int1(thisFinTxactItm.getNm1s1Inst1Int1());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getNm1s1Inst1Int2(), thisFinTxactItm.getNm1s1Inst1Int2())){
                        thisFinTxact.setNm1s1Inst1Int2(thisFinTxactItm.getNm1s1Inst1Int2());
                        thisFinTxactIsChanged = true;
                    }
                    if (thisFinTxactIsChanged){
                        thisFinTxact.updateNm1s1Inst1Ts1();
                        thisFinTxact.setId2Calc(thisFinTxact.getId2CalcFrFields());
                        thisFinTxact.setId2(thisFinTxact.getId2Calc());
                        dataManager.save(thisFinTxact);
                    }
                }
                break;
        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxact1_Id2Trgt(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String finTxact1_Id2Trgt_ = thisFinTxactItm.getFinTxact1_Id2Trgt();
        String finTxact1_Id2Trgt = thisFinTxactItm.getId2CalcFrFields().substring(0, 24);
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
            String qry1 = "select count(e.amtNet) from enty_UsrFinTxactItm e where e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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
            String qry1 = "select sum(e.amtDebt) from enty_UsrFinTxactItm e where e.finTxact1_Id = :finTxact1_Id";
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
            String qry1 = "select sum(e.amtCred) from enty_UsrFinTxactItm e where e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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

    private UsrNodeBase findFinTxactItmById2(@NotNull String finTxactItm_Id2) {
        String logPrfx = "findFinTxactItmById2";
        logger.trace(logPrfx + " --> ");

        if (finTxactItm_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactItm_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxactItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrFinTxactItme where e.id2 = :id2";
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

    private UsrNodeBase findFinTxactById2(@NotNull String finTxact_Id2) {
        String logPrfx = "findFinTxactById2";
        logger.trace(logPrfx + " --> ");

        if (finTxact_Id2 == null) {
            logger.debug(logPrfx + " --- finTxact_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxact_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrFinTxact e where e.id2 = :id2";
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

        String qry = "select e from enty_UsrFinTxact e where e.id2 = :id2";
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
            ts1.setElTs(thisFinTxactItm.getNm1s1Inst1Ts1().getElTs());
            newFinTxact.setTs1(ts1);
            newFinTxact.updateNm1s1Inst1Ts1();

            newFinTxact.setNm1s1Inst1Int1(thisFinTxactItm.getNm1s1Inst1Int1());
            newFinTxact.setNm1s1Inst1Int2(thisFinTxactItm.getNm1s1Inst1Int2());

            newFinTxact.setId2Calc(newFinTxact.getId2CalcFrFields());
            newFinTxact.setId2(newFinTxact.getId2Calc());


            UsrNodeBase savedFinTxact = dataManager.save(newFinTxact);

            UsrNodeBase mergedFinTxact = dataContext.merge(savedFinTxact);
            logger.debug(logPrfx + " --- created FinTxactItm id: " + mergedFinTxact.getId());
            notifications.create().withCaption("Created FinTxactItm with id2:" + mergedFinTxact.getId2()).show();

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
                case 1: // Link to Exist Txset
                    finTxactSet1_Id = findFinTxactSetById2(thisFinTxact.getFinTxactSet1_Id2Trgt());
                    if (!Objects.equals(finTxactSet1_Id_, finTxactSet1_Id)){
                        isChanged = true;
                        thisFinTxact.setFinTxactSet1_Id(finTxactSet1_Id);
                    }
                    break;
                case 2: // Link to Exist/New Txset
                    finTxactSet1_Id = findFinTxactSetById2(thisFinTxact.getFinTxactSet1_Id2Trgt());
                    if (finTxactSet1_Id == null) {
                        finTxactSet1_Id = createFinTxactSetFrFinTxactItm(thisFinTxactItm);
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
                        if (!Objects.equals(thisFinTxactSet.getNm1s1Inst1Ts1().getElTs(), thisFinTxactItm.getNm1s1Inst1Ts1().getElTs())) {
                            thisFinTxactSet.getTs1().setElTs(thisFinTxactItm.getNm1s1Inst1Ts1().getElTs());
                            thisFinTxactSetIsChanged = true;
                        }
                        if (!Objects.equals(thisFinTxactSet.getNm1s1Inst1Int1(), thisFinTxactItm.getNm1s1Inst1Int1())) {
                            thisFinTxactSet.setNm1s1Inst1Int1(thisFinTxactItm.getNm1s1Inst1Int1());
                            thisFinTxactSetIsChanged = true;
                        }
                        if (thisFinTxactSetIsChanged) {
                            thisFinTxactSet.updateNm1s1Inst1Ts1();
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

    private Boolean updateFinTxact1_FinTxactSet1_Id2Trgt(@NotNull UsrNodeBase thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_FinTxactSet1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNodeBase thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null){
            String id2Calc_ = thisFinTxactItm.getId2Calc();
            String id2Calc = thisFinTxactItm.getId2CalcFrFields().substring(0, 20);
            if (!Objects.equals(id2Calc_, id2Calc)){
                thisFinTxact.setFinTxactSet1_Id2Trgt(id2Calc);
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private UsrNodeBase findFinTxactSetById2(@NotNull String finTxactSet_Id2) {
        String logPrfx = "findFinTxactSetById2";
        logger.trace(logPrfx + " --> ");

        if (finTxactSet_Id2 == null) {
            logger.debug(logPrfx + " --- finTxactSet_Id2 is null.");
            notifications.create().withCaption("finTxactSet_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrFinTxactSete where e.id2 = :id2";
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

            String qry = "select e from enty_UsrFinTxactSet e where e.id2 = :id2";
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
                ts1.setElTs(thisFinTxactItm.getNm1s1Inst1Ts1().getElTs());
                newFinTxactSet.setTs1(ts1);
                newFinTxactSet.updateNm1s1Inst1Ts1();

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


    private Boolean updateAmtFinTxactItm1_EI1_Rate(@NotNull UsrNodeBase thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtFinTxactItm1_EI1_Rate";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal rate_ = thisFinTxactItm.getAmtFinTxactItm1_EI1_Rate();

        UsrFinFmla amtFinFmla1 = thisFinTxactItm.getAmtFinFmla1_Id();
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

        UsrNodeBase amtFinTxactItm1 = thisFinTxactItm.getAmtFinTxactItm1_Id();
        if (amtFinTxactItm1 == null) {
            logger.debug(logPrfx + " --- amtFinTxactItm1: null");
            notifications.create().withCaption("Unable to get reference to the other FinTxactItm. Please ensure amtFinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        SysNodeBase curcyFr = thisFinTxactItm.getAmtFinTxactItm1_Id().getSysFinCurcy1_Id();
        if (curcyFr == null) {
            logger.debug(logPrfx + " --- curcyFr: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxactItm. Please ensure a FinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- curcyFr.Id: " + curcyFr.getId());
        }

        SysNodeBase curcyTo = thisFinTxactItm.getSysFinCurcy1_Id();
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
                .appendPattern("yyyyMMdd")
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
                            + " and   e.ts1.date1 = :date1"
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
                                + " and   e.ts1.date1 = :date1"
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
                rate = curcyExchRate.getAmt1();
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

        UsrFinFmla amtFinFmla1 = thisFinTxactItm.getAmtFinFmla1_Id();
        if (amtFinFmla1 == null) {
            logger.debug(logPrfx + " --- amtFinFmla1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- amtFinFmla1: " + amtFinFmla1.getId());
        }

        UsrNodeBase prev1FinTxactItm;
        UsrNodeBase prev2FinTxactItm;

        // Switch statement over above string
        switch (amtFinFmla1.getId2()) {

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

    public Boolean updateAmtNet(@NotNull UsrNodeBase thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal netAmt_ = thisFinTxactItm.getAmtNet();

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

        BigDecimal netAmt = BigDecimal.valueOf(0);
        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();

        if (thisFinTxactItm.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                netAmt = netAmt.subtract(thisFinTxactItm.getAmtDebt());
            } else {
                netAmt = netAmt.add(thisFinTxactItm.getAmtDebt());
            }
        }

        if (thisFinTxactItm.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                netAmt = netAmt.subtract(thisFinTxactItm.getAmtCred());
            } else {
                netAmt = netAmt.add(thisFinTxactItm.getAmtCred());
            }
        }
        if(!Objects.equals(netAmt_, netAmt)){
            thisFinTxactItm.setAmtNet(netAmt);
            logger.debug(logPrfx + " --- amtNet: " + netAmt);
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
            logger.debug(logPrfx + " --- finTaxLne.id2: " + finTaxLne.getId2());
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

            String qry = "select e from enty_UsrFinTxactItm e"
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

        whatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whatText1Field.setOptionsList()");

        finTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_WhatText1Field.setOptionsList()");

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

        whyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whyText1Field.setOptionsList()");


        finTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_WhatText1Field.setOptionsList()");


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

        finTxact1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finTxact1_EI1_RoleField.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinStmtItm1_Desc1List(){
        String logPrfx = "reloadFinStmtItm1_Desc1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc1"
                + " from enty_UsrFinTxactItm e"
                + " where e.finStmtItm1_Desc1 is not null"
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

        finStmtItm1_Desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Desc2List(){
        String logPrfx = "reloadFinStmtItm1_Desc2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc2"
                + " from enty_UsrFinTxactItm e"
                + " where e.finStmtItm1_Desc2 is not null"
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

        finStmtItm1_Desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc2Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Desc3List(){
        String logPrfx = "reloadFinStmtItm1_Desc3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc3"
                + " from enty_UsrFinTxactItm e"
                + " where e.finStmtItm1_Desc3 is not null"
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

        finStmtItm1_Desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc3Field.setOptionsList()");

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

}