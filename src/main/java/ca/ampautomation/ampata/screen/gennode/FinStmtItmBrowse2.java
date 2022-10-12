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
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@UiController("ampata_FinStmtItm.browse2")
@UiDescriptor("fin-stmt-itm-browse2.xml")
@LookupComponent("table")
public class FinStmtItmBrowse2 extends MasterDetailScreen<GenNode> {

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



    //Filter

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_desc1;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_desc2;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_desc3;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_desc4;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_finAcct1_Id;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_finStmt1_Id;


    //Template

    @Autowired
    protected EntityComboBox<GenNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;


    @Autowired
    protected DateField<LocalDateTime> tmplt_Beg1Ts1Field;

    @Autowired
    protected CheckBox tmplt_Beg1Ts1FieldChk;

    @Autowired
    protected TextField<Integer> tmplt_IdXField;

    @Autowired
    protected RadioButtonGroup<Integer> tmplt_IdXFieldRdo;


    @Autowired
    protected EntityComboBox<GenNode> tmplt_FinStmt1_IdField;

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
    protected TextField<BigDecimal> tmplt_amtDebtField;

    @Autowired
    protected CheckBox tmplt_amtDebtFieldChk;

    @Autowired
    protected TextField<BigDecimal> tmplt_amtCredField;

    @Autowired
    protected CheckBox tmplt_amtCredFieldChk;



    
    @Autowired
    private Table<GenNode> table;

    @Autowired
    private CollectionContainer<GenNode> finStmtItmsDc;

    @Autowired
    private CollectionLoader<GenNode> finStmtItmsDl;

    @Autowired
    private InstanceContainer<GenNode> finStmtItmDc;

    private CollectionContainer<GenNodeType> finStmtItmTypesDc;

    private CollectionLoader<GenNodeType> finStmtItmTypesDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    private CollectionContainer<GenNode> finStmtsDc;

    private CollectionLoader<GenNode> finStmtsDl;


    private CollectionContainer<GenNode> finAcctsDc;

    private CollectionLoader<GenNode> finAcctsDl;



    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;


    @Autowired
    private ComboBox<String> desc1Field;

    @Autowired
    private ComboBox<String> desc2Field;

    @Autowired
    private ComboBox<String> desc3Field;

    @Autowired
    private ComboBox<String> desc4Field;



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
    private EntityComboBox<GenNode> finStmt1_IdField;


    Logger logger = LoggerFactory.getLogger(FinStmtItmBrowse2.class);


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
        ComboBox<String> propFilterCmpnt_desc1 = (ComboBox<String>) filterConfig1A_desc1.getValueComponent();
        propFilterCmpnt_desc1.setNullOptionVisible(true);
        propFilterCmpnt_desc1.setNullSelectionCaption("<null>");

        ComboBox<String> propFilterCmpnt_desc2 = (ComboBox<String>) filterConfig1A_desc2.getValueComponent();
        propFilterCmpnt_desc2.setNullOptionVisible(true);
        propFilterCmpnt_desc2.setNullSelectionCaption("<null>");

        ComboBox<String> propFilterCmpnt_desc3 = (ComboBox<String>) filterConfig1A_desc3.getValueComponent();
        propFilterCmpnt_desc3.setNullOptionVisible(true);
        propFilterCmpnt_desc3.setNullSelectionCaption("<null>");

        ComboBox<String> propFilterCmpnt_desc4 = (ComboBox<String>) filterConfig1A_desc4.getValueComponent();
        propFilterCmpnt_desc4.setNullOptionVisible(true);
        propFilterCmpnt_desc4.setNullSelectionCaption("<null>");

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


        finStmtItmTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finStmtItmTypesDl = dataComponents.createCollectionLoader();
        finStmtItmTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinStmtItm' order by e.id2");
        FetchPlan finStmtItmTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtItmTypesDl.setFetchPlan(finStmtItmTypesFp);
        finStmtItmTypesDl.setContainer(finStmtItmTypesDc);
        finStmtItmTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(finStmtItmTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(finStmtItmTypesDc);
        

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


        finStmtsDc = dataComponents.createCollectionContainer(GenNode.class);
        finStmtsDl = dataComponents.createCollectionLoader();
        finStmtsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtsDl.setFetchPlan(finStmtsFp);
        finStmtsDl.setContainer(finStmtsDc);
        finStmtsDl.setDataContext(getScreenData().getDataContext());

        finStmt1_IdField.setOptionsContainer(finStmtsDc);
        //template
        tmplt_FinStmt1_IdField.setOptionsContainer(finStmtsDc);
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finStmt1_Id = (EntityComboBox<GenNode>) filterConfig1A_finStmt1_Id.getValueComponent();
        propFilterCmpnt_finStmt1_Id.setOptionsContainer(finStmtsDc);


        finAcctsDc = dataComponents.createCollectionContainer(GenNode.class);
        finAcctsDl = dataComponents.createCollectionLoader();
        finAcctsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctsDl.setFetchPlan(finAcctsFp);
        finAcctsDl.setContainer(finAcctsDc);
        finAcctsDl.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finAcct1_Id = (EntityComboBox<GenNode>) filterConfig1A_finAcct1_Id.getValueComponent();
        propFilterCmpnt_finAcct1_Id.setOptionsContainer(finAcctsDc);

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

        GenNode thisFinStmtItm = event.getEntity();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItm.setClassName("FinStmtItm");
        logger.debug(logPrfx + " --- className: FinStmtItm");

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

    @Subscribe(id = "finStmtItmsDc", target = Target.DATA_CONTAINER)
    public void onFinStmtItmsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinStmtItmsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = event.getItem();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItm.setClassName("FinStmtItm");
        logger.debug(logPrfx + " --- className: FinStmtItm");

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "table.[idTs.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        finStmtItmTypesDl.load();
        logger.debug(logPrfx + " --- called finStmtItmTypesDl.load() ");


        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");

        reloadDesc1List();
        reloadDesc2List();
        reloadDesc3List();
        reloadDesc4List();

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }



    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Gen_Node_Pr_Upd()");
        repo.execGenNodePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Gen_Node_Pr_Upd()");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Stmt_Itm_Pr_Upd()");
        repo.execFinStmtItmPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Stmt_Itm_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finStmtItmsDl.load()");
        finStmtItmsDl.load();
        logger.debug(logPrfx + " --- finished finStmtItmsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinStmtItms.forEach(orig -> {
            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

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

            if (tmplt_amtDebtFieldChk.isChecked()) {
                copy.setAmtDebt(tmplt_amtDebtField.getValue());
            }

            if (tmplt_amtCredFieldChk.isChecked()) {
                copy.setAmtCred(tmplt_amtCredField.getValue());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setIdX((orig.getIdX() == null ? 0 : copy.getIdX()) + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            updateCalcVals(copy);

            GenNode savedCopy = dataManager.save(copy);
            finStmtItmsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinStmtItm " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        table.sort("id2", Table.SortDirection.ASCENDING);
        try{
            table.setSelected(sels);

        }catch (IllegalArgumentException e){
            logger.trace(logPrfx + " --- IllegalArgumentException");
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinStmtItms.forEach(thisFinStmtItm -> {
            GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
            thisFinStmtItm = thisTrackedFinStmtItm;
            if (thisFinStmtItm != null) {

                Boolean thisFinStmtItmIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                LocalDateTime idTs1 = thisFinStmtItm.getIdTs() != null ? thisFinStmtItm.getIdTs().getTs1() : null;
                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    idTs1 = tmplt_Beg1Ts1Field.getValue();
                    thisFinStmtItm.getBeg1().setTs1(idTs1);
                    updateIdTs(thisFinStmtItm);
                }

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
                            && idTs1 != null) {
                        thisFinStmtItmIsChanged = true;
                        idX = getIdXMax(idTs1);
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


                if (tmplt_amtDebtFieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setAmtDebt(tmplt_amtDebtField.getValue());
                }

                if (tmplt_amtCredFieldChk.isChecked()) {
                    thisFinStmtItmIsChanged = true;
                    thisFinStmtItm.setAmtCred(tmplt_amtCredField.getValue());
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

            logger.debug(logPrfx + " --- executing finStmtItmsDl.load().");
            finStmtItmsDl.load();

            List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinStmtItms.forEach(thisFinStmtItm -> {
                //GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                if (thisFinStmtItm != null) {
                    GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
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

                logger.debug(logPrfx + " --- executing finStmtItmsDl.load().");
                finStmtItmsDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinStmtItms);
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

        List<GenNode> thisFinTxfers = table.getSelected().stream().toList();
        if (thisFinTxfers == null || thisFinTxfers.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxfer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxfers.forEach(thisFinTxfer -> {
            if (thisFinTxfer != null) {
                GenNode thisTrackedFinTxfer = dataContext.merge(thisFinTxfer);
                thisFinTxfer = thisTrackedFinTxfer;

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

        updateFinStmtItmHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {
                GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean thisFinStmtItmIsChanged = false;

                LocalDateTime idTs1 = thisFinStmtItm.getIdTs() != null ? thisFinStmtItm.getIdTs().getTs1() : null;
                if (idTs1 != null){

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

        List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {
                GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean thisFinStmtItmIsChanged = false;

                LocalDateTime idTs1 = thisFinStmtItm.getIdTs() != null ? thisFinStmtItm.getIdTs().getTs1() : null;
                if (idTs1 != null){

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

        List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {
                GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean thisFinStmtItmIsChanged = false;

                LocalDateTime idTs1 = thisFinStmtItm.getIdTs() != null ? thisFinStmtItm.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                            + " where e.className = 'FinStmtItm'"
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

        List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {

                GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean isChanged = false;

                isChanged = updateIdParts(thisFinStmtItm);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finStmtItmsDl.load().");
            finStmtItmsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinStmtItms);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinStmtItms = table.getSelected().stream().toList();
        if (thisFinStmtItms == null || thisFinStmtItms.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmtItms.forEach(thisFinStmtItm -> {
            if (thisFinStmtItm != null) {

                GenNode thisTrackedFinStmtItm = dataContext.merge(thisFinStmtItm);
                thisFinStmtItm = thisTrackedFinStmtItm;

                Boolean isChanged = false;

                isChanged = updateCalcVals(thisFinStmtItm);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finStmtItmsDl.load().");
            finStmtItmsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinStmtItms);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
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

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
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
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        finStmtItmTypesDl.load();
        logger.debug(logPrfx + " --- called finStmtItmTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }

            logger.debug(logPrfx + " --- calling updateIdTs(thisFinStmtItm)");
            updateIdTs(thisFinStmtItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinStmtItm)");
            updateId2Calc(thisFinStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateBeg1Ts1FieldBtn")
    public void onUpdateBeg1Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg1Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg1(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
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

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtNet(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxfers1_DebtSumFieldFieldBtn")
    public void onUpdateFinTxfers1_DebtSumFieldFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_DebtSumFieldFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_DebtSum(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxfers1_CredSumFieldFieldBtn")
    public void onUpdateFinTxfers1_CredSumFieldFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_CredSumFieldFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_CredSum(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxfers1_IdCntFieldBtn")
    public void onUpdateFinTxfers1_IdCntFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_IdCntFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_IdCnt(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxfers1_DebtEqCredBoxBtn")
    public void onUpdateFinTxfers1_DebtEqCredBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_DebtEqCredBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_DebtEqCred(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");

    }


    private Boolean updateCalcVals(@NotNull GenNode thisFinStmtItm) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        isChanged = updateFinStmtItmCalcVals(thisFinStmtItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinStmtItmCalcVals(@NotNull GenNode thisFinStmtItm) {
        String logPrfx = "updateFinStmtItmCalcVals";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        // Stored in FinStmtItm Object
        isChanged = updateIdTs(thisFinStmtItm) || isChanged;
        isChanged = updateId2Calc(thisFinStmtItm) || isChanged;
        isChanged = updateId2Cmp(thisFinStmtItm) || isChanged;
        isChanged = updateId2Dup(thisFinStmtItm) || isChanged;
        isChanged = updateAmtNet(thisFinStmtItm) || isChanged;

        isChanged = updateFinTxfers1_DebtSum(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxfers1_CredSum(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxfers1_IdCnt(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxfers1_DebtEqCred(thisFinStmtItm)  || isChanged;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull GenNode thisFinStmtItm) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        isChanged = updateBeg1(thisFinStmtItm) || isChanged;
        isChanged = updateIdX(thisFinStmtItm)  || isChanged;
        isChanged = updateIdTs(thisFinStmtItm)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
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

    private Boolean updateId2Calc(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
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

    private Boolean updateId2Cmp(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
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

    private Boolean updateId2Dup(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Integer id2Dup_ = thisFinStmtItm.getId2Dup();
        if (thisFinStmtItm.getId2() != null) {
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinStmtItm' and e.id2 = :id2 and e.id <> :id";
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


    private Boolean updateIdTs(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateIdTs";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateIdTs();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg1(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateBeg1";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateBeg1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg2(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateBeg2";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateBeg2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateIdX();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private Boolean updateFinTxfers1_DebtSum(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateDebtSum";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal debtSum_ = thisFinStmtItm.getFinTxfers1_DebtSum();
        BigDecimal debtSum = null ;
        String qry1 = "select sum(e.amtDebt) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            debtSum = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
                    .one();
            if (debtSum == null) {
                debtSum = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- debtSum: " + debtSum);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- debtSum: null");
        }

        if(!Objects.equals(debtSum_, debtSum)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_DebtSum(debtSum);
            logger.debug(logPrfx + " --- debtSum: " + debtSum);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxfers1_CredSum(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateCredSum";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal credSum_ = thisFinStmtItm.getFinTxfers1_CredSum();
        BigDecimal credSum = null ;
        String qry1 = "select sum(e.amtCred) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            credSum = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
                    .one();
            if (credSum == null) {
                credSum = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- credSum: " + credSum);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- credSum: null");
        }

        if(!Objects.equals(credSum_, credSum)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_CredSum(credSum);
            logger.debug(logPrfx + " --- credSum: " + credSum);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxfers1_IdCnt(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxfers1_IdCnt";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Integer idCnt_ = thisFinStmtItm.getFinTxfers1_IdCnt();
        Integer idCnt = null ;
        String qry1 = "select count(e.amtNet) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            idCnt = dataManager.loadValue(qry1,Integer.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
                    .one();
            if (idCnt == null) {
                idCnt = 0;}
            logger.debug(logPrfx + " --- idCnt: " + idCnt);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- debtSum: null");
        }

        if(!Objects.equals(idCnt_, idCnt)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_IdCnt(idCnt);
            logger.debug(logPrfx + " --- idCnt: " + idCnt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private GenNode findFinStmtItmById2(@NotNull String finStmtItm_Id2) {
        String logPrfx = "findFinStmtItmById2";
        logger.trace(logPrfx + " --> ");

        if (finStmtItm_Id2 == null) {
            logger.debug(logPrfx + " --- finStmtItm_Id2 is null.");
            notifications.create().withCaption("finStmtItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinStmtItm' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finStmtItm_Id2);

        GenNode finStmtItm1_Id = null;
        try {
            finStmtItm1_Id = dataManager.load(GenNode.class)
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

    private Boolean updateFinTxfers1_DebtEqCred(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxfers1_DebtEqCred";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Boolean debtEqCred_ = thisFinStmtItm.getFinTxfers1_DebtEqCred();
        Boolean debtEqCred = Objects.equals(thisFinStmtItm.getFinTxfers1_DebtSum()
                , thisFinStmtItm.getFinTxfers1_CredSum());

        if(!Objects.equals(debtEqCred_, debtEqCred)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_DebtEqCred(debtEqCred);
            logger.debug(logPrfx + " --- debtEqCred: " + debtEqCred);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtNet(@NotNull GenNode thisFinStmtItm) {
        //Assume thisFinStmtItm is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal netAmt_ = thisFinStmtItm.getAmtNet();

        GenNode thisFinStmt = thisFinStmtItm.getFinStmt1_Id();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finStmt_Id is null. Please select an account.");
            notifications.create().withCaption("FinStmt_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        GenNode thisFinAcct = thisFinStmt.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        GenNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal netAmt = BigDecimal.valueOf(0);
        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();

        if (thisFinStmtItm.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                netAmt = netAmt.subtract(thisFinStmtItm.getAmtDebt());
            } else {
                netAmt = netAmt.add(thisFinStmtItm.getAmtDebt());
            }
        }

        if (thisFinStmtItm.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                netAmt = netAmt.subtract(thisFinStmtItm.getAmtCred());
            } else {
                netAmt = netAmt.add(thisFinStmtItm.getAmtCred());
            }
        }
        if(!Objects.equals(netAmt_, netAmt)){
            thisFinStmtItm.setAmtNet(netAmt);
            logger.debug(logPrfx + " --- amtNet: " + netAmt);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private void reloadDesc1List(){
        String logPrfx = "reloadDesc1List";
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

        ComboBox<String> propFilterCmpnt_desc1 = (ComboBox<String>) filterConfig1A_desc1.getValueComponent();
        propFilterCmpnt_desc1.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_desc1.setOptionsList()");

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

        ComboBox<String> propFilterCmpnt_desc2 = (ComboBox<String>) filterConfig1A_desc2.getValueComponent();
        propFilterCmpnt_desc2.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_desc2.setOptionsList()");

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

        ComboBox<String> propFilterCmpnt_desc3 = (ComboBox<String>) filterConfig1A_desc3.getValueComponent();
        propFilterCmpnt_desc3.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_desc3.setOptionsList()");

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
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
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

        ComboBox<String> propFilterCmpnt_desc4 = (ComboBox<String>) filterConfig1A_desc4.getValueComponent();
        propFilterCmpnt_desc4.setOptionsList(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_desc4.setOptionsList()");

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

    private Integer getIdXMax(LocalDateTime idTs1){
        String logPrfx = "getIdXMax";
        logger.trace(logPrfx + " --> ");

        Integer idX, idXMax;
        String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
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
                + " where e.className = 'FinStmtItm'"
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
    
}