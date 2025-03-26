package ca.ampautomation.ampata.entity.usr.node.base;

import ca.ampautomation.ampata.entity.HasDate;
import ca.ampautomation.ampata.entity.HasTime;
import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.node.fin.SysNodeFinCurcy;
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
import ca.ampautomation.ampata.other.Globals;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.annotation.TenantId;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.net.URI;
import java.util.*;

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

public class UsrNodeBase implements AcceptsTenant, Globals {

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
    protected UsrNodeBase parent1_Id;

    @Column(name = "PARENT1__ID2")
    protected String parent1_Id2;

    @Column(name = "PARENT1__ID2_TRGT")
    protected String parent1_Id2Trgt;

    @Column(name = "PARENT1__TYPE1__ID2")
    protected String parent1_Type1_Id2;

    @Column(name = "PARENT1__EI1__ROLE")
    protected String parent1_EI1_Role;

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
    @EmbeddedParameters(nullAllowed = true)
    @Embedded
    protected HasTmst nm1s1Inst1Ts1;

    @AttributeOverrides({
            @AttributeOverride(name = "elDt", column = @Column(name = "NM1S1_INST1_DT1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "NM1S1_INST1_DT1_EL_DT_DAY"))
    })
    @EmbeddedParameters(nullAllowed = true)
    @Embedded
    protected HasDate nm1s1Inst1Dt1;

    @AttributeOverrides({
            @AttributeOverride(name = "elTm", column = @Column(name = "NM1S1_INST1_TM1_EL_TM")),
            @AttributeOverride(name = "elTmHr", column = @Column(name = "NM1S1_INST1_TM1_EL_TM_HR")),
            @AttributeOverride(name = "elTmMin", column = @Column(name = "NM1S1_INST1_TM1_EL_TM_MIN"))
    })
    @EmbeddedParameters(nullAllowed = true)
    @Embedded
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
    @Lob
    protected String txt1;

    @Column(name = "TXT2")
    @Lob
    protected String txt2;

    @Column(name = "TXT4")
    @Lob
    protected String txt4;

    @Column(name = "TXT3")
    @Lob
    protected String txt3;

    @Column(name = "INT1")
    protected Integer int1;

    @Column(name = "INT2")
    protected Integer int2;

    @Column(name = "INT3")
    protected Integer int3;

    @Column(name = "INT4")
    protected Integer int4;


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
    @EmbeddedParameters(nullAllowed = true)
    @Embedded
    protected HasTmst ts1;

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
    @EmbeddedParameters(nullAllowed = true)
    @Embedded
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
    @EmbeddedParameters(nullAllowed = true)
    @Embedded
    protected HasTmst ts4;


    @AttributeOverrides({
            @AttributeOverride(name = "elDt", column = @Column(name = "DT1_EL_DT")),
            @AttributeOverride(name = "elDtYr", column = @Column(name = "DT1_EL_DT_YR")),
            @AttributeOverride(name = "elDtQtr", column = @Column(name = "DT1_EL_DT_QTR")),
            @AttributeOverride(name = "elDtMon", column = @Column(name = "DT1_EL_DT_MON")),
            @AttributeOverride(name = "elDtMon2", column = @Column(name = "DT1_EL_DT_MON2")),
            @AttributeOverride(name = "elDtDay", column = @Column(name = "DT1_EL_DT_DAY"))
    })
    @EmbeddedParameters(nullAllowed = true)
    @Embedded
    protected HasDate dt1;

    @EmbeddedParameters(nullAllowed = true)
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
    protected UsrNodeFinTxactSet finTxactSet1_Id;

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
    protected UsrNodeFinTxact finTxact1_Id;

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
    protected UsrNodeFinStmt finStmt1_Id;

    @Column(name = "FIN_STMT1__ID2")
    protected String finStmt1_Id2;

    @Column(name = "TXT_CURCY_EXCH")
    @Lob
    protected String txtCurcyExch;

    @Column(name = "TXT_REF_ID")
    @Lob
    protected String txtRefId;


    @JoinColumn(name = "FIN_STMT_ITM1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeFinStmtItm finStmtItm1_Id;

    @Column(name = "FIN_STMT_ITM1__ID2")
    protected String finStmtItm1_Id2;

    @Column(name = "FIN_STMT_ITM1__ID2_TRGT")
    protected String finStmtItm1_Id2Trgt;

    @Column(name = "FIN_STMT_ITM1__TXT1")
    @Lob
    protected String finStmtItm1_Txt1;

    @Column(name = "FIN_STMT_ITM1__TXT2")
    @Lob
    protected String finStmtItm1_Txt2;

    @Column(name = "FIN_STMT_ITM1__TXT3")
    @Lob
    protected String finStmtItm1_Txt3;

    @Column(name = "FIN_STMT_ITM1__TXT4")
    @Lob
    protected String finStmtItm1_Txt4;

    @Column(name = "FIN_STMT_ITM1__TXT_CURCY_EXCH")
    protected String finStmtItm1_TxtCurcyExch;

    @Column(name = "FIN_STMT_ITM1__TXT_REF_ID")
    protected String finStmtItm1_TxtRefId;

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
    protected UsrNodeFinTaxLne finTaxLne1_Id;

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

    @Column(name = "AMT_NET2", precision = 19, scale = 2)
    protected BigDecimal amtNet2;

    @JoinColumn(name = "SYS_NODE_FIN_CURCY1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected SysNodeFinCurcy sysNodeFinCurcy1_Id;

    @Column(name = "SYS_NODE_FIN_CURCY1__ID2")
    protected String sysNodeFinCurcy1_Id2;

    @JoinColumn(name = "AMT_CALC_FIN_TXACT_ITM1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrNodeFinTxactItm amtCalcFinTxactItm1_Id;

    @Column(name = "AMT_CALC_FIN_TXACT_ITM1__ID2")
    protected String amtCalcFinTxactItm1_Id2;

    @Column(name = "AMT_CALC_FIN_TXACT_ITM1__EI1__RATE", precision = 19, scale = 9)
    protected BigDecimal amtCalcFinTxactItm1_EI1_Rate;

    @Column(name = "AMT_CALC_FIN_TXACT_ITM1__FIN_ACCT1__ID2")
    protected String amtCalcFinTxactItm1_FinAcct1_Id2;

    @Column(name = "AMT_CALC", precision = 19, scale = 2)
    protected BigDecimal amtCalc;

    @JoinColumn(name = "AMT_CALC_GEN_FMLA1__ID")
    @ManyToOne(fetch = FetchType.LAZY)
    protected UsrItemGenFmla amtCalcGenFmla1_Id;

    @Column(name = "AMT_CALC_GEN_FMLA1__ID2")
    protected String amtCalcGenFmla1_Id2;


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

    @Column(name = "FIN_TXACT_ITMS1__AMT_NET2_SUM_CALC", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtNet2SumCalc;

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

    @Column(name = "FIN_TXACT_ITMS1__AMT_NET2_SUM_DIFF", precision = 19, scale = 2)
    protected BigDecimal finTxactItms1_AmtNet2SumDiff;

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

    public String getParent1_Id2Trgt() { return parent1_Id2Trgt; }

    public void setParent1_Id2Trgt(String parent1_Id2Trgt) { this.parent1_Id2Trgt = parent1_Id2Trgt; }

    public String getParent1_Type1_Id2() { return parent1_Type1_Id2; }

    public void setParent1_Type1_Id2(String parent1_Type1_Id2) { this.parent1_Type1_Id2 = parent1_Type1_Id2; }

    public String getParent1_EI1_Role() { return parent1_EI1_Role; }

    public void setParent1_EI1_Role(String parent1_EI1_Role) { this.parent1_EI1_Role = parent1_EI1_Role; }

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

    public String getTxt4() { return txt4; }

    public void setTxt4(String txt4) { this.txt4 = txt4; }


    public Integer getInt1() { return int1; }

    public void setInt1(Integer int1) { this.int1 = int1; }

    public Integer getInt2() { return int2; }

    public void setInt2(Integer int2) { this.int2 = int2; }

    public Integer getInt3() { return int3; }

    public void setInt3(Integer int3) { this.int3 = int3; }

    public Integer getInt4() { return int4; }

    public void setInt4(Integer int4) { this.int4 = int4; }

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


    public UsrNodeFinTxactSet getFinTxactSet1_Id() { return finTxactSet1_Id; }

    public void setFinTxactSet1_Id(UsrNodeFinTxactSet finTxactSet1_Id) { this.finTxactSet1_Id = finTxactSet1_Id; }

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





    public UsrNodeFinTxact getFinTxact1_Id() {return finTxact1_Id; }

    public void setFinTxact1_Id(UsrNodeFinTxact finTxact1_Id) { this.finTxact1_Id = finTxact1_Id; }

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

    public UsrNodeFinStmt getFinStmt1_Id() { return finStmt1_Id; }

    public void setFinStmt1_Id(UsrNodeFinStmt finStmt1_Id) { this.finStmt1_Id = finStmt1_Id; }

    public String getTxtCurcyExch() { return txtCurcyExch; }

    public void setTxtCurcyExch(String txtCurcyExch) { this.txtCurcyExch = txtCurcyExch; }

    public String getTxtRefId() { return txtRefId; }

    public void setTxtRefId(String txtRefId) { this.txtRefId = txtRefId; }


    public UsrNodeFinStmtItm getFinStmtItm1_Id() { return finStmtItm1_Id; }

    public void setFinStmtItm1_Id(UsrNodeFinStmtItm finStmtItm1_Id) { this.finStmtItm1_Id = finStmtItm1_Id; }

    public String getFinStmtItm1_Id2() { return finStmtItm1_Id2; }

    public void setFinStmtItm1_Id2(String finStmtItm1_Id2) { this.finStmtItm1_Id2 = finStmtItm1_Id2; }

    public String getFinStmtItm1_Id2Trgt() { return finStmtItm1_Id2Trgt; }

    public void setFinStmtItm1_Id2Trgt(String finStmtItm1_Id2Trgt) {this.finStmtItm1_Id2Trgt = finStmtItm1_Id2Trgt;}

    public String getFinStmtItm1_Txt1() { return finStmtItm1_Txt1; }

    public void setFinStmtItm1_Txt1(String finStmtItm1_Txt1) { this.finStmtItm1_Txt1 = finStmtItm1_Txt1; }

    public String getFinStmtItm1_Txt2() { return finStmtItm1_Txt2; }

    public void setFinStmtItm1_Txt2(String finStmtItm1_Txt2) { this.finStmtItm1_Txt2 = finStmtItm1_Txt2; }

    public String getFinStmtItm1_Txt3() { return finStmtItm1_Txt3; }

    public void setFinStmtItm1_Txt3(String finStmtItm1_Txt3) { this.finStmtItm1_Txt3 = finStmtItm1_Txt3; }

    public String getFinStmtItm1_Txt4() { return finStmtItm1_Txt4; }

    public void setFinStmtItm1_Txt4(String finStmtItm1_Txt4) { this.finStmtItm1_Txt4 = finStmtItm1_Txt4; }

    public String getFinStmtItm1_TxtCurcyExch() { return finStmtItm1_TxtCurcyExch; }

    public void setFinStmtItm1_TxtCurcyExch(String finStmtItm1_TxtCurcyExch) { this.finStmtItm1_TxtCurcyExch = finStmtItm1_TxtCurcyExch; }

    public String getFinAcct1_Type1_Id2() { return finAcct1_Type1_Id2; }

    public String getFinStmtItm1_TxtRefId() {return finStmtItm1_TxtRefId;}

    public void setFinStmtItm1_TxtRefId(String finStmtItm1_TxtRefId) {this.finStmtItm1_TxtRefId = finStmtItm1_TxtRefId;}

    public void setFinAcct1_Type1_Id2(String finAcct1_Type1_Id2) { this.finAcct1_Type1_Id2 = finAcct1_Type1_Id2; }


    public UsrNodeFinAcct getFinAcct1_Id() { return finAcct1_Id; }

    public void setFinAcct1_Id(UsrNodeFinAcct finAcct1_Id) { this.finAcct1_Id = finAcct1_Id; }

    public String getFinAcct1_Id2() { return finAcct1_Id2; }

    public void setFinAcct1_Id2(String finAcct1_Id2) { this.finAcct1_Id2 = finAcct1_Id2; }


    public UsrNodeFinDept getFinDept1_Id() { return finDept1_Id; }

    public void setFinDept1_Id(UsrNodeFinDept finDept1_Id) { this.finDept1_Id = finDept1_Id; }

    public String getFinDept1_Id2() { return finDept1_Id2; }

    public void setFinDept1_Id2(String finDept1_Id2) { this.finDept1_Id2 = finDept1_Id2; }




    public SysNodeFinCurcy getSysNodeFinCurcy1_Id() { return sysNodeFinCurcy1_Id; }

    public void setSysNodeFinCurcy1_Id(SysNodeFinCurcy sysNodeFinCurcy1_Id) { this.sysNodeFinCurcy1_Id = sysNodeFinCurcy1_Id; }

    public String getSysNodeFinCurcy1_Id2() { return sysNodeFinCurcy1_Id2; }

    public void setSysNodeFinCurcy1_Id2(String sysNodeFinCurcy1_Id2) { this.sysNodeFinCurcy1_Id2 = sysNodeFinCurcy1_Id2; }




    public UsrNodeFinTaxLne getFinTaxLne1_Id() { return finTaxLne1_Id; }

    public void setFinTaxLne1_Id(UsrNodeFinTaxLne finTaxLne1_Id) { this.finTaxLne1_Id = finTaxLne1_Id; }

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

    public UsrNodeFinTxactItm getAmtCalcFinTxactItm1_Id() { return amtCalcFinTxactItm1_Id; }

    public void setAmtCalcFinTxactItm1_Id(UsrNodeFinTxactItm amtCalcFinTxactItm1_Id) { this.amtCalcFinTxactItm1_Id = amtCalcFinTxactItm1_Id; }

    public String getAmtCalcFinTxactItm1_Id2() { return amtCalcFinTxactItm1_Id2; }

    public void setAmtCalcFinTxactItm1_Id2(String amtCalcFinTxactItm1_Id2) { this.amtCalcFinTxactItm1_Id2 = amtCalcFinTxactItm1_Id2; }

    public BigDecimal getAmtCalcFinTxactItm1_EI1_Rate() { return amtCalcFinTxactItm1_EI1_Rate; }

    public void setAmtCalcFinTxactItm1_EI1_Rate(BigDecimal amtCalcFinTxactItm1_EI1_Rate) { this.amtCalcFinTxactItm1_EI1_Rate = amtCalcFinTxactItm1_EI1_Rate; }

    public String getAmtCalcFinTxactItm1_FinAcct1_Id2() { return amtCalcFinTxactItm1_FinAcct1_Id2; }

    public void setAmtCalcFinTxactItm1_FinAcct1_Id2(String amtCalcFinTxactItm1_FinAcct1_Id2) { this.amtCalcFinTxactItm1_FinAcct1_Id2 = amtCalcFinTxactItm1_FinAcct1_Id2; }


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

    public BigDecimal getAmtNet2() { return amtNet2; }

    public void setAmtNet2(BigDecimal amtNet2) { this.amtNet2 = amtNet2; }


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

    public BigDecimal getFinTxactItms1_AmtNet2SumCalc() { return finTxactItms1_AmtNet2SumCalc; }

    public void setFinTxactItms1_AmtNet2SumCalc(BigDecimal finTxactItms1_AmtNet2SumCalc) { this.finTxactItms1_AmtNet2SumCalc = finTxactItms1_AmtNet2SumCalc; }

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

    public BigDecimal getFinTxactItms1_AmtNet2SumDiff() { return finTxactItms1_AmtNet2SumDiff; }

    public void setFinTxactItms1_AmtNet2SumDiff(BigDecimal finTxactItms1_AmtNet2SumDiff) { this.finTxactItms1_AmtNet2SumDiff = finTxactItms1_AmtNet2SumDiff; }

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

}

