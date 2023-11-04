package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinAcct0TypeService")
public class UsrNodeFinAcct0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinAcct0Type0Service() {
        this.typeOfNodeType = UsrNodeFinAcctType.class;
    }

}