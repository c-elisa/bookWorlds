package it.ispw.bookworlds.bean;

import it.ispw.bookworlds.model.Role;

public class AccountSignUpBean {
    private String username;
    private String password;
    private String email;
    private Role role;

    public AccountSignUpBean(String username, String password, String email, Role role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public Role getRole(){return this.role;}
}
