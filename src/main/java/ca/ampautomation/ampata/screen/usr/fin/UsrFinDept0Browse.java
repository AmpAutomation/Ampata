package ca.ampautomation.ampata.screen.usr.fin;

import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;
import io.jmix.ui.screen.*;

@UiController("enty_UsrFinDept.browse")
@UiDescriptor("usr-fin-dept-0-browse.xml")
@LookupComponent("tableMain")
public class UsrFinDept0Browse extends StandardLookup<UsrBaseNode> {
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        
    }
}