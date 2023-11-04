package ca.ampautomation.ampata.screen.usr.node.base;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class UsrNodeBase0Type0BaseLookup<NodeTypeT extends UsrNodeBaseType, NodeTypeServiceT extends UsrNodeBase0Type0Service, NodeTypeRepoT extends UsrNodeBase0Type0Repo, TableT extends Table> extends MasterDetailScreen<NodeTypeT> {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<NodeTypeT> typeOfNodeTypeT;

    @SuppressWarnings("unchecked")
    public UsrNodeBase0Type0BaseLookup() {
        this.typeOfNodeTypeT = (Class<NodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<NodeTypeT> getTable() {
        return (ListComponent) getWindow().getComponentNN("tableMain");
    }

    //Service
    protected NodeTypeServiceT service;

    protected NodeTypeServiceT getService(){
        return service;
    }

    public void setService(NodeTypeServiceT service) {
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
    protected NodeTypeRepoT repo;

    protected NodeTypeRepoT getRepo(){
        return repo;
    }

    public void setRepo(NodeTypeRepoT repo) {
        this.repo = repo;
    }


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected  PropertyFilter<NodeTypeT> filterConfig1A_Parent1_Id;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Template


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<NodeTypeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<NodeTypeT> colCntnrMain;
    @Autowired
    protected TableT tableMain;


    //Other data loaders, containers and tables


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        //tableMain.sort("sortKey", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<NodeTypeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisNodeType is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeTypeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = event.getItem();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null");
            //todo I observed thisNodeType is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPr_Upd_AllCalcVals_ForAllRows");
        repo.execPr_Upd_AllCalcVals_ForAllRows();
        logger.debug(logPrfx + " --- finished repo.execPr_Upd_AllCalcVals_ForAllRows");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodeTypes.forEach(orig -> {
            NodeTypeT copy = onDuplicateBtnClickHelper(orig);

            NodeTypeT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }


    public NodeTypeT onDuplicateBtnClickHelper(NodeTypeT orig){
        String logPrfx = "onDuplicateBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        NodeTypeT copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());

        if (orig.getId2().equals(copy.getId2())){
            copy.setId2(copy.getId2() + " Copy");
            copy.setId2Calc(copy.getId2());
        }
        logger.trace(logPrfx + " <-- ");
        return copy;
    }

    
    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();
        if (thisNodeTypes == null || thisNodeTypes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        List<NodeTypeT> chngNodes = new ArrayList<>();
        List<NodeTypeT> finalChngNodes = chngNodes;

        thisNodeTypes.forEach(thisNodeType -> {
            thisNodeType = dataContext.merge(thisNodeType);
            if (thisNodeType != null) {

                Boolean thisNodeTypeIsChanged = false;

                thisNodeTypeIsChanged = onSetBtnClickHelper(thisNodeType);

                if (thisNodeTypeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisNodeType).");
                    //dataManager.save(thisNodeType);
                }
            }
        });
        chngNodes = finalChngNodes.stream().distinct().collect(Collectors.toList());
        updateHelper(chngNodes);
        logger.trace(logPrfx + " <-- ");
    }

    public Boolean onSetBtnClickHelper(NodeTypeT thisNodeType){
        String logPrfx = "onSetBtnClickHelper";
        logger.trace(logPrfx + " --> ");

        Boolean thisNodeTypeIsChanged = false;

        UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

        thisNodeTypeIsChanged = service.updateId2Calc(this, thisNodeType, updOption) || thisNodeTypeIsChanged;
        thisNodeTypeIsChanged = service.updateId2(this, thisNodeType, updOption) || thisNodeTypeIsChanged;
        thisNodeTypeIsChanged = service.updateId2Cmp(this, thisNodeType, updOption) || thisNodeTypeIsChanged;

        logger.trace(logPrfx + " <-- ");
        return thisNodeTypeIsChanged;
    }
    
    protected void updateHelper(List<NodeTypeT> chngNodes) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");


        if(chngNodes != null && !chngNodes.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<NodeTypeT> thisNodeTypes = tableMain.getSelected().stream().toList();

            UpdateOption updOption = UpdateOption.valueOf(updateInstItemCalcValsOption.getValue())
                .orElse(UpdateOption.SKIP);

            //Loop throught the items again to update the id2Dup attribute
            chngNodes.forEach(thisNodeType -> {
                //NodeT thisNodeType = dataContext.merge(thisNodeType);
                if (thisNodeType != null) {
                    thisNodeType = dataContext.merge(thisNodeType);
                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = service.updateId2Dup(this, thisNodeType, updOption) || thisNodeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisNodeTypes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<NodeTypeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        NodeTypeT thisNodeType = event.getSource().getItemOrNull();
        if (thisNodeType == null) {
            logger.debug(logPrfx + " --- thisNodeType is null, likely because no record is selected.");
            //todo I observed thisNodeType is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }


}