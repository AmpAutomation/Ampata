package ca.ampautomation.ampata.screen.usr;

import ca.ampautomation.ampata.entity.usr.UsrNodeQryMngr;
import ca.ampautomation.ampata.entity.usr.UsrNodeType;
import ca.ampautomation.ampata.fragment.UsrNodeFrag;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_UsrNode.main")
@UiDescriptor("usr-node-main.xml")
@LookupComponent("tableMain")
public class UsrNodeMain extends UsrNodeBaseMain<UsrNode,UsrNodeType, UsrNodeQryMngr, TreeTable<UsrNode>> {

}