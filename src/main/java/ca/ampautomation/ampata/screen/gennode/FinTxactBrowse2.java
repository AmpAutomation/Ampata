package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.components.textfieldwithbutton.TextFieldWithButton;
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
import java.util.stream.Collectors;

@UiController("ampata_FinTxact.browse2")
@UiDescriptor("fin-txact-browse2.xml")
@LookupComponent("table")
public class FinTxactBrowse2 extends MasterDetailScreen<GenNode> {


    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_idTsGE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_idTsLE;

    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_genChan1_Id;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_idTsGE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_idTsLE;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idX;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_idY;


    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsTxsetOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsTxsetOption;

    @Autowired
    private CheckBox linkTxsetAutoCreateChk;

    
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
    protected CheckBox updateFilterConfig1A_IdTsLE_SyncChk;


    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdTsGERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_IdXRdo;

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
    private CollectionContainer<GenNode> finTxactsDc;

    @Autowired
    private DataLoader finTxactsDl;

    @Autowired
    private InstanceContainer<GenNode> finTxactDc;


    private CollectionContainer<GenNodeType> finTxactTypesDc;

    private CollectionLoader<GenNodeType> finTxactTypesDl;


    private CollectionContainer<GenNode> genChansDc;

    private CollectionLoader<GenNode> genChansDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    private CollectionContainer<GenNode> finTxsetsDc;

    private CollectionLoader<GenNode> finTxsetsDl;

    private CollectionContainer<GenNodeType> finTxsetTypesDc;

    private CollectionLoader<GenNodeType> finTxsetTypesDl;


    private CollectionContainer<GenNode> finCurcysDc;

    private CollectionLoader<GenNode> finCurcysDl;


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
    private EntityComboBox<GenNode> finTxset1_IdField;

    @Autowired
    private TextField<String> finTxset1_Id2CalcField;
    
    @Autowired
    private EntityComboBox<GenNodeType> finTxset1_Id_Type1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finTxset1_Id_GenChan1_IdField;

    @Autowired
    private EntityComboBox<FinHow> finTxset1_Id_FinHow1_IdField;

    @Autowired
    private EntityComboBox<FinWhat>  finTxset1_Id_FinWhat1_IdField;

    @Autowired
    private EntityComboBox<FinWhy>  finTxset1_Id_FinWhy1_IdField;


    @Autowired
    private EntityComboBox<GenNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<FinHow>  finHow1_IdField;

    @Autowired
    private EntityComboBox<FinWhat>  finWhat1_IdField;

    @Autowired
    private EntityComboBox<FinWhy>  finWhy1_IdField;


    Logger logger = LoggerFactory.getLogger(FinTxactBrowse2.class);

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_idXFieldRdo.setOptionsMap(map1);
        tmplt_idYFieldRdo.setOptionsMap(map1);

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put("None", 0);
        map2.put("Link to Exist Txact", 1);
        map2.put("Link to Exist/New Txact", 2);
        map2.put("Update Exist Txact", 3);
        updateColItemCalcValsTxsetOption.setOptionsMap(map2);
        updateInstItemCalcValsTxsetOption.setOptionsMap(map2);

        Map<String, Integer> map3 = new LinkedHashMap<>();
        map3.put("Clear", 0);
        map3.put("Match Current Row", 1);
        updateFilterConfig1B_IdTsGERdo.setOptionsMap(map3);
        updateFilterConfig1B_IdXRdo.setOptionsMap(map3);


        finTxactTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finTxactTypesDl = dataComponents.createCollectionLoader();
        finTxactTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinTxact' order by e.id2");
        FetchPlan finTxactTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxactTypesDl.setFetchPlan(finTxactTypesFp);
        finTxactTypesDl.setContainer(finTxactTypesDc);
        finTxactTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finTxactTypesDc);


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
        finTxset1_Id_GenChan1_IdField.setOptionsContainer(genChansDc);
        EntityComboBox<GenNode> propFilterCmpnt_genChan1_Id = (EntityComboBox<GenNode>) filterConfig1A_genChan1_Id.getValueComponent();
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


        finTxsetsDc = dataComponents.createCollectionContainer(GenNode.class);
        finTxsetsDl = dataComponents.createCollectionLoader();
        finTxsetsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetsDl.setFetchPlan(finTxsetsFp);
        finTxsetsDl.setContainer(finTxsetsDc);
        finTxsetsDl.setDataContext(getScreenData().getDataContext());

        finTxset1_IdField.setOptionsContainer(finTxsetsDc);

        finTxsetTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finTxsetTypesDl = dataComponents.createCollectionLoader();
        finTxsetTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinTxset' order by e.id2");
        FetchPlan finTxsetTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finTxsetTypesDl.setFetchPlan(finTxsetTypesFp);
        finTxsetTypesDl.setContainer(finTxsetTypesDc);
        finTxsetTypesDl.setDataContext(getScreenData().getDataContext());

        finTxset1_Id_Type1_IdField.setOptionsContainer(finTxsetTypesDc);


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
        finTxset1_Id_FinHow1_IdField.setOptionsContainer(finHowsDc);


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
        finTxset1_Id_FinWhat1_IdField.setOptionsContainer(finWhatsDc);


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
        finTxset1_Id_FinWhy1_IdField.setOptionsContainer(finWhysDc);


        logger.trace(logPrfx + " <--- ");


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

        GenNode thisFinTxact = event.getEntity();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setClassName("FinTxact");
        logger.debug(logPrfx + " --- className: FinTxact");

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


    @Subscribe(id = "finTxactsDc", target = Target.DATA_CONTAINER)
    public void onFinTxactsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinTxactsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = event.getItem();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setClassName("FinTxact");
        logger.debug(logPrfx + " --- className: FinTxact");

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

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Gen_Node_Pr_Upd()");
        repo.execGenNodePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Gen_Node_Pr_Upd()");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txact_Pr_Upd()");
        repo.execFinTxactPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txact_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finTxactsDl.load()");
        finTxactsDl.load();
        logger.debug(logPrfx + " --- finished finTxactsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinTxacts.forEach(orig -> {
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

                    idX = getIdXMax(idTs1);
                    if (idX == null) return;
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

                    idY = getIdYMax(idTs1, idX);
                    if (idY == null) return;
                    copy.setIdY(idY);
                }

            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (copy.getId2().equals(orig.getId2())){
                copy.setIdZ(copy.getIdZ() == null ? 1 : copy.getIdZ() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            Integer finTxsetOption = updateColItemCalcValsTxsetOption.getValue();
            if (finTxsetOption == null){
                finTxsetOption = 0;}
            updateCalcVals(copy, finTxsetOption);

            GenNode savedCopy = dataManager.save(copy);
            finTxactsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinTxact " + copy.getId2() + " "
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

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                LocalDateTime idTs1 = thisTrackedFinTxact.getIdTs() != null ? thisTrackedFinTxact.getIdTs().getTs1() : null;
                if (tmplt_beg1Ts1FieldChk.isChecked()) {
                    idTs1 = tmplt_beg1Ts1Field.getValue();
                    thisTrackedFinTxact.getBeg1().setTs1(idTs1);
                    updateIdTs(thisTrackedFinTxact);
                }

                Integer idX = thisTrackedFinTxact.getIdX();
                if (tmplt_idXFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_idXFieldRdo.getValue() == 1){
                        idX = tmplt_idXField.getValue();
                        thisTrackedFinTxact.setIdX(idX);
                    }
                    // Max
                    else if (tmplt_idXFieldRdo.getValue() == 2
                            && idTs1 != null) {

                        idX = getIdXMax(idTs1);
                        if (idX == null) return;
                        thisTrackedFinTxact.setIdX(idX);
                    }
                }

                Integer idY = thisTrackedFinTxact.getIdY();
                if (tmplt_idYFieldRdo.getValue() != null){
                    // Set
                    if (tmplt_idYFieldRdo.getValue() == 1){
                        idY = tmplt_idYField.getValue();
                        thisTrackedFinTxact.setIdY(idY);
                    }
                    // Max
                    else if (tmplt_idYFieldRdo.getValue() == 2
                            && idTs1 != null) {

                        idY = getIdYMax(idTs1, idX);
                        if (idY == null) return;
                        thisTrackedFinTxact.setIdY(idY);
                    }
                }

                thisTrackedFinTxact.setId2Calc(thisTrackedFinTxact.getId2CalcFrFields());
                thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());

                Integer finTxsetOption = updateColItemCalcValsTxsetOption.getValue();
                if (finTxsetOption == null){
                    finTxsetOption = 0;}
                updateCalcVals(thisTrackedFinTxact, finTxsetOption);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                finTxactsDl.load();

                updateId2Dup(thisTrackedFinTxact);

                thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());

                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                finTxactsDl.load();
            }
        });

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

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
        };
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
        switch (idTsGEOption){
            case 0: // Clear
                filterConfig1B_idTsGE.clear();
                break;

            case 1:  // Set to match current row
                List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                GenNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getIdX() != null && thisFinTxact.getIdTs().getTs1() != null){
                        filterConfig1B_idTsGE.setValue(thisFinTxact.getIdTs().getTs1());
                        filterConfig1B_idTsGE.apply();
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
                filterConfig1B_idX.clear();
                break;

            case 1:  // Set to match current row
                List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
                if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                GenNode thisFinTxact = thisFinTxacts.get(0);
                if (thisFinTxact != null){
                    if (thisFinTxact.getIdX() != null){
                        filterConfig1B_idX.setValue(thisFinTxact.getIdX());
                        filterConfig1B_idX.apply();
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

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                LocalDateTime idTs1 = thisTrackedFinTxact.getIdTs() != null ? thisTrackedFinTxact.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX = thisTrackedFinTxact.getIdX();
                    if (!(Objects.equals(idX == null ? 0 : idX, 0))){
                        idX = 0;
                        thisTrackedFinTxact.setIdX(idX);
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdX(" + (idX) + ")");

                        updateId2Calc(thisTrackedFinTxact);

                        thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                        updateId2Cmp(thisTrackedFinTxact);

                        thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                        thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                        thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        finTxactsDl.load();

                        updateId2Dup(thisTrackedFinTxact);

                        thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                        finTxactsDl.load();
                    }
                }

            }
        });

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdXBtn")
    public void onDecIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                Integer idX = thisTrackedFinTxact.getIdX();
                logger.debug(logPrfx + " --- idX: " + idX);
                if (idX == null || idX == 0) {
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.getIdX(): " + idX);
                }else{
                    thisTrackedFinTxact.setIdX(idX - 1);
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdX(" + (idX - 1) + ")");

                    updateId2Calc(thisTrackedFinTxact);

                    thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                    updateId2Cmp(thisTrackedFinTxact);

                    thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                    thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                    thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                    //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                }
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
        finTxactsDl.load();

        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                updateId2Dup(thisTrackedFinTxact);

                thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
        finTxactsDl.load();

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdXBtn")
    public void onIncIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                Integer idX = thisTrackedFinTxact.getIdX();
                logger.debug(logPrfx + " --- idX: " + idX);
                if (idX == null) {
                    thisTrackedFinTxact.setIdX(1);
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdX(" + 1 + ")");
                } else {
                    thisTrackedFinTxact.setIdX(idX + 1);
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdX(" + (idX + 1) + ")");
                }

                updateId2Calc(thisTrackedFinTxact);

                thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                updateId2Cmp(thisTrackedFinTxact);

                thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

            }
        });
        logger.debug(logPrfx + " --- dataContext.hasChanges(): "+ dataContext.hasChanges());

        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        finTxactsDl.load();

        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                updateId2Dup(thisTrackedFinTxact);

                thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
        finTxactsDl.load();

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("maxIdXBtn")
    public void onMaxIdXBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdXBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                LocalDateTime idTs1 = thisTrackedFinTxact.getIdTs() != null ? thisTrackedFinTxact.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX = thisTrackedFinTxact.getIdX();
                    Integer idXMax = 0;
                    String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                            + " where e.className = 'FinTxact'"
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
                        thisTrackedFinTxact.setIdX(idX);
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdX(" + (idX) + ")");

                        updateId2Calc(thisTrackedFinTxact);

                        thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                        updateId2Cmp(thisTrackedFinTxact);

                        thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                        thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                        thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- dataContext.hasChanges(): "+ dataContext.hasChanges());

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                        finTxactsDl.load();

                        updateId2Dup(thisTrackedFinTxact);

                        thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- dataContext.hasChanges(): "+ dataContext.hasChanges());

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                        finTxactsDl.load();
                    }
                }

            }
        });

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("zeroIdYBtn")
    public void onZeroIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onZeroIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                LocalDateTime idTs1 = thisTrackedFinTxact.getIdTs() != null ? thisTrackedFinTxact.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idY = thisTrackedFinTxact.getIdY();
                    if (!(Objects.equals(idY == null ? 0 : idY, 0))){
                        idY = 0;
                        thisTrackedFinTxact.setIdY(idY);
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdY(" + (idY) + ")");

                        updateId2Calc(thisTrackedFinTxact);

                        thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                        updateId2Cmp(thisTrackedFinTxact);

                        thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                        thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                        thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                        finTxactsDl.load();

                        updateId2Dup(thisTrackedFinTxact);

                        thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                        finTxactsDl.load();
                    }
                }

            }
        });

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("decIdYBtn")
    public void onDecIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDecIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                Integer idY = thisTrackedFinTxact.getIdY();
                logger.debug(logPrfx + " --- idY: " + idY);
                if (idY == null || idY == 0) {
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.getIdY(): " + idY);
                }else {
                    thisTrackedFinTxact.setIdY(idY - 1);
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdY(" + (idY - 1) + ")");

                    updateId2Calc(thisTrackedFinTxact);

                    thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                    updateId2Cmp(thisTrackedFinTxact);

                    thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                    thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                    thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                    //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
                }
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
        finTxactsDl.load();

        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                updateId2Dup(thisTrackedFinTxact);

                thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
        finTxactsDl.load();

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("incIdYBtn")
    public void onIncIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onIncIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                Integer idY = thisTrackedFinTxact.getIdY();
                logger.debug(logPrfx + " --- idY: " + idY);
                if (idY == null) {
                    thisTrackedFinTxact.setIdY(1);
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdY(" + 1 + ")");
                } else {
                    thisTrackedFinTxact.setIdY(idY + 1);
                    logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdY(" + (idY + 1) + ")");
                }

                updateId2Calc(thisTrackedFinTxact);

                thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                updateId2Cmp(thisTrackedFinTxact);

                thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
        finTxactsDl.load();

        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                updateId2Dup(thisTrackedFinTxact);

                thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        logger.debug(logPrfx + " --- executing dataContext.commit().");
        dataContext.commit();

        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
        finTxactsDl.load();

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("maxIdYBtn")
    public void onMaxIdYBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMaxIdYBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                LocalDateTime idTs1 = thisTrackedFinTxact.getIdTs() != null ? thisTrackedFinTxact.getIdTs().getTs1() : null;
                if (idTs1 != null){

                    Integer idX = thisTrackedFinTxact.getIdX();
                    Integer idY = thisTrackedFinTxact.getIdY();
                    Integer idYMax = 0;
                    String idYMaxQry = "select max(e.idY) from ampata_GenNode e"
                            + " where e.className = 'FinTxact'"
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
                        thisTrackedFinTxact.setIdY(idY);
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setIdY(" + (idY) + ")");

                        updateId2Calc(thisTrackedFinTxact);

                        thisTrackedFinTxact.setId2(thisTrackedFinTxact.getId2Calc());
                        logger.debug(logPrfx + " --- thisTrackedFinTxact.setId2(" + thisTrackedFinTxact.getId2Calc() + ")");

                        updateId2Cmp(thisTrackedFinTxact);

                        thisFinTxact.setId2Calc(thisTrackedFinTxact.getId2Calc());
                        thisFinTxact.setId2(thisTrackedFinTxact.getId2());
                        thisFinTxact.setId2Cmp(thisTrackedFinTxact.getId2Cmp());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        updateId2Dup(thisTrackedFinTxact);

                        thisFinTxact.setId2Dup(thisTrackedFinTxact.getId2Dup());
                        //logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                        //metadataTools.copy(thisTrackedFinTxact, thisFinTxact);

                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();

                        logger.debug(logPrfx + " --- executing finTxactsDl.load().");
                        finTxactsDl.load();
                    }
                }

            }
        });

        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(thisFinTxacts);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                Integer finTxsetOption = updateColItemCalcValsTxsetOption.getValue();
                if (finTxsetOption == null){
                    finTxsetOption = 0;}
                updateCalcVals(thisTrackedFinTxact, finTxsetOption);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactsDl.load().");
            finTxactsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxacts);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTxsetBtn")
    public void onUpdateTxsetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTxsetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                Integer finTxsetOption = 3; // update finTxset to match this finTxact
                updateFinTxset1_Id(thisTrackedFinTxact, finTxsetOption);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactsDl.load().");
            finTxactsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxacts);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("linkTxsetBtn")
    public void onLinkTxsetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onLinkTxsetBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer finTxsetOption;
        if (!linkTxsetAutoCreateChk.isChecked()){
            finTxsetOption = 1;
        }else{
            finTxsetOption = 2;
        }

        List<GenNode> thisFinTxacts = table.getSelected().stream().toList();
        if (thisFinTxacts == null || thisFinTxacts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxacts.forEach(thisFinTxact -> {
            GenNode thisTrackedFinTxact = dataContext.merge(thisFinTxact);
            if (thisTrackedFinTxact != null) {

                updateFinTxset1_Id(thisTrackedFinTxact, finTxsetOption);

                logger.debug(logPrfx + " --- executing metadataTools.copy(thisTrackedFinTxact,thisFinTxact).");
                metadataTools.copy(thisTrackedFinTxact, thisFinTxact);
            }
        });
        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finTxactsDl.load().");
            finTxactsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinTxacts);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxsetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxsetOption == null){
            finTxsetOption = 0;}
        updateCalcVals(thisFinTxact, finTxsetOption);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
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

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxact);
        updateId2Dup(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setId2(thisFinTxact.getId2Calc());

        logger.debug(logPrfx + " --- id2: " + thisFinTxact.getId2());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxactTypesDl.load();
        logger.debug(logPrfx + " --- called finTxactTypesDl.load() ");

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

    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdTs(thisFinTxact)");
            updateIdTs(thisFinTxact);
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxact)");
            String id2Calc = updateId2Calc(thisFinTxact);
            updateFinTxset1_Id2Calc(thisFinTxact, id2Calc.substring(0, 24));
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("beg2Ts1Field")
    public void onBeg2Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg2Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdTs(thisFinTxact)");
            updateIdTs(thisFinTxact);
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxact)");
            String id2Calc = updateId2Calc(thisFinTxact);
            updateFinTxset1_Id2Calc(thisFinTxact, id2Calc.substring(0, 24));
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");
        if (event.isUserOriginated()) {
            GenNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxact)");
            String id2Calc = updateId2Calc(thisFinTxact);
            updateFinTxset1_Id2Calc(thisFinTxact, id2Calc.substring(0, 20));
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("idYField")
    public void onIdYFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdYFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinTxact = finTxactDc.getItemOrNull();
            if (thisFinTxact == null) {
                logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinTxact)");
            String id2Calc = updateId2Calc(thisFinTxact);
            updateFinTxset1_Id2Calc(thisFinTxact, id2Calc.substring(0, 24));
        }
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


    @Subscribe("updateFinTxset1_IdFieldBtn")
    public void onUpdateFinTxset1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- thisFinTxact is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Integer finTxsetOption = updateInstItemCalcValsTxsetOption.getValue();
        if (finTxsetOption == null){
            finTxsetOption = 0;}
        updateFinTxset1_Id(thisFinTxact, finTxsetOption);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinTxset1_IdFieldListBtn")
    public void onUpdateFinTxset1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finTxsetsDl.load();
        logger.debug(logPrfx + " --- called finTxsetsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id2CalcBtn")
    public void onUpdateFinTxset1_Id2CalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id2CalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxset1_Id2Calc(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxset1_Id_Type1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_Type1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finTxsetTypesDl.load();
        logger.debug(logPrfx + " --- called finTxsetTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_GenChan1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_How1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_How1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_How1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finHowsDl.load();
        logger.debug(logPrfx + " --- called finHowsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_What1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_What1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_What1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhatsDl.load();
        logger.debug(logPrfx + " --- called finWhatsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxset1_Id_Why1_IdFieldListBtn")
    public void onUpdateFinTxset1_Id_Why1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id_Why1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxset1_Id2CalcBtn")
    public void onUpdateFinTxset1_Id2CalcBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxset1_Id2CalcBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = finTxactDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- finTxactDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxset1_Id2Calc(thisFinTxact);

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


    @Subscribe("updateFinWhy1_IdFieldListBtn")
    public void onUpdateFinWhy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finWhysDl.load();
        logger.debug(logPrfx + " --- called finWhysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    private void updateCalcVals(@NotNull GenNode thisFinTxact, Integer finTxsetOption) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        updateIdTs(thisFinTxact);
        String id2Calc = updateId2Calc(thisFinTxact);
        updateDesc1(thisFinTxact);
        updateFinTxset1_Id2Calc(thisFinTxact, id2Calc.substring(0, 20));
        updateFinTxset1_Id(thisFinTxact, finTxsetOption);
        
        logger.trace(logPrfx + " <-- ");
    }


    private String updateId2Calc(GenNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisFinTxact.getId2CalcFrFields();
        thisFinTxact.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        updateId2Cmp(thisFinTxact);
        updateId2Dup(thisFinTxact);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private String updateId2Calc(@NotNull GenNode thisFinTxact, String id2Calc) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        thisFinTxact.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private void updateId2Cmp(GenNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisFinTxact.setId2Cmp(!thisFinTxact.getId2().equals(thisFinTxact.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisFinTxact.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }
    private void updateId2Dup(GenNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisFinTxact.getId2() != null) {
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinTxact' and e.id2 = :id2 and e.id <> :id";
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
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");

            thisFinTxact.setId2Dup(id2Dup + 1);
            logger.debug(logPrfx + " --- thisFinTxact.setId2Dup(" + (id2Dup + 1) + ")");
        }
        logger.trace(logPrfx + " <-- ");
    }

    private void updateDesc1(GenNode thisFinTxact){
        // Assume thisFinTxact is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        String thisAmt = "";
        if (thisFinTxact.getAmtDebt() != null) {
            thisAmt = thisAmt + " Debt " + thisFinTxact.getAmtDebt();
        }
        if (thisFinTxact.getAmtCred() != null) {
            thisAmt = thisAmt + " Cred " + thisFinTxact.getAmtCred();
        }
        if (!thisAmt.equals("")) {
            thisAmt = thisAmt.trim();
        }
        logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

        String thisCurcy = "";
        if (thisFinTxact.getFinCurcy1_Id() != null) {
            thisCurcy = Objects.toString(thisFinTxact.getFinCurcy1_Id().getId2(), "");
        }
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
                thisAmt
                , thisCurcy
                , thisChan
                , thisWhat
                , thisWhy
                , thisHow
                , thisDocVer
                , thisTag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));
        thisFinTxact.setDesc1(desc1);

        logger.debug(logPrfx + " --- desc1: " + desc1);

        logger.trace(logPrfx + " <-- ");
    }

    private void updateIdTs(@NotNull GenNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateIdTs";
        logger.trace(logPrfx + " --> ");

        thisFinTxact.updateIdTs();

        logger.trace(logPrfx + " <-- ");
    }

    private void updateFinTxset1_Id(@NotNull GenNode thisFinTxact, Integer finTxsetOption) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_Id";
        logger.trace(logPrfx + " --> ");

        GenNode finTxset1_Id;
        // Update finTxset
        switch (finTxsetOption) {
            case 1: // Link to Exist Txset
                finTxset1_Id = findFinTxsetById2(thisFinTxact.getFinTxset1_Id2Calc());
                if (finTxset1_Id != null) {
                    thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                }
                break;
            case 2: // Link to Exist/New Txset
                finTxset1_Id = findFinTxsetById2(thisFinTxact.getFinTxset1_Id2Calc());
                if (finTxset1_Id != null) {
                    thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                } else{
                    finTxset1_Id = createFinTxsetFrFinTxact(thisFinTxact);
                    if (finTxset1_Id != null) {
                        thisFinTxact.setFinTxset1_Id(finTxset1_Id);
                    }
                }
                break;
            case 3: // Update Exist Txset
                GenNode thisFinTxset = thisFinTxact.getFinTxset1_Id();
                if (thisFinTxset != null){
                    Boolean hasChanged = false;
                    if (!thisFinTxset.getIdTs().getTs1().equals(thisFinTxact.getIdTs().getTs1())){
                        thisFinTxset.getBeg1().setTs1(thisFinTxact.getIdTs().getTs1());
                        hasChanged = true;
                    }
                    if (!thisFinTxset.getIdX().equals(thisFinTxact.getIdX())){
                        thisFinTxset.setIdX(thisFinTxact.getIdX());
                        hasChanged = true;
                    }
                    if (!thisFinTxset.getIdY().equals(thisFinTxact.getIdY())){
                        thisFinTxset.setIdY(thisFinTxact.getIdY());
                        hasChanged = true;
                    }
                    if (hasChanged){
                        thisFinTxset.updateIdTs();
                        thisFinTxset.setId2Calc(thisFinTxset.getId2CalcFrFields());
                        thisFinTxset.setId2(thisFinTxset.getId2Calc());
                    }
                }
                break;
        }
        logger.trace(logPrfx + " <-- ");
    }

    private String updateFinTxset1_Id2Calc(@NotNull GenNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_Id2Calc";
        logger.trace(logPrfx + " --> ");

        String finTxset1_Id2Calc = thisFinTxact.getId2CalcFrFields().substring(0, 24);
        thisFinTxact.setFinTxset1_Id2Calc(finTxset1_Id2Calc);
        logger.debug(logPrfx + " --- finTxset1_Id2Calc: " + finTxset1_Id2Calc);

        logger.trace(logPrfx + " <-- ");
        return finTxset1_Id2Calc;
    }

    private String updateFinTxset1_Id2Calc(GenNode thisFinTxact, String finTxset1_Id2Calc) {
        // Assume thisFinTxact is not null
        String logPrfx = "updateFinTxset1_Id2Calc";
        logger.trace(logPrfx + " --> ");

        thisFinTxact.setFinTxset1_Id2Calc(finTxset1_Id2Calc);
        logger.debug(logPrfx + " --- finTxset1_Id2Calc: " + finTxset1_Id2Calc);

        logger.trace(logPrfx + " <-- ");
        return finTxset1_Id2Calc;
    }

    private GenNode findFinTxsetById2(@NotNull String finTxset_Id2) {
        String logPrfx = "findFinTxsetById2";
        logger.trace(logPrfx + " --> ");

        if (finTxset_Id2 == null) {
            logger.debug(logPrfx + " --- finTxset_Id2 is null, finTxset_Id2.");
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
            // notifications.create().withCaption("FinTxset with id2: " + finTxset1_Id2Calc + " already exists.").show();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return finTxset1_Id;

    }

    private GenNode createFinTxsetFrFinTxact(@NotNull GenNode thisFinTxact) {
        // Assume thisFinTxact is not null
        String logPrfx = "createFinTxsetFrFinTxact";
        logger.trace(logPrfx + " --> ");

        String finTxset_Id2 = thisFinTxact.getFinTxset1_Id2Calc();
        if (finTxset_Id2 == null) {
            logger.debug(logPrfx + " --- finTxset_Id2 is null, finTxset_Id2.");
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
            beg1.setTs1(thisFinTxact.getIdTs().getTs1());
            newFinTxset.setBeg1(beg1);
            newFinTxset.updateIdTs();

            newFinTxset.setIdX(thisFinTxact.getIdX());
            newFinTxset.setIdY(thisFinTxact.getIdY());

            newFinTxset.setId2Calc(newFinTxset.getId2CalcFrFields());
            newFinTxset.setId2(newFinTxset.getId2Calc());


            GenNode savedFinTxset = dataManager.save(newFinTxset);

            GenNode mergedFinTxset = dataContext.merge(savedFinTxset);
            logger.debug(logPrfx + " --- created FinTxact id: " + mergedFinTxset.getId());
            notifications.create().withCaption("Created FinTxact with id2:" + mergedFinTxset.getId2()).show();

            logger.trace(logPrfx + " <-- ");
            return mergedFinTxset;
        }
    }


    private Integer getIdXMax(LocalDateTime idTs1){
        String logPrfx = "getIdXMax";
        logger.trace(logPrfx + " --> ");

        Integer idX, idXMax;
        String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                + " where e.className = 'FinTxact'"
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
                + " where e.className = 'FinTxact'"
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
                + " where e.className = 'FinTxact'"
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
                + " where e.className = 'FinTxact'"
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


    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

}