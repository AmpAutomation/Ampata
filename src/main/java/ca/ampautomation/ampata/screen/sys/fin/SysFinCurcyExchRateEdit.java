package ca.ampautomation.ampata.screen.sys.fin;

import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.sys.SysNode;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_SysFinCurcyExchRate.edit")
@UiDescriptor("sys-fin-curcy-exch-rate-edit.xml")
@EditedEntityContainer("sysFinCurcyExchRateDc")
public class SysFinCurcyExchRateEdit extends StandardEditor<SysNode> {

    @Autowired
    private InstanceContainer<SysNode> sysFinCurcyExchRateDc;

    @Autowired
    private Notifications notifications;

    Logger logger = LoggerFactory.getLogger(SysFinCurcyExchRateEdit.class);
    @Autowired
    private DataManager dataManager;

    @Subscribe("id2Field")
    public void onId2FieldValueChange(HasValue.ValueChangeEvent<String> event) {
        String logPrfx = "onId2FieldValueChange";
        logger.trace(logPrfx + " --> ");

        if (event.isUserOriginated()) {
            SysNode thisSysFinCurcyExchRate = sysFinCurcyExchRateDc.getItemOrNull();
            if (thisSysFinCurcyExchRate == null) {
                logger.debug(logPrfx + " --- thisSysFinCurcyExchRate is null, likely because no record is selected.");
                notifications.create().withCaption("No record selected. Please select a record.").show();
                logger.trace(logPrfx + " <-- ");
                return;
            }
            updateId2Cmp(thisSysFinCurcyExchRate);
            updateId2Dup(thisSysFinCurcyExchRate);
        }
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2FieldBtn")
    public void onUpdateId2FieldBtnClick(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2FieldBtnClick";
        logger.trace(logPrfx + " --> ");

        SysNode thisSysFinCurcyExchRate = sysFinCurcyExchRateDc.getItemOrNull();
        if (thisSysFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisSysFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisSysFinCurcyExchRate.setId2(thisSysFinCurcyExchRate.getId2Calc());
        updateId2Cmp(thisSysFinCurcyExchRate);
        updateId2Dup(thisSysFinCurcyExchRate);

        logger.debug(logPrfx + " --- id2: " + thisSysFinCurcyExchRate.getId2());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {

    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
        logger.trace(logPrfx + " --> ");

        SysNode thisSysFinCurcyExchRate = sysFinCurcyExchRateDc.getItemOrNull();
        if (thisSysFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisSysFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Cmp(thisSysFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2DupFieldBtn")
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
        logger.trace(logPrfx + " --> ");

        SysNode thisSysFinCurcyExchRate = sysFinCurcyExchRateDc.getItemOrNull();
        if (thisSysFinCurcyExchRate == null) {
            logger.debug(logPrfx + " --- thisSysFinCurcyExchRate is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        updateId2Dup(thisSysFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }



    private void updateCalcVals(@NotNull SysNode thisSysFinCurcyExchRate) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        String id2Calc = updateId2Calc(thisSysFinCurcyExchRate);
        //updateDesc1(thisSysFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    private String updateId2Calc(@NotNull SysNode thisSysFinCurcyExchRate) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisSysFinCurcyExchRate.getId2CalcFrFields();
        thisSysFinCurcyExchRate.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        updateId2Cmp(thisSysFinCurcyExchRate);
        updateId2Dup(thisSysFinCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private String updateId2Calc(@NotNull SysNode thisSysFinCurcyExchRate, String id2Calc) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        thisSysFinCurcyExchRate.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private void updateId2Cmp(@NotNull SysNode thisSysFinCurcyExchRate) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisSysFinCurcyExchRate.setId2Cmp(!thisSysFinCurcyExchRate.getId2().equals(thisSysFinCurcyExchRate.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisSysFinCurcyExchRate.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }

    private void updateId2Dup(@NotNull SysNode thisSysFinCurcyExchRate) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisSysFinCurcyExchRate.getId2() != null) {
            String id2Qry = "select count(e) from ampata_SysFinCurcyExchRate e where e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisSysFinCurcyExchRate.getId())
                        .parameter("id2", thisSysFinCurcyExchRate.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");

            thisSysFinCurcyExchRate.setId2Dup(id2Dup + 1);
            logger.debug(logPrfx + " --- thisFinTxactItm.setId2Dup(" + (id2Dup + 1) + ")");

        }
        logger.trace(logPrfx + " <-- ");
    }

}