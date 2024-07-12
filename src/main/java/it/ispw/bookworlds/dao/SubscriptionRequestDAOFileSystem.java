package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.model.*;
import it.ispw.bookworlds.utils.FileOverwrite;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubscriptionRequestDAOFileSystem implements SubscriptionRequestDAO{
    private static SubscriptionRequestDAOFileSystem instance = null;
    private static final String FILEPATH = "csv/subscriptionRequests.csv";
    private File fd;

    private SubscriptionRequestDAOFileSystem(){
        try{
            fd = new File(FILEPATH);
            if(!fd.exists() && !fd.createNewFile()){ throw new IOException();}
        } catch (IOException | NullPointerException e) {
            Printer.printError(e);
            System.exit(-1);
        }
    }

    public static SubscriptionRequestDAOFileSystem getInstance(){
        if(instance == null) instance = new SubscriptionRequestDAOFileSystem();
        return instance;
    }

    @Override
    public List<SubscriptionRequestEntity> getRequestsByUsername(String username) {
        List<SubscriptionRequestEntity> requests = new ArrayList<>();

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            requests = createListFromFile(csvReader, username);
        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        return requests;

    }

    @Override
    public List<SubscriptionRequestEntity> getRequestsByBookClubName(String name) {
        List<SubscriptionRequestEntity> requests = new ArrayList<>();

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            requests = createListFromFile(csvReader, name);
        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        return requests;
    }

    @Override
    public boolean hasAlreadySentRequest(String bookClubName, String username){
        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()], username)
                        && Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()], bookClubName)
                && Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.STATE.getIndex()], RequestState.PENDING.toString())) return true;
            }

        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        return false;
    }

    @Override
    public void addSubscriptionRequest(SubscriptionRequestEntity request) {
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)))){
            ArrayList<String> data = new ArrayList<String>();
            data.add(request.getBookClubName());
            data.add(request.getReaderUsername());
            data.add(request.getState().toString());
            csvWriter.writeNext(data.toArray(new String[0]));
        }catch(IOException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }



    public void updateSubscriptionRequest(SubscriptionRequestEntity request){
        ArrayList<String[]> records = new ArrayList<>();

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()], request.getBookClubName())
                        && Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()], request.getReaderUsername())){
                    nextRecord[SubscriptionRequestAttributesOrder.STATE.getIndex()] = request.getState().toString();
                }
                records.add(nextRecord);
            }

            FileOverwrite.writeToFile(fd, records);
        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }
    }

    public void deleteSubscriptionRequest(SubscriptionRequestEntity request){
        ArrayList<String[]> records = new ArrayList<>();

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(!Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()], request.getBookClubName())
                        && !Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()], request.getReaderUsername())) records.add(nextRecord);
            }

        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        FileOverwrite.writeToFile(fd, records);
    }

    @Override
    public void deleteSubscriptionRequests(String username) {
        List<String[]> records = new ArrayList<>();

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(!Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()], username) ||
                        (Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()], username)
                                && Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.STATE.getIndex()], "PENDING"))) records.add(nextRecord);
            }

        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        FileOverwrite.writeToFile(fd, records);
    }

    private List<SubscriptionRequestEntity> createListFromFile(CSVReader csvReader, String toCompare) throws CsvValidationException, IOException {
        List<SubscriptionRequestEntity> requests = new ArrayList<>();
        String[] nextRecord;

        while((nextRecord = csvReader.readNext()) != null){
            if(Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()], toCompare) && Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.STATE.getIndex()], RequestState.PENDING.toString())){
                requests.add(new SubscriptionRequestEntity(
                        nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()],
                        nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()],
                        RequestState.valueOf(nextRecord[SubscriptionRequestAttributesOrder.STATE.getIndex()])
                ));
            }
        }

        return requests;
    }
}
