package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {

    private static MyConfig instance;
    private final String DEFAULT_PROPERTIES = "default.properties";
    private final String CUSTOM_PROPERTIES = "custom.properties";
    private Properties appProperties;

    private MyConfig(){

        Properties defaultProperties = new Properties();

        try(FileInputStream fis = new FileInputStream(DEFAULT_PROPERTIES)){

            defaultProperties.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        appProperties = new Properties(defaultProperties);

        try(FileInputStream fis = new FileInputStream(CUSTOM_PROPERTIES)){

            appProperties.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void save(){
        try(FileOutputStream fos = new FileOutputStream(CUSTOM_PROPERTIES)){

            appProperties.store(fos,"UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MyConfig getInstance(){
        if(instance==null)
            instance = new MyConfig();

        return instance;
    }

    public String getUsername(){
        return appProperties.getProperty("USERNAME");
    }
    public void setUsername(String username){
        appProperties.setProperty("USERNAME",username);
        save();
    }
    public String getPassword(){
        return appProperties.getProperty("PASSWORD");
    }
    public String getDBHost(){
        return appProperties.getProperty("DB_HOST");
    }
    public String getDBPort(){
        return appProperties.getProperty("DB_PORT");
    }
    public String getDBSchema(){
        return appProperties.getProperty("DB_SCHEMA");
    }

    public String getOracleDBHost() {
        return appProperties.getProperty("ORACLE_DB_HOST");
    }

    public String getOracleDBPort() {
        return appProperties.getProperty("ORACLE_DB_PORT");
    }

    public String getOracleUsername() {
        return appProperties.getProperty("ORACLE_USERNAME");
    }

    public String getOraclePassword() {
        return appProperties.getProperty("ORACLE_PASSWORD");
    }
}
