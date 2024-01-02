package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFile;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenFile0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFileType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenFile0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "UsrNodeGenFiles/:id", layout = MainView.class)
@ViewController("enty_UsrNodeGenFile.edit")
@ViewDescriptor("usr-node-gen-file-0-edit.xml")
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