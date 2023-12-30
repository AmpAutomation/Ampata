package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@UiController("enty_UsrNodeFinTxactSet.main")
@UiDescriptor("usr-node-fin-txact-set-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinTxactSet0Main extends UsrNodeBase0BaseMain<UsrNodeFinTxactSet, UsrNodeFinTxactSetType, UsrNodeFinTxactSet0Service, UsrNodeFinTxactSet0Repo, Table<UsrNodeFinTxactSet>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet.Service")
    public void setService(UsrNodeFinTxactSet0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet.Repo")
    public void setRepo(UsrNodeFinTxactSet0Repo repo) { this.repo = repo; }

    //Filter
    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1A_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1A_GenChan2_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1A_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1A_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1A_FinWhy1_Id;


    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_Ts1_ElTs_LE;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_SortIdx;


    //Template
    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1_ElTsField;
    @Autowired
    protected CheckBox tmplt_Ts1_ElTsFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeGenChan> tmplt_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeGenChan> tmplt_GenChan2_IdField;

    @Autowired
    protected CheckBox tmplt_GenChan2_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinHow> tmplt_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhat> tmplt_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhy> tmplt_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_FinWhy1_IdFieldChk;


    //Toolbar
    @Autowired
    protected CheckBox updateFilterConfig1A_Ts1_ElTs_LE_SyncChk;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_Ts1_ElTs_GERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_SortIdxRdo;


    //Other data containers, loaders and table
    private CollectionContainer<UsrNodeGenChan> colCntnrGenChan;
    private CollectionLoader<UsrNodeGenChan> colLoadrGenChan;

    private CollectionContainer<UsrItemFinHow> colCntnrFinHow;
    private CollectionLoader<UsrItemFinHow> colLoadrFinHow;

    private CollectionContainer<UsrItemFinWhat> colCntnrFinWhat;
    private CollectionLoader<UsrItemFinWhat> colLoadrFinWhat;

    private CollectionContainer<UsrItemFinWhy> colCntnrFinWhy;
    private CollectionLoader<UsrItemFinWhy> colLoadrFinWhy;


    //Field
    @Autowired
    private EntityComboBox<UsrNodeGenChan> genChan1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenChan> genChan2_IdField;

    @Autowired
    private EntityComboBox<UsrItemFinHow>  finHow1_IdField;

    @Autowired
    private ComboBox<String> whatText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhat>  finWhat1_IdField;

    @Autowired
    private ComboBox<String> whyText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhy>  finWhy1_IdField;



    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);
        
        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_SortIdxFieldRdo.setOptionsMap(map1);

        Map<String, Integer> map3 = new LinkedHashMap<>();
        map3.put("Clear", 0);
        map3.put("Match Current Row", 1);
        updateFilterConfig1B_Ts1_ElTs_GERdo.setOptionsMap(map3);
        updateFilterConfig1B_SortIdxRdo.setOptionsMap(map3);


        //colCntnrGenChan
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
        genChan2_IdField.setOptionsContainer(colCntnrGenChan);
        EntityComboBox<UsrNodeGenChan> propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1A_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);
        EntityComboBox<UsrNodeGenChan> propFilterCmpnt_GenChan2_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1A_GenChan2_Id.getValueComponent();
        propFilterCmpnt_GenChan2_Id.setOptionsContainer(colCntnrGenChan);


        //colCntnrFinHow
        colCntnrFinHow = dataComponents.createCollectionContainer(UsrItemFinHow.class);
        colLoadrFinHow = dataComponents.createCollectionLoader();
        colLoadrFinHow.setQuery("select e from enty_UsrItemFinHow e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinHow_Inst = fetchPlans.builder(UsrItemFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinHow.setFetchPlan(fchPlnFinHow_Inst);
        colLoadrFinHow.setContainer(colCntnrFinHow);
        colLoadrFinHow.setDataContext(getScreenData().getDataContext());

        finHow1_IdField.setOptionsContainer(colCntnrFinHow);


        //colCntnrFinHow
        colCntnrFinWhat = dataComponents.createCollectionContainer(UsrItemFinWhat.class);
        colLoadrFinWhat = dataComponents.createCollectionLoader();
        colLoadrFinWhat.setQuery("select e from enty_UsrItemFinWhat e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhat_Inst = fetchPlans.builder(UsrItemFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhat.setFetchPlan(fchPlnFinWhat_Inst);
        colLoadrFinWhat.setContainer(colCntnrFinWhat);
        colLoadrFinWhat.setDataContext(getScreenData().getDataContext());

        finWhat1_IdField.setOptionsContainer(colCntnrFinWhat);


        //colCntnrFinHow
        colCntnrFinWhy = dataComponents.createCollectionContainer(UsrItemFinWhy.class);
        colLoadrFinWhy = dataComponents.createCollectionLoader();
        colLoadrFinWhy.setQuery("select e from enty_UsrItemFinWhy e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhy_Inst = fetchPlans.builder(UsrItemFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhy.setFetchPlan(fchPlnFinWhy_Inst);
        colLoadrFinWhy.setContainer(colCntnrFinWhy);
        colLoadrFinWhy.setDataContext(getScreenData().getDataContext());

        finWhy1_IdField.setOptionsContainer(colCntnrFinWhy);


        logger.trace(logPrfx + " <-- ");


    }

    @Install(to = "tableMain.[ts1.elTs]", subject = "formatter")
    private String tableMainTs1_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

        reloadWhatText1List();
        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

        reloadWhyText1List();
        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");


        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("deleteColOrphansBtn")
    public void onDeleteColOrphansBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeleteColOrphansBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPr_Del_ForOrphanedRows");
        repo.execPr_Del_ForOrphanedRows();
        logger.debug(logPrfx + " --- finished repo.execPr_Del_ForOrphanedRows");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    public UsrNodeFinTxactSet onDuplicateBtnClickHelper(UsrNodeFinTxactSet orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactSet copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.isChecked()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_Ts1_ElTsFieldChk.isChecked()) {
            copy.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
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
    public Boolean onSetBtnClickHelper(UsrNodeFinTxactSet thisNode){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        boolean thisNodeIsChanged = false;

        if (tmplt_Type1_IdFieldChk.isChecked()
        ) {
            thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_Ts1_ElTsFieldChk.isChecked()) {
            thisNode.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
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

        if (tmplt_GenChan1_IdFieldChk.isChecked() ) {
            thisNode.setGenChan1_Id(tmplt_GenChan1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_GenChan2_IdFieldChk.isChecked()) {
            thisNode.setGenChan2_Id(tmplt_GenChan2_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_FinHow1_IdFieldChk.isChecked()) {
            thisNode.setFinHow1_Id(tmplt_FinHow1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_WhatText1FieldChk.isChecked()) {
            thisNode.setWhatText1(tmplt_WhatText1Field.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_FinWhat1_IdFieldChk.isChecked()) {
            thisNode.setFinWhat1_Id(tmplt_FinWhat1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_WhyText1FieldChk.isChecked()) {
            thisNode.setWhyText1(tmplt_WhyText1Field.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_FinWhy1_IdFieldChk.isChecked()) {
            thisNode.setFinWhy1_Id(tmplt_FinWhy1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }


    @Subscribe("updateFilterConfig1A_Ts1_ElTs_GE_DecMonBtn")
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_DecMonBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_DecMonBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> propFilterCmpnt_Ts1_ElTs_GE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
        LocalDateTime l_ts1_ElTs_GE = propFilterCmpnt_Ts1_ElTs_GE.getValue();
        if (l_ts1_ElTs_GE == null) {
            logger.debug(logPrfx + " --- l_ts1_ElTs_GE: null");
        } else {
            logger.debug(logPrfx + " --- l_ts1_ElTs_GE: " + l_ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            l_ts1_ElTs_GE = l_ts1_ElTs_GE.minusMonths(1);
            logger.debug(logPrfx + " --- l_ts1_ElTs_GE: " + l_ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_Ts1_ElTs_GE.setValue(l_ts1_ElTs_GE);
            filter.apply();
        }

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()){
            DateField<LocalDateTime> propFilterCmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime idDtLE = propFilterCmpnt_Ts1_ElTs_LE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.minusMonths(1);
                logger.debug(logPrfx + " --- l_ts1_ElTs_GE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_Ts1_ElTs_LE.setValue(idDtLE);
                filter.apply();
            }
        };
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1A_Ts1_ElTs_GE_IncMonBtn")
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_IncMonBtnBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_IncMonBtnBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> propFilterCmpnt_Ts1_ElTs_GE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
        LocalDateTime idDtGE = propFilterCmpnt_Ts1_ElTs_GE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.plusMonths(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_Ts1_ElTs_GE.setValue(idDtGE);
            filter.apply();
        }
        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()) {
            DateField<LocalDateTime> propFilterCmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime idDtLE = propFilterCmpnt_Ts1_ElTs_LE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.plusMonths(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_Ts1_ElTs_LE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_Ts1_ElTs_GE_DecDayBtn")
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_DecDayBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_DecDayBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> cmpnt_Ts1_ElTs_GE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
        LocalDateTime idDtGE = cmpnt_Ts1_ElTs_GE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.minusDays(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_Ts1_ElTs_GE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()) {
            DateField<LocalDateTime> cmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime idDtLE = cmpnt_Ts1_ElTs_LE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.minusDays(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_Ts1_ElTs_LE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_Ts1_ElTs_GE_IncDayBtn")
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_IncDayClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_IncDayClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> cmpnt_Ts1_ElTs_GE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
        LocalDateTime idDtGE = cmpnt_Ts1_ElTs_GE.getValue();
        if (idDtGE == null) {
            logger.debug(logPrfx + " --- idDtGE: null");
        } else {
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            idDtGE = idDtGE.plusDays(1);
            logger.debug(logPrfx + " --- idDtGE: " + idDtGE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_Ts1_ElTs_GE.setValue(idDtGE);
            filter.apply();
        }

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()) {
            DateField<LocalDateTime> cmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime idDtLE = cmpnt_Ts1_ElTs_LE.getValue();
            if (idDtLE == null) {
                logger.debug(logPrfx + " --- idDtLE: null");
            } else {
                logger.debug(logPrfx + " --- idDtLE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                idDtLE = idDtLE.plusDays(1);
                logger.debug(logPrfx + " --- idDtGE: " + idDtLE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_Ts1_ElTs_LE.setValue(idDtLE);
                filter.apply();
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1B_Ts1_ElTs_GEBtn")
    public void onUpdateFilterConfig1B_Ts1_ElTs_GEBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_Ts1_ElTs_GEBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer ts1_ElTs_GEOption = updateFilterConfig1B_Ts1_ElTs_GERdo.getValue();
        switch (ts1_ElTs_GEOption){
            case 0: // Clear
                filterConfig1B_Ts1_ElTs_GE.clear();
                break;

            case 1:  // Set to match current row
                List<UsrNodeFinTxactSet> thisFinTxactSets = tableMain.getSelected().stream().toList();
                if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactSets is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeFinTxactSet thisFinTxactSet = thisFinTxactSets.get(0);
                if (thisFinTxactSet != null){
                    if (thisFinTxactSet.getTs1() != null && thisFinTxactSet.getTs1().getElTs() != null){
                        filterConfig1B_Ts1_ElTs_GE.setValue(thisFinTxactSet.getTs1().getElTs());
                        filterConfig1B_Ts1_ElTs_GE.apply();
                    }
                }
                break;
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1B_SortIdxBtn")
    public void onUpdateFilterConfig1B_SortIdxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_SortIdxBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer sortIdxOption = updateFilterConfig1B_SortIdxRdo.getValue();
        switch (sortIdxOption){
            case 0: // Clear
                filterConfig1B_SortIdx.clear();
                break;

            case 1:  // Set to match current row
                List<UsrNodeFinTxactSet> thisFinTxactSets = tableMain.getSelected().stream().toList();
                if (thisFinTxactSets == null || thisFinTxactSets.isEmpty()) {
                    logger.debug(logPrfx + " --- thisFinTxactSets is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeFinTxactSet thisFinTxactSet = thisFinTxactSets.get(0);
                if (thisFinTxactSet != null){
                    if (thisFinTxactSet.getSortIdx() != null){
                        filterConfig1B_SortIdx.setValue(thisFinTxactSet.getSortIdx());
                        filterConfig1B_SortIdx.apply();
                    }
                }
                break;
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tmplt_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_WhatText1FieldFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_WhatText1FieldFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_WhyText1FieldFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_WhyText1FieldFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "desc1Node1_IdField.entityLookup", subject = "screenConfigurer")
    private void  desc1Node1_IdFieldLookupScreenConfigurer(Screen screen) {
        String logPrfx = "desc1Node1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactSet thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(screen instanceof UsrNodeFinTxactItm0Lookup scrn){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            scrn.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            scrn.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getSortIdx() == null ? 0 : thisNode.getSortIdx();
            scrn.getFilterConfig1A_Int1().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "desc1Node2_IdField.entityLookup", subject = "screenConfigurer")
    private void  desc1Node2_IdFieldLookupScreenConfigurer(Screen screen) {
        String logPrfx = "desc1Node2_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactSet thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(screen instanceof UsrNodeFinTxactItm0Lookup scrn){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            scrn.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            scrn.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getSortIdx() == null ? 0 : thisNode.getSortIdx();
            scrn.getFilterConfig1A_Int1().setValue(int1);

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


    @Subscribe("updateGenChan2_IdFieldListBtn")
    public void onUpdateGenChan2_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan2_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinHow1_IdFieldListBtn")
    public void onUpdateFinHow1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinHow1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "whatText1Field", subject = "enterPressHandler")
    private void whatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "whatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateWhatText1FieldListBtn")
    public void onUpdateWhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateWhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinWhat1_IdFieldListBtn")
    public void onUpdateFinWhat1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhat1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "whyText1Field", subject = "enterPressHandler")
    private void whyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "whyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateWhyText1FieldListBtn")
    public void onUpdateWhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateWhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinWhy1_IdFieldListBtn")
    public void onUpdateFinWhy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinWhy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("ts1_ElTsField")
    public void onTs1_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1_ElTsFieldValueChange";
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
            service.updateTs1Deps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }


    private void reloadWhatText1List(){
        String logPrfx = "reloadWhatText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whatText1"
                + " from enty_UsrNodeFinTxactSet e"
                + " where e.whatText1 is not null"
                + " order by e.whatText1"
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

        tmplt_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_WhatText1Field.setOptionsList()");

        whatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whatText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadWhyText1List(){
        String logPrfx = "reloadWhyText1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.whyText1"
                + " from enty_UsrNodeFinTxactSet e"
                + " where e.whyText1 is not null"
                + " order by e.whyText1"
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

        tmplt_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_WhyText1Field.setOptionsList()");

        whyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called whyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }





    @Subscribe("updateFinTxacts1_IdCntCalcFieldBtn")
    public void onUpdateFinTxacts1_IdCntCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxacts1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTxacts1_IdCntCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    protected Map<?, List<UsrNodeFinTxactSet>> getGrpgMap(List<UsrNodeFinTxactSet> thisNodes){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrNodeFinTxactSetGrpg, List<UsrNodeFinTxactSet>> grpMap = thisNodes.stream()
                .collect(groupingBy(node ->
                        new UsrNodeFinTxactSetGrpg(node.getParent1_Id()
                                ,node.getTs1() == null ? null : node.getTs1().getElTs())));

        logger.trace(logPrfx + " <-- ");
        return grpMap;
    }

    @Override
    protected Condition getConditionGrpg(Object grpgKey){
        String logPrfx = "getGrpgCondition";
        logger.trace(logPrfx + " --> ");

        // Ensure grpgKey is instanceof UsrNodeFinTxactSetGrpg
        if (!(grpgKey instanceof UsrNodeFinTxactSetGrpg l_grpgKey)){
            logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeFinTxactSetGrpg");
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

}