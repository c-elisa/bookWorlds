package it.ispw.bookworlds.view.cli.curator;

import it.ispw.bookworlds.controller.graphic.cli.curator.RemoveSubscriberGraphicController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

import java.util.List;

public class RemoveSubscriberCLI extends GeneralPageCLI implements PageCLI {
    RemoveSubscriberGraphicController controller = new RemoveSubscriberGraphicController();

    @Override
    public void display() {

        printTitle();
        Printer.println(" --- RIMUOVI UN ISCRITTO ---\n\nI tuoi club del libro:");

        try {
            List<String> bookClubs = controller.retrieveBookClubs();
            printList(bookClubs);
            Printer.println("[0] Indietro");

            int selection = selectFromList(bookClubs, "Selezionare un club del libro -> ");
            if(selection == 0) return;
            String selectedBookClub = bookClubs.get(selection - 1);

            List<String> subscribers = controller.getSubscribers(selectedBookClub);

            Printer.println("\nLista iscritti:");

            printList(subscribers);

            selection = selectFromList(subscribers, "Selezionare un utente -> ");

            controller.removeSubscriber(subscribers.get(selection - 1), selectedBookClub);

        } catch (SessionNotFoundException e) {
            printErrorMessage(e.getLocalizedMessage());
        }
    }



}
