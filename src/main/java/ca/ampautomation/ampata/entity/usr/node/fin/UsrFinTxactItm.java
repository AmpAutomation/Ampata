package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.base.UsrNodeBase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;

@JmixEntity
@Entity(name = "enty_UsrFinTxactItm")
public class UsrFinTxactItm extends UsrNodeBase {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Boolean updateDesc1() {
        // Assume this is not null
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        String desc1_ = this.getDesc1();
        String thisAmt = "";
        if (this.getAmtDebt() != null) {
            thisAmt = thisAmt + " Debt " + this.getAmtDebt();
        }
        if (this.getAmtCred() != null) {
            thisAmt = thisAmt + " Cred " + this.getAmtCred();
        }
        if (this.getSysFinCurcy1_Id() != null) {
            thisAmt = thisAmt + " " + Objects.toString(this.getSysFinCurcy1_Id().getId2(), "");
        }
        if (!thisAmt.equals("")) {
            thisAmt = thisAmt.trim();
        }
        logger.debug(logPrfx + " --- thisAmt: " + thisAmt);

        String thisCurcy = "";
        logger.debug(logPrfx + " --- thisCurcy: " + thisCurcy);

        String thisStmtItm1_Desc = "";
        UsrNodeBase thisFinStmtItm = this.getFinStmtItm1_Id();
        //thisFinStmtItm = dataContext.merge(thisFinStmtItm);
        if (thisFinStmtItm != null) {
            String thisStmtItm1_Desc1 = Objects.toString(thisFinStmtItm.getDesc1(), "");
            String thisStmtItm1_Desc2 = Objects.toString(thisFinStmtItm.getDesc2(), "");
            List<String> list1 = Arrays.asList(
                    thisStmtItm1_Desc1
                    , thisStmtItm1_Desc2
            );
            thisStmtItm1_Desc = list1.stream().filter(StringUtils::isNotBlank)
                    .collect(Collectors.joining(" "));

        }
        if (!thisStmtItm1_Desc.equals("")) {
            thisStmtItm1_Desc = "for " + thisStmtItm1_Desc;
        }
        logger.debug(logPrfx + " --- thisStmtItm1_Desc: " + thisStmtItm1_Desc);

        String thisAcct = "";
        if (this.getFinAcct1_Id() != null) {
            thisAcct = Objects.toString(this.getFinAcct1_Id().getId2(), "");
        }
        if (!thisAcct.equals("")) {
            thisAcct = "to acct [" + thisAcct + "]";
        }
        logger.debug(logPrfx + " --- thisAcct: " + thisAcct);

        String thisDept = "";
        if (this.getFinDept1_Id() != null) {
            thisDept = Objects.toString(this.getFinDept1_Id().getId2(), "");
        }
        if (!thisDept.equals("")) {
            thisDept = "to dept [" + thisDept + "]";
        }
        logger.debug(logPrfx + " --- thisDept: " + thisDept);

        String thisTaxLne = "";
        if (this.getFinTaxLne1_Id() != null) {
            thisTaxLne = "[" + Objects.toString(this.getFinTaxLne1_Id().getId2(), "") + "]";
        }
        if (!thisTaxLne.equals("")) {
            thisTaxLne = "with tax line " + thisTaxLne;
        }
        logger.debug(logPrfx + " --- thisTaxLne: " + thisTaxLne);

        String thisChan = "";
        if (this.getGenChan1_Id() != null) {
            thisChan = Objects.toString(this.getGenChan1_Id().getId2(), "");
        }
        if (!thisChan.equals("")) {
            thisChan = "in chan [" + thisChan + "]";
        }
        logger.debug(logPrfx + " --- thisChan: " + thisChan);


        String thisHow = "";
        if (this.getFinHow1_Id() != null) {
            thisHow = Objects.toString(this.getFinHow1_Id().getId2(), "");
        }
        if (!thisHow.equals("")) {
            thisHow = "via " + thisHow;
        }
        logger.debug(logPrfx + " --- thisHow: " + thisHow);

        String thisWhat = Objects.toString(this.getWhatText1(), "");
        if (this.getFinWhat1_Id() != null) {
            thisWhat = thisWhat + " " + Objects.toString(this.getFinWhat1_Id().getId2());
            thisWhat = thisWhat.trim();
        }
        if (!thisWhat.equals("")) {
            thisWhat = "for " + thisWhat;
        }
        logger.debug(logPrfx + " --- thisWhat: " + thisWhat);

        String thisWhy = Objects.toString(this.getWhyText1(), "");
        if (this.getFinWhy1_Id() != null) {
            thisWhy = thisWhy + " " + Objects.toString(this.getFinWhy1_Id().getId2());
            thisWhy = thisWhy.trim();
        }
        if (!thisWhy.equals("")) {
            thisWhy = "for " + thisWhy;
        }
        logger.debug(logPrfx + " --- thisWhy: " + thisWhy);

        String thisDocVer = "";
        if (this.getGenDocVer1_Id() != null) {
            thisDocVer = Objects.toString(this.getGenDocVer1_Id().getId2());
        }
        if (!thisDocVer.equals("")) {
            thisDocVer = "doc ver " + thisDocVer;
        }
        logger.debug(logPrfx + " --- thisDocVer: " + thisDocVer);

        String thisTag = "";
        String thisTag1 = "";
        if (this.getGenTag1_Id() != null) {
            thisTag1 = Objects.toString(this.getGenTag1_Id().getId2());
        }
        String thisTag2 = "";
        if (this.getGenTag1_Id() != null) {
            thisTag2 = Objects.toString(this.getGenTag2_Id().getId2());
        }
        String thisTag3 = "";
        if (this.getGenTag1_Id() != null) {
            thisTag3 = Objects.toString(this.getGenTag3_Id().getId2());
        }
        String thisTag4 = "";
        if (this.getGenTag1_Id() != null) {
            thisTag4 = Objects.toString(this.getGenTag4_Id().getId2());
        }
        if (!(thisTag1 + thisTag2 + thisTag3 + thisTag4).equals("")) {
            thisTag = "tag [" + String.join(",", thisTag1, thisTag2, thisTag3, thisTag4) + "]";
        }
        logger.debug(logPrfx + " --- thisTag: " + thisTag);

        List<String> list = Arrays.asList(
                thisAmt
                , thisAcct
                , thisDept
                , thisStmtItm1_Desc
                , thisChan
                , thisWhat
                , thisWhy
                , thisHow
                , thisDocVer
                , thisTag);

        String desc1 = list.stream().filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(" "));

        if (!Objects.equals(desc1_, desc1)){
            this.setDesc1(desc1);
            logger.debug(logPrfx + " --- desc1: " + desc1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}
