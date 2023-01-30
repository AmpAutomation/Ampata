package ca.ampautomation.ampata.screen.sysnode.gen;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.screen.*;

@UiController("ampata_GenDocVer.browse")
@UiDescriptor("gen-doc-ver-browse.xml")
@LookupComponent("sysNodesTable")
public class GenDocVerBrowse extends StandardLookup<SysNode> {
}