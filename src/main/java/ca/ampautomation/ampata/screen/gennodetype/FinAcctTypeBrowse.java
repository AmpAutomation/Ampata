package ca.ampautomation.ampata.screen.gennodetype;

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
import ca.ampautomation.ampata.entity.GenNodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinAcctType.browse")
@UiDescriptor("fin-acct-type-browse.xml")
@LookupComponent("genNodeTypesTable")
public class FinAcctTypeBrowse extends StandardLookup<GenNodeType> {

    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<GenNodeType> genNodeTypesTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<GenNodeType> genNodeTypesDc;


    Logger logger = LoggerFactory.getLogger(FinAcctTypeBrowse.class);


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        genNodeTypesTable.getSelected().stream()
                .forEach(orig -> {
                    GenNodeType copy = makeCopy(orig);
                    GenNodeType savedCopy = dataManager.save(copy);
                    genNodeTypesDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinAcctType " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );


                });
    }

    private GenNodeType makeCopy(GenNodeType orig) {
        GenNodeType copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }

}