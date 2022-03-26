package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_GenNode.browse")
@UiDescriptor("gen-node-browse.xml")
@LookupComponent("genNodesTable")
public class GenNodeBrowse extends StandardLookup<GenNode> {
}