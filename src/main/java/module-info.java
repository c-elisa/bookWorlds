module it.ispw.bookworlds.bookworlds {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.opencsv;
    requires java.desktop;
    requires java.logging;

    opens it.ispw.bookworlds to javafx.fxml;
    exports it.ispw.bookworlds;
}