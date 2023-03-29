package mk.automation.selenium.jassert;

import java.util.function.Function;
import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebDriver;

/**
 * Custom assertions for {@link WebDriver}.
 */
public class WebDriverAssert extends AbstractAssert<WebDriverAssert, WebDriver> {


  protected WebDriverAssert(WebDriver webDriver) {
    super(webDriver, WebDriverAssert.class);
  }

  public static WebDriverAssert assertThat(WebDriver actual) {
    return new WebDriverAssert(actual);
  }

  /**
   * Checks and throws an assertion error when one of the following occurs:
   * <ul>
   *  <li>the function returns null or false
   *  <li>the function throws an exception
   * </ul>
   *
   * @param condition check function
   * @return self
   * @see org.openqa.selenium.support.ui.FluentWait
   */
  public WebDriverAssert expect(Function<? super WebDriver, ?> condition) {
    isNotNull();

    try {
      var value = condition.apply(actual);

      if (value == null || (value.getClass() == Boolean.class && Boolean.FALSE.equals(value))) {
        failWithMessage("Condition [" + condition.toString() + "] returned: " + value);
      }
    } catch (Exception ex) {
      failWithMessage("Condition [" + condition.toString() + "] not met: " + ex.getMessage());
    }

    return this;
  }

}
