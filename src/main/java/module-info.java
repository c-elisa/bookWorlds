module it.ispw.bookworlds.bookworlds {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens it.ispw.bookworlds to javafx.fxml;
    exports it.ispw.bookworlds;
}