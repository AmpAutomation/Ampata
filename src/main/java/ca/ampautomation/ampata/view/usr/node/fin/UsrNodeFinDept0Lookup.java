package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinDept0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinDept0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinDept.lookup")
@UiDescriptor("usr-node-fin-dept-0-lookup.xml")
@LookupComponent("tableMain")
public class UsrNodeFinDept0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinDept, UsrNodeFinDeptType, UsrNodeFinDept0Service, UsrNodeFinDept0Repo, Table<UsrNodeFinDept>> {

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
