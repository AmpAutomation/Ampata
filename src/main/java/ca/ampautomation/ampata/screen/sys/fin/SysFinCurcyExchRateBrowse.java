package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyExchRateQryMngr;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.sys.SysNode;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@UiController("enty_SysFinCurcyExchRate.browse")
@UiDescriptor("sys-fin-curcy-exch-rate-browse.xml")
@LookupComponent("tableMain")
public class SysFinCurcyExchRateBrowse extends StandardLookup<SysNode> {


    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SysFinCurcyExchRateQryMngr qryMngr;

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


    //Filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_Beg1Date1GE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_Beg1Date1LE;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_SysFinCurcy1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_SysFinCurcy2_Id;

    //Toolbar

    //Template
    @Autowired
    private CheckBox tmplt_Beg1Ts1FieldChk;

    @Autowired
    private DateField<LocalDateTime> tmplt_Beg1Ts1Field;


    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<SysNode> colCntnrMain;
    @Autowired
    private CollectionLoader<SysNode> colLoadrMain;
    @Autowired
    private Table<SysNode> tableMain;


    //Type data container and loader
    private CollectionContainer<SysNode> colCntnrType;
    private CollectionLoader<SysNode> colLoadrType;


    //Field



    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        colCntnrType = dataComponents.createCollectionContainer(SysNode.class);
        colLoadrType = dataComponents.createCollectionLoader();
        colLoadrType.setQuery("select e from enty_SysNode e where e.className = 'SysFinCurcy' order by e.id2");
        FetchPlan finCurcysFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrType.setFetchPlan(finCurcysFp);
        colLoadrType.setContainer(colCntnrType);
        colLoadrType.setDataContext(getScreenData().getDataContext());

        EntityComboBox<SysNode> propFilterCmpnt_SysFinCurcy1_Id = (EntityComboBox<SysNode>) filterConfig1A_SysFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_SysFinCurcy1_Id.setOptionsContainer(colCntnrType);

        EntityComboBox<SysNode> propFilterCmpnt_SysFinCurcy2_Id = (EntityComboBox<SysNode>) filterConfig1A_SysFinCurcy2_Id.getValueComponent();
        propFilterCmpnt_SysFinCurcy2_Id.setOptionsContainer(colCntnrType);

    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onChange(DataContext.ChangeEvent event) {
        String logPrfx = "onChange";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " -- Changed entity: " + event.getEntity());

        logger.trace(logPrfx + " <-- ");
    }


    /*
    AfterInitEvent is sent when the screen controller and all its declaratively defined components are created,
    dependency injection is completed, and all components have completed their internal initialization procedures.
    Nested screen fragments (if any) have sent their InitEvent and AfterInitEvent. In this event listener, you can
    create visual and data components and perform additional initialization if it depends on initialized nested
    fragments.
    */

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        String logPrfx = "onAfterInit";
        logger.trace(logPrfx + " --> ");

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

        tableMain.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "tableMain.[beg1.date1]", subject = "formatter")
    private String tableBegDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        colLoadrType.load();
        logger.debug(logPrfx + " --- called colLoadrType.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisSysFinCurcyExchRate = tableMain.getSelected().stream().toList();
        if (thisSysFinCurcyExchRate == null || thisSysFinCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisSysFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNode> sels = new ArrayList<>();

        thisSysFinCurcyExchRate.forEach(orig -> {

            SysNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            HasTmst beg1 = dataManager.create(HasTmst.class);
            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                beg1.setElTs(tmplt_Beg1Ts1Field.getValue());
                copy.getTs1().setElTs(beg1.getElTs());
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            SysNode savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });
        tableMain.sort("id2", Table.SortDirection.ASCENDING);
        tableMain.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisSysFinCurcyExchRate = tableMain.getSelected().stream().toList();
        if (thisSysFinCurcyExchRate == null || thisSysFinCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisSysFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNode> sels = new ArrayList<>();

        thisSysFinCurcyExchRate.forEach(orig -> {

            SysNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            HasTmst beg1 = dataManager.create(HasTmst.class);
            if (tmplt_Beg1Ts1FieldChk.isChecked()) {
                beg1.setElTs(tmplt_Beg1Ts1Field.getValue());
                copy.getTs1().setElTs(beg1.getElTs());
            }else{
                if (orig.getTs1().getElDt() != null) {
                    beg1.setElTs(orig.getTs1().getElTs().plusDays(1));
                    copy.getTs1().setElTs(beg1.getElTs());
                }
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            SysNode savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Derived SysFinCurcyExchRate " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });
        tableMain.sort("id2", Table.SortDirection.ASCENDING);
        tableMain.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }

}