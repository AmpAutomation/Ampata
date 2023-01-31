package ca.ampautomation.ampata.screen.ext;

import ca.ampautomation.ampata.entity.SysNode;
import ca.ampautomation.ampata.entity.ExtCurcyExchRateRepository;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.options.ListOptions;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.ExtCurcyExchRate;
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

@UiController("ampata_ExtCurcyExchRate.browse2")
@UiDescriptor("ext-curcy-exch-rate-browse2.xml")
@LookupComponent("table")
public class ExtCurcyExchRateBrowse2 extends MasterDetailScreen<ExtCurcyExchRate> {

    @Autowired
    protected UiComponents uiComponents;

    //filter
    @Autowired
    protected Filter filter;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_beg1Date1GE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_beg1Date1LE;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_finCurcy1_Id2;

    @Autowired
    protected PropertyFilter<String> filterConfig1A_finCurcy2_Id2;

    @Autowired
    private CheckBox tmplt_Beg1Date1FieldChk;

    //tmplt
    @Autowired
    private DateField<LocalDate> tmplt_Beg1Date1Field;

    @Autowired
    protected ComboBox<String> tmplt_FinCurcy1_Id2Field;

    @Autowired
    protected CheckBox tmplt_FinCurcy1_Id2FieldChk;

    @Autowired
    protected ComboBox<String> tmplt_FinCurcy2_Id2Field;

    @Autowired
    protected CheckBox tmplt_FinCurcy2_Id2FieldChk;


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ExtCurcyExchRateRepository repo;

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
    private Table<ExtCurcyExchRate> table;

    @Autowired
    private InstanceContainer<ExtCurcyExchRate> extCurcyExchRateDc;


    @Autowired
    private CollectionContainer<ExtCurcyExchRate> extCurcyExchRatesDc;

    @Autowired
    private DataLoader extCurcyExchRatesDl;


    @Autowired
    private ComboBox<String> finCurcy1_Id2Field;

    @Autowired
    private ComboBox<String> finCurcy2_Id2Field;



    Logger logger = LoggerFactory.getLogger(ExtCurcyExchRateBrowse.class);

    /*
InitEvent is sent when the screen controller and all its declaratively defined components are created,
and dependency injection is completed. Nested fragments are not initialized yet. Some visual components
are not fully initialized, for example, buttons are not linked with actions.
 */
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");
        
        ComboBox<String> propFilterCmpnt_finCurcy1_Id2;
        propFilterCmpnt_finCurcy1_Id2 = (ComboBox<String>) filterConfig1A_finCurcy1_Id2.getValueComponent();
        propFilterCmpnt_finCurcy1_Id2.setNullOptionVisible(true);
        propFilterCmpnt_finCurcy1_Id2.setNullSelectionCaption("<null>");

        ComboBox<String> propFilterCmpnt_finCurcy2_Id2;
        propFilterCmpnt_finCurcy2_Id2 = (ComboBox<String>) filterConfig1A_finCurcy2_Id2.getValueComponent();
        propFilterCmpnt_finCurcy2_Id2.setNullOptionVisible(true);
        propFilterCmpnt_finCurcy2_Id2.setNullSelectionCaption("<null>");


        tmplt_FinCurcy1_Id2Field.setNullOptionVisible(true);
        tmplt_FinCurcy1_Id2Field.setNullSelectionCaption("<null>");

        tmplt_FinCurcy2_Id2Field.setNullOptionVisible(true);
        tmplt_FinCurcy2_Id2Field.setNullSelectionCaption("<null>");

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

        reloadFinCurcy1List();
        reloadFinCurcy2List();

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("updateColCalcValsBtn")
    public void onUpdateColCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- executing Db-Proc.Ext_Curcy_Exch_Rate_Pr_Upd()");
        repo.execExtCurcyExchRatePrUpdNative();
        logger.debug(logPrfx + " --- finished Db-Proc.Ext_Curcy_Exch_Rate_Pr_Upd()");

        logger.debug(logPrfx + " --- loading extCurcyExchRatesDl.load()");
        extCurcyExchRatesDl.load();
        logger.debug(logPrfx + " --- finished extCurcyExchRatesDl.load()");


        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDuplicateBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ExtCurcyExchRate> thisExtCurcyExchRate = table.getSelected().stream().toList();
        if (thisExtCurcyExchRate == null || thisExtCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<ExtCurcyExchRate> sels = new ArrayList<>();

        thisExtCurcyExchRate.forEach(orig -> {

            ExtCurcyExchRate copy = metadataTools.copy(orig);
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

            ExtCurcyExchRate savedCopy = dataManager.save(copy);
            extCurcyExchRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Duplicated ExtCurcyExchRate " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        updateExtCurcyExchRateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ExtCurcyExchRate> thisExtCurcyExchRate = table.getSelected().stream().toList();
        if (thisExtCurcyExchRate == null || thisExtCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<ExtCurcyExchRate> sels = new ArrayList<>();

        thisExtCurcyExchRate.forEach(orig -> {

            ExtCurcyExchRate copy = metadataTools.copy(orig);
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

            ExtCurcyExchRate savedCopy = dataManager.save(copy);
            extCurcyExchRatesDc.getMutableItems().add(savedCopy);
            logger.debug("Derived ExtCurcyExchRate " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        updateExtCurcyExchRateHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ExtCurcyExchRate> thisExtCurcyExchRates = table.getSelected().stream().toList();
        if (thisExtCurcyExchRates == null || thisExtCurcyExchRates.isEmpty()) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisExtCurcyExchRates.forEach(thisExtCurcyExchRate -> {
            ExtCurcyExchRate thisTrackedExtCurcyExchRate = dataContext.merge(thisExtCurcyExchRate);
            thisExtCurcyExchRate = thisTrackedExtCurcyExchRate;
            if (thisExtCurcyExchRate != null) {

                Boolean thisExtCurcyExchRateIsChanged = false;

                LocalDate beg1;
                if (tmplt_Beg1Date1FieldChk.isChecked()) {
                    thisExtCurcyExchRateIsChanged = true;
                    beg1 = tmplt_Beg1Date1Field.getValue();
                    thisExtCurcyExchRate.getBeg1().setDate1(beg1);
                }

                if (tmplt_FinCurcy1_Id2FieldChk.isChecked()) {
                    thisExtCurcyExchRateIsChanged = true;
                    thisExtCurcyExchRate.setFinCurcy1_Id2(tmplt_FinCurcy1_Id2Field.getValue());
                }

                if (tmplt_FinCurcy2_Id2FieldChk.isChecked()) {
                    thisExtCurcyExchRateIsChanged = true;
                    thisExtCurcyExchRate.setFinCurcy2_Id2(tmplt_FinCurcy2_Id2Field.getValue());
                }

                thisExtCurcyExchRateIsChanged = updateId2Calc(thisExtCurcyExchRate) || thisExtCurcyExchRateIsChanged;
                thisExtCurcyExchRateIsChanged = updateId2(thisExtCurcyExchRate) || thisExtCurcyExchRateIsChanged;
                thisExtCurcyExchRateIsChanged = updateId2Cmp(thisExtCurcyExchRate) || thisExtCurcyExchRateIsChanged;

                if (thisExtCurcyExchRateIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisExtCurcyExchRate).");
                    //dataManager.save(thisExtCurcyExchRate);
                }

            }
        });
        updateExtCurcyExchRateHelper();
        logger.trace(logPrfx + " <-- ");
    }

    private void updateExtCurcyExchRateHelper() {
        String logPrfx = "updateExtCurcyExchRateHelper";
        logger.trace(logPrfx + " --> ");

        if(dataContext.hasChanges()) {

            //call dataContext.commit to sync the UI with the changes to the database
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing extCurcyExchRatesDl.load().");
            extCurcyExchRatesDl.load();

            List<ExtCurcyExchRate> thisExtCurcyExchRates = table.getSelected().stream().toList();

            //Loop throught the items again to update the id2Dup attribute
            thisExtCurcyExchRates.forEach(thisExtCurcyExchRate -> {
                //SysNode thisTrackedExtCurcyExchRate = dataContext.merge(thisExtCurcyExchRate);
                if (thisExtCurcyExchRate != null) {
                    thisExtCurcyExchRate = dataContext.merge(thisExtCurcyExchRate);

                    Boolean thisExtCurcyExchRateIsChanged = false;

                    thisExtCurcyExchRateIsChanged = updateId2Dup(thisExtCurcyExchRate) || thisExtCurcyExchRateIsChanged;

                    if (thisExtCurcyExchRateIsChanged) {
                        logger.debug(logPrfx + " --- executing dataManager.save(thisExtCurcyExchRate).");
                        //dataManager.save(thisExtCurcyExchRate);
                    }
                }
            });

            if (dataContext.hasChanges()) {
                logger.debug(logPrfx + " --- executing dataContext.commit().");
                dataContext.commit();

                logger.debug(logPrfx + " --- executing extCurcyExchRatesDl.load().");
                extCurcyExchRatesDl.load();

                table.sort("id2", Table.SortDirection.ASCENDING);
                table.setSelected(thisExtCurcyExchRates);
            }
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateColItemCalcValsBtn")
    public void onUpdateColItemCalcValsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateColItemCalcValsBtnClick";
        logger.trace(logPrfx + " --> ");

        List<ExtCurcyExchRate> thisExtCurcyExchRates = table.getSelected().stream().toList();
        if (thisExtCurcyExchRates == null || thisExtCurcyExchRates.isEmpty()) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRates is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisExtCurcyExchRates.forEach(thisExtCurcyExchRate -> {
            if (thisExtCurcyExchRate != null) {
                thisExtCurcyExchRate = dataContext.merge(thisExtCurcyExchRate);

                boolean isChanged = false;

                isChanged = updateCalcVals(thisExtCurcyExchRate);

            }
        });

        if (dataContext.hasChanges()){
            logger.debug(logPrfx + " --- executing dataContext.commit().");
            dataContext.commit();

            logger.debug(logPrfx + " --- executing extCurcyExchRatesDl.load().");
            extCurcyExchRatesDl.load();

            table.sort("id2", Table.SortDirection.ASCENDING);
            try{table.setSelected(thisExtCurcyExchRates);
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

        ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
        if (thisExtCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateDesc1(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
            if (thisExtCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisExtCurcyExchRate);
            updateId2Dup(thisExtCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");
    }
    
    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
        if (thisExtCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2(thisExtCurcyExchRate);
        updateId2Cmp(thisExtCurcyExchRate);
        updateId2Dup(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CalcFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
        if (thisExtCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Calc(thisExtCurcyExchRate);
        updateId2Cmp(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
        if (thisExtCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
        if (thisExtCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("begDate1Field")
    public void onBegDate1FieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        String logPrfx = "onBegDate1FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
            if (thisExtCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisExtCurcyExchRate);
            updateId2Cmp(thisExtCurcyExchRate);
            updateId2Dup(thisExtCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");

    }

    @Subscribe("finCurcy1_Id2Field")
    public void onFinCurcy1_Id2FieldValueChange(HasValue.ValueChangeEvent<SysNode> event) {
        String logPrfx = "onFinCurcy1_Id2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
            if (thisExtCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisExtCurcyExchRate);
            updateId2Cmp(thisExtCurcyExchRate);
            updateId2Dup(thisExtCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Install(to = "finCurcy1_Id2Field", subject = "enterPressHandler")
    private void finCurcy1_Id2FieldEnterPressHandler(HasEnterPressHandler.EnterPressEvent enterPressEvent) {
        String logPrfx = "finCurcy1_Id2FieldEnterPressHandler";
        logger.trace(logPrfx + " --> ");

        addEnteredTextToComboBoxOptionsList(enterPressEvent);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy1_Id2FieldListBtn")
    public void onUpdateFinCurcy1_Id2FieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_Id2FieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        reloadFinCurcy1List();

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finCurcy2_Id2Field")
    public void onFinCurcy2_Id2FieldValueChange(HasValue.ValueChangeEvent<SysNode> event) {
        String logPrfx = "onFinCurcy2_Id2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
            if (thisExtCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Calc(thisExtCurcyExchRate);
            updateId2Cmp(thisExtCurcyExchRate);
            updateId2Dup(thisExtCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("updateAmt2FieldBtn")
    public void onUpdateAmt2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmt2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        ExtCurcyExchRate thisExtCurcyExchRate = extCurcyExchRateDc.getItemOrNull();
        if (thisExtCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisExtCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateExtCurcyExchAmt2(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");

    }

    private Boolean updateCalcVals(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = updateExtCurcyExchRateCalcVals(thisExtCurcyExchRate) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateExtCurcyExchRateCalcVals(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        String logPrfx = "updateExtCurcyExchRateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // Stored in ExtCurcyExchRate Object
        isChanged = updateId2Calc(thisExtCurcyExchRate) || isChanged;
        isChanged = updateId2Cmp(thisExtCurcyExchRate) || isChanged;
        isChanged = updateId2Dup(thisExtCurcyExchRate) || isChanged;
        isChanged = updateDesc1(thisExtCurcyExchRate) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisExtCurcyExchRate is not null
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2_ = thisExtCurcyExchRate.getId2();
        String id2 = thisExtCurcyExchRate.getId2Calc();
        if(!Objects.equals(id2_, id2)){
            thisExtCurcyExchRate.setId2(id2);
            logger.debug(logPrfx + " --- id2: " + id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Calc(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisExtCurcyExchRate is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String id2Calc_ = thisExtCurcyExchRate.getId2Calc();
        String id2Calc = thisExtCurcyExchRate.getId2CalcFrFields();
        if(!Objects.equals(id2Calc_, id2Calc)){
            thisExtCurcyExchRate.setId2Calc(id2Calc);
            logger.debug(logPrfx + " --- id2Calc: " + id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Cmp(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisExtCurcyExchRate is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Boolean id2Cmp_ = thisExtCurcyExchRate.getId2Cmp();
        Boolean id2Cmp = !Objects.equals(thisExtCurcyExchRate.getId2(),thisExtCurcyExchRate.getId2Calc());
        if (!Objects.equals(id2Cmp_, id2Cmp)){
            thisExtCurcyExchRate.setId2Cmp(id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateId2Dup(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisExtCurcyExchRate is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer id2Dup_ = thisExtCurcyExchRate.getId2Dup();
        if (thisExtCurcyExchRate.getId2() != null) {
            String id2Qry = "select count(e) from ampata_ExtCurcyExchRate e where e.id2 = :id2 and e.id <> :id";
            int id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisExtCurcyExchRate.getId())
                        .parameter("id2", thisExtCurcyExchRate.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            id2Dup = id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");
            if (!Objects.equals(id2Dup_, id2Dup)){
                thisExtCurcyExchRate.setId2Dup(id2Dup);
                logger.debug(logPrfx + " --- thisExtCurcyExchRate.setId2Dup(" + (id2Dup) + ")");
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    private Boolean updateDesc1(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisExtCurcyExchRate is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        boolean isChanged = false;
        String desc1_ = thisExtCurcyExchRate.getDesc1();
        String thisCurcyPair = "";
        if (thisExtCurcyExchRate.getFinCurcy1_Id2() != null) {
            thisCurcyPair = thisCurcyPair + thisExtCurcyExchRate.getFinCurcy1_Id2();
        }else{
            thisCurcyPair = thisCurcyPair + "???";
        }

        if (!thisCurcyPair.equals("")) {
            thisCurcyPair = thisCurcyPair + "->";
        }

        if (thisExtCurcyExchRate.getFinCurcy2_Id2() != null) {
            thisCurcyPair = thisCurcyPair + thisExtCurcyExchRate.getFinCurcy2_Id2();
        }else{
            thisCurcyPair = thisCurcyPair + "???";
        }

        if (!thisCurcyPair.equals("")) {
            thisCurcyPair = "Exchange " + thisCurcyPair + "";
        }
        logger.debug(logPrfx + " --- thisCurcyPair: " + thisCurcyPair);


        String thisDate = "";
        if (thisExtCurcyExchRate.getBeg1() != null && thisExtCurcyExchRate.getBeg1().getDate1() != null) {
            thisDate = thisExtCurcyExchRate.getBeg1().getDate1().format(frmtDt);
        }else {
            thisDate = "????-??-??";
        }
        if (!thisDate.equals("")) {
            thisDate = "on date " + thisDate + "";
        }
        logger.debug(logPrfx + " --- thisDate: " + thisDate);


        String thisRate = "";
        if (thisExtCurcyExchRate.getAmt1() != null) {
            thisRate = Objects.toString(thisExtCurcyExchRate.getAmt1().setScale(9, RoundingMode.HALF_UP), "");
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
            thisExtCurcyExchRate.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private Boolean updateExtCurcyExchAmt2(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        String logPrfx = "updateExtCurcyExchAmt2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        BigDecimal amt1 = thisExtCurcyExchRate.getAmt1();
        BigDecimal amt2_ = thisExtCurcyExchRate.getAmt2();
        BigDecimal amt2 = null;
        if (!amt1.equals(BigDecimal.ZERO)){
            amt2 = new BigDecimal(1 / amt1.doubleValue()).setScale(9, RoundingMode.HALF_UP);;
        }

        if (!Objects.equals(amt2_, amt2)){
            thisExtCurcyExchRate.setAmt2(amt2);
            logger.debug(logPrfx + " --- amt2: " + amt2.setScale(9, RoundingMode.HALF_UP));
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    private void reloadFinCurcy1List(){
        String logPrfx = "reloadFinCurcy1List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finCurcy1_Id2"
                + " from ampata_ExtCurcyExchRate e"
                + " and e.finCurcy1_Id2 IS NOT NULL"
                + " order by e.finCurcy1_Id2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> items = null;
        try {
            items = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + items.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        ComboBox<String> propFilterCmpnt_finCurcy1_Id2 = (ComboBox<String>) filterConfig1A_finCurcy1_Id2.getValueComponent();
        propFilterCmpnt_finCurcy1_Id2.setOptionsList(items);
        logger.debug(logPrfx + " --- called propFilterCmpnt_finCurcy1_Id2.setOptionsList()");

        tmplt_FinCurcy1_Id2Field.setOptionsList(items);
        logger.debug(logPrfx + " --- called tmplt_FinCurcy1_Id2Field.setOptionsList()");

        finCurcy1_Id2Field.setOptionsList(items);
        logger.debug(logPrfx + " --- called finCurcy1_Id2Field.setOptionsList()");

        logger.trace(logPrfx + " <-- ");
    }


    private void reloadFinCurcy2List(){
        String logPrfx = "reloadFinCurcy2List";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.finCurcy2_Id2"
                + " from ampata_ExtCurcyExchRate e"
                + " and e.finCurcy2_Id2 IS NOT NULL"
                + " order by e.finCurcy2_Id2"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> items = null;
        try {
            items = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + items.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return;
        }

        ComboBox<String> propFilterCmpnt_finCurcy2_Id2 = (ComboBox<String>) filterConfig1A_finCurcy2_Id2.getValueComponent();
        propFilterCmpnt_finCurcy2_Id2.setOptionsList(items);
        logger.debug(logPrfx + " --- called propFilterCmpnt_finCurcy2_Id2.setOptionsList()");

        tmplt_FinCurcy2_Id2Field.setOptionsList(items);
        logger.debug(logPrfx + " --- called tmplt_FinCurcy2_Id2Field.setOptionsList()");

        finCurcy2_Id2Field.setOptionsList(items);
        logger.debug(logPrfx + " --- called finCurcy2_Id2Field.setOptionsList()");

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

}