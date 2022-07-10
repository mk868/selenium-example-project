package mk.automation.page.saucedemo.products;

import mk.automation.selenium.Fragment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemFragment extends Fragment {

  @FindBy(css = "img.inventory_item_img")
  private WebElement itemImage;
  @FindBy(className = "inventory_item_name")
  private WebElement itemName;
  @FindBy(className = "inventory_item_desc")
  private WebElement itemDescription;
  @FindBy(className = "inventory_item_price")
  private WebElement itemPrice;
  @FindBy(className = "btn_inventory")
  private WebElement addToCartButton;

  protected ItemFragment(WebDriver driver,
      WebElement element) {
    super(driver, element);
  }

  public String getImageSrc() {
    return itemImage.getAttribute("src");
  }

  public String getName() {
    return itemName.getText();
  }

  public String getDescription() {
    return itemDescription.getText();
  }

  public String getPrice() {
    return itemPrice.getText();
  }

  public ItemFragment clickAddToCartButton() {
    addToCartButton.click();
    return this;
  }
}
