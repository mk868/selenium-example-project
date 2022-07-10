package mk.automation.selenium.browser;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBuilder extends BrowserBuilder<FirefoxBuilder, FirefoxDriver> {

  public static FirefoxBuilder create() {
    return new FirefoxBuilder();
  }

  @Override
  public FirefoxDriver build() {
    var options = new FirefoxOptions();
    if (headless) {
      options.setHeadless(true);
    }
    var profile = new FirefoxProfile();
    profile.setPreference("browser.download.folderList", 2);
    profile.setPreference("browser.download.manager.showWhenStarting", false);
    if (downloadDir != null) {
      profile.setPreference("browser.download.dir", downloadDir);
    }
    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/css");
    options.setProfile(profile);
    var driver = new FirefoxDriver(options);
    driver.manage().window().setSize(dimension);
    return driver;
  }
}
