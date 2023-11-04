package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSet;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBalSet0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBalSet0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinBalSet.lookup")
@UiDescriptor("usr-node-fin-bal-set-0-lookup.xml")
@LookupComponent("tableMain")
public class UsrNodeFinBalSet0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinBalSet, UsrNodeFinBalSetType, UsrNodeFinBalSet0Service, UsrNodeFinBalSet0Repo, Table<UsrNodeFinBalSet>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet.Service")
    public void setService(UsrNodeFinBalSet0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet.Repo")
    public void setRepo(UsrNodeFinBalSet0Repo repo) { this.repo = repo; }


}
