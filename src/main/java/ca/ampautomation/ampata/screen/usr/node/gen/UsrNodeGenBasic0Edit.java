package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.*;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenAgent0Repo;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenBasic0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenBasic0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenBasic.edit")
@UiDescriptor("usr-node-gen-basic-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenBasic0Edit extends UsrNodeBase0BaseEdit<UsrNodeGenBasic, UsrNodeGenBasicType, UsrNodeGenBasic0Service, UsrNodeGenBasic0Repo> {

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