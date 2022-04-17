package ca.ampautomation.ampata.entity;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedStoredProcedureQuery;
import java.util.List;

@Transactional
public interface GenNodeRepository extends CrudRepository<GenNode, Long>  {

    @Procedure(name = "GenNode.execGenNodePrUpd")
    void execGenNodePrUpd();

    @Procedure(name = "GenNode.execFinTxferPrUpd")
    void execFinTxferPrUpd();

}
