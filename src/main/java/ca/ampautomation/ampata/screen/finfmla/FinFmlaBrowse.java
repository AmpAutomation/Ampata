package ca.ampautomation.ampata.screen.finfmla;

import ca.ampautomation.ampata.entity.GenNode;
import ca.ampautomation.ampata.screen.gennode.GenAgentBrowse;
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
import ca.ampautomation.ampata.entity.FinFmla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_FinFmla.browse")
@UiDescriptor("fin-fmla-browse.xml")
@LookupComponent("finFmlasTable")
public class FinFmlaBrowse extends StandardLookup<FinFmla> {
    @Autowired
    private DataContext dataContext;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Metadata metadata;

    @Autowired
    private MetadataTools metadataTools;

    @Autowired
    private GroupTable<FinFmla> finFmlasTable;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<FinFmla> finFmlasDc;

    Logger logger = LoggerFactory.getLogger(GenAgentBrowse.class);

    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        finFmlasTable.getSelected().stream()
                .forEach(orig -> {
                    FinFmla copy = makeCopy(orig);
                    FinFmla savedCopy = dataManager.save(copy);
                    finFmlasDc.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated FinFmla " + copy.getId2()
                            + " [" + orig.getId() + "]"
                            + " ->"
                            + " [" + copy.getId() + "]"
                    );


                });
    }


    private FinFmla makeCopy(FinFmla orig) {
        FinFmla copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}