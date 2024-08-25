package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class TestCases {
    ChromeDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.out.println("Setup: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod(enabled = true)
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    @Test(enabled = false)
    public  void testCase01() throws StreamWriteException, DatabindException, IOException, InterruptedException{

        System.out.println("Start Test case: testCase01");
        driver.get("https://www.scrapethissite.com/pages/");
        HomePage HP = new HomePage(driver);
        HP.clickOnHockeyTeams();

        ArrayList<HashMap<String, Object>> scrapedData = HP.scrapeTableData1(4);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(scrapedData);
        System.out.println("Scraped Data JSON:");
        System.out.println(jsonString);
        File outputFile = new File(System.getProperty("user.dir") + "/src/test/java/JsonScrapedFile/hockey-team-data.json");
        outputFile.getParentFile().mkdirs(); 
        mapper.writeValue(outputFile, scrapedData);

        Assert.assertTrue(outputFile.exists(), "The JSON file was not created.");
        Assert.assertTrue(outputFile.length() > 0, "The JSON file is empty.");
        System.out.println("end Test case: testCase02");
        System.out.println();
    }

    @Test(enabled = true)
    public void testCase02() throws InterruptedException, IOException{
        System.out.println("Start TestCase02");
        driver.get("https://www.scrapethissite.com/pages/");
        HomePage HP = new HomePage(driver);

        HP.clickOnOscarWinningFilms();
        ArrayList<HashMap<String,Object>> scrapedData = HP.scrapeTableData2();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(scrapedData);
        System.out.println("Scraped Data JSON:");
        System.out.println(jsonString);

        File outputFile = new File(System.getProperty("user.dir") + "/src/test/java/JsonScrapedFile/Oscar-Winning-Film.json");
        outputFile.getParentFile().mkdirs(); 
        mapper.writeValue(outputFile, scrapedData);

        Assert.assertTrue(outputFile.exists(), "The JSON file was not created.");
        Assert.assertTrue(outputFile.length() > 0, "The JSON file is empty.");
        System.out.println("end Test case: testCase02");
        System.out.println();
        


    }


}

