package ca.ampautomation.ampata.entity.usr;

public interface UsrNodeRepoCustom {


    Integer execUsrNodePrUpdNative();

    Integer execUsrGenTagPrUpdNative();

    Integer execUsrGenAgentPrUpdNative();

    Integer execUsrGenChanPrUpdNative();

    Integer execUsrGenDocVerPrUpdNative();

    Integer execUsrGenFilePrUpdNative();

    Integer execUsrFinAcctPrUpdNative();

    Integer execUsrFinDeptPrUpdNative();


    Integer execUsrFinTxactItmPrUpdNative();

    Integer execUsrFinTxactItmPrPurgeNative();

    Integer execUsrFinTxactItmPrDelOrphNative();


    Integer execUsrFinTxactPrUpdNative();

    Integer execUsrFinTxactPrPurgeNative();

    Integer execUsrFinTxactPrDelOrphNative();


    Integer execUsrFinTxactSetPrUpdNative();

    Integer execUsrFinTxactSetPrPurgeNative();

    Integer execUsrFinTxactSetPrDelOrphNative();

    Integer execUsrFinBalSetPrUpdNative();

    Integer execUsrFinBalPrUpdNative();


    Integer execUsrFinStmtPrUpdNative();

    Integer execUsrFinStmtItmPrUpdNative();


}
