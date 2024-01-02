package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinAcct0Type0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0Type0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Type0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinAcctTypes", layout = MainView.class)
@ViewController("enty_UsrNodeFinAcctType.main")
@ViewDescriptor("usr-node-fin-acct-0-type-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinAcct0Type0Main extends UsrNodeBase0Type0BaseMain<UsrNodeFinAcctType, UsrNodeFinAcct0Type0Service, UsrNodeFinAcct0Type0Repo, TreeDataGrid<UsrNodeFinAcctType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct0TypeService")
    public void setService(UsrNodeFinAcct0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct0TypeRepo")
    public void setRepo(UsrNodeFinAcct0Type0Repo repo) { this.repo = repo; }



}