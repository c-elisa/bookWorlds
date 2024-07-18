package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.bean.CredentialsBean;
import it.ispw.bookworlds.utils.CurrentSession;
import it.ispw.bookworlds.dao.LoginDAO;
import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.utils.SessionManager;

public class LoginController {
    /**
     * Permette all'utente di accedere al sistema inserendo le proprie credenziali
     *
     * @param creds contiene le credenziali con cui si vuole eseguire l'accesso al sistema
     * @throws IncorrectPasswordException se lo username esiste ma la password inserita non è corretta
     * @throws UsernameNotFoundException se lo username inserito non corrisponde a nessun account registrato
     */
    public boolean login(CredentialsBean creds) throws IncorrectPasswordException, UsernameNotFoundException{
        LoginDAO loginDao = GeneralDAOFactory.getInstance().createLoginDao();
        AccountEntity account;

        account = loginDao.login(creds.getUsername(), creds.getPassword());
        SessionManager.addSession(account);
        CurrentSession.setSessionId(account.getCode());
        return true;
    }
}
