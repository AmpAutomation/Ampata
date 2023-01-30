package ca.ampautomation.ampata.screen.sysnode.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.SysNode;

@UiController("ampata_FinDept.browse2")
@UiDescriptor("fin-dept-browse2.xml")
@LookupComponent("table")
public class FinDeptBrowse2 extends MasterDetailScreen<SysNode> {
}