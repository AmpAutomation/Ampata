package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.*;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenBasic0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenBasic0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeGenBasics/:id", layout = MainView.class)
@ViewController("enty_UsrNodeGenBasic.edit")
@ViewDescriptor("usr-node-gen-basic-0-edit.xml")
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