package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactItmType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinTxactItm0TypeService")
public class UsrNodeFinTxactItm0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinTxactItm0Type0Service() {
        this.typeOfNodeType = UsrNodeFinTxactItmType.class;
    }
}