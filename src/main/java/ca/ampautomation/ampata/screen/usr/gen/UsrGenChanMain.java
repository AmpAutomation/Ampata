package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNodeRepo;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.gen.*;
import ca.ampautomation.ampata.screen.usr.UsrNodeBase;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Objects;

@UiController("enty_UsrGenChan.main")
@UiDescriptor("usr-gen-chan-main.xml")
@LookupComponent("tableMain")
public class UsrGenChanMain extends UsrNodeBase<UsrGenChan, UsrGenChanType, UsrGenChanQryMngr> {


    //Common


    //Query Manager


    //Filter


    //Toolbar


    //Template


    //Main data containers, loaders and table


    //Type data container and loader


    //GenFmla data container and loader


    //Other data loaders, containers and tables
    private CollectionContainer<UsrGenDocVer> colCntnrGenDocVer;
    private CollectionLoader<UsrGenDocVer> colLoadrGenDocVer;

    private CollectionContainer<UsrGenFile> colCntnrGenFile;
    private CollectionLoader<UsrGenFile> colLoadrGenFile;

    private CollectionContainer<UsrGenTag> colCntnrGenTag;
    private CollectionLoader<UsrGenTag> colLoadrGenTag;


    //Field
    @Autowired
    private EntityComboBox<UsrGenDocVer> genDocVer1_IdField;
    @Autowired
    private EntityComboBox<UsrGenFile> genFile1_IdField;
    @Autowired
    private EntityComboBox<UsrGenTag> genTag1_IdField;



    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        colCntnrGenDocVer = dataComponents.createCollectionContainer(UsrGenDocVer.class);
        colLoadrGenDocVer = dataComponents.createCollectionLoader();
        colLoadrGenDocVer.setQuery("select e from enty_"+ UsrGenDocVer.class.getSimpleName() + " e order by e.id2");
        FetchPlan fchPlnGenDocVer_Inst = fetchPlans.builder(UsrGenDocVer.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenDocVer.setFetchPlan(fchPlnGenDocVer_Inst);
        colLoadrGenDocVer.setContainer(colCntnrGenDocVer);
        colLoadrGenDocVer.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(colCntnrGenDocVer);


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


        colCntnrGenTag = dataComponents.createCollectionContainer(UsrGenTag.class);
        colLoadrGenTag = dataComponents.createCollectionLoader();
        colLoadrGenTag.setQuery("select e from enty_"+ UsrGenTag.class.getSimpleName() + " e order by e.id2");
        FetchPlan fchPlnGenTag_Inst = fetchPlans.builder(UsrGenTag.class)
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

        colLoadrGenDocVer.load();
        logger.debug(logPrfx + " --- called colLoadrGenDocVer.load() ");

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

        List<UsrGenChan> thisGenChans = tableMain.getSelected().stream().toList();
        if (thisGenChans == null || thisGenChans.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenChan is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenChans.forEach(thisGenChan -> {
            thisGenChan = dataContext.merge(thisGenChan);
            if (thisGenChan != null) {

                Boolean thisGenChanIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenChanIsChanged = true;
                    thisGenChan.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                thisGenChanIsChanged = thisGenChan.updateId2Calc() || thisGenChanIsChanged;
                thisGenChanIsChanged = thisGenChan.updateId2() || thisGenChanIsChanged;
                thisGenChanIsChanged = thisGenChan.updateId2Cmp() || thisGenChanIsChanged;

                if (thisGenChanIsChanged) {
                    //logger.debug(logPrfx + " --- executing dataManager.save(thisGenChan).");
                    //dataManager.save(thisGenChan);
                }
            }
        });
        super.updateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenDocVer1_IdFieldListBtn")
    public void onUpdateGenDocVer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenDocVer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenDocVer.load();
        logger.debug(logPrfx + " --- called colLoadrGenDocVer.load() ");

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