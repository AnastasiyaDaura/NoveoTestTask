import Common.TestRunner;
import Noveo.PageObjects.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class UnitTest extends TestRunner {
    public MainPage mainPage = new MainPage();

    @BeforeTest
    public void setup() {
        mainPage.open();
    }

    @Test
    public void checkLoadOfElements() {
        assertNotNull(mainPage.head(), "Элемент заголовка не найден!");
        assertNotNull(mainPage.firstName(), "Элемент 'First name' не найден!");
        assertNotNull(mainPage.lastName(), "Элемент 'Last name' не найден!");
        assertNotNull(mainPage.dob(), "Элемент 'Dob' не найден!");
        assertNotNull(mainPage.language(), "Элемент 'Language' не найден!");
        assertNotNull(mainPage.phone(), "Элемент 'Phone' не найден!");
        assertNotNull(mainPage.email(), "Элемент 'Email' не найден!");
        assertNotNull(mainPage.photo(), "Элемент 'Photo' не найден!");
        assertNotNull(mainPage.interests(), "Элемент 'Interests' не найден!");
        assertNotNull(mainPage.save(), "Элемент кнопка 'Save' не найден!");
    }

    @AfterClass
    public void afterAll() {
        mainPage.closeWebDriver();
    }
}
