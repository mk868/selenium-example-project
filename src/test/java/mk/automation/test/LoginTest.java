package mk.automation.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import mk.automation.page.saucedemo.SauceDemo;
import mk.automation.selenium.BrowserManager;
import mk.automation.testng.GuiceFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(moduleFactory = GuiceFactory.class)
public class LoginTest {

  @Inject
  BrowserManager driver;

  @Test
  public void shouldBlockLoginForLockedUser() {
    var loginPage = SauceDemo.create(driver.getWebDriver())
        .loginPage()
        .typeUsername("locked_out_user")
        .typePassword("secret_sauce")
        .clickLoginButtonExpectingErrorMessage();

    loginPage.takeScreenShot();
    assertThat(loginPage.getErrorMessage()).contains("this user has been locked out");
  }

  @Test
  public void shouldLoginStandardUser() {
    SauceDemo.create(driver.getWebDriver())
        .loginPage()
        .typeUsername("standard_user")
        .typePassword("secret_sauce")
        .clickLoginButtonWithSuccess();
    // should open products page
  }

}
