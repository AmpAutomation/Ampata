package ca.ampautomation.ampata.screen.node;

import ca.ampautomation.ampata.screen.node.gen.GenAgentBrowse;
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
import ca.ampautomation.ampata.entity.GenNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_GenNode.browse")
@UiDescriptor("gen-node-browse.xml")
@LookupComponent("genNodesTable")
public class GenNodeBrowse extends StandardLookup<GenNode> {
    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<GenNode> genNodesTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNode> genNodesDc;

    Logger logger = LoggerFactory.getLogger(GenAgentBrowse.class);

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        genNodesTable.getSelected().stream()
                .forEach(orig -> {
                    GenNode copy = makeCopy(orig);
                    GenNode savedCopy = dataManager.save(copy);
                    genNodesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated GenAgent " + copy.getId2()
                            + " [" + orig.getId() + "]"
                            + " -> "
                            + "[" + copy.getId() + "]"
                    );


                });
    }


    private GenNode makeCopy(GenNode orig) {
        GenNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}