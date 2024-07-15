package it.ispw.bookworlds.controller.graphic.gui;

import it.ispw.bookworlds.bean.AccountSignUpBean;
import it.ispw.bookworlds.controller.application.SignUpController;
import it.ispw.bookworlds.exceptions.UsernameAlreadyTakenException;
import it.ispw.bookworlds.model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SignUpGUI extends GenericGUI{
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    ToggleGroup role;

    @FXML
    private Label errorLabel;

    @FXML
    public void signUp(){
        SignUpController controller = new SignUpController();

        //creo il nuovo bean a partire dai dati inseriti dall'utente
        AccountSignUpBean bean = new AccountSignUpBean(
                usernameField.getText(), passwordField.getText(),
                emailField.getText(), Role.getRoleIta(role.getSelectedToggle().toString()));

        try {
            controller.signUp(bean);
            errorLabel.setText("Registrazione avvenuta con successo.");
        } catch (UsernameAlreadyTakenException e) {
            errorLabel.setText(e.getLocalizedMessage());
        }
    }

    @FXML
    public void goBack(){
        changePage(PagesGUI.LOGIN);
    }
}
