package ca.ampautomation.ampata.view.usr.item.gen;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmlaType;
import ca.ampautomation.ampata.repo.usr.item.gen.UsrItemGenFmla0Repo;
import ca.ampautomation.ampata.view.usr.item.base.UsrItemBase0BaseMain;
import ca.ampautomation.ampata.service.usr.item.gen.UsrItemGenFmla0Service;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrItemGenFmla.main")
@UiDescriptor("usr-item-gen-fmla-0-main.xml")
@LookupComponent("tableMain")
public class UsrItemGenFmla0Main extends UsrItemBase0BaseMain<UsrItemGenFmla, UsrItemGenFmlaType, UsrItemGenFmla0Service, UsrItemGenFmla0Repo, Table<UsrItemGenFmla>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrItemGenFmla.Service")
    public void setService(UsrItemGenFmla0Service service) {
        this.service = service;
    }


}