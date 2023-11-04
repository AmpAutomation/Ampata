package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenAgent0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgentType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenAgent0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenAgent.lookup")
@UiDescriptor("usr-node-gen-agent-0-lookup.xml")
@LookupComponent("tableMain")
public class UsrNodeGenAgent0Lookup extends UsrNodeBase0BaseLookup<UsrNodeGenAgent, UsrNodeGenAgentType, UsrNodeGenAgent0Service, UsrNodeGenAgent0Repo, Table<UsrNodeGenAgent>> {

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
