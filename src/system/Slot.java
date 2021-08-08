package system;

import java.util.Calendar;

public class Slot {
    // type your code here, all the best
    Calendar startTime;
    Calendar endTime;

    public Slot(Calendar startTime, Calendar endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
    
    public String toString(){
        return String.format("%-10s %-10s\n", startTime, endTime);
    }
}