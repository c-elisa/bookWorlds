package it.ispw.bookworlds.controller.graphic.gui.curator;

import it.ispw.bookworlds.controller.application.LoginController;
import it.ispw.bookworlds.controller.application.LogoutController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CuratorHomepageGUI extends GenericGUI {
    @FXML
    private Label errorLabel;

    public void createBookClub(){changePage(PagesGUI.CREATE_BOOK_CLUB);}
    public void manageSubscriptionRequests(){changePage(PagesGUI.MANAGE_SUBSCRIPTION_REQUESTS);}
    public void removeSubscriber(){changePage(PagesGUI.REMOVE_SUBSCRIBER);}
    public void logout(){
        try {
            LogoutController controller = new LogoutController();
            controller.logout();
            changePage(PagesGUI.LOGIN);
        }catch (SessionNotFoundException e){
            errorLabel.setText(e.getLocalizedMessage());
        }
    }
}
