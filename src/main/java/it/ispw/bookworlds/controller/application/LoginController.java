package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.bean.CredentialsBean;
import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.dao.LoginDAO;
import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.utils.Printer;
import it.ispw.bookworlds.utils.SessionManager;

public class LoginController {
    public boolean login(CredentialsBean creds) throws IncorrectPasswordException, UsernameNotFoundException{
        LoginDAO loginDao = GeneralDAOFactory.getInstance().createLoginDao();
        AccountEntity account;

        account = loginDao.login(creds.getUsername(), creds.getPassword());
        SessionManager.addSession(account);
        SessionBean.setSessionId(account.getCode());
        return true;
    }
}
