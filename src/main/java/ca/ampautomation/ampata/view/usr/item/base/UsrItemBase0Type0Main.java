package ca.ampautomation.ampata.view.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Type0Service;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.TreeDataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrItemBaseTypes", layout = MainView.class)
@ViewController("enty_UsrItemBaseType.main")
@ViewDescriptor("usr-base-item-type-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrItemBase0Type0Main extends UsrItemBase0Type0BaseMain<UsrItemBaseType, UsrItemBase0Type0Service, UsrItemBase0Type0Repo, TreeDataGrid<UsrItemBaseType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemBaseType.Service")
    public void setService(UsrItemBase0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrItemBaseType.Repo")
    public void setRepo(UsrItemBase0Type0Repo repo) { this.repo = repo; }

}