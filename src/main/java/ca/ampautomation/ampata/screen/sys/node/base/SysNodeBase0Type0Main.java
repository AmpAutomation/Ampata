package ca.ampautomation.ampata.screen.sys.node.base;

import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBaseType;
import ca.ampautomation.ampata.repo.sys.node.base.SysNodeBase0Repo;
import ca.ampautomation.ampata.repo.sys.node.base.SysNodeBase0Type0Repo;
import ca.ampautomation.ampata.service.sys.node.base.SysNodeBase0Service;
import ca.ampautomation.ampata.service.sys.node.base.SysNodeBase0Type0Service;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_SysNodeBaseType.main")
@UiDescriptor("sys-node-base-type-0-main.xml")
@LookupComponent("tableMain")
public class SysNodeBase0Type0Main extends SysNodeBase0Type0BaseMain<SysNodeBaseType, SysNodeBase0Type0Service, SysNodeBase0Type0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_SysNodeBaseType.Service")
    public void setService(SysNodeBase0Type0Service service) { this.service = service; }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_SysNodeBaseType.Repo")
    public void setRepo(SysNodeBase0Type0Repo repo) { this.repo = repo; }


}