package mk.automation.selenium.condition;

import mk.automation.selenium.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class LoadConditions {

  public static <T extends Page<T>> ExpectedCondition<T> loaded(final T component) {
    return new ExpectedCondition<>() {

      @Override
      public T apply(WebDriver driver) {
        component.isLoaded();
        return component;
      }

      @Override
      public String toString() {
        return String.format("Component '%s' loaded", component.toString());
      }
    };
  }
}
