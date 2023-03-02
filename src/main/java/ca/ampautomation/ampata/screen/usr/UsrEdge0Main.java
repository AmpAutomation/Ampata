package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrEdge;
import ca.ampautomation.ampata.entity.usr.UsrEdgeQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrEdgeType;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("enty_UsrEdge.main")
@UiDescriptor("usr-edge-0-main.xml")
@LookupComponent("tableMain")
public class UsrEdge0Main extends UsrEdge0BaseMain<UsrEdge,UsrEdgeType, UsrEdgeQryMngr, Table<UsrEdge>> {

}