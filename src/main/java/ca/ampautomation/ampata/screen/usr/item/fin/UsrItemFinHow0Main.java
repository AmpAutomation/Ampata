package ca.ampautomation.ampata.screen.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHowType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinHow0Repo;
import ca.ampautomation.ampata.screen.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinHow0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemFinHow.main")
@UiDescriptor("usr-item-fin-how-0-main.xml")
@LookupComponent("tableMain")
public class UsrItemFinHow0Main extends UsrItemBase0BaseMain<UsrItemFinHow, UsrItemFinHowType, UsrItemFinHow0Service, UsrItemFinHow0Repo, Table<UsrItemFinHow>> {

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