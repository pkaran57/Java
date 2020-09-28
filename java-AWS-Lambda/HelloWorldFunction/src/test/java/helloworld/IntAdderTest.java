package helloworld;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntAdderTest {

  @Test
  public void nonNullIntInput() {
    IntAdder intAdder = new IntAdder();
    Integer result = intAdder.handleRequest(1, null);
    assertEquals(2, result.intValue());
  }

  @Test
  public void nullIntInput() {
    IntAdder intAdder = new IntAdder();
    Integer result = intAdder.handleRequest(null, null);
    assertEquals(0, result.intValue());
  }
}
