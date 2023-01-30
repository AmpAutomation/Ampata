package ca.ampautomation.ampata.screen.sysnode;

import ca.ampautomation.ampata.entity.SysNode;
import ca.ampautomation.ampata.screen.sysnode.gen.GenAgentBrowse;
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

@UiController("ampata_SysNode.browse")
@UiDescriptor("sys-node-browse.xml")
@LookupComponent("sysNodesTable")
public class SysNodeBrowse extends StandardLookup<SysNode> {
    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<SysNode> sysNodesTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<SysNode> sysNodesDc;

    Logger logger = LoggerFactory.getLogger(GenAgentBrowse.class);

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        sysNodesTable.getSelected().stream()
                .forEach(orig -> {
                    SysNode copy = makeCopy(orig);
                    SysNode savedCopy = dataManager.save(copy);
                    sysNodesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated GenAgent " + copy.getId2()
                            + " [" + orig.getId() + "]"
                            + " -> "
                            + "[" + copy.getId() + "]"
                    );


                });
    }


    private SysNode makeCopy(SysNode orig) {
        SysNode copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}