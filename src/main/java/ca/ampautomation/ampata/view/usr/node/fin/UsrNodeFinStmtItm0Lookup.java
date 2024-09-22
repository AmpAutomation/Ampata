package ca.ampautomation.ampata.view.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItm;
import ca.ampautomation.ampata.repo.usr.node.fin.UsrNodeFinStmtItm0Repo;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItmType;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseLookup;
import ca.ampautomation.ampata.service.usr.node.fin.UsrNodeFinStmtItm0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ViewController("enty_UsrNodeFinStmtItm.lookup")
@ViewDescriptor("usr-node-fin-stmt-itm-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeFinStmtItm0Lookup extends UsrNodeBase0BaseLookup<UsrNodeFinStmtItm, UsrNodeFinStmtItmType, UsrNodeFinStmtItm0Service, UsrNodeFinStmtItm0Repo, DataGrid<UsrNodeFinStmtItm>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmtItm.Service")
    public void setService(UsrNodeFinStmtItm0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeFinStmtItm.Repo")
    public void setRepo(UsrNodeFinStmtItm0Repo repo) { this.repo = repo; }


    @ViewComponent
    protected PropertyFilter<Integer> filterConfig1A_SortIdx;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_GE;

    @ViewComponent
    protected PropertyFilter<LocalDateTime> filterConfig1A_Ts1_ElTs_LE;

    @ViewComponent
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtDebt;

    @ViewComponent
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtCred;

    @ViewComponent
    protected PropertyFilter<BigDecimal> filterConfig1A_AmtNet;


    public PropertyFilter<Integer> getFilterConfig1A_SortIdx(){
        return filterConfig1A_SortIdx;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_GE(){
        return filterConfig1A_Ts1_ElTs_GE;
    }

    public PropertyFilter<LocalDateTime> getFilterConfig1A_Ts1_ElTs_LE(){
        return filterConfig1A_Ts1_ElTs_LE;
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
