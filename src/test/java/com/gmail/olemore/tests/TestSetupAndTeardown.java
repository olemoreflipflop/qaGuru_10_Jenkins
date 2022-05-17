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
    static String login = config.login();
    static String password = config.password();
    static String selenoidUrl = System.getProperty("selenoidUrl", "selenoid.autotests.cloud/wd/hub");
    static String selenoidLogin = "https://" + login + ":" + password + "@" + selenoidUrl;

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String browser = System.getProperty("browser", "chrome");
        String browserSize = System.getProperty("browserSize");

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.remote = selenoidLogin;

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
