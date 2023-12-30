package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcct;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmt;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinStmt0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinStmt0Service;
import io.jmix.ui.component.PropertyFilter;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UiController("enty_UsrNodeFinStmt.lookup")
@UiDescriptor("usr-node-fin-stmt-0-lookup.xml")
@LookupComponent("tableMain")
public class UsrNodeFinStmt0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinStmt, UsrNodeFinStmtType, UsrNodeFinStmt0Service, UsrNodeFinStmt0Repo, Table<UsrNodeFinStmt>> {

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


    @Autowired
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @Autowired
    protected PropertyFilter<UsrNodeFinAcct> filterConfig1A_FinAcct1_Id;

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
