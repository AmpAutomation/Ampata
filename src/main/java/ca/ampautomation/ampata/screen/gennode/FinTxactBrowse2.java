package ca.ampautomation.ampata.screen.gennode;

import ca.ampautomation.ampata.components.textfieldwithbutton.TextFieldWithButton;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinTxact.browse2")
@UiDescriptor("fin-txact-browse2.xml")
@LookupComponent("table")
public class FinTxactBrowse2 extends MasterDetailScreen<GenNode> {


    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<GenNode> table;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> genNodesDc;

    @Autowired
    private InstanceContainer<GenNode> genNodeDc;

    @Autowired
    private TextField<String> classNameField;

    @Autowired
    private TextFieldWithButton id2FieldWithButton;

    @Autowired
    private TextFieldWithButton id2CalcFieldWithButton;

    Logger logger = LoggerFactory.getLogger(FinTxactBrowse2.class);

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        classNameField.setValue("FinTxact");
    }


    @Subscribe("id2FieldWithButton")
    public void onId2FieldWithButtonClick(Button.ClickEvent event) {
        String logPrfx = "onId2FieldWithButtonClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = genNodeDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setId2(thisFinTxact.getId2Calc());

        logger.debug(logPrfx + " --- id2: " + thisFinTxact.getId2());
        logger.trace(logPrfx + " <-- ");
    }

    @Subscribe("id2CalcFieldWithButton")
    public void onId2CalcFieldWithButtonClick(Button.ClickEvent event) {
        String logPrfx = "onId2CalcFieldWithButtonClick";
        logger.trace(logPrfx + " --> ");

        GenNode thisFinTxact = genNodeDc.getItemOrNull();
        if (thisFinTxact == null) {
            logger.debug(logPrfx + " --- genNodeDc is null, likely because no record is selected.");
            notifications.create().withCaption("No record selected. Please select a record.").show();
            logger.trace(logPrfx + " <-- ");
            return;
        }
        thisFinTxact.setId2Calc(thisFinTxact.getId2CalcFrFields());

        logger.debug(logPrfx + " --- id2Calc: " + thisFinTxact.getId2Calc());
        logger.trace(logPrfx + " <-- ");
    }

}