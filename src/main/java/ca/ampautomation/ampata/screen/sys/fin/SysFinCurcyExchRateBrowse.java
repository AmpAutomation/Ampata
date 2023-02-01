package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.UsrNodeRepository;
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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@UiController("ampata_SysFinCurcyExchRate.browse")
@UiDescriptor("sys-fin-curcy-exch-rate-browse.xml")
@LookupComponent("sysFinCurcyExchRateTable")
public class SysFinCurcyExchRateBrowse extends StandardLookup<SysNode> {


    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<LocalDate> propFilter_beg1Date1GE;

    @Autowired
    protected PropertyFilter<LocalDate> propFilter_beg1Date1LE;

    @Autowired
    protected PropertyFilter<UsrNode> propFilter_sysFinCurcy1_Id;

    @Autowired
    protected PropertyFilter<UsrNode> propFilter_sysFinCurcy2_Id;

    @Autowired
    private CheckBox tmplt_beg1Date1FieldChk;

    @Autowired
    private DateField<LocalDate> tmplt_beg1Date1Field;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsrNodeRepository repo;

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
    private GroupTable<SysNode> table;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<SysNode> sysFinCurcyExchRatesDc;

    @Autowired
    private DataLoader sysFinCurcyExchRatesDl;


    private CollectionContainer<UsrNode> sysFinCurcysDc;

    private CollectionLoader<UsrNode> sysFinCurcysDl;




    Logger logger = LoggerFactory.getLogger(SysFinCurcyExchRateBrowse.class);

    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        sysFinCurcysDc = dataComponents.createCollectionContainer(UsrNode.class);
        sysFinCurcysDl = dataComponents.createCollectionLoader();
        sysFinCurcysDl.setQuery("select e from ampata_UsrNode e where e.className = 'SysFinCurcy' order by e.id2");
        FetchPlan sysFinCurcysFp = fetchPlans.builder(UsrNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        sysFinCurcysDl.setFetchPlan(sysFinCurcysFp);
        sysFinCurcysDl.setContainer(sysFinCurcysDc);
        sysFinCurcysDl.setDataContext(getScreenData().getDataContext());

        EntityComboBox<UsrNode> propFilterCmpnt_sysFinCurcy1_Id = (EntityComboBox<UsrNode>) propFilter_sysFinCurcy1_Id.getValueComponent();
        propFilterCmpnt_sysFinCurcy1_Id.setOptionsContainer(sysFinCurcysDc);

        EntityComboBox<UsrNode> propFilterCmpnt_sysFinCurcy2_Id = (EntityComboBox<UsrNode>) propFilter_sysFinCurcy2_Id.getValueComponent();
        propFilterCmpnt_sysFinCurcy2_Id.setOptionsContainer(sysFinCurcysDc);

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

        table.sort("id2", Table.SortDirection.ASCENDING);

        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "table.[beg1.date1]", subject = "formatter")
    private String tableBegDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");

        sysFinCurcysDl.load();
        logger.debug(logPrfx + " --- called sysFinCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisSysFinCurcyExchRate = table.getSelected().stream().toList();
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

            LocalDate beg1;
            if (tmplt_beg1Date1FieldChk.isChecked()) {
                beg1 = tmplt_beg1Date1Field.getValue();
                copy.getBeg1().setDate1(beg1);
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            SysNode savedCopy = dataManager.save(copy);
            sysFinCurcyExchRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated SysFinCurcyExchRate " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });
        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisSysFinCurcyExchRate = table.getSelected().stream().toList();
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

            LocalDate beg1;
            if (tmplt_beg1Date1FieldChk.isChecked()) {
                beg1 = tmplt_beg1Date1Field.getValue();
                copy.getBeg1().setDate1(beg1);
            }else{
                if (orig.getBeg1().getDate1() != null) {
                    beg1 = orig.getBeg1().getDate1().plusDays(1);
                    copy.getBeg1().setDate1(beg1);
                }
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            SysNode savedCopy = dataManager.save(copy);
            sysFinCurcyExchRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Derived SysFinCurcyExchRate " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });
        table.sort("id2", Table.SortDirection.ASCENDING);
        table.setSelected(sels);

        logger.trace(logPrfx + " <-- ");
    }

}