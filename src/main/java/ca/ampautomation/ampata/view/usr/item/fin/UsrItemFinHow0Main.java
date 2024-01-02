package ca.ampautomation.ampata.view.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHowType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinHow0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinHow0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrItemFinHows", layout = MainView.class)
@ViewController("enty_UsrItemFinHow.main")
@ViewDescriptor("usr-item-fin-how-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrItemFinHow0Main extends UsrItemBase0BaseMain<UsrItemFinHow, UsrItemFinHowType, UsrItemFinHow0Service, UsrItemFinHow0Repo, DataGrid<UsrItemFinHow>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinHow.Service")
    public void setService(UsrItemFinHow0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinHow.Repo")
    public void setRepo(UsrItemFinHow0Repo repo) { this.repo = repo; }

}