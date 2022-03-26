package ca.ampautomation.ampata.screen.finwhy;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.FinWhy;

@UiController("ampata_FinWhy.edit")
@UiDescriptor("fin-why-edit.xml")
@EditedEntityContainer("finWhyDc")
public class FinWhyEdit extends StandardEditor<FinWhy> {
}