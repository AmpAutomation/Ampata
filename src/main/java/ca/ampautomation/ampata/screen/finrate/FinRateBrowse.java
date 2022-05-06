package ca.ampautomation.ampata.screen.finrate;

import ca.ampautomation.ampata.entity.GenNode;
import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
import ca.ampautomation.ampata.screen.gennode.FinTxferBrowse2;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.propertyfilter.SingleFilterSupport;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinRate;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("ampata_FinRate.browse")
@UiDescriptor("fin-rate-browse.xml")
@LookupComponent("finRatesTable")
public class FinRateBrowse extends StandardLookup<FinRate> {


    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<LocalDate> propFilter_beg1Date1GE;

    @Autowired
    protected PropertyFilter<LocalDate> propFilter_beg1Date1LE;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finCurcy1_Id;

    @Autowired
    protected PropertyFilter<GenNode> propFilter_finCurcy2_Id;

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
    private GroupTable<FinRate> finRatesTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<FinRate> finRatesDc;

    @Autowired
    private DataLoader finRatesDl;


    private CollectionContainer<GenNode> finCurcysDc;

    private CollectionLoader<GenNode> finCurcysDl;




    Logger logger = LoggerFactory.getLogger(FinRateBrowse.class);

    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");


        finCurcysDc = dataComponents.createCollectionContainer(GenNode.class);
        finCurcysDl = dataComponents.createCollectionLoader();
        finCurcysDl.setQuery("select e from ampata_GenNode e where e.className = 'FinCurcy' order by e.id2");
        FetchPlan finCurcysFp = fetchPlans.builder(GenNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finCurcysDl.setFetchPlan(finCurcysFp);
        finCurcysDl.setContainer(finCurcysDc);
        finCurcysDl.setDataContext(getScreenData().getDataContext());

        EntityComboBox<GenNode> propFilterCmpnt_finCurcy1_Id = (EntityComboBox<GenNode>) propFilter_finCurcy1_Id.getValueComponent();
        propFilterCmpnt_finCurcy1_Id.setOptionsContainer(finCurcysDc);

        EntityComboBox<GenNode> propFilterCmpnt_finCurcy2_Id = (EntityComboBox<GenNode>) propFilter_finCurcy2_Id.getValueComponent();
        propFilterCmpnt_finCurcy2_Id.setOptionsContainer(finCurcysDc);

    }
    
    @Install(to = "finRatesTable.[beg1.date1]", subject = "formatter")
    private String tableBegDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        finRatesTable.getSelected()
                .forEach(orig -> {

                    FinRate copy = metadataTools.copy(orig);
                    copy.setId(UuidProvider.createUuid());
                    copy.setId2Calc(copy.getId2CalcFrFields() + " Copy");
                    copy.setId2(copy.getId2Calc());

                    FinRate savedCopy = dataManager.save(copy);
                    finRatesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinRate " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            + " -> "
                            + "[" + copy.getId() + "]"
                    );
                });
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("reloadLists")
    public void onReloadListsClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsClick";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

}