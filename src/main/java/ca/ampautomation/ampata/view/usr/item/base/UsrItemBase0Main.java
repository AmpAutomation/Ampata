package ca.ampautomation.ampata.view.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Repo;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Service;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrItemBases", layout = MainView.class)
@ViewController("enty_UsrItemBase.main")
@ViewDescriptor("usr-base-item-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrItemBase0Main extends UsrItemBase0BaseMain<UsrItemBase, UsrItemBaseType, UsrItemBase0Service, UsrItemBase0Repo, TreeDataGrid<UsrItemBase>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemBase.Service")
    public void setService(UsrItemBase0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrItemBase.Repo")
    public void setRepo(UsrItemBase0Repo repo) { this.repo = repo; }


}