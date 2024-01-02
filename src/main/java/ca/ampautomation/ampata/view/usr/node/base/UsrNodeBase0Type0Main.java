package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeBaseTypes", layout = MainView.class)
@ViewController("enty_UsrNodeBaseType.main")
@ViewDescriptor("usr-node-base-0-type-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeBase0Type0Main extends UsrNodeBase0Type0BaseMain<UsrNodeBaseType, UsrNodeBase0Type0Service, UsrNodeBase0Type0Repo, TreeDataGrid<UsrNodeBaseType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase0TypeService")
    public void setService(UsrNodeBase0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase0Type.Repo")
    public void setRepo(UsrNodeBase0Type0Repo repo) { this.repo = repo; }

}