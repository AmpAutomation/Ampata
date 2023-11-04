package ca.ampautomation.ampata.service.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFileType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeGenFile0TypeService")
public class UsrNodeGenFile0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeGenFile0Type0Service() {
        this.typeOfNodeType = UsrNodeGenFileType.class;
    }
}