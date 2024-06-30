package it.ispw.bookworlds.controller.graphic.cli.curator;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.controller.application.curator.CreateBookClubController;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;

public class CreateBookClubGraphicController {
    public void createBookClub(BookClubBean bean) throws SessionNotFoundException {
        CreateBookClubController controller = new CreateBookClubController();

        controller.createBookClub(bean);
    }
}
