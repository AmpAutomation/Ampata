package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcct;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinAcctType.Repo")
public class UsrNodeFinAcct0Type0Repo extends UsrNodeBase0Type0Repo<UsrNodeFinAcctType, UUID> {

    public UsrNodeFinAcct0Type0Repo() {
        this.typeOfT = UsrNodeFinAcctType.class;
    }

}
