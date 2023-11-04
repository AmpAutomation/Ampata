package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSet;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactItm0Repo;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinTxactSet.edit")
@UiDescriptor("usr-node-fin-txact-set-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinTxactSet0Edit extends UsrNodeBase0BaseEdit<UsrNodeFinTxactSet, UsrNodeFinTxactSetType, UsrNodeFinTxactSet0Service, UsrNodeFinTxactSet0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet.Service")
    public void setService(UsrNodeFinTxactSet0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet.Repo")
    public void setRepo(UsrNodeFinTxactSet0Repo repo) { this.repo = repo; }

}