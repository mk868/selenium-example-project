package mk.automation.page.saucedemo.products;

import static mk.automation.selenium.jassert.WebDriverAssert.assertThat;

import java.util.List;
import mk.automation.selenium.Page;
import mk.automation.selenium.condition.UrlConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends Page<ProductsPage> {

  @FindBy(className = "inventory_item")
  private List<WebElement> items;

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected void load() {
    webDriver.get("https://www.saucedemo.com/inventory.html");
  }

  @Override
  public void isLoaded() throws Error {
    assertThat(webDriver)
        .expect(UrlConditions.pathToBe("/inventory.html"));
  }

  public List<ItemFragment> getItems() {
    return items.stream()
        .map(i -> new ItemFragment(getWrappedDriver(), i))
        .toList();
  }
}
