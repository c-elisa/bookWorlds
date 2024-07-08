package it.ispw.bookworlds.controller.graphic.gui.reader;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.bean.SubscriptionRequestBean;
import it.ispw.bookworlds.bean.observer.Observer;
import it.ispw.bookworlds.controller.application.reader.SubscribeToBookClubController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.controller.graphic.gui.PagesGUI;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.model.RequestState;
import it.ispw.bookworlds.utils.Printer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class SubscribeToBookClubGUI extends GenericGUI implements Initializable, Observer {
    @FXML
    private Label errorLabel;

    @FXML
    private ListView<String> list;

    @FXML
    private Button showButton;

    @FXML
    private Button forwardButton;

    @FXML
    private Text selectedArea;

    @FXML
    private Button addButton;

    GenresListBean genresBean = new GenresListBean();

    private SubscriptionRequestBean request;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SubscribeToBookClubController controller = new SubscribeToBookClubController();
        GenresListBean allGenres = controller.getGenresList();

        list.getItems().addAll(allGenres.getGenres());
    }

    @FXML
    public void selectGenre(){
        genresBean.getGenres().add(list.getSelectionModel().getSelectedItem());
    }

    public void showBookClubs(){

        if(genresBean.getGenres().isEmpty()) errorLabel.setText("Bisogna selezionare almeno un genere dalla lista.");
        else if(genresBean.getGenres().size() > 4){
            errorLabel.setText("Hai selezionato più di 4 generi. Selezionare nuovamente.");
            genresBean.getGenres().clear();
        }
        else displayList();
    }

    @FXML
    public void displayList(){
        SubscribeToBookClubController controller = new SubscribeToBookClubController();
        List<BookClubBean> bookClubs = controller.findBookClubs(genresBean);
        list.getItems().clear();
        for(BookClubBean bean: bookClubs){
            list.getItems().add(bean.getName());
        }
        showButton.setDisable(true);
        forwardButton.setDisable(false);
        errorLabel.setText("");
        addButton.setVisible(false);

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String selection = list.getSelectionModel().getSelectedItem();
            for(BookClubBean bookClub: bookClubs){
                if(Objects.equals(bookClub.getName(), selection)){
                    String text = "Nome: " + bookClub.getName() + " - Iscritti: " + bookClub.getNumberOfSubscribers() + "/" + bookClub.getCapacity() + " - Proprietario: " + bookClub.getOwner() + "\n";
                    for(Genre genre: bookClub.getGenres()){
                        text = text.concat(genre.toString() + ", ");
                    }
                    selectedArea.setText(text);
                    break;
                }
            }
        });
    }

    public void makeRequest(){
        String selection = list.getSelectionModel().getSelectedItem();
        SubscribeToBookClubController controller = new SubscribeToBookClubController();
        try {
            request = new SubscriptionRequestBean(selection);
            request.attach(this);
            controller.makeSubscriptionRequest(request);
        } catch (SessionNotFoundException e) {
            Printer.printError(e.getLocalizedMessage());
        }
    }

    @FXML
    public void goBack(){changePage(PagesGUI.READER_HOMEPAGE);}

    @Override
    public void update(RequestState state) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Esito richiesta di iscrizione");
        switch (state) {
            case RequestState.PENDING -> alert.setHeaderText("La richiesta è stata inoltrata con successo.");
            case RequestState.REJECTED -> alert.setHeaderText("La richiesta è stata respinta.");
            case RequestState.DUPLICATE -> alert.setHeaderText("Richiesta duplicata: sei già iscritto a questo club del libro.");
            default -> alert.setHeaderText("Nessuna informazione sulla richiesta.");
        }

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) goBack();
    }
}
