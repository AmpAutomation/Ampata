package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrg;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenDocFrg0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrgType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenDocFrg0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "UsrNodeGenDocFrgs/:id", layout = MainView.class)
@ViewController("enty_UsrNodeGenDocFrg.edit")
@ViewDescriptor("usr-node-gen-doc-frg-0-edit.xml")
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