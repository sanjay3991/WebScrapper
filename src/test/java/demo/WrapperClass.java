package demo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class WrapperClass {

    // Method to click an element
    public void clickElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Method to enter text into a text field
    public void enterText(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // Method to retrieve the text from an element
    public String getElementText(WebDriver driver, WebElement about_Message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(about_Message));
        return about_Message.getText();
    }

    // Method to get text from an element in a list at a specified index with wait
    public String getTextFromElementList(WebDriver driver, List<WebElement> elements, int index) throws InterruptedException {
            
            // Thread.sleep(1000);
            return elements.get(index).getText();
            
    }

    // Method to retrieve an element's attribute value
    public String getElementAttribute(WebDriver driver, WebElement element, String attribute) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attribute);
    }

    // Method to check if an element is displayed
    public boolean isElementDisplayed(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Method to check if an element is enabled
    public boolean isElementEnabled(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Method to check if an element is selected (useful for checkboxes, radio buttons, etc.)
    public boolean isElementSelected(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isSelected();
    }

    // Method to select an option from a dropdown by visible text
    public void selectDropdownByVisibleText(WebDriver driver, WebElement dropdownElement, String visibleText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownElement)).click();
        WebElement option = dropdownElement.findElement(By.xpath(".//option[text()='" + visibleText + "']"));
        option.click();
    }

    // Method to retrieve a list of WebElements
    public List<WebElement> getElements(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    // Method to wait for an element to be visible
    public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Method to wait for an element to be clickable
    public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Method to take a screenshot
    public String takeScreenshot(WebDriver driver, String filePath) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile.getAbsolutePath();
    }

    // Method to switch to a frame by index
    public void switchToFrameByIndex(WebDriver driver, int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    // Method to switch to a frame by name or ID
    public void switchToFrameByNameOrId(WebDriver driver, String nameOrId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
    }

    // Method to switch to a frame by WebElement
    public void switchToFrameByWebElement(WebDriver driver, WebElement frameElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    // Method to switch back to the default content from a frame
    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    // Method to handle alert popups
    public void handleAlert(WebDriver driver, boolean accept) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }
}