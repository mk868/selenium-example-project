package mk.automation.testng;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import org.openqa.selenium.Dimension;

public class BrowserTag {

  private final String tag;

  public BrowserTag(String tag) {
    this.tag = Objects.requireNonNullElse(tag, "");
  }

  public enum Browser {
    FIREFOX,
    CHROME
  }

  public Optional<Browser> getBrowser() {
    var tagLowerCase = tag.toLowerCase();
    if (tagLowerCase.contains("chrome")) {
      return Optional.of(Browser.CHROME);
    }
    if (tagLowerCase.contains("firefox")) {
      return Optional.of(Browser.FIREFOX);
    }
    return Optional.empty();
  }

  /**
   * Tries to found screen size in the input tag.
   * <p>
   * It detects:
   * <ul>
   *   <li> ###x### pattern - width x height
   *   <li> FullHD - 1920x1080
   *   <li> 4K -
   * </ul>
   *
   * @return optional dimension if found
   */
  public Optional<Dimension> getDimension() {
    var pattern = Pattern.compile("(?<width>\\d{3,5})[xX](?<height>\\d{3,5})");
    var matcher = pattern.matcher(tag);
    if (matcher.find()) {
      return Optional.of(new Dimension(
          Integer.parseInt(matcher.group("width")),
          Integer.parseInt(matcher.group("height"))
      ));
    }
    if (tag.contains("FullHD")) {
      return Optional.of(new Dimension(1920, 1080));
    }
    if (tag.contains("4K")) {
      return Optional.of(new Dimension(3840, 2160));
    }
    return Optional.empty();
  }


}
