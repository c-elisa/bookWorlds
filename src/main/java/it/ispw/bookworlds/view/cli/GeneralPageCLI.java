package it.ispw.bookworlds.view.cli;

import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.NotANumberException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
                String input = requestString(message);
                Validator.validateInt(input);
                return Integer.parseInt(input);
            } catch(NumberFormatException | NotANumberException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }
    }

    protected int selectFromList(List<String> list, String message){
        int selection;
        while(true){
            try {
                selection = requestInt(message);
                if (selection > list.size()) throw new InvalidSelectionException();
                break;
            }catch(InvalidSelectionException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }

        return selection;
    }

    protected void printList(List<String> list){
        int i = 1;
        for(String string: list){
            Printer.println("[" + i + "] " + string);
            i++;
        }
    }
}
