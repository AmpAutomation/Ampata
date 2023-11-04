package ca.ampautomation.ampata.screen.usr.edge.gen;

import ca.ampautomation.ampata.entity.usr.edge.gen.UsrEdgeGenBasic;
import ca.ampautomation.ampata.entity.usr.edge.gen.UsrEdgeGenBasicType;
import ca.ampautomation.ampata.repo.usr.edge.gen.UsrEdgeGenBasic0Repo;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinHow0Repo;
import ca.ampautomation.ampata.screen.usr.edge.base.UsrEdgeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.edge.gen.UsrEdgeGenBasic0Service;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinHow0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrEdgeGenBasic.main")
@UiDescriptor("usr-edge-gen-basic-0-main.xml")
@LookupComponent("tableMain")
public class UsrEdgeGenBasic0Main extends UsrEdgeBase0BaseMain<UsrEdgeGenBasic, UsrEdgeGenBasicType, UsrEdgeGenBasic0Service, UsrEdgeGenBasic0Repo, Table<UsrEdgeGenBasic>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinHow.Service")
    public void setService(UsrEdgeGenBasic0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinHow.Repo")
    public void setRepo(UsrEdgeGenBasic0Repo repo) { this.repo = repo; }

}

