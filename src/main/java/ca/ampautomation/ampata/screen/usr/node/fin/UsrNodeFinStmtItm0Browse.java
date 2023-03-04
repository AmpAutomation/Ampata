package ca.ampautomation.ampata.screen.usr.node.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.node.base.UsrNodeBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("enty_UsrFinStmtItm.browse")
@UiDescriptor("usr-node-fin-stmt-itm-0-browse.xml")
@LookupComponent("tableMain")
public class UsrNodeFinStmtItm0Browse extends StandardLookup<UsrNodeBase> {

    @Install(to = "tableMain.[instTs1.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}