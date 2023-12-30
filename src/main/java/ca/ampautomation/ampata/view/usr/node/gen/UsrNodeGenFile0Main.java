package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFile;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenFile0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFileType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenFile0Service;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenFile.main")
@UiDescriptor("usr-node-gen-file-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeGenFile0Main extends UsrNodeBase0BaseMain<UsrNodeGenFile, UsrNodeGenFileType, UsrNodeGenFile0Service, UsrNodeGenFile0Repo, Table<UsrNodeGenFile>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenFile.Service")
    public void setService(UsrNodeGenFile0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenFile.Repo")
    public void setRepo(UsrNodeGenFile0Repo repo) { this.repo = repo; }

}