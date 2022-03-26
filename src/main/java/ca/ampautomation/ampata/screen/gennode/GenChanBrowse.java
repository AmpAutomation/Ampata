package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_GenChan.browse")
@UiDescriptor("gen-chan-browse.xml")
@LookupComponent("genNodesTable")
public class GenChanBrowse extends StandardLookup<GenNode> {
}