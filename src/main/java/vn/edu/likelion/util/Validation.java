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

    /**
     * checkDuplicate methodd use to check element duplicate in list
     * @param id
     * @param events
     * @return
     */
    public static boolean checkDuplicate(int id, ArrayList<Event> events) {
       for (Event event : events) {
           if (event.getId() == id) {
               return true;
           }
       }
        return false;
    }

    /**
     * ValidateIntInput use to check input be entered is integer type
     * @param message
     * @return
     */
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
                System.out.println("Invalid input! Please enter Integer type.");
            }
        } while (!validInput);

        return validate;
    }

    /**
     * ValidateLongInput use to check input be entered is long type
     * @param message
     * @return
     */
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
                System.out.println("Invalid input! Please enter Long type.");
            }
        } while (!validInput);

        return validate;
    }

    /**
     * ValidateGestAge  to check age is at least 18 years old to register
     * @param age
     * @throws InvalidAgeException
     */
    public static void validateGuestAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("You must be at least 18 years old to register");
        }
    }



}
