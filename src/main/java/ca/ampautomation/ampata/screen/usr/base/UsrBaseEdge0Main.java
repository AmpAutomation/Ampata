package ca.ampautomation.ampata.screen.usr.base;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseEdge;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseEdgeQryMngr;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseEdgeType;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("enty_UsrBaseEdge.main")
@UiDescriptor("usr-base-edge-0-main.xml")
@LookupComponent("tableMain")
public class UsrBaseEdge0Main extends UsrBaseEdge0BaseMain<UsrBaseEdge, UsrBaseEdgeType, UsrBaseEdgeQryMngr, Table<UsrBaseEdge>> {

}