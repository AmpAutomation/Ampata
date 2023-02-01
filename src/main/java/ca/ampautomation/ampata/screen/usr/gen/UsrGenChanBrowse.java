package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_UsrGenChan.browse")
@UiDescriptor("usr-gen-chan-browse.xml")
@LookupComponent("table")
public class UsrGenChanBrowse extends StandardLookup<UsrNode> {
}