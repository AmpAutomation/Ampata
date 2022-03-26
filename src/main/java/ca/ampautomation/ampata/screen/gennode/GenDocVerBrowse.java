package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_GenDocVer.browse")
@UiDescriptor("gen-doc-ver-browse.xml")
@LookupComponent("genNodesTable")
public class GenDocVerBrowse extends StandardLookup<GenNode> {
}