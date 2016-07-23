package copapc.copa.web.shared;

import java.util.function.Supplier;

/**
 * @author Guilherme Pacheco
 */
public final class Lazy<T> {

  private T value;
  private Supplier<T> supplier;

  private Lazy() {
    super();
  }

  private Lazy(Supplier<T> supplier) {
    this.supplier = supplier;
  }

  public T get(Supplier<T> supplier) {
    if (value == null) {
      value = supplier.get();
    }
    return value;
  }

  public T get() {
    if (value == null && supplier != null) {
      value = supplier.get();
    }
    return value;
  }

  public void set(T value) {
    this.value = value;
  }

  public static <T> Lazy<T> of(Supplier<T> supplier) {
    return new Lazy<>(supplier);
  }

  public static <T> Lazy<T> empty() {
    return new Lazy<>();
  }

}
