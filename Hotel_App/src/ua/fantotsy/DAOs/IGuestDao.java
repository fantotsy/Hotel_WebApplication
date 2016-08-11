package ua.fantotsy.DAOs;

import ua.fantotsy.entities.Guest;

import java.util.List;

public interface IGuestDao {
    Boolean containsCertainLogin(String login);

    Boolean containsCertainGuest(String login, String password);

    int insertNewGuest(Guest guest);

    Guest getCertainGuest(String enteredLogin);

    List<Guest> getAllGuests();
}