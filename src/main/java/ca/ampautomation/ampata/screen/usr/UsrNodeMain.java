package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNodeQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.LookupComponent;

@UiController("enty_UsrNode.main")
@UiDescriptor("usr-node-main.xml")
@LookupComponent("tableMain")
public class UsrNodeMain extends UsrNodeBaseMain<UsrNode,UsrNodeType, UsrNodeQryMngr> {


}