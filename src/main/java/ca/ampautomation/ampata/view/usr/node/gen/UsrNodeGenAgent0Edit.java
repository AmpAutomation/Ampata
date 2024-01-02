package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenAgent0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgentType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenAgent0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeGenAgents/:id", layout = MainView.class)
@ViewController("enty_UsrNodeGenAgent.edit")
@ViewDescriptor("usr-node-gen-agent-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenAgent0Edit extends UsrNodeBase0BaseEdit<UsrNodeGenAgent, UsrNodeGenAgentType, UsrNodeGenAgent0Service, UsrNodeGenAgent0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenAgent.Service")
    public void setService(UsrNodeGenAgent0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenAgent.Repo")
    public void setRepo(UsrNodeGenAgent0Repo repo) { this.repo = repo; }

}