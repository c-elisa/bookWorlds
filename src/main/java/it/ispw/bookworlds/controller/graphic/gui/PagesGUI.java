package it.ispw.bookworlds.controller.graphic.gui;

public enum PagesGUI {
    LOGIN("/it/ispw/bookworlds/gui/login.fxml"),
    CURATOR_HOMEPAGE("/it/ispw/bookworlds/gui/curator/homepage.fxml"),
    READER_HOMEPAGE("/it/ispw/bookworlds/gui/reader/homepage.fxml");

    private final String path;

    PagesGUI(String path){this.path = path;}
    public String getPath(){return path;}

}
