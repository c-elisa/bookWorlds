package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.exceptions.IncorrectPasswordException;
import it.ispw.bookworlds.exceptions.UsernameNotFoundException;
import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.model.Role;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.Objects;

public class LoginDAOFileSystem implements LoginDAO{
    private static LoginDAOFileSystem instance = null;
    private static final String FILE_PATH = "csv/account.csv";
    private File fd;
    private LoginDAOFileSystem(){
        try{
            fd = new File(FILE_PATH);
            if(!fd.exists() && !fd.createNewFile()){ throw new IOException();}
        } catch (IOException | NullPointerException e) {
            Printer.printError(e);
            System.exit(-1);
        }
    }

    public static LoginDAOFileSystem getInstance(){
        if(instance == null){ instance = new LoginDAOFileSystem();}
        return instance;
    }
    @Override
    public AccountEntity login(String username, String password) throws IncorrectPasswordException, UsernameNotFoundException {
        AccountEntity account = null;

        try {
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));

            String[] nextRecord;

            while((nextRecord = csvReader.readNext()) != null){
                if(Objects.equals(nextRecord[AccountAttributesOrder.USERNAME.getIndex()], username)){
                    if(Objects.equals(nextRecord[AccountAttributesOrder.PASSWORD.getIndex()], password)){
                        account = new AccountEntity(
                                Integer.parseInt(nextRecord[AccountAttributesOrder.CODE.getIndex()]),
                                nextRecord[AccountAttributesOrder.USERNAME.getIndex()],
                                nextRecord[AccountAttributesOrder.EMAIL.getIndex()],
                                Role.getRole(nextRecord[AccountAttributesOrder.ROLE.getIndex()]));
                    }
                    else{
                        throw new IncorrectPasswordException();
                    }
                }
            }

            if(account == null) throw new UsernameNotFoundException();

        } catch (CsvValidationException | IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }

        return account;
    }
}
