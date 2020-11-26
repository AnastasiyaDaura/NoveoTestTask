package Common;

import Noveo.PageObjects.MainPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.IOException;

/**
 * Класс с общими параметрами запуска тестов
 */
public class TestRunner {
    @AfterMethod
    public void afterTest(ITestResult testResult) throws IOException {
        new MainPage().takeScreenShotOnFailure(testResult);
    }
}
