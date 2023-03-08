package ca.ampautomation.ampata.screen.usr.node.fin;

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
public class UsrNodeFinBalSet0Main extends UsrNodeBase0BaseMain<UsrNodeFinBalSet, UsrNodeFinBalSetType, UsrNodeFinBalSetQryMngr, TreeTable<UsrNodeFinBalSet>> {

    //Filter

    @Autowired
    protected PropertyFilter<UsrNodeFinAcctType> filterConfig1A_FinAcctType1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinDept> filterConfig1A_FinDept1_Id;

    //Template

    @Autowired
    protected CheckBox tmplt_Ts1ElTsFieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1ElTsField;


    @Autowired
    protected CheckBox tmplt_Ts2ElTsFieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts2ElTsField;


    @Autowired
    protected CheckBox tmplt_StatusFieldChk;
    @Autowired
    protected ComboBox<String> tmplt_StatusField;



    //Main data containers, loaders and table

    private CollectionContainer<UsrNodeFinDept> colCntnrFinDept;
    private CollectionLoader<UsrNodeFinDept> colLoadrFinDept;


    private CollectionContainer<UsrNodeFinBalSet> finBalSet1sDc;
    private CollectionLoader<UsrNodeFinBalSet> finBalSet1sDl;


    private CollectionContainer<UsrNodeGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrNodeGenDocVer> colLoadrGenDocVer;


    private CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    private CollectionLoader<UsrItemGenTag> colLoadrGenTag;


    private CollectionContainer<UsrNodeFinAcctType> colCntnrFinAcctType;
    private CollectionLoader<UsrNodeFinAcctType> colLoadrFinAcctType;

    @Autowired
    private Table<UsrNodeFinAcct> tableFinAcct;

    @Autowired
    private CollectionLoader<UsrNodeFinBal> colLoadrFinBal;
    @Autowired
    private CollectionContainer<UsrNodeFinBal> colCntnrFinBal;
    @Autowired
    private Table<UsrNodeFinBal> tableFinBal;


    //Field
    @Autowired
    private EntityComboBox<UsrNodeFinDept> finDept1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinBalSet> finBalSet1_IdField;

    @Autowired
    private ComboBox<String> statusField;





    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        statusField.setNullOptionVisible(true);
        statusField.setNullSelectionCaption("<null>");

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");

        colCntnrFinDept = dataComponents.createCollectionContainer(UsrNodeFinDept.class);
        colLoadrFinDept = dataComponents.createCollectionLoader();
        colLoadrFinDept.setQuery("select e from enty_UsrNodeFinDept e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinDept_Inst = fetchPlans.builder(UsrNodeFinDept.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinDept.setFetchPlan(fchPlnFinDept_Inst);
        colLoadrFinDept.setContainer(colCntnrFinDept);
        colLoadrFinDept.setDataContext(getScreenData().getDataContext());

        finDept1_IdField.setOptionsContainer(colCntnrFinDept);
        //filter
        EntityComboBox<UsrNodeFinDept> propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNodeFinDept>) filterConfig1A_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setOptionsContainer(colCntnrFinDept);


        finBalSet1sDc = dataComponents.createCollectionContainer(UsrNodeFinBalSet.class);
        finBalSet1sDl = dataComponents.createCollectionLoader();
        finBalSet1sDl.setQuery("select e from enty_UsrNodeFinBalSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBalSet1_Inst = fetchPlans.builder(UsrNodeFinBalSet.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSet1sDl.setFetchPlan(fchPlnFinBalSet1_Inst);
        finBalSet1sDl.setContainer(finBalSet1sDc);
        finBalSet1sDl.setDataContext(getScreenData().getDataContext());

        finBalSet1_IdField.setOptionsContainer(finBalSet1sDc);

        
        colCntnrFinAcctType = dataComponents.createCollectionContainer(UsrNodeFinAcctType.class);
        colLoadrFinAcctType = dataComponents.createCollectionLoader();
        colLoadrFinAcctType.setQuery("select e from enty_UsrNodeFinAcctType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcctType_Inst = fetchPlans.builder(UsrNodeFinAcctType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcctType.setFetchPlan(fchPlnFinAcctType_Inst);
        colLoadrFinAcctType.setContainer(colCntnrFinAcctType);
        colLoadrFinAcctType.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<UsrNodeFinAcctType> propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeFinAcctType>) filterConfig1A_FinAcctType1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrFinAcctType);

        
        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tableMain.[ts1.elTs]", subject = "formatter")
    private String tableTs1ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "tableMain.[ts2.elTs]", subject = "formatter")
    private String tableTs2ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        finBalSet1sDl.load();
        logger.debug(logPrfx + " --- called finBalSet1sDl.load() ");

        colLoadrFinAcctType.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcctType.load() ");

        reloadStatusList();

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");
        
        List<UsrNodeFinBalSet> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSets.forEach(orig -> {
            UsrNodeFinBalSet copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (tmplt_Type1_IdFieldChk.isChecked()) {
                copy.setType1_Id(tmplt_Type1_IdField.getValue());
            }

            LocalDateTime ts1;
            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                ts1 = tmplt_Ts1ElTsField.getValue();
                copy.getTs1().setElTs(ts1);
            }

            LocalDateTime ts2;
            if (tmplt_Ts2ElTsFieldChk.isChecked()) {
                ts2 = tmplt_Ts2ElTsField.getValue();
                copy.getTs2().setElTs(ts2);
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNodeFinBalSet savedCopy = dataManager.save(copy);
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

        List<UsrNodeFinBalSet> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeFinBalSet> sels = new ArrayList<>();

        thisFinBalSets.forEach(orig -> {

            UsrNodeFinBalSet copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDateTime ts1;
            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                ts1 = tmplt_Ts1ElTsField.getValue();
                copy.getTs1().setElTs(ts1);
            }else{
                if (orig.getTs1().getElTs() != null) {
                    ts1 = orig.getTs2().getElTs().plusDays(1);
                    copy.getTs1().setElTs(ts1);
                }
            }

            LocalDateTime ts2;
            if (tmplt_Ts2ElTsFieldChk.isChecked()) {
                ts2 = tmplt_Ts2ElTsField.getValue();
                copy.getTs2().setElTs(ts2);
            }else{
                if (orig.getTs2().getElTs() != null) {
                    ts2 = orig.getTs2().getElTs().plusMonths(1);
                    copy.getTs2().setElTs(ts2);
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

            UsrNodeFinBalSet savedCopy = dataManager.save(copy);
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

        List<UsrNodeFinBalSet> thisFinBalSets = tableMain.getSelected().stream().toList();
        if (thisFinBalSets == null || thisFinBalSets.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeFinBalSet> chngFinBalSets = new ArrayList<>();
        List<UsrNodeFinBalSet> finalChngFinBalSets = chngFinBalSets;

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

                LocalDateTime ts2;
                if (tmplt_Ts2ElTsFieldChk.isChecked()) {
                    ts2 = tmplt_Ts2ElTsField.getValue();
                    thisFinBalSet.getTs2().setElTs(ts2);
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);

                }

                if (tmplt_StatusFieldChk.isChecked()
                ) {
                    thisFinBalSet.setStatus(tmplt_StatusField.getValue());
                    thisFinBalSetIsChanged = true;
                    finalChngFinBalSets.add(thisFinBalSet);
                }

                thisFinBalSetIsChanged = thisFinBalSet.updateId2Calc(dataManager) || thisFinBalSetIsChanged;
                thisFinBalSetIsChanged = thisFinBalSet.updateId2(dataManager) || thisFinBalSetIsChanged;
                thisFinBalSetIsChanged = thisFinBalSet.updateId2Cmp(dataManager) || thisFinBalSetIsChanged;

            }
        });

        if (dataContext.hasChanges()) {
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();
        }

        chngFinBalSets = finalChngFinBalSets.stream().distinct().collect(Collectors.toList());
        updateHelper(chngFinBalSets);
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
    public void onFinBalSetDcItemChange(InstanceContainer.ItemChangeEvent<UsrNodeFinBalSet> event) {
        String logPrfx = "onFinBalSetDcItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisFinBalSet = event.getSource().getItemOrNull();
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

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.updateDesc1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
            if (thisFinBalSet == null) {
                logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisFinBalSet.updateId2Cmp(dataManager);
            thisFinBalSet.updateId2Dup(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.updateId2(dataManager);
        thisFinBalSet.updateId2Cmp(dataManager);
        thisFinBalSet.updateId2Dup(dataManager);

        logger.debug(logPrfx + " --- id2: " + thisFinBalSet.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.updateId2Calc(dataManager);
        thisFinBalSet.updateId2Cmp(dataManager);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinBalSet.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.updateId2Cmp(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.updateId2Dup(dataManager);

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


    @Subscribe("ts2ElTsField")
    public void onTs2ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onTs2ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
            if (thisFinBalSet == null) {
                logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling thisFinBalSet.updateTs2Deps(dataManager)");
            thisFinBalSet.updateTs2Deps(dataManager);

            logger.debug(logPrfx + " --- calling thisFinBalSet.updateTs2Deps(dataManager)");
            thisFinBalSet.updateId2Calc(dataManager);
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

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
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

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeFinAcct> thisFinAccts = new ArrayList<>(tableFinAcct.getSelected().stream().toList());
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

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeFinAcct> thisFinAccts = tableFinAcct.getItems().getItems().stream().toList();
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

        UsrNodeFinBalSet thisFinBalSet = instCntnrMain.getItemOrNull();
        if (thisFinBalSet == null) {
            logger.debug(logPrfx + " --- thisFinBalSet is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinBalSet.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    public Integer addFinAccts(@NotNull UsrNodeFinBalSet thisFinBalSet, List<UsrNodeFinAcct> thisFinAccts, int iOptions){
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
                        UsrNodeFinBal finBal = dataManager.create(UsrNodeFinBal.class);
                        finBal.setClassName("UsrNodeFinBal");

                        finBal.setFinAcct1_Id(thisFinAcct);
                        finBal.setFinBalSet1_Id(thisFinBalSet);
                        if (thisFinBalSet.getFinDept1_Id() != null) {
                            finBal.setFinDept1_Id(thisFinBalSet.getFinDept1_Id());
                        }

                        if (thisFinBalSet.getType1_Id() != null) {
                            UsrNodeBaseType type1;
                            type1 = UsrNodeBaseType.getNodeTypeById2(UsrNodeFinBalType.class, dataManager, thisFinBalSet.getType1_Id().getId2());
                            if (type1 != null) {
                                finBal.setType1_Id(type1);
                            }
                        }
                        finBal.setSysNodeFinCurcy1_Id(thisFinAcct.getSysNodeFinCurcy1_Id());

                        finBal.setSortKey("_AA" + thisFinBalSet.getSortKey() + thisFinAcct.getSortKey());

                        finBal.getTs1().setElTs(thisFinBalSet.getTs1().getElTs());
                        finBal.getTs2().setElTs(thisFinBalSet.getTs2().getElTs());
                        //TODO
                        finBal.updateDt1();

                        finBal.setAmtBegBal(BigDecimal.ZERO);
                        finBal.setAmtDebt(BigDecimal.ZERO);
                        finBal.setAmtCred(BigDecimal.ZERO);
                        finBal.setAmtNet(BigDecimal.ZERO);
                        finBal.setAmtEndBal(BigDecimal.ZERO);

                        if (thisFinBalSet.getFinBalSet1_Id() != null) {
                            UsrNodeFinBal finBal1;
                            finBal1 = getFinBalByFinBalSet1_FinAcct1(thisFinBalSet.getFinBalSet1_Id(), thisFinAcct);
                            if (finBal1 != null) {
                                finBal.setFinBal1_Id(finBal1);
                            }
                        }

                        finBal.updateId2Calc(dataManager);
                        finBal.updateId2(dataManager);
                        finBal.updateId2Cmp(dataManager);
                        finBal.updateId2Dup(dataManager);

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




    private UsrNodeFinBal getFinBalByFinBalSet1_FinAcct1(@NotNull UsrNodeFinBalSet finBalSet1, @NotNull UsrNodeFinAcct finAcct1) {
        String logPrfx = "getFinBalByFinBalSet1_FinAcct1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_UsrFinBal e"
                + " where e.finBalSet1_Id = :finBalSet1"
                + " and e.finAcct1_Id = :finAcct1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:finBalSet1: " + finBalSet1);
        logger.debug(logPrfx + " --- qry:finAcct1: " + finAcct1);

        UsrNodeFinBal finBal = null;
        try {
            finBal = dataManager.load(UsrNodeFinBal.class)
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

    public void loadTableFinBal(@NotNull UsrNodeFinBalSet thisFinBalSet) {
        String logPrfx = "loadTableFinBal";
        logger.trace(logPrfx + " --> ");

        colCntnrFinBal = dataComponents.createCollectionContainer(UsrNodeFinBal.class);
        colLoadrFinBal = dataComponents.createCollectionLoader();

        try{
            String finTxactItms1_Qry;
            finTxactItms1_Qry = "select e"
                    + " from enty_UsrFinBal e"
                    + " where e.finBalSet1_Id = :finBalSet1_Id"
                    + "";
            colLoadrFinBal.setQuery(finTxactItms1_Qry);
            colLoadrFinBal.setParameter("finBalSet1_Id",thisFinBalSet);

            FetchPlan fchPlnFinBal_Inst = fetchPlans.builder(UsrNodeFinBal.class)
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