package it.ispw.bookworlds.controller.graphic.gui.reader;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.application.reader.ViewRequestsStateController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewRequestsStateGUI extends GenericGUI implements Initializable {
    @FXML
    private ListView<String> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewRequestsStateController controller = new ViewRequestsStateController();

        try {
            List<SubscriptionRequestBean> requests = controller.retrieveSubscriptionRequests();

            for(SubscriptionRequestBean request: requests){
                String item = request.getBookClubName() + " -> " + request.getState().toString();

                list.getItems().add(item);
            }
        }catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            goBack();
        }
    }

    public void deleteRequests(){
        ViewRequestsStateController controller = new ViewRequestsStateController();

        try {
            controller.deleteRequests();
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            goBack();
        }
    }

    @FXML
    private void goBack(){changePage(PagesGUI.READER_HOMEPAGE);}
}
