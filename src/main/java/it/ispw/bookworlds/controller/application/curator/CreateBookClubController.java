package it.ispw.bookworlds.controller.application.curator;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.controller.application.GenericController;
import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.exceptions.SessionNotFoundException;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.BookClubEntity;

public class CreateBookClubController extends GenericController {
    public void createBookClub(BookClubBean bean) throws SessionNotFoundException {
        BookClubEntity bookClub = new BookClubEntity(bean);
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        bookClubDAO.createBookClub(bookClub);
    }
}
