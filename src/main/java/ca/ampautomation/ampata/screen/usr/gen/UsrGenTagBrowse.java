package ca.ampautomation.ampata.screen.usr.gen;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.UsrNode;

@UiController("ampata_UsrGenTag.browse")
@UiDescriptor("usr-gen-tag-browse.xml")
@LookupComponent("table")
public class UsrGenTagBrowse extends StandardLookup<UsrNode> {
}