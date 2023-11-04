package ca.ampautomation.ampata.repo.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocFrg;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeGenDocVerUsrNodeFinBalSetType.Repo")
public class UsrNodeGenDocVer0Repo extends UsrNodeBase0Repo<UsrNodeGenDocVer, UUID> {

    public UsrNodeGenDocVer0Repo() {
        this.typeOfT = UsrNodeGenDocVer.class;
    }

}
