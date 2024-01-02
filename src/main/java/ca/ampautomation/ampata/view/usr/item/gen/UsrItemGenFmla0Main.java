package ca.ampautomation.ampata.view.usr.item.gen;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmlaType;
import ca.ampautomation.ampata.repo.usr.item.gen.UsrItemGenFmla0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.gen.UsrItemGenFmla0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrItemGenFmlas", layout = MainView.class)
@ViewController("enty_UsrItemGenFmla.main")
@ViewDescriptor("usr-item-gen-fmla-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrItemGenFmla0Main extends UsrItemBase0BaseMain<UsrItemGenFmla, UsrItemGenFmlaType, UsrItemGenFmla0Service, UsrItemGenFmla0Repo, DataGrid<UsrItemGenFmla>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemGenFmla.Service")
    public void setService(UsrItemGenFmla0Service service) {
        this.service = service;
    }


}