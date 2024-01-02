package ca.ampautomation.ampata.view.usr.node.base;

import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@ViewController("enty_UsrNodeBase.lookup")
@ViewDescriptor("usr-node-base-0-lookup.xml")
@LookupComponent("dataGridMain")
public class UsrNodeBase0Lookup extends UsrNodeBase0BaseLookup<UsrNodeBase, UsrNodeBaseType, UsrNodeBase0Service, UsrNodeBase0Repo, DataGrid<UsrNodeBase>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase.Service")
    public void setService(UsrNodeBase0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeBase.Repo")
    public void setRepo(UsrNodeBase0Repo repo) { this.repo = repo; }



}