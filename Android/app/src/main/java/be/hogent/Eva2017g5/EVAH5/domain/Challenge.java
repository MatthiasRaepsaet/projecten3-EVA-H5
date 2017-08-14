package be.hogent.Eva2017g5.EVAH5.domain;

import java.util.Date;

/**
 * Created by chaitanya on 8/13/17.
 */

public abstract class Challenge {
    //fields
    protected boolean conpleted;
    protected Date challengeDate;

    public abstract boolean isCompleted();

}
