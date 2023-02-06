package ca.ampautomation.ampata.entity.usr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsrItemRepoCustomImpl implements UsrItemRepoCustom {

    //Common
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Integer execUsrItemPrUpdNative(){
        String logPrfx = "execItemPrUpdNative";
        logger.trace(logPrfx + " --> ");

        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call Usr_Item_Pr_Upd()");
        rows_updated = this.em.createNativeQuery("call Usr_Item_Pr_Upd()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call Usr_Item_Pr_Upd()");

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

}
