package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_UsrNodeFinStmt")
public class UsrNodeFinStmt extends UsrNodeBase {


}