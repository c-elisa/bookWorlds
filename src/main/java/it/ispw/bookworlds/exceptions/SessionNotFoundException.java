package it.ispw.bookworlds.exceptions;

public class SessionNotFoundException extends Exception{
    private static final String MESSAGE = "Codice sessione non valido.";
    public SessionNotFoundException(){super(MESSAGE);}

}
