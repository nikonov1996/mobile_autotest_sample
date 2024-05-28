
package tests.browserstack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.xpath;


public class SearchTests extends TestBase {

    @DisplayName("Checking search of java")
    @Test
    @Tag("Android")
    void successfulSearchTest() {
//        step("Skip onboarding window", () -> {
//            $(id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
//        });
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
    @DisplayName("Checking 'About' in settings")
    @Test
    @Tag("Android")
    void searchAboutTest() {
        step("Open menu", () ->
                $(id("org.wikipedia.alpha:id/menu_overflow_button")).click()
        );

        step("Open settings", () ->
                $(id("org.wikipedia.alpha:id/explore_overflow_settings")).click()
        );

        step("Verify 'About'", () -> {
            $(xpath(("//*[@text='About']"))).shouldBe(visible);
        });
    }

    @DisplayName("Checking com.browserstack.Sample")
    @Test
    @Tag("iOS")
    void iosTextTest() {

        String text = "iPhoneTest";
        step("Click Text Button", () -> {
            $(id("Text Button")).click();
        });

        step("Check initial state Output text", () -> {
            assertEquals("Waiting for text input.", $(id("Text Output")).getText());
        });

        step(format("Set value %s in the input field and press enter", text), () -> {
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys(text);
            $(id("Text Input")).pressEnter();
        });

        step("Check Output text", () -> {
            $(accessibilityId("Text Output")).shouldHave(text(text));
        });
    }
}
