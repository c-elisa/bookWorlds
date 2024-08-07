package it.ispw.bookworlds.view.cli.curator;

import it.ispw.bookworlds.controller.graphic.cli.curator.CuratorHomepageGraphicController;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

public class CuratorHomepageCLI extends GeneralPageCLI implements PageCLI {

    private static final String CURATOR_MENU = """
            HOMEPAGE - Sezione Curatore
            
            [1] Crea un club del libro
            [2] Gestisci richieste di iscrizione
            [3] Aggiungi libro ad una lista di lettura
            [4] Elimina libro da una lista di lettura
            [5] Rimuovi un iscritto
            [6] Logout
            """;

    //Controller grafico associato alla View
    private final CuratorHomepageGraphicController controller = new CuratorHomepageGraphicController();

    @Override
    public void display() {

        while(true) {
            printTitle();
            Printer.println(CURATOR_MENU);
            try {
                int selection = requestInt("Seleziona un'opzione:");
                switch (selection) {
                    case 1 -> controller.createBookClub();
                    case 2 -> controller.manageSubscriptionRequests();
                    case 5 -> controller.removeSubscriber();
                    case 6 -> controller.logout();
                    default -> throw new InvalidSelectionException();
                }
            }catch(InvalidSelectionException | SessionNotFoundException e){
                printErrorMessage(e.getLocalizedMessage());
            }
        }

    }

}
