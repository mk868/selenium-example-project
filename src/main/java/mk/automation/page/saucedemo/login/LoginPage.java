package mk.automation.page.saucedemo.login;

import mk.automation.page.saucedemo.products.ProductsPage;
import mk.automation.selenium.Page;
import mk.automation.selenium.condition.UrlConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

  @FindBy(id = "user-name")
  private WebElement usernameInput;
  @FindBy(id = "password")
  private WebElement passwordInput;
  @FindBy(id = "login-button")
  private WebElement loginButton;
  @FindBy(css = "[data-test=\"error\"]")
  private WebElement errorMessage;

  public LoginPage(WebDriver driver) {
    super(driver);
    wait.until(UrlConditions.pathToBe("/"));
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
    return errorMessage.getText();
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
