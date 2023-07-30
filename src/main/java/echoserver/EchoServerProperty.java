package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EchoServerProperty {
    public static final String LOG_DIRECTORY;

    static {
        Properties prop = new Properties();
        InputStream input = null;
        String logDirectory = null;
        System.out.println("ログプロパティ読込★★★");
        try {
            String filename = "application.properties";
            input = EchoServerProperty.class.getClassLoader().getResourceAsStream(filename);
            System.out.println("ログプロパティ読込："+input);
            if(input == null) {
                System.out.println("Sorry, unable to find " + filename);
            } else {
                prop.load(input);
                logDirectory = prop.getProperty("log.directory");
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LOG_DIRECTORY = logDirectory;
    }
}