package ca.ampautomation.ampata.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenNodeRepositoryCustomImpl implements GenNodeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execGenNodePrUpdNative(){this.em.createNativeQuery("call Gen_Node_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinAcctPrUpdNative(){this.em.createNativeQuery("call Fin_Acct_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinTxferPrUpdNative(){this.em.createNativeQuery("call Fin_Txfer_Pr_Upd()").executeUpdate();}

    @Override
    @Transactional
    public void execFinTxferPrPurgeNative(){this.em.createNativeQuery("call Fin_Txfer_Pr_Purge()").executeUpdate();}

    @Override
    @Transactional
    public void execFinTxferPrDelOrphNative(){this.em.createNativeQuery("call Fin_Txfer_Pr_Del_Orph()").executeUpdate();}


    @Override
    @Transactional
    public void execFinTxactPrUpdNative(){
        this.em.createNativeQuery("call Fin_Txact_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinTxactPrPurgeNative(){this.em.createNativeQuery("call Fin_Txact_Pr_Purge()").executeUpdate();}

    @Override
    @Transactional
    public void execFinTxactPrDelOrphNative(){this.em.createNativeQuery("call Fin_Txact_Pr_Del_Orph()").executeUpdate();}


    @Override
    @Transactional
    public void execFinTxsetPrUpdNative(){
        this.em.createNativeQuery("call Fin_Txset_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinTxsetPrPurgeNative(){this.em.createNativeQuery("call Fin_Txset_Pr_Purge()").executeUpdate();}

    @Override
    @Transactional
    public void execFinTxsetPrDelOrphNative(){this.em.createNativeQuery("call Fin_Txset_Pr_Del_Orph()").executeUpdate();}


    @Override
    @Transactional
    public void execFinStmtPrUpdNative(){
        this.em.createNativeQuery("call Fin_Stmt_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinStmtItmPrUpdNative(){
        this.em.createNativeQuery("call Fin_Stmt_Itm_Pr_Upd()").executeUpdate();
    }

}
