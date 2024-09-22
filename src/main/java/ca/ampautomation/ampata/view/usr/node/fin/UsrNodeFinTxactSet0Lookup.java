package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSet;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactSet0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactSet0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;

@ViewController("enty_UsrNodeFinTxactSet.lookup")
@ViewDescriptor("usr-node-fin-txact-set-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinTxactSet0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinTxactSet, UsrNodeFinTxactSetType, UsrNodeFinTxactSet0Service, UsrNodeFinTxactSet0Repo, DataGrid<UsrNodeFinTxactSet>> {

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

    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_GE(){
        return filterConfig1A_Ts1_ElTs_GE;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_LE(){
        return filterConfig1A_Ts1_ElTs_LE;
    }

}
