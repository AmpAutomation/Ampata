package ca.ampautomation.ampata.service.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasicType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeGenBasic0TypeService")
public class UsrNodeGenBasic0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeGenBasic0Type0Service() {
        this.typeOfNodeType = UsrNodeGenBasicType.class;
    }
}