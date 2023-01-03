package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.GenNode;
import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
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

@UiController("ampata_FinBalSet.browse2")
@UiDescriptor("fin-bal-set-browse2.xml")
@LookupComponent("table")
public class FinBalSetBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finDept1_Id;
    
    @Autowired
    protected PropertyFilter<GenNodeType> propFilter2Config1_type1_Id;


    @Autowired
    protected EntityComboBox<GenNodeType> tmplt_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;


    @Autowired
    protected CheckBox tmplt_beg1Ts1FieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_beg1Ts1Field;


    @Autowired
    protected CheckBox tmplt_end1Ts1FieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_end1Ts1Field;

    @Autowired
    protected ComboBox<String> tmplt_StatusField;

    @Autowired
    protected CheckBox tmplt_StatusFieldChk;


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
    private Table<GenNode> table;
    
    @Autowired
    private Table<GenNode> tableFinAcct;
    
    @Autowired
    private Form form;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> finBalSetsDc;

    @Autowired
    private CollectionLoader<GenNode> finBalSetsDl;

    @Autowired
    private InstanceContainer<GenNode> finBalSetDc;


    private CollectionContainer<GenNodeType> finBalSetTypesDc;

    private CollectionLoader<GenNodeType> finBalSetTypesDl;


    private CollectionContainer<GenNode> finDeptsDc;

    private CollectionLoader<GenNode> finDeptsDl;


    private CollectionContainer<GenNode> finBalSet1sDc;

    private CollectionLoader<GenNode> finBalSet1sDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    private CollectionContainer<GenNodeType> finAcctTypesDc;

    private CollectionLoader<GenNodeType> finAcctTypesDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finDept1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finBalSet1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;

    @Autowired
    private ComboBox<String> statusField;


    @Autowired
    private Table<GenNode> tableFinBal;

    @Autowired
    private CollectionContainer<GenNode> finBalsDc;

    @Autowired
    private CollectionLoader<GenNode> finBalsDl;


    Logger logger = LoggerFactory.getLogger(FinBalSetBrowse2.class);


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        finBalSetTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finBalSetTypesDl = dataComponents.createCollectionLoader();
        finBalSetTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinBalSet' order by e.id2");
        FetchPlan finBalSetTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSetTypesDl.setFetchPlan(finBalSetTypesFp);
        finBalSetTypesDl.setContainer(finBalSetTypesDc);
        finBalSetTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finBalSetTypesDc);
        //template
        tmplt_Type1_IdField.setOptionsContainer(finBalSetTypesDc);



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


        finDeptsDc = dataComponents.createCollectionContainer(GenNode.class);
        finDeptsDl = dataComponents.createCollectionLoader();
        finDeptsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinDept' order by e.id2");
        FetchPlan finDeptsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finDeptsDl.setFetchPlan(finDeptsFp);
        finDeptsDl.setContainer(finDeptsDc);
        finDeptsDl.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(finDeptsDc);
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finDept1_Id = (EntityComboBox<GenNode>) propFilter_finDept1_Id.getValueComponent();
        propFilterCmpnt_finDept1_Id.setOptionsContainer(finDeptsDc);


        finBalSet1sDc = dataComponents.createCollectionContainer(GenNode.class);
        finBalSet1sDl = dataComponents.createCollectionLoader();
        finBalSet1sDl.setQuery("select e from ampata_GenNode e where e.className = 'FinBalSet' order by e.id2");
        FetchPlan finBalSet1sFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSet1sDl.setFetchPlan(finBalSet1sFp);
        finBalSet1sDl.setContainer(finBalSet1sDc);
        finBalSet1sDl.setDataContext(getScreenData().getDataContext());

        finBalSet1_IdField.setOptionsContainer(finBalSet1sDc);

        
        finAcctTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finAcctTypesDl = dataComponents.createCollectionLoader();
        finAcctTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctTypesDl.setFetchPlan(finAcctTypesFp);
        finAcctTypesDl.setContainer(finAcctTypesDc);
        finAcctTypesDl.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<GenNodeType> propFilterCmpnt2Config1_type1_Id = (EntityComboBox<GenNodeType>) propFilter2Config1_type1_Id.getValueComponent();
        propFilterCmpnt2Config1_type1_Id.setOptionsContainer(finAcctTypesDc);

        
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        finBalSetsDl.load();
        table.sort("sortIdx", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "finBalSetsDc", target = Target.DATA_CONTAINER)
    public void onFinBalSetsDcCollectionChange(CollectionContainer.CollectionChangeEvent<GenNode> event) {
        String logPrfx = "onFinBalSetsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "finBalSetsDc", target = Target.DATA_CONTAINER)
    public void onFinBalSetsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinBalSetsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBalSet = event.getItem();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSet.setClassName("FinBalSet");
        logger.debug(logPrfx + " --- className: FinBalSet");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "table.[beg1.ts1]", subject = "formatter")
    private String tableBeg1Ts1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "table.[end1.ts1]", subject = "formatter")
    private String tableEnd1Ts1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");


        finBalSetTypesDl.load();
        logger.debug(logPrfx + " --- called finBalSetTypesDl.load() ");

        finDeptsDl.load();
        logger.debug(logPrfx + " --- called finDeptsDl.load() ");

        finBalSet1sDl.load();
        logger.debug(logPrfx + " --- called finBalSet1sDl.load() ");

        finAcctTypesDl.load();
        logger.debug(logPrfx + " --- called finAcctTypesDl.load() ");

        reloadStatusList();

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Gen_Node_Pr_Upd()");
        repo.execGenNodePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Gen_Node_Pr_Upd()");

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Bal_Set_Pr_Upd()");
        repo.execFinBalSetPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Bal_Set_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finBalSetsDl.load()");
        finBalSetsDl.load();
        logger.debug(logPrfx + " --- finished finBalSetsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");
        
        List<GenNode> thisFinBalSets = table.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSets.forEach(orig -> {
            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            LocalDateTime beg1;
            if (tmplt_beg1Ts1FieldChk.isChecked()) {
                beg1 = tmplt_beg1Ts1Field.getValue();
                copy.getBeg1().setTs1(beg1);
            }

            LocalDateTime end1;
            if (tmplt_end1Ts1FieldChk.isChecked()) {
                end1 = tmplt_end1Ts1Field.getValue();
                copy.getEnd1().setTs1(end1);
                updateIdDt(copy);
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            GenNode savedCopy = dataManager.save(copy);
            finBalSetsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinBalSet " + copy.getId2() + " "
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

        List<GenNode> thisFinBalSets = table.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinBalSets.forEach(orig -> {

            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDateTime beg1;
            if (tmplt_beg1Ts1FieldChk.isChecked()) {
                beg1 = tmplt_beg1Ts1Field.getValue();
                copy.getBeg1().setTs1(beg1);
            }else{
                if (orig.getBeg1().getTs1() != null) {
                    beg1 = orig.getEnd1().getTs1().plusDays(1);
                    copy.getBeg1().setTs1(beg1);
                }
            }

            LocalDateTime end1;
            if (tmplt_end1Ts1FieldChk.isChecked()) {
                end1 = tmplt_end1Ts1Field.getValue();
                copy.getEnd1().setTs1(end1);
                updateIdDt(copy);
            }else{
                if (orig.getEnd1().getTs1() != null) {
                    end1 = orig.getEnd1().getTs1().plusMonths(1);
                    copy.getEnd1().setTs1(end1);
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

            GenNode savedCopy = dataManager.save(copy);
            finBalSetsDc.getMutableItems().add(savedCopy);
            logger.debug("Derived FinBalSet " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
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

        List<GenNode> thisFinBalSets = table.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<GenNode> chngFinBalSets = new ArrayList<>();
        List<GenNode> finalChngFinBalSets = chngFinBalSets;

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
                if (tmplt_beg1Ts1FieldChk.isChecked()) {
                    beg1 = tmplt_beg1Ts1Field.getValue();
                    thisFinBalSet.getBeg1().setTs1(beg1);
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);
                }

                LocalDateTime end1;
                if (tmplt_end1Ts1FieldChk.isChecked()) {
                    end1 = tmplt_end1Ts1Field.getValue();
                    thisFinBalSet.getEnd1().setTs1(end1);
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

        List<GenNode> thisFinBalSets = table.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        GenNode firstFinBalSet = thisFinBalSets.get(0);

        thisFinBalSets = new ArrayList<>(getFinBalSetsByParent1(firstFinBalSet.getParent1_Id()));
        thisFinBalSets.sort(Comparator.comparing(GenNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<GenNode> chngFinBalSets = new ArrayList<>();
        List<GenNode> finalChngFinBalSets = chngFinBalSets;

        AtomicInteger sortIdx = new AtomicInteger(0);
        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {
                GenNode thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
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

        List<GenNode> thisFinBalSets = new ArrayList<>(table.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        AtomicInteger sortIdxMin = new AtomicInteger(0);

        //order ascending
        thisFinBalSets.sort(Comparator.comparing(GenNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<GenNode> chngFinBalSets = new ArrayList<>();
        List<GenNode> finalChngFinBalSets = chngFinBalSets;

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
                    List<GenNode> nextFinBalSets =  getFinBalSetsBtwnSortIdx(-1
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

        List<GenNode> thisFinBalSets = new ArrayList<>(table.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSets.sort(Comparator.comparing(GenNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<GenNode> chngFinBalSets = new ArrayList<>();
        List<GenNode> finalChngFinBalSets = chngFinBalSets;

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
                    GenNode prevFinBalSet = getFinBalSetBySortIdx(sortIdx_-1,thisFinBalSet.getParent1_Id());
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

        List<GenNode> thisFinBalSets = new ArrayList<>(table.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBalSets.sort(Comparator.comparing(GenNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())));

        List<GenNode> chngFinBalSets = new ArrayList<>();
        List<GenNode> finalChngFinBalSets = chngFinBalSets;

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
                    GenNode nextFinBalSet = getFinBalSetBySortIdx(sortIdx_+1,thisFinBalSet.getParent1_Id());
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

        List<GenNode> thisFinBalSets = new ArrayList<>(table.getSelected().stream().toList());
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        //order descending
        thisFinBalSets.sort(Comparator.comparing(GenNode::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        List<GenNode> chngFinBalSets = new ArrayList<>();
        List<GenNode> finalChngFinBalSets = chngFinBalSets;

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
                    List<GenNode> nextFinBalSets =  getFinBalSetsBtwnSortIdx(sortIdx_
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



    private void updateFinBalSetHelper(List<GenNode> chngFinBalSets) {
        String logPrfx = "updateFinBalSetHelper";
        logger.trace(logPrfx + " --> ");

        if(chngFinBalSets != null && !chngFinBalSets.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing finBalSetsDl.load().");
            finBalSetsDl.load();

            List<GenNode> thisFinBalSets = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngFinBalSets.forEach(thisFinBalSet -> {
                //GenNode thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
                if (thisFinBalSet != null) {
                    thisFinBalSet = dataContext.merge(thisFinBalSet);
                    Boolean thisFinBalSetIsChanged = false;

                    thisFinBalSetIsChanged = updateId2Dup(thisFinBalSet) || thisFinBalSetIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finBalSetsDl.load().");
                finBalSetsDl.load();

                table.setSelected(thisFinBalSets);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    private Integer getSortIdxMax(GenNode thisFinBalSet) {
        String logPrfx = "getSortIdxMax";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxMax = null;
        String sortIdxMaxQry = "select max(e.sortIdx) from ampata_GenNode e"
                + " where e.className = 'FinBalSet'"
                + " and e.parent1_Id = :parent_Id1";
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

        List<GenNode> thisFinBalSets = table.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSets.forEach(thisFinBalSet -> {
            if (thisFinBalSet != null) {

                GenNode thisTrackedFinBalSet = dataContext.merge(thisFinBalSet);
                thisFinBalSet = thisTrackedFinBalSet;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinBalSet);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finBalSetsDl.load().");
            finBalSetsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinBalSets);
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

    @Subscribe(id = "finBalSetDc", target = Target.DATA_CONTAINER)
    public void onFinBalSetDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinBalSetDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBalSet = event.getSource().getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            //todo I observed thisFinBalSet is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.setClassName("FinBalSet");
        logger.debug(logPrfx + " --- className: FinBalSet");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
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
            GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
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

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
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

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- finBalSetDc is null, likely because no record is selected.");
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
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
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
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
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
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finBalSetTypesDl.load();
        logger.debug(logPrfx + " --- called finBalSetTypesDl.load() ");

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


    @Subscribe("end1Ts1Field")
    public void onEd1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onEd1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
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

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
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

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<GenNode> thisFinAccts = new ArrayList<>(tableFinAcct.getSelected().stream().toList());
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

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<GenNode> thisFinAccts = tableFinAcct.getItems().getItems().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because there are no records in the table.");
            notifications.create().withCaption("No records in the table.").show();
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

        GenNode thisFinBalSet = finBalSetDc.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinBalSet);

        logger.trace(logPrfx + " <-- ");
    }

    public Integer addFinAccts(@NotNull GenNode thisFinBalSet, List<GenNode> thisFinAccts, int iOptions){
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
                        finBals1_QryCnt = "select count(e) from ampata_GenNode e"
                                + " where e.className = 'FinBal'"
                                + " and e.finBalSet1_Id = :finBalSet1_Id"
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
                        GenNode finBal = dataManager.create(GenNode.class);
                        finBal.setClassName("FinBal");

                        finBal.setFinAcct1_Id(thisFinAcct);
                        finBal.setFinBalSet1_Id(thisFinBalSet);
                        if (thisFinBalSet.getFinDept1_Id() != null) {
                            finBal.setFinDept1_Id(thisFinBalSet.getFinDept1_Id());
                        }

                        if (thisFinBalSet.getType1_Id() != null) {
                            GenNodeType type1;
                            type1 = getFinBalTypeById2(thisFinBalSet.getType1_Id().getId2());
                            if (type1 != null) {
                                finBal.setType1_Id(type1);
                            }
                        }
                        finBal.setFinCurcy1_Id(thisFinAcct.getFinCurcy1_Id());

                        finBal.setSortKey("_AA" + thisFinBalSet.getSortKey() + thisFinAcct.getSortKey());

                        finBal.getBeg1().setTs1(thisFinBalSet.getBeg1().getTs1());
                        finBal.getEnd1().setTs1(thisFinBalSet.getEnd1().getTs1());
                        updateIdDt(finBal);

                        finBal.setAmtBegBal(BigDecimal.ZERO);
                        finBal.setAmtDebt(BigDecimal.ZERO);
                        finBal.setAmtCred(BigDecimal.ZERO);
                        finBal.setAmtNet(BigDecimal.ZERO);
                        finBal.setAmtEndBal(BigDecimal.ZERO);

                        if (thisFinBalSet.getFinBalSet1_Id() != null) {
                            GenNode finBal1;
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

    public Boolean updateCalcVals(GenNode thisFinBalSet){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinBalSetCalcVals(thisFinBalSet) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinBalSetCalcVals(@NotNull GenNode thisFinBalSet) {
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


    private Boolean updateIdParts(@NotNull GenNode thisFinBalSet) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateEnd1(thisFinBalSet) || isChanged;
        isChanged = updateIdDt(thisFinBalSet)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull GenNode thisFinBalSet) {
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

    private Boolean updateId2Calc(GenNode thisFinBalSet){
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

    private Boolean updateId2Cmp(@NotNull GenNode thisFinBalSet) {
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
    
    private Boolean updateId2Dup(@NotNull GenNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinBalSet.getId2Dup();
        if (thisFinBalSet.getId2() != null){
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinBalSet' and e.id2 = :id2 and e.id <> :id";
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

    private Boolean updateDesc1(@NotNull GenNode thisFinBalSet){
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
        if (thisFinBalSet.getBeg1() != null) {
            begDate = Objects.toString(thisFinBalSet.getBeg1().getDate1().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!begDate.equals("")) {
            begDate = "begDate " + begDate ;}
        logger.debug(logPrfx + " --- begDate: " + begDate);

        String endDate = "";
        if (thisFinBalSet.getEnd1() != null) {
            endDate = Objects.toString(thisFinBalSet.getEnd1().getDate1().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
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


    private Boolean updateIdDt(@NotNull GenNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBalSet.updateIdDt();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateEnd1(@NotNull GenNode thisFinBalSet) {
        // Assume thisFinBalSet is not null
        String logPrfx = "updateEnd1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBalSet.updateEnd1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private GenNode findFinBalSetById2(@NotNull String FinBalSet_Id2) {
        String logPrfx = "findFinBalSetById2";
        logger.trace(logPrfx + " --> ");

        if (FinBalSet_Id2 == null) {
            logger.debug(logPrfx + " --- FinBalSet_Id2 is null.");
            notifications.create().withCaption("FinBalSet_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinBalSet' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + FinBalSet_Id2);

        GenNode FinBalSet1_Id = null;
        try {
            FinBalSet1_Id = dataManager.load(GenNode.class)
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


    private GenNode getFinBalSetBySortIdx(@NotNull Integer sortIdx, GenNode parent1_Id) {
        String logPrfx = "getFinBalSetBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from ampata_GenNode e where e.className = 'FinBalSet'"
                + " and e.parent1_Id = :parent1_Id"
                + " and e.sortIdx = :sortIdx"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        if (parent1_Id == null){
            logger.debug(logPrfx + " --- qry:parent1_Id: null");

        }else{
            logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        }
        logger.debug(logPrfx + " --- qry:sortIdx: " + sortIdx);

        GenNode finBalSet = null;
        try {
            finBalSet = dataManager.load(GenNode.class)
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


    private List<GenNode> getFinBalSetsBtwnSortIdx(@NotNull Integer sortIdxA ,@NotNull Integer sortIdxB, GenNode parent1_Id) {
        String logPrfx = "getFinBalSetsBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from ampata_GenNode e where e.className = 'FinBalSet'"
                + " and e.parent1_Id = :parent1_Id"
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

        List<GenNode> finBalSets = null;
        try {
            finBalSets = dataManager.load(GenNode.class)
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

    private List<GenNode> getFinBalSetsByParent1(GenNode parent1_Id) {
        String logPrfx = "getFinBalSetsByParent1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from ampata_GenNode e where e.className = 'FinBalSet'"
                + " and e.parent1_Id = :parent1_Id"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        if (parent1_Id == null){
            logger.debug(logPrfx + " --- qry:parent1_Id:null");
        }else {
            logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        }

        List<GenNode> finBalSets = null;
        try {
            finBalSets = dataManager.load(GenNode.class)
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

    private GenNodeType getFinBalTypeById2(@NotNull String id2) {
        String logPrfx = "getFinBalTypeById2";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from ampata_GenNodeType e where e.className = 'FinBal'"
                + " and e.id2 = :id2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + id2);

        GenNodeType finBalType = null;
        try {
            finBalType = dataManager.load(GenNodeType.class)
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


    private GenNode getFinBalByFinBalSet1_FinAcct1(@NotNull GenNode finBalSet1, @NotNull GenNode finAcct1) {
        String logPrfx = "getFinBalByFinBalSet1_FinAcct1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from ampata_GenNode e where e.className = 'FinBal'"
                + " and e.finBalSet1_Id = :finBalSet1"
                + " and e.finAcct1_Id = :finAcct1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:finBalSet1: " + finBalSet1);
        logger.debug(logPrfx + " --- qry:finAcct1: " + finAcct1);

        GenNode finBal = null;
        try {
            finBal = dataManager.load(GenNode.class)
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
                + " from ampata_GenNode e"
                + " where e.className = 'FinBalSet'"
                + " and e.status IS NOT NULL"
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

    public void loadTableFinBal(@NotNull GenNode thisFinBalSet) {
        String logPrfx = "loadTableFinBal";
        logger.trace(logPrfx + " --> ");

        finBalsDc = dataComponents.createCollectionContainer(GenNode.class);
        finBalsDl = dataComponents.createCollectionLoader();

        try{
            String finTxactItms1_Qry;
            finTxactItms1_Qry = "select e from ampata_GenNode e"
                    + " where e.className = 'FinBal'"
                    + " and e.finBalSet1_Id = :finBalSet1_Id"
                    + "";
            finBalsDl.setQuery(finTxactItms1_Qry);
            finBalsDl.setParameter("finBalSet1_Id",thisFinBalSet);

            FetchPlan finBalsFp = fetchPlans.builder(GenNode.class)
                    .addFetchPlan("finBal-fetch-plan-base")
                    .build();
            finBalsDl.setFetchPlan(finBalsFp);
            finBalsDl.setContainer(finBalsDc);

            finBalsDl.setDataContext(getScreenData().getDataContext());

            tableFinBal.setItems(new ContainerTableItems<>(finBalsDc));

//            finBalsDl.load();
//            logger.debug(logPrfx + " --- finBalsDc returned rows: " + finBalsDc.getItems().size());
//            pagetableFinBal.setDataBinder(...);


        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
        }

        logger.trace(logPrfx + " <-- ");
    }






}