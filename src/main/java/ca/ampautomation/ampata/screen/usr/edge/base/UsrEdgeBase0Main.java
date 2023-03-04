package ca.ampautomation.ampata.screen.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBase;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseQryMngr;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("enty_UsrEdgeBase.main")
@UiDescriptor("usr-base-edge-0-main.xml")
@LookupComponent("tableMain")
public class UsrEdgeBase0Main extends UsrEdgeBase0BaseMain<UsrEdgeBase, UsrEdgeBaseType, UsrEdgeBaseQryMngr, Table<UsrEdgeBase>> {

}