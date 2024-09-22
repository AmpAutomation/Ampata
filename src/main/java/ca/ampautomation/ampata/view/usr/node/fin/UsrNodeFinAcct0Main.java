package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcct;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinAcct0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTaxLne;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Service;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxBase;
import io.jmix.core.*;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

@Route(value = "usrNodeFinAccts", layout = MainView.class)
@ViewController("enty_UsrNodeFinAcct.main")
@ViewDescriptor("usr-node-fin-acct-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinAcct0Main extends UsrNodeBase0BaseMain<UsrNodeFinAcct, UsrNodeFinAcctType, UsrNodeFinAcct0Service, UsrNodeFinAcct0Repo, TreeDataGrid<UsrNodeFinAcct>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct.Service")
    public void setService(UsrNodeFinAcct0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct.Repo")
    public void setRepo(UsrNodeFinAcct0Repo repo) {
        this.repo = repo;
    }


    //Filter
    @ViewComponent
    protected PropertyFilter<UsrNodeGenAgent> filterConfig1A_GenAgent1_Id;

    @ViewComponent
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_SysNodeFinCurcy1_Id;


    //Template
    @ViewComponent
    protected EntityComboBox<UsrNodeGenAgent> tmplt_GenAgent1_IdField;
    @ViewComponent
    protected JmixCheckbox tmplt_GenAgent1_IdFieldChk;

    @ViewComponent
    protected EntityComboBox<SysNodeFinCurcy> tmplt_SysNodeFinCurcy1_IdField;
    @ViewComponent
    protected JmixCheckbox tmplt_SysNodeFinCurcy1_IdFieldChk;
    @ViewComponent
    protected EntityComboBox<UsrNodeFinTaxLne> tmplt_FinTaxLne1_IdField;
    @ViewComponent
    protected JmixCheckbox tmplt_FinTaxLne1_IdFieldChk;



    //Other data loaders and containers
    private CollectionContainer<UsrNodeGenAgent> colCntnrGenAgent;
    private CollectionLoader<UsrNodeGenAgent> colLoadrGenAgent;

    private CollectionContainer<SysNodeFinCurcy> colCntnrSysNodeFinCurcy;
    private CollectionLoader<SysNodeFinCurcy> colLoadrSysNodeFinCurcy;

    private CollectionContainer<UsrItemGenFmla> colCntnrGenFmla;
    private CollectionLoader<UsrItemGenFmla> colLoadrGenFmla;

    private CollectionContainer<UsrNodeFinTaxLne> colCntnrFinTaxLne;
    private CollectionLoader<UsrNodeFinTaxLne> colLoadrFinTaxLne;


    private CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    private CollectionLoader<UsrItemGenTag> colLoadrGenTag;


    //Field
    @Autowired
    protected EntityPicker<UsrNodeFinAcct> parent1_IdField;
    @Autowired
    private JmixComboBox<String> statusField;

    @Autowired
    private EntityComboBox<UsrNodeGenAgent> genAgent1_IdField;

    @Autowired
    private EntityComboBox<SysNodeFinCurcy> sysNodeFinCurcy1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeFinTaxLne> finTaxLne1_IdField;


    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        //statusField.setNull(true);
        //statusField.setNullSelectionCaption("<null>");

        colCntnrGenAgent = dataComponents.createCollectionContainer(UsrNodeGenAgent.class);
        colLoadrGenAgent = dataComponents.createCollectionLoader();
        colLoadrGenAgent.setQuery("select e from enty_UsrNodeGenAgent e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenAgent_Inst = fetchPlans.builder(UsrNodeGenAgent.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenAgent.setFetchPlan(fchPlnGenAgent_Inst);
        colLoadrGenAgent.setContainer(colCntnrGenAgent);
        colLoadrGenAgent.setDataContext(getViewData().getDataContext());

        genAgent1_IdField.setItems(colCntnrGenAgent);
        //template
        tmplt_GenAgent1_IdField.setItems(colCntnrGenAgent);
        //filter
        EntityComboBox<UsrNodeGenAgent> propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrNodeGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setItems(colCntnrGenAgent);


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
        //template
        tmplt_SysNodeFinCurcy1_IdField.setItems(colCntnrSysNodeFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setItems(colCntnrSysNodeFinCurcy);


        colCntnrFinTaxLne = dataComponents.createCollectionContainer(UsrNodeFinTaxLne.class);
        colLoadrFinTaxLne = dataComponents.createCollectionLoader();
        colLoadrFinTaxLne.setQuery("select e from enty_UsrNodeFinTaxLne e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTaxLne_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTaxLne.setFetchPlan(fchPlnFinTaxLne_Inst);
        colLoadrFinTaxLne.setContainer(colCntnrFinTaxLne);
        colLoadrFinTaxLne.setDataContext(getViewData().getDataContext());

        finTaxLne1_IdField.setItems(colCntnrFinTaxLne);
        //template
        tmplt_FinTaxLne1_IdField.setItems(colCntnrFinTaxLne);
        //filter


        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Override
    public UsrNodeFinAcct onDuplicateBtnClickHelper(UsrNodeFinAcct orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        UsrNodeFinAcct copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());


        if (tmplt_Type1_IdFieldChk.getValue()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_GenAgent1_IdFieldChk.getValue()) {
            copy.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, copy, updOption);
        service.updateId2(this, copy, updOption);

        if (Objects.equals(copy.getId2(), orig.getId2())) {
            copy.setSortIdx(copy.getSortIdx() == null ? 1 : copy.getSortIdx() + 1);
            service.updateId2Calc(this, copy, updOption);
            service.updateId2(this, copy, updOption);
        }

        service.updateCalcVals(this, copy, updOption);

        logger.trace(logPrfx + " <-- ");
        return copy;
    }

    @Override
    public Boolean onSetBtnClickHelper(UsrNodeFinAcct thisNode){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisNodeIsChanged = false;

        if (tmplt_Type1_IdFieldChk.getValue()
        ) {
            thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_GenAgent1_IdFieldChk.getValue()
        ) {
            thisNode.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_FinTaxLne1_IdFieldChk.getValue()
        ) {
            thisNode.setFinTaxLne1_Id(tmplt_FinTaxLne1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateColItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        thisNodeIsChanged = service.updateCalcVals(this, thisNode, updOption) || thisNodeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }


    @Subscribe("updateGenAgent1_IdFieldListBtn")
    public void onUpdateGenAgent1_IdFieldListBtn(ClickEvent<Button> event) {
        String logPrfx = "onUpdateGenAgent1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtn(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("statusField")
    public void onStatusFieldCustomValueSet(final ComboBoxBase.CustomValueSetEvent<ComboBox<String>> event) {
        String logPrfx = "onStatusFieldCustomValueSet";
        logger.trace(logPrfx + " --> ");
        
        addEnteredTextToComboBoxOptionsList(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateStatusFieldListBtn")
    public void onUpdateStatusFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateStatusFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        List<String> statuss = service.getStatusList();
        logger.debug(logPrfx + " --- called service.getStatusList()");
        statusField.setItems(statuss);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTaxLne1_IdFieldListBtn")
    public void onUpdateFinTaxLne1_IdFieldListBtn(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");

        logger.trace(logPrfx + " <-- ");
    }


}