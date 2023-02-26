package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrBaseQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import io.jmix.core.FetchPlan;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.screen.Install;
import io.jmix.ui.screen.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;


public abstract class UsrNodeBaseMain<UsrNodeT extends UsrNode, UsrNodeTypeT extends UsrNodeType, UsrNodeQryMngrT extends UsrBaseQryMngr> extends UsrNodeBaseMainN1<UsrNodeT, UsrNodeTypeT, UsrNodeQryMngrT> {


    //Filter
    @Autowired
    protected PropertyFilter<UsrNodeTypeT> filterConfig1A_Type1_Id;

    //Type data container and loader
    protected CollectionContainer<UsrNodeTypeT> colCntnrType;
    protected CollectionLoader<UsrNodeTypeT> colLoadrType;

    //GenTag data property container
    @Autowired
    protected CollectionPropertyContainer<UsrGenTag> colPropCntnrGenTag;


    //GenFmla data container and loader
    protected CollectionLoader<UsrGenFmla> colLoadrGenFmla;
    protected CollectionContainer<UsrGenFmla> colCntnrGenFmla;

    //Fields

    @Autowired
    protected EntityPicker<UsrNodeT> parent1_IdField;

    @Autowired
    protected EntityComboBox<UsrNodeTypeT> type1_IdField;

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

        super.onInit(event);


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


/*
        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_UsrGenTag e order by e.id2");
        FetchPlan fchPlnGenTag_Inst = fetchPlans.builder(UsrGenTag.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag_Inst);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());
        //Field
        genTags1_IdField.setOptionsContainer(colCntnrGenTag);
*/

        logger.trace(logPrfx + " <-- ");
    }



    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

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

/*

    @Install(to = "genTags1_IdField", subject = "searchExecutor")
    protected List<UsrGenTag> genTags1_IdFieldSearchExecutor(String searchString, Map<String, Object> searchParams) {
        return dataManager.load(UsrGenTag.class)
                .query("e.id2 like ?1 order by e.id2"
                        , "(?i)%" + searchString + "%")
                .list();
    }
*/
}