package ca.ampautomation.ampata.screen.sys;

import ca.ampautomation.ampata.entity.sys.SysNode;
import ca.ampautomation.ampata.entity.sys.SysNodeQryMngr;
import ca.ampautomation.ampata.entity.sys.SysNodeType;
import io.jmix.ui.screen.*;

@UiController("enty_SysNode.main")
@UiDescriptor("sys-node-main.xml")
@LookupComponent("tableMain")
public class SysNodeMain extends SysNodeBaseMain<SysNode,SysNodeType, SysNodeQryMngr> {

}