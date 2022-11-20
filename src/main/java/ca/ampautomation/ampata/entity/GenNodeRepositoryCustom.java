package ca.ampautomation.ampata.entity;

public interface GenNodeRepositoryCustom {
    void execGenNodePrUpdNative();

    void execFinAcctPrUpdNative();


    void execFinTxferPrUpdNative();

    void execFinTxferPrPurgeNative();

    void execFinTxferPrDelOrphNative();


    void execFinTxactPrUpdNative();

    void execFinTxactPrPurgeNative();

    void execFinTxactPrDelOrphNative();


    void execFinTxsetPrUpdNative();

    void execFinTxsetPrPurgeNative();

    void execFinTxsetPrDelOrphNative();


    void execFinStmtPrUpdNative();

    void execFinStmtItmPrUpdNative();


}
