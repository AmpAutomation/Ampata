package ca.ampautomation.ampata.screen.sysnode.fin;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinTxset.browse")
@UiDescriptor("fin-txset-browse.xml")
@LookupComponent("table")
public class FinTxsetBrowse extends StandardLookup<SysNode> {
}