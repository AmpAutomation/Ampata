package ca.ampautomation.ampata.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "AMPATA_GEN_NODE", indexes = {
        @Index(name = "IDX_GENNODE_GEN_NODE_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_GENNODE_GEN_NODE1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_GENNODE_GEN_PAT1__ID", columnList = "NAME1_PAT1__ID"),
        @Index(name = "IDX_GENNODE_GEN_CHAN1__ID", columnList = "GEN_CHAN1__ID"),
        @Index(name = "IDX_GENNODE_FIN_HOW1__ID", columnList = "FIN_HOW1__ID"),
        @Index(name = "IDX_GENNODE_FIN_WHAT1__ID", columnList = "FIN_WHAT1__ID"),
        @Index(name = "IDX_GENNODE_FIN_WHY1__ID", columnList = "FIN_WHY1__ID"),
        @Index(name = "IDX_GENNODE_FIN_TXACT1__ID", columnList = "FIN_TXACT1__ID"),
        @Index(name = "IDX_GENNODE_FIN_TXSET1__ID", columnList = "FIN_TXSET1__ID"),
        @Index(name = "IDX_GENNODE_FIN_ACCT1__ID", columnList = "FIN_ACCT1__ID"),
        @Index(name = "IDX_GENNODE_FIN_DEPT1__ID", columnList = "FIN_DEPT1__ID"),
        @Index(name = "IDX_GENNODE_FIN_STMT1__ID", columnList = "FIN_STMT1__ID"),
        @Index(name = "IDX_GENNODE_FIN_TAX1__ID", columnList = "FIN_TAX_LNE1__ID"),
        @Index(name = "IDX_GENNODE_FIN_CURCY1__ID", columnList = "FIN_CURCY1__ID"),
        @Index(name = "IDX_GENNODE_FIN_FMLA1__ID", columnList = "FIN_FMLA1__ID"),
        @Index(name = "IDX_GENNODE_FIN_TXFER1__ID", columnList = "FIN_TXFER1__ID"),
        @Index(name = "IDX_GENNODE_GEN_DOC1__ID", columnList = "GEN_DOC_VER1__ID"),
        @Index(name = "IDX_GENNODE_GEN_FILE1__ID", columnList = "GEN_FILE1__ID"),
        @Index(name = "IDX_GENNODE_GEN_TAG1__ID", columnList = "GEN_TAG1__ID"),
        @Index(name = "IDX_GENNODE_GEN_TAG2__ID", columnList = "GEN_TAG2__ID"),
        @Index(name = "IDX_GENNODE_GEN_TAG3__ID", columnList = "GEN_TAG3__ID"),
        @Index(name = "IDX_GENNODE_GEN_TAG4__ID", columnList = "GEN_TAG4__ID"),
        @Index(name = "IDX_GENNODE_GEN_AGENT1__ID", columnList = "GEN_AGENT1__ID"),
        @Index(name = "IDX_GENNODE_GEN_AGENT2__ID", columnList = "GEN_AGENT2__ID")
})
@Entity(name = "ampata_GenNode")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "DType", discriminatorType = DiscriminatorType.STRING)
@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(name = "GenNode.execGenNodePrUpd",
                procedureName = "Gen_Node_Pr_Upd", parameters = {

        }),

        @NamedStoredProcedureQuery(name = "GenNode.execGenNodePrUpd2",
                procedureName = "Gen_Node_Pr_Upd2"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
        }),

        @NamedStoredProcedureQuery(name = "GenNode.execGenNodePrUpd3",
                procedureName = "Gen_Node_Pr_Upd3"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
                , @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = String.class)
        }),

        @NamedStoredProcedureQuery(name = "GenNode.execFinTxferPrUpd",
                procedureName = "Fin_Txfer_Pr_Upd", parameters = {

        }),

        @NamedStoredProcedureQuery(name = "GenNode.execFinTxferPrUpd2",
                procedureName = "Fin_Txfer_Pr_Upd2"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
        }),

})

public class GenNode {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "ID2")
    private String id2;

    @Column(name = "ID2_CALC")
    private String id2Calc;

    @Column(name = "ID2_CMP")
    private Boolean id2Cmp;

    @Column(name = "ID2_DUP")
    private Integer id2Dup;

    @Column(name = "CLASS_NAME")
    private String className;

    @JoinColumn(name = "PARENT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode parent1_Id;

    @Column(name = "PARENT1__ID2")
    private String parent1_Id2;

    @Column(name = "ANCESTORS1__ID2")
    @Lob
    private String ancestors1_Id2;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNodeType type1_Id;

    @Column(name = "TYPE1__ID2")
    private String type1_Id2;

    @Column(name = "INST")
    private String inst;

    @Column(name = "NAME1")
    private String name1;

    @JoinColumn(name = "NAME1_PAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenPat name1Pat1_Id;

    @Column(name = "NAME1_PAT1__ID2")
    private String name1Pat1_Id2;

    @Column(name = "NAME2")
    private String name2;

    @Column(name = "ABRV", length = 16)
    private String abrv;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESC1")
    @Lob
    private String desc1;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "NOTE")
    @Lob
    private String note;

    @Lob
    @Column(name = "GEN_DOC_VERS1__ID2")
    private String genDocVers1_Id2;

    @JoinColumn(name = "GEN_DOC_VER1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genDocVer1_Id;

    @Column(name = "GEN_DOC_VER1__ID2")
    private String genDocVer1_Id2;

    @Column(name = "GEN_DOC_VER1__CODE")
    private String genDocVer1_Code;

    @Column(name = "GEN_DOC_VER1__VER")
    private String genDocVer1_Ver;

    @Column(name = "GEN_DOC_VER1__NAME1")
    private String genDocVer1_Name1;

    @JoinColumn(name = "GEN_FILE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genFile1_Id;

    @Column(name = "GEN_FILE1__ID2")
    private String genFile1_Id2;

    @Lob
    @Column(name = "GEN_FILE1__URI")
    private URI genFile1_URI;

    @Column(name = "GEN_TAGS1__ID2")
    private String genTags1_Id2;

    @JoinColumn(name = "GEN_TAG1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genTag1_Id;

    @Column(name = "GEN_TAG1__ID2")
    private String genTag1_Id2;

    @JoinColumn(name = "GEN_TAG2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genTag2_Id;

    @Column(name = "GEN_TAG2__ID2")
    private String genTag2_Id2;

    @JoinColumn(name = "GEN_TAG3__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genTag3_Id;

    @Column(name = "GEN_TAG3__ID2")
    private String genTag3_Id2;

    @JoinColumn(name = "GEN_TAG4__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genTag4_Id;

    @Column(name = "GEN_TAG4__ID2")
    private String genTag4_Id2;


    @Column(name = "NAME_PRFX")
    private String namePrfx;

    @Column(name = "NAME_FRST")
    private String nameFrst;

    @Column(name = "NAME_MIDL")
    private String nameMidl;

    @Column(name = "NAME_LAST")
    private String nameLast;

    @Column(name = "NAME_SUFX")
    private String nameSufx;

    @JoinColumn(name = "GEN_AGENT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genAgent1_Id;

    @Column(name = "GEN_AGENT1__ID2")
    private String genAgent1_Id2;

    @JoinColumn(name = "GEN_AGENT2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genAgent2_Id;

    @Column(name = "GEN_AGENT2__ID2")
    private String genAgent2_Id2;

    @AttributeOverrides({
            @AttributeOverride(name = "ts1", column = @Column(name = "BEG1_TS1")),
            @AttributeOverride(name = "date1", column = @Column(name = "BEG1_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "BEG1_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "BEG1_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "BEG1_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "BEG1_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "BEG1_DATE1_DAY")),
            @AttributeOverride(name = "time1", column = @Column(name = "BEG1_TIME1")),
            @AttributeOverride(name = "time1Hr", column = @Column(name = "BEG1_TIME1_HR")),
            @AttributeOverride(name = "time1Min", column = @Column(name = "BEG1_TIME1_MIN"))
    })
    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    private HasTmst beg1;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ts1", column = @Column(name = "BEG2_TS1")),
            @AttributeOverride(name = "date1", column = @Column(name = "BEG2_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "BEG2_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "BEG2_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "BEG2_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "BEG2_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "BEG2_DATE1_DAY")),
            @AttributeOverride(name = "time1", column = @Column(name = "BEG2_TIME1")),
            @AttributeOverride(name = "time1Hr", column = @Column(name = "BEG2_TIME1_HR")),
            @AttributeOverride(name = "time1Min", column = @Column(name = "BEG2_TIME1_MIN"))
    })
    private HasTmst beg2;

    @AttributeOverrides({
            @AttributeOverride(name = "ts1", column = @Column(name = "END1_TS1")),
            @AttributeOverride(name = "date1", column = @Column(name = "END1_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "END1_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "END1_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "END1_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "END1_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "END1_DATE1_DAY")),
            @AttributeOverride(name = "time1", column = @Column(name = "END1_TIME1")),
            @AttributeOverride(name = "time1Hr", column = @Column(name = "END1_TIME1_HR")),
            @AttributeOverride(name = "time1Min", column = @Column(name = "END1_TIME1_MIN"))
    })
    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    private HasTmst end1;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ts1", column = @Column(name = "END2_TS1")),
            @AttributeOverride(name = "date1", column = @Column(name = "END2_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "END2_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "END2_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "END2_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "END2_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "END2_DATE1_DAY")),
            @AttributeOverride(name = "time1", column = @Column(name = "END2_TIME1")),
            @AttributeOverride(name = "time1Hr", column = @Column(name = "END2_TIME1_HR")),
            @AttributeOverride(name = "time1Min", column = @Column(name = "END2_TIME1_MIN"))
    })
    private HasTmst end2;

    @Column(name = "BEG_BAL", precision = 19, scale = 2)
    private BigDecimal begBal;

    @Column(name = "END_BAL", precision = 19, scale = 2)
    private BigDecimal endBal;

    @Column(name = "DEBT_SUM", precision = 19, scale = 2)
    private BigDecimal debtSum;

    @Column(name = "CRED_SUM", precision = 19, scale = 2)
    private BigDecimal credSum;

    @Column(name = "NET_SUM", precision = 19, scale = 2)
    private BigDecimal netSum;


    @Column(name = "VER")
    private String ver;


    @JoinColumn(name = "GEN_CHAN1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode genChan1_Id;

    @Column(name = "GEN_CHAN1__ID2")
    private String genChan1_Id2;


    @Lob
    @Column(name = "FIN_TXSET1__FIN_TXACTS1__ID2")
    private String finTxset1_FinTxacts1_Id2;

    @Lob
    @Column(name = "FIN_TXSET1__FIN_ACCTS1__ID2")
    private String finTxset1_FinAccts1_Id2;

    @JoinColumn(name = "FIN_TXSET1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finTxset1_Id;

    @Column(name = "FIN_TXSET1__ID2")
    private String finTxset1_Id2;

    @Column(name = "FIN_TXSET1__ID2_CALC")
    private String finTxset1_Id2Calc;

    @Column(name = "FIN_TXSET1__EI1__ROLE")
    private String finTxset1_EI1_Role;

    @Column(name = "FIN_TXSET1__TYPE1__ID2")
    private String finTxset1_Type1_Id2;


    @Column(name = "FIN_TXSET1__GEN_CHAN1__ID2")
    private String finTxset1_GenChan1_Id2;

    @Column(name = "FIN_TXSET1__BEG1_DATE1")
    private LocalDate finTxset1_Beg1Date1;

    @Column(name = "FIN_TXSET1__BEG1_TIME1")
    private LocalTime finTxset1_Beg1Time1;


    @Column(name = "FIN_TXSET1__HOW1__ID2")
    private String finTxset1_How1_Id2;


    @Column(name = "FIN_TXSET1__WHAT_TEXT1")
    private String finTxset1_WhatText1;

    @Column(name = "FIN_TXSET1__WHAT1__ID2")
    private String finTxset1_What1_Id2;


    @Column(name = "FIN_TXSET1__WHY_TEXT1")
    private String finTxset1_WhyText1;

    @Column(name = "FIN_TXSET1__WHY1__ID2")
    private String finTxset1_Why1_Id2;


    @Lob
    @Column(name = "FIN_TXACT1__FIN_TXFERS1__ID2")
    private String finTxact1_FinTxfers1_Id2;

    @Lob
    @Column(name = "FIN_TXACT1__FIN_ACCTS1__ID2")
    private String finTxact1_FinAccts1_Id2;

    @JoinColumn(name = "FIN_TXACT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finTxact1_Id;

    @Column(name = "FIN_TXACT1__ID2")
    private String finTxact1_Id2;

    @Column(name = "FIN_TXACT1__ID2_CALC")
    private String finTxact1_Id2Calc;

    @Column(name = "FIN_TXACT1__EI1__ROLE")
    private String finTxact1_EI1_Role;

    @Column(name = "FIN_TXACT1__TYPE1__ID2")
    private String finTxact1_Type1_Id2;

    @Column(name = "FIN_TXACT1__GEN_CHAN1__ID2")
    private String finTxact1_GenChan1_Id2;

    @Column(name = "FIN_TXACT1__BEG1_DATE1")
    private LocalDate finTxact1_Beg1Date1;

    @Column(name = "FIN_TXACT1__BEG1_TIME1")
    private LocalTime finTxact1_Beg1Time1;

    @Column(name = "FIN_TXACT1__HOW1__ID2")
    private String finTxact1_How1_Id2;


    @Column(name = "FIN_TXACT1__WHAT_TEXT1")
    private String finTxact1_WhatText1;

    @Column(name = "FIN_TXACT1__WHAT1__ID2")
    private String finTxact1_What1_Id2;


    @Column(name = "FIN_TXACT1__WHY_TEXT1")
    private String finTxact1_WhyText1;

    @Column(name = "FIN_TXACT1__WHY1__ID2")
    private String finTxact1_Why1_Id2;


    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ts1", column = @Column(name = "ID_TS_TS1")),
            @AttributeOverride(name = "date1", column = @Column(name = "ID_TS_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "ID_TS_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "ID_TS_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "ID_TS_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "ID_TS_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "ID_TS_DATE1_DAY")),
            @AttributeOverride(name = "time1", column = @Column(name = "ID_TS_TIME1")),
            @AttributeOverride(name = "time1Hr", column = @Column(name = "ID_TS_TIME1_HR")),
            @AttributeOverride(name = "time1Min", column = @Column(name = "ID_TS_TIME1_MIN"))
    })
    private HasTmst idTs;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "date1", column = @Column(name = "ID_DT_DATE1")),
            @AttributeOverride(name = "date1Yr", column = @Column(name = "ID_DT_DATE1_YR")),
            @AttributeOverride(name = "date1Qtr", column = @Column(name = "ID_DT_DATE1_QTR")),
            @AttributeOverride(name = "date1Mon", column = @Column(name = "ID_DT_DATE1_MON")),
            @AttributeOverride(name = "date1Mon2", column = @Column(name = "ID_DT_DATE1_MON2")),
            @AttributeOverride(name = "date1Day", column = @Column(name = "ID_DT_DATE1_DAY"))
    })
    private HasDate idDt;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "time1", column = @Column(name = "ID_TM_TIME1")),
            @AttributeOverride(name = "time1Hr", column = @Column(name = "ID_TM_TIME1_HR")),
            @AttributeOverride(name = "time1Min", column = @Column(name = "ID_TM_TIME1_MIN"))
    })
    private HasTime idTm;

    @Column(name = "ID_X")
    private Integer idX;

    @Column(name = "ID_Y")
    private Integer idY;

    @Column(name = "ID_Z")
    @JmixProperty
    private Integer idZ;

    @JoinColumn(name = "FIN_STMT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finStmt1_Id;

    @Column(name = "FIN_STMT1__ID2")
    private String finStmt1_Id2;

    @Column(name = "FIN_STMT_ITM1__DESC1")
    private String finStmtItm1_Desc1;

    @Column(name = "FIN_STMT_ITM1__DESC2")
    private String finStmtItm1_Desc2;

    @Column(name = "FIN_STMT_ITM1__DESC3")
    private String finStmtItm1_Desc3;

    @Column(name = "FIN_STMT_ITM1__DESC_AMT")
    private String finStmtItm1_DescAmt;

    @Column(name = "FIN_STMT_ITM1__REF_ID")
    private String finStmtItm1_RefId;

    @JoinColumn(name = "FIN_ACCT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finAcct1_Id;

    @Column(name = "FIN_ACCT1__ID2")
    private String finAcct1_Id2;

    @Column(name = "FIN_ACCT1__TYPE1__ID2")
    private String finAcct1_Type1_Id2;

    @JoinColumn(name = "FIN_DEPT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finDept1_Id;

    @Column(name = "FIN_DEPT1__ID2")
    private String finDept1_Id2;

    @JoinColumn(name = "FIN_TAX_LNE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finTaxLne1_Id;

    @Column(name = "FIN_TAX_LNE1__ID2")
    private String finTaxLne1_Id2;

    @Column(name = "FIN_TAX_LNE1__CODE")
    private String finTaxLne1_Code;

    @Column(name = "FIN_TAX_LNE1__TYPE1__ID2")
    private String finTaxLne1_Type1_Id2;

    @JoinColumn(name = "FIN_HOW1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FinHow finHow1_Id;

    @Column(name = "FIN_HOW1__ID2")
    private String finHow1_Id2;

    @Column(name = "WHAT_TEXT1")
    private String whatText1;

    @JoinColumn(name = "FIN_WHAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FinWhat finWhat1_Id;

    @Column(name = "FIN_WHAT1__ID2")
    private String finWhat1_Id2;

    @Column(name = "WHY_TEXT1")
    private String whyText1;

    @JoinColumn(name = "FIN_WHY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FinWhy finWhy1_Id;

    @Column(name = "FIN_WHY1__ID2")
    private String finWhy1_Id2;

    @Column(name = "AMT_DEBT", precision = 19, scale = 2)
    private BigDecimal amtDebt;

    @Column(name = "AMT_CRED", precision = 19, scale = 2)
    private BigDecimal amtCred;

    @Column(name = "AMT_NET", precision = 19, scale = 2)
    private BigDecimal amtNet;

    @JoinColumn(name = "FIN_CURCY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finCurcy1_Id;

    @Column(name = "FIN_CURCY1__ID2")
    private String finCurcy1_Id2;

    @JoinColumn(name = "FIN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private FinFmla finFmla1_Id;

    @Column(name = "FIN_FMLA1__ID2")
    private String finFmla1_Id2;

    @JoinColumn(name = "FIN_TXFER1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenNode finTxfer1_Id;

    @Column(name = "FIN_TXFER1__ID2")
    private String finTxfer1_Id2;

    @Column(name = "FIN_TXFER1__EI1__RATE", precision = 19, scale = 9)
    private BigDecimal finTxfer1_EI1_Rate;

    @Column(name = "CALC_RSLT", precision = 19, scale = 2)
    private BigDecimal calcRslt;

    @Lob
    @Column(name = "FIN_TXSET1__GEN_DOC_VERS1__ID2")
    private String finTxset1_GenDocVers1_Id2;

    @Lob
    @Column(name = "FIN_TXACT1__GEN_DOC_VERS1__ID2")
    private String finTxact1_GenDocVers1_Id2;

    @Lob
    @Column(name = "FIN_TXSET1__GEN_TAGS1__ID2")
    private String finTxset1_GenTags1_Id2;

    @Lob
    @Column(name = "FIN_TXACT1__GEN_TAGS1__ID2")
    private String finTxact1_GenTags1_Id2;

    @Column(name = "FIN_TXFERS1__ID2")
    @Lob
    private String finTxfers1_Id2;

    @Column(name = "FIN_TXSET1__FIN_ACCTS__ID2")
    @Lob
    private String finTxset1_FinAccts_Id2;

    @Column(name = "FIN_TXFERS1__FIN_ACCTS1__ID2")
    @Lob
    private String finTxfers1_FinAccts1_Id2;

    @Column(name = "FIN_TXFERS1__DEBT_SUM", precision = 19, scale = 2)
    private BigDecimal finTxfers1_DebtSum;

    @Column(name = "FIN_TXFERS1__CRED_SUM", precision = 19, scale = 2)
    private BigDecimal finTxfers1_CredSum;

    @Column(name = "FIN_TXFERS1__ID_CNT", precision = 19, scale = 2)
    private BigDecimal finTxfers1_IdCnt;

    @Column(name = "DEBT_EQ_CRED")
    private Boolean debtEqCred;

    @Column(name = "FIN_TXACTS1__ID2")
    @Lob
    private String finTxacts1_Id2;

    @Column(name = "FIN_TXACTS1__FIN_ACCTS1__ID2")
    @Lob
    private String finTxacts1_FinAccts1_Id2;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;


    @Transient
    private Logger logger = LoggerFactory.getLogger(GenNode.class);

    public HasTmst getIdTs() {
        return idTs;
    }

    public void setIdTs(HasTmst idTs) {
        this.idTs = idTs;
    }

    public HasTmst getEnd2() {
        return end2;
    }

    public void setEnd2(HasTmst end2) {
        this.end2 = end2;
    }

    public HasTmst getBeg2() {
        return beg2;
    }

    public void setBeg2(HasTmst beg2) {
        this.beg2 = beg2;
    }

    public HasTime getIdTm() {
        return idTm;
    }

    public void setIdTm(HasTime idTm) {
        this.idTm = idTm;
    }

    public HasDate getIdDt() {
        return idDt;
    }

    public void setIdDt(HasDate idDt) {
        this.idDt = idDt;
    }

    public String getFinTxact1_GenDocVers1_Id2() {
        return finTxact1_GenDocVers1_Id2;
    }

    public String getFinTxact1_GenTags1_Id2() {
        return finTxact1_GenTags1_Id2;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId2Calc() {
        return id2Calc;
    }

    public void setId2Calc(String id2Calc) {
        this.id2Calc = id2Calc;
    }

    public Boolean getId2Cmp() {
        return id2Cmp;
    }

    public void setId2Cmp(Boolean id2Cmp) {
        this.id2Cmp = id2Cmp;
    }

    public Integer getId2Dup() {
        return id2Dup;
    }

    public void setId2Dup(Integer id2Dup) {
        this.id2Dup = id2Dup;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public GenNode getParent1_Id() {
        return parent1_Id;
    }

    public void setParent1_Id(GenNode parent1_Id) {
        this.parent1_Id = parent1_Id;
    }

    public String getParent1_Id2() {
        return parent1_Id2;
    }

    public void setParent1_Id2(String parent1_Id2) {
        this.parent1_Id2 = parent1_Id2;
    }

    public String getAncestors1_Id2() {
        return ancestors1_Id2;
    }

    public void setAncestors1_Id2(String ancestors1_Id2) {
        this.ancestors1_Id2 = ancestors1_Id2;
    }


    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }


    public void setType1_Id(GenNodeType type1_Id) {
        this.type1_Id = type1_Id;
    }

    public GenNodeType getType1_Id() {
        return type1_Id;
    }

    public String getType1_Id2() {
        return type1_Id2;
    }

    public void setType1_Id2(String type1_Id2) {
        this.type1_Id2 = type1_Id2;
    }


    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }


    public GenPat getName1Pat1_Id() {
        return name1Pat1_Id;
    }

    public void setName1Pat1_Id(GenPat name1Pat1_Id) {
        this.name1Pat1_Id = name1Pat1_Id;
    }

    public String getName1Pat1_Id2() {
        return name1Pat1_Id2;
    }

    public void setName1Pat1_Id2(String name1Pat1_Id2) {
        this.name1Pat1_Id2 = name1Pat1_Id2;
    }


    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }


    public String getAbrv() {
        return abrv;
    }

    public void setAbrv(String abrv) {
        this.abrv = abrv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.abrv = code;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



    public String getGenChan1_Id2() {
        return genChan1_Id2;
    }

    public void setGenChan1_Id2(String genChan1_Id2) {
        this.genChan1_Id2 = genChan1_Id2;
    }

    public GenNode getGenChan1_Id() {
        return genChan1_Id;
    }

    public void setGenChan1_Id(GenNode genChan1_Id) {
        this.genChan1_Id = genChan1_Id;
    }




    public void setBeg1(HasTmst beg1) {
        String logPrfx = "setBeg";
        logger.trace(logPrfx + " --> ");
        this.beg1 = beg1;

/*
        logger.debug(logPrfx + " --- calling updateId2Calc()");
        updateId2Calc();
*/

        logger.trace(logPrfx + " <-- ");
    }

    public HasTmst getBeg1() {
        return beg1;
    }

    public void setEnd1(HasTmst end1) {
        String logPrfx = "setEnd";
        logger.trace(logPrfx + " --> ");
        this.end1 = end1;

/*
        logger.debug(logPrfx + " --- calling updateId2Calc()");
        updateId2Calc();
*/

        logger.trace(logPrfx + " <-- ");
    }

    public HasTmst getEnd1() {
        return end1;
    }


    public Integer getIdX() {
        return idX;
    }

    public void setIdX(Integer idX) {
        String logPrfx = "setIdX";
        logger.trace(logPrfx + " --> ");
        this.idX = idX;

/*
        logger.debug(logPrfx + " --- calling updateId2Calc()");
        updateId2Calc();
*/

        logger.trace(logPrfx + " <-- ");
    }

    public Integer getIdY() {
        return idY;
    }

    public void setIdY(Integer idY) {
        String logPrfx = "setIdY";
        logger.trace(logPrfx + " --> ");
        this.idY = idY;

/*
        logger.debug(logPrfx + " --- calling updateId2Calc()");
        updateId2Calc();
*/

        logger.trace(logPrfx + " <-- ");
    }

    public Integer getIdZ() {
        return idZ;
    }

    public void setIdZ(Integer idZ) {
        String logPrfx = "setIdZ";
        logger.trace(logPrfx + " --> ");

//        EntityInternals.fireListeners((io.jmix.core.Entity) this, "idZ", this.idZ, idZ);
        this.idZ = idZ;

/*
        logger.debug(logPrfx + " --- calling updateId2Calc()");
        updateId2Calc();
*/

        logger.trace(logPrfx + " <-- ");
    }



    public String getGenDocVers1_Id2() {
        return genDocVers1_Id2;
    }

    public void setGenDocVers1_Id2(String genDocVers1_Id2) {
        this.genDocVers1_Id2 = genDocVers1_Id2;
    }

    public GenNode getGenDocVer1_Id() {
        return genDocVer1_Id;
    }

    public void setGenDocVer1_Id(GenNode genDocVer1_Id) {
        this.genDocVer1_Id = genDocVer1_Id;
    }

    public String getGenDocVer1_Id2() {
        return genDocVer1_Id2;
    }

    public void setGenDocVer1_Id2(String genDocVer1_Id2) {
        this.genDocVer1_Id2 = genDocVer1_Id2;
    }

    public String getGenDocVer1_Code() {
        return genDocVer1_Code;
    }

    public String getGenDocVer1_Ver() {
        return genDocVer1_Ver;
    }

    public String getGenDocVer1_Name1() {
        return genDocVer1_Name1;
    }


    public GenNode getGenFile1_Id() {
        return genFile1_Id;
    }

    public void setGenFile1_Id(GenNode genFile1_Id) {
        this.genFile1_Id = genFile1_Id;
    }

    public String getGenFile1_Id2() {
        return genFile1_Id2;
    }

    public void setGenFile1_Id2(String genFile1_Id2) {
        this.genFile1_Id2 = genFile1_Id2;
    }

    public URI getGenFile1_URI() {
        return genFile1_URI;
    }

    public void setGenFile1_URI(URI genFile1_URI) {
        this.genFile1_URI = genFile1_URI;
    }



    public String getGenTags1_Id2() {
        return genTags1_Id2;
    }

    public void setGenTags1_Id2(String genTags1_Id2) {
        this.genTags1_Id2 = genTags1_Id2;
    }


    public GenNode getGenTag1_Id() {
        return genTag1_Id;
    }

    public void setGenTag1_Id(GenNode genTag1_Id) {
        this.genTag1_Id = genTag1_Id;
    }

    public String getGenTag1_Id2() {
        return genTag1_Id2;
    }

    public void setGenTag1_Id2(String genTag1_Id2) {
        this.genTag1_Id2 = genTag1_Id2;
    }


    public GenNode getGenTag2_Id() {
        return genTag2_Id;
    }

    public void setGenTag2_Id(GenNode genTag2_Id) {
        this.genTag2_Id = genTag2_Id;
    }

    public String getGenTag2_Id2() {
        return genTag2_Id2;
    }

    public void setGenTag2_Id2(String genTag2_Id2) {
        this.genTag2_Id2 = genTag2_Id2;
    }


    public GenNode getGenTag3_Id() {
        return genTag3_Id;
    }

    public void setGenTag3_Id(GenNode genTag3_Id) {
        this.genTag3_Id = genTag3_Id;
    }

    public String getGenTag3_Id2() {
        return genTag3_Id2;
    }

    public void setGenTag3_Id2(String genTag3_Id2) {
        this.genTag3_Id2 = genTag3_Id2;
    }


    public GenNode getGenTag4_Id() {
        return genTag4_Id;
    }

    public void setGenTag4_Id(GenNode genTag4_Id) {
        this.genTag4_Id = genTag4_Id;
    }

    public String getGenTag4_Id2() {
        return genTag4_Id2;
    }

    public void setGenTag4_Id2(String genTag4_Id2) {
        this.genTag4_Id2 = genTag4_Id2;
    }




    public String getVer() {
        return ver;
    }

    public void setVer(String ver) { this.ver = ver;}

    public GenNode getGenAgent1_Id() {
        return genAgent1_Id;
    }

    public void setGenAgent1_Id(GenNode genAgent1_Id) {
        this.genAgent1_Id = genAgent1_Id;
    }

    public String getGenAgent1_Id2() {
        return genAgent1_Id2;
    }

    public void setGenAgent1_Id2(String genAgent1_Id2) {
        this.genAgent1_Id2 = genAgent1_Id2;
    }


    public GenNode getGenAgent2_Id() {
        return genAgent2_Id;
    }

    public void setGenAgent2_Id(GenNode genAgent2_Id) {
        this.genAgent2_Id = genAgent2_Id;
    }

    public String getGenAgent2_Id2() {
        return genAgent2_Id2;
    }

    public void setGenAgent2_Id2(String genAgent2_Id2) {
        this.genAgent2_Id2 = genAgent2_Id2;
    }


    public String getNamePrfx() {
        return namePrfx;
    }

    public void setNamePrfx(String namePrfx) {
        this.namePrfx = namePrfx;
    }

    public String getNameFrst() {
        return nameFrst;
    }

    public void setNameFrst(String nameFrst) {
        this.nameFrst = nameFrst;
    }

    public String getNameMidl() {
        return nameMidl;
    }

    public void setNameMidl(String nameMidl) {
        this.nameMidl = nameMidl;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNameSufx() {
        return nameSufx;
    }

    public void setNameSufx(String nameSufx) {
        this.nameSufx = nameSufx;
    }





    public String getFinTxset1_FinAccts1_Id2() {
        return finTxset1_FinAccts1_Id2;
    }

    public String getFinTxset1_FinTxacts1_Id2() {
        return finTxset1_FinTxacts1_Id2;
    }

    public void setFinTxset1_FinTxacts1_Id2(String finTxset1_FinTxacts1_Id2) {
        this.finTxset1_FinTxacts1_Id2 = finTxset1_FinTxacts1_Id2;
    }

    public String getFinTxset1_FinAccts_Id2() {
        return finTxset1_FinAccts_Id2;
    }

    public void setFinTxset1_FinAccts_Id2(String finTxset1_FinAccts_Id2) {
        this.finTxset1_FinAccts_Id2 = finTxset1_FinAccts_Id2;
    }


    public GenNode getFinTxset1_Id() {
        return finTxset1_Id;
    }

    public void setFinTxset1_Id(GenNode finTxset1_Id) {
        this.finTxset1_Id = finTxset1_Id;
    }

    public String getFinTxset1_Id2() {
        return finTxset1_Id2;
    }

    public void setFinTxset1_Id2(String finTxset1_Id2) {
        this.finTxset1_Id2 = finTxset1_Id2;
    }

    public String getFinTxset1_Id2Calc() {
        return finTxset1_Id2Calc;
    }

    public void setFinTxset1_Id2Calc(String finTxset1_Id2Calc) {
        this.finTxset1_Id2Calc = finTxset1_Id2Calc;
    }

    public String getFinTxset1_Type1_Id2() {
        return finTxset1_Type1_Id2;
    }

    public void setFinTxset1_Type1_Id2(String finTxset1_Type1_Id2) {
        this.finTxset1_Type1_Id2 = finTxset1_Type1_Id2;
    }


    public String getFinTxset1_EI1_Role() {
        return finTxset1_EI1_Role;
    }

    public void setFinTxset1_EI1_Role(String finTxset1_EI1_Role) {
        this.finTxset1_EI1_Role = finTxset1_EI1_Role;
    }


    public void setFinTxset1_Beg1Date1(LocalDate finTxset1_Beg1Date1) {
        this.finTxset1_Beg1Date1 = finTxset1_Beg1Date1;
    }

    public LocalDate getFinTxset1_Beg1Date1() {
        return finTxset1_Beg1Date1;
    }

    public void setFinTxset1_Beg1Time1(LocalTime finTxset1_Beg1Time1) {
        this.finTxset1_Beg1Time1 = finTxset1_Beg1Time1;
    }

    public LocalTime getFinTxset1_Beg1Time1() {
        return finTxset1_Beg1Time1;
    }


    public String getFinTxset1_GenTags1_Id2() {
        return finTxset1_GenTags1_Id2;
    }

    public void setFinTxset1_GenTags1_Id2(String finTxset1_GenTags1_Id2) {
        this.finTxset1_GenTags1_Id2 = finTxset1_GenTags1_Id2;
    }

    public String getFinTxset1_GenDocVers1_Id2() {
        return finTxset1_GenDocVers1_Id2;
    }

    public void setFinTxset1_GenDocVers1_Id2(String finTxset1_GenDocVers1_Id2) {
        this.finTxset1_GenDocVers1_Id2 = finTxset1_GenDocVers1_Id2;
    }



    public String getFinTxset1_GenChan1_Id2() {
        return finTxset1_GenChan1_Id2;
    }

    public void setFinTxset1_GenChan1_Id2(String finTxset1_GenChan1_Id2) {
        this.finTxset1_GenChan1_Id2 = finTxset1_GenChan1_Id2;
    }



    public String getFinTxset1_How1_Id2() {
        return finTxset1_How1_Id2;
    }

    public void setFinTxset1_How1_Id2(String finTxset1_How1_Id2) {
        this.finTxset1_How1_Id2 = finTxset1_How1_Id2;
    }

    public String getFinTxset1_WhatText1() {
        return finTxset1_WhatText1;
    }

    public void setFinTxset1_WhatText1(String finTxset1_WhatText1) {
        this.finTxset1_WhatText1 = finTxset1_WhatText1;
    }


    public String getFinTxset1_What1_Id2() {
        return finTxset1_What1_Id2;
    }

    public void setFinTxset1_What1_Id2(String finTxset1_What1_Id2) {
        this.finTxset1_What1_Id2 = finTxset1_What1_Id2;
    }


    public String getFinTxset1_WhyText1() {
        return finTxset1_WhyText1;
    }

    public void setFinTxset1_WhyText1(String finTxset1_WhyText1) {
        this.finTxset1_WhyText1 = finTxset1_WhyText1;
    }

    public String getFinTxset1_Why1_Id2() {
        return finTxset1_Why1_Id2;
    }

    public void setFinTxset1_Why1_Id2(String finTxset1_Why1_Id2) {
        this.finTxset1_Why1_Id2 = finTxset1_Why1_Id2;
    }





    public String getFinTxacts1_Id2() {
        return finTxacts1_Id2;
    }

    public void setFinTxacts1_Id2(String finTxacts1_Id2) {
        this.finTxacts1_Id2 = finTxacts1_Id2;
    }

    public String getFinTxact1_FinTxfers1_Id2() {
        return finTxact1_FinTxfers1_Id2;
    }

    public String getFinTxact1_FinAccts1_Id2() {
        return finTxact1_FinAccts1_Id2;
    }

    public String getFinTxacts1_FinAccts1_Id2() {
        return finTxacts1_FinAccts1_Id2;
    }

    public void setFinTxacts1_FinAccts1_Id2(String finTxacts1_FinAccts1_Id2) {
        this.finTxacts1_FinAccts1_Id2 = finTxacts1_FinAccts1_Id2;
    }



    public GenNode getFinTxact1_Id() {
        return finTxact1_Id;
    }

    public void setFinTxact1_Id(GenNode finTxact1_Id) {
        this.finTxact1_Id = finTxact1_Id;
    }

    public String getFinTxact1_Id2() {
        return finTxact1_Id2;
    }

    public void setFinTxact1_Id2(String finTxact1_Id2) {
        this.finTxact1_Id2 = finTxact1_Id2;
    }

    public String getFinTxact1_Id2Calc() { return finTxact1_Id2Calc;}

    public void setFinTxact1_Id2Calc(String finTxact1_Id2Calc) {
        String logPrfx = "setFinTxact1_Id2Calc()";
        logger.trace(logPrfx + " --> ");

        this.finTxact1_Id2Calc = finTxact1_Id2Calc;

        logger.trace(logPrfx + " <-- ");
    }

    public String getFinTxact1_Type1_Id2() {
        return finTxact1_Type1_Id2;
    }

    public void setFinTxact1_Type1_Id2(String finTxact1_Type1_Id2) {
        this.finTxact1_Type1_Id2 = finTxact1_Type1_Id2;
    }

    public String getFinTxact1_EI1_Role() {
        return finTxact1_EI1_Role;
    }

    public void setFinTxact1_EI1_Role(String finTxact1_EI1_Role) {
        this.finTxact1_EI1_Role = finTxact1_EI1_Role;
    }


    public String getFinTxact1_GenChan1_Id2() {
        return finTxact1_GenChan1_Id2;
    }

    public void setFinTxact1_GenChan1_Id2(String finTxact1_GenChan1_Id2) {
        this.finTxact1_GenChan1_Id2 = finTxact1_GenChan1_Id2;
    }

    public void setFinTxact1_Beg1Time1(LocalTime finTxact1_Beg1Time1) {
        this.finTxact1_Beg1Time1 = finTxact1_Beg1Time1;
    }

    public LocalTime getFinTxact1_Beg1Time1() {
        return finTxact1_Beg1Time1;
    }

    public void setFinTxact1_Beg1Date1(LocalDate finTxact1_Beg1Date1) {
        this.finTxact1_Beg1Date1 = finTxact1_Beg1Date1;
    }

    public LocalDate getFinTxact1_Beg1Date1() {
        return finTxact1_Beg1Date1;
    }


    public String getFinTxact1_How1_Id2() {
        return finTxact1_How1_Id2;
    }

    public void setFinTxact1_How1_Id2(String finTxact1_How1_Id2) {
        this.finTxact1_How1_Id2 = finTxact1_How1_Id2;
    }


    public String getFinTxact1_WhatText1() {
        return finTxact1_WhatText1;
    }

    public void setFinTxact1_WhatText1(String finTxact1_WhatText1) {
        this.finTxact1_WhatText1 = finTxact1_WhatText1;
    }

    public String getFinTxact1_What1_Id2() {
        return finTxact1_What1_Id2;
    }

    public void setFinTxact1_What1_Id2(String finTxact1_What1_Id2) {
        this.finTxact1_What1_Id2 = finTxact1_What1_Id2;
    }


    public String getFinTxact1_WhyText1() {
        return finTxact1_WhyText1;
    }

    public void setFinTxact1_WhyText1(String finTxact1_WhyText1) {
        this.finTxact1_WhyText1 = finTxact1_WhyText1;
    }

    public String getFinTxact1_Why1_Id2() {
        return finTxact1_Why1_Id2;
    }

    public void setFinTxact1_Why1_Id2(String finTxact1_Why1_Id2) {
        this.finTxact1_Why1_Id2 = finTxact1_Why1_Id2;
    }





    public String getFinStmt1_Id2() {
        return finStmt1_Id2;
    }

    public void setFinStmt1_Id2(String finStmt1_Id2) {
        this.finStmt1_Id2 = finStmt1_Id2;
    }

    public GenNode getFinStmt1_Id() {
        return finStmt1_Id;
    }

    public void setFinStmt1_Id(GenNode finStmt1_Id) {
        this.finStmt1_Id = finStmt1_Id;
    }


    public String getFinStmtItm1_RefId() {
        return finStmtItm1_RefId;
    }

    public void setFinStmtItm1_RefId(String finStmtItm1_RefId) {
        this.finStmtItm1_RefId = finStmtItm1_RefId;
    }

    public String getFinStmtItm1_DescAmt() {
        return finStmtItm1_DescAmt;
    }

    public void setFinStmtItm1_DescAmt(String finStmtItm1_DescAmt) {
        this.finStmtItm1_DescAmt = finStmtItm1_DescAmt;
    }

    public String getFinStmtItm1_Desc3() {
        return finStmtItm1_Desc3;
    }

    public void setFinStmtItm1_Desc3(String finStmtItm1_Desc3) {
        this.finStmtItm1_Desc3 = finStmtItm1_Desc3;
    }

    public String getFinStmtItm1_Desc2() {
        return finStmtItm1_Desc2;
    }

    public void setFinStmtItm1_Desc2(String finStmtItm1_Desc2) {
        this.finStmtItm1_Desc2 = finStmtItm1_Desc2;
    }

    public String getFinStmtItm1_Desc1() {
        return finStmtItm1_Desc1;
    }

    public void setFinStmtItm1_Desc1(String finStmtItm1_Desc1) {
        this.finStmtItm1_Desc1 = finStmtItm1_Desc1;
    }


    public String getFinAcct1_Type1_Id2() {
        return finAcct1_Type1_Id2;
    }

    public void setFinAcct1_Type1_Id2(String finAcct1_Type1_Id2) {
        this.finAcct1_Type1_Id2 = finAcct1_Type1_Id2;
    }


    public GenNode getFinAcct1_Id() {
        return finAcct1_Id;
    }

    public void setFinAcct1_Id(GenNode finAcct1_Id) {
        this.finAcct1_Id = finAcct1_Id;
    }

    public String getFinAcct1_Id2() {
        return finAcct1_Id2;
    }

    public void setFinAcct1_Id2(String finAcct1_Id2) {
        this.finAcct1_Id2 = finAcct1_Id2;
    }



    public GenNode getFinDept1_Id() {
        return finDept1_Id;
    }

    public void setFinDept1_Id(GenNode finDept1_Id) {
        this.finDept1_Id = finDept1_Id;
    }

    public String getFinDept1_Id2() {
        return finDept1_Id2;
    }

    public void setFinDept1_Id2(String finDept1_Id2) {
        this.finDept1_Id2 = finDept1_Id2;
    }



    public BigDecimal getAmtNet() {
        return amtNet;
    }

    public void setAmtNet(BigDecimal amtNet) {
        this.amtNet = amtNet;
    }

    public BigDecimal getAmtCred() {
        return amtCred;
    }

    public void setAmtCred(BigDecimal amtCred) {
        this.amtCred = amtCred;
    }

    public BigDecimal getAmtDebt() {
        return amtDebt;
    }

    public void setAmtDebt(BigDecimal amtDebt) {
        this.amtDebt = amtDebt;
    }

    public GenNode getFinCurcy1_Id() {
        return finCurcy1_Id;
    }

    public void setFinCurcy1_Id(GenNode finCurcy1_Id) {
        this.finCurcy1_Id = finCurcy1_Id;
    }

    public String getFinCurcy1_Id2() {
        return finCurcy1_Id2;
    }

    public void setFinCurcy1_Id2(String finCurcy1_Id2) {
        this.finCurcy1_Id2 = finCurcy1_Id2;
    }



    public GenNode getFinTaxLne1_Id() {
        return finTaxLne1_Id;
    }

    public void setFinTaxLne1_Id(GenNode finTax_1_Id) {
        this.finTaxLne1_Id = finTax_1_Id;
    }

    public String getFinTaxLne1_Id2() {
        return finTaxLne1_Id2;
    }

    public void setFinTaxLne1_Id2(String finTaxLne1_Id2) {
        this.finTaxLne1_Id2 = finTaxLne1_Id2;
    }

    public String getFinTaxLne1_Type1_Id2() {
        return finTaxLne1_Type1_Id2;
    }

    public void setFinTaxLne1_Type1_Id2(String finTaxLne1_Type1_Id2) {
        this.finTaxLne1_Type1_Id2 = finTaxLne1_Type1_Id2;
    }

    public String getFinTaxLne1_Code() {
        return finTaxLne1_Code;
    }

    public void setFinTaxLne1_Code(String finTaxLne1_Code) {
        this.finTaxLne1_Code = finTaxLne1_Code;
    }


    public FinFmla getFinFmla1_Id() {
        return finFmla1_Id;
    }

    public void setFinFmla1_Id(FinFmla finFmla1_Id) {
        this.finFmla1_Id = finFmla1_Id;
    }

    public String getFinFmla1_Id2() {
        return finFmla1_Id2;
    }

    public void setFinFmla1_Id2(String finFmla1_Id2) {
        this.finFmla1_Id2 = finFmla1_Id2;
    }


    public GenNode getFinTxfer1_Id() {
        return finTxfer1_Id;
    }

    public void setFinTxfer1_Id(GenNode finTxfer1_Id) {
        this.finTxfer1_Id = finTxfer1_Id;
    }

    public String getFinTxfer1_Id2() {
        return finTxfer1_Id2;
    }

    public void setFinTxfer1_Id2(String finTxfer1_Id2) {
        this.finTxfer1_Id2 = finTxfer1_Id2;
    }

    public BigDecimal getFinTxfer1_EI1_Rate() {
        return finTxfer1_EI1_Rate;
    }

    public void setFinTxfer1_EI1_Rate(BigDecimal finTxfer1_EI1_Rate) {
        this.finTxfer1_EI1_Rate = finTxfer1_EI1_Rate;
    }

    public BigDecimal getCalcRslt() {
        return calcRslt;
    }

    public void setCalcRslt(BigDecimal calcRslt) {
        this.calcRslt = calcRslt;
    }



    public String getFinTxfers1_Id2() {
        return finTxfers1_Id2;
    }

    public void setFinTxfers1_Id2(String finTxfers1_Id2) {
        this.finTxfers1_Id2 = finTxfers1_Id2;
    }

    public String getFinTxfers1_FinAccts1_Id2() {
        return finTxfers1_FinAccts1_Id2;
    }

    public void setFinTxfers1_FinAccts1_Id2(String finTxfers1_FinAccts1_Id2) {
        this.finTxfers1_FinAccts1_Id2 = finTxfers1_FinAccts1_Id2;
    }


    public Boolean getDebtEqCred() {
        return debtEqCred;
    }

    public void setDebtEqCred(Boolean debtEqCred) {
        this.debtEqCred = debtEqCred;
    }

    public BigDecimal getFinTxfers1_IdCnt() {
        return finTxfers1_IdCnt;
    }

    public void setFinTxfers1_IdCnt(BigDecimal finTxfers1_IdCnt) {
        this.finTxfers1_IdCnt = finTxfers1_IdCnt;
    }

    public BigDecimal getFinTxfers1_CredSum() {
        return finTxfers1_CredSum;
    }

    public void setFinTxfers1_CredSum(BigDecimal finTxfers1_CredSum) {
        this.finTxfers1_CredSum = finTxfers1_CredSum;
    }

    public BigDecimal getFinTxfers1_DebtSum() {
        return finTxfers1_DebtSum;
    }

    public void setFinTxfers1_DebtSum(BigDecimal finTxfers1_DebtSum) {
        this.finTxfers1_DebtSum = finTxfers1_DebtSum;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getNetSum() {
        return netSum;
    }

    public void setNetSum(BigDecimal netSum) {
        this.netSum = netSum;
    }

    public BigDecimal getDebtSum() {
        return debtSum;
    }

    public void setDebtSum(BigDecimal debtSum) {
        this.debtSum = debtSum;
    }

    public BigDecimal getCredSum() {
        return credSum;
    }

    public void setCredSum(BigDecimal credSum) {
        this.credSum = credSum;
    }

    public BigDecimal getEndBal() {
        return endBal;
    }

    public void setEndBal(BigDecimal endBal) {
        this.endBal = endBal;
    }

    public BigDecimal getBegBal() {
        return begBal;
    }

    public void setBegBal(BigDecimal begBal) {
        this.begBal = begBal;
    }




    public String getFinHow1_Id2() {
        return finHow1_Id2;
    }

    public void setFinHow1_Id2(String finHow1_Id2) {
        this.finHow1_Id2 = finHow1_Id2;
    }

    public FinHow getFinHow1_Id() {
        return finHow1_Id;
    }

    public void setFinHow1_Id(FinHow finHow1_Id) {
        this.finHow1_Id = finHow1_Id;
    }

    public String getWhatText1() {
        return whatText1;
    }

    public void setWhatText1(String whatText1) {
        this.whatText1 = whatText1;
    }

    public FinWhat getFinWhat1_Id() {
        return finWhat1_Id;
    }

    public void setFinWhat1_Id(FinWhat finWhat1_Id) {
        this.finWhat1_Id = finWhat1_Id;
    }

    public String getFinWhat1_Id2() {
        return finWhat1_Id2;
    }

    public void setFinWhat1_Id2(String finWhat1_Id2) {
        this.finWhat1_Id2 = finWhat1_Id2;
    }

    public String getWhyText1() {
        return whyText1;
    }

    public void setWhyText1(String whyText1) {
        this.whyText1 = whyText1;
    }

    public FinWhy getFinWhy1_Id() {
        return finWhy1_Id;
    }

    public void setFinWhy1_Id(FinWhy finWhy1_Id) {
        this.finWhy1_Id = finWhy1_Id;
    }

    public String getFinWhy1_Id2() {
        return finWhy1_Id2;
    }

    public void setFinWhy1_Id2(String finWhy1_Id2) {
        this.finWhy1_Id2 = finWhy1_Id2;
    }



    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    public void updateId2Calc(){
        String logPrfx = "updateId2Calc()";
        logger.trace(logPrfx + " --> ");

        String id2Calc = "";

        logger.debug(logPrfx + " --- calling getId2CalcFrFields()");
        id2Calc = getId2CalcFrFields();
        logger.debug(logPrfx + " --- calling setId2Calc("+ id2Calc +")");
        setId2Calc(id2Calc);

        logger.trace(logPrfx + " <-- ");
    }


    public String getId2CalcFrFields(){
        String logPrfx = "getId2CalcFrFields";
        logger.trace(logPrfx + " --> ");

        final String SEP = "/";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();
        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd")
                .toFormatter();
         DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HHmm")
                .toFormatter();

        switch (className) {
            case "FinTxset":
            case "FinTxact":
            case "FinTxfer":
                if (idTs == null) {
                    logger.debug(logPrfx + " --- idTs: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                }
                if (idTs.getTs1() == null) {
                    logger.debug(logPrfx + " --- idTs.getTs1(): null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- idTs.getTs1(): " + idTs.getTs1().format(frmtTs));
                }
                break;
            case "FinStmt":
                if (idDt == null) {
                    logger.debug(logPrfx + " --- idDt: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                }
                if (idDt.getDate1() == null) {
                    logger.debug(logPrfx + " --- idDt.getDate1(): null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- idDt.getDate1(): " + idDt.getDate1().format(frmtDt));
                }
                break;
        };
        
        switch (className) {
            case "FinStmt":
                if (finAcct1_Id == null) {
                    logger.debug(logPrfx + " --- finAcct1_Id: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- finAcct1_Id: " + finAcct1_Id.getId());
                }
                break;
            default:

        };

        switch (className){
            case "FinTxset":
            case "FinTxact":
            case "FinTxfer":
                //Date
                sb.append(SEP + "D").append(idTs.getTs1().format(frmtDt))
                    //Time
                    .append(SEP + "T").append(idTs.getTs1().format(frmtTm));
                break;

            case "FinStmt":
                //Account
                sb.append(finAcct1_Id.getId2())
                    //Date
                    .append(SEP + "D").append(idDt.getDate1().format(frmtDt));
                break;

            default:
        }

        switch (className){
            case "FinTxset":
            case "FinTxact":
            case "FinTxfer":
                //IdX
                if (idX == null) {
                    logger.debug(logPrfx + " --- idX: null");
                    sb.append(SEP + "X00");
                }else{
                    logger.debug(logPrfx + " --- idX: " + idX.toString());
                    sb.append(SEP + "X").append(String.format("%02d", idX));
                }
                break;
            default:
        }

        switch (className){
            case "FinTxact":
            case "FinTxfer":
                //IdY
                if (idY == null) {
                    logger.debug(logPrfx + " --- idY: null");
                    sb.append(SEP + "Y00");
                }else{
                    logger.debug(logPrfx + " --- idY: " + idY.toString());
                    sb.append(SEP + "Y").append(String.format("%02d", idY));
                }
                break;
            default:
        }

        switch (className){
            case "FinTxfer":
                //IdZ
                if (idZ == null) {
                    logger.debug(logPrfx + " --- idZ: null");
                    sb.append(SEP + "Z00");
                }else {
                    logger.debug(logPrfx + " --- idZ: " + idZ.toString());
                    sb.append(SEP + "Z").append(String.format("%02d", idZ));
                }
                break;
            default:
        }

        logger.debug(logPrfx + " --- sb: " + sb);
        logger.trace(logPrfx + " <--- ");
        return sb.toString();

    }
    public void updateIdTs() {
        // Assume beg1, beg2, end1 is not null
        String logPrfx = "updateIdTs()";
        logger.trace(logPrfx + " --> ");

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();

        switch (className) {
            case "FinTxset":
                if (beg1.getTs1() != null){
                    logger.debug(logPrfx + " --- calling idTs.setTs1(("+ beg1.getTs1().format(frmtTs) +")");
                    idTs.setTs1(null);
                }else{
                    logger.debug(logPrfx + " --- calling idTs.setTs1((null)");
                    idTs.setTs1(beg1.getTs1());
                }
                break;

            case "FinTxact":
            case "FinTxfer":
                if (beg2.getTs1() != null){
                    logger.debug(logPrfx + " --- calling idTs.setTs1(("+ beg2.getTs1().format(frmtTs) +")");
                    idTs.setTs1(beg2.getTs1());
                }else if (beg1.getTs1() != null){
                    logger.debug(logPrfx + " --- calling idTs.setTs1(("+ beg1.getTs1().format(frmtTs) +")");
                    idTs.setTs1(beg1.getTs1());
                }else{
                    logger.debug(logPrfx + " --- calling idTs.setTs1((null)");
                    idTs.setTs1(beg1.getTs1());
                }
                break;

            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
    }


    public void updateIdDt() {
        // Assume beg1, beg2, end1 is not null
        String logPrfx = "updateIdDt()";
        logger.trace(logPrfx + " --> ");

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd")
                .toFormatter();

        switch (className) {

            case "FinStmt":
                if (end1.getDate1() != null){
                    logger.debug(logPrfx + " --- calling idDt.setDate1(("+ end1.getDate1().format(frmtDt) +")");
                    idDt.setDate1(end1.getDate1());
                }else{
                    logger.debug(logPrfx + " --- calling idDt.setDate1((null)");
                    idDt.setDate1(null);
                }
                break;
            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
    }

    public void updateIdTm() {
        // Assume beg1, beg2 is not null
        String logPrfx = "updateIdTm()";
        logger.trace(logPrfx + " --> ");

        DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HHmm")
                .toFormatter();

        switch (className) {

            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
    }

}
