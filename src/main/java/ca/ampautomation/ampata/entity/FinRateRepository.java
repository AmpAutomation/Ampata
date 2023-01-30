package ca.ampautomation.ampata.entity;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.NamedStoredProcedureQuery;
import java.util.List;


@Transactional
public interface FinRateRepository extends CrudRepository<FinRate, Long>, FinRateRepositoryCustom  {

    @Procedure(name = "SysNode.execSysNodePrUpd")
    void execSysNodePrUpd();


}
