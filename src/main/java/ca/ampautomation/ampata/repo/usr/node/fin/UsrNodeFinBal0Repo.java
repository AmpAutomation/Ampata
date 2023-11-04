package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinAcctType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBal;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinBal.Repo")
public class UsrNodeFinBal0Repo extends UsrNodeBase0Repo<UsrNodeFinBal, UUID> {

    public UsrNodeFinBal0Repo() {
        this.typeOfT = UsrNodeFinBal.class;
    }

}
