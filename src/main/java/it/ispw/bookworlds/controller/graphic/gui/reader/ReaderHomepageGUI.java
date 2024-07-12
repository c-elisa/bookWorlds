package it.ispw.bookworlds.controller.graphic.gui.reader;

import it.ispw.bookworlds.controller.application.LogoutController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReaderHomepageGUI extends GenericGUI {
    @FXML
    private Label errorLabel;

    public void subscribeToBookClub(){changePage(PagesGUI.SUBSCRIBE_TO_BOOK_CLUB);}
    public void viewRequestsState(){changePage(PagesGUI.VIEW_REQUESTS_STATE);}
    public void unsubscribeFromBookClub(){changePage(PagesGUI.UNSUBSCRIBE);}
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
