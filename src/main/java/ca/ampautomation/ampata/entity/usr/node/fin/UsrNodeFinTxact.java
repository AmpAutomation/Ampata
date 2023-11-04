package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.base.UsrItemBase;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.querycondition.PropertyCondition;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_UsrNodeFinTxact")
public class UsrNodeFinTxact extends UsrNodeBase {


}