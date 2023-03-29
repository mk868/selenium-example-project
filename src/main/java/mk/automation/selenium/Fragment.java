package mk.automation.selenium;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class for page fragments
 */
public abstract class Fragment implements WrapsDriver, WrapsElement {

  protected final WebDriver webDriver;
  protected final WebElement element;
  protected WebDriverWait wait;

  protected Fragment(WebDriver webDriver, WebElement element) {
    Objects.requireNonNull(webDriver, "driver must not be null");
    Objects.requireNonNull(element, "element must not be null");
    PageFactory.initElements(element, this);
    this.webDriver = webDriver;
    this.element = element;
    this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
  }

  @Override
  public WebDriver getWrappedDriver() {
    return webDriver;
  }

  @Override
  public WebElement getWrappedElement() {
    return element;
  }

  /**
   * Take page fragment screenshot and append to the test report
   */
  public void takeScreenshot() {
    byte[] bytes = element.getScreenshotAs(OutputType.BYTES);
    try (var is = new ByteArrayInputStream(bytes)) {
      Allure.addAttachment(this.getClass().getSimpleName() + " screenshot", is);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
