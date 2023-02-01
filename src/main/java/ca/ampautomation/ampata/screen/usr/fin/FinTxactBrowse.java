package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinTxact.browse")
@UiDescriptor("fin-txact-browse.xml")
@LookupComponent("table")
public class FinTxactBrowse extends StandardLookup<UsrNode> {

}