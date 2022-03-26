package ca.ampautomation.ampata.screen.gennode;

import io.jmix.ui.component.DataGrid;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinTxact.browse")
@UiDescriptor("fin-txact-browse.xml")
@LookupComponent("genNodesTable")
public class FinTxactBrowse extends StandardLookup<GenNode> {
    @Subscribe("genNodesTable")
    public void onGenNodesTableEditorOpen(DataGrid.EditorOpenEvent<GenNode> event) {
        
    }
}