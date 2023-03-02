package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.*;
import io.jmix.core.*;
import io.jmix.ui.Notifications;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@UiController("enty_UsrItem.main")
@UiDescriptor("usr-item-0-main.xml")
@LookupComponent("tableMain")
public class UsrItem0Main extends UsrItem0BaseMain<UsrItem,UsrItemType, UsrItemQryMngr, TreeTable<UsrItem>> {


}