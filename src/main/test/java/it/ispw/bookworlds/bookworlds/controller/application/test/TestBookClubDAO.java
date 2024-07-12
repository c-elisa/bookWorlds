package it.ispw.bookworlds.bookworlds.controller.application.test;

import it.ispw.bookworlds.dao.BookClubDAO;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import it.ispw.bookworlds.model.BookClubEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBookClubDAO {

    @Test
    public void testGetBookClubByName(){
        BookClubDAO bookClubDAO = GeneralDAOFactory.getInstance().createBookClubDAO();

        BookClubEntity bookClub = bookClubDAO.getBookClubByName("ReadingIsFun");

        Assertions.assertNotNull(bookClub);
    }
}
