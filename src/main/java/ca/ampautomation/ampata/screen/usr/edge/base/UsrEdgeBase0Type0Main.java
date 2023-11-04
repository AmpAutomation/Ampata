package ca.ampautomation.ampata.screen.usr.edge.base;

import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.repo.usr.edge.base.UsrEdgeBase0Type0Repo;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.edge.base.UsrEdgeBase0Type0Service;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Type0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrEdgeBaseType.main")
@UiDescriptor("usr-base-edge-type-0-main.xml")
@LookupComponent("tableMain")
public class UsrEdgeBase0Type0Main extends UsrEdgeBase0Type0BaseMain<UsrEdgeBaseType, UsrEdgeBase0Type0Service, UsrEdgeBase0Type0Repo, Table<UsrEdgeBaseType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrEdgeBaseType.Service")
    public void setService(UsrEdgeBase0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrEdgeBaseType.Repo")
    public void setRepo(UsrEdgeBase0Type0Repo repo) { this.repo = repo; }


}