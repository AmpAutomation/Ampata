package ca.ampautomation.ampata.screen.sys.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.repo.sys.node.fin.SysNodeFinCurcy0Repo;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyType;
import ca.ampautomation.ampata.repo.usr.edge.gen.UsrEdgeGenBasic0Repo;
import ca.ampautomation.ampata.screen.sys.node.base.SysNodeBase0BaseMain;
import ca.ampautomation.ampata.service.sys.node.fin.SysNodeFinCurcy0Service;
import ca.ampautomation.ampata.service.usr.edge.gen.UsrEdgeGenBasic0Service;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_SysNodeFinCurcy.main")
@UiDescriptor("sys-node-fin-curcy-0-main.xml")
@LookupComponent("tableMain")
public class SysNodeFinCurcy0Main extends SysNodeBase0BaseMain<SysNodeFinCurcy, SysNodeFinCurcyType, SysNodeFinCurcy0Service, SysNodeFinCurcy0Repo> {

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