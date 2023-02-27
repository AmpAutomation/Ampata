package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNodeQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenAgent;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenAgentQryMngr;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenAgentType;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
import ca.ampautomation.ampata.screen.usr.UsrNodeBaseMain;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@UiController("enty_UsrGenAgent.main")
@UiDescriptor("usr-gen-agent-main.xml")
@LookupComponent("tableMain")
public class UsrGenAgentMain extends UsrNodeBaseMain<UsrGenAgent, UsrGenAgentType, UsrGenAgentQryMngr, Table<UsrGenAgent>> {

    //Filter

    @Autowired
    protected PropertyFilter<UsrGenAgent> filterConfig1A_GenAgent1_Id;

    @Autowired
    protected PropertyFilter<UsrGenAgent> filterConfig1A_GenAgent2_Id;


    @Autowired
    protected CheckBox tmplt_GenAgent1_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrGenAgent> tmplt_GenAgent1_IdField;

    @Autowired
    protected CheckBox tmplt_GenAgent2_IdFieldChk;
    @Autowired
    protected EntityComboBox<UsrGenAgent> tmplt_GenAgent2_IdField;



    //Other data loaders, containers and tables
    private CollectionContainer<UsrGenAgent> colCntnrGenAgent;
    private CollectionLoader<UsrGenAgent> colLoadrGenAgent;



    //Field

    @Autowired
    private EntityComboBox<UsrGenAgent> genAgent1_IdField;

    @Autowired
    private EntityComboBox<UsrGenAgent> genAgent2_IdField;


    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        colCntnrGenAgent = dataComponents.createCollectionContainer(UsrGenAgent.class);
        colLoadrGenAgent = dataComponents.createCollectionLoader();
        colLoadrGenAgent.setQuery("select e from enty_UsrGenAgent e where order by e.id2");
        FetchPlan genAgent1sFp = fetchPlans.builder(UsrGenAgent.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrGenAgent.setFetchPlan(genAgent1sFp);
        colLoadrGenAgent.setContainer(colCntnrGenAgent);
        colLoadrGenAgent.setDataContext(getScreenData().getDataContext());

        genAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        genAgent2_IdField.setOptionsContainer(colCntnrGenAgent);
        //template
        tmplt_GenAgent1_IdField.setOptionsContainer(colCntnrGenAgent);
        tmplt_GenAgent2_IdField.setOptionsContainer(colCntnrGenAgent);
        //filter
        EntityComboBox<UsrGenAgent> propFilterCmpnt_GenAgent1_Id;
        propFilterCmpnt_GenAgent1_Id = (EntityComboBox<UsrGenAgent>) filterConfig1A_GenAgent1_Id.getValueComponent();
        propFilterCmpnt_GenAgent1_Id.setOptionsContainer(colCntnrGenAgent);
        EntityComboBox<UsrGenAgent> propFilterCmpnt_GenAgent2_Id;
        propFilterCmpnt_GenAgent2_Id = (EntityComboBox<UsrGenAgent>) filterConfig1A_GenAgent2_Id.getValueComponent();
        propFilterCmpnt_GenAgent2_Id.setOptionsContainer(colCntnrGenAgent);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        colLoadrGenAgent.load();
        logger.debug(logPrfx + " --- called colLoadrGenAgent.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<UsrGenAgent> thisGenAgents = tableMain.getSelected().stream().toList();
        if (thisGenAgents == null || thisGenAgents.isEmpty()) {
            logger.debug(logPrfx + " --- thisGenAgent is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisGenAgents.forEach(thisGenAgent -> {
            thisGenAgent = dataContext.merge(thisGenAgent);
            if (thisGenAgent != null) {

                Boolean thisGenAgentIsChanged = false;

                if (tmplt_Type1_IdFieldChk.isChecked()
                ) {
                    thisGenAgentIsChanged = true;
                    thisGenAgent.setType1_Id(tmplt_Type1_IdField.getValue());
                }

                if (tmplt_GenAgent1_IdFieldChk.isChecked()
                ) {
                    thisGenAgentIsChanged = true;
                    thisGenAgent.setGenAgent1_Id(tmplt_GenAgent1_IdField.getValue());
                }

                if (tmplt_GenAgent2_IdFieldChk.isChecked()
                ) {
                    thisGenAgentIsChanged = true;
                    thisGenAgent.setGenAgent2_Id(tmplt_GenAgent2_IdField.getValue());
                }

                thisGenAgentIsChanged = thisGenAgent.updateId2Calc(dataManager) || thisGenAgentIsChanged;
                thisGenAgentIsChanged = thisGenAgent.updateId2(dataManager) || thisGenAgentIsChanged;
                thisGenAgentIsChanged = thisGenAgent.updateId2Cmp(dataManager) || thisGenAgentIsChanged;

                if (thisGenAgentIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisGenAgent).");
                    //dataManager.save(thisGenAgent);
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

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        colLoadrGenTag.load();
        logger.debug(logPrfx + " --- called colLoadrGenTag.load() ");

        logger.trace(logPrfx + " <-- ");
    }


}