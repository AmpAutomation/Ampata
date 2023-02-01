package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinTxset.browse")
@UiDescriptor("fin-txset-browse.xml")
@LookupComponent("table")
public class FinTxsetBrowse extends StandardLookup<UsrNode> {
}