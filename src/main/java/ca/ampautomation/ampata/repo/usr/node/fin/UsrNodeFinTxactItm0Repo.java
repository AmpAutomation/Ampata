package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxact;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactItm;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinTxactItm.Repo")
public class UsrNodeFinTxactItm0Repo extends UsrNodeBase0Repo<UsrNodeFinTxactItm, UUID> {

    public UsrNodeFinTxactItm0Repo() {
        this.typeOfT = UsrNodeFinTxactItm.class;
    }


}
