package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.sys.SysNode;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@UiController("enty_UsrFinAcct.main")
@UiDescriptor("usr-fin-acct-main.xml")
@LookupComponent("tableMain")
public class UsrFinAcctMain extends MasterDetailScreen<UsrNode> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsrNodeRepo repo;

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
    protected PropertyFilter<UsrNodeType> filterConfig1A_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_GenAgent1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_SysFinCurcy1_Id;

    
    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;
    
    
    //Template
    @Autowired
    protected EntityComboBox<UsrNodeType> tmplt_Type1_IdField;
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNode> tmplt_GenAgent1_IdField;
    @Autowired
    protected CheckBox tmplt_GenAgent1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysNode> tmplt_SysFinCurcy1_IdField;
    @Autowired
    protected CheckBox tmplt_SysFinCurcy1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNode> tmplt_FinTaxLne1_IdField;
    @Autowired
    protected CheckBox tmplt_FinTaxLne1_IdFieldChk;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNode> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNode> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrNode> instCntnrMain;
    @Autowired
    private TreeTable<UsrNode> tableMain;

    //Type data container and loader
    private CollectionContainer<UsrNodeType> colCntnrType;
    private CollectionLoader<UsrNodeType> colLoadrType;

    //Other data loaders and containers
    private CollectionContainer<UsrNode> genAgentsDc;
    private CollectionLoader<UsrNode> genAgentsDl;



    private CollectionContainer<SysNode> colCntnrSysFinCurcy;
    private CollectionLoader<SysNode> colLoadrSysFinCurcy;


    private CollectionContainer<UsrGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrGenFmla> colLoadrGenFmla;


    private CollectionContainer<UsrNode> finAcct1sDc;
    private CollectionLoader<UsrNode> finAcct1sDl;


    private CollectionContainer<UsrNode> colCntnrGenDocVer;
    private CollectionLoader<UsrNode> colLoadrGenDocVer;


    private CollectionContainer<UsrNode> colCntnrGenTag;
    private CollectionLoader<UsrNode> colLoadrGenTag;


    private CollectionContainer<UsrNode> usrFinTaxLnesDc;
    private CollectionLoader<UsrNode> usrFinTaxLnesDl;

    //Field
    @Autowired
    private ComboBox<String> statusField;

    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> genAgent1_IdField;

    @Autowired
    private EntityComboBox<SysNode> sysFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrGenFmla> desc1UsrNode1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> parent1_IdField;

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
    private EntityComboBox<UsrNode> finTaxLne1_IdField;


    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        statusField.setNullOptionVisible(true);
        statusField.setNullSelectionCaption("<null>");


        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("None", 0);
        map1.put("Include Id2", 1);
        updateColItemCalcValsOption.setOptionsMap(map1);
        updateInstItemCalcValsOption.setOptionsMap(map1);

        colCntnrType = dataComponents.createCollectionContainer(UsrNodeType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrNodeType e where e.className = 'UsrFinAcct' order by e.id2");
        FetchPlan finAcctTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(finAcctTypesFp);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(colCntnrType);
        //template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);



        genAgentsDc = dataComponents.createCollectionContainer(UsrNode.class);
        genAgentsDl = dataComponents.createCollectionLoader();
        genAgentsDl.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenAgent' order by e.id2");
        FetchPlan genAgentsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genAgentsDl.setFetchPlan(genAgentsFp);
        genAgentsDl.setContainer(genAgentsDc);
        genAgentsDl.setDataContext(getScreenData().getDataContext());

        genAgent1_IdField.setOptionsContainer(genAgentsDc);
        //template
        tmplt_GenAgent1_IdField.setOptionsContainer(genAgentsDc);
        //filter
        EntityComboBox<UsrNode> propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrNode>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(genAgentsDc);


        colCntnrSysFinCurcy = dataComponents.createCollectionContainer(SysNode.class);
        colLoadrSysFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysFinCurcy.setQuery("select e from enty_SysNode e where e.className = 'SysFinCurcy' order by e.id2");
        FetchPlan sysFinCurcysFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysFinCurcy.setFetchPlan(sysFinCurcysFp);
        colLoadrSysFinCurcy.setContainer(colCntnrSysFinCurcy);
        colLoadrSysFinCurcy.setDataContext(getScreenData().getDataContext());

        sysFinCurcy1_IdField.setOptionsContainer(colCntnrSysFinCurcy);
        //template
        tmplt_SysFinCurcy1_IdField.setOptionsContainer(colCntnrSysFinCurcy);
        //filter
        EntityComboBox<SysNode> propFilterCmpnt_SysFinCurcy1_Id = (EntityComboBox<SysNode>) filterConfig1A_SysFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysFinCurcy1_Id.setOptionsContainer(colCntnrSysFinCurcy);



        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrItem e where e.className = 'UsrGenFmla' order by e.id2");
        FetchPlan genFmlasFp = fetchPlans.builder(UsrGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(genFmlasFp);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());

        desc1UsrNode1_IdField.setOptionsContainer(colCntnrGenFmla);

        finAcct1sDc = dataComponents.createCollectionContainer(UsrNode.class);
        finAcct1sDl = dataComponents.createCollectionLoader();
        finAcct1sDl.setQuery("select e from enty_UsrNode e where e.className = 'UsrFinAcct' order by e.id2");
        FetchPlan finAcct1sFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcct1sDl.setFetchPlan(finAcct1sFp);
        finAcct1sDl.setContainer(finAcct1sDc);
        finAcct1sDl.setDataContext(getScreenData().getDataContext());

        parent1_IdField.setOptionsContainer(finAcct1sDc);

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


        usrFinTaxLnesDc = dataComponents.createCollectionContainer(UsrNode.class);
        usrFinTaxLnesDl = dataComponents.createCollectionLoader();
        usrFinTaxLnesDl.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenDocFrg' order by e.id2");
        FetchPlan finTaxLnesFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        usrFinTaxLnesDl.setFetchPlan(finTaxLnesFp);
        usrFinTaxLnesDl.setContainer(usrFinTaxLnesDc);
        usrFinTaxLnesDl.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(usrFinTaxLnesDc);
        //template
        tmplt_FinTaxLne1_IdField.setOptionsContainer(usrFinTaxLnesDc);
        //filter


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
    InitEntityEvent is sent in screens inherited from StandardEditor and MasterDetailScreen
    before the new entity instance is set to edited entity container.
    Use this event listener to initialize default values in the new entity instance
    */
    @Subscribe
    public void onInitEntity(InitEntityEvent<UsrNode> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = event.getEntity();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAcct.setClassName("UsrFinAcct");
        logger.debug(logPrfx + " --- className: UsrFinAcct");

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

        tableMain.sort("sortIdx", Table.SortDirection.ASCENDING);

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
    public void onFinAcctsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinAcctsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = event.getItem();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAcct.setClassName("UsrFinAcct");
        logger.debug(logPrfx + " --- className: UsrFinAcct");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        genAgentsDl.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

        colLoadrSysFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysFinCurcy.load() ");
        
        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        finAcct1sDl.load();
        logger.debug(logPrfx + " --- called finAcct1sDl.load() ");

        usrFinTaxLnesDl.load();
        logger.debug(logPrfx + " --- called usrFinTaxLnesDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNode> sels = new ArrayList<>();

        thisFinAccts.forEach(orig -> {
            UsrNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            if (tmplt_GenAgent1_IdFieldChk.isChecked()) {
                copy.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (!Objects.equals(copy.getId2(), orig.getId2())) {
                copy.setIdZ(copy.getIdZ() == null ? 1 : copy.getIdZ() + 1);
                copy.setId2Calc(copy.getId2CalcFrFields());
                copy.setId2(copy.getId2Calc());
            }

            Integer option = Optional.ofNullable(updateColItemCalcValsOption.getValue()).orElse(0);
            updateCalcVals(copy, option);

            UsrNode savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"

            );
            sels.add(savedCopy);

        });
        tableMain.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {
            thisFinAcct = dataContext.merge(thisFinAcct);
            if (thisFinAcct != null) {

                Boolean thisFinAcctIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinAcct.setType1_Id(tmplt_Type1_IdField.getValue());
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }

                if (tmplt_GenAgent1_IdFieldChk.isChecked()
                ) {
                    thisFinAcct.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }

                if (tmplt_FinTaxLne1_IdFieldChk.isChecked()
                ) {
                    thisFinAcct.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }
            }
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateFinAcctHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UsrNode firstFinAcct = thisFinAccts.get(0);

        thisFinAccts = new ArrayList<>(getFinAcctsByParent1(firstFinAcct.getParent1_Id()));
        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        AtomicInteger sortIdx = new AtomicInteger(0);
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                if (!Objects.equals(sortIdx_, sortIdx)){
                    thisFinAcct.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + sortIdx.get() + ")");
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);
                }
            }
            sortIdx.incrementAndGet() ;
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateFinAcctHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        //order ascending
        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                if (sortIdx_ != null
                        && sortIdx_ > sortIdxMin.intValue()){

                    // for this item, zero idx
                    Integer sortIdx = sortIdxMin.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisFinAcct.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                        thisFinAcctIsChanged = true;
                        finalChngFinAccts.add(thisFinAcct);
                    }

                    // for all next items, inc idx
                    List<UsrNode> nextFinAccts =  getFinAcctsBtwnSortIdx(-1
                            , sortIdx_, thisFinAcct.getParent1_Id());

                    nextFinAccts.forEach(nextFinAcct -> {
                        if (nextFinAcct != null) {
                            nextFinAcct = dataContext.merge(nextFinAcct);

                            Boolean nextFinAcctIsChanged = false;

                            Integer nextSortIdx_ = nextFinAcct.getSortIdx();
                            if (nextSortIdx_ != null
                                    && nextSortIdx_ >= sortIdxMin.intValue()) {

                                // for this item, dec idx
                                Integer nextSortIdx = nextFinAcct.getSortIdx() + 1;

                                if (!Objects.equals(nextSortIdx_, nextSortIdx)) {
                                    nextFinAcct.setSortIdx(nextSortIdx);
                                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (nextSortIdx) + ")");
                                    nextFinAcctIsChanged = true;
                                    finalChngFinAccts.add(nextFinAcct);
                                }
                            }
                        }
                    });

                    sortIdxMin.incrementAndGet();

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateFinAcctHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevSortIdxBtn")
    public void onMovePrevSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMovePrevSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {

            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisFinAcct);
                if (sortIdx_ != null
                    && sortIdx_ > sortIdxMin){

                    // for this item, dec idx
                    Integer sortIdx = sortIdx_ - 1;

                    thisFinAcct.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);

                    // for prev item, inc idx
                    UsrNode prevFinAcct = getFinAcctBySortIdx(sortIdx_-1,thisFinAcct.getParent1_Id());
                    if(prevFinAcct != null){
                        prevFinAcct = dataContext.merge(prevFinAcct);
                        Boolean prevFinAcctIsChanged = false;

                        Integer prevSortIdx_ = prevFinAcct.getSortIdx();
                        if (prevSortIdx_ != null
                                && prevSortIdx_ < sortIdxMax){

                            Integer prevSortIdx = prevSortIdx_ + 1;

                            prevFinAcct.setSortIdx(prevSortIdx);
                            logger.debug(logPrfx + " --- prevFinAcct.setSortIdx(" + (prevSortIdx) + ")");
                            prevFinAcctIsChanged = true;
                            finalChngFinAccts.add(prevFinAcct);
                        }
                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateFinAcctHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveNextSortIdxBtn")
    public void onMoveNextSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveNextSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);
                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisFinAcct);
                if (sortIdxMax != null
                        && sortIdx_ < sortIdxMax){

                    // for this item, inc idx
                    Integer sortIdx = sortIdx_ + 1;

                    thisFinAcct.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                    thisFinAcctIsChanged = true;
                    finalChngFinAccts.add(thisFinAcct);


                    // for next item, dec idx
                    UsrNode nextFinAcct = getFinAcctBySortIdx(sortIdx_+1,thisFinAcct.getParent1_Id());
                    if(nextFinAcct != null){
                        nextFinAcct = dataContext.merge(nextFinAcct);
                        Boolean nextFinAcctIsChanged = false;

                        Integer nextSortIdx_ = nextFinAcct.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ > sortIdxMin){

                            Integer nextSortIdx = nextSortIdx_ - 1;

                            nextFinAcct.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextFinAcct.setSortIdx(" + (nextSortIdx) + ")");
                            nextFinAcctIsChanged = true;
                            finalChngFinAccts.add(nextFinAcct);
                        }

                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateFinAcctHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveLastSortIdxBtn")
    public void onMoveLastSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveLastSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        //order descending
        thisFinAccts.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        List<UsrNode> chngFinAccts = new ArrayList<>();
        List<UsrNode> finalChngFinAccts = chngFinAccts;

        AtomicInteger sortIdxMax = new AtomicInteger(getSortIdxMax(thisFinAccts.get(0)));
        if (sortIdxMax == null) {
            logger.debug(logPrfx + " --- sortIdxMax is null.");
            notifications.create().withCaption("No records selected. Please rebuild sortIdx.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {
                thisFinAcct = dataContext.merge(thisFinAcct);

                Boolean thisFinAcctIsChanged = false;

                Integer sortIdx_ = thisFinAcct.getSortIdx();
                if (sortIdx_ != null 
                        && sortIdx_ < sortIdxMax.intValue()){

                    // for this item, max idx
                    Integer sortIdx = sortIdxMax.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisFinAcct.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (sortIdx) + ")");
                        thisFinAcctIsChanged = true;
                        finalChngFinAccts.add(thisFinAcct);
                    }

                    // for all next items, dec idx
                    List<UsrNode> nextFinAccts =  getFinAcctsBtwnSortIdx(sortIdx_
                            , sortIdxMax.intValue()+1, thisFinAcct.getParent1_Id());

                    nextFinAccts.forEach(nextFinAcct -> {
                                if (nextFinAcct != null) {
                                    nextFinAcct = dataContext.merge(nextFinAcct);

                                    Boolean nextFinAcctIsChanged = false;

                                    Integer nextSortIdx_ = nextFinAcct.getSortIdx();
                                    if (nextSortIdx_ != null 
                                            && nextSortIdx_ <= sortIdxMax.intValue()) {

                                        // for this item, dec idx
                                        Integer nextSortIdx = nextFinAcct.getSortIdx() - 1;

                                        if (!Objects.equals(nextSortIdx_, nextSortIdx)) {
                                            nextFinAcct.setSortIdx(nextSortIdx);
                                            logger.debug(logPrfx + " --- thisFinAcct.setSortIdx(" + (nextSortIdx) + ")");
                                            nextFinAcctIsChanged = true;
                                            finalChngFinAccts.add(nextFinAcct);
                                        }
                                    }
                                }
                            });

                    sortIdxMax.decrementAndGet();

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }

            }
        });


        chngFinAccts = finalChngFinAccts.stream().distinct().collect(Collectors.toList());
        updateFinAcctHelper(chngFinAccts);

        logger.trace(logPrfx + " <-- ");
    }


    private void updateFinAcctHelper(List<UsrNode> chngFinAccts) {
        String logPrfx = "updateFinAcctHelper";
        logger.trace(logPrfx + " --> ");

        if(chngFinAccts != null && !chngFinAccts.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngFinAccts.forEach(thisFinAcct -> {
                //UsrNode thisTrackedFinAcct = dataContext.merge(thisFinAcct);
                if (thisFinAcct != null) {
                    thisFinAcct = dataContext.merge(thisFinAcct);
                    Boolean thisFinAcctIsChanged = false;

                    thisFinAcctIsChanged = updateId2Dup(thisFinAcct) || thisFinAcctIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisFinAccts);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    private Integer getSortIdxMax(UsrNode thisFinAcct) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;
        String sortIdxMaxQry = "select max(e.sortIdx) from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent_Id1";
        try {
            sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                    .store("main")
                    .parameter("parent_Id1", thisFinAcct.getParent1_Id())
                    .one();
            // max returns null if no rows or if all rows have a null value
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- sortIdxMaxQry error: " + e.getMessage());
        }
        logger.debug(logPrfx + " --- sortIdxMaxQry result: " + sortIdxMax + "");

        logger.trace(logPrfx + " <-- ");
        return sortIdxMax;
    }


    @Subscribe("updateColItemIdPartsBtn")
    public void onUpdateColItemIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {

                UsrNode thisTrackedFinAcct = dataContext.merge(thisFinAcct);
                thisFinAcct = thisTrackedFinAcct;

                boolean isChanged = false;

                isChanged = updateIdParts(thisFinAcct);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.setSelected(thisFinAccts);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNode> thisFinAccts = tableMain.getSelected().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinAccts.forEach(thisFinAcct -> {
            if (thisFinAcct != null) {

                UsrNode thisTrackedFinAcct = dataContext.merge(thisFinAcct);
                thisFinAcct = thisTrackedFinAcct;

                boolean isChanged = false;

                Integer option = Optional.ofNullable(updateColItemCalcValsOption.getValue()).orElse(0);

                isChanged = updateCalcVals(thisFinAcct, option);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.setSelected(thisFinAccts);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateIdParts(thisFinAcct);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Integer option = Optional.ofNullable(updateInstItemCalcValsOption.getValue()).orElse(0);

        updateCalcVals(thisFinAcct, option);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
            if (thisFinAcct == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinAcct);
            updateId2Dup(thisFinAcct);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinAcct);
        updateId2Cmp(thisFinAcct);
        updateId2Dup(thisFinAcct);

        logger.debug(logPrfx + " --- id2: " + thisFinAcct.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinAcct);
        updateId2Cmp(thisFinAcct);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinAcct.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinAcct);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinAcct);

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


    @Subscribe("name1Field")
    public void onName1FieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onName1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinAcct = instCntnrMain.getItemOrNull();
            if (thisFinAcct == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinAcct)");
            updateId2Calc(thisFinAcct);
        }
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

    
    @Subscribe("updateParent1_IdFieldListBtn")
    public void onUpdateParent1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finAcct1sDl.load();
        logger.debug(logPrfx + " --- called finAcct1sDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateGenAgent1_IdFieldListBtn")
    public void onUpdateGenAgent1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenAgent1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genAgentsDl.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

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

    @Install(to = "statusField", subject = "enterPressHandler")
    private void statusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "statusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("updateStatusFieldListBtn")
    public void onUpdateStatusFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateStatusFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadStatusList();

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


    @Subscribe("updateFinTaxLne1_IdFieldListBtn")
    public void onUpdateFinTaxLne1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        usrFinTaxLnesDl.load();
        logger.debug(logPrfx + " --- called usrFinTaxLnesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    private Boolean updateCalcVals(@NotNull UsrNode thisFinAcct, Integer option) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateId2Calc(thisFinAcct) || isChanged;
        switch (option) {
            case 1: // Include Id2
                isChanged = updateId2(thisFinAcct) || isChanged;
                break;

        }
        isChanged = updateId2Cmp(thisFinAcct) || isChanged;
        isChanged = updateId2Dup(thisFinAcct) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNode thisFinAcct) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updatetName1(thisFinAcct)  || isChanged;
        isChanged = updatetAgent1(thisFinAcct)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull UsrNode thisFinAcct) {
        // Assume thisFinAcct is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinAcct.getId2();
        String id2 = thisFinAcct.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinAcct.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull UsrNode thisFinAcct) {
        // Assume thisFinAcct is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinAcct.getId2Calc();
        String id2Calc = thisFinAcct.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinAcct.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull UsrNode thisFinAcct) {
        // Assume thisFinAcct is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinAcct.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinAcct.getId2(),thisFinAcct.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinAcct.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull UsrNode thisFinAcct) {
        // Assume thisFinAcct is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinAcct.getId2Dup();
        if (thisFinAcct.getId2() != null) {
            String id2Qry = "select count(e) from enty_UsrNode e"
                    + " where e.className = 'UsrFinAcct'"
                    + " and e.id2 = :id2"
                    + " and e.id <> :id"
                    ;
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinAcct.getId())
                        .parameter("id2", thisFinAcct.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinAcct.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinAcct.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updatetName1(@NotNull UsrNode thisFinAcct) {
        // Assume thisFinAcct is not null
        String logPrfx = "updatetName1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinAcct.updateName1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updatetAgent1(@NotNull UsrNode thisFinAcct) {
        // Assume thisFinAcct is not null
        String logPrfx = "updatetAgent1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinAcct.updateGenAgent1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    

    private UsrNode getFinAcctBySortIdx(@NotNull Integer sortIdx, UsrNode parent1_Id) {
        String logPrfx = "getFinAcctBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent1_Id"
                + " and e.sortIdx = :sortIdx"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdx: " + sortIdx);

        UsrNode finAcct = null;
        try {
            finAcct = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdx", sortIdx)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finAcct;
    }


    private List<UsrNode> getFinAcctsBtwnSortIdx(@NotNull Integer sortIdxA , @NotNull Integer sortIdxB, UsrNode parent1_Id) {
        String logPrfx = "getFinAcctsBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent1_Id"
                + " and e.sortIdx > :sortIdxA"
                + " and e.sortIdx < :sortIdxB"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdxA: " + sortIdxA);
        logger.debug(logPrfx + " --- qry:sortIdxB: " + sortIdxB);

        List<UsrNode> finAccts = null;
        try {
            finAccts = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdxA", sortIdxA)
                    .parameter("sortIdxB", sortIdxB)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ finAccts.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finAccts;
    }

    private List<UsrNode> getFinAcctsByParent1(UsrNode parent1_Id) {
        String logPrfx = "getFinAcctsByParent1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.parent1_Id = :parent1_Id"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());

        List<UsrNode> finAccts = null;
        try {
            finAccts = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ finAccts.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finAccts;
    }

    private void reloadStatusList(){
        String logPrfx = "reloadStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status from enty_UsrNode e"
                + " where e.className = 'UsrFinAcct'"
                + " and e.status IS NOT NULL"
                + " order by e.status"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> statuss = null;
        try {
            statuss = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + statuss.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        statusField.setOptionsList(statuss);
        logger.debug(logPrfx + " --- called statusField.setOptionsList()");

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