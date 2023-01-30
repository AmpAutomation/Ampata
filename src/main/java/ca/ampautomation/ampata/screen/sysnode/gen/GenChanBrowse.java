package ca.ampautomation.ampata.screen.sysnode.gen;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.screen.*;

@UiController("ampata_GenChan.browse")
@UiDescriptor("gen-chan-browse.xml")
@LookupComponent("sysNodesTable")
public class GenChanBrowse extends StandardLookup<SysNode> {
}