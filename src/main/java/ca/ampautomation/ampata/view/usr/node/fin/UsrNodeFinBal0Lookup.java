package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinBal0Repo;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinBal0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ViewController("enty_UsrNodeFinBal.lookup")
@ViewDescriptor("usr-node-fin-bal-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinBal0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinBal, UsrNodeFinBalType, UsrNodeFinBal0Service, UsrNodeFinBal0Repo, DataGrid<UsrNodeFinBal>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBal.Service")
    public void setService(UsrNodeFinBal0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinBal.Repo")
    public void setRepo(UsrNodeFinBal0Repo repo) { this.repo = repo; }


    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @Autowired
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1A_FinAcct1_Id;

    @Autowired
    protected PropertyFilter<UsrNodeFinDept> filterConfig1A_FinDept1_Id;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;


    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts2_ElTs_GE;

    @Autowired
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts2_ElTs_LE;

    @Autowired
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtDebt;

    @Autowired
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtCred;

    @Autowired
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtNet;


    public PropertyFilter<Integer> getFilterConfig1A_SortIdx(){
        return filterConfig1A_SortIdx;
    }

    public PropertyFilter<UsrNodeFinAcct> getFilterConfig1A_FinAcct1_Id(){
        return filterConfig1A_FinAcct1_Id;
    }

    public PropertyFilter<UsrNodeFinDept> getFilterConfig1A_FinDept1_Id(){
        return filterConfig1A_FinDept1_Id;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_GE(){
        return filterConfig1A_Ts1_ElTs_GE;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_LE(){
        return filterConfig1A_Ts1_ElTs_LE;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts2_ElTs_GE(){
        return filterConfig1A_Ts2_ElTs_GE;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts2_ElTs_LE(){
        return filterConfig1A_Ts2_ElTs_LE;
    }

    public PropertyFilter<BigDecimal> getFilterConfig1A_AmtDebt(){
        return filterConfig1A_AmtDebt;
    }

    public PropertyFilter<BigDecimal> getFilterConfig1A_AmtCred(){
        return filterConfig1A_AmtCred;
    }

    public PropertyFilter<BigDecimal> getFilterConfig1A_AmtNet(){
        return filterConfig1A_AmtNet;
    }


}
