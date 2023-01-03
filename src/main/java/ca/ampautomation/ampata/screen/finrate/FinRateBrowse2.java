package ca.ampautomation.ampata.screen.finrate;

import ca.ampautomation.ampata.entity.GenNode;
import ca.ampautomation.ampata.entity.GenNodeRepository;
import ca.ampautomation.ampata.entity.GenNodeType;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinRate;
import io.jmix.ui.screen.LookupComponent;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@UiController("ampata_FinRate.browse2")
@UiDescriptor("fin-rate-browse2.xml")
@LookupComponent("table")
public class FinRateBrowse2 extends MasterDetailScreen<FinRate> {

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
    private CheckBox tmplt_beg1Date1FieldChk;

    @Autowired
    private DateField<LocalDate> tmplt_beg1Date1Field;

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

    @Autowired
    private Table<FinRate> table;

    @Autowired
    private InstanceContainer<FinRate> finRateDc;


    @Autowired
    private CollectionContainer<FinRate> finRatesDc;

    @Autowired
    private DataLoader finRatesDl;


    private CollectionContainer<GenNode> finCurcysDc;

    private CollectionLoader<GenNode> finCurcysDl;

    @Autowired
    private EntityComboBox<GenNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<GenNode> finCurcy2_IdField;



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

        finCurcy1_IdField.setOptionsContainer(finCurcysDc);
        finCurcy2_IdField.setOptionsContainer(finCurcysDc);
        //filter
        EntityComboBox<GenNode> propFilterCmpnt_finCurcy1_Id = (EntityComboBox<GenNode>) propFilter_finCurcy1_Id.getValueComponent();
        propFilterCmpnt_finCurcy1_Id.setOptionsContainer(finCurcysDc);

        EntityComboBox<GenNode> propFilterCmpnt_finCurcy2_Id = (EntityComboBox<GenNode>) propFilter_finCurcy2_Id.getValueComponent();
        propFilterCmpnt_finCurcy2_Id.setOptionsContainer(finCurcysDc);

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

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<FinRate> thisFinRate = table.getSelected().stream().toList();
        if (thisFinRate == null || thisFinRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<FinRate> sels = new ArrayList<>();

        thisFinRate.forEach(orig -> {

            FinRate copy = metadataTools.copy(orig);
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

            FinRate savedCopy = dataManager.save(copy);
            finRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated FinRate " + copy.getId2() + " "
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

        List<FinRate> thisFinRate = table.getSelected().stream().toList();
        if (thisFinRate == null || thisFinRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<FinRate> sels = new ArrayList<>();

        thisFinRate.forEach(orig -> {

            FinRate copy = metadataTools.copy(orig);
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

            FinRate savedCopy = dataManager.save(copy);
            finRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Derived FinRate " + copy.getId2() + " "
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


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<FinRate> thisFinRates = table.getSelected().stream().toList();
        if (thisFinRates == null || thisFinRates.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinRates is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinRates.forEach(thisFinRate -> {
            if (thisFinRate != null) {
                thisFinRate = dataContext.merge(thisFinRate);

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinRate);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finRatesDl.load().");
            finRatesDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinRates);
            }
            catch(IllegalArgumentException e){
                logger.debug(logPrfx + " --- caught IllegalArgumentException: " + e.getMessage());
                notifications.create().withCaption("Unable to keep all previous selections.").show();
            }
        }

        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateDesc1FieldBtn")
    public void onUpdateDesc1FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateDesc1FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        FinRate thisFinRate = finRateDc.getItemOrNull();
        if (thisFinRate == null) {
            logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinRate);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            FinRate thisFinRate = finRateDc.getItemOrNull();
            if (thisFinRate == null) {
                logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinRate);
            updateId2Dup(thisFinRate);
        }
        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        FinRate thisFinRate = finRateDc.getItemOrNull();
        if (thisFinRate == null) {
            logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinRate);
        updateId2Cmp(thisFinRate);
        updateId2Dup(thisFinRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        FinRate thisFinRate = finRateDc.getItemOrNull();
        if (thisFinRate == null) {
            logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinRate);
        updateId2Cmp(thisFinRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        FinRate thisFinRate = finRateDc.getItemOrNull();
        if (thisFinRate == null) {
            logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinRate);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        FinRate thisFinRate = finRateDc.getItemOrNull();
        if (thisFinRate == null) {
            logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("begDate1Field")
    public void onBegDate1FieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onBegDate1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            FinRate thisFinRate = finRateDc.getItemOrNull();
            if (thisFinRate == null) {
                logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinRate);
            updateId2Cmp(thisFinRate);
            updateId2Dup(thisFinRate);
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("finCurcy1_IdField")
    public void onFinCurcy1_IdFieldValueChange(HasValue.ValueChangeEvent<GenNode> event) {
        String logPrfx = "onFinCurcy1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            FinRate thisFinRate = finRateDc.getItemOrNull();
            if (thisFinRate == null) {
                logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinRate);
            updateId2Cmp(thisFinRate);
            updateId2Dup(thisFinRate);
        }
        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finCurcy2_IdField")
    public void onFinCurcy2_IdFieldValueChange(HasValue.ValueChangeEvent<GenNode> event) {
        String logPrfx = "onFinCurcy2_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            FinRate thisFinRate = finRateDc.getItemOrNull();
            if (thisFinRate == null) {
                logger.debug(logPrfx + " --- thisFinRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinRate);
            updateId2Cmp(thisFinRate);
            updateId2Dup(thisFinRate);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy2_IdFieldListBtn")
    public void onUpdateFinCurcy2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        finCurcysDl.load();
        logger.debug(logPrfx + " --- called finCurcysDl.load() ");

        logger.trace(logPrfx + " <-- ");

    }



    private Boolean updateCalcVals(@NotNull FinRate thisFinRate) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinRateCalcVals(thisFinRate) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinRateCalcVals(@NotNull FinRate thisFinRate) {
        String logPrfx = "updateFinRateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinRate Object
        isChanged = updateId2Calc(thisFinRate) || isChanged;
        isChanged = updateId2Cmp(thisFinRate) || isChanged;
        isChanged = updateId2Dup(thisFinRate) || isChanged;
        isChanged = updateDesc1(thisFinRate) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull FinRate thisFinRate) {
        // Assume thisFinRate is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinRate.getId2();
        String id2 = thisFinRate.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinRate.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull FinRate thisFinRate) {
        // Assume thisFinRate is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinRate.getId2Calc();
        String id2Calc = thisFinRate.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinRate.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull FinRate thisFinRate) {
        // Assume thisFinRate is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinRate.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinRate.getId2(),thisFinRate.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinRate.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull FinRate thisFinRate) {
        // Assume thisFinRate is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinRate.getId2Dup();
        if (thisFinRate.getId2() != null) {
            String id2Qry = "select count(e) from ampata_FinRate e where e.id2 = :id2 and e.id <> :id";
            int id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinRate.getId())
                        .parameter("id2", thisFinRate.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinRate.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinRate.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull FinRate thisFinRate) {
        // Assume thisFinRate is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        boolean isChanged = false;
        String desc1_ = thisFinRate.getDesc1();
        String thisCurcyPair = "";
        if (thisFinRate.getFinCurcy1_Id() != null) {
            thisCurcyPair = thisCurcyPair + thisFinRate.getFinCurcy1_Id().getId2();
        }else{
            thisCurcyPair = thisCurcyPair + "???";
        }

        if (!thisCurcyPair.equals("")) {
            thisCurcyPair = thisCurcyPair + "->";
        }

        if (thisFinRate.getFinCurcy2_Id() != null) {
            thisCurcyPair = thisCurcyPair + thisFinRate.getFinCurcy2_Id().getId2();
        }else{
            thisCurcyPair = thisCurcyPair + "???";
        }

        if (!thisCurcyPair.equals("")) {
            thisCurcyPair = "Exchange " + thisCurcyPair + "";
        }
        logger.debug(logPrfx + " --- thisCurcyPair: " + thisCurcyPair);


        String thisDate = "";
        if (thisFinRate.getBeg1() != null && thisFinRate.getBeg1().getDate1() != null) {
            thisDate = thisFinRate.getBeg1().getDate1().format(frmtDt);
        }else {
            thisDate = "????-??-??";
        }
        if (!thisDate.equals("")) {
            thisDate = "on date " + thisDate + "";
        }
        logger.debug(logPrfx + " --- thisDate: " + thisDate);


        String thisRate = "";
        if (thisFinRate.getRate() != null) {
            thisRate = Objects.toString(thisFinRate.getRate().setScale(9, RoundingMode.HALF_UP), "");
        }
        if (!thisRate.equals("")) {
            thisRate = "at rate " + thisRate + "";
        }
        logger.debug(logPrfx + " --- thisRate: " + thisRate);


        List<String> list = Arrays.asList(
                thisCurcyPair
                , thisDate
                , thisRate
                );

        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            thisFinRate.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}