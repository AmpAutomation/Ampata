package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcct;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinAcct.Repo")
public class UsrNodeFinAcct0Repo extends UsrNodeBase0Repo<UsrNodeFinAcct, UUID> {

    public UsrNodeFinAcct0Repo() {
        this.typeOfT = UsrNodeFinAcct.class;
    }

}
