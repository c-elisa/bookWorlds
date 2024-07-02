package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.AccountEntity;

public interface AccountDAO {
    public AccountEntity getAccountByUsername(String username);
}
