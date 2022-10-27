package test;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class DiskTests extends TestBase{
    private String login = "nataliavolkovatest";
    private String password = "Test##2022";

    @Test
    public void createDocumentViaYandexDisk(){
        String folderName= "TestFolder5";
        String fileName="TestFile5";

        app.authorize().login(login, password);
        app.launch().invokeUserMenu();
        app.launch().selectYandexDiskItem();
        app.launch().switchSecondOpenedTab();
        app.disk().createFolder(folderName);
        Assert.assertTrue(app.disk().isFolderPresent(folderName));
        app.disk().openFolder(folderName);
        app.disk().createDocument(fileName);

        app.document().closeDocument();
        Assert.assertTrue(app.disk().isDocumentPresent(fileName));





    }

    @AfterSuite(alwaysRun = true)
    public void suiteCleanup(){
        app.authorize().logout();
    }
}
