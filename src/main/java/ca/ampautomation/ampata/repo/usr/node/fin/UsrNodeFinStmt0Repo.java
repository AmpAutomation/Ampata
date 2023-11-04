package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinDept;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmt;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinStmt.Repo")
public class UsrNodeFinStmt0Repo extends UsrNodeBase0Repo<UsrNodeFinStmt, UUID> {

    public UsrNodeFinStmt0Repo() {
        this.typeOfT = UsrNodeFinStmt.class;
    }

}
