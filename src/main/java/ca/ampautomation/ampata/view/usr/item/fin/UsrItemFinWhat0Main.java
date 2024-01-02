package ca.ampautomation.ampata.view.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhatType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinWhat0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinWhat0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrItemFinWhats", layout = MainView.class)
@ViewController("enty_UsrItemFinWhat.main")
@ViewDescriptor("usr-item-fin-what-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrItemFinWhat0Main extends UsrItemBase0BaseMain<UsrItemFinWhat, UsrItemFinWhatType, UsrItemFinWhat0Service, UsrItemFinWhat0Repo, DataGrid<UsrItemFinWhat>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinWhat.Service")
    public void setService(UsrItemFinWhat0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinWhat.Repo")
    public void setRepo(UsrItemFinWhat0Repo repo) { this.repo = repo; }


}