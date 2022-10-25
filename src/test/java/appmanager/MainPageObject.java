package appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainPageObject {

  protected ApplicationManager app;
  protected WebDriver driver;
  WebDriverWait wait;

  //public HelperBase(WebDriver wd) {
  //  this.wd = wd;
  //}

  public MainPageObject(ApplicationManager app) {
    this.app = app;
    this.driver = app.driver;
    this.wait = new WebDriverWait(driver, 25);
  }

  public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
    By by = this.getLocatorString(locator);
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");

    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public WebElement waitForElementPresent(String locator, String error_message) {
    By by = this.getLocatorString(locator);
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.withMessage(error_message + "\n");

    return wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public WebElement waitForElementAndClick(String locator, String error_message) {
    By by = this.getLocatorString(locator);
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.withMessage(error_message + "\n");

    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
    element.click();
    return element;
  }





  public WebElement waitForElementAndSendKeys(String locator, String value, String error_message) {


      WebElement element = waitForElementPresent(locator, error_message);
      element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
      element.sendKeys(value);
      return element;

  }


  public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    By by = this.getLocatorString(locator);
    wait.withMessage(error_message + "\n");

    return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
    WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
    element.clear();
    return element;
  }

  public WebElement doubleClickElement(String locator, String error_message)
  {
    WebElement element = waitForElementPresent(locator, error_message);
    Actions action = new Actions(driver);
    action.doubleClick(element);
    return element;

  }

  public void doubleClickElement(WebElement element, String error_message)
  {

    Actions action = new Actions(driver);
    action.doubleClick(element);


  }
  private By getLocatorString(String locator_with_type) {
    String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
    String by_type = exploded_locator[0];
    String locator = exploded_locator[1];
    if (by_type.equals("xpath")) {
      return By.xpath(locator);

    } else if (by_type.equals("id")) {
      return By.id(locator);
    } else if (by_type.equals("css")) {
      return By.cssSelector(locator);
    } else {
      throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
    }
  }

  public void closeLastTab(){
    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

    driver.switchTo().window(newTab.get(newTab.size()-1));
    driver.close();
    driver.switchTo().window(newTab.get(newTab.size()-2));
  }





}










