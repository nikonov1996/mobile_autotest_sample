package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import config.Config;
import enums.PropertiesEnum;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    private static final config.Config config = ConfigFactory.create(Config.class);
    private static BrowserstackConfig browserstackConfig;

    BrowserstackDriver() {
        ConfigFactory.setProperty(
                PropertiesEnum.PLATFORM.getValue(),
                System.getProperty(PropertiesEnum.PLATFORM.getValue(), config.env()));
        browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);
    }

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", config.login());
        mutableCapabilities.setCapability("browserstack.key", config.password());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", browserstackConfig.appUrl());

        // Specify device and os_version for testing

        mutableCapabilities.setCapability("device", browserstackConfig.device());
        mutableCapabilities.setCapability("os_version", browserstackConfig.osVersion());

        // Set other BrowserStack mutableCapabilities
        mutableCapabilities.setCapability("project", browserstackConfig.projectName());
        mutableCapabilities.setCapability("build", browserstackConfig.buildName());
        mutableCapabilities.setCapability("name", browserstackConfig.testName());


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired mutableCapabilities defined above
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(browserstackConfig.baseUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
