package it.ispw.bookworlds.view.cli.curator;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.graphic.cli.curator.ManageSubscriptionRequestsGraphicController;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.utils.Validator;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

import java.util.ArrayList;

public class ManageSubscriptionRequestsCLI extends GeneralPageCLI implements PageCLI {
    private ManageSubscriptionRequestsGraphicController controller = new ManageSubscriptionRequestsGraphicController();
    @Override
    public void display() {

        printTitle();
        Printer.println("\n--- GESTISCI RICHIESTE DI ISCRIZIONE ---");

        try {
            while(true){
                //mostra la lista di richieste per i bookclub del curatore e permette di sceglierne una
                ArrayList<SubscriptionRequestBean> requests = controller.getSubscriptionRequests();
                printRequests(requests);
                Printer.println("[0] Indietro");

                int selection;
                while(true){
                    try {
                        selection = requestInt("Selezionare richiesta: ");
                        if (selection == 0) return;
                        if (selection > requests.size()) throw new InvalidSelectionException();
                        break;
                    }catch(InvalidSelectionException e){
                        printErrorMessage(e.getLocalizedMessage());
                    }
                }

                while(true) {
                    try {
                        int action = requestInt("[1] Accetta richiesta\t[2] Rifiuta richiesta\nSelezionare --> ");
                        switch (action) {
                            case 1 -> controller.acceptRequest(requests.get(selection - 1));
                            case 2 -> controller.rejectRequest(requests.get(selection - 1));
                            default -> throw new InvalidSelectionException();
                        }
                        break;
                    }catch(InvalidSelectionException e){
                        printErrorMessage(e.getLocalizedMessage());
                    }
                }
            }
        } catch (SessionNotFoundException e) {
            printErrorMessage(e.getLocalizedMessage());
        }

    }

    public void printRequests(ArrayList<SubscriptionRequestBean> requests){
        int i = 1;

        for(SubscriptionRequestBean request: requests){
            Printer.println("[" + i + "] Nome del club: " + request.getBookClubName() + " Lettore: " + request.getReaderUsername());
            i++;
        }
    }
}
