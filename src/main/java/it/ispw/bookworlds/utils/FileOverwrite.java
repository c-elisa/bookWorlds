package it.ispw.bookworlds.utils;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileOverwrite {
    private FileOverwrite(){}

    public static void writeToFile(File fd, List<String[]> records) {
        try {
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new java.io.FileWriter(fd, false)));
            csvWriter.writeAll(records);
            csvWriter.close();
        } catch (
                IOException e) {
            Printer.printError(e);
            System.exit(-1);
        }
    }
}
