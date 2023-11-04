package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Type0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0Type0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Type0Service;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinTxactSetType.edit")
@UiDescriptor("usr-node-fin-txact-set-0-type-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinTxactSetType0Edit extends UsrNodeBase0Type0BaseEdit<UsrNodeFinTxactSetType, UsrNodeFinTxactSet0Type0Service, UsrNodeFinTxactSet0Type0Repo> {

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