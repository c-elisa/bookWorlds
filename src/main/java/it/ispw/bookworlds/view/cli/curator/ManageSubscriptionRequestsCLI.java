package it.ispw.bookworlds.view.cli.curator;

import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

public class ManageSubscriptionRequestsCLI extends GeneralPageCLI implements PageCLI {
    @Override
    public void display() {

        printTitle();
        Printer.println("\n--- GESTISCI RICHIESTE DI ISCRIZIONE ---");


    }
}
