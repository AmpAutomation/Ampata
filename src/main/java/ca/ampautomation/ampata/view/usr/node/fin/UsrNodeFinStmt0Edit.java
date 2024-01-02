package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinStmt0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinStmt0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinStmts/:id", layout = MainView.class)
@ViewController("enty_UsrNodeFinStmt.edit")
@ViewDescriptor("usr-node-fin-stmt-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinStmt0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinStmt, UsrNodeFinStmtType, UsrNodeFinStmt0Service, UsrNodeFinStmt0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmt.Service")
    public void setService(UsrNodeFinStmt0Service service) { this.service = service; }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmt.Repo")
    public void setRepo(UsrNodeFinStmt0Repo repo) { this.repo = repo; }

}