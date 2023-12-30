package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinDept0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinDept0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinDept.edit")
@UiDescriptor("usr-node-fin-dept-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinDept0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinDept, UsrNodeFinDeptType, UsrNodeFinDept0Service, UsrNodeFinDept0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinDept.Service")
    public void setService(UsrNodeFinDept0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinDept.Repo")
    public void setRepo(UsrNodeFinDept0Repo repo) { this.repo = repo; }

}