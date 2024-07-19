package it.ispw.bookworlds.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ApplicationProperties {
    private static final String PROPERTIES_FILEPATH = "src/main/resources/app.properties";
    private static Properties properties = null;

    private ApplicationProperties(){}

    public static String getProperty(String prop){
        try{
            if(properties == null) {
                properties = new Properties();
                properties.load(new FileInputStream(PROPERTIES_FILEPATH));
            }
        } catch (IOException e) {
            Printer.printError("Errore durante la lettura del file di configurazione", e);
            System.exit(-1);
        }

        return properties.getProperty(prop);
    }
}
