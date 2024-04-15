package it.ispw.bookworlds.view.gui;

public enum PagesGUI {
    LOGIN("/it/ispw/bookworlds/gui/login.fxml");

    private final String path;

    PagesGUI(String path){this.path = path;}
    public String getPath(){return path;}

}
