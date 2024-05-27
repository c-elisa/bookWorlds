package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.model.AccountEntity;

public class LoginDaoJDBC implements LoginDAO{
    @Override
    public AccountEntity login(String username, String password) throws IncorrectPasswordException, UsernameNotFoundException {
        return null;
    }
}
