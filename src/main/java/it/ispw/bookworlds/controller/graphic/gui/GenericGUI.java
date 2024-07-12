package it.ispw.bookworlds.controller.graphic.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class GenericGUI {
    protected void changePage(PagesGUI page){PageControllerGUI.setPage(page);}

    protected void createAndShowAlert(String title, String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);

        alert.showAndWait();
    }
}
