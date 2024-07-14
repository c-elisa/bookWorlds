package it.ispw.bookworlds.view.cli.reader;

import it.ispw.bookworlds.controller.graphic.cli.reader.UnsubscribeFromBookClubGraphicController;
import it.ispw.bookworlds.exceptions.BookClubsNotFound;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

import java.util.List;

public class UnsubscribeFromBookClubCLI extends GeneralPageCLI implements PageCLI {
    private UnsubscribeFromBookClubGraphicController controller = new UnsubscribeFromBookClubGraphicController();

    @Override
    public void display() {

        printTitle();
        Printer.println(" --- DISISCRIVITI DA UN CLUB DEL LIBRO --- \n\nI tuoi club del libro:");

        try {
            List<String> bookClubs = controller.retrieveBookClubs();
            printList(bookClubs);
            Printer.println("[0] Indietro");

            int selection = selectFromList(bookClubs, "Selezionare un club del libro -> ");

            if (selection != 0) controller.unsubscribe(bookClubs.get(selection - 1));

        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        } catch (BookClubsNotFound e) {
            printErrorMessage(e.getLocalizedMessage());
        }
    }
}
