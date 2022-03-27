package ca.ampautomation.ampata.screen.gennodetype;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.GenNodeType;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ampata_GenNodeType.edit")
@UiDescriptor("gen-node-type-edit.xml")
@EditedEntityContainer("genNodeTypeDc")
public class GenNodeTypeEdit extends StandardEditor<GenNodeType> {

}