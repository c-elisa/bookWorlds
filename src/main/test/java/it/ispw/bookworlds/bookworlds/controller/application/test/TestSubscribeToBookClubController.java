package it.ispw.bookworlds.bookworlds.controller.application.test;

import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.controller.application.SubscribeToBookClubController;
import it.ispw.bookworlds.exceptions.NoBookClubsFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSubscribeToBookClubController {
    /**
     * Viene testato il metodo per recuperare la lista di club del libro dal DAO data una lista di generi
     * per cui esiste almeno un club del libro relativo a quei generi
     * Il test ha successo se non viene sollevata alcune eccezione
     */
    @Test
    public void testFindBookClubsSuccessfully(){
        //Crea il bean da utilizzare
        GenresListBean bean = new GenresListBean();
        bean.addGenre("FANTASY");
        bean.addGenre("THRILLER");

        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        Assertions.assertDoesNotThrow(() -> controller.findBookClubs(bean));
    }

    /**
     * Viene testato il metodo per recuperare la lista di club del libro dal DAO data una lista di generi
     * per cui non esiste alcun club del libro relativo a quei generi
     * Il test ha successo se viene sollevata l'eccezione relativa a nessun club del libro trovato
     */

    @Test
    public void testFindBookClubsNoBookClubsFound(){
        //Crea il bean da utilizzare
        GenresListBean bean = new GenresListBean();
        bean.addGenre("YA");
        bean.addGenre("POETRY");

        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        Assertions.assertThrows(NoBookClubsFoundException.class, () -> controller.findBookClubs(bean));
    }
}
