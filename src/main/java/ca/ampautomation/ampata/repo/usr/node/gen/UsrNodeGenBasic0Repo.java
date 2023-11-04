package ca.ampautomation.ampata.repo.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasic;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeGenBasic.Repo")
public class UsrNodeGenBasic0Repo extends UsrNodeBase0Repo<UsrNodeGenBasic, UUID> {

    public UsrNodeGenBasic0Repo() {
        this.typeOfT = UsrNodeGenBasic.class;
    }

}
