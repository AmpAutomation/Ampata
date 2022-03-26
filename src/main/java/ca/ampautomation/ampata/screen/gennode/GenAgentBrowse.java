package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_GenAgent.browse")
@UiDescriptor("gen-agent-browse.xml")
@LookupComponent("genNodesTable")
public class GenAgentBrowse extends StandardLookup<GenNode> {
}