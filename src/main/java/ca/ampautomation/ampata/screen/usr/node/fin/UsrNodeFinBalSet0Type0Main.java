package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBalSet0Type0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0Type0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBalSet0Type0Service;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinBalSetType.main")
@UiDescriptor("usr-node-fin-bal-set-0-type-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinBalSet0Type0Main extends UsrNodeBase0Type0BaseMain<UsrNodeFinBalSetType, UsrNodeFinBalSet0Type0Service, UsrNodeFinBalSet0Type0Repo, TreeTable<UsrNodeFinBalSetType>> {

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