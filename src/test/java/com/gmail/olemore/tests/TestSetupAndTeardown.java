package com.gmail.olemore.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.gmail.olemore.testHelpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestSetupAndTeardown {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1280x1400";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        //Добавляем видео в отчет
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        AllureAttachments.getScreenshot();
        AllureAttachments.getPageSource();
        AllureAttachments.getConsoleLogs();
        AllureAttachments.getVideo();
        closeWebDriver();
    }
}
