package it.ispw.bookworlds.controller.graphic.cli.reader;

import it.ispw.bookworlds.controller.application.reader.UnsubscribeFromBookClubController;
import it.ispw.bookworlds.exceptions.BookClubsNotFound;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;

import java.util.List;

public class UnsubscribeFromBookClubGraphicController {
    public List<String> retrieveBookClubs() throws SessionNotFoundException, BookClubsNotFound {
        UnsubscribeFromBookClubController controller = new UnsubscribeFromBookClubController();

        return controller.retrieveBookClubs();
    }

    public void unsubscribe(String bookclub) throws SessionNotFoundException {
        UnsubscribeFromBookClubController controller = new UnsubscribeFromBookClubController();

        controller.unsubscribe(bookclub);
    }
}
