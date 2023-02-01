package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;

@UiController("ampata_FinTxactItm.browse")
@UiDescriptor("fin-txact-itm-browse.xml")
@LookupComponent("table")
public class FinTxactItmBrowse extends StandardLookup<UsrNode> {

}