package it.ispw.bookworlds.utils;

import it.ispw.bookworlds.exceptions.NotANumberException;

public class Validator {
    private static final String INTEGER_REGEX = "^\\d+$";

    private Validator(){
        //questo costruttore private evita che la classe possa essere istanziata, dal momento che le classi Utility sono classi che hanno solo membri statici
    }

    public static void validateInt(String input) throws NotANumberException {
        if(!input.matches(INTEGER_REGEX)) throw new NotANumberException();
    }
}
