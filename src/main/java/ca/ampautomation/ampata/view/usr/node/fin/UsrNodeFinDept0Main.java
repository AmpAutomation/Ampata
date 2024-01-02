package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinDept0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinDept0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinDepts", layout = MainView.class)
@ViewController("enty_UsrNodeFinDept.main")
@ViewDescriptor("usr-node-fin-dept-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinDept0Main extends UsrNodeBase0BaseMain<UsrNodeFinDept, UsrNodeFinDeptType, UsrNodeFinDept0Service, UsrNodeFinDept0Repo, DataGrid<UsrNodeFinDept>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinDept.Service")
    public void setService(UsrNodeFinDept0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinDept.Repo")
    public void setRepo(UsrNodeFinDept0Repo repo) { this.repo = repo; }

}