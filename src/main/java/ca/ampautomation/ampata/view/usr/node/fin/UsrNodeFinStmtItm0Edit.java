package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinStmtItm0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinStmtItm0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinStmtItms/:id", layout = MainView.class)
@ViewController("enty_UsrNodeFinStmtItm.edit")
@ViewDescriptor("usr-node-fin-stmt-itm-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinStmtItm0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinStmtItm, UsrNodeFinStmtItmType, UsrNodeFinStmtItm0Service, UsrNodeFinStmtItm0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmtItm.Service")
    public void setService(UsrNodeFinStmtItm0Service service) { this.service = service; }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmtItm.Repo")
    public void setRepo(UsrNodeFinStmtItm0Repo repo) { this.repo = repo; }


}