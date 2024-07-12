package it.ispw.bookworlds.controller.graphic.cli.reader;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.bean.observer.Observer;
import it.ispw.bookworlds.controller.application.SubscribeToBookClubController;
import it.ispw.bookworlds.exceptions.NoBookClubsFoundException;
import it.ispw.bookworlds.model.RequestState;
import it.ispw.bookworlds.view.cli.reader.SubscribeToBookClubCLI;

import java.util.List;

public class SubscribeToBookClubGraphicController implements Observer {
    private SubscriptionRequestBean subject;

    public GenresListBean getGenresList(){
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        return controller.getGenresList();
    }

    public List<BookClubBean> findBookClubs(GenresListBean selectedGenres) throws NoBookClubsFoundException {
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        return controller.findBookClubs(selectedGenres);
    }

    public void makeSubscriptionRequest(SubscriptionRequestBean request){
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        subject = request;
        subject.attach(this);
        controller.makeSubscriptionRequest(subject);
    }

    public void update(RequestState state){
        SubscribeToBookClubCLI view = new SubscribeToBookClubCLI();
        switch(state) {
            case RequestState.PENDING -> view.showUpdate("La richiesta è stata inoltrata con successo.");
            case RequestState.REJECTED -> view.showUpdate("La richiesta è stata respinta.");
            case RequestState.DUPLICATE -> view.showUpdate("Richiesta duplicata: hai già fatto richiesta a questo club.");
            default -> view.showUpdate("Nessuna informazione sulla richiesta.");
        }
    }

}
