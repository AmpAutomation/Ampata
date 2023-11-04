package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasic;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenBasic0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasicType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenBasic0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenBasic.main")
@UiDescriptor("usr-node-gen-basic-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeGenBasic0Main extends UsrNodeBase0BaseMain<UsrNodeGenBasic, UsrNodeGenBasicType, UsrNodeGenBasic0Service, UsrNodeGenBasic0Repo, Table<UsrNodeGenBasic>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenBasic.Service")
    public void setService(UsrNodeGenBasic0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenBasic.Repo")
    public void setRepo(UsrNodeGenBasic0Repo repo) { this.repo = repo; }



}