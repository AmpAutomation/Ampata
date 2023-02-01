package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("ampata_FinDept.browse")
@UiDescriptor("fin-dept-browse.xml")
@LookupComponent("usrNodesTable")
public class FinDeptBrowse extends StandardLookup<UsrNode> {
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        
    }
}