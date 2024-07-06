package it.ispw.bookworlds.controller.graphic.gui.curator;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.controller.application.curator.CreateBookClubController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.NotANumberException;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.utils.Validator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateBookClubGUI extends GenericGUI implements Initializable {
    @FXML
    private ListView<String> genresList;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField capacityTextField;

    @FXML
    private Label errorLabel;

    private ArrayList<Genre> genres = new ArrayList<Genre>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreateBookClubController controller = new CreateBookClubController();
        GenresListBean allGenres = controller.getGenresList();

        genresList.getItems().addAll(allGenres.getGenres());
    }

    @FXML
    public void selectGenre(){
        genres.add(Genre.valueOf(genresList.getSelectionModel().getSelectedItem()));
    }

    @FXML
    public void createBookClub(){
        try {
            Validator.validateInt(capacityTextField.getText());
            BookClubBean bean = new BookClubBean(nameTextField.getText(), genres, Integer.parseInt(capacityTextField.getText()));
            CreateBookClubController controller = new CreateBookClubController();
            controller.createBookClub(bean);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Il club del libro è stato creato con successo!");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) goBack();
        } catch (NotANumberException e) {
            errorLabel.setText(e.getLocalizedMessage());
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    public void goBack(){
        changePage(PagesGUI.CURATOR_HOMEPAGE);
    }
}
