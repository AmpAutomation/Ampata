package ca.ampautomation.ampata.view.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhyType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinWhy0Repo;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinWhy0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "usrItemFinWhys/:id", layout = MainView.class)
@ViewController("enty_UsrItemFinWhy.edit")
@ViewDescriptor("usr-item-fin-why-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrItemFinWhy0Edit extends UsrItemBase0BaseEdit<UsrItemFinWhy, UsrItemFinWhyType, UsrItemFinWhy0Service, UsrItemFinWhy0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinWhy.Service")
    public void setService(UsrItemFinWhy0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrItemFinWhy.Repo")
    public void setRepo(UsrItemFinWhy0Repo repo) { this.repo = repo; }

}