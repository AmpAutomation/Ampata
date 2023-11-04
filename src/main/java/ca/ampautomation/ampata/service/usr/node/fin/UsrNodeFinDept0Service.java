package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinDept;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinDept.Service")
public class UsrNodeFinDept0Service extends UsrNodeBase0Service {

    public UsrNodeFinDept0Service() {
        this.typeOfNode = UsrNodeFinDept.class;
    }

}