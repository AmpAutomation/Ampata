package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinStmt.browse")
@UiDescriptor("fin-stmt-browse.xml")
@LookupComponent("genNodesTable")
public class FinStmtBrowse extends StandardLookup<GenNode> {
}