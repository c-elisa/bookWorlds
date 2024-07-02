package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.model.Role;
import it.ispw.bookworlds.utils.Printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class AccountDAOFileSystem implements AccountDAO{
    private static AccountDAOFileSystem instance = null;
    private static final String ACCOUNT_FILE_PATH = "csv/account.csv";

    private File accountFd;

    private AccountDAOFileSystem(){
        try{
            accountFd = new File(ACCOUNT_FILE_PATH);
            if(!accountFd.exists() && !accountFd.createNewFile()){throw new IOException();}
        } catch (IOException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    public static AccountDAOFileSystem getInstance(){
        if(instance == null) instance = new AccountDAOFileSystem();
        return instance;
    }

    @Override
    public AccountEntity getAccountByUsername(String username) {
        AccountEntity account = null;

        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(accountFd)))){
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null){
                if(Objects.equals(nextRecord[AccountAttributesOrder.USERNAME.getIndex()], username)){
                    account = new AccountEntity(
                            Integer.parseInt(nextRecord[AccountAttributesOrder.CODE.getIndex()]),
                            nextRecord[AccountAttributesOrder.USERNAME.getIndex()],
                            nextRecord[AccountAttributesOrder.EMAIL.getIndex()],
                            Role.valueOf(nextRecord[AccountAttributesOrder.ROLE.getIndex()])
                    );
                }
            }
        }catch(IOException | CsvValidationException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
        return account;
    }

}
