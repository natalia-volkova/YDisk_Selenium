package appmanager;

import org.openqa.selenium.By;

public class AuthorizationPageObject extends MainPageObject {

  private static final String
          LOGIN_BUTTON="xpath://a[contains(text(), 'Войти')]",
          LOGIN_INPUT="id:passp-field-login",
          PASSWORD_INPUT="id:passp-field-passwd",
          SING_IN_BUTTON="xpath://div[contains(@class, 'passp-sign-in-button')]";


  public AuthorizationPageObject(ApplicationManager app) {
    super(app);
  }

  public void clickAuthButton(){
    this.waitForElementPresent(LOGIN_BUTTON
            ,"Cannot find auth button",7 );
    this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find login button");
  }

  public void enterLoginData(String login, String password){
    this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot find and put the login to the login input");
    clickSubmitButton();
    this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find and put the password to the password input");
  }

  public void clickSubmitButton(){
    this.waitForElementAndClick(SING_IN_BUTTON, "Cannot find and click submit auth button");
  }

  public void login (String login, String password){
    this.clickAuthButton();
    enterLoginData(login, password);
    clickSubmitButton();

  }

  public void logout(){

  }
}
