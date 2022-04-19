package ca.ampautomation.ampata.entity;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedStoredProcedureQuery;
import java.util.List;

@Transactional
public interface GenNodeRepository extends CrudRepository<GenNode, Long>, GenNodeRepositoryCustom  {

    @Procedure(name = "GenNode.execGenNodePrUpd")
    void execGenNodePrUpd();

    @Procedure(name = "GenNode.execGenNodePrUpd2")
    void execGenNodePrUpd2(@Param("inParam1") String inParam1);

    @Procedure(name = "GenNode.execFinTxferPrUpd")
    void execFinTxferPrUpd();

    @Procedure(name = "GenNode.execFinTxferPrUpd2")
    void execFinTxferPrUpd2(@Param("inParam1") String inParam1);

}
