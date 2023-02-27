package ca.ampautomation.ampata.entity.usr;

import ca.ampautomation.ampata.entity.HasDate;
import ca.ampautomation.ampata.entity.HasTime;
import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.SysNode;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinFmla;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinHow;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinWhat;
import ca.ampautomation.ampata.entity.usr.fin.UsrFinWhy;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFile;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenFmla;
import ca.ampautomation.ampata.entity.usr.gen.UsrGenTag;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JmixEntity
@Table(name = "AMPATA_USR_NODE", indexes = {
        @Index(name = "IDX_USRNODE_PARENT1__ID", columnList = "PARENT1__ID"),
        @Index(name = "IDX_USRNODE_TYPE1__ID", columnList = "TYPE1__ID"),
        @Index(name = "IDX_USRNODE_NAME1_GEN_FMLA1__ID", columnList = "NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_TYPE1__ID", columnList = "NM1S1_TYPE1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_NAME1_GEN_FMLA1__ID", columnList = "NM1S1_NAME1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_INST1_GEN_FMLA1__ID", columnList = "NM1S1_INST1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_NM1S1_INST1_NODE1__ID", columnList = "NM1S1_INST1_NODE1__ID"),
        @Index(name = "IDX_USRNODE_DESC1_GEN_FMLA1__ID", columnList = "DESC1_GEN_FMLA1__ID"),
        @Index(name = "IDX_USRNODE_DESC1_FIN_TXACT_ITM1__ID", columnList = "DESC1_FIN_TXACT_ITM1__ID"),
        @Index(name = "IDX_USRNODE_DESC1_FIN_TXACT_ITM2__ID", columnList = "DESC1_FIN_TXACT_ITM2__ID"),
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
        @Index(name = "IDX_USRNODE_SYS_FIN_CURCY1__ID", columnList = "SYS_FIN_CURCY1__ID"),
        @Index(name = "IDX_USRNODE_AMT_FIN_FMLA1__ID", columnList = "AMT_FIN_FMLA1__ID"),
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
@Entity(name = "enty_UsrNode")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(name = "UsrNode.execUsrNodePrUpd",
                procedureName = "Usr_Node_Pr_Upd", parameters = {

        }),

        @NamedStoredProcedureQuery(name = "UsrNode.execUsrNodePrUpd2",
                procedureName = "Usr_Node_Pr_Upd2"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
        }),

        @NamedStoredProcedureQuery(name = "UsrNode.execUsrNodePrUpd3",
                procedureName = "Usr_Node_Pr_Upd3"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
                , @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = String.class)
        }),

        @NamedStoredProcedureQuery(name = "UsrNode.execUsrFinTxactItmPrUpd",
                procedureName = "Fin_Txact_Itm_Pr_Upd", parameters = {

        }),

        @NamedStoredProcedureQuery(name = "UsrNode.execUsrFinTxactItmPrUpd2",
                procedureName = "Fin_Txact_Itm_Pr_Upd2"
                , parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
        }),

})

public class UsrNode implements AcceptsTenant {

    @Transient
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    protected UsrNode parent1_Id;

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

    @JoinColumn(name = "NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrGenFmla name1GenFmla1_Id;

    @Column(name = "NAME1_GEN_FMLA1__ID2")
    protected String name1GenFmla1_Id2;

    @JoinColumn(name = "TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeType type1_Id;

    @Column(name = "TYPE1__ID2")
    protected String type1_Id2;

    @Column(name = "INST1")
    protected String inst1;

    @Column(name = "NM1S1_NAME1")
    protected String nm1s1Name1;

    @JoinColumn(name = "NM1S1_NAME1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrGenFmla nm1s1Name1GenFmla1_Id;

    @Column(name = "NM1S1_NAME1_GEN_FMLA1__ID2")
    protected String nm1s1Name1GenFmla1_Id2;

    @JoinColumn(name = "NM1S1_TYPE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeType nm1s1Type1_Id;

    @Column(name = "NM1S1_TYPE1__ID2")
    protected String nm1s1Type1_Id2;


    @Column(name = "NM1S1_INST1")
    protected String nm1s1Inst1;

    @JoinColumn(name = "NM1S1_INST1_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrGenFmla nm1s1Inst1GenFmla1_Id;

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

    @JoinColumn(name = "NM1S1_INST1_NODE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode nm1s1Inst1Node1_Id;

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
    protected UsrGenFmla desc1GenFmla1_Id;

    @Column(name = "DESC1_GEN_FMLA1__ID2")
    protected String desc1GenFmla1_Id2;

    @JoinColumn(name = "DESC1_FIN_TXACT_ITM1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode desc1FinTxactItm1_Id;

    @Column(name = "DESC1_FIN_TXACT_ITM1__ID2")
    protected String desc1FinTxactItm1_Id2;

    @JoinColumn(name = "DESC1_FIN_TXACT_ITM2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode desc1FinTxactItm2_Id;

    @Column(name = "DESC1_FIN_TXACT_ITM2__ID2")
    protected String desc1FinTxactItm2_Id2;

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
    private List<UsrEdge> edgeIns_Id;

    @Column(name = "EDGE_INS__ID2")
    private String edgeIns_Id2;

    @OneToMany(mappedBy = "nodeIn_Id")
    private List<UsrEdge> edgeOts_Id;

    @Column(name = "EDGE_OTS__ID2")
    private String edgeOts_Id2;

    @Lob
    @Column(name = "GEN_DOC_VERS1__ID2")
    protected String genDocVers1_Id2;

    @JoinColumn(name = "GEN_DOC_VER1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode genDocVer1_Id;

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
    protected UsrGenFile genFile1_Id;

    @Column(name = "GEN_FILE1__ID2")
    protected String genFile1_Id2;

    @Lob
    @Column(name = "GEN_FILE1__URI")
    protected URI genFile1_URI;


    @JoinTable(name = "AMPATA_USR_NODE__GEN_TAGS1_LINK",
            joinColumns = @JoinColumn(name = "NODE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GEN_TAG_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<UsrGenTag> genTags1_Id;

    @Column(name = "GEN_TAGS1__ID2")
    protected String genTags1_Id2;

    @JoinColumn(name = "GEN_TAG1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrGenTag genTag1_Id;

    @Column(name = "GEN_TAG1__ID2")
    protected String genTag1_Id2;

    @JoinColumn(name = "GEN_TAG2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrGenTag genTag2_Id;

    @Column(name = "GEN_TAG2__ID2")
    protected String genTag2_Id2;

    @JoinColumn(name = "GEN_TAG3__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrGenTag genTag3_Id;

    @Column(name = "GEN_TAG3__ID2")
    protected String genTag3_Id2;

    @JoinColumn(name = "GEN_TAG4__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrGenTag genTag4_Id;

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
    protected UsrNode genAgent1_Id;

    @Column(name = "GEN_AGENT1__ID2")
    protected String genAgent1_Id2;

    @JoinColumn(name = "GEN_AGENT2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode genAgent2_Id;

    @Column(name = "GEN_AGENT2__ID2")
    protected String genAgent2_Id2;

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


    @Column(name = "VER")
    protected String ver;

    @JoinColumn(name = "GEN_CHAN1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode genChan1_Id;

    @Column(name = "GEN_CHAN1__ID2")
    protected String genChan1_Id2;

    @JoinColumn(name = "GEN_CHAN2__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode genChan2_Id;

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
    protected UsrNode finTxactSet1_Id;

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
    protected UsrNode finTxact1_Id;

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
    protected UsrNode finStmt1_Id;

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
    protected UsrNode finStmtItm1_Id;

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
    protected UsrNode finAcct1_Id;

    @Column(name = "FIN_ACCT1__ID2")
    protected String finAcct1_Id2;

    @Column(name = "FIN_ACCT1__TYPE1__ID2")
    protected String finAcct1_Type1_Id2;

    @JoinColumn(name = "FIN_DEPT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode finDept1_Id;

    @Column(name = "FIN_DEPT1__ID2")
    protected String finDept1_Id2;

    @JoinColumn(name = "FIN_TAX_LNE1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode finTaxLne1_Id;

    @Column(name = "FIN_TAX_LNE1__ID2")
    protected String finTaxLne1_Id2;

    @Column(name = "FIN_TAX_LNE1__CODE")
    protected String finTaxLne1_Code;

    @Column(name = "FIN_TAX_LNE1__TYPE1__ID2")
    protected String finTaxLne1_Type1_Id2;

    @JoinColumn(name = "FIN_HOW1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrFinHow finHow1_Id;

    @Column(name = "FIN_HOW1__ID2")
    protected String finHow1_Id2;

    @Column(name = "WHAT_TEXT1")
    protected String whatText1;

    @JoinColumn(name = "FIN_WHAT1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrFinWhat finWhat1_Id;

    @Column(name = "FIN_WHAT1__ID2")
    protected String finWhat1_Id2;

    @Column(name = "WHY_TEXT1")
    protected String whyText1;

    @JoinColumn(name = "FIN_WHY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrFinWhy finWhy1_Id;

    @Column(name = "FIN_WHY1__ID2")
    protected String finWhy1_Id2;

    @Column(name = "AMT_DEBT", precision = 19, scale = 2)
    protected BigDecimal amtDebt;

    @Column(name = "AMT_CRED", precision = 19, scale = 2)
    protected BigDecimal amtCred;

    @Column(name = "AMT_NET", precision = 19, scale = 2)
    protected BigDecimal amtNet;

    @JoinColumn(name = "SYS_FIN_CURCY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysNode sysFinCurcy1_Id;

    @Column(name = "SYS_FIN_CURCY1__ID2")
    protected String sysFinCurcy1_Id2;

    @JoinColumn(name = "AMT_FIN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrFinFmla amtFinFmla1_Id;

    @Column(name = "AMT_FIN_FMLA1__ID2")
    protected String amtFinFmla1_Id2;

    @JoinColumn(name = "AMT_FIN_TXACT_ITM1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode amtFinTxactItm1_Id;

    @Column(name = "AMT_FIN_TXACT_ITM1__ID2")
    protected String amtFinTxactItm1_Id2;

    @Column(name = "AMT_FIN_TXACT_ITM1__EI1__RATE", precision = 19, scale = 9)
    protected BigDecimal amtFinTxactItm1_EI1_Rate;

    @Column(name = "AMT_FIN_TXACT_ITM1__FIN_ACCT1__ID2")
    protected String amtFinTxactItm1_FinAcct1_Id2;

    @Column(name = "FIN_ACCT2__ID2")
    protected String finAcct2_Id2;

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
    protected UsrNode finBal1_Id;

    @Column(name = "FIN_BAL1__ID2")
    protected String finBal1_Id2;


    @JoinColumn(name = "FIN_BAL_SET1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNode finBalSet1_Id;

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

    @Column(name = "FIN_TXACT_ITMS1__SYS_FIN_CURCY_CALC")
    protected Boolean finTxactItms1_SysFinCurcyEqCalc;


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


    public UsrNode getParent1_Id() { return parent1_Id; }

    public void setParent1_Id(UsrNode parent1_Id) { this.parent1_Id = parent1_Id; }

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


    public UsrGenFmla getName1GenFmla1_Id() { return name1GenFmla1_Id; }

    public void setName1GenFmla1_Id(UsrGenFmla name1GenFmla1_Id) { this.name1GenFmla1_Id = name1GenFmla1_Id; }

    public String getName1GenFmla1_Id2() { return name1GenFmla1_Id2; }

    public void setName1GenFmla1_Id2(String name1GenFmla1_Id2) { this.name1GenFmla1_Id2 = name1GenFmla1_Id2; }

    public void setType1_Id(UsrNodeType type1_Id) { this.type1_Id = type1_Id; }

    public UsrNodeType getType1_Id() { return type1_Id; }

    public String getType1_Id2() { return type1_Id2; }

    public void setType1_Id2(String type1_Id2) { this.type1_Id2 = type1_Id2; }


    public String getInst1() { return inst1; }

    public void setInst1(String inst1) { this.inst1 = inst1; }

    
    public UsrNodeType getNm1s1Type1_Id() { return nm1s1Type1_Id; }

    public void setNm1s1Type1_Id(UsrNodeType nm1s1Type1_Id) { this.nm1s1Type1_Id = nm1s1Type1_Id; }


    public String getNm1s1Type1_Id2() { return nm1s1Type1_Id2; }

    public void setNm1s1Type1_Id2(String nm1s1Type1_Id2) { this.nm1s1Type1_Id2 = nm1s1Type1_Id2; }


    public String getNm1s1Name1() { return nm1s1Name1; }

    public void setNm1s1Name1(String nm1s1Name1) { this.nm1s1Name1 = nm1s1Name1; }

    public UsrGenFmla getNm1s1Name1GenFmla1_Id() { return nm1s1Name1GenFmla1_Id; }

    public void setNm1s1Name1GenFmla1_Id(UsrGenFmla nm1s1Name1GenFmla1_Id) { this.nm1s1Name1GenFmla1_Id = nm1s1Name1GenFmla1_Id; }

    
    public String getNm1s1Name1GenFmla1_Id2() { return nm1s1Name1GenFmla1_Id2; }

    public void setNm1s1Name1GenFmla1_Id2(String nm1s1Name1GenFmla1_Id2) { this.nm1s1Name1GenFmla1_Id2 = nm1s1Name1GenFmla1_Id2; }


    public String getNm1s1Inst1() { return nm1s1Inst1; }

    public void setNm1s1Inst1(String nm1s1Inst1) { this.nm1s1Inst1 = nm1s1Inst1; }

    public UsrGenFmla getNm1s1Inst1GenFmla1_Id() { return nm1s1Inst1GenFmla1_Id; }

    public void setNm1s1Inst1GenFmla1_Id(UsrGenFmla nm1s1Inst1GenFmla1_Id) { this.nm1s1Inst1GenFmla1_Id = nm1s1Inst1GenFmla1_Id; }


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


    public UsrNode getNm1s1Inst1Node1_Id() { return nm1s1Inst1Node1_Id; }

    public void setNm1s1Inst1Node1_Id(UsrNode nm1s1Inst1Node1_Id) { this.nm1s1Inst1Node1_Id = nm1s1Inst1Node1_Id; }


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


    public UsrGenFmla getDesc1GenFmla1_Id() { return desc1GenFmla1_Id; }

    public void setDesc1GenFmla1_Id(UsrGenFmla desc1GenFmla1_Id) { this.desc1GenFmla1_Id = desc1GenFmla1_Id; }

    public String getDesc1GenFmla1_Id2() { return desc1GenFmla1_Id2; }

    public void setDesc1GenFmla1_Id2(String desc1GenFmla1_Id2) { this.desc1GenFmla1_Id2 = desc1GenFmla1_Id2; }


    public UsrNode getDesc1FinTxactItm1_Id() { return desc1FinTxactItm1_Id; }

    public void setDesc1FinTxactItm1_Id(UsrNode desc1FinTxactItm1_Id) { this.desc1FinTxactItm1_Id = desc1FinTxactItm1_Id; }

    public String getDesc1FinTxactItm1_Id2() {
        return desc1FinTxactItm1_Id2;
    }

    public void setDesc1FinTxactItm1_Id2(String desc1FinTxactItm1_Id2) { this.desc1FinTxactItm1_Id2 = desc1FinTxactItm1_Id2; }


    public UsrNode getDesc1FinTxactItm2_Id() { return desc1FinTxactItm2_Id; }

    public void setDesc1FinTxactItm2_Id(UsrNode desc1FinTxactItm2_Id) { this.desc1FinTxactItm2_Id = desc1FinTxactItm2_Id; }

    public String getDesc1FinTxactItm2_Id2() { return desc1FinTxactItm2_Id2; }

    public void setDesc1FinTxactItm2_Id2(String desc1FinTxactItm2_Id2) { this.desc1FinTxactItm2_Id2 = desc1FinTxactItm2_Id2; }


    public String getDesc2() { return desc2; }

    public void setDesc2(String desc2) { this.desc2 = desc2; }


    public String getDesc3() { return desc3; }

    public void setDesc3(String desc3) {this.desc3 = desc3;}


    public String getDesc4() { return desc4; }

    public void setDesc4(String desc4) { this.desc4 = desc4; }



    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }



    public List<UsrEdge> getEdgeIns_Id() {
        return edgeIns_Id;
    }

    public void setEdgeIns_Id(List<UsrEdge> edgeIns_Id) {
        this.edgeIns_Id = edgeIns_Id;
    }

    public String getEdgeIns_Id2() {
        return edgeIns_Id2;
    }

    public void setEdgeIns_Id2(String edgeIns_Id2) {
        this.edgeIns_Id2 = edgeIns_Id2;
    }


    public List<UsrEdge> getEdgeOts_Id() {
        return edgeOts_Id;
    }

    public void setEdgeOts_Id(List<UsrEdge> edgeOts_Id) {
        this.edgeOts_Id = edgeOts_Id;
    }

    public String getEdgeOts_Id2() {
        return edgeOts_Id2;
    }

    public void setEdgeOts_Id2(String edgeOts_Id2) {
        this.edgeOts_Id2 = edgeOts_Id2;
    }

    public String getGenChan1_Id2() { return genChan1_Id2; }

    public void setGenChan1_Id2(String genChan1_Id2) { this.genChan1_Id2 = genChan1_Id2; }

    public UsrNode getGenChan1_Id() { return genChan1_Id; }

    public void setGenChan1_Id(UsrNode genChan1_Id) { this.genChan1_Id = genChan1_Id; }

    public String getGenChan2_Id2() { return genChan2_Id2;}

    public void setGenChan2_Id2(String genChan2_Id2) {this.genChan2_Id2 = genChan2_Id2;}

    public UsrNode getGenChan2_Id() {return genChan2_Id;}

    public void setGenChan2_Id(UsrNode genChan2_Id) {this.genChan2_Id = genChan2_Id;}




    public HasTmst getTs1() { return ts1; }

    public void setTs1(HasTmst ts1) { this.ts1 = ts1; }


    public HasTmst getTs2() { return ts2; }

    public void setTs2(HasTmst ts2) { this.ts2 = ts2; }


    public void setTs3(HasTmst ts3) { this.ts3 = ts3; }

    public HasTmst getTs3() { return ts3; }


    public HasTmst getTs4() { return ts4; }

    public void setTs4(HasTmst ts4) { this.ts4 = ts4; }



    public String getGenDocVers1_Id2() { return genDocVers1_Id2; }

    public void setGenDocVers1_Id2(String genDocVers1_Id2) { this.genDocVers1_Id2 = genDocVers1_Id2; }

    public UsrNode getGenDocVer1_Id() { return genDocVer1_Id; }

    public void setGenDocVer1_Id(UsrNode genDocVer1_Id) { this.genDocVer1_Id = genDocVer1_Id; }

    public String getGenDocVer1_Id2() { return genDocVer1_Id2; }

    public void setGenDocVer1_Id2(String genDocVer1_Id2) { this.genDocVer1_Id2 = genDocVer1_Id2; }

    public String getGenDocVer1_Code() { return genDocVer1_Code; }

    public String getGenDocVer1_Ver() { return genDocVer1_Ver; }

    public String getGenDocVer1_Name1() { return genDocVer1_Name1; }


    public UsrGenFile getGenFile1_Id() { return genFile1_Id; }

    public void setGenFile1_Id(UsrGenFile genFile1_Id) { this.genFile1_Id = genFile1_Id; }

    public String getGenFile1_Id2() { return genFile1_Id2; }

    public void setGenFile1_Id2(String genFile1_Id2) { this.genFile1_Id2 = genFile1_Id2; }

    public URI getGenFile1_URI() { return genFile1_URI; }

    public void setGenFile1_URI(URI genFile1_URI) { this.genFile1_URI = genFile1_URI; }


    public String getFinTxact1_GenDocVers1_Id2() { return finTxact1_GenDocVers1_Id2; }

    public String getFinTxact1_GenTags1_Id2() { return finTxact1_GenTags1_Id2; }


    public List<UsrGenTag> getGenTags1_Id() { return genTags1_Id; }

    public void setGenTags1_Id(List<UsrGenTag> genTags1_Id) { this.genTags1_Id = genTags1_Id; }


    public String getGenTags1_Id2() { return genTags1_Id2; }

    public void setGenTags1_Id2(String genTags1_Id2) { this.genTags1_Id2 = genTags1_Id2; }


    public UsrGenTag getGenTag1_Id() { return genTag1_Id; }

    public void setGenTag1_Id(UsrGenTag genTag1_Id) { this.genTag1_Id = genTag1_Id; }

    public String getGenTag1_Id2() { return genTag1_Id2; }

    public void setGenTag1_Id2(String genTag1_Id2) { this.genTag1_Id2 = genTag1_Id2; }


    public UsrGenTag getGenTag2_Id() { return genTag2_Id; }

    public void setGenTag2_Id(UsrGenTag genTag2_Id) { this.genTag2_Id = genTag2_Id; }

    public String getGenTag2_Id2() { return genTag2_Id2; }

    public void setGenTag2_Id2(String genTag2_Id2) { this.genTag2_Id2 = genTag2_Id2; }


    public UsrGenTag getGenTag3_Id() { return genTag3_Id; }

    public void setGenTag3_Id(UsrGenTag genTag3_Id) { this.genTag3_Id = genTag3_Id; }

    public String getGenTag3_Id2() { return genTag3_Id2; }

    public void setGenTag3_Id2(String genTag3_Id2) { this.genTag3_Id2 = genTag3_Id2; }


    public UsrGenTag getGenTag4_Id() { return genTag4_Id; }

    public void setGenTag4_Id(UsrGenTag genTag4_Id) { this.genTag4_Id = genTag4_Id; }

    public String getGenTag4_Id2() { return genTag4_Id2; }

    public void setGenTag4_Id2(String genTag4_Id2) { this.genTag4_Id2 = genTag4_Id2; }



    public String getVer() { return ver; }

    public void setVer(String ver) { this.ver = ver;}

    
    public UsrNode getGenAgent1_Id() { return genAgent1_Id; }

    public void setGenAgent1_Id(UsrNode genAgent1_Id) { this.genAgent1_Id = genAgent1_Id; }

    public String getGenAgent1_Id2() { return genAgent1_Id2; }

    public void setGenAgent1_Id2(String genAgent1_Id2) { this.genAgent1_Id2 = genAgent1_Id2; }


    public UsrNode getGenAgent2_Id() { return genAgent2_Id; }

    public void setGenAgent2_Id(UsrNode genAgent2_Id) { this.genAgent2_Id = genAgent2_Id; }

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


    public UsrNode getFinTxactSet1_Id() { return finTxactSet1_Id; }

    public void setFinTxactSet1_Id(UsrNode finTxactSet1_Id) { this.finTxactSet1_Id = finTxactSet1_Id; }

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



    public String getFinTxactSet1_How1_Id2() {
        return finTxactSet1_How1_Id2;
    }

    public void setFinTxactSet1_How1_Id2(String finTxactSet1_How1_Id2) {
        this.finTxactSet1_How1_Id2 = finTxactSet1_How1_Id2;
    }

    public String getFinTxactSet1_WhatText1() {
        return finTxactSet1_WhatText1;
    }

    public void setFinTxactSet1_WhatText1(String finTxactSet1_WhatText1) {
        this.finTxactSet1_WhatText1 = finTxactSet1_WhatText1;
    }


    public String getFinTxactSet1_What1_Id2() {
        return finTxactSet1_What1_Id2;
    }

    public void setFinTxactSet1_What1_Id2(String finTxactSet1_What1_Id2) {
        this.finTxactSet1_What1_Id2 = finTxactSet1_What1_Id2;
    }


    public String getFinTxactSet1_WhyText1() {
        return finTxactSet1_WhyText1;
    }

    public void setFinTxactSet1_WhyText1(String finTxactSet1_WhyText1) {
        this.finTxactSet1_WhyText1 = finTxactSet1_WhyText1;
    }

    public String getFinTxactSet1_Why1_Id2() {
        return finTxactSet1_Why1_Id2;
    }

    public void setFinTxactSet1_Why1_Id2(String finTxactSet1_Why1_Id2) {
        this.finTxactSet1_Why1_Id2 = finTxactSet1_Why1_Id2;
    }





    public UsrNode getFinTxact1_Id() {return finTxact1_Id; }

    public void setFinTxact1_Id(UsrNode finTxact1_Id) {
        this.finTxact1_Id = finTxact1_Id;
    }

    public String getFinTxact1_Id2() {
        return finTxact1_Id2;
    }

    public void setFinTxact1_Id2(String finTxact1_Id2) {
        this.finTxact1_Id2 = finTxact1_Id2;
    }

    public String getFinTxact1_Id2Trgt() { return finTxact1_Id2Trgt;}

    public void setFinTxact1_Id2Trgt(String finTxact1_Id2Trgt) { this.finTxact1_Id2Trgt = finTxact1_Id2Trgt;}

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

    public UsrNode getFinStmt1_Id() {
        return finStmt1_Id;
    }

    public void setFinStmt1_Id(UsrNode finStmt1_Id) {
        this.finStmt1_Id = finStmt1_Id;
    }

    public String getExchDesc() {
        return exchDesc;
    }

    public void setExchDesc(String exchDesc) {
        this.exchDesc = exchDesc;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }


    public UsrNode getFinStmtItm1_Id() {
        return finStmtItm1_Id;
    }

    public void setFinStmtItm1_Id(UsrNode finStmtItm1_Id) {
        this.finStmtItm1_Id = finStmtItm1_Id;
    }

    public String getFinStmtItm1_Id2() {
        return finStmtItm1_Id2;
    }

    public void setFinStmtItm1_Id2(String finStmtItm1_Id2) {
        this.finStmtItm1_Id2 = finStmtItm1_Id2;
    }

    public String getFinStmtItm1_Id2Trgt() {
        return finStmtItm1_Id2Trgt;
    }

    public void setFinStmtItm1_Id2Trgt(String finStmtItm1_Id2Trgt) {this.finStmtItm1_Id2Trgt = finStmtItm1_Id2Trgt;}

    public String getFinStmtItm1_Desc1() {
        return finStmtItm1_Desc1;
    }

    public void setFinStmtItm1_Desc1(String finStmtItm1_Desc1) {
        this.finStmtItm1_Desc1 = finStmtItm1_Desc1;
    }

    public String getFinStmtItm1_Desc2() {
        return finStmtItm1_Desc2;
    }

    public void setFinStmtItm1_Desc2(String finStmtItm1_Desc2) {
        this.finStmtItm1_Desc2 = finStmtItm1_Desc2;
    }

    public String getFinStmtItm1_Desc3() {
        return finStmtItm1_Desc3;
    }

    public void setFinStmtItm1_Desc3(String finStmtItm1_Desc3) {
        this.finStmtItm1_Desc3 = finStmtItm1_Desc3;
    }

    public String getFinStmtItm1_Desc4() {
        return finStmtItm1_Desc4;
    }

    public void setFinStmtItm1_Desc4(String finStmtItm1_Desc4) {
        this.finStmtItm1_Desc4 = finStmtItm1_Desc4;
    }

    public String getFinStmtItm1_ExchDesc() {
        return finStmtItm1_ExchDesc;
    }

    public void setFinStmtItm1_ExchDesc(String finStmtItm1_ExchDesc) {
        this.finStmtItm1_ExchDesc = finStmtItm1_ExchDesc;
    }

    public String getFinAcct1_Type1_Id2() {
        return finAcct1_Type1_Id2;
    }

    public String getFinStmtItm1_RefId() {return finStmtItm1_RefId;}

    public void setFinStmtItm1_RefId(String finStmtItm1_RefId) {this.finStmtItm1_RefId = finStmtItm1_RefId;}

    public void setFinAcct1_Type1_Id2(String finAcct1_Type1_Id2) {
        this.finAcct1_Type1_Id2 = finAcct1_Type1_Id2;
    }


    public UsrNode getFinAcct1_Id() { return finAcct1_Id; }

    public void setFinAcct1_Id(UsrNode finAcct1_Id) {
        this.finAcct1_Id = finAcct1_Id;
    }

    public String getFinAcct1_Id2() {
        return finAcct1_Id2;
    }

    public void setFinAcct1_Id2(String finAcct1_Id2) {
        this.finAcct1_Id2 = finAcct1_Id2;
    }


    public UsrNode getFinDept1_Id() {
        return finDept1_Id;
    }

    public void setFinDept1_Id(UsrNode finDept1_Id) {
        this.finDept1_Id = finDept1_Id;
    }

    public String getFinDept1_Id2() {
        return finDept1_Id2;
    }

    public void setFinDept1_Id2(String finDept1_Id2) {
        this.finDept1_Id2 = finDept1_Id2;
    }




    public SysNode getSysFinCurcy1_Id() {
        return sysFinCurcy1_Id;
    }

    public void setSysFinCurcy1_Id(SysNode sysFinCurcy1_Id) {
        this.sysFinCurcy1_Id = sysFinCurcy1_Id;
    }

    public String getSysFinCurcy1_Id2() {
        return sysFinCurcy1_Id2;
    }

    public void setSysFinCurcy1_Id2(String sysFinCurcy1_Id2) {
        this.sysFinCurcy1_Id2 = sysFinCurcy1_Id2;
    }




    public UsrNode getFinTaxLne1_Id() {
        return finTaxLne1_Id;
    }

    public void setFinTaxLne1_Id(UsrNode finTaxLne1_Id) {
        this.finTaxLne1_Id = finTaxLne1_Id;
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

    public void setFinTaxLne1_Type1_Id2(String finTaxLne1_Type1_Id2) { this.finTaxLne1_Type1_Id2 = finTaxLne1_Type1_Id2; }

    public String getFinTaxLne1_Code() {
        return finTaxLne1_Code;
    }

    public void setFinTaxLne1_Code(String finTaxLne1_Code) {
        this.finTaxLne1_Code = finTaxLne1_Code;
    }


    public UsrFinFmla getAmtFinFmla1_Id() {
        return amtFinFmla1_Id;
    }

    public void setAmtFinFmla1_Id(UsrFinFmla amtFinFmla1_Id) {
        this.amtFinFmla1_Id = amtFinFmla1_Id;
    }

    public String getAmtFinFmla1_Id2() {
        return amtFinFmla1_Id2;
    }

    public void setAmtFinFmla1_Id2(String amtFinFmla1_Id2) {
        this.amtFinFmla1_Id2 = amtFinFmla1_Id2;
    }

    public UsrNode getAmtFinTxactItm1_Id() {
        return amtFinTxactItm1_Id;
    }

    public void setAmtFinTxactItm1_Id(UsrNode amtFinTxactItm1_Id) {
        this.amtFinTxactItm1_Id = amtFinTxactItm1_Id;
    }

    public String getAmtFinTxactItm1_Id2() {
        return amtFinTxactItm1_Id2;
    }

    public void setAmtFinTxactItm1_Id2(String amtFinTxactItm1_Id2) {
        this.amtFinTxactItm1_Id2 = amtFinTxactItm1_Id2;
    }

    public BigDecimal getAmtFinTxactItm1_EI1_Rate() {
        return amtFinTxactItm1_EI1_Rate;
    }

    public void setAmtFinTxactItm1_EI1_Rate(BigDecimal amtFinTxactItm1_EI1_Rate) { this.amtFinTxactItm1_EI1_Rate = amtFinTxactItm1_EI1_Rate; }

    public String getAmtFinTxactItm1_FinAcct1_Id2() {
        return amtFinTxactItm1_FinAcct1_Id2;
    }

    public void setAmtFinTxactItm1_FinAcct1_Id2(String amtFinTxactItm1_FinAcct1_Id2) { this.amtFinTxactItm1_FinAcct1_Id2 = amtFinTxactItm1_FinAcct1_Id2; }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCalc() {
        return statusCalc;
    }

    public void setStatusCalc(String statusCalc) {
        this.status = statusCalc;
    }


    public BigDecimal getAmtBegBal() {
        return amtBegBal;
    }

    public void setAmtBegBal(BigDecimal amtBegBal) { this.amtBegBal = amtBegBal; }

    public BigDecimal getAmtBegBalCalc() {
        return amtBegBalCalc;
    }

    public void setAmtBegBalCalc(BigDecimal amtBegBalCalc) {
        this.amtBegBalCalc = amtBegBalCalc;
    }



    public BigDecimal getAmtEndBal() {
        return amtEndBal;
    }

    public void setAmtEndBal(BigDecimal amtEndBal) {
        this.amtEndBal = amtEndBal;
    }

    public BigDecimal getAmtEndBalCalc() {
        return amtEndBalCalc;
    }

    public void setAmtEndBalCalc(BigDecimal amtEndBalCalc) {
        this.amtEndBalCalc = amtEndBalCalc;
    }




    public BigDecimal getAmtDebt() {
        return amtDebt;
    }

    public void setAmtDebt(BigDecimal amtDebt) {
        this.amtDebt = amtDebt;
    }

    public BigDecimal getAmtCred() {
        return amtCred;
    }

    public void setAmtCred(BigDecimal amtCred) {
        this.amtCred = amtCred;
    }

    public BigDecimal getAmtNet() {
        return amtNet;
    }

    public void setAmtNet(BigDecimal amtNet) {
        this.amtNet = amtNet;
    }


    public String getFinAcct2_Id2() {
        return finAcct2_Id2;
    }

    public void setFinAcct2_Id2(String finAcct2_Id2) {
        this.finAcct2_Id2 = finAcct2_Id2;
    }

    public BigDecimal getAmtCalc() {
        return amtCalc;
    }

    public void setAmtCalc(BigDecimal amtCalc) {
        this.amtCalc = amtCalc;
    }



    public UsrNode getFinBal1_Id() { return finBal1_Id; }

    public void setFinBal1_Id(UsrNode finTax_1_Id) { this.finBal1_Id = finTax_1_Id; }

    public String getFinBal1_Id2() { return finBal1_Id2; }

    public void setFinBal1_Id2(String finBal1_Id2) { this.finBal1_Id2 = finBal1_Id2; }


    public UsrNode getFinBalSet1_Id() { return finBalSet1_Id; }

    public void setFinBalSet1_Id(UsrNode finTax_1_Id) { this.finBalSet1_Id = finTax_1_Id; }

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

    public Boolean getFinTxactItms1_SysFinCurcyEqCalc() { return finTxactItms1_SysFinCurcyEqCalc; }

    public void setFinTxactItms1_SysFinCurcyEqCalc(Boolean finTxactItms1_SysFinCurcyEqCalc) {this.finTxactItms1_SysFinCurcyEqCalc = finTxactItms1_SysFinCurcyEqCalc; }


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

    public UsrFinHow getFinHow1_Id() {
        return finHow1_Id;
    }

    public void setFinHow1_Id(UsrFinHow finHow1_Id) {
        this.finHow1_Id = finHow1_Id;
    }

    public String getWhatText1() {
        return whatText1;
    }

    public void setWhatText1(String whatText1) {
        this.whatText1 = whatText1;
    }

    public UsrFinWhat getFinWhat1_Id() {
        return finWhat1_Id;
    }

    public void setFinWhat1_Id(UsrFinWhat finWhat1_Id) {
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

    public UsrFinWhy getFinWhy1_Id() {
        return finWhy1_Id;
    }

    public void setFinWhy1_Id(UsrFinWhy finWhy1_Id) {
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




    public Boolean updateCalcVals(DataManager dataManager){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateDesc1(dataManager) || isChanged;
        isChanged = this.updateInst1(dataManager) || isChanged;
        isChanged = this.updateName1(dataManager) || isChanged;
        isChanged = this.updateId2Calc(dataManager) || isChanged;
        isChanged = this.updateId2Cmp(dataManager) || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateCalcVals(DataManager dataManager, Integer option){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateCalcVals(dataManager);

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2(DataManager dataManager) {
        String logPrfx = "updateId2";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String l_id2_ = this.id2;
        String l_id2 = this.id2Calc;
        if(!Objects.equals(l_id2_, l_id2)){
            this.setId2(l_id2);
            logger.debug(logPrfx + " --- id2: " + l_id2);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Deps(DataManager dataManager) {
        String logPrfx = "updateId2Deps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2Calc(DataManager dataManager) {
        String logPrfx = "updateId2Calc";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        final String delim = "/";
        String l_id2Calc_ = this.id2Calc;
        String l_id2Calc = this.parent1_Id != null
                ? this.parent1_Id2 + delim
                + (this.name1 != null ? this.name1 : "<null>")
                : this.name1 ;
        if (!Objects.equals(l_id2Calc_, l_id2Calc)) {
            this.setId2Calc(l_id2Calc);
            logger.debug(logPrfx + " --- id2Calc:" + l_id2Calc);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

    public Boolean updateId2CalcDeps(DataManager dataManager) {
        String logPrfx = "updateId2CalcDeps";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Cmp(dataManager) || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

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

    public Boolean updateId2Dup(DataManager dataManager) {
        String logPrfx = "updateId2Dup";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        Integer l_id2Dup_ = this.id2Dup;
        if (this.id2 != null){
            String id2Qry = "select count(e) from enty_" + this.getClass().getSimpleName() + " e"
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


    public Boolean updateName1(DataManager dataManager){
        String logPrfx = "updateName1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;
        String l_name1_ = this.name1;
        String l_name1 = this.inst1;
        if (!Objects.equals(l_name1_, l_name1)) {
            this.setName1(l_name1);
            logger.debug(logPrfx + " --- name1:" + l_name1);
            isChanged = true;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

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

    public Boolean updateInst1(DataManager dataManager){
        String logPrfx = "updateInst1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;


        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


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

    public Boolean updateDesc1(DataManager dataManager){
        String logPrfx = "updateDesc1";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }



    public Boolean updateNm1s1Inst1Ts1() {
        // Assume ts1, ts2, ts3 is not null
        String logPrfx = "updateNm1s1Inst1Ts1()";
        logger.trace(logPrfx + " --> ");


        boolean isChanged = false;

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();

        LocalDateTime instTs1_Ts1_ = nm1s1Inst1Ts1.getElTs();
        LocalDateTime instTs1_Ts1 = null;

        switch (className) {
            // ts1 only
            case "FinTxactSet", "FinTxact" -> {
                if (ts1.getElTs() != null) {
                    instTs1_Ts1 = ts1.getElTs();
                }
                if (!Objects.equals(instTs1_Ts1_, instTs1_Ts1)) {
                    logger.debug(logPrfx + " --- calling instTs1.setTs1((" + instTs1_Ts1 == null ? "null" : instTs1_Ts1.format(frmtTs) + ")");
                    this.nm1s1Inst1Ts1.setElTs(instTs1_Ts1);
                    isChanged = true;
                }
            }
            // ts2 otherwise ts1
            case "FinTxactItm", "FinStmtItm" -> {
                if (ts3.getElTs() != null) {
                    instTs1_Ts1 = ts3.getElTs();
                } else if (ts2.getElTs() != null) {
                    instTs1_Ts1 = ts2.getElTs();
                } else if (ts1.getElTs() != null) {
                    instTs1_Ts1 = ts1.getElTs();
                } else {
                    instTs1_Ts1 = null;
                }
                if (!Objects.equals(instTs1_Ts1_, instTs1_Ts1)) {
                    logger.debug(logPrfx + " --- calling instTs1.setTs1((" + instTs1_Ts1 == null ? "null" : instTs1_Ts1.format(frmtTs) + ")");
                    this.nm1s1Inst1Ts1.setElTs(instTs1_Ts1);
                    isChanged = true;
                }
            }
        }

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }


    public Boolean updateNm1s1Inst1Dt1() {
        // Assume ts1, ts2, ts3 is not null
        String logPrfx = "updateNm1s1Inst1Dt1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();

        LocalDate instDt1_Ts1_ = nm1s1Inst1Dt1.getElDt();
        LocalDate instDt1_Ts1 = null;

        switch (className) {

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

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }

    public Boolean updateNm1s1Inst1Tm1() {
        String logPrfx = "updateNm1s1Inst1Tm1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HHmm")
                .toFormatter();

        switch (className) {

            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }


    public Boolean updateNm1s1Inst1Int1() {
        // Assume id2 is not null
        String logPrfx = "updateNm1s1Inst1Int1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Integer l_instInt2_ = nm1s1Inst1Int1;
        Integer l_instInt2 = null;

        switch (className) {
            case "FinTxact":
            case "FinTxactItm":
            case "FinTxactSet":
                if (id2 != null
                        && id2.length() >= 20){

                    String id2_part = id2.substring(18,20);
                    try{
                        l_instInt2 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }

                    if (!Objects.equals(l_instInt2_, l_instInt2)){
                        logger.debug(logPrfx + " --- calling setInstInt1("+ l_instInt2 +")");
                        this.setNm1s1Inst1Int1(l_instInt2);
                        isChanged = true;
                    }

                }

                break;

            default:
                break;
        }


        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }

    public Boolean updateNm1s1Inst1Int2() {
        // Assume id2 is not null
        String logPrfx = "updateNm1s1Inst1Int2()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Integer l_instInt2_ = nm1s1Inst1Int2;
        Integer l_instInt2 = null;

        switch (className) {
            case "FinTxact":
            case "FinTxactItm":
                if (id2 != null
                        && id2.length() >= 24){


                    String id2_part = id2.substring(22,24);
                    try{
                        l_instInt2 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }

                    if (!Objects.equals(l_instInt2_, l_instInt2)){
                        logger.debug(logPrfx + " --- calling setInstInt2("+ l_instInt2 +")");
                        this.setNm1s1Inst1Int2(l_instInt2);
                        isChanged = true;
                    }

                }
                break;

            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }

    public Boolean updateNm1s1Inst1Int3() {
        // Assume id2 is not null
        String logPrfx = "updateNm1s1Inst1Int3()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        Integer l_instInt3_ = nm1s1Inst1Int3;
        Integer l_instInt3 = null;

        switch (className) {
            case "FinTxactItm" -> {
                if (id2 != null
                        && id2.length() >= 28){

                    String id2_part = id2.substring(26,28);
                    try{
                        l_instInt3 = Integer.parseInt(id2_part);

                    } catch (NumberFormatException e){

                        logger.trace(logPrfx + " ---- NumberFormatException");
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }

                    if (!Objects.equals(l_instInt3_, l_instInt3)){
                        logger.debug(logPrfx + " --- calling setInstInt3("+ l_instInt3 +")");
                        this.setNm1s1Inst1Int3(l_instInt3);
                        isChanged = true;
                    }

                }
            }

        }

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }

    public Boolean updateTs1() {
        // Assume id2 is not null
        String logPrfx = "updateTs1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        LocalDateTime ts1_ts1_ = ts1.getElTs();
        LocalDateTime ts1_ts1 = null;

        switch (className) {
            case "FinTxactSet":
            case "FinTxact":
            case "FinTxactItm":
                if (ts2 != null
                        && ts2.getElTs() != null) {
                    logger.trace(logPrfx + " ---- ts2 != null");
                    logger.trace(logPrfx + " <--- ");
                    return isChanged;

                }
                if (id2 != null
                        && id2.length() >= 10){

                    DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                            .appendPattern("yyyy-MM-dd[THH:mm:ss]")
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                            .toFormatter();
                    String id2_part = id2.substring(2,10);
                    try{
                        ts1_ts1 = LocalDateTime.parse(id2_part,frmtTs);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }

                    if (!Objects.equals(ts1_ts1_, ts1_ts1)){
                        logger.debug(logPrfx + " --- calling ts1.setTs1(("+ ts1_ts1.format(frmtTs) +")");
                        this.ts1.setElTs(ts1_ts1);
                        isChanged = true;
                    }
                }
                break;

            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }


    public Boolean updateTs2() {
        // Assume id2 is not null
        String logPrfx = "updateTs2()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        LocalDateTime ts2_ts1_ = ts2.getElTs();
        LocalDateTime ts2_ts1 = null;

        switch (className) {
            case "FinTxactItm":
            case "FinStmtItm":
                if (ts1 == null
                        || ts1.getElTs() == null
//                        || beg2 == null
//                        || beg2.getTs1() == null
                ) {
                    logger.trace(logPrfx + " ---- ts1 != null");
                    logger.trace(logPrfx + " <--- ");
                    return isChanged;

                }
                if (id2 != null
                        && id2.length() >= 10){

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
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }

                    try{
                        ts2_ts1 = LocalDateTime.parse(id2_part,frmtTs);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }


                    if (!Objects.equals(ts2_ts1_, ts2_ts1)){
                        logger.debug(logPrfx + " --- calling beg2.setTs1(("+ ts2_ts1.format(frmtTs) +")");
                        this.ts2.setElTs(ts2_ts1);
                        isChanged = true;
                    }
                }
                break;

            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }

    public Boolean updateTs3() {
        // Assume id2 is not null
        String logPrfx = "updateTs3()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        LocalDateTime ts3_ts1_ = ts3.getElTs();
        LocalDateTime ts3_ts1 = null;

        switch (className) {
            case "FinStmt":
                if (id2 != null
                        && id2.length() >= 10){

                    DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                            .appendPattern("yyyy-MM-dd[THH:mm:ss]")
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                            .toFormatter();
                    Integer sep1 = id2.indexOf("//D");
                    Integer sep2 = id2.indexOf("//A");
                    if (sep1 < 0 || sep2 < 0){
                        logger.trace(logPrfx + " ---- id2 does not match FinStmt pattern.");
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }

                    String id2_part = id2.substring(sep1+"//D".length()-1,sep2);
                    try{
                        ts3_ts1 = LocalDateTime.parse(id2_part,frmtTs);

                    } catch (DateTimeParseException e){

                        logger.trace(logPrfx + " ---- DateTimeParseException");
                        logger.trace(logPrfx + " <--- ");
                        return isChanged;
                    }

                    if (!Objects.equals(ts3_ts1_, ts3_ts1)){
                        logger.debug(logPrfx + " --- calling end1.setTs1(("+ ts3_ts1.format(frmtTs) +")");
                        this.ts3.setElTs(ts3_ts1);
                        isChanged = true;
                    }
                }
                break;

            default:
                break;
        }

        logger.trace(logPrfx + " <--- ");
        return isChanged;
    }



    public Boolean updateGenAgent1(){
        String logPrfx = "updateGenAgent1()";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        UsrNode l_genAgent1_ = getGenAgent1_Id();
        UsrNode l_genAgent1 = null;


        switch (className) {

            default:
                break;
        }

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }


    public static <UsrNodeT extends UsrNode> UsrNodeT getNodeById2(Class<UsrNodeT> type, DataManager dataManager, @NotNull String crtieria_Id2) {
        final Logger logger = LoggerFactory.getLogger(UsrNode.class.getClass());
        String logPrfx = "findNodeById2";
        logger.trace(logPrfx + " --> ");

        if (crtieria_Id2 == null) {
            logger.debug(logPrfx + " --- crtieria_Id2 is null.");
            logger.trace(logPrfx + " <-- ");
            return null;
        }

        String qry = "select e from enty_" + type.getSimpleName() + " e"
                + " and e.id2 = :id2";
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:id2: " + crtieria_Id2);

        UsrNodeT node = null;
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



    public static <UsrNodeT extends UsrNode> UsrNodeT getNodesBySortIdx(Class<UsrNodeT> type, DataManager dataManager, @NotNull Integer sortIdx, UsrNode parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNode.class.getClass());
        String logPrfx = "getNodesBySortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_" + type.getSimpleName() + " e"
                + " and e.parent1_Id = :parent1_Id"
                + " and e.sortIdx = :sortIdx"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdx: " + sortIdx);

        UsrNodeT usrNode = null;
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


    public static <UsrNodeT extends UsrNode>  List<UsrNodeT> getNodesBtwnSortIdx(Class<UsrNodeT> type, DataManager dataManager, @NotNull Integer sortIdxA , @NotNull Integer sortIdxB, UsrNode parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNode.class.getClass());
        String logPrfx = "getNodesBtwnSortIdx";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_" + type.getSimpleName() + " e"
                + " and e.parent1_Id = :parent1_Id"
                + " and e.sortIdx > :sortIdxA"
                + " and e.sortIdx < :sortIdxB"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());
        logger.debug(logPrfx + " --- qry:sortIdxA: " + sortIdxA);
        logger.debug(logPrfx + " --- qry:sortIdxB: " + sortIdxB);

        List<UsrNodeT> nodes = null;
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

    public static <UsrNodeT extends UsrNode> List<UsrNodeT> getNodesByParent1(Class<UsrNodeT> type, DataManager dataManager, UsrNode parent1_Id) {
        final Logger logger = LoggerFactory.getLogger(UsrNode.class.getClass());
        String logPrfx = "getNodeListByParent1";
        logger.trace(logPrfx + " --> ");

        String qry = "select e from enty_" + type.getSimpleName() + " e"
                + " and e.parent1_Id = :parent1_Id"
                ;
        logger.debug(logPrfx + " --- qry: " + qry);
        logger.debug(logPrfx + " --- qry:parent1_Id: " + parent1_Id.getId2());

        List<UsrNodeT> nodes = null;
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


    public static <UsrNodeT extends UsrNode> List<String> getStatusList(Class<UsrNodeT> type, DataManager dataManager){
        final Logger logger = LoggerFactory.getLogger(UsrNode.class.getClass());
        String logPrfx = "getStatusList";
        logger.trace(logPrfx + " --> ");

        String qry = "select distinct e.status "
                + " from enty_" + type.getSimpleName() + " e"
                + " and e.status IS NOT NULL"
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


    public String getId2CalcFrFields(){
        String logPrfx = "getId2CalcFrFields";
        logger.trace(logPrfx + " --> ");

        final String SEP = "/";
        final String SEP2 = ";";
        StringBuilder sb = new StringBuilder();

        DateTimeFormatter frmtTs = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMdd HHmm")
                .toFormatter();
        DateTimeFormatter frmtDt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        DateTimeFormatter frmtTm = new DateTimeFormatterBuilder()
                .appendPattern("HH:mm")
                .toFormatter();
        DecimalFormat frmtDec = new DecimalFormat("+0.00;-0.00");


        //require name1
        switch (className) {
            case "FinAcct" -> {
                if (name1 == null) {
                    logger.debug(logPrfx + " --- name1: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- name1: " + name1);
                }

            }
        }

        //require instTs1.ts1
        switch (className) {
            case "timestamp based type placeholder" -> {
                if (nm1s1Inst1Ts1 == null) {
                    logger.debug(logPrfx + " --- idTs: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                }
                if (nm1s1Inst1Ts1.getElTs() == null) {
                    logger.debug(logPrfx + " --- idTs.getTs1(): null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- idTs.getTs1(): " + nm1s1Inst1Ts1.getElTs().format(frmtTs));
                }
            }
        }

        //require idDt.date1
        switch (className) {
            case "FinTxactSet", "FinTxact", "FinTxactItm","FinStmt" , "FinStmtItm" -> {
                if (nm1s1Inst1Dt1 == null) {
                    logger.debug(logPrfx + " --- idDt: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                }
                if (nm1s1Inst1Dt1.getElDt() == null) {
                    logger.debug(logPrfx + " --- idDt.getDate1(): null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- idDt.getDate1(): " + nm1s1Inst1Dt1.getElDt().format(frmtDt));
                }
            }
        }

        //require beg1.ts1
        switch (className) {
            case "FinBal" -> {
                if (ts1 == null) {
                    logger.debug(logPrfx + " --- beg1: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                }
                if (ts1.getElTs() == null) {
                    logger.debug(logPrfx + " --- beg1.getTs1(): null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- beg1.getTs1(): " + ts1.getElTs().format(frmtTs));
                }
            }
        }

        //require end1.ts1
        switch (className) {
            case "FinBal" -> {
                if (ts3 == null) {
                    logger.debug(logPrfx + " --- end1: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                }
                if (ts3.getElTs() == null) {
                    logger.debug(logPrfx + " --- end1.getTs1(): null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- end1.getTs1(): " + ts3.getElTs().format(frmtTs));
                }
            }
        }

        //require finAcct1_Id
        switch (className) {
            case "FinStmt" -> {
                if (finAcct1_Id == null) {
                    logger.debug(logPrfx + " --- finAcct1_Id: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    logger.debug(logPrfx + " --- finAcct1_Id: " + finAcct1_Id.getId());
                }

            }
        }

        //require finStmt1_Id -> finAcct1_Id
        switch (className) {
            case "FinStmtItm" ->{
                if (finStmt1_Id == null) {
                    logger.debug(logPrfx + " --- finStmt1_Id: null");
                    logger.trace(logPrfx + " <--- ");
                    return "";
                } else {
                    if (finStmt1_Id.getFinAcct1_Id()== null){
                        logger.debug(logPrfx + " --- finStmt1_Id.finAcct1_Id: null");
                        logger.trace(logPrfx + " <--- ");
                        return "";
                    }else{
                        logger.debug(logPrfx + " --- finStmt1_Id.finAcct1_Id: " + finStmt1_Id.getFinAcct1_Id().getId());
                    }
                }
            }
        }

        //update type1_Id2
        if (type1_Id != null){
            type1_Id2 = type1_Id.getId2();
        }

        //create id2
        switch (className) {
            case "GenAgent" ->{
                switch (type1_Id2){
                    case "/P" -> {
                        sb.append("P-" +  String.valueOf(name1));
                    }
                    case "/O" -> {
                        sb.append("O-" +  String.valueOf(name1));
                    }
                    case "/C" -> {
                        String a1fn = genAgent1_Id == null ? "" : String.valueOf(genAgent1_Id.getNameFrst());
                        String a1ln = genAgent1_Id == null ? "" : String.valueOf(genAgent1_Id.getNameLast());
                        String a2fn = genAgent2_Id == null ? "" : String.valueOf(genAgent2_Id.getNameFrst());
                        String a2ln = genAgent2_Id == null ? "" : String.valueOf(genAgent2_Id.getNameLast());
                        //name1
                        sb.append("C-");
                        sb.append(a1fn.charAt(0));
                        sb.append(a1ln.equals(String.valueOf(name1)) ? "" : "(" + a1ln + ")");
                        sb.append("&");
                        sb.append(a2fn.charAt(0));
                        sb.append(a2ln.equals(String.valueOf(name1)) ? "" : "(" + a2ln + ")");
                        sb.append(" ").append(String.valueOf(name1));
                    }
                    default -> {
                        sb.append(name1);
                    }
                }

            }
            case "GenChan", "GenDocVer" ->{
                //name1
                sb.append(name1);
            }
            case "FinAcct"->{
                //parent
                sb.append(parent1_Id == null ? "" : parent1_Id.getId2());
                //name1
                sb.append(SEP).append(name1);
            }
            case "FinBalSet" ->{
                //name1
                sb.append(name1);
                //finDept1
                sb.append(SEP2 + "D=").append(finDept1_Id == null ? "" : finDept1_Id.getId2()).append("");
            }
            case "FinBal" -> {
                if (finBalSet1_Id != null) {
                    //finBalSet1
                    sb.append(finBalSet1_Id.getId2());
                    //finAcct1
                    sb.append(SEP2 + "A=").append(finAcct1_Id == null ? "" : finAcct1_Id.getId2());

                } else {
                    //beg1.ts1
                    sb.append("B=").append(ts1.getElTs().format(frmtDt));
                    //end1.Ts1
                    sb.append(SEP2 + "E=").append(ts3.getElTs().format(frmtDt));
                    //finDept1
                    sb.append(SEP2 + "D=").append(finDept1_Id == null ? "" : finDept1_Id.getId2());
                    //finAcct1
                    sb.append(SEP2 + "A=").append(finAcct1_Id == null ? "" : finAcct1_Id.getId2());
                }
            }
            case "FinTxactSet" -> {
                //idDt
                sb.append(SEP + "D").append(nm1s1Inst1Dt1.getElDt().format(frmtDt));
                //IdX
                sb.append(SEP + "X").append(String.format("%02d", nm1s1Inst1Int1 == null ? 0 : nm1s1Inst1Int1));
            }
            case "FinTxact" -> {
                //idDt
                sb.append(SEP + "D").append(nm1s1Inst1Dt1.getElDt().format(frmtDt));
                //IdX
                sb.append(SEP + "X").append(String.format("%02d", nm1s1Inst1Int1 == null ? 0 : nm1s1Inst1Int1));
                //IdY
                sb.append(SEP + "Y").append(String.format("%02d", nm1s1Inst1Int2 == null ? 0 : nm1s1Inst1Int2));
            }
            case "FinTxactItm" ->{
                //idDt
                sb.append(SEP + "D").append(nm1s1Inst1Dt1.getElDt().format(frmtDt));
                //IdX
                sb.append(SEP + "X").append(String.format("%02d", nm1s1Inst1Int1 == null ? 0 : nm1s1Inst1Int1));
                //IdY
                sb.append(SEP + "Y").append(String.format("%02d", nm1s1Inst1Int2 == null ? 0 : nm1s1Inst1Int2));
                //IdZ
                sb.append(SEP + "Z").append(String.format("%02d", nm1s1Inst1Int3 == null ? 0 : nm1s1Inst1Int3));
            }
            case "FinStmt" ->{
                //finAcct1
                sb.append(finAcct1_Id.getId2());
                //idDt
                sb.append(SEP + "D").append(nm1s1Inst1Dt1.getElDt().format(frmtDt));
            }
            case "FinStmtItm" ->{
                //finAcct1
                sb.append(finStmt1_Id.getFinAcct1_Id().getId2());
                //idDt
                //sb.append(SEP + "D").append(idTs.getTs1().format(frmtDt));
                //idDt
                sb.append(SEP + "D").append(nm1s1Inst1Dt1.getElDt().format(frmtDt));
                //IdX
                sb.append(SEP + "X").append(String.format("%02d", nm1s1Inst1Int1 == null ? 0 : nm1s1Inst1Int1));
                //amtNet
                sb.append(SEP + "A").append(frmtDec.format(amtNet));
            }
        }

        switch (className){
        }



        logger.debug(logPrfx + " --- sb: " + sb);
        logger.trace(logPrfx + " <--- ");
        return sb.toString();

    }

}
