package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSet;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSetType;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Type0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("bean_UsrNodeFinTxactSetType.Repo")
public class UsrNodeFinTxactSet0Type0Repo extends UsrNodeBase0Type0Repo<UsrNodeFinTxactSetType, UUID> {

    public UsrNodeFinTxactSet0Type0Repo() {
        this.typeOfT = UsrNodeFinTxactSetType.class;
    }


}
