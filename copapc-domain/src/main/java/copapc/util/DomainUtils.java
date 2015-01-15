package copapc.util;

public class DomainUtils {

  public static <T> T nullSafe(T value, T defaultValue) {
    return (value == null) ? defaultValue : value;
  }

}
