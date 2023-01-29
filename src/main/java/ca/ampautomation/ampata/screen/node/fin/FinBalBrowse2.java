package ca.ampautomation.ampata.screen.node.fin;

import ca.ampautomation.ampata.entity.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UiController("ampata_FinBal.browse2")
@UiDescriptor("fin-bal-browse2.xml")
@LookupComponent("table")
public class FinBalBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finBalSet1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finAcct1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finDept1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finCurcy1_Id;


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
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> finBalsDc;

    @Autowired
    private CollectionLoader<GenNode> finBalsDl;

    @Autowired
    private InstanceContainer<GenNode> finBalDc;


    private CollectionContainer<GenNodeType> finBalTypesDc;

    private CollectionLoader<GenNodeType> finBalTypesDl;


    private CollectionContainer<GenNode> finBalSetsDc;

    private CollectionLoader<GenNode> finBalSetsDl;


    private CollectionContainer<GenNode> finAcctsDc;

    private CollectionLoader<GenNode> finAcctsDl;


    private CollectionContainer<GenNode> finDeptsDc;

    private CollectionLoader<GenNode> finDeptsDl;


    private CollectionContainer<GenNode> finCurcysDc;

    private CollectionLoader<GenNode> finCurcysDl;


    private CollectionContainer<GenNode> finBal1sDc;

    private CollectionLoader<GenNode> finBal1sDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finBalSet1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finAcct1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finDept1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finBal1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;

    @Autowired
    private ComboBox<String> statusField;


    @Autowired
    private Table<GenNode> tableFinTxactItm;

    @Autowired
    private CollectionContainer<GenNode> finTxactItmsDc;

    @Autowired
    private CollectionLoader<GenNode> finTxactItmsDl;

    @Autowired
    private Pagination pagetableTinTxactItm;


    Logger logger = LoggerFactory.getLogger(FinBalBrowse2.class);


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        finBalTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finBalTypesDl = dataComponents.createCollectionLoader();
        finBalTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinBal' order by e.id2");
        FetchPlan finBalTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalTypesDl.setFetchPlan(finBalTypesFp);
        finBalTypesDl.setContainer(finBalTypesDc);
        finBalTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finBalTypesDc);


        finBalSetsDc = dataComponents.createCollectionContainer(GenNode.class);
        finBalSetsDl = dataComponents.createCollectionLoader();
        finBalSetsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinBalSet' order by e.id2");
        FetchPlan finBalSetsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSetsDl.setFetchPlan(finBalSetsFp);
        finBalSetsDl.setContainer(finBalSetsDc);
        finBalSetsDl.setDataContext(getScreenData().getDataContext());

        finBalSet1_IdField.setOptionsContainer(finBalSetsDc);
        EntityComboBox<GenNode> propFilterCmpnt_finBalSet1_Id = (EntityComboBox<GenNode>) propFilter_finBalSet1_Id.getValueComponent();
        propFilterCmpnt_finBalSet1_Id.setOptionsContainer(finBalSetsDc);


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


        finAcctsDc = dataComponents.createCollectionContainer(GenNode.class);
        finAcctsDl = dataComponents.createCollectionLoader();
        finAcctsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctsDl.setFetchPlan(finAcctsFp);
        finAcctsDl.setContainer(finAcctsDc);
        finAcctsDl.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(finAcctsDc);
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finAcct1_Id = (EntityComboBox<GenNode>) propFilter_finAcct1_Id.getValueComponent();
        propFilterCmpnt_finAcct1_Id.setOptionsContainer(finAcctsDc);


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
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finCurcy1_Id = (EntityComboBox<GenNode>) propFilter_finCurcy1_Id.getValueComponent();
        propFilterCmpnt_finCurcy1_Id.setOptionsContainer(finCurcysDc);


        finBal1sDc = dataComponents.createCollectionContainer(GenNode.class);
        finBal1sDl = dataComponents.createCollectionLoader();
        finBal1sDl.setQuery("select e from ampata_GenNode e where e.className = 'FinBal' order by e.id2");
        FetchPlan finBal1sFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBal1sDl.setFetchPlan(finBal1sFp);
        finBal1sDl.setContainer(finBal1sDc);
        finBal1sDl.setDataContext(getScreenData().getDataContext());

        finBal1_IdField.setOptionsContainer(finBal1sDc);
        
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        finBalsDl.load();
        table.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "finBalsDc", target = Target.DATA_CONTAINER)
    public void onFinBalsDcCollectionChange(CollectionContainer.CollectionChangeEvent<GenNode> event) {
        String logPrfx = "onFinBalsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "finBalsDc", target = Target.DATA_CONTAINER)
    public void onFinBalsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinBalsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = event.getItem();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBal.setClassName("FinBal");
        logger.debug(logPrfx + " --- className: FinBal");

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

        finBalSetsDl.load();
        logger.debug(logPrfx + " --- called finBalSetsDl.load() ");

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        finDeptsDl.load();
        logger.debug(logPrfx + " --- called finDeptsDl.load() ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        finBal1sDl.load();
        logger.debug(logPrfx + " --- called finBal1sDl.load() ");

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

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Bal_Pr_Upd()");
        repo.execFinBalPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Bal_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finBalsDl.load()");
        finBalsDl.load();
        logger.debug(logPrfx + " --- finished finBalsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");
        
        List<GenNode> thisFinBals = table.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBals.forEach(orig -> {
            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

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
            finBalsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinBal " + copy.getId2() + " "
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

        List<GenNode> thisFinBals = table.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinBals.forEach(orig -> {

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
            finBalsDc.getMutableItems().add(savedCopy);
            logger.debug("Derived FinBal " + copy.getId2() + " "
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

        List<GenNode> thisFinBals = table.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBals.forEach(thisFinBal -> {
            GenNode thisTrackedFinBal = dataContext.merge(thisFinBal);
            thisFinBal = thisTrackedFinBal;
            if (thisFinBal != null) {

                Boolean thisFinBalIsChanged = false;
                
                LocalDateTime beg1;
                if (tmplt_beg1Ts1FieldChk.isChecked()) {
                    thisFinBalIsChanged = true;
                    beg1 = tmplt_beg1Ts1Field.getValue();
                    thisFinBal.getBeg1().setTs1(beg1);
                }

                LocalDateTime end1;
                if (tmplt_end1Ts1FieldChk.isChecked()) {
                    thisFinBalIsChanged = true;
                    end1 = tmplt_end1Ts1Field.getValue();
                    thisFinBal.getEnd1().setTs1(end1);
                    updateIdDt(thisFinBal);
                }


                if (tmplt_StatusFieldChk.isChecked()
                ) {
                    thisFinBalIsChanged = true;
                    thisFinBal.setStatus(tmplt_StatusField.getValue());
                }

                thisFinBalIsChanged = updateId2Calc(thisFinBal) || thisFinBalIsChanged;
                thisFinBalIsChanged = updateId2(thisFinBal) || thisFinBalIsChanged;
                thisFinBalIsChanged = updateId2Cmp(thisFinBal) || thisFinBalIsChanged;

                if (thisFinBalIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinBal).");
                    //dataManager.save(thisFinBal);
                }


            }
        });
        updateFinBalHelper();
        logger.trace(logPrfx + " <-- ");
    }
    private void updateFinBalHelper() {
        String logPrfx = "updateFinBalHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finBalsDl.load().");
            finBalsDl.load();

            List<GenNode> thisFinBals = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinBals.forEach(thisFinBal -> {
                //GenNode thisTrackedFinBal = dataContext.merge(thisFinBal);
                if (thisFinBal != null) {
                    GenNode thisTrackedFinBal = dataContext.merge(thisFinBal);
                    thisFinBal = thisTrackedFinBal;

                    Boolean thisFinBalIsChanged = false;

                    thisFinBalIsChanged = updateId2Dup(thisFinBal) || thisFinBalIsChanged;

                    if (thisFinBalIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinBal).");
                        //dataManager.save(thisFinBal);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finBalsDl.load().");
                finBalsDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinBals);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinBals = table.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBals.forEach(thisFinBal -> {
            if (thisFinBal != null) {

                GenNode thisTrackedFinBal = dataContext.merge(thisFinBal);
                thisFinBal = thisTrackedFinBal;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinBal);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finBalsDl.load().");
            finBalsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinBals);
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

    @Subscribe(id = "finBalDc", target = Target.DATA_CONTAINER)
    public void onFinBalDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinBalDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = event.getSource().getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBal.setClassName("FinBal");
        logger.debug(logPrfx + " --- className: FinBal");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinBal = finBalDc.getItemOrNull();
            if (thisFinBal == null) {
                logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinBal);
            updateId2Dup(thisFinBal);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinBal);
        updateId2Cmp(thisFinBal);
        updateId2Dup(thisFinBal);

        logger.debug(logPrfx + " --- id2: " + thisFinBal.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinBal);
        updateId2Cmp(thisFinBal);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinBal.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finBalTypesDl.load();
        logger.debug(logPrfx + " --- called finBalTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinBalSet1_IdFieldListBtn")
    public void onUpdateFinBalSet1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinBalSet1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finBalSetsDl.load();
        logger.debug(logPrfx + " --- called finBalSetsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

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
    
    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtBegBalBtn")
    public void onUpdateAmtBegBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtBegBal(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtBegBalBtn")
    public void onSetNullAmtBegBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtBegBal_ = thisFinBal.getAmtBegBal();
        BigDecimal amtBegBal = null;
        if(!Objects.equals(amtBegBal_, amtBegBal)){
            thisFinBal.setAmtBegBal(amtBegBal);
            logger.debug(logPrfx + " --- amtBegBal: " + amtBegBal);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtBegBalCalcBtn")
    public void onUpdateAmtBegBalCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtBegBalCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtBegBalCalc(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtDebtBtn")
    public void onUpdateAmtDebtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtDebtBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtDebt(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtDebtBtn")
    public void onSetNullAmtDebtBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtDebtBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtDebt_ = thisFinBal.getAmtDebt();
        BigDecimal amtDebt = null;
        if(!Objects.equals(amtDebt_, amtDebt)){
            thisFinBal.setAmtDebt(amtDebt);
            logger.debug(logPrfx + " --- amtDebt: " + amtDebt);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtCredBtn")
    public void onUpdateAmtCredBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtCredBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtCred(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtCredBtn")
    public void onSetNullAmtCredBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtCredBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtCred_ = thisFinBal.getAmtCred();
        BigDecimal amtCred = null;
        if(!Objects.equals(amtCred_, amtCred)){
            thisFinBal.setAmtCred(amtCred);
            logger.debug(logPrfx + " --- amtCred: " + amtCred);
        }

        logger.trace(logPrfx + " <-- ");

    }
    
    @Subscribe("setNullAmtEndBalBtn")
    public void onSetNullAmtEndBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtEndBal_ = thisFinBal.getAmtEndBal();
        BigDecimal amtEndBal = null;
        if(!Objects.equals(amtEndBal_, amtEndBal)){
            thisFinBal.setAmtEndBal(amtEndBal);
            logger.debug(logPrfx + " --- amtEndBal: " + amtEndBal);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtEndBalBtn")
    public void onUpdateAmtEndBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtEndBal(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtEndBalCalcBtn")
    public void onUpdateAmtEndBalCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtEndBalCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtEndBalCalc(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinBal1_IdFieldListBtn")
    public void onUpdateFinBal1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinBal1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finBal1sDl.load();
        logger.debug(logPrfx + " --- called finBal1sDl.load() ");

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
            GenNode thisFinBal = finBalDc.getItemOrNull();
            if (thisFinBal == null) {
                logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdDt(thisFinBal)");
            updateIdDt(thisFinBal);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinBal)");
            updateId2Calc(thisFinBal);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("amtBegBalField")
    public void onAmtBegBalFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "amtBegBalField";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinBal = finBalDc.getItemOrNull();
            if (thisFinBal == null) {
                logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
        }

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("amtBegBalCalcField")
    public void onAmtBegBalCalcFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "amtBegBalCalcField";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinBal = finBalDc.getItemOrNull();
            if (thisFinBal == null) {
                logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtEndBalCalc(thisFinBal);
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


    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinBal = finBalDc.getItemOrNull();
            if (thisFinBal == null) {
                logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtNet(thisFinBal);
            updateAmtEndBalCalc(thisFinBal);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("amtCredField")
    public void onAmtCredFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onAmtCredFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinBal = finBalDc.getItemOrNull();
            if (thisFinBal == null) {
                logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtNet(thisFinBal);
            updateAmtEndBalCalc(thisFinBal);
        }

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateInstItemManValsBtn")
    public void onInstItemManValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onInstItemManValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateManVals(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    public Boolean updateManVals(GenNode thisFinBal){
        String logPrfx = "updateManVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateAmtDebt(thisFinBal) || isChanged;
        isChanged = updateAmtCred(thisFinBal) || isChanged;

        isChanged = updateAmtNet(thisFinBal) || isChanged;

        isChanged = updateAmtBegBal(thisFinBal) || isChanged;
        isChanged = updateAmtEndBal(thisFinBal) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateCalcVals(GenNode thisFinBal){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinBalCalcVals(thisFinBal) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinBalCalcVals(@NotNull GenNode thisFinBal) {
        String logPrfx = "updateFinBalCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinBal Object
        isChanged = updateIdDt(thisFinBal) || isChanged;
        isChanged = updateId2Calc(thisFinBal) || isChanged;
        isChanged = updateId2Cmp(thisFinBal) || isChanged;
        isChanged = updateId2Dup(thisFinBal) || isChanged;
        isChanged = updateDesc1(thisFinBal) || isChanged;

        isChanged = updateAmtNet(thisFinBal) || isChanged;
        isChanged = updateAmtBegBalCalc(thisFinBal) || isChanged;
        isChanged = updateFinTxactItms1_FieldSet(thisFinBal) || isChanged;
        isChanged = updateAmtEndBalCalc(thisFinBal) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull GenNode thisFinBal) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateEnd1(thisFinBal) || isChanged;
        isChanged = updateIdDt(thisFinBal)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull GenNode thisFinBal) {
        // Assume thisFinBal is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinBal.getId2();
        String id2 = thisFinBal.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinBal.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(GenNode thisFinBal){
        // Assume thisFinBal is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinBal.getId2Calc();
        String id2Calc = thisFinBal.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinBal.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull GenNode thisFinBal) {
        // Assume thisFinBal is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinBal.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinBal.getId2(),thisFinBal.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinBal.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateId2Dup(@NotNull GenNode thisFinBal) {
        // Assume thisFinBal is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinBal.getId2Dup();
        if (thisFinBal.getId2() != null){
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinBal' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try{
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",thisFinBal.getId())
                        .parameter("id2",thisFinBal.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinBal.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinBal.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull GenNode thisFinBal){
        // Assume thisFinBal is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String desc1_ = thisFinBal.getDesc1();
        String  status = Objects.toString(thisFinBal.getStatus(),"");
        if (!status.equals("")){
            status = "" + status;
        }
        logger.debug(logPrfx + " --- status: " + status);

        String begDate = "";
        if (thisFinBal.getBeg1() != null) {
            begDate = Objects.toString(thisFinBal.getBeg1().getDate1().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!begDate.equals("")) {
            begDate = "begDate " + begDate ;}
        logger.debug(logPrfx + " --- begDate: " + begDate);

        String  begBal = Objects.toString(thisFinBal.getAmtBegBal(),"");
        if (!begBal.equals("")){
            begBal = "begBal " + begBal;
        }
        logger.debug(logPrfx + " --- begBal: " + begBal);

        String endDate = "";
        if (thisFinBal.getEnd1() != null) {
            endDate = Objects.toString(thisFinBal.getEnd1().getDate1().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!endDate.equals("")) {
            endDate = "endDate " + endDate ;}
        logger.debug(logPrfx + " --- endDate: " + endDate);

        String acct = "";
        if (thisFinBal.getFinAcct1_Id() != null) {
            acct = Objects.toString(thisFinBal.getFinAcct1_Id().getId2(),"");}
        if (!acct.equals("")) {
            acct = "Acct [" + acct + "]";}
        logger.debug(logPrfx + " --- acct: " + acct);

        String dept = "";
        if (thisFinBal.getFinDept1_Id() != null) {
            dept = Objects.toString(thisFinBal.getFinDept1_Id().getId2(),"");}
        if (!dept.equals("")) {
            dept = " dept [" + dept + "]";}
        logger.debug(logPrfx + " --- dept: " + dept);

        String  endBalCalc = Objects.toString(thisFinBal.getAmtEndBalCalc(),"");
        if (!endBalCalc.equals("")){
            endBalCalc = "endBalCalc " + endBalCalc;
        }
        logger.debug(logPrfx + " --- endBalCalc: " + endBalCalc);


        String tag = "";
        String tag1 = "";
        if (thisFinBal.getGenTag1_Id() != null) {
            tag1 = Objects.toString(thisFinBal.getGenTag1_Id().getId2());}
        String tag2 = "";
        if (thisFinBal.getGenTag1_Id() != null) {
            tag2 = Objects.toString(thisFinBal.getGenTag2_Id().getId2());}
        String tag3 = "";
        if (thisFinBal.getGenTag1_Id() != null) {
            tag3 = Objects.toString(thisFinBal.getGenTag3_Id().getId2());}
        String tag4 = "";
        if (thisFinBal.getGenTag1_Id() != null) {
            tag4 = Objects.toString(thisFinBal.getGenTag4_Id().getId2());}
        if (!(tag1 + tag2 + tag3 + tag4).equals("")) {
            tag = "tag [" + String.join(",",tag1, tag2, tag3, tag4) + "]";}
        logger.debug(logPrfx + " --- tag: " + tag);

        List<String> list = Arrays.asList(
                acct
                ,dept
                ,begDate
                ,"to"
                ,endDate
                ,endBalCalc
                ,tag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            thisFinBal.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull GenNode thisFinBal) {
        // Assume thisFinBal is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBal.updateIdDt();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtDebt(@NotNull GenNode thisFinBal){
        String logPrfx = "updateAmtDebt";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;
        BigDecimal amtDebt_ = thisFinBal.getAmtDebt();
        BigDecimal amtDebt = thisFinBal.getFinTxactItms1_AmtDebtSumCalc();

        if (!Objects.equals(amtDebt_, amtDebt)){
            thisFinBal.setAmtDebt(amtDebt);
            logger.debug(logPrfx + " --- amtDebt: " + amtDebt);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtCred(@NotNull GenNode thisFinBal){
        String logPrfx = "updateAmtCred";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;
        BigDecimal amtCred_ = thisFinBal.getAmtCred();
        BigDecimal amtCred = thisFinBal.getFinTxactItms1_AmtCredSumCalc();

        if (!Objects.equals(amtCred_, amtCred)){
            thisFinBal.setAmtCred(amtCred);
            logger.debug(logPrfx + " --- amtCred: " + amtCred);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtNet(@NotNull GenNode thisFinBal){
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;
        BigDecimal amtNet_ = thisFinBal.getAmtNet();
        BigDecimal amtNet = null;
        if (thisFinBal.getAmtDebt() != null && thisFinBal.getAmtCred() != null){
            if (thisFinBal.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                    && thisFinBal.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                amtNet = thisFinBal.getAmtDebt().subtract(thisFinBal.getAmtCred());

            }else{
                amtNet = thisFinBal.getAmtCred().subtract(thisFinBal.getAmtDebt());
            }
        }

        if (!Objects.equals(amtNet_, amtNet)){
            thisFinBal.setAmtNet(amtNet);
            logger.debug(logPrfx + " --- amtNet: " + amtNet);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtBegBal(@NotNull GenNode thisFinBal){
        String logPrfx = "updateAmtBegBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtBegBal_ = thisFinBal.getAmtBegBal();
        BigDecimal amtBegBal = thisFinBal.getAmtBegBalCalc();
            if(!Objects.equals(amtBegBal_, amtBegBal)){
            thisFinBal.setAmtBegBal(amtBegBal);
            logger.debug(logPrfx + " --- amtBegBal: " + amtBegBal);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtBegBalCalc(@NotNull GenNode thisFinBal){
        String logPrfx = "updateAmtBegBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtBegBalCalc_ = thisFinBal.getAmtEndBalCalc();
        BigDecimal amtBegBalCalc = null;

        final String SEP = "/";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd")
                .toFormatter();

        if (thisFinBal.getFinBal1_Id() == null) {
            amtBegBalCalc = BigDecimal.ZERO;
        }else{
            amtBegBalCalc = thisFinBal.getFinBal1_Id().getAmtEndBalCalc();
        }

        if (!Objects.equals(amtBegBalCalc_, amtBegBalCalc)){
            thisFinBal.setAmtBegBalCalc(amtBegBalCalc);
            logger.debug(logPrfx + " --- amtBegBalCalc: " + amtBegBalCalc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtEndBal(@NotNull GenNode thisFinBal){
        String logPrfx = "updateAmtEndBal";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtEndBal_ = thisFinBal.getAmtEndBal();
        BigDecimal amtEndBal = thisFinBal.getAmtBegBalCalc() == null
                || thisFinBal.getAmtNet() == null
                ? null
                : thisFinBal.getAmtBegBalCalc().add(thisFinBal.getAmtNet());

        if(!Objects.equals(amtEndBal_, amtEndBal)){
            thisFinBal.setAmtEndBal(amtEndBal);
            logger.debug(logPrfx + " --- amtEndBal: " + amtEndBal);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtEndBalCalc(@NotNull GenNode thisFinBal){
        String logPrfx = "updateAmtEndBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtEndBalCalc_ = thisFinBal.getAmtEndBalCalc();
        BigDecimal amtEndBalCalc = null;
            if (thisFinBal.getAmtNet() != null){
                if (thisFinBal.getAmtBegBalCalc() != null ){
                    amtEndBalCalc = thisFinBal.getAmtBegBalCalc().add(thisFinBal.getAmtNet());
                }else {
                    if (thisFinBal.getAmtBegBal() != null) {
                        amtEndBalCalc = thisFinBal.getAmtBegBal().add(thisFinBal.getAmtNet());
                    }
                }
            }

            if (!Objects.equals(amtEndBalCalc_, amtEndBalCalc)){
            thisFinBal.setAmtEndBalCalc(amtEndBalCalc);
            logger.debug(logPrfx + " --- amtEndBalCalc: " + amtEndBalCalc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinTxactItms1_FieldSet(@NotNull GenNode thisFinBal) {
        String logPrfx = "updateFinTxactItms1_FieldSet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        GenNode finAcct1 = thisFinBal.getFinAcct1_Id();
        GenNode finDept1 = thisFinBal.getFinDept1_Id();

        if (finAcct1 == null) {
            logger.debug(logPrfx + " --- finAcct1 is null.");
            notifications.create().withCaption("finAcct1 is null.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        LocalDate begDt = thisFinBal.getBeg1() == null ? null : thisFinBal.getBeg1().getDate1();
        if (begDt == null) {
            logger.debug(logPrfx + " --- begDt is null.");
            notifications.create().withCaption("begDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }
        LocalDate endDt = thisFinBal.getEnd1() == null ? null : thisFinBal.getEnd1().getDate1();
        if (endDt == null) {
            logger.debug(logPrfx + " --- endDt is null.");
            notifications.create().withCaption("endDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        Integer finTxactItms1_IdCntCalc_ = thisFinBal.getFinTxactItms1_IdCntCalc();
        Integer finTxactItms1_IdCntCalc = null;

        try{
            String finTxactItms1_QryCnt;
            if (finDept1 == null) {
                finTxactItms1_QryCnt = "select count(e) from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItms1_IdCntCalc = dataManager.loadValue(finTxactItms1_QryCnt,Integer.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }else{
                finTxactItms1_QryCnt = "select count(e) from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItms1_IdCntCalc = dataManager.loadValue(finTxactItms1_QryCnt,Integer.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("finDept1_Id",finDept1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }
            if (finTxactItms1_IdCntCalc == null) {
                finTxactItms1_IdCntCalc = 0;}
            logger.debug(logPrfx + " --- finTxactItms1_IdCntCalc: " + finTxactItms1_IdCntCalc);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- finTxactItms1_IdCntCalc: null");
        }

        if (!Objects.equals(finTxactItms1_IdCntCalc_, finTxactItms1_IdCntCalc)){
            thisFinBal.setFinTxactItms1_IdCntCalc(finTxactItms1_IdCntCalc);
            logger.debug(logPrfx + " --- finTxactItms1_IdCntCalc: " + finTxactItms1_IdCntCalc);
            isChanged = true;
        }

        BigDecimal finTxactItms1_AmtDebtSumCalc_ = thisFinBal.getFinTxactItms1_AmtDebtSumCalc();
        BigDecimal finTxactItms1_AmtDebtSumDiff_ = thisFinBal.getFinTxactItms1_AmtDebtSumDiff();

        BigDecimal finTxactItms1_AmtDebtSumCalc = null;
        BigDecimal finTxactItms1_AmtDebtSumDiff = null;

        try{
            String finTxactItms1_QryDebt;
            if (finDept1 == null) {
                finTxactItms1_QryDebt = "select sum(e.amtDebt) from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItms1_AmtDebtSumCalc = dataManager.loadValue(finTxactItms1_QryDebt,BigDecimal.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }else{
                finTxactItms1_QryDebt = "select sum(e.amtDebt) from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItms1_AmtDebtSumCalc = dataManager.loadValue(finTxactItms1_QryDebt,BigDecimal.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("finDept1_Id",finDept1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }
            if (finTxactItms1_AmtDebtSumCalc == null) {
                finTxactItms1_AmtDebtSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: " + finTxactItms1_AmtDebtSumCalc);

            if (thisFinBal.getAmtDebt() == null){
                finTxactItms1_AmtDebtSumDiff = BigDecimal.valueOf(0);
            }else{
                finTxactItms1_AmtDebtSumDiff = thisFinBal.getAmtDebt().subtract(finTxactItms1_AmtDebtSumCalc);
            }
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: " + finTxactItms1_AmtDebtSumDiff);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: null");
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: null");
        }

        if (!Objects.equals(finTxactItms1_AmtDebtSumCalc_, finTxactItms1_AmtDebtSumCalc)){
            thisFinBal.setFinTxactItms1_AmtDebtSumCalc(finTxactItms1_AmtDebtSumCalc);
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: " + finTxactItms1_AmtDebtSumCalc);
            isChanged = true;
        }

        if (!Objects.equals(finTxactItms1_AmtDebtSumDiff_, finTxactItms1_AmtDebtSumDiff)){
            thisFinBal.setFinTxactItms1_AmtDebtSumDiff(finTxactItms1_AmtDebtSumDiff);
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: " + finTxactItms1_AmtDebtSumDiff);
            isChanged = true;
        }


        BigDecimal finTxactItms1_AmtCredSumCalc_ = thisFinBal.getFinTxactItms1_AmtCredSumCalc();
        BigDecimal finTxactItms1_AmtCredSumDiff_ = thisFinBal.getFinTxactItms1_AmtCredSumDiff();

        BigDecimal finTxactItms1_AmtCredSumCalc = null;
        BigDecimal finTxactItms1_AmtCredSumDiff = null;

        try {
            String finTxactItms1_QryCred;
            if (finDept1 == null) {
                finTxactItms1_QryCred = "select sum(e.amtCred) from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItms1_AmtCredSumCalc = dataManager.loadValue(finTxactItms1_QryCred,BigDecimal.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }else{
                finTxactItms1_QryCred = "select sum(e.amtCred) from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItms1_AmtCredSumCalc = dataManager.loadValue(finTxactItms1_QryCred,BigDecimal.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("finDept1_Id",finDept1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }
            if (finTxactItms1_AmtCredSumCalc == null) {
                finTxactItms1_AmtCredSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: " + finTxactItms1_AmtCredSumCalc);

            if (thisFinBal.getAmtDebt() == null){
                finTxactItms1_AmtCredSumDiff = BigDecimal.valueOf(0);
            }else{
                finTxactItms1_AmtCredSumDiff = thisFinBal.getAmtCred().subtract(finTxactItms1_AmtCredSumCalc);
            }
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: " + finTxactItms1_AmtCredSumDiff);

        } catch (IllegalStateException e ){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: null");
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: null");
        }


        if (!Objects.equals(finTxactItms1_AmtCredSumCalc_, finTxactItms1_AmtCredSumCalc)){
            thisFinBal.setFinTxactItms1_AmtCredSumCalc(finTxactItms1_AmtCredSumCalc);
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: " + finTxactItms1_AmtCredSumCalc);
            isChanged = true;
        }

        if (!Objects.equals(finTxactItms1_AmtCredSumDiff_, finTxactItms1_AmtCredSumDiff)){
            thisFinBal.setFinTxactItms1_AmtCredSumDiff(finTxactItms1_AmtCredSumDiff);
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: " + finTxactItms1_AmtCredSumDiff);
            isChanged = true;
        }

        BigDecimal finTxactItms1_AmtNetSumCalc_ = thisFinBal.getFinTxactItms1_AmtNetSumCalc();
        BigDecimal finTxactItms1_AmtNetSumDiff_ = thisFinBal.getFinTxactItms1_AmtNetSumDiff();

        BigDecimal finTxactItms1_AmtNetSumCalc = null;
        BigDecimal finTxactItms1_AmtNetSumDiff = null;

        if (finTxactItms1_AmtDebtSumCalc == null || finTxactItms1_AmtCredSumCalc == null){
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: null");
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: null");
        }else{

            if (thisFinBal.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                    && thisFinBal.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtDebtSumCalc.subtract(finTxactItms1_AmtCredSumCalc);
            }else{
                finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtCredSumCalc.subtract(finTxactItms1_AmtDebtSumCalc);
            }

            if (!Objects.equals(finTxactItms1_AmtNetSumCalc_, finTxactItms1_AmtNetSumCalc)){
                thisFinBal.setFinTxactItms1_AmtNetSumCalc(finTxactItms1_AmtNetSumCalc);
                logger.debug(logPrfx + " --- finTxactItms1_AmtNetSumCalc: " + finTxactItms1_AmtNetSumCalc);
                isChanged = true;
            }

            finTxactItms1_AmtNetSumDiff = finTxactItms1_AmtNetSumCalc.subtract(thisFinBal.getAmtNet());

            if (!Objects.equals(finTxactItms1_AmtNetSumDiff_, finTxactItms1_AmtNetSumDiff)){
                thisFinBal.setFinTxactItms1_AmtNetSumDiff(finTxactItms1_AmtNetSumDiff);
                logger.debug(logPrfx + " --- finTxactItms1_AmtNetSumDiff: " + finTxactItms1_AmtNetSumDiff);
                isChanged = true;
            }

        }

        Boolean finTxactItms1_AmtEqCalc_ = thisFinBal.getFinTxactItms1_AmtEqCalc();
        Boolean finTxactItms1_AmtEqCalc = finTxactItms1_AmtNetSumDiff == null ? null : finTxactItms1_AmtNetSumDiff.compareTo(BigDecimal.ZERO) == 0;

        if (!Objects.equals(finTxactItms1_AmtEqCalc_, finTxactItms1_AmtEqCalc)){
            thisFinBal.setFinTxactItms1_AmtEqCalc(finTxactItms1_AmtEqCalc);
            logger.debug(logPrfx + " --- finTxactItms1_AmtEqCalc: " + finTxactItms1_AmtEqCalc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateEnd1(@NotNull GenNode thisFinBal) {
        // Assume thisFinBal is not null
        String logPrfx = "updateEnd1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBal.updateEnd1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private GenNode findFinBalById2(@NotNull String FinBal_Id2) {
        String logPrfx = "findFinBalById2";
        logger.trace(logPrfx + " --> ");

        if (FinBal_Id2 == null) {
            logger.debug(logPrfx + " --- FinBal_Id2 is null.");
            notifications.create().withCaption("FinBal_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinBal' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + FinBal_Id2);

        GenNode FinBal1_Id = null;
        try {
            FinBal1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", FinBal_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return FinBal1_Id;

    }


    private void reloadStatusList(){
        String logPrfx = "reloadStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status"
                + " from ampata_GenNode e"
                + " where e.className = 'FinBal'"
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
            // see onUpdateFinBalItm1_Desc1Field
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

    @Subscribe("loadTableFinTxactItmBtn")
    public void onLoadTableFinTxactItmBtnClick(Button.ClickEvent event) {
        String logPrfx = "onLoadTableFinTxactItmBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinBal = finBalDc.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        loadTableFinTxactItm(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    public void loadTableFinTxactItm(@NotNull GenNode thisFinBal) {
        String logPrfx = "loadTableFinTxactItm";
        logger.trace(logPrfx + " --> ");

        GenNode finAcct1 = thisFinBal.getFinAcct1_Id();
        GenNode finDept1 = thisFinBal.getFinDept1_Id();

        if (finAcct1 == null) {
            logger.debug(logPrfx + " --- finAcct1 is null.");
            notifications.create().withCaption("finAcct1 is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        LocalDate begDt = thisFinBal.getBeg1() == null ? null : thisFinBal.getBeg1().getDate1();
        if (begDt == null) {
            logger.debug(logPrfx + " --- begDt is null.");
            notifications.create().withCaption("begDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        LocalDate endDt = thisFinBal.getEnd1() == null ? null : thisFinBal.getEnd1().getDate1();
        if (endDt == null) {
            logger.debug(logPrfx + " --- endDt is null.");
            notifications.create().withCaption("endDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        finTxactItmsDc = dataComponents.createCollectionContainer(GenNode.class);
        finTxactItmsDl = dataComponents.createCollectionLoader();

        try{
            String finTxactItms1_Qry;
            if (finDept1 == null) {
                finTxactItms1_Qry = "select e from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItmsDl.setQuery(finTxactItms1_Qry);
                finTxactItmsDl.setParameter("finAcct1_Id",finAcct1);
                finTxactItmsDl.setParameter("begDt",begDt);
                finTxactItmsDl.setParameter("endDt",endDt);
                ;
            }else{
                finTxactItms1_Qry = "select e from ampata_GenNode e"
                        + " where e.className = 'FinTxactItm'"
                        + " and e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.idDt.date1 between :begDt and :endDt"
                        + "";
                finTxactItmsDl.setQuery(finTxactItms1_Qry);
                finTxactItmsDl.setParameter("finAcct1_Id",finAcct1);
                finTxactItmsDl.setParameter("finDept1_Id",finDept1);
                finTxactItmsDl.setParameter("begDt",begDt);
                finTxactItmsDl.setParameter("endDt",endDt);
            }
            FetchPlan finTxactItmsFp = fetchPlans.builder(GenNode.class)
                    .addFetchPlan("finTxactItm-fetch-plan-base")
                    .build();
            finTxactItmsDl.setFetchPlan(finTxactItmsFp);
            finTxactItmsDl.setContainer(finTxactItmsDc);

            finTxactItmsDl.setDataContext(getScreenData().getDataContext());

            tableFinTxactItm.setItems(new ContainerTableItems<>(finTxactItmsDc));

//            finTxactItmsDl.load();
//            logger.debug(logPrfx + " --- finTxactItmsDc returned rows: " + finTxactItmsDc.getItems().size());
            //pagetableTinTxactItm.setDataBinder(...);



        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
        }

        logger.trace(logPrfx + " <-- ");
    }


}