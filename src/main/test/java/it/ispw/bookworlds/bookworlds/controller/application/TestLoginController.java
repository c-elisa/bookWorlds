package it.ispw.bookworlds.bookworlds.controller.application;

import it.ispw.bookworlds.bean.CredentialsBean;
import it.ispw.bookworlds.controller.application.LoginController;
import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestLoginController {

    /**
     * Viene testato il login inserendo credenziali corrette
     */

    @Test
    public void testLoginCorrectCredentials(){
        LoginController controller = new LoginController();

        CredentialsBean creds = new CredentialsBean("antoni", "12345");

        Assertions.assertDoesNotThrow(() -> controller.login(creds));
    }

    /**
     * Viene testato il login inserendo credenziali con username sbagliato e password corretta
     * Il test ha successo se viene sollevata l'eccezione relativa a username non trovato
     */

    @Test
    public void testLoginUsernameNotFound(){
        LoginController controller = new LoginController();

        CredentialsBean creds = new CredentialsBean("anton", "12345");

        Assertions.assertThrows(UsernameNotFoundException.class, () -> controller.login(creds));
    }

    /**
     * Viene testato il login inserendo credenziali con username corretto ma password sbagliata
     * Il test ha successo se viene sollevata l'eccezione relativa a password non corretta
     */

    @Test
    public void testLoginIncorrectPassword(){
        LoginController controller = new LoginController();

        CredentialsBean creds = new CredentialsBean("elisaV", "pass");

        Assertions.assertThrows(IncorrectPasswordException.class, () -> controller.login(creds));
    }
}
