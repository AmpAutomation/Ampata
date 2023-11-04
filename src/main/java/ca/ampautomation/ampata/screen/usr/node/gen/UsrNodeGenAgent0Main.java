package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenAgent0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgentType;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenAgent0Service;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenAgent.main")
@UiDescriptor("usr-node-gen-agent-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeGenAgent0Main extends UsrNodeBase0BaseMain<UsrNodeGenAgent, UsrNodeGenAgentType, UsrNodeGenAgent0Service, UsrNodeGenAgent0Repo, Table<UsrNodeGenAgent>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenAgent.Service")
    public void setService(UsrNodeGenAgent0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenAgent.Repo")
    public void setRepo(UsrNodeGenAgent0Repo repo) { this.repo = repo; }

    //Filter

    @Autowired
    protected PropertyFilter<UsrNodeGenAgent> filterConfig1A_GenAgent1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeGenAgent> filterConfig1A_GenAgent2_Id;


    @Autowired
    protected CheckBox tmplt_GenAgent1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrNodeGenAgent> tmplt_GenAgent1_IdField;

    @Autowired
    protected CheckBox tmplt_GenAgent2_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrNodeGenAgent> tmplt_GenAgent2_IdField;



    //Other data loaders, containers and tables
    private CollectionContainer<UsrNodeGenAgent> colCntnrGenAgent;
    private CollectionLoader<UsrNodeGenAgent> colLoadrGenAgent;



    //Field

    @Autowired
    private EntityComboBox<UsrNodeGenAgent> genAgent1_IdField;

    @Autowired
    private EntityComboBox<UsrNodeGenAgent> genAgent2_IdField;


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        colCntnrGenAgent = dataComponents.createCollectionContainer(UsrNodeGenAgent.class);
        colLoadrGenAgent = dataComponents.createCollectionLoader();
        colLoadrGenAgent.setQuery("select e from enty_UsrNodeGenAgent e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenAgent1_Inst = fetchPlans.builder(UsrNodeGenAgent.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenAgent.setFetchPlan(fchPlnGenAgent1_Inst);
        colLoadrGenAgent.setContainer(colCntnrGenAgent);
        colLoadrGenAgent.setDataContext(getScreenData().getDataContext());

        genAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        genAgent2_IdField.setOptionsContainer(colCntnrGenAgent);
        //template
        tmplt_GenAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        tmplt_GenAgent2_IdField.setOptionsContainer(colCntnrGenAgent);
        //filter
        EntityComboBox<UsrNodeGenAgent> propFilterCmpnt_GenAgent1_Id;
        propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrNodeGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(colCntnrGenAgent);
        EntityComboBox<UsrNodeGenAgent> propFilterCmpnt_GenAgent2_Id;
        propFilterCmpnt_GenAgent2_Id = (EntityComboBox<UsrNodeGenAgent>) filterConfig1A_GenAgent2_Id.getValueComponent();
        propFilterCmpnt_GenAgent2_Id.setOptionsContainer(colCntnrGenAgent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called colLoadrGenAgent.load() ");

        logger.trace(logPrfx + " <-- ");

    }


    @Override
    public Boolean onSetBtnClickHelper(UsrNodeGenAgent thisNode){
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

        if (tmplt_GenAgent2_IdFieldChk.isChecked()
        ) {
            thisNode.setGenAgent2_Id(tmplt_GenAgent2_IdField.getValue());
            thisNodeIsChanged = true;
        }

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        thisNodeIsChanged = service.updateCalcVals(this, thisNode, updOption) || thisNodeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisNodeIsChanged;
    }

    @Subscribe("updateGenAgent1_IdFieldListBtn")
    public void onUpdateGenAgent1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenAgent1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling colLoadrGenAgent.load() ");
        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called colLoadrGenAgent.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateGenAgent2_IdFieldListBtn")
    public void onUpdateGenAgent2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenAgent2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling colLoadrGenAgent.load() ");
        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called colLoadrGenAgent.load() ");

        logger.trace(logPrfx + " <-- ");

    }


}