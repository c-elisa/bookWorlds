package it.ispw.bookworlds.utils;

import it.ispw.bookworlds.exceptions.NotANumberException;

public class Validator {
    private static final String INTEGER_REGEX = "^\\d+$";

    public static void validateInt(String input) throws NotANumberException {
        if(!input.matches(INTEGER_REGEX)) throw new NotANumberException();
    }
}
