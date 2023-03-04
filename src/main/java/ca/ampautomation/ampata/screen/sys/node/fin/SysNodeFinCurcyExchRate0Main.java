package ca.ampautomation.ampata.screen.sys.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRate;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRateQryMngr;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRateType;
import ca.ampautomation.ampata.screen.sys.base.SysNodeBase0BaseMain;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@UiController("enty_SysNodeFinCurcyExchRate.main")
@UiDescriptor("sys-node-fin-curcy-exch-rate-0-main.xml")
@LookupComponent("tableMain")
public class SysNodeFinCurcyExchRate0Main extends SysNodeBase0BaseMain<SysNodeFinCurcyExchRate, SysNodeFinCurcyExchRateType, SysNodeFinCurcyExchRateQryMngr> {


    //Filter

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_Ts1ElDtGE;

    @Autowired
    protected PropertyFilter<LocalDate> filterConfig1A_Ts1ElDtLE;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_FinCurcy1_Id;

    @Autowired
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_FinCurcy2_Id;

    @Autowired
    private CheckBox tmplt_Ts1ElTsFieldChk;


    //Toolbar

    //Template
    @Autowired
    private DateField<LocalDateTime> tmplt_Ts1ElTsField;

    @Autowired
    protected EntityComboBox<SysNodeFinCurcy> tmplt_FinCurcy1_IdField;
    @Autowired
    protected CheckBox tmplt_FinCurcy1_IdFieldChk;

    @Autowired
    protected EntityComboBox<SysNodeFinCurcy> tmplt_FinCurcy2_IdField;
    @Autowired
    protected CheckBox tmplt_FinCurcy2_IdFieldChk;



    //Other data containers and loaders
    protected CollectionContainer<SysNodeFinCurcy> colCntnrFinCurcy;
    protected CollectionLoader<SysNodeFinCurcy> colLoadrFinCurcy;


    //Field
    @Autowired
    private EntityComboBox<SysNodeFinCurcy> finCurcy1_IdField;

    @Autowired
    private EntityComboBox<SysNodeFinCurcy> finCurcy2_IdField;



    @Override
    @Subscribe
    public void onInit(InitEvent event) {
        String logPrfx = "onInit";
        logger.trace(logPrfx + " --> ");

        super.onInit(event);

        colCntnrFinCurcy = dataComponents.createCollectionContainer(SysNodeFinCurcy.class);
        colLoadrFinCurcy = dataComponents.createCollectionLoader();
        colLoadrFinCurcy.setQuery("select e from enty_SysNodeFinCurcy e order by e.sortKey, e.id2");
        FetchPlan fchPlnFinCurcy_Inst = fetchPlans.builder(SysNodeFinCurcy.class)
                .addFetchPlan(FetchPlan.INSTANCE_NAME)
                .build();
        colLoadrFinCurcy.setFetchPlan(fchPlnFinCurcy_Inst);
        colLoadrFinCurcy.setContainer(colCntnrFinCurcy);
        colLoadrFinCurcy.setDataContext(getScreenData().getDataContext());
        //Field
        finCurcy1_IdField.setOptionsContainer(colCntnrFinCurcy);
        finCurcy2_IdField.setOptionsContainer(colCntnrFinCurcy);
        //template
        tmplt_FinCurcy1_IdField.setOptionsContainer(colCntnrFinCurcy);
        tmplt_FinCurcy2_IdField.setOptionsContainer(colCntnrFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_FinCurcy1_Id;
        propFilterCmpnt_FinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_FinCurcy1_Id.getValueComponent();
        propFilterCmpnt_FinCurcy1_Id.setOptionsContainer(colCntnrFinCurcy);

        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_FinCurcy2_Id;
        propFilterCmpnt_FinCurcy2_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_FinCurcy2_Id.getValueComponent();
        propFilterCmpnt_FinCurcy2_Id.setOptionsContainer(colCntnrFinCurcy);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "tableMain.[ts1.elDt]", subject = "formatter")
    private String tableBegDate1Formatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(Button.ClickEvent event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        logger.debug(logPrfx + " --- calling colLoadrFinCurcy.load()");
        colLoadrFinCurcy.load();
        logger.debug(logPrfx + " --- finished colLoadrFinCurcy.load()");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(Button.ClickEvent event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNodeFinCurcyExchRate> thisFinCurcyExchRate = tableMain.getSelected().stream().toList();
        if (thisFinCurcyExchRate == null || thisFinCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create().withCaption("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNodeFinCurcyExchRate> sels = new ArrayList<>();

        thisFinCurcyExchRate.forEach(orig -> {

            SysNodeFinCurcyExchRate copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            HasTmst ts1 = dataManager.create(HasTmst.class);
            if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                ts1.setElTs(tmplt_Ts1ElTsField.getValue());
                copy.getTs1().setElTs(ts1.getElTs());
            }else{
                if (orig.getTs1().getElDt() != null) {
                    ts1.setElTs(orig.getTs1().getElTs().plusDays(1));
                    copy.getTs1().setElTs(ts1.getElTs());
                }
            }

            copy.updateCalcVals(dataManager);
            copy.setId2(copy.getId2Calc());
            if (orig.getId2().equals(copy.getId2())){
                copy.setId2(copy.getId2() + " Copy");
                copy.setId2Calc(copy.getId2());
            }

            SysNodeFinCurcyExchRate savedCopy = dataManager.save(copy);
            colCntnrMain.getMutableItems().add(savedCopy);
            logger.debug("Derived " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                    + "[" + orig.getId() + "]"
                    + " -> "
                    + "[" + copy.getId() + "]"
            );

            sels.add(savedCopy);

        });

        super.updateHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Subscribe("setBtn")
    public void onSetBtnClick(Button.ClickEvent event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNodeFinCurcyExchRate> thisFinCurcyExchRates = tableMain.getSelected().stream().toList();
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

                HasTmst ts1 = dataManager.create(HasTmst.class);
                if (tmplt_Ts1ElTsFieldChk.isChecked()) {
                    thisFinCurcyExchRateIsChanged = true;
                    ts1.setElTs(tmplt_Ts1ElTsField.getValue());
                    thisFinCurcyExchRate.getTs1().setElTs(ts1.getElTs());
                }

                if (tmplt_FinCurcy1_IdFieldChk.isChecked()) {
                    thisFinCurcyExchRateIsChanged = true;
                    thisFinCurcyExchRate.setFinCurcy1_Id(tmplt_FinCurcy1_IdField.getValue());
                }

                if (tmplt_FinCurcy2_IdFieldChk.isChecked()) {
                    thisFinCurcyExchRateIsChanged = true;
                    thisFinCurcyExchRate.setFinCurcy2_Id(tmplt_FinCurcy2_IdField.getValue());
                }

                thisFinCurcyExchRateIsChanged = thisFinCurcyExchRate.updateId2Calc(dataManager) || thisFinCurcyExchRateIsChanged;
                thisFinCurcyExchRateIsChanged = thisFinCurcyExchRate.updateId2(dataManager) || thisFinCurcyExchRateIsChanged;
                thisFinCurcyExchRateIsChanged = thisFinCurcyExchRate.updateId2Cmp(dataManager) || thisFinCurcyExchRateIsChanged;

                if (thisFinCurcyExchRateIsChanged) {
                    logger.debug(logPrfx + " --- executing dataManager.save(thisFinCurcyExchRate).");
                    //dataManager.save(thisFinCurcyExchRate);
                }

            }
        });
        super.updateHelper();
        logger.trace(logPrfx + " <-- ");
    }


    @Subscribe("finCurcy1_IdField")
    public void onFinCurcy1_IdFieldValueChange(HasValue.ValueChangeEvent<SysNodeFinCurcyExchRate> event) {
        String logPrfx = "onFinCurcy1_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNodeFinCurcyExchRate thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisFinCurcyExchRate.updateFinCurcy1_IdDeps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy1_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling colLoadrFinCurcy.load()");
        colLoadrFinCurcy.load();
        logger.debug(logPrfx + " --- finished colLoadrFinCurcy.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("finCurcy2_IdField")
    public void onFinCurcy2_IdFieldValueChange(HasValue.ValueChangeEvent<SysNodeFinCurcyExchRate> event) {
        String logPrfx = "onFinCurcy2_IdFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNodeFinCurcyExchRate thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisFinCurcyExchRate.updateFinCurcy2_IdDeps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy2_IdFieldListBtn")
    public void onUpdateFinCurcy2_IdFieldListBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateFinCurcy2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling colLoadrFinCurcy.load()");
        colLoadrFinCurcy.load();
        logger.debug(logPrfx + " --- finished colLoadrFinCurcy.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("ts1ElTsField")
    public void onTs1ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNodeFinCurcyExchRate thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisFinCurcyExchRate.updateTs1Deps(dataManager);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmt2FieldBtn")
    public void onUpdateAmt2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateAmt2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNodeBase thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinCurcyExchRate.updateAmt2(dataManager);

        logger.trace(logPrfx + " <-- ");

    }



}