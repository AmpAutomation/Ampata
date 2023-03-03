package ca.ampautomation.ampata.screen.usr.base;

import ca.ampautomation.ampata.entity.usr.base.UsrNodeBaseQryMngr;
import ca.ampautomation.ampata.entity.usr.base.UsrNodeBaseType;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrNodeBase;
import io.jmix.ui.screen.LookupComponent;

@UiController("enty_UsrNodeBase.main")
@UiDescriptor("usr-base-node-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeBase0Main extends UsrNodeBase0BaseMain<UsrNodeBase, UsrNodeBaseType, UsrNodeBaseQryMngr, TreeTable<UsrNodeBase>> {

}