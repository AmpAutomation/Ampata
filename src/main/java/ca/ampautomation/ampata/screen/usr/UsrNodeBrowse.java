package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.screen.usr.gen.GenAgentBrowse;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_UsrNode.browse")
@UiDescriptor("usr-node-browse.xml")
@LookupComponent("usrNodesTable")
public class UsrNodeBrowse extends StandardLookup<UsrNode> {
    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<UsrNode> usrNodesTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<UsrNode> usrNodesDc;

    Logger logger = LoggerFactory.getLogger(GenAgentBrowse.class);

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        usrNodesTable.getSelected().stream()
                .forEach(orig -> {
                    UsrNode copy = makeCopy(orig);
                    UsrNode savedCopy = dataManager.save(copy);
                    usrNodesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated GenAgent " + copy.getId2()
                            + " [" + orig.getId() + "]"
                            + " -> "
                            + "[" + copy.getId() + "]"
                    );


                });
    }


    private UsrNode makeCopy(UsrNode orig) {
        UsrNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}