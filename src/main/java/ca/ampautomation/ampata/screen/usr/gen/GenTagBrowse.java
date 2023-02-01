package ca.ampautomation.ampata.screen.usr.gen;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("ampata_GenTag.browse")
@UiDescriptor("gen-tag-browse.xml")
@LookupComponent("usrNodesTable")
public class GenTagBrowse extends StandardLookup<UsrNode> {
}