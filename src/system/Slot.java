package system;

import java.util.Calendar;

public class Slot {
    private Calendar startTime;
    private Calendar endTime;

    public final static Slot[] AVAILABLE_SLOTS = {
        new Slot(setupTimeSlot(9, 00), setupTimeSlot(10, 30)),
        new Slot(setupTimeSlot(9, 00), setupTimeSlot(11, 00)),
        new Slot(setupTimeSlot(10, 00), setupTimeSlot(12, 00)),
        new Slot(setupTimeSlot(10, 00), setupTimeSlot(11, 00)),
        new Slot(setupTimeSlot(14, 00), setupTimeSlot(15, 00)),
        new Slot(setupTimeSlot(14, 00), setupTimeSlot(16, 00)),
        new Slot(setupTimeSlot(16, 30), setupTimeSlot(18, 30)),
        new Slot(setupTimeSlot(16, 00), setupTimeSlot(17, 30)),
        new Slot(setupTimeSlot(16, 00), setupTimeSlot(17, 00))
    };

    public Slot(Calendar startTime, Calendar endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private static Calendar setupTimeSlot(int hours, int minutes){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minutes);
        return c;
    }

    public static Calendar setupDate(int day, int month, int year){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, year);
        return c;
    }

    public static void listSlots(){
        System.out.println("\tStart Time\tEnd Time");
        System.out.println("\t==========\t========");
        for (int i = 0; i < AVAILABLE_SLOTS.length; i++) {
            Slot slot = AVAILABLE_SLOTS[i];
            System.out.printf("%d.\t%s\n", i+1, slot.toString());
        }
    }

    public static Slot search(int index){
        if(index < 0 || index >= AVAILABLE_SLOTS.length){
            return null;
        }

        return AVAILABLE_SLOTS[index];
    }

    public String toString(){
        return String.format("%s\t\t%s", this.getStartTime(), this.getEndTime());
    }

    public String showTime() {
        return String.format("%s - %s", this.getStartTime(), this.getEndTime());
    }

    public String getStartTime() {
        int hours = startTime.get(Calendar.HOUR_OF_DAY);
        int minutes = startTime.get(Calendar.MINUTE);
        return String.format("%02d:%02d", hours, minutes);
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        int hours = endTime.get(Calendar.HOUR_OF_DAY);
        int minutes = endTime.get(Calendar.MINUTE);
        return String.format("%02d:%02d", hours, minutes);
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
}

