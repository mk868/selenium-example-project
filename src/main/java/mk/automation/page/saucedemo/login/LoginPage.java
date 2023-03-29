package mk.automation.page.saucedemo.login;

import static mk.automation.selenium.jassert.WebDriverAssert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import mk.automation.page.saucedemo.products.ProductsPage;
import mk.automation.selenium.Page;
import mk.automation.selenium.condition.UrlConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page<LoginPage> {

  @FindBy(id = "user-name")
  private WebElement usernameInput;
  @FindBy(id = "password")
  private WebElement passwordInput;
  @FindBy(id = "login-button")
  private WebElement loginButton;
  @FindBy(css = "[data-test='error']")
  private WebElement errorMessage;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected void load() {
    webDriver.get("https://www.saucedemo.com/");
  }

  @Override
  protected void isLoaded() throws Error {
    assertThat(webDriver)
        .expect(UrlConditions.pathToBe("/"))
        .expect(visibilityOf(usernameInput));
  }

  public LoginPage typeUsername(String username) {
    usernameInput.clear();
    usernameInput.sendKeys(username);
    return this;
  }

  public LoginPage typePassword(String password) {
    passwordInput.clear();
    passwordInput.sendKeys(password);
    return this;
  }

  public String getErrorMessage() {
    return wait.until(visibilityOf(errorMessage)).getText();
  }

  public LoginPage clickLoginButtonExpectingErrorMessage() {
    loginButton.click();
    return this;
  }

  public ProductsPage clickLoginButtonWithSuccess() {
    loginButton.click();
    return new ProductsPage(getWrappedDriver());
  }
}
