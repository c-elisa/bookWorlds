package it.ispw.bookworlds.controller.graphic.cli.curator;

import it.ispw.bookworlds.controller.application.LogoutController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.view.cli.LoginPageCLI;
import it.ispw.bookworlds.view.cli.PageCLI;
import it.ispw.bookworlds.view.cli.curator.CreateBookClubCLI;
import it.ispw.bookworlds.view.cli.curator.ManageSubscriptionRequestsCLI;

public class CuratorHomepageGraphicController {
    public void createBookClub(){changePage(new CreateBookClubCLI());}
    public void manageBookClubs(){//è vuoto
         }
    public void manageSubscriptionRequests(){changePage(new ManageSubscriptionRequestsCLI());}
    public void logout() throws SessionNotFoundException {
        LogoutController controller = new LogoutController();
        controller.logout();
        changePage(new LoginPageCLI());
    }
    public void changePage(PageCLI page) {page.display();}
}
