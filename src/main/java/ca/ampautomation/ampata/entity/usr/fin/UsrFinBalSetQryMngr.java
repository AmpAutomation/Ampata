package ca.ampautomation.ampata.entity.usr.fin;

import ca.ampautomation.ampata.entity.usr.UsrBaseQryMngr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsrFinBalSetQryMngr implements UsrBaseQryMngr {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Integer execPrUpdAllCalcValsforAllRowsNative(){
        String logPrfx = "execPrUpdAllCalcValsforAllRowsNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Fin_Bal_Set_Pr_Upd_All_Calc_Vals_for_All_Rows()");
        rows_updated = this.em.createNativeQuery("call Usr_Fin_Bal_Set_Pr_Upd_All_Calc_Vals_for_All_Rows()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Fin_Bal_Set_Pr_Upd_All_Calc_Vals_for_All_Rows()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

}
