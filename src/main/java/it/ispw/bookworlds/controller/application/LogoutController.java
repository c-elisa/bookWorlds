package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.SessionManager;

public class LogoutController {
    public void logout() throws SessionNotFoundException {
        SessionManager.removeSession(SessionManager.getAccountBySessionId(SessionBean.getSessionId()));
        SessionBean.setSessionId(0);
    }
}
