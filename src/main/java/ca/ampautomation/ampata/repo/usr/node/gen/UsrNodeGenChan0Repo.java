package ca.ampautomation.ampata.repo.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenBasic;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeGenChan.Repo")
public class UsrNodeGenChan0Repo extends UsrNodeBase0Repo<UsrNodeGenChan, UUID> {

    public UsrNodeGenChan0Repo() {
        this.typeOfT = UsrNodeGenChan.class;
    }

}
