package it.ispw.bookworlds.bean;

public class CredentialsBean {
    private String username;
    private String password;

    public CredentialsBean(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
