package vn.edu.likelion.app;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;
import vn.edu.likelion.service.EventService;
import vn.edu.likelion.service.GuestService;
import vn.edu.likelion.util.Validation;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main - Class main to run EventManagement App
 *
 * @param
 * @return
 * @throws
 */
public class Main {

    public static EventService eventService  = new EventService();
    public static GuestService guestService =  new GuestService();

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        ArrayList<Event> events = new ArrayList<>();

        Guest guest = new Guest();

        boolean close = false;
        String choose = "";

        menuConsole();
        while (true) {
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    eventService.addEvent();
                    break;
                case "2":
                    int idFindEvent = Validation.validateIntInput("Enter the Id you would like to find: ");
                    eventService.showDetailEvent(idFindEvent,EventService.events);
                    break;
                case "3":
                    int idEditEvent = Validation.validateIntInput("Enter the Id you would like to edit: ");
                    eventService.editEvent(idEditEvent, EventService.events);
                    break;
                case "4":
                    int idDeleteEvent = Validation.validateIntInput("Enter the Id you would like to delete: ");
                    eventService.deleteEvent(idDeleteEvent);
                    break;
                case "5":
                    eventService.showEvent();
                    break;
                case "6":
                    guestService.addGuest(events);
                    break;
                case "7":
                    int idFindGuest = Validation.validateIntInput("Enter the Id you would like to find: ");
                    guestService.showDetailEvent(idFindGuest,GuestService.guests);
                    break;
                case "8":
                    int idEditGuest = Validation.validateIntInput("Enter the Id you would like to edit: ");
                    guestService.editGuest(idEditGuest,GuestService.guests,EventService.events,guest);
                    break;
                case "9":
                    int idDeleteGuest = Validation.validateIntInput("Enter the Id you would like to delete: ");
                    guestService.deleteGuest(idDeleteGuest);
                    break;
                case "10":
                    guestService.showAllGuests();
                    break;
                case "0":
                    close = true;
                    break;
                default:
                    System.out.println("Invalid! please choose action in below menu:");
                    break;
            }if(close) {
                break;
            }
            menuConsole();
        }

    }


    public static void menuConsole() {
        System.out.print("##------------------ Menu-----------------##\n\n");
        System.out.print("|--------------------------------------|\n");
        System.out.print("|                Event                 |\n");
        System.out.print("| Option 1 - Add Event                 |\n");
        System.out.print("| Option 2 - Find Event by Id          |\n");
        System.out.print("| Option 3 - Edit Event by Id          |\n");
        System.out.print("| Option 4 - Delete Event by Id        |\n");
        System.out.print("| Option 5 - Display list Event        |\n");
        System.out.print("|                                      |\n");
        System.out.print("|                Guest                 |\n");
        System.out.print("| Option 6 - Add Guest                 |\n");
        System.out.print("| Option 7 - Find Guest by Id          |\n");
        System.out.print("| Option 8 - Edit Guest by Id          |\n");
        System.out.print("| Option 9 - Delete Guest by Id        |\n");
        System.out.print("| Option 10 - Display list Guest       |\n");
        System.out.print("|                                      |\n");
        System.out.print("| Option 0 - Exit                      |\n");
        System.out.print("|--------------------------------------|\n");
        System.out.print("Choose something: ");
    }
}
