package it.ispw.bookworlds.dao;

import it.ispw.bookworlds.utils.Printer;

import java.io.File;
import java.io.IOException;

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
}
