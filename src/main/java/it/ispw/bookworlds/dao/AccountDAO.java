package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.model.Role;

public interface AccountDAO {
    public AccountEntity getAccountByUsername(String username);
    public void signUp(int code, String username, String password, String email, Role role);
    public int getMaxCode();
}
