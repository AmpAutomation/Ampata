package ca.ampautomation.ampata.repo.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFile;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeGenFile.Repo")
public class UsrNodeGenFile0Repo extends UsrNodeBase0Repo<UsrNodeGenFile, UUID> {

    public UsrNodeGenFile0Repo() {
        this.typeOfT = UsrNodeGenFile.class;
    }

}
