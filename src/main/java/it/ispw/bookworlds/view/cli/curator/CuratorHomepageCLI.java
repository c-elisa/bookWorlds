package it.ispw.bookworlds.view.cli.curator;

import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

public class CuratorHomepageCLI extends GeneralPageCLI implements PageCLI {
    @Override
    public void display(){
        clearScreen();
        printTitle();
        Printer.println("HOMEPAGE - Sezione Curatore");

        Printer.println("Seleziona un'opzione:");
        Printer.println("[1] Crea un club del libro");
        Printer.println("[2] Gestisci i tuoi club del libro");
        Printer.println("[3] Gestisci richieste di iscrizione");
        Printer.println("[4] Logout");
    }

}
