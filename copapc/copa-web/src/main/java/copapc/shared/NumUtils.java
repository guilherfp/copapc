package copapc.shared;

import java.text.NumberFormat;

/**
 * @author Guilherme Pacheco
 */
public class NumUtils {

  private static final NumberFormat NUMBER_FORMAT;

  static {
    NUMBER_FORMAT = NumberFormat.getInstance();
    NUMBER_FORMAT.setMaximumFractionDigits(1);
    NUMBER_FORMAT.setMinimumFractionDigits(0);
  }

  private NumUtils() {
    super();
  }

  public static String format(double value) {
    return NUMBER_FORMAT.format(value);
  }

  public static double media(double valor, double divisor) {
    return divisor != 0 ? valor / divisor : valor;
  }

}
