package ca.ampautomation.ampata.entity.sys;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SysNodeRepositoryCustomImpl implements SysNodeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execSysFinCurcyExchRatePrUpdNative(){this.em.createNativeQuery("call Sys_Curcy_Exch_Rate_Pr_Upd()").executeUpdate();
    }

}
