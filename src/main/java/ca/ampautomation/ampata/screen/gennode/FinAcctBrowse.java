package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinAcct.browse")
@UiDescriptor("fin-acct-browse.xml")
@LookupComponent("genNodesTable")
public class FinAcctBrowse extends StandardLookup<GenNode> {
}