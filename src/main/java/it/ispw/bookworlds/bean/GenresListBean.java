package it.ispw.bookworlds.bean;

import it.ispw.bookworlds.utils.Printer;

import java.util.ArrayList;

public class GenresListBean {
    public ArrayList<String> genres;

    public GenresListBean(){genres = new ArrayList<>();}

    public ArrayList<String> getGenres(){return genres;}
}
