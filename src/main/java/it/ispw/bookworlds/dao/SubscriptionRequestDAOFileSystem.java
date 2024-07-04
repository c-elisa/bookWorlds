package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.model.*;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class SubscriptionRequestDAOFileSystem implements SubscriptionRequestDAO{
    private static SubscriptionRequestDAOFileSystem instance = null;
    private final String FILE_PATH = "csv/subscriptionRequests.csv";
    private File fd;

    private SubscriptionRequestDAOFileSystem(){
        try{
            fd = new File(FILE_PATH);
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
    public ArrayList<SubscriptionRequestEntity> getRequestsByUsername(String username) {
        ArrayList<SubscriptionRequestEntity> requests = new ArrayList<>();

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()], username)){
                    requests.add(new SubscriptionRequestEntity(
                            nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()],
                            nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()],
                            RequestState.valueOf(nextRecord[SubscriptionRequestAttributesOrder.STATE.getIndex()])
                    ));
                }
            }

        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        return requests;

    }

    @Override
    public ArrayList<SubscriptionRequestEntity> getRequestsByBookClubName(String name) {
        ArrayList<SubscriptionRequestEntity> requests = new ArrayList<>();

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(Objects.equals(nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()], name)){
                    requests.add(new SubscriptionRequestEntity(
                            nextRecord[SubscriptionRequestAttributesOrder.READER_USERNAME.getIndex()],
                            nextRecord[SubscriptionRequestAttributesOrder.BOOKCLUB_NAME.getIndex()],
                            RequestState.valueOf(nextRecord[SubscriptionRequestAttributesOrder.STATE.getIndex()])
                    ));
                }
            }

        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        return requests;
    }

    @Override
    public void addSubscriptionRequest(SubscriptionRequestEntity request) {
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)))){
            ArrayList<String> data = new ArrayList<String>();
            data.add(request.getReaderUsername());
            data.add(request.getBookClubName());
            data.add(request.getState().toString());
            csvWriter.writeNext(data.toArray(new String[0]));
        }catch(IOException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
