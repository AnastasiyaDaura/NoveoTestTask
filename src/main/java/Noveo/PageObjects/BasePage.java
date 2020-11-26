package Noveo.PageObjects;

import Noveo.WDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;

import java.io.IOException;

/**
 * Базовый класс, содержащий общие для всех страниц элементы и методы
 */
public abstract class BasePage {
    protected WDriver driver = WDriver.getInstance();

    public WebElement spinner() {
        return driver.findElementByCssSelector("[id = 'loading']");
    }

    public boolean isVisibleSpinner() {
        if (spinner() != null)
            return spinner().isDisplayed();
        else return false;
    }

    public void closeWebDriver() {
        driver.close();
    }

    public String getAlertMessage() {
        return driver.getAlertMessage();
    }

    public void closeAlertWindow() {
        driver.closeAlertWindow();
    }

    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        driver.takeScreenShotOnFailure(testResult);
    }
}