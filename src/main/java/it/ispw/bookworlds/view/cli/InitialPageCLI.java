package it.ispw.bookworlds.view.cli;

import it.ispw.bookworlds.utils.Printer;

public class InitialPageCLI extends GeneralPageCLI implements PageCLI {
    @Override
    public void display() {

        while(true) {
            printTitle();
            Printer.println("[1] Login\t[2] Registrati");

            int selection = 0;
            while (selection != 1 && selection != 2) {
                selection = requestInt("Selezionare: ");
            }

            if (selection == 1) new LoginPageCLI().display();
            else new SignUpCLI().display();
        }
    }
}
