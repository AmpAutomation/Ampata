package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBalSet0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBal0Service;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBalSet0Service;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import io.jmix.core.*;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Route(value = "usrNodeFinBalSets", layout = MainView.class)
@ViewController("enty_UsrNodeFinBalSet.main")
@ViewDescriptor("usr-node-fin-bal-set-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinBalSet0Main extends UsrNodeBase0BaseMain<UsrNodeFinBalSet, UsrNodeFinBalSetType, UsrNodeFinBalSet0Service, UsrNodeFinBalSet0Repo, DataGrid<UsrNodeFinBalSet>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet.Service")
    public void setService(UsrNodeFinBalSet0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet.Repo")
    public void setRepo(UsrNodeFinBalSet0Repo repo) { this.repo = repo; }

    // Service
    @Autowired
    protected UsrNodeFinBal0Service serviceFinBal;

    //Filter
    @ViewComponent
    protected PropertyFilter<UsrNodeFinDept> filterConfig1A_FinDept1_Id;

    @ViewComponent
    protected PropertyFilter<UsrNodeFinAcctType> filter2Config1A_Type1_Id;

    //Template

    @ViewComponent
    protected JmixCheckbox tmplt_Ts1_ElTsFieldChk;
    @Autowired
    protected TypedDateTimePicker<LocalDateTime> tmplt_Ts1_ElTsField;


    @ViewComponent
    protected JmixCheckbox tmplt_Ts2_ElTsFieldChk;
    @Autowired
    protected TypedDateTimePicker<LocalDateTime> tmplt_Ts2_ElTsField;


    @ViewComponent
    protected JmixCheckbox tmplt_StatusFieldChk;
    @ViewComponent
    protected JmixComboBox<String> tmplt_StatusField;



    //Main data containers, loaders and table

    private CollectionContainer<UsrNodeFinDept> colCntnrFinDept;
    private CollectionLoader<UsrNodeFinDept> colLoadrFinDept;


    private CollectionContainer<UsrNodeFinBalSet> finBalSet1sDc;
    private CollectionLoader<UsrNodeFinBalSet> finBalSet1sDl;


    private CollectionContainer<UsrNodeFinAcctType> colCntnrFinAcctType;
    private CollectionLoader<UsrNodeFinAcctType> colLoadrFinAcctType;

    @Autowired
    private DataGrid<UsrNodeFinAcct> tableFinAcct;

    @Autowired
    private CollectionContainer<UsrNodeFinBal> colCntnrFinBal;
    @Autowired
    private CollectionLoader<UsrNodeFinBal> colLoadrFinBal;
    @Autowired
    private DataGrid<UsrNodeFinBal> tableFinBal;


    //Field
    @Autowired
    private EntityComboBox<UsrNodeFinDept> finDept1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinBalSet> finBalSet1_IdField;

    @Autowired
    private JmixComboBox<String> statusField;



    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        //todo check how to refactor setNullOptionVisible
        //statusField.setNullOptionVisible(true);
        //statusField.setNullSelectionCaption("<null>");

        //tmplt_StatusField.setNullOptionVisible(true);
        //tmplt_StatusField.setNullSelectionCaption("<null>");

        colCntnrFinDept = dataComponents.createCollectionContainer(UsrNodeFinDept.class);
        colLoadrFinDept = dataComponents.createCollectionLoader();
        colLoadrFinDept.setQuery("select e from enty_UsrNodeFinDept e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinDept_Inst = fetchPlans.builder(UsrNodeFinDept.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinDept.setFetchPlan(fchPlnFinDept_Inst);
        colLoadrFinDept.setContainer(colCntnrFinDept);
        colLoadrFinDept.setDataContext(getViewData().getDataContext());

        finDept1_IdField.setItems(colCntnrFinDept);
        //filter
        EntityComboBox<UsrNodeFinDept> propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNodeFinDept>) filterConfig1A_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setItems(colCntnrFinDept);


        finBalSet1sDc = dataComponents.createCollectionContainer(UsrNodeFinBalSet.class);
        finBalSet1sDl = dataComponents.createCollectionLoader();
        finBalSet1sDl.setQuery("select e from enty_UsrNodeFinBalSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBalSet1_Inst = fetchPlans.builder(UsrNodeFinBalSet.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finBalSet1sDl.setFetchPlan(fchPlnFinBalSet1_Inst);
        finBalSet1sDl.setContainer(finBalSet1sDc);
        finBalSet1sDl.setDataContext(getViewData().getDataContext());

        finBalSet1_IdField.setItems(finBalSet1sDc);

        
        colCntnrFinAcctType = dataComponents.createCollectionContainer(UsrNodeFinAcctType.class);
        colLoadrFinAcctType = dataComponents.createCollectionLoader();
        colLoadrFinAcctType.setQuery("select e from enty_UsrNodeFinAcctType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcctType_Inst = fetchPlans.builder(UsrNodeFinAcctType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcctType.setFetchPlan(fchPlnFinAcctType_Inst);
        colLoadrFinAcctType.setContainer(colCntnrFinAcctType);
        colLoadrFinAcctType.setDataContext(getViewData().getDataContext());

        //filter
        EntityComboBox<UsrNodeFinAcctType> propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeFinAcctType>) filter2Config1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setItems(colCntnrFinAcctType);

        
        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "dataGridMain.[ts1.elTs]", subject = "formatter")
    private String dataGridMainTs1_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "dataGridMain.[ts2.elTs]", subject = "formatter")
    private String dataGridMainTs2_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
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


    @Override
    public UsrNodeFinBalSet onDuplicateBtnClickHelper(UsrNodeFinBalSet orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());


        if (tmplt_Type1_IdFieldChk.getValue()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        LocalDateTime ts1;
        if (tmplt_Ts1_ElTsFieldChk.getValue()) {
            ts1 = tmplt_Ts1_ElTsField.getValue();
            copy.getTs1().setElTs(ts1);
        }

        LocalDateTime ts2;
        if (tmplt_Ts2_ElTsFieldChk.getValue()) {
            ts2 = tmplt_Ts2_ElTsField.getValue();
            copy.getTs2().setElTs(ts2);
        }

        Integer sortIdx = copy.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null){
            switch (sortIdx){
                // Set
                case 1 ->{
                    sortIdx = tmplt_SortIdxField.getTypedValue();
                    copy.setSortIdx(sortIdx);
                }
                // Max+1
                case 2 ->{
                    UsrNodeBaseGrpg grpg = new UsrNodeBaseGrpg(copy.getParent1_Id());
                    Integer sortIdxMax = service.getSortIdxMax(this, grpg);
                    sortIdx = sortIdxMax == null ? null : sortIdxMax + 1;
                    if (sortIdx != null){
                        copy.setSortIdx(sortIdx);
                    };
                }
            }
        }

        if (tmplt_StatusFieldChk.getValue()) {
            copy.setSortIdx(tmplt_SortIdxField.getTypedValue());
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, copy, updOption);

        if (Objects.equals(copy.getId2(), orig.getId2())) {
            sortIdx = service.getSortIdxMax(this, copy);
            copy.setSortIdx(sortIdx);
            service.updateSortIdxDeps(this, copy, updOption);
        }

        logger.trace(logPrfx + " <-- ");
        return copy;
    }

    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinBalSet> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeFinBalSet> sels = new ArrayList<>();

        thisNodes.forEach(orig -> {
            UsrNodeFinBalSet copy = onDeriveBtnClickHelper(orig);

            UsrNodeFinBalSet savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Derived " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        //todo check how to dataGridMain.setSelected
        //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
        //dataGridMain.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }


    public UsrNodeFinBalSet onDeriveBtnClickHelper(UsrNodeFinBalSet orig){
        String logPrfx = "onDeriveBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.getValue()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        LocalDateTime ts1_ElTs;
        if (tmplt_Ts1_ElTsFieldChk.getValue()) {
            ts1_ElTs = tmplt_Ts1_ElTsField.getValue();
            copy.getTs1().setElTs(ts1_ElTs);
        }else{
            if (orig.getTs1().getElTs() != null) {
                ts1_ElTs = orig.getTs2().getElTs().plusDays(1);
                copy.getTs1().setElTs(ts1_ElTs);
            }
        }

        LocalDateTime ts2_ElTs;
        if (tmplt_Ts2_ElTsFieldChk.getValue()) {
            ts2_ElTs = tmplt_Ts2_ElTsField.getValue();
            copy.getTs2().setElTs(ts2_ElTs);
        }else{
            if (orig.getTs2().getElTs() != null) {
                ts2_ElTs = orig.getTs2().getElTs().plusMonths(1);
                copy.getTs2().setElTs(ts2_ElTs);
            }
        }

        Integer sortIdx = copy.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null){
            switch (sortIdx){
                // Set
                case 1 ->{
                    sortIdx = tmplt_SortIdxField.getTypedValue();
                    copy.setSortIdx(sortIdx);
                }
                // Max+1
                case 2 ->{
                    UsrNodeBaseGrpg grpg = new UsrNodeBaseGrpg(copy.getParent1_Id());
                    Integer sortIdxMax = service.getSortIdxMax(this, grpg);
                    sortIdx = sortIdxMax == null ? null : sortIdxMax + 1;
                    if (sortIdx != null){
                        copy.setSortIdx(sortIdx);
                    };
                }
            }
        }

        if (orig.getAmtEndBalCalc() != null) {
            copy.setAmtBegBal(orig.getAmtEndBalCalc());}

        if (tmplt_StatusFieldChk.getValue()) {
            copy.setSortIdx(tmplt_SortIdxField.getTypedValue());
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, copy, updOption);

        if (Objects.equals(copy.getId2(), orig.getId2())) {
            sortIdx = service.getSortIdxMax(this, copy);
            copy.setSortIdx(sortIdx);
            service.updateSortIdxDeps(this, copy, updOption);
        }

        logger.trace(logPrfx + " <-- ");
        return copy;
    }


    @Override
    public Boolean onSetBtnClickHelper(UsrNodeFinBalSet thisNode){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisNodeIsChanged = false;


        if (tmplt_Type1_IdFieldChk.getValue()
        ) {
            thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Ts1_ElTsFieldChk.getValue()) {
            thisNode.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Ts2_ElTsFieldChk.getValue()) {
            thisNode.getTs2().setElTs(tmplt_Ts2_ElTsField.getValue());
            thisNodeIsChanged = true;
        }

        Integer sortIdx = thisNode.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null){
            switch (sortIdx){
                // Set
                case 1 ->{
                    sortIdx = tmplt_SortIdxField.getTypedValue();
                    thisNode.setSortIdx(sortIdx);
                    thisNodeIsChanged = true;
                }
                // Max+1
                case 2 ->{
                    UsrNodeBaseGrpg grpg = new UsrNodeBaseGrpg(thisNode.getParent1_Id());
                    Integer sortIdxMax = service.getSortIdxMax(this, grpg);
                    sortIdx = sortIdxMax == null ? null : sortIdxMax + 1;
                    if (sortIdx != null){
                        thisNode.setSortIdx(sortIdx);
                        thisNodeIsChanged = true;
                    };
                }
            }
        }
        if (tmplt_StatusFieldChk.getValue()) {
            thisNode.setStatus(tmplt_StatusField.getValue());
            thisNodeIsChanged = true;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }

    @Subscribe("tmplt_StatusField")
    public void onTmplt_StatusFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_StatusFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinDept1_IdFieldListBtn")
    public void onUpdateFinDept1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinDept1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateFinBalSet1_IdFieldListBtn")
    public void onUpdateFinBalSet1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinBalSet1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finBalSet1sDl.load();
        logger.debug(logPrfx + " --- called finBalSet1sDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("ts2_ElTsField")
    public void onTs2_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onTs2_ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinBalSet thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateTs2Deps(this, thisNode, updOption);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("statusField")
    public void onStatusFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onStatusFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");
        
        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("loadTableFinBalBtn")
    public void onLoadTableFinBalBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onLoadTableFinBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        loadTableFinBal(thisNode);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("addSelTableFinAcctBtn")
    public void onAddSelTableFinAcctBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onAddSelTableFinAcctBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeFinAcct> thisFinAccts = new ArrayList<>(tableFinAcct.getSelectedItems().stream().toList());
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        addFinAccts(thisNode, thisFinAccts,0);

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("addAllTableFinAcctBtn")
    public void onAddAllTableFinAcctBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onAddAllTableFinAcctBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBalSet thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<UsrNodeFinAcct> thisFinAccts = tableFinAcct.getItems().getItems().stream().toList();
        if (thisFinAccts == null || thisFinAccts.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinAcct is null, likely because there are no records in the dataGridMain.");
            notifications.create("No records in the dataGridMain.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        addFinAccts(thisNode, thisFinAccts,1);

        logger.trace(logPrfx + " <-- ");

    }

    public Integer addFinAccts(@NotNull UsrNodeFinBalSet thisNode, List<UsrNodeFinAcct> thisFinAccts, int iOptions){
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
                    notifications.create("Account type " + finAcctType1_Id2 + " cannot be added to the set.").show();

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
                                .parameter("finBalSet1_Id",thisNode)
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
                            notifications.create("Account " + thisFinAcct.getId2() + " is already in the set.").show();
                        }
                    } else {
                        UsrNodeFinBal newFinBal = dataManager.create(UsrNodeFinBal.class);

                        newFinBal.setFinAcct1_Id(thisFinAcct);
                        newFinBal.setFinBalSet1_Id(thisNode);
                        if (thisNode.getFinDept1_Id() != null) {
                            newFinBal.setFinDept1_Id(thisNode.getFinDept1_Id());
                        }

                        if (thisNode.getType1_Id() != null) {
                            UsrNodeFinBalType type1 = null;
                            LogicalCondition logcCond = LogicalCondition.and();
                            String id2Criteria = thisNode.getType1_Id().getId2();
                            // todo check type string
                            logcCond.add(PropertyCondition.equal("id2",id2Criteria));
                            List<UsrNodeFinBalType> qryRsltNodeTypes = dataManager.load(UsrNodeFinBalType.class).condition(logcCond).list();

                            if (qryRsltNodeTypes.size() == 0){
                                logger.debug(logPrfx + " --- qryRsltNodes.size(): 0.");
                                logger.trace(logPrfx + " <-- ");

                            }else{
                                // Use the first candidate
                                type1 = qryRsltNodeTypes.get(0);

                            }
                            if (type1 != null) {
                                newFinBal.setType1_Id(type1);
                            }
                        }
                        newFinBal.setSysNodeFinCurcy1_Id(thisFinAcct.getSysNodeFinCurcy1_Id());

                        newFinBal.setSortKey("_AA" + thisNode.getSortKey() + thisFinAcct.getSortKey());

                        newFinBal.getTs1().setElTs(thisNode.getTs1().getElTs());
                        newFinBal.getTs2().setElTs(thisNode.getTs2().getElTs());
                        //TODO

                        newFinBal.setAmtBegBal(BigDecimal.ZERO);
                        newFinBal.setAmtDebt(BigDecimal.ZERO);
                        newFinBal.setAmtCred(BigDecimal.ZERO);
                        newFinBal.setAmtNet(BigDecimal.ZERO);
                        newFinBal.setAmtEndBal(BigDecimal.ZERO);

                        if (thisNode.getFinBalSet1_Id() != null) {
                            UsrNodeFinBal finBal1;
                            finBal1 = getFinBalByFinBalSet1_FinAcct1(thisNode.getFinBalSet1_Id(), thisFinAcct);
                            if (finBal1 != null) {
                                newFinBal.setFinBal1_Id(finBal1);
                            }
                        }

                        serviceFinBal.updateId2Calc(this, newFinBal, UpdateOption.LOCAL);
                        serviceFinBal.updateId2(this, newFinBal, UpdateOption.LOCAL);
                        serviceFinBal.updateId2Cmp(this, newFinBal, UpdateOption.LOCAL);
                        serviceFinBal.updateId2Dup(this, newFinBal, UpdateOption.LOCAL);

                        saveContext.saving(newFinBal);
                        isChanged.set(true);
                        iChanged.incrementAndGet();
                        notifications.create("Account " + thisFinAcct.getId2() + " added to the set.").show();
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

        String qry = "select e from enty_UsrNodeFinBal e"
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
                + " from enty_UsrNodeFinBalSet e"
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

        tmplt_StatusField.setItems(texts);
        logger.debug(logPrfx + " --- called tmplt_StatusField.setItems()");

        statusField.setItems(texts);
        logger.debug(logPrfx + " --- called statusField.setItems()");

        logger.trace(logPrfx + " <-- ");
    }

    public void loadTableFinBal(@NotNull UsrNodeFinBalSet thisNode) {
        String logPrfx = "loadTableFinBal";
        logger.trace(logPrfx + " --> ");

        //colCntnrFinBal = dataComponents.createCollectionContainer(UsrNodeFinBal.class);
        //colLoadrFinBal = dataComponents.createCollectionLoader();

        try{
            String finTxactItms1_Qry;
            finTxactItms1_Qry = "select e"
                    + " from enty_UsrNodeFinBal e"
                    + " where e.finBalSet1_Id = :finBalSet1_Id"
                    + "";
            colLoadrFinBal.setQuery(finTxactItms1_Qry);
            colLoadrFinBal.setParameter("finBalSet1_Id",thisNode);

/*
            FetchPlan fchPlnFinBal_Inst = fetchPlans.builder(UsrNodeFinBal.class)
                    .addFetchPlan("fetchPlan_UsrNodeFinBal_Base")
                    .build();
            colLoadrFinBal.setFetchPlan(fchPlnFinBal_Inst);
            colLoadrFinBal.setContainer(colCntnrFinBal);

            colLoadrFinBal.setDataContext(getViewData().getDataContext());

            tableFinBal.setItems(new ContainerTableItems<>(colCntnrFinBal));
*/

            logger.debug(logPrfx + " --- calling colLoadrFinBal.load() ");
            colLoadrFinBal.load();
            logger.debug(logPrfx + " --- called colLoadrFinBal.load() ");

//            logger.debug(logPrfx + " --- colCntnrFinBal returned rows: " + colCntnrFinBal.getItems().size());
//            pagetableFinBal.setDataBinder(...);


        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
        }

        logger.trace(logPrfx + " <-- ");
    }






}