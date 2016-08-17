package ua.fantotsy.DAOs;

import ua.fantotsy.entities.Reservation;

import java.util.List;

public interface IReservationDao {
    int insertNewReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    List<Reservation> getCertainReservations(Integer guestId);

    int deleteCertainReservation(Integer reservationId);

    void updateReservation(Integer apartment, String arrival, String departure);
}