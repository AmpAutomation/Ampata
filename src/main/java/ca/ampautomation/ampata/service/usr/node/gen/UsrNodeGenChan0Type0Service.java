package ca.ampautomation.ampata.service.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChanType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeGenChan0TypeService")
public class UsrNodeGenChan0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeGenChan0Type0Service() {
        this.typeOfNodeType = UsrNodeGenChanType.class;
    }
}