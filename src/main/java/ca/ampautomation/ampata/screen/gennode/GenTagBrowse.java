package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_GenTag.browse")
@UiDescriptor("gen-tag-browse.xml")
@LookupComponent("genNodesTable")
public class GenTagBrowse extends StandardLookup<GenNode> {
}