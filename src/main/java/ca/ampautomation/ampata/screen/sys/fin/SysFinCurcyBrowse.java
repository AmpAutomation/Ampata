package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_SysFinCurcy.browse")
@UiDescriptor("sys-fin-curcy-browse.xml")
@LookupComponent("table")
public class SysFinCurcyBrowse extends StandardLookup<UsrNode> {
}