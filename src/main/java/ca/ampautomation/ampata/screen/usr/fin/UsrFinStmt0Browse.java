package ca.ampautomation.ampata.screen.usr.fin;

import io.jmix.ui.screen.*;
import ca.ampautomation.ampata.entity.usr.base.UsrBaseNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@UiController("enty_UsrFinStmt.browse")
@UiDescriptor("usr-fin-stmt-0-browse.xml")
@LookupComponent("tableMain")
public class UsrFinStmt0Browse extends StandardLookup<UsrBaseNode> {
    @Install(to = "tableMain.[n1s1Inst1Ts1.elTs]", subject = "formatter")
    private String tableIdDtDate1Formatter(LocalDateTime ts) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .toFormatter();
        return ts == null ? null : ts.format(formatter);
    }

}