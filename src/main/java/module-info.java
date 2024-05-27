module it.ispw.bookworlds.bookworlds {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.opencsv;

    opens it.ispw.bookworlds to javafx.fxml;
    exports it.ispw.bookworlds;
}