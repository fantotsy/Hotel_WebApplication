package ua.fantotsy.DAOs;

import ua.fantotsy.entities.Reservation;

import java.util.List;
import java.util.Map;

public interface IDAOReservation {
    Map<Integer, Integer> getNumbersOfApartmentsOnDate(String arrival, String departure);

    int insertNewReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    List<Reservation> getCertainReservations(Integer guestId);

    int deleteCertainReservation(Integer reservationId);
}