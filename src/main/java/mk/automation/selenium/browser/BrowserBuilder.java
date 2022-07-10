package mk.automation.selenium.browser;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public abstract class BrowserBuilder<T extends BrowserBuilder<T, D>, D extends WebDriver> {

  protected Dimension dimension = new Dimension(1920, 1080);
  protected boolean headless = false;
  protected String downloadDir = null;

  @SuppressWarnings("unchecked")
  public T dimension(Dimension dimension) {
    this.dimension = dimension;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T headless(boolean headless) {
    this.headless = headless;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T downloadDir(String downloadDir) {
    this.downloadDir = downloadDir;
    return (T) this;
  }

  public abstract D build();
}
