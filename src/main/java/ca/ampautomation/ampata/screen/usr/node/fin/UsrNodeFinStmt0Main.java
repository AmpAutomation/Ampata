package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinStmt0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinStmt0Service;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@UiController("enty_UsrNodeFinStmt.main")
@UiDescriptor("usr-node-fin-stmt-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinStmt0Main extends UsrNodeBase0BaseMain<UsrNodeFinStmt, UsrNodeFinStmtType, UsrNodeFinStmt0Service, UsrNodeFinStmt0Repo, Table<UsrNodeFinStmt>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmt.Service")
    public void setService(UsrNodeFinStmt0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmt.Repo")
    public void setRepo(UsrNodeFinStmt0Repo repo) { this.repo = repo; }

    @Autowired
    protected UsrNodeFinStmt0Service serviceFinStmtItm;

    //Filter
    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1A_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1A_FinAcct1_Id;

    //Toolbar

    //Template
    @Autowired
    protected CheckBox tmplt_Ts1_ElTsFieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1_ElTsField;

    @Autowired
    protected CheckBox tmplt_Ts2_ElTsFieldChk;
    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts2_ElTsField;

    @Autowired
    protected ComboBox<String> tmplt_StatusField;
    @Autowired
    protected CheckBox tmplt_StatusFieldChk;



    //Other data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNodeFinStmtItm> colCntnrFinStmtItm;
    @Autowired
    private CollectionLoader<UsrNodeFinStmtItm> colLoadrFinStmtItm;
    @Autowired
    private Table<UsrNodeFinStmtItm> tableFinStmtItm;


    private CollectionContainer<UsrNodeGenChan> colCntnrGenChan;
    private CollectionLoader<UsrNodeGenChan> colLoadrGenChan;

    private CollectionContainer<UsrNodeFinAcct> colCntnrFinAcct;
    private CollectionLoader<UsrNodeFinAcct> colLoadrFinAcct;

    
    //Field
    @Autowired
    private EntityComboBox<UsrNodeGenChan> genChan1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinAcct> finAcct1_IdField;

    @Autowired
    private ComboBox<String> statusField;


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        tmplt_StatusField.setNullOptionVisible(true);
        tmplt_StatusField.setNullSelectionCaption("<null>");


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrNodeGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrNodeGenChan e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenChan_Inst = fetchPlans.builder(UsrNodeGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(fchPlnGenChan_Inst);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(colCntnrGenChan);
        //filter
        EntityComboBox<UsrNodeGenChan> propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1A_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNodeFinAcct.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrNodeFinAcct e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcct_Inst = fetchPlans.builder(UsrNodeFinAcct.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(fchPlnFinAcct_Inst);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getScreenData().getDataContext());

        finAcct1_IdField.setOptionsContainer(colCntnrFinAcct);
        //filter
        EntityComboBox<UsrNodeFinAcct> propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNodeFinAcct>) filterConfig1A_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setOptionsContainer(colCntnrFinAcct);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tableMain.[ts1.elTs]", subject = "formatter")
    private String tableMainTs1_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }

    @Install(to = "tableMain.[ts2.elTs]", subject = "formatter")
    private String tableMainTs2_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null: ts.format(formatter);
    }
    

    @Install(to = "tableFinStmtItm.[ts1.elTs]", subject = "formatter")
    private String tableFinStmtItmTs1_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }


    @Override
    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<UsrNodeFinStmt> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmt thisNode = event.getItem();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");
            //todo I observed thisNode is null when selecting a new item
            colLoadrFinStmtItm.removeParameter("finStmt1_Id");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        colLoadrFinStmtItm.setParameter("finStmt1_Id", thisNode.getFinStmt1_Id());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        reloadStatusList();

        logger.trace(logPrfx + " <-- ");

    }


    @Override
    public UsrNodeFinStmt onDuplicateBtnClickHelper(UsrNodeFinStmt orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmt copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.isChecked()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_Ts1_ElTsFieldChk.isChecked()) {
            copy.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
        }

        if (tmplt_Ts2_ElTsFieldChk.isChecked()) {
            copy.getTs2().setElTs(tmplt_Ts2_ElTsField.getValue());
        }

        Integer sortIdx = copy.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null){
            switch (sortIdx){
                // Set
                case 1 ->{
                    sortIdx = tmplt_SortIdxField.getValue();
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

        if (tmplt_StatusFieldChk.isChecked()) {
            copy.setSortIdx(tmplt_SortIdxField.getValue());
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
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinStmt> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeFinStmt> sels = new ArrayList<>();

        thisNodes.forEach(orig -> {
           UsrNodeFinStmt copy = onDeriveBtnClickHelper(orig);

            UsrNodeFinStmt savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Derived " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });
        //tableMain.sort("id2", Table.SortDirection.ASCENDING);
        tableMain.setSelected(sels);
        
        logger.trace(logPrfx + " <-- ");
    }

    public UsrNodeFinStmt onDeriveBtnClickHelper(UsrNodeFinStmt orig){
        String logPrfx = "onDeriveBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmt copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.isChecked()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        LocalDateTime ts1_ElTs;
        if (tmplt_Ts1_ElTsFieldChk.isChecked()) {
            ts1_ElTs = tmplt_Ts1_ElTsField.getValue();
            copy.getTs1().setElTs(ts1_ElTs);
        }else{
            if (orig.getTs1().getElTs() != null) {
                ts1_ElTs = orig.getTs2().getElTs().plusDays(1);
                copy.getTs1().setElTs(ts1_ElTs);
            }
        }

        LocalDateTime ts2_ElTs;
        if (tmplt_Ts2_ElTsFieldChk.isChecked()) {
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
                    sortIdx = tmplt_SortIdxField.getValue();
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

        if (tmplt_StatusFieldChk.isChecked()) {
            copy.setSortIdx(tmplt_SortIdxField.getValue());
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
    public Boolean onSetBtnClickHelper(UsrNodeFinStmt thisNode){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisNodeIsChanged = false;


        if (tmplt_Type1_IdFieldChk.isChecked()
        ) {
            thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Ts1_ElTsFieldChk.isChecked()) {
            thisNode.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Ts2_ElTsFieldChk.isChecked()) {
            thisNode.getTs2().setElTs(tmplt_Ts2_ElTsField.getValue());
            thisNodeIsChanged = true;
        }

        Integer sortIdx = thisNode.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null){
            switch (sortIdx){
                // Set
                case 1 ->{
                    sortIdx = tmplt_SortIdxField.getValue();
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
        if (tmplt_StatusFieldChk.isChecked()) {
            thisNode.setStatus(tmplt_StatusField.getValue());
            thisNodeIsChanged = true;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }


    @Subscribe("ts2_ElTsField")
    public void onTs2_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onTs2_ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateTs2Deps(this, thisNode, updOption);
        }

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


    //FinStmt Field

    @Install(to = "finStmt1_IdField.entityLookup", subject = "screenConfigurer")
    private void  finStmt1_IdFieldLookupScreenConfigurer(Screen screen) {
        String logPrfx = "finStmt1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinStmt thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(screen instanceof UsrNodeFinStmt0Lookup scrn){

            Optional<UsrNodeFinAcct> o_finAcct1_Id = Optional.ofNullable(thisNode.getFinAcct1_Id());
            if (o_finAcct1_Id.isPresent()){
                scrn.getFilterConfig1A_FinAcct1_Id().setValue(o_finAcct1_Id.get());
            }

            Optional<LocalDateTime> o_ts1_ElTs = Optional.ofNullable(thisNode.getTs1()).map(n -> n.getElTs());
            if (o_ts1_ElTs.isPresent()){
                scrn.getFilterConfig1A_Ts2_ElTs_GE().setValue(o_ts1_ElTs.get().minusDays(1));
                scrn.getFilterConfig1A_Ts2_ElTs_LE().setValue(o_ts1_ElTs.get().minusDays(1));
            }

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmt1_IdFieldBtn")
    public void onUpdateFinStmt1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmt1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");


        UsrNodeFinStmt thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmt1_Id(this, thisNode, updOption);


        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setNullAmtBegBalBtn")
    public void onSetNullAmtBegBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
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
    public void onUpdateAmtBegBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtBegBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtBegBal(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("amtBegBalField")
    public void onAmtBegBalFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "amtBegBalField";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
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
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateAmtBegBalCalcDeps(this, thisNode, updOption);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateAmtBegBalCalcBtn")
    public void onUpdateAmtBegBalCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtBegBalCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtBegBalCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
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

        if (event.isUserOriginated()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
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
    public void onUpdateAmtNetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtNetBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtNet(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("setNullAmtEndBalBtn")
    public void onSetNullAmtEndBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetNullAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
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
    public void onUpdateAmtEndBalBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtEndBalBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtEndBal(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmtEndBalCalcBtn")
    public void onUpdateAmtEndBalCalcBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtEndBalCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtEndBalCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_StatusField", subject = "enterPressHandler")
    private void tmplt_StatusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_StatusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "statusField", subject = "enterPressHandler")
    private void statusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "statusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }



    private void reloadStatusList(){
        String logPrfx = "reloadStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status"
                + " from enty_UsrNodeFinStmt e"
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



    @Subscribe("updateFinStmtItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFinStmtItms1_AmtDebtSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinStmtItms1_AmtDebtSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_AmtDebtSumCalc(this, thisNode, updOption);
        service.updateFinStmtItms1_AmtDebtSumCalcDeps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinStmtItms1_AmtDebtSumDiffFieldBtn")
    public void onUpdateFinStmtItms1_AmtDebtSumDiffFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinStmtItms1_AmtDebtSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_AmtDebtSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateFinStmtItms1_AmtCredSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinStmtItms1_AmtCredSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_AmtCredSumCalc(this, thisNode, updOption);
        service.updateFinStmtItms1_AmtCredSumCalcDeps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinStmtItms1_AmtCredSumDiffFieldBtn")
    public void onUpdateFinStmtItms1_AmtCredSumDiffFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinStmtItms1_AmtCredSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_AmtCredSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItms1_AmtNetSumCalcFieldBtn")
    public void onUpdateFinStmtItms1_AmtNetSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinStmtItms1_AmtNetSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_AmtNetSumCalc(this, thisNode, updOption);
        service.updateFinStmtItms1_AmtNetSumCalcDeps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItms1_AmtNetSumDiffFieldBtn")
    public void onUpdateFinStmtItms1_AmtNetSumDiffFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinStmtItms1_AmtNetSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_AmtNetSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItms1_IdCntCalcFieldBtn")
    public void onUpdateFinStmtItms1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_IdCntCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItms1_AmtEqCalcBoxBtn")
    public void onUpdateFinStmtItms1_AmtEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmtItms1_AmtEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItms1_AmtEqCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }




    @Subscribe("updateFinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtDebtSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
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
    public void onUpdateFinTxactItms1_AmtDebtSumDiffFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtDebtSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtDebtSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtCredSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtCredSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
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
    public void onUpdateFinTxactItms1_AmtCredSumDiffFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtCredSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtCredSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtNetSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtNetSumCalcFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtNetSumCalcFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
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
    public void onUpdateFinTxactItms1_AmtNetSumDiffFieldBtn(Button.ClickEvent event) {
        String logPrfx = "updateFinTxactItms1_AmtNetSumDiffFieldBtn";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtNetSumDiff(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateFinTxactItms1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_IdCntCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtEqCalcBoxBtn")
    public void onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxactItms1_AmtEqCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }





}