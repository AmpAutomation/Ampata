package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_GenDocVer.browse")
@UiDescriptor("gen-doc-ver-browse.xml")
@LookupComponent("usrNodesTable")
public class GenDocVerBrowse extends StandardLookup<UsrNode> {
}