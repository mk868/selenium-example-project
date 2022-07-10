package mk.automation.page.saucedemo;

import mk.automation.page.saucedemo.login.LoginPage;
import org.openqa.selenium.WebDriver;

public class SauceDemo {

  private final WebDriver driver;

  public SauceDemo(WebDriver driver) {
    this.driver = driver;
  }

  public static SauceDemo create(WebDriver driver) {
    return new SauceDemo(driver);
  }

  public LoginPage loginPage() {
    driver.get("https://www.saucedemo.com/");
    return new LoginPage(driver);
  }

}
