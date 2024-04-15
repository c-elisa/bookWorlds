package it.ispw.bookworlds.exceptions;

public class InvalidSelectionException extends Exception{
    public static final String MESSAGE = "Selezione non valida, riprovare";

    public InvalidSelectionException(){super(MESSAGE);}
    public InvalidSelectionException(String message){super(message);}
}
