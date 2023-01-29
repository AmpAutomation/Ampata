package ca.ampautomation.ampata.screen.node.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinTxset.browse")
@UiDescriptor("fin-txset-browse.xml")
@LookupComponent("table")
public class FinTxsetBrowse extends StandardLookup<GenNode> {
}