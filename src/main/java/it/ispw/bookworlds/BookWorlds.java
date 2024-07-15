package it.ispw.bookworlds;

import it.ispw.bookworlds.controller.graphic.gui.PageControllerGUI;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.InitialPageCLI;
import it.ispw.bookworlds.view.cli.LoginPageCLI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookWorlds extends Application {
    public static void main(String[] args) {

        Printer.println("Scegliere un tipo di interfaccia");
        Printer.println("[1] Linea di comando");
        Printer.println("[2] Interfaccia grafica");

        int selection;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            try {
                Printer.print("Scelta --> ");
                selection = Integer.parseInt(reader.readLine());
                if(selection != 1 && selection != 2) throw new InvalidSelectionException();
                break;
            } catch (IOException e) {
                Printer.printError("Errore durante la lettura dell'input, riprovare.");
            }catch(NumberFormatException e){
                Printer.printError("Input non valido, riprovare");
            } catch (InvalidSelectionException e) {
                Printer.printError(e);
            }
        }

        if(selection == 1){
            new InitialPageCLI().display();
        }else{
            launch(args);
        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        Printer.println("Avviando l'interfaccia grafica...");
        stage.setTitle("BookWorlds");

        PageControllerGUI.setStage(stage);
        PageControllerGUI.setPage(PagesGUI.LOGIN);
    }
}
