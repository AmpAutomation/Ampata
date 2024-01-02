package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.*;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenUnion0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenUnion0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "UsrNodeGenUnions", layout = MainView.class)
@ViewController("enty_UsrNodeGenUnion.main")
@ViewDescriptor("usr-node-gen-union-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeGenUnion0Main extends UsrNodeBase0BaseMain<UsrNodeGenUnion, UsrNodeGenUnionType, UsrNodeGenUnion0Service, UsrNodeGenUnion0Repo, DataGrid<UsrNodeGenUnion>> {


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