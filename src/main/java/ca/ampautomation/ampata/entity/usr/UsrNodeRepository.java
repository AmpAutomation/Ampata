package ca.ampautomation.ampata.entity.usr;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UsrNodeRepository extends CrudRepository<UsrNode, Long>, UsrNodeRepositoryCustom {

    @Procedure(name = "UsrNode.execUsrNodePrUpd")
    void execUsrNodePrUpd();

    @Procedure(name = "UsrNode.execUsrNodePrUpd2")
    void execUsrNodePrUpd2(@Param("inParam1") String inParam1);

    @Procedure(name = "UsrNode.execUsrNodePrUpd3")
    String execUsrNodePrUpd3(@Param("inParam1") String inParam1);

    @Procedure(name = "UsrNode.execFinTxactItmPrUpd")
    void execFinTxactItmPrUpd();

    @Procedure(name = "UsrNode.execFinTxactItmPrUpd2")
    void execFinTxactItmPrUpd2(@Param("inParam1") String inParam1);

    @Procedure(name = "UsrNode.execFinBalPrUpd")
    void execFinBalPrUpd();

    @Procedure(name = "UsrNode.execFinStmtPrUpd")
    void execFinStmtPrUpd();

    @Procedure(name = "UsrNode.execFinStmtItmPrUpd")
    void execFinStmtItmPrUpd();

}
