package ca.ampautomation.ampata.repo.usr.node.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFile;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenUnion;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeGenUnion.Repo")
public class UsrNodeGenUnion0Repo extends UsrNodeBase0Repo<UsrNodeGenUnion, UUID> {

    public UsrNodeGenUnion0Repo() {
        this.typeOfT = UsrNodeGenUnion.class;
    }

}
