package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeBaseType.edit")
@UiDescriptor("usr-node-base-0-type-0-edit.xml")
@EditedEntityContainer("usrNodeTypeDc")
public class UsrNodeBase0Type0Edit extends UsrNodeBase0Type0BaseEdit<UsrNodeBaseType, UsrNodeBase0Type0Service, UsrNodeBase0Type0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase0Type.Service")
    public void setService(UsrNodeBase0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase0Type.Repo")
    public void setRepo(UsrNodeBase0Type0Repo repo) { this.repo = repo; }


}