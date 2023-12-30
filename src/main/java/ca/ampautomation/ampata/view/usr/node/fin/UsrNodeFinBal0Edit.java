package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBal;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBal0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBal0Service;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinBal.edit")
@UiDescriptor("usr-node-fin-bal-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinBal0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinBal, UsrNodeFinBalType, UsrNodeFinBal0Service, UsrNodeFinBal0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBal.Service")
    public void setService(UsrNodeFinBal0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBal.Repo")
    public void setRepo(UsrNodeFinBal0Repo repo) { this.repo = repo; }

}