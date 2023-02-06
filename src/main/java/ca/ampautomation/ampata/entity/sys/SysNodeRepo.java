package ca.ampautomation.ampata.entity.sys;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface SysNodeRepo extends CrudRepository<SysNode, Long>, SysNodeRepoCustom {

    @Procedure(name = "SysNode.execNodePrUpd")
    void execNodePrUpd();

}
