package it.ispw.bookworlds.exceptions;

public class NotANumberException extends Exception{
    public static final String MESSAGE = "Inserire un numero";

    public NotANumberException(){super(MESSAGE);}
}
