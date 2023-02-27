package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.gen.*;
import ca.ampautomation.ampata.screen.usr.UsrNode0BaseMain;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("enty_UsrGenDocVer.main")
@UiDescriptor("usr-gen-doc-ver-0-main.xml")
@LookupComponent("tableMain")
public class UsrGenDocVer0Main extends UsrNode0BaseMain<UsrGenDocVer, UsrGenDocVerType, UsrGenDocVerQryMngr, Table<UsrGenDocVer>> {


    //Common


    //Query Manager


    //Filter


    //Toolbar


    //Template


    //Main data containers, loaders and table


    //Type data container and loader


    //GenFmla data container and loader


    //Other data loaders, containers and tables
    private CollectionContainer<UsrGenChan> colCntnrGenChan;
    private CollectionLoader<UsrGenChan> colLoadrGenChan;

    private CollectionContainer<UsrGenFile> colCntnrGenFile;
    private CollectionLoader<UsrGenFile> colLoadrGenFile;

    private CollectionContainer<UsrGen> colCntnrGenTag;
    private CollectionLoader<UsrGen> colLoadrGenTag;


    //Field
    @Autowired
    private EntityComboBox<UsrGenChan> genChan1_IdField;
    @Autowired
    private EntityComboBox<UsrGenFile> genFile1_IdField;
    @Autowired
    private EntityComboBox<UsrGen> genTag1_IdField;



    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        colCntnrGenChan = dataComponents.createCollectionContainer(UsrGenChan.class);
        colLoadrGenChan = dataComponents.createCollectionLoader();
        colLoadrGenChan.setQuery("select e from enty_"+ UsrGenChan.class.getSimpleName() + " e order by e.id2");
        FetchPlan fchPlnGenChan_Inst = fetchPlans.builder(UsrGenChan.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenChan.setFetchPlan(fchPlnGenChan_Inst);
        colLoadrGenChan.setContainer(colCntnrGenChan);
        colLoadrGenChan.setDataContext(getScreenData().getDataContext());

        genChan1_IdField.setOptionsContainer(colCntnrGenChan);


        colCntnrGenFile = dataComponents.createCollectionContainer(UsrGenFile.class);
        colLoadrGenFile = dataComponents.createCollectionLoader();
        colLoadrGenFile.setQuery("select e from enty_"+ UsrGenFile.class.getSimpleName() + " e order by e.id2");
        FetchPlan fchPlnGenFile_Inst = fetchPlans.builder(UsrGenFile.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenFile.setFetchPlan(fchPlnGenFile_Inst);
        colLoadrGenFile.setContainer(colCntnrGenFile);
        colLoadrGenFile.setDataContext(getScreenData().getDataContext());

        genFile1_IdField.setOptionsContainer(colCntnrGenFile);


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGen.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_"+ UsrGen.class.getSimpleName() + " e order by e.id2");
        FetchPlan fchPlnGenTag_Inst = fetchPlans.builder(UsrGen.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenTag.setFetchPlan(fchPlnGenTag_Inst);
        colLoadrGenTag.setContainer(colCntnrGenTag);
        colLoadrGenTag.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(colCntnrGenTag);

        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        colLoadrGenFile.load();
        logger.debug(logPrfx + " --- called colLoadrGenFile.load() ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Override
    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrGenDocVer> thisGenDocVers = tableMain.getSelected().stream().toList();
        if (thisGenDocVers == null || thisGenDocVers.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenDocVer is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenDocVers.forEach(thisGenDocVer -> {
            thisGenDocVer = dataContext.merge(thisGenDocVer);
            if (thisGenDocVer != null) {

                Boolean thisGenDocVerIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenDocVerIsChanged = true;
                    thisGenDocVer.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisGenDocVerIsChanged = thisGenDocVer.updateId2Calc(dataManager) || thisGenDocVerIsChanged;
                thisGenDocVerIsChanged = thisGenDocVer.updateId2(dataManager) || thisGenDocVerIsChanged;
                thisGenDocVerIsChanged = thisGenDocVer.updateId2Cmp(dataManager) || thisGenDocVerIsChanged;

                if (thisGenDocVerIsChanged) {
                    //logger.debug(logPrfx + " --- executing dataManager.save(thisGenDocVer).");
                    //dataManager.save(thisGenDocVer);
                }
            }
        });
        super.updateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenChan1_IdFieldListBtn")
    public void onUpdateGenChan1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenChan1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenChan.load();
        logger.debug(logPrfx + " --- called colLoadrGenChan.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenFile1_IdFieldListBtn")
    public void onUpdateGenFile1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenFile1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenFile.load();
        logger.debug(logPrfx + " --- called genFilesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }



}