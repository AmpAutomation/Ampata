package ca.ampautomation.ampata.screen.node.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNode;

@UiController("ampata_FinDept.browse")
@UiDescriptor("fin-dept-browse.xml")
@LookupComponent("genNodesTable")
public class FinDeptBrowse extends StandardLookup<GenNode> {
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        
    }
}