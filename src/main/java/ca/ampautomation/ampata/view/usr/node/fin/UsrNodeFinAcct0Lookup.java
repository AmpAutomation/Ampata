package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinAcct0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@ViewController("enty_UsrNodeFinAcct.lookup")
@ViewDescriptor("usr-node-fin-acct-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinAcct0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinAcct, UsrNodeFinAcctType, UsrNodeFinAcct0Service, UsrNodeFinAcct0Repo, DataGrid<UsrNodeFinAcct>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct.Service")
    public void setService(UsrNodeFinAcct0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct.Repo")
    public void setRepo(UsrNodeFinAcct0Repo repo) { this.repo = repo;}


}
