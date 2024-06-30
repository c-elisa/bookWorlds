package it.ispw.bookworlds.utils;

import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.model.AccountEntity;

import java.util.ArrayList;

public class SessionManager {
    private static final ArrayList<AccountEntity> sessions = new ArrayList<AccountEntity>();

    private SessionManager(){}

    public static void addSession(AccountEntity account){sessions.add(account);}
    public static void removeSession(AccountEntity account){sessions.remove(account);}
    public static AccountEntity getAccountBySessionId(int sessionId) throws SessionNotFoundException {
        for(AccountEntity a: sessions){
            if(a.getCode() == sessionId) return a;
        }
        throw new SessionNotFoundException();
    }
}
