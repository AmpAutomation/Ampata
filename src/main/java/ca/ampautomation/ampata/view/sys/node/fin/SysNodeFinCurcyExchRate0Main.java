package ca.ampautomation.ampata.view.sys.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRate;
import ca.ampautomation.ampata.repo.sys.node.fin.SysNodeFinCurcyExchRate0Repo;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRateType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.sys.node.base.SysNodeBase0BaseMain;
import ca.ampautomation.ampata.service.sys.node.fin.SysNodeFinCurcyExchRate0Service;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import io.jmix.core.*;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.checkbox.JmixCheckbox;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.datetimepicker.TypedDateTimePicker;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@Route(value = "sysNodeFinCurcyExchRates", layout = MainView.class)
@ViewController("enty_SysNodeFinCurcyExchRate.main")
@ViewDescriptor("sys-node-fin-curcy-exch-rate-0-main.xml")
@LookupComponent("dataGridMain")
public class SysNodeFinCurcyExchRate0Main extends SysNodeBase0BaseMain<SysNodeFinCurcyExchRate, SysNodeFinCurcyExchRateType, SysNodeFinCurcyExchRate0Service, SysNodeFinCurcyExchRate0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_SysNodeFinCurcyExchRate.Service")
    public void setService(SysNodeFinCurcyExchRate0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_SysNodeFinCurcyExchRate.Repo")
    public void setRepo(SysNodeFinCurcyExchRate0Repo repo) { this.repo = repo; }


    //Filter
    @ViewComponent
    protected PropertyFilter<LocalDate> filterConfig1A_Ts1ElDtGE;

    @ViewComponent
    protected PropertyFilter<LocalDate> filterConfig1A_Ts1ElDtLE;

    @ViewComponent
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_FinCurcy1_Id;

    @ViewComponent
    protected PropertyFilter<SysNodeFinCurcy> filterConfig1A_FinCurcy2_Id;

    @Autowired
    private JmixCheckbox tmplt_Ts1_ElTsFieldChk;


    //Toolbar

    //Template
    @Autowired
    private TypedDateTimePicker<LocalDateTime> tmplt_Ts1_ElTsField;

    @ViewComponent
    protected EntityComboBox<SysNodeFinCurcy> tmplt_FinCurcy1_IdField;
    @ViewComponent
    protected JmixCheckbox tmplt_FinCurcy1_IdFieldChk;

    @ViewComponent
    protected EntityComboBox<SysNodeFinCurcy> tmplt_FinCurcy2_IdField;
    @ViewComponent
    protected JmixCheckbox tmplt_FinCurcy2_IdFieldChk;



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
        colLoadrFinCurcy.setDataContext(getViewData().getDataContext());
        //Field
        finCurcy1_IdField.setItems(colCntnrFinCurcy);
        finCurcy2_IdField.setItems(colCntnrFinCurcy);
        //template
        tmplt_FinCurcy1_IdField.setItems(colCntnrFinCurcy);
        tmplt_FinCurcy2_IdField.setItems(colCntnrFinCurcy);
        //filter
        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_FinCurcy1_Id;
        propFilterCmpnt_FinCurcy1_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_FinCurcy1_Id.getValueComponent();
        propFilterCmpnt_FinCurcy1_Id.setItems(colCntnrFinCurcy);

        EntityComboBox<SysNodeFinCurcy> propFilterCmpnt_FinCurcy2_Id;
        propFilterCmpnt_FinCurcy2_Id = (EntityComboBox<SysNodeFinCurcy>) filterConfig1A_FinCurcy2_Id.getValueComponent();
        propFilterCmpnt_FinCurcy2_Id.setItems(colCntnrFinCurcy);

        logger.trace(logPrfx + " <-- ");
    }

    @Install(to = "dataGridMain.[ts1.elTs]", subject = "formatter")
    private String dataGridMainTs1_ElTsFormatter(LocalDate date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return date == null ? null: date.format(formatter);
    }

    @Override
    @Subscribe("reloadListsBtn")
    public void onReloadListsBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onReloadListsBtnClick";
        logger.trace(logPrfx + " --> ");

        super.onReloadListsBtnClick(event);

        logger.debug(logPrfx + " --- calling colLoadrFinCurcy.load()");
        colLoadrFinCurcy.load();
        logger.debug(logPrfx + " --- finished colLoadrFinCurcy.load()");

        logger.trace(logPrfx + " <-- ");

    }


    @Subscribe("deriveBtn")
    public void onDeriveBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onDeriveBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNodeFinCurcyExchRate> thisFinCurcyExchRate = dataGridMain.getSelectedItems().stream().toList();
        if (thisFinCurcyExchRate == null || thisFinCurcyExchRate.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        List<SysNodeFinCurcyExchRate> sels = new ArrayList<>();

        thisFinCurcyExchRate.forEach(orig -> {

            SysNodeFinCurcyExchRate copy = metadataTools.copy(orig);
            copy.setId(UuidProvider.createUuid());

            HasTmst ts1 = dataManager.create(HasTmst.class);
            if (tmplt_Ts1_ElTsFieldChk.getValue()) {
                ts1.setElTs(tmplt_Ts1_ElTsField.getValue());
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
    public void onSetBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onSetBtnClick";
        logger.trace(logPrfx + " --> ");

        List<SysNodeFinCurcyExchRate> thisFinCurcyExchRates = dataGridMain.getSelectedItems().stream().toList();
        if (thisFinCurcyExchRates == null || thisFinCurcyExchRates.isEmpty()) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no records are selected.");
            notifications.create("No records selected. Please select one or more record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }

        thisFinCurcyExchRates.forEach(thisFinCurcyExchRate -> {
            thisFinCurcyExchRate = dataContext.merge(thisFinCurcyExchRate);
            if (thisFinCurcyExchRate != null) {

                Boolean thisFinCurcyExchRateIsChanged = false;

                HasTmst ts1 = dataManager.create(HasTmst.class);
                if (tmplt_Ts1_ElTsFieldChk.getValue()) {
                    thisFinCurcyExchRateIsChanged = true;
                    ts1.setElTs(tmplt_Ts1_ElTsField.getValue());
                    thisFinCurcyExchRate.getTs1().setElTs(ts1.getElTs());
                }

                if (tmplt_FinCurcy1_IdFieldChk.getValue()) {
                    thisFinCurcyExchRateIsChanged = true;
                    thisFinCurcyExchRate.setFinCurcy1_Id(tmplt_FinCurcy1_IdField.getValue());
                }

                if (tmplt_FinCurcy2_IdFieldChk.getValue()) {
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

        if (event.isFromClient()) {
            SysNodeFinCurcyExchRate thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisFinCurcyExchRate.updateFinCurcy1_IdDeps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy1_IdFieldListBtn")
    public void onUpdateFinCurcy1_IdFieldListBtnClick(ClickEvent<Button> event) {
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

        if (event.isFromClient()) {
            SysNodeFinCurcyExchRate thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisFinCurcyExchRate.updateFinCurcy2_IdDeps(dataManager);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateFinCurcy2_IdFieldListBtn")
    public void onUpdateFinCurcy2_IdFieldListBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateFinCurcy2_IdFieldListBtnClick";
        logger.trace(logPrfx + " --> ");

        logger.debug(logPrfx + " --- calling colLoadrFinCurcy.load()");
        colLoadrFinCurcy.load();
        logger.debug(logPrfx + " --- finished colLoadrFinCurcy.load()");

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("ts1_ElTsField")
    public void onTs1_ElTsFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        String logPrfx = "onTs1_ElTsFieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isFromClient()) {
            SysNodeFinCurcyExchRate thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
            if (thisFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            thisFinCurcyExchRate.updateTs1Deps(dataManager);
        }

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateAmt2FieldBtn")
    public void onUpdateAmt2FieldBtnClick(ClickEvent<Button> event) {
        String logPrfx = "onUpdateAmt2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNodeBase thisFinCurcyExchRate = instCntnrMain.getItemOrNull();
        if (thisFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinCurcyExchRate.updateAmt2(dataManager);

        logger.trace(logPrfx + " <-- ");

    }



}