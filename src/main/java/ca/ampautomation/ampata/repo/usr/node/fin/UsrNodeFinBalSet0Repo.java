package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBal;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSet;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinBalSet.Repo")
public class UsrNodeFinBalSet0Repo extends UsrNodeBase0Repo<UsrNodeFinBalSet, UUID> {

    public UsrNodeFinBalSet0Repo() {
        this.typeOfT = UsrNodeFinBalSet.class;
    }

}
