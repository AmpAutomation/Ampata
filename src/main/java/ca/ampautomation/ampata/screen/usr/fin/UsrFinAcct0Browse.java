package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinAcctQryMngr;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.Filter;
import io.jmix.ui.component.PropertyFilter;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataComponents;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@UiController("enty_UsrFinAcct.browse")
@UiDescriptor("usr-fin-acct-0-browse.xml")
@LookupComponent("tableMain")
public class UsrFinAcct0Browse extends StandardLookup<UsrNode> {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsrFinAcctQryMngr qryMngr;

    @Autowired
    private DataComponents dataComponents;

    @Autowired
    private FetchPlans fetchPlans;

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private Notifications notifications;


    //Filter
    @Autowired
    protected Filter filter;



    //Toolbar

    //Template
    @Autowired
    protected PropertyFilter<UsrNode> filterConfig1A_GenAgent1_Id;



    //Other data containers, loaders and table
    private CollectionContainer<UsrNode> genAgentsDc;
    private CollectionLoader<UsrNode> genAgentsDl;


    /*
    InitEvent is sent when the screen controller and all its declaratively defined components are created,
    and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
    are not fully initialized, for example, buttons are not linked with actions.
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        genAgentsDc = dataComponents.createCollectionContainer(UsrNode.class);
        genAgentsDl = dataComponents.createCollectionLoader();
        genAgentsDl.setQuery("select e from enty_UsrNode e where e.className = 'UsrGenAgent' order by e.id2");
        FetchPlan genAgentsFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genAgentsDl.setFetchPlan(genAgentsFp);
        genAgentsDl.setContainer(genAgentsDc);
        genAgentsDl.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<UsrNode> propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrNode>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(genAgentsDc);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        genAgentsDl.load();
        logger.debug(logPrfx + " --- called genAgentsDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }


}