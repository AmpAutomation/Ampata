package ca.ampautomation.ampata.screen.sysnode.fin;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinTxact.browse")
@UiDescriptor("fin-txact-browse.xml")
@LookupComponent("table")
public class FinTxactBrowse extends StandardLookup<SysNode> {

}