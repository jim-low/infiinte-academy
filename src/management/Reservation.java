package management;

import system.Session;

public interface Reservation {
    public void addReservation(Session session);
    public boolean listReservations();
    public void editReservation(int index, Session session);
    public void editReservation(Session oldSession, Session newSession);
    public void removeReservation(Session session);
}

