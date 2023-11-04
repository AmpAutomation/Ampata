package ca.ampautomation.ampata.repo.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinBalSetType;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeGenAgent.Repo")
public class UsrNodeGenAgent0Repo extends UsrNodeBase0Repo<UsrNodeGenAgent, UUID> {

    public UsrNodeGenAgent0Repo() {
        this.typeOfT = UsrNodeGenAgent.class;
    }

}
