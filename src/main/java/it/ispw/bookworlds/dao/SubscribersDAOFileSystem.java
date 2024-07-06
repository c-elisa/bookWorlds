package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SubscribersDAOFileSystem implements SubscribersDAO{
    private static SubscribersDAOFileSystem instance = null;
    private final String FILE_PATH = "csv/subscribers.csv";
    private File fd;

    private SubscribersDAOFileSystem(){
        try{
            fd = new File(FILE_PATH);
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
                if(Objects.equals(nextRecord[0], bookClub)){
                    for(int i=1; i< nextRecord.length; i++){
                        if(Objects.equals(nextRecord[i], reader)) return true;
                    }
                }
            }
        }catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        return false;
    }

    public void addSubscriber(String bookClub, String reader){
        List<String[]> records = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                if(Objects.equals(nextRecord[0], bookClub)){
                    String[] newRecord = Arrays.copyOf(nextRecord, nextRecord.length + 1);
                    newRecord[nextRecord.length] = reader;
                    records.add(newRecord);
                }
                else records.add(nextRecord);
            }
        }catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
        }

        try{
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, false)));
            csvWriter.writeAll(records);
            csvWriter.close();
        } catch (IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }
    }
}
