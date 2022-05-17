package com.gmail.olemore.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.gmail.olemore.config.CredentialsConfig;
import com.gmail.olemore.testHelpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestSetupAndTeardown {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        String login = config.login();
        String password = config.password();
        String selenoidUrl = System.getProperty("remote", "selenoid.autotests.cloud/wd/hub");
        Configuration.remote = "https://" + login + ":" + password + "@" + selenoidUrl;
        Configuration.baseUrl = "https://demoqa.com";

        String browser = System.getProperty("browser", "chrome");
        String browserSize = System.getProperty("browserSize", "1240x1400");
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;

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
