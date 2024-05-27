package it.ispw.bookworlds.bean;

public class SessionBean {
    private static int sessionId;

    public static int getSessionId(){return sessionId;}
    public static void setSessionId(int id){sessionId = id;}
}
