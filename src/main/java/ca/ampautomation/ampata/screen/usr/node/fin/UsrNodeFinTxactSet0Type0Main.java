package ca.ampautomation.ampata.screen.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Repo;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Type0Repo;
import ca.ampautomation.ampata.screen.usr.node.base.UsrNodeBase0Type0BaseMain;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Type0Service;
import io.jmix.ui.component.TreeTable;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_UsrNodeFinTxactSetType.main")
@UiDescriptor("usr-node-fin-txact-set-0-type-0-main.xml")
@LookupComponent("tableMain")
public class UsrNodeFinTxactSet0Type0Main extends UsrNodeBase0Type0BaseMain<UsrNodeFinTxactSetType, UsrNodeFinTxactSet0Type0Service, UsrNodeFinTxactSet0Type0Repo, TreeTable<UsrNodeFinTxactSetType>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet0TypeService")
    public void setService(UsrNodeFinTxactSet0Type0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSetType.Repo")
    public void setRepo(UsrNodeFinTxactSet0Type0Repo repo) { this.repo = repo; }



}