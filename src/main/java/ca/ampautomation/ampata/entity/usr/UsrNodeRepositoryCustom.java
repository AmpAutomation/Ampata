package ca.ampautomation.ampata.entity.usr;

public interface UsrNodeRepositoryCustom {


    void execNodePrUpdNative();

    void execGenChanPrUpdNative();

    void execFinAcctPrUpdNative();

    void execFinDeptPrUpdNative();


    void execFinTxactItmPrUpdNative();

    void execFinTxactItmPrPurgeNative();

    void execFinTxactItmPrDelOrphNative();


    void execFinTxactPrUpdNative();

    void execFinTxactPrPurgeNative();

    void execFinTxactPrDelOrphNative();


    void execFinTxsetPrUpdNative();

    void execFinTxsetPrPurgeNative();

    void execFinTxsetPrDelOrphNative();

    void execFinBalSetPrUpdNative();

    void execFinBalPrUpdNative();


    void execFinStmtPrUpdNative();

    void execFinStmtItmPrUpdNative();


}