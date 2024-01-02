package ca.ampautomation.ampata.view.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasic;
import ca.ampautomation.ampata.repo.usr.node.gen.UsrNodeGenBasic0Repo;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasicType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.usr.node.base.UsrNodeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.node.gen.UsrNodeGenBasic0Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.*;
import io.jmix.flowui.view.*;

@Route(value = "UsrNodeGenBasics", layout = MainView.class)
@ViewController("enty_UsrNodeGenBasic.main")
@ViewDescriptor("usr-node-gen-basic-0-main.xml")
@LookupComponent("dataGridMain")
@DialogMode(width = "64em")
public class UsrNodeGenBasic0Main extends UsrNodeBase0BaseMain<UsrNodeGenBasic, UsrNodeGenBasicType, UsrNodeGenBasic0Service, UsrNodeGenBasic0Repo, DataGrid<UsrNodeGenBasic>> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenBasic.Service")
    public void setService(UsrNodeGenBasic0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_UsrNodeGenBasic.Repo")
    public void setRepo(UsrNodeGenBasic0Repo repo) { this.repo = repo; }



}