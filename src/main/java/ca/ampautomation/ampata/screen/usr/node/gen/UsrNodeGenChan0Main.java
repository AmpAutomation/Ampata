package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenChan0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChanType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenChan0Service;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenChan.main")
@UiDescriptor("usr-node-gen-chan-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeGenChan0Main extends UsrNodeBase0BaseMain<UsrNodeGenChan, UsrNodeGenChanType, UsrNodeGenChan0Service, UsrNodeGenChan0Repo, Table<UsrNodeGenChan>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenChan.Service")
    public void setService(UsrNodeGenChan0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenChan.Repo")
    public void setRepo(UsrNodeGenChan0Repo repo) { this.repo = repo; }

    
}