package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.*;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenUnion0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenUnion0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeGenUnion.main")
@UiDescriptor("usr-node-gen-union-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeGenUnion0Main extends UsrNodeBase0BaseMain<UsrNodeGenUnion, UsrNodeGenUnionType, UsrNodeGenUnion0Service, UsrNodeGenUnion0Repo, Table<UsrNodeGenUnion>> {


    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenUnion.Service")
    public void setService(UsrNodeGenUnion0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenUnion.Repo")
    public void setRepo(UsrNodeGenUnion0Repo repo) { this.repo = repo; }


}