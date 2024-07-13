package it.ispw.bookworlds.controller.graphic.gui.reader;

import it.ispw.bookworlds.controller.application.reader.UnsubscribeFromBookClubController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.BookClubsNotFound;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.Printer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UnsubscribeFromBookClubGUI extends GenericGUI implements Initializable {
    @FXML
    private ListView<String> list;

    @FXML
    private Text selectedArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnsubscribeFromBookClubController controller = new UnsubscribeFromBookClubController();

        try {
            List<String> bookClubs = controller.retrieveBookClubs();

            for(String bookClub : bookClubs){
                list.getItems().add(bookClub);
            }

            list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> selectedArea.setText(list.getSelectionModel().getSelectedItem()));
        }catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            goBack();
        } catch (BookClubsNotFound e) {
            createAndShowAlert("Info", e.getLocalizedMessage());
            goBack();
        }
    }

    public void unsubscribe(){
        UnsubscribeFromBookClubController controller = new UnsubscribeFromBookClubController();

        try {
            controller.unsubscribe(list.getSelectionModel().getSelectedItem());
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @FXML
    private void goBack(){changePage(PagesGUI.READER_HOMEPAGE);}
}
