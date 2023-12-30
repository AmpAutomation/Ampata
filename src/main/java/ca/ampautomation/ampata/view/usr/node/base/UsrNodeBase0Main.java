package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeBase.main")
@UiDescriptor("usr-node-base-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeBase0Main extends UsrNodeBase0BaseMain<UsrNodeBase, UsrNodeBaseType, UsrNodeBase0Service, UsrNodeBase0Repo, Table<UsrNodeBase>> {

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