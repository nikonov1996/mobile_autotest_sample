package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.BrowserstackConfig;
import config.Config;
import drivers.BrowserstackDriver;
import drivers.EmulatorDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    protected static final Config config = ConfigFactory.create(Config.class);

    @BeforeAll
    static void beforeAll() {

        if (List.of("android", "ios").contains(config.env().toLowerCase())) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else if (config.env().equalsIgnoreCase("emulator")) {
            Configuration.browser = EmulatorDriver.class.getName();
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = Selenide.sessionId().toString();
        Attach.pageSource();
        closeWebDriver();

        if (List.of("android", "ios").contains(config.env().toLowerCase())) {
            Attach.addVideo( sessionId );
        }
    }

}