package ca.ampautomation.ampata.view.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBase;
import ca.ampautomation.ampata.repo.usr.edge.base.UsrEdgeBase0Repo;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.service.usr.edge.base.UsrEdgeBase0Service;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;

@Route(value = "usrEdgeGenBases", layout = MainView.class)
@ViewController("enty_UsrEdgeBase.main")
@ViewDescriptor("usr-base-edge-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrEdgeBase0Main extends UsrEdgeBase0BaseMain<UsrEdgeBase, UsrEdgeBaseType, UsrEdgeBase0Service, UsrEdgeBase0Repo, DataGrid<UsrEdgeBase>> {

}