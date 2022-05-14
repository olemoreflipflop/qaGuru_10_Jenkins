package com.gmail.olemore.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.gmail.olemore.pages.pageComponents.CalendarComponent;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    public CalendarComponent calendarComponent = new CalendarComponent();

    SelenideElement
            studentRegistrationForm = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            resultsTable = $(".table-responsive"),
            userNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            currentAddressInput = $("#currentAddress"),
            stateSelect = $("#state"),
            citySelect = $("#city"),
            submitButton = $("#submit"),
            pictureUploadFile = $("#uploadPicture"),
            hobbiesCheckbox = $("#hobbiesWrapper"),
            genderRadioButton = $("#genterWrapper");

    SelenideElement stateSelectOption(String value) {
        return $("#stateCity-wrapper").$(byText(value));
    }

    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        studentRegistrationForm.shouldHave(text("Student Registration Form"));
        return this;
    }
    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderRadioButton.$(byText(value)).click();
        return this;
    }

    public RegistrationPage typeUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage typeSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesCheckbox.$(byText(value)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String path) {
        pictureUploadFile.uploadFromClasspath(path);
        return this;
    }

    public RegistrationPage typeAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateSelect.scrollTo().click();
        stateSelectOption(value).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        citySelect.scrollTo().click();
        stateSelectOption(value).click();
        return this;
    }

    public RegistrationPage clickSubmit() {
        submitButton.scrollTo().click();
        return this;
    }

    public RegistrationPage checkResultsValue(String key, String value) {
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));
        return this; // return new RegistrationPage() - То же самое
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage closeModal() {
        $("#closeLargeModal").click();
        return this;
    }
}
