package mk.automation.selenium;

import com.google.common.base.Strings;
import mk.automation.selenium.browser.ChromeBuilder;
import mk.automation.selenium.browser.FirefoxBuilder;
import mk.automation.testng.BrowserTag;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

  public static final String DEFAULT_TAG = "firefox";

  public static WebDriver create(String tag) {
    if (Strings.isNullOrEmpty(tag)) {
      return create(DEFAULT_TAG);
    }
    var browserTag = new BrowserTag(tag);

    if (browserTag.getBrowser().isEmpty()) {
      throw new IllegalArgumentException("Cannot find browser by tag '" + tag + "'");
    }

    var builder = switch (browserTag.getBrowser().get()) {
      case CHROME -> ChromeBuilder.create();
      case FIREFOX -> FirefoxBuilder.create();
    };

    builder.headless(isHeadless());
    browserTag.getDimension().ifPresent(builder::dimension);

    return builder.build();
  }

  private static boolean isHeadless() {
    var value = System.getenv("DISPLAY");
    return Strings.isNullOrEmpty(value);
  }

}
