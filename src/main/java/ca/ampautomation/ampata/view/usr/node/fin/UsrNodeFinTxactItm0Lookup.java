package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinTxactItm0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinTxactItm0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;

@ViewController("enty_UsrNodeFinTxactItm.lookup")
@ViewDescriptor("usr-node-fin-txact-itm-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinTxactItm0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinTxactItm, UsrNodeFinTxactItmType, UsrNodeFinTxactItm0Service, UsrNodeFinTxactItm0Repo, DataGrid<UsrNodeFinTxactItm>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactItm.Service")
    public void setService(UsrNodeFinTxactItm0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinTxactItm.Repo")
    public void setRepo(UsrNodeFinTxactItm0Repo repo) { this.repo = repo; }

    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;

    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_Int1;

    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_Int2;


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

    public PropertyFilter<Integer> getFilterConfig1A_Int2(){
        return filterConfig1A_Int2;
    }
}
