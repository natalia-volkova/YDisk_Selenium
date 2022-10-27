package appmanager;

import org.openqa.selenium.TimeoutException;

public class DocumentPageObject extends MainPageObject{
    public DocumentPageObject(ApplicationManager app) {
        super(app);
    }
    private static final String
        TITLE="xpath://title";

    public boolean isTitleShown(){
        try {
            waitForElementPresent(TITLE, "The title is not shown", 20);
            return true;
        }
        catch (TimeoutException e){
            return false;
        }

    }

    public String getDocumentTitle(){
        return  waitForElementPresent(TITLE, "The title is not shown", 20).getText();

    }

    public void closeDocument(){
        NavigationPageObject navigationPageObject= new NavigationPageObject(app);
        navigationPageObject.closeLastTab();

    }
}
