package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSet;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBalSet0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBalSet0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinBalSets/:id", layout = MainView.class)
@ViewController("enty_UsrNodeFinBalSet.edit")
@ViewDescriptor("usr-node-fin-bal-set-0-edit.xml")
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