package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import ca.ampautomation.ampata.entity.HasTmst;

@JmixEntity
@Entity(name = "enty_UsrNodeFinBal")
public class UsrNodeFinBal extends UsrNodeBase {

}