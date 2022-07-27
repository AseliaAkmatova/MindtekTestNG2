package utilities;

public class TestProperties {
    public static void main(String[] args) {

        System.out.println(ConfigReader.getProperty("URL"));
        System.out.println(ConfigReader.getProperty("browser"));
    }
}
