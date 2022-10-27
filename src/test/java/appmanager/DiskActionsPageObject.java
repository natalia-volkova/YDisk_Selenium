package appmanager;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DiskActionsPageObject extends MainPageObject {
    public DiskActionsPageObject(ApplicationManager app) {
        super(app);
    }
    private static final String
            CREATE_BUTTON="xpath://span[contains(@class, 'create-resource')]/button",
            CREATE_FOLDER="xpath://span[contains(text(), 'Папку')]",
            CREATE_DOCUMENT="xpath://span[contains(text(), 'Текстовый документ')]",
            CREATE_TABLE="xpath://span[contains(text(), 'Таблицу')]",
            CREATE_PTT="xpath://span[contains(text(), 'Презентацию')]",
            ITEM_NAME_INPUT="xpath://form[contains(@class, 'rename-dialog')]//input",
            SAVE_ITEM_BUTTON="xpath://span[contains(text(), 'Сохранить')]/..",

            CREATE_ITEM_BUTTON="xpath://div/button/span[contains(text(), 'Создать')]/..";



    public void clickCreateButton() {
        waitForElementAndClick(CREATE_BUTTON, "The create button click can't be performed");
    }



    public void clickCreateDocument(){
        waitForElementAndClick(CREATE_DOCUMENT, "The create document button click can't be performed");
    }

    public void clickCreateTable(){
        waitForElementAndClick(CREATE_TABLE, "The create table button click can't be performed");
    }

    public void clickCreatePresentation(){
        waitForElementAndClick(CREATE_PTT, "The create presentaion button click can't be performed");
    }

    public void clickCreateFolder(){
        waitForElementAndClick(CREATE_FOLDER, "The create folder button click can't be performed");
        }

    public void enterFileName(String name){
        waitForElementAndSendKeys(ITEM_NAME_INPUT, name, "It is not possible to enter filename");
    }

    public void createItem(String itemType, String itemName){
        clickCreateButton();
        if (itemType.equals("doc")){
            clickCreateDocument();

        }
        else if (itemType.equals("table")) {
            clickCreateTable();
        }

        else if (itemType.equals("folder")) {
            clickCreateFolder();
        }

        else if (itemType.equals("ptt")) {
            clickCreatePresentation();
        }

        enterFileName(itemName);

        if (itemType.equals("folder")){

            waitForElementAndClick(SAVE_ITEM_BUTTON, "The save button is not clickable");
           // waitForElementNotPresent(SAVE_ITEM_BUTTON, "The create folder form is still shown.", 5);
            return;
        }

        else{
            waitForElementAndClick(CREATE_ITEM_BUTTON, "The create button is not clickable");
            waitForElementNotPresent(CREATE_ITEM_BUTTON, "The create document form is still shown.", 5);
            app.launch().switchLastTab();
            DocumentPageObject documentPageObject = new DocumentPageObject(app);
            Assert.assertTrue(documentPageObject.isTitleShown());
            }

    }

    public void createFolder(String name){
        createItem("folder", name);
    }

    public void createDocument(String name){
        createItem("doc", name);
    }

    public void createTable(String name){
        createItem("table", name);
    }
    public void createPresentation(String name){
        createItem("ptt", name);
    }
    public void openFolder(String name){
        WebElement folder = findItemByNameAndExtension(name, "folder");
        wait.until(ExpectedConditions.elementToBeClickable(folder)).click();


        doubleClickElement(folder, "The double click can't be performed!");
        String openedFolderLocator = "xpath://h1[@title='"+name+"']";
        waitForElementPresent(openedFolderLocator, "The folder is not opened");

    }

    public WebElement findItemByNameAndExtension(String name, String extension) {
        String itemLocator;
        if (extension.equals("folder"))
        {
            itemLocator="xpath://div[@class='listing__items']//span[text()='"+name+"']/../..";
        }
        else {itemLocator="xpath://span[text()='"+name+'.'+extension+"']";}
        return waitForElementPresent(itemLocator, "The required item is not found!");


    }

    public void closeDocument(){
        NavigationPageObject navigationPageObject= new NavigationPageObject(app);
        navigationPageObject.closeLastTab();

    }

    public boolean isFilePresent(String fileName, String extension) {
        try{
            findItemByNameAndExtension(fileName,extension);
            return true;
        }
        catch (TimeoutException e){
            return false;

        }

    }



    public boolean isDocumentPresent(String filename){
        return isFilePresent(filename, "docx");
    }

    public boolean isFolderPresent(String filename){
        return isFilePresent(filename, "folder");
    }

    public boolean isTablePresent(String filename){
        return isFilePresent(filename, "xlsx");
    }

    public boolean isPresentationPresent(String filename){
        return isFilePresent(filename, "pttx");
    }

}
