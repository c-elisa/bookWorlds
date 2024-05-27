package it.ispw.bookworlds.exceptions;

public class InvalidPersistenceTypeException extends Exception{
    public static final String MESSAGE = "Tipo di persistenza selezionato non valido.\nPer default la persistenza è stata impostata su file system";

    public InvalidPersistenceTypeException(){super(MESSAGE);}
}
