package ca.ampautomation.ampata.view.sys.node.base;

import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.repo.sys.node.base.SysNodeBase0Repo;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBaseType;
import ca.ampautomation.ampata.service.sys.node.base.SysNodeBase0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_SysNodeBase.main")
@UiDescriptor("sys-node-base-0-main.xml")
@LookupComponent("tableMain")
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