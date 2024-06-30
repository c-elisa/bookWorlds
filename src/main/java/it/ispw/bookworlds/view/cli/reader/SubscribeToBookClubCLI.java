package it.ispw.bookworlds.view.cli.reader;

import it.ispw.bookworlds.controller.application.reader.SubscribeToBookClubGraphicController;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.NoGenresSelectedException;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

import java.util.ArrayList;

public class SubscribeToBookClubCLI extends GeneralPageCLI implements PageCLI {
    private ArrayList<Genre> genres = new ArrayList<Genre>();
    private SubscribeToBookClubGraphicController controller = new SubscribeToBookClubGraphicController();

    @Override
    public void display() {

        printTitle();
        Printer.println("\n--- ISCRIVITI A UN CLUB DEL LIBRO ---");
        selectGenres();



    }

    private void selectGenres(){
        int selection;

        Printer.println("Generi disponibili:");
        Printer.println("[0] Fine selezione");
        Genre.printAsList();
        Printer.println("Selezionare fino a 4 generi.");
        while(genres.size() < 4){
            try{
                selection = requestInt("Seleziona: ");
                if(selection > Genre.possibleNumbers()) throw new InvalidSelectionException();
                if(selection == 0 && genres.isEmpty()) throw new NoGenresSelectedException();
                if(selection == 0) break;
                genres.add(Genre.values()[selection - 1]);
            }catch(InvalidSelectionException | NoGenresSelectedException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }
    }
}
