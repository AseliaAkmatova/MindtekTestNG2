package utilities;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.HttpSessionId.getSessionId;

public class Driver {
    static WebDriver driver;

    public static WebDriver getDriver(){
        String browserType=ConfigReader.getProperty("browser");
        if(driver==null||((RemoteWebDriver)driver).getSessionId()==null){
            if(browserType.equals("chrome")){
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                driver=new ChromeDriver();
            }else if(browserType=="firefox"){
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
            }else if(browserType=="edge"){
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
            }
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;

    }
}
