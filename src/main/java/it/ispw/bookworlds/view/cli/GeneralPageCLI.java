package it.ispw.bookworlds.view.cli;

import it.ispw.bookworlds.utils.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneralPageCLI {

    protected static void printTitle(){
        Printer.println("*********************************");
        Printer.println("*          BookWorlds           *");
        Printer.println("*********************************\n");
    }

    protected String requestString(String message){
        String input = null;
        while(true) {
            try {
                Printer.print(message);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                input = reader.readLine();
                break;
            } catch (IOException e) {
                Printer.printError("Errore durante la lettura dell'input, riprovare.");
            }
        }

        return input;
    }
}
