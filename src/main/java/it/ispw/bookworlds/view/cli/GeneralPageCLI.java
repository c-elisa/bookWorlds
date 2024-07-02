package it.ispw.bookworlds.view.cli;

import it.ispw.bookworlds.utils.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GeneralPageCLI {

    protected static void printTitle(){
        Printer.println("*********************************");
        Printer.println("*          BookWorlds           *");
        Printer.println("*********************************\n");
    }

    protected void printErrorMessage(String message){Printer.printError(message);}

    protected String requestString(String message){
        while(true) {
            try {
                Printer.print(message);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                return reader.readLine();
            } catch (IOException e) {
                printErrorMessage("Errore durante la lettura dell'input, riprovare.");
            }
        }
    }

    protected int requestInt(String message){
        while(true){
            try {
                return Integer.parseInt(requestString(message));
            } catch(NumberFormatException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }
    }

    protected static void clearScreen(){
        System.out.print("\\033[H\\033[2J");
    }

    protected void printList(ArrayList<String> list){
        int i = 1;
        for(String string: list){
            Printer.println("[" + i + "] " + string);
            i++;
        }
    }
}
