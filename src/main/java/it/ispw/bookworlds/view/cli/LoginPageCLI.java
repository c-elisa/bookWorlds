package it.ispw.bookworlds.view.cli;

import it.ispw.bookworlds.utils.Printer;

public class LoginPageCLI extends GeneralPageCLI implements PageCLI{

    @Override
    public void display(){
        printTitle();
        Printer.println("Effettuare l'accesso per continuare...");
        requestCredentials();
    }

    public void requestCredentials(){
        while(true){
            String username = requestString("Username: ");
            String password = requestString("Password: ");
        }
    }
}
