package management;

import system.Session;

public interface Reservation {
    public void addReservation(Session session);
    public void listReservation();
    public void editReservation(int index, Session session);
    public void removeReservation(Session session);
}

