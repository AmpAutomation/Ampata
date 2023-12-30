package ca.ampautomation.ampata.entity.usr.node.fin;

import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBaseType;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;

import jakarta.persistence.Entity;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;
import java.util.Optional;

@JmixEntity
@Entity(name = "enty_UsrNodeFinAcct")
public class UsrNodeFinAcct extends UsrNodeBase {

}