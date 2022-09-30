package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.component.propertyfilter.SingleFilterSupport;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@UiController("ampata_FinStmtItm.edit")
@UiDescriptor("fin-stmt-itm-edit.xml")
@EditedEntityContainer("finStmtItmDc")
public class FinStmtItmEdit extends StandardEditor<GenNode> {

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GenNodeRepository repo;

    @Autowired
    private DataComponents dataComponents;

    @Autowired
    private FetchPlans fetchPlans;

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private Notifications notifications;



    private CollectionContainer<GenNode> genAgentsDc;

    private CollectionLoader<GenNode> genAgentsDl;


    @Autowired
    private InstanceContainer<GenNode> finStmtItmDc;

    private CollectionContainer<GenNodeType> finStmtItmTypesDc;

    private CollectionLoader<GenNodeType> finStmtItmTypesDl;


    private CollectionContainer<GenNode> genDocVersDc;

    private CollectionLoader<GenNode> genDocVersDl;


    private CollectionContainer<GenNode> genTagsDc;

    private CollectionLoader<GenNode> genTagsDl;


    private CollectionContainer<GenNode> finStmtsDc;

    private CollectionLoader<GenNode> finStmtsDl;


    private CollectionContainer<GenNode> finAcctsDc;

    private CollectionLoader<GenNode> finAcctsDl;



    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextField<String> id2Field;

    @Autowired
    private TextField<String> id2CalcField;

    @Autowired
    private EntityComboBox<GenNodeType> type1_IdField;


    @Autowired
    private ComboBox<String> desc1Field;

    @Autowired
    private ComboBox<String> desc2Field;

    @Autowired
    private ComboBox<String> desc3Field;

    @Autowired
    private ComboBox<String> desc4Field;



    @Autowired
    private EntityComboBox<GenNode> genDocVer1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag1_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag2_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag3_IdField;

    @Autowired
    private EntityComboBox<GenNode> genTag4_IdField;


    @Autowired
    private EntityComboBox<GenNode> finStmt1_IdField;




    Logger logger = LoggerFactory.getLogger(FinStmtItmBrowse.class);


    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        desc1Field.setNullOptionVisible(true);
        desc1Field.setNullSelectionCaption("<null>");
        desc2Field.setNullOptionVisible(true);
        desc2Field.setNullSelectionCaption("<null>");
        desc3Field.setNullOptionVisible(true);
        desc3Field.setNullSelectionCaption("<null>");
        desc4Field.setNullOptionVisible(true);
        desc4Field.setNullSelectionCaption("<null>");


        finStmtItmTypesDc = dataComponents.createCollectionContainer(GenNodeType.class);
        finStmtItmTypesDl = dataComponents.createCollectionLoader();
        finStmtItmTypesDl.setQuery("select e from ampata_GenNodeType e where e.className = 'FinStmtItm' order by e.id2");
        FetchPlan finStmtItmTypesFp = fetchPlans.builder(GenNodeType.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtItmTypesDl.setFetchPlan(finStmtItmTypesFp);
        finStmtItmTypesDl.setContainer(finStmtItmTypesDc);
        finStmtItmTypesDl.setDataContext(getScreenData().getDataContext());

        type1_IdField.setOptionsContainer(finStmtItmTypesDc);


        genDocVersDc = dataComponents.createCollectionContainer(GenNode.class);
        genDocVersDl = dataComponents.createCollectionLoader();
        genDocVersDl.setQuery("select e from ampata_GenNode e where e.className = 'GenDocVer' order by e.id2");
        FetchPlan genDocVersFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genDocVersDl.setFetchPlan(genDocVersFp);
        genDocVersDl.setContainer(genDocVersDc);
        genDocVersDl.setDataContext(getScreenData().getDataContext());

        genDocVer1_IdField.setOptionsContainer(genDocVersDc);


        genTagsDc = dataComponents.createCollectionContainer(GenNode.class);
        genTagsDl = dataComponents.createCollectionLoader();
        genTagsDl.setQuery("select e from ampata_GenNode e where e.className = 'GenTag' order by e.id2");
        FetchPlan genTagsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        genTagsDl.setFetchPlan(genTagsFp);
        genTagsDl.setContainer(genTagsDc);
        genTagsDl.setDataContext(getScreenData().getDataContext());

        genTag1_IdField.setOptionsContainer(genTagsDc);
        genTag2_IdField.setOptionsContainer(genTagsDc);
        genTag3_IdField.setOptionsContainer(genTagsDc);
        genTag4_IdField.setOptionsContainer(genTagsDc);


        finStmtsDc = dataComponents.createCollectionContainer(GenNode.class);
        finStmtsDl = dataComponents.createCollectionLoader();
        finStmtsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinStmt' order by e.id2");
        FetchPlan finStmtsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finStmtsDl.setFetchPlan(finStmtsFp);
        finStmtsDl.setContainer(finStmtsDc);
        finStmtsDl.setDataContext(getScreenData().getDataContext());

        finStmt1_IdField.setOptionsContainer(finStmtsDc);


        finAcctsDc = dataComponents.createCollectionContainer(GenNode.class);
        finAcctsDl = dataComponents.createCollectionLoader();
        finAcctsDl.setQuery("select e from ampata_GenNode e where e.className = 'FinAcct' order by e.id2");
        FetchPlan finAcctsFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finAcctsDl.setFetchPlan(finAcctsFp);
        finAcctsDl.setContainer(finAcctsDc);
        finAcctsDl.setDataContext(getScreenData().getDataContext());

        logger.trace(logPrfx + " <--- ");


    }


    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- Changed entity: " + event.getEntity());

        logger.trace(logPrfx + " <-- ");
    }




    /*
    BeforeShowEvent is sent right before the screen is shown, for example, it is not added to the application UI yet.
    Security restrictions are applied to UI components. In this event listener, you can load data,
    check permissions and modify UI components.
    */
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        String logPrfx = "onBeforeShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }

    /*
    AfterShowEvent is sent right after the screen is shown, for example, when it is added to the application UI.
    In this event listener, you can show notifications, dialogs or other screens
    */
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        String logPrfx = "onAfterShow";
        logger.trace(logPrfx + " --> ");

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        finStmtItmTypesDl.load();
        logger.debug(logPrfx + " --- called finStmtItmTypesDl.load() ");


        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");

        reloadDesc1List();
        reloadDesc2List();
        reloadDesc3List();
        reloadDesc4List();

        finAcctsDl.load();
        logger.debug(logPrfx + " --- called finAcctsDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateInstItemIdPartsBtn")
    public void onUpdateInstIdPartsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstIdPartsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateIdParts(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateInstItemCalcValsBtn")
    public void onUpdateInstItemValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateInstItemValsBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        updateCalcVals(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinStmtItm);
            updateId2Dup(thisFinStmtItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinStmtItm);
        updateId2Cmp(thisFinStmtItm);
        updateId2Dup(thisFinStmtItm);

        logger.debug(logPrfx + " --- id2: " + thisFinStmtItm.getId2());
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinStmtItm);
        updateId2Cmp(thisFinStmtItm);

        logger.debug(logPrfx + " --- id2Calc: " + thisFinStmtItm.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateType1_IdFieldListBtn")
    public void onUpdateType1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateType1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        finStmtItmTypesDl.load();
        logger.debug(logPrfx + " --- called finStmtItmTypesDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("beg1Ts1Field")
    public void onBeg1Ts1FieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onBeg1Ts1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- thisFinStmtItm is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }

            logger.debug(logPrfx + " --- calling updateIdTs(thisFinStmtItm)");
            updateIdTs(thisFinStmtItm);

            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinStmtItm)");
            updateId2Calc(thisFinStmtItm);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateBeg1Ts1FieldBtn")
    public void onUpdateBeg1Ts1FieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateBeg1Ts1FieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateBeg1(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("idXField")
    public void onIdXFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        String logPrfx = "onIdXFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
            if (thisFinStmtItm == null) {
                logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            logger.debug(logPrfx + " --- calling updateId2Calc(thisFinStmtItm)");
            updateId2Calc(thisFinStmtItm);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateIdXFieldBtn")
    public void onUpdateIdXFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateIdXFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateIdX(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateGenDocVer1_IdFieldListBtn")
    public void onUpdateGenDocVer1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenDocVer1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genDocVersDl.load();
        logger.debug(logPrfx + " --- called genDocVersDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateGenTag1_IdFieldListBtn")
    public void onUpdateGenTag1_IdFieldListBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateGenTag1_IdFieldListBtn";
        logger.trace(logPrfx + " --> ");

        genTagsDl.load();
        logger.debug(logPrfx + " --- called genTagsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "desc1Field", subject = "enterPressHandler")
    private void desc1FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc1FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc1FieldListBtn")
    public void onUpdateDesc1FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "desc2Field", subject = "enterPressHandler")
    private void desc2FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc2FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc2FieldListBtn")
    public void onUpdateDesc2FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc2FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc2List();

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "desc3Field", subject = "enterPressHandler")
    private void desc3FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc3FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc3FieldListBtn")
    public void onUpdateDesc3FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc3FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc3List();

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "desc4Field", subject = "enterPressHandler")
    private void desc4FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "desc4FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateDesc4FieldListBtn")
    public void onUpdateDesc4FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc4FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadDesc3List();

        logger.trace(logPrfx + " <-- ");
    }



    @Subscribe("updateFinStmt1_IdFieldListBtn")
    public void onUpdateFinStmt1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinStmt1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finStmtsDl.load();
        logger.debug(logPrfx + " --- called finStmtsDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmtNetBtn")
    public void onUpdateAmtNetBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmtNetBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateAmtNet(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxfers1_DebtSumFieldFieldBtn")
    public void onUpdateFinTxfers1_DebtSumFieldFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_DebtSumFieldFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_DebtSum(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxfers1_CredSumFieldFieldBtn")
    public void onUpdateFinTxfers1_CredSumFieldFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_CredSumFieldFieldBtn";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_CredSum(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateFinTxfers1_IdCntFieldBtn")
    public void onUpdateFinTxfers1_IdCntFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_IdCntFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_IdCnt(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinTxfers1_DebtEqCredBoxBtn")
    public void onUpdateFinTxfers1_DebtEqCredBoxBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinTxfers1_DebtEqCredBoxBtnClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinStmtItm = finStmtItmDc.getItemOrNull();
        if (thisFinStmtItm == null) {
            logger.debug(logPrfx + " --- finStmtItmDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateFinTxfers1_DebtEqCred(thisFinStmtItm);

        logger.trace(logPrfx + " <-- ");

    }


    private Boolean updateCalcVals(@NotNull GenNode thisFinStmtItm) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        isChanged = updateFinStmtItmCalcVals(thisFinStmtItm) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateFinStmtItmCalcVals(@NotNull GenNode thisFinStmtItm) {
        String logPrfx = "updateFinStmtItmCalcVals";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        // Stored in FinStmtItm Object
        isChanged = updateIdTs(thisFinStmtItm) || isChanged;
        isChanged = updateId2Calc(thisFinStmtItm) || isChanged;
        isChanged = updateId2Cmp(thisFinStmtItm) || isChanged;
        isChanged = updateId2Dup(thisFinStmtItm) || isChanged;
        isChanged = updateAmtNet(thisFinStmtItm) || isChanged;

        isChanged = updateFinTxfers1_DebtSum(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxfers1_CredSum(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxfers1_IdCnt(thisFinStmtItm)  || isChanged;
        isChanged = updateFinTxfers1_DebtEqCred(thisFinStmtItm)  || isChanged;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdParts(@NotNull GenNode thisFinStmtItm) {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;

        isChanged = updateBeg1(thisFinStmtItm) || isChanged;
        isChanged = updateIdX(thisFinStmtItm)  || isChanged;
        isChanged = updateIdTs(thisFinStmtItm)  || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateId2(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        String id2_ = thisFinStmtItm.getId2();
        String id2 = thisFinStmtItm.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinStmtItm.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        String id2Calc_ = thisFinStmtItm.getId2Calc();
        String id2Calc = thisFinStmtItm.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinStmtItm.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Boolean id2Cmp_ = thisFinStmtItm.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinStmtItm.getId2(),thisFinStmtItm.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinStmtItm.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Integer id2Dup_ = thisFinStmtItm.getId2Dup();
        if (thisFinStmtItm.getId2() != null) {
            String id2Qry = "select count(e) from ampata_GenNode e where e.className = 'FinStmtItm' and e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinStmtItm.getId())
                        .parameter("id2", thisFinStmtItm.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinStmtItm.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinStmtItm.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdTs(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateIdTs";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateIdTs();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg1(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateBeg1";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateBeg1();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateBeg2(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateBeg2";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateBeg2();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateIdX(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateIdX";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        isChanged = isChanged || thisFinStmtItm.updateIdX();

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    private Boolean updateFinTxfers1_DebtSum(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateDebtSum";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal debtSum_ = thisFinStmtItm.getFinTxfers1_DebtSum();
        BigDecimal debtSum = null ;
        String qry1 = "select sum(e.amtDebt) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            debtSum = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
                    .one();
            if (debtSum == null) {
                debtSum = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- debtSum: " + debtSum);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- debtSum: null");
        }

        if(!Objects.equals(debtSum_, debtSum)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_DebtSum(debtSum);
            logger.debug(logPrfx + " --- debtSum: " + debtSum);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxfers1_CredSum(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateCredSum";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal credSum_ = thisFinStmtItm.getFinTxfers1_CredSum();
        BigDecimal credSum = null ;
        String qry1 = "select sum(e.amtCred) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            credSum = dataManager.loadValue(qry1,BigDecimal.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
                    .one();
            if (credSum == null) {
                credSum = BigDecimal.valueOf(0);}
            logger.debug(logPrfx + " --- credSum: " + credSum);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- credSum: null");
        }

        if(!Objects.equals(credSum_, credSum)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_CredSum(credSum);
            logger.debug(logPrfx + " --- credSum: " + credSum);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinTxfers1_IdCnt(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxfers1_IdCnt";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Integer idCnt_ = thisFinStmtItm.getFinTxfers1_IdCnt();
        Integer idCnt = null ;
        String qry1 = "select count(e.amtNet) from ampata_GenNode e where e.className = 'FinTxfer' and e.finStmtItm1_Id = :finStmtItm1_Id";
        try{
            idCnt = dataManager.loadValue(qry1,Integer.class)
                    .store("main")
                    .parameter("finStmtItm1_Id",thisFinStmtItm)
                    .one();
            if (idCnt == null) {
                idCnt = 0;}
            logger.debug(logPrfx + " --- idCnt: " + idCnt);

        } catch (IllegalStateException e){
            logger.debug(logPrfx + " --- IllegalStateException message: " + e.getMessage());
            logger.debug(logPrfx + " --- debtSum: null");
        }

        if(!Objects.equals(idCnt_, idCnt)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_IdCnt(idCnt);
            logger.debug(logPrfx + " --- idCnt: " + idCnt);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private GenNode findFinStmtItmById2(@NotNull String finStmtItm_Id2) {
        String logPrfx = "findFinStmtItmById2";
        logger.trace(logPrfx + " --> ");

        if (finStmtItm_Id2 == null) {
            logger.debug(logPrfx + " --- finStmtItm_Id2 is null.");
            notifications.create().withCaption("finStmtItm_Id2 is empty. Please set it to correct value.").show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from ampata_GenNode e where e.className = 'FinStmtItm' and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + finStmtItm_Id2);

        GenNode finStmtItm1_Id = null;
        try {
            finStmtItm1_Id = dataManager.load(GenNode.class)
                    .query(qry)
                    .parameter("id2", finStmtItm_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return finStmtItm1_Id;
    }

    private Boolean updateFinTxfers1_DebtEqCred(@NotNull GenNode thisFinStmtItm) {
        // Assume thisFinStmtItm is not null
        String logPrfx = "updateFinTxfers1_DebtEqCred";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        Boolean debtEqCred_ = thisFinStmtItm.getFinTxfers1_DebtEqCred();
        Boolean debtEqCred = Objects.equals(thisFinStmtItm.getFinTxfers1_DebtSum()
                , thisFinStmtItm.getFinTxfers1_CredSum());

        if(!Objects.equals(debtEqCred_, debtEqCred)){
            isChanged = true;
            thisFinStmtItm.setFinTxfers1_DebtEqCred(debtEqCred);
            logger.debug(logPrfx + " --- debtEqCred: " + debtEqCred);
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateAmtNet(@NotNull GenNode thisFinStmtItm) {
        //Assume thisFinStmtItm is not null
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        Boolean isChanged = false;
        BigDecimal netAmt_ = thisFinStmtItm.getAmtNet();

        GenNode thisFinStmt = thisFinStmtItm.getFinStmt1_Id();
        if (thisFinStmt == null) {
            logger.debug(logPrfx + " --- finStmt_Id is null. Please select an account.");
            notifications.create().withCaption("FinStmt_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        GenNode thisFinAcct = thisFinStmt.getFinAcct1_Id();
        if (thisFinAcct == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id is null. Please select an account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        GenNodeType thisFinAcctType = thisFinAcct.getType1_Id();
        if (thisFinAcctType == null) {
            logger.debug(logPrfx + " --- finStmt_Id.finAcct1_Id.type1_Id is null. Please select an account.");
            notifications.create().withCaption("finStmt_Id.finAcct1_Id.type1_Id is null. Please select a type for this account.").show();
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        BigDecimal netAmt = BigDecimal.valueOf(0);
        Boolean balIncOnDebt = thisFinAcctType.getBalIncOnDebt();
        Boolean balIncOnCred = thisFinAcctType.getBalIncOnCred();

        if (thisFinStmtItm.getAmtDebt() != null) {
            if (balIncOnDebt == null || !balIncOnDebt) {
                netAmt = netAmt.subtract(thisFinStmtItm.getAmtDebt());
            } else {
                netAmt = netAmt.add(thisFinStmtItm.getAmtDebt());
            }
        }

        if (thisFinStmtItm.getAmtCred() != null) {
            if (balIncOnCred == null || !balIncOnCred) {
                netAmt = netAmt.subtract(thisFinStmtItm.getAmtCred());
            } else {
                netAmt = netAmt.add(thisFinStmtItm.getAmtCred());
            }
        }
        if(!Objects.equals(netAmt_, netAmt)){
            thisFinStmtItm.setAmtNet(netAmt);
            logger.debug(logPrfx + " --- amtNet: " + netAmt);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private void reloadDesc1List(){
        String logPrfx = "reloadDesc1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc1"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.desc1 IS NOT NULL"
                + " order by e.desc1"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        desc1Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc1Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadDesc2List(){
        String logPrfx = "reloadDesc2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc2"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.desc2 IS NOT NULL"
                + " order by e.desc2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        desc2Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc2Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadDesc3List(){
        String logPrfx = "reloadDesc3List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc3"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.desc3 IS NOT NULL"
                + " order by e.desc3"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        desc3Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc3Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }

    private void reloadDesc4List(){
        String logPrfx = "reloadDesc4List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.desc4"
                + " from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.desc4 IS NOT NULL"
                + " order by e.desc4"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> descs = null;
        try {
            descs = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + descs.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        desc4Field.setOptionsList(descs);
        logger.debug(logPrfx + " --- called desc4Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void addEnteredTextToComboBoxOptionsList(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
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

    private Integer getIdXMax(LocalDateTime idTs1){
        String logPrfx = "getIdXMax";
        logger.trace(logPrfx + " --> ");

        Integer idX, idXMax;
        String idXMaxQry = "select max(e.idX) from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.idTs.ts1 = :idTs1";
        try {
            idXMax = dataManager.loadValue(idXMaxQry, Integer.class)
                    .store("main")
                    .parameter("idTs1", idTs1)
                    .one();
            // max returns null if no rows or if all rows have a null value
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- idXMaxQry error: " + e.getMessage());
            notifications.create().withCaption("idXMaxQry error: " + e.getMessage()).show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }
        logger.debug(logPrfx + " --- idXMaxQry result: " + idXMax + "");

        Integer idXCntIsNull = null;
        String idXCntIsNullQry = "select count(e) from ampata_GenNode e"
                + " where e.className = 'FinStmtItm'"
                + " and e.idTs.ts1 = :idTs1"
                + " and e.idX is null";
        try {
            idXCntIsNull = dataManager.loadValue(idXCntIsNullQry, Integer.class)
                    .store("main")
                    .parameter("idTs1", idTs1)
                    .one();
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- idXCntIsNullQry error: " + e.getMessage());
            notifications.create().withCaption("idXCntIsNullQry error: " + e.getMessage()).show();
            logger.trace(logPrfx + " <-- ");
            return null;
        }
        logger.debug(logPrfx + " --- idXCntIsNullQry result: " + idXCntIsNull + "");

        if (idXMax == null && idXCntIsNull != 0){
            idX = 1;
        }else{
            idX = idXMax == null ? 0 : idXMax + 1;
        }

        logger.trace(logPrfx + " <-- ");
        return idX;

    }


}