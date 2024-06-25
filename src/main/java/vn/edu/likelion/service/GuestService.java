package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;
import vn.edu.likelion.util.Validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * GuestService - handle logic in Guest
 *
 * @param
 * @return
 * @throws
 */
public class GuestService implements GuestImpl {
    public static ArrayList<Guest> guests = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    /**
     * getGuest method to get element in guests (Arraylist)
     *
     * @param id
     * @param guests
     * @return
     */
    @Override
    public Guest getGuest(int id, ArrayList<Guest> guests) {
        for (Guest g : guests) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    private Event findEventByName(ArrayList<Event> events, String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }
        return null;
    }

    /**
     * addGuest method use to add Guest
     *
     * @param events
     * @throws Exception
     */

    @Override
    public void addGuest(List<Event> events) throws Exception {
        Guest guest = new Guest();
        inputId(guest);
        inputName(guest);
        inputAge(guest);
        inputNameEvent(guest);
        inputRegisterDate(guest, events);
        guests.add(guest);
    }

    /**
     * editGuest use to edit Guest
     *
     * @param id
     * @param guests
     * @param guest
     */
    @Override
    public void editGuest(int id, ArrayList<Guest> guests,ArrayList<Event> events, Guest guest) {
        boolean flag = false;
        for (int i = 0; i < guests.size(); i++) {
            Guest g = guests.get(i);
            if (g.getId() == id) {
                if (g.getNameEvent() != null && !g.getNameEvent().getName().isEmpty()) {
                    Event oldEvent = findEventByName(events, g.getNameEvent().getName());
                    if (oldEvent != null) {
                        oldEvent.removeGuest(g);
                        g.setNameEvent(null);
                    }
                }
                String name = inputName(g);
                int age = inputAge(g);
                String nameEvent = inputNameEvent(g);
                g.setName(name);
                g.setAge(age);
                guests.set(i, g);
                flag = true;
                System.out.println("Guest updated successfully");
                break;
            }
        }
        if(!flag) {
            System.out.println("Guest with "+id+" not found");
        }
    }
    /**
     * deleteGuest method to delete guest by ID
     *
     * @param id
     */
    @Override
    public void deleteGuest(int id) {
        Guest guest = null;
        for (Guest g : guests) {
            if (g.getId() == id) {
                guest = g;
                break;
            }
        }
        if (guest != null) {
            guests.remove(guest);
            System.out.println("Delete guest successfully");
        } else {
            System.out.println("Guest not found with id " + id + ". Please try again");
        }
    }

    public void showDetailEvent(int id , ArrayList<Guest> guests) {
        if(guests.contains(getGuest(id,guests))){
            for (Guest g : guests) {
                System.out.print("ID : " + g.getId() + " | ");
                System.out.print("Name: " + g.getName() + " | ");
                System.out.print("Age: " + g.getAge() + " | ");
                System.out.print("Event Name: " + g.getNameEvent().getName() + " | ");
                System.out.print("Register Date: " + g.getRegisterDate() + " | ");
                System.out.println();
            }
        }
        else {
            System.out.println("Id not found");
        }
    }

    /**
     * ShowAllGuests - Show All Guests
     */
    @Override
    public void showAllGuests() {
        int i = 0;
        System.out.println("\nGuests :");
        for (Guest g : guests) {
            System.out.print(++i + " | ");
            System.out.print("ID : " + g.getId() + " | ");
            System.out.print("Name: " + g.getName() + " | ");
            System.out.print("Age: " + g.getAge() + " | ");
            System.out.print("Event Name: " + g.getNameEvent().getName() + " | ");
            System.out.print("Register Date: " + g.getRegisterDate() + " | ");
            System.out.println();
        }
    }

    /**
     * inputId - to enter Id in console
     *
     * @param guest
     * @return
     * @throws InputMismatchException
     */
    private static int inputId(Guest guest) throws InputMismatchException {
        while (true) {
            try {
                System.out.print("Enter the guest id: ");
                int id = scanner.nextInt();
                if (guest.getId() == id) {
                    System.out.println("Duplicate ID. Please try again.");
                } else {
                    guest.setId(id);
                    break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Not Integer. Please try again.");
                scanner.nextLine();
            }
        }
        return guest.getId();
    }

    /**
     * inputName - to enter Name in console
     *
     * @param guest
     * @return
     */
    private static String inputName(Guest guest) {

        while (true) {
            try {
                System.out.print("Enter the guest name: ");
                String name = scanner.next().trim();
                if (name.isEmpty()) {
                    System.out.println("This field is empty. Please try again.");
                } else {
                    guest.setName(name);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error. Please try again.");
                scanner.nextLine();
            }
        }
        return guest.getName();
    }

    /**
     * inputAge - to enter Age in console
     *
     * @param guest
     * @return
     */
    private static int inputAge(Guest guest) {
        while (true) {
            try {
                int age = Validation.validateIntInput("Enter age of guest: ");
                if (age == 0) {
                    System.out.println("This field is empty. Please try again.");
                } else {
                    Validation.validateGuestAge(age);
                    guest.setAge(age);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Age must be more than 17. Please try again.");
            }
        }
        return guest.getAge();
    }

    /**
     * InputNameEvent - method use to enter field name event
     *
     * @param guest
     * @return
     * @throws Exception
     */
    private static String inputNameEvent(Guest guest) {
        while (true) {
            System.out.print("Enter the name event: ");
            String eventName = scanner.next().trim();

            Event selectedEvent = null;
            for (Event event : EventService.events) {
                if (event.getName().equalsIgnoreCase(eventName)) {
                    selectedEvent = event;
                    break;
                }
            }
            boolean isGuestAlreadyRegistered = false;
            for (Event event : EventService.events) {
                if (event.getGuests().contains(guest)) {
                    isGuestAlreadyRegistered = true;
                    break;
                }
            }
            if (isGuestAlreadyRegistered) {
                System.out.println("The Guest is already registered for an event. Try again.");
                continue;
            }
            if (selectedEvent == null) {
                System.out.println("Event does not exist. Please try again.");
                continue;
            }
            if (selectedEvent.getGuests().isEmpty()) {
                guest.setNameEvent(selectedEvent);
                selectedEvent.addGuest(guest);
                return guest.getName();
            } else {
                System.out.println("The event already has guests. Try another event.");
            }
        }
    }

    /**
     * inputRegisterDate - to use set Local Date Time for guest when register
     *
     * @param guest
     * @param events
     * @return
     */
    private static LocalDateTime inputRegisterDate(Guest guest, List<Event> events) {
        LocalDateTime registerDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("Register at: " + registerDate.format(formatter));

        guest.setRegisterDate(registerDate);

        return registerDate;
    }


}
