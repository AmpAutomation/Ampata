package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBalSet0Type0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0Type0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBalSet0Type0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinBalSetTypes", layout = MainView.class)
@ViewController("enty_UsrNodeFinBalSetType.main")
@ViewDescriptor("usr-node-fin-bal-set-0-type-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinBalSet0Type0Main extends UsrNodeBase0Type0BaseMain<UsrNodeFinBalSetType, UsrNodeFinBalSet0Type0Service, UsrNodeFinBalSet0Type0Repo, TreeDataGrid<UsrNodeFinBalSetType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet0TypeService")
    public void setService(UsrNodeFinBalSet0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet0TypeRepo")
    public void setRepo(UsrNodeFinBalSet0Type0Repo repo) { this.repo = repo; }



}