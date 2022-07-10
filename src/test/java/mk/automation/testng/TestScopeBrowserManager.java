package mk.automation.testng;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mk.automation.selenium.BrowserManager;
import mk.automation.selenium.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.TestRunner;
import org.testng.xml.XmlTest;

public class TestScopeBrowserManager implements ITestListener, BrowserManager {

  private static final Logger log = LoggerFactory.getLogger(TestScopeBrowserManager.class);
  private static final String BROWSER_PARAM = "browser";

  private static final Map<XmlTest, WebDriver> webDrivers = new ConcurrentHashMap<>();

  private final XmlTest currentXmlTest;

  public TestScopeBrowserManager(ITestContext context) {
    this.currentXmlTest = context.getCurrentXmlTest();
    if (context instanceof TestRunner testRunner) {
      if (!testRunner.getTestListeners().contains(this)) {
        testRunner.addListener(this);
      }
    }
  }

  public WebDriver getWebDriver() {
    return webDrivers.computeIfAbsent(currentXmlTest, s -> createNewDriver());
  }

  private WebDriver createNewDriver() {
    return WebDriverFactory.create(currentXmlTest.getParameter(BROWSER_PARAM));
  }

  @Override
  public void onFinish(ITestContext context) {
    var currentTest = context.getCurrentXmlTest();
    if (!currentTest.equals(currentXmlTest)) {
      // other test listener will should handle this
      return;
    }
    webDrivers.computeIfPresent(currentTest, (test, driver) -> {
      closeWebDriver(driver);
      return null;
    });
  }

  private void closeWebDriver(WebDriver driver) {
    try {
      driver.close();
      driver.quit();
    } catch (Exception ex) {
      log.error("Cannot properly close webdriver", ex);
    }
  }
}
