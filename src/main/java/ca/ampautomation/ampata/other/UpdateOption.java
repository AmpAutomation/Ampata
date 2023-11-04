package ca.ampautomation.ampata.other;

import java.util.Arrays;
import java.util.Optional;

public enum UpdateOption {
      SKIP(0, "Skip")
    , LOCAL(1, "Local")
    , LOCAL__REF_TO_EXIST(2, "Local, Ref to Exist")
    , LOCAL__REF_TO_EXIST_NEW(3, "Local, Ref to Exist or New")
    , LOCAL__REF_IF_EMPTY_TO_EXIST(4, "Local, Ref (if Empty) to Exist")
    , LOCAL__REF_IF_EMPTY_TO_EXIST_NEW(5, "Local, Ref (if Empty) to Exist or New")
    ;
    UpdateOption(int intVal, String strVal) {
        this.intVal = intVal;
        this.strVal = strVal;
    }

    private int intVal;
    private String strVal;

    public String toString() { return strVal; }
    public int toInt() { return intVal; }

    public static Optional<UpdateOption> valueOf(Integer value) {
        if (value == null){
            return Optional.of(UpdateOption.SKIP);
        }
        else {
            return Arrays.stream(values())
                    .filter(UpdateOption -> UpdateOption.intVal == value)
                    .findFirst();
        }
    }
}

