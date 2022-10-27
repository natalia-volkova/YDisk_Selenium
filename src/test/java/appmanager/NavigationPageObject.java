package appmanager;

import java.util.ArrayList;

public class NavigationPageObject extends MainPageObject {
  private static final String
          AVATAR_ICON="xpath://a[@href='https://passport.yandex.ru']",
          DISK_MENU="xpath://a[contains(@href,'disk')]",
          LOGOUT="xpath://span[contains(text(),'Выход')]";


  public NavigationPageObject(ApplicationManager app) {
    super(app);
  }

  public void invokeUserMenu() {
    this.waitForElementAndClick(AVATAR_ICON,"The user menu item is not shown.");
    this.waitForElementPresent(DISK_MENU, "The menu items are not shown");
  }

  public void selectYandexDiskItem() {
    this.waitForElementAndClick(DISK_MENU,"The disk menu item is not shown.");

  }

  public void selectLogoutItem() {
    this.waitForElementAndClick(LOGOUT,"The logout item is not shown.");

  }




  public void switchSecondOpenedTab(){
    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

    driver.switchTo().window(newTab.get(1));
  }

  public void switchMainTab(){
    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

    driver.switchTo().window(newTab.get(0));
  }

  public void closeLastTab(){
    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

    driver.switchTo().window(newTab.get(newTab.size()-1));
    driver.close();
    driver.switchTo().window(newTab.get(newTab.size()-2));
  }

  public void switchLastTab(){
    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

    driver.switchTo().window(newTab.get(newTab.size()-1));
  }






}
