import Common.TestRunner;
import Noveo.PageObjects.MainPage;
import Noveo.Pojo.RegistrationData;
import TestDataCreating.CreatingTestData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PositiveTests extends TestRunner {
    public MainPage mainPage = new MainPage();
    private RegistrationData registrationData = new CreatingTestData().createPositiveData();

    @BeforeTest
    public void setup() {
        mainPage.open();
    }

    @Test
    public void testWithEmptyPhoto() {
        List<String> languages = new ArrayList<>();
        registrationData.languageAndLevel.forEach(language -> languages.add(language.first()));

        mainPage.fillFirstName(registrationData.firstName);
        mainPage.fillLastName(registrationData.lastName);
        mainPage.fillDob(registrationData.dob);
        mainPage.fillPhone(registrationData.phone);
        mainPage.fillEmail(registrationData.email);
        mainPage.choiceLanguageFromList(languages);
        if (!registrationData.languageAndLevel.contains("Не знаю ни одного языка")) {
            mainPage.deleteLanguage("Не знаю ни одного языка");
        }
        mainPage.choiceLanguageLevel(registrationData.languageAndLevel);
        mainPage.fillInterests(registrationData.interests);
        //Текст сообщения об успешности не достоверен, т. к. не было данных ожидаемого результата.
        Assert.assertEquals(mainPage.getAlertMessage(), "Регистрация успешна!");
        mainPage.closeAlertWindow();

    }

    @AfterClass
    public void afterAll() {
        mainPage.closeWebDriver();
    }
}
