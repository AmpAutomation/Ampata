package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.*;
import ca.ampautomation.ampata.entity.sys.SysNode;
import ca.ampautomation.ampata.entity.usr.*;
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

@UiController("ampata_FinTxactItm.edit")
@UiDescriptor("fin-txact-itm-edit.xml")
@EditedEntityContainer("finTxactItmDc")
public class FinTxactItmEdit extends StandardEditor<UsrNode> {

    @Autowired
    protected UiComponents uiComponents;

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
    protected ComboBox<Integer> updateInstItemCalcValsTxactOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxsetOption;


    @Autowired
    private InstanceContainer<UsrNode> finTxactItmDc;

    private CollectionContainer<UsrNodeType> finTxactItmTypesDc;

    private CollectionLoader<UsrNodeType> finTxactItmTypesDl;


    private CollectionContainer<UsrNode> genChansDc;

    private CollectionLoader<UsrNode> genChansDl;


    private CollectionContainer<UsrNode> genDocVersDc;

    private CollectionLoader<UsrNode> genDocVersDl;


    private CollectionContainer<UsrNode> genTagsDc;

    private CollectionLoader<UsrNode> genTagsDl;


    private CollectionContainer<UsrNode> finTxactsDc;

    private CollectionLoader<UsrNode> finTxactsDl;


    private CollectionContainer<UsrNodeType> finTxactTypesDc;

    private CollectionLoader<UsrNodeType> finTxactTypesDl;


    private CollectionContainer<UsrNode> finTxsetsDc;

    private CollectionLoader<UsrNode> finTxsetsDl;


    private CollectionContainer<UsrNodeType> finTxsetTypesDc;

    private CollectionLoader<UsrNodeType> finTxsetTypesDl;


    private CollectionContainer<UsrNode> finStmtsDc;

    private CollectionLoader<UsrNode> finStmtsDl;


    private CollectionContainer<UsrNode> finDeptsDc;

    private CollectionLoader<UsrNode> finDeptsDl;


    private CollectionContainer<UsrNode> finTaxLnesDc;

    private CollectionLoader<UsrNode> finTaxLnesDl;


    private CollectionContainer<UsrNode> finAcctsDc;

    private CollectionLoader<UsrNode> finAcctsDl;


    private CollectionContainer<UsrNode> finCurcysDc;

    private CollectionLoader<UsrNode> finCurcysDl;


    private CollectionContainer<UsrNode> finTxactItm1sDc;

    private CollectionLoader<UsrNode> finTxactItm1sDl;


    private CollectionContainer<UsrFinFmla> finFmlasDc;

    private CollectionLoader<UsrFinFmla> finFmlasDl;


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
    private ComboBox<String> finTxact1_EI1_RoleField;

    @Autowired
    private EntityComboBox<UsrNode> genChan1_IdField;

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
    private EntityComboBox<UsrGenPat> desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> desc1FinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genTag1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genTag2_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genTag3_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genTag4_IdField;



    @Autowired
    private EntityComboBox<UsrNode> finStmt1_IdField;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc1Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc2Field;

    @Autowired
    private ComboBox<String> finStmtItm1_Desc3Field;


    @Autowired
    private EntityComboBox<UsrNode> finTaxLne1_IdField;

    @Autowired
    private ComboBox<String> finTaxLne1_CodeField;

    @Autowired
    private EntityComboBox<UsrNode> finAcct1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finDept1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> amtFinTxactItm1_IdField;

    @Autowired
    private EntityComboBox<UsrFinFmla> amtFinFmla1_IdField;


    // FinTxact
    @Autowired
    private EntityComboBox<UsrNode> finTxact1_IdField;

    @Autowired
    private TextField<String> finTxact1_Id2TrgtField;

    @Autowired
    private EntityComboBox<UsrNodeType> finTxact1_Id_Type1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxset1_EI1_RoleField;

    @Autowired
    private EntityComboBox<UsrNode> finTxact1_Id_GenChan1_IdField;

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
    private EntityComboBox<UsrGenPat> finTxact1_Id_Desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finTxact1_Id_Desc1FinTxactItm1_IdField;

    // FinTxset
    @Autowired
    private EntityComboBox<UsrNode> finTxact1_Id_FinTxset1_IdField;

    @Autowired
    private TextField<String> finTxact1_Id_FinTxset1_Id2TrgtField;

    @Autowired
    private EntityComboBox<UsrNodeType> finTxact1_Id_FinTxset1_Id_Type1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finTxact1_Id_FinTxset1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrFinHow> finTxact1_Id_FinTxset1_Id_FinHow1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxset1_Id_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhat> finTxact1_Id_FinTxset1_Id_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> finTxact1_Id_FinTxset1_Id_WhyText1Field;

    @Autowired
    private EntityComboBox<UsrFinWhy> finTxact1_Id_FinTxset1_Id_FinWhy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenPat> finTxact1_Id_FinTxset1_Id_Desc1GenPat1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdField;

    Logger logger = LoggerFactory.getLogger(FinTxactItmBrowse2.class);

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


        finTxactItmTypesDc = dataComponents.createCollectionContainer(UsrNodeType.class);
        finTxactItmTypesDl = dataComponents.createCollectionLoader();
        finTxactItmTypesDl.setQuery("select e from ampata_UsrNodeType e where e.className = 'FinTxactItm' order by e.id2");
        FetchPlan finTxactItmTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactItmTypesDl.setFetchPlan(finTxactItmTypesFp);
        finTxactItmTypesDl.setContainer(finTxactItmTypesDc);
        finTxactItmTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(finTxactItmTypesDc);


        genChansDc = dataComponents.createCollectionContainer(UsrNode.class);
        genChansDl = dataComponents.createCollectionLoader();
        genChansDl.setQuery("select e from ampata_UsrNode e where e.className = 'GenChan' order by e.id2");
        FetchPlan genChansFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genChansDl.setFetchPlan(genChansFp);
        genChansDl.setContainer(genChansDc);
        genChansDl.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(genChansDc);
        finTxact1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        finTxact1_Id_FinTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);


        finHowsDc = dataComponents.createCollectionContainer(UsrFinHow.class);
        finHowsDl = dataComponents.createCollectionLoader();
        finHowsDl.setQuery("select e from ampata_FinHow e order by e.id2");
        FetchPlan finHowsFp = fetchPlans.builder(UsrFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finHowsDl.setFetchPlan(finHowsFp);
        finHowsDl.setContainer(finHowsDc);
        finHowsDl.setDataContext(getScreenData().getDataContext());

        finHow1_IdField.setOptionsContainer(finHowsDc);
        finTxact1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);
        finTxact1_Id_FinTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);


        finWhatsDc = dataComponents.createCollectionContainer(UsrFinWhat.class);
        finWhatsDl = dataComponents.createCollectionLoader();
        finWhatsDl.setQuery("select e from ampata_FinWhat e order by e.id2");
        FetchPlan finWhatsFp = fetchPlans.builder(UsrFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finWhatsDl.setFetchPlan(finWhatsFp);
        finWhatsDl.setContainer(finWhatsDc);
        finWhatsDl.setDataContext(getScreenData().getDataContext());

        finWhat1_IdField.setOptionsContainer(finWhatsDc);
        finTxact1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);
        finTxact1_Id_FinTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);


        finWhysDc = dataComponents.createCollectionContainer(UsrFinWhy.class);
        finWhysDl = dataComponents.createCollectionLoader();
        finWhysDl.setQuery("select e from ampata_FinWhy e order by e.id2");
        FetchPlan finWhysFp = fetchPlans.builder(UsrFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finWhysDl.setFetchPlan(finWhysFp);
        finWhysDl.setContainer(finWhysDc);
        finWhysDl.setDataContext(getScreenData().getDataContext());

        finWhy1_IdField.setOptionsContainer(finWhysDc);
        finTxact1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);
        finTxact1_Id_FinTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);


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
        finTxact1_Id_Desc1GenPat1_IdField.setOptionsContainer(genPatsDc);
        finTxact1_Id_FinTxset1_Id_Desc1GenPat1_IdField.setOptionsContainer(genPatsDc);


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
        genTag2_IdField.setOptionsContainer(genTagsDc);
        genTag3_IdField.setOptionsContainer(genTagsDc);
        genTag4_IdField.setOptionsContainer(genTagsDc);


        finTxactsDc = dataComponents.createCollectionContainer(UsrNode.class);
        finTxactsDl = dataComponents.createCollectionLoader();
        finTxactsDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactsDl.setFetchPlan(finTxactsFp);
        finTxactsDl.setContainer(finTxactsDc);
        finTxactsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_IdField.setOptionsContainer(finTxactsDc);


        finTxactTypesDc = dataComponents.createCollectionContainer(UsrNodeType.class);
        finTxactTypesDl = dataComponents.createCollectionLoader();
        finTxactTypesDl.setQuery("select e from ampata_UsrNodeType e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactTypesDl.setFetchPlan(finTxactTypesFp);
        finTxactTypesDl.setContainer(finTxactTypesDc);
        finTxactTypesDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_Type1_IdField.setOptionsContainer(finTxactTypesDc);


        finTxsetsDc = dataComponents.createCollectionContainer(UsrNode.class);
        finTxsetsDl = dataComponents.createCollectionLoader();
        finTxsetsDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetsDl.setFetchPlan(finTxsetsFp);
        finTxsetsDl.setContainer(finTxsetsDc);
        finTxsetsDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxset1_IdField.setOptionsContainer(finTxsetsDc);


        finTxsetTypesDc = dataComponents.createCollectionContainer(UsrNodeType.class);
        finTxsetTypesDl = dataComponents.createCollectionLoader();
        finTxsetTypesDl.setQuery("select e from ampata_UsrNodeType e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetTypesDl.setFetchPlan(finTxsetTypesFp);
        finTxsetTypesDl.setContainer(finTxsetTypesDc);
        finTxsetTypesDl.setDataContext(getScreenData().getDataContext());

        finTxact1_Id_FinTxset1_Id_Type1_IdField.setOptionsContainer(finTxsetTypesDc);


        finStmtsDc = dataComponents.createCollectionContainer(UsrNode.class);
        finStmtsDl = dataComponents.createCollectionLoader();
        finStmtsDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtsDl.setFetchPlan(finStmtsFp);
        finStmtsDl.setContainer(finStmtsDc);
        finStmtsDl.setDataContext(getScreenData().getDataContext());

        finStmt1_IdField.setOptionsContainer(finStmtsDc);


        finTaxLnesDc = dataComponents.createCollectionContainer(UsrNode.class);
        finTaxLnesDl = dataComponents.createCollectionLoader();
        finTaxLnesDl.setQuery("select e from ampata_UsrNode e where e.className = 'GenDocFrg' order by e.id2");
        FetchPlan finTaxLnesFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTaxLnesDl.setFetchPlan(finTaxLnesFp);
        finTaxLnesDl.setContainer(finTaxLnesDc);
        finTaxLnesDl.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(finTaxLnesDc);


        finAcctsDc = dataComponents.createCollectionContainer(UsrNode.class);
        finAcctsDl = dataComponents.createCollectionLoader();
        finAcctsDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctsDl.setFetchPlan(finAcctsFp);
        finAcctsDl.setContainer(finAcctsDc);
        finAcctsDl.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(finAcctsDc);


        finDeptsDc = dataComponents.createCollectionContainer(UsrNode.class);
        finDeptsDl = dataComponents.createCollectionLoader();
        finDeptsDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinDept' order by e.id2");
        FetchPlan finDeptsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finDeptsDl.setFetchPlan(finDeptsFp);
        finDeptsDl.setContainer(finDeptsDc);
        finDeptsDl.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(finDeptsDc);


        finCurcysDc = dataComponents.createCollectionContainer(UsrNode.class);
        finCurcysDl = dataComponents.createCollectionLoader();
        finCurcysDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinCurcy' order by e.id2");
        FetchPlan finCurcysFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finCurcysDl.setFetchPlan(finCurcysFp);
        finCurcysDl.setContainer(finCurcysDc);
        finCurcysDl.setDataContext(getScreenData().getDataContext());

        finCurcy1_IdField.setOptionsContainer(finCurcysDc);


        finTxactItm1sDc = dataComponents.createCollectionContainer(UsrNode.class);
        finTxactItm1sDl = dataComponents.createCollectionLoader();
        finTxactItm1sDl.setQuery("select e from ampata_UsrNode e where e.className = 'FinTxactItm' order by e.id2");
        FetchPlan finTxactItm1sFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactItm1sDl.setFetchPlan(finTxactItm1sFp);
        finTxactItm1sDl.setContainer(finTxactItm1sDc);
        finTxactItm1sDl.setDataContext(getScreenData().getDataContext());

        desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);
        finTxact1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);
        finTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);
        amtFinTxactItm1_IdField.setOptionsContainer(finTxactItm1sDc);


        finFmlasDc = dataComponents.createCollectionContainer(UsrFinFmla.class);
        finFmlasDl = dataComponents.createCollectionLoader();
        finFmlasDl.setQuery("select e from ampata_FinFmla e order by e.id2");
        FetchPlan finFmlasFp = fetchPlans.builder(UsrFinFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finFmlasDl.setFetchPlan(finFmlasFp);
        finFmlasDl.setContainer(finFmlasDc);
        finFmlasDl.setDataContext(getScreenData().getDataContext());

        amtFinFmla1_IdField.setOptionsContainer(finFmlasDc);


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

        genPatsDl.load();
        logger.debug(logPrfx + " --- called genPat.load() ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");
        reloadFinStmtItm1_Desc1List();
        reloadFinStmtItm1_Desc2List();
        reloadFinStmtItm1_Desc3List();

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



    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxactOption = updateInstItemCalcValsTxactOption.getValue();
        if (finTxactOption == null){
            finTxactOption = 0;}
        Integer finTxsetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxsetOption == null){
            finTxsetOption = 0;}

        updateCalcVals(thisFinTxactItm, finTxactOption, finTxsetOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
            UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
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
            UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

    @Subscribe("updateBeg1Ts1FieldBtn")
    public void onUpdateBeg1Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg1Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
            UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

    @Subscribe("updateBeg2Ts1FieldBtn")
    public void onUpdateBeg2Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg2Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
            UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxactItm)");
            updateId2Calc(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateFinTxact1_Id2Trgt(thisFinTxactItm)");
            updateFinTxact1_Id2Trgt(thisFinTxactItm);

            logger.debug(logPrfx + " --- calling updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxactItm)");
            updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxactItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
            UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
            UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
            if (thisFinTxactItm == null) {
                logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
    public void onFinTxact1_IdFieldValueChange(HasValue.ValueChangeEvent<UsrNode> event) {
        String logPrfx = "onFinTxact1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

    @Subscribe("updateFinTxact1_Id2TrgtBtn")
    public void onUpdateFinTxact1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

    @Subscribe("updateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FinTxactItm1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFintxact1_Id_FinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFintxact1_Id_FinTxactItms1_AmtEqCalc(thisFinTxactItm);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Desc1FieldBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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


    @Subscribe("updateFinTxact1_Id_FinTxset1_Id2TrgtBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id2TrgtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id2TrgtBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

    @Subscribe("updateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtn")
    public void onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxact1_Id_FinTxset1_Id_Desc1FinTxactItm1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

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

    @Subscribe("updateFinTaxLne1_IdFieldBtn")
    public void onUpdateFinTaxLne1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

    @Subscribe("updateAmtFinTxactItm1_IdFieldListBtn")
    public void onUpdateAmtFinTxactItm1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxactItm1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxactItm1sDl.load();
        logger.debug(logPrfx + " --- called finTxactItm1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtFinTxactItm1_EI1_RateBtn")
    public void onUpdateAmtFinTxactItm1_EI1_RateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinTxactItm1_EI1_RateBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
        if (thisFinTxactItm == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
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

        UsrNode thisFinTxactItm = finTxactItmDc.getItemOrNull();
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
    public void onUpdateAmtFinFmla1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtFinFmla1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finFmlasDl.load();
        logger.debug(logPrfx + " --- called finFmlasDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull UsrNode thisFinTxactItm, Integer finTxactOption, Integer finTxsetOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinTxactItmCalcVals(thisFinTxactItm, finTxactOption) || isChanged;
        isChanged = updateFinTxactCalcVals(thisFinTxactItm, finTxsetOption) || isChanged;
        isChanged = updateFinTxsetCalcVals(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactItmCalcVals(@NotNull UsrNode thisFinTxactItm, Integer finTxactOption) {
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

    private Boolean updateFinTxactCalcVals(@NotNull UsrNode thisFinTxactItm, Integer finTxsetOption) {
        String logPrfx = "updateFinTxactCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxact Object
        isChanged = updateFinTxact1_Id_Desc1(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_IdCntCalc(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_AmtDebtSumCalc(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc(thisFinTxactItm)  || isChanged;
        isChanged = updateFintxact1_Id_FinTxactItms1_AmtEqCalc(thisFinTxactItm)  || isChanged;

        isChanged = updateFinTxact1_FinTxset1_Id2Trgt(thisFinTxactItm) || isChanged;
        isChanged = updateFinTxact1_FinTxset1_Id(thisFinTxactItm, finTxsetOption) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxsetCalcVals(@NotNull UsrNode thisFinTxactItm) {
        String logPrfx = "updateFinTxsetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinTxset Object
        isChanged = updateFinTxact1_Id_FinTxset1_Id_Desc1(thisFinTxactItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdParts(@NotNull UsrNode thisFinTxactItm) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateBeg1(thisFinTxactItm) || isChanged;
        isChanged = updateBeg2(thisFinTxactItm) || isChanged;
        isChanged = updateIdX(thisFinTxactItm)  || isChanged;
        isChanged = updateIdY(thisFinTxactItm)  || isChanged;
        isChanged = updateIdZ(thisFinTxactItm)  || isChanged;
        isChanged = updateIdTs(thisFinTxactItm)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull UsrNode thisFinTxactItm) {
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

    private Boolean updateId2Calc(@NotNull UsrNode thisFinTxactItm) {
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

    private Boolean updateId2Cmp(@NotNull UsrNode thisFinTxactItm) {
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

    private Boolean updateId2Dup(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinTxactItm.getId2Dup();
        if (thisFinTxactItm.getId2() != null) {
            String id2Qry = "select count(e) from ampata_UsrNode e where e.className = 'FinTxactItm' and e.id2 = :id2 and e.id <> :id";
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

    private Boolean updateDesc1(@NotNull UsrNode thisFinTxactItm) {
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

    private Boolean updateFinTxact1_Id_Desc1(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();

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

    private Boolean updateFinTxact1_Id_FinTxset1_Id_Desc1(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id_FinTxset1_Id_Desc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxset = thisFinTxactItm.getFinTxact1_Id() == null ? null : thisFinTxactItm.getFinTxact1_Id().getFinTxset1_Id();

        if (thisFinTxset != null) {
            //finTxset is a 2nd ref (ref of a ref) of finTxactItm and is not automatically loaded into the dataContext
            UsrNode thisTrackedTxset = dataContext.merge(thisFinTxset);
            thisFinTxset = thisTrackedTxset;

            String desc1_ = thisFinTxset.getDesc1();

            if (thisFinTxset.getDesc1GenPat1_Id() == null) {

                //thisType
                String thisType = "";
                if (thisFinTxset.getType1_Id() != null) {
                    thisType = Objects.toString(thisFinTxset.getType1_Id().getId2(), "");
                }
                logger.debug(logPrfx + " --- thisType: " + thisType);

                String desc1FinTxactItm1_Id2 = null;
                switch (thisType) {
                    case "/Txfer-Exch":
                        desc1FinTxactItm1_Id2 = thisFinTxset.getId2()+ "/Y01/Z00";
                        break;
                    default:
                        desc1FinTxactItm1_Id2 = thisFinTxset.getId2()+ "/Y00/Z00";
                }
                UsrNode desc1FinTxactItm1 = thisFinTxset.getDesc1FinTxactItm1_Id() == null
                        ? findFinTxactItmById2(desc1FinTxactItm1_Id2)
                        : thisFinTxset.getDesc1FinTxactItm1_Id();

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


    private Boolean updateIdTs(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdTs";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdTs();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg1(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateBeg1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateBeg1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg2(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateBeg2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateBeg2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdX();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdY(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdY";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdY();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateIdZ(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateIdZ";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinTxactItm.updateIdZ();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxact1_Id(@NotNull UsrNode thisFinTxactItm, Integer finTxactOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode finTxact1_Id_ = thisFinTxactItm.getFinTxact1_Id();
        UsrNode finTxact1_Id;
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
                UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
                if (thisFinTxact != null){
                    if (!Objects.equals(thisFinTxact.getIdTs().getTs1(), thisFinTxactItm.getIdTs().getTs1())){
                        thisFinTxact.getBeg1().setTs1(thisFinTxactItm.getIdTs().getTs1());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getIdX(), thisFinTxactItm.getIdX())){
                        thisFinTxact.setIdX(thisFinTxactItm.getIdX());
                        thisFinTxactIsChanged = true;
                    }
                    if (!Objects.equals(thisFinTxact.getIdY(), thisFinTxactItm.getIdY())){
                        thisFinTxact.setIdY(thisFinTxactItm.getIdY());
                        thisFinTxactIsChanged = true;
                    }
                    if (thisFinTxactIsChanged){
                        thisFinTxact.updateIdTs();
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

    private Boolean updateFinTxact1_Id2Trgt(@NotNull UsrNode thisFinTxactItm) {
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

    private Boolean updateFintxact1_Id_FinTxactItms1_IdCntCalc(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            Integer idCntCalc_ = thisFinTxact.getFinTxactItms1_IdCntCalc();
            Integer idCntCalc = null ;
            String qry1 = "select count(e.amtNet) from ampata_UsrNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id group by e.finTxact1_Id";
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



    private Boolean updateFintxact1_Id_FinTxactItms1_AmtDebtSumCalc(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_DebtSum";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
            BigDecimal amtDebtSumCalc_ = thisFinTxact.getFinTxactItms1_AmtDebtSumCalc();
            BigDecimal amtDebtSumCalc = null ;
            String qry1 = "select sum(e.amtDebt) from ampata_UsrNode e where e.className = 'FinTxactItm' and e.finTxact1_Id = :finTxact1_Id";
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



    private Boolean updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null) {
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
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }




    private Boolean updateFintxact1_Id_FinTxactItms1_AmtEqCalc(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFintxact1_Id_FinTxactItms1_AmtEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
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

    private UsrNode findFinTxactById2(@NotNull String finTxact_Id2) {
        String logPrfx = "findFinTxactById2";
        logger.trace(logPrfx + " --> ");

        if (finTxact_Id2 == null) {
            logger.debug(logPrfx + " --- finTxact_Id2 is null, finTxact_Id2.");
            notifications.create().withCaption("finTxact_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_UsrNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        UsrNode finTxact1_Id = null;
        try {
            finTxact1_Id = dataManager.load(UsrNode.class)
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

    private UsrNode createFinTxactFrFinTxactItm(@NotNull UsrNode thisFinTxactItm) {
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

        String qry = "select e from ampata_UsrNode e where e.className = 'FinTxact' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finTxact_Id2);

        try {
            UsrNode finTxact = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", finTxact_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
            logger.trace(logPrfx + " <-- ");
            return finTxact;

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

            UsrNode newFinTxact = dataManager.create(UsrNode.class);
            newFinTxact.setClassName("FinTxact");

            HasTmst beg1 = dataManager.create(HasTmst.class);
            beg1.setTs1(thisFinTxactItm.getIdTs().getTs1());
            newFinTxact.setBeg1(beg1);
            newFinTxact.updateIdTs();

            newFinTxact.setIdX(thisFinTxactItm.getIdX());
            newFinTxact.setIdY(thisFinTxactItm.getIdY());

            newFinTxact.setId2Calc(newFinTxact.getId2CalcFrFields());
            newFinTxact.setId2(newFinTxact.getId2Calc());


            UsrNode savedFinTxact = dataManager.save(newFinTxact);

            UsrNode mergedFinTxact = dataContext.merge(savedFinTxact);
            logger.debug(logPrfx + " --- created FinTxactItm id: " + mergedFinTxact.getId());
            notifications.create().withCaption("Created FinTxactItm with id2:" + mergedFinTxact.getId2()).show();

            logger.trace(logPrfx + " <-- ");
            return mergedFinTxact;
        }
    }


    private Boolean updateFinTxact1_FinTxset1_Id(@NotNull UsrNode thisFinTxactItm, Integer finTxsetOption) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_FinTxset1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
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
                        finTxset1_Id = createFinTxsetFrFinTxactItm(thisFinTxactItm);
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
                        if (!Objects.equals(thisFinTxset.getIdTs().getTs1(), thisFinTxactItm.getIdTs().getTs1())) {
                            thisFinTxset.getBeg1().setTs1(thisFinTxactItm.getIdTs().getTs1());
                            thisFinTxsetIsChanged = true;
                        }
                        if (!Objects.equals(thisFinTxset.getIdX(), thisFinTxactItm.getIdX())) {
                            thisFinTxset.setIdX(thisFinTxactItm.getIdX());
                            thisFinTxsetIsChanged = true;
                        }
                        if (thisFinTxsetIsChanged) {
                            thisFinTxset.updateIdTs();
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

    private Boolean updateFinTxact1_FinTxset1_Id2Trgt(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTxact1_FinTxset1_Id2Trgt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode thisFinTxact = thisFinTxactItm.getFinTxact1_Id();
        if (thisFinTxact != null){
            String id2Calc_ = thisFinTxactItm.getId2Calc();
            String id2Calc = thisFinTxactItm.getId2CalcFrFields().substring(0, 20);
            if (!Objects.equals(id2Calc_, id2Calc)){
                isChanged = true;
                thisFinTxact.setFinTxset1_Id2Trgt(id2Calc);
                logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private UsrNode findFinTxsetById2(@NotNull String finTxset_Id2) {
        String logPrfx = "findFinTxsetById2";
        logger.trace(logPrfx + " --> ");

        if (finTxset_Id2 == null) {
            logger.debug(logPrfx + " --- finTxset_Id2 is null.");
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
            // notifications.create().withCaption("finTxset with id2: " + finTxset1_Id2Trgt + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finTxset1_Id;

    }

    private UsrNode createFinTxsetFrFinTxactItm(@NotNull UsrNode thisFinTxactItm) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "createFinTxsetFrFinTxactItm";
        logger.trace(logPrfx + " --> ");

        UsrNode mergedFinTxset = null;
        if (thisFinTxactItm.getFinTxact1_Id() != null) {
            String finTxset_Id2 = thisFinTxactItm.getFinTxact1_Id().getFinTxset1_Id2Trgt();
            if (finTxset_Id2 == null) {
                logger.debug(logPrfx + " --- finTxset_Id2 is null.");
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
                beg1.setTs1(thisFinTxactItm.getIdTs().getTs1());
                newFinTxset.setBeg1(beg1);
                newFinTxset.updateIdTs();

                newFinTxset.setIdX(thisFinTxactItm.getIdX());

                newFinTxset.setId2Calc(newFinTxset.getId2CalcFrFields());
                newFinTxset.setId2(newFinTxset.getId2Calc());


                UsrNode savedFinTxset = dataManager.save(newFinTxset);

                mergedFinTxset = dataContext.merge(savedFinTxset);
                logger.debug(logPrfx + " --- created FinTxset id: " + mergedFinTxset.getId());
                notifications.create().withCaption("Created FinTxset with id2:" + mergedFinTxset.getId2()).show();
            }

        }
        logger.trace(logPrfx + " <-- ");
        return mergedFinTxset;
    }


    private Boolean updateAmtFinTxactItm1_EI1_Rate(@NotNull UsrNode thisFinTxactItm) {
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

        UsrNode amtFinTxactItm1 = thisFinTxactItm.getAmtFinTxactItm1_Id();
        if (amtFinTxactItm1 == null) {
            logger.debug(logPrfx + " --- amtFinTxactItm1: null");
            notifications.create().withCaption("Unable to get reference to the other FinTxactItm. Please ensure amtFinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal rate = BigDecimal.valueOf(0);
        boolean qryRsltGood = false;

        SysNode curcyFr = thisFinTxactItm.getAmtFinTxactItm1_Id().getSysFinCurcy1_Id();
        if (curcyFr == null) {
            logger.debug(logPrfx + " --- curcyFr: null");
            notifications.create().withCaption("Unable to get the currency from the other FinTxactItm. Please ensure a FinTxactItm1_Id is selected and it has a currency is selected.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        } else {
            logger.debug(logPrfx + " --- curcyFr.Id: " + curcyFr.getId());
        }

        SysNode curcyTo = thisFinTxactItm.getSysFinCurcy1_Id();
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

        SysNode curcyExchRate;
        try {
            curcyExchRate = dataManager.load(SysNode.class)
                    .query("select e from ampata_SysNode e"
                            + " where e.finCurcy1_Id.id = :curcyFr"
                            + " and   e.finCurcy2_Id.id = :curcyTo"
                            + " and   e.beg1.date1 = :date1"
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

                curcyExchRate = dataManager.load(SysNode.class)
                        .query("select e from ampata_SysNode e "
                                + " where e.finCurcy1_Id.id = :curcyFr"
                                + " and   e.finCurcy2_Id.id = :curcyTo"
                                + " and   e.beg1.date1 = :date1"
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


    private Boolean updateAmtCalc(@NotNull UsrNode thisFinTxactItm) {
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

        UsrNode prev1FinTxactItm;
        UsrNode prev2FinTxactItm;

        // Switch statement over above string
        switch (amtFinFmla1.getId2()) {

            // Ref1 Mult Exch_Rate
            case "Ref1 Mult Exch_Rate":

                UsrNode amtFinTxactItm1 = thisFinTxactItm.getAmtFinTxactItm1_Id();
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

    public Boolean updateAmtNet(@NotNull UsrNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal netAmt_ = thisFinTxactItm.getAmtNet();

        UsrNode thisFinAcct = thisFinTxactItm.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("FinAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        UsrNodeType thisFinAcctType = thisFinAcct.getType1_Id();
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

    private Boolean updateFinTaxLne1_Id(@NotNull UsrNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "updateFinTaxLne1_Id";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        UsrNode finTaxLne_ = thisFinTxactItm.getFinTaxLne1_Id();
        UsrNode finTaxLne = thisFinTxactItm.getFinAcct1_Id() == null ? null :
                thisFinTxactItm.getFinAcct1_Id().getFinTaxLne1_Id();

        if (!Objects.equals(finTaxLne_, finTaxLne)){
            thisFinTxactItm.setFinTaxLne1_Id(finTaxLne);
            logger.debug(logPrfx + " --- finTaxLne.id2: " + finTaxLne.getId2());
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public UsrNode getPrevFinTxactItm(@NotNull UsrNode thisFinTxactItm) {
        //Assume thisFinTxactItm is not null
        String logPrfx = "getPrevFinTxactItm";
        logger.trace(logPrfx + " --> ");

        UsrNode prevFinTxactItm = null;
        boolean qryRsltGood = false;

        if (thisFinTxactItm.getIdZ() != null || thisFinTxactItm.getIdZ() >= 1) {
            prevFinTxactItm = metadataTools.copy(thisFinTxactItm);
            prevFinTxactItm.setIdZ(prevFinTxactItm.getIdZ() - 1);
            String prev1FinTxactItm_Id2 = prevFinTxactItm.getId2CalcFrFields();

            String qry = "select gn from ampata_UsrNode gn"
                    + " where gn.id2 = :id2";

            logger.debug(logPrfx + " --- qry: " + qry);

            try {
                prevFinTxactItm = dataManager.load(UsrNode.class)
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

        whatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whatText1Field.setOptionsList()");

        finTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_WhatText1Field.setOptionsList()");

        finTxact1_Id_FinTxset1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_FinTxset1_Id_WhatText1Field.setOptionsList()");

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

        whyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whyText1Field.setOptionsList()");


        finTxact1_Id_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_WhatText1Field.setOptionsList()");


        finTxact1_Id_FinTxset1_Id_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finTxact1_Id_FinTxset1_Id_WhyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxact1_Id_FinTxset1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_Id_FinTxset1_EI1_RoleList";
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

        finTxact1_Id_FinTxset1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinTxact1_EI1_RoleList(){
        String logPrfx = "reloadFinTxact1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTxact1_EI1_Role"
                + " from ampata_UsrNode e"
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

        finTxact1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called finTxact1_EI1_RoleField.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinStmtItm1_Desc1List(){
        String logPrfx = "reloadFinStmtItm1_Desc1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc1"
                + " from ampata_UsrNode e"
                + " where e.className = 'FinTxactItm'"
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

        finStmtItm1_Desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Desc2List(){
        String logPrfx = "reloadFinStmtItm1_Desc2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc2"
                + " from ampata_UsrNode e"
                + " where e.className = 'FinTxactItm'"
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

        finStmtItm1_Desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc2Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Desc3List(){
        String logPrfx = "reloadFinStmtItm1_Desc3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finStmtItm1_Desc3"
                + " from ampata_UsrNode e"
                + " where e.className = 'FinTxactItm'"
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

        finStmtItm1_Desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc3Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinTaxLne1_CodeList(){
        String logPrfx = "reloadFinTaxLne1_CodeList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTaxLne1_Code"
                + " from ampata_UsrNode e"
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