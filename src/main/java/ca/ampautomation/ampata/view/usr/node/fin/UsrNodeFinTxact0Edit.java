package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxact0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxact0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinTxact.edit")
@UiDescriptor("usr-node-fin-txact-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinTxact0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinTxact, UsrNodeFinTxactType, UsrNodeFinTxact0Service, UsrNodeFinTxact0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxact.Service")
    public void setService(UsrNodeFinTxact0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxact.Repo")
    public void setRepo(UsrNodeFinTxact0Repo repo) { this.repo = repo; }

}