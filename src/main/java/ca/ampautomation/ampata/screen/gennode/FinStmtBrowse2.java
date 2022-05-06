package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
import ca.ampautomation.ampata.entity.HasTmst;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
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
    protected DateField<LocalDate> tmplt_beg1Date1Field;


    @Autowired
    protected DateField<LocalDate> tmplt_end1Date1Field;


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
    private GroupTable<GenNode> table2;

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
    private CollectionContainer<GenNode> finTxfersDc;

    @Autowired
    private CollectionLoader<GenNode> finTxfersDl;


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
        String logPrfx = "onFinStmtHideBtnClick";
        logger.trace(logPrfx + " --> ");

        List<String> list = new ArrayList<>();
        list.add("Reconciled");
        list.add("Non-Reconciled");
        statusField.setOptionsList(list);


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

        finTxfersDl.setParameter("finStmt1_Id", event.getItem());
        finTxfersDl.load();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");

        genChansDl.load();
        logger.debug(logPrfx + " --- called genChansDl.load() ");

        finAcctsDl.load();
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

        logger.debug(logPrfx + " --- executing Db-Proc.Fin_Txfer_Pr_Upd()");
        repo.execFinStmtPrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Fin_Txfer_Pr_Upd()");

        logger.debug(logPrfx + " --- loading finTxfersDl.load()");
        finTxfersDl.load();
        logger.debug(logPrfx + " --- finished finTxfersDl.load()");

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

            if (tmplt_beg1Date1Field.getValue() != null) {
                HasTmst beg1 = dataManager.create(HasTmst.class);
                beg1.setTs1(tmplt_beg1Date1Field.getValue().atTime(0,0));
                copy.setBeg1(beg1);
            }

            if (tmplt_end1Date1Field.getValue() != null) {
                HasTmst end1 = dataManager.create(HasTmst.class);
                end1.setTs1(tmplt_beg1Date1Field.getValue().atTime(0,0));
                copy.setBeg1(end1);
            }

            copy.setId2(copy.getId2() + " Copy");
            copy.setId2Calc(copy.getId2() + " Copy");

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
        thisFinStmts.forEach(orig -> {

            GenNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getBeg1().getDate1() != null) {
                copy.getBeg1().setTs1(orig.getBeg1().getTs1().plusMonths(1));}

            if (orig.getEnd1().getDate1() != null) {
                copy.getEnd1().setTs1(orig.getEnd1().getTs1().plusMonths(1));}

            if (orig.getEndBal() != null) {
                copy.setBegBal(orig.getEndBal());}

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2() + " Copy");
            }

            GenNode savedCopy = dataManager.save(copy);
            finStmtsDc.getMutableItems().add(savedCopy);
            logger.debug("Derived FinStmt " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        
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

        GenNode thisFinStmt = finStmtDc.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        GenNode thisFinStmtTracked = dataContext.find(thisFinStmt);
        if (thisFinStmtTracked != null) {
            updateCalcVals(thisFinStmtTracked);

            logger.debug(logPrfx + " --- executing metadataTools.copy(thisFinStmtTracked,thisFinStmt).");
            metadataTools.copy(thisFinStmtTracked,thisFinStmt);
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTxferHideBtn")
    public void onFinTxferHideBtnClick(Button.ClickEvent event) {
        String logPrfx = "onFinTxferHideBtnClick";
        logger.trace(logPrfx + " --> ");

        if (table2.isVisible()){
            table2.setVisible(false);
            finTxferHideBtn.setCaption("Show Txfers");
        }else{
            table2.setVisible(true);
            finTxferHideBtn.setCaption("Hide Txfers");
        }
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
        thisFinStmt.setId2(thisFinStmt.getId2Calc());
        updateId2Cmp(thisFinStmt);
        updateId2Dup(thisFinStmt);

        logger.debug(logPrfx + " --- id2: " + thisFinStmt.getId2());
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
            String id2Calc = updateId2Calc(thisFinStmt);
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

    @Install(to = "table2.[idDt.date1]", subject = "formatter")
    private String table2IdDtDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
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

    public void updateCalcVals(GenNode thisFinStmt){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        updateIdDt(thisFinStmt);
        String id2Calc = updateId2Calc(thisFinStmt);
        updateDesc1(thisFinStmt);
        updateFinAcct1_Type1_Id2(thisFinStmt);
        updateSumNet(thisFinStmt);
        updateEndBal(thisFinStmt);
        updateFieldSet1(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
    }

    private String updateId2Calc(GenNode thisFinStmt){
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisFinStmt.getId2CalcFrFields();
        thisFinStmt.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        updateId2Cmp(thisFinStmt);
        updateId2Dup(thisFinStmt);

        logger.trace(logPrfx + " <-- ");
        return  id2Calc;
    }

    private String updateId2Calc(GenNode thisFinStmt, String id2Calc){
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        thisFinStmt.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
        return  id2Calc;
    }

    private void updateId2Cmp(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisFinStmt.setId2Cmp(!thisFinStmt.getId2().equals(thisFinStmt.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisFinStmt.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }
    private void updateId2Dup(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisFinStmt.getId2() != null){
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinTxfer' and e.id2 = :id2 and e.id <> :id";
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
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");

            thisFinStmt.setId2Dup(id2Dup + 1);
            logger.debug(logPrfx + " --- thisFinStmt.setId2Dup(" + (id2Dup + 1) + ")");

        }
        logger.trace(logPrfx + " <-- ");
    }

    private void updateDesc1(@NotNull GenNode thisFinStmt){
        // Assume thisFinStmt is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

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
        thisFinStmt.setDesc1(desc1);

        logger.debug(logPrfx + " --- desc1: " + desc1);
        logger.trace(logPrfx + " <-- ");
    }

    private void updateIdDt(@NotNull GenNode thisFinStmt) {
        // Assume thisFinStmt is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        thisFinStmt.updateIdDt();

        logger.trace(logPrfx + " <-- ");
    }


    public void updateEndBal(@NotNull GenNode thisFinStmt){
        String logPrfx = "updateEndBal";
        logger.trace(logPrfx + " --> ");

        BigDecimal endBal = BigDecimal.valueOf(0);
        if (thisFinStmt.getBegBal() == null || thisFinStmt.getNetSum() == null){
            thisFinStmt.setEndBal(null);
            logger.debug(logPrfx + " --- endBal: null");
            return;
        }
        endBal = thisFinStmt.getBegBal().add(thisFinStmt.getNetSum());
        thisFinStmt.setEndBal(endBal);
        logger.debug(logPrfx + " --- endBal: " + endBal);

        logger.trace(logPrfx + " <-- ");
    }

    public void updateSumNet(@NotNull GenNode thisFinStmt){
        String logPrfx = "updateSumNet";
        logger.trace(logPrfx + " --> ");

        BigDecimal netSum = BigDecimal.valueOf(0);
        if (thisFinStmt.getDebtSum() == null || thisFinStmt.getCredSum() == null){
            thisFinStmt.setNetSum(null);
            logger.debug(logPrfx + " --- netSum: null");
            return;
        }

        if (thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt() != null
                && thisFinStmt.getFinAcct1_Id().getType1_Id().getBalIncOnDebt()){
            netSum = thisFinStmt.getDebtSum().subtract(thisFinStmt.getCredSum());

        }else{
            netSum = thisFinStmt.getCredSum().subtract(thisFinStmt.getDebtSum());
        }
        thisFinStmt.setNetSum(netSum);
        logger.debug(logPrfx + " --- netSum: " + netSum);
        logger.trace(logPrfx + " <-- ");
    }

    public void updateFinAcct1_Type1_Id2(@NotNull GenNode thisFinStmt){
        String logPrfx = "updateFinAcct1_Type1_Id2";
        logger.trace(logPrfx + " --> ");

        String finAcct1_Type1_Id2;
        if (thisFinStmt.getFinAcct1_Id() == null
                || thisFinStmt.getFinAcct1_Id().getType1_Id() == null){
            thisFinStmt.setFinAcct1_Type1_Id2(null);
            logger.debug(logPrfx + " --- finAcct1_Type1_Id2: null");

        }else{
            finAcct1_Type1_Id2 = thisFinStmt.getFinAcct1_Id().getType1_Id().getId2();
            thisFinStmt.setFinAcct1_Type1_Id2(finAcct1_Type1_Id2);
            logger.debug(logPrfx + " --- finAcct1_Type1_Id2: " + finAcct1_Type1_Id2);
        }

        logger.trace(logPrfx + " <-- ");
    }

    public void updateFieldSet1(@NotNull GenNode thisFinStmt) {
        String logPrfx = "updateFieldSet1";
        logger.trace(logPrfx + " --> ");

        BigDecimal debtSumCalc = null;
        BigDecimal debtSumDiff = null;
        String qry1 = "select sum(e.amtDebt) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmt1_Id = :finStmt1_Id group by e.finStmt1_Id";
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

        BigDecimal credSumCalc = null;
        BigDecimal credSumDiff = null;
        String qry2 = "select sum(e.amtCred) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmt1_Id = :finStmt1_Id group by e.finStmt1_Id";
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
    }


}