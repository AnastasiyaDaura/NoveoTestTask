package Noveo.Pojo;

import org.testng.internal.collections.Pair;

import java.util.List;

/**
 * Класс полей элементов формы регистрации
 */
public class RegistrationData {
    public String firstName;
    public String lastName;
    public String dob;
    public String phone;
    public String email;
    //Список пар: язык - уровень знания языка
    public List<Pair<String, String>> languageAndLevel;
    public String photoPath;
    public String interests;

    public RegistrationData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegistrationData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegistrationData setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public RegistrationData setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public RegistrationData setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegistrationData setLanguageAndLevel(List<Pair<String, String>> languageAndLevel) {
        this.languageAndLevel = languageAndLevel;
        return this;
    }

    public RegistrationData setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }

    public RegistrationData setInterests(String interests) {
        this.interests = interests;
        return this;
    }
}
