package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSet;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBalSet0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBalSet0Service;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinBalSet.edit")
@UiDescriptor("usr-node-fin-bal-set-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinBalSet0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinBalSet, UsrNodeFinBalSetType, UsrNodeFinBalSet0Service, UsrNodeFinBalSet0Repo> {

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