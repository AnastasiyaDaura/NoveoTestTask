package Noveo;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс-обёртка над WebDriver
 */
public class WDriver {
    private static WDriver ourInstance = new WDriver();
    private static WebDriverWait wait;
    private static FirefoxDriver driver;
    private static Logger log;
//    //Объявление WebDriver для браузера Google Chrome
//    private static ChromeDriver driver;

    public static WDriver getInstance() {
        return ourInstance;
    }

    private WDriver() {
        log = LogManager.getLogger();
        System.setProperty("webdriver.gecko.driver", "D:\\IdeaProjects\\NoveoTestTask\\bin\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window();
        wait = new WebDriverWait(driver, 10, 100);
//        //Параметры для WebDriver браузера Google Chrome
//        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\NoveoTestTask\\bin\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        //Параметр для отключения инфо-бара об автоматизированном управлении ПО
//        options.addArguments("disable-infobars");
//        //Разворачивание окна браузера на весь экран
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void get(String url) {
        log.trace(String.format("Открывается страница по адресу '%s'", url));
        driver.get(url);
    }

    public void closeWebDriver() {
        driver.quit();
    }

    @AfterTest
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            doScreenshot();
        }
    }

    /**
     * Метод ищет элемент по cssSelector
     *
     * @param cssSelector - cssSelector элемента
     * @return - WebElement, найденный по cssSelector
     */
    public WebElement findElementByCssSelector(String cssSelector) {
        log.trace(String.format("Поиск элемента по локатору '%s'", cssSelector));
        WebElement element = null;

        try {
            element = getWebElement(By.cssSelector(cssSelector));
            scrollToElement(element);
        } catch (Exception e) {
            doScreenshot();
        }

        return element;
    }

    /**
     * Метод ищет элементы по cssSelector
     *
     * @param cssSelector - cssSelector элементов
     * @return - список WebElement, найденных по cssSelector
     */
    public List<WebElement> findElementsByCssSelector(String cssSelector) {
        log.trace(String.format("Поиск элементов по локатору '%s'", cssSelector));
        List<WebElement> elements = new ArrayList<>();
        WebElement element = null;

        try {
            element = getWebElement(By.cssSelector(cssSelector));
            scrollToElement(element);

            elements.addAll(driver.findElements(By.cssSelector(cssSelector)));
        } catch (Exception e) {
            doScreenshot();
        }

        return elements;
    }

    /**
     * Метод ищет элемент по xpath
     *
     * @param xpath - xPath элемента
     * @return - WebElement, найденный по xpath
     */
    public WebElement findElementByXPath(String xpath) {
        log.trace(String.format("Поиск элемента по локатору '%s'", xpath));
        WebElement element = null;

        try {
            element = getWebElement(By.xpath(xpath));
            scrollToElement(element);
        } catch (Exception e) {
            doScreenshot();
        }

        return element;
    }

    /**
     * Метод ищет элементы по xpath
     *
     * @param xpath - xPath элементов
     * @return - список WebElement, найденных по xpath
     */
    public List<WebElement> findElementsByXPath(String xpath) {
        log.trace(String.format("Поиск элементов по локатору '%s'", xpath));
        List<WebElement> elements = new ArrayList<>();
        WebElement element = null;

        try {
            element = getWebElement(By.xpath(xpath));
            scrollToElement(element);

            elements.addAll(driver.findElements(By.xpath(xpath)));
        } catch (Exception e) {
            doScreenshot();
        }

        return elements;
    }

    private WebElement getWebElement(By by) {
        WebElement element;
        element = wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
        element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(by));
        element = wait.until(
                ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    private void doScreenshot() {
        File file = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        try {
            SimpleDateFormat format =
                    new SimpleDateFormat("dd.MM_HH-mm-ss");
            Date date = new Date();
            FileUtils.copyFile(file, new File(
                    String.format("screenshots/%s-scr.jpg",
                            format.format(date))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод выполняет прокручивание страницы до элемента
     *
     * @param element - элемент, до которого необходимо прокрутить страницу
     */
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getAlertMessage() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    public void closeAlertWindow() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void close() {
        if (driver != null)
            driver.close();
    }
}