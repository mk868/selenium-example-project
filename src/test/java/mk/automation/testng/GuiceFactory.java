package mk.automation.testng;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import mk.automation.selenium.BrowserManager;
import org.testng.IModuleFactory;
import org.testng.ITestContext;

/**
 * @see com.google.inject.Inject
 * @see org.testng.annotations.Guice
 */
public class GuiceFactory implements IModuleFactory {

  @Override
  public Module createModule(ITestContext context, Class<?> aClass) {
    return new AbstractModule() {
      @Override
      protected void configure() {
        super.configure();
        bind(BrowserManager.class)
            .toProvider(() -> new TestScopeBrowserManager(context))
            .in(Scopes.SINGLETON);

      }
    };
  }
}
