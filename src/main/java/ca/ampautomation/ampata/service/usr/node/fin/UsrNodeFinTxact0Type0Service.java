package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinTxact0TypeService")
public class UsrNodeFinTxact0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinTxact0Type0Service() {
        this.typeOfNodeType = UsrNodeFinTxactType.class;
    }
}