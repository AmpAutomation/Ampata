package ca.ampautomation.ampata.screen.sys.base;

import ca.ampautomation.ampata.entity.sys.comn.SysComnBaseQryMngr;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBaseType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;

public abstract class SysNodeBase0BaseBrowse<NodeT extends SysNodeBase, NodeTypeT extends SysNodeBaseType, NodeQryMngrT extends SysComnBaseQryMngr> extends StandardLookup<NodeT> {

    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    private Class<NodeT> typeOfNodeT;
    private Class<NodeTypeT> typeOfNodeTypeT;

    @SuppressWarnings("unchecked")
    public SysNodeBase0BaseBrowse() {
        this.typeOfNodeT = (Class<NodeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<NodeT> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected EntityManagerFactory entityManagerFactory;

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

    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<NodeTypeT> filterConfig1A_Type1_Id;


    //Toolbar


    //Template


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<NodeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<NodeT> colCntnrMain;
    @Autowired
    protected TreeTable<NodeT> tableMain;



    /**
     * InitEvent is sent when the screen controller and all its declaratively defined components are created, and dependency injection is completed. Nested fragments are not initialized yet. Some visual components are not fully initialized, for example, buttons are not linked with actions.
     * @param event
     */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit[super]";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    /**
     * AfterInitEvent is sent when the screen controller and all its declaratively defined components are created, dependency injection is completed, and all components have completed their internal initialization procedures. Nested screen fragments (if any) have sent their InitEvent and AfterInitEvent. In this event listener, you can create visual and data components and perform additional initialization if it depends on initialized nested fragments.
     * @param event
     */
    @Subscribe
    public void onAfterInitEvent(AfterInitEvent event) {
        String logPrfx = "onAfterInitEvent[super]";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    /**
     * InitEntityEvent is sent in screens inherited from StandardEditor and MasterDetailScreen before the new entity instance is set to edited entity container.
     *
     * Use this event listener to initialize default values in the new entity instance
     * @param event
     */
    @Subscribe
    public void onInitEntityEvent(InitEvent event) {
        String logPrfx = "onInitEntityEvent[super]";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    /**
     * BeforeShowEvent is sent right before the screen is shown, for example, it is not added to the application UI yet. Security restrictions are applied to UI components. In this event listener, you can load data, check permissions and modify UI components.
     * @param event
     */
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow[super]";
        logger.trace(logPrfx + " --> ");

        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }

    /**
     * AfterShowEvent is sent right after the screen is shown, for example, when it is added to the application UI. In this event listener, you can show notifications, dialogs or other screens
     * @param event
     */
    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        String logPrfx = "onAfterShow[super]";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<NodeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange[super]";
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
        String logPrfx = "onColCntnrMainItemChange[super]";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = event.getItem();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");
            //todo I observed thisNode is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if (thisNode.getClassName() == null || thisNode.getClassName().isBlank()){
            thisNode.setClassName(typeOfNodeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfNodeT.getSimpleName());
        }

        logger.trace(logPrfx + " <-- ");
    }



}
