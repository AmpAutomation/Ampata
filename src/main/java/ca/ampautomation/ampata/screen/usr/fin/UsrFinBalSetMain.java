package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinBalSetQryMngr;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenDocVer;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
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

@UiController("enty_UsrFinBalSet.main")
@UiDescriptor("usr-fin-bal-set-main.xml")
@LookupComponent("tableMain")
public class UsrFinBalSetMain extends MasterDetailScreen<UsrNode> {

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
    private UsrFinBalSetQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_FinDept1_Id;
    
    @Autowired
    protected PropertyFilter<UsrNodeType> filterConfig1A_Type1_Id;

    //Template
    @Autowired
    protected EntityComboBox<UsrNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;


    @Autowired
    protected CheckBox tmplt_Beg1Ts1FieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_Beg1Ts1Field;


    @Autowired
    protected CheckBox tmplt_End1Ts1FieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_End1Ts1Field;


    @Autowired
    protected CheckBox tmplt_StatusFieldChk;
    @Autowired
    protected ComboBox<String> tmplt_StatusField;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNode> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNode> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrNode> instCntnrMain;
    @Autowired
    private Table<UsrNode> tableMain;



    //Other data loaders, containers and tables
    private CollectionContainer<UsrNodeType> finBalSetTypesDc;
    private CollectionLoader<UsrNodeType> finBalSetTypesDl;


    private CollectionContainer<UsrNode> colCntnrFinDept;
    private CollectionLoader<UsrNode> colLoadrFinDept;


    private CollectionContainer<UsrNode> finBalSet1sDc;
    private CollectionLoader<UsrNode> finBalSet1sDl;


    private CollectionContainer<UsrGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrGenDocVer> colLoadrGenDocVer;


    private CollectionContainer<UsrGenTag> colCntnrGenTag;
    private CollectionLoader<UsrGenTag> colLoadrGenTag;


    private CollectionContainer<UsrNodeType> colCntnrFinAcctType;
    private CollectionLoader<UsrNodeType> colLoadrFinAcctType;

    @Autowired
    private Table<UsrNode> tableFinAcct;

    @Autowired
    private CollectionLoader<UsrNode> colLoadrFinBal;
    @Autowired
    private CollectionContainer<UsrNode> colCntnrFinBal;
    @Autowired
    private Table<UsrNode> tableFinBal;


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
    private EntityComboBox<UsrNode> finDept1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finBalSet1_IdField;

    @Autowired
    private EntityComboBox<UsrGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag1_IdField;

    @Autowired
    private ComboBox<String> statusField;





    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        finBalSetTypesDc = dataComponents.createCollectionContainer(UsrNodeType.class);
        finBalSetTypesDl = dataComponents.createCollectionLoader();
        finBalSetTypesDl.setQuery("select e from enty_UsrBalSetType e order by e.sortKey, e.id2");
        FetchPlan finBalSetTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSetTypesDl.setFetchPlan(finBalSetTypesFp);
        finBalSetTypesDl.setContainer(finBalSetTypesDc);
        finBalSetTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finBalSetTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(finBalSetTypesDc);



        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrGenDocVer.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_UsrGenDocVer e order by e.sortKey, e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(UsrGenDocVer.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(genDocVersFp);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrGenTag e order by e.sortKey, e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(UsrGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(genTagsFp);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);


        colCntnrFinDept = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrFinDept = dataComponents.createCollectionLoader();
        colLoadrFinDept.setQuery("select e from enty_UsrFinDept e order by e.sortKey, e.id2");
        FetchPlan finDeptsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinDept.setFetchPlan(finDeptsFp);
        colLoadrFinDept.setContainer(colCntnrFinDept);
        colLoadrFinDept.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(colCntnrFinDept);
        //filter
        EntityComboBox<UsrNode> propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNode>) filterConfig1A_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setOptionsContainer(colCntnrFinDept);


        finBalSet1sDc = dataComponents.createCollectionContainer(UsrNode.class);
        finBalSet1sDl = dataComponents.createCollectionLoader();
        finBalSet1sDl.setQuery("select e from enty_UsrFinBalSet e order by e.sortKey, e.id2");
        FetchPlan finBalSet1sFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSet1sDl.setFetchPlan(finBalSet1sFp);
        finBalSet1sDl.setContainer(finBalSet1sDc);
        finBalSet1sDl.setDataContext(getScreenData().getDataContext());

        finBalSet1_IdField.setOptionsContainer(finBalSet1sDc);

        
        colCntnrFinAcctType = dataComponents.createCollectionContainer(UsrNodeType.class);
        colLoadrFinAcctType = dataComponents.createCollectionLoader();
        colLoadrFinAcctType.setQuery("select e from enty_UsrFinAcctType e order by e.sortKey, e.id2");
        FetchPlan finAcctTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcctType.setFetchPlan(finAcctTypesFp);
        colLoadrFinAcctType.setContainer(colCntnrFinAcctType);
        colLoadrFinAcctType.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<UsrNodeType> propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeType>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrFinAcctType);

        
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("sortIdx", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinBalSetsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNode> event) {
        String logPrfx = "onFinBalSetsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinBalSetsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinBalSetsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinBalSet = event.getItem();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSet.setClassName("UsrFinBalSet");
        logger.debug(logPrfx + " --- className: UsrFinBalSet");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tableMain.[beg1.ts1]", subject = "formatter")
    private String tableBeg1Ts1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "tableMain.[end1.ts1]", subject = "formatter")
    private String tableEnd1Ts1Formatter(LocalDateTime ts) {
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
        
        List<UsrNode> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSets.forEach(orig -> {
            UsrNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            LocalDateTime beg1;
            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                beg1 = tmplt_Beg1Ts1Field.getValue();
                copy.getTs1().setElTs(beg1);
            }

            LocalDateTime end1;
            if (tmplt_End1Ts1FieldChk.isChecked()) {
                end1 = tmplt_End1Ts1Field.getValue();
                copy.getTs3().setElTs(end1);
                updateIdDt(copy);
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNode savedCopy = dataManager.save(copy);
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

        List<UsrNode> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNode> sels = new ArrayList<>();

        thisFinBalSets.forEach(orig -> {

            UsrNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDateTime beg1;
            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                beg1 = tmplt_Beg1Ts1Field.getValue();
                copy.getTs1().setElTs(beg1);
            }else{
                if (orig.getTs1().getElTs() != null) {
                    beg1 = orig.getTs3().getElTs().plusDays(1);
                    copy.getTs1().setElTs(beg1);
                }
            }

            LocalDateTime end1;
            if (tmplt_End1Ts1FieldChk.isChecked()) {
                end1 = tmplt_End1Ts1Field.getValue();
                copy.getTs3().setElTs(end1);
                updateIdDt(copy);
            }else{
                if (orig.getTs3().getElTs() != null) {
                    end1 = orig.getTs3().getElTs().plusMonths(1);
                    copy.getTs3().setElTs(end1);
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

            UsrNode savedCopy = dataManager.save(copy);
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

        List<UsrNode> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNode> chngFinBalSets = new ArrayList<>();
        List<UsrNode> finalChngFinBalSets = chngFinBalSets;

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

                LocalDateTime beg1;
                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    beg1 = tmplt_Beg1Ts1Field.getValue();
                    thisFinBalSet.getTs1().setElTs(beg1);
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);
                }

                LocalDateTime end1;
                if (tmplt_End1Ts1FieldChk.isChecked()) {
                    end1 = tmplt_End1Ts1Field.getValue();
                    thisFinBalSet.getTs3().setElTs(end1);
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

        List<UsrNode> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UsrNode firstFinBalSet = thisFinBalSets.get(0);

        thisFinBalSets = new ArrayList<>(getFinBalSetsByParent1(firstFinBalSet.getParent1_Id()));
        thisFinBalSets.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinBalSets = new ArrayList<>();
        List<UsrNode> finalChngFinBalSets = chngFinBalSets;

        AtomicInteger sortIdx = new AtomicInteger(0);
        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {
                UsrNode thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
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

        List<UsrNode> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        //order ascending
        thisFinBalSets.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinBalSets = new ArrayList<>();
        List<UsrNode> finalChngFinBalSets = chngFinBalSets;

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
                    List<UsrNode> nextFinBalSets =  getFinBalSetsBtwnSortIdx(-1
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

        List<UsrNode> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSets.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinBalSets = new ArrayList<>();
        List<UsrNode> finalChngFinBalSets = chngFinBalSets;

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
                    UsrNode prevFinBalSet = getFinBalSetBySortIdx(sortIdx_-1,thisFinBalSet.getParent1_Id());
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

        List<UsrNode> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSets.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<UsrNode> chngFinBalSets = new ArrayList<>();
        List<UsrNode> finalChngFinBalSets = chngFinBalSets;

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
                    UsrNode nextFinBalSet = getFinBalSetBySortIdx(sortIdx_+1,thisFinBalSet.getParent1_Id());
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

        List<UsrNode> thisFinBalSets = new ArrayList<>(tableMain.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        //order descending
        thisFinBalSets.sort(Comparator.comparing(UsrNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        List<UsrNode> chngFinBalSets = new ArrayList<>();
        List<UsrNode> finalChngFinBalSets = chngFinBalSets;

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
                    List<UsrNode> nextFinBalSets =  getFinBalSetsBtwnSortIdx(sortIdx_
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



    private void updateFinBalSetHelper(List<UsrNode> chngFinBalSets) {
        String logPrfx = "updateFinBalSetHelper";
        logger.trace(logPrfx + " --> ");

        if(chngFinBalSets != null && !chngFinBalSets.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNode> thisFinBalSets = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngFinBalSets.forEach(thisFinBalSet -> {
                //UsrNode thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
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


    private Integer getSortIdxMax(UsrNode thisFinBalSet) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;
        String sortIdxMaxQry = "select max(e.sortIdx) from enty_UsrFinBalSet e"
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

        List<UsrNode> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {

                UsrNode thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
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
    public void onFinBalSetDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinBalSetDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinBalSet = event.getSource().getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            //todo I observed thisFinBalSet is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.setClassName("UsrFinBalSet");
        logger.debug(logPrfx + " --- className: UsrFinBalSet");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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
            UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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


    @Subscribe("end1Ts1Field")
    public void onEd1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onEd1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNode> thisFinAccts = new ArrayList<>(tableFinAcct.getSelected().stream().toList());
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNode> thisFinAccts = tableFinAcct.getItems().getItems().stream().toList();
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

        UsrNode thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinBalSet);

        logger.trace(logPrfx + " <-- ");
    }

    public Integer addFinAccts(@NotNull UsrNode thisFinBalSet, List<UsrNode> thisFinAccts, int iOptions){
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
                        finBals1_QryCnt = "select count(e) from enty_UsrFinBal e"
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
                        UsrNode finBal = dataManager.create(UsrNode.class);
                        finBal.setClassName("UsrFinBal");

                        finBal.setFinAcct1_Id(thisFinAcct);
                        finBal.setFinBalSet1_Id(thisFinBalSet);
                        if (thisFinBalSet.getFinDept1_Id() != null) {
                            finBal.setFinDept1_Id(thisFinBalSet.getFinDept1_Id());
                        }

                        if (thisFinBalSet.getType1_Id() != null) {
                            UsrNodeType type1;
                            type1 = getFinBalTypeById2(thisFinBalSet.getType1_Id().getId2());
                            if (type1 != null) {
                                finBal.setType1_Id(type1);
                            }
                        }
                        finBal.setSysFinCurcy1_Id(thisFinAcct.getSysFinCurcy1_Id());

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
                            UsrNode finBal1;
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

    public Boolean updateCalcVals(UsrNode thisFinBalSet){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinBalSetCalcVals(thisFinBalSet) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinBalSetCalcVals(@NotNull UsrNode thisFinBalSet) {
        String logPrfx = "updateFinBalSetCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinBalSet Object
        isChanged = updateIdDt(thisFinBalSet) || isChanged;
        isChanged = updateId2Calc(thisFinBalSet) || isChanged;
        isChanged = updateId2Cmp(thisFinBalSet) || isChanged;
        isChanged = updateId2Dup(thisFinBalSet) || isChanged;
        isChanged = updateDesc1(thisFinBalSet) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNode thisFinBalSet) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateEnd1(thisFinBalSet) || isChanged;
        isChanged = updateIdDt(thisFinBalSet)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull UsrNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinBalSet.getId2();
        String id2 = thisFinBalSet.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinBalSet.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(UsrNode thisFinBalSet){
        // Assume thisFinBalSet is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinBalSet.getId2Calc();
        String id2Calc = thisFinBalSet.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinBalSet.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull UsrNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinBalSet.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinBalSet.getId2(),thisFinBalSet.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinBalSet.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateId2Dup(@NotNull UsrNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinBalSet.getId2Dup();
        if (thisFinBalSet.getId2() != null){
            String id2Qry = "select count(e) from enty_UsrFinBalSet e where e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try{
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",thisFinBalSet.getId())
                        .parameter("id2",thisFinBalSet.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinBalSet.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinBalSet.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull UsrNode thisFinBalSet){
        // Assume thisFinBalSet is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String desc1_ = thisFinBalSet.getDesc1();

        String  status = Objects.toString(thisFinBalSet.getStatus(),"");
        if (!status.equals("")){
            status = "" + status;
        }
        logger.debug(logPrfx + " --- status: " + status);

        String type1 = "";
        if (thisFinBalSet.getType1_Id() != null) {
            type1 = Objects.toString(thisFinBalSet.getType1_Id().getId2(),"");}
        if (!type1.equals("")) {
            type1 = "" + type1 ;}
        logger.debug(logPrfx + " --- type1: " + type1);

        String begDate = "";
        if (thisFinBalSet.getTs1() != null) {
            begDate = Objects.toString(thisFinBalSet.getTs1().getElDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!begDate.equals("")) {
            begDate = "begDate " + begDate ;}
        logger.debug(logPrfx + " --- begDate: " + begDate);

        String endDate = "";
        if (thisFinBalSet.getTs3() != null) {
            endDate = Objects.toString(thisFinBalSet.getTs3().getElDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!endDate.equals("")) {
            endDate = "endDate " + endDate ;}
        logger.debug(logPrfx + " --- endDate: " + endDate);

        String dept = "";
        if (thisFinBalSet.getFinDept1_Id() != null) {
            dept = Objects.toString(thisFinBalSet.getFinDept1_Id().getId2(),"");}
        if (!dept.equals("")) {
            dept = "on dept [" + dept + "]";}
        logger.debug(logPrfx + " --- dept: " + dept);


        String tag = "";
        String tag1 = "";
        if (thisFinBalSet.getGenTag1_Id() != null) {
            tag1 = Objects.toString(thisFinBalSet.getGenTag1_Id().getId2());}
        String tag2 = "";
        if (thisFinBalSet.getGenTag1_Id() != null) {
            tag2 = Objects.toString(thisFinBalSet.getGenTag2_Id().getId2());}
        String tag3 = "";
        if (thisFinBalSet.getGenTag1_Id() != null) {
            tag3 = Objects.toString(thisFinBalSet.getGenTag3_Id().getId2());}
        String tag4 = "";
        if (thisFinBalSet.getGenTag1_Id() != null) {
            tag4 = Objects.toString(thisFinBalSet.getGenTag4_Id().getId2());}
        if (!(tag1 + tag2 + tag3 + tag4).equals("")) {
            tag = "tag [" + String.join(",",tag1, tag2, tag3, tag4) + "]";}
        logger.debug(logPrfx + " --- tag: " + tag);

        List<String> list = Arrays.asList(
                status
                ,type1
                ,begDate
                ,endDate
                ,dept
                ,tag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            thisFinBalSet.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull UsrNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBalSet.updateNm1s1Inst1Dt1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateEnd1(@NotNull UsrNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateEnd1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBalSet.updateTs3();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private UsrNode findFinBalSetById2(@NotNull String FinBalSet_Id2) {
        String logPrfx = "findFinBalSetById2";
        logger.trace(logPrfx + " --> ");

        if (FinBalSet_Id2 == null) {
            logger.debug(logPrfx + " --- FinBalSet_Id2 is null.");
            notifications.create().withCaption("FinBalSet_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrFinBalSet e where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + FinBalSet_Id2);

        UsrNode FinBalSet1_Id = null;
        try {
            FinBalSet1_Id = dataManager.load(UsrNode.class)
                    .query(qry)
                    .parameter("id2", FinBalSet_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return FinBalSet1_Id;

    }


    private UsrNode getFinBalSetBySortIdx(@NotNull Integer sortIdx, UsrNode parent1_Id) {
        String logPrfx = "getFinBalSetBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBalSet e"
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

        UsrNode finBalSet = null;
        try {
            finBalSet = dataManager.load(UsrNode.class)
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


    private List<UsrNode> getFinBalSetsBtwnSortIdx(@NotNull Integer sortIdxA , @NotNull Integer sortIdxB, UsrNode parent1_Id) {
        String logPrfx = "getFinBalSetsBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBalSet e"
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

        List<UsrNode> finBalSets = null;
        try {
            finBalSets = dataManager.load(UsrNode.class)
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

    private List<UsrNode> getFinBalSetsByParent1(UsrNode parent1_Id) {
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

        List<UsrNode> finBalSets = null;
        try {
            finBalSets = dataManager.load(UsrNode.class)
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

    private UsrNodeType getFinBalTypeById2(@NotNull String id2) {
        String logPrfx = "getFinBalTypeById2";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBalType e"
                + " where e.id2 = :id2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + id2);

        UsrNodeType finBalType = null;
        try {
            finBalType = dataManager.load(UsrNodeType.class)
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


    private UsrNode getFinBalByFinBalSet1_FinAcct1(@NotNull UsrNode finBalSet1, @NotNull UsrNode finAcct1) {
        String logPrfx = "getFinBalByFinBalSet1_FinAcct1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBal e"
                + " where e.finBalSet1_Id = :finBalSet1"
                + " and e.finAcct1_Id = :finAcct1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:finBalSet1: " + finBalSet1);
        logger.debug(logPrfx + " --- qry:finAcct1: " + finAcct1);

        UsrNode finBal = null;
        try {
            finBal = dataManager.load(UsrNode.class)
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

    public void loadTableFinBal(@NotNull UsrNode thisFinBalSet) {
        String logPrfx = "loadTableFinBal";
        logger.trace(logPrfx + " --> ");

        colCntnrFinBal = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrFinBal = dataComponents.createCollectionLoader();

        try{
            String finTxactItms1_Qry;
            finTxactItms1_Qry = "select e from enty_UsrFinBal e"
                    + " where e.finBalSet1_Id = :finBalSet1_Id"
                    + "";
            colLoadrFinBal.setQuery(finTxactItms1_Qry);
            colLoadrFinBal.setParameter("finBalSet1_Id",thisFinBalSet);

            FetchPlan finBalsFp = fetchPlans.builder(UsrNode.class)
                    .addFetchPlan("fetchPlan_UsrFinBal_Base")
                    .build();
            colLoadrFinBal.setFetchPlan(finBalsFp);
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