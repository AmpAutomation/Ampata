package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_UsrNodeFinStmtItm")
public class UsrNodeFinStmtItm extends UsrNodeBase {

    public String getLine1(){
        return getTxt1();
    }
    public void setLine1(String line1){ setTxt1(line1);
    }
}