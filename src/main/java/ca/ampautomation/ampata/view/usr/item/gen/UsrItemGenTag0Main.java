package ca.ampautomation.ampata.view.usr.item.gen;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTagType;
import ca.ampautomation.ampata.repo.usr.item.gen.UsrItemGenTag0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.gen.UsrItemGenTag0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrItemGenTags", layout = MainView.class)
@ViewController("enty_UsrItemGenTag.main")
@ViewDescriptor("usr-item-gen-tag-0-main.xml")
@LookupComponent("dataGridMain")
public class UsrItemGenTag0Main extends UsrItemBase0BaseMain<UsrItemGenTag, UsrItemGenTagType, UsrItemGenTag0Service, UsrItemGenTag0Repo, DataGrid<UsrItemGenTag>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemGenTag.Service")
    public void setService(UsrItemGenTag0Service service) {
        this.service = service;
    }



}