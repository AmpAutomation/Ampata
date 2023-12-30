package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeBase.edit")
@UiDescriptor("usr-node-base-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeBase0Edit extends UsrNodeBase0BaseEdit<UsrNodeBase, UsrNodeBaseType, UsrNodeBase0Service, UsrNodeBase0Repo>  {

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