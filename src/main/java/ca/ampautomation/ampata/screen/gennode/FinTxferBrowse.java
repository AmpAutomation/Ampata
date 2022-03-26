package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinTxfer.browse")
@UiDescriptor("fin-txfer-browse.xml")
@LookupComponent("genNodesTable")
public class FinTxferBrowse extends StandardLookup<GenNode> {
}