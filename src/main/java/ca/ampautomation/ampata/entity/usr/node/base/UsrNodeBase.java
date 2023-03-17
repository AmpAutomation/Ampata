package ca.ampautomation.ampata.entity.usr.node.base;

import ca.ampautomation.ampata.entity.HasDate;
import ca.ampautomation.ampata.entity.HasTime;
import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.node.base.SysNodeBase;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBase;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinHow;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhat;
import ca.ampautomation.ampata.entity.usr.item.fin.UsrItemFinWhy;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenFmla;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenSel;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.fin.*;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenAgent;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenChan;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenDocVer;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrNodeGenFile;
import io.jmix.core.DataManager;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.multitenancy.core.AcceptsTenant;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URI;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JmixEntity
@Table(name = "AMPATA_USR_NODE", indexes = {
        @Index(name = "IDX_USRNODE_PARENT1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_USRNODE_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_USRNODE_NAME1_GEN_SEL1__ID", columnList = "NAME1_GEN_SEL1__ID"),
        @Index(name = "IDX_USRNODE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_TYPE1__ID", columnList = "NM1S1_TYPE1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_NAME1_GEN_FMLA1__ID", columnList = "NM1S1_NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_INST1_GEN_FMLA1__ID", columnList = "NM1S1_INST1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_INST1_NODE1__ID", columnList = "NM1S1_INST1_NODE1__ID"),
        @Index(name = "IDX_USRNODE_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_DESC1_NODE1__ID", columnList = "DESC1_NODE1__ID"),
        @Index(name = "IDX_USRNODE_DESC1_NODE2__ID", columnList = "DESC1_NODE2__ID"),
        @Index(name = "IDX_USRNODE_NODE1__ID", columnList = "NODE1__ID"),
        @Index(name = "IDX_USRNODE_GEN_CHAN1__ID", columnList = "GEN_CHAN1__ID"),
        @Index(name = "IDX_USRNODE_GEN_CHAN2__ID", columnList = "GEN_CHAN2__ID"),
        @Index(name = "IDX_USRNODE_FIN_HOW1__ID", columnList = "FIN_HOW1__ID"),
        @Index(name = "IDX_USRNODE_FIN_WHAT1__ID", columnList = "FIN_WHAT1__ID"),
        @Index(name = "IDX_USRNODE_FIN_WHY1__ID", columnList = "FIN_WHY1__ID"),
        @Index(name = "IDX_USRNODE_FIN_TXACT_SET1__ID", columnList = "FIN_TXACT_SET1__ID"),
        @Index(name = "IDX_USRNODE_FIN_TXACT1__ID", columnList = "FIN_TXACT1__ID"),
        @Index(name = "IDX_USRNODE_FIN_ACCT1__ID", columnList = "FIN_ACCT1__ID"),
        @Index(name = "IDX_USRNODE_FIN_DEPT1__ID", columnList = "FIN_DEPT1__ID"),
        @Index(name = "IDX_USRNODE_FIN_STMT1__ID", columnList = "FIN_STMT1__ID"),
        @Index(name = "IDX_USRNODE_FIN_STMT_ITM1__ID", columnList = "FIN_STMT_ITM1__ID"),
        @Index(name = "IDX_USRNODE_FIN_BAL1__ID", columnList = "FIN_BAL1__ID"),
        @Index(name = "IDX_USRNODE_FIN_BAL_SET1__ID", columnList = "FIN_BAL_SET1__ID"),
        @Index(name = "IDX_USRNODE_FIN_TAX_LNE1__ID", columnList = "FIN_TAX_LNE1__ID"),
        @Index(name = "IDX_USRNODE_SYS_NODE_FIN_CURCY1__ID", columnList = "SYS_NODE_FIN_CURCY1__ID"),
        @Index(name = "IDX_USRNODE_AMT_GEN_FMLA1__ID", columnList = "AMT_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_AMT_FIN_TXACT_ITM1__ID", columnList = "AMT_FIN_TXACT_ITM1__ID"),
        @Index(name = "IDX_USRNODE_GEN_DOC_VER1__ID", columnList = "GEN_DOC_VER1__ID"),
        @Index(name = "IDX_USRNODE_GEN_FILE1__ID", columnList = "GEN_FILE1__ID"),
        @Index(name = "IDX_USRNODE_GEN_TAG1__ID", columnList = "GEN_TAG1__ID"),
        @Index(name = "IDX_USRNODE_GEN_TAG2__ID", columnList = "GEN_TAG2__ID"),
        @Index(name = "IDX_USRNODE_GEN_TAG3__ID", columnList = "GEN_TAG3__ID"),
        @Index(name = "IDX_USRNODE_GEN_TAG4__ID", columnList = "GEN_TAG4__ID"),
        @Index(name = "IDX_USRNODE_GEN_AGENT1__ID", columnList = "GEN_AGENT1__ID"),
        @Index(name = "IDX_USRNODE_GEN_AGENT2__ID", columnList = "GEN_AGENT2__ID")
})
@Entity(name = "enty_UsrNodeBase")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(name = "UsrNodeBase.execUsrNodeBasePrUpd",
                procedureName = "Usr_Node_Pr_Upd", parameters = {

        }),

        @NamedStoredProcedureQuery(name = "UsrNodeBase.execUsrNodeBasePrUpd2",
                procedureName = "Usr_Node_Pr_Upd2"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
        }),

        @NamedStoredProcedureQuery(name = "UsrNodeBase.execUsrNodeBasePrUpd3",
                procedureName = "Usr_Node_Pr_Upd3"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
                , @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = String.class)
        }),

        @NamedStoredProcedureQuery(name = "UsrNodeBase.execUsrNodeFinTxactItmPrUpd",
                procedureName = "Fin_Txact_Itm_Pr_Upd", parameters = {

        }),

        @NamedStoredProcedureQuery(name = "UsrNodeBase.execUsrNodeFinTxactItmPrUpd2",
                procedureName = "Fin_Txact_Itm_Pr_Upd2"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
        }),

})

public class UsrNodeBase implements AcceptsTenant {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Transient
    protected final String SEP0 = " ";

    @Transient
    protected final String SEP1 = "/";
    @Transient
    protected final String SEP2 = ";";
    @Transient
    protected final String SEP3 = "::";
    @Transient
    protected final DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
            .appendPattern("yyyyMMdd HHmm")
            .toFormatter();
    @Transient
    protected final DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .toFormatter();
    @Transient
    protected final DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
            .appendPattern("HH:mm")
            .toFormatter();
    @Transient
    protected final DecimalFormat frmtDec = new DecimalFormat("+0.00;-0.00");

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    protected UUID id;

    @TenantId
    @Column(name = "TENANT")
    protected String tenant;

    @Column(name = "DTYPE", insertable = false, updatable = false)
    protected String dtype;

    @Column(name = "CLASS_NAME")
    protected String className;

    @InstanceName
    @Column(name = "ID2")
    protected String id2;

    @Column(name = "ID2_CALC")
    protected String id2Calc;

    @Column(name = "ID2_CMP")
    protected Boolean id2Cmp;

    @Column(name = "ID2_DUP")
    protected Integer id2Dup;


    @JoinColumn(name = "PARENT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase parent1_Id;

    @Column(name = "PARENT1__ID2")
    protected String parent1_Id2;

    @Column(name = "ANCESTORS1__ID2")
    @Lob
    protected String ancestors1_Id2;

    @Column(name = "SORT_IDX")
    protected Integer sortIdx;

    @Column(name = "SORT_KEY")
    protected String sortKey;

    @Column(name = "NAME1")
    protected String name1;

    @JoinColumn(name = "NAME1_GEN_SEL1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenSel name1GenSel1_Id;

    @Column(name = "NAME1_GEN_SEL1__ID2")
    protected String name1GenSel1_Id2;

    @JoinColumn(name = "NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla name1GenFmla1_Id;

    @Column(name = "NAME1_GEN_FMLA1__ID2")
    protected String name1GenFmla1_Id2;

    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBaseType type1_Id;

    @Column(name = "TYPE1__ID2")
    protected String type1_Id2;

    @Column(name = "INST1")
    protected String inst1;

    @Column(name = "NM1S1_NAME1")
    protected String nm1s1Name1;

    @JoinColumn(name = "NM1S1_NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla nm1s1Name1GenFmla1_Id;

    @Column(name = "NM1S1_NAME1_GEN_FMLA1__ID2")
    protected String nm1s1Name1GenFmla1_Id2;

    @JoinColumn(name = "NM1S1_TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBaseType nm1s1Type1_Id;

    @Column(name = "NM1S1_TYPE1__ID2")
    protected String nm1s1Type1_Id2;


    @Column(name = "NM1S1_INST1")
    protected String nm1s1Inst1;

    @JoinColumn(name = "NM1S1_INST1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla nm1s1Inst1GenFmla1_Id;

    @Column(name = "NM1S1_INST1_GEN_FMLA1__ID2")
    protected String nm1s1Inst1GenFmla1_Id2;


    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elTs", column = @Column(name = "NM1S1_INST1_TS1_EL_TS")),
            @AttributeOverride(name = "elDt", column = @Column(name = "NM1S1_INST1_TS1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "NM1S1_INST1_TS1_EL_DT_DAY")),
            @AttributeOverride(name = "elTm", column = @Column(name = "NM1S1_INST1_TS1_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "NM1S1_INST1_TS1_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "NM1S1_INST1_TS1_EL_TM_MIN"))
    })
    protected HasTmst nm1s1Inst1Ts1;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elDt", column = @Column(name = "NM1S1_INST1_DT1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_DAY"))
    })
    protected HasDate nm1s1Inst1Dt1;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elTm", column = @Column(name = "NM1S1_INST1_TM1_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "NM1S1_INST1_TM1_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "NM1S1_INST1_TM1_EL_TM_MIN"))
    })
    protected HasTime nm1s1Inst1Tm1;

    @Column(name = "NM1S1_INST1_INT1")
    protected Integer nm1s1Inst1Int1;

    @Column(name = "NM1S1_INST1_INT2")
    protected Integer nm1s1Inst1Int2;

    @Column(name = "NM1S1_INST1_INT3")
    protected Integer nm1s1Inst1Int3;

    @Column(name = "NM1S1_INST1_TXT1")
    protected String nm1s1Inst1Txt1;

    @Column(name = "NM1S1_INST1_TXT2")
    protected String nm1s1Inst1Txt2;

    @Column(name = "NM1S1_INST1_TXT3")
    protected String nm1s1Inst1Txt3;

    @JoinColumn(name = "NM1S1_INST1_NODE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase nm1s1Inst1Node1_Id;

    @Column(name = "NM1S1_INST1_NODE1__ID2")
    protected String nm1s1Inst1Node1_Id2;


    @Column(name = "NAME2")
    protected String name2;

    @Column(name = "ABRV", length = 16)
    protected String abrv;

    @Column(name = "CODE")
    protected String code;

    @Column(name = "DESC1")
    @Lob
    protected String desc1;

    @JoinColumn(name = "DESC1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    protected String desc1GenFmla1_Id2;

    @JoinColumn(name = "DESC1_NODE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase desc1Node1_Id;

    @Column(name = "DESC1_NODE1__ID2")
    protected String desc1Node1_Id2;

    @JoinColumn(name = "DESC1_NODE2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase desc1Node2_Id;

    @Column(name = "DESC1_NODE2__ID2")
    protected String desc1Node2_Id2;

    @Column(name = "DESC2")
    @Lob
    protected String desc2;

    @Column(name = "DESC3")
    @Lob
    protected String desc3;

    @Column(name = "DESC4")
    @Lob
    protected String desc4;

    @Column(name = "STATUS")
    protected String status;

    @Column(name = "STATUS_CALC")
    protected String statusCalc;

    @Column(name = "NOTE")
    @Lob
    protected String note;

    @OneToMany(mappedBy = "nodeOt_Id")
    private List<UsrEdgeBase> edgeIns_Id;

    @Column(name = "EDGE_INS__ID2")
    private String edgeIns_Id2;

    @OneToMany(mappedBy = "nodeIn_Id")
    private List<UsrEdgeBase> edgeOts_Id;

    @Column(name = "EDGE_OTS__ID2")
    private String edgeOts_Id2;



    @Column(name = "TXT1")
    protected String txt1;

    @Column(name = "TXT2")
    protected String txt2;

    @Column(name = "TXT3")
    protected String txt3;

    @Column(name = "INT1")
    protected Integer int1;

    @Column(name = "INT2")
    protected Integer int2;

    @Column(name = "INT3")
    protected Integer int3;

    @AttributeOverrides({
            @AttributeOverride(name = "elTs", column = @Column(name = "TS1_EL_TS")),
            @AttributeOverride(name = "elDt", column = @Column(name = "TS1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "TS1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "TS1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "TS1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "TS1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "TS1_EL_DT_DAY")),
            @AttributeOverride(name = "elTm", column = @Column(name = "TS1_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "TS1_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "TS1_EL_TM_MIN"))
    })
    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    protected HasTmst ts1;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elTs", column = @Column(name = "TS2_EL_TS")),
            @AttributeOverride(name = "elDt", column = @Column(name = "TS2_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "TS2_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "TS2_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "TS2_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "TS2_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "TS2_EL_DT_DAY")),
            @AttributeOverride(name = "elTm", column = @Column(name = "TS2_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "TS2_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "TS2_EL_TM_MIN"))
    })
    protected HasTmst ts2;

    @AttributeOverrides({
            @AttributeOverride(name = "elTs", column = @Column(name = "TS3_EL_TS")),
            @AttributeOverride(name = "elDt", column = @Column(name = "TS3_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "TS3_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "TS3_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "TS3_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "TS3_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "TS3_EL_DT_DAY")),
            @AttributeOverride(name = "elTm", column = @Column(name = "TS3_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "TS3_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "TS3_EL_TM_MIN"))
    })
    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    protected HasTmst ts3;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elTs", column = @Column(name = "TS4_EL_TS")),
            @AttributeOverride(name = "elDt", column = @Column(name = "TS4_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "TS4_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "TS4_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "TS4_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "TS4_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "TS4_EL_DT_DAY")),
            @AttributeOverride(name = "elTm", column = @Column(name = "TS4_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "TS4_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "TS4_EL_TM_MIN"))
    })
    protected HasTmst ts4;


    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elDt", column = @Column(name = "DT1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "DT1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "DT1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "DT1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "DT1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "DT1_EL_DT_DAY"))
    })
    protected HasDate dt1;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "elTm", column = @Column(name = "TM1_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "TM1_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "TM1_EL_TM_MIN"))
    })
    protected HasTime tm1;

    @JoinColumn(name = "NODE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase node1_Id;

    @Column(name = "NODE1__ID2")
    protected String node1_Id2;


    @Lob
    @Column(name = "GEN_DOC_VERS1__ID2")
    protected String genDocVers1_Id2;

    @JoinColumn(name = "GEN_DOC_VER1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeGenDocVer genDocVer1_Id;

    @Column(name = "GEN_DOC_VER1__ID2")
    protected String genDocVer1_Id2;

    @Column(name = "GEN_DOC_VER1__CODE")
    protected String genDocVer1_Code;

    @Column(name = "GEN_DOC_VER1__VER")
    protected String genDocVer1_Ver;

    @Column(name = "GEN_DOC_VER1__NAME1")
    protected String genDocVer1_Name1;

    @JoinColumn(name = "GEN_FILE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeGenFile genFile1_Id;

    @Column(name = "GEN_FILE1__ID2")
    protected String genFile1_Id2;

    @Lob
    @Column(name = "GEN_FILE1__URI")
    protected URI genFile1_URI;


    @JoinTable(name = "AMPATA_USR_NODE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "NODE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrItemGenTag> genTags1_Id;

    @Column(name = "GEN_TAGS1__ID2")
    protected String genTags1_Id2;

    @JoinColumn(name = "GEN_TAG1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenTag genTag1_Id;

    @Column(name = "GEN_TAG1__ID2")
    protected String genTag1_Id2;

    @JoinColumn(name = "GEN_TAG2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenTag genTag2_Id;

    @Column(name = "GEN_TAG2__ID2")
    protected String genTag2_Id2;

    @JoinColumn(name = "GEN_TAG3__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenTag genTag3_Id;

    @Column(name = "GEN_TAG3__ID2")
    protected String genTag3_Id2;

    @JoinColumn(name = "GEN_TAG4__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenTag genTag4_Id;

    @Column(name = "GEN_TAG4__ID2")
    protected String genTag4_Id2;


    @Column(name = "NAME_PRFX")
    protected String namePrfx;

    @Column(name = "NAME_FRST")
    protected String nameFrst;

    @Column(name = "NAME_MIDL")
    protected String nameMidl;

    @Column(name = "NAME_LAST")
    protected String nameLast;

    @Column(name = "NAME_SUFX")
    protected String nameSufx;

    @JoinColumn(name = "GEN_AGENT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeGenAgent genAgent1_Id;

    @Column(name = "GEN_AGENT1__ID2")
    protected String genAgent1_Id2;

    @JoinColumn(name = "GEN_AGENT2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeGenAgent genAgent2_Id;

    @Column(name = "GEN_AGENT2__ID2")
    protected String genAgent2_Id2;

    @Column(name = "VER")
    protected String ver;

    @JoinColumn(name = "GEN_CHAN1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeGenChan genChan1_Id;

    @Column(name = "GEN_CHAN1__ID2")
    protected String genChan1_Id2;

    @JoinColumn(name = "GEN_CHAN2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeGenChan genChan2_Id;

    @Column(name = "GEN_CHAN2__ID2")
    protected String genChan2_Id2;


    @Lob
    @Column(name = "FIN_TXACT_SET1__FIN_TXACTS1__ID2")
    protected String finTxactSet1_FinTxacts1_Id2;

    @Lob
    @Column(name = "FIN_TXACT_SET1__FIN_ACCTS1__ID2")
    protected String finTxactSet1_FinAccts1_Id2;

    @JoinColumn(name = "FIN_TXACT_SET1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase finTxactSet1_Id;

    @Column(name = "FIN_TXACT_SET1__ID2")
    protected String finTxactSet1_Id2;

    @Column(name = "FIN_TXACT_SET1__ID2_TRGT")
    protected String finTxactSet1_Id2Trgt;

    @Column(name = "FIN_TXACT_SET1__EI1__ROLE")
    protected String finTxactSet1_EI1_Role;

    @Column(name = "FIN_TXACT_SET1__TYPE1__ID2")
    protected String finTxactSet1_Type1_Id2;

    @Column(name = "FIN_TXACT_SET1__DESC1")
    @Lob
    protected String finTxactSet1_Desc1;


    @Column(name = "FIN_TXACT_SET1__GEN_CHAN1__ID2")
    protected String finTxactSet1_GenChan1_Id2;

    @Column(name = "FIN_TXACT_SET1__HOW1__ID2")
    protected String finTxactSet1_How1_Id2;


    @Column(name = "FIN_TXACT_SET1__WHAT_TEXT1")
    protected String finTxactSet1_WhatText1;

    @Column(name = "FIN_TXACT_SET1__WHAT1__ID2")
    protected String finTxactSet1_What1_Id2;


    @Column(name = "FIN_TXACT_SET1__WHY_TEXT1")
    protected String finTxactSet1_WhyText1;

    @Column(name = "FIN_TXACT_SET1__WHY1__ID2")
    protected String finTxactSet1_Why1_Id2;


    @JoinColumn(name = "FIN_TXACT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase finTxact1_Id;

    @Column(name = "FIN_TXACT1__ID2")
    protected String finTxact1_Id2;

    @Column(name = "FIN_TXACT1__ID2_TRGT")
    protected String finTxact1_Id2Trgt;

    @Column(name = "FIN_TXACT1__EI1__ROLE")
    protected String finTxact1_EI1_Role;

    @Column(name = "FIN_TXACT1__TYPE1__ID2")
    protected String finTxact1_Type1_Id2;

    @Column(name = "FIN_TXACT1__GEN_CHAN1__ID2")
    protected String finTxact1_GenChan1_Id2;

    @Column(name = "FIN_TXACT1__HOW1__ID2")
    protected String finTxact1_How1_Id2;


    @Column(name = "FIN_TXACT1__WHAT_TEXT1")
    protected String finTxact1_WhatText1;

    @Column(name = "FIN_TXACT1__WHAT1__ID2")
    protected String finTxact1_What1_Id2;


    @Column(name = "FIN_TXACT1__WHY_TEXT1")
    protected String finTxact1_WhyText1;

    @Column(name = "FIN_TXACT1__WHY1__ID2")
    protected String finTxact1_Why1_Id2;

    @JoinColumn(name = "FIN_STMT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase finStmt1_Id;

    @Column(name = "FIN_STMT1__ID2")
    protected String finStmt1_Id2;

    @Column(name = "EXCH_DESC")
    @Lob
    protected String exchDesc;

    @Column(name = "REF_ID")
    @Lob
    protected String refId;


    @JoinColumn(name = "FIN_STMT_ITM1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeFinStmtItm finStmtItm1_Id;

    @Column(name = "FIN_STMT_ITM1__ID2")
    protected String finStmtItm1_Id2;

    @Column(name = "FIN_STMT_ITM1__ID2_TRGT")
    protected String finStmtItm1_Id2Trgt;

    @Column(name = "FIN_STMT_ITM1__DESC1")
    @Lob
    protected String finStmtItm1_Desc1;

    @Column(name = "FIN_STMT_ITM1__DESC2")
    @Lob
    protected String finStmtItm1_Desc2;

    @Column(name = "FIN_STMT_ITM1__DESC3")
    @Lob
    protected String finStmtItm1_Desc3;

    @Column(name = "FIN_STMT_ITM1__DESC4")
    @Lob
    protected String finStmtItm1_Desc4;

    @Column(name = "FIN_STMT_ITM1__EXCH_DESC")
    protected String finStmtItm1_ExchDesc;

    @Column(name = "FIN_STMT_ITM1__REF_ID")
    protected String finStmtItm1_RefId;

    @JoinColumn(name = "FIN_ACCT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeFinAcct finAcct1_Id;

    @Column(name = "FIN_ACCT1__ID2")
    protected String finAcct1_Id2;

    @Column(name = "FIN_ACCT1__TYPE1__ID2")
    protected String finAcct1_Type1_Id2;

    //Used for doing GST aggregates
    @Column(name = "FIN_ACCT2__ID2")
    protected String finAcct2_Id2;

    @JoinColumn(name = "FIN_DEPT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeFinDept finDept1_Id;

    @Column(name = "FIN_DEPT1__ID2")
    protected String finDept1_Id2;

    @JoinColumn(name = "FIN_TAX_LNE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase finTaxLne1_Id;

    @Column(name = "FIN_TAX_LNE1__ID2")
    protected String finTaxLne1_Id2;

    @Column(name = "FIN_TAX_LNE1__CODE")
    protected String finTaxLne1_Code;

    @Column(name = "FIN_TAX_LNE1__TYPE1__ID2")
    protected String finTaxLne1_Type1_Id2;

    @JoinColumn(name = "FIN_HOW1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemFinHow finHow1_Id;

    @Column(name = "FIN_HOW1__ID2")
    protected String finHow1_Id2;

    @Column(name = "WHAT_TEXT1")
    protected String whatText1;

    @JoinColumn(name = "FIN_WHAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemFinWhat finWhat1_Id;

    @Column(name = "FIN_WHAT1__ID2")
    protected String finWhat1_Id2;

    @Column(name = "WHY_TEXT1")
    protected String whyText1;

    @JoinColumn(name = "FIN_WHY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemFinWhy finWhy1_Id;

    @Column(name = "FIN_WHY1__ID2")
    protected String finWhy1_Id2;

    @Column(name = "AMT_DEBT", precision = 19, scale = 2)
    protected BigDecimal amtDebt;

    @Column(name = "AMT_CRED", precision = 19, scale = 2)
    protected BigDecimal amtCred;

    @Column(name = "AMT_NET", precision = 19, scale = 2)
    protected BigDecimal amtNet;

    @JoinColumn(name = "SYS_NODE_FIN_CURCY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysNodeBase sysNodeFinCurcy1_Id;

    @Column(name = "SYS_NODE_FIN_CURCY1__ID2")
    protected String sysNodeFinCurcy1_Id2;

    @JoinColumn(name = "AMT_CALC_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla amtCalcGenFmla1_Id;

    @Column(name = "AMT_CALC_GEN_FMLA1__ID2")
    protected String amtCalcGenFmla1_Id2;

    @JoinColumn(name = "AMT_FIN_TXACT_ITM1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeBase amtFinTxactItm1_Id;

    @Column(name = "AMT_FIN_TXACT_ITM1__ID2")
    protected String amtFinTxactItm1_Id2;

    @Column(name = "AMT_FIN_TXACT_ITM1__EI1__RATE", precision = 19, scale = 9)
    protected BigDecimal amtFinTxactItm1_EI1_Rate;

    @Column(name = "AMT_FIN_TXACT_ITM1__FIN_ACCT1__ID2")
    protected String amtFinTxactItm1_FinAcct1_Id2;

    @Column(name = "AMT_CALC", precision = 19, scale = 2)
    protected BigDecimal amtCalc;


    @Column(name = "AMT_BEG_BAL", precision = 19, scale = 2)
    protected BigDecimal amtBegBal;

    @Column(name = "AMT_BEG_BAL_CALC", precision = 19, scale = 2)
    protected BigDecimal amtBegBalCalc;


    @Column(name = "AMT_END_BAL", precision = 19, scale = 2)
    protected BigDecimal amtEndBal;

    @Column(name = "AMT_END_BAL_CALC", precision = 19, scale = 2)
    protected BigDecimal amtEndBalCalc;


    @JoinColumn(name = "FIN_BAL1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeFinBal finBal1_Id;

    @Column(name = "FIN_BAL1__ID2")
    protected String finBal1_Id2;


    @JoinColumn(name = "FIN_BAL_SET1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeFinBalSet finBalSet1_Id;

    @Column(name = "FIN_BAL_SET1__ID2")
    protected String finBalSet1_Id2;


    // Edge to FinTxact1->FinTxactItms1
    @Lob
    @Column(name = "FIN_TXACT1__FIN_TXACT_ITMS1__ID2")
    protected String finTxact1_FinTxactItms1_Id2;

    // Edge to FinTxact1->FinAccts1
    @Lob
    @Column(name = "FIN_TXACT1__FIN_ACCTS1__ID2")
    protected String finTxact1_FinAccts1_Id2;


    // Edge to FinBals
    @Column(name = "FIN_BALS1__ID2")
    @Lob
    protected String finBals1_Id2;


    @Column(name = "FIN_BALS1__ID_CNT_CALC")
    protected Integer finBals1_IdCntCalc;

    @Column(name = "FIN_BALS1__AMT_EQ_CALC")
    protected Boolean finBals1_AmtEqCalc;


    // Edge to FinTxactItms
    @Column(name = "FIN_TXACT_ITMS1__ID2")
    @Lob
    protected String finTxactItms1_Id2;

    @Column(name = "FIN_TXACT_ITMS1__FIN_ACCTS1__ID2")
    @Lob
    protected String finTxactItms1_FinAccts1_Id2;


    @Column(name = "FIN_TXACT_ITMS1__ID_CNT_CALC")
    protected Integer finTxactItms1_IdCntCalc;

    @Column(name = "FIN_TXACT_ITMS1__AMT_DEBT_SUM_CALC", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtDebtSumCalc;

    @Column(name = "FIN_TXACT_ITMS1__AMT_CRED_SUM_CALC", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtCredSumCalc;

    @Column(name = "FIN_TXACT_ITMS1__AMT_NET_SUM_CALC", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtNetSumCalc;

    @Column(name = "FIN_TXACT_ITMS1__AMT_EQ_CALC")
    protected Boolean finTxactItms1_AmtEqCalc;

    @Column(name = "FIN_TXACT_ITMS1__SYS_NODE_FIN_CURCY_CALC")
    protected Boolean finTxactItms1_SysNodeFinCurcyEqCalc;


    @Column(name = "FIN_TXACT_ITMS1__AMT_DEBT_SUM_DIFF", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtDebtSumDiff;

    @Column(name = "FIN_TXACT_ITMS1__AMT_CRED_SUM_DIFF", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtCredSumDiff;

    @Column(name = "FIN_TXACT_ITMS1__AMT_NET_SUM_DIFF", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtNetSumDiff;

    @Column(name = "FIN_TXACT_ITMS1__AMT_EQ_DIFF")
    protected Boolean finTxactItms1_AmtEqDiff;


    // Edge to FinTxacts
    @Column(name = "FIN_TXACTS1__ID2")
    @Lob
    protected String finTxacts1_Id2;

    @Column(name = "FIN_TXACTS1__FIN_ACCTS1__ID2")
    @Lob
    protected String finTxacts1_FinAccts1_Id2;

    @Column(name = "FIN_TXACTS1__ID_CNT_CALC")
    protected Integer finTxacts1_IdCntCalc;

    @Column(name = "FIN_TXACTS1__AMT_EQ_CALC")
    protected Boolean finTxacts1_AmtEqCalc;


    // Edge to FinStmtItms

    @Column(name = "FIN_STMT_ITMS1__ID_CNT_CALC")
    protected Integer finStmtItms1_IdCntCalc;

    @Column(name = "FIN_STMT_ITMS1__AMT_DEBT_SUM_CALC", precision = 19, scale = 2)
    protected BigDecimal finStmtItms1_AmtDebtSumCalc;

    @Column(name = "FIN_STMT_ITMS1__AMT_CRED_SUM_CALC", precision = 19, scale = 2)
    protected BigDecimal finStmtItms1_AmtCredSumCalc;

    @Column(name = "FIN_STMT_ITMS1__AMT_NET_SUM_CALC", precision = 19, scale = 2)
    protected BigDecimal finStmtItms1_AmtNetSumCalc;

    @Column(name = "FIN_STMT_ITMS1__AMT_EQ_CALC")
    protected Boolean finStmtItms1_AmtEqCalc;


    @Column(name = "FIN_STMT_ITMS1__AMT_DEBT_SUM_DIFF", precision = 19, scale = 2)
    protected BigDecimal finStmtItms1_AmtDebtSumDiff;

    @Column(name = "FIN_STMT_ITMS1__AMT_CRED_SUM_DIFF", precision = 19, scale = 2)
    protected BigDecimal finStmtItms1_AmtCredSumDiff;

    @Column(name = "FIN_STMT_ITMS1__AMT_NET_SUM_DIFF", precision = 19, scale = 2)
    protected BigDecimal finStmtItms1_AmtNetSumDiff;

    @Column(name = "FIN_STMT_ITMS1__AMT_EQ_DIFF")
    protected Boolean finStmtItms1_AmtEqDiff;


    // Edge to FinTxactSet1.GenDocVers1
    @Lob
    @Column(name = "FIN_TXACT_SET1__GEN_DOC_VERS1__ID2")
    protected String finTxactSet1_GenDocVers1_Id2;

    // Edge to FinTxact1.GenDocVers1
    @Lob
    @Column(name = "FIN_TXACT1__GEN_DOC_VERS1__ID2")
    protected String finTxact1_GenDocVers1_Id2;

    // Edge to FinTxactSet1.GenTags1
    @Lob
    @Column(name = "FIN_TXACT_SET1__GEN_TAGS1__ID2")
    protected String finTxactSet1_GenTags1_Id2;

    // Edge to FinTxact1.GenTags1
    @Lob
    @Column(name = "FIN_TXACT1__GEN_TAGS1__ID2")
    protected String finTxact1_GenTags1_Id2;


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


    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    @Override
    public String getTenantId() { return tenant; }

    public String getTenant() { return tenant; }

    public void setTenant(String tenant) { this.tenant = tenant; }


    public String getDtype() {return dtype; }

    public String getClassName() { return className; }

    public void setClassName(String className) { this.className = className; }


    public String getId2() { return id2; }

    public void setId2(String id2) { this.id2 = id2; }

    public String getId2Calc() { return id2Calc; }

    public void setId2Calc(String id2Calc) { this.id2Calc = id2Calc; }

    public Boolean getId2Cmp() { return id2Cmp; }

    public void setId2Cmp(Boolean id2Cmp) { this.id2Cmp = id2Cmp; }

    public Integer getId2Dup() { return id2Dup; }

    public void setId2Dup(Integer id2Dup) { this.id2Dup = id2Dup; }


    public UsrNodeBase getParent1_Id() { return parent1_Id; }

    public void setParent1_Id(UsrNodeBase parent1_Id) { this.parent1_Id = parent1_Id; }

    public String getParent1_Id2() { return parent1_Id2; }

    public void setParent1_Id2(String parent1_Id2) { this.parent1_Id2 = parent1_Id2; }

    public String getAncestors1_Id2() { return ancestors1_Id2; }

    public void setAncestors1_Id2(String ancestors1_Id2) { this.ancestors1_Id2 = ancestors1_Id2; }


    public Integer getSortIdx() { return sortIdx; }

    public void setSortIdx(Integer sortIdx) { this.sortIdx = sortIdx; }

    public String getSortKey() { return sortKey; }

    public void setSortKey(String sortKey) { this.sortKey = sortKey; }

    public String getName1() { return name1; }

    public void setName1(String name1) { this.name1 = name1; }


    public UsrItemGenSel getName1GenSel1_Id() { return name1GenSel1_Id; }

    public void setName1GenSel1_Id(UsrItemGenSel name1GenSel1_Id) { this.name1GenSel1_Id = name1GenSel1_Id; }

    public String getName1GenSel1_Id2() { return name1GenSel1_Id2; }

    public void setName1GenSel1_Id2(String name1GenSel1_Id2) { this.name1GenSel1_Id2 = name1GenSel1_Id2; }


    public UsrItemGenFmla getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(UsrItemGenFmla name1GenFmla1_Id) { this.name1GenFmla1_Id = name1GenFmla1_Id; }

    public String getName1GenFmla1_Id2() { return name1GenFmla1_Id2; }

    public void setName1GenFmla1_Id2(String name1GenFmla1_Id2) { this.name1GenFmla1_Id2 = name1GenFmla1_Id2; }

    public void setType1_Id(UsrNodeBaseType type1_Id) { this.type1_Id = type1_Id; }

    public UsrNodeBaseType getType1_Id() { return type1_Id; }

    public String getType1_Id2() { return type1_Id2; }

    public void setType1_Id2(String type1_Id2) { this.type1_Id2 = type1_Id2; }


    public String getInst1() { return inst1; }

    public void setInst1(String inst1) { this.inst1 = inst1; }

    
    public UsrNodeBaseType getNm1s1Type1_Id() { return nm1s1Type1_Id; }

    public void setNm1s1Type1_Id(UsrNodeBaseType nm1s1Type1_Id) { this.nm1s1Type1_Id = nm1s1Type1_Id; }


    public String getNm1s1Type1_Id2() { return nm1s1Type1_Id2; }

    public void setNm1s1Type1_Id2(String nm1s1Type1_Id2) { this.nm1s1Type1_Id2 = nm1s1Type1_Id2; }


    public String getNm1s1Name1() { return nm1s1Name1; }

    public void setNm1s1Name1(String nm1s1Name1) { this.nm1s1Name1 = nm1s1Name1; }

    public UsrItemGenFmla getNm1s1Name1GenFmla1_Id() { return nm1s1Name1GenFmla1_Id; }

    public void setNm1s1Name1GenFmla1_Id(UsrItemGenFmla nm1s1Name1GenFmla1_Id) { this.nm1s1Name1GenFmla1_Id = nm1s1Name1GenFmla1_Id; }

    
    public String getNm1s1Name1GenFmla1_Id2() { return nm1s1Name1GenFmla1_Id2; }

    public void setNm1s1Name1GenFmla1_Id2(String nm1s1Name1GenFmla1_Id2) { this.nm1s1Name1GenFmla1_Id2 = nm1s1Name1GenFmla1_Id2; }


    public String getNm1s1Inst1() { return nm1s1Inst1; }

    public void setNm1s1Inst1(String nm1s1Inst1) { this.nm1s1Inst1 = nm1s1Inst1; }

    public UsrItemGenFmla getNm1s1Inst1GenFmla1_Id() { return nm1s1Inst1GenFmla1_Id; }

    public void setNm1s1Inst1GenFmla1_Id(UsrItemGenFmla nm1s1Inst1GenFmla1_Id) { this.nm1s1Inst1GenFmla1_Id = nm1s1Inst1GenFmla1_Id; }


    public String getNm1s1Inst1GenFmla1_Id2() { return nm1s1Inst1GenFmla1_Id2; }

    public void setNm1s1Inst1GenFmla1_Id2(String nm1s1Inst1GenFmla1_Id2) { this.nm1s1Inst1GenFmla1_Id2 = nm1s1Inst1GenFmla1_Id2; }


    public HasTmst getNm1s1Inst1Ts1() { return nm1s1Inst1Ts1; }

    public void setNm1s1Inst1Ts1(HasTmst nm1s1Inst1Ts1) { this.nm1s1Inst1Ts1 = nm1s1Inst1Ts1; }

    public HasDate getNm1s1Inst1Dt1() { return nm1s1Inst1Dt1; }

    public void setNm1s1Inst1Dt1(HasDate nm1s1Inst1Dt1) { this.nm1s1Inst1Dt1 = nm1s1Inst1Dt1; }

    public HasTime getNm1s1Inst1Tm1() { return nm1s1Inst1Tm1; }

    public void setNm1s1Inst1Tm1(HasTime nm1s1Inst1Tm1) { this.nm1s1Inst1Tm1 = nm1s1Inst1Tm1; }


    public Integer getNm1s1Inst1Int1() { return nm1s1Inst1Int1; }

    public void setNm1s1Inst1Int1(Integer nm1s1Inst1Int1) { this.nm1s1Inst1Int1 = nm1s1Inst1Int1; }

    public Integer getNm1s1Inst1Int2() { return nm1s1Inst1Int2; }

    public void setNm1s1Inst1Int2(Integer nm1s1Inst1Int2) { this.nm1s1Inst1Int2 = nm1s1Inst1Int2; }

    public Integer getNm1s1Inst1Int3() { return nm1s1Inst1Int3; }

    public void setNm1s1Inst1Int3(Integer nm1s1Inst1Int3) { this.nm1s1Inst1Int3 = nm1s1Inst1Int3;}


    public String getNm1s1Inst1Txt1() { return nm1s1Inst1Txt1; }

    public void setNm1s1Inst1Txt1(String nm1s1Inst1Txt1) { this.nm1s1Inst1Txt1 = nm1s1Inst1Txt1; }

    public String getNm1s1Inst1Txt2() { return nm1s1Inst1Txt2; }

    public void setNm1s1Inst1Txt2(String nm1s1Inst1Txt2) { this.nm1s1Inst1Txt2 = nm1s1Inst1Txt2; }

    public String getNm1s1Inst1Txt3() { return nm1s1Inst1Txt3; }

    public void setNm1s1Inst1Txt3(String nm1s1Inst1Txt3) { this.nm1s1Inst1Txt3 = nm1s1Inst1Txt3; }


    public UsrNodeBase getNm1s1Inst1Node1_Id() { return nm1s1Inst1Node1_Id; }

    public void setNm1s1Inst1Node1_Id(UsrNodeBase nm1s1Inst1Node1_Id) { this.nm1s1Inst1Node1_Id = nm1s1Inst1Node1_Id; }


    public String getNm1s1Inst1Node1_Id2() { return nm1s1Inst1Node1_Id2; }


    public void setNm1s1Inst1Node1_Id2(String nm1s1Inst1Node1_Id2) { this.nm1s1Inst1Node1_Id2 = nm1s1Inst1Node1_Id2; }

    public String getName2() { return name2; }

    public void setName2(String name2) { this.name2 = name2; }


    public String getAbrv() { return abrv; }

    public void setAbrv(String abrv) { this.abrv = abrv; }

    public String getCode() { return code; }

    public void setCode(String code) { this.abrv = code; }

    public String getDesc1() { return desc1; }

    public void setDesc1(String desc1) { this.desc1 = desc1; }


    public UsrItemGenFmla getDesc1GenFmla1_Id() { return desc1GenFmla1_Id; }

    public void setDesc1GenFmla1_Id(UsrItemGenFmla desc1GenFmla1_Id) { this.desc1GenFmla1_Id = desc1GenFmla1_Id; }

    public String getDesc1GenFmla1_Id2() { return desc1GenFmla1_Id2; }

    public void setDesc1GenFmla1_Id2(String desc1GenFmla1_Id2) { this.desc1GenFmla1_Id2 = desc1GenFmla1_Id2; }


    public UsrNodeBase getDesc1Node1_Id() { return desc1Node1_Id; }

    public void setDesc1Node1_Id(UsrNodeBase desc1Node1_Id) { this.desc1Node1_Id = desc1Node1_Id; }

    public String getDesc1Node1_Id2() { return desc1Node1_Id2; }

    public void setDesc1Node1_Id2(String desc1Node1_Id2) { this.desc1Node1_Id2 = desc1Node1_Id2; }


    public UsrNodeBase getDesc1Node2_Id() { return desc1Node2_Id; }

    public void setDesc1Node2_Id(UsrNodeBase desc1Node2_Id) { this.desc1Node2_Id = desc1Node2_Id; }

    public String getDesc1Node2_Id2() { return desc1Node2_Id2; }

    public void setDesc1Node2_Id2(String desc1Node2_Id2) { this.desc1Node2_Id2 = desc1Node2_Id2; }


    public String getDesc2() { return desc2; }

    public void setDesc2(String desc2) { this.desc2 = desc2; }


    public String getDesc3() { return desc3; }

    public void setDesc3(String desc3) {this.desc3 = desc3;}


    public String getDesc4() { return desc4; }

    public void setDesc4(String desc4) { this.desc4 = desc4; }



    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }



    public List<UsrEdgeBase> getEdgeIns_Id() {
        return edgeIns_Id;
    }

    public void setEdgeIns_Id(List<UsrEdgeBase> edgeIns_Id) {
        this.edgeIns_Id = edgeIns_Id;
    }

    public String getEdgeIns_Id2() {
        return edgeIns_Id2;
    }

    public void setEdgeIns_Id2(String edgeIns_Id2) {
        this.edgeIns_Id2 = edgeIns_Id2;
    }


    public List<UsrEdgeBase> getEdgeOts_Id() {
        return edgeOts_Id;
    }

    public void setEdgeOts_Id(List<UsrEdgeBase> edgeOts_Id) {
        this.edgeOts_Id = edgeOts_Id;
    }

    public String getEdgeOts_Id2() {
        return edgeOts_Id2;
    }

    public void setEdgeOts_Id2(String edgeOts_Id2) {
        this.edgeOts_Id2 = edgeOts_Id2;
    }

    public UsrNodeGenChan getGenChan1_Id() { return genChan1_Id; }

    public void setGenChan1_Id(UsrNodeGenChan genChan1_Id) { this.genChan1_Id = genChan1_Id; }

    public String getGenChan1_Id2() { return genChan1_Id2; }

    public void setGenChan1_Id2(String genChan1_Id2) { this.genChan1_Id2 = genChan1_Id2; }

    public UsrNodeGenChan getGenChan2_Id() {return genChan2_Id;}

    public void setGenChan2_Id(UsrNodeGenChan genChan2_Id) {this.genChan2_Id = genChan2_Id;}

    public String getGenChan2_Id2() { return genChan2_Id2;}

    public void setGenChan2_Id2(String genChan2_Id2) {this.genChan2_Id2 = genChan2_Id2;}




    public String getTxt1() { return txt1; }

    public void setTxt1(String txt1) { this.txt1 = txt1; }

    public String getTxt2() { return txt2; }

    public void setTxt2(String txt2) { this.txt2 = txt2; }

    public String getTxt3() { return txt3; }

    public void setTxt3(String txt3) { this.txt3 = txt3; }


    public Integer getInt1() { return int1; }

    public void setInt1(Integer int1) { this.int1 = int1; }

    public Integer getInt2() { return int2; }

    public void setInt2(Integer int2) { this.int2 = int2; }

    public Integer getInt3() { return int3; }

    public void setInt3(Integer int3) { this.int3 = int3; }

    public HasTmst getTs1() { return ts1; }

    public void setTs1(HasTmst ts1) { this.ts1 = ts1; }


    public HasTmst getTs2() { return ts2; }

    public void setTs2(HasTmst ts2) { this.ts2 = ts2; }


    public void setTs3(HasTmst ts3) { this.ts3 = ts3; }

    public HasTmst getTs3() { return ts3; }


    public HasTmst getTs4() { return ts4; }

    public void setTs4(HasTmst ts4) { this.ts4 = ts4; }


    public UsrNodeBase getNode1_Id() { return node1_Id; }

    public void setNode1_Id(UsrNodeBase node1_Id) { this.node1_Id = node1_Id; }

    public String getNode1_Id2() { return node1_Id2; }

    public void setNode1_Id2(String node1_Id2) { this.node1_Id2 = node1_Id2; }


    public HasDate getDt1() { return dt1; }

    public void setDt1(HasDate dt1) { this.dt1 = dt1; }


    public HasTime getTm1() { return tm1; }

    public void setTm1(HasTime tm1) { this.tm1 = tm1; }


    public String getGenDocVers1_Id2() { return genDocVers1_Id2; }

    public void setGenDocVers1_Id2(String genDocVers1_Id2) { this.genDocVers1_Id2 = genDocVers1_Id2; }

    public UsrNodeGenDocVer getGenDocVer1_Id() { return genDocVer1_Id; }

    public void setGenDocVer1_Id(UsrNodeGenDocVer genDocVer1_Id) { this.genDocVer1_Id = genDocVer1_Id; }

    public String getGenDocVer1_Id2() { return genDocVer1_Id2; }

    public void setGenDocVer1_Id2(String genDocVer1_Id2) { this.genDocVer1_Id2 = genDocVer1_Id2; }

    public String getGenDocVer1_Code() { return genDocVer1_Code; }

    public String getGenDocVer1_Ver() { return genDocVer1_Ver; }

    public String getGenDocVer1_Name1() { return genDocVer1_Name1; }


    public UsrNodeGenFile getGenFile1_Id() { return genFile1_Id; }

    public void setGenFile1_Id(UsrNodeGenFile genFile1_Id) { this.genFile1_Id = genFile1_Id; }

    public String getGenFile1_Id2() { return genFile1_Id2; }

    public void setGenFile1_Id2(String genFile1_Id2) { this.genFile1_Id2 = genFile1_Id2; }

    public URI getGenFile1_URI() { return genFile1_URI; }

    public void setGenFile1_URI(URI genFile1_URI) { this.genFile1_URI = genFile1_URI; }


    public String getFinTxact1_GenDocVers1_Id2() { return finTxact1_GenDocVers1_Id2; }

    public String getFinTxact1_GenTags1_Id2() { return finTxact1_GenTags1_Id2; }


    public List<UsrItemGenTag> getGenTags1_Id() { return genTags1_Id; }

    public void setGenTags1_Id(List<UsrItemGenTag> genTags1_Id) { this.genTags1_Id = genTags1_Id; }


    public String getGenTags1_Id2() { return genTags1_Id2; }

    public void setGenTags1_Id2(String genTags1_Id2) { this.genTags1_Id2 = genTags1_Id2; }


    public UsrItemGenTag getGenTag1_Id() { return genTag1_Id; }

    public void setGenTag1_Id(UsrItemGenTag genTag1_Id) { this.genTag1_Id = genTag1_Id; }

    public String getGenTag1_Id2() { return genTag1_Id2; }

    public void setGenTag1_Id2(String genTag1_Id2) { this.genTag1_Id2 = genTag1_Id2; }


    public UsrItemGenTag getGenTag2_Id() { return genTag2_Id; }

    public void setGenTag2_Id(UsrItemGenTag genTag2_Id) { this.genTag2_Id = genTag2_Id; }

    public String getGenTag2_Id2() { return genTag2_Id2; }

    public void setGenTag2_Id2(String genTag2_Id2) { this.genTag2_Id2 = genTag2_Id2; }


    public UsrItemGenTag getGenTag3_Id() { return genTag3_Id; }

    public void setGenTag3_Id(UsrItemGenTag genTag3_Id) { this.genTag3_Id = genTag3_Id; }

    public String getGenTag3_Id2() { return genTag3_Id2; }

    public void setGenTag3_Id2(String genTag3_Id2) { this.genTag3_Id2 = genTag3_Id2; }


    public UsrItemGenTag getGenTag4_Id() { return genTag4_Id; }

    public void setGenTag4_Id(UsrItemGenTag genTag4_Id) { this.genTag4_Id = genTag4_Id; }

    public String getGenTag4_Id2() { return genTag4_Id2; }

    public void setGenTag4_Id2(String genTag4_Id2) { this.genTag4_Id2 = genTag4_Id2; }



    public String getVer() { return ver; }

    public void setVer(String ver) { this.ver = ver;}

    
    public UsrNodeGenAgent getGenAgent1_Id() { return genAgent1_Id; }

    public void setGenAgent1_Id(UsrNodeGenAgent genAgent1_Id) { this.genAgent1_Id = genAgent1_Id; }

    public String getGenAgent1_Id2() { return genAgent1_Id2; }

    public void setGenAgent1_Id2(String genAgent1_Id2) { this.genAgent1_Id2 = genAgent1_Id2; }


    public UsrNodeGenAgent getGenAgent2_Id() { return genAgent2_Id; }

    public void setGenAgent2_Id(UsrNodeGenAgent genAgent2_Id) { this.genAgent2_Id = genAgent2_Id; }

    public String getGenAgent2_Id2() { return genAgent2_Id2; }

    public void setGenAgent2_Id2(String genAgent2_Id2) { this.genAgent2_Id2 = genAgent2_Id2; }


    public String getNamePrfx() { return namePrfx; }

    public void setNamePrfx(String namePrfx) { this.namePrfx = namePrfx; }

    public String getNameFrst() { return nameFrst; }

    public void setNameFrst(String nameFrst) { this.nameFrst = nameFrst; }

    public String getNameMidl() { return nameMidl; }

    public void setNameMidl(String nameMidl) { this.nameMidl = nameMidl; }

    public String getNameLast() { return nameLast; }

    public void setNameLast(String nameLast) { this.nameLast = nameLast; }

    public String getNameSufx() { return nameSufx; }

    public void setNameSufx(String nameSufx) { this.nameSufx = nameSufx; }



    public String getFinTxactSet1_FinAccts1_Id2() { return finTxactSet1_FinAccts1_Id2; }

    public String getFinTxactSet1_FinTxacts1_Id2() { return finTxactSet1_FinTxacts1_Id2; }

    public void setFinTxactSet1_FinTxacts1_Id2(String finTxactSet1_FinTxacts1_Id2) {this.finTxactSet1_FinTxacts1_Id2 = finTxactSet1_FinTxacts1_Id2;}


    public UsrNodeBase getFinTxactSet1_Id() { return finTxactSet1_Id; }

    public void setFinTxactSet1_Id(UsrNodeBase finTxactSet1_Id) { this.finTxactSet1_Id = finTxactSet1_Id; }

    public String getFinTxactSet1_Id2() { return finTxactSet1_Id2; }

    public void setFinTxactSet1_Id2(String finTxactSet1_Id2) { this.finTxactSet1_Id2 = finTxactSet1_Id2; }

    public String getFinTxactSet1_Id2Trgt() { return finTxactSet1_Id2Trgt; }

    public void setFinTxactSet1_Id2Trgt(String finTxactSet1_Id2Trgt) {this.finTxactSet1_Id2Trgt = finTxactSet1_Id2Trgt;}

    public String getFinTxactSet1_Type1_Id2() { return finTxactSet1_Type1_Id2; }

    public void setFinTxactSet1_Type1_Id2(String finTxactSet1_Type1_Id2) { this.finTxactSet1_Type1_Id2 = finTxactSet1_Type1_Id2; }


    public String getFinTxactSet1_Desc1() { return finTxactSet1_Desc1; }

    public void setFinTxactSet1_Desc1(String finTxactSet1_Desc1) { this.finTxactSet1_Desc1 = finTxactSet1_Desc1; }

    public String getFinTxactSet1_EI1_Role() { return finTxactSet1_EI1_Role; }

    public void setFinTxactSet1_EI1_Role(String finTxactSet1_EI1_Role) { this.finTxactSet1_EI1_Role = finTxactSet1_EI1_Role; }

    public String getFinTxactSet1_GenTags1_Id2() { return finTxactSet1_GenTags1_Id2; }

    public void setFinTxactSet1_GenTags1_Id2(String finTxactSet1_GenTags1_Id2) { this.finTxactSet1_GenTags1_Id2 = finTxactSet1_GenTags1_Id2; }

    public String getFinTxactSet1_GenDocVers1_Id2() { return finTxactSet1_GenDocVers1_Id2; }

    public void setFinTxactSet1_GenDocVers1_Id2(String finTxactSet1_GenDocVers1_Id2) { this.finTxactSet1_GenDocVers1_Id2 = finTxactSet1_GenDocVers1_Id2; }



    public String getFinTxactSet1_GenChan1_Id2() { return finTxactSet1_GenChan1_Id2; }

    public void setFinTxactSet1_GenChan1_Id2(String finTxactSet1_GenChan1_Id2) { this.finTxactSet1_GenChan1_Id2 = finTxactSet1_GenChan1_Id2; }



    public String getFinTxactSet1_How1_Id2() { return finTxactSet1_How1_Id2; }

    public void setFinTxactSet1_How1_Id2(String finTxactSet1_How1_Id2) { this.finTxactSet1_How1_Id2 = finTxactSet1_How1_Id2; }

    public String getFinTxactSet1_WhatText1() { return finTxactSet1_WhatText1; }

    public void setFinTxactSet1_WhatText1(String finTxactSet1_WhatText1) { this.finTxactSet1_WhatText1 = finTxactSet1_WhatText1; }


    public String getFinTxactSet1_What1_Id2() { return finTxactSet1_What1_Id2; }

    public void setFinTxactSet1_What1_Id2(String finTxactSet1_What1_Id2) { this.finTxactSet1_What1_Id2 = finTxactSet1_What1_Id2; }


    public String getFinTxactSet1_WhyText1() { return finTxactSet1_WhyText1; }

    public void setFinTxactSet1_WhyText1(String finTxactSet1_WhyText1) { this.finTxactSet1_WhyText1 = finTxactSet1_WhyText1; }

    public String getFinTxactSet1_Why1_Id2() { return finTxactSet1_Why1_Id2; }

    public void setFinTxactSet1_Why1_Id2(String finTxactSet1_Why1_Id2) { this.finTxactSet1_Why1_Id2 = finTxactSet1_Why1_Id2; }





    public UsrNodeBase getFinTxact1_Id() {return finTxact1_Id; }

    public void setFinTxact1_Id(UsrNodeBase finTxact1_Id) { this.finTxact1_Id = finTxact1_Id; }

    public String getFinTxact1_Id2() { return finTxact1_Id2; }

    public void setFinTxact1_Id2(String finTxact1_Id2) { this.finTxact1_Id2 = finTxact1_Id2; }

    public String getFinTxact1_Id2Trgt() { return finTxact1_Id2Trgt;}

    public void setFinTxact1_Id2Trgt(String finTxact1_Id2Trgt) { this.finTxact1_Id2Trgt = finTxact1_Id2Trgt;}

    public String getFinTxact1_Type1_Id2() { return finTxact1_Type1_Id2; }

    public void setFinTxact1_Type1_Id2(String finTxact1_Type1_Id2) { this.finTxact1_Type1_Id2 = finTxact1_Type1_Id2; }

    public String getFinTxact1_EI1_Role() { return finTxact1_EI1_Role; }

    public void setFinTxact1_EI1_Role(String finTxact1_EI1_Role) { this.finTxact1_EI1_Role = finTxact1_EI1_Role; }


    public String getFinTxact1_GenChan1_Id2() { return finTxact1_GenChan1_Id2; }

    public void setFinTxact1_GenChan1_Id2(String finTxact1_GenChan1_Id2) { this.finTxact1_GenChan1_Id2 = finTxact1_GenChan1_Id2; }


    public String getFinTxact1_How1_Id2() { return finTxact1_How1_Id2; }

    public void setFinTxact1_How1_Id2(String finTxact1_How1_Id2) { this.finTxact1_How1_Id2 = finTxact1_How1_Id2; }


    public String getFinTxact1_WhatText1() { return finTxact1_WhatText1; }

    public void setFinTxact1_WhatText1(String finTxact1_WhatText1) { this.finTxact1_WhatText1 = finTxact1_WhatText1; }

    public String getFinTxact1_What1_Id2() { return finTxact1_What1_Id2; }

    public void setFinTxact1_What1_Id2(String finTxact1_What1_Id2) { this.finTxact1_What1_Id2 = finTxact1_What1_Id2; }


    public String getFinTxact1_WhyText1() { return finTxact1_WhyText1; }

    public void setFinTxact1_WhyText1(String finTxact1_WhyText1) { this.finTxact1_WhyText1 = finTxact1_WhyText1; }

    public String getFinTxact1_Why1_Id2() { return finTxact1_Why1_Id2; }

    public void setFinTxact1_Why1_Id2(String finTxact1_Why1_Id2) { this.finTxact1_Why1_Id2 = finTxact1_Why1_Id2; }






    public String getFinStmt1_Id2() { return finStmt1_Id2; }

    public void setFinStmt1_Id2(String finStmt1_Id2) { this.finStmt1_Id2 = finStmt1_Id2; }

    public UsrNodeBase getFinStmt1_Id() { return finStmt1_Id; }

    public void setFinStmt1_Id(UsrNodeBase finStmt1_Id) { this.finStmt1_Id = finStmt1_Id; }

    public String getExchDesc() { return exchDesc; }

    public void setExchDesc(String exchDesc) { this.exchDesc = exchDesc; }

    public String getRefId() { return refId; }

    public void setRefId(String refId) { this.refId = refId; }


    public UsrNodeFinStmtItm getFinStmtItm1_Id() { return finStmtItm1_Id; }

    public void setFinStmtItm1_Id(UsrNodeFinStmtItm finStmtItm1_Id) { this.finStmtItm1_Id = finStmtItm1_Id; }

    public String getFinStmtItm1_Id2() { return finStmtItm1_Id2; }

    public void setFinStmtItm1_Id2(String finStmtItm1_Id2) { this.finStmtItm1_Id2 = finStmtItm1_Id2; }

    public String getFinStmtItm1_Id2Trgt() { return finStmtItm1_Id2Trgt; }

    public void setFinStmtItm1_Id2Trgt(String finStmtItm1_Id2Trgt) {this.finStmtItm1_Id2Trgt = finStmtItm1_Id2Trgt;}

    public String getFinStmtItm1_Desc1() { return finStmtItm1_Desc1; }

    public void setFinStmtItm1_Desc1(String finStmtItm1_Desc1) { this.finStmtItm1_Desc1 = finStmtItm1_Desc1; }

    public String getFinStmtItm1_Desc2() { return finStmtItm1_Desc2; }

    public void setFinStmtItm1_Desc2(String finStmtItm1_Desc2) { this.finStmtItm1_Desc2 = finStmtItm1_Desc2; }

    public String getFinStmtItm1_Desc3() { return finStmtItm1_Desc3; }

    public void setFinStmtItm1_Desc3(String finStmtItm1_Desc3) { this.finStmtItm1_Desc3 = finStmtItm1_Desc3; }

    public String getFinStmtItm1_Desc4() { return finStmtItm1_Desc4; }

    public void setFinStmtItm1_Desc4(String finStmtItm1_Desc4) { this.finStmtItm1_Desc4 = finStmtItm1_Desc4; }

    public String getFinStmtItm1_ExchDesc() { return finStmtItm1_ExchDesc; }

    public void setFinStmtItm1_ExchDesc(String finStmtItm1_ExchDesc) { this.finStmtItm1_ExchDesc = finStmtItm1_ExchDesc; }

    public String getFinAcct1_Type1_Id2() { return finAcct1_Type1_Id2; }

    public String getFinStmtItm1_RefId() {return finStmtItm1_RefId;}

    public void setFinStmtItm1_RefId(String finStmtItm1_RefId) {this.finStmtItm1_RefId = finStmtItm1_RefId;}

    public void setFinAcct1_Type1_Id2(String finAcct1_Type1_Id2) { this.finAcct1_Type1_Id2 = finAcct1_Type1_Id2; }


    public UsrNodeFinAcct getFinAcct1_Id() { return finAcct1_Id; }

    public void setFinAcct1_Id(UsrNodeFinAcct finAcct1_Id) { this.finAcct1_Id = finAcct1_Id; }

    public String getFinAcct1_Id2() { return finAcct1_Id2; }

    public void setFinAcct1_Id2(String finAcct1_Id2) { this.finAcct1_Id2 = finAcct1_Id2; }


    public UsrNodeFinDept getFinDept1_Id() { return finDept1_Id; }

    public void setFinDept1_Id(UsrNodeFinDept finDept1_Id) { this.finDept1_Id = finDept1_Id; }

    public String getFinDept1_Id2() { return finDept1_Id2; }

    public void setFinDept1_Id2(String finDept1_Id2) { this.finDept1_Id2 = finDept1_Id2; }




    public SysNodeBase getSysNodeFinCurcy1_Id() { return sysNodeFinCurcy1_Id; }

    public void setSysNodeFinCurcy1_Id(SysNodeBase sysNodeFinCurcy1_Id) { this.sysNodeFinCurcy1_Id = sysNodeFinCurcy1_Id; }

    public String getSysNodeFinCurcy1_Id2() { return sysNodeFinCurcy1_Id2; }

    public void setSysNodeFinCurcy1_Id2(String sysNodeFinCurcy1_Id2) { this.sysNodeFinCurcy1_Id2 = sysNodeFinCurcy1_Id2; }




    public UsrNodeBase getFinTaxLne1_Id() { return finTaxLne1_Id; }

    public void setFinTaxLne1_Id(UsrNodeBase finTaxLne1_Id) { this.finTaxLne1_Id = finTaxLne1_Id; }

    public String getFinTaxLne1_Id2() { return finTaxLne1_Id2; }

    public void setFinTaxLne1_Id2(String finTaxLne1_Id2) { this.finTaxLne1_Id2 = finTaxLne1_Id2; }

    public String getFinTaxLne1_Type1_Id2() { return finTaxLne1_Type1_Id2; }

    public void setFinTaxLne1_Type1_Id2(String finTaxLne1_Type1_Id2) { this.finTaxLne1_Type1_Id2 = finTaxLne1_Type1_Id2; }

    public String getFinTaxLne1_Code() { return finTaxLne1_Code; }

    public void setFinTaxLne1_Code(String finTaxLne1_Code) { this.finTaxLne1_Code = finTaxLne1_Code; }


    public UsrItemGenFmla getAmtCalcGenFmla1_Id() { return amtCalcGenFmla1_Id; }

    public void setAmtCalcGenFmla1_Id(UsrItemGenFmla amtCalcGenFmla1_Id) { this.amtCalcGenFmla1_Id = amtCalcGenFmla1_Id; }

    public String getAmtCalcGenFmla1_Id2() { return amtCalcGenFmla1_Id2; }

    public void setAmtCalcGenFmla1_Id2(String amtCalcGenFmla1_Id2) { this.amtCalcGenFmla1_Id2 = amtCalcGenFmla1_Id2; }

    public UsrNodeBase getAmtFinTxactItm1_Id() { return amtFinTxactItm1_Id; }

    public void setAmtFinTxactItm1_Id(UsrNodeBase amtFinTxactItm1_Id) { this.amtFinTxactItm1_Id = amtFinTxactItm1_Id; }

    public String getAmtFinTxactItm1_Id2() { return amtFinTxactItm1_Id2; }

    public void setAmtFinTxactItm1_Id2(String amtFinTxactItm1_Id2) { this.amtFinTxactItm1_Id2 = amtFinTxactItm1_Id2; }

    public BigDecimal getAmtFinTxactItm1_EI1_Rate() { return amtFinTxactItm1_EI1_Rate; }

    public void setAmtFinTxactItm1_EI1_Rate(BigDecimal amtFinTxactItm1_EI1_Rate) { this.amtFinTxactItm1_EI1_Rate = amtFinTxactItm1_EI1_Rate; }

    public String getAmtFinTxactItm1_FinAcct1_Id2() { return amtFinTxactItm1_FinAcct1_Id2; }

    public void setAmtFinTxactItm1_FinAcct1_Id2(String amtFinTxactItm1_FinAcct1_Id2) { this.amtFinTxactItm1_FinAcct1_Id2 = amtFinTxactItm1_FinAcct1_Id2; }


    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getStatusCalc() { return statusCalc; }

    public void setStatusCalc(String statusCalc) { this.status = statusCalc; }


    public BigDecimal getAmtBegBal() { return amtBegBal; }

    public void setAmtBegBal(BigDecimal amtBegBal) { this.amtBegBal = amtBegBal; }

    public BigDecimal getAmtBegBalCalc() { return amtBegBalCalc; }

    public void setAmtBegBalCalc(BigDecimal amtBegBalCalc) { this.amtBegBalCalc = amtBegBalCalc; }



    public BigDecimal getAmtEndBal() { return amtEndBal; }

    public void setAmtEndBal(BigDecimal amtEndBal) { this.amtEndBal = amtEndBal; }

    public BigDecimal getAmtEndBalCalc() { return amtEndBalCalc; }

    public void setAmtEndBalCalc(BigDecimal amtEndBalCalc) { this.amtEndBalCalc = amtEndBalCalc; }




    public BigDecimal getAmtDebt() { return amtDebt; }

    public void setAmtDebt(BigDecimal amtDebt) { this.amtDebt = amtDebt; }

    public BigDecimal getAmtCred() { return amtCred; }

    public void setAmtCred(BigDecimal amtCred) { this.amtCred = amtCred; }

    public BigDecimal getAmtNet() { return amtNet; }

    public void setAmtNet(BigDecimal amtNet) { this.amtNet = amtNet; }


    public String getFinAcct2_Id2() { return finAcct2_Id2; }

    public void setFinAcct2_Id2(String finAcct2_Id2) { this.finAcct2_Id2 = finAcct2_Id2; }

    public BigDecimal getAmtCalc() { return amtCalc; }

    public void setAmtCalc(BigDecimal amtCalc) { this.amtCalc = amtCalc; }



    public UsrNodeFinBal getFinBal1_Id() { return finBal1_Id; }

    public void setFinBal1_Id(UsrNodeFinBal finTax_1_Id) { this.finBal1_Id = finTax_1_Id; }

    public String getFinBal1_Id2() { return finBal1_Id2; }

    public void setFinBal1_Id2(String finBal1_Id2) { this.finBal1_Id2 = finBal1_Id2; }


    public UsrNodeFinBalSet getFinBalSet1_Id() { return finBalSet1_Id; }

    public void setFinBalSet1_Id(UsrNodeFinBalSet finBalSet1_Id) { this.finBalSet1_Id = finBalSet1_Id; }

    public String getFinBalSet1_Id2() { return finBalSet1_Id2; }

    public void setFinBalSet1_Id2(String finBalSet1_Id2) { this.finBalSet1_Id2 = finBalSet1_Id2; }

    // Edge to FinBals


    public String getFinBals1_Id2() {
        return finBals1_Id2;
    }

    public void setFinBals1_Id2(String finBals1_Id2) {
        this.finBals1_Id2 = finBals1_Id2;
    }


    public Integer getFinBals1_IdCntCalc() {
        return finBals1_IdCntCalc;
    }

    public void setFinBals1_IdCntCalc(Integer finBals1_IdCntCalc) {
        this.finBals1_IdCntCalc = finBals1_IdCntCalc;
    }

    public Boolean getFinBals1_AmtEqCalc() {
        return finBals1_AmtEqCalc;
    }

    public void setFinBals1_IdCntCalc(Boolean finBals1_AmtEqCalc) {
        this.finBals1_AmtEqCalc = finBals1_AmtEqCalc;
    }


    // Edge to FinTxactItms

    public String getFinTxactItms1_Id2() {
        return finTxactItms1_Id2;
    }

    public void setFinTxactItms1_Id2(String finTxactItms1_Id2) {
        this.finTxactItms1_Id2 = finTxactItms1_Id2;
    }

    public String getFinTxactItms1_FinAccts1_Id2() {
        return finTxactItms1_FinAccts1_Id2;
    }

    public void setFinTxactItms1_FinAccts1_Id2(String finTxactItms1_FinAccts1_Id2) { this.finTxactItms1_FinAccts1_Id2 = finTxactItms1_FinAccts1_Id2; }


    public Integer getFinTxactItms1_IdCntCalc() { return finTxactItms1_IdCntCalc; }

    public void setFinTxactItms1_IdCntCalc(Integer finTxactItms1_IdCntCalc) { this.finTxactItms1_IdCntCalc = finTxactItms1_IdCntCalc; }

    public BigDecimal getFinTxactItms1_AmtDebtSumCalc() {
        return finTxactItms1_AmtDebtSumCalc;
    }

    public void setFinTxactItms1_AmtDebtSumCalc(BigDecimal finTxactItms1_AmtDebtSumCalc) { this.finTxactItms1_AmtDebtSumCalc = finTxactItms1_AmtDebtSumCalc; }

    public BigDecimal getFinTxactItms1_AmtCredSumCalc() {
        return finTxactItms1_AmtCredSumCalc;
    }

    public void setFinTxactItms1_AmtCredSumCalc(BigDecimal finTxactItms1_AmtCredSumCalc) { this.finTxactItms1_AmtCredSumCalc = finTxactItms1_AmtCredSumCalc; }

    public BigDecimal getFinTxactItms1_AmtNetSumCalc() { return finTxactItms1_AmtNetSumCalc; }

    public void setFinTxactItms1_AmtNetSumCalc(BigDecimal finTxactItms1_AmtNetSumCalc) { this.finTxactItms1_AmtNetSumCalc = finTxactItms1_AmtNetSumCalc; }

    public Boolean getFinTxactItms1_AmtEqCalc() { return finTxactItms1_AmtEqCalc; }

    public void setFinTxactItms1_AmtEqCalc(Boolean finTxactItms1_AmtEqCalc) {this.finTxactItms1_AmtEqCalc = finTxactItms1_AmtEqCalc; }

    public Boolean getFinTxactItms1_SysNodeFinCurcyEqCalc() { return finTxactItms1_SysNodeFinCurcyEqCalc; }

    public void setFinTxactItms1_SysNodeFinCurcyEqCalc(Boolean finTxactItms1_SysNodeFinCurcyEqCalc) {this.finTxactItms1_SysNodeFinCurcyEqCalc = finTxactItms1_SysNodeFinCurcyEqCalc; }


    public BigDecimal getFinTxactItms1_AmtDebtSumDiff() { return finTxactItms1_AmtDebtSumDiff; }

    public void setFinTxactItms1_AmtDebtSumDiff(BigDecimal finTxactItms1_AmtDebtSumDiff) { this.finTxactItms1_AmtDebtSumDiff = finTxactItms1_AmtDebtSumDiff; }

    public BigDecimal getFinTxactItms1_AmtCredSumDiff() { return finTxactItms1_AmtCredSumDiff; }

    public void setFinTxactItms1_AmtCredSumDiff(BigDecimal finTxactItms1_AmtCredSumDiff) { this.finTxactItms1_AmtCredSumDiff = finTxactItms1_AmtCredSumDiff; }

    public BigDecimal getFinTxactItms1_AmtNetSumDiff() { return finTxactItms1_AmtNetSumDiff; }

    public void setFinTxactItms1_AmtNetSumDiff(BigDecimal finTxactItms1_AmtNetSumDiff) { this.finTxactItms1_AmtNetSumDiff = finTxactItms1_AmtNetSumDiff; }

    public Boolean getFinTxactItms1_AmtEqDiff() { return finTxactItms1_AmtEqDiff; }

    public void setFinTxactItms1_AmtEqDiff(Boolean finTxactItms1_AmtEqDiff) {this.finTxactItms1_AmtEqDiff = finTxactItms1_AmtEqDiff; }



    // Edge to FinTxacts

    public String getFinTxacts1_Id2() {
        return finTxacts1_Id2;
    }

    public void setFinTxacts1_Id2(String finTxacts1_Id2) {
        this.finTxacts1_Id2 = finTxacts1_Id2;
    }

    public String getFinTxacts1_FinAccts1_Id2() {
        return finTxacts1_FinAccts1_Id2;
    }

    public void setFinTxacts1_FinAccts1_Id2(String finTxacts1_FinAccts1_Id2) { this.finTxacts1_FinAccts1_Id2 = finTxacts1_FinAccts1_Id2; }


    public Integer getFinTxacts1_IdCntCalc() { return finTxacts1_IdCntCalc; }

    public void setFinTxacts1_IdCntCalc(Integer finTxacts1_IdCntCalc) {this.finTxacts1_IdCntCalc = finTxacts1_IdCntCalc; }

    public Boolean getFinTxacts1_AmtEqCalc() { return finTxacts1_AmtEqCalc; }

    public void setFinTxacts1_AmtEqCalc(Boolean finTxacts1_AmtEqCalc) {this.finTxacts1_AmtEqCalc = finTxacts1_AmtEqCalc; }


    // Edge to FinTxact1.FinTxactSets

    public String getFinTxact1_FinTxactItms1_Id2() {
        return finTxact1_FinTxactItms1_Id2;
    }

    public String getFinTxact1_FinAccts1_Id2() {
        return finTxact1_FinAccts1_Id2;
    }





    // Edge to FinStmtItms

    public Integer getFinStmtItms1_IdCntCalc() { return finStmtItms1_IdCntCalc; }

    public void setFinStmtItms1_IdCntCalc(Integer finStmtItms1_IdCntCalc) {this.finStmtItms1_IdCntCalc = finStmtItms1_IdCntCalc;    }

    public BigDecimal getFinStmtItms1_AmtDebtSumCalc() {
        return finStmtItms1_AmtDebtSumCalc;
    }

    public void setFinStmtItms1_AmtDebtSumCalc(BigDecimal finStmtItms1_AmtDebtSumCalc) { this.finStmtItms1_AmtDebtSumCalc = finStmtItms1_AmtDebtSumCalc; }

    public BigDecimal getFinStmtItms1_AmtCredSumCalc() { return finStmtItms1_AmtCredSumCalc; }

    public void setFinStmtItms1_AmtCredSumCalc(BigDecimal finStmtItms1_AmtCredSumCalc) { this.finStmtItms1_AmtCredSumCalc = finStmtItms1_AmtCredSumCalc; }

    public BigDecimal getFinStmtItms1_AmtNetSumCalc() { return finStmtItms1_AmtNetSumCalc; }

    public void setFinStmtItms1_AmtNetSumCalc(BigDecimal finStmtItms1_AmtNetSumCalc) { this.finStmtItms1_AmtNetSumCalc = finStmtItms1_AmtNetSumCalc; }

    public Boolean getFinStmtItms1_AmtEqCalc() {
        return finStmtItms1_AmtEqCalc;
    }

    public void setFinStmtItms1_AmtEqCalc(Boolean finStmtItms1_AmtEqCalc) {this.finStmtItms1_AmtEqCalc = finStmtItms1_AmtEqCalc; }


    public BigDecimal getFinStmtItms1_AmtDebtSumDiff() { return finStmtItms1_AmtDebtSumDiff; }

    public void setFinStmtItms1_AmtDebtSumDiff(BigDecimal finStmtItms1_AmtDebtSumDiff) { this.finStmtItms1_AmtDebtSumDiff = finStmtItms1_AmtDebtSumDiff; }

    public BigDecimal getFinStmtItms1_AmtCredSumDiff() { return finStmtItms1_AmtCredSumDiff; }

    public void setFinStmtItms1_AmtCredSumDiff(BigDecimal finStmtItms1_AmtCredSumDiff) { this.finStmtItms1_AmtCredSumDiff = finStmtItms1_AmtCredSumDiff; }

    public BigDecimal getFinStmtItms1_AmtNetSumDiff() { return finStmtItms1_AmtNetSumDiff; }

    public void setFinStmtItms1_AmtNetSumDiff(BigDecimal finStmtItms1_AmtNetSumDiff) { this.finStmtItms1_AmtNetSumDiff = finStmtItms1_AmtNetSumDiff; }

    public Boolean getFinStmtItms1_AmtEqDiff() { return finStmtItms1_AmtEqDiff; }

    public void setFinStmtItms1_AmtEqDiff(Boolean finStmtItms1_AmtEqDiff) {this.finStmtItms1_AmtEqDiff = finStmtItms1_AmtEqDiff; }




    public String getFinHow1_Id2() {
        return finHow1_Id2;
    }

    public void setFinHow1_Id2(String finHow1_Id2) {
        this.finHow1_Id2 = finHow1_Id2;
    }

    public UsrItemFinHow getFinHow1_Id() {
        return finHow1_Id;
    }

    public void setFinHow1_Id(UsrItemFinHow finHow1_Id) {
        this.finHow1_Id = finHow1_Id;
    }

    public String getWhatText1() {
        return whatText1;
    }

    public void setWhatText1(String whatText1) {
        this.whatText1 = whatText1;
    }

    public UsrItemFinWhat getFinWhat1_Id() {
        return finWhat1_Id;
    }

    public void setFinWhat1_Id(UsrItemFinWhat finWhat1_Id) {
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

    public UsrItemFinWhy getFinWhy1_Id() {
        return finWhy1_Id;
    }

    public void setFinWhy1_Id(UsrItemFinWhy finWhy1_Id) {
        this.finWhy1_Id = finWhy1_Id;
    }

    public String getFinWhy1_Id2() {
        return finWhy1_Id2;
    }

    public void setFinWhy1_Id2(String finWhy1_Id2) {
        this.finWhy1_Id2 = finWhy1_Id2;
    }



    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) { this.deletedDate = deletedDate; }




    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateCalcVals(DataManager dataManager){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateName1(dataManager) || isChanged;

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update all calculated fields</h1>
     * <p></p>
     * <h2>Updated Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateCalcVals(DataManager dataManager, Integer option){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    
    /** 
     * <h1>Update the <b>id2</b> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>id2Calc</i> required</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateId2(DataManager dataManager) {
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String l_id2_ = this.id2;
        String l_id2 = this.id2Calc;
        logger.debug(logPrfx + " --- l_id2:" + l_id2);

        if(Objects.equals(l_id2_, l_id2)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setId2(l_id2);
            logger.debug(logPrfx + " --- called setId2(l_id2)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2</i> field dependent fields</h1>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>id2Cmp</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateId2Deps(DataManager dataManager) {
        String logPrfx = "updateId2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2Calc</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "Gen"</li>
     *          <li><i>id2Calc</i> = "Gen"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i> = "Biz"</li>
     *          <li><i>id2Calc</i> = "Biz"</li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateId2Calc(DataManager dataManager) {
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //name1 required
        Optional<String> l_name1 = Optional.ofNullable(this.name1);
        if(l_name1.isEmpty()) {
            logger.trace(logPrfx + " --- name1 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.trace(logPrfx + " --- name1 :" + l_name1.get());
            sb.append(l_name1.get());
        }

        String l_id2Calc_ = this.id2Calc;
        String l_id2Calc = sb.toString();
        logger.debug(logPrfx + " --- l_id2Calc:" + l_id2Calc);

        if (Objects.equals(l_id2Calc_, l_id2Calc)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setId2Calc(l_id2Calc);
            logger.debug(logPrfx + " --- called setId2Calc(l_id2Calc)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2Calc</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateId2CalcDeps(DataManager dataManager) {
        String logPrfx = "updateId2CalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the id2Cmp field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 required</li>
     *          <li>id2Calc required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Gen"</li>
     *          <li>id2Calc = "Gen"</li>
     *          <li>id2Cmp = true</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Gen"</li>
     *          <li>id2Calc = "Biz"</li>
     *          <li>id2Cmp = false</li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateId2Cmp(DataManager dataManager) {
        String logPrfx = "updateId2Cmp";
        logger.trace(logPrfx + " --> ");
        
        boolean isChanged = false;
        Boolean l_id2Cmp_ = this.id2Cmp;
        Boolean l_id2Cmp = !Objects.equals(this.id2,this.id2Calc);
        if (!Objects.equals(l_id2Cmp_, l_id2Cmp)){
            this.setId2Cmp(l_id2Cmp);
            logger.debug(logPrfx + " --- id2Cmp: " + l_id2Cmp);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <b>id2Dup</b> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Gen"</li>
     *          <li>id2Cmp = 1</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li>id2 = "Prf"</li>
     *          <li>id2Cmp = 3<br>
     *              Assuming there are 3 objects of this type with id2 = "Prf"
     *          </li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateId2Dup(DataManager dataManager) {
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer l_id2Dup_ = this.id2Dup;
        if (this.id2 != null){
            String id2Qry = "select count(e)"
                    + " from enty_" + this.getClass().getSimpleName() + " e"
                    + " where e.id2 = :id2"
                    + " and e.id <> :id";
            Integer l_id2Dup;
            try{
                l_id2Dup = dataManager.loadValue(id2Qry, Integer.class)
                        .store("main")
                        .parameter("id",this.id)
                        .parameter("id2",this.id2)
                        .one();
            }catch (IllegalStateException e){
                l_id2Dup =0;

            }
            l_id2Dup = l_id2Dup + 1;
            logger.debug(logPrfx + " --- id2Dup qry counted: " + l_id2Dup + " rows");
            if (!Objects.equals(l_id2Dup_, l_id2Dup)){
                this.setId2Dup(l_id2Dup);
                logger.debug(logPrfx + " --- id2Dup: " + l_id2Dup);
                isChanged = true;
            }

        }
        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>id2Dup</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>id2Cmp</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateId2DupDeps(DataManager dataManager) {
        String logPrfx = "updateId2DupDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>name1</i> field</h1>
     * <p></p>
     * <h2>Precedent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> optional</li>
     *          <li><i>inst1</i> required</li>
     *      </ul>
     * <p></p>
     * <h2>Examples</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> = null</li>
     *          <li><i>inst1</i> = "Gen"</li>
     *          <li><i>name1</i> = "Gen"</li>
     *      </ul>
     * <p></p>
     *      <ul style="margin-left: 24px;">
     *          <li><i>type1_Id.id2</i> = "/"</li>
     *          <li><i>inst1</i> = "Biz"</li>
     *          <li><i>name1</i> = "/::Biz"</li>
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateName1(DataManager dataManager){
        String logPrfx = "updateName1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        StringBuilder sb = new StringBuilder();

        //type1_Id.id2 required
        Optional<String> l_type1_Id2 = Optional.ofNullable(this.type1_Id).map(UsrNodeBaseType::getId2);
        if (l_type1_Id2.isEmpty()) {
            logger.debug(logPrfx + " --- l_type1_Id2: null");
        }else{
            logger.debug(logPrfx + " --- l_type1_Id2: " + l_type1_Id2.get());
            sb.append(l_type1_Id2.get());
            sb.append(SEP3);
        }

        //inst1 required
        Optional<String> l_inst1 = Optional.ofNullable(this.inst1);
        if (l_inst1.isEmpty()) {
            logger.debug(logPrfx + " --- inst1: null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }else{
            logger.debug(logPrfx + " --- inst1: " + l_inst1.get());
            sb.append(l_inst1.get());
        }

        String l_name1_ = this.name1;
        String l_name1 = l_inst1.get();
        logger.debug(logPrfx + " --- l_name1:" + l_name1);

        if (!Objects.equals(l_name1_, l_name1)) {
            logger.debug(logPrfx + " --- no change detected");
        }else{
            this.setName1(l_name1);
            logger.debug(logPrfx + " --- called setName1(l_name1)");
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>name1</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateName1Deps(DataManager dataManager) {
        String logPrfx = "updateName1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>type1_Id</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateType1_IdDeps(DataManager dataManager) {
        String logPrfx = "updateType1_IdDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>inst1</i> field</h1>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>inst1</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <h2>Dependent Fields</h2>
     *      <ul style="margin-left: 24px;">
     *          <li><i>name1</i></li>
     *          <li><i>id2Calc</i></li>
     *          <li><i>id2Cmp</i></li>
     *          <li><i>id2Dup</i></li>
     *      </ul>
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateInst1Deps(DataManager dataManager) {
        String logPrfx = "updateInst1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>desc1</i> field</h1>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if the field was changed, otherwise false
     */
    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>desc1</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateDesc1Deps(DataManager dataManager) {
        String logPrfx = "updateDesc1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>txt1</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTxt1Deps(DataManager dataManager) {
        String logPrfx = "updateTxt1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }
    public Boolean updateTs1(DataManager dataManager) {
        // Assume ts1, ts2, ts3 is not null
        String logPrfx = "updateTs1";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();

        LocalDateTime ts1_Ts1_ = this.ts1.getElTs();
        LocalDateTime ts1_Ts1 = null;

        switch (this.dtype.substring(5)) {
            // ts1 only
            case "FinTxactSet", "FinTxact" -> {
                if (ts1.getElTs() != null) {
                    ts1_Ts1 = ts1.getElTs();
                }
                if (!Objects.equals(ts1_Ts1_, ts1_Ts1)) {
                    logger.debug(logPrfx + " --- calling ts1.setElTs((" + ts1_Ts1 == null ? "null" : ts1_Ts1.format(frmtTs) + ")");
                    this.ts1.setElTs(ts1_Ts1);
                    isChanged = true;
                }
            }
            // ts2 otherwise ts1
            case "FinTxactItm", "FinStmtItm" -> {
                if (ts3.getElTs() != null) {
                    ts1_Ts1 = ts3.getElTs();
                } else if (ts2.getElTs() != null) {
                    ts1_Ts1 = ts2.getElTs();
                } else if (ts1.getElTs() != null) {
                    ts1_Ts1 = ts1.getElTs();
                } else {
                    ts1_Ts1 = null;
                }
                if (!Objects.equals(ts1_Ts1_, ts1_Ts1)) {
                    logger.debug(logPrfx + " --- calling ts1.setElTs((" + ts1_Ts1 == null ? "null" : ts1_Ts1.format(frmtTs) + ")");
                    this.ts1.setElTs(ts1_Ts1);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>ts1</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTs1Deps(DataManager dataManager) {
        String logPrfx = "updateTs1Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    /**
     * <h1>Update the <i>ts2</i> field</h1>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTs2() {
        String logPrfx = "updateTs2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    /**
     * <h1>Update the <i>ts2</i> field dependent fields</h1>
     * <p>This includes intermediate fields that higher fields depend on.</p>
     * <p>Ex.</p>
     *      <p style="margin-left: 24px;">
     *          if (<i>A</i> depends on <i>B</i>) and (<i>B</i> depends on <i>C</i>)<br>
     *          then update <i>C<br></i>
     *          then update <i>B<br></i>
     *      </p>
     * <p></p>
     * <b>Note</b>: this method is to be overridden
     * <p></p>
     * @return Boolean true if any field was changed, otherwise false
     */
    public Boolean updateTs2Deps(DataManager dataManager) {
        String logPrfx = "updateTs2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public Boolean updateAmtNet(DataManager dataManager) {
        String logPrfx = "updateAmtNet";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateDt1() {
        // Assume ts1, ts2, ts3 is not null
        String logPrfx = "updateDt1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        LocalDate dt1_Ts1_ = this.dt1.getElDt();
        LocalDate dt1_Ts1 = null;

        switch (this.dtype.substring(5)) {

            case "FinTxactSet", "FinTxact":
                if (ts1.getElDt() != null){
                    logger.debug(logPrfx + " --- calling instDt1.setDate1(("+ ts1.getElDt().format(frmtDt) +")");
                    nm1s1Inst1Dt1.setElDt(ts1.getElDt());
                }else{
                    logger.debug(logPrfx + " --- calling instDt1.setDate1((null)");
                    nm1s1Inst1Dt1.setElDt(null);
                }
                break;

            case  "FinTxactItm", "FinStmtItm" :
                if (ts3.getElDt() != null) {
                    logger.debug(logPrfx + " --- calling instDt1.setDate1((" + ts3.getElDt().format(frmtDt) + ")");
                    nm1s1Inst1Dt1.setElDt(ts3.getElDt());
                } else if (ts2.getElDt() != null) {
                    logger.debug(logPrfx + " --- calling instDt1.setDate1((" + ts2.getElDt().format(frmtDt) + ")");
                    nm1s1Inst1Dt1.setElDt(ts2.getElDt());
                }else if (ts1.getElDt() != null){
                    logger.debug(logPrfx + " --- calling instDt1.setDate1(("+ ts1.getElDt().format(frmtDt) +")");
                    nm1s1Inst1Dt1.setElDt(ts1.getElDt());
                }else{
                    logger.debug(logPrfx + " --- calling instDt1.setDate1((null)");
                    nm1s1Inst1Dt1.setElDt(null);
                }
                break;
            case "FinStmt":
                if (ts3.getElDt() != null){
                    logger.debug(logPrfx + " --- calling instDt1.setDate1(("+ ts3.getElDt().format(frmtDt) +")");
                    nm1s1Inst1Dt1.setElDt(ts3.getElDt());
                }else{
                    logger.debug(logPrfx + " --- calling instDt1.setDate1((null)");
                    nm1s1Inst1Dt1.setElDt(null);
                }
                break;
            default:
                break;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateNm1s1Inst1Tm1() {
        String logPrfx = "updateNm1s1Inst1Tm1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateInt1FrId2(DataManager dataManager) {
        String logPrfx = "updateInt1FrId2()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // require id2
        if(this.id2 == null) {
            logger.trace(logPrfx + " --- id2 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        Integer l_int1_ = this.int1;
        Integer l_int1 = null;

        switch (this.dtype.substring(5)) {
            case "UsrNodeFinTxact":
            case "UsrNodeFinTxactItm":
            case "UsrNodeFinTxactSet":
                if (id2.length() >= 20){

                    String id2_part = id2.substring(18,20);
                    try{
                        l_int1 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    if (!Objects.equals(l_int1_, l_int1)){
                        logger.debug(logPrfx + " --- calling setInt1("+ l_int1 +")");
                        this.setInt1(l_int1);
                        isChanged = true;
                    }

                }

                break;

            default:
                break;
        }


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt2FrId2() {
        String logPrfx = "updateInt2FrId2()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // require id2
        if(this.id2 == null) {
            logger.trace(logPrfx + " --- id2 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        Integer l_int2_ = this.int2;
        Integer l_int2 = null;

        switch (this.dtype.substring(5)) {
            case "UsrNodeFinTxact":
            case "UsrNodeFinTxactItm":
                if (id2.length() >= 24){


                    String id2_part = id2.substring(22,24);
                    try{
                        l_int2 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    if (!Objects.equals(l_int2_, l_int2)){
                        logger.debug(logPrfx + " --- calling setInt2("+ l_int2 +")");
                        this.setInt2(l_int2);
                        isChanged = true;
                    }

                }
                break;

            default:
                break;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateInt3FrId2() {
        String logPrfx = "updateInt3FrId2()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // require id2
        if(this.id2 == null) {
            logger.trace(logPrfx + " --- id2 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        Integer l_int3_ = this.int3;
        Integer l_int3 = null;

        switch (this.dtype.substring(5)) {
            case "UsrNodeFinTxactItm" -> {
                if (id2.length() >= 28){

                    String id2_part = id2.substring(26,28);
                    try{
                        l_int3 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    if (!Objects.equals(l_int3_, l_int3)){
                        logger.debug(logPrfx + " --- calling setInt3("+ l_int3 +")");
                        this.setInt3(l_int3);
                        isChanged = true;
                    }

                }
            }

        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateTs1FrId2(DataManager dataManager) {
        String logPrfx = "updateTs1FrId2()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // require id2
        if(this.id2 == null) {
            logger.trace(logPrfx + " --- id2 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        LocalDateTime ts1_el_ts_ = ts1.getElTs();
        LocalDateTime ts1_el_ts = null;

        switch (this.dtype.substring(5)) {
            case "UsrNodeFinTxactSet":
            case "UsrNodeFinTxact":
            case "UsrNodeFinTxactItm":
                if (id2.length() >= 10){

                    DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                            .appendPattern("yyyy-MM-dd[THH:mm:ss]")
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                            .toFormatter();
                    String id2_part = id2.substring(2,10);
                    try{
                        ts1_el_ts = LocalDateTime.parse(id2_part,frmtTs);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    if (!Objects.equals(ts1_el_ts_, ts1_el_ts)){
                        logger.debug(logPrfx + " --- calling ts1.setElTs(("+ ts1_el_ts.format(frmtTs) +")");
                        this.ts1.setElTs(ts1_el_ts);
                        isChanged = true;
                    }
                }
                break;

            default:
                break;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public Boolean updateTs2FrId2(DataManager dataManager) {
        String logPrfx = "updateTs2FrId2()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        // require id2
        if(this.id2 == null) {
            logger.trace(logPrfx + " --- id2 is null");
            logger.trace(logPrfx + " <-- ");
            return isChanged;
        }

        LocalDateTime ts2_ts1_ = ts2.getElTs();
        LocalDateTime ts2_ts1 = null;

        switch (this.dtype.substring(5)) {
            case    "UsrNodeFinTxactItm",
                    "UsrNodeFinStmtItm" ->
                {

                    if (ts1 == null
                            || ts1.getElTs() == null
//                        || ts2 == null
//                        || ts2.getElTs() == null
                    ) {
                        logger.trace(logPrfx + " ---- ts1 != null");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;

                    }
                    if (id2.length() >= 10){

                        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                                .appendPattern("yyyy-MM-dd[THH:mm:ss]")
                                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                                .toFormatter();

                        String id2_part;
                        Pattern pattern = Pattern.compile("/D[0-9]{4}-[0-9]{2}-[0-9]{2}");
                        Matcher matcher = pattern.matcher(id2);
                        if (matcher.find()) {
                            id2_part = matcher.group(0).substring(2,12);
                        } else {
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }

                        try{
                            ts2_ts1 = LocalDateTime.parse(id2_part,frmtTs);

                        } catch (DateTimeParseException e){

                            logger.trace(logPrfx + " ---- DateTimeParseException");
                            logger.trace(logPrfx + " <-- ");
                            return isChanged;
                        }


                        if (!Objects.equals(ts2_ts1_, ts2_ts1)){
                            logger.debug(logPrfx + " --- calling ts2.setElTs(("+ ts2_ts1.format(frmtTs) +")");
                            this.ts2.setElTs(ts2_ts1);
                            isChanged = true;
                        }
                    }

            }
            case "UsrNodeFinStmt" ->{

                if (id2.length() >= 10){

                    DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                            .appendPattern("yyyy-MM-dd[THH:mm:ss]")
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                            .toFormatter();
                    Integer sep1 = id2.indexOf("//D");
                    Integer sep2 = id2.indexOf("//A");
                    if (sep1 < 0 || sep2 < 0){
                        logger.trace(logPrfx + " ---- id2 does not match UsrNodeFinStmt pattern.");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    String id2_part = id2.substring(sep1+"//D".length()-1,sep2);
                    try{
                        ts2_ts1 = LocalDateTime.parse(id2_part,frmtTs);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <-- ");
                        return isChanged;
                    }

                    if (!Objects.equals(ts2_ts1_, ts2_ts1)){
                        logger.debug(logPrfx + " --- calling ts2.setElTs(("+ ts2_ts1.format(frmtTs) +")");
                        this.ts2.setElTs(ts2_ts1);
                        isChanged = true;
                    }
                }
            }

            default ->{
            }
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public Boolean updateGenAgent1(){
        String logPrfx = "updateGenAgent1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        UsrNodeGenAgent l_genAgent1_ = getGenAgent1_Id();
        UsrNodeGenAgent l_genAgent1 = null;


        switch (this.dtype.substring(5)) {

            default:
                break;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public static <NodeT extends UsrNodeBase> NodeT getNodeById2(Class<NodeT> type, DataManager dataManager, @NotNull String crtieria_Id2) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
        String logPrfx = "getNodeById2";
        logger.trace(logPrfx + " --> ");

        if (crtieria_Id2 == null) {
            logger.debug(logPrfx + " --- crtieria_Id2 is null.");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + crtieria_Id2);

        NodeT node = null;
        try {
            node = dataManager.load(type)
                    .query(qry)
                    .parameter("id2", crtieria_Id2)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");

        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");

        }
        logger.trace(logPrfx + " <-- ");
        return node;

    }



    public static <NodeT extends UsrNodeBase> NodeT getNodesBySortIdx(Class<NodeT> type, DataManager dataManager, @NotNull Integer sortIdx, UsrNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
        String logPrfx = "getNodesBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.parent1_Id = :parent1_Id"
                + " and e.sortIdx = :sortIdx"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdx: " + sortIdx);

        NodeT usrNode = null;
        try {
            usrNode = dataManager.load(type)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdx", sortIdx)
                    .one();
            logger.debug(logPrfx + " --- query qry returned ONE result");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return usrNode;
    }


    public static <NodeT extends UsrNodeBase>  List<NodeT> getNodesBtwnSortIdx(Class<NodeT> type, DataManager dataManager, @NotNull Integer sortIdxA , @NotNull Integer sortIdxB, UsrNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
        String logPrfx = "getNodesBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.parent1_Id = :parent1_Id"
                + " and e.sortIdx > :sortIdxA"
                + " and e.sortIdx < :sortIdxB"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdxA: " + sortIdxA);
        logger.debug(logPrfx + " --- qry:sortIdxB: " + sortIdxB);

        List<NodeT> nodes = null;
        try {
            nodes = dataManager.load(type)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .parameter("sortIdxA", sortIdxA)
                    .parameter("sortIdxB", sortIdxB)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ nodes.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return nodes;
    }

    public static <NodeT extends UsrNodeBase> List<NodeT> getNodesByParent1(Class<NodeT> type, DataManager dataManager, UsrNodeBase parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
        String logPrfx = "getNodeListByParent1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e"
                + " from enty_" + type.getSimpleName() + " e"
                + " where e.parent1_Id = :parent1_Id"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());

        List<NodeT> nodes = null;
        try {
            nodes = dataManager.load(type)
                    .query(qry)
                    .parameter("parent1_Id", parent1_Id)
                    .list();
            logger.debug(logPrfx + " --- query qry returned "+ nodes.size() +" results");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned NO results");
        }

        logger.trace(logPrfx + " <-- ");
        return nodes;
    }


    public static <NodeT extends UsrNodeBase> List<String> getStatusList(Class<NodeT> type, DataManager dataManager){
        final Logger logger = LoggerFactory.getLogger(UsrNodeBase.class.getClass());
        String logPrfx = "getStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status "
                + " from enty_" + type.getSimpleName() + " e"
                + " and e.status is not null"
                + " order by e.status"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);


        List<String> statuss = null;
        try {
            statuss = dataManager.loadValue(qry, String.class)
                    .store("main")
                    .list();
            logger.debug(logPrfx + " --- query qry returned " + statuss.size() + " rows");
        } catch (IllegalStateException e) {
            logger.debug(logPrfx + " --- query qry returned no rows");
            logger.trace(logPrfx + " <-- ");
            return statuss;
        }

        logger.trace(logPrfx + " <-- ");
        return statuss;
    }

    public Boolean updateIdParts() {
        String logPrfx = "updateIdParts";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


}
