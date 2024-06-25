package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;

import java.util.ArrayList;
import java.util.List;

/**
 * GuestImpl -
 *
 * @param
 * @return
 * @throws
 */
public interface GuestImpl {


    Guest getGuest(int id, ArrayList<Guest> guests);
    void addGuest(List<Event> events) throws Exception;
    void editGuest(int id,ArrayList<Guest> guests,ArrayList<Event> events,Guest guest);
    void deleteGuest(int id);
    void showAllGuests();
}
