package ua.fantotsy.DAOs;

import ua.fantotsy.entities.Reservation;

import java.util.List;

public interface IDAOReservation {
    int insertNewReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    List<Reservation> getCertainReservations(Integer guestId);

    int deleteCertainReservation(Integer reservationId);
}