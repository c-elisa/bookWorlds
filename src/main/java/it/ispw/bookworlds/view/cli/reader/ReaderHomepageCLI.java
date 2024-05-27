package it.ispw.bookworlds.view.cli.reader;

import it.ispw.bookworlds.view.cli.GeneralPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;

public class ReaderHomepageCLI extends GeneralPageCLI implements PageCLI {
    @Override
    public void display(){
        clearScreen();
        printTitle();
    }
}
