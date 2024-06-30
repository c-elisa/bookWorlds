package it.ispw.bookworlds.dao;

import com.opencsv.CSVWriter;
import it.ispw.bookworlds.bean.BookClubBean;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.Printer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookClubDAOFileSystem implements BookClubDAO {
    private static BookClubDAOFileSystem instance = null;
    private static final String BOOKCLUB_FILE_PATH = "csv/bookclub.csv";
    private static final String SUBSCRIBERS_FILE_PATH = "csv/subscribers.csv";
    private static final String READINGLIST_FILE_PATH = "csv/readingList.csv";
    private File bookclubFd;
    private File subscribersFd;
    private File readingListFd;

    private BookClubDAOFileSystem(){
        try{
            bookclubFd = new File(BOOKCLUB_FILE_PATH);
            if(!bookclubFd.exists() && !bookclubFd.createNewFile()){throw new IOException();}
            subscribersFd = new File(SUBSCRIBERS_FILE_PATH);
            if(!subscribersFd.exists() && !subscribersFd.createNewFile()){throw new IOException();}
            readingListFd = new File(READINGLIST_FILE_PATH);
            if(!readingListFd.exists() && !readingListFd.createNewFile()){throw new IOException();}
        } catch (IOException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    public static BookClubDAOFileSystem getInstance(){
        if(instance == null) instance = new BookClubDAOFileSystem();
        return instance;
    }

    @Override
    public void createBookClub(BookClubEntity bookClub) {
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(bookclubFd, true)))){
            ArrayList<String> data = new ArrayList<String>();
            data.add(bookClub.getName());
            data.add(bookClub.getOwner());
            data.add(String.valueOf(bookClub.getNumberOfSubscribers()));
            for(Genre g: bookClub.getGenres()) data.add(String.valueOf(g));
            csvWriter.writeNext(data.toArray(new String[0]));
        }catch(IOException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
