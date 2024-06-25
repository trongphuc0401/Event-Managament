package vn.edu.likelion.util;

import vn.edu.likelion.entities.Event;
import vn.edu.likelion.entities.Guest;
import vn.edu.likelion.util.exception.InvalidAgeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Validation - Validation
 *
 * @param
 * @return
 * @throws
 */
public class Validation {

    private static Scanner scanner = new Scanner(System.in);

    public static boolean checkDuplicate(int id, List<Event> events) {
       for (Event event : events) {
           if (event.getId() == id) {
               return true;
           }
       }
        return false;
    }

    public static int validateIntInput(String message) {
        int validate = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                validate = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid Integer.");
            }
        } while (!validInput);

        return validate;
    }
    public static long validateLongInput(String message) {
        long validate = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                validate = Long.parseLong(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid Integer.");
            }
        } while (!validInput);

        return validate;
    }

    public static boolean validateGuestForEvent(String guestName, List<Event> events) {
        for (Event event : events) {
            for (Guest guest: event.getGuests()) {
                if (guest.getName().equalsIgnoreCase(guestName)) {
                    return  false;
                }
            }
        }
        return true;
    }

    public static void validateGuestAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("You must be at least 18 years old to register!");
        }
    }



}
