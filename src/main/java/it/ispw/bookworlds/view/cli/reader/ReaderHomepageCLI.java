package it.ispw.bookworlds.view.cli.reader;

import it.ispw.bookworlds.controller.graphic.cli.reader.ReaderHomepageGraphicController;
import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

public class ReaderHomepageCLI extends GeneralPageCLI implements PageCLI {
    private static final String READER_MENU = """
            HOMEPAGE - Sezione lettore
            
            [1] Iscriviti a un club del libro
            [2] Scrivi commento su un libro in lettura
            [3] Disiscriviti da un club del libro
            [4] "Consigliami un libro"
            [5] Consulta una lista di libri
            [6] Gestisci liste dei libri
            [7] Logout
            """;

    //Controller grafico associato alla View
    private final ReaderHomepageGraphicController controller = new ReaderHomepageGraphicController();
    @Override
    public void display(){
        clearScreen();
        printTitle();
    }
}
