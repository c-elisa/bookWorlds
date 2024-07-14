package it.ispw.bookworlds.controller.graphic.cli.curator;

import it.ispw.bookworlds.controller.application.curator.RemoveSubscriberController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;

import java.util.List;

public class RemoveSubscriberGraphicController {
    public List<String> retrieveBookClubs() throws SessionNotFoundException {
        RemoveSubscriberController controller = new RemoveSubscriberController();

        return controller.retrieveOwnerBookClubs();
    }

    public List<String> getSubscribers(String bookClub){
        RemoveSubscriberController controller = new RemoveSubscriberController();

        return controller.retrieveReadersByBookClubName(bookClub);
    }

    public void removeSubscriber(String reader, String bookClub){
        RemoveSubscriberController controller = new RemoveSubscriberController();

        controller.removeSubscriber(bookClub, reader);
    }
}
