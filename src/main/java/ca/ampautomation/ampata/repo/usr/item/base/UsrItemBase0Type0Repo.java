package ca.ampautomation.ampata.repo.usr.item.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;

@Repository("bean_UsrItemBaseType.Repo")
public class UsrItemBase0Type0Repo<T, Id extends Serializable>
        implements UsrItemBase0Type0RepoIntfc<T, Id> {


    //Common
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<T> typeOfT;

    @SuppressWarnings("unchecked")
    public UsrItemBase0Type0Repo() {
/*
        this.typeOfT = (Class<T>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
*/
    }

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    @Override
    public Integer execPr_Upd_AllCalcVals_ForAllRows(){
        String logPrfx = "execPr_Upd_AllCalcVals_ForAllRows";
        logger.trace(logPrfx + " --> ");

        String sNodeClass = typeOfT.getSimpleName();
        String sProc = sNodeClass + "_Pr_Upd_AllCalcVals_ForAllRows";
        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call " + sProc);
        rows_updated = this.entityManager.createNativeQuery("call " + sProc + "()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call " + sProc);

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

    @Transactional
    @Override
    public Integer execPr_Del_ForDeletedRows() {
        String logPrfx = "execPrDel_ForDeletedRows_Native";
        logger.trace(logPrfx + " --> ");

        String sNodeClass = typeOfT.getSimpleName();
        String sProc = sNodeClass + "_Pr_Del_ForDeletedRows";
        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call " + sProc);
        rows_updated = this.entityManager.createNativeQuery("call " + sProc + "()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call " + sProc);

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

}