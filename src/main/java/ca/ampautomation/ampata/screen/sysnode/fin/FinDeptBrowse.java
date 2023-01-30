package ca.ampautomation.ampata.screen.sysnode.fin;

import ca.ampautomation.ampata.entity.SysNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinDept.browse")
@UiDescriptor("fin-dept-browse.xml")
@LookupComponent("sysNodesTable")
public class FinDeptBrowse extends StandardLookup<SysNode> {
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        
    }
}