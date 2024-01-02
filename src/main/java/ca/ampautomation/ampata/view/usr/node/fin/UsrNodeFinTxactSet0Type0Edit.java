package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Type0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0Type0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Type0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinTxactSetTypes/:id", layout = MainView.class)
@ViewController("enty_UsrNodeFinTxactSetType.edit")
@ViewDescriptor("usr-node-fin-txact-set-0-type-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinTxactSet0Type0Edit extends UsrNodeBase0Type0BaseEdit<UsrNodeFinTxactSetType, UsrNodeFinTxactSet0Type0Service, UsrNodeFinTxactSet0Type0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet0TypeService")
    public void setService(UsrNodeFinTxactSet0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSetType.Repo")
    public void setRepo(UsrNodeFinTxactSet0Type0Repo repo) { this.repo = repo; }


}