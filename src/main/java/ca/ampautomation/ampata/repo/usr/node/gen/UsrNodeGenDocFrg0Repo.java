package ca.ampautomation.ampata.repo.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrg;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeGenDocFrg.Repo")
public class UsrNodeGenDocFrg0Repo extends UsrNodeBase0Repo<UsrNodeGenDocFrg, UUID> {

    public UsrNodeGenDocFrg0Repo() {
        this.typeOfT = UsrNodeGenDocFrg.class;
    }

}
