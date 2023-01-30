package ca.ampautomation.ampata.entity;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SysNodeRepository extends CrudRepository<SysNode, Long>, SysNodeRepositoryCustom {

    @Procedure(name = "SysNode.execSysNodePrUpd")
    void execSysNodePrUpd();

    @Procedure(name = "SysNode.execSysNodePrUpd2")
    void execSysNodePrUpd2(@Param("inParam1") String inParam1);

    @Procedure(name = "SysNode.execSysNodePrUpd3")
    String execSysNodePrUpd3(@Param("inParam1") String inParam1);

    @Procedure(name = "SysNode.execFinTxactItmPrUpd")
    void execFinTxactItmPrUpd();

    @Procedure(name = "SysNode.execFinTxactItmPrUpd2")
    void execFinTxactItmPrUpd2(@Param("inParam1") String inParam1);

    @Procedure(name = "SysNode.execFinBalPrUpd")
    void execFinBalPrUpd();

    @Procedure(name = "SysNode.execFinStmtPrUpd")
    void execFinStmtPrUpd();

    @Procedure(name = "SysNode.execFinStmtItmPrUpd")
    void execFinStmtItmPrUpd();

}
