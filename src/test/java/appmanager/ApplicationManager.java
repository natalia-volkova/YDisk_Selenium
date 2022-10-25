package appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  WebDriver driver;

  private NavigationPageObject navigationPageObject;

  private AuthorizationPageObject authorizationPageObject;
  private DiskActionsPageObject diskActionsPageObject;

  private String browser;


  public ApplicationManager(String browser)  {

    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {


    System.setProperty("webdriver.chrome.driver","/drivers/selenium/chromedriver.exe");
    driver = new ChromeDriver();
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrl"));

    navigationPageObject = new NavigationPageObject(this);
    authorizationPageObject = new AuthorizationPageObject(this);
    diskActionsPageObject = new DiskActionsPageObject(this);


  }


  public void stop() {
    driver.quit();
  }



  public AuthorizationPageObject authorize() {
    return authorizationPageObject;
  }

  public NavigationPageObject goTo() {
    return navigationPageObject;
  }


  public DiskActionsPageObject disk() {
    return diskActionsPageObject;
  }


  public byte[] takeScreenshot() {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }


}
