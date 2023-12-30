package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBalSet0Type0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0Type0BaseEdit;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBalSet0Type0Service;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinBalSetType.edit")
@UiDescriptor("usr-node-fin-bal-set-0-type-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrNodeFinBalSetType0Edit extends UsrNodeBase0Type0BaseEdit<UsrNodeFinBalSetType, UsrNodeFinBalSet0Type0Service, UsrNodeFinBalSet0Type0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet0TypeService")
    public void setService(UsrNodeFinBalSet0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBalSet0TypeRepo")
    public void setRepo(UsrNodeFinBalSet0Type0Repo repo) { this.repo = repo; }


}