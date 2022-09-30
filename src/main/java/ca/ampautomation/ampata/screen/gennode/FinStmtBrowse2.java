package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
import ca.ampautomation.ampata.entity.HasTmst;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.component.propertyfilter.SingleFilterSupport;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import io.jmix.ui.screen.LookupComponent;
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
import java.util.stream.Collectors;

@UiController("ampata_FinStmt.browse2")
@UiDescriptor("fin-stmt-browse2.xml")
@LookupComponent("table")
public class FinStmtBrowse2 extends MasterDetailScreen<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_genChan1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finAcct1_Id;


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
    private Table<GenNode> table2;

    @Autowired
    private Form form;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> finStmtsDc;

    @Autowired
    private CollectionLoader<GenNode> finStmtsDl;

    @Autowired
    private InstanceContainer<GenNode> finStmtDc;

    @Autowired
    private CollectionContainer<GenNode> finStmtItmsDc;

    @Autowired
    private CollectionLoader<GenNode> finStmtItmsDl;


    private CollectionContainer<GenNodeType> finStmtTypesDc;

    private CollectionLoader<GenNodeType> finStmtTypesDl;


    private CollectionContainer<GenNode> genChansDc;

    private CollectionLoader<GenNode> genChansDl;


    private CollectionContainer<GenNode> finAcctsDc;

    private CollectionLoader<GenNode> finAcctsDl;


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
    private EntityComboBox<GenNode> genChan1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;



    Logger logger = LoggerFactory.getLogger(FinStmtBrowse2.class);

    @Autowired
    private TextField<String> debtSumCalcField;

    @Autowired
    private TextField<String> debtSumDiffField;

    @Autowired
    private TextField<String> credSumCalcField;

    @Autowired
    private TextField<String> credSumDiffField;

    @Autowired
    private TextField<String> netSumCalcField;

    @Autowired
    private TextField<String> netSumDiffField;

    @Autowired
    private Button finStmtHideBtn;

    @Autowired
    private Button finTxferHideBtn;

    @Autowired
    private ComboBox<String> statusField;

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        finStmtTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finStmtTypesDl = dataComponents.createCollectionLoader();
        finStmtTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtTypesDl.setFetchPlan(finStmtTypesFp);
        finStmtTypesDl.setContainer(finStmtTypesDc);
        finStmtTypesDl.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(finStmtTypesDc);


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
        EntityComboBox<GenNode> propFilterCmpnt_genChan1_Id = (EntityComboBox<GenNode>) propFilter_genChan1_Id.getValueComponent();
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


        finAcctsDc = dataComponents.createCollectionContainer(GenNode.class);
        finAcctsDl = dataComponents.createCollectionLoader();
        finAcctsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctsDl.setFetchPlan(finAcctsFp);
        finAcctsDl.setContainer(finAcctsDc);
        finAcctsDl.setDataContext(getScreenData().getDataContext());
        EntityComboBox<GenNode> propFilterCmpnt_finAcct1_Id = (EntityComboBox<GenNode>) propFilter_finAcct1_Id.getValueComponent();
        propFilterCmpnt_finAcct1_Id.setOptionsContainer(finAcctsDc);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        finStmtsDl.load();
        table.sort("id2", Table.SortDirection.ASCENDING);
        table2.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "finStmtsDc", target = Target.DATA_CONTAINER)
    public void onFinStmtsDcCollectionChange(CollectionContainer.CollectionChangeEvent<GenNode> event) {
        String logPrfx = "onFinStmtsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "finStmtsDc", target = Target.DATA_CONTAINER)
    public void onFinStmtsDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinStmtsDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = event.getItem();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinStmt.setClassName("FinStmt");
        logger.debug(logPrfx + " --- className: FinStmt");

        finStmtItmsDl.setParameter("finStmt1_Id", event.getItem());
        finStmtItmsDl.load();

        logger.trace(logPrfx + " <-- ");
    }
    
    @Install(to = "table.[beg1.date1]", subject = "formatter")
    private String tableBeg1Date1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Install(to = "table.[end1.date1]", subject = "formatter")
    private String tableEnd1Date1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Install(to = "table2.[idTs.ts1]", subject = "formatter")
    private String table2IdTsTs1Formatter(LocalDateTime ts) {
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

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

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

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txfer_Pr_Upd()");
        repo.execFinStmtPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txfer_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finStmtsDl.load()");
        finStmtsDl.load();
        logger.debug(logPrfx + " --- finished finStmtsDl.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");
        
        List<GenNode> thisFinStmts = table.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmts.forEach(orig -> {
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
            finStmtsDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinStmt " + copy.getId2() + " "
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

        List<GenNode> thisFinStmts = table.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<GenNode> sels = new ArrayList<>();

        thisFinStmts.forEach(orig -> {

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

            if (orig.getEndBal() != null) {
                copy.setBegBal(orig.getEndBal());}

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            GenNode savedCopy = dataManager.save(copy);
            finStmtsDc.getMutableItems().add(savedCopy);
            logger.debug("Derived FinStmt " + copy.getId2() + " "
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

        List<GenNode> thisFinStmts = table.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinStmts.forEach(thisFinStmt -> {
            GenNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
            thisFinStmt = thisTrackedFinStmt;
            if (thisFinStmt != null) {

                Boolean thisFinStmtIsChanged = false;
                
                LocalDateTime beg1;
                if (tmplt_beg1Ts1FieldChk.isChecked()) {
                    thisFinStmtIsChanged = true;
                    beg1 = tmplt_beg1Ts1Field.getValue();
                    thisFinStmt.getBeg1().setTs1(beg1);
                }

                LocalDateTime end1;
                if (tmplt_end1Ts1FieldChk.isChecked()) {
                    thisFinStmtIsChanged = true;
                    end1 = tmplt_end1Ts1Field.getValue();
                    thisFinStmt.getEnd1().setTs1(end1);
                    updateIdDt(thisFinStmt);
                }


                if (tmplt_StatusFieldChk.isChecked()
                ) {
                    thisFinStmtIsChanged = true;
                    thisFinStmt.setStatus(tmplt_StatusField.getValue());
                }

                thisFinStmtIsChanged = updateId2Calc(thisFinStmt) || thisFinStmtIsChanged;
                thisFinStmtIsChanged = updateId2(thisFinStmt) || thisFinStmtIsChanged;
                thisFinStmtIsChanged = updateId2Cmp(thisFinStmt) || thisFinStmtIsChanged;

                if (thisFinStmtIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinStmt).");
                    //dataManager.save(thisFinStmt);
                }


            }
        });
        updateFinStmtHelper();
        logger.trace(logPrfx + " <-- ");
    }
    private void updateFinStmtHelper() {
        String logPrfx = "updateFinStmtHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finStmtsDl.load().");
            finStmtsDl.load();

            List<GenNode> thisFinStmts = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinStmts.forEach(thisFinStmt -> {
                //GenNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
                if (thisFinStmt != null) {
                    GenNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
                    thisFinStmt = thisTrackedFinStmt;

                    Boolean thisFinStmtIsChanged = false;

                    thisFinStmtIsChanged = updateId2Dup(thisFinStmt) || thisFinStmtIsChanged;

                    if (thisFinStmtIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinStmt).");
                        //dataManager.save(thisFinStmt);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finStmtsDl.load().");
                finStmtsDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinStmts);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finStmtHideBtn")
    public void onFinStmtHideBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFinStmtHideBtnClick";
        logger.trace(logPrfx + " --> ");
        if (form.isVisible()){
            form.setVisible(false);
            finStmtHideBtn.setCaption("Show Stmt Details");
        }else{
            form.setVisible(true);
            finStmtHideBtn.setCaption("Hide Stmt Details");
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<GenNode> thisFinStmts = table.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmts.forEach(thisFinStmt -> {
            if (thisFinStmt != null) {

                GenNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
                thisFinStmt = thisTrackedFinStmt;

                Boolean isChanged = false;

                isChanged = updateCalcVals(thisFinStmt);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finStmtsDl.load().");
            finStmtsDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            table.setSelected(thisFinStmts);
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

    @Subscribe(id = "finStmtDc", target = Target.DATA_CONTAINER)
    public void onFinStmtDcItemChange(InstanceContainer.ItemChangeEvent<GenNode> event) {
        String logPrfx = "onFinStmtDcItemChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = event.getSource().getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmt.setClassName("FinStmt");
        logger.debug(logPrfx + " --- className: FinStmt");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmt = finStmtDc.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinStmt);
            updateId2Dup(thisFinStmt);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinStmt);
        updateId2Cmp(thisFinStmt);
        updateId2Dup(thisFinStmt);

        logger.debug(logPrfx + " --- id2: " + thisFinStmt.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finTxferDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinStmt);
        updateId2Cmp(thisFinStmt);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinStmt.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListType1_IdFieldBtn")
    public void onUpdateListType1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListType1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        finStmtTypesDl.load();
        logger.debug(logPrfx + " --- called finStmtTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListGenChan1_IdFieldBtn")
    public void onUpdateListGenChan1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListGenChan1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finAcct1_IdField")
    public void onFinAcct1_IdFieldValueChange(HasValue.ValueChangeEvent<GenNode> event) {
        String logPrfx = "onFinAcct1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinAcct1_Type1_Id2(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateListGenDocVer1_IdFieldBtn")
    public void onUpdateListGenDocVer1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListGenDocVer1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateListGenTag1_IdFieldBtn")
    public void onUpdateListGenTag1_IdFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateListGenTag1_IdFieldBtn";
        logger.trace(logPrfx + " --> ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("end1Date1Field")
    public void onEnd1Date1FieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onEnd1Date1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmt = finStmtDc.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateIdDt(thisFinStmt)");
            updateIdDt(thisFinStmt);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinStmt)");
            updateId2Calc(thisFinStmt);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("begBalField")
    public void onBegBalFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "begBalField";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateEndBal(thisFinStmt);

        logger.trace(logPrfx + " <-- ");

    }

    @Install(to = "statusField", subject = "enterPressHandler")
    private void statusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "statusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("credSumField")
    public void onCredSumFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onCredSumFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateSumNet(thisFinStmt);
        updateEndBal(thisFinStmt);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("debtSumField")
    public void onDebtSumFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onDebtSumFieldValueChange";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateSumNet(thisFinStmt);
        updateEndBal(thisFinStmt);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    public Boolean updateCalcVals(GenNode thisFinStmt){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        isChanged = updateFinStmtCalcVals(thisFinStmt) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinStmtCalcVals(@NotNull GenNode thisFinStmt) {
        String logPrfx = "updateFinStmtCalcVals";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        // Stored in FinStmt Object
        isChanged = updateIdDt(thisFinStmt) || isChanged;
        isChanged = updateId2Calc(thisFinStmt) || isChanged;
        isChanged = updateId2Cmp(thisFinStmt) || isChanged;
        isChanged = updateId2Dup(thisFinStmt) || isChanged;
        isChanged = updateDesc1(thisFinStmt) || isChanged;

        isChanged = updateFinAcct1_Type1_Id2(thisFinStmt) || isChanged;
        isChanged = updateSumNet(thisFinStmt) || isChanged;
        isChanged = updateEndBal(thisFinStmt) || isChanged;
        isChanged = updateFieldSet1(thisFinStmt) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull GenNode thisFinStmt) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        isChanged = updateEnd1(thisFinStmt) || isChanged;
        isChanged = updateIdDt(thisFinStmt)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        String id2_ = thisFinStmt.getId2();
        String id2 = thisFinStmt.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinStmt.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(GenNode thisFinStmt){
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        String id2Calc_ = thisFinStmt.getId2Calc();
        String id2Calc = thisFinStmt.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinStmt.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Boolean id2Cmp_ = thisFinStmt.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinStmt.getId2(),thisFinStmt.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinStmt.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    private Boolean updateId2Dup(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Integer id2Dup_ = thisFinStmt.getId2Dup();
        if (thisFinStmt.getId2() != null){
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinStmt' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try{
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",thisFinStmt.getId())
                        .parameter("id2",thisFinStmt.getId2())
                        .one();
            }catch (IllegalStateException e){
                id2Dup =0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinStmt.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinStmt.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull GenNode thisFinStmt){
        // Assume thisFinStmt is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        String desc1_ = thisFinStmt.getDesc1();
        String  thisStatus = Objects.toString(thisFinStmt.getStatus(),"");
        if (!thisStatus.equals("")){
            thisStatus = "" + thisStatus;
        }
        logger.debug(logPrfx + " --- thisStatus: " + thisStatus);

        String thisBegDate = "";
        if (thisFinStmt.getBeg1() != null) {
            thisBegDate = Objects.toString(thisFinStmt.getBeg1().getDate1().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!thisBegDate.equals("")) {
            thisBegDate = "BegDate " + thisBegDate ;}
        logger.debug(logPrfx + " --- thisBegDate: " + thisBegDate);

        String  thisBegBal = Objects.toString(thisFinStmt.getBegBal(),"");
        if (!thisBegBal.equals("")){
            thisBegBal = "BegBal " + thisBegBal;
        }
        logger.debug(logPrfx + " --- thisBegBal: " + thisBegBal);

        String thisEndDate = "";
        if (thisFinStmt.getEnd1() != null) {
            thisEndDate = Objects.toString(thisFinStmt.getEnd1().getDate1().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!thisEndDate.equals("")) {
            thisEndDate = "EndDate " + thisEndDate ;}
        logger.debug(logPrfx + " --- thisEndDate: " + thisEndDate);

        String  thisEndBal = Objects.toString(thisFinStmt.getEndBal(),"");
        if (!thisEndBal.equals("")){
            thisEndBal = "EndBal " + thisEndBal;
        }
        logger.debug(logPrfx + " --- thisEndBal: " + thisEndBal);

        String thisAcct = "";
        if (thisFinStmt.getFinAcct1_Id() != null) {
            thisAcct = Objects.toString(thisFinStmt.getFinAcct1_Id().getId2(),"");}
        if (!thisAcct.equals("")) {
            thisAcct = "on acct [" + thisAcct + "]";}
        logger.debug(logPrfx + " --- thisAcct: " + thisAcct);


        String thisTag = "";
        String thisTag1 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            thisTag1 = Objects.toString(thisFinStmt.getGenTag1_Id().getId2());}
        String thisTag2 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            thisTag2 = Objects.toString(thisFinStmt.getGenTag2_Id().getId2());}
        String thisTag3 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            thisTag3 = Objects.toString(thisFinStmt.getGenTag3_Id().getId2());}
        String thisTag4 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            thisTag4 = Objects.toString(thisFinStmt.getGenTag4_Id().getId2());}
        if (!(thisTag1 + thisTag2 + thisTag3 + thisTag4).equals("")) {
            thisTag = "tag [" + String.join(",",thisTag1, thisTag2, thisTag3, thisTag4) + "]";}
        logger.debug(logPrfx + " --- thisTag: " + thisTag);

        List<String> list = Arrays.asList(
                thisStatus
                ,thisAcct
                ,thisEndDate
                ,thisEndBal
                ,thisTag);
        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            thisFinStmt.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmt.updateIdDt();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateEndBal(@NotNull GenNode thisFinStmt){
        String logPrfx = "updateEndBal";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal endBal_ = thisFinStmt.getEndBal();
        BigDecimal endBal = BigDecimal.valueOf(0);
        if (thisFinStmt.getBegBal() == null || thisFinStmt.getNetSum() == null){
            thisFinStmt.setEndBal(null);
            logger.debug(logPrfx + " --- endBal: null");
            return isChanged;
        }
        endBal = thisFinStmt.getBegBal().add(thisFinStmt.getNetSum());

        if (!Objects.equals(endBal_, endBal)){
            thisFinStmt.setEndBal(endBal);
            logger.debug(logPrfx + " --- endBal: " + endBal);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateSumNet(@NotNull GenNode thisFinStmt){
        String logPrfx = "updateSumNet";
        logger.trace(logPrfx + " --> ");


        Boolean isChanged = false;
        BigDecimal netSum_ = thisFinStmt.getNetSum();
        BigDecimal netSum = BigDecimal.valueOf(0);
        if (thisFinStmt.getDebtSum() == null || thisFinStmt.getCredSum() == null){
            thisFinStmt.setNetSum(null);
            logger.debug(logPrfx + " --- netSum: null");
            return isChanged;
        }

        if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
            netSum = thisFinStmt.getDebtSum().subtract(thisFinStmt.getCredSum());

        }else{
            netSum = thisFinStmt.getCredSum().subtract(thisFinStmt.getDebtSum());
        }

        if (!Objects.equals(netSum_, netSum)){
            thisFinStmt.setNetSum(netSum);
            logger.debug(logPrfx + " --- netSum: " + netSum);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFinAcct1_Type1_Id2(@NotNull GenNode thisFinStmt){
        String logPrfx = "updateFinAcct1_Type1_Id2";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        String finAcct1_Type1_Id2_ = thisFinStmt.getFinAcct1_Type1_Id2();
        String finAcct1_Type1_Id2 = null;
        if (thisFinStmt.getFinAcct1_Id() != null
                && thisFinStmt.getFinAcct1_Id().getType1_Id() != null){
            finAcct1_Type1_Id2 = thisFinStmt.getFinAcct1_Id().getType1_Id().getId2();
        }

        if (!Objects.equals(finAcct1_Type1_Id2_, finAcct1_Type1_Id2)){
            thisFinStmt.setFinAcct1_Type1_Id2(finAcct1_Type1_Id2);
            logger.debug(logPrfx + " --- finAcct1_Type1_Id2: " + finAcct1_Type1_Id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateFieldSet1(@NotNull GenNode thisFinStmt) {
        String logPrfx = "updateFieldSet1";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal debtSumCalc_ = ((debtSumCalcField.getValue() == null
                || debtSumCalcField.getValue().isEmpty())
                ? new BigDecimal(0)
                : new BigDecimal(debtSumCalcField.getValue()));
        BigDecimal debtSumDiff_ = ((debtSumDiffField.getValue() == null
                || debtSumDiffField.getValue().isEmpty())
                ? new BigDecimal(0)
                : new BigDecimal(debtSumDiffField.getValue()));

        BigDecimal debtSumCalc = null;
        BigDecimal debtSumDiff = null;
        String qry1 = "select sum(e.amtDebt) from ampata_GenNode e where e.className = 'FinStmtItm' and e.finStmt1_Id = :finStmt1_Id group by e.finStmt1_Id";
        try{
            debtSumCalc = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id",thisFinStmt)
                    .one();
            if (debtSumCalc == null) {
                debtSumCalc = BigDecimal.valueOf(0);}
            
            
            debtSumCalcField.setValue(debtSumCalc.toString());
            logger.debug(logPrfx + " --- debtSumCalc: " + debtSumCalc);

            if (thisFinStmt.getDebtSum() == null){
                debtSumDiffField.setValue(null);
            }else{
                debtSumDiff = thisFinStmt.getDebtSum().subtract(debtSumCalc);
                debtSumDiffField.setValue(debtSumDiff.toString());
            }
            logger.debug(logPrfx + " --- debtSumDiff: " + debtSumDiff);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            debtSumCalcField.setValue(null);
            logger.debug(logPrfx + " --- debtSumCalc: null");
            debtSumDiffField.setValue(null);
            logger.debug(logPrfx + " --- debtSumDiff: null");
        }


        BigDecimal credSumCalc_ = ((credSumCalcField.getValue() == null
                || credSumCalcField.getValue().isEmpty())
                ? new BigDecimal(0)
                : new BigDecimal(credSumCalcField.getValue()));
        BigDecimal credSumDiff_ = ((credSumDiffField.getValue() == null
                || credSumDiffField.getValue().isEmpty())
                ? new BigDecimal(0)
                : new BigDecimal(credSumDiffField.getValue()));
        BigDecimal credSumCalc = null;
        BigDecimal credSumDiff = null;
        String qry2 = "select sum(e.amtCred) from ampata_GenNode e where e.className = 'FinStmtItm' and e.finStmt1_Id = :finStmt1_Id group by e.finStmt1_Id";
        try {
            credSumCalc = dataManager.loadValue(qry2, BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id", thisFinStmt)
                    .one();
            if (credSumCalc == null) {
                credSumCalc = BigDecimal.valueOf(0);}
            credSumCalcField.setValue(credSumCalc.toString());
            logger.debug(logPrfx + " --- credSumCalc: " + credSumCalc);

            if (thisFinStmt.getDebtSum() == null){
                credSumDiffField.setValue(null);
            }else{
                credSumDiff = thisFinStmt.getCredSum().subtract(credSumCalc);
                credSumDiffField.setValue(credSumDiff.toString());
            }
            logger.debug(logPrfx + " --- credSumDiff: " + credSumDiff);

        } catch (IllegalStateException e ){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            credSumCalcField.setValue(null);
            logger.debug(logPrfx + " --- credSumCalc: null");
            credSumDiffField.setValue(null);
            logger.debug(logPrfx + " --- credSumDiff: null");
        }


        BigDecimal netSumCalc_ = ((netSumCalcField.getValue() == null
                || netSumCalcField.getValue().isEmpty())
                ? new BigDecimal(0)
                : new BigDecimal(netSumCalcField.getValue()));
        BigDecimal netSumDiff_ = ((netSumDiffField.getValue() == null
                || netSumDiffField.getValue().isEmpty())
                ? new BigDecimal(0)
                : new BigDecimal(netSumDiffField.getValue()));
        BigDecimal netSumCalc = null;
        BigDecimal netSumDiff = null;
        if (debtSumCalc == null || credSumCalc == null){
            netSumCalcField.setValue(null);
            logger.debug(logPrfx + " --- netSumCalc: null");
            netSumDiffField.setValue(null);
            logger.debug(logPrfx + " --- netSumDiff: null");
        }else{

            if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                    && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                netSumCalc = debtSumCalc.subtract(credSumCalc);
            }else{
                netSumCalc = credSumCalc.subtract(debtSumCalc);
            }
            netSumCalcField.setValue(netSumCalc.toString());
            logger.debug(logPrfx + " --- netSumCalc: " + netSumCalc);

            netSumDiff = thisFinStmt.getNetSum().subtract(netSumCalc);
            netSumDiffField.setValue(netSumDiff.toString());
            logger.debug(logPrfx + " --- netSumDiff: " + netSumDiff);

        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateEnd1(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateEnd1";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmt.updateEnd1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private GenNode findFinStmtById2(@NotNull String FinStmt_Id2) {
        String logPrfx = "findFinStmtById2";
        logger.trace(logPrfx + " --> ");

        if (FinStmt_Id2 == null) {
            logger.debug(logPrfx + " --- FinStmt_Id2 is null.");
            notifications.create().withCaption("FinStmt_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinStmt' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + FinStmt_Id2);

        GenNode FinStmt1_Id = null;
        try {
            FinStmt1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", FinStmt_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return FinStmt1_Id;

    }


    private void reloadStatusList(){
        String logPrfx = "reloadStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmt'"
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