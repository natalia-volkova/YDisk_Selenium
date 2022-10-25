package appmanager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class DiskActionsPageObject extends MainPageObject {
    public DiskActionsPageObject(ApplicationManager app) {
        super(app);
    }
    private static final String
            CREATE_BUTTON="xpath://span[contains(@class, 'create-resource')]/button",
            CREATE_FOLDER="xpath://span[contains(text(), 'Папку')]",
            CREATE_DOCUMENT="xpath://span[contains(text(), 'Папку')]",
            CREATE_TABLE="xpath://span[contains(text(), 'Папку')]",
            CREATE_PTT="xpath://span[contains(text(), 'Папку')]",
            ITEM_NAME_INPUT="xpath://form[contains(@class, 'rename-dialog')]//input",
            SAVE_ITEM_BUTTON="xpath://span[contains(text(), 'Сохранить')]/..";



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
        waitForElementAndClick(SAVE_ITEM_BUTTON, "The save button is not clickable");
    }

    public void createFolder(String name){
        createItem("folder", name);
    }

    public void createDocument(String name){
        createItem("doc", name);
    }

    public void openFolder(String name){
        doubleClickElement(findItemByNameAndExtension(name, ""), "The double click can't be performed!");

    }

    public WebElement findItemByNameAndExtension(String name, String extension) {
        String itemLocator;
        if (extension.equals(""))
        {
            itemLocator="xpath://span[text()='"+name+"']";
        }
        else itemLocator="xpath://span[text()='"+name+'.'+extension+"']";
        return waitForElementPresent(itemLocator, "The required item is not found!");


    }

    public void closeDocument(){
        closeLastTab();

    }

    public boolean isFileCreated(String fileName, String extension) {
        try{
            findItemByNameAndExtension(fileName,extension);
            return true;
        }
        catch (TimeoutException e){
            return false;

        }

    }

    public boolean isDocumentCreated(String filename){
        return isFileCreated(filename, "docx");
    }

    public boolean isFolderCreated(String filename){
        return isFileCreated(filename, "");
    }

    public boolean isTableCreated(String filename){
        return isFileCreated(filename, "xls");
    }

    public boolean isPresentationCreated(String filename){
        return isFileCreated(filename, "pttx");
    }

}
