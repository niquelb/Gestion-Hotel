package com.example.gestionhotel;

import java.util.regex.Pattern;

public abstract class EmailPasswordChecker {

    /**
     * Method for validating password:
     *  Min 8 characters
     *  At least 1 uppercase and 1 lowercase
     *  At least 1 number
     * @param target String to compare to
     * @return True if string matches, false if not
     */
    public final static boolean validatePassword(String target) {
        return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$").matcher(target).matches();
    }

    /**
     * Method for validating usernames (emails):
     *  [a-zA-Z0-9\-\.]@[a-zA-Z\-].[a-zA-Z\-]
     *  ^ Simplified version
     * @param target String to compare to
     * @return True if string matches, false if not
     */
    public final static boolean validateUName(String target) {
        return Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(target).matches();
    }
}
