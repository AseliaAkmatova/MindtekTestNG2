package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static{
        FileInputStream input=null;
        properties = new Properties();
        String path = "/Users/aselek/IdeaProjects/MindtekTestNG2/src/test/resources/configurations/Configuration.properties";
        try {
            input=new FileInputStream(path);
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Properties file files is not being read");;
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                System.out.println("Input object was not instantiated,and there was an exception closing it");
            }
        }

    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
