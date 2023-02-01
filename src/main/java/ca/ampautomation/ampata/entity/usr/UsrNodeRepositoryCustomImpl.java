package ca.ampautomation.ampata.entity.usr;

import ca.ampautomation.ampata.entity.sys.SysNodeRepositoryCustomImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsrNodeRepositoryCustomImpl implements UsrNodeRepositoryCustom {

    //Common
    Logger logger = LoggerFactory.getLogger(UsrNodeRepositoryCustomImpl.class);
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void execNodePrUpdNative(){
        String logPrfx = "execNodePrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Usr_Node_Pr_Upd()");
        this.em.createNativeQuery("call Usr_Node_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Node_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execGenChanPrUpdNative(){
        String logPrfx = "execGenChanPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Gen_Chan_Pr_Upd()");
        this.em.createNativeQuery("call Gen_Chan_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Gen_Chan_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinAcctPrUpdNative(){
        String logPrfx = "execFinAcctPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Acct_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Acct_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Acct_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinDeptPrUpdNative(){
        String logPrfx = "execFinDeptPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Dept_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Dept_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Dept_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxactItmPrUpdNative(){
        String logPrfx = "execFinTxactItmPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txact_Itm_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Txact_Itm_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txact_Itm_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxactItmPrPurgeNative(){
        String logPrfx = "execFinTxactItmPrPurgeNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txact_Itm_Pr_Purge()");
        this.em.createNativeQuery("call Fin_Txact_Itm_Pr_Purge()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txact_Itm_Pr_Purge()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxactItmPrDelOrphNative(){
        String logPrfx = "execFinTxactItmPrDelOrphNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txact_Itm_Pr_Del_Orph()");
        this.em.createNativeQuery("call Fin_Txact_Itm_Pr_Del_Orph()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txact_Itm_Pr_Del_Orph()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxactPrUpdNative(){
        String logPrfx = "execFinTxactPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txact_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Txact_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txact_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxactPrPurgeNative(){
        String logPrfx = "execFinTxactPrPurgeNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txact_Pr_Purge()");
        this.em.createNativeQuery("call Fin_Txact_Pr_Purge()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txact_Pr_Purge()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxactPrDelOrphNative(){
        String logPrfx = "execFinTxactPrDelOrphNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txact_Pr_Del_Orph()");
        this.em.createNativeQuery("call Fin_Txact_Pr_Del_Orph()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txact_Pr_Del_Orph()");
        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Transactional
    public void execFinTxsetPrUpdNative(){
        String logPrfx = "execFinTxsetPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txset_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Txset_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txset_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxsetPrPurgeNative(){
        String logPrfx = "execFinTxsetPrPurgeNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txset_Pr_Purge()");
        this.em.createNativeQuery("call Fin_Txset_Pr_Purge()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txset_Pr_Purge()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinTxsetPrDelOrphNative(){
        String logPrfx = "execFinTxsetPrDelOrphNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Txset_Pr_Del_Orph()");
        this.em.createNativeQuery("call Fin_Txset_Pr_Del_Orph()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Txset_Pr_Del_Orph()");
        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Transactional
    public void execFinBalSetPrUpdNative(){
        String logPrfx = "execFinBalSetPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Bal_Set_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Bal_Set_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Bal_Set_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinBalPrUpdNative(){
        String logPrfx = "execFinBalPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Bal_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Bal_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Bal_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }


    @Override
    @Transactional
    public void execFinStmtPrUpdNative(){
        String logPrfx = "execFinStmtPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Stmt_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Stmt_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Stmt_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

    @Override
    @Transactional
    public void execFinStmtItmPrUpdNative(){
        String logPrfx = "execFinStmtItmPrUpdNative";
        logger.trace(logPrfx + " --> ");
        logger.debug(logPrfx + " --- executing(db) call Fin_Stmt_Itm_Pr_Upd()");
        this.em.createNativeQuery("call Fin_Stmt_Itm_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Fin_Stmt_Itm_Pr_Upd()");
        logger.trace(logPrfx + " <-- ");
    }

}
