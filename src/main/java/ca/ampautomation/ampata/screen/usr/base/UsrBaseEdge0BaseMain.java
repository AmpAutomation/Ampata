package ca.ampautomation.ampata.screen.usr.base;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseEdge;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseEdgeType;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseQryMngr;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenBasc;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class UsrBaseEdge0BaseMain<EdgeT extends UsrBaseEdge, EdgeTypeT extends UsrBaseEdgeType, EdgeQryMngrT extends UsrBaseQryMngr, TableT extends Table> extends MasterDetailScreen<EdgeT> {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    protected Class<EdgeT> typeOfEdgeT;
    protected Class<EdgeTypeT> typeOfEdgeTypeT;

    @SuppressWarnings("unchecked")
    public UsrBaseEdge0BaseMain() {
        this.typeOfEdgeT = (Class<EdgeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.typeOfEdgeTypeT = (Class<EdgeTypeT>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected ListComponent<EdgeT> getTable() {
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
    protected EdgeQryMngrT qryMngr;


    //Toolbar
    @Autowired
    protected ComboBox<Integer> updateColItemCalcValsOption;

    @Autowired
    protected ComboBox<Integer> updateInstItemCalcValsOption;


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<EdgeTypeT> filterConfig1A_Type1_Id;


    //Template
    @Autowired
    protected CheckBox tmplt_Type1_IdFieldChk;
    @Autowired
    protected EntityComboBox<EdgeTypeT> tmplt_Type1_IdField;


    //Main data containers, loaders and table
    @Autowired
    protected CollectionLoader<EdgeT> colLoadrMain;
    @Autowired
    protected CollectionContainer<EdgeT> colCntnrMain;
    @Autowired
    protected InstanceContainer<EdgeT> instCntnrMain;
    @Autowired
    protected TableT tableMain;


    //Type data container and loader
    protected CollectionContainer<EdgeTypeT> colCntnrType;
    protected CollectionLoader<EdgeTypeT> colLoadrType;

    //GenTag data container loader and data property container
    protected CollectionContainer<UsrGenTag> colCntnrGenTag;
    protected CollectionLoader<UsrGenTag> colLoadrGenTag;
    @Autowired
    protected CollectionPropertyContainer<UsrGenTag> colPropCntnrGenTag;


    //GenFmla data container and loader
    protected CollectionLoader<UsrGenFmla> colLoadrGenFmla;
    protected CollectionContainer<UsrGenFmla> colCntnrGenFmla;


    //Other data loaders, containers and tables


    //Field
    @Autowired
    protected TextField<String> id2Field;

    @Autowired
    protected TextField<String> id2CalcField;

    @Autowired
    protected EntityComboBox<EdgeTypeT> type1_IdField;

    @Autowired
    protected EntityComboBox<UsrGenFmla> name1GenFmla1_IdField;

    @Autowired
    protected EntityComboBox<UsrGenFmla> desc1GenFmla1_IdField;

    @Autowired
    protected TagField<UsrGenTag> genTags1_IdField;

    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colCntnrType = dataComponents.createCollectionContainer(this.typeOfEdgeTypeT);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_"+ this.typeOfEdgeTypeT.getSimpleName() + " e order by e.sortKey, e.id2");
        FetchPlan fchPlnType_Inst = fetchPlans.builder(this.typeOfEdgeTypeT)
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
        EntityComboBox<EdgeTypeT> propFilterCmpnt_Type1_Id;
        propFilterCmpnt_Type1_Id = (EntityComboBox<EdgeTypeT>) filterConfig1A_Type1_Id.getValueComponent();
        propFilterCmpnt_Type1_Id.setOptionsContainer(colCntnrType);

        colCntnrGenFmla = dataComponents.createCollectionContainer(UsrGenFmla.class);
        colLoadrGenFmla = dataComponents.createCollectionLoader();
        colLoadrGenFmla.setQuery("select e from enty_UsrGenFmla e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenFmla_Inst = fetchPlans.builder(UsrGenFmla.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFmla.setFetchPlan(fchPlnGenFmla_Inst);
        colLoadrGenFmla.setContainer(colCntnrGenFmla);
        colLoadrGenFmla.setDataContext(getScreenData().getDataContext());
        //Field
        name1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);
        desc1GenFmla1_IdField.setOptionsContainer(colCntnrGenFmla);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrGenTag e order by e.sortKey, e.id2");
        FetchPlan fchPlnGenTag = fetchPlans.builder(UsrGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        colLoadrMain.load();
        tableMain.sort("id2", Table.SortDirection.ASCENDING);

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<EdgeT> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        if (event.getSource().getItemOrNull() == null){
            logger.debug(logPrfx + " --- thisEdge is null.");
            logger.trace(logPrfx + " <-- ");
            return;
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<EdgeT> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = event.getItem();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null");
            //todo I observed thisEdge is null when selecting a new item
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        colLoadrGenFmla.load();
        logger.debug(logPrfx + " --- called colLoadrGenFmla.load() ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

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

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdges.forEach(orig -> {
            EdgeT copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            EdgeT savedCopy = dataManager.save(copy);
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

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisEdges.forEach(thisEdge -> {
            thisEdge = dataContext.merge(thisEdge);
            if (thisEdge != null) {

                Boolean thisEdgeIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisEdgeIsChanged = true;
                    thisEdge.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisEdgeIsChanged = thisEdge.updateId2Calc(dataManager) || thisEdgeIsChanged;
                thisEdgeIsChanged = thisEdge.updateId2(dataManager) || thisEdgeIsChanged;
                thisEdgeIsChanged = thisEdge.updateId2Cmp(dataManager) || thisEdgeIsChanged;

                if (thisEdgeIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisEdge).");
                    //dataManager.save(thisEdge);
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

            List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisEdges.forEach(thisEdge -> {
                //T thisTrackedEdge = dataContext.merge(thisEdge);
                if (thisEdge != null) {
                    thisEdge = dataContext.merge(thisEdge);

                    Boolean thisEdgeIsChanged = false;

                    thisEdgeIsChanged = thisEdge.updateId2Dup(dataManager) || thisEdgeIsChanged;

                    if (thisEdgeIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisEdge).");
                        //dataManager.save(thisEdge);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.sort("id2", Table.SortDirection.ASCENDING);
                tableMain.setSelected(thisEdges);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    protected void updateHelper2(List<EdgeT> chngEdges) {
        String logPrfx = "updateHelper2";
        logger.trace(logPrfx + " --> ");

        if(chngEdges != null && !chngEdges.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngEdges.forEach(thisEdge -> {
                //UsrBaseEdge thisTrackedFinAcct = dataContext.merge(thisEdge);
                if (thisEdge != null) {
                    thisEdge = dataContext.merge(thisEdge);
                    Boolean thisEdgeIsChanged = false;

                    thisEdgeIsChanged = thisEdge.updateId2Dup(dataManager) || thisEdgeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisEdges);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    protected void updateHelper(List<UsrBaseEdge> chngEdges) {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        if(chngEdges != null && !chngEdges.isEmpty()) {

            //sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            chngEdges.forEach(thisEdge -> {
                //UsrBaseEdge thisTrackedFinAcct = dataContext.merge(thisEdge);
                if (thisEdge != null) {
                    thisEdge = dataContext.merge(thisEdge);
                    Boolean thisEdgeIsChanged = false;

                    thisEdgeIsChanged = thisEdge.updateId2Dup(dataManager) || thisEdgeIsChanged;

                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing colLoadrMain.load().");
                colLoadrMain.load();

                tableMain.setSelected(thisEdges);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdges.forEach(thisEdge -> {
            if (thisEdge != null) {

                thisEdge = dataContext.merge(thisEdge);;

                boolean isChanged = false;

                isChanged = thisEdge.updateCalcVals(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisEdges);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemId2Btn")
    public void onUpdateColItemId2BtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemId2BtnClick";
        logger.trace(logPrfx + " --> ");

        List<EdgeT> thisEdges = tableMain.getSelected().stream().toList();
        if (thisEdges == null || thisEdges.isEmpty()) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdges.forEach(thisEdge -> {
            if (thisEdge != null) {

                thisEdge = dataContext.merge(thisEdge);;

                boolean isChanged = false;

                isChanged = thisEdge.updateId2(dataManager);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing colLoadrMain.load().");
            colLoadrMain.load();

            tableMain.sort("id2", Table.SortDirection.ASCENDING);
            tableMain.setSelected(thisEdges);
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<EdgeT> event) {
        String logPrfx = "onInstCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = event.getSource().getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            //todo I observed thisEdge is null when selecting a new item
            //notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            EdgeT thisEdge = instCntnrMain.getItemOrNull();
            if (thisEdge == null) {
                logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisEdge.updateId2Deps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateId2(dataManager);
        thisEdge.updateId2Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateId2Calc(dataManager);
        thisEdge.updateId2Deps(dataManager);

        logger.debug(logPrfx + " --- id2Calc: " + thisEdge.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateId2Cmp(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateId2Dup(dataManager);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateName1FieldBtn")
    public void onUpdateName1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1FieldBtnClick[super]";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- instCntnrMain is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateName1(dataManager);
        thisEdge.updateName1Deps(dataManager);

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

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateInst1(dataManager);
        thisEdge.updateInst1Deps(dataManager);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        EdgeT thisEdge = instCntnrMain.getItemOrNull();
        if (thisEdge == null) {
            logger.debug(logPrfx + " --- thisEdge is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisEdge.updateDesc1(dataManager);

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

/*

    @Install(to = "genTags1_IdField", subject = "searchExecutor")
    protected List<UsrGenTag> genTags1_IdFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        return dataManager.load(UsrGenTag.class)
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

}