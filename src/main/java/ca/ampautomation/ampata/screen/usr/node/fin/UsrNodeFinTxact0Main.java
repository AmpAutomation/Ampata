package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseGrpg;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxact0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxact0Service;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@UiController("enty_UsrNodeFinTxact.main")
@UiDescriptor("usr-node-fin-txact-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinTxact0Main extends UsrNodeBase0BaseMain<UsrNodeFinTxact, UsrNodeFinTxactType, UsrNodeFinTxact0Service, UsrNodeFinTxact0Repo, Table<UsrNodeFinTxact>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxact.Service")
    public void setService(UsrNodeFinTxact0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxact.Repo")
    public void setRepo(UsrNodeFinTxact0Repo repo) { this.repo = repo; }

    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet.Service")
    protected UsrNodeFinTxactSet0Service serviceParent1;

    // filterConfig1A

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;


    // filterConfig1A
    @Autowired
    protected PropertyFilter<UsrNodeFinTxactType> filterConfig1B_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinTxactSetType> filterConfig1A_Parent1_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1A_Parent1_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1A_Parent1_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1A_Parent1_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1A_Parent1_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1A_Parent1_GenTag1_Id;


    // filterConfig1B
    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_Ts1_ElTs_LE;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_Int1;

    @Autowired
    protected PropertyFilter<UsrNodeFinTxactSetType> filterConfig1B_Parent1_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1B_Parent1_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1B_Parent1_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1B_Parent1_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1B_Parent1_FinWhy1_Id;

    @Autowired
    protected PropertyFilter<UsrItemGenTag> filterConfig1B_Parent1_GenTag1_Id;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsParent1Option;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsParent1Option;

    @Autowired
    protected CheckBox updateFilterConfig1A_Ts1_ElTs_LE_SyncChk;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_Ts1_ElTs_GERdo;

    @Autowired
    protected RadioButtonGroup<Integer> updateFilterConfig1B_Int1Rdo;


    //Template

    @Autowired
    protected ComboBox<String> tmplt_Parent1_EI1_RoleField;

    @Autowired
    protected CheckBox tmplt_Parent1_EI1_RoleFieldChk;

    @Autowired
    protected DateField<LocalDateTime> tmplt_Ts1_ElTsField;
    @Autowired
    protected CheckBox tmplt_Ts1_ElTsFieldChk;

    @Autowired
    protected TextField<Integer> tmplt_Int1Field;
    @Autowired
    protected RadioButtonGroup<Integer> tmplt_Int1FieldRdo;


    //Template (FinTxactSet)
    @Autowired
    protected EntityComboBox<UsrNodeFinTxactSetType> tmplt_Parent1_Type1_IdField;

    @Autowired
    protected CheckBox tmplt_Parent1_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeGenChan> tmplt_Parent1_GenChan1_IdField;

    @Autowired
    protected CheckBox tmplt_Parent1_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinHow> tmplt_Parent1_FinHow1_IdField;

    @Autowired
    protected CheckBox tmplt_Parent1_FinHow1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_Parent1_WhatText1Field;

    @Autowired
    protected CheckBox tmplt_Parent1_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhat> tmplt_Parent1_FinWhat1_IdField;

    @Autowired
    protected CheckBox tmplt_Parent1_FinWhat1_IdFieldChk;

    @Autowired
    protected ComboBox<String> tmplt_Parent1_WhyText1Field;

    @Autowired
    protected CheckBox tmplt_Parent1_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhy> tmplt_Parent1_FinWhy1_IdField;

    @Autowired
    protected CheckBox tmplt_Parent1_FinWhy1_IdFieldChk;



    //Other data loaders, containers and tables
    private CollectionContainer<UsrNodeGenChan> colCntnrGenChan;
    private CollectionLoader<UsrNodeGenChan> colLoadrGenChan;


    private CollectionContainer<UsrNodeFinTxactSet> colCntnrParent1;
    private CollectionLoader<UsrNodeFinTxactSet> colLoadrParent1;

    private CollectionContainer<UsrNodeFinTxactSetType> colCntnrParent1Type;
    private CollectionLoader<UsrNodeFinTxactSetType> colLoadrParent1Type;

    @Autowired
    private CollectionContainer<UsrNodeFinTxactItm> colCntnrFinTxactItm;
    @Autowired
    private CollectionLoader<UsrNodeFinTxactItm> colLoadrFinTxactItm;

    private CollectionContainer<UsrItemFinHow> colCntnrFinHow;
    private CollectionLoader<UsrItemFinHow> colLoadrFinHow;

    private CollectionContainer<UsrItemFinWhat> colCntnrFinWhat;
    private CollectionLoader<UsrItemFinWhat> colLoadrFinWhat;

    private CollectionContainer<UsrItemFinWhy> colCntnrFinWhy;
    private CollectionLoader<UsrItemFinWhy> colLoadrFinWhy;


    //Field
    @Autowired
    private ComboBox<String> parent1_EI1_RoleField;

    // Field (FinTxactSet)

    @Autowired
    private EntityComboBox<UsrNodeFinTxactSetType> parent1_Type1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenChan> parent1_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrItemFinHow> parent1_FinHow1_IdField;

    @Autowired
    private ComboBox<String> parent1_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhat>  parent1_FinWhat1_IdField;

    @Autowired
    private ComboBox<String> parent1_WhyText1Field;
    @Autowired
    private EntityComboBox<UsrItemFinWhy>  parent1_FinWhy1_IdField;


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("Skip", 0);
        map1.put("Max+1", 2);
        map1.put("", 1);
        tmplt_Int1FieldRdo.setOptionsMap(map1);

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put(UpdateOption.SKIP.toString(), UpdateOption.SKIP.toInt());
        map2.put(UpdateOption.LOCAL.toString(), UpdateOption.LOCAL.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST.toString(), UpdateOption.LOCAL__REF_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_TO_EXIST_NEW.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toInt());
        updateColItemCalcValsParent1Option.setOptionsMap(map2);
        updateColItemCalcValsParent1Option.setValue(UpdateOption.LOCAL.toInt());
        updateInstItemCalcValsParent1Option.setOptionsMap(map2);
        updateInstItemCalcValsParent1Option.setValue(UpdateOption.LOCAL.toInt());

        Map<String, Integer> map3 = new LinkedHashMap<>();
        map3.put("Clear", 0);
        map3.put("Match Current Row", 1);
        updateFilterConfig1B_Ts1_ElTs_GERdo.setOptionsMap(map3);
        updateFilterConfig1B_Int1Rdo.setOptionsMap(map3);


        //filter
        EntityComboBox<UsrNodeFinTxactType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeFinTxactType>) filterConfig1B_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrNodeGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrNodeGenChan e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenChan_Inst = fetchPlans.builder(UsrNodeGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(fchPlnGenChan_Inst);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());
        parent1_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        //template
        tmplt_Parent1_GenChan1_IdField.setOptionsContainer(colCntnrGenChan);
        //filter
        EntityComboBox<UsrNodeGenChan> propFilterCmpnt_GenChan1_Id;
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1A_Parent1_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1B_Parent1_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setOptionsContainer(colCntnrGenChan);


        colCntnrFinHow = dataComponents.createCollectionContainer(UsrItemFinHow.class);
        colLoadrFinHow = dataComponents.createCollectionLoader();
        colLoadrFinHow.setQuery("select e from enty_UsrItemFinHow e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinHow_Inst = fetchPlans.builder(UsrItemFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinHow.setFetchPlan(fchPlnFinHow_Inst);
        colLoadrFinHow.setContainer(colCntnrFinHow);
        colLoadrFinHow.setDataContext(getScreenData().getDataContext());

        parent1_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        //template
        tmplt_Parent1_FinHow1_IdField.setOptionsContainer(colCntnrFinHow);
        //filter
        EntityComboBox<UsrItemFinHow> propFilterCmpnt_FinHow1_Id;
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1A_Parent1_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(colCntnrFinHow);
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1B_Parent1_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setOptionsContainer(colCntnrFinHow);


        colCntnrFinWhat = dataComponents.createCollectionContainer(UsrItemFinWhat.class);
        colLoadrFinWhat = dataComponents.createCollectionLoader();
        colLoadrFinWhat.setQuery("select e from enty_UsrItemFinWhat e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhat_Inst = fetchPlans.builder(UsrItemFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhat.setFetchPlan(fchPlnFinWhat_Inst);
        colLoadrFinWhat.setContainer(colCntnrFinWhat);
        colLoadrFinWhat.setDataContext(getScreenData().getDataContext());

        parent1_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        //template
        tmplt_Parent1_FinWhat1_IdField.setOptionsContainer(colCntnrFinWhat);
        //filter
        EntityComboBox<UsrItemFinWhat> propFilterCmpnt_FinWhat1_Id;
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1A_Parent1_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(colCntnrFinWhat);
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1B_Parent1_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setOptionsContainer(colCntnrFinWhat);


        colCntnrFinWhy = dataComponents.createCollectionContainer(UsrItemFinWhy.class);
        colLoadrFinWhy = dataComponents.createCollectionLoader();
        colLoadrFinWhy.setQuery("select e from enty_UsrItemFinWhy e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhy_Inst = fetchPlans.builder(UsrItemFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhy.setFetchPlan(fchPlnFinWhy_Inst);
        colLoadrFinWhy.setContainer(colCntnrFinWhy);
        colLoadrFinWhy.setDataContext(getScreenData().getDataContext());

        parent1_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        //template
        tmplt_Parent1_FinWhy1_IdField.setOptionsContainer(colCntnrFinWhy);
        //filter
        EntityComboBox<UsrItemFinWhy> propFilterCmpnt_FinWhy1_Id;
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1A_Parent1_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(colCntnrFinWhy);
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1B_Parent1_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setOptionsContainer(colCntnrFinWhy);



        colCntnrParent1 = dataComponents.createCollectionContainer(UsrNodeFinTxactSet.class);
        colLoadrParent1 = dataComponents.createCollectionLoader();
        colLoadrParent1.setQuery("select e from enty_UsrNodeFinTxactSet e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSet_Inst = fetchPlans.builder(UsrNodeFinTxactSet.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrParent1.setFetchPlan(fchPlnFinTxactSet_Inst);
        colLoadrParent1.setContainer(colCntnrParent1);
        colLoadrParent1.setDataContext(getScreenData().getDataContext());


        colCntnrParent1Type = dataComponents.createCollectionContainer(UsrNodeFinTxactSetType.class);
        colLoadrParent1Type = dataComponents.createCollectionLoader();
        colLoadrParent1Type.setQuery("select e from enty_UsrNodeFinTxactSetType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSetType_Inst = fetchPlans.builder(UsrNodeFinTxactSetType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrParent1Type.setFetchPlan(fchPlnFinTxactSetType_Inst);
        colLoadrParent1Type.setContainer(colCntnrParent1Type);
        colLoadrParent1Type.setDataContext(getScreenData().getDataContext());

        parent1_Type1_IdField.setOptionsContainer(colCntnrParent1Type);
        //template
        tmplt_Parent1_Type1_IdField.setOptionsContainer(colCntnrParent1Type);
        //filter
        EntityComboBox<UsrNodeFinTxactSetType> propFilterCmpnt_FinTxactSetType1_Id;
        propFilterCmpnt_FinTxactSetType1_Id = (EntityComboBox<UsrNodeFinTxactSetType>) filterConfig1A_Parent1_Type1_Id.getValueComponent();
        propFilterCmpnt_FinTxactSetType1_Id.setOptionsContainer(colCntnrParent1Type);
        propFilterCmpnt_FinTxactSetType1_Id = (EntityComboBox<UsrNodeFinTxactSetType>) filterConfig1B_Parent1_Type1_Id.getValueComponent();
        propFilterCmpnt_FinTxactSetType1_Id.setOptionsContainer(colCntnrParent1Type);


        colCntnrFinTxactItm = dataComponents.createCollectionContainer(UsrNodeFinTxactItm.class);
        colLoadrFinTxactItm = dataComponents.createCollectionLoader();
        colLoadrFinTxactItm.setQuery("select e from enty_UsrNodeFinTxactItm e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactItm_Inst = fetchPlans.builder(UsrNodeFinTxactItm.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactItm.setFetchPlan(fchPlnFinTxactItm_Inst);
        colLoadrFinTxactItm.setContainer(colCntnrFinTxactItm);
        colLoadrFinTxactItm.setDataContext(getScreenData().getDataContext());

        logger.trace(logPrfx + " <-- ");


    }

    @Install(to = "tableMain.[ts1.elTs]", subject = "formatter")
    private String tableMainTs1_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

    // Toolbar

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        reloadParent1_EI1_RoleList();

        colLoadrParent1Type.load();
        logger.debug(logPrfx + " --- called colLoadrParent1Type.load() ");

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
    public UsrNodeFinTxact onDuplicateBtnClickHelper(UsrNodeFinTxact orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.isChecked()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_Parent1_EI1_RoleFieldChk.isChecked()) {
            copy.setParent1_EI1_Role(tmplt_Parent1_EI1_RoleField.getValue());
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

        // If parent1_id is present
        // int1 is driven by parent_Id1.sortIdx
        // Therefore, int1 will not change
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(copy.getParent1_Id());
        if (o_parent1_Id.isEmpty()){
            Integer int1 = copy.getInt1();
            if (tmplt_Int1FieldRdo.getValue() != null){
                switch (int1){
                    // Set
                    case 1 ->{
                        int1 = tmplt_Int1Field.getValue();
                        copy.setInt1(int1);
                    }
                }
            }
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, copy, updOption);

        if (Objects.equals(copy.getId2(), orig.getId2())) {
            UsrNodeBaseGrpg grpg = new UsrNodeBaseGrpg(copy.getParent1_Id());
            Integer sortIdxMax = service.getSortIdxMax(this, grpg);
            if(sortIdxMax != null){
                copy.setSortIdx(sortIdx + 1);
                service.updateSortIdxDeps(this, copy, updOption);
            }
        }

        logger.trace(logPrfx + " <-- ");
        return copy;
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodes.forEach(thisNode -> {
            UsrNodeFinTxact thisTrackedFinTxact = dataContext.merge(thisNode);
            thisNode = thisTrackedFinTxact;
            if (thisNode != null) {

                Boolean thisNodeIsChanged = false;

                thisNodeIsChanged = onSetBtnClickHelper(thisNode) || thisNodeIsChanged;

                UsrNodeFinTxactSet thisNodeSet = thisNode.getFinTxactSet1_Id();
                if (thisNodeSet != null) {

                    UsrNodeFinTxactSet thisTrackedFinTxactSet = dataContext.merge(thisNodeSet);
                    thisNodeSet = thisTrackedFinTxactSet;

                    Boolean thisNodeSetIsChanged = false;

                    if (tmplt_Parent1_Type1_IdFieldChk.isChecked()
                            && thisNodeSet != null
                    ) {
                        thisNodeSetIsChanged = true;
                        thisNodeSet.setType1_Id(tmplt_Parent1_Type1_IdField.getValue());
                    }

                    if (tmplt_Parent1_GenChan1_IdFieldChk.isChecked()
                            && thisNodeSet != null
                    ) {
                        thisNodeSetIsChanged = true;
                        thisNodeSet.setGenChan1_Id(tmplt_Parent1_GenChan1_IdField.getValue());
                    }

                    if (tmplt_Parent1_FinHow1_IdFieldChk.isChecked()
                            && thisNodeSet != null
                    ) {
                        thisNodeSetIsChanged = true;
                        thisNodeSet.setFinHow1_Id(tmplt_Parent1_FinHow1_IdField.getValue());
                    }

                    if (tmplt_Parent1_WhatText1FieldChk.isChecked()
                            && thisNodeSet != null
                    ) {
                        thisNodeSetIsChanged = true;
                        thisNodeSet.setWhatText1(tmplt_Parent1_WhatText1Field.getValue());
                    }

                    if (tmplt_Parent1_FinWhat1_IdFieldChk.isChecked()
                            && thisNodeSet != null
                    ) {
                        thisNodeSetIsChanged = true;
                        thisNodeSet.setFinWhat1_Id(tmplt_Parent1_FinWhat1_IdField.getValue());
                    }

                    if (tmplt_Parent1_WhyText1FieldChk.isChecked()
                            && thisNodeSet != null
                    ) {
                        thisNodeSetIsChanged = true;
                        thisNodeSet.setWhyText1(tmplt_Parent1_WhyText1Field.getValue());
                    }

                    if (tmplt_Parent1_FinWhy1_IdFieldChk.isChecked()
                            && thisNodeSet != null
                    ) {
                        thisNodeSetIsChanged = true;
                        thisNodeSet.setFinWhy1_Id(tmplt_Parent1_FinWhy1_IdField.getValue());
                    }

                    //logger.debug(logPrfx + " --- executing metadataTools.copy(thisNode,thisNode).");
                    //metadataTools.copy(thisNode, thisNode);

                    //logger.debug(logPrfx + " --- executing dataContext.commit().");
                    //dataContext.commit();

                    if (thisNodeSetIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisNodeSet).");
                        //dataManager.save(thisNodeSet);
                    }

                }


            }
        });
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    public Boolean onSetBtnClickHelper(UsrNodeFinTxact thisNode){
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
                // Max+1
                case 2 ->{
                    UsrNodeBaseGrpg grpg = new UsrNodeBaseGrpg(thisNode.getParent1_Id());
                    Integer sortIdxMax = service.getSortIdxMax(this, grpg);
                    sortIdx = sortIdxMax == null ? null : sortIdxMax + 1;
                    if (sortIdx != null){
                        thisNode.setSortIdx(sortIdx);
                    };
                    thisNodeIsChanged = true;
                }
            }
        }

        if (tmplt_Parent1_EI1_RoleFieldChk.isChecked()) {
            thisNode.setParent1_EI1_Role(tmplt_Parent1_EI1_RoleField.getValue());
            thisNodeIsChanged = true;
        }

        // If parent1_id is present
        // int1 is driven by parent_Id1.sortIdx
        // Therefore, int1 will not change
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isEmpty()){
            Integer int1 = thisNode.getInt1();
            if (tmplt_Int1FieldRdo.getValue() != null){
                switch (int1){
                    // Set
                    case 1 ->{
                        int1 = tmplt_Int1Field.getValue();
                        thisNode.setInt1(int1);
                        thisNodeIsChanged = true;
                    }
                }
            }
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

//        thisNodeIsChanged = service.updateId2Calc(thisNode, updOption) || thisNodeIsChanged;
//        thisNodeIsChanged = service.updateId2(thisNode, updOption) || thisNodeIsChanged;
//        thisNodeIsChanged = service.updateId2Cmp(thisNode, updOption) || thisNodeIsChanged;
        thisNodeIsChanged = service.updateCalcVals(this, thisNode, updOption) || thisNodeIsChanged;


        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }



    @Subscribe("updateFilterConfig1A_Ts1_ElTs_GE_DecMonBtn")
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_DecMonBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_DecMonBtnClick";
        logger.trace(logPrfx + " --> ");

        DateField<LocalDateTime> propFilterCmpnt_Ts1_ElTs_GE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
        LocalDateTime ts1_ElTs_GE = propFilterCmpnt_Ts1_ElTs_GE.getValue();
        if (ts1_ElTs_GE == null) {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: null");
        } else {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ts1_ElTs_GE = ts1_ElTs_GE.minusMonths(1);
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_Ts1_ElTs_GE.setValue(ts1_ElTs_GE);
            filter.apply();
        }

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()){
            DateField<LocalDateTime> propFilterCmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime ts1_ElTs_LE = propFilterCmpnt_Ts1_ElTs_LE.getValue();
            if (ts1_ElTs_LE == null) {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: null");
            } else {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                ts1_ElTs_LE = ts1_ElTs_LE.minusMonths(1);
                logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_Ts1_ElTs_LE.setValue(ts1_ElTs_LE);
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
        LocalDateTime ts1_ElTs_GE = propFilterCmpnt_Ts1_ElTs_GE.getValue();
        if (ts1_ElTs_GE == null) {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: null");
        } else {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ts1_ElTs_GE = ts1_ElTs_GE.plusMonths(1);
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            propFilterCmpnt_Ts1_ElTs_GE.setValue(ts1_ElTs_GE);
            filter.apply();
        }
        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()) {
            DateField<LocalDateTime> propFilterCmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime ts1_ElTs_LE = propFilterCmpnt_Ts1_ElTs_LE.getValue();
            if (ts1_ElTs_LE == null) {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: null");
            } else {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                ts1_ElTs_LE = ts1_ElTs_LE.plusMonths(1);
                logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                propFilterCmpnt_Ts1_ElTs_LE.setValue(ts1_ElTs_LE);
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
        LocalDateTime ts1_ElTs_GE = cmpnt_Ts1_ElTs_GE.getValue();
        if (ts1_ElTs_GE == null) {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: null");
        } else {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ts1_ElTs_GE = ts1_ElTs_GE.minusDays(1);
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_Ts1_ElTs_GE.setValue(ts1_ElTs_GE);
            filter.apply();
        }

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()) {
            DateField<LocalDateTime> cmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime ts1_ElTs_LE = cmpnt_Ts1_ElTs_LE.getValue();
            if (ts1_ElTs_LE == null) {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: null");
            } else {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                ts1_ElTs_LE = ts1_ElTs_LE.minusDays(1);
                logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_Ts1_ElTs_LE.setValue(ts1_ElTs_LE);
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
        LocalDateTime ts1_ElTs_GE = cmpnt_Ts1_ElTs_GE.getValue();
        if (ts1_ElTs_GE == null) {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: null");
        } else {
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ts1_ElTs_GE = ts1_ElTs_GE.plusDays(1);
            logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_GE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cmpnt_Ts1_ElTs_GE.setValue(ts1_ElTs_GE);
            filter.apply();
        }

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.isChecked()) {
            DateField<LocalDateTime> cmpnt_Ts1_ElTs_LE = (DateField<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
            LocalDateTime ts1_ElTs_LE = cmpnt_Ts1_ElTs_LE.getValue();
            if (ts1_ElTs_LE == null) {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: null");
            } else {
                logger.debug(logPrfx + " --- ts1_ElTs_LE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                ts1_ElTs_LE = ts1_ElTs_LE.plusDays(1);
                logger.debug(logPrfx + " --- ts1_ElTs_GE: " + ts1_ElTs_LE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                cmpnt_Ts1_ElTs_LE.setValue(ts1_ElTs_LE);
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
                List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
                if (thisNodes.isEmpty()) {
                    logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeFinTxact thisNode = thisNodes.get(0);
                if (thisNode != null){
                    if (thisNode.getTs1() != null && thisNode.getTs1().getElTs() != null){
                        filterConfig1B_Ts1_ElTs_GE.setValue(thisNode.getTs1().getElTs());
                        filterConfig1B_Ts1_ElTs_GE.apply();
                    }
                }
                break;
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1B_Int1Btn")
    public void onUpdateFilterConfig1B_Int1BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFilterConfig1B_Int1BtnClick";
        logger.trace(logPrfx + " --> ");

        Integer int1Option = updateFilterConfig1B_Int1Rdo.getValue();
        switch (int1Option){
            case 0: // Clear
                filterConfig1B_Int1.clear();
                break;

            case 1:  // Set to match current row
                List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
                if (thisNodes.isEmpty()) {
                    logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
                    notifications.create().withCaption("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeFinTxact thisNode = thisNodes.get(0);
                if (thisNode != null){
                    if (thisNode.getInt1() != null){
                        filterConfig1B_Int1.setValue(thisNode.getInt1());
                        filterConfig1B_Int1.apply();
                    }
                }
                break;
        }

        logger.trace(logPrfx + " <-- ");
    }


    protected Comparator<UsrNodeFinTxact> getComparator(){
        String logPrfx = "getComparatorThisNode";
        logger.trace(logPrfx + " --> ");

        Comparator<UsrNodeFinTxact> comparator =
                Comparator.comparing((UsrNodeFinTxact n) ->
                    n.getTs1() == null ? null : n.getTs1().getElTs()
                    ,Comparator.nullsFirst(Comparator.naturalOrder())
                )
                .thenComparing(UsrNodeBase::getSortIdx
                    ,Comparator.nullsFirst(Comparator.naturalOrder())
                )
                ;

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }


    @Subscribe("moveFrstInt1Btn")
    public void onMoveFrstInt1BtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveFrstInt1BtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("movePrevInt1Btn")
    public void onMovePrevInt1BtnClick(Button.ClickEvent event) {
        String logPrfx = "onMovePrevInt1BtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("moveNextInt1Btn")
    public void onMoveNextInt1BtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveNextInt1BtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("moveLastInt1Btn")
    public void onMoveLastInt1BtnClick(Button.ClickEvent event) {
        String logPrfx = "onMoveLastInt1BtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxact> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOptionFinTxactSet = UpdateOption.valueOf(updateColItemCalcValsParent1Option.getValue())
                .orElse(UpdateOption.SKIP);
        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {
                thisNode = dataContext.merge(thisNode);

                boolean isChanged = false;

                Optional<UsrNodeBase> o_Parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
                if (o_Parent1_Id.isPresent()){
                    isChanged = serviceParent1.updateCalcVals(this, o_Parent1_Id.get(), updOptionFinTxactSet) || isChanged;
                }

                isChanged = service.updateCalcVals(this, thisNode, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //tableMain.sort("id2", Table.SortDirection.ASCENDING);
            try{tableMain.setSelected(thisNodes);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        Optional<UsrNodeBase> o_parent1 = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateCalcVals(this, o_parent1.get(), updOptionParent1);
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tmplt_Parent1_WhatText1Field", subject = "enterPressHandler")
    private void tmplt_Parent1_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_Parent1_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tmplt_Parent1_WhyText1Field", subject = "enterPressHandler")
    private void tmplt_Parent1_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_Parent1_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }



    @Install(to = "tmplt_Parent1_EI1_RoleField", subject = "enterPressHandler")
    private void tmplt_Parent1_EI1_RoleFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "tmplt_Parent1_EI1_RoleFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }



    // Main Field

    @Install(to = "parent1_IdField.entityLookup", subject = "screenConfigurer")
    private void  parent1_IdFieldLookupScreenConfigurer(Screen screen) {
        String logPrfx = "parent1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(screen instanceof UsrNodeFinTxactSet0Lookup scrn){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            scrn.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            scrn.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getInt1();
            scrn.getFilterConfig1A_SortIdx().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("ts1_ElTsField")
    public void onTs1_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1_ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
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

    @Subscribe("extractTs1_ElTsFieldBtn")
    public void onUpdateTs1_ElTsFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateTs1_ElTsFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateTs1(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("int1Field")
    public void onInt1FieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onInt1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateInt1Deps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInt1FieldBtn")
    public void onUpdateInt1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInt1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateInt1(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    

    @Subscribe("updateParent1_IdFieldBtn")
    public void onUpdateParent1_IdFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateParent1_Id(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    //Parent1 (FinTxactSet) Field

    @Subscribe("updateParent1_Desc1FieldBtn")
    public void onUpdateParent1_Desc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeFinTxactSet> o_parent1_Id = Optional.ofNullable(thisNode.getFinTxactSet1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateDesc1(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "parent1_Desc1Node1_IdField.entityLookup", subject = "screenConfigurer")
    private void  parent1_Desc1Node1_IdFieldLookupScreenConfigurer(Screen screen) {
        String logPrfx = "parent1_Desc1Node1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(screen instanceof UsrNodeFinTxactItm0Lookup scrn){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            scrn.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            scrn.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getInt1() == null ? 0 : thisNode.getInt1();
            scrn.getFilterConfig1A_Int1().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "parent1_Desc1Node2_IdField.entityLookup", subject = "screenConfigurer")
    private void  parent1_Desc1Node2_IdFieldLookupScreenConfigurer(Screen screen) {
        String logPrfx = "parent1_Desc1Node2_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxact thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(screen instanceof UsrNodeFinTxactItm0Lookup scrn){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            scrn.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            scrn.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getInt1() == null ? 0 : thisNode.getInt1();
            scrn.getFilterConfig1A_Int1().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Type1_IdFieldListBtn")
    public void onUpdateParent1_Type1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrParent1Type.load();
        logger.debug(logPrfx + " --- called colLoadrParent1Type.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_GenChan1_IdFieldListBtn")
    public void onUpdateParent1_GenChan1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_How1_IdFieldListBtn")
    public void onUpdateParent1_How1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_How1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- called colLoadrFinHow.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "parent1_WhatText1Field", subject = "enterPressHandler")
    private void parent1_WhatText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "parent1_WhatText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_WhatText1FieldListBtn")
    public void onUpdateParent1_WhatText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_WhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhatText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_What1_IdFieldListBtn")
    public void onUpdateParent1_What1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_What1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhat.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "parent1_WhyText1Field", subject = "enterPressHandler")
    private void parent1_WhyText1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "parent1_WhyText1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_WhyText1FieldListBtn")
    public void onUpdateParent1_WhyText1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_WhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadWhyText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Why1_IdFieldListBtn")
    public void onUpdateParent1_Why1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateParent1_Why1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- called colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    

    @Subscribe("updateFinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtDebtSumCalcFieldBtnClick";
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

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxactItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtCredSumCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtCredSumCalcFieldBtnClick";
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

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxactItms1_AmtNet2SumCalcFieldBtn")
    public void onUpdateFinTxactItms1_AmtNet2SumCalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_AmtNet2SumCalcFieldBtnClick";
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
        service.updateFinTxactItms1_AmtNet2SumCalc(this, thisNode, updOption);

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

    @Subscribe("updateFinTxactItms1_SysNodeFinCurcyEqCalcBoxBtn")
    public void onUpdateFinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick";
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
        service.updateFinTxactItms1_SysNodeFinCurcyEqCalc(this, thisNode, updOption);

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


        tmplt_Parent1_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_Parent1_WhatText1Field.setOptionsList()");

        parent1_WhatText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called parent1_WhatText1Field.setOptionsList()");

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

        tmplt_Parent1_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called tmplt_Parent1_WhyText1Field.setOptionsList()");

        parent1_WhyText1Field.setOptionsList(texts);
        logger.debug(logPrfx + " --- called finParent1_WhyText1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadParent1_EI1_RoleList(){
        String logPrfx = "reloadParent1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.parent1_EI1_Role"
                + " from enty_UsrNodeFinTxact e"
                + " where e.parent1_EI1_Role is not null"
                + " order by e.parent1_EI1_Role"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);

        List<String> roles = null;
        try {
            roles = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + roles.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_Parent1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called tmplt_Parent1_EI1_RoleField.setOptionsList()");

        parent1_EI1_RoleField.setOptionsList(roles);
        logger.debug(logPrfx + " --- called parent1_EI1_RoleField.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("loadTableFinTxactItmBtn")
    public void onLoadTableFinTxactItmBtnClick(Button.ClickEvent event) {
        String logPrfx = "onLoadTableFinTxactItmBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
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

        cond.add(PropertyCondition.equal("parent1_Id", thisNode));

        colLoadrFinTxactItm.setQuery("select e from enty_UsrNodeFinTxactItm e order by e.sortKey, e.id2");
        colLoadrFinTxactItm.setCondition(cond);

        logger.debug(logPrfx + " --- calling colLoadrFinTxactItm.load() ");
        colLoadrFinTxactItm.load();
        logger.debug(logPrfx + " --- called colLoadrFinTxactItm.load() ");

        logger.trace(logPrfx + " <-- ");
    }

}