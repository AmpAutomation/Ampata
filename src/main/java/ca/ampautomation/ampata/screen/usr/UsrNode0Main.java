package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNodeQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.LookupComponent;

@UiController("enty_UsrNode.main")
@UiDescriptor("usr-node-0-main.xml")
@LookupComponent("tableMain")
public class UsrNode0Main extends UsrNode0BaseMain<UsrNode,UsrNodeType, UsrNodeQryMngr, TreeTable<UsrNode>> {

}