package ca.ampautomation.ampata.view.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhatType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinWhat0Repo;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinWhat0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemFinWhat.main")
@UiDescriptor("usr-item-fin-what-0-main.xml")
@LookupComponent("tableMain")
public class UsrItemFinWhat0Main extends UsrItemBase0BaseMain<UsrItemFinWhat, UsrItemFinWhatType, UsrItemFinWhat0Service, UsrItemFinWhat0Repo, Table<UsrItemFinWhat>> {

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