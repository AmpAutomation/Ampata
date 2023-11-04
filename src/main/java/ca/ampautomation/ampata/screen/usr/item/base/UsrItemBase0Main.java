package ca.ampautomation.ampata.screen.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Repo;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinHow0Repo;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Service;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinHow0Service;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemBase.main")
@UiDescriptor("usr-base-item-0-main.xml")
@LookupComponent("tableMain")
public class UsrItemBase0Main extends UsrItemBase0BaseMain<UsrItemBase, UsrItemBaseType, UsrItemBase0Service, UsrItemBase0Repo, TreeTable<UsrItemBase>> {

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