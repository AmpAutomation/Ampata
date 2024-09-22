package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBal0Repo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Route(value = "usrNodeFinBals", layout = MainView.class)
@ViewController("enty_UsrNodeFinBal.main")
@ViewDescriptor("usr-node-fin-bal-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinBal0Main extends UsrNodeBase0BaseMain<UsrNodeFinBal, UsrNodeFinBalType, UsrNodeFinBal0Service, UsrNodeFinBal0Repo, DataGrid<UsrNodeFinBal>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBal.Service")
    public void setService(UsrNodeFinBal0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBal.Repo")
    public void setRepo(UsrNodeFinBal0Repo repo) { this.repo = repo; }

    @Autowired
    protected UsrNodeFinBalSet0Service serviceParent1;

    @Autowired
    protected UsrNodeFinBal0Service serviceFinBal;

    //Filter
    @ViewComponent
    protected PropertyFilter<UsrNodeFinBalSet> filterConfig1A_FinBalSet1_Id;

    @ViewComponent
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1A_FinAcct1_Id;

    @ViewComponent
    protected PropertyFilter<UsrNodeFinDept> filterConfig1A_FinDept1_Id;

    @ViewComponent
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_SysNodeFinCurcy1_Id;


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
    protected JmixComboBox<String> tmplt_StatusField;
    @ViewComponent
    protected JmixCheckbox tmplt_StatusFieldChk;


    //Other data containers, loaders and table
    private CollectionContainer<UsrNodeFinBalSet> colCntnrFinBalSet;
    private CollectionLoader<UsrNodeFinBalSet> colLoadrFinBalSet;


    private CollectionContainer<UsrNodeFinAcct> colCntnrFinAcct;
    private CollectionLoader<UsrNodeFinAcct> colLoadrFinAcct;


    private CollectionContainer<UsrNodeFinDept> colCntnrFinDept;
    private CollectionLoader<UsrNodeFinDept> colLoadrFinDept;


    private CollectionContainer<SysNodeFinCurcy> colCntnrSysNodeFinCurcy;
    private CollectionLoader<SysNodeFinCurcy> colLoadrSysNodeFinCurcy;


    @Autowired
    private EntityComboBox<UsrNodeFinBalSet> finBalSet1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinAcct> finAcct1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinDept> finDept1_IdField;

    @Autowired
    private EntityComboBox<SysNodeFinCurcy> sysNodeFinCurcy1_IdField;

    @Autowired
    private JmixComboBox<String> statusField;


    @Autowired
    private CollectionContainer<UsrNodeBase> colCntnrFinTxactItm;

    @Autowired
    private CollectionLoader<UsrNodeBase> colLoadrFinTxactItm;

    @Autowired
    private DataGrid<UsrNodeBase> tableFinTxactItm;


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        //tmplt_StatusField.setNullOptionVisible(true);
        //tmplt_StatusField.setNullSelectionCaption("<null>");

        colCntnrFinBalSet = dataComponents.createCollectionContainer(UsrNodeFinBalSet.class);
        colLoadrFinBalSet = dataComponents.createCollectionLoader();
        colLoadrFinBalSet.setQuery("select e from enty_UsrNodeFinBalSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinBalSet_Inst = fetchPlans.builder(UsrNodeFinBalSet.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinBalSet.setFetchPlan(fchPlnFinBalSet_Inst);
        colLoadrFinBalSet.setContainer(colCntnrFinBalSet);
        colLoadrFinBalSet.setDataContext(getViewData().getDataContext());

        finBalSet1_IdField.setItems(colCntnrFinBalSet);
        EntityComboBox<UsrNodeFinBalSet> propFilterCmpnt_FinBalSet1_Id = (EntityComboBox<UsrNodeFinBalSet>) filterConfig1A_FinBalSet1_Id.getValueComponent();
        propFilterCmpnt_FinBalSet1_Id.setItems(colCntnrFinBalSet);


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNodeFinAcct.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrNodeFinAcct e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcct_Inst = fetchPlans.builder(UsrNodeFinAcct.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(fchPlnFinAcct_Inst);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getViewData().getDataContext());

        finAcct1_IdField.setItems(colCntnrFinAcct);
        //filter
        EntityComboBox<UsrNodeFinAcct> propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNodeFinAcct>) filterConfig1A_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setItems(colCntnrFinAcct);


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


        colCntnrSysNodeFinCurcy = dataComponents.createCollectionContainer(SysNodeFinCurcy.class);
        colLoadrSysNodeFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysNodeFinCurcy.setQuery("select e from enty_SysNodeFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnSysNodeFinCurcy_Inst = fetchPlans.builder(SysNodeFinCurcy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysNodeFinCurcy.setFetchPlan(fchPlnSysNodeFinCurcy_Inst);
        colLoadrSysNodeFinCurcy.setContainer(colCntnrSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setDataContext(getViewData().getDataContext());

        sysNodeFinCurcy1_IdField.setItems(colCntnrSysNodeFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setItems(colCntnrSysNodeFinCurcy);
        
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
        
        colLoadrFinBalSet.load();
        logger.debug(logPrfx + " --- called colLoadrFinBalSet.load() ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- called colLoadrFinDept.load() ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        reloadStatusList();

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    public UsrNodeFinBal onDuplicateBtnClickHelper(UsrNodeFinBal orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBal copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.getValue()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_Ts1_ElTsFieldChk.getValue()) {
            copy.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
        }

        if (tmplt_Ts2_ElTsFieldChk.getValue()) {
            copy.getTs2().setElTs(tmplt_Ts2_ElTsField.getValue());
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

        List<UsrNodeFinBal> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeFinBal> sels = new ArrayList<>();

        thisNodes.forEach(orig -> {
            UsrNodeFinBal copy = onDeriveBtnClickHelper(orig);

            UsrNodeFinBal savedCopy = dataManager.save(copy);
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

    public UsrNodeFinBal onDeriveBtnClickHelper(UsrNodeFinBal orig){
        String logPrfx = "onDeriveBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBal copy = metadataTools.copy(orig);
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
                // Max
                case 2 ->{
                    sortIdx = service.getSortIdxMax(this, copy);
                    if (sortIdx != null){
                        copy.setSortIdx(sortIdx);
                    }
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
    public Boolean onSetBtnClickHelper(UsrNodeFinBal thisNode){
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

    @Subscribe("updateFinBalSet1_IdFieldListBtn")
    public void onUpdateFinBalSet1_IdFieldListBtn(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinBalSet1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinBalSet.load();
        logger.debug(logPrfx + " --- called colLoadrFinBalSet.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    //FinBal Field

    @Install(to = "finBal1_IdField.entityLookup", subject = "screenConfigurer")
    private void  finBal1_IdFieldLookupScreenConfigurer(View view) {
        String logPrfx = "finBal1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinBal thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(view instanceof UsrNodeFinBal0Lookup view2){

            Optional<UsrNodeFinAcct> o_finAcct1_Id = Optional.ofNullable(thisNode.getFinAcct1_Id());
            if (o_finAcct1_Id.isPresent()){
                view2.getFilterConfig1A_FinAcct1_Id().setValue(o_finAcct1_Id.get());
            }

            Optional<UsrNodeFinDept> o_finDept1_Id = Optional.ofNullable(thisNode.getFinDept1_Id());
            if (o_finDept1_Id.isPresent()){
                view2.getFilterConfig1A_FinDept1_Id().setValue(o_finDept1_Id.get());
            }else {
                //todo this was PropertyFilter.Operation.IS_NOT_SET however
                // IS_NOT_SET is not available in this review
                view2.getFilterConfig1A_FinDept1_Id().setOperation(PropertyFilter.Operation.IS_SET);
            }

            Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(n -> n.getElTs());
            if (o_ts1_ElTs.isPresent()){
                view2.getFilterConfig1A_Ts2_ElTs_GE().setValue(o_ts1_ElTs.get().minusMinutes(1));
                view2.getFilterConfig1A_Ts2_ElTs_LE().setValue(o_ts1_ElTs.get().minusMinutes(1));
            }

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinBal1_IdFieldBtn")
    public void onUpdateFinBal1_IdFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinBal1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");


        UsrNodeFinBal thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinBal1_Id(this, thisNode, updOption);


        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrFinAcct.load() ");

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
    
    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtBegBalBtn")
    public void onUpdateAmtBegBalBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtBegBal(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtBegBalBtn")
    public void onSetNullAmtBegBalBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetNullAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtBegBal_ = thisNode.getAmtBegBal();
        BigDecimal amtBegBal = null;

        if(Objects.equals(amtBegBal_, amtBegBal)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setAmtBegBal(amtBegBal);
            logger.debug(logPrfx + " --- thisNode.setAmtBegBal(amtBegBal)");
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtBegBalCalcBtn")
    public void onUpdateAmtBegBalCalcBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtBegBalCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtBegBalCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtDebtBtn")
    public void onUpdateAmtDebtBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtDebtBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtDebt(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtDebtBtn")
    public void onSetNullAmtDebtBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetNullAmtDebtBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtDebt_ = thisNode.getAmtDebt();
        BigDecimal amtDebt = null;

        if(Objects.equals(amtDebt_, amtDebt)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setAmtDebt(amtDebt);
            logger.debug(logPrfx + " --- thisNode.setAmtDebt(amtDebt)");
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtCredBtn")
    public void onUpdateAmtCredBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtCredBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtCred(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtCredBtn")
    public void onSetNullAmtCredBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetNullAmtCredBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtCred_ = thisNode.getAmtCred();
        BigDecimal amtCred = null;

        if(Objects.equals(amtCred_, amtCred)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setAmtCred(amtCred);
            logger.debug(logPrfx + " --- thisNode.setAmtCred(amtCred)");
        }

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateAmtNetBtn")
    public void onUpdateAmtNetBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtNetBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtNet(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("setNullAmtEndBalBtn")
    public void onSetNullAmtEndBalBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetNullAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        BigDecimal amtEndBal_ = thisNode.getAmtEndBal();
        BigDecimal amtEndBal = null;

        if(Objects.equals(amtEndBal_, amtEndBal)){
            logger.debug(logPrfx + " --- no change detected");
        }else{
            thisNode.setAmtEndBal(amtEndBal);
            logger.debug(logPrfx + " --- thisNode.setAmtEndBal(amtEndBal)");
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtEndBalBtn")
    public void onUpdateAmtEndBalBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtEndBal(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtEndBalCalcBtn")
    public void onUpdateAmtEndBalCalcBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtEndBalCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtEndBalCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("ts2_ElTsField")
    public void onTs2_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onTs2_ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinBal thisNode = instCntnrMain.getItemOrNull();
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


    @Subscribe("amtBegBalField")
    public void onAmtBegBalFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "amtBegBalField";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
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

        if (event.isFromClient()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateAmtBegBalCalcDeps(this, thisNode, updOption);
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


    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateAmtDebtDeps(this, thisNode, updOption);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("amtCredField")
    public void onAmtCredFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onAmtCredFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateAmtCredDeps(this, thisNode, updOption);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateInstItemManValsBtn")
    public void onUpdateInstItemManValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInstItemManValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateManVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
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

        tmplt_StatusField.setItems(texts);
        logger.debug(logPrfx + " --- called tmplt_StatusField.setItems()");

        statusField.setItems(texts);
        logger.debug(logPrfx + " --- called statusField.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtDebtSumCalc(this, thisNode, updOption);
        service.updateFinTxactItms1_AmtDebtSumCalcDeps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinTxactItms1_AmtDebtSumDiffFieldBtn")
    public void onUpdateFinTxactItms1_AmtDebtSumDiffFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "updateFinTxactItms1_AmtDebtSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtDebtSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtCredSumCalcFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtCredSumCalc(this, thisNode, updOption);
        service.updateFinTxactItms1_AmtCredSumCalcDeps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactItms1_AmtCredSumDiffFieldBtn")
    public void onUpdateFinTxactItms1_AmtCredSumDiffFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "updateFinTxactItms1_AmtCredSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtCredSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtNetSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtNetSumCalcFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtNetSumCalc(this, thisNode, updOption);
        service.updateFinTxactItms1_AmtNetSumCalcDeps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtNetSumDiffFieldBtn")
    public void onUpdateFinTxactItms1_AmtNetSumDiffFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "updateFinTxactItms1_AmtNetSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtNetSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateFinTxactItms1_IdCntCalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_IdCntCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtEqCalcBoxBtn")
    public void onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtEqCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("loadTableFinTxactItmBtn")
    public void onLoadTableFinTxactItmBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onLoadTableFinTxactItmBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        loadTableFinTxactItm(thisNode);

        logger.trace(logPrfx + " <-- ");
    }

    public void loadTableFinTxactItm(@NotNull UsrNodeBase thisNode) {
        String logPrfx = "loadTableFinTxactItm";
        logger.trace(logPrfx + " --> ");

        LogicalCondition cond = LogicalCondition.and();

        Optional<UsrNodeFinAcct> o_finAcct1_Id = Optional.ofNullable(thisNode.getFinAcct1_Id());
        if (o_finAcct1_Id.isEmpty()) {
            logger.debug(logPrfx + " --- o_finAcct1: is null.");
            notifications.create("o_finAcct1: is null.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        cond.add(PropertyCondition.equal("finAcct1_Id", thisNode.getFinAcct1_Id()));

        Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(n -> n.getElTs());
        if (o_ts1_ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- o_ts1_ElTs: is empty.");
            notifications.create("o_ts1_ElTs: is empty.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        cond.add(PropertyCondition.greaterOrEqual("ts1.elTs", o_ts1_ElTs.get()));

        Optional<LocalDateTime> o_ts2_ElTs = Optional.ofNullable(thisNode.getTs2()).map(n -> n.getElTs());
        if (o_ts2_ElTs.isEmpty()) {
            logger.debug(logPrfx + " --- o_ts2_ElTs: is empty.");
            notifications.create("o_ts2_ElTs: is empty.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        cond.add(PropertyCondition.lessOrEqual("ts1.elTs", o_ts2_ElTs.get()));

        LogicalCondition condFinAcct1 = LogicalCondition.or();

        Optional<UsrNodeFinDept> o_finDept1_Id = Optional.ofNullable(thisNode.getFinDept1_Id());
        if (o_finDept1_Id.isEmpty()){
            cond.add(PropertyCondition.isSet("finDept1_Id", false));
        }else{
            cond.add(PropertyCondition.equal("finDept1_Id", o_finDept1_Id.get()));
        }

        colLoadrFinTxactItm.setQuery("select e from enty_UsrNodeFinTxactItm e order by e.sortKey, e.id2");
        colLoadrFinTxactItm.setCondition(cond);

        logger.debug(logPrfx + " --- calling colLoadrFinTxactItm.load() ");
        colLoadrFinTxactItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }


}