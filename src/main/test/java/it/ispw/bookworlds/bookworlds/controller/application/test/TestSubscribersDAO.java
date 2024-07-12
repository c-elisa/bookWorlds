package it.ispw.bookworlds.bookworlds.controller.application.test;

import it.ispw.bookworlds.dao.SubscribersDAO;
import it.ispw.bookworlds.factory.GeneralDAOFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSubscribersDAO {

    @Test
    public void testRemoveSubscriber(){
        String username = "elisaV";
        String bookClub = "ReadingIsFun";

        SubscribersDAO subscribersDAO = GeneralDAOFactory.getInstance().createSubscribersDAO();

        boolean isSubscriber = subscribersDAO.isSubscriber(username, bookClub);

        subscribersDAO.removeSubscriber(bookClub, username);

        Assertions.assertNotEquals(isSubscriber, subscribersDAO.isSubscriber(username, bookClub));
    }
}
