package ca.ampautomation.ampata.service.usr.edge.gen;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.usr.edge.gen.UsrEdgeGenBasic;
import ca.ampautomation.ampata.entity.usr.item.gen.UsrItemGenTag;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBase;
import ca.ampautomation.ampata.entity.usr.edge.base.UsrEdgeBaseType;
import ca.ampautomation.ampata.other.UpdateOption;
import ca.ampautomation.ampata.screen.usr.edge.base.UsrEdgeBase0BaseComn;
import ca.ampautomation.ampata.screen.usr.edge.base.UsrEdgeBase0BaseMain;
import ca.ampautomation.ampata.service.usr.edge.base.UsrEdgeBase0Service;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.UiControllerUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Component("bean_UsrEdgeGenBasic.Service")
public class UsrEdgeGenBasic0Service extends UsrEdgeBase0Service {

    public UsrEdgeGenBasic0Service() { this.typeOfEdge = UsrEdgeGenBasic.class; }



}