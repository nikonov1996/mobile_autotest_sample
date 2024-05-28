package tests.local;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.OnboardingPage;
import tests.TestBase;

import static io.qameta.allure.Allure.step;

public class OnboardingTests extends TestBase {

    @DisplayName("Checking onboarding screen")
    @Test
    @Tag("emulator")
    public void onboardingTest() {

        OnboardingPage onboardingPage = new OnboardingPage();

        step("Checking of 1st page", () -> {
            onboardingPage.verifyBasicElements("The Free Encyclopedia")
                    .checkButtonIsVisible(onboardingPage.addLangButton)
                    .checkButtonIsVisible(onboardingPage.skipButton)
                    .goToNextPage();
        });

        step("Checking of 2nd page", () -> {
            onboardingPage.verifyBasicElements("New ways to explore")
                    .goToNextPage();
        });

        step("Checking of 3rd page", () -> {
            onboardingPage.verifyBasicElements("Reading lists with sync")
                    .goToNextPage();
        });

        step("Checking of 4th page", () -> {
            onboardingPage.verifyBasicElements("Data & Privacy")
                    .checkButtonIsVisible(onboardingPage.onboardingDoneButton);
        });
    }
}