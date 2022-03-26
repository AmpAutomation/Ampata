package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_GenDocFrg.browse")
@UiDescriptor("gen-doc-frg-browse.xml")
@LookupComponent("genNodesTable")
public class GenDocFrgBrowse extends StandardLookup<GenNode> {
}