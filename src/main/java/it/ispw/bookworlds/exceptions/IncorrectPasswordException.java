package it.ispw.bookworlds.exceptions;

public class IncorrectPasswordException extends Exception{
    public static final String MESSAGE = "Password errata.";

    public IncorrectPasswordException(){super(MESSAGE);}
}
