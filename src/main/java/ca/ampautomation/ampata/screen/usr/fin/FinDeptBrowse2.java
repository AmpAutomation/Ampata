package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("ampata_FinDept.browse2")
@UiDescriptor("fin-dept-browse2.xml")
@LookupComponent("table")
public class FinDeptBrowse2 extends MasterDetailScreen<UsrNode> {
}