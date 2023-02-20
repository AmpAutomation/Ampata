package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcy;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyQryMngr;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyType;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.screen.sys.SysNodeBaseBrowse;
import ca.ampautomation.ampata.screen.sys.SysNodeBaseEdit;
import io.jmix.ui.screen.*;

@UiController("enty_SysFinCurcy.browse")
@UiDescriptor("sys-fin-curcy-browse.xml")
@LookupComponent("tableMain")
public class SysFinCurcyBrowse extends SysNodeBaseBrowse<SysFinCurcy, SysFinCurcyType, SysFinCurcyQryMngr> {

}