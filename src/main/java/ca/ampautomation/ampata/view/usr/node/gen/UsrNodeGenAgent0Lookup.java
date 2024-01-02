package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenAgent0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgentType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenAgent0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@ViewController("enty_UsrNodeGenAgent.lookup")
@ViewDescriptor("usr-node-gen-agent-0-lookup.xml")
@LookupComponent("dataGridMain")
@DialogMode(width = "64em")
public class UsrNodeGenAgent0Lookup extends UsrNodeBase0BaseLookup<UsrNodeGenAgent, UsrNodeGenAgentType, UsrNodeGenAgent0Service, UsrNodeGenAgent0Repo, DataGrid<UsrNodeGenAgent>> {

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
