package ca.ampautomation.ampata.screen.sys.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyQryMngr;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyType;
import ca.ampautomation.ampata.screen.sys.base.SysNodeBase0BaseBrowse;
import io.jmix.ui.screen.*;

@UiController("enty_SysFinCurcy.browse")
@UiDescriptor("sys-node-fin-curcy-0-browse.xml")
@LookupComponent("tableMain")
public class SysNodeFinCurcy0Browse extends SysNodeBase0BaseBrowse<SysNodeFinCurcy, SysNodeFinCurcyType, SysNodeFinCurcyQryMngr> {

}