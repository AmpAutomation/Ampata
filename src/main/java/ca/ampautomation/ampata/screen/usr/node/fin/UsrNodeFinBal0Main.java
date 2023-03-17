package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
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

@UiController("enty_UsrNodeFinBal.main")
@UiDescriptor("usr-node-fin-bal-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinBal0Main extends UsrNodeBase0BaseMain<UsrNodeFinBal, UsrNodeFinBalType, UsrNodeFinBalQryMngr, Table<UsrNodeFinBal>> {

    @Autowired
    protected PropertyFilter<UsrNodeBase> filterConfig1A_FinBalSet1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeBase> filterConfig1A_FinAcct1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeBase> filterConfig1A_FinDept1_Id;

    @Autowired
    protected PropertyFilter<SysNodeBase> filterConfig1A_SysNodeFinCurcy1_Id;


    //Template
    @Autowired
    protected CheckBox tmplt_Ts1ElTsFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1ElTsField;

    @Autowired
    protected CheckBox tmplt_Ts3ElTsFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts3ElTsField;

    @Autowired
    protected ComboBox<String> tmplt_StatusField;

    @Autowired
    protected CheckBox tmplt_StatusFieldChk;



    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNodeBase> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNodeBase> colLoadrMain;
    @Autowired
    private InstanceContainer<UsrNodeBase> instCntnrMain;
    @Autowired
    private Table<UsrNodeBase> tableMain;


    //Type data container and loader
    private CollectionContainer<UsrNodeBaseType> colCntnrType;
    private CollectionLoader<UsrNodeBaseType> colLoadrType;


    //Other data containers, loaders and table
    private CollectionContainer<UsrNodeBase> colCntnrFinBalSet;
    private CollectionLoader<UsrNodeBase> colLoadrFinBalSet;


    private CollectionContainer<UsrNodeBase> colCntnrFinAcct;
    private CollectionLoader<UsrNodeBase> colLoadrFinAcct;


    private CollectionContainer<UsrNodeBase> colCntnrFinDept;
    private CollectionLoader<UsrNodeBase> colLoadrFinDept;


    private CollectionContainer<SysNodeBase> colCntnrSysNodeFinCurcy;
    private CollectionLoader<SysNodeBase> colLoadrSysNodeFinCurcy;


    private CollectionContainer<UsrNodeBase> colCntnrFinBal1;
    private CollectionLoader<UsrNodeBase> colLoadrFinBal1;


    private CollectionContainer<UsrNodeGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrNodeGenDocVer> colLoadrGenDocVer;


    private CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    private CollectionLoader<UsrItemGenTag> colLoadrGenTag;


    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<UsrNodeBaseType> type1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finBalSet1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finAcct1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finDept1_IdField;

    @Autowired
    private EntityComboBox<SysNodeBase> sysNodeFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeBase> finBal1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenDocVer> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenTag> genTag1_IdField;

    @Autowired
    private ComboBox<String> statusField;


    @Autowired
    private Table<UsrNodeBase> tableFinTxactItm;

    @Autowired
    private CollectionContainer<UsrNodeBase> colCntnrFinTxactItm;

    @Autowired
    private CollectionLoader<UsrNodeBase> colLoadrFinTxactItm;

    @Autowired
    private Pagination pagetableTinTxactItm;



    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        colCntnrType = dataComponents.createCollectionContainer(UsrNodeBaseType.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_UsrNodeFinBalType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBalType_Inst = fetchPlans.builder(UsrNodeBaseType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnFinBalType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());
        type1_IdField.setOptionsContainer(colCntnrType);


        colCntnrFinBalSet = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinBalSet = dataComponents.createCollectionLoader();
        colLoadrFinBalSet.setQuery("select e from enty_UsrNodeFinBalSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBalSet_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinBalSet.setFetchPlan(fchPlnFinBalSet_Inst);
        colLoadrFinBalSet.setContainer(colCntnrFinBalSet);
        colLoadrFinBalSet.setDataContext(getScreenData().getDataContext());

        finBalSet1_IdField.setOptionsContainer(colCntnrFinBalSet);
        EntityComboBox<UsrNodeBase> propFilterCmpnt_FinBalSet1_Id = (EntityComboBox<UsrNodeBase>) filterConfig1A_FinBalSet1_Id.getValueComponent();
        propFilterCmpnt_FinBalSet1_Id.setOptionsContainer(colCntnrFinBalSet);


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


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrNodeFinAcct e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcct_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(fchPlnFinAcct_Inst);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(colCntnrFinAcct);
        //filter
        EntityComboBox<UsrNodeBase> propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNodeBase>) filterConfig1A_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setOptionsContainer(colCntnrFinAcct);


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


        colCntnrSysNodeFinCurcy = dataComponents.createCollectionContainer(SysNodeBase.class);
        colLoadrSysNodeFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysNodeFinCurcy.setQuery("select e from enty_SysNodeFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnSysNodeFinCurcy_Inst = fetchPlans.builder(SysNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysNodeFinCurcy.setFetchPlan(fchPlnSysNodeFinCurcy_Inst);
        colLoadrSysNodeFinCurcy.setContainer(colCntnrSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setDataContext(getScreenData().getDataContext());

        sysNodeFinCurcy1_IdField.setOptionsContainer(colCntnrSysNodeFinCurcy);
        //filter
        EntityComboBox<SysNodeBase> propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeBase>) filterConfig1A_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setOptionsContainer(colCntnrSysNodeFinCurcy);


        colCntnrFinBal1 = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinBal1 = dataComponents.createCollectionLoader();
        colLoadrFinBal1.setQuery("select e from enty_UsrNodeFinBal e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBal1_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinBal1.setFetchPlan(fchPlnFinBal1_Inst);
        colLoadrFinBal1.setContainer(colCntnrFinBal1);
        colLoadrFinBal1.setDataContext(getScreenData().getDataContext());

        finBal1_IdField.setOptionsContainer(colCntnrFinBal1);
        
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinBalsDcCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinBalsDcCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            return;
        }
        logger.debug(logPrfx + " --- fired");
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onFinBalsDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinBalsDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBal = event.getItem();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBal.setClassName("UsrNodeFinBal");
        logger.debug(logPrfx + " --- className: UsrNodeFinBal");

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

        colLoadrFinBalSet.load();
        logger.debug(logPrfx + " --- called colLoadrFinBalSet.load() ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        colLoadrFinBal1.load();
        logger.debug(logPrfx + " --- called colLoadrFinBal1.load() ");

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
        
        List<UsrNodeBase> thisFinBals = tableMain.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBals.forEach(orig -> {
            UsrNodeBase copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

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

            copy.updateId2Calc(dataManager);
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

        List<UsrNodeBase> thisFinBals = tableMain.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeBase> sels = new ArrayList<>();

        thisFinBals.forEach(orig -> {

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

            copy.updateId2Calc(dataManager);
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNodeBase savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Derived FinBal " + copy.getId2() + " "
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

        List<UsrNodeBase> thisFinBals = tableMain.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinBals.forEach(thisFinBal -> {
            UsrNodeBase thisTrackedFinBal = dataContext.merge(thisFinBal);
            thisFinBal = thisTrackedFinBal;
            if (thisFinBal != null) {

                Boolean thisFinBalIsChanged = false;
                
                LocalDateTime ts1;
                if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                    thisFinBalIsChanged = true;
                    ts1 = tmplt_Ts1ElTsField.getValue();
                    thisFinBal.getTs1().setElTs(ts1);
                }

                LocalDateTime ts3;
                if (tmplt_Ts3ElTsFieldChk.isChecked()) {
                    thisFinBalIsChanged = true;
                    ts3 = tmplt_Ts3ElTsField.getValue();
                    thisFinBal.getTs3().setElTs(ts3);
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

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNodeBase> thisFinBals = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinBals.forEach(thisFinBal -> {
                //UsrNodeBase thisTrackedFinBal = dataContext.merge(thisFinBal);
                if (thisFinBal != null) {
                    UsrNodeBase thisTrackedFinBal = dataContext.merge(thisFinBal);
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

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisFinBals);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeBase> thisFinBals = tableMain.getSelected().stream().toList();
        if (thisFinBals == null || thisFinBals.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBals.forEach(thisFinBal -> {
            if (thisFinBal != null) {

                UsrNodeBase thisTrackedFinBal = dataContext.merge(thisFinBal);
                thisFinBal = thisTrackedFinBal;

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinBal);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisFinBals);
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
    public void onFinBalDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeBase> event) {
        String logPrfx = "onFinBalDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBal = event.getSource().getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            //todo I observed thisFinBal is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBal.setClassName("UsrNodeFinBal");
        logger.debug(logPrfx + " --- className: UsrNodeFinBal");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinBal);

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

    @Subscribe("updateFinBalSet1_IdFieldListBtn")
    public void onUpdateFinBalSet1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinBalSet1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinBalSet.load();
        logger.debug(logPrfx + " --- called colLoadrFinBalSet.load() ");

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

    @Subscribe("updateFinDept1_IdFieldListBtn")
    public void onUpdateFinDept1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinDept1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        logger.trace(logPrfx + " <-- ");

    }
    
    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtBegBalBtn")
    public void onUpdateAmtBegBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        colLoadrFinBal1.load();
        logger.debug(logPrfx + " --- called colLoadrFinBal1.load() ");

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
            UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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
            UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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
            UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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
            UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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
            UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateCalcVals(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    public Boolean updateManVals(UsrNodeBase thisFinBal){
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

    public Boolean updateCalcVals(UsrNodeBase thisFinBal){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinBalCalcVals(thisFinBal) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinBalCalcVals(@NotNull UsrNodeBase thisFinBal) {
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


    private Boolean updateId2(@NotNull UsrNodeBase thisFinBal) {
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

    private Boolean updateId2Calc(UsrNodeBase thisFinBal){
        // Assume thisFinBal is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinBal.getId2Calc();
        String id2Calc = thisFinBal.getId2CalcFrFields(dataManager);
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinBal.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull UsrNodeBase thisFinBal) {
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
    
    private Boolean updateId2Dup(@NotNull UsrNodeBase thisFinBal) {
        // Assume thisFinBal is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinBal.getId2Dup();
        if (thisFinBal.getId2() != null){
            String id2Qry = "select count(e) from enty_UsrNodeFinBal e where e.id2 = :id2 and e.id <> :id";
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

    private Boolean updateDesc1(@NotNull UsrNodeBase thisFinBal){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdDt(@NotNull UsrNodeBase thisFinBal) {
        // Assume thisFinBal is not null
        String logPrfx = "updateIdDt";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        isChanged = isChanged || thisFinBal.updateDt1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtDebt(@NotNull UsrNodeBase thisFinBal){
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

    public Boolean updateAmtCred(@NotNull UsrNodeBase thisFinBal){
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


    public Boolean updateAmtNet(@NotNull UsrNodeBase thisFinBal){
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

    public Boolean updateAmtBegBal(@NotNull UsrNodeBase thisFinBal){
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

    public Boolean updateAmtBegBalCalc(@NotNull UsrNodeBase thisFinBal){
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


    public Boolean updateAmtEndBal(@NotNull UsrNodeBase thisFinBal){
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

    public Boolean updateAmtEndBalCalc(@NotNull UsrNodeBase thisFinBal){
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


    public Boolean updateFinTxactItms1_FieldSet(@NotNull UsrNodeBase thisFinBal) {
        String logPrfx = "updateFinTxactItms1_FieldSet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        UsrNodeBase finAcct1 = thisFinBal.getFinAcct1_Id();
        UsrNodeBase finDept1 = thisFinBal.getFinDept1_Id();

        if (finAcct1 == null) {
            logger.debug(logPrfx + " --- finAcct1 is null.");
            notifications.create().withCaption("finAcct1 is null.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        LocalDate begDt = thisFinBal.getTs1() == null ? null : thisFinBal.getTs1().getElDt();
        if (begDt == null) {
            logger.debug(logPrfx + " --- begDt is null.");
            notifications.create().withCaption("begDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }
        LocalDate endDt = thisFinBal.getTs3() == null ? null : thisFinBal.getTs3().getElDt();
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
                finTxactItms1_QryCnt = "select count(e)"
                        + " from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
                        + "";
                finTxactItms1_IdCntCalc = dataManager.loadValue(finTxactItms1_QryCnt,Integer.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }else{
                finTxactItms1_QryCnt = "select count(e) from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
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
                finTxactItms1_QryDebt = "select sum(e.amtDebt) from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
                        + "";
                finTxactItms1_AmtDebtSumCalc = dataManager.loadValue(finTxactItms1_QryDebt,BigDecimal.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }else{
                finTxactItms1_QryDebt = "select sum(e.amtDebt) from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
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
                finTxactItms1_QryCred = "select sum(e.amtCred) from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
                        + "";
                finTxactItms1_AmtCredSumCalc = dataManager.loadValue(finTxactItms1_QryCred,BigDecimal.class)
                        .store("main")
                        .parameter("finAcct1_Id",finAcct1)
                        .parameter("begDt",begDt)
                        .parameter("endDt",endDt)
                        .one();
            }else{
                finTxactItms1_QryCred = "select sum(e.amtCred) from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
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



    private UsrNodeBase findFinBalById2(@NotNull String FinBal_Id2) {
        String logPrfx = "findFinBalById2";
        logger.trace(logPrfx + " --> ");

        if (FinBal_Id2 == null) {
            logger.debug(logPrfx + " --- FinBal_Id2 is null.");
            notifications.create().withCaption("FinBal_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_UsrNodeFinBale where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + FinBal_Id2);

        UsrNodeBase FinBal1_Id = null;
        try {
            FinBal1_Id = dataManager.load(UsrNodeBase.class)
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
                + " from enty_UsrNodeFinBal e"
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

        UsrNodeBase thisFinBal = instCntnrMain.getItemOrNull();
        if (thisFinBal == null) {
            logger.debug(logPrfx + " --- thisFinBal is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        loadTableFinTxactItm(thisFinBal);

        logger.trace(logPrfx + " <-- ");
    }

    public void loadTableFinTxactItm(@NotNull UsrNodeBase thisFinBal) {
        String logPrfx = "loadTableFinTxactItm";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase finAcct1 = thisFinBal.getFinAcct1_Id();
        UsrNodeBase finDept1 = thisFinBal.getFinDept1_Id();

        if (finAcct1 == null) {
            logger.debug(logPrfx + " --- finAcct1 is null.");
            notifications.create().withCaption("finAcct1 is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        LocalDate begDt = thisFinBal.getTs1() == null ? null : thisFinBal.getTs1().getElDt();
        if (begDt == null) {
            logger.debug(logPrfx + " --- begDt is null.");
            notifications.create().withCaption("begDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        LocalDate endDt = thisFinBal.getTs3() == null ? null : thisFinBal.getTs3().getElDt();
        if (endDt == null) {
            logger.debug(logPrfx + " --- endDt is null.");
            notifications.create().withCaption("endDt is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        colCntnrFinTxactItm = dataComponents.createCollectionContainer(UsrNodeBase.class);
        colLoadrFinTxactItm = dataComponents.createCollectionLoader();

        try{
            String finTxactItms1_Qry;
            if (finDept1 == null) {
                finTxactItms1_Qry = "select e from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.nm1S1Inst1Dt1.elDt between :begDt and :endDt"
                        + "";
                colLoadrFinTxactItm.setQuery(finTxactItms1_Qry);
                colLoadrFinTxactItm.setParameter("finAcct1_Id",finAcct1);
                colLoadrFinTxactItm.setParameter("begDt",begDt);
                colLoadrFinTxactItm.setParameter("endDt",endDt);
                ;
            }else{
                finTxactItms1_Qry = "select e from enty_UsrNodeFinTxactItm e"
                        + " where e.finAcct1_Id = :finAcct1_Id"
                        + " and e.finDept1_Id = :finDept1_Id"
                        + " and e.n1s1Inst1Dt1.elDt between :begDt and :endDt"
                        + "";
                colLoadrFinTxactItm.setQuery(finTxactItms1_Qry);
                colLoadrFinTxactItm.setParameter("finAcct1_Id",finAcct1);
                colLoadrFinTxactItm.setParameter("finDept1_Id",finDept1);
                colLoadrFinTxactItm.setParameter("begDt",begDt);
                colLoadrFinTxactItm.setParameter("endDt",endDt);
            }
            FetchPlan fchPlnFinTxactItm_Inst = fetchPlans.builder(UsrNodeBase.class)
                    .addFetchPlan("fetchPlan_UsrNodeFinTxactItm_Base")
                    .build();
            colLoadrFinTxactItm.setFetchPlan(fchPlnFinTxactItm_Inst);
            colLoadrFinTxactItm.setContainer(colCntnrFinTxactItm);

            colLoadrFinTxactItm.setDataContext(getScreenData().getDataContext());

            tableFinTxactItm.setItems(new ContainerTableItems<>(colCntnrFinTxactItm));

//            colLoadrFinTxactItm.load();
//            logger.debug(logPrfx + " --- colCntnrFinTxactItm returned rows: " + colCntnrFinTxactItm.getItems().size());
            //pagetableTinTxactItm.setDataBinder(...);



        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
        }

        logger.trace(logPrfx + " <-- ");
    }


}