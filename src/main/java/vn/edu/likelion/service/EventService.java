package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.util.Validation;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * EventService - to handle event logic on app
 *
 * @param
 * @return
 * @throws
 */
public class EventService implements  EventImpl{

    public static ArrayList<Event> events = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public EventService() {
    }

    /**
     * GetEvent method to get element in events
     * @param id
     * @param events
     * @return
     */
    @Override
    public Event getEvent(int id, ArrayList<Event> events) {
        for (Event e : events) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * addEvent method use to add Event
     * @throws Exception
     */
    @Override
    public void addEvent() throws Exception {
        if (events.size() < 5) {
            Event event = new Event();
            inputId(event);
            inputName(event);
            inputDate(event);
            inputNumberOfEvent(event);
            System.out.println("Event added successfully");
            events.add(event);
        }else {
            System.out.println("Event must less than 5 event");
        }

    }


    /**
     * editEvent method use to edit Event
     * @param id
     * @param events
     * @throws Exception
     */
    @Override
    public void editEvent(int id, ArrayList<Event> events) throws Exception {
        boolean flag = false;
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            if (e.getId() == id) {
                String name = inputName(e);
                String openDate = inputDate(e);
                long numberGuest = inputNumberOfEvent(e);

                e.setName(name);
                e.setOpenDate(openDate);
                e.setNumberGuest(numberGuest);

                events.set(i, e);
                flag = true;
                System.out.println("Event updated successfully");
                break;
            }
        }

        if (!flag) {
            System.out.println("Event with id " + id + " not found");
        }
    }

    /**
     * deleteEvent method use for delete a event
     * @param id
     */
    @Override
    public void deleteEvent(int id) {
        Event event = null;
        for (Event e : events) {
            if (e.getId() == id) {
                event = e;
                break;
            }
        }
        if (event != null) {
            events.remove(event);
            System.out.println("Delete event successfully");
        } else {
            System.out.println("Event not found with id " + id + ". Please try again");
        }
    }


    /**
     * showEvent method use to display list event on console
     */
    public void showEvent() {
        int i = 0;
        System.out.println("\nEvents :");
        for (Event event : events) {
            System.out.print(++i + " | ");
            System.out.print("ID : " + event.getId() + " | ");
            System.out.print("Name: " + event.getName() + " | ");
            System.out.print("Open Date: " + event.getOpenDate() + " | ");
            System.out.print("Number Guest: " + event.getNumberGuest() + " | ");
            System.out.println();
        }
    }

    /**
     * showDetailEvent method use for show a element to display on console
     * @param id
     */
    public void showDetailEvent(int id , ArrayList<Event> events) {
            if(events.contains(getEvent(id,events))) {
                for (Event e : events) {
                    System.out.print("ID : " + e.getId() + " | ");
                    System.out.print("Name: " + e.getName() + " | ");
                    System.out.print("Open Date: " + e.getOpenDate() + " | ");
                    System.out.print("Number Guest: " + e.getNumberGuest() + " | ");
                    System.out.println();
                }
            }
            else {
                System.out.println("Id not found");
            }
    }

    /**
     * inputId - to enter Id in console
     * @param event
     * @return
     * @throws InputMismatchException
     */
    private static int inputId(Event event) throws InputMismatchException {
        while (true) {
            try {
                int id = Validation.validateIntInput("Enter Id of event: ");
                if (Validation.checkDuplicate(id,events)) {
                    System.out.println("Event id already exists. Please try again");
                }else {
                    event.setId(id);
                    break;
                }
            }catch (InputMismatchException e){
                System.out.println("Not Integer. Please try again.");
                scanner.nextLine();
            }
        }
        return event.getId();
    }


    /**
     * inputName - to enter Name in console
     * @param event
     * @return
     * @throws Exception
     */
    private static String inputName(Event event) throws Exception {

        while (true) {
            try {
                System.out.print("Enter name of event: ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("This field is empty. Please try again.");
                }else {
                    event.setName(name);
                    break;
                }
            }catch (Exception e){
                System.out.println("Not String. Please try again.");
                scanner.nextLine();
            }
        }
        return event.getName();
    }


    /**
     * inputDate - to enter Open Date in console
     * @param event
     * @return
     * @throws Exception
     */
    private static String inputDate(Event event) throws Exception {
        while (true) {
            System.out.print("Enter open date for Event (dd/MM/yyyy): ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = scanner.nextLine().trim();
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                event.setOpenDate(date);
            } catch (ParseException ex) {
                System.out.println("Invalid date format. Open date should be in the format dd/MM/yyyy.");
                return inputDate(event);
            }
            return date;
        }

    }

    /**
     * inputNumberOfEvent - to enter number of event
     * @param event
     * @return
     */
    private static long inputNumberOfEvent(Event event) {
        while (true) {
                long numberGuest = Validation.validateLongInput("Enter number of Guest for Event: ");
                if (numberGuest == 0) {
                    System.out.println("This field empty. Please try again.");
                }else if(numberGuest > 3) {
                    System.out.println("Number of guest maximum only 3 guest");
                }else {
                    event.setNumberGuest(numberGuest);
                    break;
            }

        }
        return event.getNumberGuest();
    }
}
