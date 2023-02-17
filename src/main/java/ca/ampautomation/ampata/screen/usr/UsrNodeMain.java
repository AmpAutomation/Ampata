package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNodeQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.LookupComponent;

import java.util.List;

@UiController("enty_UsrNode.main")
@UiDescriptor("usr-node-main.xml")
@LookupComponent("tableMain")
public class UsrNodeMain extends UsrNodeBase<UsrNode,UsrNodeType, UsrNodeQryMngr> {

    //Common


    //Query Manager

    
    //Filter

    
    //Toolbar

    
    //Template

    
    //Main data containers, loaders and table

    
    //Type data container and loader


    //GenFmla data container and loader


    //Other data loaders, containers and tables


    //Field



    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        super.onBeforeShow(event);

        logger.trace(logPrfx + " <-- ");

    }

    @Override
    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainCollectionChange(CollectionContainer.CollectionChangeEvent<UsrNode> event) {
        String logPrfx = "onColCntnrMainCollectionChange";
        logger.trace(logPrfx + " --> ");

        super.onColCntnrMainCollectionChange(event);

        logger.trace(logPrfx + " <-- ");

    }

    @Override
    @Subscribe(id = "colCntnrMain", target = Target.DATA_CONTAINER)
    public void onColCntnrMainItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onColCntnrMainItemChange";
        logger.trace(logPrfx + " --> ");

        super.onColCntnrMainItemChange(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        logger.trace(logPrfx + " <-- ");

    }

    @Override
    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateColCalcValsBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onDuplicateBtnClick(event);
    }


    @Override
    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onSetBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    protected void updateHelper() {
        String logPrfx = "updateHelper";
        logger.trace(logPrfx + " --> ");

        super.updateHelper();

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateColItemCalcValsBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe(id = "instCntnrMain", target = Target.DATA_CONTAINER)
    public void onInstCntnrMainItemChange(InstanceContainer.ItemChangeEvent<UsrNode> event) {
        String logPrfx = "onNodeDcItemChange";
        logger.trace(logPrfx + " --> ");

        super.onInstCntnrMainItemChange(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateInstItemValsBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        super.onId2FieldValueChange(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateId2FieldBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateId2CalcFieldBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateId2CmpFieldBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateId2DupFieldBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateType1_IdFieldListBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateInst1FieldBtn")
    public void onUpdateInst1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInst1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateInst1FieldBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Subscribe("updateName1GenFmla1_IdFieldListBtn")
    public void onUpdateName1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateName1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateName1GenFmla1_IdFieldListBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateDesc1FieldBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("updateDesc1GenFmla1_IdFieldListBtn")
    public void onUpdateDesc1GenFmla1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1GenFmla1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onUpdateDesc1GenFmla1_IdFieldListBtnClick(event);

        logger.trace(logPrfx + " <-- ");
    }

}