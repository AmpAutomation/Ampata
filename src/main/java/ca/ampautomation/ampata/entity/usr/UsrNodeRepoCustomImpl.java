package ca.ampautomation.ampata.entity.usr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsrNodeRepoCustomImpl implements UsrNodeRepoCustom {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Integer execUsrNodePrUpdNative(){
        String logPrfx = "execNodePrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Node_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Node_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Node_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrGenTagPrUpdNative(){
        String logPrfx = "execGenTagPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Gen_Tag_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Gen_Tag_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Gen_Tag_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrGenAgentPrUpdNative(){
        String logPrfx = "execGenAgentPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Gen_Agent_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Gen_Agent_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Gen_Agent_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrGenChanPrUpdNative(){
        String logPrfx = "execGenChanPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Gen_Chan_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Gen_Chan_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Gen_Chan_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrGenDocVerPrUpdNative(){
        String logPrfx = "execGenDocVerPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Gen_Doc_Ver_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Gen_Doc_Ver_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Gen_Doc_Ver_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrGenFilePrUpdNative(){
        String logPrfx = "execGenFilePrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Gen_File_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Gen_File_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Gen_File_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinAcctPrUpdNative(){
        String logPrfx = "execFinAcctPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Acct_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Acct_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Acct_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinDeptPrUpdNative(){
        String logPrfx = "execFinDeptPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Dept_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Dept_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Dept_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactItmPrUpdNative(){
        String logPrfx = "execFinTxactItmPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txact_Itm_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txact_Itm_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txact_Itm_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactItmPrPurgeNative(){
        String logPrfx = "execFinTxactItmPrPurgeNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txact_Itm_Pr_Purge()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txact_Itm_Pr_Purge()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txact_Itm_Pr_Purge()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactItmPrDelOrphNative(){
        String logPrfx = "execFinTxactItmPrDelOrphNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txact_Itm_Pr_Del_Orph()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txact_Itm_Pr_Del_Orph()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txact_Itm_Pr_Del_Orph()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactPrUpdNative(){
        String logPrfx = "execFinTxactPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txact_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txact_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txact_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactPrPurgeNative(){
        String logPrfx = "execFinTxactPrPurgeNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txact_Pr_Purge()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txact_Pr_Purge()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txact_Pr_Purge()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactPrDelOrphNative(){
        String logPrfx = "execFinTxactPrDelOrphNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txact_Pr_Del_Orph()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txact_Pr_Del_Orph()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txact_Pr_Del_Orph()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }


    @Override
    @Transactional
    public Integer execUsrFinTxactSetPrUpdNative(){
        String logPrfx = "execFinTxactSetPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txset_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txset_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txset_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactSetPrPurgeNative(){
        String logPrfx = "execFinTxactSetPrPurgeNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txset_Pr_Purge()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txset_Pr_Purge()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txset_Pr_Purge()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinTxactSetPrDelOrphNative(){
        String logPrfx = "execFinTxactSetPrDelOrphNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Txset_Pr_Del_Orph()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Txset_Pr_Del_Orph()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Txset_Pr_Del_Orph()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }


    @Override
    @Transactional
    public Integer execUsrFinBalSetPrUpdNative(){
        String logPrfx = "execFinBalSetPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Bal_Set_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Bal_Set_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Bal_Set_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinBalPrUpdNative(){
        String logPrfx = "execFinBalPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Bal_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Bal_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Bal_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }


    @Override
    @Transactional
    public Integer execUsrFinStmtPrUpdNative(){
        String logPrfx = "execFinStmtPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Stmt_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Stmt_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Stmt_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Override
    @Transactional
    public Integer execUsrFinStmtItmPrUpdNative(){
        String logPrfx = "execFinStmtItmPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Stmt_Itm_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Stmt_Itm_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Stmt_Itm_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

}
