package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactItm;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxactSet;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository("bean_UsrNodeFinTxactSet.Repo")
public class UsrNodeFinTxactSet0Repo extends UsrNodeBase0Repo<UsrNodeFinTxactSet, UUID> {

    public UsrNodeFinTxactSet0Repo() {
        this.typeOfT = UsrNodeFinTxactSet.class;
    }

    @Transactional
    public Integer execPr_Del_ForOrphanedRows() {
        String logPrfx = "execPr_Del_ForOrphanedRows";
        logger.trace(logPrfx + " --> ");

        String sNodeClass = typeOfT.getSimpleName();
        String sProc = sNodeClass + "_Pr_Del_ForOrphanedRows";
        int rows_updated;
        logger.debug(logPrfx + " --- executing(db) call " + sProc);
        rows_updated = this.entityManager.createNativeQuery("call " + sProc + "()").executeUpdate();
        logger.debug(logPrfx + " --- finished(db) call " + sProc);

        logger.trace(logPrfx + " <-- ");
        return rows_updated;
    }

}
