package ca.ampautomation.ampata.screen.sysnode.gen;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.SysNode;

@UiController("ampata_GenTag.browse")
@UiDescriptor("gen-tag-browse.xml")
@LookupComponent("sysNodesTable")
public class GenTagBrowse extends StandardLookup<SysNode> {
}