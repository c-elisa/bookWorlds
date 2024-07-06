package it.ispw.bookworlds.view.cli;

import it.ispw.bookworlds.bean.CredentialsBean;
import it.ispw.bookworlds.controller.graphic.cli.LoginGraphicController;
import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.utils.Printer;

public class LoginPageCLI extends GeneralPageCLI implements PageCLI{

    @Override
    public void display(){
        printTitle();
        Printer.println("Effettuare l'accesso per continuare...");
        requestCredentials();
    }

    public void requestCredentials(){
        String username = requestString("Username: ");
        String password = requestString("Password: ");

        CredentialsBean creds = new CredentialsBean(username, password);
        LoginGraphicController controller = new LoginGraphicController();

        try {
            controller.login(creds);
        } catch (UsernameNotFoundException | IncorrectPasswordException e) {
            Printer.printError(e.getLocalizedMessage());
            display();
        }
    }
}
