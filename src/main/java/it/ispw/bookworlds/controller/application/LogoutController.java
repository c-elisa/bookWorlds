package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.utils.CurrentSession;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.SessionManager;

public class LogoutController {
    /**
     * Permette all'utente di uscire dal sistema
     * @throws SessionNotFoundException viene sollevata se non viene trovato alcun account con il codice di sessione specificato
     */
    public void logout() throws SessionNotFoundException {
        SessionManager.removeSession(SessionManager.getAccountBySessionId(CurrentSession.getSessionId()));
        CurrentSession.setSessionId(0);
    }
}
