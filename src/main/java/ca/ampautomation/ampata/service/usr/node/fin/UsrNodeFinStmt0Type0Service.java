package ca.ampautomation.ampata.service.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtType;
import ca.ampautomation.ampata.service.usr.node.base.UsrNodeBase0Type0Service;
import org.springframework.stereotype.Component;

@Component("bean_UsrNodeFinStmt0TypeService")
public class UsrNodeFinStmt0Type0Service extends UsrNodeBase0Type0Service {

    public UsrNodeFinStmt0Type0Service() {
        this.typeOfNodeType = UsrNodeFinStmtType.class;
    }
}