package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSet;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinBalSetType.Repo")
public class UsrNodeFinBalSet0Type0Repo extends UsrNodeBase0Type0Repo<UsrNodeFinBalSetType, UUID> {

    public UsrNodeFinBalSet0Type0Repo() {
        this.typeOfT = UsrNodeFinBalSetType.class;
    }

}
