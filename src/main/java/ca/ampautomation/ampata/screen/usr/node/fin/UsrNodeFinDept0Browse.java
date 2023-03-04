package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.ui.screen.*;

@UiController("enty_UsrNodeFinDept.browse")
@UiDescriptor("usr-node-fin-dept-0-browse.xml")
@LookupComponent("tableMain")
public class UsrNodeFinDept0Browse extends StandardLookup<UsrNodeBase> {
    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        
    }
}