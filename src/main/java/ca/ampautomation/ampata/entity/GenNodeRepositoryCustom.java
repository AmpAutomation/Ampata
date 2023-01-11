package ca.ampautomation.ampata.entity;

public interface GenNodeRepositoryCustom {


    void execGenNodePrUpdNative();

    void execGenChanPrUpdNative();

    void execFinAcctPrUpdNative();

    void execFinDeptPrUpdNative();

    void execFinCurcyPrUpdNative();


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
