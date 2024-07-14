package it.ispw.bookworlds.controller.graphic.gui;

public enum PagesGUI {
    LOGIN("/it/ispw/bookworlds/gui/login.fxml"),
    CURATOR_HOMEPAGE("/it/ispw/bookworlds/gui/curator/homepage.fxml"),
    READER_HOMEPAGE("/it/ispw/bookworlds/gui/reader/homepage.fxml"),
    CREATE_BOOK_CLUB("/it/ispw/bookworlds/gui/curator/createBookClub.fxml"),
    SUBSCRIBE_TO_BOOK_CLUB("/it/ispw/bookworlds/gui/reader/subscribeToBookClub.fxml"),
    MANAGE_SUBSCRIPTION_REQUESTS("/it/ispw/bookworlds/gui/curator/manageSubscriptionRequests.fxml"),
    VIEW_REQUESTS_STATE("/it/ispw/bookworlds/gui/reader/viewRequestsState.fxml"),
    UNSUBSCRIBE("/it/ispw/bookworlds/gui/reader/unsubscribeFromBookClub.fxml"),
    REMOVE_SUBSCRIBER("/it/ispw/bookworlds/gui/curator/removeSubscriber.fxml");

    private final String path;

    PagesGUI(String path){this.path = path;}
    public String getPath(){return path;}

}
