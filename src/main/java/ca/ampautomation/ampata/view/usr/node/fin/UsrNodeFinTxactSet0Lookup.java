package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSet;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Service;
import io.jmix.ui.component.PropertyFilter;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;

@UiController("enty_UsrNodeFinTxactSet.lookup")
@UiDescriptor("usr-node-fin-txact-set-0-lookup.xml")
@LookupComponent("tableMain")
public class UsrNodeFinTxactSet0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinTxactSet, UsrNodeFinTxactSetType, UsrNodeFinTxactSet0Service, UsrNodeFinTxactSet0Repo, Table<UsrNodeFinTxactSet>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet.Service")
    public void setService(UsrNodeFinTxactSet0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactSet.Repo")
    public void setRepo(UsrNodeFinTxactSet0Repo repo) { this.repo = repo; }

    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_GE(){
        return filterConfig1A_Ts1_ElTs_GE;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_LE(){
        return filterConfig1A_Ts1_ElTs_LE;
    }

}
