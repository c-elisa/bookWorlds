module it.ispw.bookworlds.bookworlds {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.opencsv;
    requires java.desktop;
    requires java.logging;
    requires java.sql;

    opens it.ispw.bookworlds.controller.graphic.gui to javafx.fxml;
    opens it.ispw.bookworlds.controller.graphic.gui.curator to javafx.fxml;
    opens it.ispw.bookworlds.controller.graphic.gui.reader to javafx.fxml;
    exports it.ispw.bookworlds;
}