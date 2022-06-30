package ca.ampautomation.ampata.screen.finrate;

import ca.ampautomation.ampata.entity.GenNode;
import ca.ampautomation.ampata.screen.gennode.FinStmtBrowse2;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinRate;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinRate.edit")
@UiDescriptor("fin-rate-edit.xml")
@EditedEntityContainer("finRateDc")
public class FinRateEdit extends StandardEditor<FinRate> {

    @Autowired
    private InstanceContainer<FinRate> finRateDc;

    @Autowired
    private Notifications notifications;

    Logger logger = LoggerFactory.getLogger(FinRateEdit.class);
    @Autowired
    private DataManager dataManager;

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
        thisFinRate.setId2(thisFinRate.getId2Calc());
        updateId2Cmp(thisFinRate);
        updateId2Dup(thisFinRate);

        logger.debug(logPrfx + " --- id2: " + thisFinRate.getId2());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("updateId2CalcFieldBtn")
    public void onUpdateId2CalcFieldBtnClick(Button.ClickEvent event) {

    }

    @Subscribe("updateId2CmpFieldBtn")
    public void onUpdateId2CmpFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2CmpFieldBtn";
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
    public void onUpdateId2DupFieldBtn(Button.ClickEvent event) {
        String logPrfx = "onUpdateId2DupFieldBtn";
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



    private void updateCalcVals(@NotNull FinRate thisFinRate) {
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        String id2Calc = updateId2Calc(thisFinRate);
        //updateDesc1(thisFinRate);

        logger.trace(logPrfx + " <-- ");
    }

    private String updateId2Calc(@NotNull FinRate thisFinRate) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        String id2Calc = thisFinRate.getId2CalcFrFields();
        thisFinRate.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        updateId2Cmp(thisFinRate);
        updateId2Dup(thisFinRate);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private String updateId2Calc(@NotNull FinRate thisFinRate, String id2Calc) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        thisFinRate.setId2Calc(id2Calc);
        logger.debug(logPrfx + " --- id2Calc: " + id2Calc);

        logger.trace(logPrfx + " <-- ");
        return id2Calc;
    }

    private void updateId2Cmp(@NotNull FinRate thisFinRate) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");

        thisFinRate.setId2Cmp(!thisFinRate.getId2().equals(thisFinRate.getId2Calc()));
        logger.debug(logPrfx + " --- id2Cmp: " + thisFinRate.getId2Cmp());

        logger.trace(logPrfx + " <-- ");
    }

    private void updateId2Dup(@NotNull FinRate thisFinRate) {
        // Assume thisFinTxfer is not null
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        if (thisFinRate.getId2() != null) {
            String id2Qry = "select count(e) from ampata_FinRate e where e.id2 = :id2 and e.id <> :id";
            Integer id2Dup;
            try {
                id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id", thisFinRate.getId())
                        .parameter("id2", thisFinRate.getId2())
                        .one();
            } catch (IllegalStateException e) {
                id2Dup = 0;

            }
            logger.debug(logPrfx + " --- id2Dup qry counted: " + id2Dup + " rows");

            thisFinRate.setId2Dup(id2Dup + 1);
            logger.debug(logPrfx + " --- thisFinTxfer.setId2Dup(" + (id2Dup + 1) + ")");

        }
        logger.trace(logPrfx + " <-- ");
    }

}