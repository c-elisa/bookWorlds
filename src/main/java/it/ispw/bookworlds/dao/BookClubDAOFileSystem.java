package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public ArrayList<BookClubEntity> getBookClubsByGenres(ArrayList<Genre> genres) {
        //Lista di tutti i club del libro presenti nel sistema
        ArrayList<BookClubEntity> allBookClubs = new ArrayList<BookClubEntity>();
        // Lista di club del libro da restituire
        ArrayList<BookClubEntity> bookClubs = new ArrayList<BookClubEntity>();

        //recupera tutti i dati relativi ai club del libro esistenti
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(bookclubFd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                ArrayList<Genre> bookClubGenres = new ArrayList<>();

                for(int i = BookClubAttributesOrder.GENRES.getIndex(); i<nextRecord.length; i++){
                    bookClubGenres.add(Genre.valueOf(nextRecord[i]));
                }

                BookClubEntity bookClub = new BookClubEntity(
                        nextRecord[BookClubAttributesOrder.NAME.getIndex()],
                        bookClubGenres,
                        Integer.parseInt(nextRecord[BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getIndex()]),
                        nextRecord[BookClubAttributesOrder.OWNER.getIndex()]
                );

                allBookClubs.add(bookClub);
            }
        } catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        for(BookClubEntity bookClub: allBookClubs){
            for(Genre g: genres){
                if(bookClub.getGenres().contains(g) && !bookClubs.contains(bookClub)) bookClubs.add(bookClub);
            }
        }

        return bookClubs;

        /**try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(bookclubFd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                for(int i = BookClubAttributesOrder.GENRES.getIndex(); i<nextRecord.length; i++){
                    for (Genre genre : genres) {
                        if (Objects.equals(nextRecord[i], genre.toString())) {
                            ArrayList<Genre> bookClubGenres = new ArrayList<Genre>();

                            for(int k = BookClubAttributesOrder.GENRES.getIndex(); k<nextRecord.length; k++){
                                bookClubGenres.add(Genre.valueOf(nextRecord[k]));
                            }

                            BookClubEntity bookClub = new BookClubEntity(
                                    nextRecord[BookClubAttributesOrder.NAME.getIndex()],
                                    bookClubGenres,
                                    Integer.parseInt(nextRecord[BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getIndex()]),
                                    nextRecord[BookClubAttributesOrder.OWNER.getIndex()]
                            );

                            if(!bookClubs.contains(bookClub)) bookClubs.add(bookClub);
                            break;
                        }
                    }
                }

            }
        } catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }**/
    }
}
