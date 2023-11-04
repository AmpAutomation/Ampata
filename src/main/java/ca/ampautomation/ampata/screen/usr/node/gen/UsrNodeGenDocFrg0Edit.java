package ca.ampautomation.ampata.screen.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrg;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenChan0Repo;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenDocFrg0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrgType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenDocFrg0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenDocFrg.edit")
@UiDescriptor("usr-node-gen-doc-frg-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenDocFrg0Edit extends UsrNodeBase0BaseEdit<UsrNodeGenDocFrg, UsrNodeGenDocFrgType, UsrNodeGenDocFrg0Service, UsrNodeGenDocFrg0Repo> {

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