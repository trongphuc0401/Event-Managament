package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;

import java.util.ArrayList;

/**
 * EventImpl - Interface for event Service
 *
 * @param
 * @return
 * @throws
 */
public interface EventImpl {

     Event getEvent(int id, ArrayList<Event> events);
     void addEvent(Event event) throws Exception;
     void editEvent(int id ,ArrayList<Event> events , Event event) throws Exception;
     void deleteEvent(int id) ;
     void showEvent();







}
