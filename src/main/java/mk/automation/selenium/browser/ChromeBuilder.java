package mk.automation.selenium.browser;

import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBuilder extends BrowserBuilder<ChromeBuilder, ChromeDriver> {

  public static ChromeBuilder create() {
    return new ChromeBuilder();
  }

  @Override
  public ChromeDriver build() {
    var options = new ChromeOptions();
    if (headless) {
      options
          .setLogLevel(ChromeDriverLogLevel.ALL)
          .addArguments(
              "--headless",
              "--no-sandbox",
              "--disable-gpu",
              "--autoplay-policy=no-user-gesture-required",
              "--no-first-run",
              "--use-fake-ui-for-media-stream",
              "--use-fake-device-for-media-stream",
              "--disable-sync",
              "--disable-software-rasterizer" // to disable WebGL
          );
    }
    var prefs = new HashMap<String, Object>();
    if (downloadDir != null) {
      prefs.put("download.default_directory", downloadDir);
    }
    options
        .setExperimentalOption("prefs", prefs);
    var driver = new ChromeDriver(options);
    driver.manage().window().setSize(dimension);
    return driver;
  }
}
