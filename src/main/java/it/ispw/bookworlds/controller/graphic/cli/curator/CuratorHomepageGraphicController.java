package it.ispw.bookworlds.controller.graphic.cli.curator;

import it.ispw.bookworlds.view.cli.PageCLI;
import it.ispw.bookworlds.view.cli.curator.CreateBookClubCLI;

public class CuratorHomepageGraphicController {
    public void createBookClub(){changePage(new CreateBookClubCLI());}
    public void manageBookClubs(){}
    public void manageSubscriptionRequests(){}
    public void logout(){}
    public void changePage(PageCLI page) {page.display();}
}
