package vn.edu.likelion.app;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;
import vn.edu.likelion.service.EventService;
import vn.edu.likelion.service.GuestService;

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
        ArrayList<Guest> guests = new ArrayList<>();
        Event event = new Event();
        Guest guest = new Guest();
        Boolean close = false;
        String choose = "";
        menuConsole();
        while (true) {
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    eventService.addEvent(event);
                    break;
                case "2":
                    System.out.print("Enter your Id you would like to find: ");
                    int idFind = scanner.nextInt();
                    eventService.showDetailEvent(idFind, event);
                    break;
                case "3":
                    System.out.print("Enter your Id you would like to edit: ");
                    int idEdit = scanner.nextInt();
                    eventService.editEvent(idEdit, events,event);
                    break;
                case "4":
                    System.out.print("Enter your Id you would like to delete: ");
                    int idDelete = scanner.nextInt();
                    eventService.deleteEvent(idDelete, event);
                    break;
                case "5":
                    eventService.showEvent();
                    break;
                case "6":
                    guestService.addGuest(guest,event);
                    break;
                case "7":
                    eventService.showEvent();
                    break;
                case "8":
                    eventService.showEvent();
                    break;
                case "9":
                    eventService.showEvent();
                    break;
                case "10":
                    guestService.showAllGuests();
                    break;

                case "11":
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
        System.out.print("| Option 8 - Exit                      |\n");
        System.out.print("|--------------------------------------|\n");
        System.out.print("Choose something: ");
    }
}
