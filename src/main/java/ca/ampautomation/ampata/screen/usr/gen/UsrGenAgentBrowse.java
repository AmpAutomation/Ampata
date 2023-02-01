package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
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

@UiController("ampata_UsrGenAgent.browse")
@UiDescriptor("usr-gen-agent-browse.xml")
@LookupComponent("table")
public class UsrGenAgentBrowse extends StandardLookup<UsrNode> {
    
    //Common

    Logger logger = LoggerFactory.getLogger(UsrGenAgentBrowse.class);
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
    private CollectionContainer<UsrNode> usrNodesDc;

    @Autowired
    private GroupTable<UsrNode> table;



    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        table.getSelected().stream()
            .forEach(orig -> {
                UsrNode copy = makeCopy(orig);
                UsrNode savedCopy = dataManager.save(copy);
                usrNodesDc.getMutableItems().add(savedCopy);
                logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                        + "[" + orig.getId() + "]"
                        +" -> "
                        +"[" + copy.getId() + "]"
                );


            });
    }


    private UsrNode makeCopy(UsrNode orig) {
        UsrNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}