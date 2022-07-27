package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SmartbearTests {
    WebDriver driver;
    String username = "Tester";
    String password = "test";
    String expectedWelcomeText="Welcome, Tester!";
    WebElement usernameInput;
    WebElement passwordInput;
    WebElement loginBtn;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");

        usernameInput=driver.findElement(By.id("ctl00_MainContent_username"));
        passwordInput= driver.findElement(By.id("ctl00_MainContent_password"));
        loginBtn = driver.findElement(By.id("ctl00_MainContent_login_button"));
    }

    @Test
    public void loginPositive(){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();

    }

    @Test
    public void loginNegative(){
        usernameInput.sendKeys("Asel");
        passwordInput.sendKeys("1234");
        loginBtn.click();
        String actualInvalidMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();



    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
