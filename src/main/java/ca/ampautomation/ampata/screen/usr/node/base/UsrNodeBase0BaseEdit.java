package ca.ampautomation.ampata.screen.usr.node.base;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseGrpg;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.core.*;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public abstract class UsrNodeBase0BaseEdit<NodeT extends UsrNodeBase, NodeTypeT extends UsrNodeBaseType, NodeServiceT extends UsrNodeBase0Service, NodeRepoT extends UsrNodeBase0Repo> extends StandardEditor<NodeT> implements UsrNodeBase0BaseComn {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<NodeT> typeOfNodeT;
    protected Class<NodeTypeT> typeOfNodeTypeT;

    @SuppressWarnings("unchecked")
    public UsrNodeBase0BaseEdit() {
        this.typeOfNodeT = (Class<NodeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[1];
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



    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Main data containers, loaders and table
    protected InstanceContainer<NodeT> instCntnrMain;


    //Type data container and loader
    protected CollectionContainer<NodeTypeT> colCntnrType;
    protected CollectionLoader<NodeTypeT> colLoadrType;


    //GenTag data container loader and data property container
    protected CollectionContainer<UsrItemGenTag> colCntnrGenTag;
    protected CollectionLoader<UsrItemGenTag> colLoadrGenTag;
    @Autowired
    protected CollectionPropertyContainer<UsrItemGenTag> colPropCntnrGenTag;


    //Field
    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected EntityComboBox<NodeTypeT> type1_IdField;

    @Autowired
    protected TagField<UsrItemGenTag> genTags1_IdField;


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

        Map<String, Integer> map2 = new LinkedHashMap<>();
        map2.put(UpdateOption.SKIP.toString(), UpdateOption.SKIP.toInt());
        map2.put(UpdateOption.LOCAL.toString(), UpdateOption.LOCAL.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST.toString(), UpdateOption.LOCAL__REF_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_TO_EXIST_NEW.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST.toInt());
        map2.put(UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toString(), UpdateOption.LOCAL__REF_IF_EMPTY_TO_EXIST_NEW.toInt());
        updateInstItemCalcValsOption.setOptionsMap(map2);
        updateInstItemCalcValsOption.setValue(UpdateOption.LOCAL.toInt());

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfNodeTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfNodeTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfNodeTypeT)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());
        //Field
        type1_IdField.setOptionsContainer(colCntnrType);

        colCntnrGenTag = dataComponents.createCollectionContainer(UsrItemGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrItemGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag = fetchPlans.builder(UsrItemGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        logger.trace(logPrfx + " <-- ");
    }

    /**
     * InitEntityEvent is sent in screens inherited from StandardEditor and MasterDetailScreen
     * before the new entity instance is set to edited entity container.
     * Use this event listener to initialize default values in the new entity instance
     */
    @Subscribe
    public void onInitEntity(MasterDetailScreen.InitEntityEvent<NodeT> event) {
        String logPrfx = "onInitEntity";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = event.getEntity();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

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

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing colLoadrType.load() ");
        colLoadrType.load();
        logger.debug(logPrfx + " --- finished colLoadrType.load() ");

        logger.debug(logPrfx + " --- executing colLoadrGenTag.load() ");
        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- finished colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    protected Map<?, List<NodeT>> getGrpgMap(List<NodeT> thisNodes){
        String logPrfx = "getGrpMap";
        logger.trace(logPrfx + " --> ");

        Map<UsrNodeBaseGrpg, List<NodeT>> grpMap = thisNodes.stream()
                .collect(groupingBy(node -> new UsrNodeBaseGrpg(node.getParent1_Id())));

        logger.trace(logPrfx + " <-- ");
        return grpMap;
    }

    protected Condition getConditionGrpg(Object grpgKey){
        String logPrfx = "getConditionGrpg";
        logger.trace(logPrfx + " --> ");

        // Ensure grpgKey is instanceof UsrNodeBaseGrpg
        if (!(grpgKey instanceof UsrNodeBaseGrpg l_grpgKey)){
            logger.trace(logPrfx + " --- grpgKey is not instanceof UsrNodeBaseGrpg");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        PropertyCondition cond;
        //Add condition for this group
        cond = PropertyCondition.equal("parent1_Id",l_grpgKey.parent1_Id());

        logger.trace(logPrfx + " <-- ");
        return cond;
    }

    protected Comparator<NodeT> getComparator(){
        String logPrfx = "getComparator";
        logger.trace(logPrfx + " --> ");

        Comparator<NodeT> comparator = Comparator.comparing(
                NodeT::getSortIdx,Comparator.nullsFirst(Comparator.naturalOrder()));

        logger.trace(logPrfx + " <-- ");
        return comparator;
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = event.getSource().getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            //todo I observed thisNode is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateCalcVals(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            NodeT thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
            service.updateId2Deps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2(this, thisNode, updOption);
        service.updateId2Deps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Calc(this, thisNode, updOption);
        service.updateId2Deps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Cmp(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateId2Dup(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("sortIdxField")
    public void onSortIdxFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onSortIdxFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeBase thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                    .orElse(UpdateOption.SKIP);
            service.updateSortIdxDeps(this, thisNode, updOption);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateSortKeyFieldBtn")
    public void onUpdateSortKeyFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateSortKeyFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateSortKey(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateName1(this, thisNode, updOption);
        service.updateName1Deps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateInst1FieldBtn")
    public void onUpdateInst1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInst1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateInst1(this, thisNode, updOption);
        service.updateInst1Deps(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        NodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);
        service.updateDesc1(this, thisNode, updOption);

        logger.trace(logPrfx + " <-- ");
    }

/*

    @Install(to = "genTags1_IdField", subject = "searchExecutor")
    protected List<UsrItemGenTag> genTags1_IdFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        return dataManager.load(UsrItemGenTag.class)
                .query("e.id2 like ?1 order by e.sortKey, e.id2"
                        , "(?i)%" + searchString + "%")
                .list();
    }
*/

    protected void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "addEnteredTextToComboBoxOptionsList";
        logger.trace(logPrfx + " --> ");

        String text = enterPressEvent.getText();
        if (!Objects.equals(text, "<null>")){
            @SuppressWarnings("unchecked")
            // enterPressEvent.getSource is directly connected to a ComboBox
            ComboBox<String> cb = (ComboBox<String>) enterPressEvent.getSource();

            List<String> list;
            // this comboBox options list is created with a call to setOptionsList(List)
            // see onUpdateFinStmtItm1_Desc1Field
            // therefore cb.getOptions is type ListOptions
            ListOptions<String> listOptions = (ListOptions<String>) cb.getOptions();
            if (listOptions != null && !listOptions.getItemsCollection().isEmpty()) {
                list = (List<String>) listOptions.getItemsCollection();
            } else {
                list = new ArrayList<String>();
            }

            list.add(text);
            logger.trace(logPrfx + " --- called list.add( " + text + ")");

            cb.setOptionsList(list);

            notifications.create()
                    .withCaption("Added " + text + " to list.")
                    .show();
        }

    }

    public Notifications getNotifications(){
        return notifications;
    }

}