package ca.ampautomation.ampata.view.sys.node.base;

import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.repo.sys.node.base.SysNodeBase0Repo;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBaseType;
import ca.ampautomation.ampata.service.sys.node.base.SysNodeBase0Service;
import ca.ampautomation.ampata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Route(value = "sysNodeBases", layout = MainView.class)
@ViewController("enty_SysNodeBase.main")
@ViewDescriptor("sys-node-base-0-main.xml")
@LookupComponent("dataGridMain")
public class SysNodeBase0Main extends SysNodeBase0BaseMain<SysNodeBase, SysNodeBaseType, SysNodeBase0Service, SysNodeBase0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_SysNodeBase.Service")
    public void setService(SysNodeBase0Service service) { this.service = service; }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_SysNodeBase.Repo")
    public void setRepo(SysNodeBase0Repo repo) { this.repo = repo; }

}