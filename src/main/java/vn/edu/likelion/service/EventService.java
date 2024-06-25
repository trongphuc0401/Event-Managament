package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;
import vn.edu.likelion.util.Validation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * EventService - to handle event logic on app
 *
 * @param
 * @return
 * @throws
 */
public class EventService implements  EventImpl{
    private static final int MAX_EVENTS = 5;
    private static final int MAX_GUESTS_PER_EVENT = 3;

    public static ArrayList<Event> events = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public EventService() {
    }
    @Override
    public Event getEvent(int id, ArrayList<Event> events) {
        for (Event e : events) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void addEvent(Event event) throws Exception {


            inputId(event);
            inputName(event);
            inputDate(event);
            inputNumberOfEvent(event);
            System.out.println("Event added successfully");
            events.add(event);


    }


    @Override
    public void editEvent(int id, ArrayList<Event> events, Event event) throws Exception {
        String name  = inputName(event);
        String openDate = inputDate(event);
        long numberGuest = inputNumberOfEvent(event);
        boolean flag = false;
        int index = -1;
        for(Event e : events) {
            if (e.getId() != id) {
                event.setName(name);
                event.setOpenDate(openDate);
                event.setNumberGuest(numberGuest);
                events.set(index,e);
                flag = true;
                System.out.println("Event updated successfully");
                break;
            }
            if(!flag) {
                throw new NullPointerException("Id not be null");
            }
        }

    }

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

    public void showDetailEvent(int id , Event event) {
            if(event.getId() ==id) {
                for (Event e : events) {
                    System.out.print("ID : " + e.getId() + " | ");
                    System.out.print("Name: " + e.getName() + " | ");
                    System.out.print("Open Date: " + e.getOpenDate() + " | ");
                    System.out.print("Number Guest: " + e.getNumberGuest() + " | ");
                    System.out.println();
                    break;
                }
            }


    }

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

    private static long inputNumberOfEvent(Event event) {
        while (true) {
                long numberGuest = Validation.validateLongInput("Enter number of Guest for Event: ");
                if (numberGuest == 0) {
                    System.out.println("This field empty. Please try again.");
                }else {
                    event.setNumberGuest(numberGuest);
                    break;
                }
        }
        return event.getNumberGuest();
    }
}
