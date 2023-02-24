package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrBaseQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class UsrNodeBaseMain<UsrNodeT extends UsrNode, UsrNodeTypeT extends UsrNodeType, UsrNodeQryMngrT extends UsrBaseQryMngr> extends MasterDetailScreen<UsrNodeT> {

    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    private Class<UsrNodeT> typeOfUsrNodeT;
    private Class<UsrNodeTypeT> typeOfUsrNodeTypeT;

    @SuppressWarnings("unchecked")
    public UsrNodeBaseMain() {
        this.typeOfUsrNodeT = (Class<UsrNodeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfUsrNodeTypeT = (Class<UsrNodeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<UsrNodeT> getTable() {
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

    
    //Query Manager
    protected UsrNodeQryMngrT qryMngr;

    
    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<UsrNodeTypeT> filterConfig1A_Type1_Id;

    
    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;

    
    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrNodeTypeT> tmplt_Type1_IdField;

    
    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<UsrNodeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<UsrNodeT> colCntnrMain;
    @Autowired
    protected InstanceContainer<UsrNodeT> instCntnrMain;
    @Autowired
    protected TreeTable<UsrNodeT> tableMain;

    
    //Type data container and loader
    protected CollectionContainer<UsrNodeTypeT> colCntnrType;
    protected CollectionLoader<UsrNodeTypeT> colLoadrType;


    //GenFmla data container and loader
    protected CollectionLoader<UsrGenFmla> colLoadrGenFmla;
    protected CollectionContainer<UsrGenFmla> colCntnrGenFmla;


    //Other data loaders, containers and tables


    //Field
    @Autowired
    protected TextField<String> classNameField;

    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected EntityPicker<UsrNodeT> parent1_IdField;

    @Autowired
    protected EntityComboBox<UsrNodeTypeT> type1_IdField;

    @Autowired
    protected EntityComboBox<UsrGenFmla> name1GenFmla1_IdField;

    @Autowired
    protected EntityComboBox<UsrGenFmla> desc1GenFmla1_IdField;




    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        colCntnrType = dataComponents.createCollectionContainer(this.typeOfUsrNodeTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfUsrNodeTypeT.getSimpleName() + " e order by e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfUsrNodeTypeT)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(fchPlnType_Inst);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());
        //Field
        type1_IdField.setOptionsContainer(colCntnrType);
        //Template
        tmplt_Type1_IdField.setOptionsContainer(colCntnrType);
        //Filter
        EntityComboBox<UsrNodeTypeT> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<UsrNodeTypeT>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);


        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrGenFmla e order by e.id2");
        FetchPlan fchPlnGenFmla_Inst = fetchPlans.builder(UsrGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla_Inst);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());
        //Field
        name1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNodeT> event) {
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
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<UsrNodeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = event.getItem();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null");
            //todo I observed thisNode is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if (thisNode.getClassName() == null || thisNode.getClassName().isBlank()){
            thisNode.setClassName(this.getClass().getSimpleName());
            logger.debug(logPrfx + " --- className: " + this.getClass().getSimpleName());
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");

    }
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execPrUpdAllCalcValsforAllNative()");
        qryMngr.execPrUpdAllCalcValsforAllRowsNative();
        logger.debug(logPrfx + " --- finished repo.execPrUpdAllCalcValsforAllNative()");

        logger.debug(logPrfx + " --- loading colLoadrMain.load()");
        colLoadrMain.load();
        logger.debug(logPrfx + " --- finished colLoadrMain.load()");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(orig -> {
            UsrNodeT copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            UsrNodeT savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + ":" + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    +" -> "
                    +"[" + copy.getId() + "]"
            );
        });
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisNodes.forEach(thisNode -> {
            thisNode = dataContext.merge(thisNode);
            if (thisNode != null) {

                Boolean thisNodeIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisNodeIsChanged = true;
                    thisNode.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisNodeIsChanged = thisNode.updateId2Calc(dataManager) || thisNodeIsChanged;
                thisNodeIsChanged = thisNode.updateId2(dataManager) || thisNodeIsChanged;
                thisNodeIsChanged = thisNode.updateId2Cmp(dataManager) || thisNodeIsChanged;

                if (thisNodeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisNode).");
                    //dataManager.save(thisNode);
                }
            }
        });
        updateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    protected void updateHelper() {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNodeT> thisNodes = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisNodes.forEach(thisNode -> {
                //T thisTrackedNode = dataContext.merge(thisNode);
                if (thisNode != null) {
                    thisNode = dataContext.merge(thisNode);

                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = thisNode.updateId2Dup(dataManager) || thisNodeIsChanged;

                    if (thisNodeIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisNode).");
                        //dataManager.save(thisNode);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisNodes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    private void updateHelper(List<UsrNode> chngNodes) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(chngNodes != null && !chngNodes.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<UsrNodeT> thisNodes = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngNodes.forEach(thisNode -> {
                //UsrNode thisTrackedFinAcct = dataContext.merge(thisNode);
                if (thisNode != null) {
                    thisNode = dataContext.merge(thisNode);
                    Boolean thisNodeIsChanged = false;

                    thisNodeIsChanged = thisNode.updateId2Dup(dataManager) || thisNodeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisNodes);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrNodeT> thisNodes = tableMain.getSelected().stream().toList();
        if (thisNodes == null || thisNodes.isEmpty()) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNodes.forEach(thisNode -> {
            if (thisNode != null) {

                thisNode = dataContext.merge(thisNode);;

                boolean isChanged = false;

                isChanged = thisNode.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisNodes);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<UsrNodeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = event.getSource().getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            //todo I observed thisNode is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        if (StringUtils.isEmpty(thisNode.getClassName())) {
            thisNode.setClassName(typeOfUsrNodeT.getSimpleName());
            logger.debug(logPrfx + " --- className: " + typeOfUsrNodeT.getSimpleName());
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            UsrNodeT thisNode = instCntnrMain.getItemOrNull();
            if (thisNode == null) {
                logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisNode.updateId2Deps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2(dataManager);
        thisNode.updateId2Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2Calc(dataManager);
        thisNode.updateId2Deps(dataManager);

        logger.debug(logPrfx + " --- id2Calc: " + thisNode.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2Cmp(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateName1(dataManager);
        thisNode.updateName1Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1GenFmla1_IdFieldListBtn")
    public void onUpdateName1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

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

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateInst1(dataManager);
        thisNode.updateInst1Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        UsrNodeT thisNode = instCntnrMain.getItemOrNull();
        if (thisNode == null) {
            logger.debug(logPrfx + " --- thisNode is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisNode.updateDesc1(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1GenFmla1_IdFieldListBtn")
    public void onUpdateDesc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");
    }

}