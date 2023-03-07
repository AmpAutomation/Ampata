package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetQryMngr;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinDept;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.component.data.table.ContainerTableItems;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.*;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@UiController("enty_UsrNodeFinBalSet.main")
@UiDescriptor("usr-node-fin-bal-set-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinBalSet0Main extends MasterDetailScreen<UsrNodeBase> {

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
    private Form form;

    @Autowired
    private Notifications notifications;

    //Query Manager
    @Autowired
    private UsrNodeFinBalSetQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<UsrNodeFinDept> filterConfig1A_FinDept1_Id;
    
    @Autowired
    protected PropertyFilter<UsrNodeBaseType> filterConfig1A_Type1_Id;

    //Template
    @Autowired
    protected EntityComboBox<UsrNodeBaseType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;


    @Autowired
    protected CheckBox tmplt_Ts1ElTsFieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1ElTsField;


    @Autowired
    protected CheckBox tmplt_Ts3ElTsFieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts3ElTsField;


    @Autowired
    protected CheckBox tmplt_StatusFieldChk;
    @Autowired
    protected ComboBox<String> tmplt_StatusField;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNodeBase> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNodeBase> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrNodeBase> instCntnrMain;
    @Autowired
    private Table<UsrNodeBase> tableMain;



    //Other data loaders, containers and tables
    private CollectionContainer<UsrNodeBaseType> finBalSetTypesDc;
    private CollectionLoader<UsrNodeBaseType> finBalSetTypesDl;


    private CollectionContainer<UsrNodeBase> colCntnrFinDept;
    private CollectionLoader<UsrNodeBase> colLoadrFinDept;


    private CollectionContainer<UsrNodeBase> finBalSet1sDc;
    private CollectionLoader<UsrNodeBase> finBalSet1sDl;


    private CollectionContainer<UsrNodeGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrNodeGenDocVer> colLoadrGenDocVer;


    private CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    private CollectionLoader<UsrItemGenTag> colLoadrGenTag;


    private CollectionContainer<UsrNodeBaseType> colCntnrFinAcctType;
    private CollectionLoader<UsrNodeBaseType> colLoadrFinAcctType;

    @Autowired
    private Table<UsrNodeBase> tableFinAcct;

    @Autowired
    private CollectionLoader<UsrNodeBase> colLoadrFinBal;
    @Autowired
    private CollectionContainer<UsrNodeBase> colCntnrFinBal;
    @Autowired
    private Table<UsrNodeBase> tableFinBal;


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
    private EntityComboBox<UsrNodeBase> finDept1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finBalSet1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> genTag1_IdField;

    @Autowired
    private ComboBox<String> statusField;





    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        finBalSetTypesDc = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        finBalSetTypesDl = dataComponents.createCollectionLoader();
        finBalSetTypesDl.setQuery("select e from enty_UsrBalSetType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBalSetType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSetTypesDl.setFetchPlan(fchPlnFinBalSetType_Inst);
        finBalSetTypesDl.setContainer(finBalSetTypesDc);
        finBalSetTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finBalSetTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(finBalSetTypesDc);



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


        colCntnrFinDept = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinDept = dataComponents.createCollectionLoader();
        colLoadrFinDept.setQuery("select e from enty_UsrNodeFinDept e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinDept_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinDept.setFetchPlan(fchPlnFinDept_Inst);
        colLoadrFinDept.setContainer(colCntnrFinDept);
        colLoadrFinDept.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(colCntnrFinDept);
        //filter
        EntityComboBox<UsrNodeBase> propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNodeBase>) filterConfig1A_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setOptionsContainer(colCntnrFinDept);


        finBalSet1sDc = dataComponents.createCollectionContainer(UsrNodeBase.class);
        finBalSet1sDl = dataComponents.createCollectionLoader();
        finBalSet1sDl.setQuery("select e from enty_UsrNodeFinBalSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBalSet1_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSet1sDl.setFetchPlan(fchPlnFinBalSet1_Inst);
        finBalSet1sDl.setContainer(finBalSet1sDc);
        finBalSet1sDl.setDataContext(getScreenData().getDataContext());

        finBalSet1_IdField.setOptionsContainer(finBalSet1sDc);

        
        colCntnrFinAcctType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrFinAcctType = dataComponents.createCollectionLoader();
        colLoadrFinAcctType.setQuery("select e from enty_UsrNodeFinAcctType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcctType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcctType.setFetchPlan(fchPlnFinAcctType_Inst);
        colLoadrFinAcctType.setContainer(colCntnrFinAcctType);
        colLoadrFinAcctType.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<UsrNodeBaseType> propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeBaseType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrFinAcctType);

        
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("sortIdx", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinBalSetsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinBalSetsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinBalSetsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinBalSetsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = event.getItem();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSet.setClassName("UsrNodeFinBalSet");
        logger.debug(logPrfx + " --- className: UsrNodeFinBalSet");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tableMain.[ts1.elTs]", subject = "formatter")
    private String tableTs1ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "tableMain.[ts3.elTs]", subject = "formatter")
    private String tableTs3ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");


        finBalSetTypesDl.load();
        logger.debug(logPrfx + " --- called finBalSetTypesDl.load() ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        finBalSet1sDl.load();
        logger.debug(logPrfx + " --- called finBalSet1sDl.load() ");

        colLoadrFinAcctType.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcctType.load() ");

        reloadStatusList();

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


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");
        
        List<UsrNodeBase> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSets.forEach(orig -> {
            UsrNodeBase copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            LocalDateTime ts1;
            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                ts1 = tmplt_Ts1ElTsField.getValue();
                copy.getTs1().setElTs(ts1);
            }

            LocalDateTime ts3;
            if (tmplt_Ts3ElTsFieldChk.isChecked()) {
                ts3 = tmplt_Ts3ElTsField.getValue();
                copy.getTs3().setElTs(ts3);
                updateIdDt(copy);
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNodeBase savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeBase> sels = new ArrayList<>();

        thisFinBalSets.forEach(orig -> {

            UsrNodeBase copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDateTime ts1;
            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                ts1 = tmplt_Ts1ElTsField.getValue();
                copy.getTs1().setElTs(ts1);
            }else{
                if (orig.getTs1().getElTs() != null) {
                    ts1 = orig.getTs3().getElTs().plusDays(1);
                    copy.getTs1().setElTs(ts1);
                }
            }

            LocalDateTime ts3;
            if (tmplt_Ts3ElTsFieldChk.isChecked()) {
                ts3 = tmplt_Ts3ElTsField.getValue();
                copy.getTs3().setElTs(ts3);
                updateIdDt(copy);
            }else{
                if (orig.getTs3().getElTs() != null) {
                    ts3 = orig.getTs3().getElTs().plusMonths(1);
                    copy.getTs3().setElTs(ts3);
                }
            }

            if (orig.getAmtEndBalCalc() != null) {
                copy.setAmtBegBal(orig.getAmtEndBalCalc());}

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNodeBase savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Derived FinBalSet " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
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

        List<UsrNodeBase> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeBase> chngFinBalSets = new ArrayList<>();
        List<UsrNodeBase> finalChngFinBalSets = chngFinBalSets;

        thisFinBalSets.forEach(thisFinBalSet -> {
            thisFinBalSet = dataContext.merge(thisFinBalSet);
            if (thisFinBalSet != null) {

                Boolean thisFinBalSetIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisFinBalSet.setType1_Id(tmplt_Type1_IdField.getValue());
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);
                }

                LocalDateTime ts1;
                if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                    ts1 = tmplt_Ts1ElTsField.getValue();
                    thisFinBalSet.getTs1().setElTs(ts1);
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);
                }

                LocalDateTime ts3;
                if (tmplt_Ts3ElTsFieldChk.isChecked()) {
                    ts3 = tmplt_Ts3ElTsField.getValue();
                    thisFinBalSet.getTs3().setElTs(ts3);
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);

                }

                if (tmplt_StatusFieldChk.isChecked()
                ) {
                    thisFinBalSet.setStatus(tmplt_StatusField.getValue());
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);
                }

                thisFinBalSetIsChanged = updateId2Calc(thisFinBalSet) || thisFinBalSetIsChanged;
                thisFinBalSetIsChanged = updateId2(thisFinBalSet) || thisFinBalSetIsChanged;
                thisFinBalSetIsChanged = updateId2Cmp(thisFinBalSet) || thisFinBalSetIsChanged;

            }
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        updateFinBalSetHelper(chngFinBalSets);
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("rebuildSortIdxBtn")
    public void onRebuildSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onRebuildSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UsrNodeBase firstFinBalSet = thisFinBalSets.get(0);

        thisFinBalSets = new ArrayList<>(getFinBalSetsByParent1(firstFinBalSet.getParent1_Id()));
        thisFinBalSets.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngFinBalSets = new ArrayList<>();
        List<UsrNodeBase> finalChngFinBalSets = chngFinBalSets;

        AtomicInteger sortIdx = new AtomicInteger(0);
        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {
                UsrNodeBase thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
                thisFinBalSet = thisTrackedFinBalSet;

                Boolean thisFinBalSetIsChanged = false;

                Integer sortIdx_ = thisFinBalSet.getSortIdx();
                if (!Objects.equals(sortIdx_, sortIdx)){
                    thisFinBalSet.setSortIdx(sortIdx.get());
                    logger.debug(logPrfx + " --- thisFinBalSet.setSortIdx(" + sortIdx.get() + ")");
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);
                }
            }
            sortIdx.incrementAndGet() ;
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        updateFinBalSetHelper(chngFinBalSets);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveFrstSortIdxBtn")
    public void onMoveFrstSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        //order ascending
        thisFinBalSets.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngFinBalSets = new ArrayList<>();
        List<UsrNodeBase> finalChngFinBalSets = chngFinBalSets;

        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {
                thisFinBalSet = dataContext.merge(thisFinBalSet);
                Boolean thisFinBalSetIsChanged = false;

                Integer sortIdx_ = thisFinBalSet.getSortIdx();
                if (sortIdx_ != null
                        && sortIdx_ > sortIdxMin.intValue()){

                    // for this item, zero idx
                    Integer sortIdx = sortIdxMin.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisFinBalSet.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisFinBalSet.setSortIdx(" + (sortIdx) + ")");
                        thisFinBalSetIsChanged = true;
                        finalChngFinBalSets.add(thisFinBalSet);
                    }

                    // for all next items, inc idx
                    List<UsrNodeBase> nextFinBalSets =  getFinBalSetsBtwnSortIdx(-1
                            , sortIdx_, thisFinBalSet.getParent1_Id());

                    nextFinBalSets.forEach(nextFinBalSet -> {
                        if (nextFinBalSet != null) {
                            nextFinBalSet = dataContext.merge(nextFinBalSet);

                            Boolean nextFinBalSetIsChanged = false;

                            Integer nextSortIdx_ = nextFinBalSet.getSortIdx();
                            if (nextSortIdx_ != null
                                    && nextSortIdx_ >= sortIdxMin.intValue()) {

                                // for this item, dec idx
                                Integer nextSortIdx = nextFinBalSet.getSortIdx() + 1;

                                if (!Objects.equals(nextSortIdx_, nextSortIdx)) {
                                    nextFinBalSet.setSortIdx(nextSortIdx);
                                    logger.debug(logPrfx + " --- thisFinBalSet.setSortIdx(" + (nextSortIdx) + ")");
                                    nextFinBalSetIsChanged = true;
                                    finalChngFinBalSets.add(nextFinBalSet);
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

        chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        updateFinBalSetHelper(chngFinBalSets);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevSortIdxBtn")
    public void onMovePrevSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMovePrevSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSets.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngFinBalSets = new ArrayList<>();
        List<UsrNodeBase> finalChngFinBalSets = chngFinBalSets;

        thisFinBalSets.forEach(thisFinBalSet -> {

            if (thisFinBalSet != null) {
                thisFinBalSet = dataContext.merge(thisFinBalSet);
                Boolean thisFinBalSetIsChanged = false;

                Integer sortIdx_ = thisFinBalSet.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisFinBalSet);
                if (sortIdx_ != null
                        && sortIdx_ > sortIdxMin){

                    // for this item, dec idx
                    Integer sortIdx = sortIdx_ - 1;

                    thisFinBalSet.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisFinBalSet.setSortIdx(" + (sortIdx) + ")");
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);

                    // for prev item, inc idx
                    UsrNodeBase prevFinBalSet = getFinBalSetBySortIdx(sortIdx_-1,thisFinBalSet.getParent1_Id());
                    if(prevFinBalSet != null){
                        prevFinBalSet = dataContext.merge(prevFinBalSet);
                        Boolean prevFinBalSetIsChanged = false;

                        Integer prevSortIdx_ = prevFinBalSet.getSortIdx();
                        if (prevSortIdx_ != null
                                && prevSortIdx_ < sortIdxMax){

                            Integer prevSortIdx = prevSortIdx_ + 1;

                            prevFinBalSet.setSortIdx(prevSortIdx);
                            logger.debug(logPrfx + " --- prevFinBalSet.setSortIdx(" + (prevSortIdx) + ")");
                            prevFinBalSetIsChanged = true;
                            finalChngFinBalSets.add(prevFinBalSet);
                        }
                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        updateFinBalSetHelper(chngFinBalSets);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveNextSortIdxBtn")
    public void onMoveNextSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveNextSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSets.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNodeBase> chngFinBalSets = new ArrayList<>();
        List<UsrNodeBase> finalChngFinBalSets = chngFinBalSets;

        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {
                thisFinBalSet = dataContext.merge(thisFinBalSet);
                Boolean thisFinBalSetIsChanged = false;

                Integer sortIdx_ = thisFinBalSet.getSortIdx();
                Integer sortIdxMin = 0;
                Integer sortIdxMax = getSortIdxMax(thisFinBalSet);
                if (sortIdxMax != null
                        && sortIdx_ < sortIdxMax){

                    // for this item, inc idx
                    Integer sortIdx = sortIdx_ + 1;

                    thisFinBalSet.setSortIdx(sortIdx);
                    logger.debug(logPrfx + " --- thisFinBalSet.setSortIdx(" + (sortIdx) + ")");
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);


                    // for next item, dec idx
                    UsrNodeBase nextFinBalSet = getFinBalSetBySortIdx(sortIdx_+1,thisFinBalSet.getParent1_Id());
                    if(nextFinBalSet != null){
                        nextFinBalSet = dataContext.merge(nextFinBalSet);
                        Boolean nextFinBalSetIsChanged = false;

                        Integer nextSortIdx_ = nextFinBalSet.getSortIdx();
                        if (nextSortIdx_ != null
                                && nextSortIdx_ > sortIdxMin){

                            Integer nextSortIdx = nextSortIdx_ - 1;

                            nextFinBalSet.setSortIdx(nextSortIdx);
                            logger.debug(logPrfx + " --- nextFinBalSet.setSortIdx(" + (nextSortIdx) + ")");
                            nextFinBalSetIsChanged = true;
                            finalChngFinBalSets.add(nextFinBalSet);
                        }

                    }

                    if (dataContext.hasChanges()) {
                        logger.debug(logPrfx + " --- executing dataContext.commit().");
                        dataContext.commit();
                    }

                }
            }
        });

        chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        updateFinBalSetHelper(chngFinBalSets);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveLastSortIdxBtn")
    public void onMoveLastSortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveLastSortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        //order descending
        thisFinBalSets.sort(Comparator.comparing(UsrNodeBase::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        List<UsrNodeBase> chngFinBalSets = new ArrayList<>();
        List<UsrNodeBase> finalChngFinBalSets = chngFinBalSets;

        AtomicInteger sortIdxMax = new AtomicInteger(getSortIdxMax(thisFinBalSets.get(0)));
        if (sortIdxMax == null) {
            logger.debug(logPrfx + " --- sortIdxMax is null.");
            notifications.create().withCaption("No records selected. Please rebuild sortIdx.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {
                thisFinBalSet = dataContext.merge(thisFinBalSet);
                Boolean thisFinBalSetIsChanged = false;

                Integer sortIdx_ = thisFinBalSet.getSortIdx();
                if (sortIdx_ != null
                        && sortIdx_ < sortIdxMax.intValue()){

                    // for this item, max idx
                    Integer sortIdx = sortIdxMax.intValue();

                    if (!Objects.equals(sortIdx_, sortIdx)){
                        thisFinBalSet.setSortIdx(sortIdx);
                        logger.debug(logPrfx + " --- thisFinBalSet.setSortIdx(" + (sortIdx) + ")");
                        thisFinBalSetIsChanged = true;
                        finalChngFinBalSets.add(thisFinBalSet);
                    }

                    // for all next items, dec idx
                    List<UsrNodeBase> nextFinBalSets =  getFinBalSetsBtwnSortIdx(sortIdx_
                            , sortIdxMax.intValue()+1, thisFinBalSet.getParent1_Id());

                    nextFinBalSets.forEach(nextFinBalSet -> {
                        if (nextFinBalSet != null) {
                            nextFinBalSet = dataContext.merge(nextFinBalSet);

                            Boolean nextFinBalSetIsChanged = false;

                            Integer nextSortIdx_ = nextFinBalSet.getSortIdx();
                            if (nextSortIdx_ != null
                                    && nextSortIdx_ <= sortIdxMax.intValue()) {

                                // for this item, dec idx
                                Integer nextSortIdx = nextFinBalSet.getSortIdx() - 1;

                                if (!Objects.equals(nextSortIdx_, nextSortIdx)) {
                                    nextFinBalSet.setSortIdx(nextSortIdx);
                                    logger.debug(logPrfx + " --- thisFinBalSet.setSortIdx(" + (nextSortIdx) + ")");
                                    nextFinBalSetIsChanged = true;
                                    finalChngFinBalSets.add(nextFinBalSet);
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


        chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        updateFinBalSetHelper(chngFinBalSets);

        logger.trace(logPrfx + " <-- ");
    }



    private void updateFinBalSetHelper(List<UsrNodeBase> chngFinBalSets) {
        String logPrfx = "updateFinBalSetHelper";
        logger.trace(logPrfx + " --> ");

        if(chngFinBalSets != null && !chngFinBalSets.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNodeBase> thisFinBalSets = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngFinBalSets.forEach(thisFinBalSet -> {
                //UsrNodeBase thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
                if (thisFinBalSet != null) {
                    thisFinBalSet = dataContext.merge(thisFinBalSet);
                    Boolean thisFinBalSetIsChanged = false;

                    thisFinBalSetIsChanged = updateId2Dup(thisFinBalSet) || thisFinBalSetIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisFinBalSets);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    private Integer getSortIdxMax(UsrNodeBase thisFinBalSet) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;
        String sortIdxMaxQry = "select max(e.sortIdx) from enty_UsrNodeFinBalSet e"
                + " where e.parent1_Id = :parent_Id1";
        try {
            sortIdxMax = dataManager.loadValue(sortIdxMaxQry, Integer.class)
                    .store("main")
                    .parameter("parent_Id1", thisFinBalSet.getParent1_Id())
                    .one();
            // max returns null if no rows or if all rows have a null value
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- sortIdxMaxQry error: " + e.getMessage());
        }
        logger.debug(logPrfx + " --- sortIdxMaxQry result: " + sortIdxMax + "");

        logger.trace(logPrfx + " <-- ");
        return sortIdxMax;
    }



    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {

                UsrNodeBase thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
                thisFinBalSet = thisTrackedFinBalSet;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinBalSet);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinBalSets);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tmplt_StatusField", subject = "enterPressHandler")
    private void tmplt_StatusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_StatusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinBalSetDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinBalSetDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = event.getSource().getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            //todo I observed thisFinBalSet is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.setClassName("UsrNodeFinBalSet");
        logger.debug(logPrfx + " --- className: UsrNodeFinBalSet");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinBalSet);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
            if (thisFinBalSet == null) {
                logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinBalSet);
            updateId2Dup(thisFinBalSet);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinBalSet);
        updateId2Cmp(thisFinBalSet);
        updateId2Dup(thisFinBalSet);

        logger.debug(logPrfx + " --- id2: " + thisFinBalSet.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinBalSet);
        updateId2Cmp(thisFinBalSet);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinBalSet.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinBalSet);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinBalSet);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finBalSetTypesDl.load();
        logger.debug(logPrfx + " --- called finBalSetTypesDl.load() ");

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

    @Subscribe("updateFinBalSet1_IdFieldListBtn")
    public void onUpdateFinBalSet1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinBalSet1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finBalSet1sDl.load();
        logger.debug(logPrfx + " --- called finBalSet1sDl.load() ");

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


    @Subscribe("ts3ElTsField")
    public void onEd1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onEd1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
            if (thisFinBalSet == null) {
                logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdDt(thisFinBalSet)");
            updateIdDt(thisFinBalSet);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinBalSet)");
            updateId2Calc(thisFinBalSet);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "statusField", subject = "enterPressHandler")
    private void statusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "statusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("loadTableFinBalBtn")
    public void onLoadTableFinBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onLoadTableFinBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        loadTableFinBal(thisFinBalSet);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("addSelTableFinAcctBtn")
    public void onAddSelTableFinAcctBtnClick(Button.ClickEvent event) {
        String logPrfx = "onAddSelTableFinAcctBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeBase> thisFinAccts = new ArrayList<>(tableFinAcct.getSelected().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        addFinAccts(thisFinBalSet, thisFinAccts,0);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("addAllTableFinAcctBtn")
    public void onAddAllTableFinAcctBtnClick(Button.ClickEvent event) {
        String logPrfx = "onAddAllTableFinAcctBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeBase> thisFinAccts = tableFinAcct.getItems().getItems().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because there are no records in the tableMain.");
            notifications.create().withCaption("No records in the tableMain.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        addFinAccts(thisFinBalSet, thisFinAccts,1);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinBalSet);

        logger.trace(logPrfx + " <-- ");
    }

    public Integer addFinAccts(@NotNull UsrNodeBase thisFinBalSet, List<UsrNodeBase> thisFinAccts, int iOptions){
        String logPrfx = "addFinAccts";
        logger.trace(logPrfx + " --> ");

        SaveContext saveContext = new SaveContext();
        AtomicBoolean isChanged = new AtomicBoolean(false);
        AtomicInteger iChanged = new AtomicInteger(0);

        thisFinAccts.forEach(thisFinAcct -> {

            if (thisFinAcct != null) {
                //thisFinAcct = dataContext.merge(thisFinAcct);

                String finAcctType1_Id2 = thisFinAcct.getType1_Id() == null ? null : thisFinAcct.getType1_Id().getId2();
                if (Objects.equals(finAcctType1_Id2, "/") || Objects.equals(finAcctType1_Id2, "/Grp")) {
                    logger.debug(logPrfx + " --- Account type " + finAcctType1_Id2 + " cannot be added to the set.");
                    notifications.create().withCaption("Account type " + finAcctType1_Id2 + " cannot be added to the set.").show();

                }else{

                    Integer finBals1_IdCntCalc;
                    try{
                        String finBals1_QryCnt;
                        finBals1_QryCnt = "select count(e)"
                                + " from enty_UsrNodeFinBal e"
                                + " where e.finBalSet1_Id = :finBalSet1_Id"
                                + " and e.finAcct1_Id = :finAcct1_Id"
                                + "";
                        finBals1_IdCntCalc = dataManager.loadValue(finBals1_QryCnt,Integer.class)
                                .store("main")
                                .parameter("finBalSet1_Id",thisFinBalSet)
                                .parameter("finAcct1_Id",thisFinAcct)
                                .one();
                        if (finBals1_IdCntCalc == null) {
                            finBals1_IdCntCalc = 0;}
                        logger.debug(logPrfx + " --- finBals1_IdCntCalc: " + finBals1_IdCntCalc);

                    } catch (IllegalStateException e){
                        logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
                        logger.debug(logPrfx + " --- finBals1_IdCntCalc: null");
                        return;
                    }

                    if (finBals1_IdCntCalc > 0){
                        if (iOptions == 0){
                            logger.debug(logPrfx + " --- Account " + thisFinAcct.getId2() + " is already in the set.");
                            notifications.create().withCaption("Account " + thisFinAcct.getId2() + " is already in the set.").show();
                        }
                    } else {
                        UsrNodeBase finBal = dataManager.create(UsrNodeBase.class);
                        finBal.setClassName("UsrNodeFinBal");

                        finBal.setFinAcct1_Id(thisFinAcct);
                        finBal.setFinBalSet1_Id(thisFinBalSet);
                        if (thisFinBalSet.getFinDept1_Id() != null) {
                            finBal.setFinDept1_Id(thisFinBalSet.getFinDept1_Id());
                        }

                        if (thisFinBalSet.getType1_Id() != null) {
                            UsrNodeBaseType type1;
                            type1 = getFinBalTypeById2(thisFinBalSet.getType1_Id().getId2());
                            if (type1 != null) {
                                finBal.setType1_Id(type1);
                            }
                        }
                        finBal.setSysNodeFinCurcy1_Id(thisFinAcct.getSysNodeFinCurcy1_Id());

                        finBal.setSortKey("_AA" + thisFinBalSet.getSortKey() + thisFinAcct.getSortKey());

                        finBal.getTs1().setElTs(thisFinBalSet.getTs1().getElTs());
                        finBal.getTs3().setElTs(thisFinBalSet.getTs3().getElTs());
                        updateIdDt(finBal);

                        finBal.setAmtBegBal(BigDecimal.ZERO);
                        finBal.setAmtDebt(BigDecimal.ZERO);
                        finBal.setAmtCred(BigDecimal.ZERO);
                        finBal.setAmtNet(BigDecimal.ZERO);
                        finBal.setAmtEndBal(BigDecimal.ZERO);

                        if (thisFinBalSet.getFinBalSet1_Id() != null) {
                            UsrNodeBase finBal1;
                            finBal1 = getFinBalByFinBalSet1_FinAcct1(thisFinBalSet.getFinBalSet1_Id(), thisFinAcct);
                            if (finBal1 != null) {
                                finBal.setFinBal1_Id(finBal1);
                            }
                        }

                        updateId2Calc(finBal);
                        updateId2(finBal);
                        updateId2Cmp(finBal);
                        updateId2Dup(finBal);

                        saveContext.saving(finBal);
                        isChanged.set(true);
                        notifications.create().withCaption("Account " + thisFinAcct.getId2() + " added to the set.").show();
                    }

                }
            }
        });
        if(isChanged.get()){
            dataManager.save(saveContext);
        }

        //chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        //updateFinBalSetHelper(chngFinBalSets);

        logger.trace(logPrfx + " <-- ");
        return iChanged.get();
    }

    public Boolean updateCalcVals(UsrNodeBase thisFinBalSet){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinBalSetCalcVals(thisFinBalSet) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private Boolean updateIdParts(@NotNull UsrNodeBase thisFinBalSet) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateTs3(thisFinBalSet) || isChanged;
        isChanged = updateIdDt(thisFinBalSet)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull UsrNodeBase thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBalSet.updateDt1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateTs3(@NotNull UsrNodeBase thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateTs3";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBalSet.updateTs3();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private UsrNodeBase getFinBalSetBySortIdx(@NotNull Integer sortIdx, UsrNodeBase parent1_Id) {
        String logPrfx = "getFinBalSetBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNodeFinBalSet e"
                + " where e.parent1_Id = :parent1_Id"
                + " and e.sortIdx = :sortIdx"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        if (parent1_Id == null){
            logger.debug(logPrfx + " --- qry:parent1_Id: null");

        }else{
            logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        }
        logger.debug(logPrfx + " --- qry:sortIdx: " + sortIdx);

        UsrNodeBase finBalSet = null;
        try {
            finBalSet = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdx", sortIdx)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finBalSet;
    }


    private List<UsrNodeBase> getFinBalSetsBtwnSortIdx(@NotNull Integer sortIdxA , @NotNull Integer sortIdxB, UsrNodeBase parent1_Id) {
        String logPrfx = "getFinBalSetsBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrNodeFinBalSet e"
                + " where e.parent1_Id = :parent1_Id"
                + " and e.sortIdx > :sortIdxA"
                + " and e.sortIdx < :sortIdxB"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        if (parent1_Id == null){
            logger.debug(logPrfx + " --- qry:parent1_Id: null");

        }else{
            logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        }
        logger.debug(logPrfx + " --- qry:sortIdxA: " + sortIdxA);
        logger.debug(logPrfx + " --- qry:sortIdxB: " + sortIdxB);

        List<UsrNodeBase> finBalSets = null;
        try {
            finBalSets = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdxA", sortIdxA)
                    .parameter("sortIdxB", sortIdxB)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ finBalSets.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finBalSets;
    }

    private List<UsrNodeBase> getFinBalSetsByParent1(UsrNodeBase parent1_Id) {
        String logPrfx = "getFinBalSetsByParent1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBalSet e"
                + " where e.parent1_Id = :parent1_Id"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        if (parent1_Id == null){
            logger.debug(logPrfx + " --- qry:parent1_Id:null");
        }else {
            logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        }

        List<UsrNodeBase> finBalSets = null;
        try {
            finBalSets = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ finBalSets.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finBalSets;
    }

    private UsrNodeBaseType getFinBalTypeById2(@NotNull String id2) {
        String logPrfx = "getFinBalTypeById2";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBalType e"
                + " where e.id2 = :id2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + id2);

        UsrNodeBaseType finBalType = null;
        try {
            finBalType = dataManager.load(UsrNodeBaseType.class)
                    .query(qry)
                    .parameter("id2", id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finBalType;
    }


    private UsrNodeBase getFinBalByFinBalSet1_FinAcct1(@NotNull UsrNodeBase finBalSet1, @NotNull UsrNodeBase finAcct1) {
        String logPrfx = "getFinBalByFinBalSet1_FinAcct1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBal e"
                + " where e.finBalSet1_Id = :finBalSet1"
                + " and e.finAcct1_Id = :finAcct1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:finBalSet1: " + finBalSet1);
        logger.debug(logPrfx + " --- qry:finAcct1: " + finAcct1);

        UsrNodeBase finBal = null;
        try {
            finBal = dataManager.load(UsrNodeBase.class)
                    .query(qry)
                    .parameter("finBalSet1", finBalSet1)
                    .parameter("finAcct1", finAcct1)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finBal;
    }

    private void reloadStatusList(){
        String logPrfx = "reloadStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status"
                + " from enty_UsrFinBalSet e"
                + " where e.status is not null"
                + " order by e.status"
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

        tmplt_StatusField.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_StatusField.setOptionsList()");

        statusField.setOptionsList(texts);
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
            // see onUpdateFinBalSet1_Desc1Field
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

    public void loadTableFinBal(@NotNull UsrNodeBase thisFinBalSet) {
        String logPrfx = "loadTableFinBal";
        logger.trace(logPrfx + " --> ");

        colCntnrFinBal = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinBal = dataComponents.createCollectionLoader();

        try{
            String finTxactItms1_Qry;
            finTxactItms1_Qry = "select e from enty_UsrFinBal e"
                    + " where e.finBalSet1_Id = :finBalSet1_Id"
                    + "";
            colLoadrFinBal.setQuery(finTxactItms1_Qry);
            colLoadrFinBal.setParameter("finBalSet1_Id",thisFinBalSet);

            FetchPlan fchPlnFinBal_Inst = fetchPlans.builder(UsrNodeBase.class)
                    .addFetchPlan("fetchPlan_UsrFinBal_Base")
                    .build();
            colLoadrFinBal.setFetchPlan(fchPlnFinBal_Inst);
            colLoadrFinBal.setContainer(colCntnrFinBal);

            colLoadrFinBal.setDataContext(getScreenData().getDataContext());

            tableFinBal.setItems(new ContainerTableItems<>(colCntnrFinBal));

//            colLoadrFinBal.load();
//            logger.debug(logPrfx + " --- colCntnrFinBal returned rows: " + colCntnrFinBal.getItems().size());
//            pagetableFinBal.setDataBinder(...);


        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
        }

        logger.trace(logPrfx + " <-- ");
    }






}