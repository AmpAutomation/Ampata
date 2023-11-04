package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinAcct0Type0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0Type0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Type0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinAcctType.edit")
@UiDescriptor("usr-node-fin-acct-0-type-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinAcctType0Edit extends UsrNodeBase0Type0BaseEdit<UsrNodeFinAcctType, UsrNodeFinAcct0Type0Service, UsrNodeFinAcct0Type0Repo> {

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