package ca.ampautomation.ampata.service.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenUnionType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeGenUnion0TypeService")
public class UsrNodeGenUnion0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeGenUnion0Type0Service() {
        this.typeOfNodeType = UsrNodeGenUnionType.class;
    }

}