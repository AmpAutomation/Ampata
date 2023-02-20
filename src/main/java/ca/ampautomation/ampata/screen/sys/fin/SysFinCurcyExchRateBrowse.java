package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.HasTmst;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyExchRate;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyExchRateQryMngr;
import ca.ampautomation.ampata.entity.sys.fin.SysFinCurcyExchRateType;
import ca.ampautomation.ampata.screen.sys.SysNodeBaseBrowse;
import io.jmix.core.*;
import io.jmix.ui.component.*;
import io.jmix.ui.model.*;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.sys.SysNode;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@UiController("enty_SysFinCurcyExchRate.browse")
@UiDescriptor("sys-fin-curcy-exch-rate-browse.xml")
@LookupComponent("tableMain")
public class SysFinCurcyExchRateBrowse extends SysNodeBaseBrowse<SysFinCurcyExchRate, SysFinCurcyExchRateType, SysFinCurcyExchRateQryMngr> {

}