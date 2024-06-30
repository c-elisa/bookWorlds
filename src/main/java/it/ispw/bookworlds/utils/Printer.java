package it.ispw.bookworlds.utils;

import java.util.logging.Logger;

public class Printer {
    private Printer(){}
    public static void print(String string){System.out.print(string);}
    public static void println(String string){System.out.println(string);}
    public static void printError(String error){System.out.println(error);}
    public static void printError(Exception e){System.out.println(e.getMessage());}
    public static void printError(String message, Exception e){System.err.print(message + ":" + e.getMessage());}
}
