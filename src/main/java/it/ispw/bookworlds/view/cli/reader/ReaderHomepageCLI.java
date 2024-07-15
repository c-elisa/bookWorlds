package it.ispw.bookworlds.view.cli.reader;

import it.ispw.bookworlds.controller.graphic.cli.reader.ReaderHomepageGraphicController;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

public class ReaderHomepageCLI extends GeneralPageCLI implements PageCLI {
    private static final String READER_MENU = """
            HOMEPAGE - Sezione lettore
            
            [1] Iscriviti a un club del libro
            [2] Segna libro come letto
            [3] Disiscriviti da un club del libro
            [4] "Consigliami un libro"
            [5] Visualizza stato richieste di iscrizione
            [6] Logout
            """;

    //Controller grafico associato alla View
    private final ReaderHomepageGraphicController controller = new ReaderHomepageGraphicController();

    @Override
    public void display(){

        while(true) {
            printTitle();
            Printer.println(READER_MENU);
            try{
                int selection = requestInt("Seleziona un'opzione: ");
                switch(selection){
                    case 1 -> controller.subscribeToBookClub();
                    case 3 -> controller.unsubscribeFromBookClub();
                    case 5 -> controller.viewRequestsState();
                    case 6 -> controller.logout();
                    default -> throw new InvalidSelectionException();
                }
            }catch(InvalidSelectionException | SessionNotFoundException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }
    }
}
