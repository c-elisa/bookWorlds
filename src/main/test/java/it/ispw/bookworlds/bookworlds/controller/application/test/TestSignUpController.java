package it.ispw.bookworlds.bookworlds.controller.application.test;

import it.ispw.bookworlds.bean.AccountSignUpBean;
import it.ispw.bookworlds.controller.application.SignUpController;
import it.ispw.bookworlds.exceptions.UsernameAlreadyTakenException;
import it.ispw.bookworlds.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSignUpController {
    /**
     * Viene testata la registrazione al sistema inserendo credenziali con uno username disponibile
     * Il test ha successo se non viene sollevata alcuna eccezione
     */
    @Test
    public void testSignUpSuccessfully(){
        AccountSignUpBean bean = new AccountSignUpBean("newUser", "password", "newUser@gmail.com", Role.CURATOR);

        SignUpController controller = new SignUpController();

        Assertions.assertDoesNotThrow(() -> controller.signUp(bean));
    }

    /**
     * Viene testata la registrazione al sistema inserendo credenziali con uno username non disponibile
     * Il test ha successo se viene sollevata l'eccezione relativa a username già preso da un altro utente
     */
    @Test
    public void testSignUpFails(){
        AccountSignUpBean bean = new AccountSignUpBean("elisaV", "password", "newUser@gmail.com", Role.CURATOR);

        SignUpController controller = new SignUpController();

        Assertions.assertThrows(UsernameAlreadyTakenException.class, () -> controller.signUp(bean));
    }
}
