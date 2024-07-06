package it.ispw.bookworlds.controller.graphic.gui;

import it.ispw.bookworlds.BookWorlds;
import it.ispw.bookworlds.utils.Printer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class PageControllerGUI {
    private static Stage stage = null;
    private PageControllerGUI(){}

    public static void setStage(Stage stage){PageControllerGUI.stage = stage;}
    public static void setPage(PagesGUI page){
        //Si carica la nuova view
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(BookWorlds.class.getResource(page.getPath())));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Printer.printError(e.getMessage());
            System.exit(-1);
        }
    }
}
