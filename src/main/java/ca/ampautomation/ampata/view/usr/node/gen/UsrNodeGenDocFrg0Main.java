package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrg;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenDocFrg0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrgType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenDocFrg0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenDocFrg.main")
@UiDescriptor("usr-node-gen-doc-frg-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeGenDocFrg0Main extends UsrNodeBase0BaseMain<UsrNodeGenDocFrg, UsrNodeGenDocFrgType, UsrNodeGenDocFrg0Service, UsrNodeGenDocFrg0Repo, Table<UsrNodeGenDocFrg>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenDocFrg.Service")
    public void setService(UsrNodeGenDocFrg0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenDocFrg.Repo")
    public void setRepo(UsrNodeGenDocFrg0Repo repo) { this.repo = repo; }

}