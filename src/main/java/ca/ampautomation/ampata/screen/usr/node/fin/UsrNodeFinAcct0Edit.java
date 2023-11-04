package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinAcct0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinAcct.edit")
@UiDescriptor("usr-node-fin-acct-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinAcct0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinAcct, UsrNodeFinAcctType, UsrNodeFinAcct0Service, UsrNodeFinAcct0Repo> {

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