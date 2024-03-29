package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinAcct0Type0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0Type0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Type0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinAcctTypes/:id", layout = MainView.class)
@ViewController("enty_UsrNodeFinAcctType.edit")
@ViewDescriptor("usr-node-fin-acct-0-type-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinAcct0Type0Edit extends UsrNodeBase0Type0BaseEdit<UsrNodeFinAcctType, UsrNodeFinAcct0Type0Service, UsrNodeFinAcct0Type0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct0TypeService")
    public void setService(UsrNodeFinAcct0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct0TypeRepo")
    public void setRepo(UsrNodeFinAcct0Type0Repo repo) { this.repo = repo; }


}