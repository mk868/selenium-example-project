package mk.automation.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import mk.automation.page.saucedemo.SauceDemo;
import mk.automation.page.saucedemo.products.ItemFragment;
import mk.automation.page.saucedemo.products.ProductsPage;
import mk.automation.selenium.BrowserManager;
import mk.automation.selenium.Fragment;
import mk.automation.testng.GuiceFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(moduleFactory = GuiceFactory.class)
public class ProductsPageTest {

  @Inject
  BrowserManager driver;
  ProductsPage productsPage;

  @BeforeTest
  public void init() {
    productsPage = SauceDemo.create(driver.getWebDriver())
        .loginPage()
        .typeUsername("standard_user")
        .typePassword("secret_sauce")
        .clickLoginButtonWithSuccess();
  }

  @Test
  public void shouldListThreeItems() {
    assertThat(productsPage.getItems())
        .as("There should be three items on page")
        .hasSize(6);
  }

  @Test
  public void shouldDisplayUniqueImages() {
    // each item should contain unique image
    var items = productsPage.getItems();

    // let's add some screenshots to the report
    productsPage.takeScreenShot();
    items.forEach(Fragment::takeScreenshot);

    assertThat(items)
        .extracting(ItemFragment::getImageSrc)
        .doesNotContainNull()
        .doesNotHaveDuplicates();
  }

}
