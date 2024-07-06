package it.ispw.bookworlds.controller.graphic.gui.curator;

import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.controller.application.curator.ManageSubscriptionRequestsController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.NoRequestSelectedException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageSubscriptionRequestsGUI extends GenericGUI implements Initializable {

    @FXML
    private ListView<String> list;

    @FXML
    private Label errorLabel;

    List<SubscriptionRequestBean> requests;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ManageSubscriptionRequestsController controller = new ManageSubscriptionRequestsController();
            requests = controller.retrieveSubscriptionRequests();

            for(SubscriptionRequestBean request: requests){
                String item = "[" + request.getBookClubName() + "] Lettore: " + request.getReaderUsername();

                list.getItems().add(item);
            }
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            goBack();
        }
    }

    public void updateList(){
        try {
            ManageSubscriptionRequestsController controller = new ManageSubscriptionRequestsController();
            requests = controller.retrieveSubscriptionRequests();

            list.getItems().clear();
            for(SubscriptionRequestBean request: requests){
                String item = "[" + request.getBookClubName() + "] Lettore: " + request.getReaderUsername();

                list.getItems().add(item);
            }
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            goBack();
        }
    }

    @FXML
    public void acceptRequest(){
        try {
            ManageSubscriptionRequestsController controller = new ManageSubscriptionRequestsController();

            int index = list.getSelectionModel().getSelectedIndex();
            if (index == -1) throw new NoRequestSelectedException();
            controller.acceptRequest(requests.get(index));
            errorLabel.setText("");
            updateList();
        }catch(NoRequestSelectedException e){
            errorLabel.setText(e.getLocalizedMessage());
        }
    }

    @FXML
    private void rejectRequest(){
        try {
            ManageSubscriptionRequestsController controller = new ManageSubscriptionRequestsController();

            int index = list.getSelectionModel().getSelectedIndex();
            if (index == -1) throw new NoRequestSelectedException();
            controller.rejectRequest(requests.get(index));
            errorLabel.setText("");
            updateList();
        }catch(NoRequestSelectedException e){
            errorLabel.setText(e.getLocalizedMessage());
        }
    }

    public void goBack(){changePage(PagesGUI.CURATOR_HOMEPAGE);}
}
