package mk.automation.selenium;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.Clock;
import java.time.Duration;
import java.util.Objects;

/**
 * Base class for page classes
 */
public abstract class Page<T extends Page<T>> extends SlowLoadableComponent<T> implements
    WrapsDriver {

  private static final int DEFAULT_TIMEOUT = 15;

  protected final WebDriver webDriver;
  protected WebDriverWait wait;

  protected Page(WebDriver webDriver) {
    super(Clock.systemUTC(), DEFAULT_TIMEOUT);
    Objects.requireNonNull(webDriver, "driver must not be null");
    PageFactory.initElements(webDriver, this);
    this.webDriver = webDriver;
    this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIMEOUT));
  }

  @Override
  public WebDriver getWrappedDriver() {
    return webDriver;
  }

  /**
   * Take page screenshot and append to the test report
   */
  public void takeScreenShot() {
    byte[] bytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    try (var is = new ByteArrayInputStream(bytes)) {
      Allure.addAttachment(this.getClass().getSimpleName() + " screenshot", is);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
