package TestDataCreating;

import Noveo.Pojo.RegistrationData;
import org.testng.internal.collections.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для временной генерации тестовых данных
 */
public class CreatingTestData {
    List<Pair<String, String>> languages = new ArrayList<>();

    public RegistrationData createDataByCyrillic() {
        languages.clear();
        languages.add(new Pair<>("JavaScript", "Middle"));
        return new RegistrationData()
                .setFirstName("Иван")
                .setLastName("Иванов")
                .setDob("12/08/1980")
                .setPhone("+7 (222) 333-444-5")
                .setEmail("iIvanov@ya.ru")
                .setLanguageAndLevel(languages)
                .setInterests("Музыка");
    }

    public RegistrationData createPositiveData() {
        languages.clear();
        languages.add(new Pair<>("JavaScript", "Middle"));
        return new RegistrationData()
                .setFirstName("Ivan")
                .setLastName("Иванов")
                .setDob("12/08/1980")
                .setPhone("+7 (222) 333-444-5")
                .setEmail("iIvanov@ya.ru")
                .setLanguageAndLevel(languages)
                .setInterests("Music");
    }
}
