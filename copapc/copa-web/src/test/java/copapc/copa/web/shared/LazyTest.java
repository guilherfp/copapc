package copapc.copa.web.shared;

import static org.junit.Assert.assertEquals;

import java.util.function.Supplier;

import org.junit.Test;

/**
 * @author Guilherme Pacheco
 */
public class LazyTest {

  private int count = 1;

  @Test
  public void testGet() throws Exception {
    Lazy<String> lazy = Lazy.of(supplier());
    assertEquals("1", lazy.get());
    assertEquals("1", lazy.get());
    assertEquals(2, count);
  }

  @Test
  public void testGetSupplier() throws Exception {
    Lazy<String> lazy = Lazy.empty();
    assertEquals("1", lazy.get(supplier()));
    assertEquals("1", lazy.get(supplier()));
    assertEquals(2, count);
  }

  private Supplier<String> supplier() {
    return () -> String.valueOf(count++);
  }

}
