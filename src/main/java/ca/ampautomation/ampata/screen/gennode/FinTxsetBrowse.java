package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinTxset.browse")
@UiDescriptor("fin-txset-browse.xml")
@LookupComponent("finTxsetTable")
public class FinTxsetBrowse extends StandardLookup<GenNode> {
}