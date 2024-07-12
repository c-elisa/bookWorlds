package it.ispw.bookworlds.controller.graphic.cli.curator;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.application.SubscribeToBookClubController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;

import java.util.List;

public class ManageSubscriptionRequestsGraphicController {
    public List<SubscriptionRequestBean> getSubscriptionRequests() throws SessionNotFoundException {
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        return controller.retrieveSubscriptionRequests();
    }

    public void acceptRequest(SubscriptionRequestBean request){
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        controller.acceptRequest(request);
    }

    public void rejectRequest(SubscriptionRequestBean request){
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        controller.rejectRequest(request);
    }
}
