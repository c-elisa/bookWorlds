package it.ispw.bookworlds.bean;

public class SessionBean {
    private static int sessionId;

    private SessionBean(){
        sessionId = 0;
    }

    public static int getSessionId(){return sessionId;}
    public static void setSessionId(int id){sessionId = id;}
}
