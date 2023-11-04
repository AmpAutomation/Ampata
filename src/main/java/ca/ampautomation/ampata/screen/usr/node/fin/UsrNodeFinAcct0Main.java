package ca.ampautomation.ampata.screen.usr.node.fin;

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
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Service;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

@UiController("enty_UsrNodeFinAcct.main")
@UiDescriptor("usr-node-fin-acct-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinAcct0Main extends UsrNodeBase0BaseMain<UsrNodeFinAcct, UsrNodeFinAcctType, UsrNodeFinAcct0Service, UsrNodeFinAcct0Repo, TreeTable<UsrNodeFinAcct>> {

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
    @Autowired
    protected PropertyFilter<UsrNodeGenAgent> filterConfig1A_GenAgent1_Id;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_SysNodeFinCurcy1_Id;


    //Template
    @Autowired
    protected EntityComboBox<UsrNodeGenAgent> tmplt_GenAgent1_IdField;
    @Autowired
    protected CheckBox tmplt_GenAgent1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysNodeFinCurcy> tmplt_SysNodeFinCurcy1_IdField;
    @Autowired
    protected CheckBox tmplt_SysNodeFinCurcy1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrNodeFinTaxLne> tmplt_FinTaxLne1_IdField;
    @Autowired
    protected CheckBox tmplt_FinTaxLne1_IdFieldChk;



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
    private ComboBox<String> statusField;

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

        statusField.setNullOptionVisible(true);
        statusField.setNullSelectionCaption("<null>");

        colCntnrGenAgent = dataComponents.createCollectionContainer(UsrNodeGenAgent.class);
        colLoadrGenAgent = dataComponents.createCollectionLoader();
        colLoadrGenAgent.setQuery("select e from enty_UsrNodeGenAgent e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenAgent_Inst = fetchPlans.builder(UsrNodeGenAgent.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenAgent.setFetchPlan(fchPlnGenAgent_Inst);
        colLoadrGenAgent.setContainer(colCntnrGenAgent);
        colLoadrGenAgent.setDataContext(getScreenData().getDataContext());

        genAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        //template
        tmplt_GenAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        //filter
        EntityComboBox<UsrNodeGenAgent> propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrNodeGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(colCntnrGenAgent);


        colCntnrSysNodeFinCurcy = dataComponents.createCollectionContainer(SysNodeFinCurcy.class);
        colLoadrSysNodeFinCurcy = dataComponents.createCollectionLoader();
        colLoadrSysNodeFinCurcy.setQuery("select e from enty_SysNodeFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnSysNodeFinCurcy_Inst = fetchPlans.builder(SysNodeFinCurcy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrSysNodeFinCurcy.setFetchPlan(fchPlnSysNodeFinCurcy_Inst);
        colLoadrSysNodeFinCurcy.setContainer(colCntnrSysNodeFinCurcy);
        colLoadrSysNodeFinCurcy.setDataContext(getScreenData().getDataContext());

        sysNodeFinCurcy1_IdField.setOptionsContainer(colCntnrSysNodeFinCurcy);
        //template
        tmplt_SysNodeFinCurcy1_IdField.setOptionsContainer(colCntnrSysNodeFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_SysNodeFinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_SysNodeFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysNodeFinCurcy1_Id.setOptionsContainer(colCntnrSysNodeFinCurcy);


        colCntnrFinTaxLne = dataComponents.createCollectionContainer(UsrNodeFinTaxLne.class);
        colLoadrFinTaxLne = dataComponents.createCollectionLoader();
        colLoadrFinTaxLne.setQuery("select e from enty_UsrNodeFinTaxLne e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinTaxLne_Inst = fetchPlans.builder(UsrNodeBase.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinTaxLne.setFetchPlan(fchPlnFinTaxLne_Inst);
        colLoadrFinTaxLne.setContainer(colCntnrFinTaxLne);
        colLoadrFinTaxLne.setDataContext(getScreenData().getDataContext());

        finTaxLne1_IdField.setOptionsContainer(colCntnrFinTaxLne);
        //template
        tmplt_FinTaxLne1_IdField.setOptionsContainer(colCntnrFinTaxLne);
        //filter


        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
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


        if (tmplt_Type1_IdFieldChk.isChecked()) {
            copy.setType1_Id(tmplt_Type1_IdField.getValue());
        }

        if (tmplt_GenAgent1_IdFieldChk.isChecked()) {
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

        if (tmplt_Type1_IdFieldChk.isChecked()
        ) {
            thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_GenAgent1_IdFieldChk.isChecked()
        ) {
            thisNode.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
            thisNodeIsChanged = true;
        }

        if (tmplt_FinTaxLne1_IdFieldChk.isChecked()
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
    public void onUpdateGenAgent1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenAgent1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrSysNodeFinCurcy.load();
        logger.debug(logPrfx + " --- called colLoadrSysNodeFinCurcy.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "statusField", subject = "enterPressHandler")
    private void statusFieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "statusFieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateStatusFieldListBtn")
    public void onUpdateStatusFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateStatusFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        List<String> statuss = service.getStatusList();
        logger.debug(logPrfx + " --- called service.getStatusList()");
        statusField.setOptionsList(statuss);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTaxLne1_IdFieldListBtn")
    public void onUpdateFinTaxLne1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTaxLne1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrFinTaxLne.load();
        logger.debug(logPrfx + " --- called colLoadrFinTaxLne.load() ");

        logger.trace(logPrfx + " <-- ");
    }


}