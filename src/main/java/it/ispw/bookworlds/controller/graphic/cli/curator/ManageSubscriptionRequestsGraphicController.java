package it.ispw.bookworlds.controller.graphic.cli.curator;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.application.curator.ManageSubscriptionRequestsController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;

import java.util.ArrayList;

public class ManageSubscriptionRequestsGraphicController {
    public ArrayList<SubscriptionRequestBean> getSubscriptionRequests() throws SessionNotFoundException {
        ManageSubscriptionRequestsController controller = new ManageSubscriptionRequestsController();

        return controller.retrieveSubscriptionRequests();
    }

    public void acceptRequest(SubscriptionRequestBean request){
        ManageSubscriptionRequestsController controller = new ManageSubscriptionRequestsController();

        controller.acceptRequest(request);
    }

    public void rejectRequest(SubscriptionRequestBean request){
        ManageSubscriptionRequestsController controller = new ManageSubscriptionRequestsController();

        controller.rejectRequest(request);
    }
}
