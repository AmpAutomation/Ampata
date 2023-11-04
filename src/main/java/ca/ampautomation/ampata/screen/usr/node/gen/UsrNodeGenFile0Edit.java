package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFile;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenDocVer0Repo;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenFile0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFileType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenFile0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenFile.edit")
@UiDescriptor("usr-node-gen-file-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenFile0Edit extends UsrNodeBase0BaseEdit<UsrNodeGenFile, UsrNodeGenFileType, UsrNodeGenFile0Service, UsrNodeGenFile0Repo> {

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