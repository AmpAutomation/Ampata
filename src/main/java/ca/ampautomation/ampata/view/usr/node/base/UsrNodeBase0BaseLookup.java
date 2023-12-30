package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataComponents;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public abstract class UsrNodeBase0BaseLookup<NodeT extends UsrNodeBase, NodeTypeT extends UsrNodeBaseType, NodeServiceT extends UsrNodeBase0Service, NodeRepoT extends UsrNodeBase0Repo, TableT extends Table> extends StandardLookup<NodeT> implements UsrNodeBase0BaseComn {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<NodeT> typeOfNodeT;
    protected Class<NodeTypeT> typeOfNodeTypeT;

    @SuppressWarnings("unchecked")
    public UsrNodeBase0BaseLookup() {
        this.typeOfNodeT = (Class<NodeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[1];
    }

    protected ListComponent<NodeT> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    //Service
    protected NodeServiceT service;

    protected NodeServiceT getService(){
        return service;
    }

    public void setService(NodeServiceT service) {
        this.service = service;
    }

    @Autowired
    protected DataComponents dataComponents;

    @Autowired
    protected FetchPlans fetchPlans;

    @Autowired
    protected DataContext dataContext;

    @Autowired
    protected DataManager dataManager;

    @Autowired
    protected Metadata metadata;

    @Autowired
    protected MetadataTools metadataTools;

    @Autowired
    protected Notifications notifications;

    //Repository
    protected NodeRepoT repo;

    protected NodeRepoT getRepo(){
        return repo;
    }

    public void setRepo(NodeRepoT repo) {
        this.repo = repo;
    }


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Id2;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_Id2Dup;

    @Autowired
    protected PropertyFilter<NodeTypeT> filterConfig1A_Type1_Id;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Name1;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_Desc1;

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;


    /**
     * InitEvent is sent when the screen controller and all its declaratively defined
     * components are created, and dependency injection is completed. Nested fragments
     * are not initialized yet. Some visual components are not fully initialized,
     * for example, buttons are not linked with actions.
     *
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    /**
     * AfterInitEvent is sent when the screen controller and all its declaratively defined components are created,
     * dependency injection is completed, and all components have completed their internal initialization procedures.
     * Nested screen fragments (if any) have sent their InitEvent and AfterInitEvent. In this event listener, you can
     * create visual and data components and perform additional initialization if it depends on initialized nested
     * fragments.
     */
    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        String logPrfx = "onAfterInit";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    /**
     * BeforeShowEvent is sent right before the screen is shown, for example, it is
     * not added to the application UI yet.
     * Security restrictions are applied to UI components. In this event listener, you can load data,
     * check permissions and modify UI components.
     */
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");

    }

    /**
     * AfterShowEvent is sent right after the screen is shown, for example, when
     * it is added to the application UI.
     * In this event listener, you can show notifications, dialogs or other screens
     */
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        String logPrfx = "onAfterShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- Changed entity: " + event.getEntity());

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<NodeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisNode is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = event.getItem();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");
            //todo I observed thisNode is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    public Filter getFilter(){
        return filter;
    }

    public PropertyFilter<String> getFilterConfig1A_Id2(){
        return filterConfig1A_Id2;
    }

    public PropertyFilter<Integer> getFilterConfig1A_Id2Dup(){
        return filterConfig1A_Id2Dup;
    }

    public PropertyFilter<NodeTypeT> getFilterConfig1A_Type1_Id(){
        return filterConfig1A_Type1_Id;
    }

    public PropertyFilter<String> getFilterConfig1A_Name1(){
        return filterConfig1A_Name1;
    }

    public PropertyFilter<Integer> getFilterConfig1A_SortIdx(){
        return filterConfig1A_SortIdx;
    }

    public PropertyFilter<String> getFilterConfig1A_Desc1(){
        return filterConfig1A_Desc1;
    }

    public Notifications getNotifications(){
        return notifications;
    }

}
