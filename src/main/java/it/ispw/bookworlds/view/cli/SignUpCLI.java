package it.ispw.bookworlds.view.cli;

import it.ispw.bookworlds.bean.AccountSignUpBean;
import it.ispw.bookworlds.controller.graphic.cli.SignUpGraphicController;
import it.ispw.bookworlds.controller.graphic.gui.GenericGUI;
import it.ispw.bookworlds.exceptions.UsernameAlreadyTakenException;
import it.ispw.bookworlds.model.Role;
import it.ispw.bookworlds.utils.Printer;

public class SignUpCLI extends GeneralPageCLI implements PageCLI {
    private final SignUpGraphicController controller = new SignUpGraphicController();

    @Override
    public void display() {
        printTitle();
        Printer.println(" --- REGISTRAZIONE ---\n");

        while (true) {
            try {
                String username = requestString("Username: ");
                String password = requestString("Password: ");
                String email = requestString("Email: ");
                Printer.println("Ruolo: [1] Lettore\t[2] Curatore");

                int selection = 0;
                while (selection != 1 && selection != 2) {
                    selection = requestInt("Selezionare: ");
                }

                Role role;
                if (selection == 1) role = Role.getRole("Reader");
                else role = Role.getRole("Curator");

                AccountSignUpBean bean = new AccountSignUpBean(username, password, email, role);

                controller.signUp(bean);
                Printer.println("Registrazione eseguita con successo");
                return;
            } catch (UsernameAlreadyTakenException e) {
                Printer.println(e.getLocalizedMessage());
            }
        }
    }
}
