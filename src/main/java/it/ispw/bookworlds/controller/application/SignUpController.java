package it.ispw.bookworlds.controller.application;

import it.ispw.bookworlds.bean.AccountSignUpBean;
import it.ispw.bookworlds.dao.AccountDAO;
import it.ispw.bookworlds.exceptions.UsernameAlreadyTakenException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;

public class SignUpController {
    /**
     * Permette di registrare un nuovo utente
     * @param bean contiene le informazioni inserite dall'utente per registrarsi
     * @throws UsernameAlreadyTakenException se lo username inserito dall'utente è già associato ad un account
     */
    public void signUp(AccountSignUpBean bean) throws UsernameAlreadyTakenException {
        AccountDAO accountDAO = GeneralDAOFactory.getInstance().createAccountDAO();

        if(!(accountDAO.getAccountByUsername(bean.getUsername()) == null)) throw new UsernameAlreadyTakenException();

        accountDAO.signUp(accountDAO.getMaxCode() + 1, bean.getUsername(), bean.getPassword(), bean.getEmail(), bean.getRole());
    }
}
