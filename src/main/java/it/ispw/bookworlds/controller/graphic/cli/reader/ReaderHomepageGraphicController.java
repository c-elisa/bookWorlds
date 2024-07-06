package it.ispw.bookworlds.controller.graphic.cli.reader;

import it.ispw.bookworlds.controller.application.LogoutController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.view.cli.LoginPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;
import it.ispw.bookworlds.view.cli.reader.SubscribeToBookClubCLI;

public class ReaderHomepageGraphicController {
    public void subscribeToBookClub(){changePage(new SubscribeToBookClubCLI());}
    public void logout() throws SessionNotFoundException {
        LogoutController controller = new LogoutController();
        controller.logout();
        changePage(new LoginPageCLI());
    }
    public void changePage(PageCLI page){page.display();}
}
