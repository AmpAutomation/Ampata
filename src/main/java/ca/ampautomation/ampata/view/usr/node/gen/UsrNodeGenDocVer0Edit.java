package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenDocVer0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVerType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenDocVer0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "UsrNodeGenDocVers/:id", layout = MainView.class)
@ViewController("enty_UsrNodeGenDocVer.edit")
@ViewDescriptor("usr-node-gen-doc-ver-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeGenDocVer0Edit extends UsrNodeBase0BaseEdit<UsrNodeGenDocVer, UsrNodeGenDocVerType, UsrNodeGenDocVer0Service, UsrNodeGenDocVer0Repo> {

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