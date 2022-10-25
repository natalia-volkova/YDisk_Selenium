package appmanager;

import java.util.ArrayList;

public class NavigationPageObject extends MainPageObject {
  private static final String
          AVATAR_ICON="xpath://a[@href='https://passport.yandex.ru']",
          DISK_MENU="xpath://a[contains(@href,'disk')]";


  public NavigationPageObject(ApplicationManager app) {
    super(app);
  }

  public void invokeUserMenu() {
    this.waitForElementAndClick(AVATAR_ICON,"The user menu item is not shown.");
    this.waitForElementPresent(DISK_MENU, "The menu items are not shown");
  }

  public void yandexDisk() {
    this.waitForElementAndClick(DISK_MENU,"The disk menu item is not shown.");

  }

  public void switchSecondOpenedTab(){
    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

    driver.switchTo().window(newTab.get(1));
  }

  public void returnMainTab(){
    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());

    driver.switchTo().window(newTab.get(0));
  }




}
