package ca.ampautomation.ampata.view.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhyType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinWhy0Repo;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinWhy0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemFinWhy.main")
@UiDescriptor("usr-item-fin-why-0-main.xml")
@LookupComponent("tableMain")
public class UsrItemFinWhy0Main extends UsrItemBase0BaseMain<UsrItemFinWhy, UsrItemFinWhyType, UsrItemFinWhy0Service, UsrItemFinWhy0Repo, Table<UsrItemFinWhy>> {

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