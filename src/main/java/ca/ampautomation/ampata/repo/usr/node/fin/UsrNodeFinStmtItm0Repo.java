package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmt;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItm;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinStmtItm.Repo")
public class UsrNodeFinStmtItm0Repo extends UsrNodeBase0Repo<UsrNodeFinStmtItm, UUID> {

    public UsrNodeFinStmtItm0Repo() {
        this.typeOfT = UsrNodeFinStmtItm.class;
    }

}
