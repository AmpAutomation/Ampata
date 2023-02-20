package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcy;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyQryMngr;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyType;
import ca.ampautomation.ampata.entity.usr.UsrNode;
import ca.ampautomation.ampata.screen.sys.SysNodeBaseEdit;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_SysFinCurcy.edit")
@UiDescriptor("sys-fin-curcy-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class SysFinCurcyEdit extends SysNodeBaseEdit<SysFinCurcy, SysFinCurcyType, SysFinCurcyQryMngr> {


}