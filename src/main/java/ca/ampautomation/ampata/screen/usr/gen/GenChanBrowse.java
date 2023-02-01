package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_GenChan.browse")
@UiDescriptor("gen-chan-browse.xml")
@LookupComponent("usrNodesTable")
public class GenChanBrowse extends StandardLookup<UsrNode> {
}