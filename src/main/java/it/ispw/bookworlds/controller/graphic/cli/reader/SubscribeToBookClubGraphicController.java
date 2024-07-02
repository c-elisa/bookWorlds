package it.ispw.bookworlds.controller.graphic.cli.reader;

import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.bean.GenresListBean;
import it.ispw.bookworlds.controller.application.reader.SubscribeToBookClubController;

import java.util.ArrayList;

public class SubscribeToBookClubGraphicController {

    public GenresListBean getGenresList(){
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        return controller.getGenresList();
    }

    public ArrayList<BookClubBean> findBookClubs(GenresListBean selectedGenres){
        SubscribeToBookClubController controller = new SubscribeToBookClubController();

        return controller.findBookClubs(selectedGenres);
    }
}
