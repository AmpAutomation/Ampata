package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
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

@UiController("enty_UsrNodeFinAcctType.browse")
@UiDescriptor("usr-node-fin-acct-type-0-browse.xml")
@LookupComponent("tableMain")
public class UsrNodeFinAcctType0Browse extends StandardLookup<UsrNodeBaseType> {

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

    //Filter


    //Toolbar

    //Template

    //Main data containers, loaders and table
    @Autowired
    private CollectionContainer<UsrNodeBaseType> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNodeBaseType> colLoadrMain;
    @Autowired
    private Table<UsrNodeBaseType> tableMain;



    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        tableMain.getSelected().stream()
                .forEach(orig -> {
                    UsrNodeBaseType copy = makeCopy(orig);
                    UsrNodeBaseType savedCopy = dataManager.save(copy);
                    colCntnrMain.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );


                });
    }

    private UsrNodeBaseType makeCopy(UsrNodeBaseType orig) {
        UsrNodeBaseType copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }

}