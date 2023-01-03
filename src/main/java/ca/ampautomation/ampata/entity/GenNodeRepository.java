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

    @Procedure(name = "GenNode.execGenNodePrUpd3")
    String execGenNodePrUpd3(@Param("inParam1") String inParam1);

    @Procedure(name = "GenNode.execFinTxactItmPrUpd")
    void execFinTxactItmPrUpd();

    @Procedure(name = "GenNode.execFinTxactItmPrUpd2")
    void execFinTxactItmPrUpd2(@Param("inParam1") String inParam1);

    @Procedure(name = "GenNode.execFinBalPrUpd")
    void execFinBalPrUpd();

    @Procedure(name = "GenNode.execFinStmtPrUpd")
    void execFinStmtPrUpd();

    @Procedure(name = "GenNode.execFinStmtItmPrUpd")
    void execFinStmtItmPrUpd();

}
