package ca.ampautomation.ampata.entity.sys;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Transactional
public interface SysNodeRepo extends CrudRepository<SysNode, UUID>, SysNodeRepoCustom {

    @Procedure(name = "SysNode.execPrUpd")
    void execPrUpd();

}
