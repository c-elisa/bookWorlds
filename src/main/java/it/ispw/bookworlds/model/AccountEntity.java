package it.ispw.bookworlds.model;

public class AccountEntity {
    private final int code;
    private final String username;
    private final String email;
    private final Role role;

    public AccountEntity(int code, String username, String email, Role role){
        this.code = code;
        this.username = username;
        this.email = email;
        this.role = role;
    }
    public int getCode(){return code;}
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public Role getRole(){return role;}
}
