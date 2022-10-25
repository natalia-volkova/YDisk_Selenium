package test;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class DiskTests extends TestBase{
    private String login = "nataliavolkovatest";
    private String password = "Test##2022";

    @Test
    public void createFileViaYandexDisk(){
        String folderName= "TestFolder";
        String fileName="TestFile";

        app.authorize().login(login, password);
        app.goTo().invokeUserMenu();
        app.goTo().yandexDisk();
        app.goTo().switchSecondOpenedTab();
        app.disk().createFolder(folderName);
        Assert.assertTrue(app.disk().isFolderCreated());
        app.disk().openFolder(folderName);
        app.disk().createDocument(fileName);
        app.disk().closeDocument();
        Assert.assertTrue(app.disk().isDocumentCreated(fileName));





    }

    @AfterSuite
    public void suiteCleanup(){
        app.authorize().logout();
    }
}
