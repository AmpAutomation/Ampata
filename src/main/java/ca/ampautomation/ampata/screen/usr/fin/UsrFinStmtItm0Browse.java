package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("enty_UsrFinStmtItm.browse")
@UiDescriptor("usr-fin-stmt-itm-0-browse.xml")
@LookupComponent("tableMain")
public class UsrFinStmtItm0Browse extends StandardLookup<UsrBaseNode> {

    @Install(to = "tableMain.[instTs1.ts1]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}