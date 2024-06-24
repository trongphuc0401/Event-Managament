package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;

import java.util.ArrayList;

/**
 * GuestImpl -
 *
 * @param
 * @return
 * @throws
 */
public interface GuestImpl {

    Guest getGuest(int id, ArrayList<Guest> guests);
    void addGuest(Guest guest,Event event) throws Exception;
    void editGuest(int id,ArrayList<Guest> guests,Guest guest);
    void deleteGuest(int id ,Guest guest);
    void showAllGuests();
}
