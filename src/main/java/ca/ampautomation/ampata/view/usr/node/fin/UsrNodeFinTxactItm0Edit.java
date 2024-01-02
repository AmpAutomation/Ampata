package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactItm0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactItm0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrNodeFinTxactItms/:id", layout = MainView.class)
@ViewController("enty_UsrNodeFinTxactItm.edit")
@ViewDescriptor("usr-node-fin-txact-itm-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinTxactItm0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinTxactItm, UsrNodeFinTxactItmType, UsrNodeFinTxactItm0Service, UsrNodeFinTxactItm0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactItm.Service")
    public void setService(UsrNodeFinTxactItm0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactItm.Repo")
    public void setRepo(UsrNodeFinTxactItm0Repo repo) { this.repo = repo; }

}