package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcct;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmt;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinStmt0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinStmt0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ViewController("enty_UsrNodeFinStmt.lookup")
@ViewDescriptor("usr-node-fin-stmt-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinStmt0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinStmt, UsrNodeFinStmtType, UsrNodeFinStmt0Service, UsrNodeFinStmt0Repo, DataGrid<UsrNodeFinStmt>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmt.Service")
    public void setService(UsrNodeFinStmt0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmt.Repo")
    public void setRepo(UsrNodeFinStmt0Repo repo) { this.repo = repo; }


    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @ViewComponent
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1A_FinAcct1_Id;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;


    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts2_ElTs_GE;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts2_ElTs_LE;

    @ViewComponent
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtDebt;

    @ViewComponent
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtCred;

    @ViewComponent
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtNet;


    public PropertyFilter<Integer> getFilterConfig1A_SortIdx(){
        return filterConfig1A_SortIdx;
    }

    public PropertyFilter<UsrNodeFinAcct> getFilterConfig1A_FinAcct1_Id(){
        return filterConfig1A_FinAcct1_Id;
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
