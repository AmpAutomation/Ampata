package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinDept;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinDept.Repo")
public class UsrNodeFinDept0Repo extends UsrNodeBase0Repo<UsrNodeFinDept, UUID> {

    public UsrNodeFinDept0Repo() {
        this.typeOfT = UsrNodeFinDept.class;
    }

}
