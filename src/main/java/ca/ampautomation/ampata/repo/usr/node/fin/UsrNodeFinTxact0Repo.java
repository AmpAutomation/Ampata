package ca.ampautomation.ampata.repo.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinStmtItm;
import ca.ampautomation.ampata.entity.usr.node.fin.UsrNodeFinTxact;
import ca.ampautomation.ampata.repo.usr.node.base.UsrNodeBase0Repo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository("bean_UsrNodeFinTxact.Repo")
public class UsrNodeFinTxact0Repo extends UsrNodeBase0Repo<UsrNodeFinTxact, UUID> {

    public UsrNodeFinTxact0Repo() {
        this.typeOfT = UsrNodeFinTxact.class;
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
