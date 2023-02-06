package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.UsrNodeRepo;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@UiController("enty_UsrFinStmtItm.main")
@UiDescriptor("usr-fin-stmt-itm-main.xml")
@LookupComponent("tableMain")
public class UsrFinStmtItmMain extends MasterDetailScreen<UsrNode> {

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

    //CRUD Repo
    @Autowired
    private UsrNodeRepo repo;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Desc1;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Desc2;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Desc3;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Desc4;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_FinAcct1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_FinStmt1_Id;

    //Toolbar


    //Template
    @Autowired
    protected EntityComboBox<UsrNodeType> tmplt_Type1_IdField;
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;


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
    protected EntityComboBox<UsrNode> tmplt_FinStmt1_IdField;
    @Autowired
    protected CheckBox tmplt_FinStmt1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_Desc1Field;
    @Autowired
    protected CheckBox tmplt_Desc1FieldChk;

    @Autowired
    protected ComboBox<String> tmplt_Desc2Field;
    @Autowired
    protected CheckBox tmplt_Desc2FieldChk;

    @Autowired
    protected ComboBox<String> tmplt_Desc3Field;
    @Autowired
    protected CheckBox tmplt_Desc3FieldChk;

    @Autowired
    protected ComboBox<String> tmplt_Desc4Field;
    @Autowired
    protected CheckBox tmplt_Desc4FieldChk;

    @Autowired
    protected TextField<String> tmplt_ExchDescField;
    @Autowired
    protected CheckBox tmplt_ExchDescFieldChk;

    @Autowired
    protected TextField<String> tmplt_RefIdField;
    @Autowired
    protected CheckBox tmplt_RefIdFieldChk;

    @Autowired
    protected TextField<BigDecimal> tmplt_AmtDebtField;
    @Autowired
    protected CheckBox tmplt_AmtDebtFieldChk;

    @Autowired
    protected TextField<BigDecimal> tmplt_AmtCredField;
    @Autowired
    protected CheckBox tmplt_AmtCredFieldChk;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNode> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNode> colLoadrFinStmtItm;
    @Autowired
    private InstanceContainer<UsrNode> instCntnrMain;
    @Autowired
    private Table<UsrNode> tableMain;


    //Type data container and loader
    private CollectionContainer<UsrNodeType> colCntnrType;
    private CollectionLoader<UsrNodeType> colLoadrType;


    //Other data loaders and containers
    private CollectionContainer<UsrNode> colCntnrGenDocVer;
    private CollectionLoader<UsrNode> colLoadrGenDocVer;

    private CollectionLoader<UsrNode> colLoadrGenTag;
    private CollectionContainer<UsrNode> colCntnrGenTag;


    private CollectionContainer<UsrNode> colCntnrFinStmt;
    private CollectionLoader<UsrNode> colLoadrFinStmt;


    private CollectionContainer<UsrNode> colCntnrFinAcct;
    private CollectionLoader<UsrNode> colLoadrFinAcct;


    //Field
    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrNodeType> type1_IdField;


    @Autowired
    private ComboBox<String> desc1Field;

    @Autowired
    private ComboBox<String> desc2Field;

    @Autowired
    private ComboBox<String> desc3Field;

    @Autowired
    private ComboBox<String> desc4Field;



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




    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        //filter
        ComboBox<String> propFilterCmpnt_Desc1 = (ComboBox<String>) filterConfig1A_Desc1.getValueComponent();
        propFilterCmpnt_Desc1.setNullOptionVisible(true);
        propFilterCmpnt_Desc1.setNullSelectionCaption("<null>");

        ComboBox<String> propFilterCmpnt_Desc2 = (ComboBox<String>) filterConfig1A_Desc2.getValueComponent();
        propFilterCmpnt_Desc2.setNullOptionVisible(true);
        propFilterCmpnt_Desc2.setNullSelectionCaption("<null>");

        ComboBox<String> propFilterCmpnt_Desc3 = (ComboBox<String>) filterConfig1A_Desc3.getValueComponent();
        propFilterCmpnt_Desc3.setNullOptionVisible(true);
        propFilterCmpnt_Desc3.setNullSelectionCaption("<null>");

        ComboBox<String> propFilterCmpnt_Desc4 = (ComboBox<String>) filterConfig1A_Desc4.getValueComponent();
        propFilterCmpnt_Desc4.setNullOptionVisible(true);
        propFilterCmpnt_Desc4.setNullSelectionCaption("<null>");

        //template
        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_IdXFieldRdo.setOptionsMap(map1);

        tmplt_Desc1Field.setNullOptionVisible(true);
        tmplt_Desc1Field.setNullSelectionCaption("<null>");
        tmplt_Desc2Field.setNullOptionVisible(true);
        tmplt_Desc2Field.setNullSelectionCaption("<null>");
        tmplt_Desc3Field.setNullOptionVisible(true);
        tmplt_Desc3Field.setNullSelectionCaption("<null>");
        tmplt_Desc4Field.setNullOptionVisible(true);
        tmplt_Desc4Field.setNullSelectionCaption("<null>");


        desc1Field.setNullOptionVisible(true);
        desc1Field.setNullSelectionCaption("<null>");
        desc2Field.setNullOptionVisible(true);
        desc2Field.setNullSelectionCaption("<null>");
        desc3Field.setNullOptionVisible(true);
        desc3Field.setNullSelectionCaption("<null>");
        desc4Field.setNullOptionVisible(true);
        desc4Field.setNullSelectionCaption("<null>");


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrNodeType e where e.className = 'UsrFinStmtItm' order by e.id2");
        FetchPlan finStmtItmTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(finStmtItmTypesFp);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        

        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(genDocVersFp);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(genTagsFp);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);
        genTag2_IdField.setOptionsContainer(colCntnrGenTag);
        genTag3_IdField.setOptionsContainer(colCntnrGenTag);
        genTag4_IdField.setOptionsContainer(colCntnrGenTag);


        colCntnrFinStmt = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrFinStmt = dataComponents.createCollectionLoader();
        colLoadrFinStmt.setQuery("select e from enty_UsrNode e where e.className = 'UsrFinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinStmt.setFetchPlan(finStmtsFp);
        colLoadrFinStmt.setContainer(colCntnrFinStmt);
        colLoadrFinStmt.setDataContext(getScreenData().getDataContext());

        finStmt1_IdField.setOptionsContainer(colCntnrFinStmt);
        //template
        tmplt_FinStmt1_IdField.setOptionsContainer(colCntnrFinStmt);
        //filter
        EntityComboBox<UsrNode> propFilterCmpnt_FinStmt1_Id = (EntityComboBox<UsrNode>) filterConfig1A_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_FinStmt1_Id.setOptionsContainer(colCntnrFinStmt);


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrNode e where e.className = 'UsrFinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(finAcctsFp);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<UsrNode> propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNode>) filterConfig1A_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setOptionsContainer(colCntnrFinAcct);

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
    public void onInitEntity(InitEntityEvent<UsrNode> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = event.getEntity();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItm.setClassName("UsrFinStmtItm");
        logger.debug(logPrfx + " --- className: UsrFinStmtItm");

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
    public void onFinStmtItmsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinStmtItmsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = event.getItem();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItm.setClassName("UsrFinStmtItm");
        logger.debug(logPrfx + " --- className: UsrFinStmtItm");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "tableMain.[idDt.date1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDate ts) {
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


        colLoadrGenDocVer.load();
        logger.debug(logPrfx + " --- called colLoadrGenDocVer.load() ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        colLoadrFinStmt.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmt.load() ");

        reloadDesc1List();
        reloadDesc2List();
        reloadDesc3List();
        reloadDesc4List();

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        logger.trace(logPrfx + " <-- ");

    }



    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execFinStmtItmPrUpdNative()");
        repo.execUsrFinStmtItmPrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execFinStmtItmPrUpdNative()");

        logger.debug(logPrfx + " --- executing colLoadrFinStmtItm.load()");
        colLoadrFinStmtItm.load();
        logger.debug(logPrfx + " --- finished colLoadrFinStmtItm.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNode> sels = new ArrayList<>();

        thisFinStmtItms.forEach(orig -> {
            UsrNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

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

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            if (tmplt_FinStmt1_IdFieldChk.isChecked()) {
                copy.setFinStmt1_Id(tmplt_FinStmt1_IdField.getValue());
            }

            if (tmplt_Desc1FieldChk.isChecked()) {
                copy.setDesc1(tmplt_Desc1Field.getValue());
            }

            if (tmplt_Desc2FieldChk.isChecked()) {
                copy.setDesc2(tmplt_Desc2Field.getValue());
            }

            if (tmplt_Desc3FieldChk.isChecked()) {
                copy.setDesc3(tmplt_Desc3Field.getValue());
            }

            if (tmplt_Desc4FieldChk.isChecked()) {
                copy.setDesc4(tmplt_Desc4Field.getValue());
            }

            if (tmplt_ExchDescFieldChk.isChecked()) {
                copy.setExchDesc(tmplt_ExchDescField.getValue());
            }

            if (tmplt_RefIdFieldChk.isChecked()) {
                copy.setRefId(tmplt_RefIdField.getValue());
            }

            if (tmplt_AmtDebtFieldChk.isChecked()) {
                copy.setAmtDebt(tmplt_AmtDebtField.getValue());
            }

            if (tmplt_AmtCredFieldChk.isChecked()) {
                copy.setAmtCred(tmplt_AmtCredField.getValue());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setIdX((orig.getIdX() == null ? 0 : copy.getIdX()) + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            updateCalcVals(copy);

            UsrNode savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        tableMain.sort("id2", Table.SortDirection.ASCENDING);
        try{
            tableMain.setSelected(sels);

        }catch (IllegalArgumentException e){
            logger.trace(logPrfx + " --- IllegalArgumentException");
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinStmtItms.forEach(thisFinStmtItm -> {
            UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
            thisFinStmtItm = thisTrackedFinStmtItm;
            if (thisFinStmtItm != null) {

                Boolean thisFinStmtItmIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.getBeg1().setTs1(tmplt_Beg1Ts1Field.getValue());
                    updateIdDt(thisFinStmtItm);
                }
                if (tmplt_Beg2Ts1FieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.getBeg2().setTs1(tmplt_Beg2Ts1Field.getValue());
                    updateIdDt(thisFinStmtItm);
                }
                LocalDate idDt1 = thisFinStmtItm.getIdDt() != null ? thisFinStmtItm.getIdDt().getDate1() : null;

                Integer idX = thisFinStmtItm.getIdX();
                if (tmplt_IdXFieldRdo.getValue() != null) {
                    // Set
                    if (tmplt_IdXFieldRdo.getValue() == 1) {
                        thisFinStmtItmIsChanged = true;
                        idX = tmplt_IdXField.getValue();
                        thisFinStmtItm.setIdX(idX);
                    }
                    // Max
                    else if (tmplt_IdXFieldRdo.getValue() == 2
                            && idDt1 != null) {
                        thisFinStmtItmIsChanged = true;
                        idX = getIdXMax(idDt1);
                        if (idX == null) return;
                        thisFinStmtItm.setIdX(idX);
                    }
                }


                if (tmplt_FinStmt1_IdFieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setFinStmt1_Id(tmplt_FinStmt1_IdField.getValue());
                }

                if (tmplt_Desc1FieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setDesc1(tmplt_Desc1Field.getValue());
                }

                if (tmplt_Desc2FieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setDesc2(tmplt_Desc2Field.getValue());
                }

                if (tmplt_Desc3FieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setDesc3(tmplt_Desc3Field.getValue());
                }

                if (tmplt_Desc4FieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setDesc3(tmplt_Desc4Field.getValue());
                }

                if (tmplt_ExchDescFieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setExchDesc(tmplt_ExchDescField.getValue());
                }

                if (tmplt_RefIdFieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setRefId(tmplt_RefIdField.getValue());
                }


                if (tmplt_AmtDebtFieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setAmtDebt(tmplt_AmtDebtField.getValue());
                }

                if (tmplt_AmtCredFieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setAmtCred(tmplt_AmtCredField.getValue());
                }

                thisFinStmtItmIsChanged = updateId2Calc(thisFinStmtItm) || thisFinStmtItmIsChanged;
                thisFinStmtItmIsChanged = updateId2(thisFinStmtItm) || thisFinStmtItmIsChanged;
                thisFinStmtItmIsChanged = updateId2Cmp(thisFinStmtItm) || thisFinStmtItmIsChanged;

                if (thisFinStmtItmIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinStmtItm).");
                    //dataManager.save(thisFinStmtItm);
                }

            }
        });
        updateFinStmtItmHelper();
        logger.trace(logPrfx + " <-- ");
    }
    
    private void updateFinStmtItmHelper() {
        String logPrfx = "updateFinStmtItmHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrFinStmtItm.load().");
            colLoadrFinStmtItm.load();

            List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinStmtItms.forEach(thisFinStmtItm -> {
                //UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                if (thisFinStmtItm != null) {
                    UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                    thisFinStmtItm = thisTrackedFinStmtItm;

                    Boolean thisFinStmtItmIsChanged = false;

                    thisFinStmtItmIsChanged = updateId2Dup(thisFinStmtItm) || thisFinStmtItmIsChanged;

                    if (thisFinStmtItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinStmtItm).");
                        //dataManager.save(thisFinStmtItm);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrFinStmtItm.load().");
                colLoadrFinStmtItm.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisFinStmtItms);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tmplt_Desc1Field", subject = "enterPressHandler")
    private void tmplt_Desc1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_Desc1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_Desc2Field", subject = "enterPressHandler")
    private void tmplt_Desc2FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_Desc2FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_Desc3Field", subject = "enterPressHandler")
    private void tmplt_Desc3FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_Desc3FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_Desc4Field", subject = "enterPressHandler")
    private void tmplt_Desc4FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_Desc4FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("zeroIdXBtn")
    public void onZeroIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinTxactItms = tableMain.getSelected().stream().toList();
        if (thisFinTxactItms == null || thisFinTxactItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxactItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxactItms.forEach(thisFinTxactItm -> {
            if (thisFinTxactItm != null) {
                UsrNode thisTrackedFinTxactItm = dataContext.merge(thisFinTxactItm);
                thisFinTxactItm = thisTrackedFinTxactItm;

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

        updateFinStmtItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {
                UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean thisFinStmtItmIsChanged = false;

                LocalDate idDt1 = thisFinStmtItm.getIdDt() != null ? thisFinStmtItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinStmtItm.getIdX();
                    Integer idX = thisFinStmtItm.getIdX() == null || thisFinStmtItm.getIdX() == 0 || thisFinStmtItm.getIdX() == 1 ? 0 : thisFinStmtItm.getIdX() - 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinStmtItm.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinStmtItm.setIdX(" + (idX) + ")");
                        thisFinStmtItmIsChanged = true;
                    }

                    thisFinStmtItmIsChanged = updateId2Calc(thisFinStmtItm) || thisFinStmtItmIsChanged;
                    String id2_ = thisFinStmtItm.getId2();
                    String id2 = thisFinStmtItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinStmtItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinStmtItm.setId2(" + (id2) + ")");
                        thisFinStmtItmIsChanged = true;
                    }

                    thisFinStmtItmIsChanged = updateId2Cmp(thisFinStmtItm) || thisFinStmtItmIsChanged;

                    if (thisFinStmtItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinStmtItm).");
                        //dataManager.save(thisFinStmtItm);
                    }
                }
            }
        });

        updateFinStmtItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdXBtn")
    public void onIncIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {
                UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean thisFinStmtItmIsChanged = false;

                LocalDate idDt1 = thisFinStmtItm.getIdDt() != null ? thisFinStmtItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idX_ = thisFinStmtItm.getIdX();
                    Integer idX = (thisFinStmtItm.getIdX() == null ? 0 : thisFinStmtItm.getIdX()) + 1;
                    if (!Objects.equals(idX_, idX)){
                        thisFinStmtItm.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinStmtItm.setIdX(" + (idX) + ")");
                        thisFinStmtItmIsChanged = true;
                    }

                    thisFinStmtItmIsChanged = updateId2Calc(thisFinStmtItm) || thisFinStmtItmIsChanged;
                    String id2_ = thisFinStmtItm.getId2();
                    String id2 = thisFinStmtItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinStmtItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinStmtItm.setId2(" + (id2) + ")");
                        thisFinStmtItmIsChanged = true;
                    }

                    thisFinStmtItmIsChanged = updateId2Cmp(thisFinStmtItm) || thisFinStmtItmIsChanged;

                    if (thisFinStmtItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinStmtItm).");
                        //dataManager.save(thisFinStmtItm);
                    }
                }
            }
        });

        updateFinStmtItmHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("maxIdXBtn")
    public void onMaxIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {
                UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean thisFinStmtItmIsChanged = false;

                LocalDate idDt1 = thisFinStmtItm.getIdDt() != null ? thisFinStmtItm.getIdDt().getDate1() : null;
                if (idDt1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from enty_UsrNode e"
                            + " where e.className = 'UsrFinStmtItm'"
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

                    Integer idX_ = thisFinStmtItm.getIdX();
                    Integer idX = idXMax == null ? 0 : idXMax;

                    if (!Objects.equals(idX_, idX)){
                        thisFinStmtItm.setIdX(idX);
                        logger.debug(logPrfx + " --- thisFinStmtItm.setIdX(" + (idX) + ")");
                        thisFinStmtItmIsChanged = true;
                    }

                    thisFinStmtItmIsChanged = updateId2Calc(thisFinStmtItm) || thisFinStmtItmIsChanged;
                    String id2_ = thisFinStmtItm.getId2();
                    String id2 = thisFinStmtItm.getId2Calc();
                    if (!Objects.equals(id2_, id2)){
                        thisFinStmtItm.setId2(id2);
                        logger.debug(logPrfx + " --- thisFinStmtItm.setId2(" + (id2) + ")");
                        thisFinStmtItmIsChanged = true;
                    }

                    thisFinStmtItmIsChanged = updateId2Cmp(thisFinStmtItm) || thisFinStmtItmIsChanged;

                    if (thisFinStmtItmIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinStmtItm).");
                        //dataManager.save(thisFinStmtItm);
                    }
                }
            }
        });

        updateFinStmtItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemIdPartsBtn")
    public void onUpdateColItemIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {

                UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                boolean isChanged = false;

                isChanged = updateIdParts(thisFinStmtItm);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrFinStmtItm.load().");
            colLoadrFinStmtItm.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinStmtItms);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinStmtItms = tableMain.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {

                UsrNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinStmtItm);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrFinStmtItm.load().");
            colLoadrFinStmtItm.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinStmtItms);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateIdParts(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateCalcVals(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinStmtItm);
            updateId2Dup(thisFinStmtItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinStmtItm);
        updateId2Cmp(thisFinStmtItm);
        updateId2Dup(thisFinStmtItm);

        logger.debug(logPrfx + " --- id2: " + thisFinStmtItm.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinStmtItm);
        updateId2Cmp(thisFinStmtItm);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinStmtItm.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdDt(thisFinStmtItm)");
            updateIdDt(thisFinStmtItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinStmtItm)");
            updateId2Calc(thisFinStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateBeg1Ts1FieldBtn")
    public void onUpdateBeg1Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg1Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg1(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("beg2Ts1Field")
    public void onBeg2Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg2Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdDt(thisFinStmtItm)");
            updateIdDt(thisFinStmtItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinStmtItm)");
            updateId2Calc(thisFinStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateBeg2Ts1FieldBtn")
    public void onUpdateBeg2Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg2Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg2(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinStmtItm)");
            updateId2Calc(thisFinStmtItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdX(thisFinStmtItm);

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


    @Install(to = "desc1Field", subject = "enterPressHandler")
    private void desc1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldListBtn")
    public void onUpdateDesc1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "desc2Field", subject = "enterPressHandler")
    private void desc2FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc2FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc2FieldListBtn")
    public void onUpdateDesc2FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc2FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc2List();

        logger.trace(logPrfx + " <-- ");
    }

    
    @Install(to = "desc3Field", subject = "enterPressHandler")
    private void desc3FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc3FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc3FieldListBtn")
    public void onUpdateDesc3FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc3FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc3List();

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "desc4Field", subject = "enterPressHandler")
    private void desc4FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc4FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc4FieldListBtn")
    public void onUpdateDesc4FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc4FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc3List();

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


    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateAmtNet(thisFinStmtItm)");
            updateAmtNet(thisFinStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("amtCredField")
    public void onAmtCredFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtCredFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateAmtNet(thisFinStmtItm)");
            updateAmtNet(thisFinStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtNetBtn")
    public void onUpdateAmtNetBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtNetBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtNet(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }
    

    @Subscribe("updateFinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtDebtSumCalc(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtCredSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtCredSumCalc(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactItms1_AmtNetSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtNetSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtNetSumCalc(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateFinTxactItms1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_IdCntCalc(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtEqCalcBoxBtn")
    public void onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmtItm = instCntnrMain.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxactItms1_AmtEqCalc(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull UsrNode thisFinStmtItm) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtItmCalcVals(thisFinStmtItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinStmtItmCalcVals(@NotNull UsrNode thisFinStmtItm) {
        String logPrfx = "updateFinStmtItmCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinStmtItm Object
        isChanged = updateIdDt(thisFinStmtItm) || isChanged;
        isChanged = updateId2Calc(thisFinStmtItm) || isChanged;
        isChanged = updateId2Cmp(thisFinStmtItm) || isChanged;
        isChanged = updateId2Dup(thisFinStmtItm) || isChanged;
        isChanged = updateAmtNet(thisFinStmtItm) || isChanged;

        isChanged = updateFinTxactItms1_IdCntCalc(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxactItms1_AmtDebtSumCalc(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxactItms1_AmtCredSumCalc(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxactItms1_AmtNetSumCalc(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxactItms1_AmtEqCalc(thisFinStmtItm)  || isChanged;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNode thisFinStmtItm) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateBeg1(thisFinStmtItm) || isChanged;
        isChanged = updateIdX(thisFinStmtItm)  || isChanged;
        isChanged = updateIdDt(thisFinStmtItm)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinStmtItm.getId2();
        String id2 = thisFinStmtItm.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinStmtItm.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinStmtItm.getId2Calc();
        String id2Calc = thisFinStmtItm.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinStmtItm.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinStmtItm.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinStmtItm.getId2(),thisFinStmtItm.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinStmtItm.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinStmtItm.getId2Dup();
        if (thisFinStmtItm.getId2() != null) {
            String id2Qry = "select count(e) from enty_UsrNode e where e.className = 'UsrFinStmtItm' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinStmtItm.getId())
                        .parameter("id2", thisFinStmtItm.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinStmtItm.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinStmtItm.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateIdDt();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg1(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateBeg1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateBeg1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg2(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateBeg2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateBeg2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateIdX();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public Boolean updateAmtNet(@NotNull UsrNode thisFinStmtItm) {
        //Assume thisFinStmtItm is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtNet_ = thisFinStmtItm.getAmtNet();

        UsrNode thisFinStmt = thisFinStmtItm.getFinStmt1_Id();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finStmt_Id is null. Please select an account.");
            notifications.create().withCaption("FinStmt_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        UsrNode thisFinAcct = thisFinStmt.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        UsrNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal amtNet = BigDecimal.valueOf(0);
        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();

        if (thisFinStmtItm.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                amtNet = amtNet.subtract(thisFinStmtItm.getAmtDebt());
            } else {
                amtNet = amtNet.add(thisFinStmtItm.getAmtDebt());
            }
        }

        if (thisFinStmtItm.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                amtNet = amtNet.subtract(thisFinStmtItm.getAmtCred());
            } else {
                amtNet = amtNet.add(thisFinStmtItm.getAmtCred());
            }
        }
        if(!Objects.equals(amtNet_, amtNet)){
            thisFinStmtItm.setAmtNet(amtNet);
            logger.debug(logPrfx + " --- amtNet: " + amtNet);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxactItms1_IdCntCalc(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxactItms1_IdCntCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer idCntCalc_ = thisFinStmtItm.getFinTxactItms1_IdCntCalc();
        Integer idCntCalc = null ;
        String qry1 = "select count(e.amtNet) from enty_UsrNode e where e.className = 'UsrFinTxactItm' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            idCntCalc = dataManager.loadValue(qry1,Integer.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
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
            thisFinStmtItm.setFinTxactItms1_IdCntCalc(idCntCalc);
            logger.debug(logPrfx + " --- idCntCalc: " + idCntCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxactItms1_AmtDebtSumCalc(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtDebtSumCalc_ = thisFinStmtItm.getFinTxactItms1_AmtDebtSumCalc();
        BigDecimal amtDebtSumCalc = null ;
        String qry1 = "select sum(e.amtDebt) from enty_UsrNode e where e.className = 'UsrFinTxactItm' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            amtDebtSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
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
            thisFinStmtItm.setFinTxactItms1_AmtDebtSumCalc(amtDebtSumCalc);
            logger.debug(logPrfx + " --- amtDebtSumCalc: " + amtDebtSumCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactItms1_AmtCredSumCalc(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtCredSumCalc_ = thisFinStmtItm.getFinTxactItms1_AmtCredSumCalc();
        BigDecimal amtCredSumCalc = null ;
        String qry1 = "select sum(e.amtCred) from enty_UsrNode e where e.className = 'UsrFinTxactItm' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            amtCredSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
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
            thisFinStmtItm.setFinTxactItms1_AmtCredSumCalc(amtCredSumCalc);
            logger.debug(logPrfx + " --- amtCredSumCalc: " + amtCredSumCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinTxactItms1_AmtNetSumCalc(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        BigDecimal amtNetSumCalc_ = thisFinStmtItm.getFinTxactItms1_AmtNetSumCalc();

        UsrNode thisFinStmt = thisFinStmtItm.getFinStmt1_Id();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finStmt_Id is null. Please select an account.");
            notifications.create().withCaption("FinStmt_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        UsrNode thisFinAcct = thisFinStmt.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        UsrNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal amtNetSumCalc = BigDecimal.valueOf(0);
        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();

        if (thisFinStmtItm.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                amtNetSumCalc = amtNetSumCalc.subtract(thisFinStmtItm.getFinTxactItms1_AmtDebtSumCalc());
            } else {
                amtNetSumCalc = amtNetSumCalc.add(thisFinStmtItm.getFinTxactItms1_AmtDebtSumCalc());
            }
        }

        if (thisFinStmtItm.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                amtNetSumCalc = amtNetSumCalc.subtract(thisFinStmtItm.getFinTxactItms1_AmtCredSumCalc());
            } else {
                amtNetSumCalc = amtNetSumCalc.add(thisFinStmtItm.getFinTxactItms1_AmtCredSumCalc());
            }
        }
        if(!Objects.equals(amtNetSumCalc_, amtNetSumCalc)){
            thisFinStmtItm.setAmtNet(amtNetSumCalc);
            logger.debug(logPrfx + " --- amtNetSumCalc: " + amtNetSumCalc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxactItms1_AmtEqCalc(@NotNull UsrNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxactItms1_AmtEqCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean amtEqCalc_ = thisFinStmtItm.getFinTxactItms1_AmtEqCalc();
        Boolean amtEqCalc = Objects.equals(thisFinStmtItm.getFinTxactItms1_AmtDebtSumCalc()
                , thisFinStmtItm.getFinTxactItms1_AmtCredSumCalc());

        if(!Objects.equals(amtEqCalc_, amtEqCalc)){
            isChanged = true;
            thisFinStmtItm.setFinTxactItms1_AmtEqCalc(amtEqCalc);
            logger.debug(logPrfx + " --- amtEqCalc: " + amtEqCalc);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private UsrNode findFinStmtItmById2(@NotNull String finStmtItm_Id2) {
        String logPrfx = "findFinStmtItmById2";
        logger.trace(logPrfx + " --> ");

        if (finStmtItm_Id2 == null) {
            logger.debug(logPrfx + " --- finStmtItm_Id2 is null.");
            notifications.create().withCaption("finStmtItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNode e where e.className = 'UsrFinStmtItm' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finStmtItm_Id2);

        UsrNode finStmtItm1_Id = null;
        try {
            finStmtItm1_Id = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", finStmtItm_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finStmtItm1_Id;
    }


    private void reloadDesc1List(){
        String logPrfx = "reloadDesc1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc1"
                + " from enty_UsrNode e"
                + " where e.className = 'UsrFinStmtItm'"
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

        ComboBox<String> propFilterCmpnt_Desc1 = (ComboBox<String>) filterConfig1A_Desc1.getValueComponent();
        propFilterCmpnt_Desc1.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Desc1.setOptionsList()");

        tmplt_Desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called tmplt_Desc1Field.setOptionsList()");

        desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadDesc2List(){
        String logPrfx = "reloadDesc2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc2"
                + " from enty_UsrNode e"
                + " where e.className = 'UsrFinStmtItm'"
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

        ComboBox<String> propFilterCmpnt_Desc2 = (ComboBox<String>) filterConfig1A_Desc2.getValueComponent();
        propFilterCmpnt_Desc2.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Desc2.setOptionsList()");

        tmplt_Desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called tmplt_Desc2Field.setOptionsList()");

        desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc2Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadDesc3List(){
        String logPrfx = "reloadDesc3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc3"
                + " from enty_UsrNode e"
                + " where e.className = 'UsrFinStmtItm'"
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

        ComboBox<String> propFilterCmpnt_Desc3 = (ComboBox<String>) filterConfig1A_Desc3.getValueComponent();
        propFilterCmpnt_Desc3.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Desc3.setOptionsList()");

        tmplt_Desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called tmplt_Desc3Field.setOptionsList()");

        desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc3Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadDesc4List(){
        String logPrfx = "reloadDesc4List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc4"
                + " from enty_UsrNode e"
                + " where e.className = 'UsrFinStmtItm'"
                + " and e.desc4 IS NOT NULL"
                + " order by e.desc4"
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

        ComboBox<String> propFilterCmpnt_Desc4 = (ComboBox<String>) filterConfig1A_Desc4.getValueComponent();
        propFilterCmpnt_Desc4.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Desc4.setOptionsList()");

        tmplt_Desc4Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called tmplt_Desc4Field.setOptionsList()");

        desc4Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc4Field.setOptionsList()");

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
        String idXMaxQry = "select max(e.idX) from enty_UsrNode e"
                + " where e.className = 'UsrFinStmtItm'"
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
        String idXCntIsNullQry = "select count(e) from enty_UsrNode e"
                + " where e.className = 'UsrFinStmtItm'"
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
    
}