package ca.ampautomation.ampata.screen.usr.base;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseNodeQryMngr;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNodeType;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;
import io.jmix.ui.screen.LookupComponent;

@UiController("enty_UsrBaseNode.main")
@UiDescriptor("usr-base-node-0-main.xml")
@LookupComponent("tableMain")
public class UsrBaseNode0Main extends UsrBaseNode0BaseMain<UsrBaseNode, UsrBaseNodeType, UsrBaseNodeQryMngr, TreeTable<UsrBaseNode>> {

}