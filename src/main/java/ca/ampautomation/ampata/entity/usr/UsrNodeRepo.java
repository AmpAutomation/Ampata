package ca.ampautomation.ampata.entity.usr;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UsrNodeRepo extends CrudRepository<UsrNode, Long>, UsrNodeRepoCustom {

    @Procedure(name = "UsrNode.execNodePrUpd")
    void execNodePrUpd();

    @Procedure(name = "UsrNode.execNodePrUpd2")
    void execNodePrUpd2(@Param("inParam1") String inParam1);

    @Procedure(name = "UsrNode.execNodePrUpd3")
    String execNodePrUpd3(@Param("inParam1") String inParam1);

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
