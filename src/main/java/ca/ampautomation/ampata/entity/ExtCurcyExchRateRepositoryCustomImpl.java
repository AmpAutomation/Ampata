package ca.ampautomation.ampata.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ExtCurcyExchRateRepositoryCustomImpl implements ExtCurcyExchRateRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execExtCurcyExchRatePrUpdNative(){this.em.createNativeQuery("call Ext_Curcy_Exch_Rate_Pr_Upd()").executeUpdate();
    }

}
