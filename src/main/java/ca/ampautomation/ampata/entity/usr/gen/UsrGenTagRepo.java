package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrBaseQryMngr;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface UsrGenTagRepo extends CrudRepository<UsrGenTag, UUID> {

    @Procedure(name = "UsrNode.execNodePrUpd")
    void execNodePrUpd();

}
