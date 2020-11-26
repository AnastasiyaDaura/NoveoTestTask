package Noveo.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.internal.collections.Pair;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Главная страница
 */
public class MainPage extends BasePage {
    public void open() {
        driver.get("http://qa.noveogroup.com:8010/");
    }

    public WebElement page() {
        return driver.findElementByCssSelector("[class = 'container']");
    }

    public WebElement head() {
        return driver.findElementByXPath("//h1");
    }

    public WebElement firstName() {
        return driver.findElementByCssSelector("[id = 'firstName']");
    }

    public WebElement firstNameError() {
        return driver.findElementByCssSelector("[id = 'firstNameErrors']");
    }

    public WebElement lastName() {
        return driver.findElementByCssSelector("[name = 'lastName']");
    }

    public WebElement dob() {
        return driver.findElementByCssSelector("[name = 'dob']");
    }

    public WebElement phone() {
        return driver.findElementByCssSelector("[name = 'phone']");
    }

    public WebElement email() {
        return driver.findElementByCssSelector("[name = 'email']");
    }

    //Выпадающий список 'Language'
    public WebElement language() {
        return driver.findElementByXPath("//span[@class = 'selection']/span");
    }

    //Элементы выпадающего списка 'Language'
    public List<WebElement> languageItem() {
        return driver.findElementsByCssSelector("[class = 'select2-results__option']");
    }

    //Список выбранных языков
    public List<WebElement> choiceLanguage() {
        return driver.findElementsByCssSelector("[class = 'select2-selection__choice']");
    }

    //Список элементов 'x' для удаления языка
    public List<WebElement> deletingLanguage() {
        return driver.findElementsByCssSelector("[class = 'select2-selection__choice__remove']");
    }

    //Список блоков выбора уровней выбранных в 'Language' языков
    public List<WebElement> languageLevelBlock() {
        return driver.findElementsByXPath("//label[contains(text(), 'language level')]/..");
    }

    //Список уровней языка 'languageName'
    public List<WebElement> languageLevel(String languageName) {
        WebElement languageLevelBlock = languageLevelBlock().stream()
                .filter(element -> element.getText().contains(languageName))
                .limit(1)
                .collect(Collectors.toList()).get(0);
        return languageLevelBlock.findElements(By.xpath("div[@class = 'custom-control custom-radio custom-control-inline']"));
    }

    public WebElement photo() {
        return driver.findElementByXPath("//div[contains(@class, 'dropzone needsclick dz-clickable js-reference-dropzone')]");
    }

    public WebElement removePhotoLink() {
        return driver.findElementByCssSelector("[class = 'dz-remove']");
    }

    public WebElement interests() {
        return driver.findElementByCssSelector("[name = 'interests']");
    }

    public WebElement save() {
        return driver.findElementByCssSelector("[id = 'submit']");
    }

    public void fillFirstName(String firstName) {
        firstName().sendKeys(firstName);
        page().click();
    }

    public void fillLastName(String lastName) {
        if (!isVisibleSpinner()) {
            lastName().sendKeys(lastName);
        }
        page().click();
    }

    public void fillDob(String dob) {
        if (!isVisibleSpinner()) {
            dob().sendKeys(dob);
        }
        dob().click();
        page().click();
    }

    public void fillPhone(String phone) {
        if (!isVisibleSpinner()) {
            phone().sendKeys(phone);
        }
        page().click();
    }

    public void fillEmail(String email) {
        if (!isVisibleSpinner()) {
            email().sendKeys(email);
        }
        page().click();
    }

    public void fillInterests(String interests) {
        if (!isVisibleSpinner()) {
            interests().sendKeys(interests);
        }
        page().click();
    }

    public void pressSave() {
        save().click();
    }

    /**
     * Метод для выбора одного языка language из выпадающего списка
     *
     * @param language - язык, который нужно выбрать
     */
    public void choiceLanguage(String language) {
        languageItem().forEach(item -> {
            if (item.getText().equals(language)) {
                item.click();
            }
        });
    }

    /**
     * Метод выбирает из выпадающего списка все языки из languages
     *
     * @param languages - список языков, которые нужно выбрать
     */
    public void choiceLanguageFromList(List<String> languages) {
        language().click();
        languages.forEach(language -> choiceLanguage(language));
        page().click();
    }

    /**
     * Метод по удалению языка по названию
     *
     * @param languageName - название языка, который нужно удалить из списка
     */
    public void deleteLanguage(String languageName) {
        for (int i = 0; i < choiceLanguage().size(); i++) {
            if (choiceLanguage().get(i).getText().replace("×", "").equals(languageName)) {
                deletingLanguage().get(i).click();
                page().click();
                break;
            }
        }
    }

    /**
     * Метод отмечает заданный уровень у каждого из языков
     *
     * @param languagesAndLevel - список пар: язык - уровень
     */
    public void choiceLanguageLevel(List<Pair<String, String>> languagesAndLevel) {
        for (int i = 0; i < languagesAndLevel.size(); i++) {
            int index = i;
            languageLevel(languagesAndLevel.get(i).first()).stream()
                    .filter(level -> level.getText().equals(languagesAndLevel.get(index).second()))
                    .limit(1).collect(Collectors.toList()).get(0).click();
        }
    }
}
