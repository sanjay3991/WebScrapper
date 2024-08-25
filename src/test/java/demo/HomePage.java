package demo;

import org.bouncycastle.util.Exceptions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = "//a[@href='/pages/forms/']")
    WebElement ClickOnHockeyTeam;

    @FindBy(xpath = "//tr[@class = 'team']")
    List<WebElement> tableRow;

    @FindBy(xpath = "//tr[@class = 'film']")
    List<WebElement> tableRowToViewFilms;

    @FindBy(xpath = "//a[@href='/pages/ajax-javascript/']")
    WebElement OscarWinningFilms_link;

    @FindBy(xpath = "//a[@class='year-link']")
    List<WebElement> yearsToViewFilms;

    WrapperClass WC = new WrapperClass();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnHockeyTeams() {
        WC.clickElement(driver, ClickOnHockeyTeam);
    }

    public void clickOnOscarWinningFilms() {
        WC.clickElement(driver, OscarWinningFilms_link);
    }

    public ArrayList<HashMap<String, Object>> scrapeTableData1(int pages) throws InterruptedException {
        ArrayList<HashMap<String, Object>> teamsData = new ArrayList<>();
        for (int PageNumber = 0; PageNumber < pages; PageNumber++) {
            String dynamicXPath = "//a[@href='/pages/forms/?page_num=" + (PageNumber + 1) + "']";
            WebElement pageNumberEle = driver.findElement(By.xpath(dynamicXPath));
            WC.clickElement(driver, pageNumberEle);

            List<WebElement> rows = tableRow;

            for (WebElement row : rows) {
                List<WebElement> col = row.findElements(By.xpath("td"));
                String teamName = WC.getTextFromElementList(driver, col, 0);
                String year = WC.getTextFromElementList(driver, col, 1);
                String winPercentage = WC.getTextFromElementList(driver, col, 5);
                float WinPercentage = Float.parseFloat(winPercentage);

                if (WinPercentage < 0.4) {
                    HashMap<String, Object> teamData = new HashMap<>();
                    teamData.put("epochTime", Instant.now().getEpochSecond());
                    teamData.put("teamName", teamName);
                    teamData.put("year", year);
                    teamData.put("winPercentage", winPercentage);
                    teamsData.add(teamData);
                }

            }
        }
        return teamsData;

    }


    public ArrayList<HashMap<String, Object>> scrapeTableData2() throws InterruptedException {
        ArrayList<HashMap<String, Object>> oscarWinnings = new ArrayList<>();//oscarWinningFilm
        for (WebElement year : yearsToViewFilms) {
            WC.clickElement(driver, year);
            Thread.sleep(5000);
            List<WebElement> rows = tableRowToViewFilms;
            int count = 1;
            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.xpath("td"));
                String Title = WC.getTextFromElementList(driver, cols, 0);
                String Nomination = WC.getTextFromElementList(driver, cols, 1);
                String Award = WC.getTextFromElementList(driver, cols, 2);
                boolean isWinner = cols.get(3).findElements(By.tagName("i")).size() > 0;
                     
                HashMap<String,Object> oscarWinning = new HashMap<>();//oscarWinning
                oscarWinning.put("epochTime", Instant.now().getEpochSecond());
                oscarWinning.put("year", year.getText());
                oscarWinning.put("Title", Title);
                oscarWinning.put("Nomination", Nomination);
                oscarWinning.put("Awards", Award);
                oscarWinning.put("isWinner", isWinner);
                oscarWinnings.add(oscarWinning);
                if(count++ ==5)
                    break;
            }

        }
        return oscarWinnings;
    }

}
