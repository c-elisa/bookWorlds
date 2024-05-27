package it.ispw.bookworlds.controller.graphic.cli;

import it.ispw.bookworlds.bean.CredentialsBean;
import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.controller.application.LoginController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.utils.SessionManager;
import it.ispw.bookworlds.view.cli.curator.CuratorHomepageCLI;
import it.ispw.bookworlds.view.cli.reader.ReaderHomepageCLI;
import it.ispw.bookworlds.view.cli.LoginPageCLI;

public class LoginGraphicController {
    public void login(CredentialsBean creds){
        LoginController controller = new LoginController();

        try {
            if(controller.login(creds)) {
                switch (SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getRole()) {
                    case CURATOR -> new CuratorHomepageCLI().display();
                    case READER -> new ReaderHomepageCLI().display();
                }
            }
            else{
                new LoginPageCLI().requestCredentials();
            }
        } catch(SessionNotFoundException e){
            Printer.printError(e);
            System.exit(-1);
        }
    }
}
