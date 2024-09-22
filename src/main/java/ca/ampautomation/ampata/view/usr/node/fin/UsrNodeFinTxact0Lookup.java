package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxact;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxact0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxact0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;

@ViewController("enty_UsrNodeFinTxact.lookup")
@ViewDescriptor("usr-node-fin-txact-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinTxact0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinTxact, UsrNodeFinTxactType, UsrNodeFinTxact0Service, UsrNodeFinTxact0Repo, DataGrid<UsrNodeFinTxact>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxact.Service")
    public void setService(UsrNodeFinTxact0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxact.Repo")
    public void setRepo(UsrNodeFinTxact0Repo repo) { this.repo = repo; }

    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;

    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_Int1;

    public PropertyFilter<Integer> getFilterConfig1A_SortIdx(){
        return filterConfig1A_SortIdx;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_GE(){
        return filterConfig1A_Ts1_ElTs_GE;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_LE(){
        return filterConfig1A_Ts1_ElTs_LE;
    }

    public PropertyFilter<Integer> getFilterConfig1A_Int1(){
        return filterConfig1A_Int1;
    }
}
