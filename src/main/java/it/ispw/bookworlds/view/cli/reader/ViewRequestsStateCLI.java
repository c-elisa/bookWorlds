package it.ispw.bookworlds.view.cli.reader;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.graphic.cli.reader.ViewRequestsStateGraphicController;
import it.ispw.bookworlds.exceptions.InvalidSelectionException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

import java.util.List;

public class ViewRequestsStateCLI extends GeneralPageCLI implements PageCLI {
    private ViewRequestsStateGraphicController controller = new ViewRequestsStateGraphicController();

    @Override
    public void display() {

        printTitle();
        Printer.println(" --- STATO RICHIESTE DI ISCRIZIONE ---");

        try {
            printRequestsList(controller.retrieveSubscriptionRequests());
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        Printer.println("[1] Cancella richieste accettate o rifiutate\t [2] Indietro");
        int selection;
        while(true){
            selection = requestInt("Seleziona opzione -> ");
            try {
                switch (selection) {
                    case 1 -> controller.deleteRequests();
                    case 2 -> {return;}
                    default -> throw new InvalidSelectionException();
                }
            }catch(InvalidSelectionException e){
                Printer.printError(e.getLocalizedMessage());
            }catch(SessionNotFoundException e){
                Printer.printError(e.getLocalizedMessage());
                System.exit(-1);
            }
        }

    }

    private void printRequestsList(List<SubscriptionRequestBean> requests){
        for(SubscriptionRequestBean request: requests){
            Printer.println("[" + (requests.indexOf(request) + 1) + "] " + request.getBookClubName() + " -> " + request.getState().toString());
        }
    }
}
