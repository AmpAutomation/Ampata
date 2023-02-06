package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.screen.usr.gen.UsrGenAgentBrowse;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.core.UuidProvider;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrNodeType.browse")
@UiDescriptor("usr-node-type-browse.xml")
@LookupComponent("tableMain")
public class UsrNodeTypeBrowse extends StandardLookup<UsrNodeType> {
}