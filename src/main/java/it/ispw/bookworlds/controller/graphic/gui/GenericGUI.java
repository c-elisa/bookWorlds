package it.ispw.bookworlds.controller.graphic.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.Callable;

public class GenericGUI {
    protected void changePage(PagesGUI page){PageControllerGUI.setPage(page);}

    protected void createAndShowAlert(String title, String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) return;
    }
}
