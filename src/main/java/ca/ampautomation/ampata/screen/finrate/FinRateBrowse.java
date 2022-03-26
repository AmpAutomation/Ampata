package ca.ampautomation.ampata.screen.finrate;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinRate;

@UiController("ampata_FinRate.browse")
@UiDescriptor("fin-rate-browse.xml")
@LookupComponent("finRatesTable")
public class FinRateBrowse extends StandardLookup<FinRate> {
}