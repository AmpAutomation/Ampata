package ca.ampautomation.ampata.view.usr.item.base;

import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBaseType;
import ca.ampautomation.ampata.repo.usr.item.base.UsrItemBase0Type0Repo;
import ca.ampautomation.ampata.service.usr.item.base.UsrItemBase0Type0Service;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemBaseType.main")
@UiDescriptor("usr-base-item-type-0-main.xml")
@LookupComponent("tableMain")
public class UsrItemBase0Type0Main extends UsrItemBase0Type0BaseMain<UsrItemBaseType, UsrItemBase0Type0Service, UsrItemBase0Type0Repo, TreeTable<UsrItemBaseType>> {

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