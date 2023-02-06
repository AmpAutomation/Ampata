package ca.ampautomation.ampata.entity.usr.gen;

import ca.ampautomation.ampata.entity.usr.UsrNode;
import io.jmix.core.DataManager;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JmixEntity
@Entity(name = "enty_UsrGenTag")
@DiscriminatorValue(value="enty_UsrGenTag")
public class UsrGenTag extends UsrNode {

    public Boolean updateCalcVals(DataManager dataManager){
        String logPrfx = "updateCalcVals";
        logger.trace(logPrfx + " --> ");

        boolean isChanged = false;

        isChanged = this.updateId2Calc() || isChanged;
        isChanged = this.updateId2Cmp() || isChanged;
        isChanged = this.updateId2Dup(dataManager) || isChanged;
        isChanged = this.updateDesc1() || isChanged;

        logger.trace(logPrfx + " <-- ");
        return isChanged;
    }

}
