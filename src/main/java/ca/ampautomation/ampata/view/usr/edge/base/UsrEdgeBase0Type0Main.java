package ca.ampautomation.ampata.view.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.repo.usr.edge.base.UsrEdgeBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.edge.base.UsrEdgeBase0Type0Service;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrEdgeGenBaseTypes", layout = MainView.class)
@ViewController("enty_UsrEdgeBaseType.main")
@ViewDescriptor("usr-base-edge-type-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrEdgeBase0Type0Main extends UsrEdgeBase0Type0BaseMain<UsrEdgeBaseType, UsrEdgeBase0Type0Service, UsrEdgeBase0Type0Repo, DataGrid<UsrEdgeBaseType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrEdgeBaseType.Service")
    public void setService(UsrEdgeBase0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrEdgeBaseType.Repo")
    public void setRepo(UsrEdgeBase0Type0Repo repo) { this.repo = repo; }


}