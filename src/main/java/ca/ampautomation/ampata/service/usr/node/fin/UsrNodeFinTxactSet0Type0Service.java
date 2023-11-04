package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinTxactSet0TypeService")
public class UsrNodeFinTxactSet0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinTxactSet0Type0Service() {
        this.typeOfNodeType = UsrNodeFinTxactSetType.class;
    }
}