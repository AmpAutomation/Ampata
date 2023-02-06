package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.ui.screen.*;

@UiController("enty_UsrFinDept.browse")
@UiDescriptor("usr-fin-dept-browse.xml")
@LookupComponent("tableMain")
public class UsrFinDeptBrowse extends StandardLookup<UsrNode> {
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        
    }
}