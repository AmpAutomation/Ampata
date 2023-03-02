package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcy;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyQryMngr;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyType;
import ca.ampautomation.ampata.screen.sys.base.SysBaseNode0BaseBrowse;
import io.jmix.ui.screen.*;

@UiController("enty_SysFinCurcy.browse")
@UiDescriptor("sys-fin-curcy-0-browse.xml")
@LookupComponent("tableMain")
public class SysFinCurcy0Browse extends SysBaseNode0BaseBrowse<SysFinCurcy, SysFinCurcyType, SysFinCurcyQryMngr> {

}