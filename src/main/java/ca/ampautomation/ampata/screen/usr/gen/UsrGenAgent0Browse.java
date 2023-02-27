package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenAgent;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.core.UuidProvider;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrGenAgent.browse")
@UiDescriptor("usr-gen-agent-0-browse.xml")
@LookupComponent("tableMain")
public class UsrGenAgent0Browse extends StandardLookup<UsrNode> {
    
    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrGenAgent> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrGenAgent> colLoadrMain;
    @Autowired
    private Table<UsrGenAgent> tableMain;



    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        tableMain.getSelected().stream()
            .forEach(orig -> {
                UsrGenAgent copy = makeCopy(orig);
                UsrGenAgent savedCopy = dataManager.save(copy);
                colCntnrMain.getMutableItems().add(savedCopy);
                logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                        + "[" + orig.getId() + "]"
                        +" -> "
                        +"[" + copy.getId() + "]"
                );


            });
    }


    private UsrGenAgent makeCopy(UsrGenAgent orig) {
        UsrGenAgent copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}