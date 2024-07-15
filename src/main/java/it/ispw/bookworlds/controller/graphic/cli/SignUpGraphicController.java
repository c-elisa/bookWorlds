package it.ispw.bookworlds.controller.graphic.cli;

import it.ispw.bookworlds.bean.AccountSignUpBean;
import it.ispw.bookworlds.controller.application.SignUpController;
import it.ispw.bookworlds.exceptions.UsernameAlreadyTakenException;

public class SignUpGraphicController {
    public void signUp(AccountSignUpBean bean) throws UsernameAlreadyTakenException {
        SignUpController controller = new SignUpController();

        controller.signUp(bean);
    }
}
