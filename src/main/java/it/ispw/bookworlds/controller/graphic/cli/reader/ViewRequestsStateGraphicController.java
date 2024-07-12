package it.ispw.bookworlds.controller.graphic.cli.reader;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.application.reader.ViewRequestsStateController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;

import java.util.List;

public class ViewRequestsStateGraphicController {
    public List<SubscriptionRequestBean> retrieveSubscriptionRequests() throws SessionNotFoundException {
        ViewRequestsStateController controller = new ViewRequestsStateController();

        return controller.retrieveSubscriptionRequests();
    }

    public void deleteRequests() throws SessionNotFoundException {
        ViewRequestsStateController controller = new ViewRequestsStateController();

        controller.deleteRequests();
    }
}
