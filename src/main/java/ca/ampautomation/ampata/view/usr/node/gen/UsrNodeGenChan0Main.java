package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenChan0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChanType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenChan0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "UsrNodeGenChans", layout = MainView.class)
@ViewController("enty_UsrNodeGenChan.main")
@ViewDescriptor("usr-node-gen-chan-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrNodeGenChan0Main extends UsrNodeBase0BaseMain<UsrNodeGenChan, UsrNodeGenChanType, UsrNodeGenChan0Service, UsrNodeGenChan0Repo, DataGrid<UsrNodeGenChan>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenChan.Service")
    public void setService(UsrNodeGenChan0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenChan.Repo")
    public void setRepo(UsrNodeGenChan0Repo repo) { this.repo = repo; }

    
}