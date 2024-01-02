package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrUsrNodeBases", layout = MainView.class)
@ViewController("enty_UsrNodeBase.main")
@ViewDescriptor("usr-node-base-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeBase0Main extends UsrNodeBase0BaseMain<UsrNodeBase, UsrNodeBaseType, UsrNodeBase0Service, UsrNodeBase0Repo, DataGrid<UsrNodeBase>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase.Service")
    public void setService(UsrNodeBase0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase.Repo")
    public void setRepo(UsrNodeBase0Repo repo) { this.repo = repo; }

}