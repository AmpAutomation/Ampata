package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.sys.SysNodeRepository;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.sys.SysNode;
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

@UiController("ampata_SysFinCurcyExchRate.browse2")
@UiDescriptor("sys-fin-curcy-exch-rate-browse2.xml")
@LookupComponent("table")
public class SysFinCurcyExchRateBrowse2 extends MasterDetailScreen<SysNode> {


    //Common
    Logger logger = LoggerFactory.getLogger(SysFinCurcyExchRateBrowse.class);

    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SysNodeRepository repo;

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
    protected PropertyFilter<SysNode> filterConfig1A_FinCurcy1_Id;

    @Autowired
    protected PropertyFilter<SysNode> filterConfig1A_FinCurcy2_Id;

    @Autowired
    private CheckBox tmplt_Beg1Date1FieldChk;


    //Toolbar

    //Template
    @Autowired
    private DateField<LocalDate> tmplt_Beg1Date1Field;

    @Autowired
    protected EntityComboBox<SysNode> tmplt_FinCurcy1_IdField;
    @Autowired
    protected CheckBox tmplt_FinCurcy1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysNode> tmplt_FinCurcy2_IdField;
    @Autowired
    protected CheckBox tmplt_FinCurcy2_IdFieldChk;


    //Main data loaders and containers
    @Autowired
    private DataLoader finCurcyExchRatesDl;
    @Autowired
    private CollectionContainer<SysNode> finCurcyExchRatesDc;
    @Autowired
    private InstanceContainer<SysNode> finCurcyExchRateDc;

    
    //Main table
    @Autowired
    private Table<SysNode> table;


    //Other data loaders and containers
    private CollectionLoader<SysNode> finCurcysDl;
    private CollectionContainer<SysNode> finCurcysDc;

    //Field
    @Autowired
    private EntityComboBox<SysNode> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<SysNode> finCurcy2_IdField;



    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        finCurcysDc = dataComponents.createCollectionContainer(SysNode.class);
        finCurcysDl = dataComponents.createCollectionLoader();
        finCurcysDl.setQuery("select e from ampata_SysNode e where e.className = 'FinCurcy' order by e.id2");
        FetchPlan finCurcysFp = fetchPlans.builder(SysNode.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        finCurcysDl.setFetchPlan(finCurcysFp);
        finCurcysDl.setContainer(finCurcysDc);
        finCurcysDl.setDataContext(getScreenData().getDataContext());

        finCurcy1_IdField.setOptionsContainer(finCurcysDc);
        finCurcy2_IdField.setOptionsContainer(finCurcysDc);
        //template
        tmplt_FinCurcy1_IdField.setOptionsContainer(finCurcysDc);
        tmplt_FinCurcy2_IdField.setOptionsContainer(finCurcysDc);
        //filter
        EntityComboBox<SysNode> propFilterCmpnt_FinCurcy1_Id;
        propFilterCmpnt_FinCurcy1_Id = (EntityComboBox<SysNode>) filterConfig1A_FinCurcy1_Id.getValueComponent();
        propFilterCmpnt_FinCurcy1_Id.setOptionsContainer(finCurcysDc);

        logger.trace(logPrfx + " <-- ");
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


    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling finCurcysDl.load()");
        finCurcysDl.load();
        logger.debug(logPrfx + " --- finished finCurcysDl.load()");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing repo.execNodePrUpdNative()");
        repo.execNodePrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execNodePrUpdNative()");

        logger.debug(logPrfx + " --- executing repo.execFinCurcyExchRatePrUpdNative()");
        repo.execFinCurcyExchRatePrUpdNative();
        logger.debug(logPrfx + " --- finished repo.execFinCurcyExchRatePrUpdNative()");

        logger.debug(logPrfx + " --- executing finCurcyExchRatesDl.load()");
        finCurcyExchRatesDl.load();
        logger.debug(logPrfx + " --- finished finCurcyExchRatesDl.load()");


        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinCurcyExchRate = table.getSelected().stream().toList();
        if (thisFinCurcyExchRate == null || thisFinCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNode> sels = new ArrayList<>();

        thisFinCurcyExchRate.forEach(orig -> {

            SysNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDate beg1;
            if (tmplt_Beg1Date1FieldChk.isChecked()) {
                beg1 = tmplt_Beg1Date1Field.getValue();
                copy.getBeg1().setDate1(beg1);
            }

            copy.setId2Calc(copy.getId2CalcFrFields());
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            SysNode savedCopy = dataManager.save(copy);
            finCurcyExchRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        updateFinCurcyExchRateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinCurcyExchRate = table.getSelected().stream().toList();
        if (thisFinCurcyExchRate == null || thisFinCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNode> sels = new ArrayList<>();

        thisFinCurcyExchRate.forEach(orig -> {

            SysNode copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            LocalDate beg1;
            if (tmplt_Beg1Date1FieldChk.isChecked()) {
                beg1 = tmplt_Beg1Date1Field.getValue();
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
            finCurcyExchRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Derived " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        updateFinCurcyExchRateHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinCurcyExchRates = table.getSelected().stream().toList();
        if (thisFinCurcyExchRates == null || thisFinCurcyExchRates.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinCurcyExchRates.forEach(thisFinCurcyExchRate -> {
            thisFinCurcyExchRate = dataContext.merge(thisFinCurcyExchRate);
            if (thisFinCurcyExchRate != null) {

                Boolean thisFinCurcyExchRateIsChanged = false;

                LocalDate beg1;
                if (tmplt_Beg1Date1FieldChk.isChecked()) {
                    thisFinCurcyExchRateIsChanged = true;
                    beg1 = tmplt_Beg1Date1Field.getValue();
                    thisFinCurcyExchRate.getBeg1().setDate1(beg1);
                }

                if (tmplt_FinCurcy1_IdFieldChk.isChecked()) {
                    thisFinCurcyExchRateIsChanged = true;
                    thisFinCurcyExchRate.setFinCurcy1_Id(tmplt_FinCurcy1_IdField.getValue());
                }

                if (tmplt_FinCurcy2_IdFieldChk.isChecked()) {
                    thisFinCurcyExchRateIsChanged = true;
                    thisFinCurcyExchRate.setFinCurcy2_Id(tmplt_FinCurcy2_IdField.getValue());
                }

                thisFinCurcyExchRateIsChanged = updateId2Calc(thisFinCurcyExchRate) || thisFinCurcyExchRateIsChanged;
                thisFinCurcyExchRateIsChanged = updateId2(thisFinCurcyExchRate) || thisFinCurcyExchRateIsChanged;
                thisFinCurcyExchRateIsChanged = updateId2Cmp(thisFinCurcyExchRate) || thisFinCurcyExchRateIsChanged;

                if (thisFinCurcyExchRateIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinCurcyExchRate).");
                    //dataManager.save(thisFinCurcyExchRate);
                }

            }
        });
        updateFinCurcyExchRateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateFinCurcyExchRateHelper() {
        String logPrfx = "updateFinCurcyExchRateHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finCurcyExchRatesDl.load().");
            finCurcyExchRatesDl.load();

            List<SysNode> thisFinCurcyExchRates = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisFinCurcyExchRates.forEach(thisFinCurcyExchRate -> {
                //UsrNode thisTrackedFinCurcyExchRate = dataContext.merge(thisFinCurcyExchRate);
                if (thisFinCurcyExchRate != null) {
                    thisFinCurcyExchRate = dataContext.merge(thisFinCurcyExchRate);

                    Boolean thisFinCurcyExchRateIsChanged = false;

                    thisFinCurcyExchRateIsChanged = updateId2Dup(thisFinCurcyExchRate) || thisFinCurcyExchRateIsChanged;

                    if (thisFinCurcyExchRateIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisFinCurcyExchRate).");
                        //dataManager.save(thisFinCurcyExchRate);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing finCurcyExchRatesDl.load().");
                finCurcyExchRatesDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisFinCurcyExchRates);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNode> thisFinCurcyExchRates = table.getSelected().stream().toList();
        if (thisFinCurcyExchRates == null || thisFinCurcyExchRates.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRates is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinCurcyExchRates.forEach(thisFinCurcyExchRate -> {
            if (thisFinCurcyExchRate != null) {
                thisFinCurcyExchRate = dataContext.merge(thisFinCurcyExchRate);

                boolean isChanged = false;

                isChanged = updateCalcVals(thisFinCurcyExchRate);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing finCurcyExchRatesDl.load().");
            finCurcyExchRatesDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisFinCurcyExchRates);
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

        SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisFinCurcyExchRate);
            updateId2Dup(thisFinCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisFinCurcyExchRate);
        updateId2Cmp(thisFinCurcyExchRate);
        updateId2Dup(thisFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisFinCurcyExchRate);
        updateId2Cmp(thisFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("begDate1Field")
    public void onBegDate1FieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onBegDate1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinCurcyExchRate);
            updateId2Cmp(thisFinCurcyExchRate);
            updateId2Dup(thisFinCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("finCurcy1_IdField")
    public void onFinCurcy1_IdFieldValueChange(HasValue.ValueChangeEvent<UsrNode> event) {
        String logPrfx = "onFinCurcy1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinCurcyExchRate);
            updateId2Cmp(thisFinCurcyExchRate);
            updateId2Dup(thisFinCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling finCurcysDl.load()");
        finCurcysDl.load();
        logger.debug(logPrfx + " --- finished finCurcysDl.load()");


        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finCurcy2_IdField")
    public void onFinCurcy2_IdFieldValueChange(HasValue.ValueChangeEvent<UsrNode> event) {
        String logPrfx = "onFinCurcy2_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisFinCurcyExchRate);
            updateId2Cmp(thisFinCurcyExchRate);
            updateId2Dup(thisFinCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy2_IdFieldListBtn")
    public void onUpdateFinCurcy2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling finCurcysDl.load()");
        finCurcysDl.load();
        logger.debug(logPrfx + " --- finished finCurcysDl.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmt2FieldBtn")
    public void onUpdateAmt2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmt2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisFinCurcyExchRate = finCurcyExchRateDc.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateExtCurcyExchAmt2(thisFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateCalcVals(@NotNull SysNode thisFinCurcyExchRate) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateFinCurcyExchRateCalcVals(thisFinCurcyExchRate) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateFinCurcyExchRateCalcVals(@NotNull SysNode thisFinCurcyExchRate) {
        String logPrfx = "updateFinCurcyExchRateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in FinCurcyExchRate Object
        isChanged = updateId2Calc(thisFinCurcyExchRate) || isChanged;
        isChanged = updateId2Cmp(thisFinCurcyExchRate) || isChanged;
        isChanged = updateId2Dup(thisFinCurcyExchRate) || isChanged;
        isChanged = updateDesc1(thisFinCurcyExchRate) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull SysNode thisFinCurcyExchRate) {
        // Assume thisFinCurcyExchRate is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisFinCurcyExchRate.getId2();
        String id2 = thisFinCurcyExchRate.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisFinCurcyExchRate.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull SysNode thisFinCurcyExchRate) {
        // Assume thisFinCurcyExchRate is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisFinCurcyExchRate.getId2Calc();
        String id2Calc = thisFinCurcyExchRate.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisFinCurcyExchRate.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull SysNode thisFinCurcyExchRate) {
        // Assume thisFinCurcyExchRate is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisFinCurcyExchRate.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisFinCurcyExchRate.getId2(),thisFinCurcyExchRate.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisFinCurcyExchRate.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull SysNode thisFinCurcyExchRate) {
        // Assume thisFinCurcyExchRate is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisFinCurcyExchRate.getId2Dup();
        if (thisFinCurcyExchRate.getId2() != null) {
            String id2Qry = "select count(e) from ampata_FinCurcyExchRate e where e.id2 = :id2 and e.id <> :id";
            int id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinCurcyExchRate.getId())
                        .parameter("id2", thisFinCurcyExchRate.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisFinCurcyExchRate.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisFinCurcyExchRate.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull SysNode thisFinCurcyExchRate) {
        // Assume thisFinCurcyExchRate is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        boolean isChanged = false;
        String desc1_ = thisFinCurcyExchRate.getDesc1();
        String thisCurcyPair = "";
        if (thisFinCurcyExchRate.getFinCurcy1_Id2() != null) {
            thisCurcyPair = thisCurcyPair + thisFinCurcyExchRate.getFinCurcy1_Id2();
        }else{
            thisCurcyPair = thisCurcyPair + "???";
        }

        if (!thisCurcyPair.equals("")) {
            thisCurcyPair = thisCurcyPair + "->";
        }

        if (thisFinCurcyExchRate.getFinCurcy2_Id2() != null) {
            thisCurcyPair = thisCurcyPair + thisFinCurcyExchRate.getFinCurcy2_Id2();
        }else{
            thisCurcyPair = thisCurcyPair + "???";
        }

        if (!thisCurcyPair.equals("")) {
            thisCurcyPair = "Exchange " + thisCurcyPair + "";
        }
        logger.debug(logPrfx + " --- thisCurcyPair: " + thisCurcyPair);


        String thisDate = "";
        if (thisFinCurcyExchRate.getBeg1() != null && thisFinCurcyExchRate.getBeg1().getDate1() != null) {
            thisDate = thisFinCurcyExchRate.getBeg1().getDate1().format(frmtDt);
        }else {
            thisDate = "????-??-??";
        }
        if (!thisDate.equals("")) {
            thisDate = "on date " + thisDate + "";
        }
        logger.debug(logPrfx + " --- thisDate: " + thisDate);


        String thisRate = "";
        if (thisFinCurcyExchRate.getAmt1() != null) {
            thisRate = Objects.toString(thisFinCurcyExchRate.getAmt1().setScale(9, RoundingMode.HALF_UP), "");
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
            thisFinCurcyExchRate.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateExtCurcyExchAmt2(@NotNull SysNode thisFinCurcyExchRate) {
        String logPrfx = "updateExtCurcyExchAmt2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amt1 = thisFinCurcyExchRate.getAmt1();
        BigDecimal amt2_ = thisFinCurcyExchRate.getAmt2();
        BigDecimal amt2 = null;
        if (!amt1.equals(BigDecimal.ZERO)){
            amt2 = new BigDecimal(1 / amt1.doubleValue()).setScale(9, RoundingMode.HALF_UP);;
        }

        if (!Objects.equals(amt2_, amt2)){
            thisFinCurcyExchRate.setAmt2(amt2);
            logger.debug(logPrfx + " --- amt2: " + amt2.setScale(9, RoundingMode.HALF_UP));
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}