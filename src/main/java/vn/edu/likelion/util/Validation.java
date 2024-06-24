package vn.edu.likelion.util;

import vn.edu.likelion.entities.Event;

import java.util.ArrayList;

/**
 * Validation - Validation
 *
 * @param
 * @return
 * @throws
 */
public class Validation {

    public static boolean duplicateId(ArrayList<Event> event , int id) {
        if (event.get(0).getId() == id) {
            return true;
        }
        return false;
    }

}
