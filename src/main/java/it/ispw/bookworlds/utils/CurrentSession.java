package it.ispw.bookworlds.utils;

public class CurrentSession {
    private static int sessionId;

    private CurrentSession(){
    }

    public static int getSessionId(){return sessionId;}
    public static void setSessionId(int id){sessionId = id;}
}
