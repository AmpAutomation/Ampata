package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinStmtQryMngr;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenChan;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenDocVer;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@UiController("enty_UsrFinStmt.main")
@UiDescriptor("usr-fin-stmt-main.xml")
@LookupComponent("tableMain")
public class UsrFinStmtMain extends MasterDetailScreen<UsrNode> {

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



    //Query Manager
    @Autowired
    private UsrFinStmtQryMngr qryMngr;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<UsrGenChan> filterConfig1A_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_FinAcct1_Id;

    //Toolbar

    //Template
    @Autowired
    protected CheckBox tmplt_Beg1Ts1FieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Beg1Ts1Field;


    @Autowired
    protected CheckBox tmplt_End1Ts1FieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_End1Ts1Field;

    @Autowired
    protected ComboBox<String> tmplt_StatusField;

    @Autowired
    protected CheckBox tmplt_StatusFieldChk;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNode> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNode> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrNode> instCntnrMain;
    @Autowired
    private Table<UsrNode> tableMain;
    @Autowired
    private Form form;

    
    //Type data container and loader
    private CollectionContainer<UsrNodeType> colCntnrType;
    private CollectionLoader<UsrNodeType> colLoadrType;


    //Other data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNode> colCntnrFinStmtItm;
    @Autowired
    private CollectionLoader<UsrNode> colLoadrFinStmtItm;
    @Autowired
    private Table<UsrNode> tableFinStmtItm;


    private CollectionContainer<UsrGenChan> colCntnrGenChan;
    private CollectionLoader<UsrGenChan> colLoadrGenChan;

    private CollectionContainer<UsrNode> colCntnrFinAcct;
    private CollectionLoader<UsrNode> colLoadrFinAcct;

    private CollectionContainer<UsrGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrGenDocVer> colLoadrGenDocVer;

    private CollectionContainer<UsrGenTag> colCntnrGenTag;
    private CollectionLoader<UsrGenTag> colLoadrGenTag;

    
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
    private EntityComboBox<UsrGenChan> genChan1_IdField;

    @Autowired
    private EntityComboBox<UsrNode> finAcct1_IdField;

    @Autowired
    private EntityComboBox<UsrGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrGenTag> genTag1_IdField;


    @Autowired
    private Button finStmtHideBtn;

    @Autowired
    private Button finTxactItmHideBtn;

    @Autowired
    private ComboBox<String> statusField;


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrFinStmtType e order by e.sortKey, e.id2");
        FetchPlan finStmtTypesFp = fetchPlans.builder(UsrNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(finStmtTypesFp);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(colCntnrType);


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrGenChan e order by e.sortKey, e.id2");
        FetchPlan genChansFp = fetchPlans.builder(UsrGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(genChansFp);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(colCntnrGenChan);
        EntityComboBox<UsrGenChan> propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrGenChan>) filterConfig1A_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);


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


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNode.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrFinAcct e order by e.sortKey, e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(finAcctsFp);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(colCntnrFinAcct);
        //filter
        EntityComboBox<UsrNode> propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNode>) filterConfig1A_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setOptionsContainer(colCntnrFinAcct);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);
        tableFinStmtItm.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinStmtsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNode> event) {
        String logPrfx = "onFinStmtsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinStmtsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinStmtsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = event.getItem();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            //todo I observed thisFinStmt is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinStmt.setClassName("UsrFinStmt");
        logger.debug(logPrfx + " --- className: UsrFinStmt");

        colLoadrFinStmtItm.setParameter("finStmt1_Id", event.getItem());
        colLoadrFinStmtItm.load();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tableMain.[ts1.ts1]", subject = "formatter")
    private String tableTs1Ts1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "tableMain.[ts2.ts1]", subject = "formatter")
    private String tableTs2Ts1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "tableFinStmtItm.[instDt1.date1]", subject = "formatter")
    private String table2InstDt1Date1Formatter(LocalDate dt) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return dt == null ? null : dt.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

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
        
        List<UsrNode> thisFinStmts = tableMain.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmts.forEach(orig -> {
            UsrNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

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

        List<UsrNode> thisFinStmts = tableMain.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNode> sels = new ArrayList<>();

        thisFinStmts.forEach(orig -> {

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
            logger.debug("Derived FinStmt " + copy.getId2() + " "
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

        List<UsrNode> thisFinStmts = tableMain.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinStmts.forEach(thisFinStmt -> {
            UsrNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
            thisFinStmt = thisTrackedFinStmt;
            if (thisFinStmt != null) {

                Boolean thisFinStmtIsChanged = false;
                
                LocalDateTime beg1;
                if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                    thisFinStmtIsChanged = true;
                    beg1 = tmplt_Beg1Ts1Field.getValue();
                    thisFinStmt.getTs1().setElTs(beg1);
                }

                LocalDateTime end1;
                if (tmplt_End1Ts1FieldChk.isChecked()) {
                    thisFinStmtIsChanged = true;
                    end1 = tmplt_End1Ts1Field.getValue();
                    thisFinStmt.getTs3().setElTs(end1);
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

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNode> thisFinStmts = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinStmts.forEach(thisFinStmt -> {
                //UsrNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
                if (thisFinStmt != null) {
                    UsrNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
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

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisFinStmts);
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

        List<UsrNode> thisFinStmts = tableMain.getSelected().stream().toList();
        if (thisFinStmts == null || thisFinStmts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmts.forEach(thisFinStmt -> {
            if (thisFinStmt != null) {

                UsrNode thisTrackedFinStmt = dataContext.merge(thisFinStmt);
                thisFinStmt = thisTrackedFinStmt;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinStmt);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinStmts);
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
    public void onFinStmtDcItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onFinStmtDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = event.getSource().getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            //todo I observed thisFinStmt is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinStmt.setClassName("UsrFinStmt");
        logger.debug(logPrfx + " --- className: UsrFinStmt");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
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
            UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
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

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finTxactItmDc is null, likely because no record is selected.");
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
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
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
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenChan1_IdFieldListBtn")
    public void onUpdateGenChan1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("setNullAmtBegBalBtn")
    public void onSetNullAmtBegBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtBegBal_ = thisFinStmt.getAmtBegBal();
        BigDecimal amtBegBal = null;
        if(!Objects.equals(amtBegBal_, amtBegBal)){
            thisFinStmt.setAmtBegBal(amtBegBal);
            logger.debug(logPrfx + " --- amtBegBal: " + amtBegBal);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtBegBalBtn")
    public void onUpdateAmtBegBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtBegBal_ = thisFinStmt.getAmtBegBal();
        BigDecimal amtBegBal = thisFinStmt.getAmtBegBalCalc();
        if(!Objects.equals(amtBegBal_, amtBegBal)){
            thisFinStmt.setAmtBegBal(amtBegBal);
            logger.debug(logPrfx + " --- amtBegBal: " + amtBegBal);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtEndBalBtn")
    public void onSetNullAmtEndBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtEndBal_ = thisFinStmt.getAmtEndBal();
        BigDecimal amtEndBal = null;
        if(!Objects.equals(amtEndBal_, amtEndBal)){
            thisFinStmt.setAmtEndBal(amtEndBal);
            logger.debug(logPrfx + " --- amtEndBal: " + amtEndBal);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtEndBalBtn")
    public void onUpdateAmtEndBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtEndBal_ = thisFinStmt.getAmtEndBal();
        BigDecimal amtEndBal = thisFinStmt.getAmtEndBalCalc();
        if(!Objects.equals(amtEndBal_, amtEndBal)){
            thisFinStmt.setAmtEndBal(amtEndBal);
            logger.debug(logPrfx + " --- amtEndBal: " + amtEndBal);
        }

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
            UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
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


    @Subscribe("amtBegBalField")
    public void onAmtBegBalFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "amtBegBalField";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
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
            UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtEndBalCalc(thisFinStmt);
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
            UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtNet(thisFinStmt);
            updateAmtEndBalCalc(thisFinStmt);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("amtCredField")
    public void onAmtCredFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onAmtCredFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateAmtNet(thisFinStmt);
            updateAmtEndBalCalc(thisFinStmt);
        }

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNode thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    public Boolean updateCalcVals(UsrNode thisFinStmt){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinStmtCalcVals(thisFinStmt) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinStmtCalcVals(@NotNull UsrNode thisFinStmt) {
        String logPrfx = "updateFinStmtCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinStmt Object
        isChanged = updateIdDt(thisFinStmt) || isChanged;
        isChanged = updateId2Calc(thisFinStmt) || isChanged;
        isChanged = updateId2Cmp(thisFinStmt) || isChanged;
        isChanged = updateId2Dup(thisFinStmt) || isChanged;
        isChanged = updateDesc1(thisFinStmt) || isChanged;

        isChanged = updateAmtNet(thisFinStmt) || isChanged;
        isChanged = updateAmtBegBalCalc(thisFinStmt) || isChanged;
        isChanged = updateAmtEndBalCalc(thisFinStmt) || isChanged;
        isChanged = updateFinStmtItms1_FieldSet(thisFinStmt) || isChanged;
        isChanged = updateFinTxactItms1_FieldSet(thisFinStmt) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull UsrNode thisFinStmt) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateEnd1(thisFinStmt) || isChanged;
        isChanged = updateIdDt(thisFinStmt)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull UsrNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
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

    private Boolean updateId2Calc(UsrNode thisFinStmt){
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
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

    private Boolean updateId2Cmp(@NotNull UsrNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
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
    
    private Boolean updateId2Dup(@NotNull UsrNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinStmt.getId2Dup();
        if (thisFinStmt.getId2() != null){
            String id2Qry = "select count(e) from enty_UsrFinStmt e where e.id2 = :id2 and e.id <> :id";
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

    private Boolean updateDesc1(@NotNull UsrNode thisFinStmt){
        // Assume thisFinStmt is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String desc1_ = thisFinStmt.getDesc1();
        String  status = Objects.toString(thisFinStmt.getStatus(),"");
        if (!status.equals("")){
            status = "" + status;
        }
        logger.debug(logPrfx + " --- status: " + status);

        String begDate = "";
        if (thisFinStmt.getTs1() != null) {
            begDate = Objects.toString(thisFinStmt.getTs1().getElDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!begDate.equals("")) {
            begDate = "begDate " + begDate ;}
        logger.debug(logPrfx + " --- begDate: " + begDate);

        String  begBal = Objects.toString(thisFinStmt.getAmtBegBal(),"");
        if (!begBal.equals("")){
            begBal = "begBal " + begBal;
        }
        logger.debug(logPrfx + " --- begBal: " + begBal);

        String endDate = "";
        if (thisFinStmt.getTs3() != null) {
            endDate = Objects.toString(thisFinStmt.getTs3().getElDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"");}
        if (!endDate.equals("")) {
            endDate = "endDate " + endDate ;}
        logger.debug(logPrfx + " --- endDate: " + endDate);

        String  endBalCalc = Objects.toString(thisFinStmt.getAmtEndBalCalc(),"");
        if (!endBalCalc.equals("")){
            endBalCalc = "endBalCalc " + endBalCalc;
        }
        logger.debug(logPrfx + " --- endBalCalc: " + endBalCalc);

        String acct = "";
        if (thisFinStmt.getFinAcct1_Id() != null) {
            acct = Objects.toString(thisFinStmt.getFinAcct1_Id().getId2(),"");}
        if (!acct.equals("")) {
            acct = "on acct [" + acct + "]";}
        logger.debug(logPrfx + " --- acct: " + acct);


        String tag = "";
        String tag1 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            tag1 = Objects.toString(thisFinStmt.getGenTag1_Id().getId2());}
        String tag2 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            tag2 = Objects.toString(thisFinStmt.getGenTag2_Id().getId2());}
        String tag3 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            tag3 = Objects.toString(thisFinStmt.getGenTag3_Id().getId2());}
        String tag4 = "";
        if (thisFinStmt.getGenTag1_Id() != null) {
            tag4 = Objects.toString(thisFinStmt.getGenTag4_Id().getId2());}
        if (!(tag1 + tag2 + tag3 + tag4).equals("")) {
            tag = "tag [" + String.join(",",tag1, tag2, tag3, tag4) + "]";}
        logger.debug(logPrfx + " --- tag: " + tag);

        List<String> list = Arrays.asList(
                status
                ,acct
                ,endDate
                ,endBalCalc
                ,tag);
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


    private Boolean updateIdDt(@NotNull UsrNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinStmt.updateNm1s1Inst1Dt1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateAmtNet(@NotNull UsrNode thisFinStmt){
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;
        BigDecimal amtNet_ = thisFinStmt.getAmtNet();
        BigDecimal amtNet = null;
        if (thisFinStmt.getAmtDebt() != null && thisFinStmt.getAmtCred() != null){
            if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                    && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                amtNet = thisFinStmt.getAmtDebt().subtract(thisFinStmt.getAmtCred());

            }else{
                amtNet = thisFinStmt.getAmtCred().subtract(thisFinStmt.getAmtDebt());
            }
        }

        if (!Objects.equals(amtNet_, amtNet)){
            thisFinStmt.setAmtNet(amtNet);
            logger.debug(logPrfx + " --- amtNet: " + amtNet);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtBegBalCalc(@NotNull UsrNode thisFinStmt){
        String logPrfx = "updateAmtBegBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtBegBalCalc_ = thisFinStmt.getAmtEndBalCalc();
        BigDecimal amtBegBalCalc = null;

        final String SEP = "/";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd")
                .toFormatter();

        if (thisFinStmt.getTs1() == null
                || thisFinStmt.getTs1().getElDt() == null
                || thisFinStmt.getFinAcct1_Id() == null
                || thisFinStmt.getFinAcct1_Id().getId2() == null
                ) {
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        //todo replace with SQL window query
        //Account
        sb.append(thisFinStmt.getFinAcct1_Id().getId2())
            //Date
            .append(SEP + "D").append(thisFinStmt.getTs1().getElDt().minusDays(1).format(frmtDt));

        UsrNode prevFinStmt = findFinStmtById2(sb.toString());
        if (prevFinStmt == null  ){
            amtBegBalCalc = BigDecimal.valueOf(0);
        } else {
            amtBegBalCalc = prevFinStmt.getAmtEndBalCalc();
        }

        if (!Objects.equals(amtBegBalCalc_, amtBegBalCalc)){
            thisFinStmt.setAmtBegBalCalc(amtBegBalCalc);
            logger.debug(logPrfx + " --- amtBegBalCalc: " + amtBegBalCalc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


        public Boolean updateAmtEndBalCalc(@NotNull UsrNode thisFinStmt){
        String logPrfx = "updateAmtEndBalCalc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amtEndBalCalc_ = thisFinStmt.getAmtEndBalCalc();
        BigDecimal amtEndBalCalc = null;
            if (thisFinStmt.getAmtNet() != null){
                if (thisFinStmt.getAmtBegBalCalc() != null ){
                    amtEndBalCalc = thisFinStmt.getAmtBegBalCalc().add(thisFinStmt.getAmtNet());
                }else {
                    if (thisFinStmt.getAmtBegBal() != null) {
                        amtEndBalCalc = thisFinStmt.getAmtBegBal().add(thisFinStmt.getAmtNet());
                    }
                }
            }

            if (!Objects.equals(amtEndBalCalc_, amtEndBalCalc)){
            thisFinStmt.setAmtEndBalCalc(amtEndBalCalc);
            logger.debug(logPrfx + " --- amtEndBalCalc: " + amtEndBalCalc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinStmtItms1_FieldSet(@NotNull UsrNode thisFinStmt) {
        String logPrfx = "updateFinStmtItms1_FieldSet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        BigDecimal finStmtItms1_AmtDebtSumCalc_ = thisFinStmt.getFinStmtItms1_AmtDebtSumCalc();
        BigDecimal finStmtItms1_AmtDebtSumDiff_ = thisFinStmt.getFinStmtItms1_AmtDebtSumDiff();

        BigDecimal finStmtItms1_AmtDebtSumCalc = null;
        BigDecimal finStmtItms1_AmtDebtSumDiff = null;
        String finStmtItms1_QryDebt = "select sum(e.amtDebt) from enty_UsrFinStmtItm e where e.finStmt1_Id = :finStmt1_Id";
        try{
            finStmtItms1_AmtDebtSumCalc = dataManager.loadValue(finStmtItms1_QryDebt,BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id",thisFinStmt)
                    .one();
            if (finStmtItms1_AmtDebtSumCalc == null) {
                finStmtItms1_AmtDebtSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumCalc: " + finStmtItms1_AmtDebtSumCalc);

            if (thisFinStmt.getAmtDebt() == null){
                finStmtItms1_AmtDebtSumDiff = BigDecimal.valueOf(0);
            }else{
                finStmtItms1_AmtDebtSumDiff = thisFinStmt.getAmtDebt().subtract(finStmtItms1_AmtDebtSumCalc);
            }
            logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumDiff: " + finStmtItms1_AmtDebtSumDiff);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumCalc: null");
            logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumDiff: null");
        }

        if (!Objects.equals(finStmtItms1_AmtDebtSumCalc_, finStmtItms1_AmtDebtSumCalc)){
            thisFinStmt.setFinStmtItms1_AmtDebtSumCalc(finStmtItms1_AmtDebtSumCalc);
            logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumCalc: " + finStmtItms1_AmtDebtSumCalc);
            isChanged = true;
        }

        if (!Objects.equals(finStmtItms1_AmtDebtSumDiff_, finStmtItms1_AmtDebtSumDiff)){
            thisFinStmt.setFinStmtItms1_AmtDebtSumDiff(finStmtItms1_AmtDebtSumDiff);
            logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumDiff: " + finStmtItms1_AmtDebtSumDiff);
            isChanged = true;
        }


        BigDecimal finStmtItms1_AmtCredSumCalc_ = thisFinStmt.getFinStmtItms1_AmtCredSumCalc();
        BigDecimal finStmtItms1_AmtCredSumDiff_ = thisFinStmt.getFinStmtItms1_AmtCredSumDiff();

        BigDecimal finStmtItms1_AmtCredSumCalc = null;
        BigDecimal finStmtItms1_AmtCredSumDiff = null;

        String finStmtItms1_QryCred = "select sum(e.amtCred) from enty_UsrFinStmtItm e where e.finStmt1_Id = :finStmt1_Id";
        try {
            finStmtItms1_AmtCredSumCalc = dataManager.loadValue(finStmtItms1_QryCred, BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id", thisFinStmt)
                    .one();
            if (finStmtItms1_AmtCredSumCalc == null) {
                finStmtItms1_AmtCredSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumCalc: " + finStmtItms1_AmtCredSumCalc);

            if (thisFinStmt.getAmtDebt() == null){
                finStmtItms1_AmtCredSumDiff = BigDecimal.valueOf(0);
            }else{
                finStmtItms1_AmtCredSumDiff = thisFinStmt.getAmtCred().subtract(finStmtItms1_AmtCredSumCalc);
            }
            logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumDiff: " + finStmtItms1_AmtCredSumDiff);

        } catch (IllegalStateException e ){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumCalc: null");
            logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumDiff: null");
        }


        if (!Objects.equals(finStmtItms1_AmtCredSumCalc_, finStmtItms1_AmtCredSumCalc)){
            thisFinStmt.setFinStmtItms1_AmtCredSumCalc(finStmtItms1_AmtCredSumCalc);
            logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumCalc: " + finStmtItms1_AmtCredSumCalc);
            isChanged = true;
        }

        if (!Objects.equals(finStmtItms1_AmtCredSumDiff_, finStmtItms1_AmtCredSumDiff)){
            thisFinStmt.setFinStmtItms1_AmtCredSumDiff(finStmtItms1_AmtCredSumDiff);
            logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumDiff: " + finStmtItms1_AmtCredSumDiff);
            isChanged = true;
        }

        BigDecimal finStmtItms1_AmtNetSumCalc_ = thisFinStmt.getFinStmtItms1_AmtNetSumCalc();
        BigDecimal finStmtItms1_AmtNetSumDiff_ = thisFinStmt.getFinStmtItms1_AmtNetSumDiff();

        BigDecimal finStmtItms1_AmtNetSumCalc = null;
        BigDecimal finStmtItms1_AmtNetSumDiff = null;

        if (finStmtItms1_AmtDebtSumCalc == null || finStmtItms1_AmtCredSumCalc == null){
            logger.debug(logPrfx + " --- finStmtItms1_AmtDebtSumCalc: null");
            logger.debug(logPrfx + " --- finStmtItms1_AmtCredSumCalc: null");
        }else{

            if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                    && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                finStmtItms1_AmtNetSumCalc = finStmtItms1_AmtDebtSumCalc.subtract(finStmtItms1_AmtCredSumCalc);
            }else{
                finStmtItms1_AmtNetSumCalc = finStmtItms1_AmtCredSumCalc.subtract(finStmtItms1_AmtDebtSumCalc);
            }

            if (!Objects.equals(finStmtItms1_AmtNetSumCalc_, finStmtItms1_AmtNetSumCalc)){
                thisFinStmt.setFinStmtItms1_AmtNetSumCalc(finStmtItms1_AmtNetSumCalc);
                logger.debug(logPrfx + " --- finStmtItms1_AmtNetSumCalc: " + finStmtItms1_AmtNetSumCalc);
                isChanged = true;
            }

            finStmtItms1_AmtNetSumDiff = finStmtItms1_AmtNetSumCalc.subtract(thisFinStmt.getAmtNet());

            if (!Objects.equals(finStmtItms1_AmtNetSumDiff_, finStmtItms1_AmtNetSumDiff)){
                thisFinStmt.setFinStmtItms1_AmtNetSumDiff(finStmtItms1_AmtNetSumDiff);
                logger.debug(logPrfx + " --- finStmtItms1_AmtNetSumDiff: " + finStmtItms1_AmtNetSumDiff);
                isChanged = true;
            }

        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateFinTxactItms1_FieldSet(@NotNull UsrNode thisFinStmt) {
        String logPrfx = "updateFinTxactItms1_FieldSet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        BigDecimal finTxactItms1_AmtDebtSumCalc_ = thisFinStmt.getFinTxactItms1_AmtDebtSumCalc();
        BigDecimal finTxactItms1_AmtDebtSumDiff_ = thisFinStmt.getFinTxactItms1_AmtDebtSumDiff();

        BigDecimal finTxactItms1_AmtDebtSumCalc = null;
        BigDecimal finTxactItms1_AmtDebtSumDiff = null;
        String finTxactItms1_QryDebt = "select sum(e.amtDebt) from enty_UsrFinTxactItm e join e.finStmtItm1_Id e2 where e2.finStmt1_Id = :finStmt1_Id";
        try{
            finTxactItms1_AmtDebtSumCalc = dataManager.loadValue(finTxactItms1_QryDebt,BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id",thisFinStmt)
                    .one();
            if (finTxactItms1_AmtDebtSumCalc == null) {
                finTxactItms1_AmtDebtSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: " + finTxactItms1_AmtDebtSumCalc);

            if (thisFinStmt.getAmtDebt() == null){
                finTxactItms1_AmtDebtSumDiff = BigDecimal.valueOf(0);
            }else{
                finTxactItms1_AmtDebtSumDiff = thisFinStmt.getAmtDebt().subtract(finTxactItms1_AmtDebtSumCalc);
            }
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: " + finTxactItms1_AmtDebtSumDiff);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: null");
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: null");
        }

        if (!Objects.equals(finTxactItms1_AmtDebtSumCalc_, finTxactItms1_AmtDebtSumCalc)){
            thisFinStmt.setFinTxactItms1_AmtDebtSumCalc(finTxactItms1_AmtDebtSumCalc);
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: " + finTxactItms1_AmtDebtSumCalc);
            isChanged = true;
        }

        if (!Objects.equals(finTxactItms1_AmtDebtSumDiff_, finTxactItms1_AmtDebtSumDiff)){
            thisFinStmt.setFinTxactItms1_AmtDebtSumDiff(finTxactItms1_AmtDebtSumDiff);
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumDiff: " + finTxactItms1_AmtDebtSumDiff);
            isChanged = true;
        }


        BigDecimal finTxactItms1_AmtCredSumCalc_ = thisFinStmt.getFinTxactItms1_AmtCredSumCalc();
        BigDecimal finTxactItms1_AmtCredSumDiff_ = thisFinStmt.getFinTxactItms1_AmtCredSumDiff();

        BigDecimal finTxactItms1_AmtCredSumCalc = null;
        BigDecimal finTxactItms1_AmtCredSumDiff = null;

        String finTxactItms1_QryCred = "select sum(e.amtCred) from enty_UsrFinTxactItm e join e.finStmtItm1_Id e2 where e2.finStmt1_Id = :finStmt1_Id";
        try {
            finTxactItms1_AmtCredSumCalc = dataManager.loadValue(finTxactItms1_QryCred, BigDecimal.class)
                    .store("main")
                    .parameter("finStmt1_Id", thisFinStmt)
                    .one();
            if (finTxactItms1_AmtCredSumCalc == null) {
                finTxactItms1_AmtCredSumCalc = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: " + finTxactItms1_AmtCredSumCalc);

            if (thisFinStmt.getAmtDebt() == null){
                finTxactItms1_AmtCredSumDiff = BigDecimal.valueOf(0);
            }else{
                finTxactItms1_AmtCredSumDiff = thisFinStmt.getAmtCred().subtract(finTxactItms1_AmtCredSumCalc);
            }
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: " + finTxactItms1_AmtCredSumDiff);

        } catch (IllegalStateException e ){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: null");
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: null");
        }


        if (!Objects.equals(finTxactItms1_AmtCredSumCalc_, finTxactItms1_AmtCredSumCalc)){
            thisFinStmt.setFinTxactItms1_AmtCredSumCalc(finTxactItms1_AmtCredSumCalc);
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: " + finTxactItms1_AmtCredSumCalc);
            isChanged = true;
        }

        if (!Objects.equals(finTxactItms1_AmtCredSumDiff_, finTxactItms1_AmtCredSumDiff)){
            thisFinStmt.setFinTxactItms1_AmtCredSumDiff(finTxactItms1_AmtCredSumDiff);
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumDiff: " + finTxactItms1_AmtCredSumDiff);
            isChanged = true;
        }

        BigDecimal finTxactItms1_AmtNetSumCalc_ = thisFinStmt.getFinTxactItms1_AmtNetSumCalc();
        BigDecimal finTxactItms1_AmtNetSumDiff_ = thisFinStmt.getFinTxactItms1_AmtNetSumDiff();

        BigDecimal finTxactItms1_AmtNetSumCalc = null;
        BigDecimal finTxactItms1_AmtNetSumDiff = null;

        if (finTxactItms1_AmtDebtSumCalc == null || finTxactItms1_AmtCredSumCalc == null){
            logger.debug(logPrfx + " --- finTxactItms1_AmtDebtSumCalc: null");
            logger.debug(logPrfx + " --- finTxactItms1_AmtCredSumCalc: null");
        }else{

            if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                    && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
                finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtDebtSumCalc.subtract(finTxactItms1_AmtCredSumCalc);
            }else{
                finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtCredSumCalc.subtract(finTxactItms1_AmtDebtSumCalc);
            }

            if (!Objects.equals(finTxactItms1_AmtNetSumCalc_, finTxactItms1_AmtNetSumCalc)){
                thisFinStmt.setFinTxactItms1_AmtNetSumCalc(finTxactItms1_AmtNetSumCalc);
                logger.debug(logPrfx + " --- finTxactItms1_AmtNetSumCalc: " + finTxactItms1_AmtNetSumCalc);
                isChanged = true;
            }

            finTxactItms1_AmtNetSumDiff = finTxactItms1_AmtNetSumCalc.subtract(thisFinStmt.getAmtNet());

            if (!Objects.equals(finTxactItms1_AmtNetSumDiff_, finTxactItms1_AmtNetSumDiff)){
                thisFinStmt.setFinTxactItms1_AmtNetSumDiff(finTxactItms1_AmtNetSumDiff);
                logger.debug(logPrfx + " --- finTxactItms1_AmtNetSumDiff: " + finTxactItms1_AmtNetSumDiff);
                isChanged = true;
            }

        }



        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateEnd1(@NotNull UsrNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateEnd1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinStmt.updateTs3();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private UsrNode findFinStmtById2(@NotNull String FinStmt_Id2) {
        String logPrfx = "findFinStmtById2";
        logger.trace(logPrfx + " --> ");

        if (FinStmt_Id2 == null) {
            logger.debug(logPrfx + " --- FinStmt_Id2 is null.");
            notifications.create().withCaption("FinStmt_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrFinStmt e where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + FinStmt_Id2);

        UsrNode FinStmt1_Id = null;
        try {
            FinStmt1_Id = dataManager.load(UsrNode.class)
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
                + " from enty_UsrFinStmt e"
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