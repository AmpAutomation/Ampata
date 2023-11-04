package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinAcct0Type0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0Type0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinAcct0Type0Service;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinAcctType.lookup")
@UiDescriptor("usr-node-fin-acct-0-type-0-lookup.xml")
@LookupComponent("tableMain")
public class UsrNodeFinAcct0Type0Lookup extends UsrNodeBase0Type0BaseLookup<UsrNodeFinAcctType, UsrNodeFinAcct0Type0Service, UsrNodeFinAcct0Type0Repo, TreeTable<UsrNodeFinAcctType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct0TypeService")
    public void setService(UsrNodeFinAcct0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinAcct0TypeRepo")
    public void setRepo(UsrNodeFinAcct0Type0Repo repo) { this.repo = repo; }



}