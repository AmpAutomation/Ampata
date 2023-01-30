package ca.ampautomation.ampata.screen.sysnode.fin;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinCurcy.browse")
@UiDescriptor("fin-curcy-browse.xml")
@LookupComponent("finCurcysTable")
public class FinCurcyBrowse extends StandardLookup<SysNode> {
}