package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItmType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinStmtItm0TypeService")
public class UsrNodeFinStmtItm0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinStmtItm0Type0Service() {
        this.typeOfNodeType = UsrNodeFinStmtItmType.class;
    }
}