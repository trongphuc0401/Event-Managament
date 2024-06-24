package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;
import vn.edu.likelion.util.Validation;
import vn.edu.likelion.util.exception.NotFoundException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

/**
 * EventService - to handle event logic on app
 *
 * @param
 * @return
 * @throws
 */
public class EventService implements  EventImpl{

    static ArrayList<Event> events = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public EventService() {
    }
    @Override
    public Event getEvent(int id, ArrayList<Event> events) {
        // làm lại coi thêm
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
        System.out.println(event.getId());

        inputName(event);
        System.out.println(event.getName());

        inputDate(event);
        System.out.println(event.getOpenDate());

        inputNumberOfEvent(event);
        System.out.println(event.getNumberGuest());
        events.add(event);

    }


    @Override
    public void editEvent(int id, ArrayList<Event> events, Event event) throws Exception {
        String name  = inputName(event);
        String openDate = inputDate(event);
        long numberGuest = inputNumberOfEvent(event);
        boolean flag = false;

        for(Event e : events) {
            if (e.getId() != id) {
                event.setName(name);
                event.setOpenDate(openDate);
                event.setNumberGuest(numberGuest);
                flag = true;
                events.remove(e);
                break;
            }
            if(!flag) {
                throw new NullPointerException("Id not be null");
            }
        }

    }

    @Override
    public void deleteEvent(int id, Event event) throws NotFoundException {
        event = getEvent(id, events);
        if (event != null) {
            events.remove(event);
        }
        System.out.println("Event not found. Please enter again");
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
                System.out.print("Enter your id: ");
                int id = scanner.nextInt();
                if (event.getId() == id) {
                    System.out.println("Duplicate ID. Please try again.");
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
                System.out.print("Enter your name: ");
                String name = scanner.next();
                if (name.isEmpty()) {
                    System.out.println("This feature is empty. Please try again.");
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
            System.out.print("Please enter your open date(dd/MM/yyyy): ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = scanner.next();

            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                event.setOpenDate(date);
                // if (!Validation(date)) {
                //     return inputDate();
                // }
            } catch (ParseException ex) {
                System.out.println("Invalid date format. Date should be in the format dd/MM/yyyy.");
                return inputDate(event);
            }
            return date;
        }

    }


    private static long inputNumberOfEvent(Event event) throws Exception {
        while (true) {
            try {
                System.out.print("Enter number of guest: ");
                long numberGuest = scanner.nextLong();
                if (numberGuest == 0) {
                    System.out.println("This feature is empty. Please try again.");
                }else {
                    event.setNumberGuest(numberGuest);
                    break;
                }
            }catch (Exception e){
                System.out.println("Not String. Please try again.");
                scanner.nextLine();
            }
        }
        return event.getNumberGuest();
    }
}
