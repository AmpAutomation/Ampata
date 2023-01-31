package ca.ampautomation.ampata.entity;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ExtCurcyExchRateRepository extends CrudRepository<FinRate, Long>, ExtCurcyExchRateRepositoryCustom {

    @Procedure(name = "SysNode.execSysNodePrUpd")
    void execSysNodePrUpd();


}
