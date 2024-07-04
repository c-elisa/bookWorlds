package it.ispw.bookworlds.bean;

import java.util.ArrayList;
import java.util.List;

public class GenresListBean {
    private List<String> genres = new ArrayList<>();

    public List<String> getGenres(){return genres;}

    public void addGenre(String genre){genres.add(genre);}

    public boolean contains(String genre){return genres.contains(genre);}
}
