package ca.ampautomation.ampata.screen.usr.item.gen;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTagType;
import ca.ampautomation.ampata.repo.usr.item.gen.UsrItemGenTag0Repo;
import ca.ampautomation.ampata.screen.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.gen.UsrItemGenTag0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemGenTag.main")
@UiDescriptor("usr-item-gen-tag-0-main.xml")
@LookupComponent("tableMain")
public class UsrItemGenTag0Main extends UsrItemBase0BaseMain<UsrItemGenTag, UsrItemGenTagType, UsrItemGenTag0Service, UsrItemGenTag0Repo, Table<UsrItemGenTag>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemGenTag.Service")
    public void setService(UsrItemGenTag0Service service) {
        this.service = service;
    }



}