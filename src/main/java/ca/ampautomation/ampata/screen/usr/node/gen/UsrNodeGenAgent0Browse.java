package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
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

@UiController("enty_UsrNodeGenAgent.browse")
@UiDescriptor("usr-node-gen-agent-0-browse.xml")
@LookupComponent("tableMain")
public class UsrNodeGenAgent0Browse extends StandardLookup<UsrNodeBase> {
    
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
    private CollectionContainer<UsrNodeGenAgent> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNodeGenAgent> colLoadrMain;
    @Autowired
    private Table<UsrNodeGenAgent> tableMain;



    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        tableMain.getSelected().stream()
            .forEach(orig -> {
                UsrNodeGenAgent copy = makeCopy(orig);
                UsrNodeGenAgent savedCopy = dataManager.save(copy);
                colCntnrMain.getMutableItems().add(savedCopy);
                logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                        + "[" + orig.getId() + "]"
                        +" -> "
                        +"[" + copy.getId() + "]"
                );


            });
    }


    private UsrNodeGenAgent makeCopy(UsrNodeGenAgent orig) {
        UsrNodeGenAgent copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}