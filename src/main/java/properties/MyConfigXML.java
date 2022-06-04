package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfigXML {

    private static MyConfigXML instance;
    private final String DEFAULT_PROPERTIES = "defaultXML.properties";
    private final String CUSTOM_PROPERTIES = "customXML.properties";
    private Properties appProperties;

    private MyConfigXML(){

        Properties defaultProperties = new Properties();

        try(FileInputStream fis = new FileInputStream(DEFAULT_PROPERTIES)){

            defaultProperties.loadFromXML(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        appProperties = new Properties(defaultProperties);

        try(FileInputStream fis = new FileInputStream(CUSTOM_PROPERTIES)){

            appProperties.loadFromXML(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void save(){
        try(FileOutputStream fos = new FileOutputStream(CUSTOM_PROPERTIES)){

            appProperties.storeToXML(fos,"UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MyConfigXML getInstance(){
        if(instance==null)
            instance = new MyConfigXML();

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

}
