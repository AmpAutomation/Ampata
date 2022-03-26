package ca.ampautomation.ampata.screen.finrate;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinRate;

@UiController("ampata_FinRate.edit")
@UiDescriptor("fin-rate-edit.xml")
@EditedEntityContainer("finRateDc")
public class FinRateEdit extends StandardEditor<FinRate> {
}