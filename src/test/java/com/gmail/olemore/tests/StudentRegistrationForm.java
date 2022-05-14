package com.gmail.olemore.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.gmail.olemore.pages.RegistrationPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.gmail.olemore.testData.RegistrationFormData.*;
import static io.qameta.allure.Allure.step;

public class StudentRegistrationForm extends TestSetupAndTeardown{

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void openForm() {
        step("Open Registration Form", () -> {
            open("/automation-practice-form");
            Selenide.executeJavaScript(
                    "document.querySelector(\"footer\").hidden = 'true';" +
                            "document.querySelector(\"#fixedban\").hidden = 'true'");
        });
    }

    @Test
    @Story("Заполнение формы регистрации с тестовыми данными")
    void setAllInputsTestWithFakeData() {
        registrationPage
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(emailAddress)
                .setGender(gender)
                .typeUserNumber(mobileNumber)
                .setBirthDate(day, month, year)
                .typeSubject(subject)
                .setHobbies(hobby)
                .uploadPicture(filePath)
                .typeAddress(address)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        //Проверка формы регистрации
        registrationPage.checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", emailAddress)
                .checkResultsValue("Gender", gender)
                .checkResultsValue("Mobile", mobileNumber)
                .checkResultsValue("Date of Birth", day + " " + month + "," + year)
                .checkResultsValue("Subjects", subject)
                .checkResultsValue("Hobbies", hobby)
                .checkResultsValue("Picture", filePath)
                .checkResultsValue("Address", address)
                .checkResultsValue("State and City", state + " " + city);
    }

    //тест без генерации тестовых данных
    @Test
    @Story("Заполнение формы регистрации без тестовых данных")
    void setAllInputsTestWithDsl() {
        registrationPage
                .typeFirstName("Olga")
                .typeLastName("Filippova")
                .typeEmail("qqq@gmail.com")
                .setGender("Female")
                .typeUserNumber("1234567890")
                .setBirthDate("26", "May", "1995")
                .typeSubject("Computer Science")
                .setHobbies("Reading")
                .uploadPicture("file.png")
                .typeAddress("Current Address")
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmit();

        //Проверка формы регистрации
        registrationPage.checkResultsValue("Student Name", "Olga Filippova")
                .checkResultsValue("Student Email", "qqq@gmail.com")
                .checkResultsValue("Gender", "Female")
                .checkResultsValue("Mobile", "1234567890")
                .checkResultsValue("Date of Birth", "26 May,1995")
                .checkResultsValue("Subjects", "Computer Science")
                .checkResultsValue("Hobbies", "Reading")
                .checkResultsValue("Picture", "file.png")
                .checkResultsValue("Address", "Current Address")
                .checkResultsValue("State and City", "NCR Delhi");
    }
}