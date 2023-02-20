package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcy;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyQryMngr;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyType;
import ca.ampautomation.ampata.screen.sys.SysNodeBaseMain;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;

@UiController("enty_SysFinCurcy.main")
@UiDescriptor("sys-fin-curcy-main.xml")
@LookupComponent("tableMain")
public class SysFinCurcyMain extends SysNodeBaseMain<SysFinCurcy, SysFinCurcyType, SysFinCurcyQryMngr> {


}