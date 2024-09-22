package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenAgent0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgentType;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenAgent0Service;
import com.vaadin.flow.router.Route;
import io.jmix.core.*;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "UsrNodeGenAgents", layout = MainView.class)
@ViewController("enty_UsrNodeGenAgent.main")
@ViewDescriptor("usr-node-gen-agent-0-main.xml")
@LookupComponent("dataGridMain")
@DialogMode(width = "64em")
public class UsrNodeGenAgent0Main extends UsrNodeBase0BaseMain<UsrNodeGenAgent, UsrNodeGenAgentType, UsrNodeGenAgent0Service, UsrNodeGenAgent0Repo, DataGrid<UsrNodeGenAgent>> {

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

    @ViewComponent
    protected PropertyFilter<UsrNodeGenAgent> filterConfig1A_GenAgent1_Id;

    @ViewComponent
    protected PropertyFilter<UsrNodeGenAgent> filterConfig1A_GenAgent2_Id;


    @ViewComponent
    protected JmixCheckbox tmplt_GenAgent1_IdFieldChk;
    @ViewComponent
    protected EntityComboBox<UsrNodeGenAgent> tmplt_GenAgent1_IdField;

    @ViewComponent
    protected JmixCheckbox tmplt_GenAgent2_IdFieldChk;
    @ViewComponent
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
        colLoadrGenAgent.setDataContext(getViewData().getDataContext());

        genAgent1_IdField.setItems(colCntnrGenAgent);
        genAgent2_IdField.setItems(colCntnrGenAgent);
        //template
        tmplt_GenAgent1_IdField.setItems(colCntnrGenAgent);
        tmplt_GenAgent2_IdField.setItems(colCntnrGenAgent);
        //filter
        EntityComboBox<UsrNodeGenAgent> propFilterCmpnt_GenAgent1_Id;
        propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrNodeGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setItems(colCntnrGenAgent);
        EntityComboBox<UsrNodeGenAgent> propFilterCmpnt_GenAgent2_Id;
        propFilterCmpnt_GenAgent2_Id = (EntityComboBox<UsrNodeGenAgent>) filterConfig1A_GenAgent2_Id.getValueComponent();
        propFilterCmpnt_GenAgent2_Id.setItems(colCntnrGenAgent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
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

        if (tmplt_GenAgent2_IdFieldChk.getValue()
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
    public void onUpdateGenAgent1_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateGenAgent1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling colLoadrGenAgent.load() ");
        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called colLoadrGenAgent.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateGenAgent2_IdFieldListBtn")
    public void onUpdateGenAgent2_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateGenAgent2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling colLoadrGenAgent.load() ");
        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called colLoadrGenAgent.load() ");

        logger.trace(logPrfx + " <-- ");

    }

}