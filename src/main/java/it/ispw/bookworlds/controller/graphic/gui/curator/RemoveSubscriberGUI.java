package it.ispw.bookworlds.controller.graphic.gui.curator;

import it.ispw.bookworlds.controller.application.curator.RemoveSubscriberController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveSubscriberGUI extends GenericGUI implements Initializable {
    @FXML
    private ListView<String> list;

    @FXML
    private Label label2;

    @FXML
    private Text selectedArea;

    @FXML
    private Button showButton;

    @FXML
    private Button removeButton;

    private String selectedBookClub;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RemoveSubscriberController controller = new RemoveSubscriberController();

        try {
            List<String> bookClubs = controller.retrieveOwnerBookClubs();

            for(String bookClub : bookClubs){
                list.getItems().add(bookClub);
            }

            list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> selectedArea.setText(list.getSelectionModel().getSelectedItem()));
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            goBack();
        }
    }

    @FXML
    public void showSubscribers(){
        selectedBookClub = list.getSelectionModel().getSelectedItem();
        showButton.setDisable(true);
        removeButton.setDisable(false);
        label2.setText("Lista iscritti: ");

        updateList();
    }

    @FXML
    public void removeSubscriber(){
        RemoveSubscriberController controller = new RemoveSubscriberController();

        controller.removeSubscriber(selectedBookClub, list.getSelectionModel().getSelectedItem());
        updateList();
    }

    @FXML
    private void updateList() {
        RemoveSubscriberController controller = new RemoveSubscriberController();
        List<String> readers = controller.retrieveReadersByBookClubName(selectedBookClub);

        list.getItems().clear();

        for (String reader : readers) {
            list.getItems().add(reader);
        }

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> selectedArea.setText(list.getSelectionModel().getSelectedItem()));
    }


    @FXML
    private void goBack(){changePage(PagesGUI.CURATOR_HOMEPAGE);}
}
