package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.Filter;
import io.jmix.ui.component.PropertyFilter;
import io.jmix.ui.component.propertyfilter.SingleFilterSupport;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataComponents;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@UiController("ampata_FinAcct.browse")
@UiDescriptor("fin-acct-browse.xml")
@LookupComponent("table")
public class FinAcctBrowse extends StandardLookup<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GenNodeRepository repo;

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


    @Autowired
    protected SingleFilterSupport singleFilterSupport;

    @Autowired
    protected Filter filter;


    @Autowired
    protected PropertyFilter<GenNode> filterConfig1A_genAgent1_Id;



    private CollectionContainer<GenNode> genAgentsDc;

    private CollectionLoader<GenNode> genAgentsDl;


    Logger logger = LoggerFactory.getLogger(FinAcctBrowse.class);

    /*
    InitEvent is sent when the screen controller and all its declaratively defined components are created,
    and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
    are not fully initialized, for example, buttons are not linked with actions.
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        genAgentsDc = dataComponents.createCollectionContainer(GenNode.class);
        genAgentsDl = dataComponents.createCollectionLoader();
        genAgentsDl.setQuery("select e from ampata_GenNode e where e.className = 'GenAgent' order by e.id2");
        FetchPlan genAgentsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genAgentsDl.setFetchPlan(genAgentsFp);
        genAgentsDl.setContainer(genAgentsDc);
        genAgentsDl.setDataContext(getScreenData().getDataContext());

        //filter
        EntityComboBox<GenNode> propFilterCmpnt_genAgent1_Id = (EntityComboBox<GenNode>) filterConfig1A_genAgent1_Id.getValueComponent();
        propFilterCmpnt_genAgent1_Id.setOptionsContainer(genAgentsDc);

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