package tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class BlazeDemoTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("http://blazedemo.com/");
    }

    @Test
    public void verifyFlightDestinations(){
        String myLocation="Paris";
        String myDestination="London";
        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        Select toPort = new Select(driver.findElement(By.name("toPort")));
        WebElement findFlightsBtn = driver.findElement(By.xpath("//input[@value='Find Flights']"));

        fromPort.selectByValue(myLocation);
        toPort.selectByValue(myDestination);
        findFlightsBtn.click();

        String expectedResult="Flights from "+myLocation+" to "+myDestination+":";
        String actualResult=driver.findElement(By.tagName("h3")).getText().trim();

        Assert.assertEquals(expectedResult,actualResult, "Flight destinations are incorrect");




    }

    @AfterMethod
    public void tearDown(){

    }




}
