package ca.ampautomation.ampata.screen.sysnodetype.fin;

import ca.ampautomation.ampata.entity.SysNodeType;
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

@UiController("ampata_FinAcctType.browse")
@UiDescriptor("fin-acct-type-browse.xml")
@LookupComponent("sysNodeTypesTable")
public class FinAcctTypeBrowse extends StandardLookup<SysNodeType> {

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<SysNodeType> sysNodeTypesTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<SysNodeType> sysNodeTypesDc;


    Logger logger = LoggerFactory.getLogger(FinAcctTypeBrowse.class);


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        sysNodeTypesTable.getSelected().stream()
                .forEach(orig -> {
                    SysNodeType copy = makeCopy(orig);
                    SysNodeType savedCopy = dataManager.save(copy);
                    sysNodeTypesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinAcctType " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );


                });
    }

    private SysNodeType makeCopy(SysNodeType orig) {
        SysNodeType copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }

}