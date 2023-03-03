package ca.ampautomation.ampata.screen.usr.gen;

import ca.ampautomation.ampata.entity.usr.node.gen.UsrGenFile;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrGenFileQryMngr;
import ca.ampautomation.ampata.entity.usr.node.gen.UsrGenFileType;
import ca.ampautomation.ampata.screen.usr.base.UsrNodeBase0BaseMain;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import io.jmix.ui.screen.LookupComponent;

@UiController("enty_UsrGenFile.main")
@UiDescriptor("usr-gen-file-0-main.xml")
@LookupComponent("tableMain")
public class UsrGenFile0Main extends UsrNodeBase0BaseMain<UsrGenFile, UsrGenFileType, UsrGenFileQryMngr, Table<UsrGenFile>> {

}