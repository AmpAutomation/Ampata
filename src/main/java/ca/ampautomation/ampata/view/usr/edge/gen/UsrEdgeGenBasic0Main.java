package ca.ampautomation.ampata.view.usr.edge.gen;

import ca.ampautomation.ampata.entity.usr.edge.gen.UsrEdgeGenBasic;
import ca.ampautomation.ampata.entity.usr.edge.gen.UsrEdgeGenBasicType;
import ca.ampautomation.ampata.repo.usr.edge.gen.UsrEdgeGenBasic0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.edge.base.UsrEdgeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.edge.gen.UsrEdgeGenBasic0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrEdgeGenBasics", layout = MainView.class)
@ViewController("enty_UsrEdgeGenBasic.main")
@ViewDescriptor("usr-edge-gen-basic-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrEdgeGenBasic0Main extends UsrEdgeBase0BaseMain<UsrEdgeGenBasic, UsrEdgeGenBasicType, UsrEdgeGenBasic0Service, UsrEdgeGenBasic0Repo, DataGrid<UsrEdgeGenBasic>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinHow.Service")
    public void setService(UsrEdgeGenBasic0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinHow.Repo")
    public void setRepo(UsrEdgeGenBasic0Repo repo) { this.repo = repo; }

}

