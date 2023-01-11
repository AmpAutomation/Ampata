package ca.ampautomation.ampata.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FinRateRepositoryCustomImpl implements FinRateRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execFinRatePrUpdNative(){this.em.createNativeQuery("call Fin_Rate_Pr_Upd()").executeUpdate();
    }

}
