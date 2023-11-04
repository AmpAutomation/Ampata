package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinBalSet0TypeService")
public class UsrNodeFinBalSet0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinBalSet0Type0Service() {
        this.typeOfNodeType = UsrNodeFinBalSetType.class;
    }
}