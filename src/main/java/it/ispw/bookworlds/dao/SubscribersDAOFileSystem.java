package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.exceptions.BookClubsNotFound;
import it.ispw.bookworlds.exceptions.NoBookClubsFoundException;
import it.ispw.bookworlds.utils.FileOverwrite;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubscribersDAOFileSystem implements SubscribersDAO{
    private static SubscribersDAOFileSystem instance = null;
    private static final String FILEPATH = "csv/subscribers.csv";
    private File fd;

    private SubscribersDAOFileSystem(){
        try{
            fd = new File(FILEPATH);
            if(!fd.exists() && !fd.createNewFile()){throw new IOException();}
        }catch(IOException | NullPointerException e){
            Printer.printError(e);
            System.exit(-1);
        }
    }

    public static SubscribersDAOFileSystem getInstance(){
        if(instance == null) instance = new SubscribersDAOFileSystem();
        return instance;
    }

    public boolean isSubscriber(String reader, String bookClub){
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                if(Objects.equals(nextRecord[0], bookClub) && Objects.equals(nextRecord[1], reader)) return true;
            }
        }catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        return false;
    }

    public void addSubscriber(String bookClub, String reader){

        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)))){
            ArrayList<String> data = new ArrayList<String>();
            data.add(bookClub);
            data.add(reader);
            csvWriter.writeNext(data.toArray(new String[0]));
        }catch(IOException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public void removeSubscriber(String bookClub, String reader) {
        List<String[]> records = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                if(!Objects.equals(nextRecord[0], bookClub) && !Objects.equals(nextRecord[1], reader)) records.add(nextRecord);
            }

            FileOverwrite.writeToFile(fd, records);
        }catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }
    }

    @Override
    public List<String> getReaderBookClubs(String reader) throws BookClubsNotFound {
        List<String> bookClubs = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader((new BufferedReader(new FileReader(fd))))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(Objects.equals(nextRecord[1], reader)) bookClubs.add(nextRecord[0]);
            }
        } catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        if(bookClubs.isEmpty()) throw new BookClubsNotFound();
        return bookClubs;
    }

    @Override
    public List<String> getSubscribers(String bookClub) {
        List<String> readers = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(Objects.equals(nextRecord[0], bookClub)) readers.add(nextRecord[1]);
            }
        } catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        return readers;
    }
}
