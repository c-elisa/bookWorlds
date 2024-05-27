package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.model.AccountEntity;

//singleton

public interface LoginDAO {
    public AccountEntity login(String username, String password) throws IncorrectPasswordException, UsernameNotFoundException;
}
