package ca.ampautomation.ampata.entity.sys;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SysNodeRepository extends CrudRepository<SysNode, Long>, SysNodeRepositoryCustom {

    @Procedure(name = "SysNode.execNodePrUpd")
    void execNodePrUpd();

}
