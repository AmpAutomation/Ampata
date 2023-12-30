package ca.ampautomation.ampata.view.sys.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRate;
import ca.ampautomation.ampata.repo.sys.node.fin.SysNodeFinCurcyExchRate0Repo;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyExchRateType;
import ca.ampautomation.ampata.view.sys.node.base.SysNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.sys.node.fin.SysNodeFinCurcyExchRate0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_SysNodeFinCurcyExchRate.edit")
@UiDescriptor("sys-node-fin-curcy-exch-rate-0-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class SysNodeFinCurcyExchRate0Edit extends SysNodeBase0BaseEdit<SysNodeFinCurcyExchRate, SysNodeFinCurcyExchRateType, SysNodeFinCurcyExchRate0Service, SysNodeFinCurcyExchRate0Repo> {

    //Service
    @Override
    @Autowired
    @Qualifier("bean_SysNodeFinCurcyExchRate.Service")
    public void setService(SysNodeFinCurcyExchRate0Service service) {
        this.service = service;
    }

    //Repo
    @Override
    @Autowired
    @Qualifier("bean_SysNodeFinCurcyExchRate.Repo")
    public void setRepo(SysNodeFinCurcyExchRate0Repo repo) { this.repo = repo; }

}