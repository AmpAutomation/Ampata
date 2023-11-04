package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinBal0TypeService")
public class UsrNodeFinBal0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinBal0Type0Service() {
        this.typeOfNodeType = UsrNodeFinBalType.class;
    }
}