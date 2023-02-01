package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.screen.usr.gen.UsrGenAgentBrowse;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.core.UuidProvider;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrNodeType.browse")
@UiDescriptor("usr-node-type-browse.xml")
@LookupComponent("usrNodeTypesTable")
public class UsrNodeTypeBrowse extends StandardLookup<UsrNodeType> {
    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private TreeTable<UsrNodeType> usrNodeTypesTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<UsrNodeType> usrNodeTypesDc;


    Logger logger = LoggerFactory.getLogger(UsrGenAgentBrowse.class);


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        usrNodeTypesTable.getSelected().stream()
                .forEach(orig -> {
                    UsrNodeType copy = makeCopy(orig);
                    UsrNodeType savedCopy = dataManager.save(copy);
                    usrNodeTypesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                            + " [" + orig.getId() + "]"
                            + " -> "
                            + " [" + copy.getId() + "]"
                    );


                });
    }


    private UsrNodeType makeCopy(UsrNodeType orig) {
        UsrNodeType copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}