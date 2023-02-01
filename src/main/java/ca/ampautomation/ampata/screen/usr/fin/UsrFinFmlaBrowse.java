package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.screen.usr.gen.UsrGenAgentBrowse;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.core.UuidProvider;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrFinFmla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrFinFmla.browse")
@UiDescriptor("usr-fin-fmla-browse.xml")
@LookupComponent("table")
public class UsrFinFmlaBrowse extends StandardLookup<UsrFinFmla> {
    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<UsrFinFmla> table;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<UsrFinFmla> finFmlasDc;

    Logger logger = LoggerFactory.getLogger(UsrGenAgentBrowse.class);

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        table.getSelected().stream()
                .forEach(orig -> {
                    UsrFinFmla copy = makeCopy(orig);
                    UsrFinFmla savedCopy = dataManager.save(copy);
                    finFmlasDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated " + copy.getClass().getName() + " " + copy.getId2() + " "
                            + " [" + orig.getId() + "]"
                            + " ->"
                            + " [" + copy.getId() + "]"
                    );


                });
    }


    private UsrFinFmla makeCopy(UsrFinFmla orig) {
        UsrFinFmla copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}