package it.ispw.bookworlds.view.cli.curator;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.controller.graphic.cli.curator.CreateBookClubGraphicController;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.NoGenresSelectedException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

import java.util.ArrayList;

public class CreateBookClubCLI extends GeneralPageCLI implements PageCLI {
    @Override
    public void display(){
        ArrayList<Genre> genres = new ArrayList<Genre>();
        int selection = 0;

        printTitle();
        Printer.println("\n--- CREA UN NUOVO CLUB DEL LIBRO ---");

        String name = requestString("Nome: ");

        Printer.println("Seleziona i generi...");
        Printer.println("[0] Fine selezione");
        //Genre.printAsList();
        do{
            try {
                selection = requestInt("Selezionare genere: ");
                if (selection > Genre.possibleNumbers()) throw new InvalidSelectionException();
                if(selection == 0 && genres.isEmpty()) {
                    selection = 1;
                    throw new NoGenresSelectedException();
                }
                if(selection != 0) genres.add(Genre.values()[selection - 1]);
            }catch(InvalidSelectionException | NoGenresSelectedException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }while(selection != 0);

        BookClubBean bean = new BookClubBean(name, genres);
        CreateBookClubGraphicController controller = new CreateBookClubGraphicController();

        try {
            controller.createBookClub(bean);
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

    }
}
