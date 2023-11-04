package ca.ampautomation.ampata.service.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgentType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeGenAgent0TypeService")
public class UsrNodeGenAgent0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeGenAgent0Type0Service() {
        this.typeOfNodeType = UsrNodeGenAgentType.class;
    }
}