package it.ispw.bookworlds.controller.graphic.gui;

import it.ispw.bookworlds.bean.CredentialsBean;
import it.ispw.bookworlds.bean.SessionBean;
import it.ispw.bookworlds.controller.application.LoginController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static it.ispw.bookworlds.model.Role.CURATOR;

public class LoginGUI extends GenericGUI{
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    public void login(){
        //Istanzia il nuovo bean a partire dalle credenziali inserite
        CredentialsBean creds = new CredentialsBean(usernameField.getText(), passwordField.getText());

        LoginController controller = new LoginController();

        try{
            if(controller.login(creds)){
                if(SessionManager.getAccountBySessionId(SessionBean.getSessionId()).getRole()==CURATOR) changePage(PagesGUI.CURATOR_HOMEPAGE);
                else changePage(PagesGUI.READER_HOMEPAGE);
            }
            else{
                errorLabel.setText("Credenziali non valide, riprovare");
                usernameField.setText("username");
                passwordField.setText("password");
            }
        }catch(SessionNotFoundException e){
            errorLabel.setText(e.getLocalizedMessage());
        }

    }
}
