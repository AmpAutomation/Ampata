package ca.ampautomation.ampata.entity.sys;

import ca.ampautomation.ampata.screen.sys.fin.SysFinCurcyBrowse2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SysNodeRepositoryCustomImpl implements SysNodeRepositoryCustom {

    //Common
    Logger logger = LoggerFactory.getLogger(SysNodeRepositoryCustomImpl.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execNodePrUpdNative(){
        String logPrfx = "execNodePrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Sys_Node_Pr_Upd()");
        this.em.createNativeQuery("call Sys_Node_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Sys_Node_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinCurcyPrUpdNative(){
        String logPrfx = "execFinCurcyPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Sys_Fin_Curcy_Pr_Upd()");
        this.em.createNativeQuery("call Sys_Fin_Curcy_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Sys_Fin_Curcy_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinCurcyExchRatePrUpdNative(){
        String logPrfx = "execFinCurcyExchRatePrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Sys_Fin_Curcy_Exch_Rate_Pr_Upd()");
        this.em.createNativeQuery("call Sys_Fin_Curcy_Exch_Rate_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Sys_Fin_Curcy_Exch_Rate_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

}
