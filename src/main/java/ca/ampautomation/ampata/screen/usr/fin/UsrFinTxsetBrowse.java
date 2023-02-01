package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_UsrFinTxset.browse")
@UiDescriptor("usr-fin-txset-browse.xml")
@LookupComponent("table")
public class UsrFinTxsetBrowse extends StandardLookup<UsrNode> {
}