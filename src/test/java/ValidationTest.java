import Common.TestRunner;
import Noveo.PageObjects.MainPage;
import Noveo.Pojo.RegistrationData;
import TestDataCreating.CreatingTestData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidationTest extends TestRunner {
    public MainPage mainPage = new MainPage();
    private RegistrationData registrationDataCyrillic = new CreatingTestData().createDataByCyrillic();

    @BeforeTest
    public void setup() {
        mainPage.open();
    }

    @Test
    public void testWithEmptyForm() {
        mainPage.pressSave();
        Assert.assertEquals(mainPage.getAlertMessage(), "Упс, что-то пошло не так");
        mainPage.closeAlertWindow();
    }

    @Test
    public void checkFillingFirstNameByCyrillic() {
        mainPage.fillFirstName(registrationDataCyrillic.firstName);
        mainPage.lastName().click();
        Assert.assertEquals(mainPage.firstNameError().getText(), "First name must contain only latin letters");
    }

    @AfterClass
    public void afterAll() {
        mainPage.closeWebDriver();
    }
}
