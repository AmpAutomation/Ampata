package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinCurcy.browse")
@UiDescriptor("sys-fin-curcy-browse.xml")
@LookupComponent("finCurcysTable")
public class SysFinCurcyBrowse extends StandardLookup<UsrNode> {
}