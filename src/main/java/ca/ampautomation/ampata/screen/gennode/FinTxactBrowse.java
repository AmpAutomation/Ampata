package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.component.DataGrid;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinTxact.browse")
@UiDescriptor("fin-txact-browse.xml")
@LookupComponent("table")
public class FinTxactBrowse extends StandardLookup<GenNode> {

}