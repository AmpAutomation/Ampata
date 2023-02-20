package ca.ampautomation.ampata.screen.sys.fin;

import ca.ampautomation.ampata.entity.sys.fin.*;
import ca.ampautomation.ampata.screen.sys.SysNodeBaseEdit;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.sys.SysNode;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("enty_SysFinCurcyExchRate.edit")
@UiDescriptor("sys-fin-curcy-exch-rate-edit.xml")
@EditedEntityContainer("instCntnrMain")
public class SysFinCurcyExchRateEdit extends SysNodeBaseEdit<SysFinCurcyExchRate, SysFinCurcyExchRateType, SysFinCurcyExchRateQryMngr> {

}