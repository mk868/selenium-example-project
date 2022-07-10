package mk.automation.selenium;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class for page classes
 */
public abstract class Page implements WrapsDriver {

  private final WebDriver driver;
  protected WebDriverWait wait;

  protected Page(WebDriver driver) {
    Objects.requireNonNull(driver, "driver must not be null");
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
  }

  @Override
  public WebDriver getWrappedDriver() {
    return driver;
  }

  /**
   * Take page screenshot and append to the test report
   */
  public void takeScreenShot() {
    byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    try (var is = new ByteArrayInputStream(bytes)) {
      Allure.addAttachment(this.getClass().getSimpleName() + " screenshot", is);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
