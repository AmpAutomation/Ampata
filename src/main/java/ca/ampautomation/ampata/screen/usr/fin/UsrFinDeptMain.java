package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("enty_UsrFinDept.main")
@UiDescriptor("usr-fin-dept-main.xml")
@LookupComponent("tableMain")
public class UsrFinDeptMain extends MasterDetailScreen<UsrNode> {
}