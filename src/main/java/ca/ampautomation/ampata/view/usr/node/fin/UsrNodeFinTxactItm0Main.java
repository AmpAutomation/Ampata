package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactItm0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.*;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import io.jmix.core.*;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.kit.component.ComponentUtils;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// import javax.transaction.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@Route(value = "usrNodeFinTxactItms", layout = MainView.class)
@ViewController("enty_UsrNodeFinTxactItm.main")
@ViewDescriptor("usr-node-fin-txact-itm-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinTxactItm0Main extends UsrNodeBase0BaseMain<UsrNodeFinTxactItm, UsrNodeFinTxactItmType, UsrNodeFinTxactItm0Service, UsrNodeFinTxactItm0Repo, DataGrid<UsrNodeFinTxactItm>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactItm.Service")
    public void setService(UsrNodeFinTxactItm0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactItm.Repo")
    public void setRepo(UsrNodeFinTxactItm0Repo repo) { this.repo = repo; }


    @Autowired
    protected UsrNodeFinTxact0Service serviceParent1;

    @Autowired
    protected UsrNodeFinStmtItm0Service serviceFinStmtItm;

    @Autowired
    protected UsrNodeFinTxactSet0Service serviceParent1Parent1;

    //Filter (filterConfig1A)
    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Parent1_EI1_Role;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_Int1;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_Int2;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @Autowired
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1A_FinAcct1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinDept> filterConfig1A_FinDept1_Id;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_SysNodeFinCurcy1_Id;

    
    //Filter (Config1A.FinTxact)
    @Autowired
    protected PropertyFilter<String> filterConfig1A_Parent1_Parent1_EI1_Role;
    

    //Filter (Config1A.FinTxact.FinTxactSet)
    @Autowired
    protected PropertyFilter<UsrNodeFinTxactSetType> filterConfig1A_Parent1_Parent1_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1A_Parent1_Parent1_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1A_Parent1_Parent1_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1A_Parent1_Parent1_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1A_Parent1_Parent1_FinWhy1_Id;

    
    //Filter (filterConfig1A.FinStmtItm)
    @Autowired
    protected PropertyFilter<UsrNodeFinStmt> filterConfig1A_FinStmtItm1_FinStmt1_Id;


    //Filter (filterConfig1B)
    @Autowired
    protected PropertyFilter<UsrNodeFinTxactItmType> filterConfig1B_Type1_Id;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1B_Ts1_ElTs_LE;

    @Autowired
    protected PropertyFilter<String> filterConfig1B_Parent1_EI1_Role;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_Int1;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_Int2;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1B_SortIdx;

    @Autowired
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1B_FinAcct1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinDept> filterConfig1B_FinDept1_Id;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1B_SysNodeFinCurcy1_Id;

    //Filter (filterConfig1B.FinTxact)
    @Autowired
    protected PropertyFilter<String> filterConfig1B_Parent1_Parent1_EI1_Role;


    //Filter (filterConfig1B.FinTxact.FinTxactSet)
    @Autowired
    protected PropertyFilter<UsrNodeFinTxactSetType> filterConfig1B_Parent1_Parent1_Type1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenChan> filterConfig1B_Parent1_Parent1_GenChan1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinHow> filterConfig1B_Parent1_Parent1_FinHow1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhat> filterConfig1B_Parent1_Parent1_FinWhat1_Id;

    @Autowired
    protected PropertyFilter<UsrItemFinWhy> filterConfig1B_Parent1_Parent1_FinWhy1_Id;


    //Filter (filterConfig1B.FinStmtItm)
    @Autowired
    protected PropertyFilter<UsrNodeFinStmt> filterConfig1B_FinStmtItm1_FinStmt1_Id;


    //Toolbar
    @Autowired
    protected JmixComboBox<Integer> updateColItemCalcValsParent1Option;

    @Autowired
    protected JmixComboBox<Integer> updateColItemCalcValsParent1Parent1Option;

    @Autowired
    protected JmixComboBox<Integer> updateColItemCalcValsFinStmtItmOption;


    @Autowired
    protected JmixComboBox<Integer> updateInstItemCalcValsParent1Option;

    @Autowired
    protected JmixComboBox<Integer> updateInstItemCalcValsParent1Parent1Option;

    @Autowired
    protected JmixComboBox<Integer> updateInstItemCalcValsFinStmtItmOption;


    @Autowired
    protected JmixCheckbox updateFilterConfig1A_Ts1_ElTs_LE_SyncChk;

    @Autowired
    protected JmixRadioButtonGroup<Integer> updateFilterConfig1B_Ts1_ElTs_GERdo;

    @Autowired
    protected JmixRadioButtonGroup<Integer> updateFilterConfig1B_Int1Rdo;

    @Autowired
    protected JmixRadioButtonGroup<Integer> updateFilterConfig1B_Int2Rdo;


    //Template

    @Autowired
    protected JmixComboBox<String> tmplt_Parent1_EI1_RoleField;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_EI1_RoleFieldChk;

    @Autowired
    protected TypedDateTimePicker<LocalDateTime> tmplt_Ts1_ElTsField;

    @Autowired
    protected JmixCheckbox tmplt_Ts1_ElTsFieldChk;

    @Autowired
    protected TypedDateTimePicker<LocalDateTime> tmplt_Ts2_ElTsField;

    @Autowired
    protected JmixCheckbox tmplt_Ts2_ElTsFieldChk;

    @Autowired
    protected TypedTextField<Integer> tmplt_Int1Field;

    @Autowired
    protected JmixRadioButtonGroup<Integer> tmplt_Int1FieldRdo;

    @Autowired
    protected TypedTextField<Integer> tmplt_Int2Field;

    @Autowired
    protected JmixRadioButtonGroup<Integer> tmplt_Int2FieldRdo;

    @Autowired
    protected TypedTextField<Integer> tmplt_SortIdxField;

    @Autowired
    protected JmixRadioButtonGroup<Integer> tmplt_SortIdxFieldRdo;


    @Autowired
    protected EntityComboBox<UsrNodeFinStmtItm> tmplt_FinStmtItm1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_FinStmtItm1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinTaxLne> tmplt_FinTaxLne1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_FinTaxLne1_IdFieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_FinTaxLne1_CodeField;

    @Autowired
    protected JmixCheckbox tmplt_FinTaxLne1_CodeFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinAcct> tmplt_FinAcct1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_FinAcct1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeFinDept> tmplt_FinDept1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_FinDept1_IdFieldChk;

    @Autowired
    protected TypedTextField<BigDecimal> tmplt_AmtDebtField;

    @Autowired
    protected JmixCheckbox tmplt_AmtDebtFieldChk;

    @Autowired
    protected TypedTextField<BigDecimal> tmplt_AmtCredField;

    @Autowired
    protected JmixCheckbox tmplt_AmtCredFieldChk;

    @Autowired
    protected EntityComboBox<SysNodeFinCurcy> tmplt_SysNodeFinCurcy1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_SysNodeFinCurcy1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemGenFmla> tmplt_AmtGenFmla1_IdField;


    //Template (FinTxact)
    @Autowired
    protected EntityComboBox<UsrNodeFinTxactType> tmplt_FinTxact1_Id_Type1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_FinTxact1_Id_Type1_IdFieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_Parent1_Parent1_EI1_RoleField;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_EI1_RoleFieldChk;


    //Template (FinTxactSet)
    @Autowired
    protected EntityComboBox<UsrNodeFinTxactSetType> tmplt_Parent1_Parent1_Type1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_Type1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrNodeGenChan> tmplt_Parent1_Parent1_GenChan1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_GenChan1_IdFieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinHow> tmplt_Parent1_Parent1_FinHow1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_FinHow1_IdFieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_Parent1_Parent1_WhatText1Field;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_WhatText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhat> tmplt_Parent1_Parent1_FinWhat1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_FinWhat1_IdFieldChk;

    @Autowired
    protected JmixComboBox<String> tmplt_Parent1_Parent1_WhyText1Field;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_WhyText1FieldChk;

    @Autowired
    protected EntityComboBox<UsrItemFinWhy> tmplt_Parent1_Parent1_FinWhy1_IdField;

    @Autowired
    protected JmixCheckbox tmplt_Parent1_Parent1_FinWhy1_IdFieldChk;


    //Other data containers, loaders and table
    private CollectionContainer<UsrNodeGenChan> colCntnrGenChan;
    private CollectionLoader<UsrNodeGenChan> colLoadrGenChan;

    private CollectionContainer<UsrItemGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrItemGenFmla> colLoadrGenFmla;

    private CollectionContainer<UsrNodeFinTxactType> colCntnrFinTxactType;
    private CollectionLoader<UsrNodeFinTxactType> colLoadrFinTxactType;

    private CollectionContainer<UsrNodeFinTxactSetType> colCntnrFinTxactSetType;
    private CollectionLoader<UsrNodeFinTxactSetType> colLoadrFinTxactSetType;

    private CollectionContainer<UsrNodeFinStmt> colCntnrFinStmt;
    private CollectionLoader<UsrNodeFinStmt> colLoadrFinStmt;

    private CollectionContainer<UsrNodeFinDept> colCntnrFinDept;
    private CollectionLoader<UsrNodeFinDept> colLoadrFinDept;

    private CollectionContainer<UsrNodeFinTaxLne> colCntnrFinTaxLne;
    private CollectionLoader<UsrNodeFinTaxLne> colLoadrFinTaxLne;

    private CollectionContainer<UsrNodeFinAcct> colCntnrFinAcct;
    private CollectionLoader<UsrNodeFinAcct> colLoadrFinAcct;

    private CollectionContainer<SysNodeFinCurcy> colCntnrSysNodeFinCurcy;
    private CollectionLoader<SysNodeFinCurcy> colLoadrSysNodeFinCurcy;

    private CollectionContainer<UsrItemFinHow> colCntnrFinHow;
    private CollectionLoader<UsrItemFinHow> colLoadrFinHow;

    private CollectionContainer<UsrItemFinWhat> colCntnrFinWhat;
    private CollectionLoader<UsrItemFinWhat> colLoadrFinWhat;

    private CollectionContainer<UsrItemFinWhy> colCntnrFinWhy;
    private CollectionLoader<UsrItemFinWhy> colLoadrFinWhy;

    //Field
    @Autowired
    private JmixComboBox<String> parent1_EI1_RoleField;

    @Autowired
    private JmixComboBox<String> finStmtItm1_Txt1Field;

    @Autowired
    private JmixComboBox<String> finStmtItm1_Txt2Field;

    @Autowired
    private JmixComboBox<String> finStmtItm1_Txt3Field;

    @Autowired
    private JmixComboBox<String> finStmtItm1_Txt4Field;


    @Autowired
    private EntityComboBox<UsrNodeFinTaxLne> finTaxLne1_IdField;

    @Autowired
    private JmixComboBox<String> finTaxLne1_CodeField;

    @Autowired
    private EntityComboBox<UsrNodeFinAcct> finAcct1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinDept> finDept1_IdField;

    @Autowired
    private EntityComboBox<SysNodeFinCurcy> sysNodeFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrItemGenFmla> amtCalcGenFmla1_IdField;

    

    //Field (FinTxact)
    @Autowired
    private EntityComboBox<UsrNodeFinTxactType> parent1_Type1_IdField;

    @Autowired
    private JmixComboBox<String> parent1_Parent1_EI1_RoleField;


    //Field (FinTxact.FinTxactSet)
    @Autowired
    private EntityPicker<UsrNodeFinTxactSet> parent1_Parent1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinTxactSetType> parent1_Parent1_Type1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenChan> parent1_Parent1_GenChan1_IdField;

    @Autowired
    private EntityComboBox<UsrItemFinHow> parent1_Parent1_FinHow1_IdField;

    @Autowired
    private JmixComboBox<String> parent1_Parent1_WhatText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhat> parent1_Parent1_FinWhat1_IdField;

    @Autowired
    private JmixComboBox<String> parent1_Parent1_WhyText1Field;

    @Autowired
    private EntityComboBox<UsrItemFinWhy> parent1_Parent1_FinWhy1_IdField;


    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);
        
        //tenantField.setItems(multitenancyUiSupport.getTenantOptions());

        //filter
        //As of JMix 1.5, executing getValueComponent() returns a JmixComboBox
        //This comboBox is a child class of Vaadin component and
        //Java is not able to cast from JmixComboBox to ComboBox.

/*
        JmixComboBox<String> propFilterCmpnt_Parent1_EI1_Role = null;
        propFilterCmpnt_Parent1_EI1_Role = filterConfig1A_Parent1_EI1_Role.getValueComponent()
                .unwrap(JmixComboBox.class);
        propFilterCmpnt_Parent1_EI1_Role.setNullOptionVisible(true);
        propFilterCmpnt_Parent1_EI1_Role.setNullSelectionCaption("<null>");
        propFilterCmpnt_Parent1_EI1_Role = filterConfig1B_Parent1_EI1_Role.getValueComponent()
                .unwrap(JmixComboBox.class);
                propFilterCmpnt_Parent1_EI1_Role.setNullOptionVisible(true);
        propFilterCmpnt_Parent1_EI1_Role.setNullSelectionCaption("<null>");
*/

        Map<Integer, String> map1 = new LinkedHashMap<>();
        map1.put(0, "Skip");
        map1.put(2,"Max+1");
        map1.put(1, "");
        ComponentUtils.setItemsMap(tmplt_SortIdxFieldRdo, map1);
        ComponentUtils.setItemsMap(tmplt_Int1FieldRdo,map1);
        ComponentUtils.setItemsMap(tmplt_Int2FieldRdo,map1);

        Map<Integer,String> map2 = new LinkedHashMap<>();
        map2.put(UpdateOption.SKIP.toInt(),UpdateOption.SKIP.toString());
        map2.put(UpdateOption.LOCAL.toInt(),UpdateOption.LOCAL.toString());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST.toInt(), UpdateOption.LOCAL__REF_TO_EXIST.toString());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST_NEW.toInt(), UpdateOption.LOCAL__REF_TO_EXIST_NEW.toString());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toInt(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toString());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toInt(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toString());
        ComponentUtils.setItemsMap(updateColItemCalcValsParent1Option,map2);
        updateColItemCalcValsParent1Option.setValue(UpdateOption.LOCAL.toInt());
        ComponentUtils.setItemsMap(updateInstItemCalcValsParent1Option,map2);
        updateInstItemCalcValsParent1Option.setValue(UpdateOption.LOCAL.toInt());

        ComponentUtils.setItemsMap(updateColItemCalcValsParent1Parent1Option,map2);
        updateColItemCalcValsParent1Parent1Option.setValue(UpdateOption.LOCAL.toInt());
        ComponentUtils.setItemsMap(updateInstItemCalcValsParent1Parent1Option,map2);
        updateInstItemCalcValsParent1Parent1Option.setValue(UpdateOption.LOCAL.toInt());

        ComponentUtils.setItemsMap(updateColItemCalcValsFinStmtItmOption,map2);
        updateColItemCalcValsFinStmtItmOption.setValue(UpdateOption.LOCAL.toInt());
        ComponentUtils.setItemsMap(updateInstItemCalcValsFinStmtItmOption,map2);
        updateInstItemCalcValsFinStmtItmOption.setValue(UpdateOption.LOCAL.toInt());

        Map<Integer, String> map5 = new LinkedHashMap<>();
        map5.put(0, "Clear");
        map5.put(1, "Match Current Row");
        ComponentUtils.setItemsMap(updateFilterConfig1B_Ts1_ElTs_GERdo,map5);
        ComponentUtils.setItemsMap(updateFilterConfig1B_Int1Rdo,map5);
        ComponentUtils.setItemsMap( updateFilterConfig1B_Int2Rdo,map5);

        //todo check how to refactor setNullOptionVisible
        //tmplt_Parent1_Parent1_EI1_RoleField.setNullOptionVisible(true);
        //tmplt_Parent1_Parent1_EI1_RoleField.setNullSelectionCaption("<null>");
        //tmplt_Parent1_Parent1_WhatText1Field.setNullOptionVisible(true);
        //tmplt_Parent1_Parent1_WhatText1Field.setNullSelectionCaption("<null>");
        //tmplt_Parent1_Parent1_WhyText1Field.setNullOptionVisible(true);
        //tmplt_Parent1_Parent1_WhyText1Field.setNullSelectionCaption("<null>");

        //tmplt_Parent1_EI1_RoleField.setNullOptionVisible(true);
        //tmplt_Parent1_EI1_RoleField.setNullSelectionCaption("<null>");

        //tmplt_FinTaxLne1_CodeField.setNullOptionVisible(true);
        //tmplt_FinTaxLne1_CodeField.setNullSelectionCaption("<null>");


        //finStmtItm1_Txt1Field.setNullOptionVisible(true);
        //finStmtItm1_Txt1Field.setNullSelectionCaption("<null>");
        //finStmtItm1_Txt2Field.setNullOptionVisible(true);
        //finStmtItm1_Txt2Field.setNullSelectionCaption("<null>");
        //finStmtItm1_Txt3Field.setNullOptionVisible(true);
        //finStmtItm1_Txt3Field.setNullSelectionCaption("<null>");

        //finTaxLne1_CodeField.setNullOptionVisible(true);
        //finTaxLne1_CodeField.setNullSelectionCaption("<null>");


        //filter
        EntityComboBox<UsrNodeFinTxactItmType> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeFinTxactItmType>) filterConfig1B_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setItems(colCntnrType);


        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrItemGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrItemGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla = fetchPlans.builder(UsrItemGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getViewData().getDataContext());

        amtCalcGenFmla1_IdField.setItems(colCntnrGenFmla);
        //template
        tmplt_AmtGenFmla1_IdField.setItems(colCntnrGenFmla);


        colCntnrGenChan = dataComponents.createCollectionContainer(UsrNodeGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_UsrNodeGenChan e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenChan = fetchPlans.builder(UsrNodeGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(fchPlnGenChan);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getViewData().getDataContext());

        parent1_Parent1_GenChan1_IdField.setItems(colCntnrGenChan);
        //template
        tmplt_Parent1_Parent1_GenChan1_IdField.setItems(colCntnrGenChan);
        //filter
        EntityComboBox<UsrNodeGenChan> propFilterCmpnt_GenChan1_Id;
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1A_Parent1_Parent1_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setItems(colCntnrGenChan);
        propFilterCmpnt_GenChan1_Id = (EntityComboBox<UsrNodeGenChan>) filterConfig1B_Parent1_Parent1_GenChan1_Id.getValueComponent();
        propFilterCmpnt_GenChan1_Id.setItems(colCntnrGenChan);


        colCntnrFinHow = dataComponents.createCollectionContainer(UsrItemFinHow.class);
        colLoadrFinHow = dataComponents.createCollectionLoader();
        colLoadrFinHow.setQuery("select e from enty_UsrItemFinHow e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinHow = fetchPlans.builder(UsrItemFinHow.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinHow.setFetchPlan(fchPlnFinHow);
        colLoadrFinHow.setContainer(colCntnrFinHow);
        colLoadrFinHow.setDataContext(getViewData().getDataContext());

        parent1_Parent1_FinHow1_IdField.setItems(colCntnrFinHow);
        //template
        tmplt_Parent1_Parent1_FinHow1_IdField.setItems(colCntnrFinHow);
        //filter
        EntityComboBox<UsrItemFinHow> propFilterCmpnt_FinHow1_Id;
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1A_Parent1_Parent1_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setItems(colCntnrFinHow);
        propFilterCmpnt_FinHow1_Id = (EntityComboBox<UsrItemFinHow>) filterConfig1B_Parent1_Parent1_FinHow1_Id.getValueComponent();
        propFilterCmpnt_FinHow1_Id.setItems(colCntnrFinHow);


        colCntnrFinWhat = dataComponents.createCollectionContainer(UsrItemFinWhat.class);
        colLoadrFinWhat = dataComponents.createCollectionLoader();
        colLoadrFinWhat.setQuery("select e from enty_UsrItemFinWhat e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhat = fetchPlans.builder(UsrItemFinWhat.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhat.setFetchPlan(fchPlnFinWhat);
        colLoadrFinWhat.setContainer(colCntnrFinWhat);
        colLoadrFinWhat.setDataContext(getViewData().getDataContext());

        parent1_Parent1_FinWhat1_IdField.setItems(colCntnrFinWhat);
        //template
        tmplt_Parent1_Parent1_FinWhat1_IdField.setItems(colCntnrFinWhat);
        //filter
        EntityComboBox<UsrItemFinWhat> propFilterCmpnt_FinWhat1_Id;
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1A_Parent1_Parent1_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setItems(colCntnrFinWhat);
        propFilterCmpnt_FinWhat1_Id = (EntityComboBox<UsrItemFinWhat>) filterConfig1B_Parent1_Parent1_FinWhat1_Id.getValueComponent();
        propFilterCmpnt_FinWhat1_Id.setItems(colCntnrFinWhat);


        colCntnrFinWhy = dataComponents.createCollectionContainer(UsrItemFinWhy.class);
        colLoadrFinWhy = dataComponents.createCollectionLoader();
        colLoadrFinWhy.setQuery("select e from enty_UsrItemFinWhy e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinWhy = fetchPlans.builder(UsrItemFinWhy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinWhy.setFetchPlan(fchPlnFinWhy);
        colLoadrFinWhy.setContainer(colCntnrFinWhy);
        colLoadrFinWhy.setDataContext(getViewData().getDataContext());

        parent1_Parent1_FinWhy1_IdField.setItems(colCntnrFinWhy);
        //template
        tmplt_Parent1_Parent1_FinWhy1_IdField.setItems(colCntnrFinWhy);
        //filter
        EntityComboBox<UsrItemFinWhy> propFilterCmpnt_FinWhy1_Id;
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1A_Parent1_Parent1_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setItems(colCntnrFinWhy);
        propFilterCmpnt_FinWhy1_Id = (EntityComboBox<UsrItemFinWhy>) filterConfig1B_Parent1_Parent1_FinWhy1_Id.getValueComponent();
        propFilterCmpnt_FinWhy1_Id.setItems(colCntnrFinWhy);


        colCntnrFinTxactType = dataComponents.createCollectionContainer(UsrNodeFinTxactType.class);
        colLoadrFinTxactType = dataComponents.createCollectionLoader();
        colLoadrFinTxactType.setQuery("select e from enty_UsrNodeFinTxactType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactType = fetchPlans.builder(UsrNodeFinTxactType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactType.setFetchPlan(fchPlnFinTxactType);
        colLoadrFinTxactType.setContainer(colCntnrFinTxactType);
        colLoadrFinTxactType.setDataContext(getViewData().getDataContext());

        parent1_Type1_IdField.setItems(colCntnrFinTxactType);
        //template
        tmplt_FinTxact1_Id_Type1_IdField.setItems(colCntnrFinTxactType);


        colCntnrFinTxactSetType = dataComponents.createCollectionContainer(UsrNodeFinTxactSetType.class);
        colLoadrFinTxactSetType = dataComponents.createCollectionLoader();
        colLoadrFinTxactSetType.setQuery("select e from enty_UsrNodeFinTxactSetType e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTxactSetType = fetchPlans.builder(UsrNodeFinTxactSetType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTxactSetType.setFetchPlan(fchPlnFinTxactSetType);
        colLoadrFinTxactSetType.setContainer(colCntnrFinTxactSetType);
        colLoadrFinTxactSetType.setDataContext(getViewData().getDataContext());

        parent1_Parent1_Type1_IdField.setItems(colCntnrFinTxactSetType);
        //template
        tmplt_Parent1_Parent1_Type1_IdField.setItems(colCntnrFinTxactSetType);
        //filter
        EntityComboBox<UsrNodeFinTxactSetType> propFilterCmpnt_Parent1_Parent1_Type1_Id;
        propFilterCmpnt_Parent1_Parent1_Type1_Id = (EntityComboBox<UsrNodeFinTxactSetType>) filterConfig1A_Parent1_Parent1_Type1_Id.getValueComponent();
        propFilterCmpnt_Parent1_Parent1_Type1_Id.setItems(colCntnrFinTxactSetType);
        propFilterCmpnt_Parent1_Parent1_Type1_Id = (EntityComboBox<UsrNodeFinTxactSetType>) filterConfig1B_Parent1_Parent1_Type1_Id.getValueComponent();
        propFilterCmpnt_Parent1_Parent1_Type1_Id.setItems(colCntnrFinTxactSetType);


        colCntnrFinStmt = dataComponents.createCollectionContainer(UsrNodeFinStmt.class);
        colLoadrFinStmt = dataComponents.createCollectionLoader();
        colLoadrFinStmt.setQuery("select e from enty_UsrNodeFinStmt e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinStmt = fetchPlans.builder(UsrNodeFinStmt.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinStmt.setFetchPlan(fchPlnFinStmt);
        colLoadrFinStmt.setContainer(colCntnrFinStmt);
        colLoadrFinStmt.setDataContext(getViewData().getDataContext());

        //filter
        EntityComboBox<UsrNodeFinStmt> propFilterCmpnt_FinStmt1_Id;
        propFilterCmpnt_FinStmt1_Id = (EntityComboBox<UsrNodeFinStmt>) filterConfig1A_FinStmtItm1_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_FinStmt1_Id.setItems(colCntnrFinStmt);
        propFilterCmpnt_FinStmt1_Id = (EntityComboBox<UsrNodeFinStmt>) filterConfig1B_FinStmtItm1_FinStmt1_Id.getValueComponent();
        propFilterCmpnt_FinStmt1_Id.setItems(colCntnrFinStmt);


        colCntnrFinTaxLne = dataComponents.createCollectionContainer(UsrNodeFinTaxLne.class);
        colLoadrFinTaxLne = dataComponents.createCollectionLoader();
        colLoadrFinTaxLne.setQuery("select e from enty_UsrNodeFinTaxLne e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTaxLne = fetchPlans.builder(UsrNodeFinTaxLne.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTaxLne.setFetchPlan(fchPlnFinTaxLne);
        colLoadrFinTaxLne.setContainer(colCntnrFinTaxLne);
        colLoadrFinTaxLne.setDataContext(getViewData().getDataContext());

        finTaxLne1_IdField.setItems(colCntnrFinTaxLne);
        //template
        tmplt_FinTaxLne1_IdField.setItems(colCntnrFinTaxLne);


        colCntnrFinAcct = dataComponents.createCollectionContainer(UsrNodeFinAcct.class);
        colLoadrFinAcct = dataComponents.createCollectionLoader();
        colLoadrFinAcct.setQuery("select e from enty_UsrNodeFinAcct e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinAcct = fetchPlans.builder(UsrNodeFinAcct.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinAcct.setFetchPlan(fchPlnFinAcct);
        colLoadrFinAcct.setContainer(colCntnrFinAcct);
        colLoadrFinAcct.setDataContext(getViewData().getDataContext());

        finAcct1_IdField.setItems(colCntnrFinAcct);
        //template
        tmplt_FinAcct1_IdField.setItems(colCntnrFinAcct);
        //filter
        EntityComboBox<UsrNodeFinAcct> propFilterCmpnt_FinAcct1_Id;
        propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNodeFinAcct>) filterConfig1A_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setItems(colCntnrFinAcct);
        propFilterCmpnt_FinAcct1_Id = (EntityComboBox<UsrNodeFinAcct>) filterConfig1B_FinAcct1_Id.getValueComponent();
        propFilterCmpnt_FinAcct1_Id.setItems(colCntnrFinAcct);


        colCntnrFinDept = dataComponents.createCollectionContainer(UsrNodeFinDept.class);
        colLoadrFinDept = dataComponents.createCollectionLoader();
        colLoadrFinDept.setQuery("select e from enty_UsrNodeFinDept e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinDept = fetchPlans.builder(UsrNodeFinDept.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinDept.setFetchPlan(fchPlnFinDept);
        colLoadrFinDept.setContainer(colCntnrFinDept);
        colLoadrFinDept.setDataContext(getViewData().getDataContext());

        finDept1_IdField.setItems(colCntnrFinDept);
        //template
        tmplt_FinDept1_IdField.setItems(colCntnrFinDept);
        //filter
        EntityComboBox<UsrNodeFinDept> propFilterCmpnt_FinDept1_Id;
        propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNodeFinDept>) filterConfig1A_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setItems(colCntnrFinDept);
        propFilterCmpnt_FinDept1_Id = (EntityComboBox<UsrNodeFinDept>) filterConfig1B_FinDept1_Id.getValueComponent();
        propFilterCmpnt_FinDept1_Id.setItems(colCntnrFinDept);


        colCntnrSysNodeFinCurcy = dataComponents.createCollectionContainer(SysNodeFinCurcy.class);
        colLoadrSysNodeFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysNodeFinCurcy.setQuery("select e from enty_SysNodeFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnSysNodeFinCurcy = fetchPlans.builder(SysNodeFinCurcy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysNodeFinCurcy.setFetchPlan(fchPlnSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setContainer(colCntnrSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setDataContext(getViewData().getDataContext());

        sysNodeFinCurcy1_IdField.setItems(colCntnrSysNodeFinCurcy);
        //template
        tmplt_SysNodeFinCurcy1_IdField.setItems(colCntnrSysNodeFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_SysNodeFinCurcy1_Id;
        propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setItems(colCntnrSysNodeFinCurcy);
        propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1B_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setItems(colCntnrSysNodeFinCurcy);


        logger.trace(logPrfx + " <-- ");


    }


    @Install(to = "dataGridMain.[ts1.elTs]", subject = "formatter")
    private String dataGridMainTs1_ElTsFormatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        reloadParent1_Parent1_EI1_RoleList();

        logger.debug(logPrfx + " --- executing colLoadrFinTxactType.load() ");
        colLoadrFinTxactType.load();
        logger.debug(logPrfx + " --- finished colLoadrFinTxactType.load() ");

        reloadParent1_EI1_RoleList();

        logger.debug(logPrfx + " --- executing colLoadrFinTxactSetType.load() ");
        colLoadrFinTxactSetType.load();
        logger.debug(logPrfx + " --- finished colLoadrFinTxactSetType.load() ");

        logger.debug(logPrfx + " --- executing colLoadrGenFmla.load() ");
        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- finished colLoadrGenFmla.load() ");

        logger.debug(logPrfx + " --- executing colLoadrGenChan.load() ");
        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- finished colLoadrGenChan.load() ");

        logger.debug(logPrfx + " --- executing colLoadrFinHow.load() ");
        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- finished colLoadrFinHow.load() ");

        reloadParent1Parent1WhatText1List();

        logger.debug(logPrfx + " --- executing colLoadrFinWhat.load() ");
        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- finished colLoadrFinWhat.load() ");

        reloadParent1Parent1WhyText1List();
        logger.debug(logPrfx + " --- executing colLoadrFinWhy.load() ");
        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- finished colLoadrFinWhy.load() ");

        logger.debug(logPrfx + " --- executing colLoadrFinStmt.load() ");
        colLoadrFinStmt.load();
        logger.debug(logPrfx + " --- finished colLoadrFinStmt.load() ");

        reloadFinStmtItm1_Txt1List();
        reloadFinStmtItm1_Txt2List();
        reloadFinStmtItm1_Txt3List();
        reloadFinStmtItm1_Txt4List();

        logger.debug(logPrfx + " --- executing colLoadrFinAcct.load() ");
        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- finished colLoadrFinAcct.load() ");

        logger.debug(logPrfx + " --- executing colLoadrFinDept.load() ");
        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- finished colLoadrFinDept.load() ");

        logger.debug(logPrfx + " --- executing colLoadrSysNodeFinCurcy.load() ");
        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- finished colLoadrSysNodeFinCurcy.load() ");

        logger.debug(logPrfx + " --- executing colLoadrFinTaxLne.load() ");
        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- finished colLoadrFinTaxLne.load() ");
        reloadFinTaxLne1_CodeList();

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("splitBtn")
    public void onSplitBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSplitBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxactItm> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is empty, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<UsrNodeFinTxactItm> sels = new ArrayList<>();

        thisNodes.forEach(orig -> {

            UsrNodeFinTxactItm copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

            service.updateInst1(this, copy, updOption);
            service.updateName1(this, copy, updOption);
            service.updateId2Calc(this, copy, updOption);
            service.updateId2(this, copy, updOption);
            if (Objects.equals(orig.getId2(), copy.getId2())) {
                copy.setSortIdx(copy.getSortIdx() == null ? 1 : copy.getSortIdx() + 1);
                service.updateInst1(this, copy, updOption);
                service.updateName1(this, copy, updOption);
                service.updateId2Calc(this, copy, updOption);
                service.updateId2(this, copy, updOption);
            }

            // on split clear the account
            copy.setFinAcct1_Id(null);
            // on split clear the statement item
            copy.setFinStmtItm1_Id(null);


            if (copy.getAmtDebt() != null) {
                copy.setAmtCred(copy.getAmtDebt());
                copy.setAmtDebt(null);
                copy.setParent1_EI1_Role("fr");
            } else if (copy.getAmtCred() != null) {
                copy.setAmtDebt(copy.getAmtCred());
                copy.setAmtCred(null);
                copy.setParent1_EI1_Role("to");
            }

            if (tmplt_FinTaxLne1_IdFieldChk.getValue()) {
                copy.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
            }
            if (tmplt_FinTaxLne1_CodeFieldChk.getValue()) {
                copy.setFinTaxLne1_Code(tmplt_FinTaxLne1_CodeField.getValue());
            }
            if (tmplt_Parent1_EI1_RoleFieldChk.getValue()) {
                copy.setParent1_EI1_Role(tmplt_Parent1_EI1_RoleField.getValue());
            }
            if (tmplt_FinAcct1_IdFieldChk.getValue()) {
                copy.setFinAcct1_Id(tmplt_FinAcct1_IdField.getValue());
            }
            if (tmplt_FinDept1_IdFieldChk.getValue()) {
                copy.setFinDept1_Id(tmplt_FinDept1_IdField.getValue());
            }
            if (tmplt_SysNodeFinCurcy1_IdFieldChk.getValue()) {
                copy.setSysNodeFinCurcy1_Id(tmplt_SysNodeFinCurcy1_IdField.getValue());
            }

            service.updateCalcVals(this, copy, updOption);

            UsrNodeFinTxactItm savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Split " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        //todo check how to dataGridMain.setSelected
        //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
        //dataGridMain.setSelected(sels);
        
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    public UsrNodeFinTxactItm onDuplicateBtnClickHelper(UsrNodeFinTxactItm orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (tmplt_Type1_IdFieldChk.getValue()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_Parent1_EI1_RoleFieldChk.getValue()) {
            copy.setParent1_EI1_Role(tmplt_Parent1_EI1_RoleField.getValue());
        }
        if (tmplt_Ts1_ElTsFieldChk.getValue()) {
            copy.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
        }
        if (tmplt_Ts2_ElTsFieldChk.getValue()) {
            copy.getTs2().setElTs(tmplt_Ts2_ElTsField.getValue());
        }

        LocalDateTime ts1_ElTs = copy.getTs1() != null ? copy.getTs1().getElTs() : null;

        UsrNodeFinTxactItmGrpg grpg = new UsrNodeFinTxactItmGrpg(
                copy.getParent1_Id()
                , copy.getTs1() == null ? null : copy.getTs1().getElTs()
                , copy.getInt1()
                , copy.getInt2()
        );

        Integer int1 = copy.getInt1();
        if (tmplt_Int1FieldRdo.getValue() != null) {
            // Set
            if (tmplt_Int1FieldRdo.getValue() == 1) {
                int1 = tmplt_Int1Field.getTypedValue();
                copy.setInt1(int1);
            }
            // Max+1
            else if (tmplt_Int1FieldRdo.getValue() == 2
                    && ts1_ElTs != null) {
                Integer int1Max = service.getInt1Max(this, grpg);
                int1 = int1Max == null ? null : int1Max + 1;
                if (int1 != null){
                    copy.setInt1(int1);
                };
            }
        }

        Integer int2 = copy.getInt2();
        if (tmplt_Int2FieldRdo.getValue() != null) {
            // Set
            if (tmplt_Int2FieldRdo.getValue() == 1) {
                int2 = tmplt_Int2Field.getTypedValue();
                copy.setInt2(int2);
            }
            // Max
            else if (tmplt_Int2FieldRdo.getValue() == 2
                    && ts1_ElTs != null) {
                Integer int2Max = service.getInt2Max(this, grpg);
                int2 = int2Max == null ? null : int2Max + 1;
                if (int2 != null){
                    copy.setInt2(int2);
                };
            }

        }

        Integer sortIdx = copy.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null) {
            // Set
            if (tmplt_SortIdxFieldRdo.getValue() == 1) {
                sortIdx = tmplt_SortIdxField.getTypedValue();
                copy.setSortIdx(sortIdx);
            }
            // Max+1
            else if (tmplt_SortIdxFieldRdo.getValue() == 2
                    && ts1_ElTs != null) {
                Integer sortIdxMax = service.getSortIdxMax(this, grpg);
                if (sortIdxMax != null){
                    sortIdx = sortIdxMax + 1;
                    copy.setSortIdx(sortIdx);
                }
            }
        }

        if (tmplt_FinAcct1_IdFieldChk.getValue()) {
            copy.setFinAcct1_Id(tmplt_FinAcct1_IdField.getValue());
        }

        if (tmplt_FinDept1_IdFieldChk.getValue()) {
            copy.setFinDept1_Id(tmplt_FinDept1_IdField.getValue());
        }

        if (tmplt_AmtDebtFieldChk.getValue()) {
            copy.setAmtDebt(tmplt_AmtDebtField.getTypedValue());
        }

        if (tmplt_AmtCredFieldChk.getValue()) {
            copy.setAmtCred(tmplt_AmtCredField.getTypedValue());
        }

        if (tmplt_SysNodeFinCurcy1_IdFieldChk.getValue()) {
            copy.setSysNodeFinCurcy1_Id(tmplt_SysNodeFinCurcy1_IdField.getValue());
        }


        Optional<UsrNodeBase> o_copyParent1_Parent1 = Optional.ofNullable(copy.getParent1_Id())
                .map(UsrNodeBase::getParent1_Id);
        if (o_copyParent1_Parent1.isPresent()){
            UpdateOption updOptParent1Parent1 = UpdateOption.valueOf(updateColItemCalcValsParent1Parent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1Parent1.updateCalcVals(this, o_copyParent1_Parent1.get(), updOptParent1Parent1);
        }

        Optional<UsrNodeBase> o_copyParent = Optional.ofNullable(copy.getParent1_Id());
        if (o_copyParent.isPresent()){
            UpdateOption updOptParent1 = UpdateOption.valueOf(updateColItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateCalcVals(this, o_copyParent.get(), updOptParent1);

        }

        Optional<UsrNodeFinStmtItm> o_copyFinStmtItm = Optional.ofNullable(copy.getFinStmtItm1_Id());
        if (o_copyFinStmtItm.isPresent()){
            UpdateOption upOptFinStmtItm = UpdateOption.valueOf(updateColItemCalcValsFinStmtItmOption.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceFinStmtItm.updateCalcVals(this, o_copyFinStmtItm.get(), upOptFinStmtItm);

        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        service.updateInst1(this, copy, updOption);
        service.updateName1(this, copy, updOption);
        service.updateId2Calc(this, copy, updOption);
        service.updateId2(this, copy, updOption);

        if (Objects.equals(copy.getId2(), orig.getId2())) {

        }else{
            Integer sortIdxMax = service.getSortIdxMax(this, grpg);
            if (sortIdxMax != null){
                sortIdx = sortIdxMax + 1;
                copy.setSortIdx(sortIdx);
                service.updateId2Calc(this, copy, updOption);
                service.updateId2(this, copy, updOption);
            }
        }
        service.updateCalcVals(this, copy, updOption);

        logger.trace(logPrfx + " <-- ");
        return copy;
    }


    @Override
    public Boolean onSetBtnClickHelper(UsrNodeFinTxactItm thisNode){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisNodeIsChanged = false;

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        if (tmplt_Type1_IdFieldChk.getValue()
        ) {
            thisNodeIsChanged = true;
            thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_Parent1_EI1_RoleFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setParent1_EI1_Role(tmplt_Parent1_EI1_RoleField.getValue());
        }

        if (tmplt_Ts1_ElTsFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.getTs1().setElTs(tmplt_Ts1_ElTsField.getValue());
        }
        if (tmplt_Ts2_ElTsFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.getTs2().setElTs(tmplt_Ts2_ElTsField.getValue());
        }
        LocalDateTime ts1_ElTs = thisNode.getTs1() != null ? thisNode.getTs1().getElTs() : null;

        UsrNodeFinTxactItmGrpg grpg = new UsrNodeFinTxactItmGrpg(
                thisNode.getParent1_Id()
                , thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs()
                , thisNode.getInt1()
                , thisNode.getInt2()
        );

        Integer int1 = thisNode.getInt1();
        if (tmplt_Int1FieldRdo.getValue() != null) {
            // Set
            if (tmplt_Int1FieldRdo.getValue() == 1) {
                thisNodeIsChanged = true;
                int1 = tmplt_Int1Field.getTypedValue();
                thisNode.setInt1(int1);
            }
            // Max+1
            else if (tmplt_Int1FieldRdo.getValue() == 2
                    && ts1_ElTs != null) {
                thisNodeIsChanged = true;
                Integer int1Max = service.getInt1Max(this, grpg);
                if (int1Max != null){
                    int1 = int1Max + 1;
                    thisNode.setInt1(int1);
                }
            }
        }

        Integer int2 = thisNode.getInt2();
        if (tmplt_Int2FieldRdo.getValue() != null) {
            // Set
            if (tmplt_Int2FieldRdo.getValue() == 1) {
                thisNodeIsChanged = true;
                int2 = tmplt_Int2Field.getTypedValue();
                thisNode.setInt2(int2);
            }
            // Max+1
            else if (tmplt_Int2FieldRdo.getValue() == 2
                    && ts1_ElTs != null) {
                thisNodeIsChanged = true;
                Integer int2Max = service.getInt2Max(this, grpg);
                if (int2Max != null){
                    int2 = int2Max + 1;
                    thisNode.setInt2(int2);
                }
            }
        }

        Integer sortIdx = thisNode.getSortIdx();
        if (tmplt_SortIdxFieldRdo.getValue() != null) {
            // Set
            if (tmplt_SortIdxFieldRdo.getValue() == 1) {
                thisNodeIsChanged = true;
                sortIdx = tmplt_SortIdxField.getTypedValue();
                thisNode.setSortIdx(sortIdx);
            }
            // Max+1
            else if (tmplt_SortIdxFieldRdo.getValue() == 2
                    && ts1_ElTs != null) {
                thisNodeIsChanged = true;

                Integer sortIdxMax = service.getSortIdxMax(this, grpg);
                if (sortIdxMax != null){
                    sortIdx = sortIdxMax + 1;
                    thisNode.setSortIdx(sortIdx);
                }
            }
        }

        if (tmplt_FinTaxLne1_IdFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
        }

        if (tmplt_FinTaxLne1_CodeFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setFinTaxLne1_Code(tmplt_FinTaxLne1_CodeField.getValue());
        }


        if (tmplt_FinAcct1_IdFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setFinAcct1_Id(tmplt_FinAcct1_IdField.getValue());
        }

        if (tmplt_FinDept1_IdFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setFinDept1_Id(tmplt_FinDept1_IdField.getValue());
        }

        if (tmplt_AmtDebtFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setAmtDebt(tmplt_AmtDebtField.getTypedValue());
        }

        if (tmplt_AmtCredFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setAmtCred(tmplt_AmtCredField.getTypedValue());
        }

        if (tmplt_SysNodeFinCurcy1_IdFieldChk.getValue()) {
            thisNodeIsChanged = true;
            thisNode.setSysNodeFinCurcy1_Id(tmplt_SysNodeFinCurcy1_IdField.getValue());
        }

        thisNodeIsChanged = service.updateId2Calc(this, thisNode, updOption) || thisNodeIsChanged;
        thisNodeIsChanged = service.updateId2(this, thisNode, updOption) || thisNodeIsChanged;
        thisNodeIsChanged = service.updateId2Cmp(this, thisNode, updOption) || thisNodeIsChanged;

        if (thisNodeIsChanged) {
            logger.debug(logPrfx + " --- executing dataManager.save(thisNode).");
            //dataManager.save(thisNode);
        }

        UsrNodeFinTxact thisFinTxact = thisNode.getFinTxact1_Id();
        if (thisFinTxact != null) {
            thisFinTxact = dataContext.merge(thisFinTxact);

            boolean thisFinTxactIsChanged = false;

            if (tmplt_FinTxact1_Id_Type1_IdFieldChk.getValue()) {
                thisFinTxactIsChanged = true;
                thisFinTxact.setType1_Id(tmplt_FinTxact1_Id_Type1_IdField.getValue());
            }

            if (tmplt_Parent1_Parent1_EI1_RoleFieldChk.getValue()) {
                thisFinTxactIsChanged = true;
                thisFinTxact.setFinTxactSet1_EI1_Role(tmplt_Parent1_Parent1_EI1_RoleField.getValue());
            }


            if (thisFinTxactIsChanged) {
                logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxact).");
                //dataManager.save(thisFinTxact);
            }
        }

        UsrNodeFinTxactSet thisFinTxactSet = thisNode.getFinTxact1_Id() == null ? null : thisNode.getFinTxact1_Id().getFinTxactSet1_Id();
        if (thisFinTxactSet != null) {
            thisFinTxactSet = dataContext.merge(thisFinTxactSet);

            boolean thisFinTxactSetIsChanged = false;

            if (tmplt_Parent1_Parent1_Type1_IdFieldChk.getValue()) {
                thisFinTxactSetIsChanged = true;
                thisFinTxactSet.setType1_Id(tmplt_Parent1_Parent1_Type1_IdField.getValue());
            }

            if (tmplt_Parent1_Parent1_GenChan1_IdFieldChk.getValue()) {
                thisFinTxactSetIsChanged = true;
                thisFinTxactSet.setGenChan1_Id(tmplt_Parent1_Parent1_GenChan1_IdField.getValue());
            }

            if (tmplt_Parent1_Parent1_FinHow1_IdFieldChk.getValue()) {
                thisFinTxactSetIsChanged = true;
                thisFinTxactSet.setFinHow1_Id(tmplt_Parent1_Parent1_FinHow1_IdField.getValue());
            }

            if (tmplt_Parent1_Parent1_WhatText1FieldChk.getValue()) {
                thisFinTxactSetIsChanged = true;
                thisFinTxactSet.setWhatText1(tmplt_Parent1_Parent1_WhatText1Field.getValue());
            }

            if (tmplt_Parent1_Parent1_FinWhat1_IdFieldChk.getValue()) {
                thisFinTxactSetIsChanged = true;
                thisFinTxactSet.setFinWhat1_Id(tmplt_Parent1_Parent1_FinWhat1_IdField.getValue());
            }

            if (tmplt_Parent1_Parent1_WhyText1FieldChk.getValue()) {
                thisFinTxactSetIsChanged = true;
                thisFinTxactSet.setWhyText1(tmplt_Parent1_Parent1_WhyText1Field.getValue());
            }

            if (tmplt_Parent1_Parent1_FinWhy1_IdFieldChk.getValue()) {
                thisFinTxactSetIsChanged = true;
                thisFinTxactSet.setFinWhy1_Id(tmplt_Parent1_Parent1_FinWhy1_IdField.getValue());
            }

            if (thisFinTxactSetIsChanged) {
                logger.debug(logPrfx + " --- executing dataManager.save(thisFinTxactSet).");
                //dataManager.save(thisFinTxactSet);
            }

        }

        thisNodeIsChanged = service.updateCalcVals(this, thisNode, updOption) || thisNodeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }


    @Subscribe("updateFilterConfig1A_Ts1_ElTs_GE_DecMonBtn")
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_DecMonBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_DecMonBtnClick";
        logger.trace(logPrfx + " --> ");

        TypedDateTimePicker<LocalDateTime> propFilterCmpnt_Ts1_ElTs_GE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
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

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.getValue()){
            TypedDateTimePicker<LocalDateTime> propFilterCmpnt_Ts1_ElTs_LE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
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
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFilterConfig1A_Ts1_ElTs_GE_IncMonBtn")
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_IncMonBtnBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_IncMonBtnBtnClick";
        logger.trace(logPrfx + " --> ");

        TypedDateTimePicker<LocalDateTime> propFilterCmpnt_Ts1_ElTs_GE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
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
        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.getValue()) {
            TypedDateTimePicker<LocalDateTime> propFilterCmpnt_Ts1_ElTs_LE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
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
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_DecDayBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_DecDayBtnClick";
        logger.trace(logPrfx + " --> ");

        TypedDateTimePicker<LocalDateTime> cmpnt_Ts1_ElTs_GE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
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

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.getValue()) {
            TypedDateTimePicker<LocalDateTime> cmpnt_Ts1_ElTs_LE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
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
    public void onUpdateFilterConfig1A_Ts1_ElTs_GE_IncDayClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFilterConfig1A_Ts1_ElTs_GE_IncDayClick";
        logger.trace(logPrfx + " --> ");

        TypedDateTimePicker<LocalDateTime> cmpnt_Ts1_ElTs_GE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_GE.getValueComponent();
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

        if (updateFilterConfig1A_Ts1_ElTs_LE_SyncChk.getValue()) {
            TypedDateTimePicker<LocalDateTime> cmpnt_Ts1_ElTs_LE = (TypedDateTimePicker<LocalDateTime>) filterConfig1A_Ts1_ElTs_LE.getValueComponent();
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
    public void onUpdateFilterConfig1B_Ts1_ElTs_GEBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFilterConfig1B_Ts1_ElTs_GEBtnClick";
        logger.trace(logPrfx + " --> ");

        Integer idDtGEOption = updateFilterConfig1B_Ts1_ElTs_GERdo.getValue();
        switch (idDtGEOption) {
            case 0 -> // Clear
                    filterConfig1B_Ts1_ElTs_GE.clear();
            case 1 -> {  // Set to match current row
                List<UsrNodeFinTxactItm> thisNodes = dataGridMain.getSelectedItems().stream().toList();
                if (thisNodes.isEmpty()) {
                    logger.debug(logPrfx + " --- thisNodes is empty, likely because no records are selected.");
                    notifications.create("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeFinTxactItm thisNode = thisNodes.get(0);
                if (thisNode != null) {
                    if (thisNode.getTs1() != null && thisNode.getTs1().getElTs() != null) {
                        filterConfig1B_Ts1_ElTs_GE.setValue(thisNode.getTs1().getElTs());
                        filterConfig1B_Ts1_ElTs_GE.apply();
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1B_Int1Btn")
    public void onUpdateFilterConfig1B_Int1BtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFilterConfig1B_Int1BtnClick";
        logger.trace(logPrfx + " --> ");

        Integer int1Option = updateFilterConfig1B_Int1Rdo.getValue();
        switch (int1Option) {
            case 0 -> // Clear
                    filterConfig1B_Int1.clear();
            case 1 -> {  // Set to match current row
                List<UsrNodeFinTxactItm> thisNodes = dataGridMain.getSelectedItems().stream().toList();
                if (thisNodes.isEmpty()) {
                    logger.debug(logPrfx + " --- thisNodes is empty, likely because no records are selected.");
                    notifications.create("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeFinTxactItm thisNode = thisNodes.get(0);
                if (thisNode != null) {
                    if (thisNode.getInt1() != null) {
                        filterConfig1B_Int1.setValue(thisNode.getInt1());
                        filterConfig1B_Int1.apply();
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFilterConfig1B_Int2Btn")
    public void onUpdateFilterConfig1B_Int2BtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFilterConfig1B_Int2BtnClick";
        logger.trace(logPrfx + " --> ");

        Integer int2Option = updateFilterConfig1B_Int2Rdo.getValue();
        switch (int2Option) {
            case 0 -> // Clear
                    filterConfig1B_Int2.clear();
            case 1 -> {  // Set to match current row
                List<UsrNodeFinTxactItm> thisNodes = dataGridMain.getSelectedItems().stream().toList();
                if (thisNodes.isEmpty()) {
                    logger.debug(logPrfx + " --- thisNodes is empty, likely because no records are selected.");
                    notifications.create("No records selected. Please select one or more record.").show();
                    logger.trace(logPrfx + " <-- ");
                    return;
                }
                UsrNodeFinTxactItm thisNode = thisNodes.get(0);
                if (thisNode != null) {
                    if (thisNode.getInt2() != null) {
                        filterConfig1B_Int2.setValue(thisNode.getInt2());
                        filterConfig1B_Int2.apply();
                    }
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxactItm> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodes is empty, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UpdateOption updOptionFinTxactSet = UpdateOption.valueOf(updateColItemCalcValsParent1Parent1Option.getValue())
                .orElse(UpdateOption.SKIP);
        UpdateOption updOptionFinTxact = UpdateOption.valueOf(updateColItemCalcValsParent1Option.getValue())
                .orElse(UpdateOption.SKIP);
        UpdateOption updOptionFinStmt = UpdateOption.valueOf(updateColItemCalcValsFinStmtItmOption.getValue())
                .orElse(UpdateOption.SKIP);
        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {
                thisNode = dataContext.merge(thisNode);

                boolean isChanged = false;

                Optional<UsrNodeFinTxactSet> o_finTxactSet = Optional.ofNullable(thisNode.getFinTxactSet1_Id());
                if (o_finTxactSet.isPresent()){
                    isChanged = serviceParent1Parent1.updateCalcVals(this, o_finTxactSet.get(), updOptionFinTxactSet) || isChanged;
                }

                Optional<UsrNodeFinTxact> o_finTxact = Optional.ofNullable(thisNode.getFinTxact1_Id());
                if (o_finTxact.isPresent()){
                    isChanged = serviceParent1.updateCalcVals(this, o_finTxact.get(), updOptionFinTxact) || isChanged;
                }

                Optional<UsrNodeFinStmtItm> o_finStmtItm = Optional.ofNullable(thisNode.getFinStmtItm1_Id());
                if (o_finStmtItm.isPresent()){
                    isChanged = serviceFinStmtItm.updateCalcVals(this, o_finStmtItm.get(), updOptionFinStmt) || isChanged;
                }

                isChanged = service.updateCalcVals(this, thisNode, updOption);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.save().");
            dataContext.save();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //todo check how to dataGridMain.setSelected
            //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
            /*
            try{dataGridMain.setSelected(thisNodes);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create("Unable to keep all previous selections.").show();
            }
            */
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("tmplt_Parent1_Parent1_WhatText1Field")
    public void onTmplt_Parent1_Parent1_WhatText1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Parent1_Parent1_WhatText1FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("tmplt_Parent1_Parent1_WhyText1Field")
    public void onTmplt_Parent1_Parent1_WhyText1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Parent1_Parent1_WhyText1FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("tmplt_Parent1_Parent1_EI1_RoleField")
    public void onTmplt_Parent1_Parent1_EI1_RoleFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Parent1_Parent1_EI1_RoleFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("tmplt_Parent1_EI1_RoleField")
    public void onTmplt_Parent1_EI1_RoleFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Parent1_EI1_RoleFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("tmplt_WhatText1Field")
    public void onTmplt_WhatText1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Parent1_EI1_RoleFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("tmplt_WhyText1Field")
    public void onTmplt_WhyText1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onTmplt_Parent1_EI1_RoleFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    

    @Subscribe("linkAsRef1Btn")
    public void onLinkAsRef1BtnClick(ClickEvent<Button> event) {
        String logPrfx = "onLinkAsRef1BtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeFinTxactItm> thisNodes = dataGridMain.getSelectedItems().stream().toList();
        if (thisNodes.size() < 2) {
            logger.debug(logPrfx + " --- thisNodes contains less that 2 items.");
            notifications.create("Less than 2 records selected. Please select 2 records.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        UsrNodeFinTxactItm selFinTxactItm0Tracked = dataContext.merge(thisNodes.get(0));
        UsrNodeFinTxactItm selFinTxactItm1Tracked = dataContext.merge(thisNodes.get(1));

        if (selFinTxactItm0Tracked != null && selFinTxactItm1Tracked != null) {
            selFinTxactItm0Tracked.setAmtCalcFinTxactItm1_Id(selFinTxactItm1Tracked);
            logger.debug("Linked FinTxactItm "
                    + selFinTxactItm0Tracked.getId2() + " [" + selFinTxactItm0Tracked.getId() + "]"
                    + " Ref1 linked to "
                    + selFinTxactItm1Tracked.getId2() + " [" + selFinTxactItm1Tracked.getId() + "]"
            );
        }
        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.save().");
            dataContext.save();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            //todo check how to dataGridMain.setSelected
            //dataGridMain.sort("id2", Table.SortDirection.ASCENDING);
            //dataGridMain.setSelected(thisNodes);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateIdPartsFrId2(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }


        Optional<UsrNodeFinStmtItm> o_finStmtItm = Optional.ofNullable(thisNode.getFinStmtItm1_Id());
        if (o_finStmtItm.isPresent()){
            UpdateOption updOptionFinStmt = UpdateOption.valueOf(updateInstItemCalcValsFinStmtItmOption.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceFinStmtItm.updateCalcVals(this, o_finStmtItm.get(), updOptionFinStmt);
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        Optional<UsrNodeBase> o_parent1 = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateCalcVals(this, o_parent1.get(), updOptionParent1);

            Optional<UsrNodeBase> o_parent1_Parent1 = Optional.ofNullable(o_parent1.get().getParent1_Id());
            if (o_parent1_Parent1.isPresent()){
                UpdateOption updOptionParent1Parent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Parent1Option.getValue())
                        .orElse(UpdateOption.SKIP);
                serviceParent1Parent1.updateCalcVals(this, o_parent1_Parent1.get(), updOptionParent1Parent1);
            }
        }

        logger.trace(logPrfx + " <-- ");
    }

    //Main Field

    @Install(to = "parent1_IdField.entityLookup", subject = "screenConfigurer")
    private void  parent1_IdFieldLookupScreenConfigurer(View view) {
        String logPrfx = "parent1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(view instanceof UsrNodeFinTxact0Lookup view2){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            view2.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            view2.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int2 = thisNode.getInt2();
            view2.getFilterConfig1A_SortIdx().setValue(int2);

            Integer int1 = thisNode.getInt1();
            view2.getFilterConfig1A_SortIdx().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("ts1_ElTsField")
    public void onTs1_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1_ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
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
    public void onUpdateTs1_ElTsFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTs1_ElTsFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateTs1(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("extractTs2_ElTsFieldBtn")
    public void onUpdateTs2_ElTsFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateTs2_ElTsFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateTs2(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("int1Field")
    public void onInt1FieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onInt1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
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
    public void onUpdateInt1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInt1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateInt1(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("int2Field")
    public void onInt2FieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onInt2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateInt2Deps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInt2FieldBtn")
    public void onUpdateInt2FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateInt2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateInt2(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("parent1_IdField")
    public void onParent1_IdFieldValueChange(HasValue.ValueChangeEvent<UsrNodeFinTxactItm> event) {
        String logPrfx = "onParent1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateParent1_IdDeps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateParent1_IdFieldBtn")
    public void onUpdateParent1_IdFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateParent1_Id(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("parent1_EI1_RoleField")
    public void onParent1_EI1_RoleFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onParent1_EI1_RoleFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_EI1_RoleFieldListBtn")
    public void onUpdateParent1_EI1_RoleFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_EI1_RoleFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadParent1_EI1_RoleList();

        logger.trace(logPrfx + " <-- ");
    }

    //Parent1 (FinTxact) Field


    @Install(to = "parent1_Parent1_IdField.entityLookup", subject = "screenConfigurer")
    private void  parent1_Parent1_IdFieldLookupScreenConfigurer(View view) {
        String logPrfx = "parent1_Parent1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(view instanceof UsrNodeFinTxactSet0Lookup view2){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            view2.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            view2.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getInt1();
            view2.getFilterConfig1A_SortIdx().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("parent1_Parent1_IdField")
    public void onParent1_Parent1_IdFieldValueChange(HasValue.ValueChangeEvent<UsrNodeFinTxactItm> event) {
        String logPrfx = "onParent1_Parent1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            Optional<UsrNodeBase> o_parent1_Parent1_Id = Optional.ofNullable(thisNode.getParent1_Id()).map(n -> n.getParent1_Id());
            if (o_parent1_Parent1_Id.isPresent()){
                UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                        .orElse(UpdateOption.SKIP);
                serviceParent1.updateParent1_IdDeps(this, o_parent1_Parent1_Id.get(), updOptionParent1);
            }
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateParent1_Parent1_IdFieldBtn")
    public void onUpdateParent1_Parent1_IdFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateParent1_Id(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Type1_IdFieldListBtn")
    public void onUpdateParent1_Type1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinTxactType.load() ");
        colLoadrFinTxactType.load();
        logger.debug(logPrfx + " --- finished colLoadrFinTxactType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Desc1FieldBtn")
    public void onUpdateParent1_Desc1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateDesc1(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("parent1_Parent1_EI1_RoleField")
    public void onParent1_Parent1_EI1_RoleFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onParent1_Parent1_EI1_RoleFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_EI1_RoleFieldListBtn")
    public void onUpdateParent1_Parent1_EI1_RoleFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_EI1_RoleFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadParent1_Parent1_EI1_RoleList();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_FinTxactItms1_IdCntCalcFieldBtn")
    public void onUpdateParent1_FinTxactItms1_IdCntCalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_FinTxactItms1_IdCntCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateFinTxactItms1_IdCntCalc(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_FinTxactItms1_AmtDebtSumCalcFieldBtn")
    public void onUpdateParent1_FinTxactItms1_AmtDebtSumCalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_FinTxactItms1_AmtDebtSumCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateFinTxactItms1_AmtDebtSumCalc(this, thisNode.getParent1_Id(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_FinTxactItms1_AmtCredSumCalcFieldBtn")
    public void onUpdateParent1_FinTxactItms1_AmtCredSumCalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_FinTxactItms1_AmtCredSumCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateFinTxactItms1_AmtCredSumCalc(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateParent1_FinTxactItms1_AmtNet2SumCalcFieldBtn")
    public void onUpdateParent1_FinTxactItms1_AmtNet2SumCalcFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_FinTxactItms1_AmtNet2SumCalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateFinTxactItms1_AmtNet2SumCalc(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateParent1_FinTxactItms1_AmtEqCalcBoxBtn")
    public void onUpdateParent1_FinTxactItms1_AmtEqCalcBoxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_FinTxactItms1_AmtEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateFinTxactItms1_AmtEqCalc(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateParent1_FinTxactItms1_SysNodeFinCurcyEqCalcBoxBtn")
    public void onUpdateParent1_FinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_FinTxactItms1_SysNodeFinCurcyEqCalcBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Id = Optional.ofNullable(thisNode.getParent1_Id());
        if (o_parent1_Id.isPresent()){
            UpdateOption updOptionParent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1.updateFinTxactItms1_SysNodeFinCurcyEqCalc(this, o_parent1_Id.get(), updOptionParent1);
        }

        logger.trace(logPrfx + " <-- ");

    }

    //Parent1.Parent1 (FinTxactSet) Field

    @Subscribe("updateParent1_Parent1_Desc1FieldBtn")
    public void onUpdateParent1_Parent1_Desc1FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_Desc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        Optional<UsrNodeBase> o_parent1_Parent1_Id = Optional.ofNullable(thisNode.getParent1_Id()).map(UsrNodeBase::getParent1_Id);
        if (o_parent1_Parent1_Id.isPresent()){
            UpdateOption updOptionParent1Parent1 = UpdateOption.valueOf(updateInstItemCalcValsParent1Parent1Option.getValue())
                    .orElse(UpdateOption.SKIP);
            serviceParent1Parent1.updateDesc1(this, o_parent1_Parent1_Id.get(), updOptionParent1Parent1);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "parent1_Parent1_Desc1Node1_IdField.entityLookup", subject = "screenConfigurer")
    private void  parent1_Parent1_Desc1Node1_IdFieldLookupScreenConfigurer(View view) {
        String logPrfx = "parent1_Parent1_Desc1Node1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(view instanceof UsrNodeFinTxactItm0Lookup view2){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            view2.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            view2.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getInt1() == null ? 0 : thisNode.getInt1();
            view2.getFilterConfig1A_Int1().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "parent1_Parent1_Desc1Node2_IdField.entityLookup", subject = "screenConfigurer")
    private void  parent1_Parent1_Desc1Node2_IdFieldLookupScreenConfigurer(View view) {
        String logPrfx = "parent1_Parent1_Desc1Node2_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(view instanceof UsrNodeFinTxactItm0Lookup view2){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            view2.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            view2.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getInt1() == null ? 0 : thisNode.getInt1();
            view2.getFilterConfig1A_Int1().setValue(int1);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_GenTag1_IdFieldListBtn")
    public void onUpdateParent1_Parent1_GenTag1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_GenTag1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrGenTag.load() ");
        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- finished colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_GenTag2_IdFieldListBtn")
    public void onUpdateParent1_Parent1_GenTag2_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_GenTag2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrGenTag.load() ");
        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- finished colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_Type1_IdFieldListBtn")
    public void onUpdateParent1_Parent1_Type1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_Type1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinTxactSetType.load() ");
        colLoadrFinTxactSetType.load();
        logger.debug(logPrfx + " --- finished colLoadrFinTxactSetType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_GenChan1_IdFieldListBtn")
    public void onUpdateParent1_Parent1_GenChan1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_GenChan1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrGenChan.load() ");
        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- finished colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_GenChan2_IdFieldListBtn")
    public void onUpdateParent1_Parent1_GenChan2_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_GenChan2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrGenChan.load() ");
        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- finished colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("updateParent1_Parent1_How1_IdFieldListBtn")
    public void onUpdateParent1_Parent1_How1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_How1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinHow.load() ");
        colLoadrFinHow.load();
        logger.debug(logPrfx + " --- finished colLoadrFinHow.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_WhatText1FieldListBtn")
    public void onUpdateParent1_Parent1_WhatText1FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_WhatText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadParent1Parent1WhatText1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("parent1_Parent1_WhatText1Field")
    public void onParent1_Parent1_WhatText1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onParent1_Parent1_WhatText1FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_What1_IdFieldListBtn")
    public void onUpdateParent1_Parent1_What1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_What1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinWhat.load() ");
        colLoadrFinWhat.load();
        logger.debug(logPrfx + " --- finished colLoadrFinWhat.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_WhyText1FieldListBtn")
    public void onUpdateParent1_Parent1_WhyText1FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_WhyText1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadParent1Parent1WhyText1List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("parent1_Parent1_WhyText1Field")
    public void onParent1_Parent1_WhyText1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onParent1_Parent1_WhyText1FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateParent1_Parent1_Why1_IdFieldListBtn")
    public void onUpdateParent1_Parent1_Why1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateParent1_Parent1_Why1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinWhy.load() ");
        colLoadrFinWhy.load();
        logger.debug(logPrfx + " --- finished colLoadrFinWhy.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    //FinStmtItm Field

    @Install(to = "finStmtItm1_IdField.entityLookup", subject = "screenConfigurer")
    private void  finStmtItm1_IdFieldLookupScreenConfigurer(View view) {
        String logPrfx = "finStmtItm1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(view instanceof UsrNodeFinStmtItm0Lookup view2){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            view2.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            view2.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            BigDecimal amtNet = thisNode.getAmtNet();
            view2.getFilterConfig1A_AmtNet().setValue(amtNet);

        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_IdFieldBtn")
    public void onUpdateFinStmtItm1_IdFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinStmtItm1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");


        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinStmtItm1_Id(this, thisNode, updOption);


        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finStmtItm1_Txt1Field")
    public void onFinStmtItm1_Txt1FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onFinStmtItm1_Txt1FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinStmtItm1_Txt1FieldListBtn")
    public void onUpdateFinStmtItm1_Txt1FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinStmtItm1_Txt1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Txt1List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("finStmtItm1_Txt2Field")
    public void onFinStmtItm1_Txt2FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onFinStmtItm1_Txt2FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_Txt2FieldListBtn")
    public void onUpdateFinStmtItm1_Txt2FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinStmtItm1_Txt2FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Txt2List();

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("finStmtItm1_Txt3Field")
    public void onFinStmtItm1_Txt3FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onFinStmtItm1_Txt3FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_Txt3FieldListBtn")
    public void onUpdateFinStmtItm1_Txt3FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinStmtItm1_Txt3FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Txt3List();

        logger.trace(logPrfx + " <-- ");
    }
    @Subscribe("finStmtItm1_Txt4Field")
    public void onFinStmtItm1_Txt4FieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onFinStmtItm1_Txt4FieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinStmtItm1_Txt4FieldListBtn")
    public void onUpdateFinStmtItm1_Txt4FieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinStmtItm1_Txt4FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinStmtItm1_Txt4List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTaxLne1_IdFieldBtn")
    public void onUpdateFinTaxLne1_IdFieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateFinTaxLne1_Id(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    //FinTaxLne1 Field

    @Subscribe("updateFinTaxLne1_IdFieldListBtn")
    public void onUpdateFinTaxLne1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinTaxLne.load() ");
        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- finished colLoadrFinTaxLne.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finTaxLne1_CodeField")
    public void onFinTaxLne1_CodeFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onFinTaxLne1_CodeFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTaxLne1_CodeFieldListBtn")
    public void onUpdateFinTaxLne1_CodeFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinTaxLne1_CodeFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinTaxLne1_CodeList();

        logger.trace(logPrfx + " <-- ");
    }

    //Acct, Dept, Curcy

    @Subscribe("updateFinAcct1_IdFieldListBtn")
    public void onUpdateFinAcct1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinAcct1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinAcct.load() ");
        colLoadrFinAcct.load();
        logger.debug(logPrfx + " --- finished colLoadrFinAcct.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinDept1_IdFieldListBtn")
    public void onUpdateFinDept1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinDept1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrFinDept.load() ");
        colLoadrFinDept.load();
        logger.debug(logPrfx + " --- finished colLoadrFinDept.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSysNodeFinCurcy1_IdFieldListBtn")
    public void onUpdateSysFinCurcy1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateSysFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrSysNodeFinCurcy.load() ");
        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- finished colLoadrSysNodeFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("amtDebtField")
    public void onAmtDebtFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onAmtDebtFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
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
            UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
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
    public void onUpdateAmtNetBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtNetBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
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

    @Subscribe("updateAmtCalcGenFmla1_IdFieldListBtn")
    public void onUpdateAmtCalcGenFmla1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtCalcGenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrGenFmla.load() ");
        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- finished colLoadrGenFmla.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "amtCalcFinTxactItm1_IdField.entityLookup", subject = "screenConfigurer")
    private void  amtCalcFinTxactItm1_IdFieldLookupScreenConfigurer(View view) {
        String logPrfx = "amtCalcFinTxactItm1_IdFieldLookupScreenConfigurer";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if(view instanceof UsrNodeFinTxactItm0Lookup view2){

            LocalDateTime ts1_ElTs = thisNode.getTs1() == null ? null : thisNode.getTs1().getElTs();
            view2.getFilterConfig1A_Ts1_ElTs_GE().setValue(ts1_ElTs);
            view2.getFilterConfig1A_Ts1_ElTs_LE().setValue(ts1_ElTs);

            Integer int1 = thisNode.getInt1();
            view2.getFilterConfig1A_Int1().setValue(int1);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtCalcFinTxactItm1_EI1_RateBtn")
    public void onUpdateAmtCalcFinTxactItm1_EI1_RateBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateCalcAmtFinTxactItm1_EI1_RateBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtCalcFinTxactItm1_EI1_Rate(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtCalcBtn")
    public void onUpdateAmtCalcBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmtCalcBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinTxactItm thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateAmtCalc(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");

    }

    private void reloadParent1Parent1WhatText1List(){
        String logPrfx = "reloadParent1Parent1WhatText1List";
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
        tmplt_Parent1_Parent1_WhatText1Field.setItems(texts);
        logger.debug(logPrfx + " --- called tmplt_Parent1_Parent1_WhatText1Field.setItems()");

        parent1_Parent1_WhatText1Field.setItems(texts);
        logger.debug(logPrfx + " --- called parent1_Parent1_WhatText1Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadParent1Parent1WhyText1List(){
        String logPrfx = "reloadParent1Parent1WhyText1List";
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
        tmplt_Parent1_Parent1_WhyText1Field.setItems(texts);
        logger.debug(logPrfx + " --- called tmplt_Parent1_Parent1_WhyText1Field.setItems()");

        parent1_Parent1_WhyText1Field.setItems(texts);
        logger.debug(logPrfx + " --- called parent1_Parent1_WhyText1Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadParent1_Parent1_EI1_RoleList(){
        String logPrfx = "reloadParent1_Parent1_EI1_RoleList";
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

        tmplt_Parent1_Parent1_EI1_RoleField.setItems(roles);
        logger.debug(logPrfx + " --- called tmplt_Parent1_Parent1_EI1_RoleField.setItems()");

        parent1_Parent1_EI1_RoleField.setItems(roles);
        logger.debug(logPrfx + " --- called finStmtItm1_Desc1Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadParent1_EI1_RoleList(){
        String logPrfx = "reloadParent1_EI1_RoleList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.parent1_EI1_Role"
                + " from enty_UsrNodeFinTxactItm e"
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

/*
        JmixComboBox<String> propFilterCmpnt_Parent1_EI1_Role = null;
        propFilterCmpnt_Parent1_EI1_Role = filterConfig1A_Parent1_EI1_Role.getValueComponent()
                .unwrap(ComboBox.class);
        propFilterCmpnt_Parent1_EI1_Role.setItems(roles);
        logger.debug(logPrfx + " --- called propFilterCmpnt_Parent1_EI1_Role.setItems()");
*/

        tmplt_Parent1_EI1_RoleField.setItems(roles);
        logger.debug(logPrfx + " --- called tmplt_Parent1_EI1_RoleField.setItems()");

        parent1_EI1_RoleField.setItems(roles);
        logger.debug(logPrfx + " --- called parent1_EI1_RoleField.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinStmtItm1_Txt1List(){
        String logPrfx = "reloadFinStmtItm1_Txt1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt1"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt1 is not null"
                + " order by e.txt1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> txts = null;
        try {
            txts = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + txts.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        finStmtItm1_Txt1Field.setItems(txts);
        logger.debug(logPrfx + " --- called finStmtItm1_Txt1Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Txt2List(){
        String logPrfx = "reloadFinStmtItm1_Txt2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt2"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt2 is not null"
                + " order by e.txt2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> txts = null;
        try {
            txts = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + txts.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        finStmtItm1_Txt2Field.setItems(txts);
        logger.debug(logPrfx + " --- called finStmtItm1_Txt2Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Txt3List(){
        String logPrfx = "reloadFinStmtItm1_Txt3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt3"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt3 is not null"
                + " order by e.txt3"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> txts = null;
        try {
            txts = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + txts.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        finStmtItm1_Txt3Field.setItems(txts);
        logger.debug(logPrfx + " --- called finStmtItm1_Txt3Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinStmtItm1_Txt4List(){
        String logPrfx = "reloadFinStmtItm1_Txt4List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.txt4"
                + " from enty_UsrNodeFinStmtItm e"
                + " where e.txt4 is not null"
                + " order by e.txt4"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> txts = null;
        try {
            txts = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + txts.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        finStmtItm1_Txt4Field.setItems(txts);
        logger.debug(logPrfx + " --- called finStmtItm1_Txt4Field.setItems()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadFinTaxLne1_CodeList(){
        String logPrfx = "reloadFinTaxLne1_CodeList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finTaxLne1_Code"
                + " from enty_UsrNodeFinTxactItm e"
                + " where e.finTaxLne1_Code is not null"
                + " order by e.finTaxLne1_Code"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> codes = null;
        try {
            codes = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + codes.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        tmplt_FinTaxLne1_CodeField.setItems(codes);
        logger.debug(logPrfx + " --- called tmplt_FinTaxLne1_CodeField.setItems()");

        finTaxLne1_CodeField.setItems(codes);
        logger.debug(logPrfx + " --- called finTaxLne1_CodeField.setItems()");

        logger.trace(logPrfx + " <-- ");
    }


}
