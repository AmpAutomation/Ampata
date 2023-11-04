package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenDocVer0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVerType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenDocVer0Service;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenDocVer.main")
@UiDescriptor("usr-node-gen-doc-ver-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeGenDocVer0Main extends UsrNodeBase0BaseMain<UsrNodeGenDocVer, UsrNodeGenDocVerType, UsrNodeGenDocVer0Service, UsrNodeGenDocVer0Repo, Table<UsrNodeGenDocVer>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenDocVer.Service")
    public void setService(UsrNodeGenDocVer0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenDocVer.Repo")
    public void setRepo(UsrNodeGenDocVer0Repo repo) { this.repo = repo; }

}