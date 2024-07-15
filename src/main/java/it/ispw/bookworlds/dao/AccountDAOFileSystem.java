package it.ispw.bookworlds.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.bookworlds.model.AccountEntity;
import it.ispw.bookworlds.model.Role;
import it.ispw.bookworlds.utils.Printer;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import static it.ispw.bookworlds.model.Role.roleToString;

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
                            Role.getRole(nextRecord[AccountAttributesOrder.ROLE.getIndex()])
                    );
                }
            }
        }catch(IOException | CsvValidationException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
        return account;
    }

    @Override
    public void signUp(int code, String username, String password, String email, Role role) {
        try(CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(accountFd, true)))){
            ArrayList<String> data = new ArrayList<String>();
            data.add(String.valueOf(code));
            data.add(username);
            data.add(password);
            data.add(email);
            data.add(Role.roleToString(role));
            csvWriter.writeNext(data.toArray(new String[0]));
        }catch(IOException e){
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    @Override
    public int getMaxCode() {
        int code = 0;

        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(accountFd)))) {
            String[] nextRecord;

            while((nextRecord = csvReader.readNext())!=null) code = Integer.parseInt(nextRecord[0]);
        } catch (IOException | CsvValidationException e) {
            Printer.printError(e.getLocalizedMessage());
            System.exit(-1);
        }

        return code;
    }

}
