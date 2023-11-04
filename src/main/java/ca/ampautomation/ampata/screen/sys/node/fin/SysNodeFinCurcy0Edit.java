package ca.ampautomation.ampata.screen.sys.node.fin;

import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
import ca.ampautomation.ampata.repo.sys.node.fin.SysNodeFinCurcy0Repo;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcyType;
import ca.ampautomation.ampata.screen.sys.node.base.SysNodeBase0BaseEdit;
import ca.ampautomation.ampata.service.sys.node.fin.SysNodeFinCurcy0Service;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@UiController("enty_SysNodeFinCurcy.edit")
@UiDescriptor("sys-node-fin-curcy-0-edit.xml")
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