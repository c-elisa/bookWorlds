package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.model.BookClubEntity;
import it.ispw.bookworlds.model.Genre;
import it.ispw.bookworlds.utils.FileOverwrite;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookClubDAOFileSystem implements BookClubDAO {
    private static BookClubDAOFileSystem instance = null;
    private static final String BOOKCLUB_FILE_PATH = "csv/bookclub.csv";
    private File fd;

    private BookClubDAOFileSystem(){
        try{
            fd = new File(BOOKCLUB_FILE_PATH);
            if(!fd.exists() && !fd.createNewFile()){throw new IOException();}
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
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)))){
            ArrayList<String> data = new ArrayList<String>();
            data.add(bookClub.getName());
            data.add(bookClub.getOwner());
            data.add(String.valueOf(bookClub.getNumberOfSubscribers()));
            data.add(String.valueOf(bookClub.getCapacity()));
            for(Genre g: bookClub.getGenres()) data.add(String.valueOf(g));
            csvWriter.writeNext(data.toArray(new String[0]));
        }catch(IOException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public List<BookClubEntity> getBookClubsByGenres(List<Genre> genres) {
        //Lista di tutti i club del libro presenti nel sistema
        ArrayList<BookClubEntity> allBookClubs = new ArrayList<BookClubEntity>();
        // Lista di club del libro da restituire
        ArrayList<BookClubEntity> bookClubs = new ArrayList<BookClubEntity>();

        //recupera tutti i dati relativi ai club del libro esistenti
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
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
                        Integer.parseInt(nextRecord[BookClubAttributesOrder.CAPACITY.getIndex()]),
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
    }

    @Override
    public BookClubEntity getBookClubByName(String name){
        BookClubEntity bookClub = null;
        //recupera tutti i dati relativi ai club del libro esistenti
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                if(Objects.equals(nextRecord[BookClubAttributesOrder.NAME.getIndex()], name)){
                    ArrayList<Genre> bookClubGenres = new ArrayList<>();

                    for(int i = BookClubAttributesOrder.GENRES.getIndex(); i<nextRecord.length; i++){
                        bookClubGenres.add(Genre.valueOf(nextRecord[i]));
                    }

                    bookClub = new BookClubEntity(
                            nextRecord[BookClubAttributesOrder.NAME.getIndex()],
                            bookClubGenres,
                            Integer.parseInt(nextRecord[BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getIndex()]),
                            Integer.parseInt(nextRecord[BookClubAttributesOrder.CAPACITY.getIndex()]),
                            nextRecord[BookClubAttributesOrder.OWNER.getIndex()]
                    );
                }
            }
        } catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        return bookClub;
    }

    @Override
    public ArrayList<BookClubEntity> getBookClubsByOwner(String name) {
        //Lista di club del libro da restituire
        ArrayList<BookClubEntity> bookClubs = new ArrayList<BookClubEntity>();

        //recupera tutti i dati relativi ai club del libro per quel curatore
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                if(Objects.equals(nextRecord[BookClubAttributesOrder.OWNER.getIndex()], name)) {
                    ArrayList<Genre> bookClubGenres = new ArrayList<>();

                    for(int i = BookClubAttributesOrder.GENRES.getIndex(); i<nextRecord.length; i++){
                        bookClubGenres.add(Genre.valueOf(nextRecord[i]));
                    }

                    BookClubEntity bookClub = new BookClubEntity(
                            nextRecord[BookClubAttributesOrder.NAME.getIndex()],
                            bookClubGenres,
                            Integer.parseInt(nextRecord[BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getIndex()]),
                            Integer.parseInt(nextRecord[BookClubAttributesOrder.CAPACITY.getIndex()]),
                            nextRecord[BookClubAttributesOrder.OWNER.getIndex()]
                    );

                    bookClubs.add(bookClub);
                }
            }
        } catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        return bookClubs;
    }

    @Override
    public void addSubscriber(String name){
        List<String[]> records = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                if(Objects.equals(nextRecord[BookClubAttributesOrder.NAME.getIndex()], name)){
                    nextRecord[BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getIndex()] =
                            String.valueOf(Integer.parseInt(nextRecord[BookClubAttributesOrder.NUMBER_OF_SUBSCRIBERS.getIndex()]) + 1);
                }
                records.add(nextRecord);
            }

            FileOverwrite.writeToFile(fd, records);
        }catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }
    }
}
