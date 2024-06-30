package it.ispw.bookworlds.controller.graphic.cli.reader;

import it.ispw.bookworlds.view.cli.PageCLI;
import it.ispw.bookworlds.view.cli.reader.SubscribeToBookClubCLI;

public class ReaderHomepageGraphicController {
    public void subscribeToBookClub(){changePage(new SubscribeToBookClubCLI());}
    public void changePage(PageCLI page){page.display();}
}
