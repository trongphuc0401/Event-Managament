package vn.edu.likelion.service;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * GuestService -
 *
 * @param
 * @return
 * @throws
 */
public class GuestService implements GuestImpl {
    static ArrayList<Guest> guests = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    @Override
    public Guest getGuest(int id, ArrayList<Guest> guests) {
        for(Guest g : guests) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    @Override
    public void addGuest(Guest guest,Event event) throws Exception {
        inputId(guest);
        System.out.println(guest.getId());

        inputName(guest);
        System.out.println(guest.getName());

        inputAge(guest);
        System.out.println(guest.getAge());

        inputNameEvent(guest,event);
        System.out.println(guest.getName());
        inputRegisterDate(guest,event);
        guests.add(guest);

    }

    @Override
    public void editGuest(int id, ArrayList<Guest> guests, Guest guest) {

    }

    @Override
    public void deleteGuest(int id, Guest guest) {

    }

    @Override
    public void showAllGuests() {
        int i = 0;
        System.out.println("\nGuests :");
        for(Guest g : guests) {
            System.out.print(++i + " | ");
            System.out.print("ID : " + g.getId() + " | ");
            System.out.print("Name: " + g.getName() + " | ");
            System.out.print("Open Date: " + g.getAge() + " | ");
            System.out.print("Number Guest: " + g.getNameEvent().getName() + " | ");
            System.out.print("Register Date: " + g.getRegisterDate() + " | ");
            System.out.println();
        }
    }

    private static int inputId(Guest guest) throws InputMismatchException {
        while (true) {
            try {
                System.out.print("Enter the guest id: ");
                int id = scanner.nextInt();
                if (guest.getId() == id) {
                    System.out.println("Duplicate ID. Please try again.");
                }else {
                    guest.setId(id);
                    break;
                }
            }catch (InputMismatchException e){
                System.out.println("Not Integer. Please try again.");
                scanner.nextLine();
            }
        }
        return guest.getId();
    }

    private static String inputName(Guest guest) throws Exception {

        while (true) {
            try {
                System.out.print("Enter the guest name: ");
                String name = scanner.next();
                if (name.isEmpty()) {
                    System.out.println("This feature is empty. Please try again.");
                }else {
                    guest.setName(name);
                    break;
                }
            }catch (Exception e){
                System.out.println("Error. Please try again.");
                scanner.nextLine();
            }
        }
        return guest.getName();
    }

    private static int inputAge(Guest guest) throws Exception {

        while (true) {
            try {
                System.out.print("Enter the age guest: ");
                int age = scanner.nextInt();
                if (age ==0) {
                    System.out.println("This field is empty. Please try again.");
                }else {
                    guest.setAge(age);
                    break;
                }
            }catch (Exception e){
                System.out.println("Not String. Please try again.");
                scanner.nextLine();
            }
        }
        return guest.getAge();
    }

    /**
     * ý tưởng của bài này là liệt kê các list các event r để cho người dùng lựa chọn
     * như kiểu 1: Event A
     *          2: Event B
     *          3: Event C
     *          4: Event D
     *     người dùng sẽ được chọn
     *     sẽ có switch-case để lựa chọn trong hàm bên dưới
     *     nếu người dùng chonk các số thì sẽ lấy event.setname(event.getName())
     * @param guest
     * @param event
     * @return
     * @throws Exception
     *
     */
    private static String inputNameEvent(Guest guest,Event event) throws Exception {
        while (true) {
            try {
                System.out.print("Enter the name event: ");
                String name = scanner.next();
                if (guest.getNameEvent().getName().equals(name)) {
                    System.out.println("The Guest already register this event. Try again");
                }else {
                    guest.setNameEvent(event);
                    break;
                }
            }catch (Exception e){
                System.out.println("Not String. Please try again.");
                scanner.nextLine();
            }
        }
        return guest.getName();
    }

    private static LocalDateTime inputRegisterDate(Guest guest,Event event) throws Exception {
        while (true) {
            try {
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime localDateTime1 = LocalDateTime.parse(localDateTime.format(dateTimeFormatter), dateTimeFormatter);
                guest.setRegisterDate(localDateTime1);

            }catch (Exception e){
                System.out.println("Not String. Please try again.");
                scanner.nextLine();
            }
        }
    }

    public static void showNameEvent(ArrayList<Event> events) throws Exception {
        for(Event event : events) {
            System.out.println(event.getName());
        }
    }
}
