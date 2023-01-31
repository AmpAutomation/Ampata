package ca.ampautomation.ampata.screen.ext;

import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.ExtCurcyExchRate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_ExtCurcyExchRate.edit")
@UiDescriptor("ext-curcy-exch-rate-edit.xml")
@EditedEntityContainer("extCurcyExchRateDc")
public class ExtCurcyExchRateEdit extends StandardEditor<ExtCurcyExchRate> {

    @Autowired
    private InstanceContainer<ExtCurcyExchRate> extCurcyExchRateDc;

    @Autowired
    private Notifications notifications;

    Logger logger = LoggerFactory.getLogger(ExtCurcyExchRateEdit.class);
    @Autowired
    private DataManager dataManager;

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
        thisExtCurcyExchRate.setId2(thisExtCurcyExchRate.getId2Calc());
        updateId2Cmp(thisExtCurcyExchRate);
        updateId2Dup(thisExtCurcyExchRate);

        logger.debug(logPrfx + " --- id2: " + thisExtCurcyExchRate.getId2());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {

    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
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
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
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



    private void updateCalcVals(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        String id2Calc = updateId2Calc(thisExtCurcyExchRate);
        //updateDesc1(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
    }

    private String updateId2Calc(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisExtCurcyExchRate.getId2CalcFrFields();
        thisExtCurcyExchRate.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        updateId2Cmp(thisExtCurcyExchRate);
        updateId2Dup(thisExtCurcyExchRate);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private String updateId2Calc(@NotNull ExtCurcyExchRate thisExtCurcyExchRate, String id2Calc) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        thisExtCurcyExchRate.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private void updateId2Cmp(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisExtCurcyExchRate.setId2Cmp(!thisExtCurcyExchRate.getId2().equals(thisExtCurcyExchRate.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisExtCurcyExchRate.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }

    private void updateId2Dup(@NotNull ExtCurcyExchRate thisExtCurcyExchRate) {
        // Assume thisFinTxactItm is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisExtCurcyExchRate.getId2() != null) {
            String id2Qry = "select count(e) from ampata_ExtCurcyExchRate e where e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisExtCurcyExchRate.getId())
                        .parameter("id2", thisExtCurcyExchRate.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");

            thisExtCurcyExchRate.setId2Dup(id2Dup + 1);
            logger.debug(logPrfx + " --- thisFinTxactItm.setId2Dup(" + (id2Dup + 1) + ")");

        }
        logger.trace(logPrfx + " <-- ");
    }

}