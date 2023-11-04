package ca.ampautomation.ampata.screen.usr.node.base;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeBaseType.main")
@UiDescriptor("usr-node-base-0-type-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeBase0Type0Main extends UsrNodeBase0Type0BaseMain<UsrNodeBaseType, UsrNodeBase0Type0Service, UsrNodeBase0Type0Repo, TreeTable<UsrNodeBaseType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase0TypeService")
    public void setService(UsrNodeBase0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase0Type.Repo")
    public void setRepo(UsrNodeBase0Type0Repo repo) { this.repo = repo; }

}