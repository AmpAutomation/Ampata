package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinDeptType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinDept0TypeService")
public class UsrNodeFinDept0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinDept0Type0Service() {
        this.typeOfNodeType = UsrNodeFinDeptType.class;
    }
}