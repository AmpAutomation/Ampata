package ca.ampautomation.ampata.screen.usr.item.fin;

import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHowType;
import ca.ampautomation.ampata.repo.usr.item.fin.UsrItemFinHow0Repo;
import ca.ampautomation.ampata.screen.usr.item.base.UsrItemBase0BaseEdit;
import ca.ampautomation.ampata.service.usr.item.fin.UsrItemFinHow0Service;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemFinHow.edit")
@UiDescriptor("usr-item-fin-how-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class UsrItemFinHow0Edit extends UsrItemBase0BaseEdit<UsrItemFinHow, UsrItemFinHowType, UsrItemFinHow0Service, UsrItemFinHow0Repo> {

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