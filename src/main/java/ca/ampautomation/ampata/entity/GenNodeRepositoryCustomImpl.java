package ca.ampautomation.ampata.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenNodeRepositoryCustomImpl implements GenNodeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execGenNodePrUpdNative(){
        this.em.createNativeQuery("call Gen_Node_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinTxferPrUpdNative(){
        this.em.createNativeQuery("call Fin_Txfer_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinTxactPrUpdNative(){
        this.em.createNativeQuery("call Fin_Txact_Pr_Upd()").executeUpdate();
    }

    @Override
    @Transactional
    public void execFinStmtPrUpdNative(){
        this.em.createNativeQuery("call Fin_Stmt_Pr_Upd()").executeUpdate();
    }

}
