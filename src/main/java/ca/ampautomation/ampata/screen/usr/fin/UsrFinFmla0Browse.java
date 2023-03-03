package ca.ampautomation.ampata.screen.usr.fin;

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
import ca.ampautomation.ampata.entity.usr.item.fin.UsrFinFmla;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrFinFmla.browse")
@UiDescriptor("usr-fin-fmla-0-browse.xml")
@LookupComponent("tableMain")
public class UsrFinFmla0Browse extends StandardLookup<UsrFinFmla> {

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

    @Autowired
    private GroupTable<UsrFinFmla> tableMain;


    @Autowired
    private CollectionContainer<UsrFinFmla> colCntnrFinFmla;


    @Subscribe("duplicateBtn")
    public void onDuplicateBtnClick(Button.ClickEvent event) {
        tableMain.getSelected().stream()
                .forEach(orig -> {
                    UsrFinFmla copy = makeCopy(orig);
                    UsrFinFmla savedCopy = dataManager.save(copy);
                    colCntnrFinFmla.getMutableItems().add(savedCopy);
                    logger.debug("Duplicated " + copy.getClass().getName() + " " + copy.getId2() + " "
                            + " [" + orig.getId() + "]"
                            + " ->"
                            + " [" + copy.getId() + "]"
                    );


                });
    }


    private UsrFinFmla makeCopy(UsrFinFmla orig) {
        UsrFinFmla copy = metadataTools.copy(orig);
        copy.setId(UuidProvider.createUuid());
        return copy;
    }
}