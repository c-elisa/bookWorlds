package it.ispw.bookworlds.bean.observer;

import it.ispw.bookworlds.model.RequestState;

public interface Observer {
    public void update(RequestState state);
}
