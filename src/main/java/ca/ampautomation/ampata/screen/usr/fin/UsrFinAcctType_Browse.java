package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNodeType;
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

@UiController("enty_UsrFinAcctType.browse")
@UiDescriptor("usr-fin-acct-type-browse.xml")
@LookupComponent("tableMain")
public class UsrFinAcctType_Browse extends StandardLookup<UsrNodeType> {

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
    private CollectionContainer<UsrNodeType> colCntnrMain;
    @Autowired
    private CollectionLoader<UsrNodeType> colLoadrMain;
    @Autowired
    private Table<UsrNodeType> tableMain;



    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        tableMain.getSelected().stream()
                .forEach(orig -> {
                    UsrNodeType copy = makeCopy(orig);
                    UsrNodeType savedCopy = dataManager.save(copy);
                    colCntnrMain.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated " + copy.getClass().getName() + "(" + copy.getClassName() +") " + copy.getId2() + " "
                            + "[" + orig.getId() + "]"
                            +" -> "
                            +"[" + copy.getId() + "]"
                    );


                });
    }

    private UsrNodeType makeCopy(UsrNodeType orig) {
        UsrNodeType copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }

}