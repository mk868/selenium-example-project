package mk.automation.selenium.condition;

import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Conditions for URLs. To better understand naming please see <a
 * href="https://edu.gcfglobal.org/en/internet-tips/understanding-urls/1/">Internet Tips -
 * Understanding URLs</a>
 *
 * @see org.openqa.selenium.support.ui.ExpectedConditions
 */
public class UrlConditions {

  private UrlConditions() {
  }

  /**
   * Check if anchor equals
   *
   * @param anchor url anchor, for example "#login-page"
   * @return true when page URL anchor same as expected anchor
   */
  public static ExpectedCondition<Boolean> anchorToBe(final String anchor) {
    return new ExpectedCondition<>() {
      private final Pattern pattern = Pattern.compile("(?<anchor>#.*$)");
      private String currentAnchor;

      @Override
      public Boolean apply(WebDriver driver) {
        var currentUrl = driver.getCurrentUrl();
        currentAnchor = "";
        var matcher = pattern.matcher(currentUrl);
        if (matcher.find()) {
          currentAnchor = matcher.group("anchor");
        }
        return currentAnchor.equals(anchor);
      }

      @Override
      public String toString() {
        return String.format("url anchor \"%s\" should equal \"%s\"", currentAnchor, anchor);
      }
    };
  }

  /**
   * Check if path equals
   *
   * @param path url file path, for example "/login/index.php"
   * @return true when page URL file path same as expected path
   */
  public static ExpectedCondition<Boolean> pathToBe(final String path) {
    return new ExpectedCondition<>() {
      private final Pattern pattern = Pattern.compile("//[^/]+(?<path>/[^#?]*)");
      private String currentPath;

      @Override
      public Boolean apply(WebDriver driver) {
        var currentUrl = driver.getCurrentUrl();
        currentPath = "";
        var matcher = pattern.matcher(currentUrl);
        if (matcher.find()) {
          currentPath = matcher.group("path");
        }
        return currentPath.equals(path);
      }

      @Override
      public String toString() {
        return String.format("url path \"%s\" should equal \"%s\"", currentPath, path);
      }
    };
  }
}
