package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinStmtItm0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinStmtItm0Service;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Route(value = "usrNodeFinStmtItms", layout = MainView.class)
@ViewController("enty_UsrNodeFinStmtItm.main")
@ViewDescriptor("usr-node-fin-stmt-itm-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinStmtItm0Main extends UsrNodeBase0BaseMain<UsrNodeFinStmtItm, UsrNodeFinStmtItmType, UsrNodeFinStmtItm0Service, UsrNodeFinStmtItm0Repo, DataGrid<UsrNodeFinStmtItm>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmtItm.Service")
    public void setService(UsrNodeFinStmtItm0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmtItm.Repo")
    public void setRepo(UsrNodeFinStmtItm0Repo repo) { this.repo = repo; }

    //Filter
    @Autowired
    protected PropertyFilter<String> filterConfig1A_Txt1;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Txt2;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Txt3;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Txt4;

    @Autowired
    protected PropertyFilter<UsrNodeFinStmt> filterConfig1A_FinStmt1_Id;

    //Toolbar


    //Template
    @Autowired
    protected TypedDateTimePicker<LocalDateTime> tmplt_Ts1_ElTsField;
    @Autowired
    protected JmixCheckbox tmplt_Ts1_ElTsFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinStmt> tmplt_FinStmt1_IdField;
    @Autowired
    protected JmixCheckbox tmplt_FinStmt1_IdFieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_Txt1Field;
    @Autowired
    protected JmixCheckbox tmplt_Txt1FieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_Txt2Field;
    @Autowired
    protected JmixCheckbox tmplt_Txt2FieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_Txt3Field;
    @Autowired
    protected JmixCheckbox tmplt_Txt3FieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_Txt4Field;
    @Autowired
    protected JmixCheckbox tmplt_Txt4FieldChk;

    @Autowired
    protected TypedTextField<String> tmplt_TxtCurcyExchField;
    @Autowired
    protected JmixCheckbox tmplt_TxtCurcyExchFieldChk;

    @Autowired
    protected TypedTextField<String> tmplt_TxtRefIdField;
    @Autowired
    protected JmixCheckbox tmplt_TxtRefIdFieldChk;

    @Autowired
    protected TypedTextField<BigDecimal> tmplt_AmtDebtField;
    @Autowired
    protected JmixCheckbox tmplt_AmtDebtFieldChk;

    @Autowired
    protected TypedTextField<BigDecimal> tmplt_AmtCredField;
    @Autowired
    protected JmixCheckbox tmplt_AmtCredFieldChk;




    //Other data loaders and containers
    private CollectionContainer<UsrNodeFinStmt> colCntnrFinStmt;
    private CollectionLoader<UsrNodeFinStmt> colLoadrFinStmt;

    @Autowired
    private CollectionContainer<UsrNodeFinTxactItm> colCntnrFinTxactItm1;
    @Autowired
    private CollectionLoader<UsrNodeFinTxactItm> colLoadrFinTxactItm1;


    //Field
    @Autowired
    private JmixComboBox<String> txt1Field;

    @Autowired
    private JmixComboBox<String> txt2Field;

    @Autowired
    private JmixComboBox<String> txt3Field;

    @Autowired
    private JmixComboBox<String> txt4Field;

    @Autowired
    private EntityComboBox<UsrNodeFinStmt> finStmt1_IdField;




    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);


        //filter
        JmixComboBox<String> propFilterCmpnt_Txt1 = (JmixComboBox<String>) filterConfig1A_Txt1.getValueComponent();
        //todo check how to refactor setNullOptionVisible
        //propFilterCmpnt_Txt1.setNullOptionVisible(true);
        //propFilterCmpnt_Txt1.setNullSelectionCaption("<null>");

        JmixComboBox<String> propFilterCmpnt_Txt2 = (JmixComboBox<String>) filterConfig1A_Txt2.getValueComponent();
        //propFilterCmpnt_Txt2.setNullOptionVisible(true);
        //propFilterCmpnt_Txt2.setNullSelectionCaption("<null>");

        JmixComboBox<String> propFilterCmpnt_Txt3 = (JmixComboBox<String>) filterConfig1A_Txt3.getValueComponent();
        //propFilterCmpnt_Txt3.setNullOptionVisible(true);
        //propFilterCmpnt_Txt3.setNullSelectionCaption("<null>");

        JmixComboBox<String> propFilterCmpnt_Txt4 = (JmixComboBox<String>) filterConfig1A_Txt4.getValueComponent();
        //propFilterCmpnt_Txt4.setNullOptionVisible(true);
        //propFilterCmpnt_Txt4.setNullSelectionCaption("<null>");

        //template
        Map<Integer, String> map1 = new LinkedHashMap<>();
        map1.put(0, "Skip");
        map1.put(2,"Next");
        map1.put(1, "");
        ComponentUtils.setItemsMap(tmplt_SortIdxFieldRdo, map1);

        //todo check how to refactor setNullOptionVisible
        //tmplt_Txt1Field.setNullOptionVisible(true);
        //tmplt_Txt1Field.setNullSelectionCaption("<null>");
        //tmplt_Txt2Field.setNullOptionVisible(true);
        //tmplt_Txt2Field.setNullSelectionCaption("<null>");
        //tmplt_Txt3Field.setNullOptionVisible(true);
        //tmplt_Txt3Field.setNullSelectionCaption("<null>");
        //tmplt_Txt4Field.setNullOptionVisible(true);
        //tmplt_Txt4Field.setNullSelectionCaption("<null>");


        //todo check how to refactor setNullOptionVisible
        //txt1Field.setNullOptionVisible(true);
        //txt1Field.setNullSelectionCaption("<null>");
        //txt2Field.setNullOptionVisible(true);
        //txt2Field.setNullSelectionCaption("<null>");
        //txt3Field.setNullOptionVisible(true);
        //txt3Field.setNullSelectionCaption("<null>");
        //txt4Field.setNullOptionVisible(true);
        //txt4Field.setNullSelectionCaption("<null>");



        colCntnrFinStmt = dataComponents.createCollectionContainer(UsrNodeFinStmt.class);
        colLoadrFinStmt = dataComponents.createCollectionLoader();
        colLoadrFinStmt.setQuery("select e from enty_UsrNodeFinStmt e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinStmt_Inst = fetchPlans.builder(UsrNodeFinStmt.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinStmt.setFetchPlan(fchPlnFinStmt_Inst);
        colLoadrFinStmt.setContainer(colCntnrFinStmt);
        colLoadrFinStmt.setDataContext(getViewData().getDataContext());

        finStmt1_IdField.setItems(colCntnrFinStmt);
        //template
        tmplt_FinStmt1_IdField.setItems(colCntnrFinStmt);
        //filter
        EntityComboBox<UsrNodeFinStmt> propFilterCmpnt_FinStmt1_Id = (EntityComboBox<UsrNodeFinStmt>) filterConfig1A_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_FinStmt1_Id.setItems(colCntnrFinStmt);

        logger.trace(logPrfx + " <-- ");


    }

    @Install(to = "dataGridMain.[ts1.elTs]", subject = "formatter")
    private String dataGridMainTs1_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrFinStmt.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmt.load() ");

        reloadTxt1List();
        reloadTxt2List();
        reloadTxt3List();
        reloadTxt4List();

        logger.trace(logPrfx + " <-- ");

    }


    @Override
    public UsrNodeFinStmtItm onDuplicateBtnClickHelper(UsrNodeFinStmtItm orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmtItm copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.getValue()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_Ts1_ElTsFieldChk.getValue()) {
            copy.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
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

        if (tmplt_FinStmt1_IdFieldChk.getValue()) {
            copy.setFinStmt1_Id(tmplt_FinStmt1_IdField.getValue());
        }

        if (tmplt_Txt1FieldChk.getValue()) {
            copy.setTxt1(tmplt_Txt1Field.getValue());
        }

        if (tmplt_Txt2FieldChk.getValue()) {
            copy.setTxt2(tmplt_Txt2Field.getValue());
        }

        if (tmplt_Txt3FieldChk.getValue()) {
            copy.setTxt3(tmplt_Txt3Field.getValue());
        }

        if (tmplt_Txt4FieldChk.getValue()) {
            copy.setTxt4(tmplt_Txt4Field.getValue());
        }

        if (tmplt_TxtCurcyExchFieldChk.getValue()) {
            copy.setTxtCurcyExch(tmplt_TxtCurcyExchField.getValue());
        }

        if (tmplt_TxtRefIdFieldChk.getValue()) {
            copy.setTxtRefId(tmplt_TxtRefIdField.getValue());
        }

        if (tmplt_AmtDebtFieldChk.getValue()) {
            copy.setAmtDebt(tmplt_AmtDebtField.getTypedValue());
        }

        if (tmplt_AmtCredFieldChk.getValue()) {
            copy.setAmtCred(tmplt_AmtCredField.getTypedValue());
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
    public Boolean onSetBtnClickHelper(UsrNodeFinStmtItm thisNode){
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

        Integer sortIdx = thisNode.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null){
            switch (sortIdx){
                // Set
                case 1 ->{
                    sortIdx = tmplt_SortIdxField.getTypedValue();
                    thisNode.setSortIdx(sortIdx);
                    thisNodeIsChanged = true;
                }
                // Max
                case 2 ->{
                    sortIdx = service.getSortIdxMax(this, thisNode);
                    if (sortIdx != null){
                        thisNode.setSortIdx(sortIdx);
                    };
                    thisNodeIsChanged = true;
                }
            }
        }

        if (tmplt_FinStmt1_IdFieldChk.getValue()) {
            thisNode.setFinStmt1_Id(tmplt_FinStmt1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Txt1FieldChk.getValue()) {
            thisNode.setTxt1(tmplt_Txt1Field.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Txt2FieldChk.getValue()) {
            thisNode.setTxt2(tmplt_Txt2Field.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Txt3FieldChk.getValue()) {
            thisNode.setTxt3(tmplt_Txt3Field.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Txt4FieldChk.getValue()) {
            thisNode.setTxt3(tmplt_Txt4Field.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_TxtCurcyExchFieldChk.getValue()) {
            thisNode.setTxtCurcyExch(tmplt_TxtCurcyExchField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_TxtRefIdFieldChk.getValue()) {
            thisNode.setTxtRefId(tmplt_TxtRefIdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_AmtDebtFieldChk.getValue()) {
            thisNode.setAmtDebt(tmplt_AmtDebtField.getTypedValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_AmtCredFieldChk.getValue()) {
            thisNode.setAmtCred(tmplt_AmtCredField.getTypedValue());
            thisNodeIsChanged = true;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }

    @Subscribe("tmplt_Txt1Field")
    public void onTmplt_Txt1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Txt1FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("tmplt_Txt2Field")
    public void onTmplt_Txt2FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Txt2FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("tmplt_Txt3Field")
    public void onTmplt_Txt3FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Txt3FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("tmplt_Txt4Field")
    public void onTmplt_Txt4FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Txt4FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("ts1_ElTsField")
    public void onTs1_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1_ElTsFieldValueChange";
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
            service.updateTs1Deps(this, thisNode, updOption);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("extractTs1_ElTsFieldBtn")
    public void onUpdateTs1_ElTsFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTs1_ElTsFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateTs1FrId2(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("ts2_ElTsField")
    public void onTs2_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs2_ElTsFieldValueChange";
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
            service.updateTs2Deps(this, thisNode, updOption);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("extractTs2_ElTsFieldBtn")
    public void onUpdateTs2_ElTsFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTs2_ElTsFieldBtn";
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
        service.updateTs2FrId2(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("txt1Field")
    public void onTxt1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTxt1FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTxt1FieldListBtn")
    public void onUpdateTxt1FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTxt1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadTxt1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("txt2Field")
    public void onTxt2FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTxt2FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTxt2FieldListBtn")
    public void onUpdateTxt2FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTxt2FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadTxt2List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("txt3Field")
    public void onTxt3FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTxt3FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTxt3FieldListBtn")
    public void onUpdateTxt3FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTxt3FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadTxt3List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("txt4Field")
    public void onTxt4FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTxt4FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateTxt4FieldListBtn")
    public void onUpdateTxt4FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTxt4FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadTxt3List();

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinStmt1_IdFieldListBtn")
    public void onUpdateFinStmt1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinStmt1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinStmt.load();
        logger.debug(logPrfx + " --- called colLoadrFinStmt.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("amtBegBalField")
    public void onAmtBegBalFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "amtBegBalField";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeBase thisFinStmt = instCntnrMain.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
        }

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("setNullAmtBegBalBtn")
    public void onSetNullAmtBegBalBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetNullAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmtItm thisNode = instCntnrMain.getItemOrNull();
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


    @Subscribe("updateAmtBegBalBtn")
    public void onUpdateAmtBegBalBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisFinStmt = instCntnrMain.getItemOrNull();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtBegBal(this, thisFinStmt, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("amtBegBalCalcField")
    public void onAmtBegBalCalcFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "amtBegBalCalcField";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeBase thisFinStmt = instCntnrMain.getItemOrNull();
            if (thisFinStmt == null) {
                logger.debug(logPrfx + " --- thisFinStmt is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateAmtBegBalCalcDeps(this, thisFinStmt, updOption);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
    public void onAmtCredFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtCredFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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

    @Subscribe("updateAmtNetBtn")
    public void onUpdateAmtNetBtn(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtNetBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtNet(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }
    

    @Subscribe("updateFinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtn(ClickEvent<Button> event) {
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
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
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtEqCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadTxt1List(){
        String logPrfx = "reloadTxt1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt1"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt1 is not null"
                + " order by e.txt1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        JmixComboBox<String> propFilterCmpnt_Txt1 = (JmixComboBox<String>) filterConfig1A_Txt1.getValueComponent();
        propFilterCmpnt_Txt1.setItems(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Txt1.setItems()");

        tmplt_Txt1Field.setItems(descs);
        logger.debug(logPrfx + " --- called tmplt_Txt1Field.setItems()");

        txt1Field.setItems(descs);
        logger.debug(logPrfx + " --- called txt1Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadTxt2List(){
        String logPrfx = "reloadTxt2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt2"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt2 is not null"
                + " order by e.txt2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        JmixComboBox<String> propFilterCmpnt_Txt2 = (JmixComboBox<String>) filterConfig1A_Txt2.getValueComponent();
        propFilterCmpnt_Txt2.setItems(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Txt2.setItems()");

        tmplt_Txt2Field.setItems(descs);
        logger.debug(logPrfx + " --- called tmplt_Txt2Field.setItems()");

        txt2Field.setItems(descs);
        logger.debug(logPrfx + " --- called txt2Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadTxt3List(){
        String logPrfx = "reloadTxt3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt3"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt3 is not null"
                + " order by e.txt3"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        JmixComboBox<String> propFilterCmpnt_Txt3 = (JmixComboBox<String>) filterConfig1A_Txt3.getValueComponent();
        propFilterCmpnt_Txt3.setItems(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Txt3.setItems()");

        tmplt_Txt3Field.setItems(descs);
        logger.debug(logPrfx + " --- called tmplt_Txt3Field.setItems()");

        txt3Field.setItems(descs);
        logger.debug(logPrfx + " --- called txt3Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadTxt4List(){
        String logPrfx = "reloadTxt4List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt4"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt4 is not null"
                + " order by e.txt4"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        ComboBox<String> propFilterCmpnt_Txt4 = (ComboBox<String>) filterConfig1A_Txt4.getValueComponent();
        propFilterCmpnt_Txt4.setItems(descs);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Txt4.setItems()");

        tmplt_Txt4Field.setItems(descs);
        logger.debug(logPrfx + " --- called tmplt_Txt4Field.setItems()");

        txt4Field.setItems(descs);
        logger.debug(logPrfx + " --- called txt4Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    protected Map<?, List<UsrNodeFinStmtItm>> getGrpgMap(List<UsrNodeFinStmtItm> thisNodes){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrNodeFinStmtItmGrpg, List<UsrNodeFinStmtItm>> grpMap = thisNodes.stream()
                .collect(groupingBy(node ->
                        new UsrNodeFinStmtItmGrpg(node.getParent1_Id()
                                ,node.getTs1() == null ? null : node.getTs1().getElTs())));

        logger.trace(logPrfx + " <-- ");
        return grpMap;
    }

    @Override
    protected Condition getConditionGrpg(Object grpgKey){
        String logPrfx = "getGrpgCondition";
        logger.trace(logPrfx + " --> ");

        // Ensure grpgKey is instanceof UsrNodeFinStmtItmGrpg
        if (!(grpgKey instanceof UsrNodeFinStmtItmGrpg l_grpgKey)){
            logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeFinStmtItmGrpg");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        HasTmst ts1 = dataManager.create(HasTmst.class);
        ts1.setElTs(l_grpgKey.ts1());

        LogicalCondition cond = LogicalCondition.and();
        //Add condition for this group
        cond.add(PropertyCondition.equal("parent1_Id",l_grpgKey.parent1_Id()));
        cond.add(PropertyCondition.equal("ts1",ts1));

        logger.trace(logPrfx + " <-- ");
        return cond;
    }


    @Subscribe("loadTableBtnFinTxactItm1")
    public void onLoadTableBtnFinTxactItm1Click(ClickEvent<Button> event) {
        String logPrfx = "onLoadTableBtnFinTxactItm1Click";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        LogicalCondition cond1 = LogicalCondition.or();

        LogicalCondition cond1A = LogicalCondition.and();
        cond1A.add(PropertyCondition.equal("finStmtItm1_Id", thisNode));

        cond1.add(cond1A);

        colLoadrFinTxactItm1.setQuery("select e from enty_UsrNodeFinTxactItm e order by e.sortKey, e.id2");
        colLoadrFinTxactItm1.setCondition(cond1);

        logger.debug(logPrfx + " --- calling colLoadrFinTxactItm.load() ");
        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("unloadTableBtnFinTxactItm1")
    public void onUnloadTableBtnFinTxactItm1Click(ClickEvent<Button> event) {
        String logPrfx = "onUnloadTableBtnFinTxactItm1Click";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        colLoadrFinTxactItm1.setQuery("select e from enty_UsrNodeFinTxactItm e where e.id2 is null order by e.sortKey, e.id2");

        logger.debug(logPrfx + " --- calling colLoadrFinTxactItm.load() ");
        colLoadrFinTxactItm1.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig_FinTxactItm1_1B_FinStmtItm1_Id_This")
    public void onUpdateFilterConfig_FinTxactItm1_1B_FinStmtItm1_Id_ThisClick(ClickEvent<Button> event) {
        String logPrfx = "loadTableFinTxactItm";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


}