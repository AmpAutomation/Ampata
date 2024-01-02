package ca.ampautomation.ampata.view.sys.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.repo.sys.node.fin.SysNodeFinCurcy0Repo;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyType;
import ca.ampautomation.ampata.view.main.MainView;
import ca.ampautomation.ampata.view.sys.node.base.SysNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.sys.node.fin.SysNodeFinCurcy0Service;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "sysNodeFinCurcys/id", layout = MainView.class)
@ViewController("enty_SysNodeFinCurcy.edit")
@ViewDescriptor("sys-node-fin-curcy-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class SysNodeFinCurcy0Edit extends SysNodeBase0BaseEdit<SysNodeFinCurcy, SysNodeFinCurcyType, SysNodeFinCurcy0Service, SysNodeFinCurcy0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_SysNodeFinCurcy.Service")
    public void setService(SysNodeFinCurcy0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_SysNodeFinCurcy.Repo")
    public void setRepo(SysNodeFinCurcy0Repo repo) { this.repo = repo; }


}